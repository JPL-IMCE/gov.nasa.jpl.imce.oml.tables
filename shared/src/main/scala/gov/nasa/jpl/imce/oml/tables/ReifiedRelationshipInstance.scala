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
import scala.scalajs.js.annotation.JSExport
import scala._
import scala.Predef._

/**
  * @param uuid[1,1]
  * @param descriptionBoxUUID[1,1]
  * @param singletonReifiedRelationshipClassifierUUID[1,1]
  * @param name[1,1]
  */
@JSExport
case class ReifiedRelationshipInstance
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) descriptionBoxUUID: UUID,
  @(JSExport @field) singletonReifiedRelationshipClassifierUUID: UUID,
  @(JSExport @field) name: LocalName
) {
  // Ctor(uuidWithGenerator)   
  @JSExport
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    descriptionBoxUUID: UUID,
    singletonReifiedRelationshipClassifierUUID: UUID,
    name: LocalName)
  = this(
      oug.namespaceUUID(descriptionBoxUUID, "name" -> name).toString,
      descriptionBoxUUID,
      singletonReifiedRelationshipClassifierUUID,
      name)

  override val hashCode
  : scala.Int 
  = (uuid, descriptionBoxUUID, singletonReifiedRelationshipClassifierUUID, name).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ReifiedRelationshipInstance =>
  	  (this.uuid == that.uuid) &&
  	  (this.descriptionBoxUUID == that.descriptionBoxUUID) &&
  	  (this.singletonReifiedRelationshipClassifierUUID == that.singletonReifiedRelationshipClassifierUUID) &&
  	  (this.name == that.name)
    case _ =>
      false
  }
  
}

@JSExport
object ReifiedRelationshipInstanceHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "ReifiedRelationshipInstances.json"
  
  implicit val w
  : upickle.default.Writer[ReifiedRelationshipInstance]
  = upickle.default.macroW[ReifiedRelationshipInstance]

  @JSExport
  def toJSON(c: ReifiedRelationshipInstance)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[ReifiedRelationshipInstance]
  = upickle.default.macroR[ReifiedRelationshipInstance]

  @JSExport
  def fromJSON(c: String)
  : ReifiedRelationshipInstance
  = upickle.default.read[ReifiedRelationshipInstance](c)

}	
