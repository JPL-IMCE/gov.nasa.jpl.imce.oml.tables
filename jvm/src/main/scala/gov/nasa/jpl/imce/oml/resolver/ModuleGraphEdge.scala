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

package gov.nasa.jpl.imce.oml.resolver

import gov.nasa.jpl.imce.oml._

import scala.collection.immutable.Seq
import scala.{Any,Boolean,Int,Product,StringContext}

import scalax.collection.GraphEdge.{DiEdge, EdgeCopy, ExtendedKey, NodeProduct}
import scalax.collection.GraphPredef.OuterEdge

case class ModuleGraphEdge[N]
(override val nodes: Product, moduleEdge: resolver.api.ModuleEdge)
  extends DiEdge[N](nodes)
    with ExtendedKey[N]
    with EdgeCopy[ModuleGraphEdge]
    with OuterEdge[N, ModuleGraphEdge]
{
  def keyAttributes = Seq(moduleEdge)
  override def copy[NN](newNodes: Product) = new ModuleGraphEdge[NN](newNodes, moduleEdge)
  override protected def attributesToString = s" $moduleEdge"

  override val hashCode: Int = (nodes, moduleEdge).##

  override def equals(other: Any): Boolean = other match {
    case that: ModuleGraphEdge[n] =>
      this.nodes == that.nodes &&
      this.moduleEdge == that.moduleEdge
  }
}

object ModuleGraphEdge {

  def apply
  (moduleEdge: resolver.api.ModuleEdge)
  (implicit ext: resolver.api.Extent)
  = new ModuleGraphEdge[resolver.api.TerminologyBox](
    NodeProduct(moduleEdge.sourceModule(), moduleEdge.targetModule()), moduleEdge)

}