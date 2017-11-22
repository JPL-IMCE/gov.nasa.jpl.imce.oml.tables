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
  * @param structuredDataPropertyUUID[1,1]
  * @param restrictedEntityUUID[1,1]
  */
@JSExportTopLevel("EntityStructuredDataPropertyParticularRestrictionAxiom")
case class EntityStructuredDataPropertyParticularRestrictionAxiom
(
  @(JSExport @field) override val uuid: taggedTypes.EntityStructuredDataPropertyParticularRestrictionAxiomUUID,
  @(JSExport @field) override val tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) override val structuredDataPropertyUUID: taggedTypes.DataRelationshipToStructureUUID,
  @(JSExport @field) override val restrictedEntityUUID: taggedTypes.EntityUUID
) extends EntityStructuredDataPropertyRestrictionAxiom with RestrictionStructuredDataPropertyContext {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    structuredDataPropertyUUID: taggedTypes.DataRelationshipToStructureUUID,
    restrictedEntityUUID: taggedTypes.EntityUUID)
  = this(
      taggedTypes.entityStructuredDataPropertyParticularRestrictionAxiomUUID(oug.namespaceUUID(
        "EntityStructuredDataPropertyParticularRestrictionAxiom",
        "tbox" -> tboxUUID,
        "structuredDataProperty" -> structuredDataPropertyUUID,
        "restrictedEntity" -> restrictedEntityUUID).toString),
      tboxUUID,
      structuredDataPropertyUUID,
      restrictedEntityUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, structuredDataPropertyUUID, restrictedEntityUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: EntityStructuredDataPropertyParticularRestrictionAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.structuredDataPropertyUUID == that.structuredDataPropertyUUID)  &&
  	  (this.restrictedEntityUUID == that.restrictedEntityUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("EntityStructuredDataPropertyParticularRestrictionAxiomHelper")
object EntityStructuredDataPropertyParticularRestrictionAxiomHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "EntityStructuredDataPropertyParticularRestrictionAxioms.json"

  implicit val decodeEntityStructuredDataPropertyParticularRestrictionAxiom: Decoder[EntityStructuredDataPropertyParticularRestrictionAxiom]
  = Decoder.instance[EntityStructuredDataPropertyParticularRestrictionAxiom] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.EntityStructuredDataPropertyParticularRestrictionAxiomUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  structuredDataPropertyUUID <- c.downField("structuredDataPropertyUUID").as[taggedTypes.DataRelationshipToStructureUUID]
    	  restrictedEntityUUID <- c.downField("restrictedEntityUUID").as[taggedTypes.EntityUUID]
    	} yield EntityStructuredDataPropertyParticularRestrictionAxiom(
    	  uuid,
    	  tboxUUID,
    	  structuredDataPropertyUUID,
    	  restrictedEntityUUID
    	)
  }
  
  implicit val encodeEntityStructuredDataPropertyParticularRestrictionAxiom: Encoder[EntityStructuredDataPropertyParticularRestrictionAxiom]
  = new Encoder[EntityStructuredDataPropertyParticularRestrictionAxiom] {
    override final def apply(x: EntityStructuredDataPropertyParticularRestrictionAxiom): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeEntityStructuredDataPropertyParticularRestrictionAxiomUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("structuredDataPropertyUUID", taggedTypes.encodeDataRelationshipToStructureUUID(x.structuredDataPropertyUUID)),
    	  ("restrictedEntityUUID", taggedTypes.encodeEntityUUID(x.restrictedEntityUUID))
    )
  }

  @JSExport
  def toJSON(c: EntityStructuredDataPropertyParticularRestrictionAxiom)
  : String
  = encodeEntityStructuredDataPropertyParticularRestrictionAxiom(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : EntityStructuredDataPropertyParticularRestrictionAxiom
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeEntityStructuredDataPropertyParticularRestrictionAxiom(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
