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
 * An OML Element is a logical abstraction
 * for everything involved in OML that
 * is globally identified by a version 5 namespace UUID deterministically
 * derived from essential information about the OML Element.
 * An OML Element can be the subject of multiple OML AnnotationPropertyValues;
 * however, there can be at most one OML AnnotationPropertyValue for a given
 * pair of OML Element and OML AnnotationProperty.
 */
trait Element
  extends IdentityKind
{
  override val uuid: taggedTypes.ElementUUID

  def moduleContext
  ()(implicit extent: Extent): scala.Option[Module]
}

object Element {

  def moduleContext
  (e: Element, ext: Extent)
  : scala.Option[Module]
  = e.moduleContext()(ext)

}
