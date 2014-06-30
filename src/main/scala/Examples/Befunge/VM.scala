package com.rumblesan.examples.befunge

import scalaz._, Scalaz._

import Types._


case class VM(
  counter: Counter,
  grid: Grid,
  stack: Stack,
  xSize: Int,
  ySize: Int
)

object VM {

  def apply(xVal: Int, yVal: Int): VM = {
    VM(
      Counter(0, 0, East),
      Vector.fill[Instruction](xVal, yVal)(NOP),
      List.empty[Integer],
      xVal,
      yVal
    )
  }

  def getInstruction: VMState[Instruction] = {
    for {
      vm <- State.get
      instruction <- Grid.getGridCell(vm.counter.xPos, vm.counter.yPos)
    } yield instruction
  }

  def executeInstruction(inst: Instruction): VMState[Unit] = {
    inst match {

      case Up    => Counter.updateCounter(North)
      case Down  => Counter.updateCounter(South)
      case Right => Counter.updateCounter(East)
      case Left  => Counter.updateCounter(West)

      case NOP => State.modify(vm => vm)
      case _ => State.modify(vm => vm)

    }
  }

  def runSimulationTurn: VMState[Unit] = {
    for {
      instruction <- getInstruction
      _ <- executeInstruction(instruction)
      _ <- Counter.moveCounter
    } yield ()
  }

}

