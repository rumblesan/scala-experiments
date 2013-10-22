package com.rumblesan.scalaexperiments.scalaztypes.writer

import scalaz._, Scalaz._

sealed trait Instruction
case object Double extends Instruction
case object Triple extends Instruction
case object AddOne extends Instruction

object ScalazWriterStuff {

  def double(value: Int): Writer[List[Instruction], Int] = (value * 2).set(List(Double))

  def triple(value: Int): Writer[List[Instruction], Int] = (value * 3).set(List(Triple))

  def addOne(value: Int): Writer[List[Instruction], Int] = (value + 1).set(List(AddOne))

}

