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
  * @param descriptionBoxUUID[1,1]
  * @param reifiedRelationshipInstanceUUID[1,1]
  * @param domainUUID[1,1]
  */
@JSExportTopLevel("ReifiedRelationshipInstanceDomain")
case class ReifiedRelationshipInstanceDomain
(
  @(JSExport @field) override val uuid: taggedTypes.ReifiedRelationshipInstanceDomainUUID,
  @(JSExport @field) val descriptionBoxUUID: taggedTypes.DescriptionBoxUUID,
  @(JSExport @field) val reifiedRelationshipInstanceUUID: taggedTypes.ReifiedRelationshipInstanceUUID,
  @(JSExport @field) val domainUUID: taggedTypes.ConceptualEntitySingletonInstanceUUID
) extends ElementCrossReferenceTuple with TerminologyInstanceAssertion {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    descriptionBoxUUID: taggedTypes.DescriptionBoxUUID,
    reifiedRelationshipInstanceUUID: taggedTypes.ReifiedRelationshipInstanceUUID,
    domainUUID: taggedTypes.ConceptualEntitySingletonInstanceUUID)
  = this(
      taggedTypes.reifiedRelationshipInstanceDomainUUID(oug.namespaceUUID(
        "ReifiedRelationshipInstanceDomain",
        "descriptionBox" -> descriptionBoxUUID,
        "reifiedRelationshipInstance" -> reifiedRelationshipInstanceUUID,
        "domain" -> domainUUID).toString),
      descriptionBoxUUID,
      reifiedRelationshipInstanceUUID,
      domainUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, descriptionBoxUUID, reifiedRelationshipInstanceUUID, domainUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ReifiedRelationshipInstanceDomain =>
  	  (this.uuid == that.uuid) &&
  	  (this.descriptionBoxUUID == that.descriptionBoxUUID)  &&
  	  (this.reifiedRelationshipInstanceUUID == that.reifiedRelationshipInstanceUUID)  &&
  	  (this.domainUUID == that.domainUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("ReifiedRelationshipInstanceDomainHelper")
object ReifiedRelationshipInstanceDomainHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "ReifiedRelationshipInstanceDomains.json"

  implicit val decodeReifiedRelationshipInstanceDomain: Decoder[ReifiedRelationshipInstanceDomain]
  = Decoder.instance[ReifiedRelationshipInstanceDomain] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.ReifiedRelationshipInstanceDomainUUID]
    	  descriptionBoxUUID <- c.downField("descriptionBoxUUID").as[taggedTypes.DescriptionBoxUUID]
    	  reifiedRelationshipInstanceUUID <- c.downField("reifiedRelationshipInstanceUUID").as[taggedTypes.ReifiedRelationshipInstanceUUID]
    	  domainUUID <- c.downField("domainUUID").as[taggedTypes.ConceptualEntitySingletonInstanceUUID]
    	} yield ReifiedRelationshipInstanceDomain(
    	  uuid,
    	  descriptionBoxUUID,
    	  reifiedRelationshipInstanceUUID,
    	  domainUUID
    	)
  }
  
  implicit val encodeReifiedRelationshipInstanceDomain: Encoder[ReifiedRelationshipInstanceDomain]
  = new Encoder[ReifiedRelationshipInstanceDomain] {
    override final def apply(x: ReifiedRelationshipInstanceDomain): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeReifiedRelationshipInstanceDomainUUID(x.uuid)),
    	  ("descriptionBoxUUID", taggedTypes.encodeDescriptionBoxUUID(x.descriptionBoxUUID)),
    	  ("reifiedRelationshipInstanceUUID", taggedTypes.encodeReifiedRelationshipInstanceUUID(x.reifiedRelationshipInstanceUUID)),
    	  ("domainUUID", taggedTypes.encodeConceptualEntitySingletonInstanceUUID(x.domainUUID))
    )
  }

  @JSExport
  def toJSON(c: ReifiedRelationshipInstanceDomain)
  : String
  = encodeReifiedRelationshipInstanceDomain(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : ReifiedRelationshipInstanceDomain
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeReifiedRelationshipInstanceDomain(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
