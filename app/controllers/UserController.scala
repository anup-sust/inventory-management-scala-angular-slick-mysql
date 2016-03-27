package controllers


import play.api._
import play.api.libs.iteratee.Done
import play.api.mvc._
import services.UserService

import models._

import play.api.libs.concurrent.Execution.Implicits.defaultContext

import play.api.libs.Crypto
import views.inventory.UserView

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

import play.api.libs.json.{Json, Writes}

/**
 * Created by Anup_sust on 8/17/2015.
 */
class UserController extends Controller {

  val userSessionKey = "universityInvMgmt"

  def getAllUsers = Action.async { implicit request =>
    UserService.getAll.map(result =>
      Ok(views.inventory.UserView.renderUsers(result))
    )
  }

  def updateUser = Action.async { implicit request => {

    request.body.asJson.map { requestJson =>
      val id = (requestJson \ "id").asOpt[Int].getOrElse(0)
      val username = (requestJson \ "username").asOpt[String].getOrElse("Nothing")
      val userRole = (requestJson \ "userRole").asOpt[Int]
      val email = (requestJson \ "email").asOpt[String]
      val activated = (requestJson \ "activated").asOpt[Int]
      val banned = (requestJson \ "banned").asOpt[Boolean]

      UserService.updateUser(User(id, username, null, userRole, email, activated, banned)).map((index) => {
        Ok(index.toString)
      })

    }.getOrElse {
      Future.successful(BadRequest("Expecting Json data"))
    }

  }
  }

  def signup = Action.async { implicit request => {

    request.body.asJson.map { requestJson =>
      val username = (requestJson \ "username").asOpt[String].getOrElse("Nothing")
      val password = (requestJson \ "password").asOpt[String].getOrElse("password")
      val email = (requestJson \ "email").asOpt[String]


      UserService.getByName(username).flatMap(res=>{
        if(res.length>0) Future.successful(Ok(UserView.renderUserDetail(UserDetail())))
        else
        UserService.addUser(User(0, username, password, Some(4), email, None, None)).flatMap((index) => {
          UserService.getUserDetalByNamePass(username, password).map(result => {
            if (result.length > 0) Ok(UserView.renderUserDetail(UserDetail(result(0)._1, result(0)._2, result(0)._3, result(0)._4, result(0)._5, result(0)._6, result(0)._7))).withSession(userSessionKey -> (username + "||_||" + password))
            else Ok(UserView.renderUserDetail(UserDetail()))
          }
          )
        })

      })


    }.getOrElse {
      Future.successful(BadRequest("Expecting Json data"))
    }

  }
  }

  def getAllUsersDetail = Action.async { implicit request =>
    UserService.getAllUserDetails.map(result =>
      Ok(views.inventory.UserView.renderUserDetailForTup(result))
    )
  }

  def deleteProduct(id: Int) = Action.async { implicit request =>
    UserService.deleteUser(id).map(res=> Ok(""))
  }

  def login = Action.async { implicit request =>
    request.body.asJson.map(requestJson => {
      val userId = (requestJson \ "userId").asOpt[String].getOrElse("Nothing")
      val password = (requestJson \ "password").asOpt[String].getOrElse("Nothing")

      UserService.getUserDetalByNamePass(userId, password).map(result => {
        if (result.length > 0) Ok(UserView.renderUserDetail(UserDetail(result(0)._1, result(0)._2, result(0)._3, result(0)._4, result(0)._5, result(0)._6, result(0)._7))).withSession(userSessionKey -> (userId + "||_||" + password))
        else Ok(UserView.renderUserDetail(UserDetail()))
      }
      )
    }
    ).get
  }

  //  def authenticate = Action.async{ implicit request =>
  //    request.session.get(userSessionKey).map(user=>{
  //            val userId = user.split("\\|\\|\\_\\|\\|")(0)
  //            val password = user.split("\\|\\|\\_\\|\\|")(1)
  //            UserService.getUserDetalByNamePass(userId, password).map(result => {
  //              println(result)
  //              if (result.length > 0) {
  //                Ok(UserView.renderUserDetail(result))
  //              }
  //              else Ok("20")
  //            })
  //    }).getOrElse(Future.successful(Ok("20")))
  //  }

  def authenticate2 = AuthenticationHelper { user =>
    Action { implicit request =>
      Ok(UserView.renderUserDetail(user))
    }
  }

  def logout = Action {
    Redirect(routes.Application.index).withNewSession.flashing(
      "success" -> "You are now logged out."
    )
  }


  def AuthenticationHelper(action: UserDetail => EssentialAction): EssentialAction = {
    EssentialAction { request => {
      println(request.session.get(userSessionKey))
      request.session.get(userSessionKey).map(user => {

        val userId = user.split("\\|\\|\\_\\|\\|")(0)
        val password = user.split("\\|\\|\\_\\|\\|")(1)
        var result = Await.result(UserService.getUserDetalByNamePass(userId, password), Duration.Inf)
        // UserService.getUserDetalByNamePass(userId, password).map(result => {
        println(result)
        if (result.length > 0) {
          action(UserDetail(result(0)._1, result(0)._2, result(0)._3, result(0)._4, result(0)._5, result(0)._6, result(0)._7))(request)
        }
        else action(UserDetail())(request)
        // })
      }).getOrElse(Done(Ok(UserView.renderUserDetail(UserDetail()))))
    }
    }
  }

  def checkAvailability() ={
  }


  //  def addUser = Action.async { implicit request => {
  //    request.body.asJson.map { requestJson =>
  //      val id = (requestJson \ "id").asOpt[Int].getOrElse(0)
  //      val username = (requestJson \ "username").asOpt[String].getOrElse("Nothing")
  //      val password = (requestJson \ "password").asOpt[String].getOrElse("Nothing")
  //      val userRole = (requestJson \ "userRole").asOpt[Int]
  //      val email = (requestJson \ "email").asOpt[String]
  //      val activated = (requestJson \ "activated").asOpt[Int]
  //
  //      UserService.addUser(User(id,username,password,userRole,email,activated)).map((index) => {
  //        Ok("2")
  //      })
  //
  //    }.getOrElse {
  //      Future.successful(BadRequest("Expecting Json data"))
  //    }
  //
  //  }
  //  }

  //  def authenticate = Action.async { implicit request =>
  //
  //    request.session.get(userSessionKey).map(  user => {
  ////      println(user)
  ////      val userId = user.split("\\|\\|\\_\\|\\|")(0)
  ////      val password = user.split("\\|\\|\\_\\|\\|")(1)
  ////      println(userId)
  ////      println(password)
  //
  ////      UserService.getByNamePass(userId, password).map(result => {
  ////        println(result)
  ////        if (result.length > 0) Ok("20")
  ////        else Ok("20")
  ////      }
  //
  //
  //    }).get
  //
  //
  //
  //
  //
  //  }

}

object UserController extends UserController
