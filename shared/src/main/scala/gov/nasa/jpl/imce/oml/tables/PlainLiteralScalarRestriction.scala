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
  * @param langRange[0,1]
  * @param pattern[0,1]
  */
case class PlainLiteralScalarRestriction
(
  @(JSExport @field) override val uuid: taggedTypes.PlainLiteralScalarRestrictionUUID,
  @(JSExport @field) override val tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) override val restrictedRangeUUID: taggedTypes.DataRangeUUID,
  @(JSExport @field) val length: scala.Option[taggedTypes.PositiveIntegerLiteral],
  @(JSExport @field) val minLength: scala.Option[taggedTypes.PositiveIntegerLiteral],
  @(JSExport @field) val maxLength: scala.Option[taggedTypes.PositiveIntegerLiteral],
  @(JSExport @field) override val name: taggedTypes.LocalName,
  @(JSExport @field) val langRange: scala.Option[taggedTypes.LanguageTagDataType],
  @(JSExport @field) val pattern: scala.Option[taggedTypes.LiteralPattern]
) extends RestrictedDataRange {
  def this(
    uuid: taggedTypes.PlainLiteralScalarRestrictionUUID,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    restrictedRangeUUID: taggedTypes.DataRangeUUID,
    name: taggedTypes.LocalName)
  = this(
      uuid,
      tboxUUID,
      restrictedRangeUUID,
      scala.None /* length */,
      scala.None /* minLength */,
      scala.None /* maxLength */,
      name,
      scala.None /* langRange */,
      scala.None /* pattern */)

  def withLength(l: taggedTypes.PositiveIntegerLiteral)	 
  : PlainLiteralScalarRestriction
  = copy(length=scala.Some(l))
  
  def withMinLength(l: taggedTypes.PositiveIntegerLiteral)	 
  : PlainLiteralScalarRestriction
  = copy(minLength=scala.Some(l))
  
  def withMaxLength(l: taggedTypes.PositiveIntegerLiteral)	 
  : PlainLiteralScalarRestriction
  = copy(maxLength=scala.Some(l))
  
  def withLangRange(l: taggedTypes.LanguageTagDataType)	 
  : PlainLiteralScalarRestriction
  = copy(langRange=scala.Some(l))
  
  def withPattern(l: taggedTypes.LiteralPattern)	 
  : PlainLiteralScalarRestriction
  = copy(pattern=scala.Some(l))
  
  // Ctor(uuidWithGenerator)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    restrictedRangeUUID: taggedTypes.DataRangeUUID,
    name: taggedTypes.LocalName)
  = this(
      taggedTypes.plainLiteralScalarRestrictionUUID(oug.namespaceUUID(
        tboxUUID,
        "name" -> name).toString),
      tboxUUID,
      restrictedRangeUUID,
      name)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, restrictedRangeUUID, length, minLength, maxLength, name, langRange, pattern).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: PlainLiteralScalarRestriction =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.restrictedRangeUUID == that.restrictedRangeUUID)  &&
  	  (this.length == that.length) &&
  	  (this.minLength == that.minLength) &&
  	  (this.maxLength == that.maxLength) &&
  	  (this.name == that.name) &&
  	  (this.langRange == that.langRange) &&
  	  (this.pattern == that.pattern)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("PlainLiteralScalarRestrictionHelper")
object PlainLiteralScalarRestrictionHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "PlainLiteralScalarRestrictions.json"

  implicit val decodePlainLiteralScalarRestriction: Decoder[PlainLiteralScalarRestriction]
  = Decoder.instance[PlainLiteralScalarRestriction] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.PlainLiteralScalarRestrictionUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  restrictedRangeUUID <- c.downField("restrictedRangeUUID").as[taggedTypes.DataRangeUUID]
    	  length <- Decoder.decodeOption(taggedTypes.decodePositiveIntegerLiteral)(c.downField("length").success.get)
    	  minLength <- Decoder.decodeOption(taggedTypes.decodePositiveIntegerLiteral)(c.downField("minLength").success.get)
    	  maxLength <- Decoder.decodeOption(taggedTypes.decodePositiveIntegerLiteral)(c.downField("maxLength").success.get)
    	  name <- c.downField("name").as[taggedTypes.LocalName]
    	  langRange <- Decoder.decodeOption(taggedTypes.decodeLanguageTagDataType)(c.downField("langRange").success.get)
    	  pattern <- Decoder.decodeOption(taggedTypes.decodeLiteralPattern)(c.downField("pattern").success.get)
    	} yield PlainLiteralScalarRestriction(
    	  uuid,
    	  tboxUUID,
    	  restrictedRangeUUID,
    	  length,
    	  minLength,
    	  maxLength,
    	  name,
    	  langRange,
    	  pattern
    	)
  }
  
  implicit val encodePlainLiteralScalarRestriction: Encoder[PlainLiteralScalarRestriction]
  = new Encoder[PlainLiteralScalarRestriction] {
    override final def apply(x: PlainLiteralScalarRestriction): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodePlainLiteralScalarRestrictionUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("restrictedRangeUUID", taggedTypes.encodeDataRangeUUID(x.restrictedRangeUUID)),
    	  ("length", Encoder.encodeOption(taggedTypes.encodePositiveIntegerLiteral).apply(x.length)),
    	  ("minLength", Encoder.encodeOption(taggedTypes.encodePositiveIntegerLiteral).apply(x.minLength)),
    	  ("maxLength", Encoder.encodeOption(taggedTypes.encodePositiveIntegerLiteral).apply(x.maxLength)),
    	  ("name", taggedTypes.encodeLocalName(x.name)),
    	  ("langRange", Encoder.encodeOption(taggedTypes.encodeLanguageTagDataType).apply(x.langRange)),
    	  ("pattern", Encoder.encodeOption(taggedTypes.encodeLiteralPattern).apply(x.pattern))
    )
  }

  @JSExport
  def toJSON(c: PlainLiteralScalarRestriction)
  : String
  = encodePlainLiteralScalarRestriction(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : PlainLiteralScalarRestriction
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodePlainLiteralScalarRestriction(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
