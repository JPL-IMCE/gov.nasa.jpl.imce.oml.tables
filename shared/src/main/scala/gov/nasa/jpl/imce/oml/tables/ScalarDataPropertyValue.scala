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
  */
@JSExportTopLevel("ScalarDataPropertyValue")
case class ScalarDataPropertyValue
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) scalarDataPropertyUUID: UUID,
  @(JSExport @field) scalarPropertyValue: scala.Predef.String,
  @(JSExport @field) structuredDataPropertyContextUUID: UUID
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    scalarDataPropertyUUID: UUID,
    scalarPropertyValue: scala.Predef.String,
    structuredDataPropertyContextUUID: UUID)
  = this(
      oug.namespaceUUID(
        "ScalarDataPropertyValue",
        "scalarDataProperty" -> scalarDataPropertyUUID,
        "structuredDataPropertyContext" -> structuredDataPropertyContextUUID).toString,
      scalarDataPropertyUUID,
      scalarPropertyValue,
      structuredDataPropertyContextUUID)

  override val hashCode
  : scala.Int 
  = (uuid, scalarDataPropertyUUID, scalarPropertyValue, structuredDataPropertyContextUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ScalarDataPropertyValue =>
  	  (this.uuid == that.uuid) &&
  	  (this.scalarDataPropertyUUID == that.scalarDataPropertyUUID) &&
  	  (this.scalarPropertyValue == that.scalarPropertyValue) &&
  	  (this.structuredDataPropertyContextUUID == that.structuredDataPropertyContextUUID)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("ScalarDataPropertyValueHelper")
object ScalarDataPropertyValueHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "ScalarDataPropertyValues.json"
  
  implicit val w
  : upickle.default.Writer[ScalarDataPropertyValue]
  = upickle.default.macroW[ScalarDataPropertyValue]

  @JSExport
  def toJSON(c: ScalarDataPropertyValue)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[ScalarDataPropertyValue]
  = upickle.default.macroR[ScalarDataPropertyValue]

  @JSExport
  def fromJSON(c: String)
  : ScalarDataPropertyValue
  = upickle.default.read[ScalarDataPropertyValue](c)

}	
