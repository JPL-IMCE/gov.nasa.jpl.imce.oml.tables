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
 * An OML RestrictionScalarDataPropertyValue specifies a literal string as the value of
 * an OML DataRelationshipToScalar in the scope of an OML RestrictionStructuredDataPropertyContext.
 */
trait RestrictionScalarDataPropertyValue
  extends LogicalElement
  with ValueCrossReferenceTuple
{
  override val uuid: taggedTypes.RestrictionScalarDataPropertyValueUUID

  val scalarDataProperty: DataRelationshipToScalar
  val scalarPropertyValue: gov.nasa.jpl.imce.oml.tables.LiteralValue
  val valueType: scala.Option[DataRange]

  def terminologyBox
  ()(implicit extent: Extent): scala.Option[TerminologyBox]
  def moduleContext
  ()(implicit extent: Extent): scala.Option[Module]
}

object RestrictionScalarDataPropertyValue {

  def terminologyBox
  (r: RestrictionScalarDataPropertyValue, ext: Extent)
  : scala.Option[TerminologyBox]
  = r.terminologyBox()(ext)

  def moduleContext
  (r: RestrictionScalarDataPropertyValue, ext: Extent)
  : scala.Option[Module]
  = r.moduleContext()(ext)

}
