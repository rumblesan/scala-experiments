package com.rumblesan.scalaexperiments.mytree

import scalaz.Equal

trait MyTreeEqual[A] extends Equal[MyTree[A]] {

  implicit def A: Equal[A]

  def equal(t1: MyTree[A], t2: MyTree[A]): Boolean = {
    (t1, t2) match {
      case (MyTreeEmpty, MyTreeEmpty) => true
      case (MyTreeLeaf(data1), MyTreeLeaf(data2)) => A.equal(data1, data2)
      case (MyTreeTree(data1, left1, right1), MyTreeTree(data2, left2, right2)) => {
        A.equal(data1, data2) && equal(left1, left2) && equal(right1, right2)
      }
      case _ => false
    }
  }

}

