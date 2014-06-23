package com.rumblesan.examples.gameoflife

import scalaz._, Scalaz._

object Types {

  type CellState = Int

  type BoardState[A] = State[Board, A]

  type Grid = Vector[Vector[CellState]]

  case class CoordDelta(x: Int, y: Int)

}

