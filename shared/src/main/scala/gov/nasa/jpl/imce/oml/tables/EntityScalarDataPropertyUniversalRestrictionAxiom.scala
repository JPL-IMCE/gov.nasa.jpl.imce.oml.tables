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
  * @param restrictedEntityUUID[1,1]
  * @param scalarPropertyUUID[1,1]
  * @param scalarRestrictionUUID[1,1]
  */
@JSExportTopLevel("EntityScalarDataPropertyUniversalRestrictionAxiom")
case class EntityScalarDataPropertyUniversalRestrictionAxiom
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) tboxUUID: UUID,
  @(JSExport @field) restrictedEntityUUID: UUID,
  @(JSExport @field) scalarPropertyUUID: UUID,
  @(JSExport @field) scalarRestrictionUUID: UUID
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: UUID,
    restrictedEntityUUID: UUID,
    scalarPropertyUUID: UUID,
    scalarRestrictionUUID: UUID)
  = this(
      oug.namespaceUUID(
        "EntityScalarDataPropertyUniversalRestrictionAxiom",
        "tbox" -> tboxUUID,
        "restrictedEntity" -> restrictedEntityUUID,
        "scalarProperty" -> scalarPropertyUUID,
        "scalarRestriction" -> scalarRestrictionUUID).toString,
      tboxUUID,
      restrictedEntityUUID,
      scalarPropertyUUID,
      scalarRestrictionUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, restrictedEntityUUID, scalarPropertyUUID, scalarRestrictionUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: EntityScalarDataPropertyUniversalRestrictionAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID) &&
  	  (this.restrictedEntityUUID == that.restrictedEntityUUID) &&
  	  (this.scalarPropertyUUID == that.scalarPropertyUUID) &&
  	  (this.scalarRestrictionUUID == that.scalarRestrictionUUID)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("EntityScalarDataPropertyUniversalRestrictionAxiomHelper")
object EntityScalarDataPropertyUniversalRestrictionAxiomHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "EntityScalarDataPropertyUniversalRestrictionAxioms.json"
  
  implicit val w
  : upickle.default.Writer[EntityScalarDataPropertyUniversalRestrictionAxiom]
  = upickle.default.macroW[EntityScalarDataPropertyUniversalRestrictionAxiom]

  @JSExport
  def toJSON(c: EntityScalarDataPropertyUniversalRestrictionAxiom)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[EntityScalarDataPropertyUniversalRestrictionAxiom]
  = upickle.default.macroR[EntityScalarDataPropertyUniversalRestrictionAxiom]

  @JSExport
  def fromJSON(c: String)
  : EntityScalarDataPropertyUniversalRestrictionAxiom
  = upickle.default.read[EntityScalarDataPropertyUniversalRestrictionAxiom](c)

}	
