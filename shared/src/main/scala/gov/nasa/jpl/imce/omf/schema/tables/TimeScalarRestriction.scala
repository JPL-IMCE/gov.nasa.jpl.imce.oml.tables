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
  * @param maxExclusive[0,1]
  * @param maxInclusive[0,1]
  * @param minExclusive[0,1]
  * @param minInclusive[0,1]
  * @param restrictedRangeUUID[1,1]
  */
case class TimeScalarRestriction
(
 @(JSExport @field) graphUUID: UUID,
 @(JSExport @field) uuid: UUID,
 @(JSExport @field) name: LocalName,
 @(JSExport @field) iri: IRI,
 @(JSExport @field) maxExclusive: scala.Option[LexicalTime],
 @(JSExport @field) maxInclusive: scala.Option[LexicalTime],
 @(JSExport @field) minExclusive: scala.Option[LexicalTime],
 @(JSExport @field) minInclusive: scala.Option[LexicalTime],
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

def withMaxExclusive(l: LexicalTime)	 
: TimeScalarRestriction
= copy(maxExclusive=Some(l))

def withMaxInclusive(l: LexicalTime)	 
: TimeScalarRestriction
= copy(maxInclusive=Some(l))

def withMinExclusive(l: LexicalTime)	 
: TimeScalarRestriction
= copy(minExclusive=Some(l))

def withMinInclusive(l: LexicalTime)	 
: TimeScalarRestriction
= copy(minInclusive=Some(l))

}


@JSExport
object TimeScalarRestrictionHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "TimeScalarRestrictions.json"
  
  implicit val w
  : upickle.default.Writer[TimeScalarRestriction]
  = upickle.default.macroW[TimeScalarRestriction]

  @JSExport
  def toJSON(c: TimeScalarRestriction)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[TimeScalarRestriction]
  = upickle.default.macroR[TimeScalarRestriction]

  @JSExport
  def fromJSON(c: String)
  : TimeScalarRestriction
  = upickle.default.read[TimeScalarRestriction](c)

}	
