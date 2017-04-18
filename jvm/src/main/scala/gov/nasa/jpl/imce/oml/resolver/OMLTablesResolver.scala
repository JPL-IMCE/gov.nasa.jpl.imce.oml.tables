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

import java.lang.IllegalArgumentException
import java.util.UUID

import gov.nasa.jpl.imce.oml.{resolver, _}

import scalax.collection.immutable.Graph
import scala.collection.immutable.Seq
import scala.collection.parallel.immutable.ParSeq
import scala.{None, Some, StringContext, Tuple2, Tuple3, Tuple4, Tuple5}
import scala.util.{Failure, Success, Try}
import scala.Predef.ArrowAssoc

case class OMLTablesResolver private[resolver]
(context: TerminologyContext,
 queue: tables.OMLSpecificationTables,
 factory: api.OMLResolvedFactory)

object OMLTablesResolver {

  def mapAnnotationProperties
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = r.queue.annotationProperties.foldLeft[Try[OMLTablesResolver]](Success(r)) {
    case (Success(ri), tap) =>
      val (ej, rap) = ri.factory.createAnnotationProperty(ri.context.extent, UUID.fromString(tap.uuid), tap.iri, tap.abbrevIRI)
      if (!ej.lookupAnnotationProperty(rap.uuid).contains(rap))
        Failure(new IllegalArgumentException(s"AnnotationProperty not in extent: $rap"))
      else if (rap.uuid.toString != tap.uuid)
        Failure(new IllegalArgumentException(s"AnnotationProperty: $tap vs. $rap"))
      else
        Success(ri.copy(context = ri.context.copy(extent = ej)))
    case (Failure(f), _) =>
      Failure(f)
  }

  def mapTerminologyGraphs
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = r.queue.terminologyGraphs.foldLeft[Try[OMLTablesResolver]](Success(r)) {
    case (Success(ri), tg) =>
      val (ej, rg) = ri.factory.createTerminologyGraph(ri.context.extent, tg.kind, tg.iri)
      if (!ej.lookupTerminologyGraph(rg.uuid).contains(rg))
        Failure(new IllegalArgumentException(s"TerminologyGraph not in extent: $rg"))
      else if (!OMLOps.uuidEquivalent(rg.uuid, tg.uuid))
        Failure(new IllegalArgumentException(s"TerminologyGraph: $tg vs. $rg"))
      else
        Success(ri.copy(context = ri.context.copy(extent = ej)))
    case (Failure(f), _) =>
      Failure(f)
  }

  def mapBundles
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = r.queue.bundles.foldLeft[Try[OMLTablesResolver]](Success(r)) {
    case (Success(ri), tb) =>
      val (ej, rb) = ri.factory.createBundle(ri.context.extent, tb.kind, tb.iri)
      if (!ej.lookupBundle(rb.uuid).contains(rb))
        Failure(new IllegalArgumentException(s"Bundle not in extent: $rb"))
      else if (!OMLOps.uuidEquivalent(rb.uuid, tb.uuid))
        Failure(new IllegalArgumentException(s"Bundle: $tb vs. $rb"))
      else
        Success(ri.copy(context = ri.context.copy(extent = ej)))
    case (Failure(f), _) =>
      Failure(f)
  }

  def mapDescriptionBoxes
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = r.queue.descriptionBoxes.foldLeft[Try[OMLTablesResolver]](Success(r)) {
    case (Success(ri), tdb) =>
      val (ej, rdb) = ri.factory.createDescriptionBox(ri.context.extent, tdb.kind, tdb.iri)
      if (!ej.lookupDescriptionBox(rdb.uuid).contains(rdb))
        Failure(new IllegalArgumentException(s"DescriptionBox not in extent: $rdb"))
      else if (!OMLOps.uuidEquivalent(rdb.uuid, tdb.uuid))
        Failure(new IllegalArgumentException(s"DescriptionBox: $tdb vs. $rdb"))
      else
        Success(ri.copy(context = ri.context.copy(extent = ej)))
    case (Failure(f), _) =>
      Failure(f)
  }

  def mapAspects
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.aspects
        .groupBy(_.tboxUUID)
        .map { case (tboxUUID, aspects) => UUID.fromString(tboxUUID) -> aspects }

    val byTBox = for {
      pair <- byUUID
      (tboxUUID, aspects) = pair
      tboxM = r.context.extent.lookupTerminologyBox(tboxUUID)
    } yield (tboxM, aspects)

