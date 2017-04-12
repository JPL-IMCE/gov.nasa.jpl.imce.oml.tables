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
import scala._
import scala.Predef._

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
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) tboxUUID: UUID,
  @(JSExport @field) restrictedRangeUUID: UUID,
  @(JSExport @field) length: scala.Option[scala.Int],
  @(JSExport @field) minLength: scala.Option[scala.Int],
  @(JSExport @field) maxLength: scala.Option[scala.Int],
  @(JSExport @field) name: LocalName,
  @(JSExport @field) langRange: scala.Option[LangRange],
  @(JSExport @field) pattern: scala.Option[Pattern]
) {
  def this(
    uuid: UUID,
    tboxUUID: UUID,
    restrictedRangeUUID: UUID,
    name: LocalName)
  = this(
      uuid,
      tboxUUID,
      restrictedRangeUUID,
      None /* length */,
      None /* minLength */,
      None /* maxLength */,
      name,
      None /* langRange */,
      None /* pattern */)

  def withLength(l: scala.Int)	 
  : PlainLiteralScalarRestriction
  = copy(length=Some(l))
  
  def withMinLength(l: scala.Int)	 
  : PlainLiteralScalarRestriction
  = copy(minLength=Some(l))
  
  def withMaxLength(l: scala.Int)	 
  : PlainLiteralScalarRestriction
  = copy(maxLength=Some(l))
  
  def withLangRange(l: LangRange)	 
  : PlainLiteralScalarRestriction
  = copy(langRange=Some(l))
  
  def withPattern(l: Pattern)	 
  : PlainLiteralScalarRestriction
  = copy(pattern=Some(l))
  
  // Ctor(uuidWithGenerator)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: UUID,
    restrictedRangeUUID: UUID,
    name: LocalName)
  = this(
      oug.namespaceUUID(tboxUUID, "name" -> name).toString,
      tboxUUID,
      restrictedRangeUUID,
      name)

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, restrictedRangeUUID, length, minLength, maxLength, name, langRange, pattern).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: PlainLiteralScalarRestriction =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID) &&
  	  (this.restrictedRangeUUID == that.restrictedRangeUUID) &&
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

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "PlainLiteralScalarRestrictions.json"
  
  implicit val w
  : upickle.default.Writer[PlainLiteralScalarRestriction]
  = upickle.default.macroW[PlainLiteralScalarRestriction]

  @JSExport
  def toJSON(c: PlainLiteralScalarRestriction)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[PlainLiteralScalarRestriction]
  = upickle.default.macroR[PlainLiteralScalarRestriction]

  @JSExport
  def fromJSON(c: String)
  : PlainLiteralScalarRestriction
  = upickle.default.read[PlainLiteralScalarRestriction](c)

}	
