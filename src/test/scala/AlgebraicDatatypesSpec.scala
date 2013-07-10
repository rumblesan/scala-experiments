package com.rumblesan.scalaexperiments

import org.specs2.mutable._

import com.rumblesan.scalaexperiments.algebraic._


class AlgebraicDatatypesSpec extends Specification {

  "The 'Algebraic Datatype Example'" should {
    "create a basic tree of strings" in {

      val tree = MyTree.addData("some data", MyTreeEmpty[String]())

      tree must haveClass[MyTreeLeaf[String]]

    }
    "create a more complex tree" in {
      val data = "this is my new data list".split(" ")
      val tree = data.foldLeft(MyTreeEmpty[String](): MyTree[String])((tree, data) =>
        MyTree.addData(data, tree)
      )

      tree must haveClass[MyTreeTree[String]]
    }
  }
}
