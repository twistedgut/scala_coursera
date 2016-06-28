package com.gvolpe.week7

object lazyeval {
  
  lazy val x = 5 + 2                              //> x: => Int
  x                                               //> res0: Int = 7
  
  def expr = {
  	val x = { print("x"); 1 }
  	lazy val y = { print("y"); 2 }
  	def z = { print("z"); 3 }
  	z + y + x + z + y + x
  }                                               //> expr: => Int
  expr                                            //> xzyzres1: Int = 12
  
}