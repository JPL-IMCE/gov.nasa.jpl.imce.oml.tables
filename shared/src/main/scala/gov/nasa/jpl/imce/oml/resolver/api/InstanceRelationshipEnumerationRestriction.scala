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
 * An OML InstanceRelationshipEnumerationRestriction specifies an OWL ObjectAllValuesFrom
 * about an OML Relationship involving an OML ConceptualEntitySingletonInstance
 * playing the domain role and a list of OML ConceptualEntitySingletonInstance for
 * an OWL ObjectOneOf enumeration playing the range role.
 */
trait InstanceRelationshipEnumerationRestriction
  extends TerminologyInstanceAssertion
  with ElementCrossReferenceTuple
{
  override val uuid: taggedTypes.InstanceRelationshipEnumerationRestrictionUUID

  val domain: ConceptualEntitySingletonInstance
  val restrictedRelationship: RestrictableRelationship

  def descriptionBox
  ()(implicit extent: Extent): scala.Option[DescriptionBox]
  def moduleContext
  ()(implicit extent: Extent): scala.Option[Module]
  def allNestedElements
  ()(implicit extent: Extent): scala.collection.immutable.Set[_ <: LogicalElement]
}

object InstanceRelationshipEnumerationRestriction {

  def descriptionBox
  (i: InstanceRelationshipEnumerationRestriction, ext: Extent)
  : scala.Option[DescriptionBox]
  = i.descriptionBox()(ext)

  def moduleContext
  (i: InstanceRelationshipEnumerationRestriction, ext: Extent)
  : scala.Option[Module]
  = i.moduleContext()(ext)

  def allNestedElements
  (i: InstanceRelationshipEnumerationRestriction, ext: Extent)
  : scala.collection.immutable.Set[_ <: LogicalElement]
  = i.allNestedElements()(ext)

}
