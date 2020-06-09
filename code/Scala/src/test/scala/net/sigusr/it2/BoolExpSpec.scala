package net.sigusr.it2

class BoolExpSpec extends org.specs2.Specification {

  def is = s2"""
BoolSym expressions when interpreted with the 'Int' evaluator
   add(lit(7), neg(mul(lit(1), num(2)))) must be 5 $e1
"""

  private def e1 = {
    import ExpSymInt.expSymInt
    import MulSymInt.mulSymInt
    import BoolSymInt.boolSymInt
    BoolSymSamples.tfb1.unR must_== true
  }
}
