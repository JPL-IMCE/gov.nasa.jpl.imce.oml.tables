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
 * An OML ReifiedRelationshipRestriction is an OML ConceptualRelationship
 * that plays the role of a specific entity for one or more OML ReifiedRelationshipSpecializationAxioms
 * with respect to an OML ConceptualRelationship as its general entity.
 * Note that this statement is well formed in a given OML TerminologyBox
 * iff it is the specific entity of at least one OML ReifiedRelationshipSpecializationAxiom
 * asserted in that OML TerminologyBox.
 */
trait ReifiedRelationshipRestriction
  extends ConceptualRelationship
{
  override val uuid: taggedTypes.ReifiedRelationshipRestrictionUUID

  override def allNestedElements
  ()(implicit extent: Extent): scala.collection.immutable.Set[_ <: LogicalElement]
  override def rootReifiedRelationships
  ()(implicit extent: Extent): scala.collection.immutable.Set[_ <: ReifiedRelationship]
}

object ReifiedRelationshipRestriction {

  def allNestedElements
  (r: ReifiedRelationshipRestriction, ext: Extent)
  : scala.collection.immutable.Set[_ <: LogicalElement]
  = r.allNestedElements()(ext)

  def rootReifiedRelationships
  (r: ReifiedRelationshipRestriction, ext: Extent)
  : scala.collection.immutable.Set[_ <: ReifiedRelationship]
  = r.rootReifiedRelationships()(ext)

}
