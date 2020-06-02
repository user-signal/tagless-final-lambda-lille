package net.sigusr.it0
import org.specs2.specification.core.SpecStructure

class ExpSpec extends org.specs2.Specification {
  override def is: SpecStructure = s2"""
An Exp expression when interpreted with the 'eval' evaluator
   add(lit(8), neg(add(lit(1), lit(2)))) must be 5 $e1
   add(lit(8), neg(add(lit(1), lit(2)))) must be 5 $e2
"""

  private def e1 = {
    Exp.eval(ExpSamples.ti3).asB must_== true
  }

  private def e2 = {
    Exp.eval(ExpSamples.tie) must throwA[MatchError]
  }
}
