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

import gov.nasa.jpl.imce.oml.tables
import gov.nasa.jpl.imce.oml.tables.taggedTypes

import scala.collection.immutable._
import scala.{Option,None,Some}
import scala.Predef.ArrowAssoc

object Extent2Tables {

  def convertAnnotationProperty
  (acc: Seq[tables.AnnotationProperty],
   x: api.AnnotationProperty)
  : Seq[tables.AnnotationProperty]
  = acc :+ tables.AnnotationProperty(
    taggedTypes.annotationPropertyUUID(x.uuid.toString),
    x.iri,
    x.abbrevIRI)

  def convertTerminologyGraph
  (acc: Seq[tables.TerminologyGraph],
   x: api.TerminologyGraph)
  : Seq[tables.TerminologyGraph]
  = acc :+ tables.TerminologyGraph(
    taggedTypes.terminologyGraphUUID(x.uuid.toString),
    x.kind,
    x.iri)

  def convertBundle
  (acc: Seq[tables.Bundle],
   x: api.Bundle)
  : Seq[tables.Bundle]
  = acc :+ tables.Bundle(
    taggedTypes.bundleUUID(x.uuid.toString),
    x.kind,
    x.iri)

  def convertDescriptionBox
  (acc: Seq[tables.DescriptionBox],
   x: api.DescriptionBox)
  : Seq[tables.DescriptionBox]
  = acc :+ tables.DescriptionBox(
    taggedTypes.descriptionBoxUUID(x.uuid.toString),
    x.kind,
    x.iri)

