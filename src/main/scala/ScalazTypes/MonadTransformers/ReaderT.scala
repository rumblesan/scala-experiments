package com.rumblesan.scalaexperiments.scalaztypes.monadtransformers.readertoption

import scalaz._, Scalaz._, Kleisli._

object ScalazReaderTOptionExample {

  type ReaderTO[C, R] = ReaderT[Option, C, R]

  case class Config(value: Int)

  def addValue(input: Int):ReaderTO[Config, Int] = kleisli{c =>
    Some(c.value + input)
  }

  def addValueIfEven(input: Int): ReaderTO[Config, Int] = kleisli{c =>
    if ((input % 2) == 0) Some(c.value + input)
    else None
  }

}

