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
 * An axiom that specifies how one string scalar adds facet restrictions to another
 * Applies when the restricted scalar represents strings (OWL2: 4.3)
 * i.e., when it is one of the following scalars (or their transitively restricted ones):
 * xsd:string
 * xsd:normalizedString
 * xsd:token
 * xsd:language
 * xsd:Name
 * xsd:NCName
 * xsd:NMTOKEN
 * xsd:length, xsd:minLength, xsd:maxLength, xsd:pattern
 */
trait StringScalarRestrictionAxiom
  extends ScalarRestrictionAxiom
{

  /*
   * The length of the string
   */
  val length: scala.Option[scala.Int]
  /*
   * The minimum length of the string
   */
  val minLength: scala.Option[scala.Int]
  /*
   * The maximum length of the string
   */
  val maxLength: scala.Option[scala.Int]
  /*
   * The pattern of the string (https://www.w3.org/TR/xmlschema-2/#regexs)
   */
  val pattern: scala.Option[gov.nasa.jpl.imce.omf.schema.tables.Pattern]
}
