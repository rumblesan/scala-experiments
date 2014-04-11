package com.rumblesan.scalaexperiments.tests.scalaztypes.monadtransformers.eithert

import scalaz._, Scalaz._

import org.specs2.mutable._

import com.rumblesan.scalaexperiments.scalaztypes.monadtransformers.eithert._


class EitherTSpec extends Specification {

  import ScalazEitherTExample._

  "The Option Transformer functions" should {

    "allow me to handle nested monads" in {

      val input = EitherT(List(1.right[String], 2.right[String]))

      val result = for {
        v1 <- input
        v2 <- addTwo(v1)
        v3 <- addTwoAndDouble(v2)
      } yield v3

      val expected = List(5.right[String], 5.right[String], 6.right[String], 6.right[String])

      result.run must_==(expected)

    }

    "work when there are Left values" in {

      val input = EitherT(List("error".left[Int], 2.right[String]))

      val result = for {
        v1 <- input
        v2 <- addTwo(v1)
        v3 <- addTwoAndDouble(v2)
      } yield v3

      val expected = List("error".left[Int], 6.right[String], 6.right[String])

      result.run must_==(expected)

    }

    "work with errors from functions" in {

      val input = EitherT(List(1.right[String], 2.right[String]))

      val result = for {
        v1 <- input
        v2 <- addTwo(v1)
        v3 <- addError(v2)
      } yield v3

      val expected = List(3.right[String], "new error".left[Int], 4.right[String], "new error".left[Int])

      result.run must_==(expected)

    }


  }

}

