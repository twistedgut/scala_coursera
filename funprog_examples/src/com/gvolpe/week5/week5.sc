package com.gvolpe.week5

import com.gvolpe.week5._

object week5 {
  
  var mlf = MoreListsFunctions                    //> mlf  : com.gvolpe.week5.MoreListsFunctions.type = com.gvolpe.week5.MoreLists
                                                  //| Functions$@5c594008
  
  var lista = List(3,6,23,54,33,12)               //> lista  : List[Int] = List(3, 6, 23, 54, 33, 12)
  mlf.last(lista)                                 //> res0: Int = 12
  mlf.init(lista)                                 //> res1: List[Int] = List(3, 6, 23, 54, 33)
  mlf.reverse(lista)                              //> res2: List[Int] = List(12, 33, 54, 23, 6, 3)
  
  mlf.removeAt(1, lista)                          //> res3: List[Int] = List(3, 23, 54, 33, 12)
  
  var sort = MergeSorting                         //> sort  : com.gvolpe.week5.MergeSorting.type = com.gvolpe.week5.MergeSorting$@
                                                  //| baea1ed
  sort.msort(lista)                               //> res4: List[Int] = List(3, 6, 12, 23, 33, 54)
  
  sort.genericmsort(lista)((x, y) => x < y)       //> res5: List[Int] = List(3, 6, 12, 23, 33, 54)
  var letters = List("a", "y", "x", "e", "r", "c", "g")
                                                  //> letters  : List[String] = List(a, y, x, e, r, c, g)
  sort.genericmsort(letters )((x, y) => x.compareTo(y) < 0)
                                                  //> res6: List[String] = List(a, c, e, g, r, x, y)
                                                   
  sort.msortWithOrdering(lista)(Ordering.Int)     //> res7: List[Int] = List(3, 6, 12, 23, 33, 54)
  sort.msortWithOrdering(letters)(Ordering[String])
                                                  //> res8: List[String] = List(a, c, e, g, r, x, y)
  // Implicit parameter
  sort.msortWithOrdering(lista)                   //> res9: List[Int] = List(3, 6, 12, 23, 33, 54)
  
  // Higher Order Lists
  var hol = HigherOrderLists                      //> hol  : com.gvolpe.week5.HigherOrderLists.type = com.gvolpe.week5.HigherOrder
                                                  //| Lists$@72d3f17d
  var numbers = List(2.1, 4.3, 5.3, 7.7)          //> numbers  : List[Double] = List(2.1, 4.3, 5.3, 7.7)
  hol.scaleList(numbers, 2)                       //> res10: List[Double] = List(4.2, 8.6, 10.6, 15.4)
 
  // Generalizing with Map
  numbers map (x => x * 2)                        //> res11: List[Double] = List(4.2, 8.6, 10.6, 15.4)
 
  var nums = List(2,3,5,6,8)                      //> nums  : List[Int] = List(2, 3, 5, 6, 8)
  hol.squareList(nums)                            //> res12: List[Int] = List(4, 9, 25, 36, 64)
  hol.squareListWithMap(nums)                     //> res13: List[Int] = List(4, 9, 25, 36, 64)
  
  // Negatives and positives
  var negpos = List(2, -5, 32, -87, 18)           //> negpos  : List[Int] = List(2, -5, 32, -87, 18)
  hol.posElems(negpos)                            //> res14: List[Int] = List(2, 32, 18)
  hol.posElemsWithFilter(negpos )                 //> res15: List[Int] = List(2, 32, 18)
 
  val data = List("a", "a", "a", "b", "c", "c", "a")
                                                  //> data  : List[String] = List(a, a, a, b, c, c, a)
  var list = hol.pack(data)                       //> list  : List[List[String]] = List(List(a, a, a), List(b), List(c, c), List(
                                                  //| a))
  list foreach (x => println((x(0), x.length)))   //> (a,3)
                                                  //| (b,1)
                                                  //| (c,2)
                                                  //| (a,1)
                                                  
  list foreach (x => println((x(0), x.length) :: List()))
                                                  //> List((a,3))
                                                  //| List((b,1))
                                                  //| List((c,2))
                                                  //| List((a,1))
                                                    
  list map (ys => (ys.head, ys.length))           //> res16: List[(String, Int)] = List((a,3), (b,1), (c,2), (a,1))
                                                  
  hol.encode(data)                                //> res17: List[(String, Int)] = List((a,3), (b,1), (c,2), (a,1))
}