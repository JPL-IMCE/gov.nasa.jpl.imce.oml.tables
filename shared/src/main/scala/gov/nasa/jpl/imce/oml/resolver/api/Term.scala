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

package gov.nasa.jpl.imce.oml.resolver.api

/*
 * An OML Term map to the declaration of an [OWL2-DL Entity] of some kind.
 */
trait Term
  extends TerminologyBoxStatement
  with Resource
{

  override val name: gov.nasa.jpl.imce.oml.tables.LocalName

  override def iri
  (): gov.nasa.jpl.imce.oml.tables.IRI
  /*
   * The UUID of a Term is a Version5 namespace UUID based on the term's IRI.
   */
  override def calculateUUID
  (): java.util.UUID
}
