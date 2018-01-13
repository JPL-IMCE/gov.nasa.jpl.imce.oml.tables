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
  * @param name[1,1]
  * @param reifiedRelationshipUUID[1,1]
  */
@JSExportTopLevel("ForwardProperty")
case class ForwardProperty
(
  @(JSExport @field) override val uuid: taggedTypes.ForwardPropertyUUID,
  @(JSExport @field) val name: taggedTypes.LocalName,
  @(JSExport @field) val reifiedRelationshipUUID: taggedTypes.ReifiedRelationshipUUID
) extends Resource {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    name: taggedTypes.LocalName,
    reifiedRelationshipUUID: taggedTypes.ReifiedRelationshipUUID)
  = this(
      taggedTypes.forwardPropertyUUID(oug.namespaceUUID(
        "ForwardProperty",
        "name" -> name.value,
        "reifiedRelationship" -> reifiedRelationshipUUID).toString),
      name,
      reifiedRelationshipUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, name, reifiedRelationshipUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ForwardProperty =>
  	  (this.uuid == that.uuid) &&
  	  (this.name == that.name) &&
  	  (this.reifiedRelationshipUUID == that.reifiedRelationshipUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("ForwardPropertyHelper")
object ForwardPropertyHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "ForwardProperties.json"

  implicit val decodeForwardProperty: Decoder[ForwardProperty]
  = Decoder.instance[ForwardProperty] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.ForwardPropertyUUID]
    	  name <- c.downField("name").as[taggedTypes.LocalName]
    	  reifiedRelationshipUUID <- c.downField("reifiedRelationshipUUID").as[taggedTypes.ReifiedRelationshipUUID]
    	} yield ForwardProperty(
    	  uuid,
    	  name,
    	  reifiedRelationshipUUID
    	)
  }
  
  implicit val encodeForwardProperty: Encoder[ForwardProperty]
  = new Encoder[ForwardProperty] {
    override final def apply(x: ForwardProperty): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeForwardPropertyUUID(x.uuid)),
    	  ("name", taggedTypes.encodeLocalName(x.name)),
    	  ("reifiedRelationshipUUID", taggedTypes.encodeReifiedRelationshipUUID(x.reifiedRelationshipUUID))
    )
  }

  @JSExport
  def toJSON(c: ForwardProperty)
  : String
  = encodeForwardProperty(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : ForwardProperty
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeForwardProperty(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
