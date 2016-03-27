package controllers

import models.Product
import services.ProductService
import play.api.mvc._

import play.api.libs.json._
import play.api.libs.functional.syntax._

import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

/**
 * Created by Anup_sust on 8/21/2015.
 */
class ProductController extends Controller {

  def getAllProducts = Action.async { implicit request =>
    ProductService.getAllProdut.map(result =>
      Ok(views.inventory.ProductView.renderProducts(result))
    )
  }

  def getProductById(id: Int) = Action.async { implicit request =>
    ProductService.getProdutById(id).map(result =>
      Ok(views.inventory.ProductView.renderProduct(result(0)))
    )
  }

  def getAllProductsDetail = Action.async { implicit request => {
    val page = request.queryString.get("page").getOrElse(Seq("0"))(0).toInt
    val pageSize = request.queryString.get("pageSize").getOrElse(Seq("10"))(0).toInt
    val search = request.getQueryString("search").map(q => q.toLowerCase()).getOrElse("")
    println(search);

    ProductService.getAllProductDetails(page, pageSize,search).map(result => {
      Ok(views.inventory.ProductView.renderProductDetail(result))
    }

    )
  }
  }


  def updateProduct = Action.async { implicit request => {

    request.body.asJson.map { requestJson =>
      val productId = (requestJson \ "id").asOpt[Int].getOrElse(0)
      val productName = (requestJson \ "productName").asOpt[String].getOrElse("Nothing")
      val supplierId = (requestJson \ "supplierId").asOpt[Int]
      val categoryId = (requestJson \ "categoryId").asOpt[Int]
      val ProductStatusID = (requestJson \ "ProductStatusID").asOpt[Int]
      val quantityPerUnit = (requestJson \ "quantityPerUnit").asOpt[Int]
      val unitPrice = (requestJson \ "unitPrice").asOpt[BigDecimal]
      val unitsInStock = (requestJson \ "unitsInStock").asOpt[Int]
      val unitsOnOrder = (requestJson \ "unitsOnOrder").asOpt[Int]
      val about = (requestJson \ "about").asOpt[String]
      val image = (requestJson \ "image").asOpt[String]
      val created = (requestJson \ "created").asOpt[String]

      ProductService.updateProduct(Product(productId, productName, supplierId, categoryId, ProductStatusID, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, Some(0), Some(false), about, image, created)).map((index) => {
        println(index)
        Ok(index.toString)
      })


    }.getOrElse {
      Future.successful(BadRequest("Expecting Json data"))
    }

  }
  }


  def addProduct = Action.async { implicit request => {

    request.body.asJson.map { requestJson =>
      val productId = (requestJson \ "id").asOpt[Int].getOrElse(0)
      val productName = (requestJson \ "productName").asOpt[String].getOrElse("Nothing")
      val supplierId = (requestJson \ "supplierId").asOpt[Int]
      val categoryId = (requestJson \ "categoryId").asOpt[Int]
      val ProductStatusID = (requestJson \ "ProductStatusID").asOpt[Int]
      val quantityPerUnit = (requestJson \ "quantityPerUnit").asOpt[Int]
      val unitPrice = (requestJson \ "unitPrice").asOpt[BigDecimal]
      val unitsInStock = (requestJson \ "unitsInStock").asOpt[Int]
      val unitsOnOrder = (requestJson \ "unitsOnOrder").asOpt[Int]
      val about = (requestJson \ "about").asOpt[String]
      val image = (requestJson \ "image").asOpt[String]
      val created = (requestJson \ "created").asOpt[String]

      println(created)

      ProductService.addProduct(Product(productId,productName,supplierId,categoryId,ProductStatusID,quantityPerUnit,unitPrice,unitsInStock,unitsOnOrder, Some(0), Some(false), about, image, created)).map((index)=>{
        println(index)
        Ok(index.toString)
      })


    }.getOrElse {
      Future.successful(BadRequest("Expecting Json data"))
    }

  }
  }

  def deleteProduct(id: Int) = Action.async { implicit request =>
    ProductService.deleteProduct(id).map(res=> Ok(""))
  }



}

object ProductController extends ProductController
