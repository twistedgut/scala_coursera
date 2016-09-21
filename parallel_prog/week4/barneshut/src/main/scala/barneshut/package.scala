import common._
import barneshut.conctrees._

package object barneshut {

  class Boundaries {
    var minX = Float.MaxValue

    var minY = Float.MaxValue

    var maxX = Float.MinValue

    var maxY = Float.MinValue

    def width = maxX - minX

    def height = maxY - minY

    def size = math.max(width, height)

    def centerX = minX + width / 2

    def centerY = minY + height / 2

    override def toString = s"Boundaries($minX, $minY, $maxX, $maxY)"
  }

  sealed abstract class Quad {
    //massX and massY represent the center of mass of the bodies in the respective cell
    def massX: Float

    def massY: Float

    //mass is the total mass of bodies in that cell
    def mass: Float

    //centerX and centerY are the coordinates of the center of the cell
    def centerX: Float

    def centerY: Float

    //size is the length of the side of the cell
    def size: Float

    //total is the total number of bodies in the cell
    def total: Int

    //insert creates a new quadtree which additionally contains the body b, and covers the same area in space as the original quadtree
    def insert(b: Body): Quad
  }

//  mass = m_B + m_C + m_D + m_E
//  massX = (m_B * x_B + m_C * x_C + m_D * x_D + m_E * x_E) / mass
//  massY = (m_B * y_B + m_C * y_C + m_D * y_D + m_E * y_E) / mass

  case class Empty(centerX: Float, centerY: Float, size: Float) extends Quad {
    def massX: Float = centerX
    def massY: Float = centerY
    def mass: Float = 0f
    def total: Int = 0
    def insert(b: Body): Quad = Leaf(centerX, centerY, size, Seq(b))
  }

  case class Fork(
    nw: Quad, ne: Quad, sw: Quad, se: Quad
  ) extends Quad {
    val quads          = List(nw, ne, sw, se)
    val centerX: Float = nw.centerX + nw.size / 2
    val centerY: Float = nw.centerY + nw.size / 2
    val size: Float    = nw.size * 2
    val mass: Float    = quads.foldLeft(0f){_ + _.mass}
    val massX: Float   = mass match {
      case 0 => centerX
      case _ => quads.foldLeft(0f) { (acc, q) => acc + (q.massX * q.mass)} / mass
    }
    val massY: Float   = mass match {
      case 0 => centerY
      case _ => quads.foldLeft(0f) { (acc, q) => acc + (q.massY * q.mass) } / mass
    }
    val total: Int     = quads.foldLeft(0){_ + _.total}

    def insert(b: Body): Fork = {
      val ltCenterX = b.x < centerX
      val ltCenterY = b.y < centerY

      (ltCenterX, ltCenterY) match {
        case (true, true)   => Fork(nw.insert(b), ne, sw, se)
        case (true, false)  => Fork(nw, ne, sw.insert(b), se)
        case (false, true)  => Fork(nw, ne.insert(b), sw, se)
        case (false, false) => Fork(nw, ne, sw, se.insert(b))
      }
    }
  }

  case class Leaf(centerX: Float, centerY: Float, size: Float, bodies: Seq[Body])
  extends Quad {
    val mass: Float  = bodies.foldLeft(0f){_ + _.mass}
    val massX: Float = bodies.foldLeft(0f){(acc, q) => acc + (q.x * q.mass)} / mass
    val massY: Float = bodies.foldLeft(0f){(acc, q) => acc + (q.y * q.mass)} / mass
    val total: Int   = bodies.size

    // if size of a Leaf > minimumSize, create Fork with empty children and add all bodies to Fork
    // otherwise create new Leaf with all bodies and b inserted into that Leaf .

    def insert(b: Body): Quad =
      if (size > minimumSize)
        (bodies :+ b).foldLeft(newFork)((acc, body) => acc.insert(body))
      else
        Leaf(centerX, centerY, size, bodies :+ b)

    val newFork = Fork(
      Empty(centerX - size/4, centerY - size/4, size/2),
      Empty(centerX + size/4, centerY - size/4, size/2),
      Empty(centerX - size/4, centerY + size/4, size/2),
      Empty(centerX + size/4, centerY + size/4, size/2)
    )
  }

  def minimumSize = 0.00001f

  def gee: Float = 100.0f

  def delta: Float = 0.01f

  def theta = 0.5f

  def eliminationThreshold = 0.5f

  def force(m1: Float, m2: Float, dist: Float): Float = gee * m1 * m2 / (dist * dist)

  def distance(x0: Float, y0: Float, x1: Float, y1: Float): Float = {
    math.sqrt((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0)).toFloat
  }

  class Body(val mass: Float, val x: Float, val y: Float, val xspeed: Float, val yspeed: Float) {

    def updated(quad: Quad): Body = {
      var netforcex = 0.0f
      var netforcey = 0.0f

      def addForce(thatMass: Float, thatMassX: Float, thatMassY: Float): Unit = {
        val dist = distance(thatMassX, thatMassY, x, y)
        /* If the distance is smaller than 1f, we enter the realm of close
         * body interactions. Since we do not model them in this simplistic
         * implementation, bodies at extreme proximities get a huge acceleration,
         * and are catapulted from each other's gravitational pull at extreme
         * velocities (something like this:
         * http://en.wikipedia.org/wiki/Interplanetary_spaceflight#Gravitational_slingshot).
         * To decrease the effect of this gravitational slingshot, as a very
         * simple approximation, we ignore gravity at extreme proximities.
         */
        if (dist > 1f) {
          val dforce = force(mass, thatMass, dist)
          val xn = (thatMassX - x) / dist
          val yn = (thatMassY - y) / dist
          val dforcex = dforce * xn
          val dforcey = dforce * yn
          netforcex += dforcex
          netforcey += dforcey
        }
      }

      def traverse(quad: Quad): Unit = (quad: Quad) match {
        case Empty(_, _, _) =>
          // no force
        case Leaf(_, _, _, bodies) =>
          // add force contribution of each body by calling addForce
          bodies.foreach{ b => addForce(b.mass, b.x, b.y)}
        case Fork(nw, ne, sw, se) =>
          // see if node is far enough from the body,
          // or recursion is needed
          // quad.size / dist < theta
          // def distance(x0: Float, y0: Float, x1: Float, y1: Float):
          if ( distance(x, y, quad.massX, quad.massY) < theta ) {
            addForce(quad.mass, quad.massX, quad.massY)
          } else {
            traverse(nw)
            traverse(ne)
            traverse(sw)
            traverse(se)
          }
      }

      traverse(quad)

      val nx = x + xspeed * delta
      val ny = y + yspeed * delta
      val nxspeed = xspeed + netforcex / mass * delta
      val nyspeed = yspeed + netforcey / mass * delta

      new Body(mass, nx, ny, nxspeed, nyspeed)
    }

  }

  val SECTOR_PRECISION = 8

  class SectorMatrix(val boundaries: Boundaries, val sectorPrecision: Int) {
    val sectorSize = boundaries.size / sectorPrecision
    val matrix = new Array[ConcBuffer[Body]](sectorPrecision * sectorPrecision)
    for (i <- matrix.indices) matrix(i) = new ConcBuffer

    def +=(b: Body): SectorMatrix = {

      ???
      this
    }

    def apply(x: Int, y: Int) = matrix(y * sectorPrecision + x)

    def combine(that: SectorMatrix): SectorMatrix = {
      ???
    }

    def toQuad(parallelism: Int): Quad = {
      def BALANCING_FACTOR = 4
      def quad(x: Int, y: Int, span: Int, achievedParallelism: Int): Quad = {
        if (span == 1) {
          val sectorSize = boundaries.size / sectorPrecision
          val centerX = boundaries.minX + x * sectorSize + sectorSize / 2
          val centerY = boundaries.minY + y * sectorSize + sectorSize / 2
          var emptyQuad: Quad = Empty(centerX, centerY, sectorSize)
          val sectorBodies = this(x, y)
          sectorBodies.foldLeft(emptyQuad)(_ insert _)
        } else {
          val nspan = span / 2
          val nAchievedParallelism = achievedParallelism * 4
          val (nw, ne, sw, se) =
            if (parallelism > 1 && achievedParallelism < parallelism * BALANCING_FACTOR) parallel(
              quad(x, y, nspan, nAchievedParallelism),
              quad(x + nspan, y, nspan, nAchievedParallelism),
              quad(x, y + nspan, nspan, nAchievedParallelism),
              quad(x + nspan, y + nspan, nspan, nAchievedParallelism)
            ) else (
              quad(x, y, nspan, nAchievedParallelism),
              quad(x + nspan, y, nspan, nAchievedParallelism),
              quad(x, y + nspan, nspan, nAchievedParallelism),
              quad(x + nspan, y + nspan, nspan, nAchievedParallelism)
            )
          Fork(nw, ne, sw, se)
        }
      }

      quad(0, 0, sectorPrecision, 1)
    }

    override def toString = s"SectorMatrix(#bodies: ${matrix.map(_.size).sum})"
  }

  class TimeStatistics {
    private val timeMap = collection.mutable.Map[String, (Double, Int)]()

    def clear() = timeMap.clear()

    def timed[T](title: String)(body: =>T): T = {
      var res: T = null.asInstanceOf[T]
      val totalTime = /*measure*/ {
        val startTime = System.currentTimeMillis()
        res = body
        (System.currentTimeMillis() - startTime)
      }

      timeMap.get(title) match {
        case Some((total, num)) => timeMap(title) = (total + totalTime, num + 1)
        case None => timeMap(title) = (0.0, 0)
      }

      println(s"$title: ${totalTime} ms; avg: ${timeMap(title)._1 / timeMap(title)._2}")
      res
    }

    override def toString = {
      timeMap map {
        case (k, (total, num)) => k + ": " + (total / num * 100).toInt / 100.0 + " ms"
      } mkString("\n")
    }
  }
}
