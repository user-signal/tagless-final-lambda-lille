package net.sigusr.it1

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
    import net.sigusr.it1.ExpSymInt.expSymInt
    import net.sigusr.it1.MulSymInt.mulSymInt
    MulSymSamples.tfm1 must_== 5
  }

  private def e2 = {
    import net.sigusr.it1.ExpSymInt.expSymInt
    import net.sigusr.it1.MulSymInt.mulSymInt
    MulSymSamples.tfm2 must_== 35
  }

  private def e3 = {
    import net.sigusr.it1.ExpSymString.expSymString
    import net.sigusr.it1.MulSymString.mulSymString
    MulSymSamples.tfm1 must_== "(7 + (-(2 * 1)))"
  }

  private def e4 = {
    import net.sigusr.it1.ExpSymString.expSymString
    import net.sigusr.it1.MulSymString.mulSymString
    MulSymSamples.tfm2 must_== "(7 * (8 + (-(1 + 2))))"
  }

  private def e5 = {
    import net.sigusr.it1.ExpSymPushNeg.expSymPushNeg
    import net.sigusr.it1.ExpSymString.expSymString
    import net.sigusr.it1.MulSymPushNeg.mulSymPushNeg
    import net.sigusr.it1.MulSymString.mulSymString
    MulSymSamples.tfm1(expSymPushNeg, mulSymPushNeg)(Pos) must_== "(7 + (2 * (-1)))"
  }
}
