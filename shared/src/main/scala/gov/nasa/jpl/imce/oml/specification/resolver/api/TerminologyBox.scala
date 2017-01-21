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

package gov.nasa.jpl.imce.oml.specification.resolver.api

/*
 * A TerminologyBox is a logical container for a set of TerminologyBoxStatements.
 */
trait TerminologyBox
  extends TerminologyThing
  with Resource
{

  override val iri: gov.nasa.jpl.imce.oml.specification.tables.IRI
  val nsPrefix: gov.nasa.jpl.imce.oml.specification.tables.NamespacePrefix
  val annotations: scala.collection.immutable.SortedSet[Annotation]
  val kind: gov.nasa.jpl.imce.oml.specification.tables.TerminologyGraphKind
  /*
   * The TerminologyBoxAxioms asserted in this TerminologyBox
   */
  val terminologyBoxAxioms: scala.collection.immutable.SortedSet[TerminologyBoxAxiom]
  /*
   * The TerminologyBoxStatements asserted in this TerminologyBox
   */
  val boxStatements: scala.collection.immutable.SortedSet[TerminologyBoxStatement]

  def annotationsByProperty
  (): scala.collection.immutable.SortedSet[AnnotationPropertyTable]
  def withAnnotations
  (a: scala.collection.immutable.SortedSet[AnnotationPropertyTable]
  ): TerminologyBox
  def withBoxStatements
  (s: scala.collection.immutable.SortedSet[TerminologyBoxStatement]
  ): TerminologyBox
  /*
   * The subset of statements that are entities.
   */
  def entities
  (): scala.collection.immutable.SortedSet[Entity]
  /*
   * The subset of statements that are aspects.
   */
  def aspects
  (): scala.collection.immutable.SortedSet[Aspect]
  /*
   * The subset of statements that are concepts.
   */
  def concepts
  (): scala.collection.immutable.SortedSet[Concept]
  /*
   * The subset of statements that are reified relationships.
   */
  def reifiedRelationships
  (): scala.collection.immutable.SortedSet[ReifiedRelationship]
  /*
   * The subset of statements that are unreified relationships.
   */
  def unreifiedRelationships
  (): scala.collection.immutable.SortedSet[UnreifiedRelationship]
  /*
   * The subset of statements that are data relationships.
   */
  def dataRelationships
  (): scala.collection.immutable.SortedSet[DataRelationship]
  /*
   * A map for the subset of statements that are
   * entity scalar data property terms indexed by their uuid.
   */
  def entityScalarDataProperties
  (): scala.collection.immutable.SortedSet[EntityScalarDataProperty]
  /*
   * A map for the subset of statements that are
   * data range terms indexed by their uuid.
   */
  def dataranges
  (): scala.collection.immutable.SortedSet[DataRange]
  /*
   * A map for the subset of statements that are
   * scalar datatype terms indexed by their uuid.
   */
  def scalars
  (): scala.collection.immutable.SortedSet[Scalar]
  /*
   * A map for the subset of statements that are
   * structured datatype terms indexed by their uuid.
   */
  def structures
  (): scala.collection.immutable.SortedSet[Structure]
  /*
   * The subset of axioms about terms.
   */
  def termAxioms
  (): scala.collection.immutable.SortedSet[TermAxiom]
  def everything
  (): scala.collection.immutable.SortedSet[TerminologyThing]
}
