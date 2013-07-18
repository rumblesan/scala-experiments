package com.rumblesan.scalaexperiments.scalaztypes.validation

import scalaz._, Scalaz._


object MyValidationStuff {


  def likesEven(value: Int): \/[String, Int] = {
    if (value % 2 == 0) value.right[String]
    else "i hate odds".left[Int]
  }

  def likesNonZero(value: Int): \/[String, Int] = {
    if (value != 0) value.right[String]
    else "i hate zeros".left[Int]
  }

  def justTimes2(value: Int): Int = value * 2

}

