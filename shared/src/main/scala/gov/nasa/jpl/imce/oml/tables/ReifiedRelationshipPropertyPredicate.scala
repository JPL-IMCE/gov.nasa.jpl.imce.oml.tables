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
  * @param reifiedRelationshipUUID[1,1]
  */
@JSExportTopLevel("ReifiedRelationshipPropertyPredicate")
case class ReifiedRelationshipPropertyPredicate
(
  @(JSExport @field) override val uuid: taggedTypes.ReifiedRelationshipPropertyPredicateUUID,
  @(JSExport @field) override val bodySegmentUUID: taggedTypes.RuleBodySegmentUUID,
  @(JSExport @field) val reifiedRelationshipUUID: taggedTypes.ReifiedRelationshipUUID
) extends BinarySegmentForwardPropertyPredicate {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    bodySegmentUUID: taggedTypes.RuleBodySegmentUUID,
    reifiedRelationshipUUID: taggedTypes.ReifiedRelationshipUUID)
  = this(
      taggedTypes.reifiedRelationshipPropertyPredicateUUID(oug.namespaceUUID(
        "ReifiedRelationshipPropertyPredicate",
        "bodySegment" -> bodySegmentUUID,
        "reifiedRelationship" -> reifiedRelationshipUUID).toString),
      bodySegmentUUID,
      reifiedRelationshipUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, bodySegmentUUID, reifiedRelationshipUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ReifiedRelationshipPropertyPredicate =>
  	  (this.uuid == that.uuid) &&
  	  (this.bodySegmentUUID == that.bodySegmentUUID)  &&
  	  (this.reifiedRelationshipUUID == that.reifiedRelationshipUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("ReifiedRelationshipPropertyPredicateHelper")
object ReifiedRelationshipPropertyPredicateHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "ReifiedRelationshipPropertyPredicates.json"

  implicit val decodeReifiedRelationshipPropertyPredicate: Decoder[ReifiedRelationshipPropertyPredicate]
  = Decoder.instance[ReifiedRelationshipPropertyPredicate] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.ReifiedRelationshipPropertyPredicateUUID]
    	  bodySegmentUUID <- c.downField("bodySegmentUUID").as[taggedTypes.RuleBodySegmentUUID]
    	  reifiedRelationshipUUID <- c.downField("reifiedRelationshipUUID").as[taggedTypes.ReifiedRelationshipUUID]
    	} yield ReifiedRelationshipPropertyPredicate(
    	  uuid,
    	  bodySegmentUUID,
    	  reifiedRelationshipUUID
    	)
  }
  
  implicit val encodeReifiedRelationshipPropertyPredicate: Encoder[ReifiedRelationshipPropertyPredicate]
  = new Encoder[ReifiedRelationshipPropertyPredicate] {
    override final def apply(x: ReifiedRelationshipPropertyPredicate): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeReifiedRelationshipPropertyPredicateUUID(x.uuid)),
    	  ("bodySegmentUUID", taggedTypes.encodeRuleBodySegmentUUID(x.bodySegmentUUID)),
    	  ("reifiedRelationshipUUID", taggedTypes.encodeReifiedRelationshipUUID(x.reifiedRelationshipUUID))
    )
  }

  @JSExport
  def toJSON(c: ReifiedRelationshipPropertyPredicate)
  : String
  = encodeReifiedRelationshipPropertyPredicate(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : ReifiedRelationshipPropertyPredicate
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeReifiedRelationshipPropertyPredicate(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
