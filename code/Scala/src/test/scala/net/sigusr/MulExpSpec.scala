package net.sigusr

import net.sigusr.MulSymPushNeg.mulSymPushNeg

class MulExpSpec extends org.specs2.Specification {

  def is = s2"""
MulSym expressions when interpreted with the 'Int' evaluator
   add(lit(7), neg(mul(lit(1), lit(2)))) must be 5 $e1
   mul(lit(7), ExpSymSamples.tf1) must be 35 $e2
MulSym expressions when interpreted with the 'String' evaluator
   add(lit(7), neg(mul(lit(1), lit(2)))) must be "(7 + (-(2 * 1)))" $e3
   mul(lit(7), ExpSymSamples.tf1) must be "(7 * (8 + (-(1 + 2))))" $e4
A MulSym expression  when interpreted with the 'PushNeg' and then the 'String' evaluators
   add(lit(7), neg(mul(lit(1), lit(2)))) must be "(7 + (2 * (-1)))" $e5
"""

  private def e1 = {
    import ExpSymInt.expSymInt
    import MulSymInt.mulSymInt
    MulSymSamples.tfm1 must_== 5
  }

  private def e2 = {
    import ExpSymInt.expSymInt
    import MulSymInt.mulSymInt
    MulSymSamples.tfm2 must_== 35
  }

  private def e3 = {
    import ExpSymString.expSymString
    import MulSymString.mulSymString
    MulSymSamples.tfm1 must_== "(7 + (-(2 * 1)))"
  }

  private def e4 = {
    import ExpSymString.expSymString
    import MulSymString.mulSymString
    MulSymSamples.tfm2 must_== "(7 * (8 + (-(1 + 2))))"
  }

  private def e5 = {
    import ExpSymString.expSymString
    import ExpSymPushNeg.expSymPushNeg
    import MulSymString.mulSymString
    import MulSymPushNeg.mulSymPushNeg
    MulSymSamples.tfm1(expSymPushNeg, mulSymPushNeg)(Pos) must_== "(7 + (2 * (-1)))"
  }
}
