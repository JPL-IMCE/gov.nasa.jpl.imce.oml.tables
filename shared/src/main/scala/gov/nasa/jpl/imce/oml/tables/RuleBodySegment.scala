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

/**
  * @param uuid[1,1]
  * @param previousSegmentUUID[0,1]
  * @param ruleUUID[0,1]
  */
case class RuleBodySegment
(
  @(JSExport @field) uuid: taggedTypes.RuleBodySegmentUUID,
  @(JSExport @field) previousSegmentUUID: scala.Option[taggedTypes.RuleBodySegmentXRef],
  @(JSExport @field) ruleUUID: scala.Option[taggedTypes.ChainRuleXRef]
) {
  def this(
    uuid: taggedTypes.RuleBodySegmentUUID)
  = this(
      uuid,
      scala.None /* previousSegmentUUID */,
      scala.None /* ruleUUID */)

  def withPreviousSegmentUUID(l: taggedTypes.RuleBodySegmentXRef)	 
  : RuleBodySegment
  = copy(previousSegmentUUID=scala.Some(l))
  
  def withRuleUUID(l: taggedTypes.ChainRuleXRef)	 
  : RuleBodySegment
  = copy(ruleUUID=scala.Some(l))
  

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, previousSegmentUUID, ruleUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: RuleBodySegment =>
  	  (this.uuid == that.uuid) &&
  	  ((this.previousSegmentUUID, that.previousSegmentUUID) match {
  	      case (scala.Some(t1), scala.Some(t2)) =>
  	        gov.nasa.jpl.imce.oml.covariantTag.compareTaggedValues(t1, t2)
  	      case (scala.None, scala.None) =>
  	        true
  	      case _ =>
  	        false
  	  }) &&
  	  ((this.ruleUUID, that.ruleUUID) match {
  	      case (scala.Some(t1), scala.Some(t2)) =>
  	        gov.nasa.jpl.imce.oml.covariantTag.compareTaggedValues(t1, t2)
  	      case (scala.None, scala.None) =>
  	        true
  	      case _ =>
  	        false
  	  })
    case _ =>
      false
  }
  
}

@JSExportTopLevel("RuleBodySegmentHelper")
object RuleBodySegmentHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "RuleBodySegments.json"

  implicit val decodeRuleBodySegment: Decoder[RuleBodySegment]
  = Decoder.instance[RuleBodySegment] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.RuleBodySegmentUUID]
    	  previousSegmentUUID <- Decoder.decodeOption(taggedTypes.decodeRuleBodySegmentUUID)(c.downField("previousSegmentUUID").success.get)
    	  ruleUUID <- Decoder.decodeOption(taggedTypes.decodeChainRuleUUID)(c.downField("ruleUUID").success.get)
    	} yield RuleBodySegment(
    	  uuid,
    	  previousSegmentUUID,
    	  ruleUUID
    	)
  }
  
  implicit val encodeRuleBodySegment: Encoder[RuleBodySegment]
  = new Encoder[RuleBodySegment] {
    override final def apply(x: RuleBodySegment): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeRuleBodySegmentUUID(x.uuid)),
    	  ("previousSegmentUUID", Encoder.encodeOption(taggedTypes.encodeRuleBodySegmentUUID).apply(x.previousSegmentUUID)),
    	  ("ruleUUID", Encoder.encodeOption(taggedTypes.encodeChainRuleUUID).apply(x.ruleUUID))
    )
  }

  @JSExport
  def toJSON(c: RuleBodySegment)
  : String
  = encodeRuleBodySegment(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : RuleBodySegment
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeRuleBodySegment(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
