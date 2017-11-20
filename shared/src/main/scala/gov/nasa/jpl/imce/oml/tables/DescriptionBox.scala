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

/**
  * @param uuid[1,1]
  * @param kind[1,1]
  * @param iri[1,1]
  */
@JSExportTopLevel("DescriptionBox")
case class DescriptionBox
(
  @(JSExport @field) uuid: taggedTypes.DescriptionBoxUUID,
  @(JSExport @field) kind: DescriptionKind,
  @(JSExport @field) iri: taggedTypes.IRI
) {
  // Ctor(uuidWithoutContainer)
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    kind: DescriptionKind,
    iri: taggedTypes.IRI)
  = this(
      taggedTypes.descriptionBoxUUID(oug.namespaceUUID(
        iri.toString).toString),
      kind,
      iri)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, kind, iri).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: DescriptionBox =>
  	  (this.uuid == that.uuid) &&
  	  (this.kind == that.kind) &&
  	  (this.iri == that.iri)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("DescriptionBoxHelper")
object DescriptionBoxHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "DescriptionBoxes.json"

  implicit val decodeDescriptionBox: Decoder[DescriptionBox]
  = Decoder.instance[DescriptionBox] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.DescriptionBoxUUID]
    	  kind <- c.downField("kind").as[DescriptionKind]
    	  iri <- c.downField("iri").as[taggedTypes.IRI]
    	} yield DescriptionBox(
    	  uuid,
    	  kind,
    	  iri
    	)
  }
  
  implicit val encodeDescriptionBox: Encoder[DescriptionBox]
  = new Encoder[DescriptionBox] {
    override final def apply(x: DescriptionBox): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeDescriptionBoxUUID(x.uuid)),
    	  ("kind", DescriptionKind.encodeDescriptionKind(x.kind)),
    	  ("iri", taggedTypes.encodeIRI(x.iri))
    )
  }

  @JSExport
  def toJSON(c: DescriptionBox)
  : String
  = encodeDescriptionBox(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : DescriptionBox
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeDescriptionBox(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
