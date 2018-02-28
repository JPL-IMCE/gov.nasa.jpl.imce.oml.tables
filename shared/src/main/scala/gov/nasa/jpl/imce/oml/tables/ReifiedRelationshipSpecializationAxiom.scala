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
  * @param superRelationshipUUID[1,1]
  * @param subRelationshipUUID[1,1]
  */
@JSExportTopLevel("ReifiedRelationshipSpecializationAxiom")
case class ReifiedRelationshipSpecializationAxiom
(
  @(JSExport @field) override val uuid: taggedTypes.ReifiedRelationshipSpecializationAxiomUUID,
  @(JSExport @field) override val tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) val superRelationshipUUID: taggedTypes.ConceptualRelationshipUUID,
  @(JSExport @field) val subRelationshipUUID: taggedTypes.ConceptualRelationshipUUID
) extends SpecializationAxiom {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    superRelationshipUUID: taggedTypes.ConceptualRelationshipUUID,
    subRelationshipUUID: taggedTypes.ConceptualRelationshipUUID)
  = this(
      taggedTypes.reifiedRelationshipSpecializationAxiomUUID(oug.namespaceUUID(
        "ReifiedRelationshipSpecializationAxiom",
        "tbox" -> tboxUUID,
        "superRelationship" -> superRelationshipUUID,
        "subRelationship" -> subRelationshipUUID).toString),
      tboxUUID,
      superRelationshipUUID,
      subRelationshipUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, superRelationshipUUID, subRelationshipUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ReifiedRelationshipSpecializationAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.superRelationshipUUID == that.superRelationshipUUID)  &&
  	  (this.subRelationshipUUID == that.subRelationshipUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("ReifiedRelationshipSpecializationAxiomHelper")
object ReifiedRelationshipSpecializationAxiomHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "ReifiedRelationshipSpecializationAxioms.json"

  implicit val decodeReifiedRelationshipSpecializationAxiom: Decoder[ReifiedRelationshipSpecializationAxiom]
  = Decoder.instance[ReifiedRelationshipSpecializationAxiom] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.ReifiedRelationshipSpecializationAxiomUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  superRelationshipUUID <- c.downField("superRelationshipUUID").as[taggedTypes.ConceptualRelationshipUUID]
    	  subRelationshipUUID <- c.downField("subRelationshipUUID").as[taggedTypes.ConceptualRelationshipUUID]
    	} yield ReifiedRelationshipSpecializationAxiom(
    	  uuid,
    	  tboxUUID,
    	  superRelationshipUUID,
    	  subRelationshipUUID
    	)
  }
  
  implicit val encodeReifiedRelationshipSpecializationAxiom: Encoder[ReifiedRelationshipSpecializationAxiom]
  = new Encoder[ReifiedRelationshipSpecializationAxiom] {
    override final def apply(x: ReifiedRelationshipSpecializationAxiom): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeReifiedRelationshipSpecializationAxiomUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("superRelationshipUUID", taggedTypes.encodeConceptualRelationshipUUID(x.superRelationshipUUID)),
    	  ("subRelationshipUUID", taggedTypes.encodeConceptualRelationshipUUID(x.subRelationshipUUID))
    )
  }

  @JSExport
  def toJSON(c: ReifiedRelationshipSpecializationAxiom)
  : String
  = encodeReifiedRelationshipSpecializationAxiom(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : ReifiedRelationshipSpecializationAxiom
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeReifiedRelationshipSpecializationAxiom(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
