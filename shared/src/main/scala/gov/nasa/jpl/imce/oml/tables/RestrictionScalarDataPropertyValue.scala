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
  * @param scalarDataPropertyUUID[1,1]
  * @param scalarPropertyValue[1,1]
  * @param structuredDataPropertyContextUUID[1,1]
  * @param valueTypeUUID[0,1]
  */
case class RestrictionScalarDataPropertyValue
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) scalarDataPropertyUUID: UUID,
  @(JSExport @field) scalarPropertyValue: LiteralValue,
  @(JSExport @field) structuredDataPropertyContextUUID: UUID,
  @(JSExport @field) valueTypeUUID: scala.Option[UUID]
) {
  def this(
    uuid: UUID,
    scalarDataPropertyUUID: UUID,
    scalarPropertyValue: LiteralValue,
    structuredDataPropertyContextUUID: UUID)
  = this(
      uuid,
      scalarDataPropertyUUID,
      scalarPropertyValue,
      structuredDataPropertyContextUUID,
      None /* valueTypeUUID */)

  def withValueTypeUUID(l: UUID)	 
  : RestrictionScalarDataPropertyValue
  = copy(valueTypeUUID=Some(l))
  
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    scalarDataPropertyUUID: UUID,
    scalarPropertyValue: LiteralValue,
    structuredDataPropertyContextUUID: UUID)
  = this(
      oug.namespaceUUID(
        "RestrictionScalarDataPropertyValue",
        "scalarDataProperty" -> scalarDataPropertyUUID,
        "structuredDataPropertyContext" -> structuredDataPropertyContextUUID).toString,
      scalarDataPropertyUUID,
      scalarPropertyValue,
      structuredDataPropertyContextUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, scalarDataPropertyUUID, scalarPropertyValue, structuredDataPropertyContextUUID, valueTypeUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: RestrictionScalarDataPropertyValue =>
  	  (this.uuid == that.uuid) &&
  	  (this.scalarDataPropertyUUID == that.scalarDataPropertyUUID) &&
  	  (this.scalarPropertyValue == that.scalarPropertyValue) &&
  	  (this.structuredDataPropertyContextUUID == that.structuredDataPropertyContextUUID) &&
  	  (this.valueTypeUUID == that.valueTypeUUID)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("RestrictionScalarDataPropertyValueHelper")
object RestrictionScalarDataPropertyValueHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "RestrictionScalarDataPropertyValues.json"
  
  implicit val w
  : upickle.default.Writer[RestrictionScalarDataPropertyValue]
  = upickle.default.macroW[RestrictionScalarDataPropertyValue]

  @JSExport
  def toJSON(c: RestrictionScalarDataPropertyValue)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[RestrictionScalarDataPropertyValue]
  = upickle.default.macroR[RestrictionScalarDataPropertyValue]

  @JSExport
  def fromJSON(c: String)
  : RestrictionScalarDataPropertyValue
  = upickle.default.read[RestrictionScalarDataPropertyValue](c)

}	
