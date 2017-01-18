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

 
package gov.nasa.jpl.imce.oml.specification.tables

import scala.annotation.meta.field
import scala.scalajs.js.annotation.JSExport
import scala._
import scala.Predef._

/**
  * @param graphUUID[1,1]
  * @param uuid[1,1]
  * @param name[1,1]
  * @param maxExclusive[0,1]
  * @param maxInclusive[0,1]
  * @param minExclusive[0,1]
  * @param minInclusive[0,1]
  * @param restrictedRangeUUID[1,1]
  */
case class NumericScalarRestriction
(
  @(JSExport @field) graphUUID: UUID,
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) name: LocalName,
  @(JSExport @field) maxExclusive: scala.Option[LexicalNumber],
  @(JSExport @field) maxInclusive: scala.Option[LexicalNumber],
  @(JSExport @field) minExclusive: scala.Option[LexicalNumber],
  @(JSExport @field) minInclusive: scala.Option[LexicalNumber],
  @(JSExport @field) restrictedRangeUUID: UUID
) {
  @JSExport
  def this(
    graphUUID: UUID,
    uuid: UUID,
    name: LocalName,
    restrictedRangeUUID: UUID)
  = this(
      graphUUID,
      uuid,
      name,
      None /* maxExclusive */,
      None /* maxInclusive */,
      None /* minExclusive */,
      None /* minInclusive */,
      restrictedRangeUUID)

  def withMaxExclusive(l: LexicalNumber)	 
  : NumericScalarRestriction
  = copy(maxExclusive=Some(l))
  
  def withMaxInclusive(l: LexicalNumber)	 
  : NumericScalarRestriction
  = copy(maxInclusive=Some(l))
  
  def withMinExclusive(l: LexicalNumber)	 
  : NumericScalarRestriction
  = copy(minExclusive=Some(l))
  
  def withMinInclusive(l: LexicalNumber)	 
  : NumericScalarRestriction
  = copy(minInclusive=Some(l))
  
  override val hashCode
  : scala.Int 
  = (graphUUID, uuid, name, maxExclusive, maxInclusive, minExclusive, minInclusive, restrictedRangeUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: NumericScalarRestriction =>
  	  (this.graphUUID == that.graphUUID) &&
  	  (this.uuid == that.uuid) &&
  	  (this.name == that.name) &&
  	  (this.maxExclusive == that.maxExclusive) &&
  	  (this.maxInclusive == that.maxInclusive) &&
  	  (this.minExclusive == that.minExclusive) &&
  	  (this.minInclusive == that.minInclusive) &&
  	  (this.restrictedRangeUUID == that.restrictedRangeUUID)
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
