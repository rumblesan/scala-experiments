package com.rumblesan.scalaexperiments.tests.cakepattern

import org.specs2.mutable._

import com.rumblesan.scalaexperiments.cakepattern._


class CakePatternSpec extends Specification {

  "The Cake Pattern Example" should {
    "let me replace the service implementations" in {

      trait ServiceFooTestImpl extends ServiceFooInterface {
        def foo(): List[Int] = List(3)
      }

      trait ServiceBarTestImpl extends ServiceBarInterface {
        def bar(): Map[String,Int] = Map("two" -> 2)
      }

      object MyTestService extends MyServiceOps with ServiceFooTestImpl with ServiceBarTestImpl

      val fooResult = MyTestService.callFoo()
      fooResult must_==(List(4))

      val barResult = MyTestService.callBar()
      barResult must_==(Map("two" -> 2, "three" -> 3))

    }
  }
}
