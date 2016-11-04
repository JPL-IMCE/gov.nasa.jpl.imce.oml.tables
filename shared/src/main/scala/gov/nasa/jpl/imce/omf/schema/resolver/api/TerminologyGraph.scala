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
 
package gov.nasa.jpl.imce.omf.schema.resolved

/*
 * A TerminologyGraph is a concrete TerminologyBox.
 * OMF: https://github.jpl.nasa.gov/pages/imce/gov.nasa.jpl.omf.scala.core/latest/api/index.html#gov.nasa.jpl.omf.scala.core.OMF@ModelTerminologyGraph
 * OWL: https://github.jpl.nasa.gov/pages/imce/gov.nasa.jpl.omf.scala.binding.owlapi/latest/api/gov/nasa/jpl/omf/scala/binding/owlapi/types/ModelTerminologyGraph.html#inheritance-diagram
 */
trait TerminologyGraph
  extends TerminologyBox
{

  /*
   * OMF: https://github.jpl.nasa.gov/pages/imce/gov.nasa.jpl.omf.scala.core/latest/api/index.html#gov.nasa.jpl.omf.scala.core.TerminologyGraphSignature@kind:gov.nasa.jpl.omf.scala.core.TerminologyKind.TerminologyKind
   * OWL: https://github.jpl.nasa.gov/pages/imce/gov.nasa.jpl.omf.scala.binding.owlapi/latest/api/index.html#gov.nasa.jpl.omf.scala.binding.owlapi.types.ModelTerminologyGraph@kind:gov.nasa.jpl.omf.scala.core.TerminologyKind.TerminologyKind
   */
  val kind: gov.nasa.jpl.imce.omf.schema.tables.TerminologyGraphKind

  /*
   * c, if there is a unique axiom
   * TerminologyNestingAxiom(nestedTerminology=this, nestingContext=c, nestingTerminology=_)
   */
  val nestingConcept: Concept
  /*
   * p, if there is a unique axiom
   * TerminologyNestingAxiom(nestedTerminology=this, nestingContext=_, nestingTerminology=p)
   */
  val nestingParent: TerminologyGraph
  /*
   * the set e from all axioms
   * TerminologyExtensionAxiom(extendedTerminology=e, extendingTerminology=this)
   */
  val extendedGraphs: TerminologyGraph
}
