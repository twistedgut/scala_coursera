package com.gvolpe

object MyApp extends App {

  val sq = (x: Int) => x * x
  val showSq = (x: Int) => println(sq(x))

  def esImpar = (x: Int) => x % 2 != 0
  def esPar = (x: Int) => x % 2 == 0

  //println(sq(5))

  var lista = List(1, 2, 3, 4, 5)
  //lista.foreach(println)
  //lista foreach println

  //  lista.reverse.foreach(println)
  //
  //  println("---------------")
  //
  //  lista.foreach(showSq)

  println(">>>> IMPARES <<<<<")
  lista.filter(esImpar).foreach(println)

  println(">>>> PARES <<<<<")
  lista.filter(esPar).foreach(println)

  println(">>>> PASCAL TRIANGLE <<<<<")
  //println(new Main().pascal(3, 7));

}