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
 * An OML CardinalityRestrictedConcept is an OML ConceptKind defined as a named
 * cardinality restriction of an OML RestrictableRelationship whose domain is an OML ConceptKind
 * and that can be optionally qualified by a restricted range OML Entity.
 * 
 * An OML CardinalityRestrictedConcept`(C, Min, n, Rel)` corresponds
 * to the following in OWL2 Functional Syntax:
 * 
 * ```
 * Declaration(Class C))
 * SubClassOf(C ObjectMinCardinality(n Rel))
 * ```
 * 
 * An OML CardinalityRestrictedConcept`(C, Min, n, Rel, Ran)` corresponds
 * to the following in OWL2 Functional Syntax:
 * 
 * ```
 * Declaration(Class C))
 * SubClassOf(C ObjectMinCardinality(n Rel Ran))
 * ```
 * 
 * An OML CardinalityRestrictedConcept`(C, Max, n, Rel)` corresponds
 * to the following in OWL2 Functional Syntax:
 * 
 * ```
 * Declaration(Class C))
 * SubClassOf(C ObjectMaxCardinality(n Rel))
 * ```
 * 
 * An OML CardinalityRestrictedConcept`(C, Max, n, Rel, Ran)` corresponds
 * to the following in OWL2 Functional Syntax:
 * 
 * ```
 * Declaration(Class C))
 * SubClassOf(C ObjectMaxCardinality(n Rel Ran))
 * ```
 * 
 * An OML CardinalityRestrictedConcept`(C, Equal, n, Rel)` corresponds
 * to the following in OWL2 Functional Syntax:
 * 
 * ```
 * Declaration(Class C))
 * SubClassOf(C ObjectExactCardinality(n Rel))
 * ```
 * 
 * An OML CardinalityRestrictedConcept`(C, Equal, n, Rel)` corresponds
 * to the following in OWL2 Functional Syntax:
 * 
 * ```
 * Declaration(Class C))
 * SubClassOf(C ObjectExactCardinality(n Rel))
 * ```
 */
trait CardinalityRestrictedConcept
  extends ConceptKind
{
  override val uuid: taggedTypes.CardinalityRestrictedConceptUUID

  val restrictionKind: gov.nasa.jpl.imce.oml.tables.CardinalityRestrictionKind
  val restrictedRelationship: RestrictableRelationship
  val restrictedRange: scala.Option[Entity]
  val restrictedCardinality: gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral
}
