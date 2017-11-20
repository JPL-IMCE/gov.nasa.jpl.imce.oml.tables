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
  * @param isIdentityCriteria[1,1]
  * @param name[1,1]
  */
@JSExportTopLevel("EntityStructuredDataProperty")
case class EntityStructuredDataProperty
(
  @(JSExport @field) uuid: taggedTypes.EntityStructuredDataPropertyUUID,
  @(JSExport @field) tboxUUID: taggedTypes.TerminologyBoxXRef,
  @(JSExport @field) domainUUID: taggedTypes.EntityXRef,
  @(JSExport @field) rangeUUID: taggedTypes.StructureXRef,
  @(JSExport @field) isIdentityCriteria: scala.Boolean,
  @(JSExport @field) name: taggedTypes.LocalName
) {
  // Ctor(uuidWithGenerator)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxXRef,
    domainUUID: taggedTypes.EntityXRef,
    rangeUUID: taggedTypes.StructureXRef,
    isIdentityCriteria: scala.Boolean,
    name: taggedTypes.LocalName)
  = this(
      taggedTypes.entityStructuredDataPropertyUUID(oug.namespaceUUID(
        tboxUUID,
        "name" -> name).toString),
      tboxUUID,
      domainUUID,
      rangeUUID,
      isIdentityCriteria,
      name)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, domainUUID, rangeUUID, isIdentityCriteria, name).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: EntityStructuredDataProperty =>
  	  (this.uuid == that.uuid) &&
  	  gov.nasa.jpl.imce.oml.covariantTag.compareTaggedValues(this.tboxUUID, that.tboxUUID)  &&
  	  gov.nasa.jpl.imce.oml.covariantTag.compareTaggedValues(this.domainUUID, that.domainUUID)  &&
  	  gov.nasa.jpl.imce.oml.covariantTag.compareTaggedValues(this.rangeUUID, that.rangeUUID)  &&
  	  (this.isIdentityCriteria == that.isIdentityCriteria) &&
  	  (this.name == that.name)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("EntityStructuredDataPropertyHelper")
object EntityStructuredDataPropertyHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "EntityStructuredDataProperties.json"

  implicit val decodeEntityStructuredDataProperty: Decoder[EntityStructuredDataProperty]
  = Decoder.instance[EntityStructuredDataProperty] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.EntityStructuredDataPropertyUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  domainUUID <- c.downField("domainUUID").as[taggedTypes.EntityUUID]
    	  rangeUUID <- c.downField("rangeUUID").as[taggedTypes.StructureUUID]
    	  isIdentityCriteria <- c.downField("isIdentityCriteria").as[scala.Boolean]
    	  name <- c.downField("name").as[taggedTypes.LocalName]
    	} yield EntityStructuredDataProperty(
    	  uuid,
    	  tboxUUID,
    	  domainUUID,
    	  rangeUUID,
    	  isIdentityCriteria,
    	  name
    	)
  }
  
  implicit val encodeEntityStructuredDataProperty: Encoder[EntityStructuredDataProperty]
  = new Encoder[EntityStructuredDataProperty] {
    override final def apply(x: EntityStructuredDataProperty): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeEntityStructuredDataPropertyUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("domainUUID", taggedTypes.encodeEntityUUID(x.domainUUID)),
    	  ("rangeUUID", taggedTypes.encodeStructureUUID(x.rangeUUID)),
    	  ("isIdentityCriteria", Encoder.encodeBoolean(x.isIdentityCriteria)),
    	  ("name", taggedTypes.encodeLocalName(x.name))
    )
  }

  @JSExport
  def toJSON(c: EntityStructuredDataProperty)
  : String
  = encodeEntityStructuredDataProperty(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : EntityStructuredDataProperty
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeEntityStructuredDataProperty(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
