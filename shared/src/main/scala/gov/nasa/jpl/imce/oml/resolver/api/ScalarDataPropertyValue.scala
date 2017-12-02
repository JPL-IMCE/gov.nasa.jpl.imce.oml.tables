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
 * An OML ScalarDataPropertyValue specifies a literal string as the value of
 * an OML DataRelationshipToScalar in the scope of an OML SingletonInstanceStructuredDataPropertyContext.
 */
trait ScalarDataPropertyValue
  extends Element
  with ValueCrossReferenceTuple
{
  override val uuid: taggedTypes.ScalarDataPropertyValueUUID

  val scalarDataProperty: DataRelationshipToScalar
  val scalarPropertyValue: gov.nasa.jpl.imce.oml.tables.LiteralValue
  val valueType: scala.Option[DataRange]

  def descriptionBox
  ()(implicit extent: Extent): scala.Option[DescriptionBox]
  def moduleContext
  ()(implicit extent: Extent): scala.Option[Module]
}

object ScalarDataPropertyValue {

  def descriptionBox
  (s: ScalarDataPropertyValue, ext: Extent)
  : scala.Option[DescriptionBox]
  = s.descriptionBox()(ext)

  def moduleContext
  (s: ScalarDataPropertyValue, ext: Extent)
  : scala.Option[Module]
  = s.moduleContext()(ext)

}
