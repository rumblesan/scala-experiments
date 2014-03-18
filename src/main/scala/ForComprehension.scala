package com.rumblesan.scalaexperiments.forcomprehension

object ForComprehension {

  object OptionExample {

    def maybeValue(value: Int): Option[Int] = {
      if (value == 4) None
      else Some(value)
    }

    def maybeMult(value: Int, mult: Int): Option[Int] = {
      if (mult == 5) None
      else Some(value * mult)
    }

    def maybePlus(v1: Int, v2: Int): Option[Int] = {
      if (v1 == v2) None
      else Some(v1 + v2)
    }

  }

  object ListExample {

    def oneUpAndDown(value: Int): List[Int] = {
      List(value - 1, value, value + 1)
    }

    def doubleIt(value: Int): List[Int] = {
      List(value, value)
    }

  }

}


