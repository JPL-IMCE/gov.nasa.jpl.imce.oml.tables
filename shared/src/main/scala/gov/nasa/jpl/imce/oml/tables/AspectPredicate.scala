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
  * @param aspectUUID[1,1]
  * @param bodySegmentUUID[1,1]
  */
@JSExportTopLevel("AspectPredicate")
case class AspectPredicate
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) aspectUUID: UUID,
  @(JSExport @field) bodySegmentUUID: UUID
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    aspectUUID: UUID,
    bodySegmentUUID: UUID)
  = this(
      oug.namespaceUUID(
        "AspectPredicate",
        "aspect" -> aspectUUID,
        "bodySegment" -> bodySegmentUUID).toString,
      aspectUUID,
      bodySegmentUUID)

  override val hashCode
  : scala.Int 
  = (uuid, aspectUUID, bodySegmentUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: AspectPredicate =>
  	  (this.uuid == that.uuid) &&
  	  (this.aspectUUID == that.aspectUUID) &&
  	  (this.bodySegmentUUID == that.bodySegmentUUID)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("AspectPredicateHelper")
object AspectPredicateHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "AspectPredicates.json"
  
  implicit val w
  : upickle.default.Writer[AspectPredicate]
  = upickle.default.macroW[AspectPredicate]

  @JSExport
  def toJSON(c: AspectPredicate)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[AspectPredicate]
  = upickle.default.macroR[AspectPredicate]

  @JSExport
  def fromJSON(c: String)
  : AspectPredicate
  = upickle.default.read[AspectPredicate](c)

}	
