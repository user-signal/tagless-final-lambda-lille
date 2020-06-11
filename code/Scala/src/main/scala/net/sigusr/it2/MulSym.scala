package net.sigusr.it2

trait MulSym[R[_]] {
  def mul(r1: R[Int], r2: R[Int]): R[Int]
}

object MulSymSamples {

  def tf2[R[_]:ExpSym: MulSym]: R[Int] = {
    val e = implicitly[ExpSym[R]]
    val m = implicitly[MulSym[R]]
    m.mul(e.num(7), ExpSymSamples.tf1)
  }
}

object MulSymInt {
  implicit val mulSymI: MulSym[I] = (r1: I[Int], r2: I[Int]) => r1 * r2
}

object MulSymString {
  implicit val mulSymS: MulSym[S] = (r1: S[Int], r2: S[Int]) => s"(${r1} × ${r2})"
}

