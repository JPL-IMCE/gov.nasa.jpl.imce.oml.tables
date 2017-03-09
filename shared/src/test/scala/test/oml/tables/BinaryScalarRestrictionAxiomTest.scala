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

package test.oml.tables

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

import gov.nasa.jpl.imce.oml.tables._
import org.scalacheck.Prop.forAll
import org.scalacheck.Properties
import scala.{Option,StringContext}

object BinaryScalarRestrictionAxiomTest extends Properties("") {

  property("construction") = forAll(
    SchemaGenerators.uuid,
    SchemaGenerators.uuid,
    SchemaGenerators.optInt_0_to_3,
    SchemaGenerators.optInt_0_to_3,
    SchemaGenerators.optInt_0_to_3,
    SchemaGenerators.uuid,
    SchemaGenerators.name)((graphUUID: java.util.UUID, uuid: java.util.UUID, length: Option[scala.Int], maxLength: Option[scala.Int], minLength: Option[scala.Int], restrictedScalarUUID: java.util.UUID, scalarName: LocalName) => {
    val w = new BinaryScalarRestriction(graphUUID.toString, uuid.toString, restrictedScalarUUID.toString, length, maxLength, minLength, scalarName)
    val s = BinaryScalarRestrictionHelper.toJSON(w)
    val t = s"""{"uuid":"${w.uuid}","tboxUUID":"${w.tboxUUID}","restrictedRangeUUID":"${w.restrictedRangeUUID}""length":${w.length},"maxLength":${w.maxLength},"minLength":${w.minLength},"iri":"${w.name}"}"""
    val r = BinaryScalarRestrictionHelper.fromJSON(s)
    (s == t) && (w.tboxUUID == r.tboxUUID) && (w.uuid == r.uuid) && (w.length == r.length) && (w.maxLength == r.maxLength) && (w.minLength == r.minLength) && (w.restrictedRangeUUID == r.restrictedRangeUUID) && (w.name == r.name)
  })

}