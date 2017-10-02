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
  * @param singletonInstanceUUID[1,1]
  * @param scalarDataPropertyUUID[1,1]
  * @param scalarPropertyValue[1,1]
  * @param valueTypeUUID[0,1]
  */
case class SingletonInstanceScalarDataPropertyValue
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) descriptionBoxUUID: UUID,
  @(JSExport @field) singletonInstanceUUID: UUID,
  @(JSExport @field) scalarDataPropertyUUID: UUID,
  @(JSExport @field) scalarPropertyValue: LiteralValue,
  @(JSExport @field) valueTypeUUID: scala.Option[UUID]
) {
  def this(
    uuid: UUID,
    descriptionBoxUUID: UUID,
    singletonInstanceUUID: UUID,
    scalarDataPropertyUUID: UUID,
    scalarPropertyValue: LiteralValue)
  = this(
      uuid,
      descriptionBoxUUID,
      singletonInstanceUUID,
      scalarDataPropertyUUID,
      scalarPropertyValue,
      None /* valueTypeUUID */)

  def withValueTypeUUID(l: UUID)	 
  : SingletonInstanceScalarDataPropertyValue
  = copy(valueTypeUUID=Some(l))
  
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    descriptionBoxUUID: UUID,
    singletonInstanceUUID: UUID,
    scalarDataPropertyUUID: UUID,
    scalarPropertyValue: LiteralValue)
  = this(
      oug.namespaceUUID(
        "SingletonInstanceScalarDataPropertyValue",
        "descriptionBox" -> descriptionBoxUUID,
        "singletonInstance" -> singletonInstanceUUID,
        "scalarDataProperty" -> scalarDataPropertyUUID).toString,
      descriptionBoxUUID,
      singletonInstanceUUID,
      scalarDataPropertyUUID,
      scalarPropertyValue)

  override val hashCode
  : scala.Int 
  = (uuid, descriptionBoxUUID, singletonInstanceUUID, scalarDataPropertyUUID, scalarPropertyValue, valueTypeUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: SingletonInstanceScalarDataPropertyValue =>
  	  (this.uuid == that.uuid) &&
  	  (this.descriptionBoxUUID == that.descriptionBoxUUID) &&
  	  (this.singletonInstanceUUID == that.singletonInstanceUUID) &&
  	  (this.scalarDataPropertyUUID == that.scalarDataPropertyUUID) &&
  	  (this.scalarPropertyValue == that.scalarPropertyValue) &&
  	  (this.valueTypeUUID == that.valueTypeUUID)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("SingletonInstanceScalarDataPropertyValueHelper")
object SingletonInstanceScalarDataPropertyValueHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "SingletonInstanceScalarDataPropertyValues.json"
  
  implicit val w
  : upickle.default.Writer[SingletonInstanceScalarDataPropertyValue]
  = upickle.default.macroW[SingletonInstanceScalarDataPropertyValue]

  @JSExport
  def toJSON(c: SingletonInstanceScalarDataPropertyValue)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[SingletonInstanceScalarDataPropertyValue]
  = upickle.default.macroR[SingletonInstanceScalarDataPropertyValue]

  @JSExport
  def fromJSON(c: String)
  : SingletonInstanceScalarDataPropertyValue
  = upickle.default.read[SingletonInstanceScalarDataPropertyValue](c)

}	
