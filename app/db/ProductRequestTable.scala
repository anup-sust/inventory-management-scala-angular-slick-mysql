package db

import models.ProductRequest

import org.joda.time.DateTime
import com.github.tototoshi.slick.JdbcJodaSupport._

import slick.driver.MySQLDriver.api._
import slick.lifted.Tag

/**
 * Created by Anup_sust on 8/17/2015.
 */
class ProductRequestTable(tag: Tag) extends Table[ProductRequest](tag, "productrequest") {

  def id = column[Int]("id", O.PrimaryKey)

  def requestorId = column[Int]("requestorId")

  def productId = column[Int]("productId")

  def requestStatus = column[Option[String]]("requestStatus")

  def token = column[Option[String]]("token")

  def quantity = column[Option[Int]]("quantity")

  def updateInfo = column[Option[String]]("updateInfo")

  def requestAdded = column[Option[DateTime]]("requestAdded")


  def * = (id, requestorId, productId, requestStatus, token, quantity, updateInfo, requestAdded) <>(ProductRequest.tupled, ProductRequest.unapply _)

  def requestor =
    foreignKey(
      "FK_request_user",
      requestorId,
      TableQuery[UsersTable]
    )(
        _.id,
        onUpdate=ForeignKeyAction.Cascade,
        onDelete=ForeignKeyAction.Cascade
      )

  def product =
    foreignKey(
      "FK_request_product",
      productId,
      TableQuery[ProductsTable]
    )(
        _.productId,
        onUpdate=ForeignKeyAction.Cascade,
        onDelete=ForeignKeyAction.Cascade
      )

}
