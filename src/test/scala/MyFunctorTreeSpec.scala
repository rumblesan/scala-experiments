package com.rumblesan.scalaexperiments

import org.specs2.mutable._

import scalaz._, Scalaz._

import com.rumblesan.scalaexperiments.algebraic._


class MyFunctorTreeSpec extends Specification {

  "The MyFunctorTree Example" should {
    "map a function over a tree" in {
      val initialTree = List(1,4,6,9).foldLeft(MyTreeEmpty: MyTree[Integer])((tree, data) =>
        MyTree.addData(data, tree)
      )
      val newTree = initialTree.map((_ + 1))

      val expectedTree = List(2,5,7,10).foldLeft(MyTreeEmpty: MyTree[Integer])((tree, data) =>
        MyTree.addData(data, tree)
      )

      newTree must haveClass[MyTreeTree[Integer]]
      newTree must_== expectedTree

    }
  }
}
