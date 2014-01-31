package com.rumblesan.scalaexperiments.scalaztypes.lens

import scalaz._, Scalaz._

case class Point(x: Double, y: Double)

case class Turtle(
  position: Point,
  heading: Double
)

object TurtleLenses {

  val turtleHeading = Lens.lensu[Turtle, Double] (
    (t, h) => t.copy(heading = h),
    _.heading
  )

  val turtlePosition = Lens.lensu[Turtle, Point] (
    (t, pos) => t.copy(position = pos),
    _.position
  )

}

object PointLenses {

  val pointX = Lens.lensu[Point, Double] (
    (p, xVal) => p.copy(x = xVal),
    _.x
  )

  val pointY = Lens.lensu[Point, Double] (
    (p, yVal) => p.copy(y = yVal),
    _.y
  )

}

