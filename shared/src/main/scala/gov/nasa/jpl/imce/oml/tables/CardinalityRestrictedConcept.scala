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
  * @param restrictedRangeUUID[0,1]
  * @param name[1,1]
  * @param restrictedCardinality[1,1]
  * @param restrictedRelationshipUUID[1,1]
  * @param restrictionKind[1,1]
  */
case class CardinalityRestrictedConcept
(
  @(JSExport @field) override val uuid: taggedTypes.CardinalityRestrictedConceptUUID,
  @(JSExport @field) override val tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) val restrictedRangeUUID: scala.Option[taggedTypes.EntityUUID],
  @(JSExport @field) override val name: taggedTypes.LocalName,
  @(JSExport @field) val restrictedCardinality: taggedTypes.PositiveIntegerLiteral,
  @(JSExport @field) val restrictedRelationshipUUID: taggedTypes.RestrictableRelationshipUUID,
  @(JSExport @field) val restrictionKind: CardinalityRestrictionKind
) extends ConceptKind {
  def this(
    uuid: taggedTypes.CardinalityRestrictedConceptUUID,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    name: taggedTypes.LocalName,
    restrictedCardinality: taggedTypes.PositiveIntegerLiteral,
    restrictedRelationshipUUID: taggedTypes.RestrictableRelationshipUUID,
    restrictionKind: CardinalityRestrictionKind)
  = this(
      uuid,
      tboxUUID,
      scala.None /* restrictedRangeUUID */,
      name,
      restrictedCardinality,
      restrictedRelationshipUUID,
      restrictionKind)

  def withRestrictedRangeUUID(l: taggedTypes.EntityUUID)	 
  : CardinalityRestrictedConcept
  = copy(restrictedRangeUUID=scala.Some(l))
  
  // Ctor(uuidWithGenerator)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    name: taggedTypes.LocalName,
    restrictedCardinality: taggedTypes.PositiveIntegerLiteral,
    restrictedRelationshipUUID: taggedTypes.RestrictableRelationshipUUID,
    restrictionKind: CardinalityRestrictionKind)
  = this(
      taggedTypes.cardinalityRestrictedConceptUUID(oug.namespaceUUID(
        tboxUUID,
        "name" -> name).toString),
      tboxUUID,
      name,
      restrictedCardinality,
      restrictedRelationshipUUID,
      restrictionKind)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, restrictedRangeUUID, name, restrictedCardinality, restrictedRelationshipUUID, restrictionKind).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: CardinalityRestrictedConcept =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  ((this.restrictedRangeUUID, that.restrictedRangeUUID) match {
  	      case (scala.Some(t1), scala.Some(t2)) =>
  	        t1 == t2
  	      case (scala.None, scala.None) =>
  	        true
  	      case _ =>
  	        false
  	  }) &&
  	  (this.name == that.name) &&
  	  (this.restrictedCardinality == that.restrictedCardinality) &&
  	  (this.restrictedRelationshipUUID == that.restrictedRelationshipUUID)  &&
  	  (this.restrictionKind == that.restrictionKind)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("CardinalityRestrictedConceptHelper")
object CardinalityRestrictedConceptHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "CardinalityRestrictedConcepts.json"

  implicit val decodeCardinalityRestrictedConcept: Decoder[CardinalityRestrictedConcept]
  = Decoder.instance[CardinalityRestrictedConcept] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.CardinalityRestrictedConceptUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  restrictedRangeUUID <- Decoder.decodeOption(taggedTypes.decodeEntityUUID)(c.downField("restrictedRangeUUID").success.get)
    	  name <- c.downField("name").as[taggedTypes.LocalName]
    	  restrictedCardinality <- c.downField("restrictedCardinality").as[taggedTypes.PositiveIntegerLiteral]
    	  restrictedRelationshipUUID <- c.downField("restrictedRelationshipUUID").as[taggedTypes.RestrictableRelationshipUUID]
    	  restrictionKind <- c.downField("restrictionKind").as[CardinalityRestrictionKind]
    	} yield CardinalityRestrictedConcept(
    	  uuid,
    	  tboxUUID,
    	  restrictedRangeUUID,
    	  name,
    	  restrictedCardinality,
    	  restrictedRelationshipUUID,
    	  restrictionKind
    	)
  }
  
  implicit val encodeCardinalityRestrictedConcept: Encoder[CardinalityRestrictedConcept]
  = new Encoder[CardinalityRestrictedConcept] {
    override final def apply(x: CardinalityRestrictedConcept): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeCardinalityRestrictedConceptUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("restrictedRangeUUID", Encoder.encodeOption(taggedTypes.encodeEntityUUID).apply(x.restrictedRangeUUID)),
    	  ("name", taggedTypes.encodeLocalName(x.name)),
    	  ("restrictedCardinality", taggedTypes.encodePositiveIntegerLiteral(x.restrictedCardinality)),
    	  ("restrictedRelationshipUUID", taggedTypes.encodeRestrictableRelationshipUUID(x.restrictedRelationshipUUID)),
    	  ("restrictionKind", CardinalityRestrictionKind.encodeCardinalityRestrictionKind(x.restrictionKind))
    )
  }

  @JSExport
  def toJSON(c: CardinalityRestrictedConcept)
  : String
  = encodeCardinalityRestrictedConcept(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : CardinalityRestrictedConcept
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeCardinalityRestrictedConcept(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
