package views.inventory

import models._
import play.api.libs.json.{Json, Writes}

/**
 * Created by Anup_sust on 8/17/2015.
 */
object CommonView {

  implicit val supplierWrites = new Writes[Supplier] {
    def writes(supplier: Supplier) = Json.obj(
      "id" -> supplier.supplierID,
      "companyName" -> supplier.companyName,
      "contactName" -> supplier.contactName,
      "contactTitle" -> supplier.contactTitle,
      "address" -> supplier.address,
      "city" -> supplier.city,
      "region" -> supplier.region,
      "postalCode" -> supplier.postalCode,
      "country" -> supplier.country,
      "phone" -> supplier.phone,
      "homePage" -> supplier.homePage
    )
  }

  implicit val categoriesWrites = new Writes[Category] {
    def writes(category: Category) = Json.obj(
      "id" -> category.categoryID,
      "categoryName" -> category.categoryName,
      "description" -> category.description,
      "pictureUrl" -> category.pictureUrl
    )
  }

  implicit val productStatusWrites = new Writes[ProductStatus] {
    def writes(productStatus: ProductStatus) = Json.obj(
      "id" -> productStatus.statusID,
      "StatusName" -> productStatus.StatusName,
      "description" -> productStatus.description
    )
  }



  def renderSuppliers(suppliers: Seq[Supplier]) = Json.obj("records" -> Json.toJson(suppliers))

  def renderSupplier(supplier: Supplier) = Json.toJson(supplier)

  def renderCategories(categories: Seq[Category]) = Json.obj("records" -> Json.toJson(categories))

  def renderCategory(category: Category) = Json.toJson(category)

  def renderProductStatus(productStatus: Seq[ProductStatus]) = Json.obj("records" -> Json.toJson(productStatus))

  def renderProductStatus(productStatus: ProductStatus) = Json.toJson(productStatus)

//
//  def renderUserRole(userRole: Seq[UserRole]) = Json.obj("records" -> Json.toJson(userRole))
//
//
//  implicit val OrderDetailsWrites = new Writes[(Int, String, String)] {
//    def writes(userDetails: (Int, String, String)) = Json.obj(
//      "id" -> userDetails._1,
//      "name" -> userDetails._2,
//      "role" -> userDetails._3
//
//    )
//  }
//
//
//  def renderUserDetail(userDetails: Seq[(Int, String, String)]) = Json.toJson(userDetails)


}
