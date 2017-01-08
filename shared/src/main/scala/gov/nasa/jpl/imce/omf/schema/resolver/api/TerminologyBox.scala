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
 * A TerminologyBox is a logical container for a set of TerminologyBoxStatements.
 */
trait TerminologyBox
  extends TerminologyThing
  with Resource
{

  val annotations: scala.collection.immutable.Set[_ <: Annotation]
  /*
   * OMF: https://github.jpl.nasa.gov/pages/imce/gov.nasa.jpl.omf.scala.core/latest/api/index.html#gov.nasa.jpl.omf.scala.core.TerminologyGraphSignature@kind:gov.nasa.jpl.omf.scala.core.TerminologyKind.TerminologyKind
   * OWL: https://github.jpl.nasa.gov/pages/imce/gov.nasa.jpl.omf.scala.binding.owlapi/latest/api/index.html#gov.nasa.jpl.omf.scala.binding.owlapi.types.ModelTerminologyGraph@kind:gov.nasa.jpl.omf.scala.core.TerminologyKind.TerminologyKind
   */
  val kind: gov.nasa.jpl.imce.omf.schema.tables.TerminologyGraphKind
  /*
   * The TerminologyBoxStatements asserted in this TerminologyBox
   */
  val boxStatements: scala.collection.immutable.Set[_ <: TerminologyBoxStatement]

  def withAnnotations
  (a: scala.collection.immutable.Set[_ <: Annotation]
  ): TerminologyBox
  def withBoxStatements
  (s: scala.collection.immutable.Set[_ <: TerminologyBoxStatement]
  ): TerminologyBox
  /*
   * A map for the subset of statements that are
   * entities terms indexed by their uuid.
   */
  def entities
  (): scala.collection.immutable.Map[java.util.UUID,Entity]
  /*
   * A map for the subset of statements that are
   * aspects terms indexed by their uuid.
   */
  def aspects
  (): scala.collection.immutable.Map[java.util.UUID,Aspect]
  /*
   * A map for the subset of statements that are
   * concepts terms indexed by their uuid.
   */
  def concepts
  (): scala.collection.immutable.Map[java.util.UUID,Concept]
  /*
   * A map for the subset of statements that are
   * reified relationship terms indexed by their uuid.
   */
  def reifiedRelationships
  (): scala.collection.immutable.Map[java.util.UUID,ReifiedRelationship]
  /*
   * A map for the subset of statements that are
   * unreified relationship terms indexed by their uuid.
   */
  def unreifiedRelationships
  (): scala.collection.immutable.Map[java.util.UUID,UnreifiedRelationship]
  /*
   * A map for the subset of statements that are
   * datatype terms indexed by their uuid.
   */
  def dataRelationships
  (): scala.collection.immutable.Map[java.util.UUID,DataRelationship]
  /*
   * A map for the subset of statements that are
   * entity scalar data property terms indexed by their uuid.
   */
  def entityScalarDataProperties
  (): scala.collection.immutable.Map[java.util.UUID,EntityScalarDataProperty]
  /*
   * A map for the subset of statements that are
   * data range terms indexed by their uuid.
   */
  def dataranges
  (): scala.collection.immutable.Map[java.util.UUID,DataRange]
  /*
   * A map for the subset of statements that are
   * scalar datatype terms indexed by their uuid.
   */
  def scalars
  (): scala.collection.immutable.Map[java.util.UUID,Scalar]
  /*
   * A map for the subset of statements that are
   * structured datatype terms indexed by their uuid.
   */
  def structures
  (): scala.collection.immutable.Map[java.util.UUID,Structure]
  /*
   * The subset of axioms about terms.
   */
  def termAxioms
  (): scala.collection.immutable.Set[_ <: TermAxiom]
  def everything
  (): scala.collection.immutable.Set[_ <: TerminologyThing]
}
