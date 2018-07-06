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
  * @param designatedConceptUUID[1,1]
  * @param designatedTerminologyIRI[1,1]
  */
@JSExportTopLevel("ConceptDesignationTerminologyAxiom")
case class ConceptDesignationTerminologyAxiom
(
  @(JSExport @field) override val uuid: taggedTypes.ConceptDesignationTerminologyAxiomUUID,
  @(JSExport @field) override val tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) val designatedConceptUUID: taggedTypes.ConceptKindUUID,
  @(JSExport @field) val designatedTerminologyIRI: taggedTypes.IRI
) extends TerminologyBoxAxiom {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    designatedConceptUUID: taggedTypes.ConceptKindUUID,
    designatedTerminologyIRI: taggedTypes.IRI)
  = this(
      taggedTypes.conceptDesignationTerminologyAxiomUUID(oug.namespaceUUID(
        "ConceptDesignationTerminologyAxiom",
        "tbox" -> tboxUUID,
        "designatedConcept" -> designatedConceptUUID,
        "designatedTerminology" -> oug.namespaceUUID(designatedTerminologyIRI).toString).toString),
      tboxUUID,
      designatedConceptUUID,
      designatedTerminologyIRI)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, designatedConceptUUID, designatedTerminologyIRI).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ConceptDesignationTerminologyAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.designatedConceptUUID == that.designatedConceptUUID)  &&
  	  (this.designatedTerminologyIRI == that.designatedTerminologyIRI)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("ConceptDesignationTerminologyAxiomHelper")
object ConceptDesignationTerminologyAxiomHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "ConceptDesignationTerminologyAxioms.json"

  implicit val decodeConceptDesignationTerminologyAxiom: Decoder[ConceptDesignationTerminologyAxiom]
  = Decoder.instance[ConceptDesignationTerminologyAxiom] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.ConceptDesignationTerminologyAxiomUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  designatedConceptUUID <- c.downField("designatedConceptUUID").as[taggedTypes.ConceptKindUUID]
    	  designatedTerminologyIRI <- c.downField("designatedTerminologyIRI").as[taggedTypes.IRI]
    	} yield ConceptDesignationTerminologyAxiom(
    	  uuid,
    	  tboxUUID,
    	  designatedConceptUUID,
    	  designatedTerminologyIRI
    	)
  }
  
  implicit val encodeConceptDesignationTerminologyAxiom: Encoder[ConceptDesignationTerminologyAxiom]
  = new Encoder[ConceptDesignationTerminologyAxiom] {
    override final def apply(x: ConceptDesignationTerminologyAxiom): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeConceptDesignationTerminologyAxiomUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("designatedConceptUUID", taggedTypes.encodeConceptKindUUID(x.designatedConceptUUID)),
    	  ("designatedTerminologyIRI", taggedTypes.encodeIRI(x.designatedTerminologyIRI))
    )
  }

  @JSExport
  def toJSON(c: ConceptDesignationTerminologyAxiom)
  : String
  = encodeConceptDesignationTerminologyAxiom(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : ConceptDesignationTerminologyAxiom
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeConceptDesignationTerminologyAxiom(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
