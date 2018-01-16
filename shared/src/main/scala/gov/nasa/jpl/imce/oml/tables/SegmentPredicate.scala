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
  * @param predicateUUID[0,1]
  * @param reifiedRelationshipSourceUUID[0,1]
  * @param reifiedRelationshipInverseSourceUUID[0,1]
  * @param reifiedRelationshipTargetUUID[0,1]
  * @param reifiedRelationshipInverseTargetUUID[0,1]
  * @param unreifiedRelationshipInverseUUID[0,1]
  */
case class SegmentPredicate
(
  @(JSExport @field) override val uuid: taggedTypes.SegmentPredicateUUID,
  @(JSExport @field) val bodySegmentUUID: taggedTypes.RuleBodySegmentUUID,
  @(JSExport @field) val predicateUUID: scala.Option[taggedTypes.PredicateUUID],
  @(JSExport @field) val reifiedRelationshipSourceUUID: scala.Option[taggedTypes.ReifiedRelationshipUUID],
  @(JSExport @field) val reifiedRelationshipInverseSourceUUID: scala.Option[taggedTypes.ReifiedRelationshipUUID],
  @(JSExport @field) val reifiedRelationshipTargetUUID: scala.Option[taggedTypes.ReifiedRelationshipUUID],
  @(JSExport @field) val reifiedRelationshipInverseTargetUUID: scala.Option[taggedTypes.ReifiedRelationshipUUID],
  @(JSExport @field) val unreifiedRelationshipInverseUUID: scala.Option[taggedTypes.UnreifiedRelationshipUUID]
) extends ElementCrossReferenceTuple {
  def this(
    uuid: taggedTypes.SegmentPredicateUUID,
    bodySegmentUUID: taggedTypes.RuleBodySegmentUUID)
  = this(
      uuid,
      bodySegmentUUID,
      scala.None /* predicateUUID */,
      scala.None /* reifiedRelationshipSourceUUID */,
      scala.None /* reifiedRelationshipInverseSourceUUID */,
      scala.None /* reifiedRelationshipTargetUUID */,
      scala.None /* reifiedRelationshipInverseTargetUUID */,
      scala.None /* unreifiedRelationshipInverseUUID */)

  def withPredicateUUID(l: taggedTypes.PredicateUUID)	 
  : SegmentPredicate
  = copy(predicateUUID=scala.Some(l))
  
  def withReifiedRelationshipSourceUUID(l: taggedTypes.ReifiedRelationshipUUID)	 
  : SegmentPredicate
  = copy(reifiedRelationshipSourceUUID=scala.Some(l))
  
  def withReifiedRelationshipInverseSourceUUID(l: taggedTypes.ReifiedRelationshipUUID)	 
  : SegmentPredicate
  = copy(reifiedRelationshipInverseSourceUUID=scala.Some(l))
  
  def withReifiedRelationshipTargetUUID(l: taggedTypes.ReifiedRelationshipUUID)	 
  : SegmentPredicate
  = copy(reifiedRelationshipTargetUUID=scala.Some(l))
  
  def withReifiedRelationshipInverseTargetUUID(l: taggedTypes.ReifiedRelationshipUUID)	 
  : SegmentPredicate
  = copy(reifiedRelationshipInverseTargetUUID=scala.Some(l))
  
  def withUnreifiedRelationshipInverseUUID(l: taggedTypes.UnreifiedRelationshipUUID)	 
  : SegmentPredicate
  = copy(unreifiedRelationshipInverseUUID=scala.Some(l))
  
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    bodySegmentUUID: taggedTypes.RuleBodySegmentUUID)
  = this(
      taggedTypes.segmentPredicateUUID(oug.namespaceUUID(
        "SegmentPredicate",
        "bodySegment" -> bodySegmentUUID).toString),
      bodySegmentUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, bodySegmentUUID, predicateUUID, reifiedRelationshipSourceUUID, reifiedRelationshipInverseSourceUUID, reifiedRelationshipTargetUUID, reifiedRelationshipInverseTargetUUID, unreifiedRelationshipInverseUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: SegmentPredicate =>
  	  (this.uuid == that.uuid) &&
  	  (this.bodySegmentUUID == that.bodySegmentUUID)  &&
  	  ((this.predicateUUID, that.predicateUUID) match {
  	      case (scala.Some(t1), scala.Some(t2)) =>
  	        t1 == t2
  	      case (scala.None, scala.None) =>
  	        true
  	      case _ =>
  	        false
  	  }) &&
  	  ((this.reifiedRelationshipSourceUUID, that.reifiedRelationshipSourceUUID) match {
  	      case (scala.Some(t1), scala.Some(t2)) =>
  	        t1 == t2
  	      case (scala.None, scala.None) =>
  	        true
  	      case _ =>
  	        false
  	  }) &&
  	  ((this.reifiedRelationshipInverseSourceUUID, that.reifiedRelationshipInverseSourceUUID) match {
  	      case (scala.Some(t1), scala.Some(t2)) =>
  	        t1 == t2
  	      case (scala.None, scala.None) =>
  	        true
  	      case _ =>
  	        false
  	  }) &&
  	  ((this.reifiedRelationshipTargetUUID, that.reifiedRelationshipTargetUUID) match {
  	      case (scala.Some(t1), scala.Some(t2)) =>
  	        t1 == t2
  	      case (scala.None, scala.None) =>
  	        true
  	      case _ =>
  	        false
  	  }) &&
  	  ((this.reifiedRelationshipInverseTargetUUID, that.reifiedRelationshipInverseTargetUUID) match {
  	      case (scala.Some(t1), scala.Some(t2)) =>
  	        t1 == t2
  	      case (scala.None, scala.None) =>
  	        true
  	      case _ =>
  	        false
  	  }) &&
  	  ((this.unreifiedRelationshipInverseUUID, that.unreifiedRelationshipInverseUUID) match {
  	      case (scala.Some(t1), scala.Some(t2)) =>
  	        t1 == t2
  	      case (scala.None, scala.None) =>
  	        true
  	      case _ =>
  	        false
  	  })
    case _ =>
      false
  }
  
}

