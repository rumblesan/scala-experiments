package com.rumblesan.examples.befunge

import scalaz._, Scalaz._

import Types._


object Grid {

  def setGridCell(xPos: Int, yPos: Int, inst: Instruction): VMState[Unit] = {
    State.modify(vm => {
      val x = Math.abs(xPos) % vm.xSize
      val y = Math.abs(yPos) % vm.ySize
      val g = vm.grid
      vm.copy(grid = g.updated(x, (g(x).updated(y, inst))))
    })
  }

  def getGridCell(xPos: Int, yPos: Int): VMState[Instruction] = {
    State.gets(vm => {
      val x = Math.abs(xPos) % vm.xSize
      val y = Math.abs(yPos) % vm.ySize
      vm.grid(x)(y)
    })
  }

}

