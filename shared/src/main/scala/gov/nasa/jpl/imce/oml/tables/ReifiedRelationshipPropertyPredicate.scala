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
  * @param bodySegmentUUID[1,1]
  * @param reifiedRelationshipUUID[1,1]
  */
@JSExportTopLevel("ReifiedRelationshipPropertyPredicate")
case class ReifiedRelationshipPropertyPredicate
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) bodySegmentUUID: UUID,
  @(JSExport @field) reifiedRelationshipUUID: UUID
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    bodySegmentUUID: UUID,
    reifiedRelationshipUUID: UUID)
  = this(
      oug.namespaceUUID(
        "ReifiedRelationshipPropertyPredicate",
        "bodySegment" -> bodySegmentUUID,
        "reifiedRelationship" -> reifiedRelationshipUUID).toString,
      bodySegmentUUID,
      reifiedRelationshipUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, bodySegmentUUID, reifiedRelationshipUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ReifiedRelationshipPropertyPredicate =>
  	  (this.uuid == that.uuid) &&
  	  (this.bodySegmentUUID == that.bodySegmentUUID) &&
  	  (this.reifiedRelationshipUUID == that.reifiedRelationshipUUID)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("ReifiedRelationshipPropertyPredicateHelper")
object ReifiedRelationshipPropertyPredicateHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "ReifiedRelationshipPropertyPredicates.json"
  
  implicit val w
  : upickle.default.Writer[ReifiedRelationshipPropertyPredicate]
  = upickle.default.macroW[ReifiedRelationshipPropertyPredicate]

  @JSExport
  def toJSON(c: ReifiedRelationshipPropertyPredicate)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[ReifiedRelationshipPropertyPredicate]
  = upickle.default.macroR[ReifiedRelationshipPropertyPredicate]

  @JSExport
  def fromJSON(c: String)
  : ReifiedRelationshipPropertyPredicate
  = upickle.default.read[ReifiedRelationshipPropertyPredicate](c)

}	
