package net.sigusr.it2

class BoolExpSpec extends org.specs2.Specification {

  def is = s2"""
BoolSym expressions when interpreted with the 'Int' evaluator
   add(lit(7), neg(mul(lit(1), num(2)))) must be 5 $e1
   add(lit(7), neg(mul(lit(1), num(2)))) must be 5 $e2
"""

  private def e1 = {
    import ExpSymInt.expSymI
    import MulSymInt.mulSymI
    import BoolSymI.boolSymI
    BoolSymSamples.tf3 must_== true
  }

  private def e2 = {
    import ExpSymString.expSymS
    import MulSymString.mulSymS
    import BoolSymS.boolSymS
    BoolSymSamples.tf3 must_== "(((8 + (-(1 + 2))) ≦ (7 × (8 + (-(1 + 2))))) ∨ (0 ≦ (7 × (8 + (-(1 + 2))))))"
  }
}
