package net.sigusr

/*

  Typeclass definition

  class ExpSYM repr where
      lit :: Int -> repr
      neg :: repr -> repr
      add :: repr -> repr -> repr

 */
trait ExpSym[R] {
  def lit(i: Int): R
  def neg(r: R): R
  def add(r1: R, r2: R): R
}

/*
  Examples in the tagless final form
 */
object ExpSymSamples {

  /*
      tf1 = add (lit 8) (neg (add (lit 1) (lit 2)))
   */
  def tf1[R: ExpSym]: R = {
    val e = implicitly[ExpSym[R]]
    e.add(e.lit(8), e.neg(e.add(e.lit(1), e.lit(2))))
  }

  /*
      tf3 = (add tf1 (neg (neg tf1)))
   */
  def tf3[R](implicit e: ExpSym[R]): R = {
    e.add(tf1, e.neg(e.neg(tf1)))
  }

  /*
     First example in serialized tree form
   */
  val tf1_tree: Node = Node("Add", List(
    Node("Lit", List(Leaf("8"))),
    Node("Neg", List(Node("Add", List(Node("Lit", List(Leaf("1"))), Node("Lit", List(Leaf("2")))))))
  ))
}

/*

  An instance definition for integers

  instance ExpSYM Int where
      lit n = n
      neg e = - e
      add e1 e2 = e1 + e2

 */
object ExpSymInt {
  implicit val expSymInt: ExpSym[Int] = new ExpSym[Int] {
    def lit(i: Int): Int = i
    def neg(r: Int): Int = -r
    def add(r1: Int, r2: Int): Int = r1 + r2
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
  implicit val expSymString: ExpSym[String] = new ExpSym[String] {
    def lit(i: Int): String = i.toString
    def neg(r: String): String = s"(-$r)"
    def add(r1: String, r2: String): String = s"($r1 + $r2)"
  }
}

/*

  An instance definition for the tree based serialization format

  instance ExpSYM Tree where
      lit n = Node "Lit" [Leaf $ show n]
      neg e = Node "Neg" [e]
      add e1 e2 = Node "Add" [e1,e2]

 */
object ExpSymTree {
  implicit val expSymTree: ExpSym[Tree] = new ExpSym[Tree] {
    def lit(i: Int): Tree = Node("Lit", List(Leaf(i.toString)))
    def neg(r: Tree): Tree = Node("Neg", List(r))
    def add(r1: Tree, r2: Tree): Tree = Node("Add", List(r1, r2))
  }
}

/*

  Tagless transformer
  An instance definition to produce a interpreter from another

  instance ExpSYM repr => ExpSYM (Ctx -> repr) where
      lit n Pos = lit n
      lit n Neg = neg (lit n)
      neg e Pos = e Neg
      neg e Neg = e Pos
      add e1 e2 ctx = add (e1 ctx) (e2 ctx)

 */

object ExpSymPushNeg {
  implicit def expSymPushNeg[R](implicit e: ExpSym[R]): ExpSym[Ctx0 => R] = new ExpSym[Ctx0 => R] {
    def lit(i: Int): Ctx0 => R = {
      case Pos => e.lit(i)
      case Neg => e.neg(e.lit(i))
    }
    def neg(r: Ctx0 => R): Ctx0 => R = {
      case Pos => r(Neg)
      case Neg => r(Pos)
    }
    def add(r1: Ctx0 => R, r2: Ctx0 => R): Ctx0 => R = (ctx: Ctx0) => e.add(r1(ctx), r2(ctx))
  }
}

/*

  Tagless transformer
  An instance definition to produce a interpreter from another

  instance ExpSYM repr => ExpSYM (Ctx repr -> repr) where
      lit n NonLCA   = lit n
      lit n (LCA e)  = add (lit n) e
      neg e NonLCA   = neg (e NonLCA)
      neg e (LCA e3) = add (neg (e NonLCA)) e3 -- assume only lits are negated
      add e1 e2 ctx  = e1 (LCA (e2 ctx))

 */

object ExpSymFlata {
  implicit def expSymFlata[R](implicit e: ExpSym[R]): ExpSym[Ctx1[R] => R] = new ExpSym[Ctx1[R] => R] {
    def lit(i: Int): Ctx1[R] => R = {
      case NonLca => e.lit(i)
      case Lca(r) => e.add(e.lit(i), r)
    }
    def neg(r: Ctx1[R] => R): Ctx1[R] => R = {
      case NonLca => e.neg(r(NonLca))
      case Lca(r1) => e.add(e.neg(r(NonLca)), r1) // assume only lits are negated
    }
    def add(r1: Ctx1[R] => R, r2: Ctx1[R] => R): Ctx1[R] => R = (ctx: Ctx1[R]) => r1(Lca(r2(ctx)))
  }
}
