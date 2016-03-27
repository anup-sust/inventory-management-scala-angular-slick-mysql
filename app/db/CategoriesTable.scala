package db

import models.Category
import slick.driver.MySQLDriver.api._
import slick.lifted.Tag

/**
 * Created by Anup_sust on 8/17/2015.
 */
class CategoriesTable(tag: Tag) extends Table[Category](tag, "CATEGORIES") {

  def categoryID = column[Int]("categoryID", O.PrimaryKey)

  def categoryName = column[String]("categoryName")

  def description = column[Option[String]]("description")

  def pictureUrl = column[Option[String]]("pictureUrl")


  def * = (categoryID, categoryName, description, pictureUrl) <>(Category.tupled, Category.unapply _)

}
