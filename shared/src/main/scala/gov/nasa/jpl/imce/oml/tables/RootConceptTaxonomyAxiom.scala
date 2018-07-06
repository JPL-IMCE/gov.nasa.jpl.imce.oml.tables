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
  * @param rootUUID[1,1]
  */
@JSExportTopLevel("RootConceptTaxonomyAxiom")
case class RootConceptTaxonomyAxiom
(
  @(JSExport @field) override val uuid: taggedTypes.RootConceptTaxonomyAxiomUUID,
  @(JSExport @field) override val bundleUUID: taggedTypes.BundleUUID,
  @(JSExport @field) val rootUUID: taggedTypes.ConceptKindUUID
) extends ConceptTreeDisjunction with TerminologyBundleStatement {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    bundleUUID: taggedTypes.BundleUUID,
    rootUUID: taggedTypes.ConceptKindUUID)
  = this(
      taggedTypes.rootConceptTaxonomyAxiomUUID(oug.namespaceUUID(
        "RootConceptTaxonomyAxiom",
        "bundle" -> bundleUUID,
        "root" -> rootUUID).toString),
      bundleUUID,
      rootUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, bundleUUID, rootUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: RootConceptTaxonomyAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.bundleUUID == that.bundleUUID)  &&
  	  (this.rootUUID == that.rootUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("RootConceptTaxonomyAxiomHelper")
object RootConceptTaxonomyAxiomHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "RootConceptTaxonomyAxioms.json"

  implicit val decodeRootConceptTaxonomyAxiom: Decoder[RootConceptTaxonomyAxiom]
  = Decoder.instance[RootConceptTaxonomyAxiom] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.RootConceptTaxonomyAxiomUUID]
    	  bundleUUID <- c.downField("bundleUUID").as[taggedTypes.BundleUUID]
    	  rootUUID <- c.downField("rootUUID").as[taggedTypes.ConceptKindUUID]
    	} yield RootConceptTaxonomyAxiom(
    	  uuid,
    	  bundleUUID,
    	  rootUUID
    	)
  }
  
  implicit val encodeRootConceptTaxonomyAxiom: Encoder[RootConceptTaxonomyAxiom]
  = new Encoder[RootConceptTaxonomyAxiom] {
    override final def apply(x: RootConceptTaxonomyAxiom): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeRootConceptTaxonomyAxiomUUID(x.uuid)),
    	  ("bundleUUID", taggedTypes.encodeBundleUUID(x.bundleUUID)),
    	  ("rootUUID", taggedTypes.encodeConceptKindUUID(x.rootUUID))
    )
  }

  @JSExport
  def toJSON(c: RootConceptTaxonomyAxiom)
  : String
  = encodeRootConceptTaxonomyAxiom(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : RootConceptTaxonomyAxiom
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeRootConceptTaxonomyAxiom(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
