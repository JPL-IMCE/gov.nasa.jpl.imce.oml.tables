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

import scala.collection.immutable.{Map, HashMap, Set}
import scala.Option
 
// Container types:
// - Bundle (bundles)
// - ChainRule (terminologies)
// - ConceptTreeDisjunction (bundles)
// - DescriptionBox (descriptions)
// - Element (common)
// - RestrictionStructuredDataPropertyContext (terminologies)
// - RuleBodySegment (terminologies)
// - SingletonInstanceStructuredDataPropertyContext (descriptions)
// - TerminologyBox (terminologies)
// Contained types:
// - AnnotationPropertyValue (common)
// - ConceptInstance (descriptions)
// - DescriptionBoxExtendsClosedWorldDefinitions (descriptions)
// - DescriptionBoxRefinement (descriptions)
// - DisjointUnionOfConceptsAxiom (bundles)
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
( annotationProperties: Map[java.util.UUID, AnnotationProperty] = HashMap.empty[java.util.UUID, AnnotationProperty],
  terminologyGraphs: Map[java.util.UUID, TerminologyGraph] = HashMap.empty[java.util.UUID, TerminologyGraph],
  bundles: Map[java.util.UUID, Bundle] = HashMap.empty[java.util.UUID, Bundle],
  descriptionBoxes: Map[java.util.UUID, DescriptionBox] = HashMap.empty[java.util.UUID, DescriptionBox],

  annotations: Map[Element, Set[AnnotationPropertyValue]] = HashMap.empty[Element, Set[AnnotationPropertyValue]],
  boxAxioms: Map[TerminologyBox, Set[TerminologyBoxAxiom]] = HashMap.empty[TerminologyBox, Set[TerminologyBoxAxiom]],
  boxStatements: Map[TerminologyBox, Set[TerminologyBoxStatement]] = HashMap.empty[TerminologyBox, Set[TerminologyBoxStatement]],
  firstSegment: Map[ChainRule, RuleBodySegment] = HashMap.empty[ChainRule, RuleBodySegment],
  predicate: Map[RuleBodySegment, SegmentPredicate] = HashMap.empty[RuleBodySegment, SegmentPredicate],
  nextSegment: Map[RuleBodySegment, RuleBodySegment] = HashMap.empty[RuleBodySegment, RuleBodySegment],
  structuredDataPropertyRestrictions: Map[RestrictionStructuredDataPropertyContext, Set[RestrictionStructuredDataPropertyTuple]] = HashMap.empty[RestrictionStructuredDataPropertyContext, Set[RestrictionStructuredDataPropertyTuple]],
  scalarDataPropertyRestrictions: Map[RestrictionStructuredDataPropertyContext, Set[RestrictionScalarDataPropertyValue]] = HashMap.empty[RestrictionStructuredDataPropertyContext, Set[RestrictionScalarDataPropertyValue]],
  bundleAxioms: Map[Bundle, Set[TerminologyBundleAxiom]] = HashMap.empty[Bundle, Set[TerminologyBundleAxiom]],
  bundleStatements: Map[Bundle, Set[TerminologyBundleStatement]] = HashMap.empty[Bundle, Set[TerminologyBundleStatement]],
  disjunctions: Map[ConceptTreeDisjunction, Set[DisjointUnionOfConceptsAxiom]] = HashMap.empty[ConceptTreeDisjunction, Set[DisjointUnionOfConceptsAxiom]],
  descriptionBoxRefinements: Map[DescriptionBox, Set[DescriptionBoxRefinement]] = HashMap.empty[DescriptionBox, Set[DescriptionBoxRefinement]],
  closedWorldDefinitions: Map[DescriptionBox, Set[DescriptionBoxExtendsClosedWorldDefinitions]] = HashMap.empty[DescriptionBox, Set[DescriptionBoxExtendsClosedWorldDefinitions]],
  conceptInstances: Map[DescriptionBox, Set[ConceptInstance]] = HashMap.empty[DescriptionBox, Set[ConceptInstance]],
  reifiedRelationshipInstances: Map[DescriptionBox, Set[ReifiedRelationshipInstance]] = HashMap.empty[DescriptionBox, Set[ReifiedRelationshipInstance]],
  reifiedRelationshipInstanceDomains: Map[DescriptionBox, Set[ReifiedRelationshipInstanceDomain]] = HashMap.empty[DescriptionBox, Set[ReifiedRelationshipInstanceDomain]],
  reifiedRelationshipInstanceRanges: Map[DescriptionBox, Set[ReifiedRelationshipInstanceRange]] = HashMap.empty[DescriptionBox, Set[ReifiedRelationshipInstanceRange]],
  unreifiedRelationshipInstanceTuples: Map[DescriptionBox, Set[UnreifiedRelationshipInstanceTuple]] = HashMap.empty[DescriptionBox, Set[UnreifiedRelationshipInstanceTuple]],
  singletonScalarDataPropertyValues: Map[DescriptionBox, Set[SingletonInstanceScalarDataPropertyValue]] = HashMap.empty[DescriptionBox, Set[SingletonInstanceScalarDataPropertyValue]],
  singletonStructuredDataPropertyValues: Map[DescriptionBox, Set[SingletonInstanceStructuredDataPropertyValue]] = HashMap.empty[DescriptionBox, Set[SingletonInstanceStructuredDataPropertyValue]],
  structuredPropertyTuples: Map[SingletonInstanceStructuredDataPropertyContext, Set[StructuredDataPropertyTuple]] = HashMap.empty[SingletonInstanceStructuredDataPropertyContext, Set[StructuredDataPropertyTuple]],
  scalarDataPropertyValues: Map[SingletonInstanceStructuredDataPropertyContext, Set[ScalarDataPropertyValue]] = HashMap.empty[SingletonInstanceStructuredDataPropertyContext, Set[ScalarDataPropertyValue]],

  elementOfAnnotationPropertyValue: Map[AnnotationPropertyValue, Element] = HashMap.empty[AnnotationPropertyValue, Element],
  terminologyBoxOfTerminologyBoxAxiom: Map[TerminologyBoxAxiom, TerminologyBox] = HashMap.empty[TerminologyBoxAxiom, TerminologyBox],
  terminologyBoxOfTerminologyBoxStatement: Map[TerminologyBoxStatement, TerminologyBox] = HashMap.empty[TerminologyBoxStatement, TerminologyBox],
  chainRuleOfRuleBodySegment: Map[RuleBodySegment, ChainRule] = HashMap.empty[RuleBodySegment, ChainRule],
  ruleBodySegmentOfSegmentPredicate: Map[SegmentPredicate, RuleBodySegment] = HashMap.empty[SegmentPredicate, RuleBodySegment],
  ruleBodySegmentOfRuleBodySegment: Map[RuleBodySegment, RuleBodySegment] = HashMap.empty[RuleBodySegment, RuleBodySegment],
  restrictionStructuredDataPropertyContextOfRestrictionStructuredDataPropertyTuple: Map[RestrictionStructuredDataPropertyTuple, RestrictionStructuredDataPropertyContext] = HashMap.empty[RestrictionStructuredDataPropertyTuple, RestrictionStructuredDataPropertyContext],
  restrictionStructuredDataPropertyContextOfRestrictionScalarDataPropertyValue: Map[RestrictionScalarDataPropertyValue, RestrictionStructuredDataPropertyContext] = HashMap.empty[RestrictionScalarDataPropertyValue, RestrictionStructuredDataPropertyContext],
  bundleOfTerminologyBundleAxiom: Map[TerminologyBundleAxiom, Bundle] = HashMap.empty[TerminologyBundleAxiom, Bundle],
  bundleOfTerminologyBundleStatement: Map[TerminologyBundleStatement, Bundle] = HashMap.empty[TerminologyBundleStatement, Bundle],
  conceptTreeDisjunctionOfDisjointUnionOfConceptsAxiom: Map[DisjointUnionOfConceptsAxiom, ConceptTreeDisjunction] = HashMap.empty[DisjointUnionOfConceptsAxiom, ConceptTreeDisjunction],
  descriptionBoxOfDescriptionBoxRefinement: Map[DescriptionBoxRefinement, DescriptionBox] = HashMap.empty[DescriptionBoxRefinement, DescriptionBox],
  descriptionBoxOfDescriptionBoxExtendsClosedWorldDefinitions: Map[DescriptionBoxExtendsClosedWorldDefinitions, DescriptionBox] = HashMap.empty[DescriptionBoxExtendsClosedWorldDefinitions, DescriptionBox],
  descriptionBoxOfConceptInstance: Map[ConceptInstance, DescriptionBox] = HashMap.empty[ConceptInstance, DescriptionBox],
  descriptionBoxOfReifiedRelationshipInstance: Map[ReifiedRelationshipInstance, DescriptionBox] = HashMap.empty[ReifiedRelationshipInstance, DescriptionBox],
  descriptionBoxOfReifiedRelationshipInstanceDomain: Map[ReifiedRelationshipInstanceDomain, DescriptionBox] = HashMap.empty[ReifiedRelationshipInstanceDomain, DescriptionBox],
  descriptionBoxOfReifiedRelationshipInstanceRange: Map[ReifiedRelationshipInstanceRange, DescriptionBox] = HashMap.empty[ReifiedRelationshipInstanceRange, DescriptionBox],
  descriptionBoxOfUnreifiedRelationshipInstanceTuple: Map[UnreifiedRelationshipInstanceTuple, DescriptionBox] = HashMap.empty[UnreifiedRelationshipInstanceTuple, DescriptionBox],
  descriptionBoxOfSingletonInstanceScalarDataPropertyValue: Map[SingletonInstanceScalarDataPropertyValue, DescriptionBox] = HashMap.empty[SingletonInstanceScalarDataPropertyValue, DescriptionBox],
  descriptionBoxOfSingletonInstanceStructuredDataPropertyValue: Map[SingletonInstanceStructuredDataPropertyValue, DescriptionBox] = HashMap.empty[SingletonInstanceStructuredDataPropertyValue, DescriptionBox],
  singletonInstanceStructuredDataPropertyContextOfStructuredDataPropertyTuple: Map[StructuredDataPropertyTuple, SingletonInstanceStructuredDataPropertyContext] = HashMap.empty[StructuredDataPropertyTuple, SingletonInstanceStructuredDataPropertyContext],
  singletonInstanceStructuredDataPropertyContextOfScalarDataPropertyValue: Map[ScalarDataPropertyValue, SingletonInstanceStructuredDataPropertyContext] = HashMap.empty[ScalarDataPropertyValue, SingletonInstanceStructuredDataPropertyContext],

  annotationPropertyValueByUUID: Map[java.util.UUID, AnnotationPropertyValue] = HashMap.empty[java.util.UUID, AnnotationPropertyValue],
  conceptInstanceByUUID: Map[java.util.UUID, ConceptInstance] = HashMap.empty[java.util.UUID, ConceptInstance],
  descriptionBoxExtendsClosedWorldDefinitionsByUUID: Map[java.util.UUID, DescriptionBoxExtendsClosedWorldDefinitions] = HashMap.empty[java.util.UUID, DescriptionBoxExtendsClosedWorldDefinitions],
  descriptionBoxRefinementByUUID: Map[java.util.UUID, DescriptionBoxRefinement] = HashMap.empty[java.util.UUID, DescriptionBoxRefinement],
  disjointUnionOfConceptsAxiomByUUID: Map[java.util.UUID, DisjointUnionOfConceptsAxiom] = HashMap.empty[java.util.UUID, DisjointUnionOfConceptsAxiom],
  reifiedRelationshipInstanceByUUID: Map[java.util.UUID, ReifiedRelationshipInstance] = HashMap.empty[java.util.UUID, ReifiedRelationshipInstance],
  reifiedRelationshipInstanceDomainByUUID: Map[java.util.UUID, ReifiedRelationshipInstanceDomain] = HashMap.empty[java.util.UUID, ReifiedRelationshipInstanceDomain],
  reifiedRelationshipInstanceRangeByUUID: Map[java.util.UUID, ReifiedRelationshipInstanceRange] = HashMap.empty[java.util.UUID, ReifiedRelationshipInstanceRange],
  restrictionScalarDataPropertyValueByUUID: Map[java.util.UUID, RestrictionScalarDataPropertyValue] = HashMap.empty[java.util.UUID, RestrictionScalarDataPropertyValue],
  restrictionStructuredDataPropertyTupleByUUID: Map[java.util.UUID, RestrictionStructuredDataPropertyTuple] = HashMap.empty[java.util.UUID, RestrictionStructuredDataPropertyTuple],
  ruleBodySegmentByUUID: Map[java.util.UUID, RuleBodySegment] = HashMap.empty[java.util.UUID, RuleBodySegment],
  scalarDataPropertyValueByUUID: Map[java.util.UUID, ScalarDataPropertyValue] = HashMap.empty[java.util.UUID, ScalarDataPropertyValue],
  segmentPredicateByUUID: Map[java.util.UUID, SegmentPredicate] = HashMap.empty[java.util.UUID, SegmentPredicate],
  singletonInstanceScalarDataPropertyValueByUUID: Map[java.util.UUID, SingletonInstanceScalarDataPropertyValue] = HashMap.empty[java.util.UUID, SingletonInstanceScalarDataPropertyValue],
  singletonInstanceStructuredDataPropertyValueByUUID: Map[java.util.UUID, SingletonInstanceStructuredDataPropertyValue] = HashMap.empty[java.util.UUID, SingletonInstanceStructuredDataPropertyValue],
  structuredDataPropertyTupleByUUID: Map[java.util.UUID, StructuredDataPropertyTuple] = HashMap.empty[java.util.UUID, StructuredDataPropertyTuple],
  terminologyBoxAxiomByUUID: Map[java.util.UUID, TerminologyBoxAxiom] = HashMap.empty[java.util.UUID, TerminologyBoxAxiom],
  terminologyBoxStatementByUUID: Map[java.util.UUID, TerminologyBoxStatement] = HashMap.empty[java.util.UUID, TerminologyBoxStatement],
  terminologyBundleAxiomByUUID: Map[java.util.UUID, TerminologyBundleAxiom] = HashMap.empty[java.util.UUID, TerminologyBundleAxiom],
  terminologyBundleStatementByUUID: Map[java.util.UUID, TerminologyBundleStatement] = HashMap.empty[java.util.UUID, TerminologyBundleStatement],
  unreifiedRelationshipInstanceTupleByUUID: Map[java.util.UUID, UnreifiedRelationshipInstanceTuple] = HashMap.empty[java.util.UUID, UnreifiedRelationshipInstanceTuple]
) {
  def withAnnotationPropertyValue(key: Element, value: AnnotationPropertyValue)
  : Map[Element, Set[AnnotationPropertyValue]] 
  = annotations.updated(key, annotations.getOrElse(key, Set.empty[AnnotationPropertyValue]) + value)
  
  def withTerminologyBoxAxiom(key: TerminologyBox, value: TerminologyBoxAxiom)
  : Map[TerminologyBox, Set[TerminologyBoxAxiom]] 
  = boxAxioms.updated(key, boxAxioms.getOrElse(key, Set.empty[TerminologyBoxAxiom]) + value)
  
  def withTerminologyBoxStatement(key: TerminologyBox, value: TerminologyBoxStatement)
  : Map[TerminologyBox, Set[TerminologyBoxStatement]] 
  = boxStatements.updated(key, boxStatements.getOrElse(key, Set.empty[TerminologyBoxStatement]) + value)
  
  def withRuleBodySegment(key: ChainRule, value: RuleBodySegment)
  : Map[ChainRule, RuleBodySegment] 
  = firstSegment.updated(key, value)
  
  def withSegmentPredicate(key: RuleBodySegment, value: SegmentPredicate)
  : Map[RuleBodySegment, SegmentPredicate] 
  = predicate.updated(key, value)
  
  def withRuleBodySegment(key: RuleBodySegment, value: RuleBodySegment)
  : Map[RuleBodySegment, RuleBodySegment] 
  = nextSegment.updated(key, value)
  
  def withRestrictionStructuredDataPropertyTuple(key: RestrictionStructuredDataPropertyContext, value: RestrictionStructuredDataPropertyTuple)
  : Map[RestrictionStructuredDataPropertyContext, Set[RestrictionStructuredDataPropertyTuple]] 
  = structuredDataPropertyRestrictions.updated(key, structuredDataPropertyRestrictions.getOrElse(key, Set.empty[RestrictionStructuredDataPropertyTuple]) + value)
  
  def withRestrictionScalarDataPropertyValue(key: RestrictionStructuredDataPropertyContext, value: RestrictionScalarDataPropertyValue)
  : Map[RestrictionStructuredDataPropertyContext, Set[RestrictionScalarDataPropertyValue]] 
  = scalarDataPropertyRestrictions.updated(key, scalarDataPropertyRestrictions.getOrElse(key, Set.empty[RestrictionScalarDataPropertyValue]) + value)
  
  def withTerminologyBundleAxiom(key: Bundle, value: TerminologyBundleAxiom)
  : Map[Bundle, Set[TerminologyBundleAxiom]] 
  = bundleAxioms.updated(key, bundleAxioms.getOrElse(key, Set.empty[TerminologyBundleAxiom]) + value)
  
  def withTerminologyBundleStatement(key: Bundle, value: TerminologyBundleStatement)
  : Map[Bundle, Set[TerminologyBundleStatement]] 
  = bundleStatements.updated(key, bundleStatements.getOrElse(key, Set.empty[TerminologyBundleStatement]) + value)
  
  def withDisjointUnionOfConceptsAxiom(key: ConceptTreeDisjunction, value: DisjointUnionOfConceptsAxiom)
  : Map[ConceptTreeDisjunction, Set[DisjointUnionOfConceptsAxiom]] 
  = disjunctions.updated(key, disjunctions.getOrElse(key, Set.empty[DisjointUnionOfConceptsAxiom]) + value)
  
  def withDescriptionBoxRefinement(key: DescriptionBox, value: DescriptionBoxRefinement)
  : Map[DescriptionBox, Set[DescriptionBoxRefinement]] 
  = descriptionBoxRefinements.updated(key, descriptionBoxRefinements.getOrElse(key, Set.empty[DescriptionBoxRefinement]) + value)
  
  def withDescriptionBoxExtendsClosedWorldDefinitions(key: DescriptionBox, value: DescriptionBoxExtendsClosedWorldDefinitions)
  : Map[DescriptionBox, Set[DescriptionBoxExtendsClosedWorldDefinitions]] 
  = closedWorldDefinitions.updated(key, closedWorldDefinitions.getOrElse(key, Set.empty[DescriptionBoxExtendsClosedWorldDefinitions]) + value)
  
  def withConceptInstance(key: DescriptionBox, value: ConceptInstance)
  : Map[DescriptionBox, Set[ConceptInstance]] 
  = conceptInstances.updated(key, conceptInstances.getOrElse(key, Set.empty[ConceptInstance]) + value)
  
  def withReifiedRelationshipInstance(key: DescriptionBox, value: ReifiedRelationshipInstance)
  : Map[DescriptionBox, Set[ReifiedRelationshipInstance]] 
  = reifiedRelationshipInstances.updated(key, reifiedRelationshipInstances.getOrElse(key, Set.empty[ReifiedRelationshipInstance]) + value)
  
  def withReifiedRelationshipInstanceDomain(key: DescriptionBox, value: ReifiedRelationshipInstanceDomain)
  : Map[DescriptionBox, Set[ReifiedRelationshipInstanceDomain]] 
  = reifiedRelationshipInstanceDomains.updated(key, reifiedRelationshipInstanceDomains.getOrElse(key, Set.empty[ReifiedRelationshipInstanceDomain]) + value)
  
  def withReifiedRelationshipInstanceRange(key: DescriptionBox, value: ReifiedRelationshipInstanceRange)
  : Map[DescriptionBox, Set[ReifiedRelationshipInstanceRange]] 
  = reifiedRelationshipInstanceRanges.updated(key, reifiedRelationshipInstanceRanges.getOrElse(key, Set.empty[ReifiedRelationshipInstanceRange]) + value)
  
  def withUnreifiedRelationshipInstanceTuple(key: DescriptionBox, value: UnreifiedRelationshipInstanceTuple)
  : Map[DescriptionBox, Set[UnreifiedRelationshipInstanceTuple]] 
  = unreifiedRelationshipInstanceTuples.updated(key, unreifiedRelationshipInstanceTuples.getOrElse(key, Set.empty[UnreifiedRelationshipInstanceTuple]) + value)
  
  def withSingletonInstanceScalarDataPropertyValue(key: DescriptionBox, value: SingletonInstanceScalarDataPropertyValue)
  : Map[DescriptionBox, Set[SingletonInstanceScalarDataPropertyValue]] 
  = singletonScalarDataPropertyValues.updated(key, singletonScalarDataPropertyValues.getOrElse(key, Set.empty[SingletonInstanceScalarDataPropertyValue]) + value)
  
  def withSingletonInstanceStructuredDataPropertyValue(key: DescriptionBox, value: SingletonInstanceStructuredDataPropertyValue)
  : Map[DescriptionBox, Set[SingletonInstanceStructuredDataPropertyValue]] 
  = singletonStructuredDataPropertyValues.updated(key, singletonStructuredDataPropertyValues.getOrElse(key, Set.empty[SingletonInstanceStructuredDataPropertyValue]) + value)
  
  def withStructuredDataPropertyTuple(key: SingletonInstanceStructuredDataPropertyContext, value: StructuredDataPropertyTuple)
  : Map[SingletonInstanceStructuredDataPropertyContext, Set[StructuredDataPropertyTuple]] 
  = structuredPropertyTuples.updated(key, structuredPropertyTuples.getOrElse(key, Set.empty[StructuredDataPropertyTuple]) + value)
  
  def withScalarDataPropertyValue(key: SingletonInstanceStructuredDataPropertyContext, value: ScalarDataPropertyValue)
  : Map[SingletonInstanceStructuredDataPropertyContext, Set[ScalarDataPropertyValue]] 
  = scalarDataPropertyValues.updated(key, scalarDataPropertyValues.getOrElse(key, Set.empty[ScalarDataPropertyValue]) + value)
  
		
  def lookupModule(uuid: Option[java.util.UUID])
  : Option[Module]
  = uuid.flatMap { lookupModule }
  
  def lookupModule(uuid: java.util.UUID)
  : Option[Module]
  = lookupTerminologyBox(uuid) orElse lookupDescriptionBox(uuid)
  
  def lookupTerminologyBox(uuid: Option[java.util.UUID])
  : Option[TerminologyBox]
  = uuid.flatMap { lookupTerminologyBox }
  
  def lookupTerminologyBox(uuid: java.util.UUID)
  : Option[TerminologyBox]
  = lookupTerminologyGraph(uuid) orElse lookupBundle(uuid)

  
  def lookupAnnotationProperty(uuid: Option[java.util.UUID])
  : Option[AnnotationProperty]
  = uuid.flatMap { lookupAnnotationProperty } 
  
  def lookupAnnotationProperty(uuid: java.util.UUID)
  : Option[AnnotationProperty]
  = annotationProperties.get(uuid)
    
  
  def lookupTerminologyGraph(uuid: Option[java.util.UUID])
  : Option[TerminologyGraph]
  = uuid.flatMap { lookupTerminologyGraph } 
  
  def lookupTerminologyGraph(uuid: java.util.UUID)
  : Option[TerminologyGraph]
  = terminologyGraphs.get(uuid)
    
  
  def lookupBundle(uuid: Option[java.util.UUID])
  : Option[Bundle]
  = uuid.flatMap { lookupBundle } 
  
  def lookupBundle(uuid: java.util.UUID)
  : Option[Bundle]
  = bundles.get(uuid)
    
  
  def lookupDescriptionBox(uuid: Option[java.util.UUID])
  : Option[DescriptionBox]
  = uuid.flatMap { lookupDescriptionBox } 
  
  def lookupDescriptionBox(uuid: java.util.UUID)
  : Option[DescriptionBox]
  = descriptionBoxes.get(uuid)

  
  def lookupAnnotations(key: Option[Element])
  : Set[AnnotationPropertyValue]
  = key.fold[Set[AnnotationPropertyValue]](Set.empty[AnnotationPropertyValue]) { lookupAnnotations }
  
  def lookupAnnotations(key: Element)
  : Set[AnnotationPropertyValue]
  = annotations.getOrElse(key, Set.empty[AnnotationPropertyValue])
  
  def lookupAnnotationPropertyValue(uuid: Option[java.util.UUID])
  : Option[AnnotationPropertyValue]
  = uuid.flatMap { lookupAnnotationPropertyValue } 
  
  def lookupAnnotationPropertyValue(uuid: java.util.UUID)
  : Option[AnnotationPropertyValue]
  = annotationPropertyValueByUUID.get(uuid)
    
  def lookupBoxAxioms(key: Option[TerminologyBox])
  : Set[TerminologyBoxAxiom]
  = key.fold[Set[TerminologyBoxAxiom]](Set.empty[TerminologyBoxAxiom]) { lookupBoxAxioms }
  
  def lookupBoxAxioms(key: TerminologyBox)
  : Set[TerminologyBoxAxiom]
  = boxAxioms.getOrElse(key, Set.empty[TerminologyBoxAxiom])
  
  def lookupTerminologyBoxAxiom(uuid: Option[java.util.UUID])
  : Option[TerminologyBoxAxiom]
  = uuid.flatMap { lookupTerminologyBoxAxiom } 
  
  def lookupTerminologyBoxAxiom(uuid: java.util.UUID)
  : Option[TerminologyBoxAxiom]
  = terminologyBoxAxiomByUUID.get(uuid)
    
  def lookupBoxStatements(key: Option[TerminologyBox])
  : Set[TerminologyBoxStatement]
  = key.fold[Set[TerminologyBoxStatement]](Set.empty[TerminologyBoxStatement]) { lookupBoxStatements }
  
  def lookupBoxStatements(key: TerminologyBox)
  : Set[TerminologyBoxStatement]
  = boxStatements.getOrElse(key, Set.empty[TerminologyBoxStatement])
  
  def lookupTerminologyBoxStatement(uuid: Option[java.util.UUID])
  : Option[TerminologyBoxStatement]
  = uuid.flatMap { lookupTerminologyBoxStatement } 
  
  def lookupTerminologyBoxStatement(uuid: java.util.UUID)
  : Option[TerminologyBoxStatement]
  = terminologyBoxStatementByUUID.get(uuid)
    
  def lookupFirstSegment(key: Option[ChainRule])
  : Option[RuleBodySegment]
  = key.flatMap { lookupFirstSegment }
  
  def lookupFirstSegment(key: ChainRule)
  : Option[RuleBodySegment]
  = firstSegment.get(key)
  
  def lookupRuleBodySegment(uuid: Option[java.util.UUID])
  : Option[RuleBodySegment]
  = uuid.flatMap { lookupRuleBodySegment } 
  
  def lookupRuleBodySegment(uuid: java.util.UUID)
  : Option[RuleBodySegment]
  = ruleBodySegmentByUUID.get(uuid)
    
  def lookupPredicate(key: Option[RuleBodySegment])
  : Option[SegmentPredicate]
  = key.flatMap { lookupPredicate }
  
  def lookupPredicate(key: RuleBodySegment)
  : Option[SegmentPredicate]
  = predicate.get(key)
  
  def lookupSegmentPredicate(uuid: Option[java.util.UUID])
  : Option[SegmentPredicate]
  = uuid.flatMap { lookupSegmentPredicate } 
  
  def lookupSegmentPredicate(uuid: java.util.UUID)
  : Option[SegmentPredicate]
  = segmentPredicateByUUID.get(uuid)
    
  def lookupNextSegment(key: Option[RuleBodySegment])
  : Option[RuleBodySegment]
  = key.flatMap { lookupNextSegment }
  
  def lookupNextSegment(key: RuleBodySegment)
  : Option[RuleBodySegment]
  = nextSegment.get(key)
    
  def lookupStructuredDataPropertyRestrictions(key: Option[RestrictionStructuredDataPropertyContext])
  : Set[RestrictionStructuredDataPropertyTuple]
  = key.fold[Set[RestrictionStructuredDataPropertyTuple]](Set.empty[RestrictionStructuredDataPropertyTuple]) { lookupStructuredDataPropertyRestrictions }
  
  def lookupStructuredDataPropertyRestrictions(key: RestrictionStructuredDataPropertyContext)
  : Set[RestrictionStructuredDataPropertyTuple]
  = structuredDataPropertyRestrictions.getOrElse(key, Set.empty[RestrictionStructuredDataPropertyTuple])
  
  def lookupRestrictionStructuredDataPropertyTuple(uuid: Option[java.util.UUID])
  : Option[RestrictionStructuredDataPropertyTuple]
  = uuid.flatMap { lookupRestrictionStructuredDataPropertyTuple } 
  
  def lookupRestrictionStructuredDataPropertyTuple(uuid: java.util.UUID)
  : Option[RestrictionStructuredDataPropertyTuple]
  = restrictionStructuredDataPropertyTupleByUUID.get(uuid)
    
  def lookupScalarDataPropertyRestrictions(key: Option[RestrictionStructuredDataPropertyContext])
  : Set[RestrictionScalarDataPropertyValue]
  = key.fold[Set[RestrictionScalarDataPropertyValue]](Set.empty[RestrictionScalarDataPropertyValue]) { lookupScalarDataPropertyRestrictions }
  
  def lookupScalarDataPropertyRestrictions(key: RestrictionStructuredDataPropertyContext)
  : Set[RestrictionScalarDataPropertyValue]
  = scalarDataPropertyRestrictions.getOrElse(key, Set.empty[RestrictionScalarDataPropertyValue])
  
  def lookupRestrictionScalarDataPropertyValue(uuid: Option[java.util.UUID])
  : Option[RestrictionScalarDataPropertyValue]
  = uuid.flatMap { lookupRestrictionScalarDataPropertyValue } 
  
  def lookupRestrictionScalarDataPropertyValue(uuid: java.util.UUID)
  : Option[RestrictionScalarDataPropertyValue]
  = restrictionScalarDataPropertyValueByUUID.get(uuid)
    
  def lookupBundleAxioms(key: Option[Bundle])
  : Set[TerminologyBundleAxiom]
  = key.fold[Set[TerminologyBundleAxiom]](Set.empty[TerminologyBundleAxiom]) { lookupBundleAxioms }
  
  def lookupBundleAxioms(key: Bundle)
  : Set[TerminologyBundleAxiom]
  = bundleAxioms.getOrElse(key, Set.empty[TerminologyBundleAxiom])
  
  def lookupTerminologyBundleAxiom(uuid: Option[java.util.UUID])
  : Option[TerminologyBundleAxiom]
  = uuid.flatMap { lookupTerminologyBundleAxiom } 
  
  def lookupTerminologyBundleAxiom(uuid: java.util.UUID)
  : Option[TerminologyBundleAxiom]
  = terminologyBundleAxiomByUUID.get(uuid)
    
  def lookupBundleStatements(key: Option[Bundle])
  : Set[TerminologyBundleStatement]
  = key.fold[Set[TerminologyBundleStatement]](Set.empty[TerminologyBundleStatement]) { lookupBundleStatements }
  
  def lookupBundleStatements(key: Bundle)
  : Set[TerminologyBundleStatement]
  = bundleStatements.getOrElse(key, Set.empty[TerminologyBundleStatement])
  
  def lookupTerminologyBundleStatement(uuid: Option[java.util.UUID])
  : Option[TerminologyBundleStatement]
  = uuid.flatMap { lookupTerminologyBundleStatement } 
  
  def lookupTerminologyBundleStatement(uuid: java.util.UUID)
  : Option[TerminologyBundleStatement]
  = terminologyBundleStatementByUUID.get(uuid)
    
  def lookupDisjunctions(key: Option[ConceptTreeDisjunction])
  : Set[DisjointUnionOfConceptsAxiom]
  = key.fold[Set[DisjointUnionOfConceptsAxiom]](Set.empty[DisjointUnionOfConceptsAxiom]) { lookupDisjunctions }
  
  def lookupDisjunctions(key: ConceptTreeDisjunction)
  : Set[DisjointUnionOfConceptsAxiom]
  = disjunctions.getOrElse(key, Set.empty[DisjointUnionOfConceptsAxiom])
  
  def lookupDisjointUnionOfConceptsAxiom(uuid: Option[java.util.UUID])
  : Option[DisjointUnionOfConceptsAxiom]
  = uuid.flatMap { lookupDisjointUnionOfConceptsAxiom } 
  
  def lookupDisjointUnionOfConceptsAxiom(uuid: java.util.UUID)
  : Option[DisjointUnionOfConceptsAxiom]
  = disjointUnionOfConceptsAxiomByUUID.get(uuid)
    
  def lookupDescriptionBoxRefinements(key: Option[DescriptionBox])
  : Set[DescriptionBoxRefinement]
  = key.fold[Set[DescriptionBoxRefinement]](Set.empty[DescriptionBoxRefinement]) { lookupDescriptionBoxRefinements }
  
  def lookupDescriptionBoxRefinements(key: DescriptionBox)
  : Set[DescriptionBoxRefinement]
  = descriptionBoxRefinements.getOrElse(key, Set.empty[DescriptionBoxRefinement])
  
  def lookupDescriptionBoxRefinement(uuid: Option[java.util.UUID])
  : Option[DescriptionBoxRefinement]
  = uuid.flatMap { lookupDescriptionBoxRefinement } 
  
  def lookupDescriptionBoxRefinement(uuid: java.util.UUID)
  : Option[DescriptionBoxRefinement]
  = descriptionBoxRefinementByUUID.get(uuid)
    
  def lookupClosedWorldDefinitions(key: Option[DescriptionBox])
  : Set[DescriptionBoxExtendsClosedWorldDefinitions]
  = key.fold[Set[DescriptionBoxExtendsClosedWorldDefinitions]](Set.empty[DescriptionBoxExtendsClosedWorldDefinitions]) { lookupClosedWorldDefinitions }
  
  def lookupClosedWorldDefinitions(key: DescriptionBox)
  : Set[DescriptionBoxExtendsClosedWorldDefinitions]
  = closedWorldDefinitions.getOrElse(key, Set.empty[DescriptionBoxExtendsClosedWorldDefinitions])
  
  def lookupDescriptionBoxExtendsClosedWorldDefinitions(uuid: Option[java.util.UUID])
  : Option[DescriptionBoxExtendsClosedWorldDefinitions]
  = uuid.flatMap { lookupDescriptionBoxExtendsClosedWorldDefinitions } 
  
  def lookupDescriptionBoxExtendsClosedWorldDefinitions(uuid: java.util.UUID)
  : Option[DescriptionBoxExtendsClosedWorldDefinitions]
  = descriptionBoxExtendsClosedWorldDefinitionsByUUID.get(uuid)
    
  def lookupConceptInstances(key: Option[DescriptionBox])
  : Set[ConceptInstance]
  = key.fold[Set[ConceptInstance]](Set.empty[ConceptInstance]) { lookupConceptInstances }
  
  def lookupConceptInstances(key: DescriptionBox)
  : Set[ConceptInstance]
  = conceptInstances.getOrElse(key, Set.empty[ConceptInstance])
  
  def lookupConceptInstance(uuid: Option[java.util.UUID])
  : Option[ConceptInstance]
  = uuid.flatMap { lookupConceptInstance } 
  
  def lookupConceptInstance(uuid: java.util.UUID)
  : Option[ConceptInstance]
  = conceptInstanceByUUID.get(uuid)
    
  def lookupReifiedRelationshipInstances(key: Option[DescriptionBox])
  : Set[ReifiedRelationshipInstance]
  = key.fold[Set[ReifiedRelationshipInstance]](Set.empty[ReifiedRelationshipInstance]) { lookupReifiedRelationshipInstances }
  
  def lookupReifiedRelationshipInstances(key: DescriptionBox)
  : Set[ReifiedRelationshipInstance]
  = reifiedRelationshipInstances.getOrElse(key, Set.empty[ReifiedRelationshipInstance])
  
  def lookupReifiedRelationshipInstance(uuid: Option[java.util.UUID])
  : Option[ReifiedRelationshipInstance]
  = uuid.flatMap { lookupReifiedRelationshipInstance } 
  
  def lookupReifiedRelationshipInstance(uuid: java.util.UUID)
  : Option[ReifiedRelationshipInstance]
  = reifiedRelationshipInstanceByUUID.get(uuid)
    
  def lookupReifiedRelationshipInstanceDomains(key: Option[DescriptionBox])
  : Set[ReifiedRelationshipInstanceDomain]
  = key.fold[Set[ReifiedRelationshipInstanceDomain]](Set.empty[ReifiedRelationshipInstanceDomain]) { lookupReifiedRelationshipInstanceDomains }
  
  def lookupReifiedRelationshipInstanceDomains(key: DescriptionBox)
  : Set[ReifiedRelationshipInstanceDomain]
  = reifiedRelationshipInstanceDomains.getOrElse(key, Set.empty[ReifiedRelationshipInstanceDomain])
  
  def lookupReifiedRelationshipInstanceDomain(uuid: Option[java.util.UUID])
  : Option[ReifiedRelationshipInstanceDomain]
  = uuid.flatMap { lookupReifiedRelationshipInstanceDomain } 
  
  def lookupReifiedRelationshipInstanceDomain(uuid: java.util.UUID)
  : Option[ReifiedRelationshipInstanceDomain]
  = reifiedRelationshipInstanceDomainByUUID.get(uuid)
    
  def lookupReifiedRelationshipInstanceRanges(key: Option[DescriptionBox])
  : Set[ReifiedRelationshipInstanceRange]
  = key.fold[Set[ReifiedRelationshipInstanceRange]](Set.empty[ReifiedRelationshipInstanceRange]) { lookupReifiedRelationshipInstanceRanges }
  
  def lookupReifiedRelationshipInstanceRanges(key: DescriptionBox)
  : Set[ReifiedRelationshipInstanceRange]
  = reifiedRelationshipInstanceRanges.getOrElse(key, Set.empty[ReifiedRelationshipInstanceRange])
  
  def lookupReifiedRelationshipInstanceRange(uuid: Option[java.util.UUID])
  : Option[ReifiedRelationshipInstanceRange]
  = uuid.flatMap { lookupReifiedRelationshipInstanceRange } 
  
  def lookupReifiedRelationshipInstanceRange(uuid: java.util.UUID)
  : Option[ReifiedRelationshipInstanceRange]
  = reifiedRelationshipInstanceRangeByUUID.get(uuid)
    
  def lookupUnreifiedRelationshipInstanceTuples(key: Option[DescriptionBox])
  : Set[UnreifiedRelationshipInstanceTuple]
  = key.fold[Set[UnreifiedRelationshipInstanceTuple]](Set.empty[UnreifiedRelationshipInstanceTuple]) { lookupUnreifiedRelationshipInstanceTuples }
  
  def lookupUnreifiedRelationshipInstanceTuples(key: DescriptionBox)
  : Set[UnreifiedRelationshipInstanceTuple]
  = unreifiedRelationshipInstanceTuples.getOrElse(key, Set.empty[UnreifiedRelationshipInstanceTuple])
  
  def lookupUnreifiedRelationshipInstanceTuple(uuid: Option[java.util.UUID])
  : Option[UnreifiedRelationshipInstanceTuple]
  = uuid.flatMap { lookupUnreifiedRelationshipInstanceTuple } 
  
  def lookupUnreifiedRelationshipInstanceTuple(uuid: java.util.UUID)
  : Option[UnreifiedRelationshipInstanceTuple]
  = unreifiedRelationshipInstanceTupleByUUID.get(uuid)
    
  def lookupSingletonScalarDataPropertyValues(key: Option[DescriptionBox])
  : Set[SingletonInstanceScalarDataPropertyValue]
  = key.fold[Set[SingletonInstanceScalarDataPropertyValue]](Set.empty[SingletonInstanceScalarDataPropertyValue]) { lookupSingletonScalarDataPropertyValues }
  
  def lookupSingletonScalarDataPropertyValues(key: DescriptionBox)
  : Set[SingletonInstanceScalarDataPropertyValue]
  = singletonScalarDataPropertyValues.getOrElse(key, Set.empty[SingletonInstanceScalarDataPropertyValue])
  
  def lookupSingletonInstanceScalarDataPropertyValue(uuid: Option[java.util.UUID])
  : Option[SingletonInstanceScalarDataPropertyValue]
  = uuid.flatMap { lookupSingletonInstanceScalarDataPropertyValue } 
  
  def lookupSingletonInstanceScalarDataPropertyValue(uuid: java.util.UUID)
  : Option[SingletonInstanceScalarDataPropertyValue]
  = singletonInstanceScalarDataPropertyValueByUUID.get(uuid)
    
  def lookupSingletonStructuredDataPropertyValues(key: Option[DescriptionBox])
  : Set[SingletonInstanceStructuredDataPropertyValue]
  = key.fold[Set[SingletonInstanceStructuredDataPropertyValue]](Set.empty[SingletonInstanceStructuredDataPropertyValue]) { lookupSingletonStructuredDataPropertyValues }
  
  def lookupSingletonStructuredDataPropertyValues(key: DescriptionBox)
  : Set[SingletonInstanceStructuredDataPropertyValue]
  = singletonStructuredDataPropertyValues.getOrElse(key, Set.empty[SingletonInstanceStructuredDataPropertyValue])
  
  def lookupSingletonInstanceStructuredDataPropertyValue(uuid: Option[java.util.UUID])
  : Option[SingletonInstanceStructuredDataPropertyValue]
  = uuid.flatMap { lookupSingletonInstanceStructuredDataPropertyValue } 
  
  def lookupSingletonInstanceStructuredDataPropertyValue(uuid: java.util.UUID)
  : Option[SingletonInstanceStructuredDataPropertyValue]
  = singletonInstanceStructuredDataPropertyValueByUUID.get(uuid)
    
  def lookupStructuredPropertyTuples(key: Option[SingletonInstanceStructuredDataPropertyContext])
  : Set[StructuredDataPropertyTuple]
  = key.fold[Set[StructuredDataPropertyTuple]](Set.empty[StructuredDataPropertyTuple]) { lookupStructuredPropertyTuples }
  
  def lookupStructuredPropertyTuples(key: SingletonInstanceStructuredDataPropertyContext)
  : Set[StructuredDataPropertyTuple]
  = structuredPropertyTuples.getOrElse(key, Set.empty[StructuredDataPropertyTuple])
  
  def lookupStructuredDataPropertyTuple(uuid: Option[java.util.UUID])
  : Option[StructuredDataPropertyTuple]
  = uuid.flatMap { lookupStructuredDataPropertyTuple } 
  
  def lookupStructuredDataPropertyTuple(uuid: java.util.UUID)
  : Option[StructuredDataPropertyTuple]
  = structuredDataPropertyTupleByUUID.get(uuid)
    
  def lookupScalarDataPropertyValues(key: Option[SingletonInstanceStructuredDataPropertyContext])
  : Set[ScalarDataPropertyValue]
  = key.fold[Set[ScalarDataPropertyValue]](Set.empty[ScalarDataPropertyValue]) { lookupScalarDataPropertyValues }
  
  def lookupScalarDataPropertyValues(key: SingletonInstanceStructuredDataPropertyContext)
  : Set[ScalarDataPropertyValue]
  = scalarDataPropertyValues.getOrElse(key, Set.empty[ScalarDataPropertyValue])
  
  def lookupScalarDataPropertyValue(uuid: Option[java.util.UUID])
  : Option[ScalarDataPropertyValue]
  = uuid.flatMap { lookupScalarDataPropertyValue } 
  
  def lookupScalarDataPropertyValue(uuid: java.util.UUID)
  : Option[ScalarDataPropertyValue]
  = scalarDataPropertyValueByUUID.get(uuid)

  
  def lookupElement(uuid: java.util.UUID)
  : Option[Element]
  = lookupModule(uuid) orElse
    lookupTerminologyBoxAxiom(uuid) orElse
    lookupTerminologyBoxStatement(uuid) orElse
    lookupRuleBodySegment(uuid) orElse
    lookupSegmentPredicate(uuid) orElse
    lookupRuleBodySegment(uuid) orElse
    lookupRestrictionStructuredDataPropertyTuple(uuid) orElse
    lookupRestrictionScalarDataPropertyValue(uuid) orElse
    lookupTerminologyBundleAxiom(uuid) orElse
    lookupTerminologyBundleStatement(uuid) orElse
    lookupDisjointUnionOfConceptsAxiom(uuid) orElse
    lookupDescriptionBoxRefinement(uuid) orElse
    lookupDescriptionBoxExtendsClosedWorldDefinitions(uuid) orElse
    lookupConceptInstance(uuid) orElse
    lookupReifiedRelationshipInstance(uuid) orElse
    lookupReifiedRelationshipInstanceDomain(uuid) orElse
    lookupReifiedRelationshipInstanceRange(uuid) orElse
    lookupUnreifiedRelationshipInstanceTuple(uuid) orElse
    lookupSingletonInstanceScalarDataPropertyValue(uuid) orElse
    lookupSingletonInstanceStructuredDataPropertyValue(uuid) orElse
    lookupStructuredDataPropertyTuple(uuid) orElse
    lookupScalarDataPropertyValue(uuid)

}
