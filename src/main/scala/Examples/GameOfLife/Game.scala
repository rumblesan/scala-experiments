package com.rumblesan.examples.gameoflife

import scalaz._, Scalaz._

import com.rumblesan.examples.gameoflife.Types._
import com.rumblesan.examples.gameoflife.Constants._

object Game {

  def cellLiveOrDie(currentState: CellState, liveNeighbours: Int): CellState = {
    currentState match {
      case `deadState` => {
        if (liveNeighbours >= turnOnNumber) liveState
        else deadState
      }
      case `liveState` => {
        if (liveNeighbours < turnOffNumber || liveNeighbours > overcrowdNumber) deadState
        else liveState
      }
    }
  }

  val neighbourPositions = List(
    CoordDelta(-1, -1), CoordDelta(-1,  0), CoordDelta(-1,  1),
    CoordDelta( 0, -1), CoordDelta( 0,  1),
    CoordDelta( 1, -1), CoordDelta( 1,  0), CoordDelta( 1,  1)
  )

  def getLiveNeighbourNumber(xPos: Int, yPos: Int): BoardState[Int] = {
    for {
      neighbours <- neighbourPositions.traverseS(
        cd => Board.getCellState(xPos + cd.x, yPos + cd.y)
      )
    } yield neighbours.filter(n => n != deadState).length
  }

  def newCellState(xPos: Int, yPos: Int): BoardState[CellState] = for {
    currentState <- Board.getCellState(xPos, yPos)
    liveNeighbours <- getLiveNeighbourNumber(xPos, yPos)
  } yield cellLiveOrDie(currentState, liveNeighbours)

  def generateCoords(): BoardState[Stream[CoordDelta]] = {
    State.gets(board =>
      for {
        x <- (0 until board.xSize).toStream
        y <- (0 until board.ySize).toStream
      } yield (CoordDelta(x, y))
    )
  }

  def runSimulationTurn: BoardState[Unit] = {
    for {
      coords <- generateCoords
      newStates <- coords.traverseS(
        cd => newCellState(cd.x, cd.y).map(cs => (cd, cs))
      )
      _ <- State.modify[Board](oldBoard =>
        Board(oldBoard.xSize, oldBoard.ySize)
      )
      _ <- newStates.traverseS{
        case (cd, cs) => Board.setCellState(cd.x, cd.y, cs)
      }
    } yield ()
  }

}

