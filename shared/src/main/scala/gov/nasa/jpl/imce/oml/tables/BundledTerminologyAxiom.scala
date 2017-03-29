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
  * @param bundledTerminologyUUID[1,1]
  * @param bundleUUID[1,1]
  */
case class BundledTerminologyAxiom
(
  @(JSExport @field) uuid: scala.Option[UUID],
  @(JSExport @field) bundledTerminologyUUID: UUID,
  @(JSExport @field) bundleUUID: UUID
) {
  @JSExport
  def this(
    bundledTerminologyUUID: UUID,
    bundleUUID: UUID)
  = this(
      None /* uuid */,
      bundledTerminologyUUID,
      bundleUUID)

  def withUuid(l: UUID)	 
  : BundledTerminologyAxiom
  = copy(uuid=Some(l))
  
  override val hashCode
  : scala.Int 
  = (uuid, bundledTerminologyUUID, bundleUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: BundledTerminologyAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.bundledTerminologyUUID == that.bundledTerminologyUUID) &&
  	  (this.bundleUUID == that.bundleUUID)
    case _ =>
      false
  }
  
}

@JSExport
object BundledTerminologyAxiomHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "BundledTerminologyAxioms.json"
  
  implicit val w
  : upickle.default.Writer[BundledTerminologyAxiom]
  = upickle.default.macroW[BundledTerminologyAxiom]

  @JSExport
  def toJSON(c: BundledTerminologyAxiom)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[BundledTerminologyAxiom]
  = upickle.default.macroR[BundledTerminologyAxiom]

  @JSExport
  def fromJSON(c: String)
  : BundledTerminologyAxiom
  = upickle.default.read[BundledTerminologyAxiom](c)

}	
