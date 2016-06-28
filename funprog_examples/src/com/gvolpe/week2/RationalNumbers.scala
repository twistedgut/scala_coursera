package com.gvolpe.week2

object RationalNumbers {

  def main(args: Array[String]) {
    println(toString(addRational(new Rational(1, 2), new Rational(2, 3))))

    println(new Rational(1, 2).+(new Rational(2, 3)))

    val x = new Rational(1, 3)
    val y = new Rational(5, 7)
    val z = new Rational(3, 2)

    println(x - y - z)
    println(y + y)
    println(x < y)
    println(x.max(y))
    
    println(y + y)
  }

  def addRational(r: Rational, s: Rational): Rational = {
    new Rational(r.numer * s.denom + s.numer * r.denom, r.denom * s.denom)
  }

  def toString(r: Rational) = r.numer + "/" + r.denom

}

class Rational(x: Int, y: Int) {

  require(y != 0, "Denominator must be nonzero.")

  // Another constructor
  def this(x: Int) = this(x, 1)

  // Great Common Divisor
  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  //  val numer = x / gcd(x, y)
  //  val denom = y / gcd(x, y)
  val numer = x
  val denom = y

  //def less(that: Rational) = numer * that.denom < that.numer * denom
  def < (that: Rational) = numer * that.denom < that.numer * denom

  def max(that: Rational) = if (this < that) that else this

//  def add(that: Rational): Rational = {
  def + (that: Rational): Rational = {
    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  }

  def unary_- : Rational = new Rational(-numer, denom)

  def -(that: Rational): Rational = this + -that

  override def toString = {
    def g = gcd(numer, denom)
    numer / g + "/" + denom / g
  }

}