/*
 * Copyright 2017
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
 */
package gov.nasa.jpl.imce.oml.uuid

import java.util.UUID

import com.fasterxml.uuid.Generators
import com.fasterxml.uuid.impl.NameBasedGenerator

import scala.Tuple2
import scala.Predef.String

object OMLUUIDGenerator {

  def uuidFromURL(url: String)
  : UUID
  = Generators
    .nameBasedGenerator(NameBasedGenerator.NAMESPACE_URL)
    .generate(url)

  def derivedUUID(topic: String, pairs: Tuple2[String,UUID]*)
  : UUID
  = {
    val name = topic + pairs.map { case (k,v) => k+":"+v }.mkString(",")
    Generators
      .nameBasedGenerator(NameBasedGenerator.NAMESPACE_URL)
      .generate(name)
  }
}