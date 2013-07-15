package com.rumblesan.scalaexperiments.mytree

import scalaz.Functor

trait MyTreeFunctor extends Functor[MyTree] {

  def map[A, B](fa: MyTree[A])(f: A => B): MyTree[B] = {
    fa match {
      case MyTreeEmpty => MyTreeEmpty
      case MyTreeLeaf(data) => MyTreeLeaf(f(data))
      case MyTreeTree(data, left, right) => MyTreeTree(f(data), map(left)(f), map(right)(f))
    }
  }

}

