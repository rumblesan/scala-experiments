package com.rumblesan.examples.gameoflife.test

import org.specs2.mutable._

import com.rumblesan.examples.gameoflife._


object GameOfLifeSpec extends Specification {

  "Game of Life board" should {

    "let you set it's state" in {
      val board = Board.setCellState(1, 1, 1).exec(Board(3, 3))
      val expected = Board(
        Vector(Vector(0, 0, 0), Vector(0, 1, 0), Vector(0, 0, 0)),
        3,
        3
      )

      board must_== expected
    }

    "correctly turn cells on and off" in {

      val boardStateChange = for {
        _ <- Board.setCellState(0, 1, 1)
        _ <- Board.setCellState(0, 2, 1)
        _ <- Board.setCellState(1, 1, 1)
        _ <- Board.setCellState(1, 2, 1)
        _ <- Board.setCellState(2, 2, 1)
        newBoard <- Game.runSimulationTurn
      } yield newBoard
      val board = boardStateChange.exec(Board(4, 4))

      val expected = Board(
        Vector(Vector(0, 1, 1, 0), Vector(0, 1, 1, 1), Vector(0, 1, 0, 0), Vector(0, 0, 0, 0)),
        4,
        4
      )

      board must_== expected
    }

    "correctly turn cells on and off over the course of 3 generations" in {

      val boardStateChange = for {
        _ <- Board.setCellState(0, 1, 1)
        _ <- Board.setCellState(0, 2, 1)
        _ <- Board.setCellState(1, 1, 1)
        _ <- Board.setCellState(1, 2, 1)
        _ <- Board.setCellState(2, 2, 1)
        _ <- Game.runSimulationTurn
      } yield ()

      val boardg1 = boardStateChange.exec(Board(4, 4))
      val expectedg1 = Board(
        Vector(Vector(0, 1, 1, 0), Vector(0, 1, 1, 1), Vector(0, 1, 0, 0), Vector(0, 0, 0, 0)),
        4,
        4
      )
      boardg1 must_== expectedg1

      val boardg2 = Game.runSimulationTurn.exec(boardg1)
      val expectedg2 = Board(
        Vector(Vector(0, 1, 1, 1), Vector(1, 1, 1, 0), Vector(0, 0, 1, 0), Vector(0, 0, 0, 0)),
        4,
        4
      )
      boardg2 must_== expectedg2

      val boardg3 = Game.runSimulationTurn.exec(boardg2)
      val expectedg3 = Board(
        Vector(Vector(1, 1, 1, 0), Vector(0, 1, 1, 1), Vector(0, 1, 0, 0), Vector(0, 0, 0, 0)),
        4,
        4
      )
      boardg3 must_== expectedg3
    }

    "correctly turn cells on and off over the course of 3 generations when run all together" in {

      val boardStateChange = for {
        _ <- Board.setCellState(0, 1, 1)
        _ <- Board.setCellState(0, 2, 1)
        _ <- Board.setCellState(1, 1, 1)
        _ <- Board.setCellState(1, 2, 1)
        _ <- Board.setCellState(2, 2, 1)
        _ <- Game.runSimulationTurn
        _ <- Game.runSimulationTurn
        _ <- Game.runSimulationTurn
      } yield ()

      val board = boardStateChange.exec(Board(4, 4))
      val expected = Board(
        Vector(Vector(1, 1, 1, 0), Vector(0, 1, 1, 1), Vector(0, 1, 0, 0), Vector(0, 0, 0, 0)),
        4,
        4
      )
      board must_== expected
    }

  }

}

