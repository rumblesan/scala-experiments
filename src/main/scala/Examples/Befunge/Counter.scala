package com.rumblesan.examples.befunge

import scalaz._, Scalaz._

import Types._


case class Counter(xPos: Int, yPos: Int, direction: Direction)

object Counter {

  def moveCounter: VMState[Unit] = State.modify { vm =>
    val counter = vm.counter
    val (tX, tY) = counter.direction match {
      case North => (counter.xPos,     counter.yPos + 1)
      case South => (counter.xPos,     counter.yPos - 1)
      case East  => (counter.xPos + 1, counter.yPos    )
      case West  => (counter.xPos - 1, counter.yPos    )
    }
    val xMin = 0
    val yMin = 0
    val xMax = vm.xSize
    val yMax = vm.ySize
    val newX = if      (tX < xMin) (tX + xMax)
               else if (tX > xMax) (tX - xMax)
               else                 tX

    val newY = if      (tY < yMin) (tY + yMax)
               else if (tY > yMax) (tY - yMax)
               else                 tY
    vm.copy(counter = Counter(newX, newY, counter.direction))
  }

  def updateCounter(newDirection: Direction): VMState[Unit] = State.modify { vm =>
    vm.copy(
      counter = vm.counter.copy(direction = newDirection)
    )
  }

}


