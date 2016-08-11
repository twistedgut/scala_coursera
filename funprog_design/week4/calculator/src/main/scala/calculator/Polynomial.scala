package calculator

object Polynomial {
  def computeDelta(a: Signal[Double], b: Signal[Double], c: Signal[Double]): Signal[Double] =
    Signal { b() * b() - 4 * (a() * c()) }

  def computeSolutions(a: Signal[Double], b: Signal[Double], c: Signal[Double], delta: Signal[Double]): Signal[Set[Double]] = {
    Signal{
      val bmo = b() * -1
      val sqd = math.sqrt(delta())
      val tta = 2 * a()
      delta() match {
        case z if z <  0.0 => Set()
        case z if z == 0.0 => Set(bmo / tta)
        case z if z >  0.0 => Set((bmo + sqd) / tta, (bmo - sqd) / tta)
      }
    }
  }
}
