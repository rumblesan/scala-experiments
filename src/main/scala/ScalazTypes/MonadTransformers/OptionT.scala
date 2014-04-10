package com.rumblesan.scalaexperiments.scalaztypes.monadtransformers.optiont

import scalaz._, Scalaz._

object ScalazOptionTExample {


  type Result[+A] = OptionT[List, A]

  def addTwo(value: Int) = (value + 2).point[Result]

  def addTwoAndDouble(value: Int) = OptionT(List(Some(value + 2), Some(value + 2)))

}

