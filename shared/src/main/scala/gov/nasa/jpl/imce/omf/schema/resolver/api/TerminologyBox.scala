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
 * A TerminologyBox is a logical container for a set of TerminologyStatements.
 */
trait TerminologyBox
  extends TerminologyThing
  with Resource
{

  /*
   * The TerminologyStatements asserted in this TerminologyBox
   */
  val statements: scala.collection.immutable.Set[_ <: TerminologyStatement]

  def withStatements
  (s: scala.collection.immutable.Set[_ <: TerminologyStatement]
  ): TerminologyBox
  /*
   * A map for the subset of statements that are
   * entities terms indexed by their uuid.
   */
  val entities: scala.collection.immutable.Map[java.util.UUID,Entity]
  /*
   * A map for the subset of statements that are
   * aspects terms indexed by their uuid.
   */
  val aspects: scala.collection.immutable.Map[java.util.UUID,Aspect]
  /*
   * A map for the subset of statements that are
   * concepts terms indexed by their uuid.
   */
  val concepts: scala.collection.immutable.Map[java.util.UUID,Concept]
  /*
   * A map for the subset of statements that are
   * reified relationship terms indexed by their uuid.
   */
  val reifiedRelationships: scala.collection.immutable.Map[java.util.UUID,ReifiedRelationship]
  /*
   * A map for the subset of statements that are
   * datatype terms indexed by their uuid.
   */
  val dataRelationships: scala.collection.immutable.Map[java.util.UUID,DataRelationship]
  /*
   * A map for the subset of statements that are
   * datatype terms indexed by their uuid.
   */
  val datatypes: scala.collection.immutable.Map[java.util.UUID,Datatype]
  /*
   * A map for the subset of statements that are
   * scalar datatype terms indexed by their uuid.
   */
  val scalars: scala.collection.immutable.Map[java.util.UUID,Scalar]
  /*
   * A map for the subset of statements that are
   * structured datatype terms indexed by their uuid.
   */
  val structures: scala.collection.immutable.Map[java.util.UUID,Structure]
  /*
   * The subset of axioms about terms.
   */
  val termAxioms: scala.collection.immutable.Set[_ <: TermAxiom]
}
