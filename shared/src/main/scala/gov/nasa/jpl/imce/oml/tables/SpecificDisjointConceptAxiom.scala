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
  * @param disjointTaxonomyParentUUID[1,1]
  * @param disjointLeafUUID[1,1]
  */
@JSExportTopLevel("SpecificDisjointConceptAxiom")
case class SpecificDisjointConceptAxiom
(
  @(JSExport @field) uuid: taggedTypes.SpecificDisjointConceptAxiomUUID,
  @(JSExport @field) disjointTaxonomyParentUUID: taggedTypes.ConceptTreeDisjunctionXRef,
  @(JSExport @field) disjointLeafUUID: taggedTypes.ConceptXRef
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    disjointTaxonomyParentUUID: taggedTypes.ConceptTreeDisjunctionXRef,
    disjointLeafUUID: taggedTypes.ConceptXRef)
  = this(
      taggedTypes.specificDisjointConceptAxiomUUID(oug.namespaceUUID(
        "SpecificDisjointConceptAxiom",
        "disjointTaxonomyParent" -> disjointTaxonomyParentUUID,
        "disjointLeaf" -> disjointLeafUUID).toString),
      disjointTaxonomyParentUUID,
      disjointLeafUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, disjointTaxonomyParentUUID, disjointLeafUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: SpecificDisjointConceptAxiom =>
  	  (this.uuid == that.uuid) &&
  	  gov.nasa.jpl.imce.oml.covariantTag.compareTaggedValues(this.disjointTaxonomyParentUUID, that.disjointTaxonomyParentUUID)  &&
  	  gov.nasa.jpl.imce.oml.covariantTag.compareTaggedValues(this.disjointLeafUUID, that.disjointLeafUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("SpecificDisjointConceptAxiomHelper")
object SpecificDisjointConceptAxiomHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "SpecificDisjointConceptAxioms.json"

  implicit val decodeSpecificDisjointConceptAxiom: Decoder[SpecificDisjointConceptAxiom]
  = Decoder.instance[SpecificDisjointConceptAxiom] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.SpecificDisjointConceptAxiomUUID]
    	  disjointTaxonomyParentUUID <- c.downField("disjointTaxonomyParentUUID").as[taggedTypes.ConceptTreeDisjunctionUUID]
    	  disjointLeafUUID <- c.downField("disjointLeafUUID").as[taggedTypes.ConceptUUID]
    	} yield SpecificDisjointConceptAxiom(
    	  uuid,
    	  disjointTaxonomyParentUUID,
    	  disjointLeafUUID
    	)
  }
  
  implicit val encodeSpecificDisjointConceptAxiom: Encoder[SpecificDisjointConceptAxiom]
  = new Encoder[SpecificDisjointConceptAxiom] {
    override final def apply(x: SpecificDisjointConceptAxiom): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeSpecificDisjointConceptAxiomUUID(x.uuid)),
    	  ("disjointTaxonomyParentUUID", taggedTypes.encodeConceptTreeDisjunctionUUID(x.disjointTaxonomyParentUUID)),
    	  ("disjointLeafUUID", taggedTypes.encodeConceptUUID(x.disjointLeafUUID))
    )
  }

  @JSExport
  def toJSON(c: SpecificDisjointConceptAxiom)
  : String
  = encodeSpecificDisjointConceptAxiom(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : SpecificDisjointConceptAxiom
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeSpecificDisjointConceptAxiom(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
