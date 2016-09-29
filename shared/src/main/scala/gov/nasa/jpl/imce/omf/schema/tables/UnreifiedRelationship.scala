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
  * @param name
  * @param iri
  * @param isAsymmetric
  * @param isEssential
  * @param isFunctional
  * @param isInverseEssential
  * @param isInverseFunctional
  * @param isIrreflexive
  * @param isReflexive
  * @param isSymmetric
  * @param isTransitive
  * @param sourceUUID
  * @param targetUUID
  */
@JSExport
case class UnreifiedRelationship
(
 @(JSExport @field) graphUUID: UUID,
 @(JSExport @field) uuid: UUID,
 @(JSExport @field) name: LocalName,
 @(JSExport @field) iri: IRI,
 @(JSExport @field) isAsymmetric: Scala.Boolean,
 @(JSExport @field) isEssential: Scala.Boolean,
 @(JSExport @field) isFunctional: Scala.Boolean,
 @(JSExport @field) isInverseEssential: Scala.Boolean,
 @(JSExport @field) isInverseFunctional: Scala.Boolean,
 @(JSExport @field) isIrreflexive: Scala.Boolean,
 @(JSExport @field) isReflexive: Scala.Boolean,
 @(JSExport @field) isSymmetric: Scala.Boolean,
 @(JSExport @field) isTransitive: Scala.Boolean,
 @(JSExport @field) sourceUUID: UUID,
 @(JSExport @field) targetUUID: UUID
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
