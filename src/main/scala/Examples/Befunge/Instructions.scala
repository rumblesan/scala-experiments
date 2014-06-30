package com.rumblesan.examples.befunge

import scalaz._, Scalaz._


sealed trait Instruction

case object NOP   extends Instruction
case object Exit  extends Instruction

case object Up    extends Instruction
case object Down  extends Instruction
case object Right extends Instruction
case object Left  extends Instruction
case object Jump  extends Instruction

case object Sum      extends Instruction
case object Subtract extends Instruction
case object Multiply extends Instruction
case object Divide   extends Instruction
case object Modulo   extends Instruction

case object Not         extends Instruction
case object GreaterThan extends Instruction

case object Random    extends Instruction
case object Duplicate extends Instruction
case object Swap      extends Instruction
case object Discard   extends Instruction

case object PopInt    extends Instruction
case object PopChar   extends Instruction

case class  Data(value: Integer) extends Instruction
case object Print    extends Instruction

case object IfHop    extends Instruction
