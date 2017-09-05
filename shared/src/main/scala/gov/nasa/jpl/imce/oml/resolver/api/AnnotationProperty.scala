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
 * An OML AnnotationProperty maps to a functional restriction of an [OWL2 AnnotationProperty].
 * Like OWL2, an OML AnnotationProperty is a non-logical property for associating some information
 * to an OML Element; unlike OWL2, an OML AnnotationProperty is functional in the sense
 * that an OML Element can have at most one OML AnnotationPropertyValue for a given OML AnnotationProperty.
 */
trait AnnotationProperty
{

  val uuid: java.util.UUID
  val iri: gov.nasa.jpl.imce.oml.tables.IRI
  val abbrevIRI: gov.nasa.jpl.imce.oml.tables.AbbrevIRI
}
