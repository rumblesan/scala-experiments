

case class UserInfo(name: String, email: String)


case class PaidUser(id: Long, name: String, email: String, payments: Double)

object PaidUser {

  class PaidUserExtended(user: PaidUser) {
    def userInfo: UserInfo = UserInfo(user.name, user.email)
  }

  implicit def PaidUser2Extended(user: PaidUser) = new PaidUserExtended(user)

}


case class BasicUser(id: Long, name: String, email: String)

object BasicUser {

  class BasicUserExtended(user: BasicUser) {
    def userInfo: UserInfo = UserInfo(user.name, user.email)
  }

  implicit def BasicUser2Extended(user: BasicUser) = new BasicUserExtended(user)

}


val basic = BasicUser(1, "foo bar", "foo@bar.com")
val paid = PaidUser(1, "baz bim", "baz@bim.com", 55.4)

println(basic.userInfo)
// UserInfo(foo bar,foo@bar.com)

println(paid.userInfo)
// UserInfo(baz bim,baz@bim.com)

