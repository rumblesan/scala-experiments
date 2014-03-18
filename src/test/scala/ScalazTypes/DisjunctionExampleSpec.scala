package com.rumblesan.scalaexperiments.tests.scalaztypes.disjunction

import scalaz._, Scalaz._

import org.specs2.mutable._

import com.rumblesan.scalaexperiments.scalaztypes.disjunction._


class ScalazDisjunctionExampleSpec extends Specification {

  "The 'Disjunctionxtend Example'" should {
    "pass a value through correctly using maps and flatmaps" in {

      val result = 2.right[String].flatMap(
        v => ScalazDisjunctionExample.likesEven(v)
      ).map(
        v => ScalazDisjunctionExample.justTimes2(v)
      ).flatMap(
        v => ScalazDisjunctionExample.likesNonZero(v)
      )

      result must_==(4.right[String])

    }
    "have a value trigger the first error" in {

      val errord = 3.right[String].flatMap(
        v => ScalazDisjunctionExample.likesEven(v)
      ).map(
        v => ScalazDisjunctionExample.justTimes2(v)
      ).flatMap(
        v => ScalazDisjunctionExample.likesNonZero(v)
      )

      errord.isLeft must beTrue
      
      errord.swap.getOrElse("") must_==("i hate odds")

    }
    "have a value trigger the second error" in {

      val errord = 0.right[String].flatMap(
        v => ScalazDisjunctionExample.likesEven(v)
      ).map(
        v => ScalazDisjunctionExample.justTimes2(v)
      ).flatMap(
        v => ScalazDisjunctionExample.likesNonZero(v)
      )

      errord.isLeft must beTrue
      
      errord.swap.getOrElse("") must_==("i hate zeros")

    }
    "work fine with for comprehensions" in {

      val result = for {
        isEven <- ScalazDisjunctionExample.likesEven(2)
        isMultiplied = ScalazDisjunctionExample.justTimes2(isEven)
        isNotZero <- ScalazDisjunctionExample.likesNonZero(isMultiplied)
      } yield isNotZero

      result must_==(4.right[String])

    }
    "correctly error with for comprehensions" in {

      val errord = for {
        isEven <- ScalazDisjunctionExample.likesEven(0)
        isMultiplied = ScalazDisjunctionExample.justTimes2(isEven)
        isNotZero <- ScalazDisjunctionExample.likesNonZero(isMultiplied)
      } yield isNotZero

      errord.isLeft must beTrue
      
      errord.swap.getOrElse("") must_==("i hate zeros")

    }
  }
}
