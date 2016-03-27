package views.inventory

import models._
import play.api.libs.json.{Json, Writes}

/**
 * Created by Anup_sust on 8/17/2015.
 */
object UserView {

  implicit val UserWrites = new Writes[User] {
    def writes(user: User) = Json.obj(
      "id" -> user.id,
      "username" -> user.username,
      "email" -> user.email,
      "userRole" -> user.userRole,
      "activated" -> user.activated,
      "banned" -> user.banned
    )
  }

  implicit val UserRoleWrites = new Writes[UserRole] {
    def writes(userRole: UserRole) = Json.obj(
      "id" -> userRole.id,
      "name" -> userRole.name,
      "description" -> userRole.description,
      "methods" -> userRole.methods
    )
  }

  def renderUsers(users: Seq[User]) = Json.obj("records" -> Json.toJson(users))


  def renderUserRole(userRole: Seq[UserRole]) = Json.obj("records" -> Json.toJson(userRole))


  implicit val UserDetailsWrites = new Writes[(Int, String, String, Option[Int], Option[String], Option[Int], Option[Boolean])] {
    def writes(userDetails: (Int, String, String, Option[Int], Option[String], Option[Int], Option[Boolean])) = Json.obj(
      "id" -> userDetails._1,
      "name" -> userDetails._2,
      "role" -> userDetails._3,
      "role_id" -> userDetails._4,
      "email" -> userDetails._5,
      "activated" -> userDetails._6,
      "banned" -> userDetails._7
    )
  }

  implicit val UserDetailsClassWrites = new Writes[UserDetail] {
    def writes(userDetails: UserDetail) = Json.obj(
      "id" -> userDetails.id,
      "name" -> userDetails.username,
      "role" -> userDetails.userRoleName,
      "role_id" -> userDetails.userRole,
      "email" -> userDetails.email,
      "activated" -> userDetails.activated,
      "banned" -> userDetails.banned
    )
  }


  def renderUserDetailForTup(userDetails: Seq[(Int, String, String, Option[Int], Option[String], Option[Int], Option[Boolean])]) = Json.obj("records" -> Json.toJson(userDetails))

  def renderUserDetail(userDetails: UserDetail) = Json.toJson(userDetails)

}
