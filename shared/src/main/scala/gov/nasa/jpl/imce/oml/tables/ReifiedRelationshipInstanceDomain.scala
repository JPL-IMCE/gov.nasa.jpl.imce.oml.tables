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
  * @param uuid[0,1]
  * @param descriptionBoxUUID[1,1]
  * @param reifiedRelationshipInstanceUUID[1,1]
  * @param domainUUID[1,1]
  * @param name[1,1]
  */
case class ReifiedRelationshipInstanceDomain
(
  @(JSExport @field) uuid: scala.Option[UUID],
  @(JSExport @field) descriptionBoxUUID: UUID,
  @(JSExport @field) reifiedRelationshipInstanceUUID: UUID,
  @(JSExport @field) domainUUID: UUID,
  @(JSExport @field) name: LocalName
) {
  @JSExport
  def this(
    descriptionBoxUUID: UUID,
    reifiedRelationshipInstanceUUID: UUID,
    domainUUID: UUID,
    name: LocalName)
  = this(
      None /* uuid */,
      descriptionBoxUUID,
      reifiedRelationshipInstanceUUID,
      domainUUID,
      name)

  def withUuid(l: UUID)	 
  : ReifiedRelationshipInstanceDomain
  = copy(uuid=Some(l))
  
  override val hashCode
  : scala.Int 
  = (uuid, descriptionBoxUUID, reifiedRelationshipInstanceUUID, domainUUID, name).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ReifiedRelationshipInstanceDomain =>
  	  (this.uuid == that.uuid) &&
  	  (this.descriptionBoxUUID == that.descriptionBoxUUID) &&
  	  (this.reifiedRelationshipInstanceUUID == that.reifiedRelationshipInstanceUUID) &&
  	  (this.domainUUID == that.domainUUID) &&
  	  (this.name == that.name)
    case _ =>
      false
  }
  
}

@JSExport
object ReifiedRelationshipInstanceDomainHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "ReifiedRelationshipInstanceDomains.json"
  
  implicit val w
  : upickle.default.Writer[ReifiedRelationshipInstanceDomain]
  = upickle.default.macroW[ReifiedRelationshipInstanceDomain]

  @JSExport
  def toJSON(c: ReifiedRelationshipInstanceDomain)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[ReifiedRelationshipInstanceDomain]
  = upickle.default.macroR[ReifiedRelationshipInstanceDomain]

  @JSExport
  def fromJSON(c: String)
  : ReifiedRelationshipInstanceDomain
  = upickle.default.read[ReifiedRelationshipInstanceDomain](c)

}	
