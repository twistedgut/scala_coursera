package com.gvolpe.week4

object Decomposition {

  def eval(e: Expr): Int = {
    if (e.isInstanceOf[Number])
      e.asInstanceOf[Number].numValue
    else if (e.isInstanceOf[Sum])
      eval(e.asInstanceOf[Sum].leftOp) + eval(e.asInstanceOf[Sum].rightOp)
    else throw new Error("Unknown expression " + e)
  }

}

trait Expr {

  def numValue: Int
  def leftOp: Expr
  def rightOp: Expr

}

class Number(n: Int) extends Expr {

  def numValue: Int = n
  def leftOp: Expr = throw new Error("It's a Number")
  def rightOp: Expr = throw new Error("It's a Number")

}

class Sum(left: Expr, right: Expr) extends Expr {

  def numValue: Int = throw new Error("It's a Sum")
  def leftOp: Expr = left
  def rightOp: Expr = right

}

/* Version 2 */

trait Expr2 {

  def eval: Int

}

class Number2(n: Int) extends Expr2 {

  def eval: Int = n

}

class Sum2(left: Expr2, right: Expr2) extends Expr2 {

  def eval: Int = left.eval + right.eval

}