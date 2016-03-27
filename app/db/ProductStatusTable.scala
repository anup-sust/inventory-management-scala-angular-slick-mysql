package db

import models.ProductStatus
import slick.driver.MySQLDriver.api._
import slick.lifted.Tag

/**
 * Created by Anup_sust on 8/17/2015.
 */
class ProductStatusTable(tag: Tag) extends Table[ProductStatus](tag, "PRODUCTSTATUS") {

  def statusID= column[Int]("statusID", O.PrimaryKey)
  def StatusName= column[String]("StatusName")
  def description= column[Option[String]]("description")

  def * = (statusID, StatusName, description) <>(ProductStatus.tupled, ProductStatus.unapply _)
}
