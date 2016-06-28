package com.gvolpe.week3.lists

import com.gvolpe.week3.lists._

object listsExercise {

  def nth[T](n: Int, xs: GenericList[T]): T = {
  	if (xs.isEmpty) throw new ArrayIndexOutOfBoundsException
    else if (n == 0) xs.head
    else nth(n - 1, xs.tail)
  }                                               //> nth: [T](n: Int, xs: com.gvolpe.week3.lists.GenericList[T])T
  
  val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))
                                                  //> list  : com.gvolpe.week3.lists.Cons[Int] = {1}{2}{3}{Nil}
  nth(2, list)                                    //> res0: Int = 3
 
  //nth(4, list) IndexOutOfBounds
}