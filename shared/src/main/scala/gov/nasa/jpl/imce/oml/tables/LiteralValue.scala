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

import scala.Option
import scala.Predef.{ArrowAssoc, String, augmentString}
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

sealed trait LiteralType

case object LiteralBooleanType extends LiteralType

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
    case LiteralBooleanType =>
      upickle.Js.Str("LiteralBooleanType")
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
        case "LiteralBooleanType" =>
          LiteralBooleanType
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

/**
  *
  * Xtext grammar:
  * LiteralValue
  * :	LiteralBoolean
  * | LiteralDateTime
  * | LiteralString
  * | LiteralUUID
  * | LiteralURI
  * | LiteralNumber;
  */
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

  implicit def toLiteralValue(v: Option[LiteralNumber])
  : Option[LiteralValue]
  = v.map { case LiteralNumber(lt, lv) =>
    LiteralValue(lt, lv)
  }

  def parseLiteralValue(v: String)
  : Option[LiteralValue]
  = parseLiteralBoolean(v)
    .orElse(LiteralDateTime.parseLiteralDateTime(v))
    .orElse(parseLiteralString(v))
    .orElse(parseLiteralUUID(v))
    .orElse(parseLiteralURI(v))
    .orElse(LiteralNumber.parseLiteralNumber(v))

  /**
    * @param v A string representation of a literal value of some kind
    * @return A non-empty LiteralValue if v matches the syntax of a LiteralBoolean
    */
  def parseLiteralBoolean(v: String)
  : Option[LiteralValue]
  = if ("true" == v || "false" == v)
    Option.apply(LiteralValue(LiteralBooleanType, v))
  else
    Option.empty[LiteralValue]

  /**
    * @param v A string representation of a literal value of some kind
    * @return A non-empty LiteralValue if v matches the syntax of a LiteralString
    *
    * LiteralString returns LiteralString:					  {LiteralString} string=STRING_VALUE;
    * terminal STRING_VALUE returns StringDataType: 	'"' ( '\\' . ('b'|'t'|'n'|'f'|'r'|'"'|"'"|'\\')| !('\\'|'"') )* '"';
    */
  def parseLiteralString(v: String)
  : Option[LiteralValue]
  = if (v.startsWith("\"") && v.endsWith("\""))
    Option.apply(LiteralValue(LiteralBooleanType, v))
  else
    Option.empty[LiteralValue]

  /**
    * Regex pattern for UUID
    *
    * LiteralUUID returns LiteralUUID:						{LiteralUUID} uuid=UUIDDataType;
    * UUIDDataType returns UUIDDataType:					UUID;
    * terminal UUID returns UUIDDataType:					'uuid=' HEX_8DIGITS '-' HEX_4DIGITS '-'
    *                                                     HEX_4DIGITS '-' HEX_4DIGITS '-' HEX_12DIGITS;
    * terminal fragment HEX_12DIGITS:							HEX_8DIGITS HEX_4DIGITS;
    * terminal fragment HEX_8DIGITS:							HEX_4DIGITS HEX_4DIGITS;
    * terminal fragment HEX_4DIGITS:							HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT;
    * terminal fragment HEX_LETTER:							  'a'..'f' | 'A'..'F';
    * terminal fragment HEX_DIGIT:							  DIGIT | HEX_LETTER;
    */
  val UUID = """uuid=[\da-zA-Z]{8}\-[\da-zA-Z]{4}\-[\da-zA-Z]{}\-[\da-zA-Z]{4}\-[\da-zA-Z]{12}""".r.pattern

  /**
    * @param v A string representation of a literal value of some kind
    * @return A non-empty LiteralValue if v matches the syntax of a LiteralUUID
    */
  def parseLiteralUUID(v: String)
  : Option[LiteralValue]
  = if (UUID.matcher(v).matches())
    Option.apply(LiteralValue(LiteralBooleanType, v))
  else
    Option.empty[LiteralValue]

  /**
    * Regex Pattern for URI
    *
    * terminal URI returns URIDataType:						SCHEME ':' IHIER_PART ( '#' IFRAGMENT )?;
    * terminal fragment SCHEME:								    ALPHA ( ALPHA | DIGIT | '+' | '-' | '.' )*;
    * terminal fragment IHIER_PART:							  '//' IAUTHORITY IPATH?;
    * terminal fragment IFRAGMENT:							  ISEGMENT;
    * terminal fragment IAUTHORITY:							  ( IUSER_INFO '@' )? IHOST ( ':' PORT )?;
    * terminal fragment IPATH:								    ISEGMENT ('/' ISEGMENT)*;
    * terminal fragment ISEGMENT:								  IPCHAR+;
    * terminal fragment IUSER_INFO:							  IUSER_PART (':' IUSER_PART)*;
    * terminal fragment IUSER_PART:							  ( UNRESERVED | PCT_ENCODED )+;
    * terminal fragment IUNRESERVED:							ALPHA IUNRESERVED_PART? ( '.' IUNRESERVED_PART )*;
    * terminal fragment IUNRESERVED_PART:					ALPHA | DIGIT | '-' | '_' | '~';
    * terminal fragment IHOST:								    IPV4_ADDRESS | IUNRESERVED;
    * terminal fragment IPV4_ADDRESS:							DEC_OCTET '.' DEC_OCTET '.' DEC_OCTET '.' DEC_OCTET;
    * terminal fragment DEC_OCTET:							  ( DIGIT | '1' DIGIT | '2' DIGIT04 DIGIT | '25' DIGIT05);
    * terminal fragment DIGIT04:								  '0'..'4';
    * terminal fragment DIGIT05:								  '0'..'5';
    *
    * TODO The pattern is incomplete...
    */
  val URI = """[a-zA-Z][\w+\-\.]*://.*""".r.pattern
  /**
    * @param v A string representation of a literal value of some kind
    * @return A non-empty LiteralValue if v matches the syntax of a LiteralURI
    */
  def parseLiteralURI(v: String)
  : Option[LiteralValue]
  = if (URI.matcher(v).matches())
    Option.apply(LiteralValue(LiteralBooleanType, v))
  else
    Option.empty[LiteralValue]

}

