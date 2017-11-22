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
  * @param bundleUUID[1,1]
  * @param bundledTerminologyIRI[1,1]
  */
@JSExportTopLevel("BundledTerminologyAxiom")
case class BundledTerminologyAxiom
(
  @(JSExport @field) override val uuid: taggedTypes.BundledTerminologyAxiomUUID,
  @(JSExport @field) override val bundleUUID: taggedTypes.BundleUUID,
  @(JSExport @field) val bundledTerminologyIRI: taggedTypes.IRI
) extends TerminologyBundleAxiom {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    bundleUUID: taggedTypes.BundleUUID,
    bundledTerminologyIRI: taggedTypes.IRI)
  = this(
      taggedTypes.bundledTerminologyAxiomUUID(oug.namespaceUUID(
        "BundledTerminologyAxiom",
        "bundle" -> bundleUUID,
        "bundledTerminology" -> oug.namespaceUUID(bundledTerminologyIRI).toString).toString),
      bundleUUID,
      bundledTerminologyIRI)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, bundleUUID, bundledTerminologyIRI).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: BundledTerminologyAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.bundleUUID == that.bundleUUID)  &&
  	  (this.bundledTerminologyIRI == that.bundledTerminologyIRI)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("BundledTerminologyAxiomHelper")
object BundledTerminologyAxiomHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "BundledTerminologyAxioms.json"

  implicit val decodeBundledTerminologyAxiom: Decoder[BundledTerminologyAxiom]
  = Decoder.instance[BundledTerminologyAxiom] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.BundledTerminologyAxiomUUID]
    	  bundleUUID <- c.downField("bundleUUID").as[taggedTypes.BundleUUID]
    	  bundledTerminologyIRI <- c.downField("bundledTerminologyIRI").as[taggedTypes.IRI]
    	} yield BundledTerminologyAxiom(
    	  uuid,
    	  bundleUUID,
    	  bundledTerminologyIRI
    	)
  }
  
  implicit val encodeBundledTerminologyAxiom: Encoder[BundledTerminologyAxiom]
  = new Encoder[BundledTerminologyAxiom] {
    override final def apply(x: BundledTerminologyAxiom): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeBundledTerminologyAxiomUUID(x.uuid)),
    	  ("bundleUUID", taggedTypes.encodeBundleUUID(x.bundleUUID)),
    	  ("bundledTerminologyIRI", taggedTypes.encodeIRI(x.bundledTerminologyIRI))
    )
  }

  @JSExport
  def toJSON(c: BundledTerminologyAxiom)
  : String
  = encodeBundledTerminologyAxiom(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : BundledTerminologyAxiom
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeBundledTerminologyAxiom(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
