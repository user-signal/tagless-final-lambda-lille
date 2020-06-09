package net.sigusr.it1

object Exp {
  def num(i: Int): Int = i
  def neg(r: Int): Int = - r
  def add(r1: Int, r2: Int): Int = r1 + r2
}

object ExpSymSamples {

  import net.sigusr.it1.Exp._

  val tf1: Int = add(num(8), neg(add(num(1), num(2))))

}
