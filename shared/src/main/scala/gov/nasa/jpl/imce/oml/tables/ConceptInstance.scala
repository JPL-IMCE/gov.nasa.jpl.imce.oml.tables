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
  * @param singletonConceptClassifierUUID[1,1]
  * @param name[1,1]
  */
@JSExportTopLevel("ConceptInstance")
case class ConceptInstance
(
  @(JSExport @field) uuid: taggedTypes.ConceptInstanceUUID,
  @(JSExport @field) descriptionBoxUUID: taggedTypes.DescriptionBoxXRef,
  @(JSExport @field) singletonConceptClassifierUUID: taggedTypes.ConceptXRef,
  @(JSExport @field) name: taggedTypes.LocalName
) {
  // Ctor(uuidWithGenerator)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    descriptionBoxUUID: taggedTypes.DescriptionBoxXRef,
    singletonConceptClassifierUUID: taggedTypes.ConceptXRef,
    name: taggedTypes.LocalName)
  = this(
      taggedTypes.conceptInstanceUUID(oug.namespaceUUID(
        descriptionBoxUUID,
        "name" -> name).toString),
      descriptionBoxUUID,
      singletonConceptClassifierUUID,
      name)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, descriptionBoxUUID, singletonConceptClassifierUUID, name).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ConceptInstance =>
  	  (this.uuid == that.uuid) &&
  	  gov.nasa.jpl.imce.oml.covariantTag.compareTaggedValues(this.descriptionBoxUUID, that.descriptionBoxUUID)  &&
  	  gov.nasa.jpl.imce.oml.covariantTag.compareTaggedValues(this.singletonConceptClassifierUUID, that.singletonConceptClassifierUUID)  &&
  	  (this.name == that.name)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("ConceptInstanceHelper")
object ConceptInstanceHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "ConceptInstances.json"

  implicit val decodeConceptInstance: Decoder[ConceptInstance]
  = Decoder.instance[ConceptInstance] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.ConceptInstanceUUID]
    	  descriptionBoxUUID <- c.downField("descriptionBoxUUID").as[taggedTypes.DescriptionBoxUUID]
    	  singletonConceptClassifierUUID <- c.downField("singletonConceptClassifierUUID").as[taggedTypes.ConceptUUID]
    	  name <- c.downField("name").as[taggedTypes.LocalName]
    	} yield ConceptInstance(
    	  uuid,
    	  descriptionBoxUUID,
    	  singletonConceptClassifierUUID,
    	  name
    	)
  }
  
  implicit val encodeConceptInstance: Encoder[ConceptInstance]
  = new Encoder[ConceptInstance] {
    override final def apply(x: ConceptInstance): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeConceptInstanceUUID(x.uuid)),
    	  ("descriptionBoxUUID", taggedTypes.encodeDescriptionBoxUUID(x.descriptionBoxUUID)),
    	  ("singletonConceptClassifierUUID", taggedTypes.encodeConceptUUID(x.singletonConceptClassifierUUID)),
    	  ("name", taggedTypes.encodeLocalName(x.name))
    )
  }

  @JSExport
  def toJSON(c: ConceptInstance)
  : String
  = encodeConceptInstance(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : ConceptInstance
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeConceptInstance(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
