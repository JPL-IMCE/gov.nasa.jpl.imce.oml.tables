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
  * @param axiomUUID[1,1]
  * @param value[1,1]
  * @param valueTypeUUID[0,1]
  */
case class ScalarOneOfLiteralAxiom
(
  @(JSExport @field) override val uuid: taggedTypes.ScalarOneOfLiteralAxiomUUID,
  @(JSExport @field) override val tboxUUID: taggedTypes.TerminologyBoxUUID,
  @(JSExport @field) val axiomUUID: taggedTypes.ScalarOneOfRestrictionUUID,
  @(JSExport @field) val value: LiteralValue,
  @(JSExport @field) val valueTypeUUID: scala.Option[taggedTypes.DataRangeUUID]
) extends TermAxiom with ValueCrossReferenceTuple {
  def this(
    uuid: taggedTypes.ScalarOneOfLiteralAxiomUUID,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    axiomUUID: taggedTypes.ScalarOneOfRestrictionUUID,
    value: LiteralValue)
  = this(
      uuid,
      tboxUUID,
      axiomUUID,
      value,
      scala.None /* valueTypeUUID */)

  def withValueTypeUUID(l: taggedTypes.DataRangeUUID)	 
  : ScalarOneOfLiteralAxiom
  = copy(valueTypeUUID=scala.Some(l))
  
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    tboxUUID: taggedTypes.TerminologyBoxUUID,
    axiomUUID: taggedTypes.ScalarOneOfRestrictionUUID,
    value: LiteralValue)
  = this(
      taggedTypes.scalarOneOfLiteralAxiomUUID(oug.namespaceUUID(
        "ScalarOneOfLiteralAxiom",
        "tbox" -> tboxUUID,
        "axiom" -> axiomUUID,
        "value" -> value.value).toString),
      tboxUUID,
      axiomUUID,
      value)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, tboxUUID, axiomUUID, value, valueTypeUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: ScalarOneOfLiteralAxiom =>
  	  (this.uuid == that.uuid) &&
  	  (this.tboxUUID == that.tboxUUID)  &&
  	  (this.axiomUUID == that.axiomUUID)  &&
  	  (this.value == that.value) &&
  	  ((this.valueTypeUUID, that.valueTypeUUID) match {
  	      case (scala.Some(t1), scala.Some(t2)) =>
  	        t1 == t2
  	      case (scala.None, scala.None) =>
  	        true
  	      case _ =>
  	        false
  	  })
    case _ =>
      false
  }
  
}

@JSExportTopLevel("ScalarOneOfLiteralAxiomHelper")
object ScalarOneOfLiteralAxiomHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "ScalarOneOfLiteralAxioms.json"

  implicit val decodeScalarOneOfLiteralAxiom: Decoder[ScalarOneOfLiteralAxiom]
  = Decoder.instance[ScalarOneOfLiteralAxiom] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.ScalarOneOfLiteralAxiomUUID]
    	  tboxUUID <- c.downField("tboxUUID").as[taggedTypes.TerminologyBoxUUID]
    	  axiomUUID <- c.downField("axiomUUID").as[taggedTypes.ScalarOneOfRestrictionUUID]
    	  value <- c.downField("value").as[LiteralValue](LiteralValue.decodeLiteralValueArray)
    	  valueTypeUUID <- Decoder.decodeOption(taggedTypes.decodeDataRangeUUID)(c.downField("valueTypeUUID").success.get)
    	} yield ScalarOneOfLiteralAxiom(
    	  uuid,
    	  tboxUUID,
    	  axiomUUID,
    	  value,
    	  valueTypeUUID
    	)
  }
  
  implicit val encodeScalarOneOfLiteralAxiom: Encoder[ScalarOneOfLiteralAxiom]
  = new Encoder[ScalarOneOfLiteralAxiom] {
    override final def apply(x: ScalarOneOfLiteralAxiom): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeScalarOneOfLiteralAxiomUUID(x.uuid)),
    	  ("tboxUUID", taggedTypes.encodeTerminologyBoxUUID(x.tboxUUID)),
    	  ("axiomUUID", taggedTypes.encodeScalarOneOfRestrictionUUID(x.axiomUUID)),
    	  ("value", LiteralValue.encodeLiteralValueArray(x.value)),
    	  ("valueTypeUUID", Encoder.encodeOption(taggedTypes.encodeDataRangeUUID).apply(x.valueTypeUUID))
    )
  }

  @JSExport
  def toJSON(c: ScalarOneOfLiteralAxiom)
  : String
  = encodeScalarOneOfLiteralAxiom(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : ScalarOneOfLiteralAxiom
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeScalarOneOfLiteralAxiom(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
