package com.rumblesan.scalaexperiments.algebraic

import scalaz._, Scalaz._

sealed trait MyTree[+T]
case class MyTreeEmpty[T: Order]() extends MyTree[T]
case class MyTreeLeaf[T: Order](data: T) extends MyTree[T]
case class MyTreeTree[T: Order](data: T, left: MyTree[T], right: MyTree[T]) extends MyTree[T]

trait MyTreeOps {

  def addData[T: Order](data: T, kbucket: MyTree[T]): MyTree[T] = {
    kbucket match {
      case MyTreeEmpty() => MyTreeLeaf[T](data)
      case leaf: MyTreeLeaf[T] => {
        if (data lt leaf.data) MyTreeTree(leaf.data, MyTreeLeaf[T](data), MyTreeEmpty[T])
        else if (data gt leaf.data) MyTreeTree[T](leaf.data, MyTreeEmpty[T], MyTreeLeaf[T](data))
        else leaf
      }
      case tree: MyTreeTree[T] => {
        if (data lt tree.data) MyTreeTree[T](tree.data, addData(data, tree.left), tree.right)
        else if (data gt tree.data) MyTreeTree[T](tree.data, tree.left, addData(data, tree.right))
        else tree
      }
    }
  }

}

object MyTree extends MyTreeOps

