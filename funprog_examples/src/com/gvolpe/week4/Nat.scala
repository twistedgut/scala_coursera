package com.gvolpe.week4

abstract class Nat {

  def isZero: Boolean
  def predecessor: Nat
  def sucessor: Nat = new Succ(this)
  def +(that: Nat): Nat
  def -(that: Nat): Nat

  def getNameIndex: Int

}

object Zero extends Nat {

  def isZero: Boolean = true
  def predecessor: Nat = throw new Error("Zero has no predecessor")
  def +(that: Nat): Nat = that
  def -(that: Nat): Nat = {
    if (that.isZero) Zero
    else throw new Error("Negative number")
  }

  override def toString = "Zero"

  def getNameIndex: Int = throw new Error("Zero has no index name")

}

class Succ(value: Nat) extends Nat {

  val numbers = List("One", "Two", "Tree", "Four", "Five", "Six", "Seven", "Eight", "Nine")

  def isZero: Boolean = false
  def predecessor: Nat = value
  def +(that: Nat): Nat = new Succ(value + that)
  def -(that: Nat): Nat = {
    if (that.isZero) this
    else value - that.predecessor
  }

  def getNameIndex: Int = {
    if (value.isZero) 1
    else 1 + value.getNameIndex
  }

  override def toString = {
    if (value.isZero) numbers(0)
    else if (value.getNameIndex < numbers.size) numbers(value.getNameIndex)
    else "Natural number not found"
  }

}