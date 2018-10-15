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
  * @param tboxUUID[1,1]
  * @param name[1,1]
  * @param headUUID[1,1]
  */
@JSExportTopLevel("ChainRule")
case class ChainRule
(
  @(JSExport @field) override val uuid: taggedTypes.ChainRuleUUID,
  @(JSExport @field) override val tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) override val name: taggedTypes.LocalName,
  @(JSExport @field) val headUUID: taggedTypes.RestrictableRelationshipUUID
) extends Rule {
  // Ctor(uuidWithGenerator)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    name: taggedTypes.LocalName,
    headUUID: taggedTypes.RestrictableRelationshipUUID)
  = this(
      taggedTypes.chainRuleUUID(oug.namespaceUUID(
        tboxUUID,
        "name" -> name).toString),
      tboxUUID,
      name,
      headUUID)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, name, headUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ChainRule =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.name == that.name) &&
  	  (this.headUUID == that.headUUID) 
    case _ =>
      false
  }
  
}

@JSExportTopLevel("ChainRuleHelper")
object ChainRuleHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "ChainRules.json"

  implicit val decodeChainRule: Decoder[ChainRule]
  = Decoder.instance[ChainRule] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.ChainRuleUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  name <- c.downField("name").as[taggedTypes.LocalName]
    	  headUUID <- c.downField("headUUID").as[taggedTypes.RestrictableRelationshipUUID]
    	} yield ChainRule(
    	  uuid,
    	  tboxUUID,
    	  name,
    	  headUUID
    	)
  }
  
  implicit val encodeChainRule: Encoder[ChainRule]
  = new Encoder[ChainRule] {
    override final def apply(x: ChainRule): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeChainRuleUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("name", taggedTypes.encodeLocalName(x.name)),
    	  ("headUUID", taggedTypes.encodeRestrictableRelationshipUUID(x.headUUID))
    )
  }

  @JSExport
  def toJSON(c: ChainRule)
  : String
  = encodeChainRule(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : ChainRule
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeChainRule(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
