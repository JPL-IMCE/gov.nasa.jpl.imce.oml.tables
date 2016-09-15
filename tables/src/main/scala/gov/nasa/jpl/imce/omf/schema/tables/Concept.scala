package gov.nasa.jpl.imce.omf.schema.tables

import scala.annotation.meta.field
import scala.scalajs.js.annotation.JSExport
import scala.Boolean
import scala.Predef.String

@JSExport
case class Concept
( @(JSExport @field) resourceIRI: String,
  @(JSExport @field) uuid: String,
  @(JSExport @field) isAbstract: Boolean,
  @(JSExport @field) name: String )