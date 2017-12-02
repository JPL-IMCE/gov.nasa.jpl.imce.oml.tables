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
 * An OML ReifiedRelationshipInstanceRange specifies which OML ConceptualEntitySingletonInstance
 * plays the role of the range for an OML ReifiedRelationshipInstance.
 * An OML ReifiedRelationshipInstanceRange has no intrinsic identity; instead,
 * an OML ReifiedRelationshipInstanceRange is semantically equivalent
 * to another OML ReifiedRelationshipInstanceRange referencing the same property and range.
 */
trait ReifiedRelationshipInstanceRange
  extends TerminologyInstanceAssertion
  with ElementCrossReferenceTuple
{
  override val uuid: taggedTypes.ReifiedRelationshipInstanceRangeUUID

  val reifiedRelationshipInstance: ReifiedRelationshipInstance
  val range: ConceptualEntitySingletonInstance

  def descriptionBox
  ()(implicit extent: Extent): scala.Option[DescriptionBox]
  def moduleContext
  ()(implicit extent: Extent): scala.Option[Module]
  def allNestedElements
  ()(implicit extent: Extent): scala.collection.immutable.Set[_ <: Element]
}

object ReifiedRelationshipInstanceRange {

  def descriptionBox
  (r: ReifiedRelationshipInstanceRange, ext: Extent)
  : scala.Option[DescriptionBox]
  = r.descriptionBox()(ext)

  def moduleContext
  (r: ReifiedRelationshipInstanceRange, ext: Extent)
  : scala.Option[Module]
  = r.moduleContext()(ext)

  def allNestedElements
  (r: ReifiedRelationshipInstanceRange, ext: Extent)
  : scala.collection.immutable.Set[_ <: Element]
  = r.allNestedElements()(ext)

}
