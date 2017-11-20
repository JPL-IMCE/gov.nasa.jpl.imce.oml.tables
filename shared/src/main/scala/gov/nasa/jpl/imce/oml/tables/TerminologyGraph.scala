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
@JSExportTopLevel("TerminologyGraph")
case class TerminologyGraph
(
  @(JSExport @field) uuid: taggedTypes.TerminologyGraphUUID,
  @(JSExport @field) kind: TerminologyKind,
  @(JSExport @field) iri: taggedTypes.IRI
) {
  // Ctor(uuidWithoutContainer)
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    kind: TerminologyKind,
    iri: taggedTypes.IRI)
  = this(
      taggedTypes.terminologyGraphUUID(oug.namespaceUUID(
        iri.toString).toString),
      kind,
      iri)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, kind, iri).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: TerminologyGraph =>
  	  (this.uuid == that.uuid) &&
  	  (this.kind == that.kind) &&
  	  (this.iri == that.iri)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("TerminologyGraphHelper")
object TerminologyGraphHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "TerminologyGraphs.json"

  implicit val decodeTerminologyGraph: Decoder[TerminologyGraph]
  = Decoder.instance[TerminologyGraph] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.TerminologyGraphUUID]
    	  kind <- c.downField("kind").as[TerminologyKind]
    	  iri <- c.downField("iri").as[taggedTypes.IRI]
    	} yield TerminologyGraph(
    	  uuid,
    	  kind,
    	  iri
    	)
  }
  
  implicit val encodeTerminologyGraph: Encoder[TerminologyGraph]
  = new Encoder[TerminologyGraph] {
    override final def apply(x: TerminologyGraph): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeTerminologyGraphUUID(x.uuid)),
    	  ("kind", TerminologyKind.encodeTerminologyKind(x.kind)),
    	  ("iri", taggedTypes.encodeIRI(x.iri))
    )
  }

  @JSExport
  def toJSON(c: TerminologyGraph)
  : String
  = encodeTerminologyGraph(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : TerminologyGraph
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeTerminologyGraph(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
