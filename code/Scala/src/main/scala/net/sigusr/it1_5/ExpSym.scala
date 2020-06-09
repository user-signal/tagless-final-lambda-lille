package net.sigusr.it1_5

trait ExpSym[R] {
  def num(i: Int): R
  def neg(r: R): R
  def add(r1: R, r2: R): R
}

object ExpSymSamples {

  def tf1[R: ExpSym]: R = {
    val e = implicitly[ExpSym[R]]
    e.add(e.num(8), e.neg(e.add(e.num(1), e.num(2))))
  }
}

object ExpSymInt {
  implicit val expSymInt: ExpSym[Int] = new ExpSym[Int] {
    def num(i: Int): Int = i
    def neg(r: Int): Int = -r
    def add(r1: Int, r2: Int): Int = r1 + r2
  }
}

object ExpSymString {
  implicit val expSymString: ExpSym[String] = new ExpSym[String] {
    def num(i: Int): String = i.toString
    def neg(r: String): String = s"(-$r)"
    def add(r1: String, r2: String): String = s"($r1 + $r2)"
  }
}
