package net.sigusr

package object it2 {

  case class R[A](unR: A) extends AnyVal

  case class S[A](unS: String) extends AnyVal
}
