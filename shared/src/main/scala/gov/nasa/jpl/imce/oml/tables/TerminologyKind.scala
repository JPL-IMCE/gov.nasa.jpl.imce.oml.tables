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

sealed trait TerminologyKind

@JSExportTopLevel("OpenWorldDefinitions")
case object OpenWorldDefinitions extends TerminologyKind

@JSExportTopLevel("ClosedWorldDesignations")
case object ClosedWorldDesignations extends TerminologyKind

@JSExportTopLevel("TerminologyKind")
object TerminologyKind {

  def toString(k: TerminologyKind)
  : String
  = k match {
    case OpenWorldDefinitions =>
      "OpenWorldDefinitions"
    case ClosedWorldDesignations =>
      "ClosedWorldDesignations"
  }

  implicit val decodeTerminologyKind: Decoder[TerminologyKind] = Decoder.decodeString.map(fromString)
  implicit val encodeTerminologyKind: Encoder[TerminologyKind] = Encoder.encodeString.contramap[TerminologyKind](toString)

  def toJSON(k: TerminologyKind)
  : String
  = encodeTerminologyKind(k).noSpaces

  def fromString(k: String)
  : TerminologyKind
  = k match {
    case "OpenWorldDefinitions" =>
      OpenWorldDefinitions
    case "ClosedWorldDesignations" =>
      ClosedWorldDesignations
  }

  def fromJSON(k: String)
  : TerminologyKind
  = parser.parse(k) match {
    case scala.Right(json) =>
      decodeTerminologyKind(json.hcursor) match {
        case scala.Right(result) =>
          result
        case scala.Left(failure) =>
          throw failure
      }
    case scala.Left(failure) =>
      throw failure
  }

}