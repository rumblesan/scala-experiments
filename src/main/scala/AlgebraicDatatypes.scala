package com.rumblesan.scalaexperiments.algebraic


sealed trait MyTree
case object MyTreeEmpty extends MyTree
case class MyTreeLeaf(data: String) extends MyTree
case class MyTreeTree(data: String, left: MyTree, right: MyTree) extends MyTree

object MyTree {

  def addData(data: String, kbucket: MyTree): MyTree = {
    kbucket match {
      case MyTreeEmpty => MyTreeLeaf(data)
      case leaf: MyTreeLeaf => addData(data, leaf)
      case tree: MyTreeTree => addData(data, tree)
    }
  }

  def addData(data: String, leaf: MyTreeLeaf): MyTree = {
    if (data < leaf.data) {
      MyTreeTree(leaf.data, MyTreeLeaf(data), MyTreeEmpty)
    } else if (data > leaf.data) {
      MyTreeTree(leaf.data, MyTreeEmpty, MyTreeLeaf(data))
    } else {
      leaf
    }
  }

  def addData(data: String, tree: MyTreeTree): MyTree = {
    if (data < tree.data) {
      MyTreeTree(tree.data, addData(data, tree.left), tree.right)
    } else if (data > tree.data) {
      MyTreeTree(tree.data, tree.left, addData(data, tree.right))
    } else {
      tree
    }
  }

}

