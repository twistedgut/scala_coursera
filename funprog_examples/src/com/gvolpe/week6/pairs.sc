package com.gvolpe.week6

object pairs {
  
  /* --------------------- PRIME NUMBER --------------------------- */
  def isPrime(n: Int): Boolean = {
  	if (n < 1) false
  	else if (n == 1) true
  	else (2 until n) forall (d => n % d != 0)
  }                                               //> isPrime: (n: Int)Boolean
  
  isPrime(2)                                      //> res0: Boolean = true
  isPrime(-2)                                     //> res1: Boolean = false
  isPrime(14)                                     //> res2: Boolean = false
  isPrime(59)                                     //> res3: Boolean = true
  
  /* --------------------- PAIRS --------------------------- */
  
	val n = 7                                 //> n  : Int = 7
	
	val xss = (1 until n) map (i => (1 until i) map (j => (i, j)))
                                                  //> xss  : scala.collection.immutable.IndexedSeq[scala.collection.immutable.Inde
                                                  //| xedSeq[(Int, Int)]] = Vector(Vector(), Vector((2,1)), Vector((3,1), (3,2)), 
                                                  //| Vector((4,1), (4,2), (4,3)), Vector((5,1), (5,2), (5,3), (5,4)), Vector((6,1
                                                  //| ), (6,2), (6,3), (6,4), (6,5)))
 
  // Equivalent to built-in method flatten
  (xss foldRight Seq[Any]())(_ ++ _)              //> res4: Seq[Any] = Vector((2,1), (3,1), (3,2), (4,1), (4,2), (4,3), (5,1), (5,
                                                  //| 2), (5,3), (5,4), (6,1), (6,2), (6,3), (6,4), (6,5))
                                                  
	xss.flatten                               //> res5: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,1
                                                  //| ), (3,2), (4,1), (4,2), (4,3), (5,1), (5,2), (5,3), (5,4), (6,1), (6,2), (6,
                                                  //| 3), (6,4), (6,5))
                                                  
  val xs = (1 until n) flatMap (i => (1 until i) map (j => (i, j)))
                                                  //> xs  : scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,1
                                                  //| ), (3,2), (4,1), (4,2), (4,3), (5,1), (5,2), (5,3), (5,4), (6,1), (6,2), (6,
                                                  //| 3), (6,4), (6,5))
                                                  
  xss.flatten filter (pair => isPrime(pair._1 + pair._2))
                                                  //> res6: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,2
                                                  //| ), (4,1), (4,3), (5,2), (6,1), (6,5))
                                                  
  /* --------------------- FOR LOOP SOLUTION --------------------------- */
  for {
  	i <- 1 until n
  	j <- 1 until i
  	if isPrime(i + j)
  } yield (i ,j)                                  //> res7: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,
                                                  //| 2), (4,1), (4,3), (5,2), (6,1), (6,5))
 
  // Translation del for de arriba (lo que hace el compilador)
  (1 until n).flatMap(i =>
  	(1 until i).withFilter(j =>
  		isPrime(i+j)).map(j => (i,j)))    //> res8: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,
                                                  //| 2), (4,1), (4,3), (5,2), (6,1), (6,5))
  
  def scalaProductFor(xs: Vector[Int], ys: Vector[Int]): Int = {
    (for ((x, y) <- xs zip ys) yield (x * y)).sum
  }                                               //> scalaProductFor: (xs: Vector[Int], ys: Vector[Int])Int
  
  val nums1 = Vector(1,3,5,7)                     //> nums1  : scala.collection.immutable.Vector[Int] = Vector(1, 3, 5, 7)
  val nums2 = Vector(2,4,6,8)                     //> nums2  : scala.collection.immutable.Vector[Int] = Vector(2, 4, 6, 8)
  
  scalaProductFor(nums1, nums2)                   //> res9: Int = 100
 
}