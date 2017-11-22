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
  * @param bodySegmentUUID[1,1]
  * @param conceptUUID[1,1]
  */
@JSExportTopLevel("ConceptPredicate")
case class ConceptPredicate
(
  @(JSExport @field) override val uuid: taggedTypes.ConceptPredicateUUID,
  @(JSExport @field) override val bodySegmentUUID: taggedTypes.RuleBodySegmentUUID,
  @(JSExport @field) val conceptUUID: taggedTypes.ConceptUUID
) extends UnarySegmentPredicate {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    bodySegmentUUID: taggedTypes.RuleBodySegmentUUID,
    conceptUUID: taggedTypes.ConceptUUID)
  = this(
      taggedTypes.conceptPredicateUUID(oug.namespaceUUID(
        "ConceptPredicate",
        "bodySegment" -> bodySegmentUUID,
        "concept" -> conceptUUID).toString),
      bodySegmentUUID,
      conceptUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, bodySegmentUUID, conceptUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ConceptPredicate =>
  	  (this.uuid == that.uuid) &&
  	  (this.bodySegmentUUID == that.bodySegmentUUID)  &&
  	  (this.conceptUUID == that.conceptUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("ConceptPredicateHelper")
object ConceptPredicateHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "ConceptPredicates.json"

  implicit val decodeConceptPredicate: Decoder[ConceptPredicate]
  = Decoder.instance[ConceptPredicate] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.ConceptPredicateUUID]
    	  bodySegmentUUID <- c.downField("bodySegmentUUID").as[taggedTypes.RuleBodySegmentUUID]
    	  conceptUUID <- c.downField("conceptUUID").as[taggedTypes.ConceptUUID]
    	} yield ConceptPredicate(
    	  uuid,
    	  bodySegmentUUID,
    	  conceptUUID
    	)
  }
  
  implicit val encodeConceptPredicate: Encoder[ConceptPredicate]
  = new Encoder[ConceptPredicate] {
    override final def apply(x: ConceptPredicate): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeConceptPredicateUUID(x.uuid)),
    	  ("bodySegmentUUID", taggedTypes.encodeRuleBodySegmentUUID(x.bodySegmentUUID)),
    	  ("conceptUUID", taggedTypes.encodeConceptUUID(x.conceptUUID))
    )
  }

  @JSExport
  def toJSON(c: ConceptPredicate)
  : String
  = encodeConceptPredicate(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : ConceptPredicate
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeConceptPredicate(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
