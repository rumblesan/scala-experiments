package com.rumblesan.scalaexperiments.tests.myreader

import scalaz._, Scalaz._

import org.specs2.mutable._

import com.rumblesan.scalaexperiments.myreader._ 
import MyReader._

case class Config(num1: Int, num2: Int)

class MyReaderSpec extends Specification {

  "The 'MyReader Example'" should {
    "Follow the left identity law" in {

      val config = Config(1, 2)

      def mFunc(value: Int): MyReader[Config,Int] = { config =>
        value + config.num1
      }

      val left = MyReader.point[Config,Int](3).flatMap(mFunc)

      val right = mFunc(3)

      left(config) must_==(right(config))

    }
    "Follow the right identity law" in {

      val config = Config(1, 2)

      val m = MyReader.point[Config,Int](3)

      val lawVal = m.flatMap(MyReader.point[Config,Int])

      lawVal(config) must_==(m(config))

    }
    "Follow the associativity law" in {

      val config = Config(1, 2)

      val m = MyReader.point[Config,Int](3)

      def mFunc(value: Int): MyReader[Config,Int] = { config =>
        value + config.num1
      }

      val left = m.flatMap(
        mFunc
      ).flatMap(
        mFunc
      )

      val right = m.flatMap(x =>
        mFunc(x).flatMap(mFunc)
      )

      left(config) must_==(right(config))

    }
    "All work correctly in for comprehensions" in {

      val config = Config(1, 2)

      def mFunc1(value: Int): MyReader[Config,Int] = { config =>
        value + config.num1
      }

      def mFunc2(value: Int): MyReader[Config,Int] = { config =>
        value + config.num2
      }

      val reader = for {
        res1 <- mFunc1(3)
        res2 <- MyReader.point[Config,Int](5)
        res3 = res1 + res2
        res4 <- mFunc2(res3)
      } yield res4

      val result = reader(config)

      result must_==(11)

    }
  }

}

