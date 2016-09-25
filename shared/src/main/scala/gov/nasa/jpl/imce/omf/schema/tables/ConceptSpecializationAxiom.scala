package gov.nasa.jpl.imce.omf.schema.tables

import scala.annotation.meta.field
import scala.scalajs.js.annotation.JSExport
import scala._
import scala.Predef._

/**
  *
  * @param graphUUID (Foreign key) The UUID of the TerminologyGraph in which this ConceptSpecializationAxiom is asserted.
  * @param supConcept (Foreign key) The UUID of the super Concept that is a specialization parent of the sub Concept.
  * @param subConcept (Foreign key) The UUID of the sub Concept that is a specialization child of the super Concept.
  */
@JSExport
case class ConceptSpecializationAxiom
( @(JSExport @field) graphUUID: String,
  @(JSExport @field) supConcept: String,
  @(JSExport @field) subConcept: String)

@JSExport
object ConceptSpecializationAxiomHelper {

  implicit val w
  : upickle.default.Writer[ConceptSpecializationAxiom]
  = upickle.default.macroW[ConceptSpecializationAxiom]

  @JSExport
  def toJSON(csa: ConceptSpecializationAxiom)
  : String
  = upickle.default.write(expr=csa, indent=0)

  implicit val r
  : upickle.default.Reader[ConceptSpecializationAxiom]
  = upickle.default.macroR[ConceptSpecializationAxiom]

  @JSExport
  def fromJSON(csa: String)
  : ConceptSpecializationAxiom
  = upickle.default.read[ConceptSpecializationAxiom](csa)

}