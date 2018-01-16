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
 * An OML ForwardProperty is an essential part of an OML ReifiedRelationship.
 * An OML ForwardProperty defines the unreified relationship from the OML ReifiedRelationship's domain to its range.
 */
trait ForwardProperty
  extends RestrictableRelationship
{
  override val uuid: taggedTypes.ForwardPropertyUUID

  val reifiedRelationship: ReifiedRelationship
  override val name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName

  override def iri
  ()(implicit extent: Extent): scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI]
  override def abbrevIRI
  ()(implicit extent: Extent): scala.Option[scala.Predef.String]
  override def relation
  (): EntityRelationship
}

object ForwardProperty {

  def iri
  (f: ForwardProperty, ext: Extent)
  : scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI]
  = f.iri()(ext)

  def abbrevIRI
  (f: ForwardProperty, ext: Extent)
  : scala.Option[scala.Predef.String]
  = f.abbrevIRI()(ext)

}
