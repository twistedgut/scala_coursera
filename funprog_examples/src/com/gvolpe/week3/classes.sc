package com.gvolpe.week3

import com.gvolpe.week3.lists._

object classes {
  val t1 = new NonEmpty(3, Empty, Empty)          //> t1  : com.gvolpe.week3.NonEmpty = {.3.}
  val t2 = t1 incl 4                              //> t2  : com.gvolpe.week3.IntSet = {.3{.4.}}

  t2 contains 3                                   //> res0: Boolean = true
  t2 contains 6                                   //> res1: Boolean = false

  def error(msg: String) = throw new Error(msg)   //> error: (msg: String)Nothing
  // error("Exceptions in scala")

  val x = null                                    //> x  : Null = null
  val y: String = x                               //> y  : String = null

  // Type Parameters
  def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])
                                                  //> singleton: [T](elem: T)com.gvolpe.week3.lists.Cons[T]

  singleton[Int](12)                              //> res2: com.gvolpe.week3.lists.Cons[Int] = com.gvolpe.week3.lists.Cons@435914a
                                                  //| c
  singleton(4) // Inferred type parameter         //> res3: com.gvolpe.week3.lists.Cons[Int] = com.gvolpe.week3.lists.Cons@d5c4abf
                                                  //| 
  singleton[Boolean](true)                        //> res4: com.gvolpe.week3.lists.Cons[Boolean] = com.gvolpe.week3.lists.Cons@262
                                                  //| f4873

}