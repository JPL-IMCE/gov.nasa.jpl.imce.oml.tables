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
 * An OML ReifiedRelationship is an OML ConceptualRelationship
 * and a kind of OML CharacterizedEntityRelationship
 * where an instance has an intrinsic identity. This means that
 * an OML ReifiedRelationship can be involved as the domain or the
 * range of another OML EntityRelationship as well as the
 * domain of an OML DataRelationshipFromEntity.
 */
trait ReifiedRelationship
  extends ConceptualRelationship
  with CharacterizedEntityRelationship
{
  override val uuid: taggedTypes.ReifiedRelationshipUUID

  override def allNestedElements
  ()(implicit extent: Extent): scala.collection.immutable.Set[_ <: LogicalElement]
}

object ReifiedRelationship {

  def allNestedElements
  (r: ReifiedRelationship, ext: Extent)
  : scala.collection.immutable.Set[_ <: LogicalElement]
  = r.allNestedElements()(ext)

}
