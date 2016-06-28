package com.gvolpe.week6

object collections {
  
	val xs = Array(1,2,3,44)                  //> xs  : Array[Int] = Array(1, 2, 3, 44)
	xs map (x => x * 2)                       //> res0: Array[Int] = Array(2, 4, 6, 88)
  
  val s = "Gabriel Marcelo Volpe"                 //> s  : String = Gabriel Marcelo Volpe
  s filter (c => c.isUpper)                       //> res1: String = GMV
  
  val r1: Range = 1 to 5                          //> r1  : Range = Range(1, 2, 3, 4, 5)
  val r2: Range = 1 until 5                       //> r2  : Range = Range(1, 2, 3, 4)
  val r3: Range = 1 to 10 by 3                    //> r3  : Range = Range(1, 4, 7, 10)
  val r4: Range = 6 to 1 by -2                    //> r4  : Range = Range(6, 4, 2)
  
  xs exists (x => x == 3)                         //> res2: Boolean = true
  xs forall (x => x == 3)                         //> res3: Boolean = false
  
  val pairs = List(1,2,3,4,5,6,7) zip s           //> pairs  : List[(Int, Char)] = List((1,G), (2,a), (3,b), (4,r), (5,i), (6,e), 
                                                  //| (7,l))
  pairs.unzip                                     //> res4: (List[Int], List[Char]) = (List(1, 2, 3, 4, 5, 6, 7),List(G, a, b, r, 
                                                  //| i, e, l))
  
  s flatMap (c => List('.', c))                   //> res5: String = .G.a.b.r.i.e.l. .M.a.r.c.e.l.o. .V.o.l.p.e
  
  xs.sum                                          //> res6: Int = 50
  xs.product                                      //> res7: Int = 264
  xs.max                                          //> res8: Int = 44
  
  /* --------------------- SCALA PRODUCT --------------------------- */
  def scalaProduct(xs: Vector[Int], ys: Vector[Int]): Int = {
  	(xs zip ys).map(xy => xy._1 * xy._2).sum
  }                                               //> scalaProduct: (xs: Vector[Int], ys: Vector[Int])Int
  
  // Abreviated Pattern Matching
  def scalaProductPM(xs: Vector[Int], ys: Vector[Int]): Int = {
  	(xs zip ys).map{case (x, y) => x * y}.sum
  }                                               //> scalaProductPM: (xs: Vector[Int], ys: Vector[Int])Int
  
  val nums1 = Vector(1,3,5,7)                     //> nums1  : scala.collection.immutable.Vector[Int] = Vector(1, 3, 5, 7)
  val nums2 = Vector(2,4,6,8)                     //> nums2  : scala.collection.immutable.Vector[Int] = Vector(2, 4, 6, 8)
  
  scalaProduct(nums1, nums2)                      //> res9: Int = 100
  scalaProductPM(nums1, nums2)                    //> res10: Int = 100
  
}