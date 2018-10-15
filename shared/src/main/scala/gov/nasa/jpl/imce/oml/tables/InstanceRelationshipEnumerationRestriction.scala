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
  * @param domainUUID[1,1]
  * @param restrictedRelationshipUUID[1,1]
  */
@JSExportTopLevel("InstanceRelationshipEnumerationRestriction")
case class InstanceRelationshipEnumerationRestriction
(
  @(JSExport @field) override val uuid: taggedTypes.InstanceRelationshipEnumerationRestrictionUUID,
  @(JSExport @field) val descriptionBoxUUID: taggedTypes.DescriptionBoxUUID,
  @(JSExport @field) val domainUUID: taggedTypes.ConceptualEntitySingletonInstanceUUID,
  @(JSExport @field) val restrictedRelationshipUUID: taggedTypes.RestrictableRelationshipUUID
) extends ElementCrossReferenceTuple with TerminologyInstanceAssertion {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    descriptionBoxUUID: taggedTypes.DescriptionBoxUUID,
    domainUUID: taggedTypes.ConceptualEntitySingletonInstanceUUID,
    restrictedRelationshipUUID: taggedTypes.RestrictableRelationshipUUID)
  = this(
      taggedTypes.instanceRelationshipEnumerationRestrictionUUID(oug.namespaceUUID(
        "InstanceRelationshipEnumerationRestriction",
        "descriptionBox" -> descriptionBoxUUID,
        "domain" -> domainUUID,
        "restrictedRelationship" -> restrictedRelationshipUUID).toString),
      descriptionBoxUUID,
      domainUUID,
      restrictedRelationshipUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, descriptionBoxUUID, domainUUID, restrictedRelationshipUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: InstanceRelationshipEnumerationRestriction =>
  	  (this.uuid == that.uuid) &&
  	  (this.descriptionBoxUUID == that.descriptionBoxUUID)  &&
  	  (this.domainUUID == that.domainUUID)  &&
  	  (this.restrictedRelationshipUUID == that.restrictedRelationshipUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("InstanceRelationshipEnumerationRestrictionHelper")
object InstanceRelationshipEnumerationRestrictionHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "InstanceRelationshipEnumerationRestrictions.json"

  implicit val decodeInstanceRelationshipEnumerationRestriction: Decoder[InstanceRelationshipEnumerationRestriction]
  = Decoder.instance[InstanceRelationshipEnumerationRestriction] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.InstanceRelationshipEnumerationRestrictionUUID]
    	  descriptionBoxUUID <- c.downField("descriptionBoxUUID").as[taggedTypes.DescriptionBoxUUID]
    	  domainUUID <- c.downField("domainUUID").as[taggedTypes.ConceptualEntitySingletonInstanceUUID]
    	  restrictedRelationshipUUID <- c.downField("restrictedRelationshipUUID").as[taggedTypes.RestrictableRelationshipUUID]
    	} yield InstanceRelationshipEnumerationRestriction(
    	  uuid,
    	  descriptionBoxUUID,
    	  domainUUID,
    	  restrictedRelationshipUUID
    	)
  }
  
  implicit val encodeInstanceRelationshipEnumerationRestriction: Encoder[InstanceRelationshipEnumerationRestriction]
  = new Encoder[InstanceRelationshipEnumerationRestriction] {
    override final def apply(x: InstanceRelationshipEnumerationRestriction): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeInstanceRelationshipEnumerationRestrictionUUID(x.uuid)),
    	  ("descriptionBoxUUID", taggedTypes.encodeDescriptionBoxUUID(x.descriptionBoxUUID)),
    	  ("domainUUID", taggedTypes.encodeConceptualEntitySingletonInstanceUUID(x.domainUUID)),
    	  ("restrictedRelationshipUUID", taggedTypes.encodeRestrictableRelationshipUUID(x.restrictedRelationshipUUID))
    )
  }

  @JSExport
  def toJSON(c: InstanceRelationshipEnumerationRestriction)
  : String
  = encodeInstanceRelationshipEnumerationRestriction(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : InstanceRelationshipEnumerationRestriction
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeInstanceRelationshipEnumerationRestriction(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
