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

import gov.nasa.jpl.imce.oml.tables

import scala.{Boolean, None, Some, StringContext, Tuple2, Tuple3}
import scala.collection.immutable.{Map, Seq, Set}
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
  synonymScalarRestrictions: Map[UUID, Seq[(UUID, tables.SynonymScalarRestriction)]],
  timeScalarRestrictions: Map[UUID, Seq[(UUID, tables.TimeScalarRestriction)]] )

object DataRangesToResolve {

  import OMLOps._

  def asDataRangeXRef(uuid: UUID)
  : tables.taggedTypes.DataRangeXRef
  = tables.taggedTypes.dataRangeUUID(uuid.toString)

  private def partitionRestrictableDataRanges[T]
  (r: OMLTablesResolver,
   tbox: api.TerminologyBox,
   drs: Seq[(UUID, T)],
   dr2restrictedDataRange: T => tables.taggedTypes.DataRangeXRef)
  : (Map[tables.taggedTypes.DataRangeXRef, api.DataRange], Seq[(UUID, T)], Seq[(UUID, T)])
  = {
    val allDataRanges
    : Set[api.DataRange]
    = r.allContexts.foldLeft(Set.empty[api.DataRange]) { case (acc, c) =>
      c.boxStatements.values.foldLeft(acc) {
        case (prev, s) =>
          s.foldLeft(prev) {
            case (next, dr: api.DataRange) =>
              next + dr
            case (next, _) =>
              next
          }
      }
    }

    val restrictableDataRanges
    : Map[tables.taggedTypes.DataRangeXRef, api.DataRange]
    = allDataRanges.map(dr => asDataRangeXRef(dr.uuid) -> dr).toMap

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
    synonymScalarRestrictions = Map.empty,
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
          ri.lookupTerminologyBox(guuid) match {
            case Some(tbox) =>

              val (restrictableDataRanges, available, remaining) =
                partitionRestrictableDataRanges[tables.BinaryScalarRestriction](ri, tbox, drs, _.restrictedRangeUUID)

              val si
              : Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]
              = available
                .foldLeft[Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]] {
                Success(Tuple3(
                  ri,
                  qi.copy(binaryScalarRestrictions = qi.binaryScalarRestrictions + (guuid -> remaining)),
                  fi))
              } {
                case (acc, (ruuid, dr)) =>
                  val next
                  : Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]
                  = for {
                    tuple <- acc
                    (rj, qj, fj) = tuple
                    pair = rj.factory.createBinaryScalarRestriction(
                      rj.context,
                      UUID.fromString(dr.uuid), // @UUID
                      tbox,
                      restrictableDataRanges(asDataRangeXRef(ruuid)),
                      dr.length,
                      dr.minLength,
                      dr.maxLength,
                      dr.name)
                    (ej, x) = pair
                    ek <- if (!uuidEquivalent(x.uuid, dr.uuid))
                      Failure(new java.lang.IllegalArgumentException(
                        s"DataRangteResolver.BinaryScalarRestriction UUID mismatch: read: $dr, created: $x"))
                    else
                      Success(Tuple3(
                        rj.copy(context = ej),
                        qi.copy(binaryScalarRestrictions = qi.binaryScalarRestrictions + (guuid -> remaining)),
                        true))
                  } yield ek
                  next
              }

              si
            case None =>
              Failure(new java.lang.IllegalArgumentException(
                s"resolve(binaryScalarRestrictions) -- unknown graph: $guuid"
              ))
          }
        case (Failure(t), _) =>
          Failure(t)
      }

      step2 <- queue.iriScalarRestrictions
        .foldLeft[Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]](Success(step1)) {
        case (Success(Tuple3(ri, qi, fi)), (guuid, drs)) =>
          ri.lookupTerminologyBox(guuid) match {
            case Some(tbox) =>

              val (restrictableDataRanges, available, remaining) =
                partitionRestrictableDataRanges[tables.IRIScalarRestriction](ri, tbox, drs, _.restrictedRangeUUID)

              val si
              : Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]
              = available
                .foldLeft[Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]] {
                Success(Tuple3(
                  ri,
                  qi.copy(iriScalarRestrictions = qi.iriScalarRestrictions + (guuid -> remaining)),
                  fi))
              } {
                case (acc, (ruuid, dr)) =>
                  val next
                  : Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]
                  = for {
                    tuple <- acc
                    (rj, qj, fj) = tuple
                    pair = ri.factory.createIRIScalarRestriction(
                      rj.context,
                      UUID.fromString(dr.uuid), // @UUID
                      tbox,
                      restrictableDataRanges(asDataRangeXRef(ruuid)),
                      dr.length,
                      dr.minLength,
                      dr.maxLength,
                      dr.name,
                      dr.pattern)
                    (ej, x) = pair
                    ek <- if (!uuidEquivalent(x.uuid, dr.uuid))
                      Failure(new java.lang.IllegalArgumentException(
                        s"DataRangteResolver.IRIScalarRestriction UUID mismatch: read: $dr, created: $x"))
                    else
                      Success(Tuple3(
                        rj.copy(context = ej),
                        qi.copy(iriScalarRestrictions = qi.iriScalarRestrictions + (guuid -> remaining)),
                        true))
                  } yield ek
                  next
              }

              si
            case None =>
              Failure(new java.lang.IllegalArgumentException(
                s"resolve(iriScalarRestrictions) -- unknown graph: $guuid"
              ))
          }
        case (Failure(t), _) =>
          Failure(t)
      }

      step3 <- queue.numericScalarRestrictions
        .foldLeft[Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]](Success(step2)) {
        case (Success(Tuple3(ri, qi, fi)), (guuid, drs)) =>
          ri.lookupTerminologyBox(guuid) match {
            case Some(tbox) =>

              val (restrictableDataRanges, available, remaining) =
                partitionRestrictableDataRanges[tables.NumericScalarRestriction](ri, tbox, drs, _.restrictedRangeUUID)

              val si
              : Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]
              = available
                .foldLeft[Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]] {
                Success(Tuple3(
                  ri,
                  qi.copy(numericScalarRestrictions = qi.numericScalarRestrictions + (guuid -> remaining)),
                  fi))
              } {
                case (acc, (ruuid, dr)) =>
                  val next
                  : Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]
                  = for {
                    tuple <- acc
                    (rj, qj, fj) = tuple
                    pair = ri.factory.createNumericScalarRestriction(
                      rj.context,
                      UUID.fromString(dr.uuid), // @UUID
                      tbox,
                      restrictableDataRanges(asDataRangeXRef(ruuid)),
                      dr.minExclusive,
                      dr.minInclusive,
                      dr.maxExclusive,
                      dr.maxInclusive,
                      dr.name)
                    (ej, x) = pair
                    ek <- if (!uuidEquivalent(x.uuid, dr.uuid))
                      Failure(new java.lang.IllegalArgumentException(
                        s"DataRangteResolver.NumericScalarRestriction UUID mismatch: read: $dr, created: $x"))
                    else
                      Success(Tuple3(
                        rj.copy(context = ej),
                        qi.copy(numericScalarRestrictions = qi.numericScalarRestrictions + (guuid -> remaining)),
                        true))
                  } yield ek
                  next
              }

              si
            case None =>
              Failure(new java.lang.IllegalArgumentException(
                s"resolve(numericScalarRestrictions) -- unknown graph: $guuid"
              ))
          }
        case (Failure(t), _) =>
          Failure(t)
      }

      step4 <- queue.plainLiteralScalarRestrictions
        .foldLeft[Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]](Success(step3)) {
        case (Success(Tuple3(ri, qi, fi)), (guuid, drs)) =>
          ri.lookupTerminologyBox(guuid) match {
            case Some(tbox) =>

              val (restrictableDataRanges, available, remaining) =
                partitionRestrictableDataRanges[tables.PlainLiteralScalarRestriction](ri, tbox, drs, _.restrictedRangeUUID)

              val si
              : Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]
              = available
                .foldLeft[Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]] {
                Success(Tuple3(
                  ri,
                  qi.copy(plainLiteralScalarRestrictions = qi.plainLiteralScalarRestrictions + (guuid -> remaining)),
                  fi))
              } {
                case (acc, (ruuid, dr)) =>
                  val next
                  : Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]
                  = for {
                    tuple <- acc
                    (rj, qj, fj) = tuple
                    pair = ri.factory.createPlainLiteralScalarRestriction(
                      rj.context,
                      UUID.fromString(dr.uuid), // @UUID
                      tbox,
                      restrictableDataRanges(asDataRangeXRef(ruuid)),
                      dr.length,
                      dr.minLength,
                      dr.maxLength,
                      dr.name,
                      dr.langRange,
                      dr.pattern)
                    (ej, x) = pair
                    ek <- if (!uuidEquivalent(x.uuid, dr.uuid))
                      Failure(new java.lang.IllegalArgumentException(
                        s"DataRangteResolver.PlainLiteralScalarRestriction UUID mismatch: read: $dr, created: $x"))
                    else
                      Success(Tuple3(
                        rj.copy(context = ej),
                        qi.copy(plainLiteralScalarRestrictions = qi.plainLiteralScalarRestrictions + (guuid -> remaining)),
                        true))
                  } yield ek
                  next
              }

              si
            case None =>
              Failure(new java.lang.IllegalArgumentException(
                s"resolve(plainLiteralScalarRestrictions) -- unknown graph: $guuid"
              ))
          }
        case (Failure(t), _) =>
          Failure(t)
      }

      step5 <- queue.scalarOneOfRestrictions
        .foldLeft[Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]](Success(step4)) {
        case (Success(Tuple3(ri, qi, fi)), (guuid, drs)) =>
          ri.lookupTerminologyBox(guuid) match {
            case Some(tbox) =>

              val (restrictableDataRanges, available, remaining) =
                partitionRestrictableDataRanges[tables.ScalarOneOfRestriction](ri, tbox, drs, _.restrictedRangeUUID)

              val si
              : Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]
              = available
                .foldLeft[Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]] {
                Success(Tuple3(
                  ri,
                  qi.copy(scalarOneOfRestrictions = qi.scalarOneOfRestrictions + (guuid -> remaining)),
                  fi))
              } {
                case (acc, (ruuid, dr)) =>
                  val next
                  : Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]
                  = for {
                    tuple <- acc
                    (rj, qj, fj) = tuple
                    pair = ri.factory.createScalarOneOfRestriction(
                      rj.context,
                      UUID.fromString(dr.uuid), // @UUID
                      tbox,
                      restrictableDataRanges(asDataRangeXRef(ruuid)),
                      dr.name)
                    (ej, x) = pair
                    ek <- if (!uuidEquivalent(x.uuid, dr.uuid))
                      Failure(new java.lang.IllegalArgumentException(
                        s"DataRangteResolver.ScalarOneOfRestriction UUID mismatch: read: $dr, created: $x"))
                    else
                      Success(Tuple3(
                        rj.copy(context = ej),
                        qi.copy(scalarOneOfRestrictions = qi.scalarOneOfRestrictions + (guuid -> remaining)),
                        true))
                  } yield ek
                  next
              }

              si
            case None =>
              Failure(new java.lang.IllegalArgumentException(
                s"resolve(scalarOneOfRestrictions) -- unknown graph: $guuid"
              ))
          }
        case (Failure(t), _) =>
          Failure(t)
      }

      step6 <- queue.stringScalarRestrictions
        .foldLeft[Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]](Success(step5)) {
        case (Success(Tuple3(ri, qi, fi)), (guuid, drs)) =>
          ri.lookupTerminologyBox(guuid) match {
            case Some(tbox) =>

              val (restrictableDataRanges, available, remaining) =
                partitionRestrictableDataRanges[tables.StringScalarRestriction](ri, tbox, drs, _.restrictedRangeUUID)

              val si
              : Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]
              = available
                .foldLeft[Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]] {
                Success(Tuple3(
                  ri,
                  qi.copy(stringScalarRestrictions = qi.stringScalarRestrictions + (guuid -> remaining)),
                  fi))
              } {
                case (acc, (ruuid, dr)) =>
                  val next
                  : Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]
                  = for {
                    tuple <- acc
                    (rj, qj, fj) = tuple
                    pair = ri.factory.createStringScalarRestriction(
                      rj.context,
                      UUID.fromString(dr.uuid), // @UUID
                      tbox,
                      restrictableDataRanges(asDataRangeXRef(ruuid)),
                      dr.length,
                      dr.minLength,
                      dr.maxLength,
                      dr.name,
                      dr.pattern)
                    (ej, x) = pair
                    ek <- if (!uuidEquivalent(x.uuid, dr.uuid))
                      Failure(new java.lang.IllegalArgumentException(
                        s"DataRangteResolver.StringScalarRestriction UUID mismatch: read: $dr, created: $x"))
                    else
                      Success(Tuple3(
                        rj.copy(context = ej),
                        qi.copy(stringScalarRestrictions = qi.stringScalarRestrictions + (guuid -> remaining)),
                        true))
                  } yield ek
                  next
              }

              si
            case None =>
              Failure(new java.lang.IllegalArgumentException(
                s"resolve(stringScalarRestrictions) -- unknown graph: $guuid"
              ))
          }
        case (Failure(t), _) =>
          Failure(t)
      }

      step7 <- queue.synonymScalarRestrictions
        .foldLeft[Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]](Success(step6)) {
        case (Success(Tuple3(ri, qi, fi)), (guuid, drs)) =>
          ri.lookupTerminologyBox(guuid) match {
            case Some(tbox) =>

              val (restrictableDataRanges, available, remaining) =
                partitionRestrictableDataRanges[tables.SynonymScalarRestriction](ri, tbox, drs, _.restrictedRangeUUID)

              val si
              : Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]
              = available
                .foldLeft[Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]] {
                Success(Tuple3(
                  ri,
                  qi.copy(synonymScalarRestrictions = qi.synonymScalarRestrictions + (guuid -> remaining)),
                  fi))
              } {
                case (acc, (ruuid, dr)) =>
                  val next
                  : Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]
                  = for {
                    tuple <- acc
                    (rj, qj, fj) = tuple
                    pair = ri.factory.createSynonymScalarRestriction(
                      rj.context,
                      UUID.fromString(dr.uuid), // @UUID
                      tbox,
                      restrictableDataRanges(asDataRangeXRef(ruuid)),
                      dr.name)
                    (ej, x) = pair
                    ek <- if (!uuidEquivalent(x.uuid, dr.uuid))
                      Failure(new java.lang.IllegalArgumentException(
                        s"DataRangteResolver.SynonymScalarRestriction UUID mismatch: read: $dr, created: $x"))
                    else
                      Success(Tuple3(
                        rj.copy(context = ej),
                        qi.copy(synonymScalarRestrictions = qi.synonymScalarRestrictions + (guuid -> remaining)),
                        true))
                  } yield ek
                  next
              }

              si
            case None =>
              Failure(new java.lang.IllegalArgumentException(
                s"resolve(synonymScalarRestrictions) -- unknown graph: $guuid"
              ))
          }
        case (Failure(t), _) =>
          Failure(t)
      }

      step8 <- queue.timeScalarRestrictions
        .foldLeft[Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]](Success(step7)) {
        case (Success(Tuple3(ri, qi, fi)), (guuid, drs)) =>
          ri.lookupTerminologyBox(guuid) match {
            case Some(tbox) =>

              val (restrictableDataRanges, available, remaining) =
                partitionRestrictableDataRanges[tables.TimeScalarRestriction](ri, tbox, drs, _.restrictedRangeUUID)

              val si
              : Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]
              = available
                .foldLeft[Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]] {
                Success(Tuple3(
                  ri,
                  qi.copy(timeScalarRestrictions = qi.timeScalarRestrictions + (guuid -> remaining)),
                  fi))
              } {
                case (acc, (ruuid, dr)) =>
                  val next
                  : Try[(OMLTablesResolver, DataRangesToResolve, Boolean)]
                  = for {
                    tuple <- acc
                    (rj, qj, fj) = tuple
                    pair = ri.factory.createTimeScalarRestriction(
                      rj.context,
                      UUID.fromString(dr.uuid), // @UUID
                      tbox,
                      restrictableDataRanges(asDataRangeXRef(ruuid)),
                      dr.minExclusive,
                      dr.minInclusive,
                      dr.maxExclusive,
                      dr.maxInclusive,
                      dr.name)
                    (ej, x) = pair
                    ek <- if (!uuidEquivalent(x.uuid, dr.uuid))
                      Failure(new java.lang.IllegalArgumentException(
                        s"DataRangteResolver.TimeScalarRestriction UUID mismatch: read: $dr, created: $x"))
                    else
                      Success(Tuple3(
                        rj.copy(context = ej),
                        qi.copy(timeScalarRestrictions = qi.timeScalarRestrictions + (guuid -> remaining)),
                        true))
                  } yield ek
                  next
              }

              si
            case None =>
              Failure(new java.lang.IllegalArgumentException(
                s"resolve(timeScalarRestrictions) -- unknown graph: $guuid"
              ))
          }
        case (Failure(t), _) =>
          Failure(t)
      }
    } yield step8

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