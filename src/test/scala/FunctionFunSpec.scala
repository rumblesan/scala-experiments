package com.rumblesan.scalaexperiments.tests.extend

import org.specs2.mutable._

import com.rumblesan.scalaexperiments.functionfun.FunctionFun._


class FunctionFunSpec extends Specification {

  "The 'Function Fun Functions'" should {
    "curry as I expect" in {

      val result = curry3(1)(3)(5)

      result must_==(9)

    }

    "show how vals and defs can be used as first order functions" in {

      val result1 = secondOrderFunc(3, valueFunction1)
      val result2 = secondOrderFunc(3, valueFunction2)

      result1 must_==(result2)
    }

    "show how vals and defs are different if you partialy apply them" in {

      val fns = List(valueFunction1 _, valueFunction2)

      val results = fns.map((fn: Int => Int) =>
        fn(3)
      )

      results must_==(List(9, 9))

    }

  }
}
