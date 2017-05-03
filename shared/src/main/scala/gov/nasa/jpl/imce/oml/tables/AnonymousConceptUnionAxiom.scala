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
  * @param disjointTaxonomyParentUUID[1,1]
  * @param name[1,1]
  */
@JSExportTopLevel("AnonymousConceptUnionAxiom")
case class AnonymousConceptUnionAxiom
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) disjointTaxonomyParentUUID: UUID,
  @(JSExport @field) name: LocalName
) {
  // Ctor(uuidWithGenerator)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    disjointTaxonomyParentUUID: UUID,
    name: LocalName)
  = this(
      oug.namespaceUUID(
        disjointTaxonomyParentUUID,
        "name" -> name).toString,
      disjointTaxonomyParentUUID,
      name)

  override val hashCode
  : scala.Int 
  = (uuid, disjointTaxonomyParentUUID, name).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: AnonymousConceptUnionAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.disjointTaxonomyParentUUID == that.disjointTaxonomyParentUUID) &&
  	  (this.name == that.name)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("AnonymousConceptUnionAxiomHelper")
object AnonymousConceptUnionAxiomHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "AnonymousConceptUnionAxioms.json"
  
  implicit val w
  : upickle.default.Writer[AnonymousConceptUnionAxiom]
  = upickle.default.macroW[AnonymousConceptUnionAxiom]

  @JSExport
  def toJSON(c: AnonymousConceptUnionAxiom)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[AnonymousConceptUnionAxiom]
  = upickle.default.macroR[AnonymousConceptUnionAxiom]

  @JSExport
  def fromJSON(c: String)
  : AnonymousConceptUnionAxiom
  = upickle.default.read[AnonymousConceptUnionAxiom](c)

}	
