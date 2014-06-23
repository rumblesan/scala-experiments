package com.rumblesan.examples.gameoflife

import scalaz._, Scalaz._

import com.rumblesan.examples.gameoflife.Constants._
import com.rumblesan.examples.gameoflife.Types._

case class Board(grid: Grid, xSize: Int, ySize: Int)

object Board {

  def apply(xval: Int, yval: Int): Board = Board(Vector.fill[CellState](xval, yval)(deadState), xval, yval)

  def getCellState(xPos: Int, yPos: Int): BoardState[CellState] = {
    State.gets(board => {
      if (xPos < 0 || yPos < 0 || xPos >= board.xSize || yPos >= board.ySize)
        deadState
      else
        board.grid(xPos)(yPos)
    })
  }

  def setCellState(xPos: Int, yPos: Int, newValue: CellState): BoardState[Unit] = {
    State.modify[Board](board => {
      if (xPos >= 0 && yPos >= 0 && xPos < board.xSize && yPos < board.ySize)
        board.copy(grid = board.grid.updated(xPos, (board.grid(xPos).updated(yPos, newValue))))
      else
        board
    })
  }
}

