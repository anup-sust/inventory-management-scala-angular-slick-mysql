package controllers

import models.{ProductRequest, UserDetail}
import play.api.libs.iteratee.Done
import play.api.mvc.{EssentialAction, Action, Controller}
import services.{UserService, ProductRequestService}
import views.inventory.UserView

import scala.concurrent.{Future, Await}
import scala.concurrent.duration.Duration

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by Anup_sust on 8/29/2015.
 */
class ProductRequestController extends Controller {
  val userSessionKey = "universityInvMgmt"

  def getAllProductsRequest = Action { implicit request =>
    Ok("")
  }

  def getAllProductsRequestDetail = AuthenticationHelper { implicit user =>
    Action.async { implicit request =>{

      val page = request.queryString.get("page").getOrElse(Seq("0"))(0).toInt
      val pageSize = request.queryString.get("pageSize").getOrElse(Seq("10"))(0).toInt
      val search = request.getQueryString("search").map(q => q.toLowerCase()).getOrElse("")

      ProductRequestService.getAllProductRequestDetails(page, pageSize,search).map(result => {
     //   println(result)
        Ok(views.inventory.ProductView.renderProductRequestDetails(result))
      }
      )
    }
    }
  }

  def getProductRequestDetailByProductId(id: Int) =AuthenticationHelper { implicit user =>
    Action.async { implicit request =>{
      val status = request.getQueryString("status")

      println(status)
      ProductRequestService.getAllProductRequestDetailsByProductId(id,status).map(result => {
        println(result)
        Ok(views.inventory.ProductView.renderProductRequestDetails((result, result.length)))
      }
      )
    }
    }
  }

  def addProductRequest = AuthenticationHelper { implicit user =>
    Action.async { implicit request => {

      request.body.asJson.map { requestJson =>
        val productId = (requestJson \ "id").asOpt[Int].getOrElse(0)
        val quantity = (requestJson \ "quantity").asOpt[Int]

        ProductRequestService.addProductRequest(ProductRequest(0, user.id, productId, Some("Pending"), None, quantity)).map((index) => {
          println(index)
          Ok(index.toString)
        })

      }.getOrElse {
        Future.successful(BadRequest("Expecting Json data"))
      }

    }
    }
  }

  def deleteProductRequest(id: Int) = Action.async { implicit request =>
    ProductRequestService.deleteProductRequest(id).map(res=> Ok(""))
  }

 def updateProductRequest =AuthenticationHelper { implicit user =>
   Action.async { implicit request => {

     request.body.asJson.map { requestJson =>
       val id = (requestJson \ "id").asOpt[Int].getOrElse(0)
       val requestorId = (requestJson \ "requestorId").asOpt[Int].getOrElse(0)
       val productId = (requestJson \ "productId").asOpt[Int].getOrElse(0)
       val requestStatus = (requestJson \ "requestStatus").asOpt[String]
       val token = (requestJson \ "token").asOpt[String]
       val quantity = (requestJson \ "quantity").asOpt[Int]

       ProductRequestService.updateProductRequest(ProductRequest(id, requestorId, productId, requestStatus, token, quantity)).map((result) => {
         // println(result)
         Ok(views.inventory.ProductView.renderProductRequestDetail(result))
       })

     }.getOrElse {
       Future.successful(BadRequest("Expecting Json data"))
     }

   }
   }
 }

  def AuthenticationHelper(action: UserDetail => EssentialAction): EssentialAction = {
    EssentialAction { request => {
      println(request.session.get(userSessionKey))
      request.session.get(userSessionKey).map(user => {

        val userId = user.split("\\|\\|\\_\\|\\|")(0)
        val password = user.split("\\|\\|\\_\\|\\|")(1)
        var result = Await.result(UserService.getUserDetalByNamePass(userId, password), Duration.Inf)
        // UserService.getUserDetalByNamePass(userId, password).map(result => {
        if (result.length > 0) {
          action(UserDetail(result(0)._1, result(0)._2, result(0)._3, result(0)._4, result(0)._5, result(0)._6, result(0)._7))(request)
        }
        else action(UserDetail())(request)
        // })
      }).getOrElse(Done(Ok(UserView.renderUserDetail(UserDetail()))))
    }
    }
  }

}

object ProductRequestController extends ProductRequestController
