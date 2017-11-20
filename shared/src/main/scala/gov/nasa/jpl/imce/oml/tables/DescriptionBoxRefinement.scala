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
  * @param refiningDescriptionBoxUUID[1,1]
  * @param refinedDescriptionBoxIRI[1,1]
  */
@JSExportTopLevel("DescriptionBoxRefinement")
case class DescriptionBoxRefinement
(
  @(JSExport @field) uuid: taggedTypes.DescriptionBoxRefinementUUID,
  @(JSExport @field) refiningDescriptionBoxUUID: taggedTypes.DescriptionBoxXRef,
  @(JSExport @field) refinedDescriptionBoxIRI: taggedTypes.IRI
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    refiningDescriptionBoxUUID: taggedTypes.DescriptionBoxXRef,
    refinedDescriptionBoxIRI: taggedTypes.IRI)
  = this(
      taggedTypes.descriptionBoxRefinementUUID(oug.namespaceUUID(
        "DescriptionBoxRefinement",
        "refiningDescriptionBox" -> refiningDescriptionBoxUUID,
        "refinedDescriptionBox" -> oug.namespaceUUID(refinedDescriptionBoxIRI).toString).toString),
      refiningDescriptionBoxUUID,
      refinedDescriptionBoxIRI)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, refiningDescriptionBoxUUID, refinedDescriptionBoxIRI).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: DescriptionBoxRefinement =>
  	  (this.uuid == that.uuid) &&
  	  gov.nasa.jpl.imce.oml.covariantTag.compareTaggedValues(this.refiningDescriptionBoxUUID, that.refiningDescriptionBoxUUID)  &&
  	  (this.refinedDescriptionBoxIRI == that.refinedDescriptionBoxIRI)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("DescriptionBoxRefinementHelper")
object DescriptionBoxRefinementHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "DescriptionBoxRefinements.json"

  implicit val decodeDescriptionBoxRefinement: Decoder[DescriptionBoxRefinement]
  = Decoder.instance[DescriptionBoxRefinement] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.DescriptionBoxRefinementUUID]
    	  refiningDescriptionBoxUUID <- c.downField("refiningDescriptionBoxUUID").as[taggedTypes.DescriptionBoxUUID]
    	  refinedDescriptionBoxIRI <- c.downField("refinedDescriptionBoxIRI").as[taggedTypes.IRI]
    	} yield DescriptionBoxRefinement(
    	  uuid,
    	  refiningDescriptionBoxUUID,
    	  refinedDescriptionBoxIRI
    	)
  }
  
  implicit val encodeDescriptionBoxRefinement: Encoder[DescriptionBoxRefinement]
  = new Encoder[DescriptionBoxRefinement] {
    override final def apply(x: DescriptionBoxRefinement): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeDescriptionBoxRefinementUUID(x.uuid)),
    	  ("refiningDescriptionBoxUUID", taggedTypes.encodeDescriptionBoxUUID(x.refiningDescriptionBoxUUID)),
    	  ("refinedDescriptionBoxIRI", taggedTypes.encodeIRI(x.refinedDescriptionBoxIRI))
    )
  }

  @JSExport
  def toJSON(c: DescriptionBoxRefinement)
  : String
  = encodeDescriptionBoxRefinement(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : DescriptionBoxRefinement
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeDescriptionBoxRefinement(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
