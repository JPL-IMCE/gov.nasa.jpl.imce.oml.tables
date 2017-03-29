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
 * An OML BundledTerminologyAxiom identifies an OML TerminologyBox that an OML Bundle aggregates.
 * An OML BundledTerminologyAxiom allows an OML Bundle to
 * make references (via OML TerminologyStatement(s)) to OML TerminologyThing(s)
 * within the transitive closure of a bundledTerminology.
 */
trait BundledTerminologyAxiom
  extends TerminologyBundleAxiom
{

  val bundledTerminology: TerminologyBox

  override def uuid
  (extent: Extent): scala.Option[java.util.UUID]
  /*
   * The bundle is the source
   */
  override def source
  (extent: Extent): scala.Option[TerminologyBox]
  /*
   * The bundledTerminology is the target
   */
  override def target
  (extent: Extent): TerminologyBox
}
