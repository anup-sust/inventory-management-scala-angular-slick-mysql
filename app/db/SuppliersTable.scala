package db

import models.Supplier
import slick.driver.MySQLDriver.api._
import slick.lifted.Tag

/**
 * Created by Anup_sust on 8/17/2015.
 */
class SuppliersTable(tag: Tag) extends Table[Supplier](tag, "SUPPLIERS") {

  def supplierID = column[Int]("supplierID", O.PrimaryKey)

  def companyName = column[String]("companyName")

  def contactName = column[Option[String]]("contactName")

  def contactTitle = column[Option[String]]("contactTitle")

  def address = column[Option[String]]("address")

  def city = column[Option[String]]("city")

  def region = column[Option[String]]("region")

  def postalCode = column[Option[String]]("postalCode")

  def country = column[Option[String]]("country")

  def phone = column[Option[String]]("phone")

  def homePage = column[Option[String]]("homePage")


  def * = (supplierID, companyName, contactName, contactTitle, address, city, region, postalCode, country, phone, homePage) <>(Supplier.tupled, Supplier.unapply _)

}
