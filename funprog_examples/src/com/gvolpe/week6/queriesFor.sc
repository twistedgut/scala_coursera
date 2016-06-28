package com.gvolpe.week6

import com.gvolpe.week6._

object queriesFor {
  
  var books = Set(
  	Book(title = "Ficciones", authors = List("Jorge Luis Borges")),
  	Book(title = "Los tres mosqueteros", authors = List("Alejandro Dumas")),
  	Book(title = "La iliada", authors = List("Homero")),
  	Book(title = "La odisea", authors = List("Homero")),
  	Book(title = "asfjg", authors = List("Homero")),
  	Book(title = "La llave del destino", authors = List("Glenn Cooper")),
  	Book(title = "Cien años de soledad", authors = List("Gabriel Garcia Marquez")),
  	Book(title = "El alquimista", authors = List("Paulo Coelho"))
  )                                               //> books  : scala.collection.immutable.Set[com.gvolpe.week6.Book] = Set(Book(as
                                                  //| fjg,List(Homero)), Book(Cien años de soledad,List(Gabriel Garcia Marquez)),
                                                  //|  Book(La llave del destino,List(Glenn Cooper)), Book(La odisea,List(Homero))
                                                  //| , Book(Los tres mosqueteros,List(Alejandro Dumas)), Book(El alquimista,List(
                                                  //| Paulo Coelho)), Book(Ficciones,List(Jorge Luis Borges)), Book(La iliada,List
                                                  //| (Homero)))
                                                  
  for (b <- books; a <- b.authors if a startsWith "Homero")
  yield b.title                                   //> res0: scala.collection.immutable.Set[String] = Set(asfjg, La odisea, La ilia
                                                  //| da)
  
  // Translation del for de arriba (lo que hace el compilador)
  books.flatMap(b => b.authors.withFilter(a => a startsWith "Homero").map(t => b.title))
                                                  //> res1: scala.collection.immutable.Set[String] = Set(asfjg, La odisea, La ilia
                                                  //| da)
  
  //for (b <- books if b.title indexOf "Homero" >= 0)
  //yield b.title
  
  var autores = for {
  	b1 <- books
  	b2 <- books
  	if b1 != b2
  	a1 <- b1.authors
  	a2 <- b2.authors
  	if a1 == a2
  } yield a1                                      //> autores  : scala.collection.immutable.Set[String] = Set(Homero)
  
  // Esto podemos hacer si trabajamos con List en lugar de Set
  //autores.distinct
}