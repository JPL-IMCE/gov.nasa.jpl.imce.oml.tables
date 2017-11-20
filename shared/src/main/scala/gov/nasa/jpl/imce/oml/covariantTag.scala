package gov.nasa.jpl.imce.oml

import scala.{Boolean,Nothing}

/**
  * This is a covariant version of Shapeless 2.3.2 tag.
  *
  * @see https://github.com/milessabin/shapeless/blob/shapeless-2.3.2/core/src/main/scala/shapeless/typeoperators.scala#L25
  */
object covariantTag {

  def apply[U] = new Tagger[U]

  trait Tagged[+U]

  class Tagger[U] {
    def apply[T](t : T) : T @@ U = t.asInstanceOf[T @@ U]
  }

  type @@[+T, U] = T with Tagged[U]

  def compareTaggedValues[T, U, V <: U]
  (x: T @@ (_ <: U), y: T @@ V)
  (implicit eu: U =!= Nothing, ev: V =!= Nothing)
  : Boolean
  = x.asInstanceOf[T] == y.asInstanceOf[T]

  trait =!=[A, B]

  implicit def neq[A, B] : A =!= B = null

  // This pair excludes the A =!= A case
  implicit def neqAmbig1[A] : A =!= A = null
  implicit def neqAmbig2[A] : A =!= A = null

  // This pair exceludes the Object =!= Nothing case
  // (this is useful to prevent type inference from considering Object & Nothing as candidates).
  implicit def objectAmbig1 : java.lang.Object =!= Nothing = null
  implicit def objectAmbig2 : java.lang.Object =!= Nothing = null
}
