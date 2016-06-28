package com.gvolpe.week5

object CombineHigherOrderLists {

  def sum(xs: List[Int]): Int = xs match {
    case Nil => 0
    case y :: ys => y + sum(ys)
  }

  def sumWithReduceLeft(xs: List[Int]): Int = {
    (0 :: xs) reduceLeft (_ + _)
  }

  /**
   * foldLeft y foldRight en estos casos es lo mismo. Solo cambia el orden de
   * los parentesis en que se van tomando los elementos de la lista y en que
   * foldRight aplica el valor z al final de la operación y foldLeft al principio.
   */
  def sumWithFoldLeft(xs: List[Int]): Int = {
    (xs foldLeft 0)(_ + _)
  }

  def sumWithFoldRight(xs: List[Int]): Int = {
    (xs foldRight 0)(_ + _)
  }

  def concat[T](xs: List[T], ys: List[T]): List[T] = {
    (xs foldRight ys)(_ :: _)
  }

}