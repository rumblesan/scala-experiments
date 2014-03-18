package com.rumblesan.scalaexperiments.tests.forcomprehension

import org.specs2.mutable._

import com.rumblesan.scalaexperiments.forcomprehension.ForComprehension.OptionExample._
import com.rumblesan.scalaexperiments.forcomprehension.ForComprehension.ListExample._


class ForComprehensionSpec extends Specification {

  "The 'Option Examples'" should {
    "work like the equivilent flatmaps" in {

      val input: Option[Int] = Some(2)

      val forResult: Option[Int] = for {
        i <- input
        v1 <- maybeValue(i)
        v2 <- maybeMult(v1, 3)
        v3 = v2 + 1
        v4 <- maybePlus(v2, v3)
      } yield v4 + 1

      val mapResult: Option[Int] = input.flatMap( i =>
        maybeValue(i).flatMap( v1 =>
          maybeMult(v1, 3).flatMap( v2 => {
            val v3 = v2 + 1
            maybePlus(v2, v3).map(v4 => v4 + 1)
          })
        )
      )

      forResult must_==(Some(14))
      mapResult must_==(Some(14))

    }
  }

  "The 'List Examples'" should {
    "work like the equivilent flatmaps" in {

      val input: List[Int] = List(4, 12)

      val forResult: List[List[Int]] = for {
        v1 <- input
        v2 <- oneUpAndDown(v1)
        v3 = doubleIt(v2)
      } yield 0 :: v3

      val mapResult: List[List[Int]] = input.flatMap(v1 =>
        oneUpAndDown(v1).map(v2 => {
          val v3 = doubleIt(v2)
          v3
        }).map(v3 => 0 :: v3)

      )


      val expected = List(
        List(0, 3, 3),
        List(0, 4, 4),
        List(0, 5, 5),
        List(0, 11, 11),
        List(0, 12, 12),
        List(0, 13, 13)
      )

      forResult must_== expected

      mapResult must_== expected

    }
  }
}
