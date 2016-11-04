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

import scala.scalajs.js.annotation.JSExport

@JSExport
object StringScalarRestrictionAxiomJS {
  
  @JSExport
  def jsStringScalarRestrictionAxiom(
    graphUUID: UUID,
    uuid: UUID,
    length: scala.scalajs.js.UndefOr[scala.Int],
    maxLength: scala.scalajs.js.UndefOr[scala.Int],
    minLength: scala.scalajs.js.UndefOr[scala.Int],
    pattern: scala.scalajs.js.UndefOr[Pattern],
    restrictedScalarUUID: UUID,
    scalarUUID: UUID
  )
  : StringScalarRestrictionAxiom
  = StringScalarRestrictionAxiom(
    graphUUID,
    uuid,
    length.toOption,
    maxLength.toOption,
    minLength.toOption,
    pattern.toOption,
    restrictedScalarUUID,
    scalarUUID
  )
  
}	
