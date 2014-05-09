package com.rumblesan.scalaexperiments.tests.scalaztypes.monadtransformers.readertoption

import scalaz._, Scalaz._

import org.specs2.mutable._

import com.rumblesan.scalaexperiments.scalaztypes.monadtransformers.readertoption._


class ReaderTOptionSpec extends Specification {

  import ScalazReaderTOptionExample._

  "The ReaderTOption functions" should {

    "allow me to handle nested options" in {

      val input = 2
      val config = Config(2)

      val result = for {
        v1 <- addValue(2)
        v2 <- addValue(v1)
        v3 <- addValueIfEven(v2)
      } yield v3

      val expected = Some(8)

      result(config) must_==(expected)

    }

    "deal with None values" in {

      val input = 2
      val config = Config(2)

      val result = for {
        v1 <- addValue(1)
        v2 <- addValue(v1)
        v3 <- addValueIfEven(v2)
      } yield v3

      val expected = None

      result(config) must_==(expected)

    }

  }

}

