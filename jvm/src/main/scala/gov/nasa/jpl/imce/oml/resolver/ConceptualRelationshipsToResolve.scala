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

import gov.nasa.jpl.imce.oml.tables

import scala.{Boolean, Int, None, Option, Some, StringContext, Tuple4}
import scala.collection.immutable.Seq
import scala.util.{Failure, Success, Try}

case class ConceptualRelationshipsToResolve(
                                             cardinalityRestrictedAspects: Seq[
                                               ConceptualRelationshipsToResolve.CardinalityRestrictedAspectInfo],
                                             cardinalityRestrictedConcepts: Seq[
                                               ConceptualRelationshipsToResolve.CardinalityRestrictedConceptInfo],
                                             cardinalityRestrictedReifiedRelationships: Seq[
                                               ConceptualRelationshipsToResolve.CardinalityRestrictedReifiedRelationshipInfo],
                                             reifiedRelationships: Seq[
                                               ConceptualRelationshipsToResolve.ReifiedRelationshipInfo],
                                             reifiedRelationshipRestrictions: Seq[
                                               ConceptualRelationshipsToResolve.ReifiedRelationshipRestrictionInfo],
                                             r: OMLTablesResolver) {
  def isEmpty: Boolean =
    cardinalityRestrictedAspects.isEmpty &&
      cardinalityRestrictedConcepts.isEmpty &&
      cardinalityRestrictedReifiedRelationships.isEmpty &&
      reifiedRelationships.isEmpty &&
      reifiedRelationshipRestrictions.isEmpty
}

object ConceptualRelationshipsToResolve {

  type CardinalityRestrictedAspectInfo =
    (api.taggedTypes.TerminologyBoxUUID,
     api.taggedTypes.RestrictableRelationshipUUID,
     Option[api.taggedTypes.EntityUUID],
     tables.CardinalityRestrictedAspect)

  type CardinalityRestrictedConceptInfo =
    (api.taggedTypes.TerminologyBoxUUID,
     api.taggedTypes.RestrictableRelationshipUUID,
     Option[api.taggedTypes.EntityUUID],
     tables.CardinalityRestrictedConcept)

  type CardinalityRestrictedReifiedRelationshipInfo =
    (api.taggedTypes.TerminologyBoxUUID,
     api.taggedTypes.RestrictableRelationshipUUID,
     Option[api.taggedTypes.EntityUUID],
     tables.CardinalityRestrictedReifiedRelationship)

  type ReifiedRelationshipInfo = (api.taggedTypes.TerminologyBoxUUID,
                                  api.taggedTypes.EntityUUID,
                                  api.taggedTypes.EntityUUID,
                                  tables.ReifiedRelationship)

  type ReifiedRelationshipRestrictionInfo =
    (api.taggedTypes.TerminologyBoxUUID,
     api.taggedTypes.EntityUUID,
     api.taggedTypes.EntityUUID,
     tables.ReifiedRelationshipRestriction)