    val unresolvable = byTBox.filter(_._1.isEmpty).flatMap(_._2).to[Seq]
    val resolvable = byTBox.flatMap {
      case ((Some(tboxM), aspects)) => Some(Tuple2(tboxM, aspects))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(aspects = unresolvable)))) {
        case (Success(ri), (tboxM, aspects)) =>
          val rj
          : Try[OMLTablesResolver]
          = aspects.foldLeft[Try[OMLTablesResolver]](Success(ri)) {
            case (Success(rk), taspect) =>
              val (ek, raspect) = rk.factory.createAspect(rk.context.extent, tboxM, taspect.name)
              if (!ek.lookupBoxStatements(tboxM).contains(raspect))
                Failure(new IllegalArgumentException(s"Aspect not in extent: $raspect"))
              else if (!OMLOps.uuidEquivalent(raspect.uuid, taspect.uuid))
                Failure(new IllegalArgumentException(s"Aspect: $taspect vs. $raspect"))
              else
                Success(rk.copy(context = rk.context.copy(extent = ek)))
            case (Failure(f), _) =>
              Failure(f)
          }
          rj
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapConcepts
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.concepts
        .groupBy(_.tboxUUID)
        .map { case (tboxUUID, concepts) => UUID.fromString(tboxUUID) -> concepts }

    val byTBox = for {
      pair <- byUUID
      (tboxUUID, concepts) = pair
      tboxM = r.context.extent.lookupTerminologyBox(tboxUUID)
    } yield (tboxM, concepts)

    val unresolvable = byTBox.filter(_._1.isEmpty).flatMap(_._2).to[Seq]
    val resolvable = byTBox.flatMap {
      case ((Some(tboxM), concepts)) => Some(Tuple2(tboxM, concepts))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r)) {
        case (Success(ri), (tboxM, concepts)) =>
          val rj
          : Try[OMLTablesResolver]
          = concepts.foldLeft[Try[OMLTablesResolver]](Success(ri.copy(queue = ri.queue.copy(concepts = unresolvable)))) {
            case (Success(rk), tconcept) =>
              val (ek, rconcept) = rk.factory.createConcept(rk.context.extent, tboxM, tconcept.name)
              if (!ek.lookupBoxStatements(tboxM).contains(rconcept))
                Failure(new IllegalArgumentException(s"Concept not in extent: $rconcept"))
              else if (!OMLOps.uuidEquivalent(rconcept.uuid, tconcept.uuid))
                Failure(new IllegalArgumentException(s"Concept: $tconcept vs. $rconcept"))
              else
                Success(rk.copy(context = rk.context.copy(extent = ek)))
            case (Failure(f), _) =>
              Failure(f)
          }
          rj
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapScalars
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.scalars
        .groupBy(_.tboxUUID)
        .map { case (tboxUUID, scalars) => UUID.fromString(tboxUUID) -> scalars }

    val byTBox = for {
      pair <- byUUID
      (tboxUUID, scalars) = pair
      tboxM = r.context.extent.lookupTerminologyBox(tboxUUID)
    } yield (tboxM, scalars)

    val unresolvable = byTBox.filter(_._1.isEmpty).flatMap(_._2).to[Seq]
    val resolvable = byTBox.flatMap {
      case ((Some(tboxM), scalars)) => Some(Tuple2(tboxM, scalars))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(scalars = unresolvable)))) {
        case (Success(ri), (tboxM, scalars)) =>
          val rj
          : Try[OMLTablesResolver]
          = scalars.foldLeft[Try[OMLTablesResolver]](Success(ri)) {
            case (Success(rk), tscalar) =>
              val (ek, rscalar) = rk.factory.createScalar(rk.context.extent, tboxM, tscalar.name)
              if (!ek.lookupBoxStatements(tboxM).contains(rscalar))
                Failure(new IllegalArgumentException(s"Scalar not in extent: $rscalar"))
              else if (!OMLOps.uuidEquivalent(rscalar.uuid, tscalar.uuid))
                Failure(new IllegalArgumentException(s"Scalar: $tscalar vs. $rscalar"))
              else
                Success(rk.copy(context = rk.context.copy(extent = ek)))
            case (Failure(f), _) =>
              Failure(f)
          }
          rj
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapStructures
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.structures
        .groupBy(_.tboxUUID)
        .map { case (tboxUUID, structures) => UUID.fromString(tboxUUID) -> structures }

    val byTBox = for {
      pair <- byUUID
      (tboxUUID, structures) = pair
      tboxM = r.context.extent.lookupTerminologyBox(tboxUUID)
    } yield (tboxM, structures)

    val unresolvable = byTBox.filter(_._1.isEmpty).flatMap(_._2).to[Seq]
    val resolvable = byTBox.flatMap {
      case ((Some(tboxM), structures)) => Some(Tuple2(tboxM, structures))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(structures = unresolvable)))) {
        case (Success(ri), (tboxM, structures)) =>
          val rj
          : Try[OMLTablesResolver]
          = structures.foldLeft[Try[OMLTablesResolver]](Success(ri)) {
            case (Success(rk), tconcept) =>
              val (ek, rconcept) = rk.factory.createStructure(rk.context.extent, tboxM, tconcept.name)
              if (!ek.lookupBoxStatements(tboxM).contains(rconcept))
                Failure(new IllegalArgumentException(s"Structure not in extent: $rconcept"))
              else if (!OMLOps.uuidEquivalent(rconcept.uuid, tconcept.uuid))
                Failure(new IllegalArgumentException(s"Structure: $tconcept vs. $rconcept"))
              else
                Success(rk.copy(context = rk.context.copy(extent = ek)))
            case (Failure(f), _) =>
              Failure(f)
          }
          rj
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapTerminologyExtends
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.terminologyExtensionAxioms
        .map { tAxiom =>
          (UUID.fromString(tAxiom.tboxUUID), UUID.fromString(tAxiom.extendedTerminologyUUID)) -> tAxiom
        }

    val byTBox = for {
      pair <- byUUID
      ((tboxUUIDSource, tboxUUIDTarget), extension) = pair
      tboxMSource = r.context.extent.lookupTerminologyBox(tboxUUIDSource)
      tboxMTarget = r.context.extent.lookupTerminologyBox(tboxUUIDTarget)
    } yield (tboxMSource, tboxMTarget, extension)

    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty).map(_._3)
    val resolvable = byTBox.flatMap {
      case (Some(tboxMSource), Some(tboxMTarget), extension) => Some(Tuple3(tboxMSource, tboxMTarget, extension))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(terminologyExtensionAxioms = unresolvable)))) {
        case (Success(ri), (tboxMSource, tboxMTarget, textension)) =>
          val (ej, rextension) = ri.factory.createTerminologyExtensionAxiom(ri.context.extent, tboxMSource, tboxMTarget)
          if (!ej.lookupBoxAxioms(tboxMSource).contains(rextension))
            Failure(new IllegalArgumentException(s"TerminologyExtensionAxiom not in extent: $rextension"))
          else if (!OMLOps.uuidEquivalent(rextension.uuid, textension.uuid))
            Failure(new IllegalArgumentException(s"TerminologyExtensionAxiom: $textension vs. $rextension"))
          else
            Success(ri.copy(context = ri.context.copy(extent = ej)))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapTerminologyNestings
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.terminologyNestingAxioms
        .map { tAxiom =>
          ( UUID.fromString(tAxiom.tboxUUID),
            UUID.fromString(tAxiom.nestingTerminologyUUID),
            UUID.fromString(tAxiom.nestingContextUUID) ) -> tAxiom
        }

    val byTBox = for {
      pair <- byUUID
      ((tboxUUIDSource, tboxUUIDTarget, tboxUUIDConcept), nesting) = pair
      tboxMSource = r.context.extent.lookupTerminologyGraph(tboxUUIDSource)
      tboxMTarget = r.context.extent.lookupTerminologyBox(tboxUUIDTarget)
      tboxMConcept = r.context.extent.lookupTerminologyBoxStatement(tboxUUIDConcept) match {
        case c: api.Concept => Some(c)
        case _ => None
      }
    } yield (tboxMSource, tboxMTarget, tboxMConcept, nesting)

    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = byTBox.flatMap {
      case (Some(tboxMSource), Some(tboxMTarget), Some(tboxMConcept), nesting) => Some(Tuple4(tboxMSource, tboxMTarget, tboxMConcept, nesting))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(terminologyNestingAxioms = unresolvable)))) {
        case (Success(ri), (tboxMSource, tboxMTarget, tboxMConcept, tnesting)) =>
          val (ej, rnesting) = ri.factory.createTerminologyNestingAxiom(ri.context.extent, tboxMSource, tboxMTarget, tboxMConcept)
          if (!ej.lookupBoxAxioms(tboxMSource).contains(rnesting))
            Failure(new IllegalArgumentException(s"TerminologyNestingAxiom not in extent: $rnesting"))
          else if (!OMLOps.uuidEquivalent(rnesting.uuid, tnesting.uuid))
            Failure(new IllegalArgumentException(s"TerminologyNestingAxiom: $tnesting vs. $rnesting"))
          else
            Success(ri.copy(context = ri.context.copy(extent = ej)))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapConceptDesignationTerminologyAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.conceptDesignationTerminologyAxioms
        .map { tAxiom =>
          ( UUID.fromString(tAxiom.tboxUUID),
            UUID.fromString(tAxiom.designatedTerminologyUUID),
            UUID.fromString(tAxiom.designatedConceptUUID) ) -> tAxiom
        }

    val byTBox = for {
      pair <- byUUID
      ((tboxUUIDSource, tboxUUIDTarget, tboxUUIDConcept), conceptDesignation) = pair
      tboxMSource = r.context.extent.lookupTerminologyGraph(tboxUUIDSource)
      tboxMTarget = r.context.extent.lookupTerminologyGraph(tboxUUIDTarget)
      tboxMConcept = r.context.extent.lookupTerminologyBoxStatement(tboxUUIDConcept) match {
        case c: api.Concept => Some(c)
        case _ => None
      }
    } yield (tboxMSource, tboxMTarget, tboxMConcept, conceptDesignation)

    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = byTBox.flatMap {
      case (Some(tboxMSource), Some(tboxMTarget), Some(tboxMConcept), conceptDesignation) => Some(Tuple4(tboxMSource, tboxMTarget, tboxMConcept, conceptDesignation))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(conceptDesignationTerminologyAxioms = unresolvable)))) {
        case (Success(ri), (tboxMSource, tboxMTarget, tboxMConcept, tConceptDesignation)) =>
          val (ej, rConceptDesignation) = ri.factory.createConceptDesignationTerminologyAxiom(ri.context.extent, tboxMSource, tboxMConcept, tboxMTarget)
          if (!ej.lookupBoxAxioms(tboxMSource).contains(rConceptDesignation))
            Failure(new IllegalArgumentException(s"ConceptDesignationTerminologyAxiom: axiom not in extent: $rConceptDesignation"))
          else if (!ej.lookupBoxStatements(tboxMTarget).contains(tboxMConcept))
            Failure(new IllegalArgumentException(s"ConceptDesignationTerminologyAxiom: designated concept not in designated graph: $rConceptDesignation"))
          else if (!OMLOps.uuidEquivalent(rConceptDesignation.uuid, tConceptDesignation.uuid))
            Failure(new IllegalArgumentException(s"ConceptDesignationTerminologyAxiom: $tConceptDesignation vs. $rConceptDesignation"))
          else
            Success(ri.copy(context = ri.context.copy(extent = ej)))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapBundledTerminologyAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.bundledTerminologyAxioms
        .map { tAxiom =>
          (UUID.fromString(tAxiom.bundleUUID), UUID.fromString(tAxiom.bundledTerminologyUUID)) -> tAxiom
        }

    val byTBox = for {
      pair <- byUUID
      ((bundleUUIDSource, tboxUUIDTarget), bundleAxiom) = pair
      bundleMSource = r.context.extent.lookupBundle(bundleUUIDSource)
      tboxMTarget = r.context.extent.lookupTerminologyBox(tboxUUIDTarget)
    } yield (bundleMSource, tboxMTarget, bundleAxiom)

    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty).map(_._3)
    val resolvable = byTBox.flatMap {
      case (Some(bundleMSource), Some(tboxMTarget), bundleAxiom) => Some(Tuple3(bundleMSource, tboxMTarget, bundleAxiom))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(bundledTerminologyAxioms = unresolvable)))) {
        case (Success(ri), (bundleMSource, tboxMTarget, tBundleAxiom)) =>
          val (ej, rBundleAxiom) = ri.factory.createBundledTerminologyAxiom(ri.context.extent, tboxMTarget, bundleMSource)
          if (!ej.lookupBundleAxioms(bundleMSource).contains(rBundleAxiom))
            Failure(new IllegalArgumentException(s"BundledTerminologyAxiom not in extent: $rBundleAxiom"))
          else if (!OMLOps.uuidEquivalent(rBundleAxiom.uuid, tBundleAxiom.uuid))
            Failure(new IllegalArgumentException(s"BundledTerminologyAxiom: $tBundleAxiom vs. $rBundleAxiom"))
          else
            Success(ri.copy(context = ri.context.copy(extent = ej)))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapRestrictedDataRanges
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val ns = r.context.tboxes
    val g = r.context.g
    val (r1, u1) =
      r.queue.binaryScalarRestrictions
        .groupBy(_.tboxUUID)
        .map { case (uuid, ranges) => UUID.fromString(uuid) -> ranges }
        .partition { case (tboxUUID, _) => ns.contains(tboxUUID) }
    val (r2, u2) =
      r.queue.iriScalarRestrictions
        .groupBy(_.tboxUUID)
        .map { case (uuid, ranges) => UUID.fromString(uuid) -> ranges }
        .partition { case (tboxUUID, _) => ns.contains(tboxUUID) }
    val (r3, u3) =
      r.queue.numericScalarRestrictions
        .groupBy(_.tboxUUID)
        .map { case (uuid, ranges) => UUID.fromString(uuid) -> ranges }
        .partition { case (tboxUUID, _) => ns.contains(tboxUUID) }
    val (r4, u4) =
      r.queue.plainLiteralScalarRestrictions
        .groupBy(_.tboxUUID)
        .map { case (uuid, ranges) => UUID.fromString(uuid) -> ranges }
        .partition { case (tboxUUID, _) => ns.contains(tboxUUID) }
    val (r5, u5) =
      r.queue.scalarOneOfRestrictions
        .groupBy(_.tboxUUID)
        .map { case (uuid, ranges) => UUID.fromString(uuid) -> ranges }
        .partition { case (tboxUUID, _) => ns.contains(tboxUUID) }
    val (r6, u6) =
      r.queue.stringScalarRestrictions
        .groupBy(_.tboxUUID)
        .map { case (uuid, ranges) => UUID.fromString(uuid) -> ranges }
        .partition { case (tboxUUID, _) => ns.contains(tboxUUID) }
    val (r7, u7) =
      r.queue.timeScalarRestrictions
        .groupBy(_.tboxUUID)
        .map { case (uuid, ranges) => UUID.fromString(uuid) -> ranges }
        .partition { case (tboxUUID, _) => ns.contains(tboxUUID) }

    val worklist = DataRangesToResolve(
      binaryScalarRestrictions =
        r1.map { case (guuid, drs) => guuid -> drs.map(dr => UUID.fromString(dr.restrictedRangeUUID) -> dr) },
      iriScalarRestrictions =
        r2.map { case (guuid, drs) => guuid -> drs.map(dr => UUID.fromString(dr.restrictedRangeUUID) -> dr) },
      numericScalarRestrictions =
        r3.map { case (guuid, drs) => guuid -> drs.map(dr => UUID.fromString(dr.restrictedRangeUUID) -> dr) },
      plainLiteralScalarRestrictions =
        r4.map { case (guuid, drs) => guuid -> drs.map(dr => UUID.fromString(dr.restrictedRangeUUID) -> dr) },
      scalarOneOfRestrictions =
        r5.map { case (guuid, drs) => guuid -> drs.map(dr => UUID.fromString(dr.restrictedRangeUUID) -> dr) },
      stringScalarRestrictions =
        r6.map { case (guuid, drs) => guuid -> drs.map(dr => UUID.fromString(dr.restrictedRangeUUID) -> dr) },
      timeScalarRestrictions =
        r7.map { case (guuid, drs) => guuid -> drs.map(dr => UUID.fromString(dr.restrictedRangeUUID) -> dr) }
    )

    DataRangesToResolve
      .resolve(r, worklist)
      .map { case (resolved, remaining) =>

        val updated = resolved.queue.copy(
          binaryScalarRestrictions =
            u1.flatMap(_._2).to[Seq] ++ remaining.binaryScalarRestrictions.flatMap(_._2.map(_._2)).to[Seq],
          iriScalarRestrictions =
            u2.flatMap(_._2).to[Seq] ++ remaining.iriScalarRestrictions.flatMap(_._2.map(_._2)).to[Seq],
          numericScalarRestrictions =
            u3.flatMap(_._2).to[Seq] ++ remaining.numericScalarRestrictions.flatMap(_._2.map(_._2)).to[Seq],
          plainLiteralScalarRestrictions =
            u4.flatMap(_._2).to[Seq] ++ remaining.plainLiteralScalarRestrictions.flatMap(_._2.map(_._2)).to[Seq],
          scalarOneOfRestrictions =
            u5.flatMap(_._2).to[Seq] ++ remaining.scalarOneOfRestrictions.flatMap(_._2.map(_._2)).to[Seq],
          stringScalarRestrictions =
            u6.flatMap(_._2).to[Seq] ++ remaining.stringScalarRestrictions.flatMap(_._2.map(_._2)).to[Seq],
          timeScalarRestrictions =
            u7.flatMap(_._2).to[Seq] ++ remaining.timeScalarRestrictions.flatMap(_._2.map(_._2)).to[Seq]
        )

        resolved.copy(queue = updated)
      }
  }

  def resolve
  (t: tables.OMLSpecificationTables,
   factory: api.OMLResolvedFactory,
   extentUUID: java.util.UUID)
  : Try[OMLTablesResolver]
  = for {
    init <- Try.apply(OMLTablesResolver(
      resolver.TerminologyContext(factory.createExtent),
      t,
      factory))
    // AnnotationProperties
    step00 <- mapAnnotationProperties(init)
    // Terminologies
    step0a <- mapTerminologyGraphs(step00)
    step0b <- mapBundles(step0a)
    step0c <- mapDescriptionBoxes(step0b)
    // Atomic terms
    step1a <- mapAspects(step0c)
    step1b <- mapConcepts(step1a)
    step1c <- mapScalars(step1b)
    step1d <- mapStructures(step1c)
    // TerminologyBoxAxiom relationships
    step2a <- mapTerminologyExtends(step1d)
    step2b <- mapTerminologyNestings(step2a)
    step2c <- mapConceptDesignationTerminologyAxioms(step2b)
    // TerminologyBundleAxiom relationships
    step3a <- mapBundledTerminologyAxioms(step2c)
    // Relational terms
    step4a <- mapRestrictedDataRanges(step3a)
    step4b <- mapReifiedRelationships(step4a)
    step4c <- mapUnreifiedRelationships(step4b)
    // DataRelationships
    step5a <- mapEntityScalarDataProperties(step4c)
    step5b <- mapEntityStructuredDataProperties(step5a)
    step5c <- mapScalarDataProperties(step5b)
    step5d <- mapStructuredDataProperties(step5c)
    // Axioms
    step6a <- mapScalarOneOfLiteralAxioms(step5d)
    // - TermAxioms
    // -- EntityRestrictionAxioms
    step7a <- mapEntityExistentialRestrictionAxioms(step6a)
    step7b <- mapEntityUniversalRestrictionAxioms(step7a)
    // -- EntityScalarDataPropertyRestrictionAxioms
    step8a <- mapEntityScalarDataPropertyExistentialRestrictionAxioms(step7b)
    step8b <- mapEntityScalarDataPropertyParticularRestrictionAxioms(step8a)
    step8c <- mapEntityScalarDataPropertyUniversalRestrictionAxioms(step8b)
    // -- SpecializationAxiom
    step9a <- mapAspectSpecializationAxioms(step8c)
    step9b <- mapConceptSpecializationAxioms(step9a)
    step9c <- mapReifiedRelationshipSpecializationAxioms(step9b)
    // TerminologyBundleStatements
    step10 <- mapRootConceptTaxonomyAxioms(step9c)
    // Annotations
    step11 <- mapAnnotations(step10)
  } yield
    step11

  def seqopAppend[T]
  (s: Seq[T], entry: (UUID, ParSeq[T]))
  : Seq[T]
  = s ++ entry._2

  def seqopAppend1[T]
  (s: Seq[T], entry: ((UUID, UUID), T))
  : Seq[T]
  = s :+ entry._2

  def combopGraphs
  (g1: Graph[api.Module, ModuleGraphEdge],
   g2: Graph[api.Module, ModuleGraphEdge])
  : Graph[api.Module, ModuleGraphEdge]
  = g1.union(g2)

  type HyperGraphV = Try[Graph[api.Module, ModuleGraphEdge]]

  def mapReifiedRelationships
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.reifiedRelationships
        .map { trr =>
          ( UUID.fromString(trr.tboxUUID),
            UUID.fromString(trr.sourceUUID),
            UUID.fromString(trr.targetUUID) ) -> trr
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, sourceUUID, targetUUID), trr) = tuple
      tboxM = r.context.extent.lookupTerminologyBox(tboxUUID)
      sourceM = r.context.extent.lookupTerminologyBoxStatement(sourceUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      targetM = r.context.extent.lookupTerminologyBoxStatement(sourceUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
    } yield (tboxM, sourceM, targetM, trr)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(sourceM), Some(targetM), trr) => Some(Tuple4(tboxM, sourceM, targetM, trr))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(reifiedRelationships = unresolvable)))) {
        case (Success(ri), (tboxM, sourceM, targetM, trr)) =>
          val (ej, rrr) = ri.factory.createReifiedRelationship(
            ri.context.extent,
            tboxM,
            sourceM,
            targetM,
            trr.isAsymmetric,
            trr.isEssential,
            trr.isFunctional,
            trr.isInverseEssential,
            trr.isInverseFunctional,
            trr.isIrreflexive,
            trr.isReflexive,
            trr.isSymmetric,
            trr.isTransitive,
            trr.name,
            trr.unreifiedPropertyName,
            trr.unreifiedInversePropertyName)

          if (!ej.lookupBoxStatements(tboxM).contains(rrr))
            Failure(new IllegalArgumentException(s"ReifiedRelationship not in extent: $rrr"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(trr.uuid)).contains(rrr))
            Failure(new IllegalArgumentException(s"ReifiedRelationship: $trr vs. $rrr"))
          else
            Success(ri.copy(context = ri.context.copy(extent = ej)))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapUnreifiedRelationships
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.unreifiedRelationships
        .map { tur =>
          ( UUID.fromString(tur.tboxUUID),
            UUID.fromString(tur.sourceUUID),
            UUID.fromString(tur.targetUUID) ) -> tur
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, sourceUUID, targetUUID), tur) = tuple
      tboxM = r.context.extent.lookupTerminologyBox(tboxUUID)
      sourceM = r.context.extent.lookupTerminologyBoxStatement(sourceUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      targetM = r.context.extent.lookupTerminologyBoxStatement(sourceUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
    } yield (tboxM, sourceM, targetM, tur)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(sourceM), Some(targetM), tur) => Some(Tuple4(tboxM, sourceM, targetM, tur))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(unreifiedRelationships = unresolvable)))) {
        case (Success(ri), (tboxM, sourceM, targetM, tur)) =>
          val (ej, rur) = ri.factory.createUnreifiedRelationship(
            ri.context.extent,
            tboxM,
            sourceM,
            targetM,
            tur.isAsymmetric,
            tur.isEssential,
            tur.isFunctional,
            tur.isInverseEssential,
            tur.isInverseFunctional,
            tur.isIrreflexive,
            tur.isReflexive,
            tur.isSymmetric,
            tur.isTransitive,
            tur.name)

          if (!ej.lookupBoxStatements(tboxM).contains(rur))
            Failure(new IllegalArgumentException(s"UnreifiedRelationship not in extent: $rur"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tur.uuid)).contains(rur))
            Failure(new IllegalArgumentException(s"UnreifiedRelationship: $tur vs. $rur"))
          else
            Success(ri.copy(context = ri.context.copy(extent = ej)))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapEntityScalarDataProperties
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.entityScalarDataProperties
        .map { tsdp =>
          ( UUID.fromString(tsdp.tboxUUID),
            UUID.fromString(tsdp.domainUUID),
            UUID.fromString(tsdp.rangeUUID) ) -> tsdp
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, domainUUID, rangeUUID), tsdp) = tuple
      tboxM = r.context.extent.lookupTerminologyBox(tboxUUID)
      domainM = r.context.extent.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      rangeM = r.context.extent.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(dr: api.DataRange) => Some(dr)
        case _ => None
      }
    } yield (tboxM, domainM, rangeM, tsdp)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(domainM), Some(rangeM), tur) => Some(Tuple4(tboxM, domainM, rangeM, tur))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(entityScalarDataProperties = unresolvable)))) {
        case (Success(ri), (tboxM, domainM, rangeM, tsdp)) =>
          val (ej, rsdp) = ri.factory.createEntityScalarDataProperty(
            ri.context.extent,
            tboxM,
            domainM,
            rangeM,
            tsdp.isIdentityCriteria,
            tsdp.name)

          if (!ej.lookupBoxStatements(tboxM).contains(rsdp))
            Failure(new IllegalArgumentException(s"EntityScalarDataProperty not in extent: $rsdp"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tsdp.uuid)).contains(rsdp))
            Failure(new IllegalArgumentException(s"EntityScalarDataProperty: $tsdp vs. $rsdp"))
          else
            Success(ri.copy(context = ri.context.copy(extent = ej)))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapEntityStructuredDataProperties
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.entityStructuredDataProperties
        .map { tsdp =>
          ( UUID.fromString(tsdp.tboxUUID),
            UUID.fromString(tsdp.domainUUID),
            UUID.fromString(tsdp.rangeUUID) ) -> tsdp
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, domainUUID, rangeUUID), tsdp) = tuple
      tboxM = r.context.extent.lookupTerminologyBox(tboxUUID)
      domainM = r.context.extent.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      rangeM = r.context.extent.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(dr: api.Structure) => Some(dr)
        case _ => None
      }
    } yield (tboxM, domainM, rangeM, tsdp)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(domainM), Some(rangeM), tur) => Some(Tuple4(tboxM, domainM, rangeM, tur))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(entityStructuredDataProperties = unresolvable)))) {
        case (Success(ri), (tboxM, domainM, rangeM, tsdp)) =>
          val (ej, rsdp) = ri.factory.createEntityStructuredDataProperty(
            ri.context.extent,
            tboxM,
            domainM,
            rangeM,
            tsdp.isIdentityCriteria,
            tsdp.name)

          if (!ej.lookupBoxStatements(tboxM).contains(rsdp))
            Failure(new IllegalArgumentException(s"EntityStructuredDataProperty not in extent: $rsdp"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tsdp.uuid)).contains(rsdp))
            Failure(new IllegalArgumentException(s"EntityStructuredDataProperty: $tsdp vs. $rsdp"))
          else
            Success(ri.copy(context = ri.context.copy(extent = ej)))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapScalarDataProperties
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.scalarDataProperties
        .map { tsdp =>
          ( UUID.fromString(tsdp.tboxUUID),
            UUID.fromString(tsdp.domainUUID),
            UUID.fromString(tsdp.rangeUUID) ) -> tsdp
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, domainUUID, rangeUUID), tsdp) = tuple
      tboxM = r.context.extent.lookupTerminologyBox(tboxUUID)
      domainM = r.context.extent.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(e: api.Structure) => Some(e)
        case _ => None
      }
      rangeM = r.context.extent.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(dr: api.DataRange) => Some(dr)
        case _ => None
      }
    } yield (tboxM, domainM, rangeM, tsdp)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(domainM), Some(rangeM), tur) => Some(Tuple4(tboxM, domainM, rangeM, tur))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(scalarDataProperties = unresolvable)))) {
        case (Success(ri), (tboxM, domainM, rangeM, tsdp)) =>
          val (ej, rsdp) = ri.factory.createScalarDataProperty(
            ri.context.extent,
            tboxM,
            domainM,
            rangeM,
            tsdp.name)

          if (!ej.lookupBoxStatements(tboxM).contains(rsdp))
            Failure(new IllegalArgumentException(s"ScalarDataProperty not in extent: $rsdp"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tsdp.uuid)).contains(rsdp))
            Failure(new IllegalArgumentException(s"ScalarDataProperty: $tsdp vs. $rsdp"))
          else
            Success(ri.copy(context = ri.context.copy(extent = ej)))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapStructuredDataProperties
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.structuredDataProperties
        .map { tsdp =>
          ( UUID.fromString(tsdp.tboxUUID),
            UUID.fromString(tsdp.domainUUID),
            UUID.fromString(tsdp.rangeUUID) ) -> tsdp
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, domainUUID, rangeUUID), tsdp) = tuple
      tboxM = r.context.extent.lookupTerminologyBox(tboxUUID)
      domainM = r.context.extent.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(e: api.Structure) => Some(e)
        case _ => None
      }
      rangeM = r.context.extent.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(dr: api.Structure) => Some(dr)
        case _ => None
      }
    } yield (tboxM, domainM, rangeM, tsdp)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(domainM), Some(rangeM), tur) => Some(Tuple4(tboxM, domainM, rangeM, tur))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(structuredDataProperties = unresolvable)))) {
        case (Success(ri), (tboxM, domainM, rangeM, tsdp)) =>
          val (ej, rsdp) = ri.factory.createStructuredDataProperty(
            ri.context.extent,
            tboxM,
            domainM,
            rangeM,
            tsdp.name)

          if (!ej.lookupBoxStatements(tboxM).contains(rsdp))
            Failure(new IllegalArgumentException(s"StructuredDataProperty not in extent: $rsdp"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tsdp.uuid)).contains(rsdp))
            Failure(new IllegalArgumentException(s"StructuredDataProperty: $tsdp vs. $rsdp"))
          else
            Success(ri.copy(context = ri.context.copy(extent = ej)))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapScalarOneOfLiteralAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.scalarOneOfLiteralAxioms
        .map { tsool =>
          ( UUID.fromString(tsool.tboxUUID),
            UUID.fromString(tsool.axiomUUID) ) -> tsool
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, axiomUUID), tsool) = tuple
      tboxM = r.context.extent.lookupTerminologyBox(tboxUUID)
      axiomM = r.context.extent.lookupTerminologyBoxStatement(axiomUUID) match {
        case Some(e: api.ScalarOneOfRestriction) => Some(e)
        case _ => None
      }
    } yield (tboxM, axiomM, tsool)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty).map(_._3)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(axiomM), tsool) => Some(Tuple3(tboxM, axiomM, tsool))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(scalarOneOfLiteralAxioms = unresolvable)))) {
        case (Success(ri), (tboxM, axiomM, tsool)) =>
          val (ej, rsool) = ri.factory.createScalarOneOfLiteralAxiom(
            ri.context.extent,
            tboxM,
            axiomM,
            tsool.value)

          if (!ej.lookupBoxStatements(tboxM).contains(rsool))
            Failure(new IllegalArgumentException(s"ScalarOneOfLiteralAxiom not in extent: $rsool"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tsool.uuid)).contains(rsool))
            Failure(new IllegalArgumentException(s"ScalarOneOfLiteralAxiom: $tsool vs. $rsool"))
          else
            Success(ri.copy(context = ri.context.copy(extent = ej)))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapEntityExistentialRestrictionAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.entityExistentialRestrictionAxioms
        .map { tra =>
          ( UUID.fromString(tra.tboxUUID),
            UUID.fromString(tra.restrictedDomainUUID),
            UUID.fromString(tra.restrictedRangeUUID),
            UUID.fromString(tra.restrictedRelationUUID) ) -> tra
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, domainUUID, rangeUUID, relationUUID), tra) = tuple
      tboxM = r.context.extent.lookupTerminologyBox(tboxUUID)
      domainM = r.context.extent.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      rangeM = r.context.extent.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      relM = r.context.extent.lookupTerminologyBoxStatement(relationUUID) match {
        case Some(e: api.EntityRelationship) => Some(e)
        case _ => None
      }
    } yield (tboxM, domainM, rangeM, relM, tra)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty || tuple._4.isEmpty).map(_._5)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(domainM), Some(rangeM), Some(relM), tra) => Some(Tuple5(tboxM, domainM, rangeM, relM, tra))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(entityExistentialRestrictionAxioms = unresolvable)))) {
        case (Success(ri), (tboxM, domainM, rangeM, relM, tra)) =>
          val (ej, rra) = ri.factory.createEntityExistentialRestrictionAxiom(
            ri.context.extent,
            tboxM,
            relM,
            domainM,
            rangeM)

          if (!ej.lookupBoxStatements(tboxM).contains(rra))
            Failure(new IllegalArgumentException(s"EntityExistentialRestrictionAxiom not in extent: $rra"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tra.uuid)).contains(rra))
            Failure(new IllegalArgumentException(s"EntityExistentialRestrictionAxiom: $tra vs. $rra"))
          else
            Success(ri.copy(context = ri.context.copy(extent = ej)))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapEntityUniversalRestrictionAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.entityUniversalRestrictionAxioms
        .map { tra =>
          ( UUID.fromString(tra.tboxUUID),
            UUID.fromString(tra.restrictedDomainUUID),
            UUID.fromString(tra.restrictedRangeUUID),
            UUID.fromString(tra.restrictedRelationUUID) ) -> tra
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, domainUUID, rangeUUID, relationUUID), tra) = tuple
      tboxM = r.context.extent.lookupTerminologyBox(tboxUUID)
      domainM = r.context.extent.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      rangeM = r.context.extent.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      relM = r.context.extent.lookupTerminologyBoxStatement(relationUUID) match {
        case Some(e: api.EntityRelationship) => Some(e)
        case _ => None
      }
    } yield (tboxM, domainM, rangeM, relM, tra)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty || tuple._4.isEmpty).map(_._5)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(domainM), Some(rangeM), Some(relM), tra) => Some(Tuple5(tboxM, domainM, rangeM, relM, tra))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(entityUniversalRestrictionAxioms = unresolvable)))) {
        case (Success(ri), (tboxM, domainM, rangeM, relM, tra)) =>
          val (ej, rra) = ri.factory.createEntityUniversalRestrictionAxiom(
            ri.context.extent,
            tboxM,
            relM,
            domainM,
            rangeM)

          if (!ej.lookupBoxStatements(tboxM).contains(rra))
            Failure(new IllegalArgumentException(s"EntityUniversalRestrictionAxiom not in extent: $rra"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tra.uuid)).contains(rra))
            Failure(new IllegalArgumentException(s"EntityUniversalRestrictionAxiom: $tra vs. $rra"))
          else
            Success(ri.copy(context = ri.context.copy(extent = ej)))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapEntityScalarDataPropertyExistentialRestrictionAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.entityScalarDataPropertyExistentialRestrictionAxioms
        .map { tra =>
          ( UUID.fromString(tra.tboxUUID),
            UUID.fromString(tra.restrictedEntityUUID),
            UUID.fromString(tra.scalarPropertyUUID),
            UUID.fromString(tra.scalarRestrictionUUID) ) -> tra
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, domainUUID, relationUUID, rangeUUID), tra) = tuple
      tboxM = r.context.extent.lookupTerminologyBox(tboxUUID)
      domainM = r.context.extent.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      rangeM = r.context.extent.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(e: api.DataRange) => Some(e)
        case _ => None
      }
      relM = r.context.extent.lookupTerminologyBoxStatement(relationUUID) match {
        case Some(e: api.EntityScalarDataProperty) => Some(e)
        case _ => None
      }
    } yield (tboxM, domainM, rangeM, relM, tra)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty || tuple._4.isEmpty).map(_._5)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(domainM), Some(rangeM), Some(relM), tra) => Some(Tuple5(tboxM, domainM, rangeM, relM, tra))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(entityScalarDataPropertyExistentialRestrictionAxioms = unresolvable)))) {
        case (Success(ri), (tboxM, domainM, rangeM, relM, tra)) =>
          val (ej, rra) = ri.factory.createEntityScalarDataPropertyExistentialRestrictionAxiom(
            ri.context.extent,
            tboxM,
            domainM,
            relM,
            rangeM)

          if (!ej.lookupBoxStatements(tboxM).contains(rra))
            Failure(new IllegalArgumentException(s"EntityScalarDataPropertyExistentialRestrictionAxiom not in extent: $rra"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tra.uuid)).contains(rra))
            Failure(new IllegalArgumentException(s"EntityScalarDataPropertyExistentialRestrictionAxiom: $tra vs. $rra"))
          else
            Success(ri.copy(context = ri.context.copy(extent = ej)))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapEntityScalarDataPropertyUniversalRestrictionAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.entityScalarDataPropertyUniversalRestrictionAxioms
        .map { tra =>
          ( UUID.fromString(tra.tboxUUID),
            UUID.fromString(tra.restrictedEntityUUID),
            UUID.fromString(tra.scalarPropertyUUID),
            UUID.fromString(tra.scalarRestrictionUUID) ) -> tra
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, domainUUID, relationUUID, rangeUUID), tra) = tuple
      tboxM = r.context.extent.lookupTerminologyBox(tboxUUID)
      domainM = r.context.extent.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      rangeM = r.context.extent.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(e: api.DataRange) => Some(e)
        case _ => None
      }
      relM = r.context.extent.lookupTerminologyBoxStatement(relationUUID) match {
        case Some(e: api.EntityScalarDataProperty) => Some(e)
        case _ => None
      }
    } yield (tboxM, domainM, rangeM, relM, tra)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty || tuple._4.isEmpty).map(_._5)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(domainM), Some(rangeM), Some(relM), tra) => Some(Tuple5(tboxM, domainM, rangeM, relM, tra))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(entityScalarDataPropertyUniversalRestrictionAxioms = unresolvable)))) {
        case (Success(ri), (tboxM, domainM, rangeM, relM, tra)) =>
          val (ej, rra) = ri.factory.createEntityScalarDataPropertyUniversalRestrictionAxiom(
            ri.context.extent,
            tboxM,
            domainM,
            relM,
            rangeM)

          if (!ej.lookupBoxStatements(tboxM).contains(rra))
            Failure(new IllegalArgumentException(s"EntityScalarDataPropertyUniversalRestrictionAxiom not in extent: $rra"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tra.uuid)).contains(rra))
            Failure(new IllegalArgumentException(s"EntityScalarDataPropertyUniversalRestrictionAxiom: $tra vs. $rra"))
          else
            Success(ri.copy(context = ri.context.copy(extent = ej)))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapEntityScalarDataPropertyParticularRestrictionAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.entityScalarDataPropertyParticularRestrictionAxioms
        .map { tra =>
          ( UUID.fromString(tra.tboxUUID),
            UUID.fromString(tra.restrictedEntityUUID),
            UUID.fromString(tra.scalarPropertyUUID) ) -> tra
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, domainUUID, relationUUID), tra) = tuple
      tboxM = r.context.extent.lookupTerminologyBox(tboxUUID)
      domainM = r.context.extent.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      relM = r.context.extent.lookupTerminologyBoxStatement(relationUUID) match {
        case Some(e: api.EntityScalarDataProperty) => Some(e)
        case _ => None
      }
    } yield (tboxM, domainM, relM, tra)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(domainM), Some(relM), tra) => Some(Tuple4(tboxM, domainM, relM, tra))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(entityScalarDataPropertyParticularRestrictionAxioms = unresolvable)))) {
        case (Success(ri), (tboxM, domainM, relM, tra)) =>
          val (ej, rra) = ri.factory.createEntityScalarDataPropertyParticularRestrictionAxiom(
            ri.context.extent,
            tboxM,
            domainM,
            relM,
            tra.literalValue)

          if (!ej.lookupBoxStatements(tboxM).contains(rra))
            Failure(new IllegalArgumentException(s"EntityScalarDataPropertyParticularRestrictionAxiom not in extent: $rra"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tra.uuid)).contains(rra))
            Failure(new IllegalArgumentException(s"EntityScalarDataPropertyParticularRestrictionAxiom: $tra vs. $rra"))
          else
            Success(ri.copy(context = ri.context.copy(extent = ej)))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapAspectSpecializationAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.aspectSpecializationAxioms
        .map { tax =>
          ( UUID.fromString(tax.tboxUUID),
            UUID.fromString(tax.superAspectUUID),
            UUID.fromString(tax.subEntityUUID) ) -> tax
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, superUUID, subUUID), tax) = tuple
      tboxM = r.context.extent.lookupTerminologyBox(tboxUUID)
      superM = r.context.extent.lookupTerminologyBoxStatement(superUUID) match {
        case Some(e: api.Aspect) => Some(e)
        case _ => None
      }
      subM = r.context.extent.lookupTerminologyBoxStatement(subUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
    } yield (tboxM, superM, subM, tax)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(superM), Some(subM), tax) => Some(Tuple4(tboxM, superM, subM, tax))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(aspectSpecializationAxioms = unresolvable)))) {
        case (Success(ri), (tboxM, superM, subM, tax)) =>
          val (ej, rax) = ri.factory.createAspectSpecializationAxiom(
            ri.context.extent,
            tboxM,
            superM,
            subM)

          if (!ej.lookupBoxStatements(tboxM).contains(rax))
            Failure(new IllegalArgumentException(s"AspectSpecializationAxiom not in extent: $rax"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tax.uuid)).contains(rax))
            Failure(new IllegalArgumentException(s"AspectSpecializationAxiom: $tax vs. $rax"))
          else
            Success(ri.copy(context = ri.context.copy(extent = ej)))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapConceptSpecializationAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.conceptSpecializationAxioms
        .map { tax =>
          ( UUID.fromString(tax.tboxUUID),
            UUID.fromString(tax.superConceptUUID),
            UUID.fromString(tax.subConceptUUID) ) -> tax
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, superUUID, subUUID), tax) = tuple
      tboxM = r.context.extent.lookupTerminologyBox(tboxUUID)
      superM = r.context.extent.lookupTerminologyBoxStatement(superUUID) match {
        case Some(e: api.Concept) => Some(e)
        case _ => None
      }
      subM = r.context.extent.lookupTerminologyBoxStatement(subUUID) match {
        case Some(e: api.Concept) => Some(e)
        case _ => None
      }
    } yield (tboxM, superM, subM, tax)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(superM), Some(subM), tax) => Some(Tuple4(tboxM, superM, subM, tax))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(conceptSpecializationAxioms = unresolvable)))) {
        case (Success(ri), (tboxM, superM, subM, tax)) =>
          val (ej, rax) = ri.factory.createConceptSpecializationAxiom(
            ri.context.extent,
            tboxM,
            superM,
            subM)

          if (!ej.lookupBoxStatements(tboxM).contains(rax))
            Failure(new IllegalArgumentException(s"ConceptSpecializationAxiom not in extent: $rax"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tax.uuid)).contains(rax))
            Failure(new IllegalArgumentException(s"ConceptSpecializationAxiom: $tax vs. $rax"))
          else
            Success(ri.copy(context = ri.context.copy(extent = ej)))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapReifiedRelationshipSpecializationAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.reifiedRelationshipSpecializationAxioms
        .map { tax =>
          ( UUID.fromString(tax.tboxUUID),
            UUID.fromString(tax.superRelationshipUUID),
            UUID.fromString(tax.subRelationshipUUID) ) -> tax
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, superUUID, subUUID), tax) = tuple
      tboxM = r.context.extent.lookupTerminologyBox(tboxUUID)
      superM = r.context.extent.lookupTerminologyBoxStatement(superUUID) match {
        case Some(e: api.ReifiedRelationship) => Some(e)
        case _ => None
      }
      subM = r.context.extent.lookupTerminologyBoxStatement(subUUID) match {
        case Some(e: api.ReifiedRelationship) => Some(e)
        case _ => None
      }
    } yield (tboxM, superM, subM, tax)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(superM), Some(subM), tax) => Some(Tuple4(tboxM, superM, subM, tax))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(reifiedRelationshipSpecializationAxioms = unresolvable)))) {
        case (Success(ri), (tboxM, superM, subM, tax)) =>
          val (ej, rax) = ri.factory.createReifiedRelationshipSpecializationAxiom(
            ri.context.extent,
            tboxM,
            superM,
            subM)

          if (!ej.lookupBoxStatements(tboxM).contains(rax))
            Failure(new IllegalArgumentException(s"ReifiedRelationshipSpecializationAxiom not in extent: $rax"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tax.uuid)).contains(rax))
            Failure(new IllegalArgumentException(s"ReifiedRelationshipSpecializationAxiom: $tax vs. $rax"))
          else
            Success(ri.copy(context = ri.context.copy(extent = ej)))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapRootConceptTaxonomyAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.rootConceptTaxonomyAxioms
        .map { tax =>
          ( UUID.fromString(tax.bundleUUID),
            UUID.fromString(tax.rootUUID) ) -> tax
        }

    val byTBox = for {
      tuple <- byUUID
      ((bundleUUID, rootUUID), tax) = tuple
      bundleM = r.context.extent.lookupBundle(bundleUUID)
      rootM = r.context.extent.lookupTerminologyBoxStatement(rootUUID) match {
        case Some(e: api.Concept) => Some(e)
        case _ => None
      }
    } yield (bundleM, rootM, tax)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty).map(_._3)
    val resolvable = byTBox.flatMap {
      case (Some(bundleM), Some(rootM), tax) => Some(Tuple3(bundleM, rootM, tax))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(rootConceptTaxonomyAxioms = unresolvable)))) {
        case (Success(ri), (bundleM, rootM, tax)) =>
          val (ej, rax) = ri.factory.createRootConceptTaxonomyAxiom(ri.context.extent, bundleM, rootM)

          if (!ej.lookupBundleStatements(bundleM).contains(rax))
            Failure(new IllegalArgumentException(s"RootConceptTaxonomyAxiom not in extent: $rax"))
          else if (!ej.lookupTerminologyBundleStatement(UUID.fromString(tax.uuid)).contains(rax))
            Failure(new IllegalArgumentException(s"RootConceptTaxonomyAxiom: $tax vs. $rax"))
          else
            mapDisjunctions(ri.copy(context = ri.context.copy(extent = ej)), rax, tax.uuid)
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapDisjunctions
  (r: OMLTablesResolver, conceptTreeDisjunctParent: api.ConceptTreeDisjunction, conceptTreeDisjunctUUID: tables.UUID)
  : Try[OMLTablesResolver]
  = {
    val as = r.queue.anonymousConceptTaxonomyAxioms.partition(_.disjointTaxonomyParentUUID == conceptTreeDisjunctUUID)
    val ss = r.queue.specificDisjointConceptAxioms.partition(_.disjointTaxonomyParentUUID == conceptTreeDisjunctUUID)

    val r1 = Try(r.copy(queue = r.queue.copy(
      anonymousConceptTaxonomyAxioms = as._2,
      specificDisjointConceptAxioms = ss._2)))

    val r2 = as._1.foldLeft[Try[OMLTablesResolver]](r1) {
        case (Success(ri), tax) =>
          val (ej, rax) = ri.factory.createAnonymousConceptTaxonomyAxiom(ri.context.extent, conceptTreeDisjunctParent, tax.name)

          if (!ej.conceptTreeDisjunctionOfDisjointUnionOfConceptsAxiom.get(rax).contains(conceptTreeDisjunctParent))
            Failure(new IllegalArgumentException(s"AnonymousConceptTaxonomyAxiom not in conceptTreeDisjunctionOfDisjointUnionOfConceptsAxiom: $rax"))
          else if (!ej.lookupDisjunctions(conceptTreeDisjunctParent).contains(rax))
            Failure(new IllegalArgumentException(s"AnonymousConceptTaxonomyAxiom: not in lookupDisjunctions: $tax vs. $rax"))
          else
            Success(ri.copy(context = ri.context.copy(extent = ej)))
        case (Failure(f), _) =>
          Failure(f)
      }

    val r3 = ss._1.foldLeft[Try[OMLTablesResolver]](r2) {
      case (Success(ri), tax) =>
        ri.context.extent.lookupTerminologyBoxStatement(UUID.fromString(tax.disjointLeafUUID)) match {
          case Some(leaf: api.Concept) =>
            val (ej, rax) = ri.factory.createSpecificDisjointConceptAxiom(ri.context.extent, conceptTreeDisjunctParent, leaf)

            if (!ej.conceptTreeDisjunctionOfDisjointUnionOfConceptsAxiom.get(rax).contains(conceptTreeDisjunctParent))
              Failure(new IllegalArgumentException(s"SpecificDisjointConceptAxiom not in conceptTreeDisjunctionOfDisjointUnionOfConceptsAxiom: $rax"))
            else if (!ej.lookupDisjunctions(conceptTreeDisjunctParent).contains(rax))
              Failure(new IllegalArgumentException(s"SpecificDisjointConceptAxiom: not in lookupDisjunctions: $tax vs. $rax"))
            else
              Success(ri.copy(context = ri.context.copy(extent = ej)))
          case _ =>
            Failure(new IllegalArgumentException(s"SpecificDisjointConceptAxiom: leaf concept not found: ${tax.disjointLeafUUID}"))
        }
      case (Failure(f), _) =>
        Failure(f)
    }

    r3
  }

  def mapAnnotations
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {

    val byUUID =
      r.queue.annotations
        .map { case (tap, taes) =>
          (r.context.extent.lookupAnnotationProperty(UUID.fromString(tap.uuid)) -> tap) ->
            taes.map { tae =>
              (
                r.context.extent.lookupModule(UUID.fromString(tae.moduleUUID)),
                r.context.extent.lookupElement(UUID.fromString(tae.subjectUUID))
              ) -> tae
            }
        }

    val unresolvable = byUUID.flatMap {
      case ((None, tap), r2taes) =>
        Some(tap -> r2taes.map(_._2))
      case ((Some(rap), tap), r2taes) =>
        val utaes = r2taes.filter(t => t._1._1.isEmpty || t._1._2.isEmpty).map(_._2)
        if (utaes.nonEmpty)
          Some(tap -> utaes)
        else
          None
    }

    val resolvable = byUUID.flatMap {
      case ((None, tap), r2taes) =>
        None
      case ((Some(rap), tap), r2taes) =>
        val rtaes = r2taes.flatMap {
          case ((Some(m), Some(s)), tae) =>
            Some(Tuple3(m, s, tae))
          case _ =>
            None
        }
        if (rtaes.nonEmpty)
          Some(Tuple3(rap, tap, rtaes))
        else
          None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(annotations = unresolvable)))) {
        case (Success(ri), (rap, _, rtaes)) =>
          val next
          : Try[OMLTablesResolver]
          = rtaes.foldLeft[Try[OMLTablesResolver]](Success(ri)) {
            case (Success(rj), (am, as, tae)) =>
              val (ej, ra) = rj.factory.createAnnotation(
                rj.context.extent,
                am, as, rap, tae.value)
              if (!ej.lookupAnnotations(am).exists { a => a.property == rap && a.subject == as && a.value == tae.value })
                Failure(new IllegalArgumentException(s"Annotation not in extent: $ra"))
              else
                Success(rj.copy(context = rj.context.copy(extent = ej)))
            case (Failure(f), _) =>
              Failure(f)
          }
          next
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

}
