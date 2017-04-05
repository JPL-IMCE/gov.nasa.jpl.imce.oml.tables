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
import scala.scalajs.js.annotation.JSExport
import scala._
import scala.Predef._

/**
  * @param uuid[1,1]
  * @param tboxUUID[1,1]
  * @param restrictedRelationUUID[1,1]
  * @param restrictedDomainUUID[1,1]
  * @param restrictedRangeUUID[1,1]
  */
@JSExport
case class EntityExistentialRestrictionAxiom
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) tboxUUID: UUID,
  @(JSExport @field) restrictedRelationUUID: UUID,
  @(JSExport @field) restrictedDomainUUID: UUID,
  @(JSExport @field) restrictedRangeUUID: UUID
) {
  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, restrictedRelationUUID, restrictedDomainUUID, restrictedRangeUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: EntityExistentialRestrictionAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID) &&
  	  (this.restrictedRelationUUID == that.restrictedRelationUUID) &&
  	  (this.restrictedDomainUUID == that.restrictedDomainUUID) &&
  	  (this.restrictedRangeUUID == that.restrictedRangeUUID)
    case _ =>
      false
  }
  
}

@JSExport
object EntityExistentialRestrictionAxiomHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "EntityExistentialRestrictionAxioms.json"
  
  implicit val w
  : upickle.default.Writer[EntityExistentialRestrictionAxiom]
  = upickle.default.macroW[EntityExistentialRestrictionAxiom]

  @JSExport
  def toJSON(c: EntityExistentialRestrictionAxiom)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[EntityExistentialRestrictionAxiom]
  = upickle.default.macroR[EntityExistentialRestrictionAxiom]

  @JSExport
  def fromJSON(c: String)
  : EntityExistentialRestrictionAxiom
  = upickle.default.read[EntityExistentialRestrictionAxiom](c)

}	
