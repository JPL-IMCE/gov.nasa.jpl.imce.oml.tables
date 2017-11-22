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
  * @param superAspectUUID[1,1]
  * @param subEntityUUID[1,1]
  */
@JSExportTopLevel("AspectSpecializationAxiom")
case class AspectSpecializationAxiom
(
  @(JSExport @field) uuid: taggedTypes.AspectSpecializationAxiomUUID,
  @(JSExport @field) tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) superAspectUUID: taggedTypes.AspectUUID,
  @(JSExport @field) subEntityUUID: taggedTypes.EntityUUID
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    superAspectUUID: taggedTypes.AspectUUID,
    subEntityUUID: taggedTypes.EntityUUID)
  = this(
      taggedTypes.aspectSpecializationAxiomUUID(oug.namespaceUUID(
        "AspectSpecializationAxiom",
        "tbox" -> tboxUUID,
        "superAspect" -> superAspectUUID,
        "subEntity" -> subEntityUUID).toString),
      tboxUUID,
      superAspectUUID,
      subEntityUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, superAspectUUID, subEntityUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: AspectSpecializationAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.superAspectUUID == that.superAspectUUID)  &&
  	  (this.subEntityUUID == that.subEntityUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("AspectSpecializationAxiomHelper")
object AspectSpecializationAxiomHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "AspectSpecializationAxioms.json"

  implicit val decodeAspectSpecializationAxiom: Decoder[AspectSpecializationAxiom]
  = Decoder.instance[AspectSpecializationAxiom] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.AspectSpecializationAxiomUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  superAspectUUID <- c.downField("superAspectUUID").as[taggedTypes.AspectUUID]
    	  subEntityUUID <- c.downField("subEntityUUID").as[taggedTypes.EntityUUID]
    	} yield AspectSpecializationAxiom(
    	  uuid,
    	  tboxUUID,
    	  superAspectUUID,
    	  subEntityUUID
    	)
  }
  
  implicit val encodeAspectSpecializationAxiom: Encoder[AspectSpecializationAxiom]
  = new Encoder[AspectSpecializationAxiom] {
    override final def apply(x: AspectSpecializationAxiom): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeAspectSpecializationAxiomUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("superAspectUUID", taggedTypes.encodeAspectUUID(x.superAspectUUID)),
    	  ("subEntityUUID", taggedTypes.encodeEntityUUID(x.subEntityUUID))
    )
  }

  @JSExport
  def toJSON(c: AspectSpecializationAxiom)
  : String
  = encodeAspectSpecializationAxiom(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : AspectSpecializationAxiom
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeAspectSpecializationAxiom(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
