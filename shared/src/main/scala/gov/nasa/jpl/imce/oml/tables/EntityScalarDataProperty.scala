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
@JSExportTopLevel("EntityScalarDataProperty")
case class EntityScalarDataProperty
(
  @(JSExport @field) override val uuid: taggedTypes.EntityScalarDataPropertyUUID,
  @(JSExport @field) override val tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) override val domainUUID: taggedTypes.EntityUUID,
  @(JSExport @field) override val rangeUUID: taggedTypes.DataRangeUUID,
  @(JSExport @field) override val isIdentityCriteria: scala.Boolean,
  @(JSExport @field) override val name: taggedTypes.LocalName
) extends DataRelationship with DataRelationshipFromEntity with DataRelationshipToScalar {
  // Ctor(uuidWithGenerator)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    domainUUID: taggedTypes.EntityUUID,
    rangeUUID: taggedTypes.DataRangeUUID,
    isIdentityCriteria: scala.Boolean,
    name: taggedTypes.LocalName)
  = this(
      taggedTypes.entityScalarDataPropertyUUID(oug.namespaceUUID(
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
  	case that: EntityScalarDataProperty =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.domainUUID == that.domainUUID)  &&
  	  (this.rangeUUID == that.rangeUUID)  &&
  	  (this.isIdentityCriteria == that.isIdentityCriteria) &&
  	  (this.name == that.name)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("EntityScalarDataPropertyHelper")
object EntityScalarDataPropertyHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "EntityScalarDataProperties.json"

  implicit val decodeEntityScalarDataProperty: Decoder[EntityScalarDataProperty]
  = Decoder.instance[EntityScalarDataProperty] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.EntityScalarDataPropertyUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  domainUUID <- c.downField("domainUUID").as[taggedTypes.EntityUUID]
    	  rangeUUID <- c.downField("rangeUUID").as[taggedTypes.DataRangeUUID]
    	  isIdentityCriteria <- c.downField("isIdentityCriteria").as[scala.Boolean]
    	  name <- c.downField("name").as[taggedTypes.LocalName]
    	} yield EntityScalarDataProperty(
    	  uuid,
    	  tboxUUID,
    	  domainUUID,
    	  rangeUUID,
    	  isIdentityCriteria,
    	  name
    	)
  }
  
  implicit val encodeEntityScalarDataProperty: Encoder[EntityScalarDataProperty]
  = new Encoder[EntityScalarDataProperty] {
    override final def apply(x: EntityScalarDataProperty): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeEntityScalarDataPropertyUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("domainUUID", taggedTypes.encodeEntityUUID(x.domainUUID)),
    	  ("rangeUUID", taggedTypes.encodeDataRangeUUID(x.rangeUUID)),
    	  ("isIdentityCriteria", Encoder.encodeBoolean(x.isIdentityCriteria)),
    	  ("name", taggedTypes.encodeLocalName(x.name))
    )
  }

  @JSExport
  def toJSON(c: EntityScalarDataProperty)
  : String
  = encodeEntityScalarDataProperty(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : EntityScalarDataProperty
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeEntityScalarDataProperty(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
