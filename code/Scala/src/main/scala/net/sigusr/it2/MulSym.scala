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
  implicit val mulSymInt: MulSym[R] = (r1: R[Int], r2: R[Int]) => R(r1.unR * r2.unR)
}

object MulSymString {
  implicit val mulSymString: MulSym[S] = (r1: S[Int], r2: S[Int]) => S(s"(${r1.unS} * ${r2.unS})")
}

