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
 * An OML SingletonInstanceStructuredDataPropertyValue is an SingletonInstanceStructuredDataPropertyContext
 * for an OML ConceptualEntitySingletonInstance.
 */
trait SingletonInstanceStructuredDataPropertyValue
  extends SingletonInstanceStructuredDataPropertyContext
  with ModuleElement
{
  override val uuid: taggedTypes.SingletonInstanceStructuredDataPropertyValueUUID

  val singletonInstance: ConceptualEntitySingletonInstance

  def descriptionBox
  ()(implicit extent: Extent): scala.Option[DescriptionBox]
  def allNestedElements
  ()(implicit extent: Extent): scala.collection.immutable.Set[_ <: Element]
}

object SingletonInstanceStructuredDataPropertyValue {

  def descriptionBox
  (s: SingletonInstanceStructuredDataPropertyValue, ext: Extent)
  : scala.Option[DescriptionBox]
  = s.descriptionBox()(ext)

  def allNestedElements
  (s: SingletonInstanceStructuredDataPropertyValue, ext: Extent)
  : scala.collection.immutable.Set[_ <: Element]
  = s.allNestedElements()(ext)

}
