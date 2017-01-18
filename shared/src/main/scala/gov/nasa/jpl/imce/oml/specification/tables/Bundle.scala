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
  * @param uuid[1,1]
  * @param kind[1,1]
  * @param name[1,1]
  * @param iri[1,1]
  */
@JSExport
case class Bundle
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) kind: TerminologyGraphKind,
  @(JSExport @field) name: LocalName,
  @(JSExport @field) iri: IRI
) {
  override val hashCode
  : scala.Int 
  = (uuid, kind, name, iri).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: Bundle =>
  	  (this.uuid == that.uuid) &&
  	  (this.kind == that.kind) &&
  	  (this.name == that.name) &&
  	  (this.iri == that.iri)
    case _ =>
      false
  }
  
}

@JSExport
object BundleHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "Bundles.json"
  
  implicit val w
  : upickle.default.Writer[Bundle]
  = upickle.default.macroW[Bundle]

  @JSExport
  def toJSON(c: Bundle)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[Bundle]
  = upickle.default.macroR[Bundle]

  @JSExport
  def fromJSON(c: String)
  : Bundle
  = upickle.default.read[Bundle](c)

}	
