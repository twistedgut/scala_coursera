package com.gvolpe.week5

object MergeSorting {

  type ??? = Nothing

  def msort(xs: List[Int]): List[Int] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (fst, snd) = xs splitAt n
      merge(msort(fst), msort(snd))
    }
  }

  // Merge with nested single pattern matching
  def oldMerge(xs: List[Int], ys: List[Int]) : List[Int] = xs match {
    case Nil => ys
    case x :: xs1 => ys match {
      case Nil => xs
      case y :: ys1 =>
        if (x < y) x :: merge(xs1, ys)
        else y :: merge(xs, ys1)
    }
  }

  // Merge with pair pattern matching
  def merge(xs: List[Int], ys: List[Int]) : List[Int] = (xs, ys) match {
    case (xs, Nil) => xs
    case (Nil, ys) => ys
    case (x :: xs1, y :: ys1) => 
        if (x < y) x :: merge(xs1, ys)
        else y :: merge(xs, ys1)
  }

  // Generic Parametrization
  def genericmsort[T](xs: List[T])(lt: (T, T) => Boolean): List[T] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (fst, snd) = xs splitAt n
      genericmerge(genericmsort(fst)(lt), genericmsort(snd)(lt))(lt)
    }
  }
  
  def genericmerge[T](xs: List[T], ys: List[T])(lt: (T, T) => Boolean) : List[T] = (xs, ys) match {
    case (xs, Nil) => xs
    case (Nil, ys) => ys
    case (x :: xs1, y :: ys1) => 
        if (lt(x, y)) x :: genericmerge(xs1, ys)(lt)
        else y :: genericmerge(xs, ys1)(lt)
  }
  
  // Using provided Ordering
  def msortWithOrdering[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (fst, snd) = xs splitAt n
      mergeWithOrdering(msortWithOrdering(fst), msortWithOrdering(snd))(ord)
    }
  }
  
  def mergeWithOrdering[T](xs: List[T], ys: List[T])(implicit ord: Ordering[T]) : List[T] = (xs, ys) match {
    case (xs, Nil) => xs
    case (Nil, ys) => ys
    case (x :: xs1, y :: ys1) => 
        if (ord.lt(x, y)) x :: mergeWithOrdering(xs1, ys)
        else y :: mergeWithOrdering(xs, ys1)
  }
   
}