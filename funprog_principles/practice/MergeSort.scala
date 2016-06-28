import math.Ordering
object MergeSort {
  def main(args: Array[String]) = println(msort(List(2, 1, 5, 2, 76, 7, 2)))

  def msort[T](list: List[T])(implicit ord: Ordering[T]): List[T] = {
    val mid = list.length / 2
    if (mid == 0) list
    else {
      def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (ord.lt(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }
      val (first, second) = list.splitAt(mid)
      merge(msort(first), msort(second))
    }
  }
}