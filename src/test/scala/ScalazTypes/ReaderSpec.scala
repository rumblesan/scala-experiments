package com.rumblesan.scalaexperiments.tests.scalaztypes.reader

import scalaz._, Scalaz._

import org.specs2.mutable._

import com.rumblesan.scalaexperiments.scalaztypes.reader._


class ReaderSpec extends Specification {

  "The Reader functions" should {
    "work like I'm expecting in a for comprehension" in {

      val config = Config(4)

      val reader = for {
        res1 <- ScalazReaderStuff.addFive
        res2 <- ScalazReaderStuff.addConfNumber(res1)
        res3 <- ScalazReaderStuff.addConfNumber(res2)
      } yield res3

      reader(config) must_==(17)
    }
  }

}

