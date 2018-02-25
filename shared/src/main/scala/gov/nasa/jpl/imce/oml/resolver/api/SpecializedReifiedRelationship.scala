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
 * An OML SpecializedReifiedRelationship is an OML ConceptualRelationship
 * that plays the role of a specific entity for an OML SpecializedReifiedRelationship
 * with respect to an OML ConceptualRelationship as its general entity in the context
 * of an OML SpecializationAxiom.
 */
trait SpecializedReifiedRelationship
  extends ConceptualRelationship
  with SpecializationAxiom
{
  override val uuid: taggedTypes.SpecializedReifiedRelationshipUUID

  val general: ConceptualRelationship

  override def child
  (): Entity
  override def parent
  (): Entity
  override def allNestedElements
  ()(implicit extent: Extent): scala.collection.immutable.Set[_ <: LogicalElement]
}

object SpecializedReifiedRelationship {

  def allNestedElements
  (s: SpecializedReifiedRelationship, ext: Extent)
  : scala.collection.immutable.Set[_ <: LogicalElement]
  = s.allNestedElements()(ext)

}
