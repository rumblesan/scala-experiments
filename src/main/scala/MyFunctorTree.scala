package com.rumblesan.scalaexperiments.myfunctortree

import scalaz._, Scalaz._

import com.rumblesan.scalaexperiments.algebraic._

trait MyFunctorTree extends Functor[MyTree] {

  def map[A, B](fa: MyTree[A])(f: A => B): MyTree[B] = {
    fa match {
      case MyTreeEmpty => MyTreeEmpty
      case MyTreeLeaf(data) => MyTreeLeaf(f(data))
      case MyTreeTree(data, left, right) => MyTreeTree(f(data), map(left)(f), map(right)(f))
    }
  }

}

object MyFunctorTreeOps extends MyFunctorTree

