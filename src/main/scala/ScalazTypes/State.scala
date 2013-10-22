package com.rumblesan.scalaexperiments.scalaztypes.state

import scalaz._, Scalaz._

object ScalazStateStuff {

  type Parsable = Pair[String, String]

  def createProgram(text: String): Parsable = (text, "")

}

