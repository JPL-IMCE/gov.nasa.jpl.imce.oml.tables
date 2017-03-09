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
 * An OML ConceptualEntitySingletonInstance defines an instance of an OML ConceptualEntity.
 * An OML ConceptualEntitySingletonInstance maps to an [OWL2 NamedIndividual].
 * The semantics depends on the kind of OML ConceptualEntity classifier:
 * - If the OML ConceptualEntity is the domain for at least at least
 *   one OML DataRelationshipFromEntity with `isIdentityCriteria=true`,
 *   then the [OWL2-DL] mapping includes an [OWL2 Key Axiom]
 *   forcing that all distinctly named OML ConceptualEntitySingletonInstance
 *   must have different values for each OML DataRelationshipFromEntity with `isIdentityCriteria=true`.
 * - otherwise, distinctly named OML ConceptualEntitySingletonInstance
 *   represent semantically different instances.
 */
trait ConceptualEntitySingletonInstance
  extends SingletonInstance
{

  def conceptualEntitySingletonClassifier
  (): ConceptualEntity
  def identifyingScalarValues
  (): scala.collection.immutable.SortedSet[ScalarDataPropertyValue]
  def identifyingStructuredTuples
  (): scala.collection.immutable.SortedSet[StructuredDataPropertyValue]
}
