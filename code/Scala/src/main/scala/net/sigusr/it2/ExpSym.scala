package net.sigusr.it2

trait ExpSym[R[_]] {
  def num(i: Int): R[Int]
  def neg(r: R[Int]): R[Int]
  def add(r1: R[Int], r2: R[Int]): R[Int]
}

object ExpSymSamples {

  def tf1[R[_]: ExpSym]: R[Int] = {
    val e = implicitly[ExpSym[R]]
    e.add(e.num(8), e.neg(e.add(e.num(1), e.num(2))))
  }
}

object ExpSymInt {
  implicit val expSymInt: ExpSym[R] = new ExpSym[R] {
    def num(i: Int): R[Int] = R(i)
    def neg(r: R[Int]): R[Int] = R(-r.unR)
    def add(r1: R[Int], r2: R[Int]): R[Int] = R(r1.unR + r2.unR)
  }
}

object ExpSymString {
  implicit val expSymString: ExpSym[S] = new ExpSym[S] {
    def num(i: Int): S[Int] = S(i.toString)
    def neg(r: S[Int]): S[Int] = S(s"(-${r.unS})")
    def add(r1: S[Int], r2: S[Int]): S[Int] = S(s"(${r1.unS} + ${r2.unS})")
  }
}
