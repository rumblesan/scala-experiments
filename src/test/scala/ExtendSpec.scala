package com.rumblesan.scalaexperiments

import org.specs2.mutable._

import com.rumblesan.scalaexperiments.extend._


class ExtendSpec extends Specification {

  "The 'Extend Example'" should {
    "get user info from the BasicUser" in {
      val basic = BasicUser(1, "foo bar", "foo@bar.com")
      val info = basic.userInfo
      val expected = UserInfo("foo bar", "foo@bar.com")
      info must_==(expected)
    }
    "get user info from the PaidUser" in {
      val paid = PaidUser(1, "baz bim", "baz@bim.com", 55.4)
      val info = paid.userInfo
      val expected = UserInfo("baz bim", "baz@bim.com")
      info must_==(expected)
    }
  }
}
