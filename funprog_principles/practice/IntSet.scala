abstract class IntSet {
  def contains(x: Int): Boolean
  def include(x: Int): IntSet
  def union(other: IntSet): IntSet
}

object Empty extends IntSet {
  override def toString = "."
  def contains(x: Int): Boolean = false
  def include(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
  def union(other: IntSet): IntSet = other
}

class NonEmpty(element: Int, left: IntSet, right: IntSet) extends IntSet {
  override def toString = "{" + left + element + right + "}"
  def contains(x: Int): Boolean =
    if (x < element) left contains x
    else if (x > element) right contains x
    else true
  def include(x: Int): IntSet =
    if (x < element) new NonEmpty(element, left include x, right);
    else if (x > element) new NonEmpty(element, left, right include x);
    else this
  def union(other: IntSet): IntSet = ((left union right) union other) include element
}