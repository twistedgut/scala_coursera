package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  // from assignment description:
  lazy val genMap: Gen[Map[Int,Int]] = for {
    k <- arbitrary[Int]
    v <- arbitrary[Int]
    m <- oneOf(const(Map.empty[Int,Int]), genMap)
  } yield m.updated(k, v)

  lazy val genHeap: Gen[H] = for {
    k <- arbitrary[A]
    m <- oneOf(genHeap, const(empty))
  } yield insert(k, m)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  property("gen1") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("min1") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
  }

  property("min of two parameters") = forAll { (a1: A, a2: A) =>
    val h = insert(a1, insert(a2, empty))
    findMin(h) == math.min(a1, a2)
  }

  property("get min from meld") = forAll { (a1:A, a2: A) =>
    val k = insert(a1, empty)
    val l = insert(a2, empty)
    val merged = meld(k, l)

    findMin(merged) == math.min(a1, a2)
  }

//  property("findMin of melded heap yields the min of the mons of heaps") = forAll { (h1: H, h2: H) =>
//    findMin(meld(h1, h2)) == math.min(findMin(h1), findMin(h2))
//  }

  property("heap with min deleted should equal heap constructed without min") = forAll { (a: A) =>
    //do this to account for test using max Int value
    val c = if (a == Int.MaxValue) a + 1 else a
    val h1 = insert(c+2, insert(c, insert(c+4, empty)))
    val h2 = deleteMin(h1)
    h2 == insert(c+2, insert(c+4, empty))
  }

}
