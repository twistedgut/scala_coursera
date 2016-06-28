package com.gvolpe.week1

import scala.annotation.tailrec

object Main {

  def main(args: Array[String]) {
    println("Pascal: " + pascal(4, 7))
    println("Balanced: " + balance("(gabriel ))(m volpe".toList))
    println("Count Change: " + countChange(4, List(1, 2)))
    println("Sum: " + sum(x => x * x, 3, 5))
  }

  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  def balance(chars: List[Char]): Boolean = {
    @tailrec
    def checkBalance(chars: List[Char], stack: List[Char] = List()): Boolean = {
      if (chars.isEmpty) stack.isEmpty;
      else if (chars.head == '(') checkBalance(chars.tail, chars.head :: stack)
      else if (chars.head == ')') !stack.isEmpty && stack.head != chars.head && checkBalance(chars.tail, stack.dropRight(1))
      else checkBalance(chars.tail, stack)
    }
    checkBalance(chars);
  }

  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) 1
    else if (money < 0 || coins.isEmpty) 0
    else countChange(money, coins.tail) + countChange(money - coins.head, coins)
  }

  def sum(f: Int => Int, a: Int, b: Int): Int = {
    @tailrec
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a + 1, f(a) + acc)
    }
    loop(a, 0)
  }

}