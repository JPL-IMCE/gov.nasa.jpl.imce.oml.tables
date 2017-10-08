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
  * @param descriptionBoxUUID[1,1]
  * @param unreifiedRelationshipUUID[1,1]
  * @param domainUUID[1,1]
  * @param rangeUUID[1,1]
  */
@JSExportTopLevel("UnreifiedRelationshipInstanceTuple")
case class UnreifiedRelationshipInstanceTuple
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) descriptionBoxUUID: UUID,
  @(JSExport @field) unreifiedRelationshipUUID: UUID,
  @(JSExport @field) domainUUID: UUID,
  @(JSExport @field) rangeUUID: UUID
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    descriptionBoxUUID: UUID,
    unreifiedRelationshipUUID: UUID,
    domainUUID: UUID,
    rangeUUID: UUID)
  = this(
      oug.namespaceUUID(
        "UnreifiedRelationshipInstanceTuple",
        "descriptionBox" -> descriptionBoxUUID,
        "unreifiedRelationship" -> unreifiedRelationshipUUID,
        "domain" -> domainUUID,
        "range" -> rangeUUID).toString,
      descriptionBoxUUID,
      unreifiedRelationshipUUID,
      domainUUID,
      rangeUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, descriptionBoxUUID, unreifiedRelationshipUUID, domainUUID, rangeUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: UnreifiedRelationshipInstanceTuple =>
  	  (this.uuid == that.uuid) &&
  	  (this.descriptionBoxUUID == that.descriptionBoxUUID) &&
  	  (this.unreifiedRelationshipUUID == that.unreifiedRelationshipUUID) &&
  	  (this.domainUUID == that.domainUUID) &&
  	  (this.rangeUUID == that.rangeUUID)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("UnreifiedRelationshipInstanceTupleHelper")
object UnreifiedRelationshipInstanceTupleHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "UnreifiedRelationshipInstanceTuples.json"
  
  implicit val w
  : upickle.default.Writer[UnreifiedRelationshipInstanceTuple]
  = upickle.default.macroW[UnreifiedRelationshipInstanceTuple]

  @JSExport
  def toJSON(c: UnreifiedRelationshipInstanceTuple)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[UnreifiedRelationshipInstanceTuple]
  = upickle.default.macroR[UnreifiedRelationshipInstanceTuple]

  @JSExport
  def fromJSON(c: String)
  : UnreifiedRelationshipInstanceTuple
  = upickle.default.read[UnreifiedRelationshipInstanceTuple](c)

}	
