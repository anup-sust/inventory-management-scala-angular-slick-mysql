package views.inventory

import models._
import play.api.libs.json.{Json, Writes}

/**
 * Created by Anup_sust on 8/17/2015.
 */
object ProductView {

  implicit val ProductWrites = new Writes[Product] {
    def writes(product: Product) = Json.obj(
      "id" -> product.productId,
      "productName" -> product.productName,
      "supplierId" -> product.supplierId,
      "categoryId" -> product.categoryId,
      "ProductStatusID" -> product.ProductStatusID,
      "quantityPerUnit" -> product.quantityPerUnit,
      "unitPrice" -> product.unitPrice,
      "unitsInStock" -> product.unitsInStock,
      "unitsOnOrder" -> product.unitsOnOrder,
      "reorderLevel" -> product.reorderLevel,
      "discontinued" -> product.discontinued,
      "about" -> product.about,
      "image" -> product.image,
      "created" -> product.created
    )
  }


  def renderProducts(products: Seq[Product]) = Json.obj("records" -> Json.toJson(products))

  def renderProduct(product: Product) = Json.toJson(product)





  implicit val productDetailsWrites = new Writes[(Int, String, String,String, String, Option[Int], Option[BigDecimal], Option[Int], Option[Int], Option[Int], Option[Boolean] )] {
    def writes(productDetails: (Int, String, String,String, String, Option[Int], Option[BigDecimal], Option[Int], Option[Int], Option[Int], Option[Boolean] )) = Json.obj(
      "id" -> productDetails._1,
      "productName" -> productDetails._2,
      "supplier" -> productDetails._3,
      "category" -> productDetails._4,
      "prodtStatus" -> productDetails._5,
      "quantityPerUnit" -> productDetails._6,
      "unitPrice" -> productDetails._7,
      "unitsInStock" -> productDetails._8,
      "unitsOnOrder" -> productDetails._9,
      "reorderLevel" -> productDetails._10,
      "discontinued" -> productDetails._11
    )
  }


  def renderProductDetail(productDetail: List[Any]) = {

  val x = productDetail(0).asInstanceOf[Seq[(Int, String, String,String, String, Option[Int], Option[BigDecimal], Option[Int], Option[Int], Option[Int], Option[Boolean] )]]
    Json.obj("records"->Json.toJson(x),"totalRecords"->productDetail(1).asInstanceOf[Int])
  }


  implicit val ProductRequestWrites = new Writes[ProductRequest] {
    def writes(productRequest: ProductRequest) = Json.obj(
      "id" -> productRequest.id,
      "requestorId" -> productRequest.requestorId,
      "productId" -> productRequest.productId,
      "requestStatus" -> productRequest.requestStatus,
      "token" -> productRequest.token,
      "quantity" -> productRequest.quantity,
      "updateInfo" -> productRequest.updateInfo,
      "requestAdded"-> productRequest.requestAdded
    )
  }

  implicit val ProductRequestDetailWrites = new Writes[ProductRequestDetail] {
    def writes(productRequestDetail: ProductRequestDetail) = Json.obj(
      "id" -> productRequestDetail.id,
      "requestorId" -> productRequestDetail.requestorId,
      "productId" -> productRequestDetail.productId,
      "requestStatus" -> productRequestDetail.requestStatus,
      "token" -> productRequestDetail.token,
      "productName" -> productRequestDetail.productName,
      "username" -> productRequestDetail.username,
      "quantity" -> productRequestDetail.quantity,
      "updateInfo" -> productRequestDetail.updateInfo,
      "requestAdded" -> productRequestDetail.requestAdded
    )
  }

  def renderProductRequestDetails(productRequestDetailsTup: (Seq[ProductRequestDetail], Int)) =  Json.obj("records"->Json.toJson(productRequestDetailsTup._1), "totalRecords"-> productRequestDetailsTup._2)

  def renderProductRequestDetail(productRequestDetails: ProductRequest) = Json.toJson(productRequestDetails)

}
