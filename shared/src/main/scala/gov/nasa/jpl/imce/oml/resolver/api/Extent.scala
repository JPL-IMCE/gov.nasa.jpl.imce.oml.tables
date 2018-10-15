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

package gov.nasa.jpl.imce.oml.resolver.api

import java.lang.IllegalArgumentException
import scala.collection.immutable.{::, HashMap, Map, Nil, Set}
import scala.util.{Failure,Success,Try}
import scala.{Option,None,Some,StringContext}

// Container types:
// - Bundle (bundles)
// - ChainRule (terminologies)
// - ConceptTreeDisjunction (bundles)
// - DescriptionBox (descriptions)
// - InstanceRelationshipEnumerationRestriction (descriptions)
// - LogicalElement (common)
// - Module (common)
// - ReifiedRelationship (terminologies)
// - RestrictionStructuredDataPropertyContext (terminologies)
// - RuleBodySegment (terminologies)
// - SingletonInstanceStructuredDataPropertyContext (descriptions)
// - TerminologyBox (terminologies)
// Contained types:
// - AnnotationProperty (common)
// - AnnotationPropertyValue (common)
// - ConceptInstance (descriptions)
// - DescriptionBoxExtendsClosedWorldDefinitions (descriptions)
// - DescriptionBoxRefinement (descriptions)
// - DisjointUnionOfConceptsAxiom (bundles)
// - ForwardProperty (terminologies)
// - InstanceRelationshipEnumerationRestriction (descriptions)
// - InstanceRelationshipExistentialRangeRestriction (descriptions)
// - InstanceRelationshipOneOfRestriction (descriptions)
// - InstanceRelationshipUniversalRangeRestriction (descriptions)
// - InstanceRelationshipValueRestriction (descriptions)
// - InverseProperty (terminologies)
// - ReifiedRelationshipInstance (descriptions)
// - ReifiedRelationshipInstanceDomain (descriptions)
// - ReifiedRelationshipInstanceRange (descriptions)
// - RestrictionScalarDataPropertyValue (terminologies)
// - RestrictionStructuredDataPropertyTuple (terminologies)
// - RuleBodySegment (terminologies)
// - ScalarDataPropertyValue (descriptions)
// - SegmentPredicate (terminologies)
// - SingletonInstanceScalarDataPropertyValue (descriptions)
// - SingletonInstanceStructuredDataPropertyValue (descriptions)
// - StructuredDataPropertyTuple (descriptions)
// - TerminologyBoxAxiom (terminologies)
// - TerminologyBoxStatement (terminologies)
// - TerminologyBundleAxiom (bundles)
// - TerminologyBundleStatement (bundles)
// - UnreifiedRelationshipInstanceTuple (descriptions)
/*
 * An OML Extent is an in-memory store of all OML Element(s)
 * loaded from external OML documents.
 */
