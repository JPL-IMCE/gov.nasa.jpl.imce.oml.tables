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

import scala.Predef.{ArrowAssoc,String}
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

sealed trait LiteralType

case object LiteralDateTimeType extends LiteralType

case object LiteralStringType extends LiteralType

case object LiteralUUIDType extends LiteralType

case object LiteralURIType extends LiteralType

sealed trait LiteralNumberType extends LiteralType

case object LiteralRealType extends LiteralNumberType

case object LiteralRationalType extends LiteralNumberType

case object LiteralFloatType extends LiteralNumberType

sealed trait AbstractLiteralDecimalType extends LiteralNumberType

case object LiteralPositiveIntegerType extends AbstractLiteralDecimalType

case object LiteralDecimalType extends AbstractLiteralDecimalType

object LiteralType {

  val literalPositiveIntegerType = LiteralPositiveIntegerType

  implicit val w
  : upickle.default.Writer[LiteralType]
  = upickle.default.Writer[LiteralType] {
    case LiteralDateTimeType =>
      upickle.Js.Str("LiteralDateTimeType")
    case LiteralStringType =>
      upickle.Js.Str("LiteralStringType")
    case LiteralUUIDType =>
      upickle.Js.Str("LiteralUUIDType")
    case LiteralURIType =>
      upickle.Js.Str("LiteralURIType")
    case LiteralRealType =>
      upickle.Js.Str("LiteralRealType")
    case LiteralRationalType =>
      upickle.Js.Str("LiteralRationalType")
    case LiteralFloatType =>
      upickle.Js.Str("LiteralFloatType")
    case LiteralPositiveIntegerType =>
      upickle.Js.Str("LiteralPositiveIntegerType")
    case LiteralDecimalType =>
      upickle.Js.Str("LiteralDecimalType")
  }

  @JSExport
  def toJSON(c: LiteralType)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[LiteralType]
  = upickle.default.Reader[LiteralType] {
    case upickle.Js.Str(str) =>
      str match {
        case "LiteralDateTimeType" =>
          LiteralDateTimeType
        case "LiteralStringType" =>
          LiteralStringType
        case "LiteralUUIDType" =>
          LiteralUUIDType
        case "LiteralURIType" =>
          LiteralURIType
        case "LiteralRealType" =>
          LiteralRealType
        case "LiteralRationalType" =>
          LiteralRationalType
        case "LiteralFloatType" =>
          LiteralFloatType
        case "LiteralPositiveIntegerType" =>
          LiteralPositiveIntegerType
        case "LiteralDecimalType" =>
          LiteralDecimalType
      }
  }

  @JSExport
  def fromJSON(c: String)
  : LiteralType
  = upickle.default.read[LiteralType](c)

}

object LiteralNumberType {

  implicit val r
  : upickle.default.Reader[LiteralNumberType]
  = upickle.default.Reader[LiteralNumberType] {
    case upickle.Js.Str(str) =>
      str match {
        case "LiteralRealType" =>
          LiteralRealType
        case "LiteralRationalType" =>
          LiteralRationalType
        case "LiteralFloatType" =>
          LiteralFloatType
        case "LiteralPositiveIntegerType" =>
          LiteralPositiveIntegerType
        case "LiteralDecimalType" =>
          LiteralDecimalType
      }
  }

}

@JSExportTopLevel("LiteralValue")
case class LiteralValue
( literalType: LiteralType,
  value: String)

object LiteralValue {

  implicit val w
  : upickle.default.Writer[LiteralValue]
  = upickle.default.Writer[LiteralValue] {
    case LiteralValue(t,v) =>
    upickle.Js.Obj(
      "literalType" -> upickle.Js.Str(t.toString),
      "value" -> upickle.Js.Str(v))
  }

  @JSExport
  def toJSON(c: LiteralValue)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[LiteralValue]
  = upickle.default.Reader[LiteralValue] {
    case upickle.Js.Obj(typePair, valuePair) =>
      LiteralValue(
        LiteralType.r.read(typePair._2),
        valuePair._2.str)
  }

  @JSExport
  def fromJSON(c: String)
  : LiteralValue
  = upickle.default.read[LiteralValue](c)

}

@JSExportTopLevel("LiteralNumber")
case class LiteralNumber
( literalType: LiteralNumberType,
  value: String)

object LiteralNumber {

  implicit val w
  : upickle.default.Writer[LiteralNumber]
  = upickle.default.Writer[LiteralNumber] {
    case LiteralNumber(t,v) =>
      upickle.Js.Obj(
        "literalType" -> upickle.Js.Str(t.toString),
        "value" -> upickle.Js.Str(v))
  }

  @JSExport
  def toJSON(c: LiteralNumber)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[LiteralNumber]
  = upickle.default.Reader[LiteralNumber] {
    case upickle.Js.Obj(typePair, valuePair) =>
      LiteralNumber(
        LiteralNumberType.r.read(typePair._2),
        valuePair._2.str)
  }

  @JSExport
  def fromJSON(c: String)
  : LiteralNumber
  = upickle.default.read[LiteralNumber](c)

}

@JSExportTopLevel("LiteralDateTime")
case class LiteralDateTime
( value: String )

object LiteralDateTime {

  implicit val w
  : upickle.default.Writer[LiteralDateTime]
  = upickle.default.Writer[LiteralDateTime] {
    case LiteralDateTime(v) =>
      upickle.Js.Str(v)
  }

  @JSExport
  def toJSON(c: LiteralDateTime)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[LiteralDateTime]
  = upickle.default.Reader[LiteralDateTime] {
    case upickle.Js.Str(v) =>
      LiteralDateTime(v)
  }

  @JSExport
  def fromJSON(c: String)
  : LiteralDateTime
  = upickle.default.read[LiteralDateTime](c)

}
