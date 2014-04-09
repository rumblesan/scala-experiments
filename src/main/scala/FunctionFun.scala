package com.rumblesan.scalaexperiments.functionfun

object FunctionFun {

  val curry3: Int => Int => Int => Int = a => b => c => {
    a + b + c
  }

  def valueFunction1(a: Int): Int = a * 3

  val valueFunction2: Int => Int = a => a * 3

  def secondOrderFunc(input: Int, func: Int => Int): Int = func(input)

}


