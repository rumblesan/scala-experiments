package com.rumblesan.scalaexperiments.tests.mytree

import org.specs2._
import org.scalacheck._

import scalaz._, Scalaz._

import com.rumblesan.scalaexperiments.mytree._


class MyTreeProperties extends Specification with ScalaCheck {
  
  def is = s2"""
    Properties for MyTree
      follows the identity law     $identityLaw
      follows the composition law  $compositionLaw
                                   """


  def identityLaw = check { (numbers: List[Int]) =>

    val tree1: MyTree[Int] = numbers.foldLeft(MyTreeEmpty: MyTree[Int])(
      (tree, number) =>
        tree.addData(number)
    )

    tree1 must_== tree1.map(identity)

  }

  def compositionLaw = check { (numbers: List[Int]) =>

    val tree: MyTree[Int] = numbers.foldLeft(MyTreeEmpty: MyTree[Int])(
      (tree, number) =>
        tree.addData(number)
    )

    val numToStr = (n: Int) => n.toString

    val strLen = (s: String) => s.length

    val composed = strLen compose numToStr

    tree.map(numToStr).map(strLen) must_== tree.map(composed)

  }

}
