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
  * @param nestingContextUUID[1,1]
  * @param nestingTerminologyIRI[1,1]
  */
@JSExportTopLevel("TerminologyNestingAxiom")
case class TerminologyNestingAxiom
(
  @(JSExport @field) override val uuid: taggedTypes.TerminologyNestingAxiomUUID,
  @(JSExport @field) override val tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) val nestingContextUUID: taggedTypes.ConceptUUID,
  @(JSExport @field) val nestingTerminologyIRI: taggedTypes.IRI
) extends TerminologyBoxAxiom {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    nestingContextUUID: taggedTypes.ConceptUUID,
    nestingTerminologyIRI: taggedTypes.IRI)
  = this(
      taggedTypes.terminologyNestingAxiomUUID(oug.namespaceUUID(
        "TerminologyNestingAxiom",
        "tbox" -> tboxUUID,
        "nestingContext" -> nestingContextUUID,
        "nestingTerminology" -> oug.namespaceUUID(nestingTerminologyIRI).toString).toString),
      tboxUUID,
      nestingContextUUID,
      nestingTerminologyIRI)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, nestingContextUUID, nestingTerminologyIRI).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: TerminologyNestingAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.nestingContextUUID == that.nestingContextUUID)  &&
  	  (this.nestingTerminologyIRI == that.nestingTerminologyIRI)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("TerminologyNestingAxiomHelper")
object TerminologyNestingAxiomHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "TerminologyNestingAxioms.json"

  implicit val decodeTerminologyNestingAxiom: Decoder[TerminologyNestingAxiom]
  = Decoder.instance[TerminologyNestingAxiom] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.TerminologyNestingAxiomUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  nestingContextUUID <- c.downField("nestingContextUUID").as[taggedTypes.ConceptUUID]
    	  nestingTerminologyIRI <- c.downField("nestingTerminologyIRI").as[taggedTypes.IRI]
    	} yield TerminologyNestingAxiom(
    	  uuid,
    	  tboxUUID,
    	  nestingContextUUID,
    	  nestingTerminologyIRI
    	)
  }
  
  implicit val encodeTerminologyNestingAxiom: Encoder[TerminologyNestingAxiom]
  = new Encoder[TerminologyNestingAxiom] {
    override final def apply(x: TerminologyNestingAxiom): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeTerminologyNestingAxiomUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("nestingContextUUID", taggedTypes.encodeConceptUUID(x.nestingContextUUID)),
    	  ("nestingTerminologyIRI", taggedTypes.encodeIRI(x.nestingTerminologyIRI))
    )
  }

  @JSExport
  def toJSON(c: TerminologyNestingAxiom)
  : String
  = encodeTerminologyNestingAxiom(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : TerminologyNestingAxiom
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeTerminologyNestingAxiom(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
