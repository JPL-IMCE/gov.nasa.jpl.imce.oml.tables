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
  * @param generalUUID[1,1]
  * @param name[1,1]
  */
@JSExportTopLevel("SpecializedReifiedRelationship")
case class SpecializedReifiedRelationship
(
  @(JSExport @field) override val uuid: taggedTypes.SpecializedReifiedRelationshipUUID,
  @(JSExport @field) override val tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) override val sourceUUID: taggedTypes.EntityUUID,
  @(JSExport @field) override val targetUUID: taggedTypes.EntityUUID,
  @(JSExport @field) val generalUUID: taggedTypes.ConceptualRelationshipUUID,
  @(JSExport @field) override val name: taggedTypes.LocalName
) extends ConceptualRelationship with SpecializationAxiom {
  // Ctor(uuidWithGenerator)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    sourceUUID: taggedTypes.EntityUUID,
    targetUUID: taggedTypes.EntityUUID,
    generalUUID: taggedTypes.ConceptualRelationshipUUID,
    name: taggedTypes.LocalName)
  = this(
      taggedTypes.specializedReifiedRelationshipUUID(oug.namespaceUUID(
        tboxUUID,
        "name" -> name).toString),
      tboxUUID,
      sourceUUID,
      targetUUID,
      generalUUID,
      name)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, sourceUUID, targetUUID, generalUUID, name).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: SpecializedReifiedRelationship =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.sourceUUID == that.sourceUUID)  &&
  	  (this.targetUUID == that.targetUUID)  &&
  	  (this.generalUUID == that.generalUUID)  &&
  	  (this.name == that.name)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("SpecializedReifiedRelationshipHelper")
object SpecializedReifiedRelationshipHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "SpecializedReifiedRelationships.json"

  implicit val decodeSpecializedReifiedRelationship: Decoder[SpecializedReifiedRelationship]
  = Decoder.instance[SpecializedReifiedRelationship] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.SpecializedReifiedRelationshipUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  sourceUUID <- c.downField("sourceUUID").as[taggedTypes.EntityUUID]
    	  targetUUID <- c.downField("targetUUID").as[taggedTypes.EntityUUID]
    	  generalUUID <- c.downField("generalUUID").as[taggedTypes.ConceptualRelationshipUUID]
    	  name <- c.downField("name").as[taggedTypes.LocalName]
    	} yield SpecializedReifiedRelationship(
    	  uuid,
    	  tboxUUID,
    	  sourceUUID,
    	  targetUUID,
    	  generalUUID,
    	  name
    	)
  }
  
  implicit val encodeSpecializedReifiedRelationship: Encoder[SpecializedReifiedRelationship]
  = new Encoder[SpecializedReifiedRelationship] {
    override final def apply(x: SpecializedReifiedRelationship): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeSpecializedReifiedRelationshipUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("sourceUUID", taggedTypes.encodeEntityUUID(x.sourceUUID)),
    	  ("targetUUID", taggedTypes.encodeEntityUUID(x.targetUUID)),
    	  ("generalUUID", taggedTypes.encodeConceptualRelationshipUUID(x.generalUUID)),
    	  ("name", taggedTypes.encodeLocalName(x.name))
    )
  }

  @JSExport
  def toJSON(c: SpecializedReifiedRelationship)
  : String
  = encodeSpecializedReifiedRelationship(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : SpecializedReifiedRelationship
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeSpecializedReifiedRelationship(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
