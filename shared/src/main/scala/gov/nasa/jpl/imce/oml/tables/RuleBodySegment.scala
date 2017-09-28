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
import scala._
import scala.Predef._

/**
  * @param uuid[1,1]
  * @param previousSegmentUUID[0,1]
  * @param ruleUUID[0,1]
  */
case class RuleBodySegment
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) previousSegmentUUID: scala.Option[UUID],
  @(JSExport @field) ruleUUID: scala.Option[UUID]
) {
  def this(
    uuid: UUID)
  = this(
      uuid,
      None /* previousSegmentUUID */,
      None /* ruleUUID */)

  def withPreviousSegmentUUID(l: UUID)	 
  : RuleBodySegment
  = copy(previousSegmentUUID=Some(l))
  
  def withRuleUUID(l: UUID)	 
  : RuleBodySegment
  = copy(ruleUUID=Some(l))
  

  override val hashCode
  : scala.Int 
  = (uuid, previousSegmentUUID, ruleUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: RuleBodySegment =>
  	  (this.uuid == that.uuid) &&
  	  (this.previousSegmentUUID == that.previousSegmentUUID) &&
  	  (this.ruleUUID == that.ruleUUID)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("RuleBodySegmentHelper")
object RuleBodySegmentHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "RuleBodySegments.json"
  
  implicit val w
  : upickle.default.Writer[RuleBodySegment]
  = upickle.default.macroW[RuleBodySegment]

  @JSExport
  def toJSON(c: RuleBodySegment)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[RuleBodySegment]
  = upickle.default.macroR[RuleBodySegment]

  @JSExport
  def fromJSON(c: String)
  : RuleBodySegment
  = upickle.default.read[RuleBodySegment](c)

}	
