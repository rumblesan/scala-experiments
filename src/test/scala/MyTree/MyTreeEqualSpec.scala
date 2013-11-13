package com.rumblesan.scalaexperiments.tests.mytree

import org.specs2.mutable.Specification
import org.specs2.ScalaCheck
import org.scalacheck.{Arbitrary, Gen, Prop}

import Arbitrary.arbitrary
import Prop.forAll

import scalaz._, Scalaz._

import com.rumblesan.scalaexperiments.mytree._


class MyTreeEqualSpec extends Specification with ScalaCheck {

  "The MyTree Equal type class" should {

    "say two empty trees are equal" in {

      val tree1: MyTree[Int] = MyTreeEmpty
      val tree2: MyTree[Int] = MyTreeEmpty

      (tree1 === tree2) must beTrue

    }
    "say two simple trees are equal" in forAll { number: Int =>

      val tree1: MyTree[Int] = MyTreeLeaf(number)
      val tree2: MyTree[Int] = MyTreeLeaf(number)

      (tree1 === tree2) must beTrue

    }
    "two trees with the same numbers should be equal" in forAll { numbers: List[Int] =>

      val tree1 = numbers.foldLeft(MyTreeEmpty: MyTree[Int])((t, n) => t.addData(n))
      val tree2 = numbers.foldLeft(MyTreeEmpty: MyTree[Int])((t, n) => t.addData(n))

      (tree1 === tree2) must beTrue

    }
    "say two different trees are not equal" in forAll { (l1: List[Int], l2: List[Int]) =>

      val tree1 = l1.foldLeft(MyTreeEmpty: MyTree[Int])((t, n) => t.addData(n))
      val tree2 = l2.foldLeft(MyTreeEmpty: MyTree[Int])((t, n) => t.addData(n))

      if (l1 === l2) (tree1 === tree2) must beTrue
      else (tree1 === tree2) must beFalse

    }
  }
}
