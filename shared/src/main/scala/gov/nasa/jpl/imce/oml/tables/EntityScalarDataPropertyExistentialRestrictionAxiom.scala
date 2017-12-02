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
@JSExportTopLevel("EntityScalarDataPropertyExistentialRestrictionAxiom")
case class EntityScalarDataPropertyExistentialRestrictionAxiom
(
  @(JSExport @field) override val uuid: taggedTypes.EntityScalarDataPropertyExistentialRestrictionAxiomUUID,
  @(JSExport @field) override val tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) override val restrictedEntityUUID: taggedTypes.EntityUUID,
  @(JSExport @field) override val scalarPropertyUUID: taggedTypes.EntityScalarDataPropertyUUID,
  @(JSExport @field) val scalarRestrictionUUID: taggedTypes.DataRangeUUID
) extends ElementCrossReferenceTuple with EntityScalarDataPropertyRestrictionAxiom {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    restrictedEntityUUID: taggedTypes.EntityUUID,
    scalarPropertyUUID: taggedTypes.EntityScalarDataPropertyUUID,
    scalarRestrictionUUID: taggedTypes.DataRangeUUID)
  = this(
      taggedTypes.entityScalarDataPropertyExistentialRestrictionAxiomUUID(oug.namespaceUUID(
        "EntityScalarDataPropertyExistentialRestrictionAxiom",
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
  	case that: EntityScalarDataPropertyExistentialRestrictionAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.restrictedEntityUUID == that.restrictedEntityUUID)  &&
  	  (this.scalarPropertyUUID == that.scalarPropertyUUID)  &&
  	  (this.scalarRestrictionUUID == that.scalarRestrictionUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("EntityScalarDataPropertyExistentialRestrictionAxiomHelper")
object EntityScalarDataPropertyExistentialRestrictionAxiomHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "EntityScalarDataPropertyExistentialRestrictionAxioms.json"

  implicit val decodeEntityScalarDataPropertyExistentialRestrictionAxiom: Decoder[EntityScalarDataPropertyExistentialRestrictionAxiom]
  = Decoder.instance[EntityScalarDataPropertyExistentialRestrictionAxiom] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.EntityScalarDataPropertyExistentialRestrictionAxiomUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  restrictedEntityUUID <- c.downField("restrictedEntityUUID").as[taggedTypes.EntityUUID]
    	  scalarPropertyUUID <- c.downField("scalarPropertyUUID").as[taggedTypes.EntityScalarDataPropertyUUID]
    	  scalarRestrictionUUID <- c.downField("scalarRestrictionUUID").as[taggedTypes.DataRangeUUID]
    	} yield EntityScalarDataPropertyExistentialRestrictionAxiom(
    	  uuid,
    	  tboxUUID,
    	  restrictedEntityUUID,
    	  scalarPropertyUUID,
    	  scalarRestrictionUUID
    	)
  }
  
  implicit val encodeEntityScalarDataPropertyExistentialRestrictionAxiom: Encoder[EntityScalarDataPropertyExistentialRestrictionAxiom]
  = new Encoder[EntityScalarDataPropertyExistentialRestrictionAxiom] {
    override final def apply(x: EntityScalarDataPropertyExistentialRestrictionAxiom): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeEntityScalarDataPropertyExistentialRestrictionAxiomUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("restrictedEntityUUID", taggedTypes.encodeEntityUUID(x.restrictedEntityUUID)),
    	  ("scalarPropertyUUID", taggedTypes.encodeEntityScalarDataPropertyUUID(x.scalarPropertyUUID)),
    	  ("scalarRestrictionUUID", taggedTypes.encodeDataRangeUUID(x.scalarRestrictionUUID))
    )
  }

  @JSExport
  def toJSON(c: EntityScalarDataPropertyExistentialRestrictionAxiom)
  : String
  = encodeEntityScalarDataPropertyExistentialRestrictionAxiom(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : EntityScalarDataPropertyExistentialRestrictionAxiom
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeEntityScalarDataPropertyExistentialRestrictionAxiom(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
