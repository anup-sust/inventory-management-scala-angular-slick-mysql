package controllers

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._
import services.UserRoleService

/**
 * Created by Anup_sust on 8/17/2015.
 */
class UserRoleController extends Controller {

  val userSessionKey = "universityInvMgmt"

  def getAllUserRoles = Action.async { implicit request =>
    UserRoleService.getAll.map(result =>
      Ok(views.inventory.UserView.renderUserRole(result))
    )
  }


}

object UserRoleController extends UserRoleController
