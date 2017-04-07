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
  * @param descriptionBoxUUID[1,1]
  * @param reifiedRelationshipInstanceUUID[1,1]
  * @param rangeUUID[1,1]
  * @param name[1,1]
  */
@JSExport
case class ReifiedRelationshipInstanceRange
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) descriptionBoxUUID: UUID,
  @(JSExport @field) reifiedRelationshipInstanceUUID: UUID,
  @(JSExport @field) rangeUUID: UUID,
  @(JSExport @field) name: LocalName
) {
  // Ctor(uuidWithGenerator)   
  @JSExport
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    descriptionBoxUUID: UUID,
    reifiedRelationshipInstanceUUID: UUID,
    rangeUUID: UUID,
    name: LocalName)
  = this(
      oug.namespaceUUID(descriptionBoxUUID, "name" -> name).toString,
      descriptionBoxUUID,
      reifiedRelationshipInstanceUUID,
      rangeUUID,
      name)

  override val hashCode
  : scala.Int 
  = (uuid, descriptionBoxUUID, reifiedRelationshipInstanceUUID, rangeUUID, name).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ReifiedRelationshipInstanceRange =>
  	  (this.uuid == that.uuid) &&
  	  (this.descriptionBoxUUID == that.descriptionBoxUUID) &&
  	  (this.reifiedRelationshipInstanceUUID == that.reifiedRelationshipInstanceUUID) &&
  	  (this.rangeUUID == that.rangeUUID) &&
  	  (this.name == that.name)
    case _ =>
      false
  }
  
}

@JSExport
object ReifiedRelationshipInstanceRangeHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "ReifiedRelationshipInstanceRanges.json"
  
  implicit val w
  : upickle.default.Writer[ReifiedRelationshipInstanceRange]
  = upickle.default.macroW[ReifiedRelationshipInstanceRange]

  @JSExport
  def toJSON(c: ReifiedRelationshipInstanceRange)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[ReifiedRelationshipInstanceRange]
  = upickle.default.macroR[ReifiedRelationshipInstanceRange]

  @JSExport
  def fromJSON(c: String)
  : ReifiedRelationshipInstanceRange
  = upickle.default.read[ReifiedRelationshipInstanceRange](c)

}	
