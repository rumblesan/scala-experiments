
case class BasicUser(id: Long, name: String, email: String)

case class PaidUser(id: Long, name: String, email: String, payments: Double)


case class UserInfo(name: String, email: String)

object UserInfoMethods {

  trait UserInfoLike[T] {
    def getUserInfo(user: T): UserInfo
  }
  object UserInfoLike {
    implicit object UserInfoLikeBasic extends UserInfoLike[BasicUser] {
      def getUserInfo(user: BasicUser): UserInfo = UserInfo(user.name, user.email)
    }
    implicit object UserInfoLikePaid extends UserInfoLike[PaidUser] {
      def getUserInfo(user: PaidUser): UserInfo = UserInfo(user.name, user.email)
    }
  }

  def userInfo[T](user: T)(implicit tc: UserInfoLike[T]): UserInfo = tc.getUserInfo(user)

}

val basic = BasicUser(1, "foo bar", "foo@bar.com")
val paid = PaidUser(1, "baz bim", "baz@bim.com", 55.4)

println(UserInfoMethods.userInfo(basic))
// UserInfo(foo bar,foo@bar.com)

println(UserInfoMethods.userInfo(paid))
// UserInfo(baz bim,baz@bim.com)

