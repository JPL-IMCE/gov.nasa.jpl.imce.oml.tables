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
 * An OML IRIScalarRestriction is a data range that specifies how one IRI scalar adds facet restrictions to another.
 * Applicable when the restricted scalar represents an [OWL2 IRI].
 * 
 * The restricted scalar must be directly or indirectly a restriction of:
 * - [xsd:anyURI]
 * 
 * Facets:
 * - [xsd:length]
 * - [xsd:minLength]
 * - [xsd:maxLength]
 * - [xsd:pattern]
 */
trait IRIScalarRestriction
  extends RestrictedDataRange
{

  /*
   * The length of the IRI
   */
  val length: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral]
  /*
   * The minimum length of the IRI
   */
  val minLength: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral]
  /*
   * The maximum length of the IRI
   */
  val maxLength: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral]
  /*
   * The pattern of the IRI (https://www.w3.org/TR/xmlschema-2/#regexs)
   */
  val pattern: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralPattern]
}
