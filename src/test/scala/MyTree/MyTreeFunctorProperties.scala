package com.rumblesan.scalaexperiments.tests.mytree

import org.specs2.mutable.Specification
import org.specs2.ScalaCheck
import org.scalacheck.{Arbitrary, Gen, Prop}

import Arbitrary.arbitrary
import Prop.forAll

import scalaz._, Scalaz._

import com.rumblesan.scalaexperiments.mytree._


class MyTreeProperties extends Specification with ScalaCheck {
  
  "Properties for MyTree" should {

    "follows the identity law" in {

      forAll(genMyTreeInt) { (tree: MyTree[Int]) =>

        tree must_== tree.map(identity)

      }

    }

    "follows the composition law" in {

      forAll(genMyTreeInt) { (tree: MyTree[Int]) =>

        val numToStr = (n: Int) => n.toString

        val strLen = (s: String) => s.length

        val composed = strLen compose numToStr

        tree.map(numToStr).map(strLen) must_== tree.map(composed)
      }

    }

  }

  lazy val genMyTreeInt: Gen[MyTree[Int]] = for {
    numbers <- arbitrary[List[Int]]
  } yield numbers.foldLeft(MyTreeEmpty: MyTree[Int])((t, n) => t.addData(n))

}
