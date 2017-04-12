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
  * @param bundleUUID[1,1]
  * @param rootUUID[1,1]
  */
@JSExportTopLevel("RootConceptTaxonomyAxiom")
case class RootConceptTaxonomyAxiom
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) bundleUUID: UUID,
  @(JSExport @field) rootUUID: UUID
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    bundleUUID: UUID,
    rootUUID: UUID)
  = this(
      oug.namespaceUUID("RootConceptTaxonomyAxiom", "bundle" -> bundleUUID, "root" -> rootUUID).toString,
      bundleUUID,
      rootUUID)

  override val hashCode
  : scala.Int 
  = (uuid, bundleUUID, rootUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: RootConceptTaxonomyAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.bundleUUID == that.bundleUUID) &&
  	  (this.rootUUID == that.rootUUID)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("RootConceptTaxonomyAxiomHelper")
object RootConceptTaxonomyAxiomHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "RootConceptTaxonomyAxioms.json"
  
  implicit val w
  : upickle.default.Writer[RootConceptTaxonomyAxiom]
  = upickle.default.macroW[RootConceptTaxonomyAxiom]

  @JSExport
  def toJSON(c: RootConceptTaxonomyAxiom)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[RootConceptTaxonomyAxiom]
  = upickle.default.macroR[RootConceptTaxonomyAxiom]

  @JSExport
  def fromJSON(c: String)
  : RootConceptTaxonomyAxiom
  = upickle.default.read[RootConceptTaxonomyAxiom](c)

}	
