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
  * @param structuredDataPropertyContextUUID[1,1]
  * @param structuredDataPropertyUUID[1,1]
  */
@JSExportTopLevel("StructuredDataPropertyTuple")
case class StructuredDataPropertyTuple
(
  @(JSExport @field) override val uuid: taggedTypes.StructuredDataPropertyTupleUUID,
  @(JSExport @field) val structuredDataPropertyContextUUID: taggedTypes.SingletonInstanceStructuredDataPropertyContextUUID,
  @(JSExport @field) override val structuredDataPropertyUUID: taggedTypes.DataRelationshipToStructureUUID
) extends SingletonInstanceStructuredDataPropertyContext {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    structuredDataPropertyContextUUID: taggedTypes.SingletonInstanceStructuredDataPropertyContextUUID,
    structuredDataPropertyUUID: taggedTypes.DataRelationshipToStructureUUID)
  = this(
      taggedTypes.structuredDataPropertyTupleUUID(oug.namespaceUUID(
        "StructuredDataPropertyTuple",
        "structuredDataPropertyContext" -> structuredDataPropertyContextUUID,
        "structuredDataProperty" -> structuredDataPropertyUUID).toString),
      structuredDataPropertyContextUUID,
      structuredDataPropertyUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, structuredDataPropertyContextUUID, structuredDataPropertyUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: StructuredDataPropertyTuple =>
  	  (this.uuid == that.uuid) &&
  	  (this.structuredDataPropertyContextUUID == that.structuredDataPropertyContextUUID)  &&
  	  (this.structuredDataPropertyUUID == that.structuredDataPropertyUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("StructuredDataPropertyTupleHelper")
object StructuredDataPropertyTupleHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "StructuredDataPropertyTuples.json"

  implicit val decodeStructuredDataPropertyTuple: Decoder[StructuredDataPropertyTuple]
  = Decoder.instance[StructuredDataPropertyTuple] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.StructuredDataPropertyTupleUUID]
    	  structuredDataPropertyContextUUID <- c.downField("structuredDataPropertyContextUUID").as[taggedTypes.SingletonInstanceStructuredDataPropertyContextUUID]
    	  structuredDataPropertyUUID <- c.downField("structuredDataPropertyUUID").as[taggedTypes.DataRelationshipToStructureUUID]
    	} yield StructuredDataPropertyTuple(
    	  uuid,
    	  structuredDataPropertyContextUUID,
    	  structuredDataPropertyUUID
    	)
  }
  
  implicit val encodeStructuredDataPropertyTuple: Encoder[StructuredDataPropertyTuple]
  = new Encoder[StructuredDataPropertyTuple] {
    override final def apply(x: StructuredDataPropertyTuple): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeStructuredDataPropertyTupleUUID(x.uuid)),
    	  ("structuredDataPropertyContextUUID", taggedTypes.encodeSingletonInstanceStructuredDataPropertyContextUUID(x.structuredDataPropertyContextUUID)),
    	  ("structuredDataPropertyUUID", taggedTypes.encodeDataRelationshipToStructureUUID(x.structuredDataPropertyUUID))
    )
  }

  @JSExport
  def toJSON(c: StructuredDataPropertyTuple)
  : String
  = encodeStructuredDataPropertyTuple(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : StructuredDataPropertyTuple
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeStructuredDataPropertyTuple(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
