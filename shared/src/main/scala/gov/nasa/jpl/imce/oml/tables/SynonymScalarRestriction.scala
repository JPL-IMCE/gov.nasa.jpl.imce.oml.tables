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
@JSExportTopLevel("SynonymScalarRestriction")
case class SynonymScalarRestriction
(
  @(JSExport @field) override val uuid: taggedTypes.SynonymScalarRestrictionUUID,
  @(JSExport @field) override val tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) override val restrictedRangeUUID: taggedTypes.DataRangeUUID,
  @(JSExport @field) override val name: taggedTypes.LocalName
) extends RestrictedDataRange {
  // Ctor(uuidWithGenerator)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    restrictedRangeUUID: taggedTypes.DataRangeUUID,
    name: taggedTypes.LocalName)
  = this(
      taggedTypes.synonymScalarRestrictionUUID(oug.namespaceUUID(
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
  	case that: SynonymScalarRestriction =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.restrictedRangeUUID == that.restrictedRangeUUID)  &&
  	  (this.name == that.name)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("SynonymScalarRestrictionHelper")
object SynonymScalarRestrictionHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "SynonymScalarRestrictions.json"

  implicit val decodeSynonymScalarRestriction: Decoder[SynonymScalarRestriction]
  = Decoder.instance[SynonymScalarRestriction] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.SynonymScalarRestrictionUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  restrictedRangeUUID <- c.downField("restrictedRangeUUID").as[taggedTypes.DataRangeUUID]
    	  name <- c.downField("name").as[taggedTypes.LocalName]
    	} yield SynonymScalarRestriction(
    	  uuid,
    	  tboxUUID,
    	  restrictedRangeUUID,
    	  name
    	)
  }
  
  implicit val encodeSynonymScalarRestriction: Encoder[SynonymScalarRestriction]
  = new Encoder[SynonymScalarRestriction] {
    override final def apply(x: SynonymScalarRestriction): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeSynonymScalarRestrictionUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("restrictedRangeUUID", taggedTypes.encodeDataRangeUUID(x.restrictedRangeUUID)),
    	  ("name", taggedTypes.encodeLocalName(x.name))
    )
  }

  @JSExport
  def toJSON(c: SynonymScalarRestriction)
  : String
  = encodeSynonymScalarRestriction(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : SynonymScalarRestriction
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeSynonymScalarRestriction(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
