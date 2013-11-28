package com.rumblesan.scalaexperiments.scalaztypes.state

import scalaz._, Scalaz._

object StateExample {

  sealed trait Instruction
  case object Double extends Instruction
  case object Triple extends Instruction
  case object AddOne extends Instruction

  type Plan = List[Instruction]

  def run(value: Int) = State[Plan, Int] {
    case inst :: plans => {
      (plans,
        inst match {
          case Double => value * 2
          case Triple => value * 3
          case AddOne => value + 1
        }
      )
    }
    case Nil => (Nil, value)
  }

  def add(inst: Instruction) = State[Plan, Unit] {
    case plans => (inst :: plans, ())
  }

}

