package com.rumblesan.scalaexperiments.myreader

import scalaz._, Scalaz._

object MyReaderT {

  type MyReaderT[A,B] = A => B

  def point[A,B](value: B): MyReaderT[A,B] = _ => value

}

