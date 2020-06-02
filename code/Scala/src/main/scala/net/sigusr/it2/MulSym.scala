package net.sigusr.it2

/*

  Typeclass definition

  class MulSYM repr where
      mul :: repr -> repr -> repr

 */
trait MulSym[R[_]] {
  def mul(r1: R[Int], r2: R[Int]): R[Int]
}

/*
  Examples in the tagless final form
 */
object MulSymSamples {

  /*
      tfm1 = add (lit 7) (neg (mul (lit 2) (lit 1)))
   */
  def tfm1[R[_]:ExpSym: MulSym]: R[Int] = {
    val e = implicitly[ExpSym[R]]
    val m = implicitly[MulSym[R]]
    e.add(e.lit(7), e.neg(m.mul(e.lit(2), e.lit(1))))
  }

  /*
      tfm2 = mul (lit 7) tf1
   */
  def tfm2[R[_]:ExpSym: MulSym]: R[Int] = {
    val e = implicitly[ExpSym[R]]
    val m = implicitly[MulSym[R]]
    m.mul(e.lit(7), ExpSymSamples.tf1)
  }
}

/*

  An instance definition for integers

  instance MulSYM Int where
      mul e1 e2 = e1 * e2

 */
object MulSymInt {
  implicit val mulSymInt: MulSym[R] = new MulSym[R] {
    override def mul(r1: R[Int], r2: R[Int]): R[Int] = R(r1.unR * r2.unR)
  }
}

/*

  An instance definition for strings

  instance MulSYM String where
      mul e1 e2 = "(" ++ e1 ++ " * " ++ e2 ++ ")"

 */
object MulSymString {
  implicit val mulSymString: MulSym[S] = new MulSym[S] {
    override def mul(r1: S[Int], r2: S[Int]): S[Int] = S(s"(${r1.unS} * ${r2.unS})")
  }
}

