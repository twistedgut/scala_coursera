package com.gvolpe.week3.lists

trait GenericList[+T] {

  def isEmpty: Boolean
  def head: T
  def tail: GenericList[T]

  // No se porque no anda esto pero en el curso compila
  //def prepend[U >: T](elem: U): List[U] = new Cons(elem, this)

}

class Cons[T](val head: T, val tail: GenericList[T]) extends GenericList[T] {

  def isEmpty = false

  override def toString = "{" + head + "}" + tail.toString()

}

class Nil[T] extends GenericList[T] {

  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")

  override def toString = "{Nil}"

}

// Covariant, contravariant and non-variant

object CovariantNil extends GenericList[Nothing] {

  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("CovariantNil.head")
  def tail: Nothing = throw new NoSuchElementException("CovariantNil.tail")

  override def toString = "{CovariantNil}"

}

object test {
  // Esto deberia funcionar por List[Nothing] es un subtipo de List[String] porque
  // estamos indicando en GenericList[+T] con el +T que es una lista que acepta covariants
  // No me funciona a mi, pero en el curso se explica asi y compila...
  //val x: List[String] = CovariantNil
}
