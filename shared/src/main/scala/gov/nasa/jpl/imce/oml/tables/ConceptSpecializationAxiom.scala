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
  * @param superConceptUUID[1,1]
  * @param subConceptUUID[1,1]
  */
@JSExportTopLevel("ConceptSpecializationAxiom")
case class ConceptSpecializationAxiom
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) tboxUUID: UUID,
  @(JSExport @field) superConceptUUID: UUID,
  @(JSExport @field) subConceptUUID: UUID
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: UUID,
    superConceptUUID: UUID,
    subConceptUUID: UUID)
  = this(
      oug.namespaceUUID(
        "ConceptSpecializationAxiom",
        "tbox" -> tboxUUID,
        "superConcept" -> superConceptUUID,
        "subConcept" -> subConceptUUID).toString,
      tboxUUID,
      superConceptUUID,
      subConceptUUID)

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, superConceptUUID, subConceptUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ConceptSpecializationAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID) &&
  	  (this.superConceptUUID == that.superConceptUUID) &&
  	  (this.subConceptUUID == that.subConceptUUID)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("ConceptSpecializationAxiomHelper")
object ConceptSpecializationAxiomHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "ConceptSpecializationAxioms.json"
  
  implicit val w
  : upickle.default.Writer[ConceptSpecializationAxiom]
  = upickle.default.macroW[ConceptSpecializationAxiom]

  @JSExport
  def toJSON(c: ConceptSpecializationAxiom)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[ConceptSpecializationAxiom]
  = upickle.default.macroR[ConceptSpecializationAxiom]

  @JSExport
  def fromJSON(c: String)
  : ConceptSpecializationAxiom
  = upickle.default.read[ConceptSpecializationAxiom](c)

}	
