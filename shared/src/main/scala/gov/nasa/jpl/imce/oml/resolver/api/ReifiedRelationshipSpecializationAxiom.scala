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
 * An OML ReifiedRelationshipSpecializationAxiom is a logical axiom
 * about a taxonomic relationship between a specific and general
 * OML ConceptualRelationship.
 */
trait ReifiedRelationshipSpecializationAxiom
  extends SpecializationAxiom
{
  override val uuid: taggedTypes.ReifiedRelationshipSpecializationAxiomUUID

  /*
   * The sub (child) relationship
   */
  val subRelationship: ConceptualRelationship
  /*
   * The super (parent) relationship
   */
  val superRelationship: ConceptualRelationship

  /*
   * Get the sub (child) entity
   */
  override def child
  (): Entity
  /*
   * Get the super (parent) entity
   */
  override def parent
  (): Entity
}
