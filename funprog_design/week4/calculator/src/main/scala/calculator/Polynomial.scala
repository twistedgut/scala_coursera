package calculator

object Polynomial {
  def computeDelta(a: Signal[Double], b: Signal[Double], c: Signal[Double]): Signal[Double] = {
    Signal {
      (b() * b()) - 4 * (a() * c())
    }
  }

  def computeSolutions(a: Signal[Double], b: Signal[Double],
                       c: Signal[Double], delta: Signal[Double]): Signal[Set[Double]] = {
    val bmo = Signal(b() * -1)
    val sqd = Signal(math.sqrt(delta()))
    val tta = Signal(2 * a())
    Signal{
      delta() match {
        case z if z <  0.0 => Set()
        case z if z >  0.0 => Set((bmo() + sqd()) / tta(), (bmo() - sqd()) / tta())
      }
    }
  }
}