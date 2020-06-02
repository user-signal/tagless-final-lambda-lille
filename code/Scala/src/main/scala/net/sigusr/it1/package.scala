package net.sigusr

package object it1 {

  /*

    Definition of a serialization format

    data Tree = Leaf String
        | Node String [Tree]
          deriving (Eq, Read, Show)

   */
  sealed trait Tree
  final case class Leaf(value: String) extends Tree
  final case class Node(name: String, children: List[Tree]) extends Tree

  /*

    Definition of a context for the 'push neg' transformer

    data Ctx = Pos | Neg

   */
  sealed trait Ctx0
  final case object Pos extends Ctx0
  final case object Neg extends Ctx0

  /*

    data Ctx e = LCA e | NonLCA

    Definition of a context for the 'flattening' transformer

  */
  sealed trait Ctx1[+E]
  final case class Lca[E](e: E) extends Ctx1[E]
  final case object NonLca extends Ctx1[Nothing]
}
