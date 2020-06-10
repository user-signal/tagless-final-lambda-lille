package net.sigusr

object tc {

  trait Ord[T] {
    def compare(a: T, b: T): Boolean
  }

  def sort[T](l: List[T], ord: Ord[T]): List[T] = ???

  val intOrd: Ord[Int] = new Ord[Int] {
    override def compare(a: Int, b: Int): Boolean = a <= b
  }

  val res: List[Int] = sort(List(26, 1, 1969), intOrd)
}

object tci {

  trait Ord[T] {
    def compare(a: T, b: T): Boolean
  }

  def sort[T](l: List[T])(implicit ord: Ord[T]): List[T] = ???

  implicit val intOrd: Ord[Int] = new Ord[Int] {
    override def compare(a: Int, b: Int): Boolean = a <= b
  }

  val res: List[Int] = sort(List(26, 1, 1969))
}

object tcic {

  trait Ord[T] {
    def compare(a: T, b: T): Boolean
  }

  def sort[T: Ord](l: List[T]): List[T] = ???

  implicit val intOrd: Ord[Int] = new Ord[Int] {
    override def compare(a: Int, b: Int): Boolean = a <= b
  }

  val res: List[Int] = sort(List(26, 1, 1969))
}
