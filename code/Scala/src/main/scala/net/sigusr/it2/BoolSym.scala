package net.sigusr.it2

trait BoolSym[R[_]] {
  def bool(b: Boolean): R[Boolean]
  def leq(r1: R[Int], r2: R[Int]): R[Boolean]
  def or(r1: R[Boolean], r2: R[Boolean]): R[Boolean]
  def and(r1: R[Boolean], r2: R[Boolean]): R[Boolean]
}

object BoolSymSamples {

  def tfb1[R[_]:ExpSym: MulSym: BoolSym]: R[Boolean] = {
    val e = implicitly[ExpSym[R]]
    val b = implicitly[BoolSym[R]]
    b.or(b.leq(MulSymSamples.tfm2, ExpSymSamples.tf1), b.leq(e.lit(0), MulSymSamples.tfm2))
  }
}

object BoolSymInt {
  implicit val boolSymInt: BoolSym[R] = new BoolSym[R] {
    def bool(b: Boolean): R[Boolean] = R(b)
    def leq(r1: R[Int], r2: R[Int]): R[Boolean] = R(r1.unR <= r2.unR)
    def or(r1: R[Boolean], r2: R[Boolean]): R[Boolean] = R(r1.unR || r2.unR)
    def and(r1: R[Boolean], r2: R[Boolean]): R[Boolean] = R(r1.unR && r2.unR)
  }
}
