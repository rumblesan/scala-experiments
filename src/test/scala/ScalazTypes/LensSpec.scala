package com.rumblesan.scalaexperiments.tests.scalaztypes.lens

import scalaz._, Scalaz._

import org.specs2.mutable._

import com.rumblesan.scalaexperiments.scalaztypes.lens._
import TurtleLenses._
import PointLenses._


class LensSpec extends Specification {

  "The Lens functions" should {
    "work like I'm expecting for just the Point class" in {

      val start = Point(3, 6)

      pointX.get(start) must_==(3)

      val end = pointY.set(start, 5)

      end must_==(Point(3, 5))

    }
    "compose nicely" in {

      val turtleY = turtlePosition >=> pointY

      val startTurtle = Turtle(
        Point(
          3,
          6
        ),
        4
      )

      val endTurtle = Turtle(
        Point(
          3,
          2
        ),
        4
      )

      turtleY.set(startTurtle, 2) must_==(endTurtle)

    }
  }

}

