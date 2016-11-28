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
  * @param name[1,1]
  * @param iri[1,1]
  * @param length[0,1]
  * @param maxLength[0,1]
  * @param minLength[0,1]
  * @param pattern[0,1]
  * @param restrictedRangeUUID[1,1]
  */
case class StringScalarRestriction
(
 @(JSExport @field) graphUUID: UUID,
 @(JSExport @field) uuid: UUID,
 @(JSExport @field) name: LocalName,
 @(JSExport @field) iri: IRI,
 @(JSExport @field) length: scala.Option[scala.Int],
 @(JSExport @field) maxLength: scala.Option[scala.Int],
 @(JSExport @field) minLength: scala.Option[scala.Int],
 @(JSExport @field) pattern: scala.Option[Pattern],
 @(JSExport @field) restrictedRangeUUID: UUID
) {

@JSExport
def this(
	graphUUID: UUID,
	uuid: UUID,
	name: LocalName,
	iri: IRI,
	restrictedRangeUUID: UUID
) 
= this(
graphUUID,
uuid,
name,
iri,
None,
None,
None,
None,
restrictedRangeUUID
) 

def withLength(l: scala.Int)	 
: StringScalarRestriction
= copy(length=Some(l))

def withMaxLength(l: scala.Int)	 
: StringScalarRestriction
= copy(maxLength=Some(l))

def withMinLength(l: scala.Int)	 
: StringScalarRestriction
= copy(minLength=Some(l))

def withPattern(l: Pattern)	 
: StringScalarRestriction
= copy(pattern=Some(l))

}


@JSExport
object StringScalarRestrictionHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "StringScalarRestrictions.json"
  
  implicit val w
  : upickle.default.Writer[StringScalarRestriction]
  = upickle.default.macroW[StringScalarRestriction]

  @JSExport
  def toJSON(c: StringScalarRestriction)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[StringScalarRestriction]
  = upickle.default.macroR[StringScalarRestriction]

  @JSExport
  def fromJSON(c: String)
  : StringScalarRestriction
  = upickle.default.read[StringScalarRestriction](c)

}	
