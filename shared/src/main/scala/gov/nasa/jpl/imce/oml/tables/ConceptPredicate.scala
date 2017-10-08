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
  * @param conceptUUID[1,1]
  */
@JSExportTopLevel("ConceptPredicate")
case class ConceptPredicate
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) bodySegmentUUID: UUID,
  @(JSExport @field) conceptUUID: UUID
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    bodySegmentUUID: UUID,
    conceptUUID: UUID)
  = this(
      oug.namespaceUUID(
        "ConceptPredicate",
        "bodySegment" -> bodySegmentUUID,
        "concept" -> conceptUUID).toString,
      bodySegmentUUID,
      conceptUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, bodySegmentUUID, conceptUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ConceptPredicate =>
  	  (this.uuid == that.uuid) &&
  	  (this.bodySegmentUUID == that.bodySegmentUUID) &&
  	  (this.conceptUUID == that.conceptUUID)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("ConceptPredicateHelper")
object ConceptPredicateHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "ConceptPredicates.json"
  
  implicit val w
  : upickle.default.Writer[ConceptPredicate]
  = upickle.default.macroW[ConceptPredicate]

  @JSExport
  def toJSON(c: ConceptPredicate)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[ConceptPredicate]
  = upickle.default.macroR[ConceptPredicate]

  @JSExport
  def fromJSON(c: String)
  : ConceptPredicate
  = upickle.default.read[ConceptPredicate](c)

}	
