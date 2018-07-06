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
  * @param superConceptUUID[1,1]
  * @param subConceptUUID[1,1]
  */
@JSExportTopLevel("ConceptSpecializationAxiom")
case class ConceptSpecializationAxiom
(
  @(JSExport @field) override val uuid: taggedTypes.ConceptSpecializationAxiomUUID,
  @(JSExport @field) override val tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) val superConceptUUID: taggedTypes.ConceptKindUUID,
  @(JSExport @field) val subConceptUUID: taggedTypes.ConceptKindUUID
) extends SpecializationAxiom {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    superConceptUUID: taggedTypes.ConceptKindUUID,
    subConceptUUID: taggedTypes.ConceptKindUUID)
  = this(
      taggedTypes.conceptSpecializationAxiomUUID(oug.namespaceUUID(
        "ConceptSpecializationAxiom",
        "tbox" -> tboxUUID,
        "superConcept" -> superConceptUUID,
        "subConcept" -> subConceptUUID).toString),
      tboxUUID,
      superConceptUUID,
      subConceptUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, superConceptUUID, subConceptUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ConceptSpecializationAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.superConceptUUID == that.superConceptUUID)  &&
  	  (this.subConceptUUID == that.subConceptUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("ConceptSpecializationAxiomHelper")
object ConceptSpecializationAxiomHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "ConceptSpecializationAxioms.json"

  implicit val decodeConceptSpecializationAxiom: Decoder[ConceptSpecializationAxiom]
  = Decoder.instance[ConceptSpecializationAxiom] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.ConceptSpecializationAxiomUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  superConceptUUID <- c.downField("superConceptUUID").as[taggedTypes.ConceptKindUUID]
    	  subConceptUUID <- c.downField("subConceptUUID").as[taggedTypes.ConceptKindUUID]
    	} yield ConceptSpecializationAxiom(
    	  uuid,
    	  tboxUUID,
    	  superConceptUUID,
    	  subConceptUUID
    	)
  }
  
  implicit val encodeConceptSpecializationAxiom: Encoder[ConceptSpecializationAxiom]
  = new Encoder[ConceptSpecializationAxiom] {
    override final def apply(x: ConceptSpecializationAxiom): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeConceptSpecializationAxiomUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("superConceptUUID", taggedTypes.encodeConceptKindUUID(x.superConceptUUID)),
    	  ("subConceptUUID", taggedTypes.encodeConceptKindUUID(x.subConceptUUID))
    )
  }

  @JSExport
  def toJSON(c: ConceptSpecializationAxiom)
  : String
  = encodeConceptSpecializationAxiom(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : ConceptSpecializationAxiom
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeConceptSpecializationAxiom(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
