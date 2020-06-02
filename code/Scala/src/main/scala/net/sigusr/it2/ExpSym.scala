package net.sigusr.it2

/*

  Typeclass definition

  class ExpSYM repr where
      lit :: Int -> repr
      neg :: repr -> repr
      add :: repr -> repr -> repr

 */

trait ExpSym[R[_]] {
  def lit(i: Int): R[Int]
  def neg(r: R[Int]): R[Int]
  def add(r1: R[Int], r2: R[Int]): R[Int]
}

/*
  Examples in the tagless final form
 */
object ExpSymSamples {

  /*
      tf1 = add (lit 8) (neg (add (lit 1) (lit 2)))
   */
  def tf1[R[_]: ExpSym]: R[Int] = {
    val e = implicitly[ExpSym[R]]
    e.add(e.lit(8), e.neg(e.add(e.lit(1), e.lit(2))))
  }

  /*
      tf3 = (add tf1 (neg (neg tf1)))
   */
  def tf3[R[_]](implicit e: ExpSym[R]): R[Int] = {
    e.add(tf1, e.neg(e.neg(tf1)))
  }
}

/*

  An instance definition for integers

  instance ExpSYM Int where
      lit n = n
      neg e = - e
      add e1 e2 = e1 + e2

 */
object ExpSymInt {
  implicit val expSymInt: ExpSym[R] = new ExpSym[R] {
    def lit(i: Int): R[Int] = R(i)
    def neg(r: R[Int]): R[Int] = R(-r.unR)
    def add(r1: R[Int], r2: R[Int]): R[Int] = R(r1.unR + r2.unR)
  }
}

/*

  An instance definition for strings

  instance ExpSYM String where
      lit n = show n
      neg e = "(-" ++ e ++ ")"
      add e1 e2 = "(" ++ e1 ++ " + " ++ e2 ++ ")"

*/
object ExpSymString {
  implicit val expSymString: ExpSym[S] = new ExpSym[S] {
    def lit(i: Int): S[Int] = S(i.toString)
    def neg(r: S[Int]): S[Int] = S(s"(-${r.unS})")
    def add(r1: S[Int], r2: S[Int]): S[Int] = S(s"(${r1.unS} + ${r2.unS})")
  }
}
