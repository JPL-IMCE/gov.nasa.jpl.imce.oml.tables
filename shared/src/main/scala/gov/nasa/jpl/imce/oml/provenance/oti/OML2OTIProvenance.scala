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

 
package gov.nasa.jpl.imce.oml.provenance.oti

import scala.annotation.meta.field
import scala.scalajs.js.annotation.JSExport
import scala._
import scala.Predef._

/**
  * @param explanation[1,1]
  * @param omlUUID[1,1]
  * @param otiID[1,1]
  * @param otiURL[1,1]
  * @param otiUUID[0,1]
  */
case class OML2OTIProvenance
(
  @(JSExport @field) explanation: scala.Predef.String,
  @(JSExport @field) omlUUID: UUID,
  @(JSExport @field) otiID: OTI_TOOL_SPECIFIC_ID,
  @(JSExport @field) otiURL: OTI_TOOL_SPECIFIC_URL,
  @(JSExport @field) otiUUID: scala.Option[OTI_TOOL_SPECIFIC_UUID]
) {
  @JSExport
  def this(
    explanation: scala.Predef.String,
    omlUUID: UUID,
    otiID: OTI_TOOL_SPECIFIC_ID,
    otiURL: OTI_TOOL_SPECIFIC_URL)
  = this(
      explanation,
      omlUUID,
      otiID,
      otiURL,
      None /* otiUUID */)

  def withOtiUUID(l: OTI_TOOL_SPECIFIC_UUID)	 
  : OML2OTIProvenance
  = copy(otiUUID=Some(l))
  
  override val hashCode
  : scala.Int 
  = (explanation, omlUUID, otiID, otiURL, otiUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: OML2OTIProvenance =>
  	  (this.explanation == that.explanation) &&
  	  (this.omlUUID == that.omlUUID) &&
  	  (this.otiID == that.otiID) &&
  	  (this.otiURL == that.otiURL) &&
  	  (this.otiUUID == that.otiUUID)
    case _ =>
      false
  }
  
}

@JSExport
object OML2OTIProvenanceHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "OML2OTIProvenances.json"
  
  implicit val w
  : upickle.default.Writer[OML2OTIProvenance]
  = upickle.default.macroW[OML2OTIProvenance]

  @JSExport
  def toJSON(c: OML2OTIProvenance)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[OML2OTIProvenance]
  = upickle.default.macroR[OML2OTIProvenance]

  @JSExport
  def fromJSON(c: String)
  : OML2OTIProvenance
  = upickle.default.read[OML2OTIProvenance](c)

}	
