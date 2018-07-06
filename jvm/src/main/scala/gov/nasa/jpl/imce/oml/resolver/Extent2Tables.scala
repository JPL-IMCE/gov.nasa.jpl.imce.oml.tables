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

import gov.nasa.jpl.imce.oml.covariantTag.@@
import gov.nasa.jpl.imce.oml.{covariantTag, tables}
import gov.nasa.jpl.imce.oml.parallelSort
import scala.collection.immutable._
import scala.{None,Option,Some,StringContext}
import scala.Predef.{ArrowAssoc,String}

object Extent2Tables {

  /**
    * Convenience conversion of `UUID @@ Tag` to `String @@ Tag` for a given `Tag`.
    *
    * @param uuid Tagged UUID
    * @tparam Tag Tag type
    * @return The `String @@ Tag` representation of `uuid`.
    */
  implicit def toUUIDString[Tag](uuid: UUID @@ Tag)
  : String @@ Tag
  = covariantTag[Tag][String](uuid.toString)

  def convertTerminologyGraph
  (acc: Seq[tables.TerminologyGraph],
   x: api.TerminologyGraph)
  : Seq[tables.TerminologyGraph]
  = acc :+ tables.TerminologyGraph(
    x.uuid,
    x.kind,
    x.iri)

  def convertBundle
  (acc: Seq[tables.Bundle],
   x: api.Bundle)
  : Seq[tables.Bundle]
  = acc :+ tables.Bundle(
    x.uuid,
    x.kind,
    x.iri)

  def convertDescriptionBox
  (acc: Seq[tables.DescriptionBox],
   x: api.DescriptionBox)
  : Seq[tables.DescriptionBox]
  = acc :+ tables.DescriptionBox(
    x.uuid,
    x.kind,
    x.iri)

  def convertAnnotationProperties
  (e: api.Extent)
  (acc: Seq[tables.AnnotationProperty],
   x: (api.Module, Set[api.AnnotationProperty]))
  : Seq[tables.AnnotationProperty]
  = acc ++ x._2.map { ap =>
    ap.moduleContext()(e) match {
      case Some(m) =>
        tables.AnnotationProperty(
          ap.uuid,
          m.uuid,
          ap.iri,
          ap.abbrevIRI)
      case None =>
        throw new IllegalArgumentException(s"convertAnnotationProperties: Cannot find module for $ap")
    }
  }

