package net.sigusr.it1_5

class MulExpSpec extends org.specs2.Specification {

  def is = s2"""
MulSym expressions when interpreted with the 'Int' evaluator
   add(lit(7), neg(mul(lit(1), num(2)))) must be 5 $e1
"""

  private def e1 = {
    import net.sigusr.it1_5.ExpSymInt.expSymInt
    import net.sigusr.it1_5.MulSymInt.mulSymInt
    MulSymSamples.tf2 must_== 35
  }
}
