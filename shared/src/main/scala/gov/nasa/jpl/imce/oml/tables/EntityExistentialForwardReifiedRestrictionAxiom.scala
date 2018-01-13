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
  * @param forwardPropertyUUID[1,1]
  * @param restrictedDomainUUID[1,1]
  * @param restrictedRangeUUID[1,1]
  */
@JSExportTopLevel("EntityExistentialForwardReifiedRestrictionAxiom")
case class EntityExistentialForwardReifiedRestrictionAxiom
(
  @(JSExport @field) override val uuid: taggedTypes.EntityExistentialForwardReifiedRestrictionAxiomUUID,
  @(JSExport @field) override val tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) override val forwardPropertyUUID: taggedTypes.ForwardPropertyUUID,
  @(JSExport @field) override val restrictedDomainUUID: taggedTypes.EntityUUID,
  @(JSExport @field) override val restrictedRangeUUID: taggedTypes.EntityUUID
) extends EntityExistentialRestrictionAxiom with EntityForwardReifiedRestrictionAxiom {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    forwardPropertyUUID: taggedTypes.ForwardPropertyUUID,
    restrictedDomainUUID: taggedTypes.EntityUUID,
    restrictedRangeUUID: taggedTypes.EntityUUID)
  = this(
      taggedTypes.entityExistentialForwardReifiedRestrictionAxiomUUID(oug.namespaceUUID(
        "EntityExistentialForwardReifiedRestrictionAxiom",
        "tbox" -> tboxUUID,
        "forwardProperty" -> forwardPropertyUUID,
        "restrictedDomain" -> restrictedDomainUUID,
        "restrictedRange" -> restrictedRangeUUID).toString),
      tboxUUID,
      forwardPropertyUUID,
      restrictedDomainUUID,
      restrictedRangeUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, forwardPropertyUUID, restrictedDomainUUID, restrictedRangeUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: EntityExistentialForwardReifiedRestrictionAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.forwardPropertyUUID == that.forwardPropertyUUID)  &&
  	  (this.restrictedDomainUUID == that.restrictedDomainUUID)  &&
  	  (this.restrictedRangeUUID == that.restrictedRangeUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("EntityExistentialForwardReifiedRestrictionAxiomHelper")
object EntityExistentialForwardReifiedRestrictionAxiomHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "EntityExistentialForwardReifiedRestrictionAxioms.json"

  implicit val decodeEntityExistentialForwardReifiedRestrictionAxiom: Decoder[EntityExistentialForwardReifiedRestrictionAxiom]
  = Decoder.instance[EntityExistentialForwardReifiedRestrictionAxiom] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.EntityExistentialForwardReifiedRestrictionAxiomUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  forwardPropertyUUID <- c.downField("forwardPropertyUUID").as[taggedTypes.ForwardPropertyUUID]
    	  restrictedDomainUUID <- c.downField("restrictedDomainUUID").as[taggedTypes.EntityUUID]
    	  restrictedRangeUUID <- c.downField("restrictedRangeUUID").as[taggedTypes.EntityUUID]
    	} yield EntityExistentialForwardReifiedRestrictionAxiom(
    	  uuid,
    	  tboxUUID,
    	  forwardPropertyUUID,
    	  restrictedDomainUUID,
    	  restrictedRangeUUID
    	)
  }
  
  implicit val encodeEntityExistentialForwardReifiedRestrictionAxiom: Encoder[EntityExistentialForwardReifiedRestrictionAxiom]
  = new Encoder[EntityExistentialForwardReifiedRestrictionAxiom] {
    override final def apply(x: EntityExistentialForwardReifiedRestrictionAxiom): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeEntityExistentialForwardReifiedRestrictionAxiomUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("forwardPropertyUUID", taggedTypes.encodeForwardPropertyUUID(x.forwardPropertyUUID)),
    	  ("restrictedDomainUUID", taggedTypes.encodeEntityUUID(x.restrictedDomainUUID)),
    	  ("restrictedRangeUUID", taggedTypes.encodeEntityUUID(x.restrictedRangeUUID))
    )
  }

  @JSExport
  def toJSON(c: EntityExistentialForwardReifiedRestrictionAxiom)
  : String
  = encodeEntityExistentialForwardReifiedRestrictionAxiom(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : EntityExistentialForwardReifiedRestrictionAxiom
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeEntityExistentialForwardReifiedRestrictionAxiom(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
