package com.rumblesan.scalaexperiments.tests.scalaztypes.validation

import scalaz._, Scalaz._

import org.specs2.mutable._

import com.rumblesan.scalaexperiments.scalaztypes.validation._


class ScalazValidationExampleSpec extends Specification {

  "The 'Validationxtend Example'" should {
    "pass a value through correctly using maps and flatmaps" in {

      val validated = 2.right[String].flatMap(
        v => ScalazValidationExample.likesEven(v)
      ).map(
        v => ScalazValidationExample.justTimes2(v)
      ).flatMap(
        v => ScalazValidationExample.likesNonZero(v)
      )

      validated must_==(4.right[String])

    }
    "have a value trigger the first error" in {

      val errord = 3.right[String].flatMap(
        v => ScalazValidationExample.likesEven(v)
      ).map(
        v => ScalazValidationExample.justTimes2(v)
      ).flatMap(
        v => ScalazValidationExample.likesNonZero(v)
      )

      errord.isLeft must beTrue
      
      errord.swap.getOrElse("") must_==("i hate odds")

    }
    "have a value trigger the second error" in {

      val errord = 0.right[String].flatMap(
        v => ScalazValidationExample.likesEven(v)
      ).map(
        v => ScalazValidationExample.justTimes2(v)
      ).flatMap(
        v => ScalazValidationExample.likesNonZero(v)
      )

      errord.isLeft must beTrue
      
      errord.swap.getOrElse("") must_==("i hate zeros")

    }
    "work fine with for comprehensions" in {

      val validated = for {
        isEven <- ScalazValidationExample.likesEven(2)
        isMultiplied = ScalazValidationExample.justTimes2(isEven)
        isNotZero <- ScalazValidationExample.likesNonZero(isMultiplied)
      } yield isNotZero

      validated must_==(4.right[String])

    }
    "correctly error with for comprehensions" in {

      val errord = for {
        isEven <- ScalazValidationExample.likesEven(0)
        isMultiplied = ScalazValidationExample.justTimes2(isEven)
        isNotZero <- ScalazValidationExample.likesNonZero(isMultiplied)
      } yield isNotZero

      errord.isLeft must beTrue
      
      errord.swap.getOrElse("") must_==("i hate zeros")

    }
  }
}
