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
 
package gov.nasa.jpl.imce.omf.schema.tables

import scala.annotation.meta.field
import scala.scalajs.js.annotation.JSExport
import scala._
import scala.Predef._

/**
  * @param graphUUID[1,1]
  * @param uuid[1,1]
  * @param maxExclusive[0,1]
  * @param maxInclusive[0,1]
  * @param minExclusive[0,1]
  * @param minInclusive[0,1]
  * @param restrictedScalarUUID[1,1]
  * @param scalarUUID[1,1]
  */
case class NumericScalarRestrictionAxiom
(
 @(JSExport @field) graphUUID: UUID,
 @(JSExport @field) uuid: UUID,
 @(JSExport @field) maxExclusive: scala.Option[LexicalNumber],
 @(JSExport @field) maxInclusive: scala.Option[LexicalNumber],
 @(JSExport @field) minExclusive: scala.Option[LexicalNumber],
 @(JSExport @field) minInclusive: scala.Option[LexicalNumber],
 @(JSExport @field) restrictedScalarUUID: UUID,
 @(JSExport @field) scalarUUID: UUID
) {
	
def this(
	@(JSExport @field) graphUUID: UUID,
	@(JSExport @field) uuid: UUID,
	@(JSExport @field) restrictedScalarUUID: UUID,
	@(JSExport @field) scalarUUID: UUID
) 
= this(
graphUUID,
uuid,
None,
None,
None,
None,
restrictedScalarUUID,
scalarUUID
) 

def withMaxExclusive(l: LexicalNumber)	 
: NumericScalarRestrictionAxiom
= copy(maxExclusive=Some(l))

def withMaxInclusive(l: LexicalNumber)	 
: NumericScalarRestrictionAxiom
= copy(maxInclusive=Some(l))

def withMinExclusive(l: LexicalNumber)	 
: NumericScalarRestrictionAxiom
= copy(minExclusive=Some(l))

def withMinInclusive(l: LexicalNumber)	 
: NumericScalarRestrictionAxiom
= copy(minInclusive=Some(l))

}


@JSExport
object NumericScalarRestrictionAxiomHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "NumericScalarRestrictionAxioms.json"
  
  implicit val w
  : upickle.default.Writer[NumericScalarRestrictionAxiom]
  = upickle.default.macroW[NumericScalarRestrictionAxiom]

  @JSExport
  def toJSON(c: NumericScalarRestrictionAxiom)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[NumericScalarRestrictionAxiom]
  = upickle.default.macroR[NumericScalarRestrictionAxiom]

  @JSExport
  def fromJSON(c: String)
  : NumericScalarRestrictionAxiom
  = upickle.default.read[NumericScalarRestrictionAxiom](c)

}	
