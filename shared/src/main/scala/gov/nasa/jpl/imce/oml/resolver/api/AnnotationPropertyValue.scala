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
 * For the OML tabular interchange representation,
 * an OML AnnotationPropertyValue is a triple <S,P,V>
 * where S is an OML Element, P an OML AnnotationProperty
 * and V is the value of P asserted about S by this triple.
 * The UUID of an OML AnnotationPropertyValue is deterministically derived
 * from its subject and property. This means that two OML AnnotationPropertyValues
 * with the same subject and property must have the same value otherwise they would
 * be structurally distinct triples with the same UUID, which would be ill-formed.
 */
trait AnnotationPropertyValue
  extends ValueCrossReferenceTuple
{
  override val uuid: taggedTypes.AnnotationPropertyValueUUID

  val subject: Element
  val property: AnnotationProperty
  val value: gov.nasa.jpl.imce.oml.tables.taggedTypes.StringDataType
}
