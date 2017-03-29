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
  * @param superAspectUUID[1,1]
  * @param subEntityUUID[1,1]
  */
case class AspectSpecializationAxiom
(
  @(JSExport @field) uuid: scala.Option[UUID],
  @(JSExport @field) tboxUUID: UUID,
  @(JSExport @field) superAspectUUID: UUID,
  @(JSExport @field) subEntityUUID: UUID
) {
  @JSExport
  def this(
    tboxUUID: UUID,
    superAspectUUID: UUID,
    subEntityUUID: UUID)
  = this(
      None /* uuid */,
      tboxUUID,
      superAspectUUID,
      subEntityUUID)

  def withUuid(l: UUID)	 
  : AspectSpecializationAxiom
  = copy(uuid=Some(l))
  
  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, superAspectUUID, subEntityUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: AspectSpecializationAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID) &&
  	  (this.superAspectUUID == that.superAspectUUID) &&
  	  (this.subEntityUUID == that.subEntityUUID)
    case _ =>
      false
  }
  
}

@JSExport
object AspectSpecializationAxiomHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "AspectSpecializationAxioms.json"
  
  implicit val w
  : upickle.default.Writer[AspectSpecializationAxiom]
  = upickle.default.macroW[AspectSpecializationAxiom]

  @JSExport
  def toJSON(c: AspectSpecializationAxiom)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[AspectSpecializationAxiom]
  = upickle.default.macroR[AspectSpecializationAxiom]

  @JSExport
  def fromJSON(c: String)
  : AspectSpecializationAxiom
  = upickle.default.read[AspectSpecializationAxiom](c)

}	
