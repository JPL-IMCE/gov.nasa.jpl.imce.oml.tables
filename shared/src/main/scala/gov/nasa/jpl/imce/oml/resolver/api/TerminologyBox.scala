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
 * An OML TerminologyBox is an OML Module for defining a domain-specific vocabulary
 * as a logical set of OML TerminologyBoxStatement(s), possibly by reuse of other
 * vocabularies via OML TerminologyBoxAxiom(s).
 * The semantics of an OML TerminologyBox domain-specific vocabulary is defined
 * by the mapping to [OWL2-DL] of the other vocabularies it reuses, if any, and
 * that of its OML TerminologyBoxAxiom(s) and OML TerminologyBoxStatement(s)
 * according to its OML TerminologyKind.
 */
trait TerminologyBox
  extends Module
{

  val kind: gov.nasa.jpl.imce.oml.tables.TerminologyKind

  override def everything
  (): scala.collection.immutable.Set[_ <: Element]
}
