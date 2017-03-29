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
  * @param uuid[0,1]
  * @param tboxUUID[1,1]
  * @param restrictedRangeUUID[1,1]
  * @param minExclusive[0,1]
  * @param minInclusive[0,1]
  * @param maxExclusive[0,1]
  * @param maxInclusive[0,1]
  * @param name[1,1]
  */
case class NumericScalarRestriction
(
  @(JSExport @field) uuid: scala.Option[UUID],
  @(JSExport @field) tboxUUID: UUID,
  @(JSExport @field) restrictedRangeUUID: UUID,
  @(JSExport @field) minExclusive: scala.Option[LexicalNumber],
  @(JSExport @field) minInclusive: scala.Option[LexicalNumber],
  @(JSExport @field) maxExclusive: scala.Option[LexicalNumber],
  @(JSExport @field) maxInclusive: scala.Option[LexicalNumber],
  @(JSExport @field) name: LocalName
) {
  @JSExport
  def this(
    tboxUUID: UUID,
    restrictedRangeUUID: UUID,
    name: LocalName)
  = this(
      None /* uuid */,
      tboxUUID,
      restrictedRangeUUID,
      None /* minExclusive */,
      None /* minInclusive */,
      None /* maxExclusive */,
      None /* maxInclusive */,
      name)

  def withUuid(l: UUID)	 
  : NumericScalarRestriction
  = copy(uuid=Some(l))
  
  def withMinExclusive(l: LexicalNumber)	 
  : NumericScalarRestriction
  = copy(minExclusive=Some(l))
  
  def withMinInclusive(l: LexicalNumber)	 
  : NumericScalarRestriction
  = copy(minInclusive=Some(l))
  
  def withMaxExclusive(l: LexicalNumber)	 
  : NumericScalarRestriction
  = copy(maxExclusive=Some(l))
  
  def withMaxInclusive(l: LexicalNumber)	 
  : NumericScalarRestriction
  = copy(maxInclusive=Some(l))
  
  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, restrictedRangeUUID, minExclusive, minInclusive, maxExclusive, maxInclusive, name).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: NumericScalarRestriction =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID) &&
  	  (this.restrictedRangeUUID == that.restrictedRangeUUID) &&
  	  (this.minExclusive == that.minExclusive) &&
  	  (this.minInclusive == that.minInclusive) &&
  	  (this.maxExclusive == that.maxExclusive) &&
  	  (this.maxInclusive == that.maxInclusive) &&
  	  (this.name == that.name)
    case _ =>
      false
  }
  
}

@JSExport
object NumericScalarRestrictionHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "NumericScalarRestrictions.json"
  
  implicit val w
  : upickle.default.Writer[NumericScalarRestriction]
  = upickle.default.macroW[NumericScalarRestriction]

  @JSExport
  def toJSON(c: NumericScalarRestriction)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[NumericScalarRestriction]
  = upickle.default.macroR[NumericScalarRestriction]

  @JSExport
  def fromJSON(c: String)
  : NumericScalarRestriction
  = upickle.default.read[NumericScalarRestriction](c)

}	