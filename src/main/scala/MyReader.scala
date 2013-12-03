package com.rumblesan.scalaexperiments.myreader

import scalaz._, Scalaz._

object MyReader {

  type MyReader[A,B] = A => B

  def point[A,B](value: B): MyReader[A,B] = _ => value

}

