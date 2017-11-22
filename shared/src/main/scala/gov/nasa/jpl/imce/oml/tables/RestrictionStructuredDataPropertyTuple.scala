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
  * @param structuredDataPropertyUUID[1,1]
  * @param structuredDataPropertyContextUUID[1,1]
  */
@JSExportTopLevel("RestrictionStructuredDataPropertyTuple")
case class RestrictionStructuredDataPropertyTuple
(
  @(JSExport @field) uuid: taggedTypes.RestrictionStructuredDataPropertyTupleUUID,
  @(JSExport @field) structuredDataPropertyUUID: taggedTypes.DataRelationshipToStructureUUID,
  @(JSExport @field) structuredDataPropertyContextUUID: taggedTypes.RestrictionStructuredDataPropertyContextUUID
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    structuredDataPropertyUUID: taggedTypes.DataRelationshipToStructureUUID,
    structuredDataPropertyContextUUID: taggedTypes.RestrictionStructuredDataPropertyContextUUID)
  = this(
      taggedTypes.restrictionStructuredDataPropertyTupleUUID(oug.namespaceUUID(
        "RestrictionStructuredDataPropertyTuple",
        "structuredDataProperty" -> structuredDataPropertyUUID,
        "structuredDataPropertyContext" -> structuredDataPropertyContextUUID).toString),
      structuredDataPropertyUUID,
      structuredDataPropertyContextUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, structuredDataPropertyUUID, structuredDataPropertyContextUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: RestrictionStructuredDataPropertyTuple =>
  	  (this.uuid == that.uuid) &&
  	  (this.structuredDataPropertyUUID == that.structuredDataPropertyUUID)  &&
  	  (this.structuredDataPropertyContextUUID == that.structuredDataPropertyContextUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("RestrictionStructuredDataPropertyTupleHelper")
object RestrictionStructuredDataPropertyTupleHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "RestrictionStructuredDataPropertyTuples.json"

  implicit val decodeRestrictionStructuredDataPropertyTuple: Decoder[RestrictionStructuredDataPropertyTuple]
  = Decoder.instance[RestrictionStructuredDataPropertyTuple] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.RestrictionStructuredDataPropertyTupleUUID]
    	  structuredDataPropertyUUID <- c.downField("structuredDataPropertyUUID").as[taggedTypes.DataRelationshipToStructureUUID]
    	  structuredDataPropertyContextUUID <- c.downField("structuredDataPropertyContextUUID").as[taggedTypes.RestrictionStructuredDataPropertyContextUUID]
    	} yield RestrictionStructuredDataPropertyTuple(
    	  uuid,
    	  structuredDataPropertyUUID,
    	  structuredDataPropertyContextUUID
    	)
  }
  
  implicit val encodeRestrictionStructuredDataPropertyTuple: Encoder[RestrictionStructuredDataPropertyTuple]
  = new Encoder[RestrictionStructuredDataPropertyTuple] {
    override final def apply(x: RestrictionStructuredDataPropertyTuple): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeRestrictionStructuredDataPropertyTupleUUID(x.uuid)),
    	  ("structuredDataPropertyUUID", taggedTypes.encodeDataRelationshipToStructureUUID(x.structuredDataPropertyUUID)),
    	  ("structuredDataPropertyContextUUID", taggedTypes.encodeRestrictionStructuredDataPropertyContextUUID(x.structuredDataPropertyContextUUID))
    )
  }

  @JSExport
  def toJSON(c: RestrictionStructuredDataPropertyTuple)
  : String
  = encodeRestrictionStructuredDataPropertyTuple(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : RestrictionStructuredDataPropertyTuple
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeRestrictionStructuredDataPropertyTuple(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
