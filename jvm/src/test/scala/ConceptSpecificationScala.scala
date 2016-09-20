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

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

import scala.Boolean
import scala.Predef.{augmentString,String}

object ConceptSpecificationScala extends Properties("Concept") {

  property("construction") = forAll { (graphUUID: String, uuid: String, isAbstract: Boolean, name: String, iri: String) =>
    val ok = iri.nonEmpty && uuid.nonEmpty && name.nonEmpty && graphUUID.nonEmpty
    if (ok) {
      val c = gov.nasa.jpl.imce.omf.schema.tables.Concept(graphUUID, uuid, isAbstract, name, iri)
      c.resourceIRI == iri && c.uuid == uuid && c.name == name && c.graphUUID == graphUUID && c.uuid == uuid
    } else
      true
  }

}