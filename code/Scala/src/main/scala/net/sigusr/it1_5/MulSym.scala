package net.sigusr.it1_5

trait MulSym[R] {
  def mul(r1: R, r2: R): R
}

object MulSymSamples {

  def tf2[R:ExpSym: MulSym]: R = {
    val e = implicitly[ExpSym[R]]
    val m = implicitly[MulSym[R]]
    m.mul(e.num(7), ExpSymSamples.tf1)
  }
}

object MulSymInt {
  implicit val mulSymInt: MulSym[Int] = (r1: Int, r2: Int) => r1 * r2
}

object MulSymString {
  implicit val mulSymString: MulSym[String] = (r1: String, r2: String) => s"($r1 * $r2)"
}

