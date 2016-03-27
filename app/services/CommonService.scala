package services

import db.{SuppliersTable, CategoriesTable, ProductStatusTable}
import models._
import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile

import scala.concurrent.Future

import slick.driver.MySQLDriver.api._
/**
 * Created by Anup_sust on 8/23/2015.
 */
object CommonService extends HasDatabaseConfig[JdbcProfile] {
  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)


  val suppliers = TableQuery[SuppliersTable]

  def getAllSUppliers: Future[Seq[Supplier]] = dbConfig.db.run(suppliers.result)

  def getSupplierById(id: Int) = dbConfig.db.run(suppliers.filter(x => x.supplierID === id).take(1).result)

  def updateSupplier(supplier: Supplier): Future[Int] = dbConfig.db.run(suppliers.filter(x => x.supplierID === supplier.supplierID).update(supplier))

  def addSupplier(supplier: Supplier) = dbConfig.db.run(suppliers += supplier)

  def deleteSupplier(id: Int) = dbConfig.db.run(suppliers.filter(_.supplierID === id).delete)

  val categories = TableQuery[CategoriesTable]

  def getAllCategories: Future[Seq[Category]] = dbConfig.db.run(categories.result)

  def getCategoryById(id: Int) = dbConfig.db.run(categories.filter(x => x.categoryID === id).take(1).result)

  def updateCategory(category: Category) = dbConfig.db.run(categories.filter(x => x.categoryID === category.categoryID).update(category))

  def addCategory(category: Category) = dbConfig.db.run(categories += category)

  def deleteCategory(id: Int) = dbConfig.db.run(categories.filter(_.categoryID === id).delete)

  val productStatus = TableQuery[ProductStatusTable]

  def getAllProductStatus: Future[Seq[ProductStatus]] = dbConfig.db.run(productStatus.result)

  def getProductStatusById(id: Int) = dbConfig.db.run(productStatus.filter(x => x.statusID === id).take(1).result)

  def updateProductStatus(pStatus: ProductStatus) = dbConfig.db.run(productStatus.filter(x => x.statusID === pStatus.statusID).update(pStatus))

  def addProductStatus(pStatus: ProductStatus) = dbConfig.db.run(productStatus += pStatus)

  def deleteProductStatus(id: Int) = dbConfig.db.run(productStatus.filter(_.statusID === id).delete)
}