@JSExportTopLevel("SegmentPredicateHelper")
object SegmentPredicateHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "SegmentPredicates.json"

  implicit val decodeSegmentPredicate: Decoder[SegmentPredicate]
  = Decoder.instance[SegmentPredicate] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.SegmentPredicateUUID]
    	  bodySegmentUUID <- c.downField("bodySegmentUUID").as[taggedTypes.RuleBodySegmentUUID]
    	  predicateUUID <- Decoder.decodeOption(taggedTypes.decodePredicateUUID)(c.downField("predicateUUID").success.get)
    	  reifiedRelationshipSourceUUID <- Decoder.decodeOption(taggedTypes.decodeReifiedRelationshipUUID)(c.downField("reifiedRelationshipSourceUUID").success.get)
    	  reifiedRelationshipInverseSourceUUID <- Decoder.decodeOption(taggedTypes.decodeReifiedRelationshipUUID)(c.downField("reifiedRelationshipInverseSourceUUID").success.get)
    	  reifiedRelationshipTargetUUID <- Decoder.decodeOption(taggedTypes.decodeReifiedRelationshipUUID)(c.downField("reifiedRelationshipTargetUUID").success.get)
    	  reifiedRelationshipInverseTargetUUID <- Decoder.decodeOption(taggedTypes.decodeReifiedRelationshipUUID)(c.downField("reifiedRelationshipInverseTargetUUID").success.get)
    	  unreifiedRelationshipInverseUUID <- Decoder.decodeOption(taggedTypes.decodeUnreifiedRelationshipUUID)(c.downField("unreifiedRelationshipInverseUUID").success.get)
    	} yield SegmentPredicate(
    	  uuid,
    	  bodySegmentUUID,
    	  predicateUUID,
    	  reifiedRelationshipSourceUUID,
    	  reifiedRelationshipInverseSourceUUID,
    	  reifiedRelationshipTargetUUID,
    	  reifiedRelationshipInverseTargetUUID,
    	  unreifiedRelationshipInverseUUID
    	)
  }
  
  implicit val encodeSegmentPredicate: Encoder[SegmentPredicate]
  = new Encoder[SegmentPredicate] {
    override final def apply(x: SegmentPredicate): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeSegmentPredicateUUID(x.uuid)),
    	  ("bodySegmentUUID", taggedTypes.encodeRuleBodySegmentUUID(x.bodySegmentUUID)),
    	  ("predicateUUID", Encoder.encodeOption(taggedTypes.encodePredicateUUID).apply(x.predicateUUID)),
    	  ("reifiedRelationshipSourceUUID", Encoder.encodeOption(taggedTypes.encodeReifiedRelationshipUUID).apply(x.reifiedRelationshipSourceUUID)),
    	  ("reifiedRelationshipInverseSourceUUID", Encoder.encodeOption(taggedTypes.encodeReifiedRelationshipUUID).apply(x.reifiedRelationshipInverseSourceUUID)),
    	  ("reifiedRelationshipTargetUUID", Encoder.encodeOption(taggedTypes.encodeReifiedRelationshipUUID).apply(x.reifiedRelationshipTargetUUID)),
    	  ("reifiedRelationshipInverseTargetUUID", Encoder.encodeOption(taggedTypes.encodeReifiedRelationshipUUID).apply(x.reifiedRelationshipInverseTargetUUID)),
    	  ("unreifiedRelationshipInverseUUID", Encoder.encodeOption(taggedTypes.encodeUnreifiedRelationshipUUID).apply(x.unreifiedRelationshipInverseUUID))
    )
  }

  @JSExport
  def toJSON(c: SegmentPredicate)
  : String
  = encodeSegmentPredicate(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : SegmentPredicate
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeSegmentPredicate(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
