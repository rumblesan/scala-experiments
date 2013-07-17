package com.rumblesan.scalaexperiments.tests.mytree

import org.specs2.mutable._

import scalaz._, Scalaz._

import com.rumblesan.scalaexperiments.mytree._


class MyTreeFunctorSpec extends Specification {

  "The MyFunctorTree Example" should {
    "map a function over a tree" in {
      val initialTree = List(1,4,6,9).foldLeft(MyTreeEmpty: MyTree[Integer])((tree, data) =>
        tree.addData(data)
      )
      val newTree = initialTree.map((_ + 1))

      val expectedTree = List(2,5,7,10).foldLeft(MyTreeEmpty: MyTree[Integer])((tree, data) =>
        tree.addData(data)
      )

      newTree must haveClass[MyTreeTree[Integer]]
      newTree must_== expectedTree

    }
  }
}
