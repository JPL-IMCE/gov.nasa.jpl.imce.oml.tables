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
import scala.scalajs.js.annotation.JSExport
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
  * @param pattern[0,1]
  */
case class IRIScalarRestriction
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) tboxUUID: UUID,
  @(JSExport @field) restrictedRangeUUID: UUID,
  @(JSExport @field) length: scala.Option[scala.Int],
  @(JSExport @field) minLength: scala.Option[scala.Int],
  @(JSExport @field) maxLength: scala.Option[scala.Int],
  @(JSExport @field) name: LocalName,
  @(JSExport @field) pattern: scala.Option[Pattern]
) {
  @JSExport
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
      None /* pattern */)

  def withLength(l: scala.Int)	 
  : IRIScalarRestriction
  = copy(length=Some(l))
  
  def withMinLength(l: scala.Int)	 
  : IRIScalarRestriction
  = copy(minLength=Some(l))
  
  def withMaxLength(l: scala.Int)	 
  : IRIScalarRestriction
  = copy(maxLength=Some(l))
  
  def withPattern(l: Pattern)	 
  : IRIScalarRestriction
  = copy(pattern=Some(l))
  
  // Ctor(uuidWithGenerator)   
  @JSExport
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
  = (uuid, tboxUUID, restrictedRangeUUID, length, minLength, maxLength, name, pattern).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: IRIScalarRestriction =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID) &&
  	  (this.restrictedRangeUUID == that.restrictedRangeUUID) &&
  	  (this.length == that.length) &&
  	  (this.minLength == that.minLength) &&
  	  (this.maxLength == that.maxLength) &&
  	  (this.name == that.name) &&
  	  (this.pattern == that.pattern)
    case _ =>
      false
  }
  
}

@JSExport
object IRIScalarRestrictionHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "IRIScalarRestrictions.json"
  
  implicit val w
  : upickle.default.Writer[IRIScalarRestriction]
  = upickle.default.macroW[IRIScalarRestriction]

  @JSExport
  def toJSON(c: IRIScalarRestriction)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[IRIScalarRestriction]
  = upickle.default.macroR[IRIScalarRestriction]

  @JSExport
  def fromJSON(c: String)
  : IRIScalarRestriction
  = upickle.default.read[IRIScalarRestriction](c)

}	
