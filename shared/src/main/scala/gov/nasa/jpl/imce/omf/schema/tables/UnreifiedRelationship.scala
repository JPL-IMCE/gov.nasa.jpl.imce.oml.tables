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
  * @param sourceUUID
  * @param targetUUID
  */
@JSExport
case class UnreifiedRelationship
(
 @(JSExport @field) graphUUID: TerminologyBox,
 @(JSExport @field) uuid: UUID,
 @(JSExport @field) asymmetric: EJavaObject,
 @(JSExport @field) essential: EJavaObject,
 @(JSExport @field) functional: EJavaObject,
 @(JSExport @field) inverseEssential: EJavaObject,
 @(JSExport @field) inverseFunctional: EJavaObject,
 @(JSExport @field) irreflexive: EJavaObject,
 @(JSExport @field) reflexive: EJavaObject,
 @(JSExport @field) symmetric: EJavaObject,
 @(JSExport @field) transitive: EJavaObject,
 @(JSExport @field) name: LocalName,
 @(JSExport @field) iri: IRI,
 @(JSExport @field) sourceUUID: Entity,
 @(JSExport @field) targetUUID: Entity
)

@JSExport
object UnreifiedRelationshipHelper {

  implicit val w
  : upickle.default.Writer[UnreifiedRelationship]
  = upickle.default.macroW[UnreifiedRelationship]

  @JSExport
  def toJSON(c: UnreifiedRelationship)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[UnreifiedRelationship]
  = upickle.default.macroR[UnreifiedRelationship]

  @JSExport
  def fromJSON(c: String)
  : UnreifiedRelationship
  = upickle.default.read[UnreifiedRelationship](c)

}	
