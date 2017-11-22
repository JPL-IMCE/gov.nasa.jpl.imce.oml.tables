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
  * @param aspectUUID[1,1]
  * @param bodySegmentUUID[1,1]
  */
@JSExportTopLevel("AspectPredicate")
case class AspectPredicate
(
  @(JSExport @field) override val uuid: taggedTypes.AspectPredicateUUID,
  @(JSExport @field) val aspectUUID: taggedTypes.AspectUUID,
  @(JSExport @field) override val bodySegmentUUID: taggedTypes.RuleBodySegmentUUID
) extends UnarySegmentPredicate {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    aspectUUID: taggedTypes.AspectUUID,
    bodySegmentUUID: taggedTypes.RuleBodySegmentUUID)
  = this(
      taggedTypes.aspectPredicateUUID(oug.namespaceUUID(
        "AspectPredicate",
        "aspect" -> aspectUUID,
        "bodySegment" -> bodySegmentUUID).toString),
      aspectUUID,
      bodySegmentUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, aspectUUID, bodySegmentUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: AspectPredicate =>
  	  (this.uuid == that.uuid) &&
  	  (this.aspectUUID == that.aspectUUID)  &&
  	  (this.bodySegmentUUID == that.bodySegmentUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("AspectPredicateHelper")
object AspectPredicateHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "AspectPredicates.json"

  implicit val decodeAspectPredicate: Decoder[AspectPredicate]
  = Decoder.instance[AspectPredicate] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.AspectPredicateUUID]
    	  aspectUUID <- c.downField("aspectUUID").as[taggedTypes.AspectUUID]
    	  bodySegmentUUID <- c.downField("bodySegmentUUID").as[taggedTypes.RuleBodySegmentUUID]
    	} yield AspectPredicate(
    	  uuid,
    	  aspectUUID,
    	  bodySegmentUUID
    	)
  }
  
  implicit val encodeAspectPredicate: Encoder[AspectPredicate]
  = new Encoder[AspectPredicate] {
    override final def apply(x: AspectPredicate): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeAspectPredicateUUID(x.uuid)),
    	  ("aspectUUID", taggedTypes.encodeAspectUUID(x.aspectUUID)),
    	  ("bodySegmentUUID", taggedTypes.encodeRuleBodySegmentUUID(x.bodySegmentUUID))
    )
  }

  @JSExport
  def toJSON(c: AspectPredicate)
  : String
  = encodeAspectPredicate(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : AspectPredicate
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeAspectPredicate(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
