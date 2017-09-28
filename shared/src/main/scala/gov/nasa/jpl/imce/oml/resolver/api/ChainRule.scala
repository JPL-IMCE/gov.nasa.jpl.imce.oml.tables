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
 * An OML ChainRule corresponds to a named SWRL implication rule
 * whose consequent head is a single OML UnreifiedRelationship, `ur`,
 * and whose antecedent body consists of at least 1 OML RuleBodySegment
 * possibly followed by other OML RuleBodySegment(s).
 * 
 * SWRL variables are implicit in OML in the following sense:
 * - The consequent head, `ur` is a binary predicate: `ur(?d, ?r)`
 *   where `?d` and `?r` are variables corresponding to, respectively,
 *   the domain and range of the OML UnreifiedRelationship `ur`.
 * - `?d` is the first variable of the OML Term used as the `termPredicate` of the `firstSegment`;
 * - `?r` is the last variable of the OML Term used as the `termPredicate` of the last OML RuleBodySegment;
 * - for an OML RuleBodySegment at `position` `i` that has a `nextSegment` at `position` `i+1`,
 *   the last variable of the OML Term used as the `termPredicate` at `i` must be identical
 *   to the first variable of the OML Term used as the `termPredicate` at `i+1;
 * - for an OML RuleBodySegment whose `segmentPrediccate` is an OML UnarySegmentPredicate,
 *   the `termPredicate` is unary and its unique variable plays the roles of both first and last variables;
 * - for an OML RuleBodySegment whose `segmentPredicate` is an OML BinarySegmentForwardPropertyPredicate,
 *   the `termPredicate` is binary and its first and second variables correspond respectively
 *   to the domain and range of the property interpreted in the forward direction from its domain to its range;
 * - for an OML RuleBodySegment whose `segmentPredicate` is an OML BinarySegmentReversePropertyPredicate,
 *   the `termPredicate` is binary and its first and second variables correspond respectively
 *   to the range and domain of the property interpreted in the reverse direction from its range to its domain.
 */
trait ChainRule
  extends Rule
{

  val head: UnreifiedRelationship
}
