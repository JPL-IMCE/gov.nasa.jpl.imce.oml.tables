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
  * @param rangeUUID[1,1]
  * @param enumerationUUID[1,1]
  */
@JSExportTopLevel("InstanceRelationshipOneOfRestriction")
case class InstanceRelationshipOneOfRestriction
(
  @(JSExport @field) override val uuid: taggedTypes.InstanceRelationshipOneOfRestrictionUUID,
  @(JSExport @field) val rangeUUID: taggedTypes.ConceptualEntitySingletonInstanceUUID,
  @(JSExport @field) val enumerationUUID: taggedTypes.InstanceRelationshipEnumerationRestrictionUUID
) extends ElementCrossReferenceTuple {
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    rangeUUID: taggedTypes.ConceptualEntitySingletonInstanceUUID,
    enumerationUUID: taggedTypes.InstanceRelationshipEnumerationRestrictionUUID)
  = this(
      taggedTypes.instanceRelationshipOneOfRestrictionUUID(oug.namespaceUUID(
        "InstanceRelationshipOneOfRestriction",
        "range" -> rangeUUID,
        "enumeration" -> enumerationUUID).toString),
      rangeUUID,
      enumerationUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, rangeUUID, enumerationUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: InstanceRelationshipOneOfRestriction =>
  	  (this.uuid == that.uuid) &&
  	  (this.rangeUUID == that.rangeUUID)  &&
  	  (this.enumerationUUID == that.enumerationUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("InstanceRelationshipOneOfRestrictionHelper")
object InstanceRelationshipOneOfRestrictionHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "InstanceRelationshipOneOfRestrictions.json"

  implicit val decodeInstanceRelationshipOneOfRestriction: Decoder[InstanceRelationshipOneOfRestriction]
  = Decoder.instance[InstanceRelationshipOneOfRestriction] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.InstanceRelationshipOneOfRestrictionUUID]
    	  rangeUUID <- c.downField("rangeUUID").as[taggedTypes.ConceptualEntitySingletonInstanceUUID]
    	  enumerationUUID <- c.downField("enumerationUUID").as[taggedTypes.InstanceRelationshipEnumerationRestrictionUUID]
    	} yield InstanceRelationshipOneOfRestriction(
    	  uuid,
    	  rangeUUID,
    	  enumerationUUID
    	)
  }
  
  implicit val encodeInstanceRelationshipOneOfRestriction: Encoder[InstanceRelationshipOneOfRestriction]
  = new Encoder[InstanceRelationshipOneOfRestriction] {
    override final def apply(x: InstanceRelationshipOneOfRestriction): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeInstanceRelationshipOneOfRestrictionUUID(x.uuid)),
    	  ("rangeUUID", taggedTypes.encodeConceptualEntitySingletonInstanceUUID(x.rangeUUID)),
    	  ("enumerationUUID", taggedTypes.encodeInstanceRelationshipEnumerationRestrictionUUID(x.enumerationUUID))
    )
  }

  @JSExport
  def toJSON(c: InstanceRelationshipOneOfRestriction)
  : String
  = encodeInstanceRelationshipOneOfRestriction(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : InstanceRelationshipOneOfRestriction
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeInstanceRelationshipOneOfRestriction(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
