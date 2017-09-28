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
 * An OML RuleBodySegment corresponds to a predicate in the body of a SWRL rule.
 * The `position` of an OML RuleBodySegment is 1 for the firstBodySegment of an OML ChainRule.
 * If the `position` of the current OML RuleBodySegment is `p`,
 * then the `position` of the `nextSegment`, if defined, is `p+1`.
 */
trait RuleBodySegment
  extends Element
{

  val previousSegment: scala.Option[RuleBodySegment]
  val rule: scala.Option[ChainRule]

  def position
  (): scala.Int
  def chainRule
  (): ChainRule
  override val uuid: java.util.UUID
  def moduleContext
  ()(implicit extent: Extent): scala.Option[Module]
}
