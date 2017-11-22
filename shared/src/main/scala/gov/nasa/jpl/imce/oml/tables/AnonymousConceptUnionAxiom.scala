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
  * @param name[1,1]
  */
@JSExportTopLevel("AnonymousConceptUnionAxiom")
case class AnonymousConceptUnionAxiom
(
  @(JSExport @field) uuid: taggedTypes.AnonymousConceptUnionAxiomUUID,
  @(JSExport @field) disjointTaxonomyParentUUID: taggedTypes.ConceptTreeDisjunctionUUID,
  @(JSExport @field) name: taggedTypes.LocalName
) {
  // Ctor(uuidWithGenerator)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    disjointTaxonomyParentUUID: taggedTypes.ConceptTreeDisjunctionUUID,
    name: taggedTypes.LocalName)
  = this(
      taggedTypes.anonymousConceptUnionAxiomUUID(oug.namespaceUUID(
        disjointTaxonomyParentUUID,
        "name" -> name).toString),
      disjointTaxonomyParentUUID,
      name)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, disjointTaxonomyParentUUID, name).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: AnonymousConceptUnionAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.disjointTaxonomyParentUUID == that.disjointTaxonomyParentUUID)  &&
  	  (this.name == that.name)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("AnonymousConceptUnionAxiomHelper")
object AnonymousConceptUnionAxiomHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "AnonymousConceptUnionAxioms.json"

  implicit val decodeAnonymousConceptUnionAxiom: Decoder[AnonymousConceptUnionAxiom]
  = Decoder.instance[AnonymousConceptUnionAxiom] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.AnonymousConceptUnionAxiomUUID]
    	  disjointTaxonomyParentUUID <- c.downField("disjointTaxonomyParentUUID").as[taggedTypes.ConceptTreeDisjunctionUUID]
    	  name <- c.downField("name").as[taggedTypes.LocalName]
    	} yield AnonymousConceptUnionAxiom(
    	  uuid,
    	  disjointTaxonomyParentUUID,
    	  name
    	)
  }
  
  implicit val encodeAnonymousConceptUnionAxiom: Encoder[AnonymousConceptUnionAxiom]
  = new Encoder[AnonymousConceptUnionAxiom] {
    override final def apply(x: AnonymousConceptUnionAxiom): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeAnonymousConceptUnionAxiomUUID(x.uuid)),
    	  ("disjointTaxonomyParentUUID", taggedTypes.encodeConceptTreeDisjunctionUUID(x.disjointTaxonomyParentUUID)),
    	  ("name", taggedTypes.encodeLocalName(x.name))
    )
  }

  @JSExport
  def toJSON(c: AnonymousConceptUnionAxiom)
  : String
  = encodeAnonymousConceptUnionAxiom(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : AnonymousConceptUnionAxiom
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeAnonymousConceptUnionAxiom(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
