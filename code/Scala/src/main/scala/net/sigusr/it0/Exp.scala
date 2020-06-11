package net.sigusr.it0

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
//  This doen't compile, of course !!!
//  def eval = {
//    case Num(i) => i
//    case Neg(e) => - eval(e)
//    case Add(e1, e2) => eval(e1) + eval(e2)
//    case Mul(e1, e2) => eval(e1) * eval(e2)
//    case Bool(b) => b
//    case Or(e1, e2) => eval(e1) || eval(e2)
//    case And(e1, e2) => eval(e1) && eval(e2)
//    case Leq(e1, e2) => eval(e1) <= eval(e2)
//  }
}


