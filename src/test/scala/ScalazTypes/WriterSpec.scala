package com.rumblesan.scalaexperiments.tests.scalaztypes.writer

import scalaz._, Scalaz._

import org.specs2.mutable._

import com.rumblesan.scalaexperiments.scalaztypes.writer._


class WriterSpec extends Specification {

  "The Writer functions" should {
    "work like I'm expecting in a for comprehension" in {

      val input = 4

      val writer = (for {
        a <- ScalazWriterExample.double(input)
        b <- ScalazWriterExample.triple(a)
        c <- ScalazWriterExample.addOne(b)
      } yield c).run

      val instructions = writer._1
      val output = writer._2

      output must_==(25)
      instructions must_==(List(Double, Triple, AddOne))

    }
  }

}

