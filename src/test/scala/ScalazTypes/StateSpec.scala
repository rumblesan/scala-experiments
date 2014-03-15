package com.rumblesan.scalaexperiments.tests.scalaztypes.state

import scalaz._, Scalaz._

import org.specs2.mutable._

import com.rumblesan.scalaexperiments.scalaztypes.state._

class StateSpec extends Specification {

  "The State functions" should {

    "work like I'm expecting in a for comprehension for the simple example" in {

      import SimpleStateExample._

      val plan = List(Double, AddOne, Double, Double, AddOne)

      def runPlan(input: Int) = for {
        a <- run(input)
        b <- run(a)
        _ <- add(Triple)
        c <- run(b)
      } yield c


      val outputState = runPlan(5)

      val remainder = outputState.exec(plan)
      val output = outputState.eval(plan)

      output must_==(33)
      remainder must_==(List(Double, Double, AddOne))

    }

    "work for the scope example" in {

      import ScopeStateExample._

      val global = Global(Map("a" -> 1))
      val scope1 = Limited(Map("b" -> 2), global)

      def runScopes = for {
        _ <- newScope(Map("c" -> 3))
        a <- getValue("a")
        b <- getValue("b")
        c <- getValue("c")
        _ <- leaveScope
        _ <- leaveScope
        d <- getValue("a")
      } yield a + b + c + d

      val finalState = runScopes.exec(scope1)
      val finalValue = runScopes.eval(scope1)

      finalValue must_==(7)
      finalState must_==(global)
    }

  }

}

