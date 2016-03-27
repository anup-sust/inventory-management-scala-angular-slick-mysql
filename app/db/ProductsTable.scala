package db

import com.github.tototoshi.slick.MySQLJodaSupport._
import models.Product
import org.joda.time.DateTime
import slick.driver.MySQLDriver.api._
import slick.lifted.Tag

/**
 * Created by Anup_sust on 8/17/2015.
 */
class ProductsTable(tag: Tag) extends Table[Product](tag, "PRODUCTS") {

  def productId = column[Int]("ProductID", O.PrimaryKey, O.AutoInc)

  def productName = column[String]("ProductName")

  def supplierId = column[Option[Int]]("SupplierID")

  def categoryId = column[Option[Int]]("CategoryID")

  def productStatusID = column[Option[Int]]("ProductStatusID")

  def quantityPerUnit = column[Option[Int]]("QuantityPerUnit")

  def unitPrice = column[Option[BigDecimal]]("UnitPrice")

  def unitsInStock = column[Option[Int]]("UnitsInStock")

  def unitsOnOrder = column[Option[Int]]("unitsOnOrder")

  def reorderLevel = column[Option[Int]]("reorderLevel")

  def discontinued = column[Option[Boolean]]("discontinued")

  def about = column[Option[String]]("about")

  def image = column[Option[String]]("image")

  def created = column[Option[String]]("created")

  def * = (productId, productName, supplierId, categoryId, productStatusID, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel, discontinued, about, image, created) <>(Product.tupled, Product.unapply _)

  def supplier =
    foreignKey(
      "FK_Products_Suppliers",
      supplierId,
      TableQuery[SuppliersTable]
    )(
        _.supplierID,
        onUpdate=ForeignKeyAction.Cascade,
        onDelete=ForeignKeyAction.Cascade
      )

  def category =
    foreignKey(
      "FK_Products_Categories",
      categoryId,
      TableQuery[CategoriesTable]
    )(
        _.categoryID,
        onUpdate=ForeignKeyAction.Cascade,
        onDelete=ForeignKeyAction.Cascade
      )

  def productStatus =
    foreignKey(
      "FK_Products_PStatus",
      productStatusID,
      TableQuery[ProductStatusTable]
    )(
        _.statusID,
        onUpdate=ForeignKeyAction.Cascade,
        onDelete=ForeignKeyAction.Cascade
      )
}
