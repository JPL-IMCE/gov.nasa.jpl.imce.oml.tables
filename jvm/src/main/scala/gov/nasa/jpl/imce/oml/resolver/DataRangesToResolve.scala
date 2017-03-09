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

import java.util.UUID

import gov.nasa.jpl.imce.oml.specification._

import scala.{Boolean, StringContext, Tuple2, Tuple3}
import scala.collection.immutable.{Map, Seq, SortedSet, TreeSet}
import scala.util.{Failure, Success, Try}
import scala.Predef.ArrowAssoc

/**
  * Each field is a map from: Graph UUID to a map of (restricted range UUID, dataRange)
  * @param binaryScalarRestrictions
  * @param iriScalarRestrictions
  * @param numericScalarRestrictions
  * @param plainLiteralScalarRestrictions
  * @param scalarOneOfRestrictions
  * @param stringScalarRestrictions
  * @param timeScalarRestrictions
  */
case class DataRangesToResolve
( binaryScalarRestrictions: Map[UUID, Seq[(UUID, tables.BinaryScalarRestriction)]],
  iriScalarRestrictions: Map[UUID, Seq[(UUID, tables.IRIScalarRestriction)]],
  numericScalarRestrictions: Map[UUID, Seq[(UUID, tables.NumericScalarRestriction)]],
  plainLiteralScalarRestrictions: Map[UUID, Seq[(UUID, tables.PlainLiteralScalarRestriction)]],
  scalarOneOfRestrictions: Map[UUID, Seq[(UUID, tables.ScalarOneOfRestriction)]],
  stringScalarRestrictions: Map[UUID, Seq[(UUID, tables.StringScalarRestriction)]],
  timeScalarRestrictions: Map[UUID, Seq[(UUID, tables.TimeScalarRestriction)]] )

object DataRangesToResolve {

  import OMLOps._

  private def partitionRestrictableDataRanges[T]
  (r: OMLTablesResolver,
   tbox: api.TerminologyBox,
   drs: Seq[(UUID, T)],
   dr2restrictedDataRange: T => tables.UUID)
  : (Map[tables.UUID, api.DataRange], Seq[(UUID, T)], Seq[(UUID, T)])
  = {
    val restrictableDataRanges
    : Map[tables.UUID, api.DataRange]
    = r.context.g.outerNodeTraverser(r.context.g.get(tbox))
      .foldLeft[Map[tables.UUID, api.DataRange]](Map.empty)(_ ++ _.dataranges().map(dr => dr.uuid.toString -> dr))

    val (available, remaining) =
      drs
        .partition { case (_, dr) => restrictableDataRanges.contains(dr2restrictedDataRange(dr)) }
    Tuple3(restrictableDataRanges, available, remaining)
  }

  val empty = DataRangesToResolve(
    binaryScalarRestrictions = Map.empty,
    iriScalarRestrictions = Map.empty,
    numericScalarRestrictions = Map.empty,
    plainLiteralScalarRestrictions = Map.empty,
    scalarOneOfRestrictions = Map.empty,
    stringScalarRestrictions = Map.empty,
    timeScalarRestrictions = Map.empty)

  final def resolve(resolver: OMLTablesResolver, queue: DataRangesToResolve)
  : Try[(OMLTablesResolver, DataRangesToResolve)]
  = {

    val r0 = resolver
    val q0 = DataRangesToResolve.empty
    val f0 = false

    val resolved = for {
      step1 <- queue.binaryScalarRestrictions
        .foldLeft[Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]](Success(Tuple3(r0, q0, f0))) {
        case (Success(Tuple3(ri, qi, fi)), (guuid, drs)) =>
          val gi = ri.context.g
          val tbox = ri.context.tboxes(guuid)

          val (restrictableDataRanges, available, remaining) =
            partitionRestrictableDataRanges[tables.BinaryScalarRestriction](ri, tbox, drs, _.restrictedRangeUUID)

          val si
          : SortedSet[api.TerminologyBoxStatement]
          = available
            .foldLeft[SortedSet[api.TerminologyBoxStatement]](TreeSet.empty[api.TerminologyBoxStatement]) {
            case (acc, (ruuid, dr)) =>
              val x =
                ri.factory.createBinaryScalarRestriction(
                  tbox,
                  restrictableDataRanges(ruuid.toString),
                  dr.length,
                  dr.minLength,
                  dr.maxLength,
                  dr.name)
              if (x.uuid.toString != dr.uuid)
                throw new java.lang.IllegalArgumentException(s"DataRangteResolver.BinaryScalarRestriction UUID mismatch: read: $dr, created: $x")
              acc + x
          }

          TerminologyContext
            .replaceNode(ri.factory, gi, tbox, tbox.withBoxStatements(si))
            .map { gj =>
              Tuple3(
                ri.copy(context = TerminologyContext(ri.context.extent, gj)),
                qi.copy(binaryScalarRestrictions = qi.binaryScalarRestrictions + (guuid -> remaining)),
                fi || si.nonEmpty)
            }
        case (Failure(t), _) =>
          Failure(t)
      }

      step2 <- queue.iriScalarRestrictions
        .foldLeft[Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]](Success(step1)) {
        case (Success(Tuple3(ri, qi, fi)), (guuid, drs)) =>
          val gi = ri.context.g
          val tbox = ri.context.tboxes(guuid)

          val (restrictableDataRanges, available, remaining) =
            partitionRestrictableDataRanges[tables.IRIScalarRestriction](ri, tbox, drs, _.restrictedRangeUUID)

          val si
          : SortedSet[api.TerminologyBoxStatement]
          = available
              .foldLeft[SortedSet[api.TerminologyBoxStatement]](TreeSet.empty[api.TerminologyBoxStatement]) {
            case (acc, (ruuid, dr)) =>
              val x = ri.factory.createIRIScalarRestriction(
                  tbox,
                  restrictableDataRanges(ruuid.toString),
                  dr.length,
                  dr.minLength,
                  dr.maxLength,
                  dr.name,
                  dr.pattern)
              if (x.uuid.toString != dr.uuid)
                throw new java.lang.IllegalArgumentException(s"DataRangteResolver.IRIScalarRestriction UUID mismatch: read: $dr, created: $x")
              acc + x
          }

          TerminologyContext
            .replaceNode(ri.factory, gi, tbox, tbox.withBoxStatements(si))
            .map { gj =>
              Tuple3(
                ri.copy(context = TerminologyContext(ri.context.extent, gj)),
                qi.copy(iriScalarRestrictions = qi.iriScalarRestrictions + (guuid -> remaining)),
                fi || si.nonEmpty)
            }
        case (Failure(t), _) =>
          Failure(t)
      }

