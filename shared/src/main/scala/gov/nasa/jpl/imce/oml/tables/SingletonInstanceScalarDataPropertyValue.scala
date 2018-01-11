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
  * @param singletonInstanceUUID[1,1]
  * @param scalarDataPropertyUUID[1,1]
  * @param scalarPropertyValue[1,1]
  * @param valueTypeUUID[0,1]
  */
case class SingletonInstanceScalarDataPropertyValue
(
  @(JSExport @field) override val uuid: taggedTypes.SingletonInstanceScalarDataPropertyValueUUID,
  @(JSExport @field) val descriptionBoxUUID: taggedTypes.DescriptionBoxUUID,
  @(JSExport @field) val singletonInstanceUUID: taggedTypes.ConceptualEntitySingletonInstanceUUID,
  @(JSExport @field) val scalarDataPropertyUUID: taggedTypes.EntityScalarDataPropertyUUID,
  @(JSExport @field) val scalarPropertyValue: LiteralValue,
  @(JSExport @field) val valueTypeUUID: scala.Option[taggedTypes.DataRangeUUID]
) extends ModuleElement with ValueCrossReferenceTuple {
  def this(
    uuid: taggedTypes.SingletonInstanceScalarDataPropertyValueUUID,
    descriptionBoxUUID: taggedTypes.DescriptionBoxUUID,
    singletonInstanceUUID: taggedTypes.ConceptualEntitySingletonInstanceUUID,
    scalarDataPropertyUUID: taggedTypes.EntityScalarDataPropertyUUID,
    scalarPropertyValue: LiteralValue)
  = this(
      uuid,
      descriptionBoxUUID,
      singletonInstanceUUID,
      scalarDataPropertyUUID,
      scalarPropertyValue,
      scala.None /* valueTypeUUID */)

  def withValueTypeUUID(l: taggedTypes.DataRangeUUID)	 
  : SingletonInstanceScalarDataPropertyValue
  = copy(valueTypeUUID=scala.Some(l))
  
  // Ctor(uuidWithContainer)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    descriptionBoxUUID: taggedTypes.DescriptionBoxUUID,
    singletonInstanceUUID: taggedTypes.ConceptualEntitySingletonInstanceUUID,
    scalarDataPropertyUUID: taggedTypes.EntityScalarDataPropertyUUID,
    scalarPropertyValue: LiteralValue)
  = this(
      taggedTypes.singletonInstanceScalarDataPropertyValueUUID(oug.namespaceUUID(
        "SingletonInstanceScalarDataPropertyValue",
        "descriptionBox" -> descriptionBoxUUID,
        "singletonInstance" -> singletonInstanceUUID,
        "scalarDataProperty" -> scalarDataPropertyUUID,
        "scalarPropertyValue" -> scalarPropertyValue.value).toString),
      descriptionBoxUUID,
      singletonInstanceUUID,
      scalarDataPropertyUUID,
      scalarPropertyValue)

val vertexId: scala.Long = uuid.hashCode.toLong

  override val hashCode
  : scala.Int 
  = (uuid, descriptionBoxUUID, singletonInstanceUUID, scalarDataPropertyUUID, scalarPropertyValue, valueTypeUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: SingletonInstanceScalarDataPropertyValue =>
  	  (this.uuid == that.uuid) &&
  	  (this.descriptionBoxUUID == that.descriptionBoxUUID)  &&
  	  (this.singletonInstanceUUID == that.singletonInstanceUUID)  &&
  	  (this.scalarDataPropertyUUID == that.scalarDataPropertyUUID)  &&
  	  (this.scalarPropertyValue == that.scalarPropertyValue) &&
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

@JSExportTopLevel("SingletonInstanceScalarDataPropertyValueHelper")
object SingletonInstanceScalarDataPropertyValueHelper {

  import io.circe.{Decoder, Encoder, HCursor, Json}
  import io.circe.parser.parse
  import scala.Predef.String

  val TABLE_JSON_FILENAME 
  : String 
  = "SingletonInstanceScalarDataPropertyValues.json"

  implicit val decodeSingletonInstanceScalarDataPropertyValue: Decoder[SingletonInstanceScalarDataPropertyValue]
  = Decoder.instance[SingletonInstanceScalarDataPropertyValue] { c: HCursor =>
    
    import cats.syntax.either._
  
    for {
    	  uuid <- c.downField("uuid").as[taggedTypes.SingletonInstanceScalarDataPropertyValueUUID]
    	  descriptionBoxUUID <- c.downField("descriptionBoxUUID").as[taggedTypes.DescriptionBoxUUID]
    	  singletonInstanceUUID <- c.downField("singletonInstanceUUID").as[taggedTypes.ConceptualEntitySingletonInstanceUUID]
    	  scalarDataPropertyUUID <- c.downField("scalarDataPropertyUUID").as[taggedTypes.EntityScalarDataPropertyUUID]
    	  scalarPropertyValue <- c.downField("scalarPropertyValue").as[LiteralValue](LiteralValue.decodeLiteralValueArray)
    	  valueTypeUUID <- Decoder.decodeOption(taggedTypes.decodeDataRangeUUID)(c.downField("valueTypeUUID").success.get)
    	} yield SingletonInstanceScalarDataPropertyValue(
    	  uuid,
    	  descriptionBoxUUID,
    	  singletonInstanceUUID,
    	  scalarDataPropertyUUID,
    	  scalarPropertyValue,
    	  valueTypeUUID
    	)
  }
  
  implicit val encodeSingletonInstanceScalarDataPropertyValue: Encoder[SingletonInstanceScalarDataPropertyValue]
  = new Encoder[SingletonInstanceScalarDataPropertyValue] {
    override final def apply(x: SingletonInstanceScalarDataPropertyValue): Json 
    = Json.obj(
    	  ("uuid", taggedTypes.encodeSingletonInstanceScalarDataPropertyValueUUID(x.uuid)),
    	  ("descriptionBoxUUID", taggedTypes.encodeDescriptionBoxUUID(x.descriptionBoxUUID)),
    	  ("singletonInstanceUUID", taggedTypes.encodeConceptualEntitySingletonInstanceUUID(x.singletonInstanceUUID)),
    	  ("scalarDataPropertyUUID", taggedTypes.encodeEntityScalarDataPropertyUUID(x.scalarDataPropertyUUID)),
    	  ("scalarPropertyValue", LiteralValue.encodeLiteralValueArray(x.scalarPropertyValue)),
    	  ("valueTypeUUID", Encoder.encodeOption(taggedTypes.encodeDataRangeUUID).apply(x.valueTypeUUID))
    )
  }

  @JSExport
  def toJSON(c: SingletonInstanceScalarDataPropertyValue)
  : String
  = encodeSingletonInstanceScalarDataPropertyValue(c).noSpaces

  @JSExport
  def fromJSON(c: String)
  : SingletonInstanceScalarDataPropertyValue
  = parse(c) match {
  	case scala.Right(json) =>
  	  decodeSingletonInstanceScalarDataPropertyValue(json.hcursor) match {
  	    	case scala.Right(result) =>
  	    	  result
  	    	case scala.Left(failure) =>
  	    	  throw failure
  	  }
    case scala.Left(failure) =>
  	  throw failure
  }

}
