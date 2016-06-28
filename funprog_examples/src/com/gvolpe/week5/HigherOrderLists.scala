package com.gvolpe.week5

object HigherOrderLists {

  def scaleList(xs: List[Double], factor: Double): List[Double] = xs match {
    case Nil => xs
    case y :: ys => y * factor :: scaleList(ys, factor)
  }

  def squareList(xs: List[Int]): List[Int] = xs match {
    case Nil => xs
    case y :: ys => y * y :: squareList(ys)
  }

  def squareListWithMap(xs: List[Int]): List[Int] = {
    xs map (x => x * x)
  }

  def posElems(xs: List[Int]): List[Int] = xs match {
    case Nil => xs
    case y :: ys => if (y > 0) y :: posElems(ys) else posElems(ys)
  }

  def posElemsWithFilter(xs: List[Int]): List[Int] = {
    xs filter (x => x > 0)
  }

  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case y :: ys =>
      val (first, rest) = xs span (x => x == y)
      first :: pack(rest)
  }

  def encode[T](xs: List[T]): List[(T, Int)] = {
    pack(xs) map (ys => (ys.head, ys.length))
  }

}