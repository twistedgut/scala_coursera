package com.gvolpe.week2

object Examples {

  def main(args: Array[String]) {
    println("Suma de enteros: " + sumInts(2, 4))
    println("Suma de cuadrados: " + sumSquares(2, 4))
    println("Suma de cubos: " + sumCubes(2, 4))
    println("Suma de factorial: " + sumFactorial(2, 7))
    println("Factorial: " + factorial(5))
    println("Suma de productos: " + product(fact)(2, 5))
  }

  def sumInts(a: Int, b: Int): Int = sum(x => x, a, b)

  def sumSquares(a: Int, b: Int): Int = sum(x => x * x, a, b)

  def sumCubes(a: Int, b: Int): Int = sum(cube, a, b)
  def cube(x: Int): Int = x * x * x

  def sumFactorial(a: Int, b: Int): Int = sum(fact, a, b)
  def fact(x: Int): Int = if (x == 0) 1 else fact(x - 1)

  def sum(f: Int => Int, a: Int, b: Int): Int = {
    if (a > b) 0
    else f(a) + sum(f, a + 1, b)
  }

  def product(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 1
    else f(a) * product(f)(a + 1, b)
  }

  //def factorial(n: Int) = product(x => x)(1, n)
  def factorial(n: Int) = product2(x => x)(1, n)

  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int = {
    if (a > b) zero
    else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))
  }

  def product2(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x * y, 1)(a, b)

}