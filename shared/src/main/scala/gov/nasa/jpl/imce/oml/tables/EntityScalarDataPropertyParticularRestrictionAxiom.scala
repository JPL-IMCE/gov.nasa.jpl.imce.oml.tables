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
  * @param literalValue[1,1]
  */
@JSExportTopLevel("EntityScalarDataPropertyParticularRestrictionAxiom")
case class EntityScalarDataPropertyParticularRestrictionAxiom
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) tboxUUID: UUID,
  @(JSExport @field) restrictedEntityUUID: UUID,
  @(JSExport @field) scalarPropertyUUID: UUID,
  @(JSExport @field) literalValue: LexicalValue
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: UUID,
    restrictedEntityUUID: UUID,
    scalarPropertyUUID: UUID,
    literalValue: LexicalValue)
  = this(
      oug.namespaceUUID(
        "EntityScalarDataPropertyParticularRestrictionAxiom",
        "tbox" -> tboxUUID,
        "restrictedEntity" -> restrictedEntityUUID,
        "scalarProperty" -> scalarPropertyUUID).toString,
      tboxUUID,
      restrictedEntityUUID,
      scalarPropertyUUID,
      literalValue)

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, restrictedEntityUUID, scalarPropertyUUID, literalValue).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: EntityScalarDataPropertyParticularRestrictionAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID) &&
  	  (this.restrictedEntityUUID == that.restrictedEntityUUID) &&
  	  (this.scalarPropertyUUID == that.scalarPropertyUUID) &&
  	  (this.literalValue == that.literalValue)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("EntityScalarDataPropertyParticularRestrictionAxiomHelper")
object EntityScalarDataPropertyParticularRestrictionAxiomHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "EntityScalarDataPropertyParticularRestrictionAxioms.json"
  
  implicit val w
  : upickle.default.Writer[EntityScalarDataPropertyParticularRestrictionAxiom]
  = upickle.default.macroW[EntityScalarDataPropertyParticularRestrictionAxiom]

  @JSExport
  def toJSON(c: EntityScalarDataPropertyParticularRestrictionAxiom)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[EntityScalarDataPropertyParticularRestrictionAxiom]
  = upickle.default.macroR[EntityScalarDataPropertyParticularRestrictionAxiom]

  @JSExport
  def fromJSON(c: String)
  : EntityScalarDataPropertyParticularRestrictionAxiom
  = upickle.default.read[EntityScalarDataPropertyParticularRestrictionAxiom](c)

}	
