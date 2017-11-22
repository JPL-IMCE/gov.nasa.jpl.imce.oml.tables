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
import scala.Predef.ArrowAssoc

/**
  * @param uuid[1,1]
  * @param subjectUUID[1,1]
  * @param propertyUUID[1,1]
  * @param value[1,1]
  */
@JSExportTopLevel("AnnotationPropertyValue")
case class AnnotationPropertyValue
(
  @(JSExport @field) uuid: taggedTypes.AnnotationPropertyValueUUID,
  @(JSExport @field) subjectUUID: taggedTypes.ElementUUID,
  @(JSExport @field) propertyUUID: taggedTypes.AnnotationPropertyUUID,
  @(JSExport @field) value: taggedTypes.StringDataType
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    subjectUUID: taggedTypes.ElementUUID,
    propertyUUID: taggedTypes.AnnotationPropertyUUID,
    value: taggedTypes.StringDataType)
  = this(
      taggedTypes.annotationPropertyValueUUID(oug.namespaceUUID(
        "AnnotationPropertyValue",
        "subject" -> subjectUUID,
        "property" -> propertyUUID).toString),
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
  	  (this.subjectUUID == that.subjectUUID)  &&
  	  (this.propertyUUID == that.propertyUUID)  &&
  	  (this.value == that.value)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("AnnotationPropertyValueHelper")
object AnnotationPropertyValueHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "AnnotationPropertyValues.json"

  implicit val decodeAnnotationPropertyValue: Decoder[AnnotationPropertyValue]
  = Decoder.instance[AnnotationPropertyValue] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.AnnotationPropertyValueUUID]
    	  subjectUUID <- c.downField("subjectUUID").as[taggedTypes.ElementUUID]
    	  propertyUUID <- c.downField("propertyUUID").as[taggedTypes.AnnotationPropertyUUID]
    	  value <- c.downField("value").as[taggedTypes.StringDataType]
    	} yield AnnotationPropertyValue(
    	  uuid,
    	  subjectUUID,
    	  propertyUUID,
    	  value
    	)
  }
  
  implicit val encodeAnnotationPropertyValue: Encoder[AnnotationPropertyValue]
  = new Encoder[AnnotationPropertyValue] {
    override final def apply(x: AnnotationPropertyValue): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeAnnotationPropertyValueUUID(x.uuid)),
    	  ("subjectUUID", taggedTypes.encodeElementUUID(x.subjectUUID)),
    	  ("propertyUUID", taggedTypes.encodeAnnotationPropertyUUID(x.propertyUUID)),
    	  ("value", taggedTypes.encodeStringDataType(x.value))
    )
  }

  @JSExport
  def toJSON(c: AnnotationPropertyValue)
  : String
  = encodeAnnotationPropertyValue(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : AnnotationPropertyValue
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeAnnotationPropertyValue(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
