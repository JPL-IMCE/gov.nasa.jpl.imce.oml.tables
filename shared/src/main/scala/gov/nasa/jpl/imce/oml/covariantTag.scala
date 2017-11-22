package gov.nasa.jpl.imce.oml

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

  type @@[+T, +U] = T with Tagged[U]

}
