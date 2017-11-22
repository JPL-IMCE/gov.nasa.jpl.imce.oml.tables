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
  * @param literalValue[1,1]
  * @param valueTypeUUID[0,1]
  */
case class EntityScalarDataPropertyParticularRestrictionAxiom
(
  @(JSExport @field) override val uuid: taggedTypes.EntityScalarDataPropertyParticularRestrictionAxiomUUID,
  @(JSExport @field) override val tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) override val restrictedEntityUUID: taggedTypes.EntityUUID,
  @(JSExport @field) override val scalarPropertyUUID: taggedTypes.EntityScalarDataPropertyUUID,
  @(JSExport @field) val literalValue: LiteralValue,
  @(JSExport @field) val valueTypeUUID: scala.Option[taggedTypes.DataRangeUUID]
) extends EntityScalarDataPropertyRestrictionAxiom {
  def this(
    uuid: taggedTypes.EntityScalarDataPropertyParticularRestrictionAxiomUUID,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    restrictedEntityUUID: taggedTypes.EntityUUID,
    scalarPropertyUUID: taggedTypes.EntityScalarDataPropertyUUID,
    literalValue: LiteralValue)
  = this(
      uuid,
      tboxUUID,
      restrictedEntityUUID,
      scalarPropertyUUID,
      literalValue,
      scala.None /* valueTypeUUID */)

  def withValueTypeUUID(l: taggedTypes.DataRangeUUID)	 
  : EntityScalarDataPropertyParticularRestrictionAxiom
  = copy(valueTypeUUID=scala.Some(l))
  
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    restrictedEntityUUID: taggedTypes.EntityUUID,
    scalarPropertyUUID: taggedTypes.EntityScalarDataPropertyUUID,
    literalValue: LiteralValue)
  = this(
      taggedTypes.entityScalarDataPropertyParticularRestrictionAxiomUUID(oug.namespaceUUID(
        "EntityScalarDataPropertyParticularRestrictionAxiom",
        "tbox" -> tboxUUID,
        "restrictedEntity" -> restrictedEntityUUID,
        "scalarProperty" -> scalarPropertyUUID).toString),
      tboxUUID,
      restrictedEntityUUID,
      scalarPropertyUUID,
      literalValue)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, restrictedEntityUUID, scalarPropertyUUID, literalValue, valueTypeUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: EntityScalarDataPropertyParticularRestrictionAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.restrictedEntityUUID == that.restrictedEntityUUID)  &&
  	  (this.scalarPropertyUUID == that.scalarPropertyUUID)  &&
  	  (this.literalValue == that.literalValue) &&
  	  ((this.valueTypeUUID, that.valueTypeUUID) match {
  	      case (scala.Some(t1), scala.Some(t2)) =>
  	        t1 == t2
  	      case (scala.None, scala.None) =>
  	        true
  	      case _ =>
  	        false
  	  })
    case _ =>
      false
  }
  
}

@JSExportTopLevel("EntityScalarDataPropertyParticularRestrictionAxiomHelper")
object EntityScalarDataPropertyParticularRestrictionAxiomHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "EntityScalarDataPropertyParticularRestrictionAxioms.json"

  implicit val decodeEntityScalarDataPropertyParticularRestrictionAxiom: Decoder[EntityScalarDataPropertyParticularRestrictionAxiom]
  = Decoder.instance[EntityScalarDataPropertyParticularRestrictionAxiom] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.EntityScalarDataPropertyParticularRestrictionAxiomUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  restrictedEntityUUID <- c.downField("restrictedEntityUUID").as[taggedTypes.EntityUUID]
    	  scalarPropertyUUID <- c.downField("scalarPropertyUUID").as[taggedTypes.EntityScalarDataPropertyUUID]
    	  literalValue <- c.downField("literalValue").as[LiteralValue]
    	  valueTypeUUID <- Decoder.decodeOption(taggedTypes.decodeDataRangeUUID)(c.downField("valueTypeUUID").success.get)
    	} yield EntityScalarDataPropertyParticularRestrictionAxiom(
    	  uuid,
    	  tboxUUID,
    	  restrictedEntityUUID,
    	  scalarPropertyUUID,
    	  literalValue,
    	  valueTypeUUID
    	)
  }
  
  implicit val encodeEntityScalarDataPropertyParticularRestrictionAxiom: Encoder[EntityScalarDataPropertyParticularRestrictionAxiom]
  = new Encoder[EntityScalarDataPropertyParticularRestrictionAxiom] {
    override final def apply(x: EntityScalarDataPropertyParticularRestrictionAxiom): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeEntityScalarDataPropertyParticularRestrictionAxiomUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("restrictedEntityUUID", taggedTypes.encodeEntityUUID(x.restrictedEntityUUID)),
    	  ("scalarPropertyUUID", taggedTypes.encodeEntityScalarDataPropertyUUID(x.scalarPropertyUUID)),
    	  ("literalValue", LiteralValue.encodeLiteralValue(x.literalValue)),
    	  ("valueTypeUUID", Encoder.encodeOption(taggedTypes.encodeDataRangeUUID).apply(x.valueTypeUUID))
    )
  }

  @JSExport
  def toJSON(c: EntityScalarDataPropertyParticularRestrictionAxiom)
  : String
  = encodeEntityScalarDataPropertyParticularRestrictionAxiom(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : EntityScalarDataPropertyParticularRestrictionAxiom
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeEntityScalarDataPropertyParticularRestrictionAxiom(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
