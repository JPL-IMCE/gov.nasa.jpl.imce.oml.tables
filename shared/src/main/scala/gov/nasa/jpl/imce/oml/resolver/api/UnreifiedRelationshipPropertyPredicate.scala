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
 * An OML UnreifiedRelationshipPropertyPredicate wraps a reference
 * to an OML UnreifiedReifiedRelationship as a binary predicate for an OML ChainRule.
 * This binary predicate is satisfied when the pair of its implicit variables are bound as follows:
 * - the first variable is bound to an instance suitable for the domain of the OML UnreifiedRelationship;
 * - the second variable is bound to an instance suitable for the range of the OML UnreifiedRelationship.
 */
trait UnreifiedRelationshipPropertyPredicate
  extends BinarySegmentForwardPropertyPredicate
{
  val unreifiedRelationship: UnreifiedRelationship

  override def termPredicate
  (): Term
  override val uuid: taggedTypes.UnreifiedRelationshipPropertyPredicateUUID
}