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
 * An OML ConceptDesignationTerminologyAxiom establishes
 * a relationship from a source OML TerminologyBox
 * where a designated OML Concept is defined to
 * a target OML TerminologyGraph in which the internal
 * structure of the designated OML Concept can be defined.
 */
trait ConceptDesignationTerminologyAxiom
  extends TerminologyBoxAxiom
{

  val designatedTerminology: TerminologyBox
  val designatedConcept: Concept

  override def uuid
  ()(implicit extent: Extent): scala.Option[java.util.UUID]
  def designationTerminologyGraph
  ()(implicit extent: Extent): scala.Option[TerminologyGraph]
  /*
   * The designationTerminologyGraph is the source
   */
  override def source
  ()(implicit extent: Extent): scala.Option[TerminologyBox]
  /*
   * The TerminologyBox that asserts the designatedConcept is the target
   */
  override def target
  ()(implicit extent: Extent): TerminologyBox
}
