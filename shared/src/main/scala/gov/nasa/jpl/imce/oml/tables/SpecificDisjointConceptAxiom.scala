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
  * @param bundleUUID[1,1]
  * @param disjointTaxonomyParentUUID[1,1]
  * @param disjointLeafUUID[1,1]
  */
case class SpecificDisjointConceptAxiom
(
  @(JSExport @field) uuid: scala.Option[UUID],
  @(JSExport @field) bundleUUID: UUID,
  @(JSExport @field) disjointTaxonomyParentUUID: UUID,
  @(JSExport @field) disjointLeafUUID: UUID
) {
  @JSExport
  def this(
    bundleUUID: UUID,
    disjointTaxonomyParentUUID: UUID,
    disjointLeafUUID: UUID)
  = this(
      None /* uuid */,
      bundleUUID,
      disjointTaxonomyParentUUID,
      disjointLeafUUID)

  def withUuid(l: UUID)	 
  : SpecificDisjointConceptAxiom
  = copy(uuid=Some(l))
  
  override val hashCode
  : scala.Int 
  = (uuid, bundleUUID, disjointTaxonomyParentUUID, disjointLeafUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: SpecificDisjointConceptAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.bundleUUID == that.bundleUUID) &&
  	  (this.disjointTaxonomyParentUUID == that.disjointTaxonomyParentUUID) &&
  	  (this.disjointLeafUUID == that.disjointLeafUUID)
    case _ =>
      false
  }
  
}

@JSExport
object SpecificDisjointConceptAxiomHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "SpecificDisjointConceptAxioms.json"
  
  implicit val w
  : upickle.default.Writer[SpecificDisjointConceptAxiom]
  = upickle.default.macroW[SpecificDisjointConceptAxiom]

  @JSExport
  def toJSON(c: SpecificDisjointConceptAxiom)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[SpecificDisjointConceptAxiom]
  = upickle.default.macroR[SpecificDisjointConceptAxiom]

  @JSExport
  def fromJSON(c: String)
  : SpecificDisjointConceptAxiom
  = upickle.default.read[SpecificDisjointConceptAxiom](c)

}	
