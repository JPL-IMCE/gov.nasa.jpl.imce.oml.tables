/*
 * Copyright 2016 California Institute of Technology ("Caltech").
 * U.S. Government sponsorship acknowledged.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * License Terms
 */

 
package gov.nasa.jpl.imce.oml.tables

import scala.annotation.meta.field
import scala.scalajs.js.annotation.{JSExport,JSExportTopLevel}
import scala._
import scala.Predef._

/**
  * @param uuid[1,1]
  * @param tboxUUID[1,1]
  * @param nestingContextUUID[1,1]
  * @param nestingTerminologyIRI[1,1]
  */
@JSExportTopLevel("TerminologyNestingAxiom")
case class TerminologyNestingAxiom
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) tboxUUID: UUID,
  @(JSExport @field) nestingContextUUID: UUID,
  @(JSExport @field) nestingTerminologyIRI: IRI
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: UUID,
    nestingContextUUID: UUID,
    nestingTerminologyIRI: IRI)
  = this(
      oug.namespaceUUID(
        "TerminologyNestingAxiom",
        "tbox" -> tboxUUID,
        "nestingContext" -> nestingContextUUID,
        "nestingTerminology" -> oug.namespaceUUID(nestingTerminologyIRI).toString).toString,
      tboxUUID,
      nestingContextUUID,
      nestingTerminologyIRI)

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, nestingContextUUID, nestingTerminologyIRI).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: TerminologyNestingAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID) &&
  	  (this.nestingContextUUID == that.nestingContextUUID) &&
  	  (this.nestingTerminologyIRI == that.nestingTerminologyIRI)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("TerminologyNestingAxiomHelper")
object TerminologyNestingAxiomHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "TerminologyNestingAxioms.json"
  
  implicit val w
  : upickle.default.Writer[TerminologyNestingAxiom]
  = upickle.default.macroW[TerminologyNestingAxiom]

  @JSExport
  def toJSON(c: TerminologyNestingAxiom)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[TerminologyNestingAxiom]
  = upickle.default.macroR[TerminologyNestingAxiom]

  @JSExport
  def fromJSON(c: String)
  : TerminologyNestingAxiom
  = upickle.default.read[TerminologyNestingAxiom](c)

}	
