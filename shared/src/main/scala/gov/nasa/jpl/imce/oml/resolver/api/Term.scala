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
 * An OML Term map to the declaration of an [OWL2-DL Entity] of some kind.
 */
trait Term
  extends TerminologyBoxStatement
  with Resource
{
  override val uuid: taggedTypes.TermUUID

  override val name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName

  override def iri
  ()(implicit extent: Extent): scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI]
  override def abbrevIRI
  ()(implicit extent: Extent): scala.Option[scala.Predef.String]
  def allNestedElements
  ()(implicit extent: Extent): scala.collection.immutable.Set[_ <: Element]
}

object Term {

  def iri
  (t: Term, ext: Extent)
  : scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI]
  = t.iri()(ext)

  def abbrevIRI
  (t: Term, ext: Extent)
  : scala.Option[scala.Predef.String]
  = t.abbrevIRI()(ext)

  def allNestedElements
  (t: Term, ext: Extent)
  : scala.collection.immutable.Set[_ <: Element]
  = t.allNestedElements()(ext)

}
