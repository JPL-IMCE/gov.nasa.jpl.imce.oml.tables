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
  * @param uuid[0,1]
  * @param singletonInstanceUUID[1,1]
  * @param scalarDataPropertyUUID[1,1]
  * @param name[1,1]
  * @param scalarPropertyValue[1,1]
  */
case class ScalarDataPropertyValue
(
  @(JSExport @field) uuid: scala.Option[UUID],
  @(JSExport @field) singletonInstanceUUID: UUID,
  @(JSExport @field) scalarDataPropertyUUID: UUID,
  @(JSExport @field) name: LocalName,
  @(JSExport @field) scalarPropertyValue: scala.Predef.String
) {
  @JSExport
  def this(
    singletonInstanceUUID: UUID,
    scalarDataPropertyUUID: UUID,
    name: LocalName,
    scalarPropertyValue: scala.Predef.String)
  = this(
      None /* uuid */,
      singletonInstanceUUID,
      scalarDataPropertyUUID,
      name,
      scalarPropertyValue)

  def withUuid(l: UUID)	 
  : ScalarDataPropertyValue
  = copy(uuid=Some(l))
  
  override val hashCode
  : scala.Int 
  = (uuid, singletonInstanceUUID, scalarDataPropertyUUID, name, scalarPropertyValue).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ScalarDataPropertyValue =>
  	  (this.uuid == that.uuid) &&
  	  (this.singletonInstanceUUID == that.singletonInstanceUUID) &&
  	  (this.scalarDataPropertyUUID == that.scalarDataPropertyUUID) &&
  	  (this.name == that.name) &&
  	  (this.scalarPropertyValue == that.scalarPropertyValue)
    case _ =>
      false
  }
  
}

@JSExport
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
