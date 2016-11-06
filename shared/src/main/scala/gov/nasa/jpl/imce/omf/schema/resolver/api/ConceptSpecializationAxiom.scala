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
 * OMF: https://github.jpl.nasa.gov/pages/imce/gov.nasa.jpl.omf.scala.core/latest/api/index.html#gov.nasa.jpl.omf.scala.core.OMF@EntityConceptSubClassAxiom<:OMFtbox.this.ModelTermAxiom
 * OWL: https://github.jpl.nasa.gov/pages/imce/gov.nasa.jpl.omf.scala.binding.owlapi/latest/api/gov/nasa/jpl/omf/scala/binding/owlapi/types/EntityConceptSubClassAxiom.html#inheritance-diagram
 */
trait ConceptSpecializationAxiom
  extends SpecializationAxiom
{

  /*
   * OWL: https://github.jpl.nasa.gov/pages/imce/gov.nasa.jpl.omf.scala.binding.owlapi/latest/api/index.html#gov.nasa.jpl.omf.scala.binding.owlapi.types.EntityConceptSubClassAxiom@sub:gov.nasa.jpl.omf.scala.binding.owlapi.types.ModelEntityConcept
   */
  val subConcept: Concept
  /*
   * OWL: https://github.jpl.nasa.gov/pages/imce/gov.nasa.jpl.omf.scala.binding.owlapi/latest/api/index.html#gov.nasa.jpl.omf.scala.binding.owlapi.types.EntityConceptSubClassAxiom@sup:gov.nasa.jpl.omf.scala.binding.owlapi.types.ModelEntityConcept
   */
  val superConcept: Concept

  /*
   * Get the sub (child) entity
   */
  override val child: Entity
  /*
   * Get the super (parent) entity
   */
  override val parent: Entity
}
