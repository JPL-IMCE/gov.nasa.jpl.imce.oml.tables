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
  * @param uuid[1,1]
  * @param designatedConceptUUID[1,1]
  * @param designationTerminologyGraphUUID[1,1]
  */
@JSExport
case class ConceptDesignationTerminologyGraphAxiom
(
 @(JSExport @field) uuid: UUID,
 @(JSExport @field) designatedConceptUUID: UUID,
 @(JSExport @field) designationTerminologyGraphUUID: UUID
) 
@JSExport
object ConceptDesignationTerminologyGraphAxiomHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "ConceptDesignationTerminologyGraphAxioms.json"
  
  implicit val w
  : upickle.default.Writer[ConceptDesignationTerminologyGraphAxiom]
  = upickle.default.macroW[ConceptDesignationTerminologyGraphAxiom]

  @JSExport
  def toJSON(c: ConceptDesignationTerminologyGraphAxiom)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[ConceptDesignationTerminologyGraphAxiom]
  = upickle.default.macroR[ConceptDesignationTerminologyGraphAxiom]

  @JSExport
  def fromJSON(c: String)
  : ConceptDesignationTerminologyGraphAxiom
  = upickle.default.read[ConceptDesignationTerminologyGraphAxiom](c)

}	
