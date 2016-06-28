package com.gvolpe.week4

import com.gvolpe.week3.lists._

object MyList {

  // MyList(1, 2) = MyList.apply(1, 2)
  def apply[T](x: T, y: T): GenericList[T] = new Cons(x, new Cons(y, new Nil))

  // MyList(1) = MyList.apply(1)
  def apply[T](x: T): GenericList[T] = new Cons(x, new Nil)

  // Empty List: MyList()
  def apply[T]: GenericList[T] = new Nil

}