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
  implicit val expSymI: ExpSym[I] = new ExpSym[I] {
    def num(i: Int): I[Int] = i
    def neg(r: I[Int]): I[Int] = -r
    def add(r1: I[Int], r2: I[Int]): I[Int] = r1 + r2
  }
}

object ExpSymString {
  implicit val expSymS: ExpSym[S] = new ExpSym[S] {
    def num(i: Int): S[Int] = i.toString
    def neg(r: S[Int]): S[Int] = s"(-${r})"
    def add(r1: S[Int], r2: S[Int]): S[Int] = s"(${r1} + ${r2})"
  }
}
