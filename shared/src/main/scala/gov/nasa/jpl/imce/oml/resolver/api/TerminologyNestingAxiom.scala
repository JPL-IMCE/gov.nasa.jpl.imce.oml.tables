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
 * An OML TerminologyNestingAxiom provides support for relating
 * a white-box nested OML TerminologyGraph used for describing internal
 * details about a nesting OML Concept defined in a nesting OML TerminologyBox.
 * This nesting OML Concept specifies the context for the internal details
 * defined in the nested OML TerminologyGraph.
 */
trait TerminologyNestingAxiom
  extends TerminologyBoxAxiom
{
  override val uuid: taggedTypes.TerminologyNestingAxiomUUID

  val nestingTerminology: gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI
  val nestingContext: ConceptKind

  def nestedTerminology
  ()(implicit extent: Extent): scala.Option[TerminologyGraph]
  /*
   * The nestedTerminology is the source
   */
  override def source
  ()(implicit extent: Extent): scala.Option[TerminologyBox]
  /*
   * The nestingTerminology is the target
   */
  override def target
  ()(implicit extent: Extent): gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI
}

object TerminologyNestingAxiom {

  def nestedTerminology
  (t: TerminologyNestingAxiom, ext: Extent)
  : scala.Option[TerminologyGraph]
  = t.nestedTerminology()(ext)

  def source
  (t: TerminologyNestingAxiom, ext: Extent)
  : scala.Option[TerminologyBox]
  = t.source()(ext)

  def target
  (t: TerminologyNestingAxiom, ext: Extent)
  : gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI
  = t.target()(ext)

}