  def convertAspects
  (acc: Seq[tables.Aspect],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.Aspect]
  = acc ++ x._2.flatMap {
    case y: api.Aspect =>
      Some(tables.Aspect(
        taggedTypes.aspectUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertConcepts
  (acc: Seq[tables.Concept],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.Concept]
  = acc ++ x._2.flatMap {
    case y: api.Concept =>
      Some(tables.Concept(
        taggedTypes.conceptUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
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
        taggedTypes.reifiedRelationshipUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.entityUUID(y.source.uuid.toString),
        taggedTypes.entityUUID(y.target.uuid.toString),
        y.isAsymmetric,
        y.isEssential,
        y.isFunctional,
        y.isInverseEssential,
        y.isInverseFunctional,
        y.isIrreflexive,
        y.isReflexive,
        y.isSymmetric,
        y.isTransitive,
        y.name,
        y.unreifiedPropertyName,
        y.unreifiedInversePropertyName))
    case _ =>
      None
  }.to[Seq]

  def convertUnreifiedRelationships
  (acc: Seq[tables.UnreifiedRelationship],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.UnreifiedRelationship]
  = acc ++ x._2.flatMap {
    case y: api.UnreifiedRelationship =>
      Some(tables.UnreifiedRelationship(
        taggedTypes.unreifiedRelationshipUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.entityUUID(y.source.uuid.toString),
        taggedTypes.entityUUID(y.target.uuid.toString),
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
        taggedTypes.scalarUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
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
        taggedTypes.structureUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
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
        taggedTypes.chainRuleUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        y.name,
        taggedTypes.unreifiedRelationshipUUID(y.head.uuid.toString)))
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
          taggedTypes.ruleBodySegmentUUID(seg.uuid.toString),
          None,
          Some(taggedTypes.chainRuleUUID(r.uuid.toString)))
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
              taggedTypes.ruleBodySegmentUUID(seg.uuid.toString),
              Some(taggedTypes.ruleBodySegmentUUID(p.uuid.toString)),
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

  def convertAspectPredicates
  (e: api.Extent)
  (acc: Seq[tables.AspectPredicate],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.AspectPredicate]
  = {

    def convertAspectPredicates
    (seg: Option[api.RuleBodySegment],
     acc: Seq[tables.AspectPredicate])
    : Seq[tables.AspectPredicate]
    = seg match {
      case Some(s) =>
        e.predicate.get(s) match {
          case Some(p: api.AspectPredicate) =>
            convertAspectPredicates(e.nextSegment.get(s),
              acc :+ tables.AspectPredicate(
                uuid=taggedTypes.aspectPredicateUUID(p.uuid.toString),
                aspectUUID=taggedTypes.aspectUUID(p.aspect.uuid.toString),
                bodySegmentUUID=taggedTypes.ruleBodySegmentUUID(s.uuid.toString)))
          case _ =>
            convertAspectPredicates(e.nextSegment.get(s), acc)
        }
      case None =>
        acc
    }

    acc ++ x._2.flatMap {
      case y: api.ChainRule =>
        convertAspectPredicates(e.firstSegment.get(y), Seq.empty[tables.AspectPredicate])
      case _ =>
        None
    }.to[Seq]
  }
  
  def convertConceptPredicates
  (e: api.Extent)
  (acc: Seq[tables.ConceptPredicate],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.ConceptPredicate]
  = {

    def convertConceptPredicates
    (seg: Option[api.RuleBodySegment],
     acc: Seq[tables.ConceptPredicate])
    : Seq[tables.ConceptPredicate]
    = seg match {
      case Some(s) =>
        e.predicate.get(s) match {
          case Some(p: api.ConceptPredicate) =>
            convertConceptPredicates(e.nextSegment.get(s),
              acc :+ tables.ConceptPredicate(
                uuid=taggedTypes.conceptPredicateUUID(p.uuid.toString),
                bodySegmentUUID=taggedTypes.ruleBodySegmentUUID(s.uuid.toString),
                conceptUUID=taggedTypes.conceptUUID(p.concept.uuid.toString)))
          case _ =>
            convertConceptPredicates(e.nextSegment.get(s), acc)
        }
      case None =>
        acc
    }

    acc ++ x._2.flatMap {
      case y: api.ChainRule =>
        convertConceptPredicates(e.firstSegment.get(y), Seq.empty[tables.ConceptPredicate])
      case _ =>
        None
    }.to[Seq]
  }

  def convertReifiedRelationshipPredicates
  (e: api.Extent)
  (acc: Seq[tables.ReifiedRelationshipPredicate],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.ReifiedRelationshipPredicate]
  = {

    def convertReifiedRelationshipPredicates
    (seg: Option[api.RuleBodySegment],
     acc: Seq[tables.ReifiedRelationshipPredicate])
    : Seq[tables.ReifiedRelationshipPredicate]
    = seg match {
      case Some(s) =>
        e.predicate.get(s) match {
          case Some(p: api.ReifiedRelationshipPredicate) =>
            convertReifiedRelationshipPredicates(e.nextSegment.get(s),
              acc :+ tables.ReifiedRelationshipPredicate(
                uuid=taggedTypes.reifiedRelationshipPredicateUUID(p.uuid.toString),
                bodySegmentUUID=taggedTypes.ruleBodySegmentUUID(s.uuid.toString),
                reifiedRelationshipUUID=taggedTypes.reifiedRelationshipUUID(p.reifiedRelationship.uuid.toString)))
          case _ =>
            convertReifiedRelationshipPredicates(e.nextSegment.get(s), acc)
        }
      case None =>
        acc
    }

    acc ++ x._2.flatMap {
      case y: api.ChainRule =>
        convertReifiedRelationshipPredicates(e.firstSegment.get(y), Seq.empty[tables.ReifiedRelationshipPredicate])
      case _ =>
        None
    }.to[Seq]
  }

  def convertReifiedRelationshipPropertyPredicates
  (e: api.Extent)
  (acc: Seq[tables.ReifiedRelationshipPropertyPredicate],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.ReifiedRelationshipPropertyPredicate]
  = {

    def convertReifiedRelationshipPropertyPredicates
    (seg: Option[api.RuleBodySegment],
     acc: Seq[tables.ReifiedRelationshipPropertyPredicate])
    : Seq[tables.ReifiedRelationshipPropertyPredicate]
    = seg match {
      case Some(s) =>
        e.predicate.get(s) match {
          case Some(p: api.ReifiedRelationshipPropertyPredicate) =>
            convertReifiedRelationshipPropertyPredicates(e.nextSegment.get(s),
              acc :+ tables.ReifiedRelationshipPropertyPredicate(
                uuid=taggedTypes.reifiedRelationshipPropertyPredicateUUID(p.uuid.toString),
                bodySegmentUUID=taggedTypes.ruleBodySegmentUUID(s.uuid.toString),
                reifiedRelationshipUUID=taggedTypes.reifiedRelationshipUUID(p.reifiedRelationship.uuid.toString)))
          case _ =>
            convertReifiedRelationshipPropertyPredicates(e.nextSegment.get(s), acc)
        }
      case None =>
        acc
    }

    acc ++ x._2.flatMap {
      case y: api.ChainRule =>
        convertReifiedRelationshipPropertyPredicates(e.firstSegment.get(y), Seq.empty[tables.ReifiedRelationshipPropertyPredicate])
      case _ =>
        None
    }.to[Seq]
  }

  def convertReifiedRelationshipSourcePropertyPredicates
  (e: api.Extent)
  (acc: Seq[tables.ReifiedRelationshipSourcePropertyPredicate],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.ReifiedRelationshipSourcePropertyPredicate]
  = {

    def convertReifiedRelationshipSourcePropertyPredicates
    (seg: Option[api.RuleBodySegment],
     acc: Seq[tables.ReifiedRelationshipSourcePropertyPredicate])
    : Seq[tables.ReifiedRelationshipSourcePropertyPredicate]
    = seg match {
      case Some(s) =>
        e.predicate.get(s) match {
          case Some(p: api.ReifiedRelationshipSourcePropertyPredicate) =>
            convertReifiedRelationshipSourcePropertyPredicates(e.nextSegment.get(s),
              acc :+ tables.ReifiedRelationshipSourcePropertyPredicate(
                uuid=taggedTypes.reifiedRelationshipSourcePropertyPredicateUUID(p.uuid.toString),
                bodySegmentUUID=taggedTypes.ruleBodySegmentUUID(s.uuid.toString),
                reifiedRelationshipUUID=taggedTypes.reifiedRelationshipUUID(p.reifiedRelationship.uuid.toString)))
          case _ =>
            convertReifiedRelationshipSourcePropertyPredicates(e.nextSegment.get(s), acc)
        }
      case None =>
        acc
    }

    acc ++ x._2.flatMap {
      case y: api.ChainRule =>
        convertReifiedRelationshipSourcePropertyPredicates(e.firstSegment.get(y), Seq.empty[tables.ReifiedRelationshipSourcePropertyPredicate])
      case _ =>
        None
    }.to[Seq]
  }

  def convertReifiedRelationshipTargetPropertyPredicates
  (e: api.Extent)
  (acc: Seq[tables.ReifiedRelationshipTargetPropertyPredicate],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.ReifiedRelationshipTargetPropertyPredicate]
  = {

    def convertReifiedRelationshipTargetPropertyPredicates
    (seg: Option[api.RuleBodySegment],
     acc: Seq[tables.ReifiedRelationshipTargetPropertyPredicate])
    : Seq[tables.ReifiedRelationshipTargetPropertyPredicate]
    = seg match {
      case Some(s) =>
        e.predicate.get(s) match {
          case Some(p: api.ReifiedRelationshipTargetPropertyPredicate) =>
            convertReifiedRelationshipTargetPropertyPredicates(e.nextSegment.get(s),
              acc :+ tables.ReifiedRelationshipTargetPropertyPredicate(
                uuid=taggedTypes.reifiedRelationshipTargetPropertyPredicateUUID(p.uuid.toString),
                bodySegmentUUID=taggedTypes.ruleBodySegmentUUID(s.uuid.toString),
                reifiedRelationshipUUID=taggedTypes.reifiedRelationshipUUID(p.reifiedRelationship.uuid.toString)))
          case _ =>
            convertReifiedRelationshipTargetPropertyPredicates(e.nextSegment.get(s), acc)
        }
      case None =>
        acc
    }

    acc ++ x._2.flatMap {
      case y: api.ChainRule =>
        convertReifiedRelationshipTargetPropertyPredicates(e.firstSegment.get(y), Seq.empty[tables.ReifiedRelationshipTargetPropertyPredicate])
      case _ =>
        None
    }.to[Seq]
  }

  def convertUnreifiedRelationshipPropertyPredicates
  (e: api.Extent)
  (acc: Seq[tables.UnreifiedRelationshipPropertyPredicate],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.UnreifiedRelationshipPropertyPredicate]
  = {

    def convertUnreifiedRelationshipPropertyPredicates
    (seg: Option[api.RuleBodySegment],
     acc: Seq[tables.UnreifiedRelationshipPropertyPredicate])
    : Seq[tables.UnreifiedRelationshipPropertyPredicate]
    = seg match {
      case Some(s) =>
        e.predicate.get(s) match {
          case Some(p: api.UnreifiedRelationshipPropertyPredicate) =>
            convertUnreifiedRelationshipPropertyPredicates(e.nextSegment.get(s),
              acc :+ tables.UnreifiedRelationshipPropertyPredicate(
                uuid=taggedTypes.unreifiedRelationshipPropertyPredicateUUID(p.uuid.toString),
                unreifiedRelationshipUUID=taggedTypes.unreifiedRelationshipUUID(p.unreifiedRelationship.uuid.toString),
                bodySegmentUUID=taggedTypes.ruleBodySegmentUUID(s.uuid.toString)))
          case _ =>
            convertUnreifiedRelationshipPropertyPredicates(e.nextSegment.get(s), acc)
        }
      case None =>
        acc
    }

    acc ++ x._2.flatMap {
      case y: api.ChainRule =>
        convertUnreifiedRelationshipPropertyPredicates(e.firstSegment.get(y), Seq.empty[tables.UnreifiedRelationshipPropertyPredicate])
      case _ =>
        None
    }.to[Seq]
  }

  def convertReifiedRelationshipInversePropertyPredicates
  (e: api.Extent)
  (acc: Seq[tables.ReifiedRelationshipInversePropertyPredicate],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.ReifiedRelationshipInversePropertyPredicate]
  = {

    def convertReifiedRelationshipInversePropertyPredicates
    (seg: Option[api.RuleBodySegment],
     acc: Seq[tables.ReifiedRelationshipInversePropertyPredicate])
    : Seq[tables.ReifiedRelationshipInversePropertyPredicate]
    = seg match {
      case Some(s) =>
        e.predicate.get(s) match {
          case Some(p: api.ReifiedRelationshipInversePropertyPredicate) =>
            convertReifiedRelationshipInversePropertyPredicates(e.nextSegment.get(s),
              acc :+ tables.ReifiedRelationshipInversePropertyPredicate(
                uuid=taggedTypes.reifiedRelationshipInversePropertyPredicateUUID(p.uuid.toString),
                bodySegmentUUID=taggedTypes.ruleBodySegmentUUID(s.uuid.toString),
                reifiedRelationshipUUID=taggedTypes.reifiedRelationshipUUID(p.reifiedRelationship.uuid.toString)))
          case _ =>
            convertReifiedRelationshipInversePropertyPredicates(e.nextSegment.get(s), acc)
        }
      case None =>
        acc
    }

    acc ++ x._2.flatMap {
      case y: api.ChainRule =>
        convertReifiedRelationshipInversePropertyPredicates(e.firstSegment.get(y), Seq.empty[tables.ReifiedRelationshipInversePropertyPredicate])
      case _ =>
        None
    }.to[Seq]
  }

  def convertReifiedRelationshipSourceInversePropertyPredicates
  (e: api.Extent)
  (acc: Seq[tables.ReifiedRelationshipSourceInversePropertyPredicate],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.ReifiedRelationshipSourceInversePropertyPredicate]
  = {

    def convertReifiedRelationshipSourceInversePropertyPredicates
    (seg: Option[api.RuleBodySegment],
     acc: Seq[tables.ReifiedRelationshipSourceInversePropertyPredicate])
    : Seq[tables.ReifiedRelationshipSourceInversePropertyPredicate]
    = seg match {
      case Some(s) =>
        e.predicate.get(s) match {
          case Some(p: api.ReifiedRelationshipSourceInversePropertyPredicate) =>
            convertReifiedRelationshipSourceInversePropertyPredicates(e.nextSegment.get(s),
              acc :+ tables.ReifiedRelationshipSourceInversePropertyPredicate(
                uuid=taggedTypes.reifiedRelationshipSourceInversePropertyPredicateUUID(p.uuid.toString),
                bodySegmentUUID=taggedTypes.ruleBodySegmentUUID(s.uuid.toString),
                reifiedRelationshipUUID=taggedTypes.reifiedRelationshipUUID(p.reifiedRelationship.uuid.toString)))
          case _ =>
            convertReifiedRelationshipSourceInversePropertyPredicates(e.nextSegment.get(s), acc)
        }
      case None =>
        acc
    }

    acc ++ x._2.flatMap {
      case y: api.ChainRule =>
        convertReifiedRelationshipSourceInversePropertyPredicates(e.firstSegment.get(y), Seq.empty[tables.ReifiedRelationshipSourceInversePropertyPredicate])
      case _ =>
        None
    }.to[Seq]
  }

  def convertReifiedRelationshipTargetInversePropertyPredicates
  (e: api.Extent)
  (acc: Seq[tables.ReifiedRelationshipTargetInversePropertyPredicate],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.ReifiedRelationshipTargetInversePropertyPredicate]
  = {

    def convertReifiedRelationshipTargetInversePropertyPredicates
    (seg: Option[api.RuleBodySegment],
     acc: Seq[tables.ReifiedRelationshipTargetInversePropertyPredicate])
    : Seq[tables.ReifiedRelationshipTargetInversePropertyPredicate]
    = seg match {
      case Some(s) =>
        e.predicate.get(s) match {
          case Some(p: api.ReifiedRelationshipTargetInversePropertyPredicate) =>
            convertReifiedRelationshipTargetInversePropertyPredicates(e.nextSegment.get(s),
              acc :+ tables.ReifiedRelationshipTargetInversePropertyPredicate(
                uuid=taggedTypes.reifiedRelationshipTargetInversePropertyPredicateUUID(p.uuid.toString),
                bodySegmentUUID=taggedTypes.ruleBodySegmentUUID(s.uuid.toString),
                reifiedRelationshipUUID=taggedTypes.reifiedRelationshipUUID(p.reifiedRelationship.uuid.toString)))
          case _ =>
            convertReifiedRelationshipTargetInversePropertyPredicates(e.nextSegment.get(s), acc)
        }
      case None =>
        acc
    }

    acc ++ x._2.flatMap {
      case y: api.ChainRule =>
        convertReifiedRelationshipTargetInversePropertyPredicates(e.firstSegment.get(y), Seq.empty[tables.ReifiedRelationshipTargetInversePropertyPredicate])
      case _ =>
        None
    }.to[Seq]
  }

  def convertUnreifiedRelationshipInversePropertyPredicates
  (e: api.Extent)
  (acc: Seq[tables.UnreifiedRelationshipInversePropertyPredicate],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.UnreifiedRelationshipInversePropertyPredicate]
  = {

    def convertUnreifiedRelationshipInversePropertyPredicates
    (seg: Option[api.RuleBodySegment],
     acc: Seq[tables.UnreifiedRelationshipInversePropertyPredicate])
    : Seq[tables.UnreifiedRelationshipInversePropertyPredicate]
    = seg match {
      case Some(s) =>
        e.predicate.get(s) match {
          case Some(p: api.UnreifiedRelationshipInversePropertyPredicate) =>
            convertUnreifiedRelationshipInversePropertyPredicates(e.nextSegment.get(s),
              acc :+ tables.UnreifiedRelationshipInversePropertyPredicate(
                uuid=taggedTypes.unreifiedRelationshipInversePropertyPredicateUUID(p.uuid.toString),
                unreifiedRelationshipUUID=taggedTypes.unreifiedRelationshipUUID(p.unreifiedRelationship.uuid.toString),
                bodySegmentUUID=taggedTypes.ruleBodySegmentUUID(s.uuid.toString)))
          case _ =>
            convertUnreifiedRelationshipInversePropertyPredicates(e.nextSegment.get(s), acc)
        }
      case None =>
        acc
    }

    acc ++ x._2.flatMap {
      case y: api.ChainRule =>
        convertUnreifiedRelationshipInversePropertyPredicates(e.firstSegment.get(y), Seq.empty[tables.UnreifiedRelationshipInversePropertyPredicate])
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
        taggedTypes.binaryScalarRestrictionUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.dataRangeUUID(y.restrictedRange.uuid.toString),
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
        taggedTypes.iriScalarRestrictionUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.dataRangeUUID(y.restrictedRange.uuid.toString),
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
        taggedTypes.numericScalarRestrictionUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.dataRangeUUID(y.restrictedRange.uuid.toString),
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
        taggedTypes.plainLiteralScalarRestrictionUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.dataRangeUUID(y.restrictedRange.uuid.toString),
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
        taggedTypes.stringScalarRestrictionUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.dataRangeUUID(y.restrictedRange.uuid.toString),
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
        taggedTypes.synonymScalarRestrictionUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.dataRangeUUID(y.restrictedRange.uuid.toString),
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
        taggedTypes.timeScalarRestrictionUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.dataRangeUUID(y.restrictedRange.uuid.toString),
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
        taggedTypes.scalarOneOfRestrictionUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.dataRangeUUID(y.restrictedRange.uuid.toString),
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
        taggedTypes.scalarOneOfLiteralAxiomUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.scalarOneOfRestrictionUUID(y.axiom.uuid.toString),
        y.value,
        y.valueType.map(dr => taggedTypes.dataRangeUUID(dr.uuid.toString))))
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
        taggedTypes.scalarDataPropertyUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.structureUUID(y.domain.uuid.toString),
        taggedTypes.dataRangeUUID(y.range.uuid.toString),
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
        taggedTypes.structuredDataPropertyUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.structureUUID(y.domain.uuid.toString),
        taggedTypes.structureUUID(y.range.uuid.toString),
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
        taggedTypes.entityScalarDataPropertyUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.entityUUID(y.domain.uuid.toString),
        taggedTypes.dataRangeUUID(y.range.uuid.toString),
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
        taggedTypes.entityStructuredDataPropertyUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.entityUUID(y.domain.uuid.toString),
        taggedTypes.structureUUID(y.range.uuid.toString),
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
        taggedTypes.conceptDesignationTerminologyAxiomUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.conceptUUID(y.designatedConcept.uuid.toString),
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
        taggedTypes.terminologyExtensionAxiomUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
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
        taggedTypes.terminologyNestingAxiomUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.conceptUUID(y.nestingContext.uuid.toString),
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
        taggedTypes.aspectSpecializationAxiomUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.aspectUUID(y.superAspect.uuid.toString),
        taggedTypes.entityUUID(y.subEntity.uuid.toString)))
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
        taggedTypes.conceptSpecializationAxiomUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.conceptUUID(y.superConcept.uuid.toString),
        taggedTypes.conceptUUID(y.subConcept.uuid.toString)))
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
        taggedTypes.reifiedRelationshipSpecializationAxiomUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.reifiedRelationshipUUID(y.superRelationship.uuid.toString),
        taggedTypes.reifiedRelationshipUUID(y.subRelationship.uuid.toString)))
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
        taggedTypes.entityExistentialRestrictionAxiomUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.reifiedRelationshipUUID(y.restrictedRelation.uuid.toString),
        taggedTypes.entityUUID(y.restrictedDomain.uuid.toString),
        taggedTypes.entityUUID(y.restrictedRange.uuid.toString)))
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
        taggedTypes.entityUniversalRestrictionAxiomUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.entityRelationshipUUID(y.restrictedRelation.uuid.toString),
        taggedTypes.entityUUID(y.restrictedDomain.uuid.toString),
        taggedTypes.entityUUID(y.restrictedRange.uuid.toString)))
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
        taggedTypes.entityScalarDataPropertyExistentialRestrictionAxiomUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.entityUUID(y.restrictedEntity.uuid.toString),
        taggedTypes.entityScalarDataPropertyUUID(y.scalarProperty.uuid.toString),
        taggedTypes.dataRangeUUID(y.scalarRestriction.uuid.toString)))
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
        taggedTypes.entityScalarDataPropertyParticularRestrictionAxiomUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.entityUUID(y.restrictedEntity.uuid.toString),
        taggedTypes.entityScalarDataPropertyUUID(y.scalarProperty.uuid.toString),
        y.literalValue,
        y.valueType.map(dr => taggedTypes.dataRangeUUID(dr.uuid.toString))))
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
        taggedTypes.entityScalarDataPropertyUniversalRestrictionAxiomUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.entityUUID(y.restrictedEntity.uuid.toString),
        taggedTypes.entityScalarDataPropertyUUID(y.scalarProperty.uuid.toString),
        taggedTypes.dataRangeUUID(y.scalarRestriction.uuid.toString)))
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
        taggedTypes.entityStructuredDataPropertyParticularRestrictionAxiomUUID(y.uuid.toString),
        taggedTypes.terminologyBoxUUID(x._1.uuid.toString),
        taggedTypes.structuredDataPropertyUUID(y.structuredDataProperty.uuid.toString),
        taggedTypes.entityUUID(y.restrictedEntity.uuid.toString)))
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
        taggedTypes.restrictionStructuredDataPropertyTupleUUID(r.uuid.toString),
        taggedTypes.structuredDataPropertyUUID(r.structuredDataProperty.uuid.toString),
        taggedTypes.restrictionStructuredDataPropertyContextUUID(c.uuid.toString))
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
            taggedTypes.restrictionScalarDataPropertyValueUUID(v.uuid.toString),
            taggedTypes.dataRelationshipToScalarUUID(v.scalarDataProperty.uuid.toString),
            v.scalarPropertyValue,
            taggedTypes.restrictionStructuredDataPropertyContextUUID(c.uuid.toString),
            v.valueType.map(dr => taggedTypes.dataRangeUUID(dr.uuid.toString)))
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
   x: (api.TerminologyBox, Set[api.TerminologyBundleAxiom]))
  : Seq[tables.BundledTerminologyAxiom]
  = acc ++ x._2.flatMap {
    case y: api.BundledTerminologyAxiom =>
      Some(tables.BundledTerminologyAxiom(
        taggedTypes.bundledTerminologyAxiomUUID(y.uuid.toString),
        taggedTypes.bundleUUID(x._1.uuid.toString),
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
          taggedTypes.anonymousConceptUnionAxiomUUID(y.uuid.toString),
          taggedTypes.conceptTreeDisjunctionUUID(y.conceptTreeDisjunctionParent().get.uuid.toString),
          y.name))
    case _ =>
      None
  }.to[Seq]

  def convertRootConceptTaxonomyAxioms
  (acc: Seq[tables.RootConceptTaxonomyAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBundleStatement]))
  : Seq[tables.RootConceptTaxonomyAxiom]
  = acc ++ x._2.flatMap {
    case y: api.RootConceptTaxonomyAxiom =>
      Some(tables.RootConceptTaxonomyAxiom(
        taggedTypes.rootConceptTaxonomyAxiomUUID(y.uuid.toString),
        taggedTypes.bundleUUID(x._1.uuid.toString),
        taggedTypes.conceptUUID(y.root.uuid.toString)))
    case _ =>
      None
  }.to[Seq]

  def convertSpecificDisjointConceptAxioms
  (acc: Seq[tables.SpecificDisjointConceptAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBundleStatement]))
  (implicit ext: api.Extent)
  : Seq[tables.SpecificDisjointConceptAxiom]
  = acc ++ x._2.flatMap {
    case y: api.SpecificDisjointConceptAxiom =>
      Some(tables.SpecificDisjointConceptAxiom(
          taggedTypes.specificDisjointConceptAxiomUUID(y.uuid.toString),
          taggedTypes.conceptTreeDisjunctionUUID(y.conceptTreeDisjunctionParent().get.uuid.toString),
          taggedTypes.conceptUUID(y.disjointLeaf.uuid.toString)))
    case _ =>
      None
  }.to[Seq]

  def convertConceptInstances
  (acc: Seq[tables.ConceptInstance],
   x: (api.ConceptInstance, api.DescriptionBox))
  : Seq[tables.ConceptInstance]
  = acc :+ tables.ConceptInstance(
        taggedTypes.conceptInstanceUUID(x._1.uuid.toString),
        taggedTypes.descriptionBoxUUID(x._2.uuid.toString),
        taggedTypes.conceptUUID(x._1.singletonConceptClassifier.uuid.toString),
        x._1.name)

  def convertDescriptionBoxExtendsClosedWorldDefinitions
  (acc: Seq[tables.DescriptionBoxExtendsClosedWorldDefinitions],
   x: (api.DescriptionBoxExtendsClosedWorldDefinitions, api.DescriptionBox))
  : Seq[tables.DescriptionBoxExtendsClosedWorldDefinitions]
  = acc :+ tables.DescriptionBoxExtendsClosedWorldDefinitions(
    taggedTypes.descriptionBoxExtendsClosedWorldDefinitionsUUID(x._1.uuid.toString),
    taggedTypes.descriptionBoxUUID(x._2.uuid.toString),
    x._1.closedWorldDefinitions)

  def convertDescriptionBoxRefinements
  (acc: Seq[tables.DescriptionBoxRefinement],
   x: (api.DescriptionBoxRefinement, api.DescriptionBox))
  : Seq[tables.DescriptionBoxRefinement]
  = acc :+ tables.DescriptionBoxRefinement(
    taggedTypes.descriptionBoxRefinementUUID(x._1.uuid.toString),
    taggedTypes.descriptionBoxUUID(x._2.uuid.toString),
    x._1.refinedDescriptionBox)

  def convertReifiedRelationshipInstances
  (acc: Seq[tables.ReifiedRelationshipInstance],
   x: (api.ReifiedRelationshipInstance, api.DescriptionBox))
  : Seq[tables.ReifiedRelationshipInstance]
  = acc :+ tables.ReifiedRelationshipInstance(
    taggedTypes.reifiedRelationshipInstanceUUID(x._1.uuid.toString),
    taggedTypes.descriptionBoxUUID(x._2.uuid.toString),
    taggedTypes.reifiedRelationshipUUID(x._1.singletonReifiedRelationshipClassifier.uuid.toString),
    x._1.name)

  def convertReifiedRelationshipInstanceDomains
  (acc: Seq[tables.ReifiedRelationshipInstanceDomain],
   x: (api.ReifiedRelationshipInstanceDomain, api.DescriptionBox))
  : Seq[tables.ReifiedRelationshipInstanceDomain]
  = acc :+ tables.ReifiedRelationshipInstanceDomain(
    taggedTypes.reifiedRelationshipInstanceDomainUUID(x._1.uuid.toString),
    taggedTypes.descriptionBoxUUID(x._2.uuid.toString),
    taggedTypes.reifiedRelationshipInstanceUUID(x._1.reifiedRelationshipInstance.uuid.toString),
    taggedTypes.conceptualEntitySingletonInstanceUUID(x._1.domain.uuid.toString))

  def convertReifiedRelationshipInstanceRanges
  (acc: Seq[tables.ReifiedRelationshipInstanceRange],
   x: (api.ReifiedRelationshipInstanceRange, api.DescriptionBox))
  : Seq[tables.ReifiedRelationshipInstanceRange]
  = acc :+ tables.ReifiedRelationshipInstanceRange(
    taggedTypes.reifiedRelationshipInstanceRangeUUID(x._1.uuid.toString),
    taggedTypes.descriptionBoxUUID(x._2.uuid.toString),
    taggedTypes.reifiedRelationshipInstanceUUID(x._1.reifiedRelationshipInstance.uuid.toString),
    taggedTypes.conceptualEntitySingletonInstanceUUID(x._1.range.uuid.toString))

  def convertUnreifiedRelationshipInstanceTuples
  (acc: Seq[tables.UnreifiedRelationshipInstanceTuple],
   x: (api.UnreifiedRelationshipInstanceTuple, api.DescriptionBox))
  : Seq[tables.UnreifiedRelationshipInstanceTuple]
  = acc :+ tables.UnreifiedRelationshipInstanceTuple(
    taggedTypes.unreifiedRelationshipInstanceTupleUUID(x._1.uuid.toString),
    taggedTypes.descriptionBoxUUID(x._2.uuid.toString),
    taggedTypes.unreifiedRelationshipUUID(x._1.unreifiedRelationship.uuid.toString),
    taggedTypes.conceptualEntitySingletonInstanceUUID(x._1.domain.uuid.toString),
    taggedTypes.conceptualEntitySingletonInstanceUUID(x._1.range.uuid.toString))

  def convertSingletonScalarDataPropertyValues
  (acc: Seq[tables.SingletonInstanceScalarDataPropertyValue],
   x: (api.DescriptionBox, Set[api.SingletonInstanceScalarDataPropertyValue]))
  : Seq[tables.SingletonInstanceScalarDataPropertyValue]
  = x._2.foldLeft[Seq[tables.SingletonInstanceScalarDataPropertyValue]](acc) { case (acc1, v) =>
    acc1 :+ tables.SingletonInstanceScalarDataPropertyValue(
      taggedTypes.singletonInstanceScalarDataPropertyValueUUID(v.uuid.toString),
      taggedTypes.descriptionBoxUUID(x._1.uuid.toString),
      taggedTypes.conceptualEntitySingletonInstanceUUID(v.singletonInstance.uuid.toString),
      taggedTypes.entityScalarDataPropertyUUID(v.scalarDataProperty.uuid.toString),
      v.scalarPropertyValue,
      v.valueType.map(dr => taggedTypes.dataRangeUUID(dr.uuid.toString)))
  }

  def convertSingletonStructuredDataPropertyValues
  (acc: Seq[tables.SingletonInstanceStructuredDataPropertyValue],
   x: (api.DescriptionBox, Set[api.SingletonInstanceStructuredDataPropertyValue]))
  : Seq[tables.SingletonInstanceStructuredDataPropertyValue]
  = x._2.foldLeft[Seq[tables.SingletonInstanceStructuredDataPropertyValue]](acc) { case (acc1, v) =>
    acc1 :+ tables.SingletonInstanceStructuredDataPropertyValue(
      taggedTypes.singletonInstanceStructuredDataPropertyValueUUID(v.uuid.toString),
      taggedTypes.descriptionBoxUUID(x._1.uuid.toString),
      taggedTypes.conceptualEntitySingletonInstanceUUID(v.singletonInstance.uuid.toString),
      taggedTypes.dataRelationshipToStructureUUID(v.structuredDataProperty.uuid.toString))
  }

  def convertScalarDataPropertyValues
  (acc: Seq[tables.ScalarDataPropertyValue],
   x: (api.SingletonInstanceStructuredDataPropertyContext, Set[api.ScalarDataPropertyValue]))
  : Seq[tables.ScalarDataPropertyValue]
  = x._2.foldLeft[Seq[tables.ScalarDataPropertyValue]](acc) { case (acc1, v) =>
    acc1 :+ tables.ScalarDataPropertyValue(
      taggedTypes.scalarDataPropertyValueUUID(v.uuid.toString),
      taggedTypes.dataRelationshipToScalarUUID(v.scalarDataProperty.uuid.toString),
      v.scalarPropertyValue,
      taggedTypes.singletonInstanceStructuredDataPropertyContextUUID(x._1.uuid.toString),
      v.valueType.map(dr => taggedTypes.dataRangeUUID(dr.uuid.toString)))
  }

  def convertStructuredDataPropertyTuples
  (acc: Seq[tables.StructuredDataPropertyTuple],
   x: (api.SingletonInstanceStructuredDataPropertyContext, Set[api.StructuredDataPropertyTuple]))
  : Seq[tables.StructuredDataPropertyTuple]
  = x._2.foldLeft[Seq[tables.StructuredDataPropertyTuple]](acc) { case (acc1, v) =>
    acc1 :+ tables.StructuredDataPropertyTuple(
      taggedTypes.structuredDataPropertyTupleUUID(v.uuid.toString),
      taggedTypes.dataRelationshipToStructureUUID(v.structuredDataProperty.uuid.toString),
      taggedTypes.singletonInstanceStructuredDataPropertyContextUUID(x._1.uuid.toString))
  }

  def convertAnnotationPropertyValue
  (apv: api.AnnotationPropertyValue)
  : tables.AnnotationPropertyValue
  = tables.AnnotationPropertyValue(
      taggedTypes.annotationPropertyValueUUID(apv.uuid.toString),
      taggedTypes.logicalElementUUID(apv.subject.uuid.toString),
      taggedTypes.annotationPropertyUUID(apv.property.uuid.toString),
      apv.value)

  def convert
  (e: api.Extent)
  : tables.OMLSpecificationTables
  = {
    implicit val ext: api.Extent = e
    val t =
      tables
        .OMLSpecificationTables.createEmptyOMLSpecificationTables()
        .copy(
          annotationProperties = e.annotationProperties.values
            .foldLeft[Seq[tables.AnnotationProperty]](Seq.empty)(convertAnnotationProperty)
            .sortBy(_.uuid.toString),

          // modules

          terminologyGraphs = e.terminologyGraphs.values
            .foldLeft[Seq[tables.TerminologyGraph]](Seq.empty)(convertTerminologyGraph)
            .sortBy(_.uuid.toString),

          bundles = e.bundles.values
            .foldLeft[Seq[tables.Bundle]](Seq.empty)(convertBundle)
            .sortBy(_.uuid.toString),

          descriptionBoxes = e.descriptionBoxes.values
            .foldLeft[Seq[tables.DescriptionBox]](Seq.empty)(convertDescriptionBox)
            .sortBy(_.uuid.toString),

          // terms

          aspects = e.boxStatements
            .foldLeft[Seq[tables.Aspect]](Seq.empty)(convertAspects)
            .sortBy(_.uuid.toString),

          concepts = e.boxStatements
            .foldLeft[Seq[tables.Concept]](Seq.empty)(convertConcepts)
            .sortBy(_.uuid.toString),

          reifiedRelationships = e.boxStatements
            .foldLeft[Seq[tables.ReifiedRelationship]](Seq.empty)(convertReifiedRelationships)
            .sortBy(_.uuid.toString),

          unreifiedRelationships = e.boxStatements
            .foldLeft[Seq[tables.UnreifiedRelationship]](Seq.empty)(convertUnreifiedRelationships)
            .sortBy(_.uuid.toString),

          scalars = e.boxStatements
            .foldLeft[Seq[tables.Scalar]](Seq.empty)(convertScalars)
            .sortBy(_.uuid.toString),

          structures = e.boxStatements
            .foldLeft[Seq[tables.Structure]](Seq.empty)(convertStructures)
            .sortBy(_.uuid.toString),

          chainRules = e.boxStatements
            .foldLeft[Seq[tables.ChainRule]](Seq.empty)(convertChainRules)
            .sortBy(_.uuid.toString),

          ruleBodySegments = e.boxStatements
            .foldLeft[Seq[tables.RuleBodySegment]](Seq.empty)(convertRuleBodySegments(e))
            .sortBy(_.uuid.toString),

          aspectPredicates = e.boxStatements
            .foldLeft[Seq[tables.AspectPredicate]](Seq.empty)(convertAspectPredicates(e))
            .sortBy(_.uuid.toString),

          conceptPredicates = e.boxStatements
            .foldLeft[Seq[tables.ConceptPredicate]](Seq.empty)(convertConceptPredicates(e))
            .sortBy(_.uuid.toString),

          reifiedRelationshipPredicates = e.boxStatements
            .foldLeft[Seq[tables.ReifiedRelationshipPredicate]](Seq.empty)(convertReifiedRelationshipPredicates(e))
            .sortBy(_.uuid.toString),

          reifiedRelationshipPropertyPredicates = e.boxStatements
            .foldLeft[Seq[tables.ReifiedRelationshipPropertyPredicate]](Seq.empty)(convertReifiedRelationshipPropertyPredicates(e))
            .sortBy(_.uuid.toString),

          reifiedRelationshipSourcePropertyPredicates = e.boxStatements
            .foldLeft[Seq[tables.ReifiedRelationshipSourcePropertyPredicate]](Seq.empty)(convertReifiedRelationshipSourcePropertyPredicates(e))
            .sortBy(_.uuid.toString),

          reifiedRelationshipTargetPropertyPredicates = e.boxStatements
            .foldLeft[Seq[tables.ReifiedRelationshipTargetPropertyPredicate]](Seq.empty)(convertReifiedRelationshipTargetPropertyPredicates(e))
            .sortBy(_.uuid.toString),

          unreifiedRelationshipPropertyPredicates = e.boxStatements
            .foldLeft[Seq[tables.UnreifiedRelationshipPropertyPredicate]](Seq.empty)(convertUnreifiedRelationshipPropertyPredicates(e))
            .sortBy(_.uuid.toString),

          reifiedRelationshipInversePropertyPredicates = e.boxStatements
            .foldLeft[Seq[tables.ReifiedRelationshipInversePropertyPredicate]](Seq.empty)(convertReifiedRelationshipInversePropertyPredicates(e))
            .sortBy(_.uuid.toString),

          reifiedRelationshipSourceInversePropertyPredicates = e.boxStatements
            .foldLeft[Seq[tables.ReifiedRelationshipSourceInversePropertyPredicate]](Seq.empty)(convertReifiedRelationshipSourceInversePropertyPredicates(e))
            .sortBy(_.uuid.toString),

          reifiedRelationshipTargetInversePropertyPredicates = e.boxStatements
            .foldLeft[Seq[tables.ReifiedRelationshipTargetInversePropertyPredicate]](Seq.empty)(convertReifiedRelationshipTargetInversePropertyPredicates(e))
            .sortBy(_.uuid.toString),

          unreifiedRelationshipInversePropertyPredicates = e.boxStatements
            .foldLeft[Seq[tables.UnreifiedRelationshipInversePropertyPredicate]](Seq.empty)(convertUnreifiedRelationshipInversePropertyPredicates(e))
            .sortBy(_.uuid.toString),

          // data ranges

          binaryScalarRestrictions = e.boxStatements
            .foldLeft[Seq[tables.BinaryScalarRestriction]](Seq.empty)(convertBinaryScalarRestrictions)
            .sortBy(_.uuid.toString),

          iriScalarRestrictions = e.boxStatements
            .foldLeft[Seq[tables.IRIScalarRestriction]](Seq.empty)(convertIRIScalarRestrictions)
            .sortBy(_.uuid.toString),

          numericScalarRestrictions = e.boxStatements
            .foldLeft[Seq[tables.NumericScalarRestriction]](Seq.empty)(convertNumericScalarRestrictions)
            .sortBy(_.uuid.toString),

          plainLiteralScalarRestrictions = e.boxStatements
            .foldLeft[Seq[tables.PlainLiteralScalarRestriction]](Seq.empty)(convertPlainLiteralScalarRestrictions)
            .sortBy(_.uuid.toString),

          stringScalarRestrictions = e.boxStatements
            .foldLeft[Seq[tables.StringScalarRestriction]](Seq.empty)(convertStringScalarRestrictions)
            .sortBy(_.uuid.toString),

          synonymScalarRestrictions = e.boxStatements
            .foldLeft[Seq[tables.SynonymScalarRestriction]](Seq.empty)(convertSynonymScalarRestrictions)
            .sortBy(_.uuid.toString),

          timeScalarRestrictions = e.boxStatements
            .foldLeft[Seq[tables.TimeScalarRestriction]](Seq.empty)(convertTimeScalarRestrictions)
            .sortBy(_.uuid.toString),

          scalarOneOfRestrictions = e.boxStatements
            .foldLeft[Seq[tables.ScalarOneOfRestriction]](Seq.empty)(convertScalarOneOfRestrictions)
            .sortBy(_.uuid.toString),

          scalarOneOfLiteralAxioms = e.boxStatements
            .foldLeft[Seq[tables.ScalarOneOfLiteralAxiom]](Seq.empty)(convertScalarOneOfLiteralAxioms)
            .sortBy(_.uuid.toString),

          // data properties

          scalarDataProperties = e.boxStatements
            .foldLeft[Seq[tables.ScalarDataProperty]](Seq.empty)(convertScalarDataProperties)
            .sortBy(_.uuid.toString),

          structuredDataProperties = e.boxStatements
            .foldLeft[Seq[tables.StructuredDataProperty]](Seq.empty)(convertStructuredDataProperties)
            .sortBy(_.uuid.toString),

          entityScalarDataProperties = e.boxStatements
            .foldLeft[Seq[tables.EntityScalarDataProperty]](Seq.empty)(convertEntityScalarDataProperties)
            .sortBy(_.uuid.toString),

          entityStructuredDataProperties = e.boxStatements
            .foldLeft[Seq[tables.EntityStructuredDataProperty]](Seq.empty)(convertEntityStructuredDataProperties)
            .sortBy(_.uuid.toString),

          // terminology box axioms

          conceptDesignationTerminologyAxioms = e.boxAxioms
            .foldLeft[Seq[tables.ConceptDesignationTerminologyAxiom]](Seq.empty)(convertConceptDesignationTerminologyAxioms)
            .sortBy(_.uuid.toString),

          terminologyExtensionAxioms = e.boxAxioms
            .foldLeft[Seq[tables.TerminologyExtensionAxiom]](Seq.empty)(convertTerminologyExtensionAxioms)
            .sortBy(_.uuid.toString),

          terminologyNestingAxioms = e.boxAxioms
            .foldLeft[Seq[tables.TerminologyNestingAxiom]](Seq.empty)(convertTerminologyNestingAxioms)
            .sortBy(_.uuid.toString),

          // terminology box statements

          aspectSpecializationAxioms = e.boxStatements
            .foldLeft[Seq[tables.AspectSpecializationAxiom]](Seq.empty)(convertAspectSpecializationAxioms)
            .sortBy(_.uuid.toString),

          conceptSpecializationAxioms = e.boxStatements
            .foldLeft[Seq[tables.ConceptSpecializationAxiom]](Seq.empty)(convertConceptSpecializationAxioms)
            .sortBy(_.uuid.toString),

          reifiedRelationshipSpecializationAxioms = e.boxStatements
            .foldLeft[Seq[tables.ReifiedRelationshipSpecializationAxiom]](Seq.empty)(convertReifiedRelationshipSpecializationAxioms)
            .sortBy(_.uuid.toString),

          entityExistentialRestrictionAxioms = e.boxStatements
            .foldLeft[Seq[tables.EntityExistentialRestrictionAxiom]](Seq.empty)(convertEntityExistentialRestrictionAxioms)
            .sortBy(_.uuid.toString),

          entityUniversalRestrictionAxioms = e.boxStatements
            .foldLeft[Seq[tables.EntityUniversalRestrictionAxiom]](Seq.empty)(convertEntityUniversalRestrictionAxioms)
            .sortBy(_.uuid.toString),

          entityScalarDataPropertyExistentialRestrictionAxioms = e.boxStatements
            .foldLeft[Seq[tables.EntityScalarDataPropertyExistentialRestrictionAxiom]](Seq.empty)(convertEntityScalarDataPropertyExistentialRestrictionAxioms)
            .sortBy(_.uuid.toString),

          entityScalarDataPropertyParticularRestrictionAxioms = e.boxStatements
            .foldLeft[Seq[tables.EntityScalarDataPropertyParticularRestrictionAxiom]](Seq.empty)(convertEntityScalarDataPropertyParticularRestrictionAxioms)
            .sortBy(_.uuid.toString),

          entityScalarDataPropertyUniversalRestrictionAxioms = e.boxStatements
            .foldLeft[Seq[tables.EntityScalarDataPropertyUniversalRestrictionAxiom]](Seq.empty)(convertEntityScalarDataPropertyUniversalRestrictionAxioms)
            .sortBy(_.uuid.toString),

          entityStructuredDataPropertyParticularRestrictionAxioms = e.boxStatements
            .foldLeft[Seq[tables.EntityStructuredDataPropertyParticularRestrictionAxiom]](Seq.empty)(convertEntityStructuredDataPropertyParticularRestrictionAxioms)
            .sortBy(_.uuid.toString),

          restrictionStructuredDataPropertyTuples = e.boxStatements
            .foldLeft[Seq[tables.RestrictionStructuredDataPropertyTuple]](Seq.empty)(convertRestrictionStructuredDataPropertyTuples(e))
            .sortBy(_.uuid.toString),

          restrictionScalarDataPropertyValues = e.boxStatements
              .foldLeft[Seq[tables.RestrictionScalarDataPropertyValue]](Seq.empty)(convertRestrictionScalarDataPropertyValues(e))
              .sortBy(_.uuid.toString),

          // bundle axioms

          bundledTerminologyAxioms = e.bundleAxioms
            .foldLeft[Seq[tables.BundledTerminologyAxiom]](Seq.empty)(convertBundledTerminologyAxioms)
            .sortBy(_.uuid.toString),

          // bundle statements

          anonymousConceptUnionAxioms = e.bundleStatements
            .foldLeft[Seq[tables.AnonymousConceptUnionAxiom]](Seq.empty)(convertAnonymousConceptUnionAxioms)
            .sortBy(_.uuid.toString),

          rootConceptTaxonomyAxioms = e.bundleStatements
            .foldLeft[Seq[tables.RootConceptTaxonomyAxiom]](Seq.empty)(convertRootConceptTaxonomyAxioms)
            .sortBy(_.uuid.toString),

          specificDisjointConceptAxioms = e.bundleStatements
            .foldLeft[Seq[tables.SpecificDisjointConceptAxiom]](Seq.empty)(convertSpecificDisjointConceptAxioms)
            .sortBy(_.uuid.toString),

          // description box statements

          conceptInstances = e.descriptionBoxOfConceptInstance
            .foldLeft[Seq[tables.ConceptInstance]](Seq.empty)(convertConceptInstances)
            .sortBy(_.uuid.toString),

          descriptionBoxExtendsClosedWorldDefinitions = e.descriptionBoxOfDescriptionBoxExtendsClosedWorldDefinitions
            .foldLeft[Seq[tables.DescriptionBoxExtendsClosedWorldDefinitions]](Seq.empty)(convertDescriptionBoxExtendsClosedWorldDefinitions)
            .sortBy(_.uuid.toString),

          descriptionBoxRefinements = e.descriptionBoxOfDescriptionBoxRefinement
            .foldLeft[Seq[tables.DescriptionBoxRefinement]](Seq.empty)(convertDescriptionBoxRefinements)
            .sortBy(_.uuid.toString),

          reifiedRelationshipInstances = e.descriptionBoxOfReifiedRelationshipInstance
            .foldLeft[Seq[tables.ReifiedRelationshipInstance]](Seq.empty)(convertReifiedRelationshipInstances)
            .sortBy(_.uuid.toString),

          reifiedRelationshipInstanceDomains = e.descriptionBoxOfReifiedRelationshipInstanceDomain
            .foldLeft[Seq[tables.ReifiedRelationshipInstanceDomain]](Seq.empty)(convertReifiedRelationshipInstanceDomains)
            .sortBy(_.uuid.toString),

          reifiedRelationshipInstanceRanges = e.descriptionBoxOfReifiedRelationshipInstanceRange
            .foldLeft[Seq[tables.ReifiedRelationshipInstanceRange]](Seq.empty)(convertReifiedRelationshipInstanceRanges)
            .sortBy(_.uuid.toString),

          unreifiedRelationshipInstanceTuples = e.descriptionBoxOfUnreifiedRelationshipInstanceTuple
            .foldLeft[Seq[tables.UnreifiedRelationshipInstanceTuple]](Seq.empty)(convertUnreifiedRelationshipInstanceTuples)
            .sortBy(_.uuid.toString),

          singletonInstanceScalarDataPropertyValues = e.singletonScalarDataPropertyValues
          .foldLeft[Seq[tables.SingletonInstanceScalarDataPropertyValue]](Seq.empty)(convertSingletonScalarDataPropertyValues)
          .sortBy(_.uuid.toString),

          singletonInstanceStructuredDataPropertyValues = e.singletonStructuredDataPropertyValues
            .foldLeft[Seq[tables.SingletonInstanceStructuredDataPropertyValue]](Seq.empty)(convertSingletonStructuredDataPropertyValues)
            .sortBy(_.uuid.toString),

          structuredDataPropertyTuples = e.structuredPropertyTuples
            .foldLeft[Seq[tables.StructuredDataPropertyTuple]](Seq.empty)(convertStructuredDataPropertyTuples)
            .sortBy(_.uuid.toString),

          scalarDataPropertyValues = e.scalarDataPropertyValues
            .foldLeft[Seq[tables.ScalarDataPropertyValue]](Seq.empty)(convertScalarDataPropertyValues)
            .sortBy(_.uuid.toString),

          annotationPropertyValues = e.annotationPropertyValueByUUID.values
            .map(convertAnnotationPropertyValue)
            .to[Seq]
            .sortBy(_.uuid.toString)
        )

    t
  }

}
