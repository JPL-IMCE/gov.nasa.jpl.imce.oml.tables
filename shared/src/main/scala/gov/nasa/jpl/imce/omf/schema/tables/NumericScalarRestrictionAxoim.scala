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
  * @param graphUUID
  * @param uuid
  * @param maxExclusive
  * @param maxInclusive
  * @param minExclusive
  * @param minInclusive
  * @param restrictedScalarUUID
  * @param scalarUUID
  */
@JSExport
case class NumericScalarRestrictionAxoim
(
 @(JSExport @field) graphUUID: TerminologyBox,
 @(JSExport @field) uuid: UUID,
 @(JSExport @field) maxExclusive: LexicalNumber,
 @(JSExport @field) maxInclusive: LexicalNumber,
 @(JSExport @field) minExclusive: LexicalNumber,
 @(JSExport @field) minInclusive: LexicalNumber,
 @(JSExport @field) restrictedScalarUUID: Scalar,
 @(JSExport @field) scalarUUID: Scalar
)

@JSExport
object NumericScalarRestrictionAxoimHelper {

  implicit val w
  : upickle.default.Writer[NumericScalarRestrictionAxoim]
  = upickle.default.macroW[NumericScalarRestrictionAxoim]

  @JSExport
  def toJSON(c: NumericScalarRestrictionAxoim)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[NumericScalarRestrictionAxoim]
  = upickle.default.macroR[NumericScalarRestrictionAxoim]

  @JSExport
  def fromJSON(c: String)
  : NumericScalarRestrictionAxoim
  = upickle.default.read[NumericScalarRestrictionAxoim](c)

}	
