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
  * @param tboxUUID[1,1]
  * @param extendedTerminologyIRI[1,1]
  */
@JSExportTopLevel("TerminologyExtensionAxiom")
case class TerminologyExtensionAxiom
(
  @(JSExport @field) uuid: taggedTypes.TerminologyExtensionAxiomUUID,
  @(JSExport @field) tboxUUID: taggedTypes.TerminologyBoxXRef,
  @(JSExport @field) extendedTerminologyIRI: taggedTypes.IRI
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxXRef,
    extendedTerminologyIRI: taggedTypes.IRI)
  = this(
      taggedTypes.terminologyExtensionAxiomUUID(oug.namespaceUUID(
        "TerminologyExtensionAxiom",
        "tbox" -> tboxUUID,
        "extendedTerminology" -> oug.namespaceUUID(extendedTerminologyIRI).toString).toString),
      tboxUUID,
      extendedTerminologyIRI)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, extendedTerminologyIRI).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: TerminologyExtensionAxiom =>
  	  (this.uuid == that.uuid) &&
  	  gov.nasa.jpl.imce.oml.covariantTag.compareTaggedValues(this.tboxUUID, that.tboxUUID)  &&
  	  (this.extendedTerminologyIRI == that.extendedTerminologyIRI)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("TerminologyExtensionAxiomHelper")
object TerminologyExtensionAxiomHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "TerminologyExtensionAxioms.json"

  implicit val decodeTerminologyExtensionAxiom: Decoder[TerminologyExtensionAxiom]
  = Decoder.instance[TerminologyExtensionAxiom] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.TerminologyExtensionAxiomUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  extendedTerminologyIRI <- c.downField("extendedTerminologyIRI").as[taggedTypes.IRI]
    	} yield TerminologyExtensionAxiom(
    	  uuid,
    	  tboxUUID,
    	  extendedTerminologyIRI
    	)
  }
  
  implicit val encodeTerminologyExtensionAxiom: Encoder[TerminologyExtensionAxiom]
  = new Encoder[TerminologyExtensionAxiom] {
    override final def apply(x: TerminologyExtensionAxiom): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeTerminologyExtensionAxiomUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("extendedTerminologyIRI", taggedTypes.encodeIRI(x.extendedTerminologyIRI))
    )
  }

  @JSExport
  def toJSON(c: TerminologyExtensionAxiom)
  : String
  = encodeTerminologyExtensionAxiom(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : TerminologyExtensionAxiom
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeTerminologyExtensionAxiom(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
