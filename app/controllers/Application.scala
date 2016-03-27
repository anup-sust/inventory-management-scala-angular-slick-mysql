package controllers

import models._
import play.api._
import play.api.mvc._

import services.CommonService
import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def index2(x:Int) = Action {
    Ok(views.html.index("Your new application is ready."))
  }

//  def authenticate = Action { implicit request =>
//    //    println(request.headers.)
//    Ok("2")
//  }

  def getSuppliers = Action.async { implicit request =>
    CommonService.getAllSUppliers.map(result =>
      Ok(views.inventory.CommonView.renderSuppliers(result))
    )
  }

  def getSupplierById(id: Int) = Action.async { implicit request =>
    CommonService.getSupplierById(id).map(result =>
      Ok(views.inventory.CommonView.renderSupplier(result(0)))
    )
  }

  def updateSupplier = Action.async { implicit request => {
    request.body.asJson.map { requestJson =>
      val supplierID = (requestJson \ "id").asOpt[Int].getOrElse(0)
      val companyName = (requestJson \ "companyName").asOpt[String].getOrElse("Nothing")
      val contactName = (requestJson \ "contactName").asOpt[String]
      val contactTitle = (requestJson \ "contactTitle").asOpt[String]
      val address = (requestJson \ "address").asOpt[String]
      val city = (requestJson \ "city").asOpt[String]
      val region = (requestJson \ "region").asOpt[String]
      val postalCode = (requestJson \ "postalCode").asOpt[String]
      val country = (requestJson \ "country").asOpt[String]
      val phone = (requestJson \ "phone").asOpt[String]
      val homePage = (requestJson \ "homePage").asOpt[String]


      CommonService.updateSupplier(Supplier(supplierID, companyName, contactName, contactTitle, address, city, region, postalCode, country, phone, homePage)).map((index) => {
        Ok("2")
      })


    }.getOrElse {
      Future.successful(BadRequest("Expecting Json data"))
    }

  }
  }


  def addSupplier = Action.async { implicit request => {

    request.body.asJson.map { requestJson =>
      val supplierID = (requestJson \ "id").asOpt[Int].getOrElse(0)
      val companyName = (requestJson \ "companyName").asOpt[String].getOrElse("Nothing")
      val contactName = (requestJson \ "contactName").asOpt[String]
      val contactTitle = (requestJson \ "contactTitle").asOpt[String]
      val address = (requestJson \ "address").asOpt[String]
      val city = (requestJson \ "city").asOpt[String]
      val region = (requestJson \ "region").asOpt[String]
      val postalCode = (requestJson \ "postalCode").asOpt[String]
      val country = (requestJson \ "country").asOpt[String]
      val phone = (requestJson \ "phone").asOpt[String]
      val homePage = (requestJson \ "homePage").asOpt[String]


      CommonService.addSupplier(Supplier(supplierID, companyName, contactName, contactTitle, address, city, region, postalCode, country, phone, homePage)).map((index) => {
        Ok("2")
      })


    }.getOrElse {
      Future.successful(BadRequest("Expecting Json data"))
    }

  }
  }

  def deleteSupplier(id: Int) = Action.async { implicit request =>
    CommonService.deleteSupplier(id).map(res=> Ok(""))
  }


  def getCategories = Action.async { implicit request =>
    CommonService.getAllCategories.map(result =>
      Ok(views.inventory.CommonView.renderCategories(result))
    )
  }

  def getCategoryById(id: Int) = Action.async { implicit request =>
    CommonService.getCategoryById(id).map(result =>
      Ok(views.inventory.CommonView.renderCategory(result(0)))
    )
  }

  def updateCategory = Action.async { implicit request => {
    request.body.asJson.map { requestJson =>
      val categoryID = (requestJson \ "id").asOpt[Int].getOrElse(0)
      val categoryName = (requestJson \ "categoryName").asOpt[String].getOrElse("Nothing")
      val description = (requestJson \ "description").asOpt[String]
      val pictureUrl = (requestJson \ "pictureUrl").asOpt[String]

      CommonService.updateCategory(Category(categoryID, categoryName, description, pictureUrl)).map((index) => {
        Ok("2")
      })

    }.getOrElse {
      Future.successful(BadRequest("Expecting Json data"))
    }

  }
  }

  def addCategory = Action.async { implicit request => {
    request.body.asJson.map { requestJson =>
      val categoryID = (requestJson \ "id").asOpt[Int].getOrElse(0)
      val categoryName = (requestJson \ "categoryName").asOpt[String].getOrElse("Nothing")
      val description = (requestJson \ "description").asOpt[String]
      val pictureUrl = (requestJson \ "pictureUrl").asOpt[String]

      CommonService.addCategory(Category(categoryID, categoryName, description, pictureUrl)).map((index) => {
        Ok("2")
      })

    }.getOrElse {
      Future.successful(BadRequest("Expecting Json data"))
    }

  }
  }

  def deleteCategory(id: Int) = Action.async { implicit request =>
    CommonService.deleteCategory(id).map(res=> Ok(""))
  }

  def getProductStatus = Action.async { implicit request =>
    CommonService.getAllProductStatus.map(result =>
      Ok(views.inventory.CommonView.renderProductStatus(result))
    )
  }

  def getProductStatusById(id: Int) = Action.async { implicit request =>
    CommonService.getProductStatusById(id).map(result =>
      Ok(views.inventory.CommonView.renderProductStatus(result(0)))
    )
  }

  def updateProductStatus = Action.async { implicit request => {
    request.body.asJson.map { requestJson =>
      val statusID = (requestJson \ "id").asOpt[Int].getOrElse(0)
      val StatusName = (requestJson \ "StatusName").asOpt[String].getOrElse("Nothing")
      val description = (requestJson \ "description").asOpt[String]

      CommonService.updateProductStatus(ProductStatus(statusID, StatusName, description)).map((index) => {
        Ok("2")
      })

    }.getOrElse {
      Future.successful(BadRequest("Expecting Json data"))
    }

  }
  }

  def addProductStatus = Action.async { implicit request => {
    request.body.asJson.map { requestJson =>
      val statusID = (requestJson \ "id").asOpt[Int].getOrElse(0)
      val StatusName = (requestJson \ "StatusName").asOpt[String].getOrElse("Nothing")
      val description = (requestJson \ "description").asOpt[String]

      CommonService.addProductStatus(ProductStatus(statusID, StatusName, description)).map((index) => {
        Ok("2")
      })

    }.getOrElse {
      Future.successful(BadRequest("Expecting Json data"))
    }

  }
  }

  def deleteProductStatus(id: Int) = Action.async { implicit request =>
    CommonService.deleteProductStatus(id).map(res=> Ok(""))
  }

}

object Application extends Application