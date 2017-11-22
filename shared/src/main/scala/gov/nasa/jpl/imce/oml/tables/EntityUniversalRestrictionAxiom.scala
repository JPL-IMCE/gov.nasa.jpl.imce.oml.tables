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
  * @param restrictedRelationUUID[1,1]
  * @param restrictedDomainUUID[1,1]
  * @param restrictedRangeUUID[1,1]
  */
@JSExportTopLevel("EntityUniversalRestrictionAxiom")
case class EntityUniversalRestrictionAxiom
(
  @(JSExport @field) uuid: taggedTypes.EntityUniversalRestrictionAxiomUUID,
  @(JSExport @field) tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) restrictedRelationUUID: taggedTypes.EntityRelationshipUUID,
  @(JSExport @field) restrictedDomainUUID: taggedTypes.EntityUUID,
  @(JSExport @field) restrictedRangeUUID: taggedTypes.EntityUUID
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    restrictedRelationUUID: taggedTypes.EntityRelationshipUUID,
    restrictedDomainUUID: taggedTypes.EntityUUID,
    restrictedRangeUUID: taggedTypes.EntityUUID)
  = this(
      taggedTypes.entityUniversalRestrictionAxiomUUID(oug.namespaceUUID(
        "EntityUniversalRestrictionAxiom",
        "tbox" -> tboxUUID,
        "restrictedRelation" -> restrictedRelationUUID,
        "restrictedDomain" -> restrictedDomainUUID,
        "restrictedRange" -> restrictedRangeUUID).toString),
      tboxUUID,
      restrictedRelationUUID,
      restrictedDomainUUID,
      restrictedRangeUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, restrictedRelationUUID, restrictedDomainUUID, restrictedRangeUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: EntityUniversalRestrictionAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.restrictedRelationUUID == that.restrictedRelationUUID)  &&
  	  (this.restrictedDomainUUID == that.restrictedDomainUUID)  &&
  	  (this.restrictedRangeUUID == that.restrictedRangeUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("EntityUniversalRestrictionAxiomHelper")
object EntityUniversalRestrictionAxiomHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "EntityUniversalRestrictionAxioms.json"

  implicit val decodeEntityUniversalRestrictionAxiom: Decoder[EntityUniversalRestrictionAxiom]
  = Decoder.instance[EntityUniversalRestrictionAxiom] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.EntityUniversalRestrictionAxiomUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  restrictedRelationUUID <- c.downField("restrictedRelationUUID").as[taggedTypes.EntityRelationshipUUID]
    	  restrictedDomainUUID <- c.downField("restrictedDomainUUID").as[taggedTypes.EntityUUID]
    	  restrictedRangeUUID <- c.downField("restrictedRangeUUID").as[taggedTypes.EntityUUID]
    	} yield EntityUniversalRestrictionAxiom(
    	  uuid,
    	  tboxUUID,
    	  restrictedRelationUUID,
    	  restrictedDomainUUID,
    	  restrictedRangeUUID
    	)
  }
  
  implicit val encodeEntityUniversalRestrictionAxiom: Encoder[EntityUniversalRestrictionAxiom]
  = new Encoder[EntityUniversalRestrictionAxiom] {
    override final def apply(x: EntityUniversalRestrictionAxiom): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeEntityUniversalRestrictionAxiomUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("restrictedRelationUUID", taggedTypes.encodeEntityRelationshipUUID(x.restrictedRelationUUID)),
    	  ("restrictedDomainUUID", taggedTypes.encodeEntityUUID(x.restrictedDomainUUID)),
    	  ("restrictedRangeUUID", taggedTypes.encodeEntityUUID(x.restrictedRangeUUID))
    )
  }

  @JSExport
  def toJSON(c: EntityUniversalRestrictionAxiom)
  : String
  = encodeEntityUniversalRestrictionAxiom(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : EntityUniversalRestrictionAxiom
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeEntityUniversalRestrictionAxiom(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
