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

import scala.{None, Some, StringContext, Tuple4}
import scala.collection.immutable.Seq
import scala.util.{Failure, Success, Try}

object ConceptualRelationshipsToResolve {

  type ReifiedRelationshipInfo
  = (api.taggedTypes.TerminologyBoxUUID,
     api.taggedTypes.EntityUUID,
     api.taggedTypes.EntityUUID,
     tables.ReifiedRelationship)

  type ReifiedRelationshipRestrictionInfo
  = (api.taggedTypes.TerminologyBoxUUID,
     api.taggedTypes.EntityUUID,
     api.taggedTypes.EntityUUID,
     tables.ReifiedRelationshipRestriction)

}

case class ConceptualRelationshipsToResolve
(reifiedRelationships: Seq[ConceptualRelationshipsToResolve.ReifiedRelationshipInfo],
 reifiedRelationshipRestrictions: Seq[ConceptualRelationshipsToResolve.ReifiedRelationshipRestrictionInfo],
 r: OMLTablesResolver ) {

  def resolve(): Try[OMLTablesResolver]
  = if (reifiedRelationships.isEmpty && reifiedRelationshipRestrictions.isEmpty)
    Success(r)
  else {

    val rr_byTBox = for {
      tuple <- reifiedRelationships
      (tboxUUID, sourceUUID, targetUUID, trr) = tuple
      tboxM = r.lookupTerminologyBox(tboxUUID)
      sourceM = r.lookupTerminologyBoxStatement(sourceUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      targetM = r.lookupTerminologyBoxStatement(targetUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
    } yield (tboxM, sourceM, targetM, trr)

    val pr_byTBox = for {
      tuple <- reifiedRelationshipRestrictions
      (tboxUUID, sourceUUID, targetUUID, trr) = tuple
      tboxM = r.lookupTerminologyBox(tboxUUID)
      sourceM = r.lookupTerminologyBoxStatement(sourceUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      targetM = r.lookupTerminologyBoxStatement(targetUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
    } yield (tboxM, sourceM, targetM, trr)

    val rr_unresolvable = rr_byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val rr_resolvable = rr_byTBox.flatMap {
      case (Some(tboxM), Some(sourceM), Some(targetM), trr) => Some(Tuple4(tboxM, sourceM, targetM, trr))
      case _ => None
    }

    val pr_unresolvable = pr_byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val pr_resolvable = pr_byTBox.flatMap {
      case (Some(tboxM), Some(sourceM), Some(targetM), trr) => Some(Tuple4(tboxM, sourceM, targetM, trr))
      case _ => None
    }

    val s1 =
      rr_resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(
        reifiedRelationships = rr_unresolvable,
        reifiedRelationshipRestrictions = pr_unresolvable)))) {
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
            trr.name)

          if (!ej.lookupBoxStatements(tboxM).contains(rrr))
            Failure(new IllegalArgumentException(s"ReifiedRelationship not in extent: $rrr"))
          else if (!ej.lookupTerminologyBoxStatement(api.taggedTypes.fromUUIDString(trr.uuid)).contains(rrr))
            Failure(new IllegalArgumentException(s"ReifiedRelationship: $trr vs. $rrr"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }

    val s2 =
      pr_resolvable.foldLeft[Try[OMLTablesResolver]](s1) {
        case (Success(ri), (tboxM, sourceM, targetM, trr)) =>
          val (ej, rrr) = ri.factory.createReifiedRelationshipRestriction(
            ri.context,
            tboxM,
            sourceM,
            targetM,
            trr.name)

          if (!ej.lookupBoxStatements(tboxM).contains(rrr))
            Failure(new IllegalArgumentException(s"ReifiedRelationshipRestriction not in extent: $rrr"))
          else if (!ej.lookupTerminologyBoxStatement(api.taggedTypes.fromUUIDString(trr.uuid)).contains(rrr))
            Failure(new IllegalArgumentException(s"ReifiedRelationshipRestriction: $trr vs. $rrr"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }

    s2
  }
}
