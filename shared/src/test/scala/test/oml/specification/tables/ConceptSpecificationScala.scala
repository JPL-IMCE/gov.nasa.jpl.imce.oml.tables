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

package test.oml.specification.tables

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

import gov.nasa.jpl.imce.oml.specification.tables._
import org.scalacheck.Prop.forAll
import org.scalacheck.Properties
import scala.{Boolean,StringContext}
import scala.Predef.String

object ConceptSpecificationScala extends Properties("Concept") {

  property("construction") = forAll(
    SchemaGenerators.uuid,
    SchemaGenerators.uuid,
    SchemaGenerators.isAbstract,
    SchemaGenerators.name)((graphUUID: java.util.UUID, uuid: java.util.UUID, isAbstract: Boolean, name: String) => {
    val w = new Concept(graphUUID.toString, uuid.toString, isAbstract, name)
    val s = ConceptHelper.toJSON(w)
    val t = s"""{"graphUUID":"${w.graphUUID}","uuid":"${w.uuid}","isAbstract":${w.isAbstract},"name":"${w.name}"}"""
    val r = ConceptHelper.fromJSON(s)
    (s == t) &&
      (w.uuid == r.uuid) &&
      (w.isAbstract == r.isAbstract) &&
      (w.name == r.name)
  })

}