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
import scala.scalajs.js.annotation.{JSExport,JSExportTopLevel}
import scala._
import scala.Predef._

/**
  * @param uuid[1,1]
  * @param tboxUUID[1,1]
  * @param axiomUUID[1,1]
  * @param value[1,1]
  * @param valueTypeUUID[0,1]
  */
case class ScalarOneOfLiteralAxiom
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) tboxUUID: UUID,
  @(JSExport @field) axiomUUID: UUID,
  @(JSExport @field) value: LiteralValue,
  @(JSExport @field) valueTypeUUID: scala.Option[UUID]
) {
  def this(
    uuid: UUID,
    tboxUUID: UUID,
    axiomUUID: UUID,
    value: LiteralValue)
  = this(
      uuid,
      tboxUUID,
      axiomUUID,
      value,
      None /* valueTypeUUID */)

  def withValueTypeUUID(l: UUID)	 
  : ScalarOneOfLiteralAxiom
  = copy(valueTypeUUID=Some(l))
  
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: UUID,
    axiomUUID: UUID,
    value: LiteralValue)
  = this(
      oug.namespaceUUID(
        "ScalarOneOfLiteralAxiom",
        "tbox" -> tboxUUID,
        "axiom" -> axiomUUID).toString,
      tboxUUID,
      axiomUUID,
      value)

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, axiomUUID, value, valueTypeUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ScalarOneOfLiteralAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID) &&
  	  (this.axiomUUID == that.axiomUUID) &&
  	  (this.value == that.value) &&
  	  (this.valueTypeUUID == that.valueTypeUUID)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("ScalarOneOfLiteralAxiomHelper")
object ScalarOneOfLiteralAxiomHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "ScalarOneOfLiteralAxioms.json"
  
  implicit val w
  : upickle.default.Writer[ScalarOneOfLiteralAxiom]
  = upickle.default.macroW[ScalarOneOfLiteralAxiom]

  @JSExport
  def toJSON(c: ScalarOneOfLiteralAxiom)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[ScalarOneOfLiteralAxiom]
  = upickle.default.macroR[ScalarOneOfLiteralAxiom]

  @JSExport
  def fromJSON(c: String)
  : ScalarOneOfLiteralAxiom
  = upickle.default.read[ScalarOneOfLiteralAxiom](c)

}	
