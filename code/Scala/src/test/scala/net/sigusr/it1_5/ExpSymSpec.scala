package net.sigusr.it1_5

class ExpSymSpec extends org.specs2.Specification {

  def is = s2"""
An ExpSym expression when interpreted with the 'Int' evaluator
   add(lit(8), neg(add(lit(1), num(2)))) must be 5 $e1
An ExpSym expression when interpreted with the 'String' evaluator
   add(lit(8), neg(add(lit(1), num(2)))) must be "(8 + (-(1 + 2)))" $e2
"""

  private def e1 = {
    import net.sigusr.it1_5.ExpSymInt.expSymInt
    ExpSymSamples.tf1 must_== 5
  }

  private def e2 = {
    import net.sigusr.it1_5.ExpSymString.expSymString
    ExpSymSamples.tf1 must_== "(8 + (-(1 + 2)))"
  }
}
