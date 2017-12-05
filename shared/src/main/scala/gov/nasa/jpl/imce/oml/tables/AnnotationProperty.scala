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
  * @param iri[1,1]
  * @param abbrevIRI[1,1]
  */
@JSExportTopLevel("AnnotationProperty")
case class AnnotationProperty
(
  @(JSExport @field) override val uuid: taggedTypes.AnnotationPropertyUUID,
  @(JSExport @field) val iri: taggedTypes.IRI,
  @(JSExport @field) val abbrevIRI: taggedTypes.AbbrevIRI
) extends IntrinsicIdentityKind with NonLogicalElement {
  // Ctor(uuidWithoutContainer)
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    iri: taggedTypes.IRI,
    abbrevIRI: taggedTypes.AbbrevIRI)
  = this(
      taggedTypes.annotationPropertyUUID(oug.namespaceUUID(
        iri.toString).toString),
      iri,
      abbrevIRI)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, iri, abbrevIRI).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: AnnotationProperty =>
  	  (this.uuid == that.uuid) &&
  	  (this.iri == that.iri) &&
  	  (this.abbrevIRI == that.abbrevIRI)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("AnnotationPropertyHelper")
object AnnotationPropertyHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "AnnotationProperties.json"

  implicit val decodeAnnotationProperty: Decoder[AnnotationProperty]
  = Decoder.instance[AnnotationProperty] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.AnnotationPropertyUUID]
    	  iri <- c.downField("iri").as[taggedTypes.IRI]
    	  abbrevIRI <- c.downField("abbrevIRI").as[taggedTypes.AbbrevIRI]
    	} yield AnnotationProperty(
    	  uuid,
    	  iri,
    	  abbrevIRI
    	)
  }
  
  implicit val encodeAnnotationProperty: Encoder[AnnotationProperty]
  = new Encoder[AnnotationProperty] {
    override final def apply(x: AnnotationProperty): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeAnnotationPropertyUUID(x.uuid)),
    	  ("iri", taggedTypes.encodeIRI(x.iri)),
    	  ("abbrevIRI", taggedTypes.encodeAbbrevIRI(x.abbrevIRI))
    )
  }

  @JSExport
  def toJSON(c: AnnotationProperty)
  : String
  = encodeAnnotationProperty(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : AnnotationProperty
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeAnnotationProperty(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
