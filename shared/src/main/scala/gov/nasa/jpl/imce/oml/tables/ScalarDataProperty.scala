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
  * @param domainUUID[1,1]
  * @param rangeUUID[1,1]
  * @param name[1,1]
  */
@JSExportTopLevel("ScalarDataProperty")
case class ScalarDataProperty
(
  @(JSExport @field) uuid: taggedTypes.ScalarDataPropertyUUID,
  @(JSExport @field) tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) domainUUID: taggedTypes.StructureUUID,
  @(JSExport @field) rangeUUID: taggedTypes.DataRangeUUID,
  @(JSExport @field) name: taggedTypes.LocalName
) {
  // Ctor(uuidWithGenerator)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    domainUUID: taggedTypes.StructureUUID,
    rangeUUID: taggedTypes.DataRangeUUID,
    name: taggedTypes.LocalName)
  = this(
      taggedTypes.scalarDataPropertyUUID(oug.namespaceUUID(
        tboxUUID,
        "name" -> name).toString),
      tboxUUID,
      domainUUID,
      rangeUUID,
      name)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, domainUUID, rangeUUID, name).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ScalarDataProperty =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.domainUUID == that.domainUUID)  &&
  	  (this.rangeUUID == that.rangeUUID)  &&
  	  (this.name == that.name)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("ScalarDataPropertyHelper")
object ScalarDataPropertyHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "ScalarDataProperties.json"

  implicit val decodeScalarDataProperty: Decoder[ScalarDataProperty]
  = Decoder.instance[ScalarDataProperty] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.ScalarDataPropertyUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  domainUUID <- c.downField("domainUUID").as[taggedTypes.StructureUUID]
    	  rangeUUID <- c.downField("rangeUUID").as[taggedTypes.DataRangeUUID]
    	  name <- c.downField("name").as[taggedTypes.LocalName]
    	} yield ScalarDataProperty(
    	  uuid,
    	  tboxUUID,
    	  domainUUID,
    	  rangeUUID,
    	  name
    	)
  }
  
  implicit val encodeScalarDataProperty: Encoder[ScalarDataProperty]
  = new Encoder[ScalarDataProperty] {
    override final def apply(x: ScalarDataProperty): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeScalarDataPropertyUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("domainUUID", taggedTypes.encodeStructureUUID(x.domainUUID)),
    	  ("rangeUUID", taggedTypes.encodeDataRangeUUID(x.rangeUUID)),
    	  ("name", taggedTypes.encodeLocalName(x.name))
    )
  }

  @JSExport
  def toJSON(c: ScalarDataProperty)
  : String
  = encodeScalarDataProperty(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : ScalarDataProperty
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeScalarDataProperty(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
