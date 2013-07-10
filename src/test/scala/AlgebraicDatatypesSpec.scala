package com.rumblesan.scalaexperiments

import org.specs2.mutable._

import com.rumblesan.scalaexperiments.algebraic._


class AlgebraicDatatypesSpec extends Specification {

  "The 'Algebraic Datatype Example'" should {
    "create a basic tree of strings" in {

      val tree = MyTree.addData("some data", MyTreeEmpty)

      tree must haveClass[MyTreeLeaf[String]]

    }
    "create a more complex tree of strings" in {
      val data = "this is my new data list".split(" ")
      val tree = data.foldLeft(MyTreeEmpty: MyTree[String])((tree, data) =>
        MyTree.addData(data, tree)
      )

      tree must haveClass[MyTreeTree[String]]
    }
    "create a basic tree of integers" in {

      val tree = MyTree.addData(4, MyTreeEmpty)

      tree must haveClass[MyTreeLeaf[Int]]

    }
    "create a more complex tree of integers" in {
      val data = List(1,4,6,45,2,3,45,7,5,2,21,3,5,6,7)
      val tree = data.foldLeft(MyTreeEmpty: MyTree[Integer])((tree, data) =>
        MyTree.addData(data, tree)
      )

      tree must haveClass[MyTreeTree[Integer]]
    }
    "create a basic tree of doubles" in {

      val tree = MyTree.addData(4.0, MyTreeEmpty)

      tree must haveClass[MyTreeLeaf[Double]]

    }
    "create a more complex tree of doubles" in {
      val data = List(1.3,4.52,6.2,45.0,2.56,3.9,45.12,7.4,5.7,2.3,21.56,3.8,5.3,6.4,7.5)
      val tree = data.foldLeft(MyTreeEmpty: MyTree[Double])((tree, data) =>
        MyTree.addData(data, tree)
      )

      tree must haveClass[MyTreeTree[Double]]
    }
  }
}
