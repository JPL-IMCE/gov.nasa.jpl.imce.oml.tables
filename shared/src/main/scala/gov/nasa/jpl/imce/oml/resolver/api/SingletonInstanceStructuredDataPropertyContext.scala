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
 * An OML SingletonInstanceStructuredDataPropertyContext defines the context of
 * an OML DataRelationshipToStructure for an insance of either an OML Concept or OML Structure
 * for specifying values of its data properties
 * via nested OML StructuredDataPropertyTuple(s) and OML ScalarDataPropertyValue(s).
 */
trait SingletonInstanceStructuredDataPropertyContext
  extends ElementCrossReferenceTuple
{
  override val uuid: taggedTypes.SingletonInstanceStructuredDataPropertyContextUUID

  val structuredDataProperty: DataRelationshipToStructure

  def descriptionBox
  ()(implicit extent: Extent): scala.Option[DescriptionBox]
  def moduleContext
  ()(implicit extent: Extent): scala.Option[Module]
  def allNestedRestrictionElements
  ()(implicit extent: Extent): scala.collection.immutable.Set[_ <: LogicalElement]
}

object SingletonInstanceStructuredDataPropertyContext {

  def descriptionBox
  (s: SingletonInstanceStructuredDataPropertyContext, ext: Extent)
  : scala.Option[DescriptionBox]
  = s.descriptionBox()(ext)

  def moduleContext
  (s: SingletonInstanceStructuredDataPropertyContext, ext: Extent)
  : scala.Option[Module]
  = s.moduleContext()(ext)

  def allNestedRestrictionElements
  (s: SingletonInstanceStructuredDataPropertyContext, ext: Extent)
  : scala.collection.immutable.Set[_ <: LogicalElement]
  = s.allNestedRestrictionElements()(ext)

}
