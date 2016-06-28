package com.gvolpe.week5

object listfun {
  var nums = List(2, 6, -5, 32, -87, -4, 18)      //> nums  : List[Int] = List(2, 6, -5, 32, -87, -4, 18)

  // List filter
  nums filter (x => x > 0)                        //> res0: List[Int] = List(2, 6, 32, 18)
  nums filterNot (x => x > 0)                     //> res1: List[Int] = List(-5, -87, -4)
  // Pair of Lists with filter and filterNot results
  nums partition (x => x > 0)                     //> res2: (List[Int], List[Int]) = (List(2, 6, 32, 18),List(-5, -87, -4))
  
  // Longest prefix with predicated true
  nums takeWhile (x => x > 0)                     //> res3: List[Int] = List(2, 6)
  nums dropWhile (x => x > 0)                     //> res4: List[Int] = List(-5, 32, -87, -4, 18)
  // Pair of Lists with takeWhile and dropWhile results
  nums span (x => x > 0)                          //> res5: (List[Int], List[Int]) = (List(2, 6),List(-5, 32, -87, -4, 18))
  
  /* ------------------ SEGUNDA PARTE ---------------------- */
  
  def sum(xs: List[Int]): Int = xs match {
    case Nil => 0
    case y :: ys => y + sum(ys)
  }                                               //> sum: (xs: List[Int])Int
  
  def sum2(xs: List[Int]): Int = {
    //(0 :: xs) reduceLeft ((x, y) => x+y)
    (0 :: xs) reduceLeft (_ + _)
  }                                               //> sum2: (xs: List[Int])Int
  
  def sumWithFoldLeft(xs: List[Int]): Int = {
    (xs foldLeft 0)(_ + _)
  }                                               //> sumWithFoldLeft: (xs: List[Int])Int
  
  sumWithFoldLeft(nums)                           //> res6: Int = -38
  sum2(nums)                                      //> res7: Int = -38
  sum(nums )                                      //> res8: Int = -38
  
  def concat[T](xs: List[T], ys: List[T]): List[T] = {
    (xs foldRight ys)(_ :: _)
  }                                               //> concat: [T](xs: List[T], ys: List[T])List[T]
  
  concat(List("gabriel", "marcelo"), List("volpe", "ybañez"))
                                                  //> res9: List[String] = List(gabriel, marcelo, volpe, ybañez)
                                                  
}