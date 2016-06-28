package com.gvolpe.week7

import com.sun.org.apache.xml.internal.serializer.ToStream

object streams {
  
	(1 to 100).toStream                       //> res0: scala.collection.immutable.Stream[Int] = Stream(1, ?)

	def streamRange(lo: Int, hi: Int): Stream[Int] = {
		if (lo >= hi) Stream.empty
		else Stream.cons(lo, streamRange(lo + 1, hi))
	}                                         //> streamRange: (lo: Int, hi: Int)Stream[Int]

	def listRange(lo: Int, hi: Int): List[Int] = {
		if (lo >= hi) Nil
		else lo :: listRange(lo + 1, hi)
	}                                         //> listRange: (lo: Int, hi: Int)List[Int]
	
	streamRange(1, 10).take(3).toList         //> res1: List[Int] = List(1, 2, 3)
  
}