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

package gov.nasa.jpl.imce.omf.schema.tables

import scala.scalajs.js.annotation.{JSExport,JSExportDescendentObjects}
import scala._
import scala.Predef._

@JSExportDescendentObjects
sealed trait TerminologyGraphKind

case object OpenWorldDefinitions extends TerminologyGraphKind

case object ClosedWorldDesignations extends TerminologyGraphKind

@JSExport
object TerminologyGraphKind {

  @JSExport
  def toString(k: TerminologyGraphKind)
  : String
  = k match {
    case OpenWorldDefinitions =>
     "OpenWorldDefinitions"
    case ClosedWorldDesignations =>
     "ClosedWorldDesignations"
  }

  implicit val w
  : upickle.default.Writer[TerminologyGraphKind]
  = upickle.default.Writer[TerminologyGraphKind]{ (k: TerminologyGraphKind) =>
    upickle.Js.Str(toString(k))
  }

  @JSExport
  def toJSON(k: TerminologyGraphKind)
  : String
  = upickle.default.write(expr=k, indent=0)

  @JSExport
  def fromString(k: String)
  : TerminologyGraphKind
  = k match {
    case "OpenWorldDefinitions" =>
      OpenWorldDefinitions
    case "ClosedWorldDesignations" =>
      ClosedWorldDesignations
  }

  implicit val r
  : upickle.default.Reader[TerminologyGraphKind]
  = upickle.default.Reader[TerminologyGraphKind]{
    case upickle.Js.Str(k) =>
      fromString(k)
  }

  @JSExport
  def fromJSON(k: String)
  : TerminologyGraphKind
  = upickle.default.read[TerminologyGraphKind](k)

}