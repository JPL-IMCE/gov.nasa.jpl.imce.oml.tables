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
import scala.Predef.ArrowAssoc

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
  * @param unreifiedPropertyName[1,1]
  * @param unreifiedInversePropertyName[0,1]
  */
case class ReifiedRelationship
(
  @(JSExport @field) uuid: taggedTypes.ReifiedRelationshipUUID,
  @(JSExport @field) tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) sourceUUID: taggedTypes.EntityUUID,
  @(JSExport @field) targetUUID: taggedTypes.EntityUUID,
  @(JSExport @field) isAsymmetric: scala.Boolean,
  @(JSExport @field) isEssential: scala.Boolean,
  @(JSExport @field) isFunctional: scala.Boolean,
  @(JSExport @field) isInverseEssential: scala.Boolean,
  @(JSExport @field) isInverseFunctional: scala.Boolean,
  @(JSExport @field) isIrreflexive: scala.Boolean,
  @(JSExport @field) isReflexive: scala.Boolean,
  @(JSExport @field) isSymmetric: scala.Boolean,
  @(JSExport @field) isTransitive: scala.Boolean,
  @(JSExport @field) name: taggedTypes.LocalName,
  @(JSExport @field) unreifiedPropertyName: taggedTypes.LocalName,
  @(JSExport @field) unreifiedInversePropertyName: scala.Option[taggedTypes.LocalName]
) {
  def this(
    uuid: taggedTypes.ReifiedRelationshipUUID,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    sourceUUID: taggedTypes.EntityUUID,
    targetUUID: taggedTypes.EntityUUID,
    isAsymmetric: scala.Boolean,
    isEssential: scala.Boolean,
    isFunctional: scala.Boolean,
    isInverseEssential: scala.Boolean,
    isInverseFunctional: scala.Boolean,
    isIrreflexive: scala.Boolean,
    isReflexive: scala.Boolean,
    isSymmetric: scala.Boolean,
    isTransitive: scala.Boolean,
    name: taggedTypes.LocalName,
    unreifiedPropertyName: taggedTypes.LocalName)
  = this(
      uuid,
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
      name,
      unreifiedPropertyName,
      scala.None /* unreifiedInversePropertyName */)

  def withUnreifiedInversePropertyName(l: taggedTypes.LocalName)	 
  : ReifiedRelationship
  = copy(unreifiedInversePropertyName=scala.Some(l))
  
  // Ctor(uuidWithGenerator)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    sourceUUID: taggedTypes.EntityUUID,
    targetUUID: taggedTypes.EntityUUID,
    isAsymmetric: scala.Boolean,
    isEssential: scala.Boolean,
    isFunctional: scala.Boolean,
    isInverseEssential: scala.Boolean,
    isInverseFunctional: scala.Boolean,
    isIrreflexive: scala.Boolean,
    isReflexive: scala.Boolean,
    isSymmetric: scala.Boolean,
    isTransitive: scala.Boolean,
    name: taggedTypes.LocalName,
    unreifiedPropertyName: taggedTypes.LocalName)
  = this(
      taggedTypes.reifiedRelationshipUUID(oug.namespaceUUID(
        tboxUUID,
        "name" -> name).toString),
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
      name,
      unreifiedPropertyName)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, sourceUUID, targetUUID, isAsymmetric, isEssential, isFunctional, isInverseEssential, isInverseFunctional, isIrreflexive, isReflexive, isSymmetric, isTransitive, name, unreifiedPropertyName, unreifiedInversePropertyName).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ReifiedRelationship =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.sourceUUID == that.sourceUUID)  &&
  	  (this.targetUUID == that.targetUUID)  &&
  	  (this.isAsymmetric == that.isAsymmetric) &&
  	  (this.isEssential == that.isEssential) &&
  	  (this.isFunctional == that.isFunctional) &&
  	  (this.isInverseEssential == that.isInverseEssential) &&
  	  (this.isInverseFunctional == that.isInverseFunctional) &&
  	  (this.isIrreflexive == that.isIrreflexive) &&
  	  (this.isReflexive == that.isReflexive) &&
  	  (this.isSymmetric == that.isSymmetric) &&
  	  (this.isTransitive == that.isTransitive) &&
  	  (this.name == that.name) &&
  	  (this.unreifiedPropertyName == that.unreifiedPropertyName) &&
  	  (this.unreifiedInversePropertyName == that.unreifiedInversePropertyName)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("ReifiedRelationshipHelper")
object ReifiedRelationshipHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "ReifiedRelationships.json"

  implicit val decodeReifiedRelationship: Decoder[ReifiedRelationship]
  = Decoder.instance[ReifiedRelationship] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.ReifiedRelationshipUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  sourceUUID <- c.downField("sourceUUID").as[taggedTypes.EntityUUID]
    	  targetUUID <- c.downField("targetUUID").as[taggedTypes.EntityUUID]
    	  isAsymmetric <- c.downField("isAsymmetric").as[scala.Boolean]
    	  isEssential <- c.downField("isEssential").as[scala.Boolean]
    	  isFunctional <- c.downField("isFunctional").as[scala.Boolean]
    	  isInverseEssential <- c.downField("isInverseEssential").as[scala.Boolean]
    	  isInverseFunctional <- c.downField("isInverseFunctional").as[scala.Boolean]
    	  isIrreflexive <- c.downField("isIrreflexive").as[scala.Boolean]
    	  isReflexive <- c.downField("isReflexive").as[scala.Boolean]
    	  isSymmetric <- c.downField("isSymmetric").as[scala.Boolean]
    	  isTransitive <- c.downField("isTransitive").as[scala.Boolean]
    	  name <- c.downField("name").as[taggedTypes.LocalName]
    	  unreifiedPropertyName <- c.downField("unreifiedPropertyName").as[taggedTypes.LocalName]
    	  unreifiedInversePropertyName <- Decoder.decodeOption(taggedTypes.decodeLocalName)(c.downField("unreifiedInversePropertyName").success.get)
    	} yield ReifiedRelationship(
    	  uuid,
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
    	  name,
    	  unreifiedPropertyName,
    	  unreifiedInversePropertyName
    	)
  }
  
  implicit val encodeReifiedRelationship: Encoder[ReifiedRelationship]
  = new Encoder[ReifiedRelationship] {
    override final def apply(x: ReifiedRelationship): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeReifiedRelationshipUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("sourceUUID", taggedTypes.encodeEntityUUID(x.sourceUUID)),
    	  ("targetUUID", taggedTypes.encodeEntityUUID(x.targetUUID)),
    	  ("isAsymmetric", Encoder.encodeBoolean(x.isAsymmetric)),
    	  ("isEssential", Encoder.encodeBoolean(x.isEssential)),
    	  ("isFunctional", Encoder.encodeBoolean(x.isFunctional)),
    	  ("isInverseEssential", Encoder.encodeBoolean(x.isInverseEssential)),
    	  ("isInverseFunctional", Encoder.encodeBoolean(x.isInverseFunctional)),
    	  ("isIrreflexive", Encoder.encodeBoolean(x.isIrreflexive)),
    	  ("isReflexive", Encoder.encodeBoolean(x.isReflexive)),
    	  ("isSymmetric", Encoder.encodeBoolean(x.isSymmetric)),
    	  ("isTransitive", Encoder.encodeBoolean(x.isTransitive)),
    	  ("name", taggedTypes.encodeLocalName(x.name)),
    	  ("unreifiedPropertyName", taggedTypes.encodeLocalName(x.unreifiedPropertyName)),
    	  ("unreifiedInversePropertyName", Encoder.encodeOption(taggedTypes.encodeLocalName).apply(x.unreifiedInversePropertyName))
    )
  }

  @JSExport
  def toJSON(c: ReifiedRelationship)
  : String
  = encodeReifiedRelationship(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : ReifiedRelationship
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeReifiedRelationship(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
