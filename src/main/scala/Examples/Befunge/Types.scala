package com.rumblesan.examples.befunge

import scalaz._, Scalaz._


object Types {

  type Grid = Vector[Vector[Instruction]]

  type Stack = List[Integer]

  type VMState[A] = State[VM, A]

  sealed trait Direction
  case object North extends Direction
  case object South extends Direction
  case object East  extends Direction
  case object West  extends Direction


}