  def resolve(s: ConceptualRelationshipsToResolve,
              progress: Int = 0): Try[OMLTablesResolver] =
    if (s.isEmpty)
      Success(s.r)
    else {
      import s._
      val ca_byTBox = for {
        tuple <- cardinalityRestrictedAspects
        (tboxUUID, relUUID, o_rangeUUID, ca) = tuple
        tboxM = r.lookupTerminologyBox(tboxUUID)
        relM = r.lookupRestrictableRelationship(relUUID) match {
          case Some(e: api.RestrictableRelationship) => Some(e)
          case _                                     => None
        }
        rangeM = o_rangeUUID.flatMap { rangeUUID =>
          r.lookupTerminologyBoxStatement(rangeUUID) match {
            case Some(e: api.Entity) => Some(e)
            case _                   => None
          }
        }
      } yield (tboxM, relM, rangeM, ca, tuple)

      val ca_unresolvable: Seq[
        ConceptualRelationshipsToResolve.CardinalityRestrictedAspectInfo] = ca_byTBox
        .filter(tuple =>
          tuple._1.isEmpty || tuple._2.isEmpty || (tuple._3.isEmpty && tuple._4.restrictedRangeUUID.nonEmpty))
        .map(_._5)
      val ca_resolvable = ca_byTBox.flatMap {
        case (Some(tboxM), Some(relM), rangeM, ca, _) =>
          if (ca.restrictedRangeUUID.isDefined) {
            if (rangeM.isDefined)
              Some(Tuple4(tboxM, relM, rangeM, ca))
            else
              None
          } else {
            Some(Tuple4(tboxM, relM, None, ca))
          }
        case _ => None
      }

      val cc_byTBox = for {
        tuple <- cardinalityRestrictedConcepts
        (tboxUUID, relUUID, o_rangeUUID, cc) = tuple
        tboxM = r.lookupTerminologyBox(tboxUUID)
        relM = r.lookupRestrictableRelationship(relUUID) match {
          case Some(e: api.RestrictableRelationship) => Some(e)
          case _                                     => None
        }
        rangeM = o_rangeUUID.flatMap { rangeUUID =>
          r.lookupTerminologyBoxStatement(rangeUUID) match {
            case Some(e: api.Entity) => Some(e)
            case _                   => None
          }
        }
      } yield (tboxM, relM, rangeM, cc, tuple)

      val cc_unresolvable: Seq[
        ConceptualRelationshipsToResolve.CardinalityRestrictedConceptInfo] = cc_byTBox
        .filter(tuple =>
          tuple._1.isEmpty || tuple._2.isEmpty || (tuple._3.isEmpty && tuple._4.restrictedRangeUUID.nonEmpty))
        .map(_._5)
      val cc_resolvable = cc_byTBox.flatMap {
        case (Some(tboxM), Some(relM), rangeM, cc, _) =>
          if (cc.restrictedRangeUUID.isDefined) {
            if (rangeM.isDefined)
              Some(Tuple4(tboxM, relM, rangeM, cc))
            else
              None
          } else {
            Some(Tuple4(tboxM, relM, None, cc))
          }
        case _ => None
      }

      val crr_byTBox = for {
        tuple <- cardinalityRestrictedReifiedRelationships
        (tboxUUID, relUUID, o_rangeUUID, crr) = tuple
        tboxM = r.lookupTerminologyBox(tboxUUID)
        relM = r.lookupRestrictableRelationship(relUUID) match {
          case Some(e: api.RestrictableRelationship) => Some(e)
          case _                                     => None
        }
        rangeM = o_rangeUUID.flatMap { rangeUUID =>
          r.lookupTerminologyBoxStatement(rangeUUID) match {
            case Some(e: api.Entity) => Some(e)
            case _                   => None
          }
        }
      } yield (tboxM, relM, rangeM, crr, tuple)

      val crr_unresolvable
        : Seq[ConceptualRelationshipsToResolve.CardinalityRestrictedReifiedRelationshipInfo] = crr_byTBox
        .filter(tuple =>
          tuple._1.isEmpty || tuple._2.isEmpty || (tuple._3.isEmpty && tuple._4.restrictedRangeUUID.nonEmpty))
        .map(_._5)
      val crr_resolvable = crr_byTBox.flatMap {
        case (Some(tboxM), Some(relM), rangeM, cc, _) =>
          if (cc.restrictedRangeUUID.isDefined) {
            if (rangeM.isDefined)
              Some(Tuple4(tboxM, relM, rangeM, cc))
            else
              None
          } else {
            Some(Tuple4(tboxM, relM, None, cc))
          }
        case _ => None
      }

      val rr_byTBox = for {
        tuple <- reifiedRelationships
        (tboxUUID, sourceUUID, targetUUID, trr) = tuple
        tboxM = r.lookupTerminologyBox(tboxUUID)
        sourceM = r.lookupTerminologyBoxStatement(sourceUUID) match {
          case Some(e: api.Entity) => Some(e)
          case _                   => None
        }
        targetM = r.lookupTerminologyBoxStatement(targetUUID) match {
          case Some(e: api.Entity) => Some(e)
          case _                   => None
        }
      } yield (tboxM, sourceM, targetM, trr, tuple)

      val rr_unresolvable
        : Seq[ConceptualRelationshipsToResolve.ReifiedRelationshipInfo] =
        rr_byTBox
          .filter(tuple =>
            tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty)
          .map(_._5)
      val rr_resolvable = rr_byTBox.flatMap {
        case (Some(tboxM), Some(sourceM), Some(targetM), trr, _) =>
          Some(Tuple4(tboxM, sourceM, targetM, trr))
        case _ =>
          None
      }

      val pr_byTBox = for {
        tuple <- reifiedRelationshipRestrictions
        (tboxUUID, sourceUUID, targetUUID, trr) = tuple
        tboxM = r.lookupTerminologyBox(tboxUUID)
        sourceM = r.lookupTerminologyBoxStatement(sourceUUID) match {
          case Some(e: api.Entity) => Some(e)
          case _                   => None
        }
        targetM = r.lookupTerminologyBoxStatement(targetUUID) match {
          case Some(e: api.Entity) => Some(e)
          case _                   => None
        }
      } yield (tboxM, sourceM, targetM, trr, tuple)

      val pr_unresolvable: Seq[
        ConceptualRelationshipsToResolve.ReifiedRelationshipRestrictionInfo] =
        pr_byTBox
          .filter(tuple =>
            tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty)
          .map(_._5)
      val pr_resolvable = pr_byTBox.flatMap {
        case (Some(tboxM), Some(sourceM), Some(targetM), trr, _) =>
          Some(Tuple4(tboxM, sourceM, targetM, trr))
        case _ =>
          None
      }

      val sa =
        ca_resolvable.foldLeft[Try[OMLTablesResolver]](
          Success(r.copy(queue = r.queue.copy(
            cardinalityRestrictedAspects = ca_unresolvable.map(_._4))))) {
          case (Success(ri), (tboxM, relM, rangeM, ca)) =>
            val (ej, ra) = ri.factory.createCardinalityRestrictedAspect(
              extent = ri.context,
              tbox = tboxM,
              restrictedRange = rangeM,
              name = ca.name,
              restrictedCardinality = ca.restrictedCardinality,
              restrictedRelationship = relM,
              restrictionKind = ca.restrictionKind
            )

            if (!ej.lookupBoxStatements(tboxM).contains(ra))
              Failure(
                new IllegalArgumentException(
                  s"CardinalityRestrictedAspect not in extent: $ra"))
            else if (!ej.lookupTerminologyBoxStatement(ra.uuid).contains(ra))
              Failure(
                new IllegalArgumentException(
                  s"CardinalityRestrictedAspect: $ca vs. $ra"))
            else
              Success(ri.copy(context = ej))
          case (Failure(f), _) =>
            Failure(f)
        }

      val sc =
        cc_resolvable.foldLeft[Try[OMLTablesResolver]](sa.map { s =>
          s.copy(
            queue = s.queue.copy(
              cardinalityRestrictedConcepts = cc_unresolvable.map(_._4)))
        }) {
          case (Success(ri), (tboxM, relM, rangeM, cc)) =>
            val (ej, rc) = ri.factory.createCardinalityRestrictedConcept(
              extent = ri.context,
              tbox = tboxM,
              restrictedRange = rangeM,
              name = cc.name,
              restrictedCardinality = cc.restrictedCardinality,
              restrictedRelationship = relM,
              restrictionKind = cc.restrictionKind
            )

            if (!ej.lookupBoxStatements(tboxM).contains(rc))
              Failure(
                new IllegalArgumentException(
                  s"CardinalityRestrictedConcept not in extent: $rc"))
            else if (!ej.lookupTerminologyBoxStatement(rc.uuid).contains(rc))
              Failure(
                new IllegalArgumentException(
                  s"CardinalityRestrictedConcept: $cc vs. $rc"))
            else
              Success(ri.copy(context = ej))
          case (Failure(f), _) =>
            Failure(f)
        }

      val sr =
        crr_resolvable.foldLeft[Try[OMLTablesResolver]](sc.map { s =>
          s.copy(
            queue = s.queue.copy(cardinalityRestrictedReifiedRelationships =
              crr_unresolvable.map(_._4)))
        }) {
          case (Success(ri), (tboxM, relM, rangeM, crr)) =>
            val (ej, rrr) =
              ri.factory.createCardinalityRestrictedReifiedRelationship(
                extent = ri.context,
                tbox = tboxM,
                restrictedRange = rangeM,
                name = crr.name,
                restrictedCardinality = crr.restrictedCardinality,
                restrictedRelationship = relM,
                restrictionKind = crr.restrictionKind
              )

            if (!ej.lookupBoxStatements(tboxM).contains(rrr))
              Failure(
                new IllegalArgumentException(
                  s"CardinalityRestrictedConcept not in extent: $rrr"))
            else if (!ej.lookupTerminologyBoxStatement(rrr.uuid).contains(rrr))
              Failure(
                new IllegalArgumentException(
                  s"CardinalityRestrictedConcept: $crr vs. $rrr"))
            else
              Success(ri.copy(context = ej))
          case (Failure(f), _) =>
            Failure(f)
        }

      val s1 =
        rr_resolvable.foldLeft[Try[OMLTablesResolver]](sr.map { s =>
          s.copy(
            queue = s.queue.copy(
              reifiedRelationships = rr_unresolvable.map(_._4),
              reifiedRelationshipRestrictions = pr_unresolvable.map(_._4)))
        }) {
          case (Success(ri), (tboxM, sourceM, targetM, trr)) =>
            val (ej, rrr) = ri.factory.createReifiedRelationship(
              ri.context,
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
              trr.name
            )

            if (!ej.lookupBoxStatements(tboxM).contains(rrr))
              Failure(
                new IllegalArgumentException(
                  s"ReifiedRelationship not in extent: $rrr"))
            else if (!ej
                       .lookupTerminologyBoxStatement(
                         api.taggedTypes.fromUUIDString(trr.uuid))
                       .contains(rrr))
              Failure(
                new IllegalArgumentException(
                  s"ReifiedRelationship: $trr vs. $rrr"))
            else
              Success(ri.copy(context = ej))
          case (Failure(f), _) =>
            Failure(f)
        }

      val s2 =
        pr_resolvable.foldLeft[Try[OMLTablesResolver]](s1) {
          case (Success(ri), (tboxM, sourceM, targetM, trr)) =>
            val (ej, rrr) =
              ri.factory.createReifiedRelationshipRestriction(ri.context,
                                                              tboxM,
                                                              sourceM,
                                                              targetM,
                                                              trr.name)

            if (!ej.lookupBoxStatements(tboxM).contains(rrr))
              Failure(
                new IllegalArgumentException(
                  s"ReifiedRelationshipRestriction not in extent: $rrr"))
            else if (!ej
                       .lookupTerminologyBoxStatement(
                         api.taggedTypes.fromUUIDString(trr.uuid))
                       .contains(rrr))
              Failure(
                new IllegalArgumentException(
                  s"ReifiedRelationshipRestriction: $trr vs. $rrr"))
            else
              Success(ri.copy(context = ej))
          case (Failure(f), _) =>
            Failure(f)
        }

      val inc = ca_resolvable.nonEmpty || cc_resolvable.nonEmpty || crr_resolvable.nonEmpty || rr_resolvable.nonEmpty || pr_resolvable.nonEmpty
      val more = ca_unresolvable.size + cc_unresolvable.size + crr_unresolvable.size + rr_unresolvable.size + pr_unresolvable.size

      if (0 == more)
        s2
      else {
        if (inc) {
          if (progress < 10) {
            s2.flatMap {
              nr =>
              val next = s.copy(
                cardinalityRestrictedAspects = ca_unresolvable,
                cardinalityRestrictedConcepts = cc_unresolvable,
                cardinalityRestrictedReifiedRelationships = crr_unresolvable,
                reifiedRelationships = rr_unresolvable,
                reifiedRelationshipRestrictions = pr_unresolvable,
                r = nr
              )
              resolve(next, progress + 1)
            }
          } else
            Failure(
              new IllegalArgumentException(
                s"Too many iterations ($progress) -- $s2"))
        } else
          Failure(new IllegalArgumentException(s"$more unresolved in $s2"))
      }
    }
}
