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
 * An OML UnreifiedRelationshipInstanceTuple specifies a triple involving
 * a reference to an OML ConceptualEntitySingletonInstance playing the role of the domain
 * of an OML UnreifiedRelationship whose range is played by a reference to an OML ConceptualEntitySingletonInstance.
 * An OML UnreifiedRelationshipInstanceTuple has no intrinsic identity; instead,
 * an OML UnreifiedRelationshipInstanceTuple is semantically equivalent
 * to another OML UnreifiedRelationshipInstanceTuple referencing the same domain, property and range.
 */
trait UnreifiedRelationshipInstanceTuple
  extends TerminologyInstanceAssertion
  with ElementCrossReferenceTuple
{
  override val uuid: taggedTypes.UnreifiedRelationshipInstanceTupleUUID

  val unreifiedRelationship: UnreifiedRelationship
  val domain: ConceptualEntitySingletonInstance
  val range: ConceptualEntitySingletonInstance

  def descriptionBox
  ()(implicit extent: Extent): scala.Option[DescriptionBox]
  def moduleContext
  ()(implicit extent: Extent): scala.Option[Module]
  def allNestedElements
  ()(implicit extent: Extent): scala.collection.immutable.Set[_ <: LogicalElement]
}

object UnreifiedRelationshipInstanceTuple {

  def descriptionBox
  (u: UnreifiedRelationshipInstanceTuple, ext: Extent)
  : scala.Option[DescriptionBox]
  = u.descriptionBox()(ext)

  def moduleContext
  (u: UnreifiedRelationshipInstanceTuple, ext: Extent)
  : scala.Option[Module]
  = u.moduleContext()(ext)

  def allNestedElements
  (u: UnreifiedRelationshipInstanceTuple, ext: Extent)
  : scala.collection.immutable.Set[_ <: LogicalElement]
  = u.allNestedElements()(ext)

}
