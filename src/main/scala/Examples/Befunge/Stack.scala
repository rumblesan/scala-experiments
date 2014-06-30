package com.rumblesan.examples.befunge

import scalaz._, Scalaz._

import Types._


object Stack {

  def pushStack(value: Integer): VMState[Unit] = {
    State.modify(vm =>
      vm.copy(stack = value :: vm.stack)
    )
  }

  def peekStack: VMState[Integer] = {
    State.gets(vm =>
      vm.stack.head
    )
  }

  def popStack: VMState[Integer] = {
    for {
      v <- peekStack
      _ <- State.modify[VM](vm =>
        vm.copy(stack = vm.stack.tail)
      )
    } yield v
  }

}

