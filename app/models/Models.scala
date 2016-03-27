package models

import org.joda.time.DateTime
import com.github.tototoshi.slick.JdbcJodaSupport._
/**
 * Created by Anup_sust on 8/17/2015.
 */
case class User(id: Int,
                username: String,
                password: String = "",
                userRole: Option[Int],
                email: Option[String],
                activated: Option[Int],
                banned: Option[Boolean]
                 )

case class UserDetail(id: Int = -1,
                      username: String = "",
                      userRoleName: String = "",
                      userRole: Option[Int] = Some(0),
                      email: Option[String] = Some(""),
                      activated: Option[Int] = Some(0),
                      banned: Option[Boolean] = Some(false)
                       )

case class UserRole(id: Option[Int],
                    name: String,
                    description: Option[String],
                    methods: Option[String]
                     )

case class Product(productId: Int,
                   productName: String,
                   supplierId: Option[Int],
                   categoryId: Option[Int],
                   ProductStatusID: Option[Int],
                   quantityPerUnit: Option[Int],
                   unitPrice: Option[BigDecimal],
                   unitsInStock: Option[Int],
                   unitsOnOrder: Option[Int],
                   reorderLevel: Option[Int],
                   discontinued: Option[Boolean],
                   about: Option[String] = None,
                   image: Option[String] = None,
                   created: Option[String] = None
                    )

case class ProductStatus(statusID: Int,
                         StatusName: String,
                         description: Option[String]
                          )

case class ProductRequest(id: Int,
                          requestorId: Int,
                          productId: Int,
                          requestStatus: Option[String],
                          var token: Option[String],
                          quantity: Option[Int]=None,
                          var updateInfo: Option[String]=None,
                          var requestAdded: Option[DateTime]=None )

case class ProductRequestDetail(id: Int,
                                requestorId: Int,
                                productId: Int,
                                requestStatus: Option[String],
                                var token: Option[String],
                                productName: String,
                                username: String,
                                quantity: Option[Int]=None,
                                updateInfo: Option[String]=None,
                                requestAdded: Option[DateTime]=None )

case class Category(categoryID: Int,
                    categoryName: String,
                    description: Option[String],
                    pictureUrl: Option[String]
                     )

case class Supplier(supplierID: Int,
                    companyName: String,
                    contactName: Option[String],
                    contactTitle: Option[String],
                    address: Option[String],
                    city: Option[String],
                    region: Option[String],
                    postalCode: Option[String],
                    country: Option[String],
                    phone: Option[String],
                    homePage: Option[String]
                     )