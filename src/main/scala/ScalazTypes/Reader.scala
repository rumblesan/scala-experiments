package com.rumblesan.scalaexperiments.scalaztypes.reader

import scalaz._, Scalaz._

case class Config(number: Int)

object ScalazReaderExample {

  def addConfNumber(value: Int): Reader[Config,Int] = Reader{
    config => value + config.number
  }

  def addFive: Reader[Config,Int] = Reader{
    config => config.number + 5
  }

}

