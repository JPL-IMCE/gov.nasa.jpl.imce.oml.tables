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
  * @param graphUUID[1,1]
  * @param uuid[1,1]
  * @param subEntityUUID[1,1]
  * @param superAspectUUID[1,1]
  */
@JSExport
case class AspectSpecializationAxiom
(
 @(JSExport @field) graphUUID: UUID,
 @(JSExport @field) uuid: UUID,
 @(JSExport @field) subEntityUUID: UUID,
 @(JSExport @field) superAspectUUID: UUID
)

@JSExport
object AspectSpecializationAxiomHelper {

  implicit val w
  : upickle.default.Writer[AspectSpecializationAxiom]
  = upickle.default.macroW[AspectSpecializationAxiom]

  @JSExport
  def toJSON(c: AspectSpecializationAxiom)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[AspectSpecializationAxiom]
  = upickle.default.macroR[AspectSpecializationAxiom]

  @JSExport
  def fromJSON(c: String)
  : AspectSpecializationAxiom
  = upickle.default.read[AspectSpecializationAxiom](c)

}	
