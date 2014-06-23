package com.rumblesan.scalaexperiments.tests.scalaztypes.applicative

import scalaz._, Scalaz._

import org.specs2.mutable._


class ApplicativeSpec extends Specification {

  "The Applicative functions" should {
    "work like I'm expecting with Options" in {

      val input = 5

      val f: Int => Int => Int = a => b => {
        a + b
      }

      val func1 = Some(f(1))
      val func2 = Some(f(2))

      val out = 5.point[Option] <*> func1 <*> func2

      out.get must_==(8)

    }
  }

}

