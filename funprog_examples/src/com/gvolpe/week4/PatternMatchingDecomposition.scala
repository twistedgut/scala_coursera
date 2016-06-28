package com.gvolpe.week4

object PatternMatchingDecomposition {

  def eval(e: PMExpr): Int = e match {
    case PMNumber(n) => n
    case PMSum(e1, e2) => eval(e1) + eval(e2)
  }

}

trait PMExpr {

  def eval: Int = this match {
    case PMNumber(n) => n
    case PMSum(e1, e2) => e1.eval + e2.eval
    case PMProd(e1, e2) => e1.eval * e2.eval
  }

  def show: String = this match {
    case PMNumber(n) => n.toString
    case PMSum(e1, e2) => e1.show + " + " + e2.show
    case PMProd(e1, e2) => e1.show + " * " + e2.show
  }

}

case class PMNumber(n: Int) extends PMExpr {

  //def eval: Int = n

}

case class PMSum(left: PMExpr, right: PMExpr) extends PMExpr {

  //def eval: Int = left.eval + right.eval

}

case class PMProd(left: PMExpr, right: PMExpr) extends PMExpr {

}