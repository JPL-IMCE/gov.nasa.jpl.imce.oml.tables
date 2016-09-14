package gov.nasa.jpl.imce.omf.schema.tables

import scala.scalajs.js.annotation.JSExport
import scala.Boolean
import scala.Predef.String

@JSExport
case class Concept
( resourceIRI: String,
  uuid: String,
  isAbstract: Boolean,
  name: String )