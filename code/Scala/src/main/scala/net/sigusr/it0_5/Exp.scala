package net.sigusr.it0_5

trait U {
  def asI: Int = this match { case UI(i) => i }
  def asB: Boolean = this match { case UB(b) => b }
}
case class UB(b: Boolean) extends U
case class UI(i: Int) extends U

sealed trait Exp
case class Num(i: Int) extends Exp
case class Neg(e: Exp) extends Exp
case class Add(e1: Exp, e2: Exp) extends Exp
case class Mul(e1: Exp, e2: Exp) extends Exp
case class Bool(b: Boolean) extends Exp
case class Or(e1: Exp, e2: Exp) extends Exp
case class And(e1: Exp, e2: Exp) extends Exp
case class Leq(e1: Exp, e2: Exp) extends Exp

object Exp {
  def eval: Exp => U = {
    case Num(i) => UI(i)
    case Neg(e) => UI(-eval(e).asI)
    case Add(e1, e2) => UI(eval(e1).asI + eval(e2).asI)
    case Mul(e1, e2) => UI(eval(e1).asI * eval(e2).asI)
    case Bool(b) => UB(b)
    case Or(e1, e2) => UB(eval(e1).asB || eval(e2).asB)
    case And(e1, e2) => UB(eval(e1).asB && eval(e2).asB)
    case Leq(e1, e2) => UB(eval(e1).asI <= eval(e2).asI)
  }
}

object ExpSamples {
  val ti1: Add = Add(Num(8), Neg(Add(Num(1), (Num(2)))))
  val ti2: Mul = Mul(Num(7), ti1)
  val ti3: Or = Or(Leq(ti2, ti1), Leq(Num(0), ti2))
  val tie: Neg = Neg(Bool(false))
}


