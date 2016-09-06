package reductions

import scala.annotation._
import org.scalameter._
import common._

object ParallelParenthesesBalancingRunner {

  @volatile var seqResult = false

  @volatile var parResult = false

  val standardConfig = config(
    Key.exec.minWarmupRuns -> 40,
    Key.exec.maxWarmupRuns -> 80,
    Key.exec.benchRuns -> 120,
    Key.verbose -> true
  ) withWarmer(new Warmer.Default)

  def main(args: Array[String]): Unit = {
    val length = 100000000
    val chars = new Array[Char](length)
    val threshold = 10000
    val seqtime = standardConfig measure {
      seqResult = ParallelParenthesesBalancing.balance(chars)
    }
    println(s"sequential result = $seqResult")
    println(s"sequential balancing time: $seqtime ms")

    val fjtime = standardConfig measure {
      parResult = ParallelParenthesesBalancing.parBalance(chars, threshold)
    }
    println(s"parallel result = $parResult")
    println(s"parallel balancing time: $fjtime ms")
    println(s"speedup: ${seqtime / fjtime}")
  }
}

object ParallelParenthesesBalancing {

  /** Returns `true` iff the parentheses in the input `chars` are balanced.
   */

  def balance(chars: Array[Char]): Boolean = chars.foldLeft(0){
    case (0, ')') => return false
    case (c, ')') => c - 1
    case (c, '(') => c + 1
    case (c, _  ) => c
  } == 0

  /** Returns `true` iff the parentheses in the input `chars` are balanced.
   */
  def parBalance(chars: Array[Char], threshold: Int): Boolean = {

    def traverse(idx: Int, until: Int, arg1: Int, arg2: Int): (Int, Int) = {
      if (idx < until) {
        chars(idx) match {
          case '(' => traverse(idx + 1, until, arg1 + 1, arg2)
          case ')' =>
            if (arg1 > 0) traverse(idx + 1, until, arg1 - 1, arg2)
            else traverse(idx + 1, until, arg1, arg2 + 1)
          case _ => traverse(idx + 1, until, arg1, arg2)
        }
      } else (arg1, arg2)
    }


//    if (until - from < threshold) {
//      Leaf(from, until, reduceSequential(from, until))
//    } else {
//      val mid = (from + until) / 2
//      val (leftTree, rightTree) = parallel(
//        reduce(from, mid),
//        reduce(mid, until)
//      )
//      Node(leftTree, rightTree)
//    }
//  }

    def reduce(from: Int, until: Int): (Int, Int) = {
      val len = until - from
      if ( until - from  < threshold ) {
        traverse(from, until , 0, 0)
      }
      else {
        val mid = (from + until) / 2
        val ((x1, x2), (y1, y2)) = parallel(reduce(from, mid), reduce(mid, until))
        // look at values returned and combine according to values of x1 and y2
        if ( x1 > y2 ) {
          ( x1 - y2 + y1 ) -> x2
        } else  {
          y1 -> ( y2 - x1 + x2 )
        }
      }
    }

    reduce(0, chars.length) == (0, 0)
  }

  // For those who want more:
  // Prove that your reduction operator is associative!

}
