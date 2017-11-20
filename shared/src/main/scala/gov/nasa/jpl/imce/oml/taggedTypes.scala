package gov.nasa.jpl.imce.oml

import covariantTag.@@
import io.circe._

import scala.{Left, Right, Some}
import scala.Predef.String

object taggedTypes {

  implicit def decodeTag[Tag]: Decoder[String @@ Tag] = new Decoder[String @@ Tag] {
    final def apply(c: HCursor): Decoder.Result[String @@ Tag] = c.value.asString match {
      case Some(string) => Right(covariantTag[Tag][String](string))
      case _ => Left(DecodingFailure("String", c.history))
    }
  }

  implicit def encodeTag[Tag]: Encoder[String @@ Tag] = new Encoder[String @@ Tag] {
    final def apply(s: String @@ Tag): Json = Json.fromString(s)
  }

}
