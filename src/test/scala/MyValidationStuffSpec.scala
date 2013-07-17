package com.rumblesan.scalaexperiments.tests.validationtest

import scalaz._, Scalaz._

import org.specs2.mutable._

import com.rumblesan.scalaexperiments.scalaz.validation._


class MyValidationStuffSpec extends Specification {

  "The 'Validationxtend Example'" should {
    "pass a value through correctly using maps and flatmaps" in {

      val validated = 2.right[String].flatMap(
        v => MyValidationStuff.likesEven(v)
      ).map(
        v => MyValidationStuff.justTimes2(v)
      ).flatMap(
        v => MyValidationStuff.likesNonZero(v)
      )

      validated must_==(4.right[String])

    }
    "have a value trigger the first error" in {

      val errord = 3.right[String].flatMap(
        v => MyValidationStuff.likesEven(v)
      ).map(
        v => MyValidationStuff.justTimes2(v)
      ).flatMap(
        v => MyValidationStuff.likesNonZero(v)
      )

      errord.isLeft must beTrue
      
      errord.swap.getOrElse("") must_==("i hate odds")

    }
    "have a value trigger the second error" in {

      val errord = 0.right[String].flatMap(
        v => MyValidationStuff.likesEven(v)
      ).map(
        v => MyValidationStuff.justTimes2(v)
      ).flatMap(
        v => MyValidationStuff.likesNonZero(v)
      )

      errord.isLeft must beTrue
      
      errord.swap.getOrElse("") must_==("i hate zeros")

    }
    "work fine with for comprehensions" in {

      val validated = for {
        isEven <- MyValidationStuff.likesEven(2)
        isMultiplied = MyValidationStuff.justTimes2(isEven)
        isNotZero <- MyValidationStuff.likesNonZero(isMultiplied)
      } yield isNotZero

      validated must_==(4.right[String])

    }
  }
}
