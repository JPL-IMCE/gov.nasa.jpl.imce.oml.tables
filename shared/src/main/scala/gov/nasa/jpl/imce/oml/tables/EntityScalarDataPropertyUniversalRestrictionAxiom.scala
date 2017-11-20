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
  * @param restrictedEntityUUID[1,1]
  * @param scalarPropertyUUID[1,1]
  * @param scalarRestrictionUUID[1,1]
  */
@JSExportTopLevel("EntityScalarDataPropertyUniversalRestrictionAxiom")
case class EntityScalarDataPropertyUniversalRestrictionAxiom
(
  @(JSExport @field) uuid: taggedTypes.EntityScalarDataPropertyUniversalRestrictionAxiomUUID,
  @(JSExport @field) tboxUUID: taggedTypes.TerminologyBoxXRef,
  @(JSExport @field) restrictedEntityUUID: taggedTypes.EntityXRef,
  @(JSExport @field) scalarPropertyUUID: taggedTypes.EntityScalarDataPropertyXRef,
  @(JSExport @field) scalarRestrictionUUID: taggedTypes.DataRangeXRef
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxXRef,
    restrictedEntityUUID: taggedTypes.EntityXRef,
    scalarPropertyUUID: taggedTypes.EntityScalarDataPropertyXRef,
    scalarRestrictionUUID: taggedTypes.DataRangeXRef)
  = this(
      taggedTypes.entityScalarDataPropertyUniversalRestrictionAxiomUUID(oug.namespaceUUID(
        "EntityScalarDataPropertyUniversalRestrictionAxiom",
        "tbox" -> tboxUUID,
        "restrictedEntity" -> restrictedEntityUUID,
        "scalarProperty" -> scalarPropertyUUID,
        "scalarRestriction" -> scalarRestrictionUUID).toString),
      tboxUUID,
      restrictedEntityUUID,
      scalarPropertyUUID,
      scalarRestrictionUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, restrictedEntityUUID, scalarPropertyUUID, scalarRestrictionUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: EntityScalarDataPropertyUniversalRestrictionAxiom =>
  	  (this.uuid == that.uuid) &&
  	  gov.nasa.jpl.imce.oml.covariantTag.compareTaggedValues(this.tboxUUID, that.tboxUUID)  &&
  	  gov.nasa.jpl.imce.oml.covariantTag.compareTaggedValues(this.restrictedEntityUUID, that.restrictedEntityUUID)  &&
  	  gov.nasa.jpl.imce.oml.covariantTag.compareTaggedValues(this.scalarPropertyUUID, that.scalarPropertyUUID)  &&
  	  gov.nasa.jpl.imce.oml.covariantTag.compareTaggedValues(this.scalarRestrictionUUID, that.scalarRestrictionUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("EntityScalarDataPropertyUniversalRestrictionAxiomHelper")
object EntityScalarDataPropertyUniversalRestrictionAxiomHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "EntityScalarDataPropertyUniversalRestrictionAxioms.json"

  implicit val decodeEntityScalarDataPropertyUniversalRestrictionAxiom: Decoder[EntityScalarDataPropertyUniversalRestrictionAxiom]
  = Decoder.instance[EntityScalarDataPropertyUniversalRestrictionAxiom] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.EntityScalarDataPropertyUniversalRestrictionAxiomUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  restrictedEntityUUID <- c.downField("restrictedEntityUUID").as[taggedTypes.EntityUUID]
    	  scalarPropertyUUID <- c.downField("scalarPropertyUUID").as[taggedTypes.EntityScalarDataPropertyUUID]
    	  scalarRestrictionUUID <- c.downField("scalarRestrictionUUID").as[taggedTypes.DataRangeUUID]
    	} yield EntityScalarDataPropertyUniversalRestrictionAxiom(
    	  uuid,
    	  tboxUUID,
    	  restrictedEntityUUID,
    	  scalarPropertyUUID,
    	  scalarRestrictionUUID
    	)
  }
  
  implicit val encodeEntityScalarDataPropertyUniversalRestrictionAxiom: Encoder[EntityScalarDataPropertyUniversalRestrictionAxiom]
  = new Encoder[EntityScalarDataPropertyUniversalRestrictionAxiom] {
    override final def apply(x: EntityScalarDataPropertyUniversalRestrictionAxiom): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeEntityScalarDataPropertyUniversalRestrictionAxiomUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("restrictedEntityUUID", taggedTypes.encodeEntityUUID(x.restrictedEntityUUID)),
    	  ("scalarPropertyUUID", taggedTypes.encodeEntityScalarDataPropertyUUID(x.scalarPropertyUUID)),
    	  ("scalarRestrictionUUID", taggedTypes.encodeDataRangeUUID(x.scalarRestrictionUUID))
    )
  }

  @JSExport
  def toJSON(c: EntityScalarDataPropertyUniversalRestrictionAxiom)
  : String
  = encodeEntityScalarDataPropertyUniversalRestrictionAxiom(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : EntityScalarDataPropertyUniversalRestrictionAxiom
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeEntityScalarDataPropertyUniversalRestrictionAxiom(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
