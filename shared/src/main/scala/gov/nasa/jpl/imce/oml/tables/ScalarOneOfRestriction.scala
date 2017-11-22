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
  * @param restrictedRangeUUID[1,1]
  * @param name[1,1]
  */
@JSExportTopLevel("ScalarOneOfRestriction")
case class ScalarOneOfRestriction
(
  @(JSExport @field) uuid: taggedTypes.ScalarOneOfRestrictionUUID,
  @(JSExport @field) tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) restrictedRangeUUID: taggedTypes.DataRangeUUID,
  @(JSExport @field) name: taggedTypes.LocalName
) {
  // Ctor(uuidWithGenerator)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    restrictedRangeUUID: taggedTypes.DataRangeUUID,
    name: taggedTypes.LocalName)
  = this(
      taggedTypes.scalarOneOfRestrictionUUID(oug.namespaceUUID(
        tboxUUID,
        "name" -> name).toString),
      tboxUUID,
      restrictedRangeUUID,
      name)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, restrictedRangeUUID, name).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ScalarOneOfRestriction =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.restrictedRangeUUID == that.restrictedRangeUUID)  &&
  	  (this.name == that.name)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("ScalarOneOfRestrictionHelper")
object ScalarOneOfRestrictionHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "ScalarOneOfRestrictions.json"

  implicit val decodeScalarOneOfRestriction: Decoder[ScalarOneOfRestriction]
  = Decoder.instance[ScalarOneOfRestriction] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.ScalarOneOfRestrictionUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  restrictedRangeUUID <- c.downField("restrictedRangeUUID").as[taggedTypes.DataRangeUUID]
    	  name <- c.downField("name").as[taggedTypes.LocalName]
    	} yield ScalarOneOfRestriction(
    	  uuid,
    	  tboxUUID,
    	  restrictedRangeUUID,
    	  name
    	)
  }
  
  implicit val encodeScalarOneOfRestriction: Encoder[ScalarOneOfRestriction]
  = new Encoder[ScalarOneOfRestriction] {
    override final def apply(x: ScalarOneOfRestriction): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeScalarOneOfRestrictionUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("restrictedRangeUUID", taggedTypes.encodeDataRangeUUID(x.restrictedRangeUUID)),
    	  ("name", taggedTypes.encodeLocalName(x.name))
    )
  }

  @JSExport
  def toJSON(c: ScalarOneOfRestriction)
  : String
  = encodeScalarOneOfRestriction(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : ScalarOneOfRestriction
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeScalarOneOfRestriction(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
