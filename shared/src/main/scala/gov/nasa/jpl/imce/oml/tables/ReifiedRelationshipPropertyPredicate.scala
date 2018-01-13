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
  * @param forwardPropertyUUID[1,1]
  * @param bodySegmentUUID[1,1]
  */
@JSExportTopLevel("ReifiedRelationshipPropertyPredicate")
case class ReifiedRelationshipPropertyPredicate
(
  @(JSExport @field) override val uuid: taggedTypes.ReifiedRelationshipPropertyPredicateUUID,
  @(JSExport @field) val forwardPropertyUUID: taggedTypes.ForwardPropertyUUID,
  @(JSExport @field) override val bodySegmentUUID: taggedTypes.RuleBodySegmentUUID
) extends BinarySegmentForwardPropertyPredicate {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    forwardPropertyUUID: taggedTypes.ForwardPropertyUUID,
    bodySegmentUUID: taggedTypes.RuleBodySegmentUUID)
  = this(
      taggedTypes.reifiedRelationshipPropertyPredicateUUID(oug.namespaceUUID(
        "ReifiedRelationshipPropertyPredicate",
        "forwardProperty" -> forwardPropertyUUID,
        "bodySegment" -> bodySegmentUUID).toString),
      forwardPropertyUUID,
      bodySegmentUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, forwardPropertyUUID, bodySegmentUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ReifiedRelationshipPropertyPredicate =>
  	  (this.uuid == that.uuid) &&
  	  (this.forwardPropertyUUID == that.forwardPropertyUUID)  &&
  	  (this.bodySegmentUUID == that.bodySegmentUUID) 
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
    	  forwardPropertyUUID <- c.downField("forwardPropertyUUID").as[taggedTypes.ForwardPropertyUUID]
    	  bodySegmentUUID <- c.downField("bodySegmentUUID").as[taggedTypes.RuleBodySegmentUUID]
    	} yield ReifiedRelationshipPropertyPredicate(
    	  uuid,
    	  forwardPropertyUUID,
    	  bodySegmentUUID
    	)
  }
  
  implicit val encodeReifiedRelationshipPropertyPredicate: Encoder[ReifiedRelationshipPropertyPredicate]
  = new Encoder[ReifiedRelationshipPropertyPredicate] {
    override final def apply(x: ReifiedRelationshipPropertyPredicate): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeReifiedRelationshipPropertyPredicateUUID(x.uuid)),
    	  ("forwardPropertyUUID", taggedTypes.encodeForwardPropertyUUID(x.forwardPropertyUUID)),
    	  ("bodySegmentUUID", taggedTypes.encodeRuleBodySegmentUUID(x.bodySegmentUUID))
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
