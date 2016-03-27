package services

import db.ProductRequestTable
import models._

import org.joda.time.DateTime
import com.github.tototoshi.slick.JdbcJodaSupport._

import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import slick.driver.MySQLDriver.api._
/**
 * Created by Anup_sust on 8/17/2015.
 */
object ProductRequestService extends HasDatabaseConfig[JdbcProfile] {
  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)

  val productRequests = TableQuery[ProductRequestTable]

  def insert(productRequest: ProductRequest) ={
   // productRequest.requestAdded = Some(DateTime.now())
    dbConfig.db.run(productRequests += productRequest)
  }

  def getAllProductRequest(implicit userDetail: UserDetail) =
    userDetail.userRoleName match {
      case "Supar Admin" | "Admin" => dbConfig.db.run(productRequests.result)
      case _ => dbConfig.db.run(productRequests.filter(x => x.requestorId === userDetail.id).result)
    }

  def updateProductRequest(productRequest: ProductRequest)(implicit userDetail: UserDetail) = {
    if (productRequest.requestStatus.getOrElse("") == "Approved" && !productRequest.token.isDefined) {
      productRequest.token = Some(java.util.UUID.randomUUID.toString)
      productRequest.updateInfo = Some("Approved By " + userDetail.username)
    }
    else if (productRequest.requestStatus.getOrElse("") == "Denied") {
      productRequest.token = None
      productRequest.updateInfo = Some("Denied By " + userDetail.username)
    }
    else if(productRequest.requestStatus.getOrElse("") == "Delivered"){
      productRequest.updateInfo = Some("Delivered By " + userDetail.username)
    }
    else  productRequest.updateInfo = None
    dbConfig.db.run(productRequests.filter(x => x.id === productRequest.id).update(productRequest)).map(y => productRequest)
  }

  def addProductRequest(productRequest: ProductRequest) = {
    productRequest.requestAdded = Some(DateTime.now())
    dbConfig.db.run(productRequests += productRequest)
  }

  def deleteProductRequest(id: Int) = dbConfig.db.run(productRequests.filter(_.id === id).delete)

  val productRequestDetails = for {
    productRQST <- productRequests
    product <- productRQST.product
    requestor <- productRQST.requestor
  } yield (
      productRQST.id,
      productRQST.requestorId,
      productRQST.productId,
      productRQST.requestStatus,
      productRQST.token,
      product.productName,
      requestor.username,
      productRQST.quantity,
      productRQST.updateInfo,
      productRQST.requestAdded
      )

  type ProductRequestDetailTupl = (Int, Int, Int, Option[String], Option[String], String, String, Option[Int], Option[String], Option[DateTime])

  def getAllProductRequestDetails(page: Int, pageSize: Int, search: String = "")(implicit userDetail: UserDetail): Future[(Seq[ProductRequestDetail], Int)] = {
    var proRqstDetails = productRequestDetails

    userDetail.userRoleName match {
      case "Supar Admin" | "Admin" => proRqstDetails = proRqstDetails
      case _ =>  proRqstDetails = proRqstDetails.filter(x => x._2 === userDetail.id)
    }
    if(search.length>0) proRqstDetails = proRqstDetails.filter(x=>(x._4.like("%"+search+"%"))||(x._5.like("%"+search+"%"))||(x._6.like("%"+search+"%"))||(x._7.like("%"+search+"%")))

    var paginatedData = proRqstDetails.sortBy(_._1.desc).drop(page*pageSize).take(pageSize)

    var resultFtr = Future.sequence(List(dbConfig.db.run(paginatedData.result), dbConfig.db.run(proRqstDetails.length.result)))

    resultFtr.map(la=>{
      var tup = la(0).asInstanceOf[Seq[ProductRequestDetailTupl]]
      var prorqstseq= tup.map(t=> ProductRequestDetail(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10))
      (prorqstseq, la(1).asInstanceOf[Int])
    })

   // dbConfig.db.run(paginatedData.result).map(s => s.map(t => ProductRequestDetail(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8)))
  }


//  def getAllProductRequestDetails(page: Int, pageSize: Int, search: String = "")(implicit userDetail: UserDetail): Future[Seq[ProductRequestDetail]] = {
//    var proRqstDetails = productRequestDetails
//    if(search.length>0) proRqstDetails = proRqstDetails.filter(x=>(x._4.like("%"+search+"%"))||(x._5.like("%"+search+"%"))||(x._6.like("%"+search+"%"))||(x._7.like("%"+search+"%")))
//    (userDetail.userRoleName match {
//      case "Supar Admin" | "Admin" => dbConfig.db.run(proRqstDetails.result)
//      case _ => dbConfig.db.run({
//        proRqstDetails.filter(x => x._2 === userDetail.id).result
//      })
//    }).map(s => s.map(t => ProductRequestDetail(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8)))
//  }

  def getAllProductRequestDetailsByProductId(productId: Int, status: Option[String] = None)(implicit userDetail: UserDetail): Future[Seq[ProductRequestDetail]] = {
    var prDetail = productRequestDetails
    if (status.isDefined) prDetail = prDetail.filter(st => st._4 === status)
    dbConfig.db.run({
      prDetail.filter(x => x._2 === userDetail.id && x._3 === productId).result
    }).map(s => s.map(t => ProductRequestDetail(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8)))
  }

  //  def insert(product: Product) = dbConfig.db.run(Products += product)
  //
  // // dbConfig.db.run(Products += (Product(4,"xxx",Some(1),Some(1),Some(1),Some(1),Some(1000),Some(15),None, None, None)))
  // // println("=======================")
  //
  //  def getAllProdut = dbConfig.db.run({
  //    Products.result
  //  })
  //
  //  def getProdutById(id: Int) = dbConfig.db.run(Products.filter(x => x.productId === id).take(1).result)
  //
  //  def updateProduct(product: Product) =  dbConfig.db.run(Products.filter(x => x.productId === product.productId).update(product))
  //
  //  def addProduct(product: Product) = dbConfig.db.run(Products += product)
  //
  // // println(addProduct(Product(4,"aaa",Some(1),Some(1),Some(1),Some(1),Some(1000),Some(15),None, None, None)))
  //
  //  val productDetails = for {
  //    product <- Products
  //    supplier <- product.supplier
  //    category <- product.category
  //    prodtStatus <- product.productStatus
  //  } yield (
  //      product.productId,
  //      product.productName,
  //      supplier.companyName,
  //      category.categoryName,
  //      prodtStatus.StatusName,
  //      product.quantityPerUnit,
  //      product.unitPrice,
  //      product.unitsInStock,
  //      product.unitsOnOrder,
  //      product.reorderLevel,
  //      product.discontinued
  //      )
  //
  //  def getAllProductDetails(page: Int, pageSize: Int, search: String = "") ={
  //    var filtered = productDetails
  //    if(search.length>0) filtered = productDetails.filter(x=>(x._2.like("%"+search+"%"))||(x._3.like("%"+search+"%")))
  //
  //    Future.sequence(List(dbConfig.db.run(filtered.sortBy(_._1.desc).drop(page*pageSize).take(pageSize).result),dbConfig.db.run(filtered.length.result)))
  //  }
  //
  //  def getAllProductDetailsById(id: Int) = dbConfig.db.run(productDetails.filter(x => x._1 === id).result)


}
