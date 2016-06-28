object nQueens {
  def main(args: Array[String]) = show(nqueens(4))

  def nqueens(n: Int): Set[List[Int]] = {
    def place(k: Int): Set[List[Int]] =
      if (k == 0) Set(List())
      else
        for {
          queens <- place(k - 1)
          col <- 0 until n
          if isSafe(col, queens)
        } yield queens :+ col

    def isSafe(col: Int, queens: List[Int]): Boolean = {
      val row = queens.length
      val queensWithRow = (0 until row) zip queens
      queensWithRow forall {
        case (r, c) => col != c && math.abs(col - c) != row - r
      }
    }
    place(n)
  }

  def show(solutions: Set[List[Int]]): Unit = {
    println((for (solution <- solutions) yield {
      (for { col <- solution } yield {
        Vector.fill(solution.length)("*").updated(col, "X").mkString(" ")
      }).mkString("\n")
    }).mkString("\n\n"))
  }
}