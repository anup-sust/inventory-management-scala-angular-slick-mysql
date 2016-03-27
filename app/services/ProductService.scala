package services

import db.ProductsTable
import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile
import models.Product
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

import slick.driver.MySQLDriver.api._

/**
 * Created by Anup_sust on 8/17/2015.
 */
object ProductService extends HasDatabaseConfig[JdbcProfile] {
  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)

  val Products = TableQuery[ProductsTable]


  def insert(product: Product) = dbConfig.db.run(Products += product)

 // dbConfig.db.run(Products += (Product(4,"xxx",Some(1),Some(1),Some(1),Some(1),Some(1000),Some(15),None, None, None)))
 // println("=======================")

  def getAllProdut = dbConfig.db.run({
    Products.result
  })

  def getProdutById(id: Int) = dbConfig.db.run(Products.filter(x => x.productId === id).take(1).result)

  def updateProduct(product: Product): Future[Int] =  dbConfig.db.run(Products.filter(x => x.productId === product.productId).update(product))

  def addProduct(product: Product) = dbConfig.db.run(Products += product)

  def deleteProduct(id: Int) : Future[Int] = dbConfig.db.run(Products.filter(x => x.productId === id).delete)

 // println(addProduct(Product(4,"aaa",Some(1),Some(1),Some(1),Some(1),Some(1000),Some(15),None, None, None)))

  val productDetails = for {
    product <- Products
    supplier <- product.supplier
    category <- product.category
    prodtStatus <- product.productStatus
  } yield (
      product.productId,
      product.productName,
      supplier.companyName,
      category.categoryName,
      prodtStatus.StatusName,
      product.quantityPerUnit,
      product.unitPrice,
      product.unitsInStock,
      product.unitsOnOrder,
      product.reorderLevel,
      product.discontinued
      )

  def getAllProductDetails(page: Int, pageSize: Int, search: String = "") : Future[List[Any]] ={
    var filtered = productDetails
    if(search.length>0) filtered = productDetails.filter(x=>(x._2.like("%"+search+"%"))||(x._3.like("%"+search+"%"))||(x._4.like("%"+search+"%"))||(x._5.like("%"+search+"%")))

    Future.sequence(List(dbConfig.db.run(filtered.sortBy(_._1.desc).drop(page*pageSize).take(pageSize).result),
                         dbConfig.db.run(filtered.length.result)))
  }

  def getAllProductDetailsById(id: Int) = dbConfig.db.run(productDetails.filter(x => x._1 === id).result)


}
