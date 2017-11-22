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
@JSExportTopLevel("ReifiedRelationshipSourcePropertyPredicate")
case class ReifiedRelationshipSourcePropertyPredicate
(
  @(JSExport @field) override val uuid: taggedTypes.ReifiedRelationshipSourcePropertyPredicateUUID,
  @(JSExport @field) override val bodySegmentUUID: taggedTypes.RuleBodySegmentUUID,
  @(JSExport @field) val reifiedRelationshipUUID: taggedTypes.ReifiedRelationshipUUID
) extends BinarySegmentForwardPropertyPredicate {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    bodySegmentUUID: taggedTypes.RuleBodySegmentUUID,
    reifiedRelationshipUUID: taggedTypes.ReifiedRelationshipUUID)
  = this(
      taggedTypes.reifiedRelationshipSourcePropertyPredicateUUID(oug.namespaceUUID(
        "ReifiedRelationshipSourcePropertyPredicate",
        "bodySegment" -> bodySegmentUUID,
        "reifiedRelationship" -> reifiedRelationshipUUID).toString),
      bodySegmentUUID,
      reifiedRelationshipUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, bodySegmentUUID, reifiedRelationshipUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ReifiedRelationshipSourcePropertyPredicate =>
  	  (this.uuid == that.uuid) &&
  	  (this.bodySegmentUUID == that.bodySegmentUUID)  &&
  	  (this.reifiedRelationshipUUID == that.reifiedRelationshipUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("ReifiedRelationshipSourcePropertyPredicateHelper")
object ReifiedRelationshipSourcePropertyPredicateHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "ReifiedRelationshipSourcePropertyPredicates.json"

  implicit val decodeReifiedRelationshipSourcePropertyPredicate: Decoder[ReifiedRelationshipSourcePropertyPredicate]
  = Decoder.instance[ReifiedRelationshipSourcePropertyPredicate] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.ReifiedRelationshipSourcePropertyPredicateUUID]
    	  bodySegmentUUID <- c.downField("bodySegmentUUID").as[taggedTypes.RuleBodySegmentUUID]
    	  reifiedRelationshipUUID <- c.downField("reifiedRelationshipUUID").as[taggedTypes.ReifiedRelationshipUUID]
    	} yield ReifiedRelationshipSourcePropertyPredicate(
    	  uuid,
    	  bodySegmentUUID,
    	  reifiedRelationshipUUID
    	)
  }
  
  implicit val encodeReifiedRelationshipSourcePropertyPredicate: Encoder[ReifiedRelationshipSourcePropertyPredicate]
  = new Encoder[ReifiedRelationshipSourcePropertyPredicate] {
    override final def apply(x: ReifiedRelationshipSourcePropertyPredicate): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeReifiedRelationshipSourcePropertyPredicateUUID(x.uuid)),
    	  ("bodySegmentUUID", taggedTypes.encodeRuleBodySegmentUUID(x.bodySegmentUUID)),
    	  ("reifiedRelationshipUUID", taggedTypes.encodeReifiedRelationshipUUID(x.reifiedRelationshipUUID))
    )
  }

  @JSExport
  def toJSON(c: ReifiedRelationshipSourcePropertyPredicate)
  : String
  = encodeReifiedRelationshipSourcePropertyPredicate(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : ReifiedRelationshipSourcePropertyPredicate
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeReifiedRelationshipSourcePropertyPredicate(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
