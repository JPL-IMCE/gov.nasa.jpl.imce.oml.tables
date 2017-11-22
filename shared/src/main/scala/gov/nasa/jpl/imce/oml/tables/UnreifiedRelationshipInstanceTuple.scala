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
  * @param unreifiedRelationshipUUID[1,1]
  * @param domainUUID[1,1]
  * @param rangeUUID[1,1]
  */
@JSExportTopLevel("UnreifiedRelationshipInstanceTuple")
case class UnreifiedRelationshipInstanceTuple
(
  @(JSExport @field) override val uuid: taggedTypes.UnreifiedRelationshipInstanceTupleUUID,
  @(JSExport @field) val descriptionBoxUUID: taggedTypes.DescriptionBoxUUID,
  @(JSExport @field) val unreifiedRelationshipUUID: taggedTypes.UnreifiedRelationshipUUID,
  @(JSExport @field) val domainUUID: taggedTypes.ConceptualEntitySingletonInstanceUUID,
  @(JSExport @field) val rangeUUID: taggedTypes.ConceptualEntitySingletonInstanceUUID
) extends TerminologyInstanceAssertion {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    descriptionBoxUUID: taggedTypes.DescriptionBoxUUID,
    unreifiedRelationshipUUID: taggedTypes.UnreifiedRelationshipUUID,
    domainUUID: taggedTypes.ConceptualEntitySingletonInstanceUUID,
    rangeUUID: taggedTypes.ConceptualEntitySingletonInstanceUUID)
  = this(
      taggedTypes.unreifiedRelationshipInstanceTupleUUID(oug.namespaceUUID(
        "UnreifiedRelationshipInstanceTuple",
        "descriptionBox" -> descriptionBoxUUID,
        "unreifiedRelationship" -> unreifiedRelationshipUUID,
        "domain" -> domainUUID,
        "range" -> rangeUUID).toString),
      descriptionBoxUUID,
      unreifiedRelationshipUUID,
      domainUUID,
      rangeUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, descriptionBoxUUID, unreifiedRelationshipUUID, domainUUID, rangeUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: UnreifiedRelationshipInstanceTuple =>
  	  (this.uuid == that.uuid) &&
  	  (this.descriptionBoxUUID == that.descriptionBoxUUID)  &&
  	  (this.unreifiedRelationshipUUID == that.unreifiedRelationshipUUID)  &&
  	  (this.domainUUID == that.domainUUID)  &&
  	  (this.rangeUUID == that.rangeUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("UnreifiedRelationshipInstanceTupleHelper")
object UnreifiedRelationshipInstanceTupleHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "UnreifiedRelationshipInstanceTuples.json"

  implicit val decodeUnreifiedRelationshipInstanceTuple: Decoder[UnreifiedRelationshipInstanceTuple]
  = Decoder.instance[UnreifiedRelationshipInstanceTuple] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.UnreifiedRelationshipInstanceTupleUUID]
    	  descriptionBoxUUID <- c.downField("descriptionBoxUUID").as[taggedTypes.DescriptionBoxUUID]
    	  unreifiedRelationshipUUID <- c.downField("unreifiedRelationshipUUID").as[taggedTypes.UnreifiedRelationshipUUID]
    	  domainUUID <- c.downField("domainUUID").as[taggedTypes.ConceptualEntitySingletonInstanceUUID]
    	  rangeUUID <- c.downField("rangeUUID").as[taggedTypes.ConceptualEntitySingletonInstanceUUID]
    	} yield UnreifiedRelationshipInstanceTuple(
    	  uuid,
    	  descriptionBoxUUID,
    	  unreifiedRelationshipUUID,
    	  domainUUID,
    	  rangeUUID
    	)
  }
  
  implicit val encodeUnreifiedRelationshipInstanceTuple: Encoder[UnreifiedRelationshipInstanceTuple]
  = new Encoder[UnreifiedRelationshipInstanceTuple] {
    override final def apply(x: UnreifiedRelationshipInstanceTuple): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeUnreifiedRelationshipInstanceTupleUUID(x.uuid)),
    	  ("descriptionBoxUUID", taggedTypes.encodeDescriptionBoxUUID(x.descriptionBoxUUID)),
    	  ("unreifiedRelationshipUUID", taggedTypes.encodeUnreifiedRelationshipUUID(x.unreifiedRelationshipUUID)),
    	  ("domainUUID", taggedTypes.encodeConceptualEntitySingletonInstanceUUID(x.domainUUID)),
    	  ("rangeUUID", taggedTypes.encodeConceptualEntitySingletonInstanceUUID(x.rangeUUID))
    )
  }

  @JSExport
  def toJSON(c: UnreifiedRelationshipInstanceTuple)
  : String
  = encodeUnreifiedRelationshipInstanceTuple(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : UnreifiedRelationshipInstanceTuple
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeUnreifiedRelationshipInstanceTuple(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
