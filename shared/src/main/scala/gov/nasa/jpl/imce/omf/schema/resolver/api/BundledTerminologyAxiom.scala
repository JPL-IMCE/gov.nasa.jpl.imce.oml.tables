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

/*
 * This axiom specifies a TerminologyBox that a Bundle aggregates.
 * A BundledTerminologyAxiom allows a bundle to
 * make references (via TerminologyStatements) to TerminologyThings
 * within the transitive closure of a bundledTerminology.
 */
trait BundledTerminologyAxiom
  extends TerminologyBundleAxiom
{

  val bundledTerminology: TerminologyBox

  /*
   * The bundle is the source
   */
  override val source: TerminologyBox
  /*
   * The bundledTerminology is the target
   */
  override val target: TerminologyBox
}
