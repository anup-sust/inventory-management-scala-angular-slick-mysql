package services

import db.UsersTable
import models._
import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile

import slick.driver.MySQLDriver.api._
/**
 * Created by Anup_sust on 8/17/2015.
 */
object UserService extends HasDatabaseConfig[JdbcProfile] {
  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)

  val Users = TableQuery[UsersTable]

  def getAll = dbConfig.db.run(Users.result)

  def getByNamePass(userName: String, pass: String) = dbConfig.db.run(Users.filter(x => (x.username === userName && x.password === pass)).take(1).result)

  def getByName(userName: String) = dbConfig.db.run(Users.filter(x => x.username === userName).take(1).result)

  def addUser(user: User) = dbConfig.db.run(Users += user)

  def deleteUser(id: Int) = dbConfig.db.run(Users.filter(_.id === id).delete)

  def updateUser(user: User) =
  {
    println(user.userRole)
    dbConfig.db.run(Users.filter(x=> x.id===user.id).map(x=>(x.userRole,x.banned,x.activated)).update((user.userRole,user.banned,user.activated)))
  }


  val userDetails = for {
    user <- Users
    userRole <- user.UserRole
  } yield (
      user.id,
      user.username,
      userRole.name,
      user.userRole,
      user.email,
      user.activated,
      user.banned
      )

    def getAllUserDetails = dbConfig.db.run(userDetails.result)

    def getAllUserDetailsById(id: Int) = dbConfig.db.run(userDetails.filter(x => x._1 === id).result)

    def getUserDetalByNamePass(userName: String, pass: String) = {

      val detail = for {
        user <- Users if(user.username === userName && user.password === pass)
        userRole <- user.UserRole
      } yield (
        user.id,
        user.username,
        userRole.name,
        user.userRole,
        user.email,
        user.activated,
        user.banned
        )

      dbConfig.db.run(detail.take(1).result)
    }




}
