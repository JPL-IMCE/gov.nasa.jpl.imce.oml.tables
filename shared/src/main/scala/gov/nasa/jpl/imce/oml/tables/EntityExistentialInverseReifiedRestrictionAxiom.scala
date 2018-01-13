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
  * @param inversePropertyUUID[1,1]
  * @param restrictedDomainUUID[1,1]
  * @param restrictedRangeUUID[1,1]
  */
@JSExportTopLevel("EntityExistentialInverseReifiedRestrictionAxiom")
case class EntityExistentialInverseReifiedRestrictionAxiom
(
  @(JSExport @field) override val uuid: taggedTypes.EntityExistentialInverseReifiedRestrictionAxiomUUID,
  @(JSExport @field) override val tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) override val inversePropertyUUID: taggedTypes.InversePropertyUUID,
  @(JSExport @field) override val restrictedDomainUUID: taggedTypes.EntityUUID,
  @(JSExport @field) override val restrictedRangeUUID: taggedTypes.EntityUUID
) extends EntityExistentialRestrictionAxiom with EntityInverseReifiedRestrictionAxiom {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    inversePropertyUUID: taggedTypes.InversePropertyUUID,
    restrictedDomainUUID: taggedTypes.EntityUUID,
    restrictedRangeUUID: taggedTypes.EntityUUID)
  = this(
      taggedTypes.entityExistentialInverseReifiedRestrictionAxiomUUID(oug.namespaceUUID(
        "EntityExistentialInverseReifiedRestrictionAxiom",
        "tbox" -> tboxUUID,
        "inverseProperty" -> inversePropertyUUID,
        "restrictedDomain" -> restrictedDomainUUID,
        "restrictedRange" -> restrictedRangeUUID).toString),
      tboxUUID,
      inversePropertyUUID,
      restrictedDomainUUID,
      restrictedRangeUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, inversePropertyUUID, restrictedDomainUUID, restrictedRangeUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: EntityExistentialInverseReifiedRestrictionAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.inversePropertyUUID == that.inversePropertyUUID)  &&
  	  (this.restrictedDomainUUID == that.restrictedDomainUUID)  &&
  	  (this.restrictedRangeUUID == that.restrictedRangeUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("EntityExistentialInverseReifiedRestrictionAxiomHelper")
object EntityExistentialInverseReifiedRestrictionAxiomHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "EntityExistentialInverseReifiedRestrictionAxioms.json"

  implicit val decodeEntityExistentialInverseReifiedRestrictionAxiom: Decoder[EntityExistentialInverseReifiedRestrictionAxiom]
  = Decoder.instance[EntityExistentialInverseReifiedRestrictionAxiom] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.EntityExistentialInverseReifiedRestrictionAxiomUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  inversePropertyUUID <- c.downField("inversePropertyUUID").as[taggedTypes.InversePropertyUUID]
    	  restrictedDomainUUID <- c.downField("restrictedDomainUUID").as[taggedTypes.EntityUUID]
    	  restrictedRangeUUID <- c.downField("restrictedRangeUUID").as[taggedTypes.EntityUUID]
    	} yield EntityExistentialInverseReifiedRestrictionAxiom(
    	  uuid,
    	  tboxUUID,
    	  inversePropertyUUID,
    	  restrictedDomainUUID,
    	  restrictedRangeUUID
    	)
  }
  
  implicit val encodeEntityExistentialInverseReifiedRestrictionAxiom: Encoder[EntityExistentialInverseReifiedRestrictionAxiom]
  = new Encoder[EntityExistentialInverseReifiedRestrictionAxiom] {
    override final def apply(x: EntityExistentialInverseReifiedRestrictionAxiom): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeEntityExistentialInverseReifiedRestrictionAxiomUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("inversePropertyUUID", taggedTypes.encodeInversePropertyUUID(x.inversePropertyUUID)),
    	  ("restrictedDomainUUID", taggedTypes.encodeEntityUUID(x.restrictedDomainUUID)),
    	  ("restrictedRangeUUID", taggedTypes.encodeEntityUUID(x.restrictedRangeUUID))
    )
  }

  @JSExport
  def toJSON(c: EntityExistentialInverseReifiedRestrictionAxiom)
  : String
  = encodeEntityExistentialInverseReifiedRestrictionAxiom(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : EntityExistentialInverseReifiedRestrictionAxiom
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeEntityExistentialInverseReifiedRestrictionAxiom(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
