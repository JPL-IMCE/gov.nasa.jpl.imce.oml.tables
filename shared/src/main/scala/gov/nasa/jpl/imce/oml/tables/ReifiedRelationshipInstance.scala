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
  * @param singletonReifiedRelationshipClassifierUUID[1,1]
  * @param name[1,1]
  */
@JSExportTopLevel("ReifiedRelationshipInstance")
case class ReifiedRelationshipInstance
(
  @(JSExport @field) uuid: taggedTypes.ReifiedRelationshipInstanceUUID,
  @(JSExport @field) descriptionBoxUUID: taggedTypes.DescriptionBoxUUID,
  @(JSExport @field) singletonReifiedRelationshipClassifierUUID: taggedTypes.ReifiedRelationshipUUID,
  @(JSExport @field) name: taggedTypes.LocalName
) {
  // Ctor(uuidWithGenerator)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    descriptionBoxUUID: taggedTypes.DescriptionBoxUUID,
    singletonReifiedRelationshipClassifierUUID: taggedTypes.ReifiedRelationshipUUID,
    name: taggedTypes.LocalName)
  = this(
      taggedTypes.reifiedRelationshipInstanceUUID(oug.namespaceUUID(
        descriptionBoxUUID,
        "name" -> name).toString),
      descriptionBoxUUID,
      singletonReifiedRelationshipClassifierUUID,
      name)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, descriptionBoxUUID, singletonReifiedRelationshipClassifierUUID, name).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ReifiedRelationshipInstance =>
  	  (this.uuid == that.uuid) &&
  	  (this.descriptionBoxUUID == that.descriptionBoxUUID)  &&
  	  (this.singletonReifiedRelationshipClassifierUUID == that.singletonReifiedRelationshipClassifierUUID)  &&
  	  (this.name == that.name)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("ReifiedRelationshipInstanceHelper")
object ReifiedRelationshipInstanceHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "ReifiedRelationshipInstances.json"

  implicit val decodeReifiedRelationshipInstance: Decoder[ReifiedRelationshipInstance]
  = Decoder.instance[ReifiedRelationshipInstance] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.ReifiedRelationshipInstanceUUID]
    	  descriptionBoxUUID <- c.downField("descriptionBoxUUID").as[taggedTypes.DescriptionBoxUUID]
    	  singletonReifiedRelationshipClassifierUUID <- c.downField("singletonReifiedRelationshipClassifierUUID").as[taggedTypes.ReifiedRelationshipUUID]
    	  name <- c.downField("name").as[taggedTypes.LocalName]
    	} yield ReifiedRelationshipInstance(
    	  uuid,
    	  descriptionBoxUUID,
    	  singletonReifiedRelationshipClassifierUUID,
    	  name
    	)
  }
  
  implicit val encodeReifiedRelationshipInstance: Encoder[ReifiedRelationshipInstance]
  = new Encoder[ReifiedRelationshipInstance] {
    override final def apply(x: ReifiedRelationshipInstance): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeReifiedRelationshipInstanceUUID(x.uuid)),
    	  ("descriptionBoxUUID", taggedTypes.encodeDescriptionBoxUUID(x.descriptionBoxUUID)),
    	  ("singletonReifiedRelationshipClassifierUUID", taggedTypes.encodeReifiedRelationshipUUID(x.singletonReifiedRelationshipClassifierUUID)),
    	  ("name", taggedTypes.encodeLocalName(x.name))
    )
  }

  @JSExport
  def toJSON(c: ReifiedRelationshipInstance)
  : String
  = encodeReifiedRelationshipInstance(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : ReifiedRelationshipInstance
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeReifiedRelationshipInstance(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
