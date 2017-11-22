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
  * @param scalarDataPropertyUUID[1,1]
  * @param scalarPropertyValue[1,1]
  * @param structuredDataPropertyContextUUID[1,1]
  * @param valueTypeUUID[0,1]
  */
case class RestrictionScalarDataPropertyValue
(
  @(JSExport @field) uuid: taggedTypes.RestrictionScalarDataPropertyValueUUID,
  @(JSExport @field) scalarDataPropertyUUID: taggedTypes.DataRelationshipToScalarUUID,
  @(JSExport @field) scalarPropertyValue: LiteralValue,
  @(JSExport @field) structuredDataPropertyContextUUID: taggedTypes.RestrictionStructuredDataPropertyContextUUID,
  @(JSExport @field) valueTypeUUID: scala.Option[taggedTypes.DataRangeUUID]
) {
  def this(
    uuid: taggedTypes.RestrictionScalarDataPropertyValueUUID,
    scalarDataPropertyUUID: taggedTypes.DataRelationshipToScalarUUID,
    scalarPropertyValue: LiteralValue,
    structuredDataPropertyContextUUID: taggedTypes.RestrictionStructuredDataPropertyContextUUID)
  = this(
      uuid,
      scalarDataPropertyUUID,
      scalarPropertyValue,
      structuredDataPropertyContextUUID,
      scala.None /* valueTypeUUID */)

  def withValueTypeUUID(l: taggedTypes.DataRangeUUID)	 
  : RestrictionScalarDataPropertyValue
  = copy(valueTypeUUID=scala.Some(l))
  
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    scalarDataPropertyUUID: taggedTypes.DataRelationshipToScalarUUID,
    scalarPropertyValue: LiteralValue,
    structuredDataPropertyContextUUID: taggedTypes.RestrictionStructuredDataPropertyContextUUID)
  = this(
      taggedTypes.restrictionScalarDataPropertyValueUUID(oug.namespaceUUID(
        "RestrictionScalarDataPropertyValue",
        "scalarDataProperty" -> scalarDataPropertyUUID,
        "structuredDataPropertyContext" -> structuredDataPropertyContextUUID).toString),
      scalarDataPropertyUUID,
      scalarPropertyValue,
      structuredDataPropertyContextUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, scalarDataPropertyUUID, scalarPropertyValue, structuredDataPropertyContextUUID, valueTypeUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: RestrictionScalarDataPropertyValue =>
  	  (this.uuid == that.uuid) &&
  	  (this.scalarDataPropertyUUID == that.scalarDataPropertyUUID)  &&
  	  (this.scalarPropertyValue == that.scalarPropertyValue) &&
  	  (this.structuredDataPropertyContextUUID == that.structuredDataPropertyContextUUID)  &&
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

@JSExportTopLevel("RestrictionScalarDataPropertyValueHelper")
object RestrictionScalarDataPropertyValueHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "RestrictionScalarDataPropertyValues.json"

  implicit val decodeRestrictionScalarDataPropertyValue: Decoder[RestrictionScalarDataPropertyValue]
  = Decoder.instance[RestrictionScalarDataPropertyValue] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.RestrictionScalarDataPropertyValueUUID]
    	  scalarDataPropertyUUID <- c.downField("scalarDataPropertyUUID").as[taggedTypes.DataRelationshipToScalarUUID]
    	  scalarPropertyValue <- c.downField("scalarPropertyValue").as[LiteralValue]
    	  structuredDataPropertyContextUUID <- c.downField("structuredDataPropertyContextUUID").as[taggedTypes.RestrictionStructuredDataPropertyContextUUID]
    	  valueTypeUUID <- Decoder.decodeOption(taggedTypes.decodeDataRangeUUID)(c.downField("valueTypeUUID").success.get)
    	} yield RestrictionScalarDataPropertyValue(
    	  uuid,
    	  scalarDataPropertyUUID,
    	  scalarPropertyValue,
    	  structuredDataPropertyContextUUID,
    	  valueTypeUUID
    	)
  }
  
  implicit val encodeRestrictionScalarDataPropertyValue: Encoder[RestrictionScalarDataPropertyValue]
  = new Encoder[RestrictionScalarDataPropertyValue] {
    override final def apply(x: RestrictionScalarDataPropertyValue): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeRestrictionScalarDataPropertyValueUUID(x.uuid)),
    	  ("scalarDataPropertyUUID", taggedTypes.encodeDataRelationshipToScalarUUID(x.scalarDataPropertyUUID)),
    	  ("scalarPropertyValue", LiteralValue.encodeLiteralValue(x.scalarPropertyValue)),
    	  ("structuredDataPropertyContextUUID", taggedTypes.encodeRestrictionStructuredDataPropertyContextUUID(x.structuredDataPropertyContextUUID)),
    	  ("valueTypeUUID", Encoder.encodeOption(taggedTypes.encodeDataRangeUUID).apply(x.valueTypeUUID))
    )
  }

  @JSExport
  def toJSON(c: RestrictionScalarDataPropertyValue)
  : String
  = encodeRestrictionScalarDataPropertyValue(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : RestrictionScalarDataPropertyValue
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeRestrictionScalarDataPropertyValue(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
