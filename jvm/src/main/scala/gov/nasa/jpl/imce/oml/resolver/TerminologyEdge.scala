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

package gov.nasa.jpl.imce.oml.specification.resolver

import gov.nasa.jpl.imce.oml.specification._

import scala.collection.immutable.Seq
import scala.{Product,StringContext,Tuple2}

import scalax.collection.GraphEdge.{DiEdge, EdgeCopy, ExtendedKey, NodeProduct}
import scalax.collection.GraphPredef.OuterEdge

case class TerminologyEdge[N]
(override val nodes: Product, tAxiom: resolver.api.TerminologyAxiom)
  extends DiEdge[N](nodes)
    with ExtendedKey[N]
    with EdgeCopy[TerminologyEdge]
    with OuterEdge[N, TerminologyEdge]
{
  def keyAttributes = Seq(tAxiom)
  override def copy[NN](newNodes: Product) = new TerminologyEdge[NN](newNodes, tAxiom)
  override protected def attributesToString = s" $tAxiom"

}

object TerminologyEdge {

  def apply
  (from: resolver.api.TerminologyBox,
   to: resolver.api.TerminologyBox,
   tAxiom:resolver.api.TerminologyAxiom)
  = new TerminologyEdge[resolver.api.TerminologyBox](NodeProduct(from, to), tAxiom)

  def replaceSource
  (factory: resolver.api.OMLResolvedFactory,
   e: TerminologyEdge[resolver.api.TerminologyBox],
   thatSource: resolver.api.TerminologyBox)
  : TerminologyEdge[resolver.api.TerminologyBox]
  = new TerminologyEdge[resolver.api.TerminologyBox](
    Tuple2(thatSource, e.target),
    replaceAxiomSource(factory, e.tAxiom, thatSource))

  def replaceTarget
  (factory: resolver.api.OMLResolvedFactory,
   e: TerminologyEdge[resolver.api.TerminologyBox],
   thatTarget: resolver.api.TerminologyBox)
  : TerminologyEdge[resolver.api.TerminologyBox]
  = new TerminologyEdge[resolver.api.TerminologyBox](
    Tuple2(e.source, thatTarget),
    replaceAxiomTarget(factory, e.tAxiom, thatTarget))

  def replaceAxiomSource
  (factory: resolver.api.OMLResolvedFactory,
   tAxiom: resolver.api.TerminologyAxiom,
   thatSource: resolver.api.TerminologyBox)
  : resolver.api.TerminologyAxiom
  = tAxiom match {
    case tx: resolver.api.TerminologyExtensionAxiom =>
      factory.copyTerminologyExtensionAxiom_tbox(tx, thatSource)
    case tx: resolver.api.ConceptDesignationTerminologyAxiom =>
      thatSource match {
        case thatGraph: resolver.api.TerminologyGraph =>
          factory.copyConceptDesignationTerminologyAxiom_tbox(tx, thatGraph)
        case _ =>
          throw new java.lang.IllegalArgumentException(
            "replaceAxiomSource for a ConceptualDesignationTerminologyAxiom must be a TerminologyGraph!")
      }
    case tx: resolver.api.TerminologyNestingAxiom =>
      thatSource match {
        case thatGraph: resolver.api.TerminologyGraph =>
          factory.copyTerminologyNestingAxiom_tbox(tx, thatGraph)
        case _ =>
          throw new java.lang.IllegalArgumentException(
            "replaceAxiomSource for a TerminologyNestingAxiom must be a TerminologyGraph!")
      }
    case tx: resolver.api.BundledTerminologyAxiom =>
      thatSource match {
        case thatBundle: resolver.api.Bundle =>
          factory.copyBundledTerminologyAxiom_bundle(tx, thatBundle)
        case _ =>
          throw new java.lang.IllegalArgumentException(
            "replaceAxiomSource for a BundledTerminologyAxiom must be a TerminologyGraph!")
      }
  }

  def replaceAxiomTarget
  (factory: resolver.api.OMLResolvedFactory,
   tAxiom: resolver.api.TerminologyAxiom,
   thatTarget: resolver.api.TerminologyBox)
  : resolver.api.TerminologyAxiom
  = tAxiom match {
    case tx: resolver.api.TerminologyExtensionAxiom =>
      factory.copyTerminologyExtensionAxiom_extendedTerminology(tx, thatTarget)
    case tx: resolver.api.ConceptDesignationTerminologyAxiom =>
      factory.copyConceptDesignationTerminologyAxiom_designatedTerminology(tx, thatTarget)
    case tx: resolver.api.TerminologyNestingAxiom =>
      factory.copyTerminologyNestingAxiom_nestingTerminology(tx, thatTarget)
    case tx: resolver.api.BundledTerminologyAxiom =>
      factory.copyBundledTerminologyAxiom_bundledTerminology(tx, thatTarget)
  }

}