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
  */
@JSExportTopLevel("UnreifiedRelationship")
case class UnreifiedRelationship
(
  @(JSExport @field) override val uuid: taggedTypes.UnreifiedRelationshipUUID,
  @(JSExport @field) override val tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) override val sourceUUID: taggedTypes.EntityUUID,
  @(JSExport @field) override val targetUUID: taggedTypes.EntityUUID,
  @(JSExport @field) override val isAsymmetric: scala.Boolean,
  @(JSExport @field) override val isEssential: scala.Boolean,
  @(JSExport @field) override val isFunctional: scala.Boolean,
  @(JSExport @field) override val isInverseEssential: scala.Boolean,
  @(JSExport @field) override val isInverseFunctional: scala.Boolean,
  @(JSExport @field) override val isIrreflexive: scala.Boolean,
  @(JSExport @field) override val isReflexive: scala.Boolean,
  @(JSExport @field) override val isSymmetric: scala.Boolean,
  @(JSExport @field) override val isTransitive: scala.Boolean,
  @(JSExport @field) override val name: taggedTypes.LocalName
) extends EntityRelationship with RestrictableRelationship {
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
    name: taggedTypes.LocalName)
  = this(
      taggedTypes.unreifiedRelationshipUUID(oug.namespaceUUID(
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
      name)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, sourceUUID, targetUUID, isAsymmetric, isEssential, isFunctional, isInverseEssential, isInverseFunctional, isIrreflexive, isReflexive, isSymmetric, isTransitive, name).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: UnreifiedRelationship =>
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
  	  (this.name == that.name)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("UnreifiedRelationshipHelper")
object UnreifiedRelationshipHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "UnreifiedRelationships.json"

  implicit val decodeUnreifiedRelationship: Decoder[UnreifiedRelationship]
  = Decoder.instance[UnreifiedRelationship] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.UnreifiedRelationshipUUID]
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
    	} yield UnreifiedRelationship(
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
    	  name
    	)
  }
  
  implicit val encodeUnreifiedRelationship: Encoder[UnreifiedRelationship]
  = new Encoder[UnreifiedRelationship] {
    override final def apply(x: UnreifiedRelationship): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeUnreifiedRelationshipUUID(x.uuid)),
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
    	  ("name", taggedTypes.encodeLocalName(x.name))
    )
  }

  @JSExport
  def toJSON(c: UnreifiedRelationship)
  : String
  = encodeUnreifiedRelationship(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : UnreifiedRelationship
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeUnreifiedRelationship(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
