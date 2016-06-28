object Sieve {
  def main(args: Array[String]): Unit = { println(sieve(from(2)).take(10).toList) }

  def from(n: Int): Stream[Int] = n #:: from(n + 1)

  def sieve(s: Stream[Int]): Stream[Int] = {
    s.head #:: sieve(s.tail.filter(_ % s.head != 0))
  }
}