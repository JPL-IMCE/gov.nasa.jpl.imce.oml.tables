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

 
package gov.nasa.jpl.imce.omf.schema.tables

import scala.annotation.meta.field
import scala.scalajs.js.annotation.JSExport
import scala._
import scala.Predef._

/**
  * @param terminologyUUID[1,1]
  * @param subjectUUID[1,1]
  * @param propertyUUID[1,1]
  * @param value[1,1]
  */
@JSExport
case class Annotation
(
 @(JSExport @field) terminologyUUID: UUID,
 @(JSExport @field) subjectUUID: UUID,
 @(JSExport @field) propertyUUID: UUID,
 @(JSExport @field) value: scala.Predef.String
) 
@JSExport
object AnnotationHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "Annotations.json"
  
  implicit val w
  : upickle.default.Writer[Annotation]
  = upickle.default.macroW[Annotation]

  @JSExport
  def toJSON(c: Annotation)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[Annotation]
  = upickle.default.macroR[Annotation]

  @JSExport
  def fromJSON(c: String)
  : Annotation
  = upickle.default.read[Annotation](c)

}	
