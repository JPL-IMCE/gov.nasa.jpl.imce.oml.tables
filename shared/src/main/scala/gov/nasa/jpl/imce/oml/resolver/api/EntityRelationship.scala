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
 * An OML EntityRelationship is a kind of OML Term that
 * is an OML DirectedBinaryRelationshipKind between a
 * domain OML Entity and a range OML Entity.
 */
trait EntityRelationship
  extends Term
  with DirectedBinaryRelationshipKind
{

  val source: Entity
  val target: Entity
  /*
   * 0 <= target.size <= 1
   */
  val isFunctional: scala.Boolean
  /*
   * 0 <= source.size <= 1
   */
  val isInverseFunctional: scala.Boolean
  /*
   * 1 <= target.size
   */
  val isEssential: scala.Boolean
  /*
   * 1 <= source.size
   */
  val isInverseEssential: scala.Boolean
  /*
   * Whether this relationship is symmetric
   */
  val isSymmetric: scala.Boolean
  /*
   * Whether this relationship is asymmetric
   */
  val isAsymmetric: scala.Boolean
  /*
   * Whether this relationship is reflexive
   */
  val isReflexive: scala.Boolean
  /*
   * Whether this relationship is irreflexive
   */
  val isIrreflexive: scala.Boolean
  /*
   * Whether this relationship is transitive
   */
  val isTransitive: scala.Boolean

  override def relationDomain
  (): Term
  override def relationRange
  (): Term
}
