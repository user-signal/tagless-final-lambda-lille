package net.sigusr.it1

class ExpSymSpec extends org.specs2.Specification {

  def is = s2"""
An ExpSym expression when interpreted with the 'Int' evaluator
   add(lit(8), neg(add(lit(1), num(2)))) must be 5 $e1
"""

  private def e1 = {
    ExpSymSamples.tf1 must_== 5
  }
}
