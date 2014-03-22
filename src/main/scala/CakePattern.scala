package com.rumblesan.scalaexperiments.cakepattern


object FooService {
  // Assume this is some external thing
  // DB access, REST call etc
  def foo() = List(1, 2, 3, 4)
}

object BarService {
  // Assume this is some external thing
  // DB access, REST call etc
  def bar() = Map("one" -> 1)
}

trait ServiceFooInterface {
  def foo(): List[Int]
}

trait ServiceBarInterface {
  def bar(): Map[String,Int]
}

trait ServiceFooImpl extends ServiceFooInterface {
  def foo(): List[Int] = FooService.foo()
}

trait ServiceBarImpl extends ServiceBarInterface {
  def bar(): Map[String,Int] = BarService.bar()
}



trait MyServiceOps {
  this: ServiceFooInterface with ServiceBarInterface =>

  def callFoo() = foo().map(x => x + 1)

  def callBar() = bar() ++ Map("three" -> 3)

}

object MyService extends MyServiceOps with ServiceFooImpl with ServiceBarImpl


