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
 * An axiom that specifies the range of values for a scalar as a restriction on another scalar's range.
 * Each specialization of ScalarRestrictionAxiom corresponds to a particular subset of the OWL2-DL datatype map.
 * @see https://www.w3.org/TR/owl2-syntax/#Datatype_Maps
 * Each specialization supports only the datatype constraint facets of the corresponding OWL2-DL datatype map subset.
 * The vocabulary of XSD fundamental facets is not included in OWL2-DL, consequently, there is no support in OMF
 * for specifying datatype restrictions involving XSD fundamental facets.
 */
trait ScalarRestrictionAxiom
  extends ScalarRangeAxiom
{

  /*
   * The restricted (general) scalar
   */
  val restrictedScalar: Scalar
}
