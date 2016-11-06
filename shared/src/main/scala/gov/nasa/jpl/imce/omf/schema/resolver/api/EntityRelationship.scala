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
 
package gov.nasa.jpl.imce.omf.schema.resolver.api

trait EntityRelationship
  extends Entity
{

  val source: Entity
  val target: Entity
  /*
   * 0 <= target.size <= 1
   * OMF: https://github.jpl.nasa.gov/pages/imce/gov.nasa.jpl.omf.scala.core/latest/api/index.html#gov.nasa.jpl.omf.scala.core.RelationshipCharacteristics$@isFunctional:gov.nasa.jpl.omf.scala.core.RelationshipCharacteristics.Value
   * OWL: Same as OMF
   */
  val isFunctional: scala.Boolean
  /*
   * 0 <= source.size <= 1
   * OMF: https://github.jpl.nasa.gov/pages/imce/gov.nasa.jpl.omf.scala.core/latest/api/index.html#gov.nasa.jpl.omf.scala.core.RelationshipCharacteristics$@isInverseFunctional:gov.nasa.jpl.omf.scala.core.RelationshipCharacteristics.Value
   * OWL: Same as OMF
   */
  val isInverseFunctional: scala.Boolean
  /*
   * 1 <= target.size
   * TODO: Review (comes from OML)
   */
  val isEssential: scala.Boolean
  /*
   * 1 <= source.size
   * TODO: Review (comes from OML)
   */
  val isInverseEssential: scala.Boolean
  /*
   * Whether this relationship is symmetric
   * OMF: https://github.jpl.nasa.gov/pages/imce/gov.nasa.jpl.omf.scala.core/latest/api/index.html#gov.nasa.jpl.omf.scala.core.RelationshipCharacteristics$@isSymmetric:gov.nasa.jpl.omf.scala.core.RelationshipCharacteristics.Value
   */
  val isSymmetric: scala.Boolean
  /*
   * Whether this relationship is asymmetric
   * OMF: https://github.jpl.nasa.gov/pages/imce/gov.nasa.jpl.omf.scala.core/latest/api/index.html#gov.nasa.jpl.omf.scala.core.RelationshipCharacteristics$@isAsymmetric:gov.nasa.jpl.omf.scala.core.RelationshipCharacteristics.Value
   */
  val isAsymmetric: scala.Boolean
  /*
   * Whether this relationship is reflexive
   * OMF: https://github.jpl.nasa.gov/pages/imce/gov.nasa.jpl.omf.scala.core/latest/api/index.html#gov.nasa.jpl.omf.scala.core.RelationshipCharacteristics$@isReflexive:gov.nasa.jpl.omf.scala.core.RelationshipCharacteristics.Value
   */
  val isReflexive: scala.Boolean
  /*
   * Whether this relationship is irreflexive
   * OMF: https://github.jpl.nasa.gov/pages/imce/gov.nasa.jpl.omf.scala.core/latest/api/index.html#gov.nasa.jpl.omf.scala.core.RelationshipCharacteristics$@isReflexive:gov.nasa.jpl.omf.scala.core.RelationshipCharacteristics.Value
   */
  val isIrreflexive: scala.Boolean
  /*
   * Whether this relationship is transitive
   * OMF: https://github.jpl.nasa.gov/pages/imce/gov.nasa.jpl.omf.scala.core/latest/api/index.html#gov.nasa.jpl.omf.scala.core.RelationshipCharacteristics$@isTransitive:gov.nasa.jpl.omf.scala.core.RelationshipCharacteristics.Value
   */
  val isTransitive: scala.Boolean
}
