package db

import com.github.tototoshi.slick.MySQLJodaSupport._
import models.UserRole
import org.joda.time.DateTime
import slick.driver.MySQLDriver.api._
import slick.lifted.Tag

/**
 * Created by Anup_sust on 8/17/2015.
 */
class UsersRolesTable(tag: Tag) extends Table[UserRole](tag, "USERROLES") {

  def id = column[Option[Int]]("id", O.PrimaryKey)

  def name = column[String]("name")

  def description = column[Option[String]]("description")

  def methods = column[Option[String]]("methods")

  def * = (id, name, description, methods) <>(UserRole.tupled, UserRole.unapply _)

}
