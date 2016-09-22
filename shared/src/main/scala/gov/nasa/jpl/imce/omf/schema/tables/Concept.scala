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
  * @param graphUUID (Foreign key) The UUID of the TerminologyGraph in which this Concept is asserted.
  * @param uuid (Primary key) The UUID of the Concept itself.
  * @param isAbstract Whether the Concept is abstract.
  * @param name Name of the Concept
  * @param resourceIRI The IRI of the Concept, which is a kind of Resource
  */
@JSExport
case class Concept
( @(JSExport @field) graphUUID: String,
  @(JSExport @field) uuid: String,
  @(JSExport @field) isAbstract: Boolean,
  @(JSExport @field) name: String,
  @(JSExport @field) resourceIRI: String )

@JSExport
object ConceptHelper {

  implicit val cw: upickle.default.Writer[Concept] = upickle.default.macroW[Concept]

  @JSExport
  def toJSON(c: Concept)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val cr: upickle.default.Reader[Concept] = upickle.default.macroR[Concept]

  @JSExport
  def fromJSON(c: String)
  : Concept
  = upickle.default.read[Concept](c)

}