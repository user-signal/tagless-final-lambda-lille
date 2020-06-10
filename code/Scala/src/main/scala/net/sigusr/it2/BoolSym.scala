package net.sigusr.it2

trait BoolSym[R[_]] {
  def bool(b: Boolean): R[Boolean]
  def leq(r1: R[Int], r2: R[Int]): R[Boolean]
  def or(r1: R[Boolean], r2: R[Boolean]): R[Boolean]
  def and(r1: R[Boolean], r2: R[Boolean]): R[Boolean]
}

object BoolSymSamples {

  def tf3[R[_]:ExpSym: MulSym: BoolSym]: R[Boolean] = {
    val e = implicitly[ExpSym[R]]
    val b = implicitly[BoolSym[R]]
    b.or(b.leq(ExpSymSamples.tf1, MulSymSamples.tf2), b.leq(e.num(0), MulSymSamples.tf2))
  }
}

object BoolSymI {
  implicit val boolSymI: BoolSym[I] = new BoolSym[I] {
    def bool(b: Boolean): I[Boolean] = b
    def leq(r1: I[Int], r2: I[Int]): I[Boolean] = r1 <= r2
    def or(r1: I[Boolean], r2: I[Boolean]): I[Boolean] = r1 || r2
    def and(r1: I[Boolean], r2: I[Boolean]): I[Boolean] = r1 && r2
  }
}

object BoolSymS {
  implicit val boolSymS: BoolSym[S] = new BoolSym[S] {
    override def bool(b: Boolean): S[Boolean] = b.toString
    override def leq(r1: S[Int], r2: S[Int]): S[Boolean] = s"(${r1} ≦ ${r2})"
    override def or(r1: S[Boolean], r2: S[Boolean]): S[Boolean] = s"(${r1} ∨ ${r2})"
    override def and(r1: S[Boolean], r2: S[Boolean]): S[Boolean] = s"(${r1} ∧ ${r2})"
  }
}
