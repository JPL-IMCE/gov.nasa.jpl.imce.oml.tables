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
 * A Bundle is a concrete TerminologyBox that is
 * an acyclic logical aggregate of other TerminologyBoxes.
 */
trait Bundle
  extends TerminologyBox
{

  /*
   * The TerminologyBundleStatements asserted in this Bundle
   */
  val bundleStatements: scala.collection.immutable.Set[_ <: TerminologyBundleStatement]
  /*
   * The BundledTerminologyAxioms asserted in this Bundle
   */
  val terminologyBundleAxioms: scala.collection.immutable.Set[_ <: TerminologyBundleAxiom]
  val disjointUnionOfConceptsAxioms: scala.collection.immutable.Set[_ <: DisjointUnionOfConceptsAxiom]

  def withBundleStatements
  (s: scala.collection.immutable.Set[_ <: TerminologyBundleStatement]
  ): Bundle
  def withBoxStatements
  (s: scala.collection.immutable.Set[_ <: TerminologyBoxStatement]
  ): Bundle
}
