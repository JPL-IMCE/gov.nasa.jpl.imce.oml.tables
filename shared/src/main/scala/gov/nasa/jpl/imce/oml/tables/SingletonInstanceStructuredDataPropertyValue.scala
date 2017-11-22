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
  * @param descriptionBoxUUID[1,1]
  * @param singletonInstanceUUID[1,1]
  * @param structuredDataPropertyUUID[1,1]
  */
@JSExportTopLevel("SingletonInstanceStructuredDataPropertyValue")
case class SingletonInstanceStructuredDataPropertyValue
(
  @(JSExport @field) uuid: taggedTypes.SingletonInstanceStructuredDataPropertyValueUUID,
  @(JSExport @field) descriptionBoxUUID: taggedTypes.DescriptionBoxUUID,
  @(JSExport @field) singletonInstanceUUID: taggedTypes.ConceptualEntitySingletonInstanceUUID,
  @(JSExport @field) structuredDataPropertyUUID: taggedTypes.DataRelationshipToStructureUUID
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    descriptionBoxUUID: taggedTypes.DescriptionBoxUUID,
    singletonInstanceUUID: taggedTypes.ConceptualEntitySingletonInstanceUUID,
    structuredDataPropertyUUID: taggedTypes.DataRelationshipToStructureUUID)
  = this(
      taggedTypes.singletonInstanceStructuredDataPropertyValueUUID(oug.namespaceUUID(
        "SingletonInstanceStructuredDataPropertyValue",
        "descriptionBox" -> descriptionBoxUUID,
        "singletonInstance" -> singletonInstanceUUID,
        "structuredDataProperty" -> structuredDataPropertyUUID).toString),
      descriptionBoxUUID,
      singletonInstanceUUID,
      structuredDataPropertyUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, descriptionBoxUUID, singletonInstanceUUID, structuredDataPropertyUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: SingletonInstanceStructuredDataPropertyValue =>
  	  (this.uuid == that.uuid) &&
  	  (this.descriptionBoxUUID == that.descriptionBoxUUID)  &&
  	  (this.singletonInstanceUUID == that.singletonInstanceUUID)  &&
  	  (this.structuredDataPropertyUUID == that.structuredDataPropertyUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("SingletonInstanceStructuredDataPropertyValueHelper")
object SingletonInstanceStructuredDataPropertyValueHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "SingletonInstanceStructuredDataPropertyValues.json"

  implicit val decodeSingletonInstanceStructuredDataPropertyValue: Decoder[SingletonInstanceStructuredDataPropertyValue]
  = Decoder.instance[SingletonInstanceStructuredDataPropertyValue] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.SingletonInstanceStructuredDataPropertyValueUUID]
    	  descriptionBoxUUID <- c.downField("descriptionBoxUUID").as[taggedTypes.DescriptionBoxUUID]
    	  singletonInstanceUUID <- c.downField("singletonInstanceUUID").as[taggedTypes.ConceptualEntitySingletonInstanceUUID]
    	  structuredDataPropertyUUID <- c.downField("structuredDataPropertyUUID").as[taggedTypes.DataRelationshipToStructureUUID]
    	} yield SingletonInstanceStructuredDataPropertyValue(
    	  uuid,
    	  descriptionBoxUUID,
    	  singletonInstanceUUID,
    	  structuredDataPropertyUUID
    	)
  }
  
  implicit val encodeSingletonInstanceStructuredDataPropertyValue: Encoder[SingletonInstanceStructuredDataPropertyValue]
  = new Encoder[SingletonInstanceStructuredDataPropertyValue] {
    override final def apply(x: SingletonInstanceStructuredDataPropertyValue): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeSingletonInstanceStructuredDataPropertyValueUUID(x.uuid)),
    	  ("descriptionBoxUUID", taggedTypes.encodeDescriptionBoxUUID(x.descriptionBoxUUID)),
    	  ("singletonInstanceUUID", taggedTypes.encodeConceptualEntitySingletonInstanceUUID(x.singletonInstanceUUID)),
    	  ("structuredDataPropertyUUID", taggedTypes.encodeDataRelationshipToStructureUUID(x.structuredDataPropertyUUID))
    )
  }

  @JSExport
  def toJSON(c: SingletonInstanceStructuredDataPropertyValue)
  : String
  = encodeSingletonInstanceStructuredDataPropertyValue(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : SingletonInstanceStructuredDataPropertyValue
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeSingletonInstanceStructuredDataPropertyValue(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
