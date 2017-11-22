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
  * @param descriptionBoxUUID[1,1]
  * @param reifiedRelationshipInstanceUUID[1,1]
  * @param rangeUUID[1,1]
  */
@JSExportTopLevel("ReifiedRelationshipInstanceRange")
case class ReifiedRelationshipInstanceRange
(
  @(JSExport @field) uuid: taggedTypes.ReifiedRelationshipInstanceRangeUUID,
  @(JSExport @field) descriptionBoxUUID: taggedTypes.DescriptionBoxUUID,
  @(JSExport @field) reifiedRelationshipInstanceUUID: taggedTypes.ReifiedRelationshipInstanceUUID,
  @(JSExport @field) rangeUUID: taggedTypes.ConceptualEntitySingletonInstanceUUID
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    descriptionBoxUUID: taggedTypes.DescriptionBoxUUID,
    reifiedRelationshipInstanceUUID: taggedTypes.ReifiedRelationshipInstanceUUID,
    rangeUUID: taggedTypes.ConceptualEntitySingletonInstanceUUID)
  = this(
      taggedTypes.reifiedRelationshipInstanceRangeUUID(oug.namespaceUUID(
        "ReifiedRelationshipInstanceRange",
        "descriptionBox" -> descriptionBoxUUID,
        "reifiedRelationshipInstance" -> reifiedRelationshipInstanceUUID,
        "range" -> rangeUUID).toString),
      descriptionBoxUUID,
      reifiedRelationshipInstanceUUID,
      rangeUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, descriptionBoxUUID, reifiedRelationshipInstanceUUID, rangeUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ReifiedRelationshipInstanceRange =>
  	  (this.uuid == that.uuid) &&
  	  (this.descriptionBoxUUID == that.descriptionBoxUUID)  &&
  	  (this.reifiedRelationshipInstanceUUID == that.reifiedRelationshipInstanceUUID)  &&
  	  (this.rangeUUID == that.rangeUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("ReifiedRelationshipInstanceRangeHelper")
object ReifiedRelationshipInstanceRangeHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "ReifiedRelationshipInstanceRanges.json"

  implicit val decodeReifiedRelationshipInstanceRange: Decoder[ReifiedRelationshipInstanceRange]
  = Decoder.instance[ReifiedRelationshipInstanceRange] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.ReifiedRelationshipInstanceRangeUUID]
    	  descriptionBoxUUID <- c.downField("descriptionBoxUUID").as[taggedTypes.DescriptionBoxUUID]
    	  reifiedRelationshipInstanceUUID <- c.downField("reifiedRelationshipInstanceUUID").as[taggedTypes.ReifiedRelationshipInstanceUUID]
    	  rangeUUID <- c.downField("rangeUUID").as[taggedTypes.ConceptualEntitySingletonInstanceUUID]
    	} yield ReifiedRelationshipInstanceRange(
    	  uuid,
    	  descriptionBoxUUID,
    	  reifiedRelationshipInstanceUUID,
    	  rangeUUID
    	)
  }
  
  implicit val encodeReifiedRelationshipInstanceRange: Encoder[ReifiedRelationshipInstanceRange]
  = new Encoder[ReifiedRelationshipInstanceRange] {
    override final def apply(x: ReifiedRelationshipInstanceRange): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeReifiedRelationshipInstanceRangeUUID(x.uuid)),
    	  ("descriptionBoxUUID", taggedTypes.encodeDescriptionBoxUUID(x.descriptionBoxUUID)),
    	  ("reifiedRelationshipInstanceUUID", taggedTypes.encodeReifiedRelationshipInstanceUUID(x.reifiedRelationshipInstanceUUID)),
    	  ("rangeUUID", taggedTypes.encodeConceptualEntitySingletonInstanceUUID(x.rangeUUID))
    )
  }

  @JSExport
  def toJSON(c: ReifiedRelationshipInstanceRange)
  : String
  = encodeReifiedRelationshipInstanceRange(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : ReifiedRelationshipInstanceRange
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeReifiedRelationshipInstanceRange(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
