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

package gov.nasa.jpl.imce.omf.schema.resolver.api

/*
 * A TerminologyContext is a directed, acyclic hypergraph
 * of TerminologyBox graphs (hypernodes) and TerminologyAxioms (hyperedges).
 * This hypergraph is induced from a set of bottom TerminologyBox hypernodes.
 * A TerminologyBox is in scope of a TerminologyContext iff it is either a bottom hypernode
 * or one that can be reached following TerminologyAxioms that directly or indirectly have a bottom hypernode as a source.
 * A TerminologyAxiom is in scope of a TerminologyContext iff it is a statement of a TerminologyBox in scope of that context.
 * A TerminologyBox is a bottom hypernode iff it is not the target of any TerminologyAxiom in scope of the TerminologyContext.
 * A TerminologyBox is a top hypernode iff it is not the source of any TerminologyAxiom in scope of the TerminologyContext.
 */
trait TerminologyContext
{

  val nodes: scala.collection.immutable.Set[_ <: TerminologyBox]

  val bottomNodes: scala.collection.immutable.Set[_ <: TerminologyBox]
  val rootNodes: scala.collection.immutable.Set[_ <: TerminologyBox]
}
