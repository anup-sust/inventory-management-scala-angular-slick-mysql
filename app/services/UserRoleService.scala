package services

import db.UsersRolesTable
import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile

import slick.driver.MySQLDriver.api._

/**
 * Created by Anup_sust on 8/17/2015.
 */
object UserRoleService extends HasDatabaseConfig[JdbcProfile]{
  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)

  val UsersRoles = TableQuery[UsersRolesTable]

  def getAll = dbConfig.db.run(UsersRoles.result)

 // def getByNamePass(userName: String, pass: String) = dbConfig.db.run(UsersRoles.filter(x=> (x.username===userName && x.password===pass)).take(1).result)

}