case class Extent
( terminologyGraphs
  : Map[taggedTypes.ModuleUUID, TerminologyGraph] 
  = HashMap.empty[taggedTypes.ModuleUUID, TerminologyGraph],
  bundles
  : Map[taggedTypes.ModuleUUID, Bundle] 
  = HashMap.empty[taggedTypes.ModuleUUID, Bundle],
  descriptionBoxes
  : Map[taggedTypes.ModuleUUID, DescriptionBox] 
  = HashMap.empty[taggedTypes.ModuleUUID, DescriptionBox],

  annotations
  : Map[LogicalElement, Set[AnnotationPropertyValue]]
  = HashMap.empty[LogicalElement, Set[AnnotationPropertyValue]],
  annotationProperties
  : Map[Module, Set[AnnotationProperty]]
  = HashMap.empty[Module, Set[AnnotationProperty]],
  boxAxioms
  : Map[TerminologyBox, Set[TerminologyBoxAxiom]]
  = HashMap.empty[TerminologyBox, Set[TerminologyBoxAxiom]],
  boxStatements
  : Map[TerminologyBox, Set[TerminologyBoxStatement]]
  = HashMap.empty[TerminologyBox, Set[TerminologyBoxStatement]],
  forwardProperty
  : Map[ReifiedRelationship, ForwardProperty]
  = HashMap.empty[ReifiedRelationship, ForwardProperty],
  inverseProperty
  : Map[ReifiedRelationship, InverseProperty]
  = HashMap.empty[ReifiedRelationship, InverseProperty],
  firstSegment
  : Map[ChainRule, RuleBodySegment]
  = HashMap.empty[ChainRule, RuleBodySegment],
  predicate
  : Map[RuleBodySegment, SegmentPredicate]
  = HashMap.empty[RuleBodySegment, SegmentPredicate],
  nextSegment
  : Map[RuleBodySegment, RuleBodySegment]
  = HashMap.empty[RuleBodySegment, RuleBodySegment],
  structuredDataPropertyRestrictions
  : Map[RestrictionStructuredDataPropertyContext, Set[RestrictionStructuredDataPropertyTuple]]
  = HashMap.empty[RestrictionStructuredDataPropertyContext, Set[RestrictionStructuredDataPropertyTuple]],
  scalarDataPropertyRestrictions
  : Map[RestrictionStructuredDataPropertyContext, Set[RestrictionScalarDataPropertyValue]]
  = HashMap.empty[RestrictionStructuredDataPropertyContext, Set[RestrictionScalarDataPropertyValue]],
  bundleAxioms
  : Map[Bundle, Set[TerminologyBundleAxiom]]
  = HashMap.empty[Bundle, Set[TerminologyBundleAxiom]],
  bundleStatements
  : Map[Bundle, Set[TerminologyBundleStatement]]
  = HashMap.empty[Bundle, Set[TerminologyBundleStatement]],
  disjunctions
  : Map[ConceptTreeDisjunction, Set[DisjointUnionOfConceptsAxiom]]
  = HashMap.empty[ConceptTreeDisjunction, Set[DisjointUnionOfConceptsAxiom]],
  descriptionBoxRefinements
  : Map[DescriptionBox, Set[DescriptionBoxRefinement]]
  = HashMap.empty[DescriptionBox, Set[DescriptionBoxRefinement]],
  closedWorldDefinitions
  : Map[DescriptionBox, Set[DescriptionBoxExtendsClosedWorldDefinitions]]
  = HashMap.empty[DescriptionBox, Set[DescriptionBoxExtendsClosedWorldDefinitions]],
  conceptInstances
  : Map[DescriptionBox, Set[ConceptInstance]]
  = HashMap.empty[DescriptionBox, Set[ConceptInstance]],
  reifiedRelationshipInstances
  : Map[DescriptionBox, Set[ReifiedRelationshipInstance]]
  = HashMap.empty[DescriptionBox, Set[ReifiedRelationshipInstance]],
  instanceRelationshipEnumerationRestrictions
  : Map[DescriptionBox, Set[InstanceRelationshipEnumerationRestriction]]
  = HashMap.empty[DescriptionBox, Set[InstanceRelationshipEnumerationRestriction]],
  instanceRelationshipValueRestrictions
  : Map[DescriptionBox, Set[InstanceRelationshipValueRestriction]]
  = HashMap.empty[DescriptionBox, Set[InstanceRelationshipValueRestriction]],
  instanceRelationshipExistentialRangeRestrictions
  : Map[DescriptionBox, Set[InstanceRelationshipExistentialRangeRestriction]]
  = HashMap.empty[DescriptionBox, Set[InstanceRelationshipExistentialRangeRestriction]],
  instanceRelationshipUniversalRangeRestrictions
  : Map[DescriptionBox, Set[InstanceRelationshipUniversalRangeRestriction]]
  = HashMap.empty[DescriptionBox, Set[InstanceRelationshipUniversalRangeRestriction]],
  reifiedRelationshipInstanceDomains
  : Map[DescriptionBox, Set[ReifiedRelationshipInstanceDomain]]
  = HashMap.empty[DescriptionBox, Set[ReifiedRelationshipInstanceDomain]],
  reifiedRelationshipInstanceRanges
  : Map[DescriptionBox, Set[ReifiedRelationshipInstanceRange]]
  = HashMap.empty[DescriptionBox, Set[ReifiedRelationshipInstanceRange]],
  unreifiedRelationshipInstanceTuples
  : Map[DescriptionBox, Set[UnreifiedRelationshipInstanceTuple]]
  = HashMap.empty[DescriptionBox, Set[UnreifiedRelationshipInstanceTuple]],
  singletonScalarDataPropertyValues
  : Map[DescriptionBox, Set[SingletonInstanceScalarDataPropertyValue]]
  = HashMap.empty[DescriptionBox, Set[SingletonInstanceScalarDataPropertyValue]],
  singletonStructuredDataPropertyValues
  : Map[DescriptionBox, Set[SingletonInstanceStructuredDataPropertyValue]]
  = HashMap.empty[DescriptionBox, Set[SingletonInstanceStructuredDataPropertyValue]],
  structuredPropertyTuples
  : Map[SingletonInstanceStructuredDataPropertyContext, Set[StructuredDataPropertyTuple]]
  = HashMap.empty[SingletonInstanceStructuredDataPropertyContext, Set[StructuredDataPropertyTuple]],
  scalarDataPropertyValues
  : Map[SingletonInstanceStructuredDataPropertyContext, Set[ScalarDataPropertyValue]]
  = HashMap.empty[SingletonInstanceStructuredDataPropertyContext, Set[ScalarDataPropertyValue]],
  references
  : Map[InstanceRelationshipEnumerationRestriction, Set[InstanceRelationshipOneOfRestriction]]
  = HashMap.empty[InstanceRelationshipEnumerationRestriction, Set[InstanceRelationshipOneOfRestriction]],

  logicalElementOfAnnotationPropertyValue
  : Map[AnnotationPropertyValue, LogicalElement]
  = HashMap.empty[AnnotationPropertyValue, LogicalElement],
  moduleOfAnnotationProperty
  : Map[AnnotationProperty, Module]
  = HashMap.empty[AnnotationProperty, Module],
  terminologyBoxOfTerminologyBoxAxiom
  : Map[TerminologyBoxAxiom, TerminologyBox]
  = HashMap.empty[TerminologyBoxAxiom, TerminologyBox],
  terminologyBoxOfTerminologyBoxStatement
  : Map[TerminologyBoxStatement, TerminologyBox]
  = HashMap.empty[TerminologyBoxStatement, TerminologyBox],
  reifiedRelationshipOfForwardProperty
  : Map[ForwardProperty, ReifiedRelationship]
  = HashMap.empty[ForwardProperty, ReifiedRelationship],
  reifiedRelationshipOfInverseProperty
  : Map[InverseProperty, ReifiedRelationship]
  = HashMap.empty[InverseProperty, ReifiedRelationship],
  chainRuleOfRuleBodySegment
  : Map[RuleBodySegment, ChainRule]
  = HashMap.empty[RuleBodySegment, ChainRule],
  ruleBodySegmentOfSegmentPredicate
  : Map[SegmentPredicate, RuleBodySegment]
  = HashMap.empty[SegmentPredicate, RuleBodySegment],
  ruleBodySegmentOfRuleBodySegment
  : Map[RuleBodySegment, RuleBodySegment]
  = HashMap.empty[RuleBodySegment, RuleBodySegment],
  restrictionStructuredDataPropertyContextOfRestrictionStructuredDataPropertyTuple
  : Map[RestrictionStructuredDataPropertyTuple, RestrictionStructuredDataPropertyContext]
  = HashMap.empty[RestrictionStructuredDataPropertyTuple, RestrictionStructuredDataPropertyContext],
  restrictionStructuredDataPropertyContextOfRestrictionScalarDataPropertyValue
  : Map[RestrictionScalarDataPropertyValue, RestrictionStructuredDataPropertyContext]
  = HashMap.empty[RestrictionScalarDataPropertyValue, RestrictionStructuredDataPropertyContext],
  bundleOfTerminologyBundleAxiom
  : Map[TerminologyBundleAxiom, Bundle]
  = HashMap.empty[TerminologyBundleAxiom, Bundle],
  bundleOfTerminologyBundleStatement
  : Map[TerminologyBundleStatement, Bundle]
  = HashMap.empty[TerminologyBundleStatement, Bundle],
  conceptTreeDisjunctionOfDisjointUnionOfConceptsAxiom
  : Map[DisjointUnionOfConceptsAxiom, ConceptTreeDisjunction]
  = HashMap.empty[DisjointUnionOfConceptsAxiom, ConceptTreeDisjunction],
  descriptionBoxOfDescriptionBoxRefinement
  : Map[DescriptionBoxRefinement, DescriptionBox]
  = HashMap.empty[DescriptionBoxRefinement, DescriptionBox],
  descriptionBoxOfDescriptionBoxExtendsClosedWorldDefinitions
  : Map[DescriptionBoxExtendsClosedWorldDefinitions, DescriptionBox]
  = HashMap.empty[DescriptionBoxExtendsClosedWorldDefinitions, DescriptionBox],
  descriptionBoxOfConceptInstance
  : Map[ConceptInstance, DescriptionBox]
  = HashMap.empty[ConceptInstance, DescriptionBox],
  descriptionBoxOfReifiedRelationshipInstance
  : Map[ReifiedRelationshipInstance, DescriptionBox]
  = HashMap.empty[ReifiedRelationshipInstance, DescriptionBox],
  descriptionBoxOfInstanceRelationshipEnumerationRestriction
  : Map[InstanceRelationshipEnumerationRestriction, DescriptionBox]
  = HashMap.empty[InstanceRelationshipEnumerationRestriction, DescriptionBox],
  descriptionBoxOfInstanceRelationshipValueRestriction
  : Map[InstanceRelationshipValueRestriction, DescriptionBox]
  = HashMap.empty[InstanceRelationshipValueRestriction, DescriptionBox],
  descriptionBoxOfInstanceRelationshipExistentialRangeRestriction
  : Map[InstanceRelationshipExistentialRangeRestriction, DescriptionBox]
  = HashMap.empty[InstanceRelationshipExistentialRangeRestriction, DescriptionBox],
  descriptionBoxOfInstanceRelationshipUniversalRangeRestriction
  : Map[InstanceRelationshipUniversalRangeRestriction, DescriptionBox]
  = HashMap.empty[InstanceRelationshipUniversalRangeRestriction, DescriptionBox],
  descriptionBoxOfReifiedRelationshipInstanceDomain
  : Map[ReifiedRelationshipInstanceDomain, DescriptionBox]
  = HashMap.empty[ReifiedRelationshipInstanceDomain, DescriptionBox],
  descriptionBoxOfReifiedRelationshipInstanceRange
  : Map[ReifiedRelationshipInstanceRange, DescriptionBox]
  = HashMap.empty[ReifiedRelationshipInstanceRange, DescriptionBox],
  descriptionBoxOfUnreifiedRelationshipInstanceTuple
  : Map[UnreifiedRelationshipInstanceTuple, DescriptionBox]
  = HashMap.empty[UnreifiedRelationshipInstanceTuple, DescriptionBox],
  descriptionBoxOfSingletonInstanceScalarDataPropertyValue
  : Map[SingletonInstanceScalarDataPropertyValue, DescriptionBox]
  = HashMap.empty[SingletonInstanceScalarDataPropertyValue, DescriptionBox],
  descriptionBoxOfSingletonInstanceStructuredDataPropertyValue
  : Map[SingletonInstanceStructuredDataPropertyValue, DescriptionBox]
  = HashMap.empty[SingletonInstanceStructuredDataPropertyValue, DescriptionBox],
  singletonInstanceStructuredDataPropertyContextOfStructuredDataPropertyTuple
  : Map[StructuredDataPropertyTuple, SingletonInstanceStructuredDataPropertyContext]
  = HashMap.empty[StructuredDataPropertyTuple, SingletonInstanceStructuredDataPropertyContext],
  singletonInstanceStructuredDataPropertyContextOfScalarDataPropertyValue
  : Map[ScalarDataPropertyValue, SingletonInstanceStructuredDataPropertyContext]
  = HashMap.empty[ScalarDataPropertyValue, SingletonInstanceStructuredDataPropertyContext],
  instanceRelationshipEnumerationRestrictionOfInstanceRelationshipOneOfRestriction
  : Map[InstanceRelationshipOneOfRestriction, InstanceRelationshipEnumerationRestriction]
  = HashMap.empty[InstanceRelationshipOneOfRestriction, InstanceRelationshipEnumerationRestriction],

  annotationPropertyByUUID
  : Map[taggedTypes.AnnotationPropertyUUID, AnnotationProperty]
  = HashMap.empty[taggedTypes.AnnotationPropertyUUID, AnnotationProperty],
  annotationPropertyValueByUUID
  : Map[taggedTypes.AnnotationPropertyValueUUID, AnnotationPropertyValue]
  = HashMap.empty[taggedTypes.AnnotationPropertyValueUUID, AnnotationPropertyValue],
  conceptInstanceByUUID
  : Map[taggedTypes.ConceptualEntitySingletonInstanceUUID, ConceptInstance]
  = HashMap.empty[taggedTypes.ConceptualEntitySingletonInstanceUUID, ConceptInstance],
  descriptionBoxExtendsClosedWorldDefinitionsByUUID
  : Map[taggedTypes.DescriptionBoxRelationshipUUID, DescriptionBoxExtendsClosedWorldDefinitions]
  = HashMap.empty[taggedTypes.DescriptionBoxRelationshipUUID, DescriptionBoxExtendsClosedWorldDefinitions],
  descriptionBoxRefinementByUUID
  : Map[taggedTypes.DescriptionBoxRelationshipUUID, DescriptionBoxRefinement]
  = HashMap.empty[taggedTypes.DescriptionBoxRelationshipUUID, DescriptionBoxRefinement],
  disjointUnionOfConceptsAxiomByUUID
  : Map[taggedTypes.ElementCrossReferenceTupleUUID, DisjointUnionOfConceptsAxiom]
  = HashMap.empty[taggedTypes.ElementCrossReferenceTupleUUID, DisjointUnionOfConceptsAxiom],
  forwardPropertyByUUID
  : Map[taggedTypes.RestrictableRelationshipUUID, ForwardProperty]
  = HashMap.empty[taggedTypes.RestrictableRelationshipUUID, ForwardProperty],
  instanceRelationshipEnumerationRestrictionByUUID
  : Map[taggedTypes.InstanceRelationshipEnumerationRestrictionUUID, InstanceRelationshipEnumerationRestriction]
  = HashMap.empty[taggedTypes.InstanceRelationshipEnumerationRestrictionUUID, InstanceRelationshipEnumerationRestriction],
  instanceRelationshipExistentialRangeRestrictionByUUID
  : Map[taggedTypes.InstanceRelationshipExistentialRangeRestrictionUUID, InstanceRelationshipExistentialRangeRestriction]
  = HashMap.empty[taggedTypes.InstanceRelationshipExistentialRangeRestrictionUUID, InstanceRelationshipExistentialRangeRestriction],
  instanceRelationshipOneOfRestrictionByUUID
  : Map[taggedTypes.ElementCrossReferenceTupleUUID, InstanceRelationshipOneOfRestriction]
  = HashMap.empty[taggedTypes.ElementCrossReferenceTupleUUID, InstanceRelationshipOneOfRestriction],
  instanceRelationshipUniversalRangeRestrictionByUUID
  : Map[taggedTypes.InstanceRelationshipUniversalRangeRestrictionUUID, InstanceRelationshipUniversalRangeRestriction]
  = HashMap.empty[taggedTypes.InstanceRelationshipUniversalRangeRestrictionUUID, InstanceRelationshipUniversalRangeRestriction],
  instanceRelationshipValueRestrictionByUUID
  : Map[taggedTypes.InstanceRelationshipValueRestrictionUUID, InstanceRelationshipValueRestriction]
  = HashMap.empty[taggedTypes.InstanceRelationshipValueRestrictionUUID, InstanceRelationshipValueRestriction],
  inversePropertyByUUID
  : Map[taggedTypes.RestrictableRelationshipUUID, InverseProperty]
  = HashMap.empty[taggedTypes.RestrictableRelationshipUUID, InverseProperty],
  reifiedRelationshipInstanceByUUID
  : Map[taggedTypes.ConceptualEntitySingletonInstanceUUID, ReifiedRelationshipInstance]
  = HashMap.empty[taggedTypes.ConceptualEntitySingletonInstanceUUID, ReifiedRelationshipInstance],
  reifiedRelationshipInstanceDomainByUUID
  : Map[taggedTypes.ReifiedRelationshipInstanceDomainUUID, ReifiedRelationshipInstanceDomain]
  = HashMap.empty[taggedTypes.ReifiedRelationshipInstanceDomainUUID, ReifiedRelationshipInstanceDomain],
  reifiedRelationshipInstanceRangeByUUID
  : Map[taggedTypes.ReifiedRelationshipInstanceRangeUUID, ReifiedRelationshipInstanceRange]
  = HashMap.empty[taggedTypes.ReifiedRelationshipInstanceRangeUUID, ReifiedRelationshipInstanceRange],
  restrictionScalarDataPropertyValueByUUID
  : Map[taggedTypes.RestrictionScalarDataPropertyValueUUID, RestrictionScalarDataPropertyValue]
  = HashMap.empty[taggedTypes.RestrictionScalarDataPropertyValueUUID, RestrictionScalarDataPropertyValue],
  restrictionStructuredDataPropertyTupleByUUID
  : Map[taggedTypes.RestrictionStructuredDataPropertyContextUUID, RestrictionStructuredDataPropertyTuple]
  = HashMap.empty[taggedTypes.RestrictionStructuredDataPropertyContextUUID, RestrictionStructuredDataPropertyTuple],
  ruleBodySegmentByUUID
  : Map[taggedTypes.ElementCrossReferenceTupleUUID, RuleBodySegment]
  = HashMap.empty[taggedTypes.ElementCrossReferenceTupleUUID, RuleBodySegment],
  scalarDataPropertyValueByUUID
  : Map[taggedTypes.ScalarDataPropertyValueUUID, ScalarDataPropertyValue]
  = HashMap.empty[taggedTypes.ScalarDataPropertyValueUUID, ScalarDataPropertyValue],
  segmentPredicateByUUID
  : Map[taggedTypes.ElementCrossReferenceTupleUUID, SegmentPredicate]
  = HashMap.empty[taggedTypes.ElementCrossReferenceTupleUUID, SegmentPredicate],
  singletonInstanceScalarDataPropertyValueByUUID
  : Map[taggedTypes.SingletonInstanceScalarDataPropertyValueUUID, SingletonInstanceScalarDataPropertyValue]
  = HashMap.empty[taggedTypes.SingletonInstanceScalarDataPropertyValueUUID, SingletonInstanceScalarDataPropertyValue],
  singletonInstanceStructuredDataPropertyValueByUUID
  : Map[taggedTypes.SingletonInstanceStructuredDataPropertyValueUUID, SingletonInstanceStructuredDataPropertyValue]
  = HashMap.empty[taggedTypes.SingletonInstanceStructuredDataPropertyValueUUID, SingletonInstanceStructuredDataPropertyValue],
  structuredDataPropertyTupleByUUID
  : Map[taggedTypes.SingletonInstanceStructuredDataPropertyContextUUID, StructuredDataPropertyTuple]
  = HashMap.empty[taggedTypes.SingletonInstanceStructuredDataPropertyContextUUID, StructuredDataPropertyTuple],
  terminologyBoxAxiomByUUID
  : Map[taggedTypes.TerminologyAxiomUUID, TerminologyBoxAxiom]
  = HashMap.empty[taggedTypes.TerminologyAxiomUUID, TerminologyBoxAxiom],
  terminologyBoxStatementByUUID
  : Map[taggedTypes.ModuleElementUUID, TerminologyBoxStatement]
  = HashMap.empty[taggedTypes.ModuleElementUUID, TerminologyBoxStatement],
  terminologyBundleAxiomByUUID
  : Map[taggedTypes.TerminologyAxiomUUID, TerminologyBundleAxiom]
  = HashMap.empty[taggedTypes.TerminologyAxiomUUID, TerminologyBundleAxiom],
  terminologyBundleStatementByUUID
  : Map[taggedTypes.ModuleElementUUID, TerminologyBundleStatement]
  = HashMap.empty[taggedTypes.ModuleElementUUID, TerminologyBundleStatement],
  unreifiedRelationshipInstanceTupleByUUID
  : Map[taggedTypes.UnreifiedRelationshipInstanceTupleUUID, UnreifiedRelationshipInstanceTuple]
  = HashMap.empty[taggedTypes.UnreifiedRelationshipInstanceTupleUUID, UnreifiedRelationshipInstanceTuple]
) {
  def withAnnotationPropertyValue
  (key: LogicalElement, value: AnnotationPropertyValue)
  : Map[LogicalElement, Set[AnnotationPropertyValue]] 
  = annotations
    .updated(key, annotations.getOrElse(key, Set.empty[AnnotationPropertyValue]) + value)
  
  def withAnnotationProperty
  (key: Module, value: AnnotationProperty)
  : Map[Module, Set[AnnotationProperty]] 
  = annotationProperties
    .updated(key, annotationProperties.getOrElse(key, Set.empty[AnnotationProperty]) + value)
  
  def withTerminologyBoxAxiom
  (key: TerminologyBox, value: TerminologyBoxAxiom)
  : Map[TerminologyBox, Set[TerminologyBoxAxiom]] 
  = boxAxioms
    .updated(key, boxAxioms.getOrElse(key, Set.empty[TerminologyBoxAxiom]) + value)
  
  def withTerminologyBoxStatement
  (key: TerminologyBox, value: TerminologyBoxStatement)
  : Map[TerminologyBox, Set[TerminologyBoxStatement]] 
  = boxStatements
    .updated(key, boxStatements.getOrElse(key, Set.empty[TerminologyBoxStatement]) + value)
  
  def withForwardProperty
  (key: ReifiedRelationship, value: ForwardProperty)
  : Map[ReifiedRelationship, ForwardProperty] 
  = forwardProperty.updated(key, value)
  
  def withInverseProperty
  (key: ReifiedRelationship, value: InverseProperty)
  : Map[ReifiedRelationship, InverseProperty] 
  = inverseProperty.updated(key, value)
  
  def withRuleBodySegment
  (key: ChainRule, value: RuleBodySegment)
  : Map[ChainRule, RuleBodySegment] 
  = firstSegment.updated(key, value)
  
  def withSegmentPredicate
  (key: RuleBodySegment, value: SegmentPredicate)
  : Map[RuleBodySegment, SegmentPredicate] 
  = predicate.updated(key, value)
  
  def withRuleBodySegment
  (key: RuleBodySegment, value: RuleBodySegment)
  : Map[RuleBodySegment, RuleBodySegment] 
  = nextSegment.updated(key, value)
  
  def withRestrictionStructuredDataPropertyTuple
  (key: RestrictionStructuredDataPropertyContext, value: RestrictionStructuredDataPropertyTuple)
  : Map[RestrictionStructuredDataPropertyContext, Set[RestrictionStructuredDataPropertyTuple]] 
  = structuredDataPropertyRestrictions
    .updated(key, structuredDataPropertyRestrictions.getOrElse(key, Set.empty[RestrictionStructuredDataPropertyTuple]) + value)
  
  def withRestrictionScalarDataPropertyValue
  (key: RestrictionStructuredDataPropertyContext, value: RestrictionScalarDataPropertyValue)
  : Map[RestrictionStructuredDataPropertyContext, Set[RestrictionScalarDataPropertyValue]] 
  = scalarDataPropertyRestrictions
    .updated(key, scalarDataPropertyRestrictions.getOrElse(key, Set.empty[RestrictionScalarDataPropertyValue]) + value)
  
  def withTerminologyBundleAxiom
  (key: Bundle, value: TerminologyBundleAxiom)
  : Map[Bundle, Set[TerminologyBundleAxiom]] 
  = bundleAxioms
    .updated(key, bundleAxioms.getOrElse(key, Set.empty[TerminologyBundleAxiom]) + value)
  
  def withTerminologyBundleStatement
  (key: Bundle, value: TerminologyBundleStatement)
  : Map[Bundle, Set[TerminologyBundleStatement]] 
  = bundleStatements
    .updated(key, bundleStatements.getOrElse(key, Set.empty[TerminologyBundleStatement]) + value)
  
  def withDisjointUnionOfConceptsAxiom
  (key: ConceptTreeDisjunction, value: DisjointUnionOfConceptsAxiom)
  : Map[ConceptTreeDisjunction, Set[DisjointUnionOfConceptsAxiom]] 
  = disjunctions
    .updated(key, disjunctions.getOrElse(key, Set.empty[DisjointUnionOfConceptsAxiom]) + value)
  
  def withDescriptionBoxRefinement
  (key: DescriptionBox, value: DescriptionBoxRefinement)
  : Map[DescriptionBox, Set[DescriptionBoxRefinement]] 
  = descriptionBoxRefinements
    .updated(key, descriptionBoxRefinements.getOrElse(key, Set.empty[DescriptionBoxRefinement]) + value)
  
  def withDescriptionBoxExtendsClosedWorldDefinitions
  (key: DescriptionBox, value: DescriptionBoxExtendsClosedWorldDefinitions)
  : Map[DescriptionBox, Set[DescriptionBoxExtendsClosedWorldDefinitions]] 
  = closedWorldDefinitions
    .updated(key, closedWorldDefinitions.getOrElse(key, Set.empty[DescriptionBoxExtendsClosedWorldDefinitions]) + value)
  
  def withConceptInstance
  (key: DescriptionBox, value: ConceptInstance)
  : Map[DescriptionBox, Set[ConceptInstance]] 
  = conceptInstances
    .updated(key, conceptInstances.getOrElse(key, Set.empty[ConceptInstance]) + value)
  
  def withReifiedRelationshipInstance
  (key: DescriptionBox, value: ReifiedRelationshipInstance)
  : Map[DescriptionBox, Set[ReifiedRelationshipInstance]] 
  = reifiedRelationshipInstances
    .updated(key, reifiedRelationshipInstances.getOrElse(key, Set.empty[ReifiedRelationshipInstance]) + value)
  
  def withInstanceRelationshipEnumerationRestriction
  (key: DescriptionBox, value: InstanceRelationshipEnumerationRestriction)
  : Map[DescriptionBox, Set[InstanceRelationshipEnumerationRestriction]] 
  = instanceRelationshipEnumerationRestrictions
    .updated(key, instanceRelationshipEnumerationRestrictions.getOrElse(key, Set.empty[InstanceRelationshipEnumerationRestriction]) + value)
  
  def withInstanceRelationshipValueRestriction
  (key: DescriptionBox, value: InstanceRelationshipValueRestriction)
  : Map[DescriptionBox, Set[InstanceRelationshipValueRestriction]] 
  = instanceRelationshipValueRestrictions
    .updated(key, instanceRelationshipValueRestrictions.getOrElse(key, Set.empty[InstanceRelationshipValueRestriction]) + value)
  
  def withInstanceRelationshipExistentialRangeRestriction
  (key: DescriptionBox, value: InstanceRelationshipExistentialRangeRestriction)
  : Map[DescriptionBox, Set[InstanceRelationshipExistentialRangeRestriction]] 
  = instanceRelationshipExistentialRangeRestrictions
    .updated(key, instanceRelationshipExistentialRangeRestrictions.getOrElse(key, Set.empty[InstanceRelationshipExistentialRangeRestriction]) + value)
  
  def withInstanceRelationshipUniversalRangeRestriction
  (key: DescriptionBox, value: InstanceRelationshipUniversalRangeRestriction)
  : Map[DescriptionBox, Set[InstanceRelationshipUniversalRangeRestriction]] 
  = instanceRelationshipUniversalRangeRestrictions
    .updated(key, instanceRelationshipUniversalRangeRestrictions.getOrElse(key, Set.empty[InstanceRelationshipUniversalRangeRestriction]) + value)
  
  def withReifiedRelationshipInstanceDomain
  (key: DescriptionBox, value: ReifiedRelationshipInstanceDomain)
  : Map[DescriptionBox, Set[ReifiedRelationshipInstanceDomain]] 
  = reifiedRelationshipInstanceDomains
    .updated(key, reifiedRelationshipInstanceDomains.getOrElse(key, Set.empty[ReifiedRelationshipInstanceDomain]) + value)
  
  def withReifiedRelationshipInstanceRange
  (key: DescriptionBox, value: ReifiedRelationshipInstanceRange)
  : Map[DescriptionBox, Set[ReifiedRelationshipInstanceRange]] 
  = reifiedRelationshipInstanceRanges
    .updated(key, reifiedRelationshipInstanceRanges.getOrElse(key, Set.empty[ReifiedRelationshipInstanceRange]) + value)
  
  def withUnreifiedRelationshipInstanceTuple
  (key: DescriptionBox, value: UnreifiedRelationshipInstanceTuple)
  : Map[DescriptionBox, Set[UnreifiedRelationshipInstanceTuple]] 
  = unreifiedRelationshipInstanceTuples
    .updated(key, unreifiedRelationshipInstanceTuples.getOrElse(key, Set.empty[UnreifiedRelationshipInstanceTuple]) + value)
  
  def withSingletonInstanceScalarDataPropertyValue
  (key: DescriptionBox, value: SingletonInstanceScalarDataPropertyValue)
  : Map[DescriptionBox, Set[SingletonInstanceScalarDataPropertyValue]] 
  = singletonScalarDataPropertyValues
    .updated(key, singletonScalarDataPropertyValues.getOrElse(key, Set.empty[SingletonInstanceScalarDataPropertyValue]) + value)
  
  def withSingletonInstanceStructuredDataPropertyValue
  (key: DescriptionBox, value: SingletonInstanceStructuredDataPropertyValue)
  : Map[DescriptionBox, Set[SingletonInstanceStructuredDataPropertyValue]] 
  = singletonStructuredDataPropertyValues
    .updated(key, singletonStructuredDataPropertyValues.getOrElse(key, Set.empty[SingletonInstanceStructuredDataPropertyValue]) + value)
  
  def withStructuredDataPropertyTuple
  (key: SingletonInstanceStructuredDataPropertyContext, value: StructuredDataPropertyTuple)
  : Map[SingletonInstanceStructuredDataPropertyContext, Set[StructuredDataPropertyTuple]] 
  = structuredPropertyTuples
    .updated(key, structuredPropertyTuples.getOrElse(key, Set.empty[StructuredDataPropertyTuple]) + value)
  
  def withScalarDataPropertyValue
  (key: SingletonInstanceStructuredDataPropertyContext, value: ScalarDataPropertyValue)
  : Map[SingletonInstanceStructuredDataPropertyContext, Set[ScalarDataPropertyValue]] 
  = scalarDataPropertyValues
    .updated(key, scalarDataPropertyValues.getOrElse(key, Set.empty[ScalarDataPropertyValue]) + value)
  
  def withInstanceRelationshipOneOfRestriction
  (key: InstanceRelationshipEnumerationRestriction, value: InstanceRelationshipOneOfRestriction)
  : Map[InstanceRelationshipEnumerationRestriction, Set[InstanceRelationshipOneOfRestriction]] 
  = references
    .updated(key, references.getOrElse(key, Set.empty[InstanceRelationshipOneOfRestriction]) + value)
  

  def singleModule
  : Try[Module]
  = ( terminologyGraphs.values.toList,
      bundles.values.toList,
      descriptionBoxes.values.toList ) match {
    case (Nil, Nil, Nil) =>
      Failure(new IllegalArgumentException("No OML Modules"))
    case (g :: Nil, Nil, Nil) =>
      Success(g)
    case (Nil, b :: Nil, Nil) =>
      Success(b)
    case (Nil, Nil, d :: Nil) =>
      Success(d)
    case (gs, bs, ds) =>
      Failure(new IllegalArgumentException(
        s"There should be exactly 1 OML Module, instead there are ${gs.size} Graphs, ${bs.size} Bundles and ${ds.size} Descriptions"))
  }

  def lookupModule
  (uuid: Option[taggedTypes.ModuleUUID])
  : Option[Module]
  = uuid.flatMap {
    lookupModule
  }
  
  def lookupModule
  (uuid: taggedTypes.ModuleUUID)
  : Option[Module]
  = lookupTerminologyBox(uuid) orElse
  lookupDescriptionBox(uuid)
  
  def lookupTerminologyBox
  (uuid: Option[taggedTypes.ModuleUUID])
  : Option[TerminologyBox]
  = uuid.flatMap { 
  	lookupTerminologyBox
  }
  
  def lookupTerminologyBox
  (uuid: taggedTypes.ModuleUUID)
  : Option[TerminologyBox]
  = lookupTerminologyGraph(uuid) orElse 
  lookupBundle(uuid)

  def lookupRestrictableRelationship
  (uuid: taggedTypes.RestrictableRelationshipUUID)
  : Option[RestrictableRelationship]
  = lookupForwardProperty(uuid) orElse
    lookupInverseProperty(uuid) orElse
    lookupUnreifiedRelationship(uuid)

  def lookupUnreifiedRelationship
  (uuid: taggedTypes.RestrictableRelationshipUUID)
  : Option[UnreifiedRelationship]
  = terminologyBoxStatementByUUID.get(taggedTypes.terminologyBoxStatementUUID(uuid)) match {
    case Some(ur: UnreifiedRelationship) =>
      Some(ur)
    case _ =>
      None
  }
 
  
  def lookupTerminologyGraph
  (uuid: Option[taggedTypes.ModuleUUID])
  : Option[TerminologyGraph]
  = uuid.flatMap {
    lookupTerminologyGraph
  } 
  
  def lookupTerminologyGraph
  (uuid: taggedTypes.ModuleUUID)
  : Option[TerminologyGraph]
  = terminologyGraphs.get(uuid)
    
  
  def lookupBundle
  (uuid: Option[taggedTypes.ModuleUUID])
  : Option[Bundle]
  = uuid.flatMap {
    lookupBundle
  } 
  
  def lookupBundle
  (uuid: taggedTypes.ModuleUUID)
  : Option[Bundle]
  = bundles.get(uuid)
    
  
  def lookupDescriptionBox
  (uuid: Option[taggedTypes.ModuleUUID])
  : Option[DescriptionBox]
  = uuid.flatMap {
    lookupDescriptionBox
  } 
  
  def lookupDescriptionBox
  (uuid: taggedTypes.ModuleUUID)
  : Option[DescriptionBox]
  = descriptionBoxes.get(uuid)

    def lookupAnnotations
  (key: Option[LogicalElement])
  : Set[AnnotationPropertyValue]
  = key
  .fold[Set[AnnotationPropertyValue]] { 
  	Set.empty[AnnotationPropertyValue] 
  }{ lookupAnnotations }
  
  def lookupAnnotations
  (key: LogicalElement)
  : Set[AnnotationPropertyValue]
  = annotations.getOrElse(key, Set.empty[AnnotationPropertyValue])
  
  def lookupAnnotationPropertyValue
  (uuid: Option[taggedTypes.AnnotationPropertyValueUUID])
  : Option[AnnotationPropertyValue]
  = uuid.flatMap {
    lookupAnnotationPropertyValue
  }
  
  def lookupAnnotationPropertyValue
  (uuid: taggedTypes.AnnotationPropertyValueUUID)
  : Option[AnnotationPropertyValue]
  = annotationPropertyValueByUUID.get(uuid)
    
  def lookupAnnotationProperties
  (key: Option[Module])
  : Set[AnnotationProperty]
  = key
  .fold[Set[AnnotationProperty]] { 
  	Set.empty[AnnotationProperty] 
  }{ lookupAnnotationProperties }
  
  def lookupAnnotationProperties
  (key: Module)
  : Set[AnnotationProperty]
  = annotationProperties.getOrElse(key, Set.empty[AnnotationProperty])
  
  def lookupAnnotationProperty
  (uuid: Option[taggedTypes.AnnotationPropertyUUID])
  : Option[AnnotationProperty]
  = uuid.flatMap {
    lookupAnnotationProperty
  }
  
  def lookupAnnotationProperty
  (uuid: taggedTypes.AnnotationPropertyUUID)
  : Option[AnnotationProperty]
  = annotationPropertyByUUID.get(uuid)
    
  def lookupBoxAxioms
  (key: Option[TerminologyBox])
  : Set[TerminologyBoxAxiom]
  = key
  .fold[Set[TerminologyBoxAxiom]] { 
  	Set.empty[TerminologyBoxAxiom] 
  }{ lookupBoxAxioms }
  
  def lookupBoxAxioms
  (key: TerminologyBox)
  : Set[TerminologyBoxAxiom]
  = boxAxioms.getOrElse(key, Set.empty[TerminologyBoxAxiom])
  
  def lookupTerminologyBoxAxiom
  (uuid: Option[taggedTypes.TerminologyAxiomUUID])
  : Option[TerminologyBoxAxiom]
  = uuid.flatMap {
    lookupTerminologyBoxAxiom
  }
  
  def lookupTerminologyBoxAxiom
  (uuid: taggedTypes.TerminologyAxiomUUID)
  : Option[TerminologyBoxAxiom]
  = terminologyBoxAxiomByUUID.get(uuid)
    
  def lookupBoxStatements
  (key: Option[TerminologyBox])
  : Set[TerminologyBoxStatement]
  = key
  .fold[Set[TerminologyBoxStatement]] { 
  	Set.empty[TerminologyBoxStatement] 
  }{ lookupBoxStatements }
  
  def lookupBoxStatements
  (key: TerminologyBox)
  : Set[TerminologyBoxStatement]
  = boxStatements.getOrElse(key, Set.empty[TerminologyBoxStatement])
  
  def lookupTerminologyBoxStatement
  (uuid: Option[taggedTypes.ModuleElementUUID])
  : Option[TerminologyBoxStatement]
  = uuid.flatMap {
    lookupTerminologyBoxStatement
  }
  
  def lookupTerminologyBoxStatement
  (uuid: taggedTypes.ModuleElementUUID)
  : Option[TerminologyBoxStatement]
  = terminologyBoxStatementByUUID.get(uuid)
    
  def lookupForwardProperty
  (key: Option[ReifiedRelationship])
  : Option[ForwardProperty]
  = key.flatMap { lookupForwardProperty }
  
  def lookupForwardProperty
  (key: ReifiedRelationship)
  : Option[ForwardProperty]
  = forwardProperty.get(key)
  
  def lookupForwardProperty
  (uuid: taggedTypes.RestrictableRelationshipUUID)
  : Option[ForwardProperty]
  = forwardPropertyByUUID.get(uuid)
    
  def lookupInverseProperty
  (key: Option[ReifiedRelationship])
  : Option[InverseProperty]
  = key.flatMap { lookupInverseProperty }
  
  def lookupInverseProperty
  (key: ReifiedRelationship)
  : Option[InverseProperty]
  = inverseProperty.get(key)
  
  def lookupInverseProperty
  (uuid: taggedTypes.RestrictableRelationshipUUID)
  : Option[InverseProperty]
  = inversePropertyByUUID.get(uuid)
    
  def lookupFirstSegment
  (key: Option[ChainRule])
  : Option[RuleBodySegment]
  = key.flatMap { lookupFirstSegment }
  
  def lookupFirstSegment
  (key: ChainRule)
  : Option[RuleBodySegment]
  = firstSegment.get(key)
  
  def lookupRuleBodySegment
  (uuid: taggedTypes.ElementCrossReferenceTupleUUID)
  : Option[RuleBodySegment]
  = ruleBodySegmentByUUID.get(uuid)
    
  def lookupPredicate
  (key: Option[RuleBodySegment])
  : Option[SegmentPredicate]
  = key.flatMap { lookupPredicate }
  
  def lookupPredicate
  (key: RuleBodySegment)
  : Option[SegmentPredicate]
  = predicate.get(key)
  
  def lookupSegmentPredicate
  (uuid: taggedTypes.ElementCrossReferenceTupleUUID)
  : Option[SegmentPredicate]
  = segmentPredicateByUUID.get(uuid)
    
  def lookupNextSegment
  (key: Option[RuleBodySegment])
  : Option[RuleBodySegment]
  = key.flatMap { lookupNextSegment }
  
  def lookupNextSegment
  (key: RuleBodySegment)
  : Option[RuleBodySegment]
  = nextSegment.get(key)
    
  def lookupStructuredDataPropertyRestrictions
  (key: Option[RestrictionStructuredDataPropertyContext])
  : Set[RestrictionStructuredDataPropertyTuple]
  = key
  .fold[Set[RestrictionStructuredDataPropertyTuple]] { 
  	Set.empty[RestrictionStructuredDataPropertyTuple] 
  }{ lookupStructuredDataPropertyRestrictions }
  
  def lookupStructuredDataPropertyRestrictions
  (key: RestrictionStructuredDataPropertyContext)
  : Set[RestrictionStructuredDataPropertyTuple]
  = structuredDataPropertyRestrictions.getOrElse(key, Set.empty[RestrictionStructuredDataPropertyTuple])
  
  def lookupRestrictionStructuredDataPropertyTuple
  (uuid: Option[taggedTypes.RestrictionStructuredDataPropertyContextUUID])
  : Option[RestrictionStructuredDataPropertyTuple]
  = uuid.flatMap {
    lookupRestrictionStructuredDataPropertyTuple
  }
  
  def lookupRestrictionStructuredDataPropertyTuple
  (uuid: taggedTypes.RestrictionStructuredDataPropertyContextUUID)
  : Option[RestrictionStructuredDataPropertyTuple]
  = restrictionStructuredDataPropertyTupleByUUID.get(uuid)
    
  def lookupScalarDataPropertyRestrictions
  (key: Option[RestrictionStructuredDataPropertyContext])
  : Set[RestrictionScalarDataPropertyValue]
  = key
  .fold[Set[RestrictionScalarDataPropertyValue]] { 
  	Set.empty[RestrictionScalarDataPropertyValue] 
  }{ lookupScalarDataPropertyRestrictions }
  
  def lookupScalarDataPropertyRestrictions
  (key: RestrictionStructuredDataPropertyContext)
  : Set[RestrictionScalarDataPropertyValue]
  = scalarDataPropertyRestrictions.getOrElse(key, Set.empty[RestrictionScalarDataPropertyValue])
  
  def lookupRestrictionScalarDataPropertyValue
  (uuid: Option[taggedTypes.RestrictionScalarDataPropertyValueUUID])
  : Option[RestrictionScalarDataPropertyValue]
  = uuid.flatMap {
    lookupRestrictionScalarDataPropertyValue
  }
  
  def lookupRestrictionScalarDataPropertyValue
  (uuid: taggedTypes.RestrictionScalarDataPropertyValueUUID)
  : Option[RestrictionScalarDataPropertyValue]
  = restrictionScalarDataPropertyValueByUUID.get(uuid)
    
  def lookupBundleAxioms
  (key: Option[Bundle])
  : Set[TerminologyBundleAxiom]
  = key
  .fold[Set[TerminologyBundleAxiom]] { 
  	Set.empty[TerminologyBundleAxiom] 
  }{ lookupBundleAxioms }
  
  def lookupBundleAxioms
  (key: Bundle)
  : Set[TerminologyBundleAxiom]
  = bundleAxioms.getOrElse(key, Set.empty[TerminologyBundleAxiom])
  
  def lookupTerminologyBundleAxiom
  (uuid: Option[taggedTypes.TerminologyAxiomUUID])
  : Option[TerminologyBundleAxiom]
  = uuid.flatMap {
    lookupTerminologyBundleAxiom
  }
  
  def lookupTerminologyBundleAxiom
  (uuid: taggedTypes.TerminologyAxiomUUID)
  : Option[TerminologyBundleAxiom]
  = terminologyBundleAxiomByUUID.get(uuid)
    
  def lookupBundleStatements
  (key: Option[Bundle])
  : Set[TerminologyBundleStatement]
  = key
  .fold[Set[TerminologyBundleStatement]] { 
  	Set.empty[TerminologyBundleStatement] 
  }{ lookupBundleStatements }
  
  def lookupBundleStatements
  (key: Bundle)
  : Set[TerminologyBundleStatement]
  = bundleStatements.getOrElse(key, Set.empty[TerminologyBundleStatement])
  
  def lookupTerminologyBundleStatement
  (uuid: Option[taggedTypes.ModuleElementUUID])
  : Option[TerminologyBundleStatement]
  = uuid.flatMap {
    lookupTerminologyBundleStatement
  }
  
  def lookupTerminologyBundleStatement
  (uuid: taggedTypes.ModuleElementUUID)
  : Option[TerminologyBundleStatement]
  = terminologyBundleStatementByUUID.get(uuid)
    
  def lookupDisjunctions
  (key: Option[ConceptTreeDisjunction])
  : Set[DisjointUnionOfConceptsAxiom]
  = key
  .fold[Set[DisjointUnionOfConceptsAxiom]] { 
  	Set.empty[DisjointUnionOfConceptsAxiom] 
  }{ lookupDisjunctions }
  
  def lookupDisjunctions
  (key: ConceptTreeDisjunction)
  : Set[DisjointUnionOfConceptsAxiom]
  = disjunctions.getOrElse(key, Set.empty[DisjointUnionOfConceptsAxiom])
  
  def lookupDisjointUnionOfConceptsAxiom
  (uuid: Option[taggedTypes.ElementCrossReferenceTupleUUID])
  : Option[DisjointUnionOfConceptsAxiom]
  = uuid.flatMap {
    lookupDisjointUnionOfConceptsAxiom
  }
  
  def lookupDisjointUnionOfConceptsAxiom
  (uuid: taggedTypes.ElementCrossReferenceTupleUUID)
  : Option[DisjointUnionOfConceptsAxiom]
  = disjointUnionOfConceptsAxiomByUUID.get(uuid)
    
  def lookupDescriptionBoxRefinements
  (key: Option[DescriptionBox])
  : Set[DescriptionBoxRefinement]
  = key
  .fold[Set[DescriptionBoxRefinement]] { 
  	Set.empty[DescriptionBoxRefinement] 
  }{ lookupDescriptionBoxRefinements }
  
  def lookupDescriptionBoxRefinements
  (key: DescriptionBox)
  : Set[DescriptionBoxRefinement]
  = descriptionBoxRefinements.getOrElse(key, Set.empty[DescriptionBoxRefinement])
  
  def lookupDescriptionBoxRefinement
  (uuid: Option[taggedTypes.DescriptionBoxRelationshipUUID])
  : Option[DescriptionBoxRefinement]
  = uuid.flatMap {
    lookupDescriptionBoxRefinement
  }
  
  def lookupDescriptionBoxRefinement
  (uuid: taggedTypes.DescriptionBoxRelationshipUUID)
  : Option[DescriptionBoxRefinement]
  = descriptionBoxRefinementByUUID.get(uuid)
    
  def lookupClosedWorldDefinitions
  (key: Option[DescriptionBox])
  : Set[DescriptionBoxExtendsClosedWorldDefinitions]
  = key
  .fold[Set[DescriptionBoxExtendsClosedWorldDefinitions]] { 
  	Set.empty[DescriptionBoxExtendsClosedWorldDefinitions] 
  }{ lookupClosedWorldDefinitions }
  
  def lookupClosedWorldDefinitions
  (key: DescriptionBox)
  : Set[DescriptionBoxExtendsClosedWorldDefinitions]
  = closedWorldDefinitions.getOrElse(key, Set.empty[DescriptionBoxExtendsClosedWorldDefinitions])
  
  def lookupDescriptionBoxExtendsClosedWorldDefinitions
  (uuid: Option[taggedTypes.DescriptionBoxRelationshipUUID])
  : Option[DescriptionBoxExtendsClosedWorldDefinitions]
  = uuid.flatMap {
    lookupDescriptionBoxExtendsClosedWorldDefinitions
  }
  
  def lookupDescriptionBoxExtendsClosedWorldDefinitions
  (uuid: taggedTypes.DescriptionBoxRelationshipUUID)
  : Option[DescriptionBoxExtendsClosedWorldDefinitions]
  = descriptionBoxExtendsClosedWorldDefinitionsByUUID.get(uuid)
    
  def lookupConceptInstances
  (key: Option[DescriptionBox])
  : Set[ConceptInstance]
  = key
  .fold[Set[ConceptInstance]] { 
  	Set.empty[ConceptInstance] 
  }{ lookupConceptInstances }
  
  def lookupConceptInstances
  (key: DescriptionBox)
  : Set[ConceptInstance]
  = conceptInstances.getOrElse(key, Set.empty[ConceptInstance])
  
  def lookupConceptInstance
  (uuid: Option[taggedTypes.ConceptualEntitySingletonInstanceUUID])
  : Option[ConceptInstance]
  = uuid.flatMap {
    lookupConceptInstance
  }
  
  def lookupConceptInstance
  (uuid: taggedTypes.ConceptualEntitySingletonInstanceUUID)
  : Option[ConceptInstance]
  = conceptInstanceByUUID.get(uuid)
    
  def lookupReifiedRelationshipInstances
  (key: Option[DescriptionBox])
  : Set[ReifiedRelationshipInstance]
  = key
  .fold[Set[ReifiedRelationshipInstance]] { 
  	Set.empty[ReifiedRelationshipInstance] 
  }{ lookupReifiedRelationshipInstances }
  
  def lookupReifiedRelationshipInstances
  (key: DescriptionBox)
  : Set[ReifiedRelationshipInstance]
  = reifiedRelationshipInstances.getOrElse(key, Set.empty[ReifiedRelationshipInstance])
  
  def lookupReifiedRelationshipInstance
  (uuid: Option[taggedTypes.ConceptualEntitySingletonInstanceUUID])
  : Option[ReifiedRelationshipInstance]
  = uuid.flatMap {
    lookupReifiedRelationshipInstance
  }
  
  def lookupReifiedRelationshipInstance
  (uuid: taggedTypes.ConceptualEntitySingletonInstanceUUID)
  : Option[ReifiedRelationshipInstance]
  = reifiedRelationshipInstanceByUUID.get(uuid)
    
  def lookupInstanceRelationshipEnumerationRestrictions
  (key: Option[DescriptionBox])
  : Set[InstanceRelationshipEnumerationRestriction]
  = key
  .fold[Set[InstanceRelationshipEnumerationRestriction]] { 
  	Set.empty[InstanceRelationshipEnumerationRestriction] 
  }{ lookupInstanceRelationshipEnumerationRestrictions }
  
  def lookupInstanceRelationshipEnumerationRestrictions
  (key: DescriptionBox)
  : Set[InstanceRelationshipEnumerationRestriction]
  = instanceRelationshipEnumerationRestrictions.getOrElse(key, Set.empty[InstanceRelationshipEnumerationRestriction])
  
  def lookupInstanceRelationshipEnumerationRestriction
  (uuid: Option[taggedTypes.InstanceRelationshipEnumerationRestrictionUUID])
  : Option[InstanceRelationshipEnumerationRestriction]
  = uuid.flatMap {
    lookupInstanceRelationshipEnumerationRestriction
  }
  
  def lookupInstanceRelationshipEnumerationRestriction
  (uuid: taggedTypes.InstanceRelationshipEnumerationRestrictionUUID)
  : Option[InstanceRelationshipEnumerationRestriction]
  = instanceRelationshipEnumerationRestrictionByUUID.get(uuid)
    
  def lookupInstanceRelationshipValueRestrictions
  (key: Option[DescriptionBox])
  : Set[InstanceRelationshipValueRestriction]
  = key
  .fold[Set[InstanceRelationshipValueRestriction]] { 
  	Set.empty[InstanceRelationshipValueRestriction] 
  }{ lookupInstanceRelationshipValueRestrictions }
  
  def lookupInstanceRelationshipValueRestrictions
  (key: DescriptionBox)
  : Set[InstanceRelationshipValueRestriction]
  = instanceRelationshipValueRestrictions.getOrElse(key, Set.empty[InstanceRelationshipValueRestriction])
  
  def lookupInstanceRelationshipValueRestriction
  (uuid: Option[taggedTypes.InstanceRelationshipValueRestrictionUUID])
  : Option[InstanceRelationshipValueRestriction]
  = uuid.flatMap {
    lookupInstanceRelationshipValueRestriction
  }
  
  def lookupInstanceRelationshipValueRestriction
  (uuid: taggedTypes.InstanceRelationshipValueRestrictionUUID)
  : Option[InstanceRelationshipValueRestriction]
  = instanceRelationshipValueRestrictionByUUID.get(uuid)
    
  def lookupInstanceRelationshipExistentialRangeRestrictions
  (key: Option[DescriptionBox])
  : Set[InstanceRelationshipExistentialRangeRestriction]
  = key
  .fold[Set[InstanceRelationshipExistentialRangeRestriction]] { 
  	Set.empty[InstanceRelationshipExistentialRangeRestriction] 
  }{ lookupInstanceRelationshipExistentialRangeRestrictions }
  
  def lookupInstanceRelationshipExistentialRangeRestrictions
  (key: DescriptionBox)
  : Set[InstanceRelationshipExistentialRangeRestriction]
  = instanceRelationshipExistentialRangeRestrictions.getOrElse(key, Set.empty[InstanceRelationshipExistentialRangeRestriction])
  
  def lookupInstanceRelationshipExistentialRangeRestriction
  (uuid: Option[taggedTypes.InstanceRelationshipExistentialRangeRestrictionUUID])
  : Option[InstanceRelationshipExistentialRangeRestriction]
  = uuid.flatMap {
    lookupInstanceRelationshipExistentialRangeRestriction
  }
  
  def lookupInstanceRelationshipExistentialRangeRestriction
  (uuid: taggedTypes.InstanceRelationshipExistentialRangeRestrictionUUID)
  : Option[InstanceRelationshipExistentialRangeRestriction]
  = instanceRelationshipExistentialRangeRestrictionByUUID.get(uuid)
    
  def lookupInstanceRelationshipUniversalRangeRestrictions
  (key: Option[DescriptionBox])
  : Set[InstanceRelationshipUniversalRangeRestriction]
  = key
  .fold[Set[InstanceRelationshipUniversalRangeRestriction]] { 
  	Set.empty[InstanceRelationshipUniversalRangeRestriction] 
  }{ lookupInstanceRelationshipUniversalRangeRestrictions }
  
  def lookupInstanceRelationshipUniversalRangeRestrictions
  (key: DescriptionBox)
  : Set[InstanceRelationshipUniversalRangeRestriction]
  = instanceRelationshipUniversalRangeRestrictions.getOrElse(key, Set.empty[InstanceRelationshipUniversalRangeRestriction])
  
  def lookupInstanceRelationshipUniversalRangeRestriction
  (uuid: Option[taggedTypes.InstanceRelationshipUniversalRangeRestrictionUUID])
  : Option[InstanceRelationshipUniversalRangeRestriction]
  = uuid.flatMap {
    lookupInstanceRelationshipUniversalRangeRestriction
  }
  
  def lookupInstanceRelationshipUniversalRangeRestriction
  (uuid: taggedTypes.InstanceRelationshipUniversalRangeRestrictionUUID)
  : Option[InstanceRelationshipUniversalRangeRestriction]
  = instanceRelationshipUniversalRangeRestrictionByUUID.get(uuid)
    
  def lookupReifiedRelationshipInstanceDomains
  (key: Option[DescriptionBox])
  : Set[ReifiedRelationshipInstanceDomain]
  = key
  .fold[Set[ReifiedRelationshipInstanceDomain]] { 
  	Set.empty[ReifiedRelationshipInstanceDomain] 
  }{ lookupReifiedRelationshipInstanceDomains }
  
  def lookupReifiedRelationshipInstanceDomains
  (key: DescriptionBox)
  : Set[ReifiedRelationshipInstanceDomain]
  = reifiedRelationshipInstanceDomains.getOrElse(key, Set.empty[ReifiedRelationshipInstanceDomain])
  
  def lookupReifiedRelationshipInstanceDomain
  (uuid: Option[taggedTypes.ReifiedRelationshipInstanceDomainUUID])
  : Option[ReifiedRelationshipInstanceDomain]
  = uuid.flatMap {
    lookupReifiedRelationshipInstanceDomain
  }
  
  def lookupReifiedRelationshipInstanceDomain
  (uuid: taggedTypes.ReifiedRelationshipInstanceDomainUUID)
  : Option[ReifiedRelationshipInstanceDomain]
  = reifiedRelationshipInstanceDomainByUUID.get(uuid)
    
  def lookupReifiedRelationshipInstanceRanges
  (key: Option[DescriptionBox])
  : Set[ReifiedRelationshipInstanceRange]
  = key
  .fold[Set[ReifiedRelationshipInstanceRange]] { 
  	Set.empty[ReifiedRelationshipInstanceRange] 
  }{ lookupReifiedRelationshipInstanceRanges }
  
  def lookupReifiedRelationshipInstanceRanges
  (key: DescriptionBox)
  : Set[ReifiedRelationshipInstanceRange]
  = reifiedRelationshipInstanceRanges.getOrElse(key, Set.empty[ReifiedRelationshipInstanceRange])
  
  def lookupReifiedRelationshipInstanceRange
  (uuid: Option[taggedTypes.ReifiedRelationshipInstanceRangeUUID])
  : Option[ReifiedRelationshipInstanceRange]
  = uuid.flatMap {
    lookupReifiedRelationshipInstanceRange
  }
  
  def lookupReifiedRelationshipInstanceRange
  (uuid: taggedTypes.ReifiedRelationshipInstanceRangeUUID)
  : Option[ReifiedRelationshipInstanceRange]
  = reifiedRelationshipInstanceRangeByUUID.get(uuid)
    
  def lookupUnreifiedRelationshipInstanceTuples
  (key: Option[DescriptionBox])
  : Set[UnreifiedRelationshipInstanceTuple]
  = key
  .fold[Set[UnreifiedRelationshipInstanceTuple]] { 
  	Set.empty[UnreifiedRelationshipInstanceTuple] 
  }{ lookupUnreifiedRelationshipInstanceTuples }
  
  def lookupUnreifiedRelationshipInstanceTuples
  (key: DescriptionBox)
  : Set[UnreifiedRelationshipInstanceTuple]
  = unreifiedRelationshipInstanceTuples.getOrElse(key, Set.empty[UnreifiedRelationshipInstanceTuple])
  
  def lookupUnreifiedRelationshipInstanceTuple
  (uuid: Option[taggedTypes.UnreifiedRelationshipInstanceTupleUUID])
  : Option[UnreifiedRelationshipInstanceTuple]
  = uuid.flatMap {
    lookupUnreifiedRelationshipInstanceTuple
  }
  
  def lookupUnreifiedRelationshipInstanceTuple
  (uuid: taggedTypes.UnreifiedRelationshipInstanceTupleUUID)
  : Option[UnreifiedRelationshipInstanceTuple]
  = unreifiedRelationshipInstanceTupleByUUID.get(uuid)
    
  def lookupSingletonScalarDataPropertyValues
  (key: Option[DescriptionBox])
  : Set[SingletonInstanceScalarDataPropertyValue]
  = key
  .fold[Set[SingletonInstanceScalarDataPropertyValue]] { 
  	Set.empty[SingletonInstanceScalarDataPropertyValue] 
  }{ lookupSingletonScalarDataPropertyValues }
  
  def lookupSingletonScalarDataPropertyValues
  (key: DescriptionBox)
  : Set[SingletonInstanceScalarDataPropertyValue]
  = singletonScalarDataPropertyValues.getOrElse(key, Set.empty[SingletonInstanceScalarDataPropertyValue])
  
  def lookupSingletonInstanceScalarDataPropertyValue
  (uuid: Option[taggedTypes.SingletonInstanceScalarDataPropertyValueUUID])
  : Option[SingletonInstanceScalarDataPropertyValue]
  = uuid.flatMap {
    lookupSingletonInstanceScalarDataPropertyValue
  }
  
  def lookupSingletonInstanceScalarDataPropertyValue
  (uuid: taggedTypes.SingletonInstanceScalarDataPropertyValueUUID)
  : Option[SingletonInstanceScalarDataPropertyValue]
  = singletonInstanceScalarDataPropertyValueByUUID.get(uuid)
    
  def lookupSingletonStructuredDataPropertyValues
  (key: Option[DescriptionBox])
  : Set[SingletonInstanceStructuredDataPropertyValue]
  = key
  .fold[Set[SingletonInstanceStructuredDataPropertyValue]] { 
  	Set.empty[SingletonInstanceStructuredDataPropertyValue] 
  }{ lookupSingletonStructuredDataPropertyValues }
  
  def lookupSingletonStructuredDataPropertyValues
  (key: DescriptionBox)
  : Set[SingletonInstanceStructuredDataPropertyValue]
  = singletonStructuredDataPropertyValues.getOrElse(key, Set.empty[SingletonInstanceStructuredDataPropertyValue])
  
  def lookupSingletonInstanceStructuredDataPropertyValue
  (uuid: Option[taggedTypes.SingletonInstanceStructuredDataPropertyValueUUID])
  : Option[SingletonInstanceStructuredDataPropertyValue]
  = uuid.flatMap {
    lookupSingletonInstanceStructuredDataPropertyValue
  }
  
  def lookupSingletonInstanceStructuredDataPropertyValue
  (uuid: taggedTypes.SingletonInstanceStructuredDataPropertyValueUUID)
  : Option[SingletonInstanceStructuredDataPropertyValue]
  = singletonInstanceStructuredDataPropertyValueByUUID.get(uuid)
    
  def lookupStructuredPropertyTuples
  (key: Option[SingletonInstanceStructuredDataPropertyContext])
  : Set[StructuredDataPropertyTuple]
  = key
  .fold[Set[StructuredDataPropertyTuple]] { 
  	Set.empty[StructuredDataPropertyTuple] 
  }{ lookupStructuredPropertyTuples }
  
  def lookupStructuredPropertyTuples
  (key: SingletonInstanceStructuredDataPropertyContext)
  : Set[StructuredDataPropertyTuple]
  = structuredPropertyTuples.getOrElse(key, Set.empty[StructuredDataPropertyTuple])
  
  def lookupStructuredDataPropertyTuple
  (uuid: Option[taggedTypes.SingletonInstanceStructuredDataPropertyContextUUID])
  : Option[StructuredDataPropertyTuple]
  = uuid.flatMap {
    lookupStructuredDataPropertyTuple
  }
  
  def lookupStructuredDataPropertyTuple
  (uuid: taggedTypes.SingletonInstanceStructuredDataPropertyContextUUID)
  : Option[StructuredDataPropertyTuple]
  = structuredDataPropertyTupleByUUID.get(uuid)
    
  def lookupScalarDataPropertyValues
  (key: Option[SingletonInstanceStructuredDataPropertyContext])
  : Set[ScalarDataPropertyValue]
  = key
  .fold[Set[ScalarDataPropertyValue]] { 
  	Set.empty[ScalarDataPropertyValue] 
  }{ lookupScalarDataPropertyValues }
  
  def lookupScalarDataPropertyValues
  (key: SingletonInstanceStructuredDataPropertyContext)
  : Set[ScalarDataPropertyValue]
  = scalarDataPropertyValues.getOrElse(key, Set.empty[ScalarDataPropertyValue])
  
  def lookupScalarDataPropertyValue
  (uuid: Option[taggedTypes.ScalarDataPropertyValueUUID])
  : Option[ScalarDataPropertyValue]
  = uuid.flatMap {
    lookupScalarDataPropertyValue
  }
  
  def lookupScalarDataPropertyValue
  (uuid: taggedTypes.ScalarDataPropertyValueUUID)
  : Option[ScalarDataPropertyValue]
  = scalarDataPropertyValueByUUID.get(uuid)
    
  def lookupReferences
  (key: Option[InstanceRelationshipEnumerationRestriction])
  : Set[InstanceRelationshipOneOfRestriction]
  = key
  .fold[Set[InstanceRelationshipOneOfRestriction]] { 
  	Set.empty[InstanceRelationshipOneOfRestriction] 
  }{ lookupReferences }
  
  def lookupReferences
  (key: InstanceRelationshipEnumerationRestriction)
  : Set[InstanceRelationshipOneOfRestriction]
  = references.getOrElse(key, Set.empty[InstanceRelationshipOneOfRestriction])
  
  def lookupInstanceRelationshipOneOfRestriction
  (uuid: Option[taggedTypes.ElementCrossReferenceTupleUUID])
  : Option[InstanceRelationshipOneOfRestriction]
  = uuid.flatMap {
    lookupInstanceRelationshipOneOfRestriction
  }
  
  def lookupInstanceRelationshipOneOfRestriction
  (uuid: taggedTypes.ElementCrossReferenceTupleUUID)
  : Option[InstanceRelationshipOneOfRestriction]
  = instanceRelationshipOneOfRestrictionByUUID.get(uuid)

  
  def lookupPredicate(uuid: taggedTypes.PredicateUUID)
  : Option[Predicate]
  = forwardPropertyByUUID.get(uuid.asInstanceOf[taggedTypes.ForwardPropertyUUID]) orElse
    inversePropertyByUUID.get(uuid.asInstanceOf[taggedTypes.InversePropertyUUID]) orElse
    lookupTerminologyBoxStatement(uuid.asInstanceOf[taggedTypes.TerminologyBoxStatementUUID]) match {
    case Some(p: Predicate) =>
      Some(p)
    case _ =>
      None
  }

  def lookupLogicalElement(uuid: taggedTypes.LogicalElementUUID)
  : Option[LogicalElement]
  = lookupModule(uuid.asInstanceOf[taggedTypes.ModuleUUID]) orElse
    lookupTerminologyBoxAxiom(uuid.asInstanceOf[taggedTypes.TerminologyBoxAxiomUUID]) orElse
    lookupTerminologyBoxStatement(uuid.asInstanceOf[taggedTypes.TerminologyBoxStatementUUID]) orElse
    lookupForwardProperty(uuid.asInstanceOf[taggedTypes.ForwardPropertyUUID]) orElse
    lookupInverseProperty(uuid.asInstanceOf[taggedTypes.InversePropertyUUID]) orElse
    lookupRuleBodySegment(uuid.asInstanceOf[taggedTypes.RuleBodySegmentUUID]) orElse
    lookupSegmentPredicate(uuid.asInstanceOf[taggedTypes.SegmentPredicateUUID]) orElse
    lookupRuleBodySegment(uuid.asInstanceOf[taggedTypes.RuleBodySegmentUUID]) orElse
    lookupRestrictionStructuredDataPropertyTuple(uuid.asInstanceOf[taggedTypes.RestrictionStructuredDataPropertyTupleUUID]) orElse
    lookupRestrictionScalarDataPropertyValue(uuid.asInstanceOf[taggedTypes.RestrictionScalarDataPropertyValueUUID]) orElse
    lookupTerminologyBundleAxiom(uuid.asInstanceOf[taggedTypes.TerminologyBundleAxiomUUID]) orElse
    lookupTerminologyBundleStatement(uuid.asInstanceOf[taggedTypes.TerminologyBundleStatementUUID]) orElse
    lookupDisjointUnionOfConceptsAxiom(uuid.asInstanceOf[taggedTypes.DisjointUnionOfConceptsAxiomUUID]) orElse
    lookupDescriptionBoxRefinement(uuid.asInstanceOf[taggedTypes.DescriptionBoxRefinementUUID]) orElse
    lookupDescriptionBoxExtendsClosedWorldDefinitions(uuid.asInstanceOf[taggedTypes.DescriptionBoxExtendsClosedWorldDefinitionsUUID]) orElse
    lookupConceptInstance(uuid.asInstanceOf[taggedTypes.ConceptInstanceUUID]) orElse
    lookupReifiedRelationshipInstance(uuid.asInstanceOf[taggedTypes.ReifiedRelationshipInstanceUUID]) orElse
    lookupInstanceRelationshipEnumerationRestriction(uuid.asInstanceOf[taggedTypes.InstanceRelationshipEnumerationRestrictionUUID]) orElse
    lookupInstanceRelationshipValueRestriction(uuid.asInstanceOf[taggedTypes.InstanceRelationshipValueRestrictionUUID]) orElse
    lookupInstanceRelationshipExistentialRangeRestriction(uuid.asInstanceOf[taggedTypes.InstanceRelationshipExistentialRangeRestrictionUUID]) orElse
    lookupInstanceRelationshipUniversalRangeRestriction(uuid.asInstanceOf[taggedTypes.InstanceRelationshipUniversalRangeRestrictionUUID]) orElse
    lookupReifiedRelationshipInstanceDomain(uuid.asInstanceOf[taggedTypes.ReifiedRelationshipInstanceDomainUUID]) orElse
    lookupReifiedRelationshipInstanceRange(uuid.asInstanceOf[taggedTypes.ReifiedRelationshipInstanceRangeUUID]) orElse
    lookupUnreifiedRelationshipInstanceTuple(uuid.asInstanceOf[taggedTypes.UnreifiedRelationshipInstanceTupleUUID]) orElse
    lookupSingletonInstanceScalarDataPropertyValue(uuid.asInstanceOf[taggedTypes.SingletonInstanceScalarDataPropertyValueUUID]) orElse
    lookupSingletonInstanceStructuredDataPropertyValue(uuid.asInstanceOf[taggedTypes.SingletonInstanceStructuredDataPropertyValueUUID]) orElse
    lookupStructuredDataPropertyTuple(uuid.asInstanceOf[taggedTypes.StructuredDataPropertyTupleUUID]) orElse
    lookupScalarDataPropertyValue(uuid.asInstanceOf[taggedTypes.ScalarDataPropertyValueUUID]) orElse
    lookupInstanceRelationshipOneOfRestriction(uuid.asInstanceOf[taggedTypes.InstanceRelationshipOneOfRestrictionUUID])

}
