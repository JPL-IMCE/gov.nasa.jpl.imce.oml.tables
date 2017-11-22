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
  * @param tboxUUID[1,1]
  * @param domainUUID[1,1]
  * @param rangeUUID[1,1]
  * @param name[1,1]
  */
@JSExportTopLevel("StructuredDataProperty")
case class StructuredDataProperty
(
  @(JSExport @field) override val uuid: taggedTypes.StructuredDataPropertyUUID,
  @(JSExport @field) override val tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) override val domainUUID: taggedTypes.StructureUUID,
  @(JSExport @field) override val rangeUUID: taggedTypes.StructureUUID,
  @(JSExport @field) override val name: taggedTypes.LocalName
) extends DataRelationship with DataRelationshipFromStructure with DataRelationshipToStructure {
  // Ctor(uuidWithGenerator)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    domainUUID: taggedTypes.StructureUUID,
    rangeUUID: taggedTypes.StructureUUID,
    name: taggedTypes.LocalName)
  = this(
      taggedTypes.structuredDataPropertyUUID(oug.namespaceUUID(
        tboxUUID,
        "name" -> name).toString),
      tboxUUID,
      domainUUID,
      rangeUUID,
      name)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, domainUUID, rangeUUID, name).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: StructuredDataProperty =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.domainUUID == that.domainUUID)  &&
  	  (this.rangeUUID == that.rangeUUID)  &&
  	  (this.name == that.name)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("StructuredDataPropertyHelper")
object StructuredDataPropertyHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "StructuredDataProperties.json"

  implicit val decodeStructuredDataProperty: Decoder[StructuredDataProperty]
  = Decoder.instance[StructuredDataProperty] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.StructuredDataPropertyUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  domainUUID <- c.downField("domainUUID").as[taggedTypes.StructureUUID]
    	  rangeUUID <- c.downField("rangeUUID").as[taggedTypes.StructureUUID]
    	  name <- c.downField("name").as[taggedTypes.LocalName]
    	} yield StructuredDataProperty(
    	  uuid,
    	  tboxUUID,
    	  domainUUID,
    	  rangeUUID,
    	  name
    	)
  }
  
  implicit val encodeStructuredDataProperty: Encoder[StructuredDataProperty]
  = new Encoder[StructuredDataProperty] {
    override final def apply(x: StructuredDataProperty): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeStructuredDataPropertyUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("domainUUID", taggedTypes.encodeStructureUUID(x.domainUUID)),
    	  ("rangeUUID", taggedTypes.encodeStructureUUID(x.rangeUUID)),
    	  ("name", taggedTypes.encodeLocalName(x.name))
    )
  }

  @JSExport
  def toJSON(c: StructuredDataProperty)
  : String
  = encodeStructuredDataProperty(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : StructuredDataProperty
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeStructuredDataProperty(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
