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

import java.util.UUID

import gov.nasa.jpl.imce.oml._

import scala.collection.immutable.{Map, Set}
import scala.util.control.Exception._
import scala.util.{Failure, Success, Try}
import scala.{Option,None,PartialFunction,StringContext,Tuple2}
import scala.Predef.{ArrowAssoc, require}

import scalax.collection.GraphPredef._
import scalax.collection.immutable.Graph

case class TerminologyContext private[resolver]
(extent: resolver.api.Extent,
 g: Graph[resolver.api.Module, ModuleGraphEdge] = Graph[resolver.api.Module, ModuleGraphEdge]()) {

  def topologicalOrder()
  : Try[g.TopologicalOrder[resolver.api.Module]]
  = g
    .topologicalSort()
    .fold[Try[g.TopologicalOrder[resolver.api.Module]]](
    (cycleNode: g.NodeT) =>
      Failure(new java.lang.IllegalArgumentException(
        s"TerminologyContext circularity on node: $cycleNode in graph $g")),
    (order: g.TopologicalOrder[g.NodeT]) =>
      Success(order.toOuter))

  def findFirstStartingFrom[T]
  (b: resolver.api.Module,
   pf: PartialFunction[resolver.api.Module, T])
  : Option[T]
  = g.get(b).outerNodeTraverser.collectFirst(pf)

  val nodes
  : Map[UUID, resolver.api.Module]
  = g.nodes.toOuter.flatMap(t => t.uuid()(extent).map(id => id -> t)).toMap

  val tboxes
  : Map[UUID, resolver.api.TerminologyBox]
  = g.nodes.toOuter
    .flatMap {
      case t: resolver.api.TerminologyBox =>
        t.uuid()(extent).map(id => id -> t)
      case _ =>
        None
    }
    .toMap

  val graphs
  : Map[UUID, resolver.api.TerminologyGraph]
  = g.nodes.toOuter
    .flatMap {
      case t: resolver.api.TerminologyGraph =>
        t.uuid()(extent).map(id => id -> t)
      case _ =>
        None
    }
    .toMap

  val bundles
  : Map[UUID, resolver.api.Bundle]
  = g.nodes.toOuter
    .flatMap {
      case t: resolver.api.Bundle =>
        t.uuid()(extent).map(id => id -> t)
      case _ =>
        None
    }
    .toMap

  val descriptions
  : Map[UUID, resolver.api.DescriptionBox]
  = g.nodes.toOuter
    .flatMap {
      case t: resolver.api.DescriptionBox =>
        t.uuid()(extent).map(id => id -> t)
      case _ =>
        None
    }
    .toMap


  val bottomNodes
  : Set[_ <: resolver.api.Module]
  = g.nodes.filter(0 == _.inDegree).toOuterNodes.to[Set]

  val rootNodes
  : Set[_ <: resolver.api.Module]
  = g.nodes.filter(0 == _.outDegree).toOuterNodes.to[Set]

}

object TerminologyContext {

  def replaceNode
  (r: OMLTablesResolver,
   g: Graph[resolver.api.Module, ModuleGraphEdge],
   prev: resolver.api.Module,
   next: resolver.api.Module)
  : Try[Graph[resolver.api.Module, ModuleGraphEdge]]
  = g
    .find(outerNode = prev)
    .fold[Try[Graph[resolver.api.Module, ModuleGraphEdge]]](
    Failure(new java.lang.IllegalArgumentException(s"prev node is not in the graph:\nprev:\n$prev\ngraph:\n$g"))
  ) { prevT =>
    nonFatalCatch[Try[Graph[resolver.api.Module, ModuleGraphEdge]]]
      .withApply { (t: java.lang.Throwable) => Failure(t) }
      .apply {
        val in: Set[ModuleGraphEdge[resolver.api.Module]] = prevT.incoming.map { eT =>
          val e = eT.toOuter
          require(e.target == prev)
          e.copy[resolver.api.Module](Tuple2(e.source, next))
        }
        val out: Set[ModuleGraphEdge[resolver.api.Module]] = prevT.outgoing.map { eT =>
          val e = eT.toOuter
          require(e.source == prev)
          e.copy[resolver.api.Module](Tuple2(next, e.target))
        }

        val g1: Graph[resolver.api.Module, ModuleGraphEdge] = g - prev + next
        val g2: Graph[resolver.api.Module, ModuleGraphEdge] = g1 ++ in ++ out
        Success(g2)
      }
  }

  def initialize
  (r: OMLTablesResolver,
   extentUUID: java.util.UUID)
  : TerminologyContext
  = TerminologyContext(r.factory.createExtent)
}
