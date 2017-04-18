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
  * @param sourceUUID[1,1]
  * @param targetUUID[1,1]
  * @param isAsymmetric[1,1]
  * @param isEssential[1,1]
  * @param isFunctional[1,1]
  * @param isInverseEssential[1,1]
  * @param isInverseFunctional[1,1]
  * @param isIrreflexive[1,1]
  * @param isReflexive[1,1]
  * @param isSymmetric[1,1]
  * @param isTransitive[1,1]
  * @param name[1,1]
  */
@JSExportTopLevel("UnreifiedRelationship")
case class UnreifiedRelationship
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) tboxUUID: UUID,
  @(JSExport @field) sourceUUID: UUID,
  @(JSExport @field) targetUUID: UUID,
  @(JSExport @field) isAsymmetric: scala.Boolean,
  @(JSExport @field) isEssential: scala.Boolean,
  @(JSExport @field) isFunctional: scala.Boolean,
  @(JSExport @field) isInverseEssential: scala.Boolean,
  @(JSExport @field) isInverseFunctional: scala.Boolean,
  @(JSExport @field) isIrreflexive: scala.Boolean,
  @(JSExport @field) isReflexive: scala.Boolean,
  @(JSExport @field) isSymmetric: scala.Boolean,
  @(JSExport @field) isTransitive: scala.Boolean,
  @(JSExport @field) name: LocalName
) {
  // Ctor(uuidWithGenerator)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: UUID,
    sourceUUID: UUID,
    targetUUID: UUID,
    isAsymmetric: scala.Boolean,
    isEssential: scala.Boolean,
    isFunctional: scala.Boolean,
    isInverseEssential: scala.Boolean,
    isInverseFunctional: scala.Boolean,
    isIrreflexive: scala.Boolean,
    isReflexive: scala.Boolean,
    isSymmetric: scala.Boolean,
    isTransitive: scala.Boolean,
    name: LocalName)
  = this(
      oug.namespaceUUID(
        tboxUUID,
        "name" -> name).toString,
      tboxUUID,
      sourceUUID,
      targetUUID,
      isAsymmetric,
      isEssential,
      isFunctional,
      isInverseEssential,
      isInverseFunctional,
      isIrreflexive,
      isReflexive,
      isSymmetric,
      isTransitive,
      name)

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, sourceUUID, targetUUID, isAsymmetric, isEssential, isFunctional, isInverseEssential, isInverseFunctional, isIrreflexive, isReflexive, isSymmetric, isTransitive, name).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: UnreifiedRelationship =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID) &&
  	  (this.sourceUUID == that.sourceUUID) &&
  	  (this.targetUUID == that.targetUUID) &&
  	  (this.isAsymmetric == that.isAsymmetric) &&
  	  (this.isEssential == that.isEssential) &&
  	  (this.isFunctional == that.isFunctional) &&
  	  (this.isInverseEssential == that.isInverseEssential) &&
  	  (this.isInverseFunctional == that.isInverseFunctional) &&
  	  (this.isIrreflexive == that.isIrreflexive) &&
  	  (this.isReflexive == that.isReflexive) &&
  	  (this.isSymmetric == that.isSymmetric) &&
  	  (this.isTransitive == that.isTransitive) &&
  	  (this.name == that.name)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("UnreifiedRelationshipHelper")
object UnreifiedRelationshipHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "UnreifiedRelationships.json"
  
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
