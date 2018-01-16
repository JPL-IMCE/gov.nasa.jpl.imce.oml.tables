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
 * An OML InverseProperty is an optional part of an OML ReifiedRelationship.
 * If defined, it corresponds to the inverse of the OML ReifiedRelationship's OML ForwardProperty.
 */
trait InverseProperty
  extends RestrictableRelationship
{
  override val uuid: taggedTypes.InversePropertyUUID

  val reifiedRelationship: ReifiedRelationship
  override val name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName

  override def iri
  ()(implicit extent: Extent): scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI]
  override def abbrevIRI
  ()(implicit extent: Extent): scala.Option[scala.Predef.String]
  override def relation
  (): EntityRelationship
  override def moduleContext
  ()(implicit extent: Extent): scala.Option[Module]
}

object InverseProperty {

  def iri
  (i: InverseProperty, ext: Extent)
  : scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI]
  = i.iri()(ext)

  def abbrevIRI
  (i: InverseProperty, ext: Extent)
  : scala.Option[scala.Predef.String]
  = i.abbrevIRI()(ext)

  def moduleContext
  (i: InverseProperty, ext: Extent)
  : scala.Option[Module]
  = i.moduleContext()(ext)

}
