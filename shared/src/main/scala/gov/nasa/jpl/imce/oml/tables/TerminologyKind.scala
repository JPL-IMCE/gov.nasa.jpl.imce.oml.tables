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

import scala.scalajs.js.annotation.{JSExport, JSExportDescendentObjects}
import scala.Predef.String

@JSExportDescendentObjects
sealed trait TerminologyKind

case object OpenWorldDefinitions extends TerminologyKind

case object ClosedWorldDesignations extends TerminologyKind

@JSExport
object TerminologyKind {

  @JSExport
  def toString(k: TerminologyKind)
  : String
  = k match {
    case OpenWorldDefinitions =>
      "OpenWorldDefinitions"
    case ClosedWorldDesignations =>
      "ClosedWorldDesignations"
  }

  implicit val w
  : upickle.default.Writer[TerminologyKind]
  = upickle.default.Writer[TerminologyKind]{ (k: TerminologyKind) =>
    upickle.Js.Str(toString(k))
  }

  @JSExport
  def toJSON(k: TerminologyKind)
  : String
  = upickle.default.write(expr=k, indent=0)

  @JSExport
  def fromString(k: String)
  : TerminologyKind
  = k match {
    case "OpenWorldDefinitions" =>
      OpenWorldDefinitions
    case "ClosedWorldDesignations" =>
      ClosedWorldDesignations
  }

  implicit val r
  : upickle.default.Reader[TerminologyKind]
  = upickle.default.Reader[TerminologyKind]{
    case upickle.Js.Str(k) =>
      fromString(k)
  }

  @JSExport
  def fromJSON(k: String)
  : TerminologyKind
  = upickle.default.read[TerminologyKind](k)

}