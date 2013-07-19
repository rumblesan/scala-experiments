package com.rumblesan.scalaexperiments.typeclass


case class BasicUser(id: Long, name: String, email: String)

case class PaidUser(id: Long, name: String, email: String, payments: Double)


case class UserInfo(name: String, email: String)


object UserInfoMethods {

  trait UserInfoTypeClass[T] {
    def getUserInfo(user: T): UserInfo
  }
  object UserInfoTypeClass {
    implicit val UserInfoTypeClassBasic = new UserInfoTypeClass[BasicUser] {
      def getUserInfo(user: BasicUser): UserInfo = UserInfo(user.name, user.email)
    }
    implicit object UserInfoLikeTypeClassPaid extends UserInfoTypeClass[PaidUser] {
      def getUserInfo(user: PaidUser): UserInfo = UserInfo(user.name, user.email)
    }
  }

  def userInfo[T](user: T)(implicit tc: UserInfoTypeClass[T]): UserInfo = tc.getUserInfo(user)

}

