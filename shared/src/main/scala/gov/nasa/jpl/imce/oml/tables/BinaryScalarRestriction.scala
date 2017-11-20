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
  * @param length[0,1]
  * @param minLength[0,1]
  * @param maxLength[0,1]
  * @param name[1,1]
  */
case class BinaryScalarRestriction
(
  @(JSExport @field) uuid: taggedTypes.BinaryScalarRestrictionUUID,
  @(JSExport @field) tboxUUID: taggedTypes.TerminologyBoxXRef,
  @(JSExport @field) restrictedRangeUUID: taggedTypes.DataRangeXRef,
  @(JSExport @field) length: scala.Option[taggedTypes.PositiveIntegerLiteral],
  @(JSExport @field) minLength: scala.Option[taggedTypes.PositiveIntegerLiteral],
  @(JSExport @field) maxLength: scala.Option[taggedTypes.PositiveIntegerLiteral],
  @(JSExport @field) name: taggedTypes.LocalName
) {
  def this(
    uuid: taggedTypes.BinaryScalarRestrictionUUID,
    tboxUUID: taggedTypes.TerminologyBoxXRef,
    restrictedRangeUUID: taggedTypes.DataRangeXRef,
    name: taggedTypes.LocalName)
  = this(
      uuid,
      tboxUUID,
      restrictedRangeUUID,
      scala.None /* length */,
      scala.None /* minLength */,
      scala.None /* maxLength */,
      name)

  def withLength(l: taggedTypes.PositiveIntegerLiteral)	 
  : BinaryScalarRestriction
  = copy(length=scala.Some(l))
  
  def withMinLength(l: taggedTypes.PositiveIntegerLiteral)	 
  : BinaryScalarRestriction
  = copy(minLength=scala.Some(l))
  
  def withMaxLength(l: taggedTypes.PositiveIntegerLiteral)	 
  : BinaryScalarRestriction
  = copy(maxLength=scala.Some(l))
  
  // Ctor(uuidWithGenerator)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxXRef,
    restrictedRangeUUID: taggedTypes.DataRangeXRef,
    name: taggedTypes.LocalName)
  = this(
      taggedTypes.binaryScalarRestrictionUUID(oug.namespaceUUID(
        tboxUUID,
        "name" -> name).toString),
      tboxUUID,
      restrictedRangeUUID,
      name)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, restrictedRangeUUID, length, minLength, maxLength, name).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: BinaryScalarRestriction =>
  	  (this.uuid == that.uuid) &&
  	  gov.nasa.jpl.imce.oml.covariantTag.compareTaggedValues(this.tboxUUID, that.tboxUUID)  &&
  	  gov.nasa.jpl.imce.oml.covariantTag.compareTaggedValues(this.restrictedRangeUUID, that.restrictedRangeUUID)  &&
  	  (this.length == that.length) &&
  	  (this.minLength == that.minLength) &&
  	  (this.maxLength == that.maxLength) &&
  	  (this.name == that.name)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("BinaryScalarRestrictionHelper")
object BinaryScalarRestrictionHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "BinaryScalarRestrictions.json"

  implicit val decodeBinaryScalarRestriction: Decoder[BinaryScalarRestriction]
  = Decoder.instance[BinaryScalarRestriction] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.BinaryScalarRestrictionUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  restrictedRangeUUID <- c.downField("restrictedRangeUUID").as[taggedTypes.DataRangeUUID]
    	  length <- Decoder.decodeOption(taggedTypes.decodePositiveIntegerLiteral)(c.downField("length").success.get)
    	  minLength <- Decoder.decodeOption(taggedTypes.decodePositiveIntegerLiteral)(c.downField("minLength").success.get)
    	  maxLength <- Decoder.decodeOption(taggedTypes.decodePositiveIntegerLiteral)(c.downField("maxLength").success.get)
    	  name <- c.downField("name").as[taggedTypes.LocalName]
    	} yield BinaryScalarRestriction(
    	  uuid,
    	  tboxUUID,
    	  restrictedRangeUUID,
    	  length,
    	  minLength,
    	  maxLength,
    	  name
    	)
  }
  
  implicit val encodeBinaryScalarRestriction: Encoder[BinaryScalarRestriction]
  = new Encoder[BinaryScalarRestriction] {
    override final def apply(x: BinaryScalarRestriction): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeBinaryScalarRestrictionUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("restrictedRangeUUID", taggedTypes.encodeDataRangeUUID(x.restrictedRangeUUID)),
    	  ("length", Encoder.encodeOption(taggedTypes.encodePositiveIntegerLiteral).apply(x.length)),
    	  ("minLength", Encoder.encodeOption(taggedTypes.encodePositiveIntegerLiteral).apply(x.minLength)),
    	  ("maxLength", Encoder.encodeOption(taggedTypes.encodePositiveIntegerLiteral).apply(x.maxLength)),
    	  ("name", taggedTypes.encodeLocalName(x.name))
    )
  }

  @JSExport
  def toJSON(c: BinaryScalarRestriction)
  : String
  = encodeBinaryScalarRestriction(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : BinaryScalarRestriction
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeBinaryScalarRestriction(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
