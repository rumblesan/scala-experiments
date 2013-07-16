package com.rumblesan.scalaexperiments

import org.specs2.mutable._

import scalaz._, Scalaz._

import mytree._


class MyTreeEqualSpec extends Specification {

  "The MyTree Equal type class" should {
    "say two empty trees are equal" in {

      val tree1: MyTree[Int] = MyTreeEmpty
      val tree2: MyTree[Int] = MyTreeEmpty

      (tree1 === tree2) must beTrue

    }
    "say two simple trees are equal" in {

      val tree1: MyTree[Int] = MyTreeLeaf(1)
      val tree2: MyTree[Int] = MyTreeLeaf(1)

      (tree1 === tree2) must beTrue

    }
    "say two different trees are not equal" in {

      val tree1: MyTree[Int] = MyTreeTree(1, MyTreeLeaf(1), MyTreeLeaf(1))
      val tree2: MyTree[Int] = MyTreeTree(1, MyTreeLeaf(1), MyTreeLeaf(2))

      (tree1 === tree2) must beFalse

    }
  }
}
