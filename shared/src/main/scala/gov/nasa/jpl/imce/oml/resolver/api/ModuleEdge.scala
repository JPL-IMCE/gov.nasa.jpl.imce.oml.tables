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
 * An OML ModuleEdge is a binary, directed relationship from one OML Module to another
 */
trait ModuleEdge
  extends Element
{
  override val uuid: taggedTypes.ModuleEdgeUUID

  def sourceModule
  ()(implicit extent: Extent): scala.Option[Module]
  def targetModule
  ()(implicit extent: Extent): gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI
  def moduleContext
  ()(implicit extent: Extent): scala.Option[Module]
}

object ModuleEdge {

  def sourceModule
  (m: ModuleEdge, ext: Extent)
  : scala.Option[Module]
  = m.sourceModule()(ext)

  def targetModule
  (m: ModuleEdge, ext: Extent)
  : gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI
  = m.targetModule()(ext)

  def moduleContext
  (m: ModuleEdge, ext: Extent)
  : scala.Option[Module]
  = m.moduleContext()(ext)

}
