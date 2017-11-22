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
  * @param restrictedRangeUUID[1,1]
  * @param minExclusive[0,1]
  * @param minInclusive[0,1]
  * @param maxExclusive[0,1]
  * @param maxInclusive[0,1]
  * @param name[1,1]
  */
case class TimeScalarRestriction
(
  @(JSExport @field) uuid: taggedTypes.TimeScalarRestrictionUUID,
  @(JSExport @field) tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) restrictedRangeUUID: taggedTypes.DataRangeUUID,
  @(JSExport @field) minExclusive: scala.Option[LiteralDateTime],
  @(JSExport @field) minInclusive: scala.Option[LiteralDateTime],
  @(JSExport @field) maxExclusive: scala.Option[LiteralDateTime],
  @(JSExport @field) maxInclusive: scala.Option[LiteralDateTime],
  @(JSExport @field) name: taggedTypes.LocalName
) {
  def this(
    uuid: taggedTypes.TimeScalarRestrictionUUID,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    restrictedRangeUUID: taggedTypes.DataRangeUUID,
    name: taggedTypes.LocalName)
  = this(
      uuid,
      tboxUUID,
      restrictedRangeUUID,
      scala.None /* minExclusive */,
      scala.None /* minInclusive */,
      scala.None /* maxExclusive */,
      scala.None /* maxInclusive */,
      name)

  def withMinExclusive(l: LiteralDateTime)	 
  : TimeScalarRestriction
  = copy(minExclusive=scala.Some(l))
  
  def withMinInclusive(l: LiteralDateTime)	 
  : TimeScalarRestriction
  = copy(minInclusive=scala.Some(l))
  
  def withMaxExclusive(l: LiteralDateTime)	 
  : TimeScalarRestriction
  = copy(maxExclusive=scala.Some(l))
  
  def withMaxInclusive(l: LiteralDateTime)	 
  : TimeScalarRestriction
  = copy(maxInclusive=scala.Some(l))
  
  // Ctor(uuidWithGenerator)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    restrictedRangeUUID: taggedTypes.DataRangeUUID,
    name: taggedTypes.LocalName)
  = this(
      taggedTypes.timeScalarRestrictionUUID(oug.namespaceUUID(
        tboxUUID,
        "name" -> name).toString),
      tboxUUID,
      restrictedRangeUUID,
      name)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, restrictedRangeUUID, minExclusive, minInclusive, maxExclusive, maxInclusive, name).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: TimeScalarRestriction =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.restrictedRangeUUID == that.restrictedRangeUUID)  &&
  	  (this.minExclusive == that.minExclusive) &&
  	  (this.minInclusive == that.minInclusive) &&
  	  (this.maxExclusive == that.maxExclusive) &&
  	  (this.maxInclusive == that.maxInclusive) &&
  	  (this.name == that.name)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("TimeScalarRestrictionHelper")
object TimeScalarRestrictionHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "TimeScalarRestrictions.json"

  implicit val decodeTimeScalarRestriction: Decoder[TimeScalarRestriction]
  = Decoder.instance[TimeScalarRestriction] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.TimeScalarRestrictionUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  restrictedRangeUUID <- c.downField("restrictedRangeUUID").as[taggedTypes.DataRangeUUID]
    	  minExclusive <- Decoder.decodeOption(LiteralDateTime.decodeLiteralDateTime)(c.downField("minExclusive").success.get)
    	  minInclusive <- Decoder.decodeOption(LiteralDateTime.decodeLiteralDateTime)(c.downField("minInclusive").success.get)
    	  maxExclusive <- Decoder.decodeOption(LiteralDateTime.decodeLiteralDateTime)(c.downField("maxExclusive").success.get)
    	  maxInclusive <- Decoder.decodeOption(LiteralDateTime.decodeLiteralDateTime)(c.downField("maxInclusive").success.get)
    	  name <- c.downField("name").as[taggedTypes.LocalName]
    	} yield TimeScalarRestriction(
    	  uuid,
    	  tboxUUID,
    	  restrictedRangeUUID,
    	  minExclusive,
    	  minInclusive,
    	  maxExclusive,
    	  maxInclusive,
    	  name
    	)
  }
  
  implicit val encodeTimeScalarRestriction: Encoder[TimeScalarRestriction]
  = new Encoder[TimeScalarRestriction] {
    override final def apply(x: TimeScalarRestriction): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeTimeScalarRestrictionUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("restrictedRangeUUID", taggedTypes.encodeDataRangeUUID(x.restrictedRangeUUID)),
    	  ("minExclusive", Encoder.encodeOption(LiteralDateTime.encodeLiteralDateTime).apply(x.minExclusive)),
    	  ("minInclusive", Encoder.encodeOption(LiteralDateTime.encodeLiteralDateTime).apply(x.minInclusive)),
    	  ("maxExclusive", Encoder.encodeOption(LiteralDateTime.encodeLiteralDateTime).apply(x.maxExclusive)),
    	  ("maxInclusive", Encoder.encodeOption(LiteralDateTime.encodeLiteralDateTime).apply(x.maxInclusive)),
    	  ("name", taggedTypes.encodeLocalName(x.name))
    )
  }

  @JSExport
  def toJSON(c: TimeScalarRestriction)
  : String
  = encodeTimeScalarRestriction(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : TimeScalarRestriction
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeTimeScalarRestriction(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
