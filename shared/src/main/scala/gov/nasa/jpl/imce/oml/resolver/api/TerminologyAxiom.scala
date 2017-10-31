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
 * An OML TerminologyAxiom is asserted in an OML TerminologyBox of some kind.
 */
trait TerminologyAxiom
  extends ModuleEdge
{

  /*
   * The source TerminologyBox
   */
  def source
  ()(implicit extent: Extent): scala.Option[TerminologyBox]
  def sourceModule
  ()(implicit extent: Extent): scala.Option[Module]
  /*
   * The target TerminologyBox
   */
  def target
  ()(implicit extent: Extent): gov.nasa.jpl.imce.oml.tables.IRI
  def targetModule
  ()(implicit extent: Extent): gov.nasa.jpl.imce.oml.tables.IRI
}

object TerminologyAxiom {

  def source
  (t: TerminologyAxiom, ext: Extent)
  : scala.Option[TerminologyBox]
  = t.source()(ext)

  def sourceModule
  (t: TerminologyAxiom, ext: Extent)
  : scala.Option[Module]
  = t.sourceModule()(ext)

  def target
  (t: TerminologyAxiom, ext: Extent)
  : gov.nasa.jpl.imce.oml.tables.IRI
  = t.target()(ext)

  def targetModule
  (t: TerminologyAxiom, ext: Extent)
  : gov.nasa.jpl.imce.oml.tables.IRI
  = t.targetModule()(ext)

}
