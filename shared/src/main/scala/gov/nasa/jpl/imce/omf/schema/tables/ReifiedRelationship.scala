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
  * @param graphUUID
  * @param uuid
  * @param asymmetric
  * @param essential
  * @param functional
  * @param inverseEssential
  * @param inverseFunctional
  * @param irreflexive
  * @param reflexive
  * @param symmetric
  * @param transitive
  * @param name
  * @param iri
  * @param abstract
  * @param sourceUUID
  * @param targetUUID
  */
@JSExport
case class ReifiedRelationship
(
 @(JSExport @field) graphUUID: UUID,
 @(JSExport @field) uuid: UUID,
 @(JSExport @field) asymmetric: Scala.Boolean,
 @(JSExport @field) essential: Scala.Boolean,
 @(JSExport @field) functional: Scala.Boolean,
 @(JSExport @field) inverseEssential: Scala.Boolean,
 @(JSExport @field) inverseFunctional: Scala.Boolean,
 @(JSExport @field) irreflexive: Scala.Boolean,
 @(JSExport @field) reflexive: Scala.Boolean,
 @(JSExport @field) symmetric: Scala.Boolean,
 @(JSExport @field) transitive: Scala.Boolean,
 @(JSExport @field) name: LocalName,
 @(JSExport @field) iri: IRI,
 @(JSExport @field) abstract: Scala.Boolean,
 @(JSExport @field) sourceUUID: UUID,
 @(JSExport @field) targetUUID: UUID
)

@JSExport
object ReifiedRelationshipHelper {

  implicit val w
  : upickle.default.Writer[ReifiedRelationship]
  = upickle.default.macroW[ReifiedRelationship]

  @JSExport
  def toJSON(c: ReifiedRelationship)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[ReifiedRelationship]
  = upickle.default.macroR[ReifiedRelationship]

  @JSExport
  def fromJSON(c: String)
  : ReifiedRelationship
  = upickle.default.read[ReifiedRelationship](c)

}	
