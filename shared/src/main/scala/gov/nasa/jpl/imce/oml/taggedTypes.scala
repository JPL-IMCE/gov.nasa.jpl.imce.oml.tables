package gov.nasa.jpl.imce.oml

import covariantTag.@@
import io.circe._

import scala.collection.immutable.Seq
import scala.{Left, Right, Some}
import scala.Predef.String

object taggedTypes {

  implicit def decodeTag[Tag]: Decoder[String @@ Tag] = new Decoder[String @@ Tag] {
    final def apply(c: HCursor): Decoder.Result[String @@ Tag] = c.value.asString match {
      case Some(string) =>
        Right(covariantTag[Tag][String](string))
      case _ =>
        Left(DecodingFailure("String", c.history))
    }
  }

  def decodeArrayTag[Tag]: Decoder[String @@ Tag] = new Decoder[String @@ Tag] {
    final def apply(c: HCursor): Decoder.Result[String @@ Tag] = c.values match {
      case Some(strings) =>
        val s =
          strings
            .flatMap(_.asString)
            .map {
              case "\"" =>
                "\""
              case "\n" =>
                "\n"
              case "\r" =>
                "\r"
              case s =>
                s
            }
            .mkString
        Right(covariantTag[Tag][String](s))
      case _ =>
        Left(DecodingFailure("String (Array)", c.history))
    }
  }

  implicit def encodeTag[Tag]: Encoder[String @@ Tag] = new Encoder[String @@ Tag] {
    final def apply(s: String @@ Tag): Json = Json.fromString(s)
  }

  def encodeArrayTag[Tag]: Encoder[String @@ Tag] = new Encoder[String @@ Tag] {
    final def apply(s: String @@ Tag): Json = {

      @scala.annotation.tailrec
      def splitString(acc: Seq[Json], ss: String)
      : Seq[Json]
      = if (ss.isEmpty)
        acc
      else {
        val qi = ss.indexOf('\"')
        val ni = ss.indexOf('\n')
        val li = ss.indexOf('\r')

        if (0 == qi)
          splitString(acc :+ Json.fromString("\""), ss.substring(1))
        else if (0 == ni)
          splitString(acc :+ Json.fromString("\n"), ss.substring(1))
        else if (0 == li)
          splitString(acc :+ Json.fromString("\r"), ss.substring(1))
        else if (0 < qi && (-1 == ni || qi < ni) && (-1 == li || qi < li))
          splitString(acc :+ Json.fromString(ss.substring(0, qi)) :+ Json.fromString("\""), ss.substring(qi+1))
        else if (0 < ni && (-1 == qi || ni < qi) && (-1 == li || ni < li))
          splitString(acc :+ Json.fromString(ss.substring(0, ni)) :+ Json.fromString("\n"), ss.substring(ni+1))
        else if (0 < li && (-1 == qi || li < qi) && (-1 == ni || li < ni))
          splitString(acc :+ Json.fromString(ss.substring(0, li)) :+ Json.fromString("\r"), ss.substring(li+1))
        else
          acc :+ Json.fromString(ss)
      }

      val values = splitString(Seq.empty[Json], s)

      Json.fromValues(values)
    }
  }
}
