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
 
package gov.nasa.jpl.imce.omf.provenance.oti.schema.tables

import scala.annotation.meta.field
import scala.scalajs.js.annotation.JSExport
import scala._
import scala.Predef._

/**
  * @param omfUUID[1,1]
  * @param otiID[1,1]
  * @param otiURL[1,1]
  * @param otiUUID[0,1]
  */
case class OMF2OTIProvenance
(
 @(JSExport @field) omfUUID: UUID,
 @(JSExport @field) otiID: OTI_TOOL_SPECIFIC_ID,
 @(JSExport @field) otiURL: OTI_TOOL_SPECIFIC_URL,
 @(JSExport @field) otiUUID: scala.Option[OTI_TOOL_SPECIFIC_UUID]
) {

@JSExport
def this(
	omfUUID: UUID,
	otiID: OTI_TOOL_SPECIFIC_ID,
	otiURL: OTI_TOOL_SPECIFIC_URL
) 
= this(
omfUUID,
otiID,
otiURL,
None
) 

def withOtiUUID(l: OTI_TOOL_SPECIFIC_UUID)	 
: OMF2OTIProvenance
= copy(otiUUID=Some(l))

}


@JSExport
object OMF2OTIProvenanceHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "OMF2OTIProvenances.json"
  
  implicit val w
  : upickle.default.Writer[OMF2OTIProvenance]
  = upickle.default.macroW[OMF2OTIProvenance]

  @JSExport
  def toJSON(c: OMF2OTIProvenance)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[OMF2OTIProvenance]
  = upickle.default.macroR[OMF2OTIProvenance]

  @JSExport
  def fromJSON(c: String)
  : OMF2OTIProvenance
  = upickle.default.read[OMF2OTIProvenance](c)

}	
