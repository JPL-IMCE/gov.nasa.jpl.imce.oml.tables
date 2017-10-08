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
  * @param subjectUUID[1,1]
  * @param propertyUUID[1,1]
  * @param value[1,1]
  */
@JSExportTopLevel("AnnotationPropertyValue")
case class AnnotationPropertyValue
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) subjectUUID: UUID,
  @(JSExport @field) propertyUUID: UUID,
  @(JSExport @field) value: StringDataType
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    subjectUUID: UUID,
    propertyUUID: UUID,
    value: StringDataType)
  = this(
      oug.namespaceUUID(
        "AnnotationPropertyValue",
        "subject" -> subjectUUID,
        "property" -> propertyUUID).toString,
      subjectUUID,
      propertyUUID,
      value)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, subjectUUID, propertyUUID, value).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: AnnotationPropertyValue =>
  	  (this.uuid == that.uuid) &&
  	  (this.subjectUUID == that.subjectUUID) &&
  	  (this.propertyUUID == that.propertyUUID) &&
  	  (this.value == that.value)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("AnnotationPropertyValueHelper")
object AnnotationPropertyValueHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "AnnotationPropertyValues.json"
  
  implicit val w
  : upickle.default.Writer[AnnotationPropertyValue]
  = upickle.default.macroW[AnnotationPropertyValue]

  @JSExport
  def toJSON(c: AnnotationPropertyValue)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[AnnotationPropertyValue]
  = upickle.default.macroR[AnnotationPropertyValue]

  @JSExport
  def fromJSON(c: String)
  : AnnotationPropertyValue
  = upickle.default.read[AnnotationPropertyValue](c)

}	