      step3 <- queue.numericScalarRestrictions
        .foldLeft[Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]](Success(step2)) {
        case (Success(Tuple3(ri, qi, fi)), (guuid, drs)) =>
          val gi = ri.context.g
          val tbox = ri.context.tboxes(guuid)

          val (restrictableDataRanges, available, remaining) =
            partitionRestrictableDataRanges[tables.NumericScalarRestriction](ri, tbox, drs, _.restrictedRangeUUID)

          val si
          : SortedSet[api.TerminologyBoxStatement]
          = available
            .foldLeft[SortedSet[api.TerminologyBoxStatement]](TreeSet.empty[api.TerminologyBoxStatement]) {
            case (acc, (ruuid, dr)) =>
              val x =
              ri.factory.createNumericScalarRestriction(
                tbox,
                restrictableDataRanges(ruuid.toString),
                dr.minExclusive,
                dr.minInclusive,
                dr.maxExclusive,
                dr.maxInclusive,
                dr.name)
              if (x.uuid.toString != dr.uuid)
                throw new java.lang.IllegalArgumentException(s"DataRangteResolver.NumericScalarRestriction UUID mismatch: read: $dr, created: $x")
              acc + x
            }

          TerminologyContext
            .replaceNode(ri.factory, gi, tbox, tbox.withBoxStatements(si))
            .map { gj =>
              Tuple3(
                ri.copy(context = TerminologyContext(ri.context.extent, gj)),
                qi.copy(numericScalarRestrictions = qi.numericScalarRestrictions + (guuid -> remaining)),
                fi || si.nonEmpty)
            }
        case (Failure(t), _) =>
          Failure(t)
      }

      step4 <- queue.plainLiteralScalarRestrictions
        .foldLeft[Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]](Success(step3)) {
        case (Success(Tuple3(ri, qi, fi)), (guuid, drs)) =>
          val gi = ri.context.g
          val tbox = ri.context.tboxes(guuid)

          val (restrictableDataRanges, available, remaining) =
            partitionRestrictableDataRanges[tables.PlainLiteralScalarRestriction](ri, tbox, drs, _.restrictedRangeUUID)

          val si
          : SortedSet[api.TerminologyBoxStatement]
          = available
            .foldLeft[SortedSet[api.TerminologyBoxStatement]](TreeSet.empty[api.TerminologyBoxStatement]) {
            case (acc, (ruuid, dr)) =>
              val x =
              ri.factory.createPlainLiteralScalarRestriction(
                tbox,
                restrictableDataRanges(ruuid.toString),
                dr.length,
                dr.minLength,
                dr.maxLength,
                dr.name,
                dr.langRange,
                dr.pattern)
              if (x.uuid.toString != dr.uuid)
                throw new java.lang.IllegalArgumentException(s"DataRangteResolver.PlainLiteralScalarRestriction UUID mismatch: read: $dr, created: $x")
              acc + x
            }

          TerminologyContext
            .replaceNode(ri.factory, gi, tbox, tbox.withBoxStatements(si))
            .map { gj =>
              Tuple3(
                ri.copy(context = TerminologyContext(ri.context.extent, gj)),
                qi.copy(plainLiteralScalarRestrictions = qi.plainLiteralScalarRestrictions + (guuid -> remaining)),
                fi || si.nonEmpty)
            }
        case (Failure(t), _) =>
          Failure(t)
      }

      step5 <- queue.scalarOneOfRestrictions
        .foldLeft[Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]](Success(step4)) {
        case (Success(Tuple3(ri, qi, fi)), (guuid, drs)) =>
          val gi = ri.context.g
          val tbox = ri.context.tboxes(guuid)

          val (restrictableDataRanges, available, remaining) =
            partitionRestrictableDataRanges[tables.ScalarOneOfRestriction](ri, tbox, drs, _.restrictedRangeUUID)

          val si
          : SortedSet[api.TerminologyBoxStatement]
          = available
            .foldLeft[SortedSet[api.TerminologyBoxStatement]](TreeSet.empty[api.TerminologyBoxStatement]) {
            case (acc, (ruuid, dr)) =>
              val x =
              ri.factory.createScalarOneOfRestriction(
                tbox,
                restrictableDataRanges(ruuid.toString),
                dr.name)
              if (x.uuid.toString != dr.uuid)
                throw new java.lang.IllegalArgumentException(s"DataRangteResolver.ScalarOneOfRestriction UUID mismatch: read: $dr, created: $x")
              acc + x
            }

          TerminologyContext
            .replaceNode(ri.factory, gi, tbox, tbox.withBoxStatements(si))
            .map { gj =>
              Tuple3(
                ri.copy(context = TerminologyContext(ri.context.extent, gj)),
                qi.copy(scalarOneOfRestrictions = qi.scalarOneOfRestrictions + (guuid -> remaining)),
                fi || si.nonEmpty)
            }
        case (Failure(t), _) =>
          Failure(t)
      }

      step6 <- queue.stringScalarRestrictions
        .foldLeft[Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]](Success(step5)) {
        case (Success(Tuple3(ri, qi, fi)), (guuid, drs)) =>
          val gi = ri.context.g
          val tbox = ri.context.tboxes(guuid)

          val (restrictableDataRanges, available, remaining) =
            partitionRestrictableDataRanges[tables.StringScalarRestriction](ri, tbox, drs, _.restrictedRangeUUID)

          val si
          : SortedSet[api.TerminologyBoxStatement]
          = available
            .foldLeft[SortedSet[api.TerminologyBoxStatement]](TreeSet.empty[api.TerminologyBoxStatement]) {
            case (acc, (ruuid, dr)) =>
              val x =
              ri.factory.createStringScalarRestriction(
                tbox,
                restrictableDataRanges(ruuid.toString),
                dr.length,
                dr.minLength,
                dr.maxLength,
                dr.name,
                dr.pattern)
              if (x.uuid.toString != dr.uuid)
                throw new java.lang.IllegalArgumentException(s"DataRangteResolver.StringScalarRestriction UUID mismatch: read: $dr, created: $x")
              acc + x
          }

          TerminologyContext
            .replaceNode(ri.factory, gi, tbox, tbox.withBoxStatements(si))
            .map { gj =>
              Tuple3(
                ri.copy(context = TerminologyContext(ri.context.extent, gj)),
                qi.copy(stringScalarRestrictions = qi.stringScalarRestrictions + (guuid -> remaining)),
                fi || si.nonEmpty)
            }
        case (Failure(t), _) =>
          Failure(t)
      }

      step7 <- queue.timeScalarRestrictions
        .foldLeft[Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]](Success(step6)) {
        case (Success(Tuple3(ri, qi, fi)), (guuid, drs)) =>
          val gi = ri.context.g
          val tbox = ri.context.tboxes(guuid)

          val (restrictableDataRanges, available, remaining) =
            partitionRestrictableDataRanges[tables.TimeScalarRestriction](ri, tbox, drs, _.restrictedRangeUUID)

          val si
          : SortedSet[api.TerminologyBoxStatement]
          = available
            .foldLeft[SortedSet[api.TerminologyBoxStatement]](TreeSet.empty[api.TerminologyBoxStatement]) {
            case (acc, (ruuid, dr)) =>
              val x =
              ri.factory.createTimeScalarRestriction(
                tbox,
                restrictableDataRanges(ruuid.toString),
                dr.minExclusive,
                dr.minInclusive,
                dr.maxExclusive,
                dr.maxInclusive,
                dr.name)
              if (x.uuid.toString != dr.uuid)
                throw new java.lang.IllegalArgumentException(s"DataRangteResolver.TimeScalarRestriction UUID mismatch: read: $dr, created: $x")
              acc + x
          }

          TerminologyContext
            .replaceNode(ri.factory, gi, tbox, tbox.withBoxStatements(si))
            .map { gj =>
              Tuple3(
                ri.copy(context = TerminologyContext(ri.context.extent, gj)),
                qi.copy(timeScalarRestrictions = qi.timeScalarRestrictions + (guuid -> remaining)),
                fi || si.nonEmpty)
            }
        case (Failure(t), _) =>
          Failure(t)
      }
    } yield step7

    resolved match {
      case Success(Tuple3(r7, q7, f7)) =>
        if (f7)
          resolve(r7, q7)
        else
          Success(Tuple2(r7, q7))
      case Failure(t) =>
        Failure(t)
    }
  }
}