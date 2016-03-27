package db

import models.User
import org.joda.time.DateTime
import slick.driver.MySQLDriver.api._
import slick.lifted.Tag

import com.github.tototoshi.slick.MySQLJodaSupport._

/**
 * Created by Anup_sust on 8/17/2015.
 */
class UsersTable(tag: Tag) extends Table[User](tag, "USERS") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def username = column[String]("USERNAME")

  def password = column[String]("PASSWORD")

  def userRole = column[Option[Int]]("USERROLE")

  def email = column[Option[String]]("EMAIL")

  def activated = column[Option[Int]]("ACTIVATED")

  def banned = column[Option[Boolean]]("BANNED")



  def * = (id, username, password, userRole, email, activated, banned) <>(User.tupled, User.unapply _)


    def UserRole =
      foreignKey(
        "FK_USERS_UROLE",
        userRole,
        TableQuery[UsersRolesTable]
      )(
          _.id,
          onUpdate=ForeignKeyAction.Cascade,
          onDelete=ForeignKeyAction.Cascade
        )
}
