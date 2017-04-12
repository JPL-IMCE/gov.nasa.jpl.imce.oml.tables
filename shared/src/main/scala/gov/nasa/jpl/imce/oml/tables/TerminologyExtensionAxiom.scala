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
  * @param extendedTerminologyUUID[1,1]
  */
@JSExportTopLevel("TerminologyExtensionAxiom")
case class TerminologyExtensionAxiom
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) tboxUUID: UUID,
  @(JSExport @field) extendedTerminologyUUID: UUID
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: UUID,
    extendedTerminologyUUID: UUID)
  = this(
      oug.namespaceUUID("TerminologyExtensionAxiom", "tbox" -> tboxUUID, "extendedTerminology" -> extendedTerminologyUUID).toString,
      tboxUUID,
      extendedTerminologyUUID)

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, extendedTerminologyUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: TerminologyExtensionAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID) &&
  	  (this.extendedTerminologyUUID == that.extendedTerminologyUUID)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("TerminologyExtensionAxiomHelper")
object TerminologyExtensionAxiomHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "TerminologyExtensionAxioms.json"
  
  implicit val w
  : upickle.default.Writer[TerminologyExtensionAxiom]
  = upickle.default.macroW[TerminologyExtensionAxiom]

  @JSExport
  def toJSON(c: TerminologyExtensionAxiom)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[TerminologyExtensionAxiom]
  = upickle.default.macroR[TerminologyExtensionAxiom]

  @JSExport
  def fromJSON(c: String)
  : TerminologyExtensionAxiom
  = upickle.default.read[TerminologyExtensionAxiom](c)

}	
