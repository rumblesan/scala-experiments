package com.rumblesan.scalaexperiments.scalaztypes.rwsmonad

import scalaz._, Scalaz._

case class Config(number: Int)

object ReaderWriterStateExample {

  type MyState = List[Int]
  type MyLog = List[String]

  def getConfigNumber(value: Int): ReaderWriterState[Config, MyLog, MyState, Int] =
    ReaderWriterState {
      case (r, s) => (s"getting ${r.number} from config" :: Nil, value + r.number, s)
    }

  def addValueToState(value: Int): ReaderWriterState[Config, MyLog, MyState, Unit] =
    ReaderWriterState {
      case (r, s) => (s"adding $value to state" :: Nil, (), value :: s)
    }

  def multiplyTopStateValue(mult: Int): ReaderWriterState[Config, MyLog, MyState, Int] =
    ReaderWriterState {
      case (r, s) => {
        val newstate = (s.head * mult) :: s.tail
        ("adding value to state" :: Nil, mult, newstate)
      }
    }

  def getTopTwoStateValueSum(): ReaderWriterState[Config, MyLog, MyState, Int] =
    ReaderWriterState {
      case (r, s) => {
        val (ret, state) = s match {
          case v1 :: v2 :: rest => (v1 + v2, rest)
          case _ => (0, s)
        }
        (Nil, ret, state)
      }
    }

  def log(message: String): ReaderWriterState[Config, MyLog, MyState, Unit] =
    ReaderWriterState {
      case (r, s) => (message :: Nil, (), s)
    }


}

