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
  * @param unreifiedRelationshipUUID[1,1]
  * @param bodySegmentUUID[1,1]
  */
@JSExportTopLevel("UnreifiedRelationshipPropertyPredicate")
case class UnreifiedRelationshipPropertyPredicate
(
  @(JSExport @field) uuid: taggedTypes.UnreifiedRelationshipPropertyPredicateUUID,
  @(JSExport @field) unreifiedRelationshipUUID: taggedTypes.UnreifiedRelationshipXRef,
  @(JSExport @field) bodySegmentUUID: taggedTypes.RuleBodySegmentXRef
) {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    unreifiedRelationshipUUID: taggedTypes.UnreifiedRelationshipXRef,
    bodySegmentUUID: taggedTypes.RuleBodySegmentXRef)
  = this(
      taggedTypes.unreifiedRelationshipPropertyPredicateUUID(oug.namespaceUUID(
        "UnreifiedRelationshipPropertyPredicate",
        "unreifiedRelationship" -> unreifiedRelationshipUUID,
        "bodySegment" -> bodySegmentUUID).toString),
      unreifiedRelationshipUUID,
      bodySegmentUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, unreifiedRelationshipUUID, bodySegmentUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: UnreifiedRelationshipPropertyPredicate =>
  	  (this.uuid == that.uuid) &&
  	  gov.nasa.jpl.imce.oml.covariantTag.compareTaggedValues(this.unreifiedRelationshipUUID, that.unreifiedRelationshipUUID)  &&
  	  gov.nasa.jpl.imce.oml.covariantTag.compareTaggedValues(this.bodySegmentUUID, that.bodySegmentUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("UnreifiedRelationshipPropertyPredicateHelper")
object UnreifiedRelationshipPropertyPredicateHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "UnreifiedRelationshipPropertyPredicates.json"

  implicit val decodeUnreifiedRelationshipPropertyPredicate: Decoder[UnreifiedRelationshipPropertyPredicate]
  = Decoder.instance[UnreifiedRelationshipPropertyPredicate] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.UnreifiedRelationshipPropertyPredicateUUID]
    	  unreifiedRelationshipUUID <- c.downField("unreifiedRelationshipUUID").as[taggedTypes.UnreifiedRelationshipUUID]
    	  bodySegmentUUID <- c.downField("bodySegmentUUID").as[taggedTypes.RuleBodySegmentUUID]
    	} yield UnreifiedRelationshipPropertyPredicate(
    	  uuid,
    	  unreifiedRelationshipUUID,
    	  bodySegmentUUID
    	)
  }
  
  implicit val encodeUnreifiedRelationshipPropertyPredicate: Encoder[UnreifiedRelationshipPropertyPredicate]
  = new Encoder[UnreifiedRelationshipPropertyPredicate] {
    override final def apply(x: UnreifiedRelationshipPropertyPredicate): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeUnreifiedRelationshipPropertyPredicateUUID(x.uuid)),
    	  ("unreifiedRelationshipUUID", taggedTypes.encodeUnreifiedRelationshipUUID(x.unreifiedRelationshipUUID)),
    	  ("bodySegmentUUID", taggedTypes.encodeRuleBodySegmentUUID(x.bodySegmentUUID))
    )
  }

  @JSExport
  def toJSON(c: UnreifiedRelationshipPropertyPredicate)
  : String
  = encodeUnreifiedRelationshipPropertyPredicate(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : UnreifiedRelationshipPropertyPredicate
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeUnreifiedRelationshipPropertyPredicate(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
