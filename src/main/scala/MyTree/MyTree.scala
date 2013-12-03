package com.rumblesan.scalaexperiments.mytree

import scalaz._, Scalaz._
import scalaz.syntax.Ops


sealed trait MyTree[+T]
case object MyTreeEmpty extends MyTree[Nothing]
sealed case class MyTreeLeaf[T](data: T) extends MyTree[T]
sealed case class MyTreeTree[T](data: T, left: MyTree[T], right: MyTree[T]) extends MyTree[T]


trait MyTreeOps[A] extends Ops[MyTree[A]] {

  implicit def A: Order[A]

  def addData[A: Order](data: A): MyTree[A] = {
    self match {
      case MyTreeEmpty => MyTreeLeaf[A](data)
      case leaf: MyTreeLeaf[A] => {
        if (A.lessThan(data, leaf.data)) MyTreeTree(leaf.data, MyTreeLeaf[A](data), MyTreeEmpty)
        else if (A.greaterThan(data, leaf.data)) MyTreeTree[A](leaf.data, MyTreeEmpty, MyTreeLeaf[A](data))
        else leaf
      }
      case tree: MyTreeTree[A] => {
        if (A.lessThan(data, tree.data)) MyTreeTree[A](tree.data, tree.left.addData(data), tree.right)
        else if (A.greaterThan(data, tree.data)) MyTreeTree[A](tree.data, tree.left, tree.right.addData(data))
        else tree
      }
    }
  }


  def isEmpty[A]: Boolean = {
    self match {
      case MyTreeEmpty => true
      case _ => false
    }
  }

}


trait ToMyTreeOps {

  implicit def ToMyTreeOps[A](v: MyTree[A])(implicit A0: Order[A]): MyTreeOps[A] = 
    new MyTreeOps[A] {
      def self = v
      implicit def A = A0
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

