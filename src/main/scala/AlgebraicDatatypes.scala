package com.rumblesan.scalaexperiments.algebraic


sealed trait MyTree
case object MyTreeEmpty extends MyTree
case class MyTreeLeaf(data: String) extends MyTree
case class MyTreeTree(data: String, left: MyTree, right: MyTree) extends MyTree

trait MyTreeOps {

  def addData(data: String, kbucket: MyTree): MyTree = {
    kbucket match {
      case MyTreeEmpty => MyTreeLeaf(data)
      case leaf: MyTreeLeaf => {
        if (data < leaf.data) MyTreeTree(leaf.data, MyTreeLeaf(data), MyTreeEmpty)
        else if (data > leaf.data) MyTreeTree(leaf.data, MyTreeEmpty, MyTreeLeaf(data))
        else leaf
      }
      case tree: MyTreeTree => {
        if (data < tree.data) MyTreeTree(tree.data, addData(data, tree.left), tree.right)
        else if (data > tree.data) MyTreeTree(tree.data, tree.left, addData(data, tree.right))
        else tree
      }
    }
  }

}

object MyTree extends MyTreeOps

