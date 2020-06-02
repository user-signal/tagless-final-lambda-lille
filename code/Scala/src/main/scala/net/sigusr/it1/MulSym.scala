package net.sigusr.it1

/*

  Typeclass definition

  class MulSYM repr where
      mul :: repr -> repr -> repr

 */
trait MulSym[R] {
  def mul(r1: R, r2: R): R
}

/*
  Examples in the tagless final form
 */
object MulSymSamples {

  /*
      tfm1 = add (lit 7) (neg (mul (lit 2) (lit 1)))
   */
  def tfm1[R:ExpSym: MulSym]: R = {
    val e = implicitly[ExpSym[R]]
    val m = implicitly[MulSym[R]]
    e.add(e.lit(7), e.neg(m.mul(e.lit(2), e.lit(1))))
  }

  /*
      tfm2 = mul (lit 7) tf1
   */
  def tfm2[R:ExpSym: MulSym]: R = {
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
  implicit val mulSymInt: MulSym[Int] = (r1: Int, r2: Int) => r1 * r2
}

/*

  An instance definition for strings

  instance MulSYM String where
      mul e1 e2 = "(" ++ e1 ++ " * " ++ e2 ++ ")"

 */
object MulSymString {
  implicit val mulSymString: MulSym[String] = (r1: String, r2: String) => s"($r1 * $r2)"
}

/*

  An instance definition for the tree based serialization format

  instance MulSYM repr => MulSYM (Ctx -> repr) where
      mul e1 e2 Pos = mul (e1 Pos) (e2 Pos)
      mul e1 e2 Neg = mul (e1 Pos) (e2 Neg)

 */
object MulSymPushNeg {
  implicit def mulSymPushNeg[R](implicit m: MulSym[R]): MulSym[Ctx0 => R] = (r1: Ctx0 => R, r2: Ctx0 => R) => {
    case Pos => m.mul(r1(Pos), r2(Pos))
    case Neg => m.mul(r1(Pos), r2(Neg))
  }
}

