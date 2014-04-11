package com.rumblesan.scalaexperiments.scalaztypes.monadtransformers.eithert

import scalaz._, Scalaz._

object ScalazEitherTExample {


  type Result[+A] = EitherT[List, String, A]

  def addTwo(value: Int) = (value + 2).point[Result]

  def addTwoAndDouble(value: Int) = EitherT(List((value + 2).right[String], (value + 2).right[String]))

  def addError(value: Int) = EitherT(List(value.right[String], "new error".left[Int]))

}

