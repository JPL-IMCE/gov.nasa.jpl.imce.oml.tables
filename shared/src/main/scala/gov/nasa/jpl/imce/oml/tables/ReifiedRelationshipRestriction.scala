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
  * @param name[1,1]
  */
@JSExportTopLevel("ReifiedRelationshipRestriction")
case class ReifiedRelationshipRestriction
(
  @(JSExport @field) override val uuid: taggedTypes.ReifiedRelationshipRestrictionUUID,
  @(JSExport @field) override val tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) val sourceUUID: taggedTypes.EntityUUID,
  @(JSExport @field) val targetUUID: taggedTypes.EntityUUID,
  @(JSExport @field) override val name: taggedTypes.LocalName
) extends ConceptualRelationship {
  // Ctor(uuidWithGenerator)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    sourceUUID: taggedTypes.EntityUUID,
    targetUUID: taggedTypes.EntityUUID,
    name: taggedTypes.LocalName)
  = this(
      taggedTypes.reifiedRelationshipRestrictionUUID(oug.namespaceUUID(
        tboxUUID,
        "name" -> name).toString),
      tboxUUID,
      sourceUUID,
      targetUUID,
      name)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, sourceUUID, targetUUID, name).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ReifiedRelationshipRestriction =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.sourceUUID == that.sourceUUID)  &&
  	  (this.targetUUID == that.targetUUID)  &&
  	  (this.name == that.name)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("ReifiedRelationshipRestrictionHelper")
object ReifiedRelationshipRestrictionHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "ReifiedRelationshipRestrictions.json"

  implicit val decodeReifiedRelationshipRestriction: Decoder[ReifiedRelationshipRestriction]
  = Decoder.instance[ReifiedRelationshipRestriction] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.ReifiedRelationshipRestrictionUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  sourceUUID <- c.downField("sourceUUID").as[taggedTypes.EntityUUID]
    	  targetUUID <- c.downField("targetUUID").as[taggedTypes.EntityUUID]
    	  name <- c.downField("name").as[taggedTypes.LocalName]
    	} yield ReifiedRelationshipRestriction(
    	  uuid,
    	  tboxUUID,
    	  sourceUUID,
    	  targetUUID,
    	  name
    	)
  }
  
  implicit val encodeReifiedRelationshipRestriction: Encoder[ReifiedRelationshipRestriction]
  = new Encoder[ReifiedRelationshipRestriction] {
    override final def apply(x: ReifiedRelationshipRestriction): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeReifiedRelationshipRestrictionUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("sourceUUID", taggedTypes.encodeEntityUUID(x.sourceUUID)),
    	  ("targetUUID", taggedTypes.encodeEntityUUID(x.targetUUID)),
    	  ("name", taggedTypes.encodeLocalName(x.name))
    )
  }

  @JSExport
  def toJSON(c: ReifiedRelationshipRestriction)
  : String
  = encodeReifiedRelationshipRestriction(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : ReifiedRelationshipRestriction
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeReifiedRelationshipRestriction(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
