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

import gov.nasa.jpl.imce.oml.specification.tables._
import org.scalacheck.Prop.forAll
import org.scalacheck.Properties
import scala.StringContext
import scala.Predef.String

object TerminologyGraphSpecification extends Properties("TerminologyGraph") {

  property("construction") = forAll(
    SchemaGenerators.uuid,
    SchemaGenerators.name,
    SchemaGenerators.kind,
    SchemaGenerators.iri,
    SchemaGenerators.nsPrefix)(
    (uuid: java.util.UUID,
     name: String,
     kind: TerminologyGraphKind,
     iri: String,
     nsPrefix: String) => {
    val w = new TerminologyGraph(uuid.toString, kind, name, iri, nsPrefix)
    val s = TerminologyGraphHelper.toJSON(w)
    val t = s"""{"uuid":"${w.uuid}","kind":"${w.kind}","name":"${w.name}","iri":"${w.iri}","nsPrefix":"${w.nsPrefix}"}"""
    val r = TerminologyGraphHelper.fromJSON(s)
    (s == t) &&
      (w.uuid == r.uuid) &&
      (w.kind == r.kind) &&
      (w.name == r.name) &&
      (w.iri == r.iri) &&
      (w.nsPrefix == r.nsPrefix)
  })
}
