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
 * An OML RestrictionStructuredDataPropertyTuple is an OML RestrictionStructuredDataPropertyContext
 * nested in a container OML RestrictionStructuredDataPropertyContext.
 */
trait RestrictionStructuredDataPropertyTuple
  extends RestrictionStructuredDataPropertyContext
{

  override def terminologyBox
  ()(implicit extent: Extent): scala.Option[TerminologyBox]
  def moduleContext
  ()(implicit extent: Extent): scala.Option[Module]
  def allNestedElements
  ()(implicit extent: Extent): scala.collection.immutable.Set[_ <: Element]
}

object RestrictionStructuredDataPropertyTuple {

  def terminologyBox
  (r: RestrictionStructuredDataPropertyTuple, ext: Extent)
  : scala.Option[TerminologyBox]
  = r.terminologyBox()(ext)

  def moduleContext
  (r: RestrictionStructuredDataPropertyTuple, ext: Extent)
  : scala.Option[Module]
  = r.moduleContext()(ext)

  def allNestedElements
  (r: RestrictionStructuredDataPropertyTuple, ext: Extent)
  : scala.collection.immutable.Set[_ <: Element]
  = r.allNestedElements()(ext)

}
