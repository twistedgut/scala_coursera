package com.gvolpe.week4

import com.gvolpe.week3._
import com.gvolpe.week4._

object week4 {
  MyList()                                        //> res0: com.gvolpe.week3.lists.GenericList[Unit] = {()}{Nil}
  MyList(1,3)                                     //> res1: com.gvolpe.week3.lists.GenericList[Int] = {1}{3}{Nil}
  MyList(4)                                       //> res2: com.gvolpe.week3.lists.GenericList[Int] = {4}{Nil}
  
  //def assertAllPos[S <: IntSet](r: S): S = Empty
  def assertAllPos(r: IntSet): IntSet = Empty     //> assertAllPos: (r: com.gvolpe.week3.IntSet)com.gvolpe.week3.IntSet
  
  // Peano numbers (natural numbers)
  Zero                                            //> res3: com.gvolpe.week4.Zero.type = Zero
  new Succ(Zero)                                  //> res4: com.gvolpe.week4.Succ = One
  var dos = new Succ(new Succ(Zero))              //> dos  : com.gvolpe.week4.Succ = Two
  var tres = new Succ(new Succ(new Succ(Zero)))   //> tres  : com.gvolpe.week4.Succ = Tree
  var cinco = tres + dos                          //> cinco  : com.gvolpe.week4.Nat = Five
  var seis = tres + tres                          //> seis  : com.gvolpe.week4.Nat = Six
  var nueve = seis + tres                         //> nueve  : com.gvolpe.week4.Nat = Nine
  var doce = seis + seis                          //> doce  : com.gvolpe.week4.Nat = Natural number not found
  
  // Decomposition
  var num1 = new Number2(4)                       //> num1  : com.gvolpe.week4.Number2 = com.gvolpe.week4.Number2@391416b3
  var num2 = new Number2(3)                       //> num2  : com.gvolpe.week4.Number2 = com.gvolpe.week4.Number2@55de5f0b
  var sum = new Sum2(num1, num2)                  //> sum  : com.gvolpe.week4.Sum2 = com.gvolpe.week4.Sum2@318f37c
  
  num1.eval                                       //> res5: Int = 4
  sum.eval                                        //> res6: Int = 7
  
  // Pattern Matching
  PatternMatchingDecomposition.eval(PMSum(PMNumber(2), PMNumber(3)))
                                                  //> res7: Int = 5
  
  var suma = PMSum(PMSum(PMNumber(1), PMNumber(2)), PMNumber(7))
                                                  //> suma  : com.gvolpe.week4.PMSum = PMSum(PMSum(PMNumber(1),PMNumber(2)),PMNumb
                                                  //| er(7))
  suma.eval                                       //> res8: Int = 10
  suma.show                                       //> res9: String = 1 + 2 + 7
  
  var prod = PMSum(PMProd(PMNumber(2), PMNumber(3)), PMNumber(3))
                                                  //> prod  : com.gvolpe.week4.PMSum = PMSum(PMProd(PMNumber(2),PMNumber(3)),PMNum
                                                  //| ber(3))
                                                  
  prod.eval                                       //> res10: Int = 9
  prod.show                                       //> res11: String = 2 * 3 + 3
  
  var prod2 = PMProd(PMSum(PMNumber(2), PMNumber(3)), PMNumber(3))
                                                  //> prod2  : com.gvolpe.week4.PMProd = PMProd(PMSum(PMNumber(2),PMNumber(3)),PM
                                                  //| Number(3))
  prod2.eval                                      //> res12: Int = 15
  prod2.show                                      //> res13: String = 2 + 3 * 3

  // Sorting Lists
  var nums = List(2, 6, 3, 9, 7)                  //> nums  : List[Int] = List(2, 6, 3, 9, 7)
  SortingLists.isort(nums)                        //> res14: List[Int] = List(2, 3, 6, 7, 9)
  
  // Maps
  var acc = Map[Char, Int]()                      //> acc  : scala.collection.immutable.Map[Char,Int] = Map()
  var char = 'a'                                  //> char  : Char = a
  var mapa = acc.updated(char, acc.withDefault(key => 0)(char) + 1)
                                                  //> mapa  : scala.collection.immutable.Map[Char,Int] = Map(a -> 1)
  mapa.updated(char, mapa.withDefault(key => 0)(char) + 1)
                                                  //> res15: scala.collection.immutable.Map[Char,Int] = Map(a -> 2)
}