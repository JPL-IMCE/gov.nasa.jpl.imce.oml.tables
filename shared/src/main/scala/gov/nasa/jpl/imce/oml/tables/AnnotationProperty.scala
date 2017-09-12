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
  * @param iri[1,1]
  * @param abbrevIRI[1,1]
  */
@JSExportTopLevel("AnnotationProperty")
case class AnnotationProperty
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) iri: IRI,
  @(JSExport @field) abbrevIRI: AbbrevIRI
) {
  // Ctor(uuidWithoutContainer)
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    iri: IRI,
    abbrevIRI: AbbrevIRI)
  = this(
      oug.namespaceUUID(
        iri.toString).toString,
      iri,
      abbrevIRI)

  override val hashCode
  : scala.Int 
  = (uuid, iri, abbrevIRI).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: AnnotationProperty =>
  	  (this.uuid == that.uuid) &&
  	  (this.iri == that.iri) &&
  	  (this.abbrevIRI == that.abbrevIRI)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("AnnotationPropertyHelper")
object AnnotationPropertyHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "AnnotationProperties.json"
  
  implicit val w
  : upickle.default.Writer[AnnotationProperty]
  = upickle.default.macroW[AnnotationProperty]

  @JSExport
  def toJSON(c: AnnotationProperty)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[AnnotationProperty]
  = upickle.default.macroR[AnnotationProperty]

  @JSExport
  def fromJSON(c: String)
  : AnnotationProperty
  = upickle.default.read[AnnotationProperty](c)

}	
