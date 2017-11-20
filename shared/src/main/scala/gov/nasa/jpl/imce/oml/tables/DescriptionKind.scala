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

sealed trait DescriptionKind

@JSExportTopLevel("Final")
case object Final extends DescriptionKind

@JSExportTopLevel("Partial")
case object Partial extends DescriptionKind

@JSExportTopLevel("DescriptionKind")
object DescriptionKind {

  def toString(k: DescriptionKind)
  : String
  = k match {
    case Final =>
      "Final"
    case Partial =>
      "Partial"
  }

  implicit val decodeDescriptionKind: Decoder[DescriptionKind] = Decoder.decodeString.map(fromString)
  implicit val encodeDescriptionKind: Encoder[DescriptionKind] = Encoder.encodeString.contramap[DescriptionKind](toString)

  def toJSON(k: DescriptionKind)
  : String
  = encodeDescriptionKind(k).noSpaces

  def fromString(k: String)
  : DescriptionKind
  = k match {
    case "Final" =>
      Final
    case "Partial" =>
      Partial
  }

  def fromJSON(k: String)
  : DescriptionKind
  = parser.parse(k) match {
    case scala.Right(json) =>
      decodeDescriptionKind(json.hcursor) match {
        case scala.Right(result) =>
          result
        case scala.Left(failure) =>
          throw failure
      }
    case scala.Left(failure) =>
      throw failure
  }
}
