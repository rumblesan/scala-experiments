package com.rumblesan.scalaexperiments.mytree

import scalaz._, Scalaz._
import scalaz.syntax.Ops


sealed trait MyTree[+T]
case object MyTreeEmpty extends MyTree[Nothing]
sealed case class MyTreeLeaf[T](data: T) extends MyTree[T]
sealed case class MyTreeTree[T](data: T, left: MyTree[T], right: MyTree[T]) extends MyTree[T]


trait MyTreeOps[T] extends Ops[MyTree[T]] {

  def addData[T: Order](data: T): MyTree[T] = {
    self match {
      case MyTreeEmpty => MyTreeLeaf[T](data)
      case leaf: MyTreeLeaf[T] => {
        if (data lt leaf.data) MyTreeTree(leaf.data, MyTreeLeaf[T](data), MyTreeEmpty)
        else if (data gt leaf.data) MyTreeTree[T](leaf.data, MyTreeEmpty, MyTreeLeaf[T](data))
        else leaf
      }
      case tree: MyTreeTree[T] => {
        if (data lt tree.data) MyTreeTree[T](tree.data, tree.left.addData(data), tree.right)
        else if (data gt tree.data) MyTreeTree[T](tree.data, tree.left, tree.right.addData(data))
        else tree
      }
    }
  }

}


trait ToMyTreeOps {

  implicit def ToMyTreeOps[T](v: MyTree[T]): MyTreeOps[T] = 
    new MyTreeOps[T] {
      def self = v
    }

}

trait MyTreeInstances0 {
  implicit def mytreeEqual[A](implicit A0: Equal[A]) = new MyTreeEqual[A] {
    implicit def A = A0
  }
}

trait MyTreeInstances extends MyTreeInstances0 {

  implicit val mytreeInstance = new MyTreeFunctor {
  }

}


object MyTree extends ToMyTreeOps with MyTreeInstances

