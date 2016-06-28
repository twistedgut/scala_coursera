package com.gvolpe.week7

object waterpouring {
  
	val problem = new Pouring(Vector(4, 9, 16))
                                                  //> problem  : com.gvolpe.week7.Pouring = com.gvolpe.week7.Pouring@12fc438b
	problem.moves                             //> res0: scala.collection.immutable.IndexedSeq[Product with Serializable with c
                                                  //| om.gvolpe.week7.waterpouring.problem.Move] = Vector(Empty(0), Empty(1), Empt
                                                  //| y(2), Fill(0), Fill(1), Fill(2), Pour(0,1), Pour(0,2), Pour(1,0), Pour(1,2),
                                                  //|  Pour(2,0), Pour(2,1))
  problem.pathSets.take(3).toList                 //> res1: List[Set[com.gvolpe.week7.waterpouring.problem.Path]] = List(Set(--> V
                                                  //| ector(0, 0, 0)), Set(Fill(0)--> Vector(4, 0, 0), Fill(1)--> Vector(0, 9, 0),
                                                  //|  Fill(2)--> Vector(0, 0, 16)), Set(Fill(0) Fill(1)--> Vector(4, 9, 0), Fill(
                                                  //| 1) Fill(2)--> Vector(0, 9, 16), Fill(0) Fill(2)--> Vector(4, 0, 16), Fill(0)
                                                  //|  Pour(0,2)--> Vector(0, 0, 4), Fill(2) Pour(2,0)--> Vector(4, 0, 12), Fill(1
                                                  //| ) Pour(1,0)--> Vector(4, 5, 0), Fill(2) Fill(1)--> Vector(0, 9, 16), Fill(1)
                                                  //|  Pour(1,2)--> Vector(0, 0, 9), Fill(2) Fill(0)--> Vector(4, 0, 16), Fill(0) 
                                                  //| Pour(0,1)--> Vector(0, 4, 0), Fill(1) Fill(0)--> Vector(4, 9, 0), Fill(2) Po
                                                  //| ur(2,1)--> Vector(0, 9, 7)))

  problem.solution(7)                             //> res2: Stream[com.gvolpe.week7.waterpouring.problem.Path] = Stream(Fill(2) Po
                                                  //| ur(2,1)--> Vector(0, 9, 7), ?)

}