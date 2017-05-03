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
  * @param disjointTreeUUID[1,1]
  */
@JSExportTopLevel("ConceptTreeTaxonomyAxiom")
case class ConceptTreeTaxonomyAxiom
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) disjointTaxonomyParentUUID: UUID,
  @(JSExport @field) disjointTreeUUID: UUID
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    disjointTaxonomyParentUUID: UUID,
    disjointTreeUUID: UUID)
  = this(
      oug.namespaceUUID(
        "ConceptTreeTaxonomyAxiom",
        "disjointTaxonomyParent" -> disjointTaxonomyParentUUID,
        "disjointTree" -> disjointTreeUUID).toString,
      disjointTaxonomyParentUUID,
      disjointTreeUUID)

  override val hashCode
  : scala.Int 
  = (uuid, disjointTaxonomyParentUUID, disjointTreeUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ConceptTreeTaxonomyAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.disjointTaxonomyParentUUID == that.disjointTaxonomyParentUUID) &&
  	  (this.disjointTreeUUID == that.disjointTreeUUID)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("ConceptTreeTaxonomyAxiomHelper")
object ConceptTreeTaxonomyAxiomHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "ConceptTreeTaxonomyAxioms.json"
  
  implicit val w
  : upickle.default.Writer[ConceptTreeTaxonomyAxiom]
  = upickle.default.macroW[ConceptTreeTaxonomyAxiom]

  @JSExport
  def toJSON(c: ConceptTreeTaxonomyAxiom)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[ConceptTreeTaxonomyAxiom]
  = upickle.default.macroR[ConceptTreeTaxonomyAxiom]

  @JSExport
  def fromJSON(c: String)
  : ConceptTreeTaxonomyAxiom
  = upickle.default.read[ConceptTreeTaxonomyAxiom](c)

}	
