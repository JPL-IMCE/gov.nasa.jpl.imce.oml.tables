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
  * @param closedWorldDefinitionsIRI[1,1]
  */
@JSExportTopLevel("DescriptionBoxExtendsClosedWorldDefinitions")
case class DescriptionBoxExtendsClosedWorldDefinitions
(
  @(JSExport @field) override val uuid: taggedTypes.DescriptionBoxExtendsClosedWorldDefinitionsUUID,
  @(JSExport @field) val descriptionBoxUUID: taggedTypes.DescriptionBoxUUID,
  @(JSExport @field) val closedWorldDefinitionsIRI: taggedTypes.IRI
) extends DescriptionBoxRelationship {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    descriptionBoxUUID: taggedTypes.DescriptionBoxUUID,
    closedWorldDefinitionsIRI: taggedTypes.IRI)
  = this(
      taggedTypes.descriptionBoxExtendsClosedWorldDefinitionsUUID(oug.namespaceUUID(
        "DescriptionBoxExtendsClosedWorldDefinitions",
        "descriptionBox" -> descriptionBoxUUID,
        "closedWorldDefinitions" -> oug.namespaceUUID(closedWorldDefinitionsIRI).toString).toString),
      descriptionBoxUUID,
      closedWorldDefinitionsIRI)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, descriptionBoxUUID, closedWorldDefinitionsIRI).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: DescriptionBoxExtendsClosedWorldDefinitions =>
  	  (this.uuid == that.uuid) &&
  	  (this.descriptionBoxUUID == that.descriptionBoxUUID)  &&
  	  (this.closedWorldDefinitionsIRI == that.closedWorldDefinitionsIRI)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("DescriptionBoxExtendsClosedWorldDefinitionsHelper")
object DescriptionBoxExtendsClosedWorldDefinitionsHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "DescriptionBoxExtendsClosedWorldDefinitions.json"

  implicit val decodeDescriptionBoxExtendsClosedWorldDefinitions: Decoder[DescriptionBoxExtendsClosedWorldDefinitions]
  = Decoder.instance[DescriptionBoxExtendsClosedWorldDefinitions] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.DescriptionBoxExtendsClosedWorldDefinitionsUUID]
    	  descriptionBoxUUID <- c.downField("descriptionBoxUUID").as[taggedTypes.DescriptionBoxUUID]
    	  closedWorldDefinitionsIRI <- c.downField("closedWorldDefinitionsIRI").as[taggedTypes.IRI]
    	} yield DescriptionBoxExtendsClosedWorldDefinitions(
    	  uuid,
    	  descriptionBoxUUID,
    	  closedWorldDefinitionsIRI
    	)
  }
  
  implicit val encodeDescriptionBoxExtendsClosedWorldDefinitions: Encoder[DescriptionBoxExtendsClosedWorldDefinitions]
  = new Encoder[DescriptionBoxExtendsClosedWorldDefinitions] {
    override final def apply(x: DescriptionBoxExtendsClosedWorldDefinitions): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeDescriptionBoxExtendsClosedWorldDefinitionsUUID(x.uuid)),
    	  ("descriptionBoxUUID", taggedTypes.encodeDescriptionBoxUUID(x.descriptionBoxUUID)),
    	  ("closedWorldDefinitionsIRI", taggedTypes.encodeIRI(x.closedWorldDefinitionsIRI))
    )
  }

  @JSExport
  def toJSON(c: DescriptionBoxExtendsClosedWorldDefinitions)
  : String
  = encodeDescriptionBoxExtendsClosedWorldDefinitions(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : DescriptionBoxExtendsClosedWorldDefinitions
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeDescriptionBoxExtendsClosedWorldDefinitions(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
