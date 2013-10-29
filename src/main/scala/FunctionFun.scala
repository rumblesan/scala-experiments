package com.rumblesan.scalaexperiments.functionfun

object FunctionFun {

  val curry3: Int => Int => Int => Int = a => b => c => {
    a + b + c
  }

}


