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
    def pascal(c: Int, r: Int): Int = {
      def loop (col: Int, row: Int): Int = {
        if (col == 0 || col == row) 1
        else loop(col-1, row-1) + loop(col, row-1)
      }
      loop(c, r)
    }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      def loop(counter: Int, c: List[Char]): Boolean = {
        if (counter < 0) false
        else if (c.isEmpty)
          if(counter == 0) true else false
        else

        {
          val countParenth =
            if (c.head == '(') counter + 1
            else if (c.head == ')') counter - 1
            else counter
          loop(countParenth, c.tail)
        }
      }
      loop(0, chars)
    }

  /**
   * Exercise 3
   */
  //def countChange(money: Int, coins: List[Int]): Int = {
  //  def loop(m: Int, c: List[Int]): Int = {
  //    if (m == 0) 1
  //    else if (m < 0) 0
  //    else if (c.isEmpty && m >= 1 ) 0
  //    else loop(m, c.tail) + loop(m - c.head, c)
  //  }
  //  loop(money, coins)
  //}

  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) 1
    else if (money < 0) 0
    else if (coins.isEmpty && money >= 1 ) 0
    else countChange(money, coins.tail) + countChange(money - coins.head, coins)
  }
}
