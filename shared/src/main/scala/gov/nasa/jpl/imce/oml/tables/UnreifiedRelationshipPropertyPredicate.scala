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
  * @param unreifiedRelationshipUUID[1,1]
  * @param bodySegmentUUID[1,1]
  */
@JSExportTopLevel("UnreifiedRelationshipPropertyPredicate")
case class UnreifiedRelationshipPropertyPredicate
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) unreifiedRelationshipUUID: UUID,
  @(JSExport @field) bodySegmentUUID: UUID
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    unreifiedRelationshipUUID: UUID,
    bodySegmentUUID: UUID)
  = this(
      oug.namespaceUUID(
        "UnreifiedRelationshipPropertyPredicate",
        "unreifiedRelationship" -> unreifiedRelationshipUUID,
        "bodySegment" -> bodySegmentUUID).toString,
      unreifiedRelationshipUUID,
      bodySegmentUUID)

  override val hashCode
  : scala.Int 
  = (uuid, unreifiedRelationshipUUID, bodySegmentUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: UnreifiedRelationshipPropertyPredicate =>
  	  (this.uuid == that.uuid) &&
  	  (this.unreifiedRelationshipUUID == that.unreifiedRelationshipUUID) &&
  	  (this.bodySegmentUUID == that.bodySegmentUUID)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("UnreifiedRelationshipPropertyPredicateHelper")
object UnreifiedRelationshipPropertyPredicateHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "UnreifiedRelationshipPropertyPredicates.json"
  
  implicit val w
  : upickle.default.Writer[UnreifiedRelationshipPropertyPredicate]
  = upickle.default.macroW[UnreifiedRelationshipPropertyPredicate]

  @JSExport
  def toJSON(c: UnreifiedRelationshipPropertyPredicate)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[UnreifiedRelationshipPropertyPredicate]
  = upickle.default.macroR[UnreifiedRelationshipPropertyPredicate]

  @JSExport
  def fromJSON(c: String)
  : UnreifiedRelationshipPropertyPredicate
  = upickle.default.read[UnreifiedRelationshipPropertyPredicate](c)

}	
