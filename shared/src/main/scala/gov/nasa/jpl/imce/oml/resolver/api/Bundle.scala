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
 * An OML Bundle is an OML TerminologyBox that is
 * an acyclic logical aggregate of other OML TerminologyBox(es)
 * and that logically assert OML TerminologyBundleStatement(s).
 */
trait Bundle
  extends TerminologyBox
{

  override def moduleEdges
  ()(implicit extent: Extent): scala.collection.immutable.Set[_ <: ModuleEdge]
  def moduleElements
  ()(implicit extent: Extent): scala.collection.immutable.Set[_ <: ModuleElement]
}

object Bundle {

  def moduleEdges
  (b: Bundle, ext: Extent)
  : scala.collection.immutable.Set[_ <: ModuleEdge]
  = b.moduleEdges()(ext)

  def moduleElements
  (b: Bundle, ext: Extent)
  : scala.collection.immutable.Set[_ <: ModuleElement]
  = b.moduleElements()(ext)

}
