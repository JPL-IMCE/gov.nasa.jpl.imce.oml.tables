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
 * An OML UnreifiedRelationship is a kind of OML CharacterizedEntityRelationship
 * where an instance has no intrinsic identity but rather structural
 * equivalence semantics as a tuple of references. This means
 * that an OML UnreifiedRelationship cannot be involved as the domain
 * or range of any kind of OML DirectedBinaryRelationshipKind.
 */
trait UnreifiedRelationship
  extends CharacterizedEntityRelationship
  with RestrictableRelationship
{
  override val uuid: taggedTypes.UnreifiedRelationshipUUID

  val source: Entity
  val target: Entity

  override def relationSource
  (): Entity
  override def relationTarget
  (): Entity
  override def relation
  (): EntityRelationship
}
