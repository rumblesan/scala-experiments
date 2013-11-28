package com.rumblesan.scalaexperiments.tests.scalaztypes.state

import scalaz._, Scalaz._

import org.specs2.mutable._

import com.rumblesan.scalaexperiments.scalaztypes.state._
import StateExample.{Double, Triple, AddOne}

class StateSpec extends Specification {

  "The State functions" should {
    "work like I'm expecting in a for comprehension" in {

      val plan = List(Double, AddOne, Double, Double, AddOne)

      def runPlan(input: Int) = for {
        a <- StateExample.run(input)
        b <- StateExample.run(a)
        _ <- StateExample.add(Triple)
        c <- StateExample.run(b)
      } yield c


      val outputState = runPlan(5)(plan)

      val remainder = outputState._1
      val output = outputState._2

      output must_==(33)
      remainder must_==(List(Double, Double, AddOne))

    }
  }

}

