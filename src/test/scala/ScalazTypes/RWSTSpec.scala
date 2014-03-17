package com.rumblesan.scalaexperiments.tests.scalaztypes.reader

import scalaz._, Scalaz._

import org.specs2.mutable._

import com.rumblesan.scalaexperiments.scalaztypes.rwsmonad._
import com.rumblesan.scalaexperiments.scalaztypes.rwsmonad.ReaderWriterStateExample._


class RWSTSpec extends Specification {

  "The RWST functions" should {
    "work like I'm expecting in a for comprehension" in {

      val config = Config(4)

      val state = List(1, 3, 4)

      val rwstExample: ReaderWriterState[Config, MyLog, MyState, Int] = for {
        _ <- log("Logging to the logger")
        n <- getConfigNumber(2)
        _ <- addValueToState(n)
        m <- multiplyTopStateValue(n)
        _ <- addValueToState(m)
        _ <- log("need to sort out return value now")
        o <- getTopTwoStateValueSum()
      } yield o

      val Need(logs, result, finalstate) = rwstExample.run((config, state))

      result must_==(37)

    }
  }

}

