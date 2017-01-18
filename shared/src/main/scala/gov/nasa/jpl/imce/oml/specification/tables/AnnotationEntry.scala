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

 
package gov.nasa.jpl.imce.oml.specification.tables

import scala.annotation.meta.field
import scala.scalajs.js.annotation.JSExport
import scala._
import scala.Predef._

/**
  * @param terminologyUUID[1,1]
  * @param subjectUUID[1,1]
  * @param value[1,1]
  */
@JSExport
case class AnnotationEntry
(
  @(JSExport @field) terminologyUUID: UUID,
  @(JSExport @field) subjectUUID: UUID,
  @(JSExport @field) value: scala.Predef.String
) {
  override val hashCode
  : scala.Int 
  = (terminologyUUID, subjectUUID, value).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: AnnotationEntry =>
  	  (this.terminologyUUID == that.terminologyUUID) &&
  	  (this.subjectUUID == that.subjectUUID) &&
  	  (this.value == that.value)
    case _ =>
      false
  }
  
}

@JSExport
object AnnotationEntryHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "AnnotationEntrys.json"
  
  implicit val w
  : upickle.default.Writer[AnnotationEntry]
  = upickle.default.macroW[AnnotationEntry]

  @JSExport
  def toJSON(c: AnnotationEntry)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[AnnotationEntry]
  = upickle.default.macroR[AnnotationEntry]

  @JSExport
  def fromJSON(c: String)
  : AnnotationEntry
  = upickle.default.read[AnnotationEntry](c)

}	
