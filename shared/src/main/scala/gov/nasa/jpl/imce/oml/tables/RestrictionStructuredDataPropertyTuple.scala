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
  * @param structuredDataPropertyUUID[1,1]
  * @param structuredDataPropertyContextUUID[1,1]
  */
@JSExportTopLevel("RestrictionStructuredDataPropertyTuple")
case class RestrictionStructuredDataPropertyTuple
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) structuredDataPropertyUUID: UUID,
  @(JSExport @field) structuredDataPropertyContextUUID: UUID
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    structuredDataPropertyUUID: UUID,
    structuredDataPropertyContextUUID: UUID)
  = this(
      oug.namespaceUUID(
        "RestrictionStructuredDataPropertyTuple",
        "structuredDataProperty" -> structuredDataPropertyUUID,
        "structuredDataPropertyContext" -> structuredDataPropertyContextUUID).toString,
      structuredDataPropertyUUID,
      structuredDataPropertyContextUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, structuredDataPropertyUUID, structuredDataPropertyContextUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: RestrictionStructuredDataPropertyTuple =>
  	  (this.uuid == that.uuid) &&
  	  (this.structuredDataPropertyUUID == that.structuredDataPropertyUUID) &&
  	  (this.structuredDataPropertyContextUUID == that.structuredDataPropertyContextUUID)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("RestrictionStructuredDataPropertyTupleHelper")
object RestrictionStructuredDataPropertyTupleHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "RestrictionStructuredDataPropertyTuples.json"
  
  implicit val w
  : upickle.default.Writer[RestrictionStructuredDataPropertyTuple]
  = upickle.default.macroW[RestrictionStructuredDataPropertyTuple]

  @JSExport
  def toJSON(c: RestrictionStructuredDataPropertyTuple)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[RestrictionStructuredDataPropertyTuple]
  = upickle.default.macroR[RestrictionStructuredDataPropertyTuple]

  @JSExport
  def fromJSON(c: String)
  : RestrictionStructuredDataPropertyTuple
  = upickle.default.read[RestrictionStructuredDataPropertyTuple](c)

}	
