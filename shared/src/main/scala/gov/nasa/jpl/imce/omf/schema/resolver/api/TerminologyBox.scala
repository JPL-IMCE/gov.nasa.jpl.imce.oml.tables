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
 * A TerminologyBox is a logical container for a set of TerminologyStatements.
 */
trait TerminologyBox
  extends TerminologyThing
  with Resource
{

  val statements: scala.collection.immutable.Set[TerminologyStatement]

  /*
   * A map for the subset of statements that are
   * entities terms indexed by their iri.
   */
  val entities: scala.collection.immutable.Map[java.net.URI,Entity]
  /*
   * A map for the subset of statements that are
   * datatype terms indexed by their iri.
   */
  val dataRelationships: scala.collection.immutable.Map[java.net.URI,DataRelationship]
  /*
   * A map for the subset of statements that are
   * datatype terms indexed by their iri.
   */
  val datatypes: scala.collection.immutable.Map[java.net.URI,Datatype]
  /*
   * The subset of axioms about terminologies.
   */
  val terminologyAxioms: scala.collection.immutable.Set[TerminologyAxiom]
  /*
   * The subset of axioms about terms.
   */
  val termAxioms: scala.collection.immutable.Set[TermAxiom]
}
