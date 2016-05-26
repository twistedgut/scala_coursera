package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = ???
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = ???
  
  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def loop(m: Int, c: List[Int]): Int = {
      if (m == 0) 1
      else if (m < 0) 0
      else if (c.isEmpty && m >= 1 ) 0
      else loop(m, c.tail) + loop(m - c.head, c)
    }
    loop(money, coins)
  }
}
