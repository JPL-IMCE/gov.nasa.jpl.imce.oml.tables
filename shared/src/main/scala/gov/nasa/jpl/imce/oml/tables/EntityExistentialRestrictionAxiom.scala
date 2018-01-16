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
  * @param restrictedDomainUUID[1,1]
  * @param restrictedRangeUUID[1,1]
  * @param restrictedRelationshipUUID[1,1]
  */
@JSExportTopLevel("EntityExistentialRestrictionAxiom")
case class EntityExistentialRestrictionAxiom
(
  @(JSExport @field) override val uuid: taggedTypes.EntityExistentialRestrictionAxiomUUID,
  @(JSExport @field) override val tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) override val restrictedDomainUUID: taggedTypes.EntityUUID,
  @(JSExport @field) override val restrictedRangeUUID: taggedTypes.EntityUUID,
  @(JSExport @field) override val restrictedRelationshipUUID: taggedTypes.RestrictableRelationshipUUID
) extends EntityRestrictionAxiom {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    restrictedDomainUUID: taggedTypes.EntityUUID,
    restrictedRangeUUID: taggedTypes.EntityUUID,
    restrictedRelationshipUUID: taggedTypes.RestrictableRelationshipUUID)
  = this(
      taggedTypes.entityExistentialRestrictionAxiomUUID(oug.namespaceUUID(
        "EntityExistentialRestrictionAxiom",
        "tbox" -> tboxUUID,
        "restrictedDomain" -> restrictedDomainUUID,
        "restrictedRange" -> restrictedRangeUUID,
        "restrictedRelationship" -> restrictedRelationshipUUID).toString),
      tboxUUID,
      restrictedDomainUUID,
      restrictedRangeUUID,
      restrictedRelationshipUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, restrictedDomainUUID, restrictedRangeUUID, restrictedRelationshipUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: EntityExistentialRestrictionAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.restrictedDomainUUID == that.restrictedDomainUUID)  &&
  	  (this.restrictedRangeUUID == that.restrictedRangeUUID)  &&
  	  (this.restrictedRelationshipUUID == that.restrictedRelationshipUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("EntityExistentialRestrictionAxiomHelper")
object EntityExistentialRestrictionAxiomHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "EntityExistentialRestrictionAxioms.json"

  implicit val decodeEntityExistentialRestrictionAxiom: Decoder[EntityExistentialRestrictionAxiom]
  = Decoder.instance[EntityExistentialRestrictionAxiom] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.EntityExistentialRestrictionAxiomUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  restrictedDomainUUID <- c.downField("restrictedDomainUUID").as[taggedTypes.EntityUUID]
    	  restrictedRangeUUID <- c.downField("restrictedRangeUUID").as[taggedTypes.EntityUUID]
    	  restrictedRelationshipUUID <- c.downField("restrictedRelationshipUUID").as[taggedTypes.RestrictableRelationshipUUID]
    	} yield EntityExistentialRestrictionAxiom(
    	  uuid,
    	  tboxUUID,
    	  restrictedDomainUUID,
    	  restrictedRangeUUID,
    	  restrictedRelationshipUUID
    	)
  }
  
  implicit val encodeEntityExistentialRestrictionAxiom: Encoder[EntityExistentialRestrictionAxiom]
  = new Encoder[EntityExistentialRestrictionAxiom] {
    override final def apply(x: EntityExistentialRestrictionAxiom): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeEntityExistentialRestrictionAxiomUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("restrictedDomainUUID", taggedTypes.encodeEntityUUID(x.restrictedDomainUUID)),
    	  ("restrictedRangeUUID", taggedTypes.encodeEntityUUID(x.restrictedRangeUUID)),
    	  ("restrictedRelationshipUUID", taggedTypes.encodeRestrictableRelationshipUUID(x.restrictedRelationshipUUID))
    )
  }

  @JSExport
  def toJSON(c: EntityExistentialRestrictionAxiom)
  : String
  = encodeEntityExistentialRestrictionAxiom(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : EntityExistentialRestrictionAxiom
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeEntityExistentialRestrictionAxiom(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
