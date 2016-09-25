package gov.nasa.jpl.imce.omf.schema.tables

import scala.scalajs.js.annotation.{JSExport,JSExportDescendentObjects}
import scala._
import scala.Predef._

@JSExportDescendentObjects
sealed trait TerminologyGraphKind

case object OpenWorldDefinitions extends TerminologyGraphKind

case object ClosedWorldDesignations extends TerminologyGraphKind

@JSExport
object TerminologyGraphKind {

  implicit val w
  : upickle.default.Writer[TerminologyGraphKind]
  = upickle.default.Writer[TerminologyGraphKind]{
    case OpenWorldDefinitions =>
      upickle.Js.Str("OpenWorldDefinitions")
    case ClosedWorldDesignations =>
      upickle.Js.Str("ClosedWorldDesignations")
  }

  @JSExport
  def toJSON(k: TerminologyGraphKind)
  : String
  = upickle.default.write(expr=k, indent=0)

  implicit val r
  : upickle.default.Reader[TerminologyGraphKind]
  = upickle.default.Reader[TerminologyGraphKind]{
    case upickle.Js.Str(k) =>
      k match {
        case "OpenWorldDefinitions" =>
          OpenWorldDefinitions
        case "ClosedWorldDesignations" =>
          ClosedWorldDesignations
      }
  }

  @JSExport
  def fromJSON(k: String)
  : TerminologyGraphKind
  = upickle.default.read[TerminologyGraphKind](k)

}