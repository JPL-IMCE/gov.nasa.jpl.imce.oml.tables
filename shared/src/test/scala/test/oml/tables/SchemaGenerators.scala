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

import gov.nasa.jpl.imce.oml.tables.{ClosedWorldDesignations, OpenWorldDefinitions}
import org.scalacheck._
import scala.{Int,StringContext}

object SchemaGenerators {

  val name = Gen.identifier

  val uuid = Gen.uuid

  val iri = for {
    n <- Gen.identifier
  } yield s"http://imce.jpl.nasa.gov/example/omf.schema.tables/$n"

  val isAbstract = Gen.oneOf(true, false)

  val kind = Gen.oneOf(OpenWorldDefinitions,ClosedWorldDesignations)

  val nsPrefix = Gen.identifier

  val optInt_0_to_3 = Gen.option(Gen.chooseNum[Int](0, 3))
}
