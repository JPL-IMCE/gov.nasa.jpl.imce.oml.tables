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

import io.circe._

import scala.scalajs.js.annotation.JSExportTopLevel
import scala.Predef.String

sealed trait CardinalityRestrictionKind

@JSExportTopLevel("MinCardinalityRestriction")
case object MinCardinalityRestriction extends CardinalityRestrictionKind

@JSExportTopLevel("MaxCardinalityRestriction")
case object MaxCardinalityRestriction extends CardinalityRestrictionKind

@JSExportTopLevel("ExactCardinalityRestriction")
case object ExactCardinalityRestriction extends CardinalityRestrictionKind

@JSExportTopLevel("CardinalityRestrictionKind")
object CardinalityRestrictionKind {

  def toString(k: CardinalityRestrictionKind)
  : String
  = k match {
    case MinCardinalityRestriction =>
      "MinCardinalityRestriction"
    case MaxCardinalityRestriction =>
      "MaxCardinalityRestriction"
    case ExactCardinalityRestriction =>
      "ExactCardinalityRestriction"
  }

  implicit val decodeCardinalityRestrictionKind: Decoder[CardinalityRestrictionKind] = Decoder.decodeString.map(fromString)
  implicit val encodeCardinalityRestrictionKind: Encoder[CardinalityRestrictionKind] = Encoder.encodeString.contramap[CardinalityRestrictionKind](toString)

  def toJSON(k: CardinalityRestrictionKind)
  : String
  = encodeCardinalityRestrictionKind(k).noSpaces

  def fromString(k: String)
  : CardinalityRestrictionKind
  = k match {
    case "MinCardinalityRestriction" =>
      MinCardinalityRestriction
    case "<=" =>
      MinCardinalityRestriction
    case "MaxCardinalityRestriction" =>
      MaxCardinalityRestriction
    case ">=" =>
      MaxCardinalityRestriction
    case "ExactCardinalityRestriction" =>
      ExactCardinalityRestriction
    case "==" =>
      ExactCardinalityRestriction
  }

  def fromJSON(k: String)
  : CardinalityRestrictionKind
  = parser.parse(k) match {
    case scala.Right(json) =>
      decodeCardinalityRestrictionKind(json.hcursor) match {
        case scala.Right(result) =>
          result
        case scala.Left(failure) =>
          throw failure
      }
    case scala.Left(failure) =>
      throw failure
  }

}