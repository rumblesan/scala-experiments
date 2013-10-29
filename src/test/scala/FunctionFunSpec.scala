package com.rumblesan.scalaexperiments.tests.extend

import org.specs2.mutable._

import com.rumblesan.scalaexperiments.functionfun.FunctionFun._


class FunctionFunSpec extends Specification {

  "The 'Function Fun Functions'" should {
    "curry as I expect" in {

      val result = curry3(1)(3)(5)

      result must_==(9)

    }
  }
}
