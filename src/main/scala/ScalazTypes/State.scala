package com.rumblesan.scalaexperiments.scalaztypes.state

import scalaz._, Scalaz._
import scala.annotation.tailrec

object SimpleStateExample {

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


object ScopeStateExample {

  sealed trait Scope
  case class Global(values: Map[String, Int]) extends Scope
  case class Limited(scope: Map[String, Int], parent: Scope) extends Scope

  class VariableNotDefined(msg: String) extends RuntimeException(msg)
  class ExitingGlobalScope(msg: String) extends RuntimeException(msg)

  def getValue(name: String) = State[Scope, Int] { case scope =>

    def searchScopes(sc: Scope): Int = {
      sc match {

        case Global(values) => values.get(name).getOrElse(
          throw new VariableNotDefined(s"Could not find variable: $name")
        )

        case Limited(values, parent) => {
          values.get(name).getOrElse(searchScopes(parent))
        }

      }
    }

    (scope, searchScopes(scope))

  }

  def newScope(variables: Map[String, Int]) = State[Scope, Unit] {
    case sc => (Limited(variables, sc), ())
  }

  def leaveScope = State[Scope, Unit] {
    case Limited(scope, parent) => (parent, ())
    case Global(scope) => throw new  ExitingGlobalScope("Can't exit the global scope")
  }

}

