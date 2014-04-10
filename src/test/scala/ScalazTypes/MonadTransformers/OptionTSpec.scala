package com.rumblesan.scalaexperiments.tests.scalaztypes.monadtransformers.optiont

import scalaz._, Scalaz._

import org.specs2.mutable._

import com.rumblesan.scalaexperiments.scalaztypes.monadtransformers.optiont._


class OptionTSpec extends Specification {

  import ScalazOptionTExample._

  "The Option Transformer functions" should {

    "allow me to handle nested monads" in {

      val input = OptionT(List(1.some, 2.some))

      val result = for {
        v1 <- input
        v2 <- addTwo(v1)
        v3 <- addTwoAndDouble(v2)
      } yield v3

      val expected = List(Some(5), Some(5), Some(6), Some(6))

      result.run must_==(expected)

    }

    "work when there are None values" in {

      val input = OptionT(List(None, 2.some))

      val result = for {
        v1 <- input
        v2 <- addTwo(v1)
        v3 <- addTwoAndDouble(v2)
      } yield v3

      val expected = List(None, Some(6), Some(6))

      result.run must_==(expected)

    }

  }

}