@JSExportTopLevel("LiteralNumber")
case class LiteralNumber
( literalType: LiteralNumberType,
  value: String)

/**
  * Xtext grammar:
  * LiteralNumber
  * : LiteralReal
  * | LiteralRational
  * | LiteralFloat
  * | LiteralDecimal;
  */
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

  def parseLiteralNumber(v: String)
  : Option[LiteralNumber]
  = parseLiteralReal(v)
    .orElse(parseLiteralFloat(v))
    .orElse(parseLiteralRational(v))
    .orElse(parseLiteralDecimal(v))

  /**
    * Regex Pattern for REAL
    *
    * LiteralRational returns LiteralRational:				{LiteralRational} rational=RationalDataType;
    * RationalDataType returns RationalDataType:			RATIONAL;
    * terminal RATIONAL returns RationalDataType:			'-'? DIGIT+ '/' DIGIT+;
    */
  val REAL = """\{[+-]?[a-zA-Z][a-zA-Z0-9]*\}""".r.pattern

  /**
    * @param v A string representation of a literal value of some kind
    * @return A non-empty LiteralValue if v matches the syntax of a LiteralReal
    */
  def parseLiteralReal(v: String)
  : Option[LiteralNumber]
  = if (REAL.matcher(v).matches())
    Option.apply(LiteralNumber(LiteralRealType, v))
  else
    Option.empty[LiteralNumber]

  /**
    * Regex Pattern for RATIONAL
    *
    * LiteralRational returns LiteralRational:				  {LiteralRational} rational=RationalDataType;
    * RationalDataType returns RationalDataType:				RATIONAL;
    * terminal RATIONAL returns RationalDataType:				'-'? DIGIT+ '/' DIGIT+;
    */
  val RATIONAL = """\-?[0-9]+/[0-9]+""".r.pattern

  /**
    * @param v A string representation of a literal value of some kind
    * @return A non-empty LiteralValue if v matches the syntax of a LiteralRational
    */
  def parseLiteralRational(v: String)
  : Option[LiteralNumber]
  = if (RATIONAL.matcher(v).matches())
    Option.apply(LiteralNumber(LiteralRationalType, v))
  else
    Option.empty[LiteralNumber]

  /**
    * Regex Pattern for FLOAT
    *
    * LiteralFloat returns LiteralFloat:						{LiteralFloat} float=FLOAT;
    * terminal FLOAT returns FloatDataType:         '-'?
    *                                               (DIGIT+ '.' DIGIT+)
    *                                               (('e' | 'E') ('+' | '-')? DIGIT+)?;
    */
  val FLOAT = """\-?[0-9]+\.[0-9]+([eE][+\-]?[0-9]+)?""".r.pattern

  /**
    * @param v A string representation of a literal value of some kind
    * @return A non-empty LiteralValue if v matches the syntax of a LiteralFloat
    */
  def parseLiteralFloat(v: String)
  : Option[LiteralNumber]
  = if (FLOAT.matcher(v).matches())
    Option.apply(LiteralNumber(LiteralRationalType, v))
  else
    Option.empty[LiteralNumber]

  /**
    * @param v A string representation of a literal value of some kind
    * @return A non-empty LiteralValue if v matches the syntax of a LiteralDecimal
    *
    * LiteralDecimal returns LiteralDecimal:					{LiteralDecimal} decimal=DIGITS | decimal=DECIMAL;
    */
  def parseLiteralDecimal(v: String)
  : Option[LiteralNumber]
  = parsePositiveIntegerLiteral(v)
    .orElse(parseHex(v))
    .orElse(parseDec(v))

  /**
    * Regex Pattern for HEX
    *
    * terminal fragment HEX:									        ('0x'|'0X')
    *                                                 (HEX_DIGIT|'_')+
    *                                                 ('#' (('b'|'B')('i'|'I') | ('l'|'L')))?;
    */
  val HEX = """0[xX][0-9a-fA-F_]+(#([bB][iI]|[lL]))?""".r.pattern

  def parseHex(v: String)
  : Option[LiteralNumber]
  = if (HEX.matcher(v).matches())
    Option.apply(LiteralNumber(LiteralPositiveIntegerType, v))
  else
    Option.empty[LiteralNumber]

  /**
    * Regex Pattern for DEC
    *
    * terminal fragment DEC:                          '-'?
    *                                                 DIGIT+
    *                                                 (('e'|'E') ('+'|'-')? DIGIT+)?
    *                                                 (('b'|'B')('i'|'I'|'d'|'D') | ('l'|'L'|'d'|'D'|'f'|'F'))?;
    */
  val DEC = """\-?[0-9]+([eE][+\-]?[0-9]+)?([bB][iIdD]|[lLdDfF])?""".r.pattern

  def parseDec(v: String)
  : Option[LiteralNumber]
  = if (DEC.matcher(v).matches())
    Option.apply(LiteralNumber(LiteralPositiveIntegerType, v))
  else
    Option.empty[LiteralNumber]

  /**
    * Regex Pattern for PositiveIntegerLiteral
    *
    * PositiveIntegerLiteral returns PositiveIntegerLiteral: 	DIGITS;
    */
  val DIGITS = """\d+""".r.pattern

  def parsePositiveIntegerLiteral(v: String)
  : Option[LiteralNumber]
  = if (DIGITS.matcher(v).matches())
    Option.apply(LiteralNumber(LiteralPositiveIntegerType, v))
  else
    Option.empty[LiteralNumber]

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

  /**
    * @param v A string representation of a literal value of some kind
    * @return A non-empty LiteralValue if v matches the syntax of a LiteralDateTime
    *
    * LiteralDateTime returns LiteralDateTime: 				{LiteralDateTime} dateTime=DATE_TIME;
    * terminal DATE_TIME returns DateTimeDataType: 		'dateTime=' YEAR_FRAG '-' MONTH_FRAG '-' DAY_FRAG 'T'
    *                                                 ( (HOUR_FRAG ':' MINUTE_FRAG ':' SECOND_FRAG) | END_OF_DAY_FRAG )
    *                                                 TIMEZONE_FRAG?;
    * terminal fragment YEAR_FRAG:							      '-'? ( (DIGIT19 DIGIT DIGIT DIGIT+) | ('0' DIGIT DIGIT DIGIT) );
    * terminal fragment MONTH_FRAG:							      ('0' DIGIT19) | ('1' DIGIT02);
    * terminal fragment DAY_FRAG:								      ('0' DIGIT19) | (('1'|'2') DIGIT) | ('3' ('0'|'1'));
    * terminal fragment HOUR_FRAG:							      (('0'|'1')DIGIT) | ('2'DIGIT03);
    * terminal fragment MINUTE_FRAG:							    DIGIT05 DIGIT;
    * terminal fragment SECOND_FRAG:							    DIGIT05 DIGIT ('.' DIGIT+)?;
    * terminal fragment END_OF_DAY_FRAG:						  '24:00:00' ('.' DIGIT+)?;
    * terminal fragment TIMEZONE_FRAG:						    'Z' |
    *                                                 ('+'|'-') (('0' DIGIT | '1' DIGIT03) ':' MINUTE_FRAG | '14:00');
    */
  def parseLiteralDateTime(v: String)
  : Option[LiteralValue]
  = if (v.startsWith("dateTime=")) // TODO parse the value according the lexical rules for DateTime!
    Option.apply(LiteralValue(LiteralDateTimeType, v))
  else
    Option.empty[LiteralValue]

  def parseDateTime(v: String)
  : Option[LiteralDateTime]
  = if (v.startsWith("dateTime=")) // TODO parse the value according the lexical rules for DateTime!
    Option.apply(LiteralDateTime(v))
  else
    Option.empty[LiteralDateTime]

}
