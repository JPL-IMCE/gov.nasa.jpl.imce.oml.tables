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
import scala.{Boolean,StringContext}
import scala.Predef.String

object BinaryScalarRestrictionAxiomTest extends Properties("") {

  property("construction") = forAll(
    SchemaGenerators.uuid,
    SchemaGenerators.uuid,
    Option[Int],
    Option[Int],
    Option[Int],
    SchemaGenerators.uuid,
    SchemaGenerators.uuid)((graphUUID: java.util.UUID, uuid: java.util.UUID, length: Option[scala.Int], maxLength: Option[scala.Int], minLength: Option[scala.Int], restrictedScalarUUID: java.util.UUID, scalarUUID: java.util.UUID) => {
    val w = new BinaryScalarRestrictionAxiom(graphUUID.toString, uuid.toString, length, maxLength, minLength, restrictedScalarUUID.toString, scalarUUID.toString)
    val s = BinaryScalarRestrictionAxiomHelper.toJSON(w)
    val t = s"""{"graphUUID":"${w.graphUUID}","uuid":"${w.uuid}","length":${w.length},"maxLength":${w.maxLength},"minLength":${w.minLength},"name":"${w.restrictedScalarUUID}","iri":"${w.scalarUUID}"}"""
    val r = BinaryScalarRestrictionAxiomHelper.fromJSON(s)
    (s == t) && (w.graphUUID == r.graphUUID) && (w.uuid == r.uuid) && (w.length == r.length) && (w.maxLength == r.maxLength) && (w.minLength == r.minLength) && (w.restrictedScalarUUID == r.restrictedScalarUUID) && (w.scalarUUID == r.scalarUUID)
  })

}