  def convertAspects
  (acc: Seq[tables.Aspect],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.Aspect]
  = acc ++ x._2.flatMap {
    case y: api.Aspect =>
      Some(tables.Aspect(
        y.uuid,
        x._1.uuid,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertCardinalityRestrictedAspects
  (acc: Seq[tables.CardinalityRestrictedAspect],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.CardinalityRestrictedAspect]
  = acc ++ x._2.flatMap {
    case y: api.CardinalityRestrictedAspect =>
      Some(tables.CardinalityRestrictedAspect(
        y.uuid,
        x._1.uuid,
        y.restrictedRange.map(_.uuid),
        y.name,
        y.restrictedCardinality,
        y.restrictedRelationship.uuid,
        y.restrictionKind
      ))
  }

  def convertConcepts
  (acc: Seq[tables.Concept],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.Concept]
  = acc ++ x._2.flatMap {
    case y: api.Concept =>
      Some(tables.Concept(
        y.uuid,
        x._1.uuid,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertCardinalityRestrictedConcepts
  (acc: Seq[tables.CardinalityRestrictedConcept],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.CardinalityRestrictedConcept]
  = acc ++ x._2.flatMap {
    case y: api.CardinalityRestrictedConcept =>
      Some(tables.CardinalityRestrictedConcept(
        y.uuid,
        x._1.uuid,
        y.restrictedRange.map(_.uuid),
        y.name,
        y.restrictedCardinality,
        y.restrictedRelationship.uuid,
        y.restrictionKind
      ))
  }

  def convertCardinalityRestrictedReifiedRelationships
  (acc: Seq[tables.CardinalityRestrictedReifiedRelationship],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.CardinalityRestrictedReifiedRelationship]
  = acc ++ x._2.flatMap {
    case y: api.CardinalityRestrictedReifiedRelationship =>
      Some(tables.CardinalityRestrictedReifiedRelationship(
        y.uuid,
        x._1.uuid,
        y.restrictedRange.map(_.uuid),
        y.name,
        y.restrictedCardinality,
        y.restrictedRelationship.uuid,
        y.restrictionKind
      ))
  }

  def convertReifiedRelationshipRestrictions
  (acc: Seq[tables.ReifiedRelationshipRestriction],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.ReifiedRelationshipRestriction]
  = acc ++ x._2.flatMap {
    case y: api.ReifiedRelationshipRestriction =>
      Some(tables.ReifiedRelationshipRestriction(
        y.uuid,
        x._1.uuid,
        y.source.uuid,
        y.target.uuid,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertReifiedRelationships
  (acc: Seq[tables.ReifiedRelationship],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.ReifiedRelationship]
  = acc ++ x._2.flatMap {
    case y: api.ReifiedRelationship =>
      Some(tables.ReifiedRelationship(
        y.uuid,
        x._1.uuid,
        y.source.uuid,
        y.target.uuid,
        y.isAsymmetric,
        y.isEssential,
        y.isFunctional,
        y.isInverseEssential,
        y.isInverseFunctional,
        y.isIrreflexive,
        y.isReflexive,
        y.isSymmetric,
        y.isTransitive,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertForwardProperties
  (acc: Seq[tables.ForwardProperty],
   x: (api.ReifiedRelationship, api.ForwardProperty))
  : Seq[tables.ForwardProperty]
  = acc :+ tables.ForwardProperty(
    x._2.uuid,
    x._2.name,
    x._1.uuid)

  def convertInverseProperties
  (acc: Seq[tables.InverseProperty],
   x: (api.ReifiedRelationship, api.InverseProperty))
  : Seq[tables.InverseProperty]
  =  acc :+ tables.InverseProperty(
    x._2.uuid,
    x._2.name,
    x._1.uuid)

  def convertUnreifiedRelationships
  (acc: Seq[tables.UnreifiedRelationship],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.UnreifiedRelationship]
  = acc ++ x._2.flatMap {
    case y: api.UnreifiedRelationship =>
      Some(tables.UnreifiedRelationship(
        y.uuid,
        x._1.uuid,
        y.source.uuid,
        y.target.uuid,
        y.isAsymmetric,
        y.isEssential,
        y.isFunctional,
        y.isInverseEssential,
        y.isInverseFunctional,
        y.isIrreflexive,
        y.isReflexive,
        y.isSymmetric,
        y.isTransitive,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertScalars
  (acc: Seq[tables.Scalar],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.Scalar]
  = acc ++ x._2.flatMap {
    case y: api.Scalar =>
      Some(tables.Scalar(
        y.uuid,
        x._1.uuid,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertStructures
  (acc: Seq[tables.Structure],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.Structure]
  = acc ++ x._2.flatMap {
    case y: api.Structure =>
      Some(tables.Structure(
        y.uuid,
        x._1.uuid,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertChainRules
  (acc: Seq[tables.ChainRule],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.ChainRule]
  = acc ++ x._2.flatMap {
    case y: api.ChainRule =>
      Some(tables.ChainRule(
        y.uuid,
        x._1.uuid,
        y.name,
        y.head.uuid))
    case _ =>
      None
  }.to[Seq]

  def convertRuleBodySegments
  (e: api.Extent)
  (acc: Seq[tables.RuleBodySegment],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.RuleBodySegment]
  = {

    def convertRuleBodySegments
    (seg: api.RuleBodySegment,
     rule: Option[api.ChainRule],
     prev: Option[api.RuleBodySegment],
     acc: Seq[tables.RuleBodySegment])
    : Seq[tables.RuleBodySegment]
    = rule match {
      case Some(r) =>
        val acc1 = acc :+ tables.RuleBodySegment(
          seg.uuid,
          None,
          Some(r.uuid))
        e.nextSegment.get(seg) match {
          case Some(next) =>
            convertRuleBodySegments(next, None, Some(seg), acc1)
          case None =>
            acc1
        }
      case None =>
        prev match {
          case Some(p) =>
            val acc1 = acc :+ tables.RuleBodySegment(
              seg.uuid,
              Some(p.uuid),
              None)
            e.nextSegment.get(seg) match {
              case Some(next) =>
                convertRuleBodySegments(next, None, Some(seg), acc1)
              case None =>
                acc1
            }
          case None =>
            acc
        }
    }

    acc ++ x._2.flatMap {
      case y: api.ChainRule =>
        e.firstSegment.get(y) match {
          case Some(seg) =>
            convertRuleBodySegments(seg, Some(y), None, Seq.empty[tables.RuleBodySegment])
          case None =>
            None
        }
      case _ =>
        None
    }.to[Seq]
  }

  def convertSegmentPredicates
  (e: api.Extent)
  (acc: Seq[tables.SegmentPredicate],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.SegmentPredicate]
  = {

    def convertSegmentPredicates
    (seg: Option[api.RuleBodySegment],
     acc: Seq[tables.SegmentPredicate])
    : Seq[tables.SegmentPredicate]
    = seg match {
      case Some(s) =>
        e.predicate.get(s) match {
          case Some(p: api.SegmentPredicate) =>
            convertSegmentPredicates(e.nextSegment.get(s),
              acc :+ tables.SegmentPredicate(
                uuid = p.uuid,
                bodySegmentUUID = s.uuid,
                predicateUUID = p.predicate.map(_.uuid),
                reifiedRelationshipSourceUUID = p.reifiedRelationshipSource.map(_.uuid),
                reifiedRelationshipInverseSourceUUID = p.reifiedRelationshipInverseSource.map(_.uuid),
                reifiedRelationshipTargetUUID = p.reifiedRelationshipTarget.map(_.uuid),
                reifiedRelationshipInverseTargetUUID = p.reifiedRelationshipInverseTarget.map(_.uuid),
                unreifiedRelationshipInverseUUID = p.unreifiedRelationshipInverse.map(_.uuid)))
          case _ =>
            convertSegmentPredicates(e.nextSegment.get(s), acc)
        }
      case None =>
        acc
    }

    acc ++ x._2.flatMap {
      case y: api.ChainRule =>
        convertSegmentPredicates(e.firstSegment.get(y), Seq.empty[tables.SegmentPredicate])
      case _ =>
        None
    }.to[Seq]
  }

  def convertBinaryScalarRestrictions
  (acc: Seq[tables.BinaryScalarRestriction],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.BinaryScalarRestriction]
  = acc ++ x._2.flatMap {
    case y: api.BinaryScalarRestriction =>
      Some(tables.BinaryScalarRestriction(
        y.uuid,
        x._1.uuid,
        y.restrictedRange.uuid,
        y.length,
        y.minLength,
        y.maxLength,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertIRIScalarRestrictions
  (acc: Seq[tables.IRIScalarRestriction],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.IRIScalarRestriction]
  = acc ++ x._2.flatMap {
    case y: api.IRIScalarRestriction =>
      Some(tables.IRIScalarRestriction(
        y.uuid,
        x._1.uuid,
        y.restrictedRange.uuid,
        y.length,
        y.minLength,
        y.maxLength,
        y.name,
        y.pattern))
    case _ =>
      None
  }.to[Seq]

  def convertNumericScalarRestrictions
  (acc: Seq[tables.NumericScalarRestriction],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.NumericScalarRestriction]
  = acc ++ x._2.flatMap {
    case y: api.NumericScalarRestriction =>
      Some(tables.NumericScalarRestriction(
        y.uuid,
        x._1.uuid,
        y.restrictedRange.uuid,
        y.minExclusive,
        y.minInclusive,
        y.maxExclusive,
        y.maxInclusive,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertPlainLiteralScalarRestrictions
  (acc: Seq[tables.PlainLiteralScalarRestriction],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.PlainLiteralScalarRestriction]
  = acc ++ x._2.flatMap {
    case y: api.PlainLiteralScalarRestriction =>
      Some(tables.PlainLiteralScalarRestriction(
        y.uuid,
        x._1.uuid,
        y.restrictedRange.uuid,
        y.length,
        y.minLength,
        y.maxLength,
        y.name,
        y.langRange,
        y.pattern))
    case _ =>
      None
  }.to[Seq]

  def convertStringScalarRestrictions
  (acc: Seq[tables.StringScalarRestriction],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.StringScalarRestriction]
  = acc ++ x._2.flatMap {
    case y: api.StringScalarRestriction =>
      Some(tables.StringScalarRestriction(
        y.uuid,
        x._1.uuid,
        y.restrictedRange.uuid,
        y.length,
        y.minLength,
        y.maxLength,
        y.name,
        y.pattern))
    case _ =>
      None
  }.to[Seq]

  def convertSynonymScalarRestrictions
  (acc: Seq[tables.SynonymScalarRestriction],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.SynonymScalarRestriction]
  = acc ++ x._2.flatMap {
    case y: api.SynonymScalarRestriction =>
      Some(tables.SynonymScalarRestriction(
        y.uuid,
        x._1.uuid,
        y.restrictedRange.uuid,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertTimeScalarRestrictions
  (acc: Seq[tables.TimeScalarRestriction],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.TimeScalarRestriction]
  = acc ++ x._2.flatMap {
    case y: api.TimeScalarRestriction =>
      Some(tables.TimeScalarRestriction(
        y.uuid,
        x._1.uuid,
        y.restrictedRange.uuid,
        y.minExclusive,
        y.minInclusive,
        y.maxExclusive,
        y.maxInclusive,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertScalarOneOfRestrictions
  (acc: Seq[tables.ScalarOneOfRestriction],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.ScalarOneOfRestriction]
  = acc ++ x._2.flatMap {
    case y: api.ScalarOneOfRestriction =>
      Some(tables.ScalarOneOfRestriction(
        y.uuid,
        x._1.uuid,
        y.restrictedRange.uuid,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertScalarOneOfLiteralAxioms
  (acc: Seq[tables.ScalarOneOfLiteralAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.ScalarOneOfLiteralAxiom]
  = acc ++ x._2.flatMap {
    case y: api.ScalarOneOfLiteralAxiom =>
      Some(tables.ScalarOneOfLiteralAxiom(
        y.uuid,
        x._1.uuid,
        y.axiom.uuid,
        y.value,
        y.valueType.map(dr => dr.uuid)))
    case _ =>
      None
  }.to[Seq]

  def convertScalarDataProperties
  (acc: Seq[tables.ScalarDataProperty],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.ScalarDataProperty]
  = acc ++ x._2.flatMap {
    case y: api.ScalarDataProperty =>
      Some(tables.ScalarDataProperty(
        y.uuid,
        x._1.uuid,
        y.domain.uuid,
        y.range.uuid,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertStructuredDataProperties
  (acc: Seq[tables.StructuredDataProperty],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.StructuredDataProperty]
  = acc ++ x._2.flatMap {
    case y: api.StructuredDataProperty =>
      Some(tables.StructuredDataProperty(
        y.uuid,
        x._1.uuid,
        y.domain.uuid,
        y.range.uuid,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertEntityScalarDataProperties
  (acc: Seq[tables.EntityScalarDataProperty],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.EntityScalarDataProperty]
  = acc ++ x._2.flatMap {
    case y: api.EntityScalarDataProperty =>
      Some(tables.EntityScalarDataProperty(
        y.uuid,
        x._1.uuid,
        y.domain.uuid,
        y.range.uuid,
        y.isIdentityCriteria,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertEntityStructuredDataProperties
  (acc: Seq[tables.EntityStructuredDataProperty],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.EntityStructuredDataProperty]
  = acc ++ x._2.flatMap {
    case y: api.EntityStructuredDataProperty =>
      Some(tables.EntityStructuredDataProperty(
        y.uuid,
        x._1.uuid,
        y.domain.uuid,
        y.range.uuid,
        y.isIdentityCriteria,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertConceptDesignationTerminologyAxioms
  (acc: Seq[tables.ConceptDesignationTerminologyAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxAxiom]))
  : Seq[tables.ConceptDesignationTerminologyAxiom]
  = acc ++ x._2.flatMap {
    case y: api.ConceptDesignationTerminologyAxiom =>
      Some(tables.ConceptDesignationTerminologyAxiom(
        y.uuid,
        x._1.uuid,
        y.designatedConcept.uuid,
        y.designatedTerminology))
    case _ =>
      None
  }.to[Seq]

  def convertTerminologyExtensionAxioms
  (acc: Seq[tables.TerminologyExtensionAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxAxiom]))
  : Seq[tables.TerminologyExtensionAxiom]
  = acc ++ x._2.flatMap {
    case y: api.TerminologyExtensionAxiom =>
      Some(tables.TerminologyExtensionAxiom(
        y.uuid,
        x._1.uuid,
        y.extendedTerminology))
    case _ =>
      None
  }.to[Seq]

  def convertTerminologyNestingAxioms
  (acc: Seq[tables.TerminologyNestingAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxAxiom]))
  : Seq[tables.TerminologyNestingAxiom]
  = acc ++ x._2.flatMap {
    case y: api.TerminologyNestingAxiom =>
      Some(tables.TerminologyNestingAxiom(
        y.uuid,
        x._1.uuid,
        y.nestingContext.uuid,
        y.nestingTerminology))
    case _ =>
      None
  }.to[Seq]

  def convertAspectSpecializationAxioms
  (acc: Seq[tables.AspectSpecializationAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.AspectSpecializationAxiom]
  = acc ++ x._2.flatMap {
    case y: api.AspectSpecializationAxiom =>
      Some(tables.AspectSpecializationAxiom(
        y.uuid,
        x._1.uuid,
        y.superAspect.uuid,
        y.subEntity.uuid))
    case _ =>
      None
  }.to[Seq]

  def convertConceptSpecializationAxioms
  (acc: Seq[tables.ConceptSpecializationAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.ConceptSpecializationAxiom]
  = acc ++ x._2.flatMap {
    case y: api.ConceptSpecializationAxiom =>
      Some(tables.ConceptSpecializationAxiom(
        y.uuid,
        x._1.uuid,
        y.superConcept.uuid,
        y.subConcept.uuid))
    case _ =>
      None
  }.to[Seq]

  def convertReifiedRelationshipSpecializationAxioms
  (acc: Seq[tables.ReifiedRelationshipSpecializationAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.ReifiedRelationshipSpecializationAxiom]
  = acc ++ x._2.flatMap {
    case y: api.ReifiedRelationshipSpecializationAxiom =>
      Some(tables.ReifiedRelationshipSpecializationAxiom(
        y.uuid,
        x._1.uuid,
        y.superRelationship.uuid,
        y.subRelationship.uuid))
    case _ =>
      None
  }.to[Seq]

  def convertEntityExistentialRestrictionAxioms
  (acc: Seq[tables.EntityExistentialRestrictionAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.EntityExistentialRestrictionAxiom]
  = acc ++ x._2.flatMap {
    case y: api.EntityExistentialRestrictionAxiom =>
      Some(tables.EntityExistentialRestrictionAxiom(
        y.uuid,
        x._1.uuid,
        y.restrictedDomain.uuid,
        y.restrictedRange.uuid,
        y.restrictedRelationship.uuid))
    case _ =>
      None
  }.to[Seq]

  def convertEntityUniversalRestrictionAxioms
  (acc: Seq[tables.EntityUniversalRestrictionAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.EntityUniversalRestrictionAxiom]
  = acc ++ x._2.flatMap {
    case y: api.EntityUniversalRestrictionAxiom =>
      Some(tables.EntityUniversalRestrictionAxiom(
        y.uuid,
        x._1.uuid,
        y.restrictedDomain.uuid,
        y.restrictedRange.uuid,
        y.restrictedRelationship.uuid))
    case _ =>
      None
  }.to[Seq]

  def convertEntityScalarDataPropertyExistentialRestrictionAxioms
  (acc: Seq[tables.EntityScalarDataPropertyExistentialRestrictionAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.EntityScalarDataPropertyExistentialRestrictionAxiom]
  = acc ++ x._2.flatMap {
    case y: api.EntityScalarDataPropertyExistentialRestrictionAxiom =>
      Some(tables.EntityScalarDataPropertyExistentialRestrictionAxiom(
        y.uuid,
        x._1.uuid,
        y.restrictedEntity.uuid,
        y.scalarProperty.uuid,
        y.scalarRestriction.uuid))
    case _ =>
      None
  }.to[Seq]

  def convertEntityScalarDataPropertyParticularRestrictionAxioms
  (acc: Seq[tables.EntityScalarDataPropertyParticularRestrictionAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.EntityScalarDataPropertyParticularRestrictionAxiom]
  = acc ++ x._2.flatMap {
    case y: api.EntityScalarDataPropertyParticularRestrictionAxiom =>
      Some(tables.EntityScalarDataPropertyParticularRestrictionAxiom(
        y.uuid,
        x._1.uuid,
        y.restrictedEntity.uuid,
        y.scalarProperty.uuid,
        y.literalValue,
        y.valueType.map(dr => dr.uuid)))
    case _ =>
      None
  }.to[Seq]

  def convertEntityScalarDataPropertyUniversalRestrictionAxioms
  (acc: Seq[tables.EntityScalarDataPropertyUniversalRestrictionAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.EntityScalarDataPropertyUniversalRestrictionAxiom]
  = acc ++ x._2.flatMap {
    case y: api.EntityScalarDataPropertyUniversalRestrictionAxiom =>
      Some(tables.EntityScalarDataPropertyUniversalRestrictionAxiom(
        y.uuid,
        x._1.uuid,
        y.restrictedEntity.uuid,
        y.scalarProperty.uuid,
        y.scalarRestriction.uuid))
    case _ =>
      None
  }.to[Seq]

  def convertEntityStructuredDataPropertyParticularRestrictionAxioms
  (acc: Seq[tables.EntityStructuredDataPropertyParticularRestrictionAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.EntityStructuredDataPropertyParticularRestrictionAxiom]
  = acc ++ x._2.flatMap {
    case y: api.EntityStructuredDataPropertyParticularRestrictionAxiom =>
      Some(tables.EntityStructuredDataPropertyParticularRestrictionAxiom(
        y.uuid,
        x._1.uuid,
        y.structuredDataProperty.uuid,
        y.restrictedEntity.uuid))
    case _ =>
      None
  }.to[Seq]

  def convertRestrictionStructuredDataPropertyTuples
  (e: api.Extent)
  (acc: Seq[tables.RestrictionStructuredDataPropertyTuple],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.RestrictionStructuredDataPropertyTuple]
  = {

    def convertRestrictionStructuredDataPropertyTuples
    (queue: Set[(api.RestrictionStructuredDataPropertyContext, api.RestrictionStructuredDataPropertyTuple)],
     acc: Seq[tables.RestrictionStructuredDataPropertyTuple])
    : Seq[tables.RestrictionStructuredDataPropertyTuple]
    = if (queue.nonEmpty) {
      val (c, r) = queue.head
      val more = e
        .structuredDataPropertyRestrictions
        .getOrElse(r, Set.empty[api.RestrictionStructuredDataPropertyTuple])
        .map(t => c -> t)
      val tuple = tables.RestrictionStructuredDataPropertyTuple(
        r.uuid,
        c.uuid,
        r.structuredDataProperty.uuid)
      convertRestrictionStructuredDataPropertyTuples(more ++ queue.tail, acc :+ tuple)
    } else acc

    acc ++ x._2.flatMap {
      case y: api.EntityStructuredDataPropertyParticularRestrictionAxiom =>
        convertRestrictionStructuredDataPropertyTuples(
          e
          .structuredDataPropertyRestrictions
          .getOrElse(y, Set.empty[api.RestrictionStructuredDataPropertyTuple])
          .map(t => y -> t),
          Seq.empty[tables.RestrictionStructuredDataPropertyTuple])
      case _ =>
        None
    }
  }

  def convertRestrictionScalarDataPropertyValues
  (e: api.Extent)
  (acc: Seq[tables.RestrictionScalarDataPropertyValue],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.RestrictionScalarDataPropertyValue]
  = {

    def convertRestrictionScalarDataPropertyValues
    (queue: Set[(api.RestrictionStructuredDataPropertyContext, api.RestrictionStructuredDataPropertyTuple)],
     acc: Seq[tables.RestrictionScalarDataPropertyValue])
    : Seq[tables.RestrictionScalarDataPropertyValue]
    = if (queue.nonEmpty) {
      val (c, r) = queue.head
      val more = e
        .structuredDataPropertyRestrictions
        .getOrElse(r, Set.empty[api.RestrictionStructuredDataPropertyTuple])
        .map(t => c -> t)
      val tuples = e
        .scalarDataPropertyRestrictions
        .getOrElse(r, Set.empty[api.RestrictionScalarDataPropertyValue])
        .toSeq
        .map { v =>
          tables.RestrictionScalarDataPropertyValue(
            v.uuid,
            c.uuid,
            v.scalarDataProperty.uuid,
            v.scalarPropertyValue,
            v.valueType.map(dr => dr.uuid))
        }
      convertRestrictionScalarDataPropertyValues(more ++ queue.tail, acc ++ tuples)
    } else acc

    acc ++ x._2.flatMap {
      case y: api.EntityStructuredDataPropertyParticularRestrictionAxiom =>
        convertRestrictionScalarDataPropertyValues(
          e
            .structuredDataPropertyRestrictions
            .getOrElse(y, Set.empty[api.RestrictionStructuredDataPropertyTuple])
            .map(t => y -> t),
          Seq.empty[tables.RestrictionScalarDataPropertyValue])
      case _ =>
        None
    }
  }

  def convertBundledTerminologyAxioms
  (acc: Seq[tables.BundledTerminologyAxiom],
   x: (api.Bundle, Set[api.TerminologyBundleAxiom]))
  : Seq[tables.BundledTerminologyAxiom]
  = acc ++ x._2.flatMap {
    case y: api.BundledTerminologyAxiom =>
      Some(tables.BundledTerminologyAxiom(
        y.uuid,
        x._1.uuid,
        y.bundledTerminology))
    case _ =>
      None
  }.to[Seq]

  def convertAnonymousConceptUnionAxioms
  (acc: Seq[tables.AnonymousConceptUnionAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBundleStatement]))
  (implicit ext: api.Extent)
  : Seq[tables.AnonymousConceptUnionAxiom]
  = acc ++ x._2.flatMap {
    case y: api.AnonymousConceptUnionAxiom =>
        Some(tables.AnonymousConceptUnionAxiom(
          y.uuid,
          y.conceptTreeDisjunctionParent().get.uuid,
          y.name))
    case _ =>
      None
  }.to[Seq]

  def convertRootConceptTaxonomyAxioms
  (acc: Seq[tables.RootConceptTaxonomyAxiom],
   x: (api.Bundle, Set[api.TerminologyBundleStatement]))
  : Seq[tables.RootConceptTaxonomyAxiom]
  = acc ++ x._2.flatMap {
    case y: api.RootConceptTaxonomyAxiom =>
      Some(tables.RootConceptTaxonomyAxiom(
        y.uuid,
        x._1.uuid,
        y.root.uuid))
    case _ =>
      None
  }.to[Seq]

  def convertSpecificDisjointConceptAxioms
  (acc: Seq[tables.SpecificDisjointConceptAxiom],
   x: (api.Bundle, Set[api.TerminologyBundleStatement]))
  (implicit ext: api.Extent)
  : Seq[tables.SpecificDisjointConceptAxiom]
  = acc ++ x._2.flatMap {
    case y: api.SpecificDisjointConceptAxiom =>
      Some(tables.SpecificDisjointConceptAxiom(
          y.uuid,
          y.conceptTreeDisjunctionParent().get.uuid,
          y.disjointLeaf.uuid))
    case _ =>
      None
  }.to[Seq]

  def convertConceptInstances
  (acc: Seq[tables.ConceptInstance],
   x: (api.ConceptInstance, api.DescriptionBox))
  : Seq[tables.ConceptInstance]
  = acc :+ tables.ConceptInstance(
        x._1.uuid,
        x._2.uuid,
        x._1.singletonConceptClassifier.uuid,
        x._1.name)

  def convertDescriptionBoxExtendsClosedWorldDefinitions
  (acc: Seq[tables.DescriptionBoxExtendsClosedWorldDefinitions],
   x: (api.DescriptionBoxExtendsClosedWorldDefinitions, api.DescriptionBox))
  : Seq[tables.DescriptionBoxExtendsClosedWorldDefinitions]
  = acc :+ tables.DescriptionBoxExtendsClosedWorldDefinitions(
    x._1.uuid,
    x._2.uuid,
    x._1.closedWorldDefinitions)

  def convertDescriptionBoxRefinements
  (acc: Seq[tables.DescriptionBoxRefinement],
   x: (api.DescriptionBoxRefinement, api.DescriptionBox))
  : Seq[tables.DescriptionBoxRefinement]
  = acc :+ tables.DescriptionBoxRefinement(
    x._1.uuid,
    x._2.uuid,
    x._1.refinedDescriptionBox)

  def convertReifiedRelationshipInstances
  (acc: Seq[tables.ReifiedRelationshipInstance],
   x: (api.ReifiedRelationshipInstance, api.DescriptionBox))
  : Seq[tables.ReifiedRelationshipInstance]
  = acc :+ tables.ReifiedRelationshipInstance(
    x._1.uuid,
    x._2.uuid,
    x._1.singletonConceptualRelationshipClassifier.uuid,
    x._1.name)

  def convertReifiedRelationshipInstanceDomains
  (acc: Seq[tables.ReifiedRelationshipInstanceDomain],
   x: (api.ReifiedRelationshipInstanceDomain, api.DescriptionBox))
  : Seq[tables.ReifiedRelationshipInstanceDomain]
  = acc :+ tables.ReifiedRelationshipInstanceDomain(
    x._1.uuid,
    x._2.uuid,
    x._1.reifiedRelationshipInstance.uuid,
    x._1.domain.uuid)

  def convertReifiedRelationshipInstanceRanges
  (acc: Seq[tables.ReifiedRelationshipInstanceRange],
   x: (api.ReifiedRelationshipInstanceRange, api.DescriptionBox))
  : Seq[tables.ReifiedRelationshipInstanceRange]
  = acc :+ tables.ReifiedRelationshipInstanceRange(
    x._1.uuid,
    x._2.uuid,
    x._1.reifiedRelationshipInstance.uuid,
    x._1.range.uuid)

  def convertUnreifiedRelationshipInstanceTuples
  (acc: Seq[tables.UnreifiedRelationshipInstanceTuple],
   x: (api.UnreifiedRelationshipInstanceTuple, api.DescriptionBox))
  : Seq[tables.UnreifiedRelationshipInstanceTuple]
  = acc :+ tables.UnreifiedRelationshipInstanceTuple(
    x._1.uuid,
    x._2.uuid,
    x._1.unreifiedRelationship.uuid,
    x._1.domain.uuid,
    x._1.range.uuid)

  def convertSingletonScalarDataPropertyValues
  (acc: Seq[tables.SingletonInstanceScalarDataPropertyValue],
   x: (api.DescriptionBox, Set[api.SingletonInstanceScalarDataPropertyValue]))
  : Seq[tables.SingletonInstanceScalarDataPropertyValue]
  = x._2.foldLeft[Seq[tables.SingletonInstanceScalarDataPropertyValue]](acc) { case (acc1, v) =>
    acc1 :+ tables.SingletonInstanceScalarDataPropertyValue(
      v.uuid,
      x._1.uuid,
      v.singletonInstance.uuid,
      v.scalarDataProperty.uuid,
      v.scalarPropertyValue,
      v.valueType.map(dr => dr.uuid))
  }

  def convertSingletonStructuredDataPropertyValues
  (acc: Seq[tables.SingletonInstanceStructuredDataPropertyValue],
   x: (api.DescriptionBox, Set[api.SingletonInstanceStructuredDataPropertyValue]))
  : Seq[tables.SingletonInstanceStructuredDataPropertyValue]
  = x._2.foldLeft[Seq[tables.SingletonInstanceStructuredDataPropertyValue]](acc) { case (acc1, v) =>
    acc1 :+ tables.SingletonInstanceStructuredDataPropertyValue(
      v.uuid,
      x._1.uuid,
      v.singletonInstance.uuid,
      v.structuredDataProperty.uuid)
  }

  def convertScalarDataPropertyValues
  (acc: Seq[tables.ScalarDataPropertyValue],
   x: (api.SingletonInstanceStructuredDataPropertyContext, Set[api.ScalarDataPropertyValue]))
  : Seq[tables.ScalarDataPropertyValue]
  = x._2.foldLeft[Seq[tables.ScalarDataPropertyValue]](acc) { case (acc1, v) =>
    acc1 :+ tables.ScalarDataPropertyValue(
      v.uuid,
      x._1.uuid,
      v.scalarDataProperty.uuid,
      v.scalarPropertyValue,
      v.valueType.map(dr => dr.uuid))
  }

  def convertStructuredDataPropertyTuples
  (acc: Seq[tables.StructuredDataPropertyTuple],
   x: (api.SingletonInstanceStructuredDataPropertyContext, Set[api.StructuredDataPropertyTuple]))
  : Seq[tables.StructuredDataPropertyTuple]
  = x._2.foldLeft[Seq[tables.StructuredDataPropertyTuple]](acc) { case (acc1, v) =>
    acc1 :+ tables.StructuredDataPropertyTuple(
      v.uuid,
      x._1.uuid,
      v.structuredDataProperty.uuid)
  }

  def convertAnnotationPropertyValue
  (apv: api.AnnotationPropertyValue)
  : tables.AnnotationPropertyValue
  = tables.AnnotationPropertyValue(
      apv.uuid,
      apv.subject.uuid,
      apv.property.uuid,
      apv.value)

  def convert
  (e: api.Extent)
  : tables.OMLSpecificationTables
  = {
    implicit val ext: api.Extent = e
    val t = tables.OMLSpecificationTables.createEmptyOMLSpecificationTables()
        .copy(
          // modules

          terminologyGraphs =
            parallelSort.parSortBy(
              e.terminologyGraphs.values.foldLeft[Seq[tables.TerminologyGraph]](Seq.empty)(convertTerminologyGraph),
              (g: tables.TerminologyGraph) => g.uuid),

          bundles =
            parallelSort.parSortBy(
              e.bundles.values.foldLeft[Seq[tables.Bundle]](Seq.empty)(convertBundle),
              (b: tables.Bundle) => b.uuid),

          descriptionBoxes =
            parallelSort.parSortBy(
              e.descriptionBoxes.values.foldLeft[Seq[tables.DescriptionBox]](Seq.empty)(convertDescriptionBox),
              (d: tables.DescriptionBox) => d.uuid),

          // annotation properties

          annotationProperties =
            parallelSort.parSortBy(
              e.annotationProperties.foldLeft[Seq[tables.AnnotationProperty]](Seq.empty)(convertAnnotationProperties(e)),
              (ap: tables.AnnotationProperty) => ap.uuid),

          // terms

          aspects =
            parallelSort.parSortBy(
              e.boxStatements.foldLeft[Seq[tables.Aspect]](Seq.empty)(convertAspects),
              (a: tables.Aspect) => a.uuid),

          cardinalityRestrictedAspects =
            parallelSort.parSortBy(
              e.boxStatements.foldLeft[Seq[tables.CardinalityRestrictedAspect]](Seq.empty)(convertCardinalityRestrictedAspects),
              (a: tables.CardinalityRestrictedAspect) => a.uuid),

          concepts =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.Concept]](Seq.empty)(convertConcepts),
              (c: tables.Concept) => c.uuid),

          cardinalityRestrictedConcepts =
            parallelSort.parSortBy(
              e.boxStatements.foldLeft[Seq[tables.CardinalityRestrictedConcept]](Seq.empty)(convertCardinalityRestrictedConcepts),
              (c: tables.CardinalityRestrictedConcept) => c.uuid),

          reifiedRelationships =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.ReifiedRelationship]](Seq.empty)(convertReifiedRelationships),
              (rr: tables.ReifiedRelationship) => rr.uuid),

          reifiedRelationshipRestrictions =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.ReifiedRelationshipRestriction]](Seq.empty)(convertReifiedRelationshipRestrictions),
              (rr: tables.ReifiedRelationshipRestriction) => rr.uuid),

          cardinalityRestrictedReifiedRelationships =
            parallelSort.parSortBy(
              e.boxStatements.foldLeft[Seq[tables.CardinalityRestrictedReifiedRelationship]](Seq.empty)(convertCardinalityRestrictedReifiedRelationships),
              (rr: tables.CardinalityRestrictedReifiedRelationship) => rr.uuid),

          forwardProperties =
            parallelSort.parSortBy(
              e.forwardProperty
                .foldLeft[Seq[tables.ForwardProperty]](Seq.empty)(convertForwardProperties),
              (f: tables.ForwardProperty) => f.uuid),

          inverseProperties =
            parallelSort.parSortBy(
              e.inverseProperty
                .foldLeft[Seq[tables.InverseProperty]](Seq.empty)(convertInverseProperties),
              (i: tables.InverseProperty) => i.uuid),

          unreifiedRelationships =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.UnreifiedRelationship]](Seq.empty)(convertUnreifiedRelationships),
              (u: tables.UnreifiedRelationship) => u.uuid),

          scalars =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.Scalar]](Seq.empty)(convertScalars),
              (s: tables.Scalar) => s.uuid),

          structures =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.Structure]](Seq.empty)(convertStructures),
              (s: tables.Structure) => s.uuid),

          chainRules =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.ChainRule]](Seq.empty)(convertChainRules),
              (r: tables.ChainRule) => r.uuid),

          ruleBodySegments =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.RuleBodySegment]](Seq.empty)(convertRuleBodySegments(e)),
              (s: tables.RuleBodySegment) => s.uuid),

          segmentPredicates =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.SegmentPredicate]](Seq.empty)(convertSegmentPredicates(e)),
              (p: tables.SegmentPredicate) => p.uuid),

          // restriction axioms

          entityExistentialRestrictionAxioms =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.EntityExistentialRestrictionAxiom]](Seq.empty)(convertEntityExistentialRestrictionAxioms),
              (a: tables.EntityExistentialRestrictionAxiom) => a.uuid),

          entityUniversalRestrictionAxioms =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.EntityUniversalRestrictionAxiom]](Seq.empty)(convertEntityUniversalRestrictionAxioms),
              (a: tables.EntityUniversalRestrictionAxiom) => a.uuid),

          // data ranges

          binaryScalarRestrictions =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.BinaryScalarRestriction]](Seq.empty)(convertBinaryScalarRestrictions),
              (r: tables.BinaryScalarRestriction) => r.uuid),

          iriScalarRestrictions =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.IRIScalarRestriction]](Seq.empty)(convertIRIScalarRestrictions),
              (r: tables.IRIScalarRestriction) => r.uuid),

          numericScalarRestrictions =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.NumericScalarRestriction]](Seq.empty)(convertNumericScalarRestrictions),
              (a: tables.NumericScalarRestriction) => a.uuid),

          plainLiteralScalarRestrictions =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.PlainLiteralScalarRestriction]](Seq.empty)(convertPlainLiteralScalarRestrictions),
              (r: tables.PlainLiteralScalarRestriction) => r.uuid),

          stringScalarRestrictions =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.StringScalarRestriction]](Seq.empty)(convertStringScalarRestrictions),
              (r: tables.StringScalarRestriction) => r.uuid),

          synonymScalarRestrictions =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.SynonymScalarRestriction]](Seq.empty)(convertSynonymScalarRestrictions),
              (r: tables.SynonymScalarRestriction) => r.uuid),

          timeScalarRestrictions =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.TimeScalarRestriction]](Seq.empty)(convertTimeScalarRestrictions),
              (r: tables.TimeScalarRestriction) => r.uuid),

          scalarOneOfRestrictions =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.ScalarOneOfRestriction]](Seq.empty)(convertScalarOneOfRestrictions),
              (r: tables.ScalarOneOfRestriction) => r.uuid),

          scalarOneOfLiteralAxioms =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.ScalarOneOfLiteralAxiom]](Seq.empty)(convertScalarOneOfLiteralAxioms),
              (a: tables.ScalarOneOfLiteralAxiom) => a.uuid),

          // data properties

          scalarDataProperties =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.ScalarDataProperty]](Seq.empty)(convertScalarDataProperties),
              (a: tables.ScalarDataProperty) => a.uuid),

          structuredDataProperties =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.StructuredDataProperty]](Seq.empty)(convertStructuredDataProperties),
              (a: tables.StructuredDataProperty) => a.uuid),

          entityScalarDataProperties =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.EntityScalarDataProperty]](Seq.empty)(convertEntityScalarDataProperties),
              (a: tables.EntityScalarDataProperty) => a.uuid),

          entityStructuredDataProperties =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.EntityStructuredDataProperty]](Seq.empty)(convertEntityStructuredDataProperties),
              (a: tables.EntityStructuredDataProperty) => a.uuid),

          // terminology box axioms

          conceptDesignationTerminologyAxioms =
            parallelSort.parSortBy(
              e.boxAxioms
                .foldLeft[Seq[tables.ConceptDesignationTerminologyAxiom]](Seq.empty)(convertConceptDesignationTerminologyAxioms),
              (a: tables.ConceptDesignationTerminologyAxiom) => a.uuid),

          terminologyExtensionAxioms =
            parallelSort.parSortBy(
              e.boxAxioms
                .foldLeft[Seq[tables.TerminologyExtensionAxiom]](Seq.empty)(convertTerminologyExtensionAxioms),
              (a: tables.TerminologyExtensionAxiom) => a.uuid),

          terminologyNestingAxioms =
            parallelSort.parSortBy(
              e.boxAxioms
                .foldLeft[Seq[tables.TerminologyNestingAxiom]](Seq.empty)(convertTerminologyNestingAxioms),
              (a: tables.TerminologyNestingAxiom) => a.uuid),

          // terminology box statements

          aspectSpecializationAxioms =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.AspectSpecializationAxiom]](Seq.empty)(convertAspectSpecializationAxioms),
              (a: tables.AspectSpecializationAxiom) => a.uuid),

          conceptSpecializationAxioms =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.ConceptSpecializationAxiom]](Seq.empty)(convertConceptSpecializationAxioms),
              (a: tables.ConceptSpecializationAxiom) => a.uuid),

          reifiedRelationshipSpecializationAxioms =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.ReifiedRelationshipSpecializationAxiom]](Seq.empty)(convertReifiedRelationshipSpecializationAxioms),
              (a: tables.ReifiedRelationshipSpecializationAxiom) => a.uuid),

          entityScalarDataPropertyExistentialRestrictionAxioms =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.EntityScalarDataPropertyExistentialRestrictionAxiom]](Seq.empty)(convertEntityScalarDataPropertyExistentialRestrictionAxioms),
              (a: tables.EntityScalarDataPropertyExistentialRestrictionAxiom) => a.uuid),

          entityScalarDataPropertyParticularRestrictionAxioms =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.EntityScalarDataPropertyParticularRestrictionAxiom]](Seq.empty)(convertEntityScalarDataPropertyParticularRestrictionAxioms),
              (a: tables.EntityScalarDataPropertyParticularRestrictionAxiom) => a.uuid),

          entityScalarDataPropertyUniversalRestrictionAxioms =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.EntityScalarDataPropertyUniversalRestrictionAxiom]](Seq.empty)(convertEntityScalarDataPropertyUniversalRestrictionAxioms),
              (a: tables.EntityScalarDataPropertyUniversalRestrictionAxiom) => a.uuid),

          entityStructuredDataPropertyParticularRestrictionAxioms =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.EntityStructuredDataPropertyParticularRestrictionAxiom]](Seq.empty)(convertEntityStructuredDataPropertyParticularRestrictionAxioms),
              (a: tables.EntityStructuredDataPropertyParticularRestrictionAxiom) => a.uuid),

          restrictionStructuredDataPropertyTuples =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.RestrictionStructuredDataPropertyTuple]](Seq.empty)(convertRestrictionStructuredDataPropertyTuples(e)),
              (a: tables.RestrictionStructuredDataPropertyTuple) => a.uuid),

          restrictionScalarDataPropertyValues =
            parallelSort.parSortBy(
              e.boxStatements
                .foldLeft[Seq[tables.RestrictionScalarDataPropertyValue]](Seq.empty)(convertRestrictionScalarDataPropertyValues(e)),
              (a: tables.RestrictionScalarDataPropertyValue) => a.uuid),

          // bundle axioms

          bundledTerminologyAxioms =
            parallelSort.parSortBy(
              e.bundleAxioms
                .foldLeft[Seq[tables.BundledTerminologyAxiom]](Seq.empty)(convertBundledTerminologyAxioms),
              (a: tables.BundledTerminologyAxiom) => a.uuid),

          // bundle statements

          anonymousConceptUnionAxioms =
            parallelSort.parSortBy(
              e.bundleStatements
                .foldLeft[Seq[tables.AnonymousConceptUnionAxiom]](Seq.empty)(convertAnonymousConceptUnionAxioms),
              (a: tables.AnonymousConceptUnionAxiom) => a.uuid),

          rootConceptTaxonomyAxioms =
            parallelSort.parSortBy(
              e.bundleStatements
                .foldLeft[Seq[tables.RootConceptTaxonomyAxiom]](Seq.empty)(convertRootConceptTaxonomyAxioms),
              (a: tables.RootConceptTaxonomyAxiom) => a.uuid),

          specificDisjointConceptAxioms =
            parallelSort.parSortBy(
              e.bundleStatements
                .foldLeft[Seq[tables.SpecificDisjointConceptAxiom]](Seq.empty)(convertSpecificDisjointConceptAxioms),
              (a: tables.SpecificDisjointConceptAxiom) => a.uuid),

          // description box statements

          conceptInstances =
            parallelSort.parSortBy(
              e.descriptionBoxOfConceptInstance
                .foldLeft[Seq[tables.ConceptInstance]](Seq.empty)(convertConceptInstances),
              (a: tables.ConceptInstance) => a.uuid),

          descriptionBoxExtendsClosedWorldDefinitions =
            parallelSort.parSortBy(
              e.descriptionBoxOfDescriptionBoxExtendsClosedWorldDefinitions
                .foldLeft[Seq[tables.DescriptionBoxExtendsClosedWorldDefinitions]](Seq.empty)(convertDescriptionBoxExtendsClosedWorldDefinitions),
              (a: tables.DescriptionBoxExtendsClosedWorldDefinitions) => a.uuid),

          descriptionBoxRefinements =
            parallelSort.parSortBy(
              e.descriptionBoxOfDescriptionBoxRefinement
                .foldLeft[Seq[tables.DescriptionBoxRefinement]](Seq.empty)(convertDescriptionBoxRefinements),
              (a: tables.DescriptionBoxRefinement) => a.uuid),

          reifiedRelationshipInstances =
            parallelSort.parSortBy(
              e.descriptionBoxOfReifiedRelationshipInstance
                .foldLeft[Seq[tables.ReifiedRelationshipInstance]](Seq.empty)(convertReifiedRelationshipInstances),
              (a: tables.ReifiedRelationshipInstance) => a.uuid),

          reifiedRelationshipInstanceDomains =
            parallelSort.parSortBy(
              e.descriptionBoxOfReifiedRelationshipInstanceDomain
                .foldLeft[Seq[tables.ReifiedRelationshipInstanceDomain]](Seq.empty)(convertReifiedRelationshipInstanceDomains),
              (a: tables.ReifiedRelationshipInstanceDomain) => a.uuid),

          reifiedRelationshipInstanceRanges =
            parallelSort.parSortBy(
              e.descriptionBoxOfReifiedRelationshipInstanceRange
                .foldLeft[Seq[tables.ReifiedRelationshipInstanceRange]](Seq.empty)(convertReifiedRelationshipInstanceRanges),
              (a: tables.ReifiedRelationshipInstanceRange) => a.uuid),

          unreifiedRelationshipInstanceTuples =
            parallelSort.parSortBy(
              e.descriptionBoxOfUnreifiedRelationshipInstanceTuple
                .foldLeft[Seq[tables.UnreifiedRelationshipInstanceTuple]](Seq.empty)(convertUnreifiedRelationshipInstanceTuples),
              (a: tables.UnreifiedRelationshipInstanceTuple) => a.uuid),

          singletonInstanceScalarDataPropertyValues =
            parallelSort.parSortBy(
              e.singletonScalarDataPropertyValues
                .foldLeft[Seq[tables.SingletonInstanceScalarDataPropertyValue]](Seq.empty)(convertSingletonScalarDataPropertyValues),
              (a: tables.SingletonInstanceScalarDataPropertyValue) => a.uuid),

          singletonInstanceStructuredDataPropertyValues =
            parallelSort.parSortBy(
              e.singletonStructuredDataPropertyValues
                .foldLeft[Seq[tables.SingletonInstanceStructuredDataPropertyValue]](Seq.empty)(convertSingletonStructuredDataPropertyValues),
              (a: tables.SingletonInstanceStructuredDataPropertyValue) => a.uuid),

          structuredDataPropertyTuples =
            parallelSort.parSortBy(
              e.structuredPropertyTuples
                .foldLeft[Seq[tables.StructuredDataPropertyTuple]](Seq.empty)(convertStructuredDataPropertyTuples),
              (t: tables.StructuredDataPropertyTuple) => t.uuid),

          scalarDataPropertyValues =
            parallelSort.parSortBy(
              e.scalarDataPropertyValues
                .foldLeft[Seq[tables.ScalarDataPropertyValue]](Seq.empty)(convertScalarDataPropertyValues),
              (v: tables.ScalarDataPropertyValue) => v.uuid),

          annotationPropertyValues =
            parallelSort.parSortBy(
              e.annotationPropertyValueByUUID.values
                .map(convertAnnotationPropertyValue)
                .to[Seq],
              (v: tables.AnnotationPropertyValue) => v.uuid)
        )

    t
  }

}
