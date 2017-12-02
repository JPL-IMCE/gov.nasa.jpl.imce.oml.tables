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
 * An OML IntrinsicIdentityKind is an abstraction for a kind of OML IdentityKind
 * where the identity stems from a single intrinsic criteria: a IRI.
 */
trait IntrinsicIdentityKind
  extends IdentityKind
{
  override val uuid: taggedTypes.IntrinsicIdentityKindUUID

  def iri
  ()(implicit extent: Extent): scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI]
}

object IntrinsicIdentityKind {

  def iri
  (i: IntrinsicIdentityKind, ext: Extent)
  : scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI]
  = i.iri()(ext)

}
