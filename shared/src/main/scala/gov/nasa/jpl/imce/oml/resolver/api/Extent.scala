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
import scala.{Option,StringContext}

// Container types:
// - Bundle (bundles)
// - ChainRule (terminologies)
// - ConceptTreeDisjunction (bundles)
// - DescriptionBox (descriptions)
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
  : Map[taggedTypes.TerminologyGraphUUID, TerminologyGraph] 
  = HashMap.empty[taggedTypes.TerminologyGraphUUID, TerminologyGraph],
  bundles
  : Map[taggedTypes.BundleUUID, Bundle] 
  = HashMap.empty[taggedTypes.BundleUUID, Bundle],
  descriptionBoxes
  : Map[taggedTypes.DescriptionBoxUUID, DescriptionBox] 
  = HashMap.empty[taggedTypes.DescriptionBoxUUID, DescriptionBox],

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

  annotationPropertyByUUID
  : Map[taggedTypes.AnnotationPropertyUUID, AnnotationProperty]
  = HashMap.empty[taggedTypes.AnnotationPropertyUUID, AnnotationProperty],
  annotationPropertyValueByUUID
  : Map[taggedTypes.AnnotationPropertyValueUUID, AnnotationPropertyValue]
  = HashMap.empty[taggedTypes.AnnotationPropertyValueUUID, AnnotationPropertyValue],
  conceptInstanceByUUID
  : Map[taggedTypes.ConceptInstanceUUID, ConceptInstance]
  = HashMap.empty[taggedTypes.ConceptInstanceUUID, ConceptInstance],
  descriptionBoxExtendsClosedWorldDefinitionsByUUID
  : Map[taggedTypes.DescriptionBoxExtendsClosedWorldDefinitionsUUID, DescriptionBoxExtendsClosedWorldDefinitions]
  = HashMap.empty[taggedTypes.DescriptionBoxExtendsClosedWorldDefinitionsUUID, DescriptionBoxExtendsClosedWorldDefinitions],
  descriptionBoxRefinementByUUID
  : Map[taggedTypes.DescriptionBoxRefinementUUID, DescriptionBoxRefinement]
  = HashMap.empty[taggedTypes.DescriptionBoxRefinementUUID, DescriptionBoxRefinement],
  disjointUnionOfConceptsAxiomByUUID
  : Map[taggedTypes.DisjointUnionOfConceptsAxiomUUID, DisjointUnionOfConceptsAxiom]
  = HashMap.empty[taggedTypes.DisjointUnionOfConceptsAxiomUUID, DisjointUnionOfConceptsAxiom],
  forwardPropertyByUUID
  : Map[taggedTypes.ForwardPropertyUUID, ForwardProperty]
  = HashMap.empty[taggedTypes.ForwardPropertyUUID, ForwardProperty],
  inversePropertyByUUID
  : Map[taggedTypes.InversePropertyUUID, InverseProperty]
  = HashMap.empty[taggedTypes.InversePropertyUUID, InverseProperty],
  reifiedRelationshipInstanceByUUID
  : Map[taggedTypes.ReifiedRelationshipInstanceUUID, ReifiedRelationshipInstance]
  = HashMap.empty[taggedTypes.ReifiedRelationshipInstanceUUID, ReifiedRelationshipInstance],
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
  : Map[taggedTypes.RestrictionStructuredDataPropertyTupleUUID, RestrictionStructuredDataPropertyTuple]
  = HashMap.empty[taggedTypes.RestrictionStructuredDataPropertyTupleUUID, RestrictionStructuredDataPropertyTuple],
  ruleBodySegmentByUUID
  : Map[taggedTypes.RuleBodySegmentUUID, RuleBodySegment]
  = HashMap.empty[taggedTypes.RuleBodySegmentUUID, RuleBodySegment],
  scalarDataPropertyValueByUUID
  : Map[taggedTypes.ScalarDataPropertyValueUUID, ScalarDataPropertyValue]
  = HashMap.empty[taggedTypes.ScalarDataPropertyValueUUID, ScalarDataPropertyValue],
  segmentPredicateByUUID
  : Map[taggedTypes.SegmentPredicateUUID, SegmentPredicate]
  = HashMap.empty[taggedTypes.SegmentPredicateUUID, SegmentPredicate],
  singletonInstanceScalarDataPropertyValueByUUID
  : Map[taggedTypes.SingletonInstanceScalarDataPropertyValueUUID, SingletonInstanceScalarDataPropertyValue]
  = HashMap.empty[taggedTypes.SingletonInstanceScalarDataPropertyValueUUID, SingletonInstanceScalarDataPropertyValue],
  singletonInstanceStructuredDataPropertyValueByUUID
  : Map[taggedTypes.SingletonInstanceStructuredDataPropertyValueUUID, SingletonInstanceStructuredDataPropertyValue]
  = HashMap.empty[taggedTypes.SingletonInstanceStructuredDataPropertyValueUUID, SingletonInstanceStructuredDataPropertyValue],
  structuredDataPropertyTupleByUUID
  : Map[taggedTypes.StructuredDataPropertyTupleUUID, StructuredDataPropertyTuple]
  = HashMap.empty[taggedTypes.StructuredDataPropertyTupleUUID, StructuredDataPropertyTuple],
  terminologyBoxAxiomByUUID
  : Map[taggedTypes.TerminologyBoxAxiomUUID, TerminologyBoxAxiom]
  = HashMap.empty[taggedTypes.TerminologyBoxAxiomUUID, TerminologyBoxAxiom],
  terminologyBoxStatementByUUID
  : Map[taggedTypes.TerminologyBoxStatementUUID, TerminologyBoxStatement]
  = HashMap.empty[taggedTypes.TerminologyBoxStatementUUID, TerminologyBoxStatement],
  terminologyBundleAxiomByUUID
  : Map[taggedTypes.TerminologyBundleAxiomUUID, TerminologyBundleAxiom]
  = HashMap.empty[taggedTypes.TerminologyBundleAxiomUUID, TerminologyBundleAxiom],
  terminologyBundleStatementByUUID
  : Map[taggedTypes.TerminologyBundleStatementUUID, TerminologyBundleStatement]
  = HashMap.empty[taggedTypes.TerminologyBundleStatementUUID, TerminologyBundleStatement],
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
  = lookupTerminologyBox(uuid.asInstanceOf[taggedTypes.TerminologyBoxUUID]) orElse
  lookupDescriptionBox(uuid.asInstanceOf[taggedTypes.DescriptionBoxUUID])
  
  def lookupTerminologyBox
  (uuid: Option[taggedTypes.TerminologyBoxUUID])
  : Option[TerminologyBox]
  = uuid.flatMap { 
  	lookupTerminologyBox
  }
  
  def lookupTerminologyBox
  (uuid: taggedTypes.TerminologyBoxUUID)
  : Option[TerminologyBox]
  = lookupTerminologyGraph(uuid.asInstanceOf[taggedTypes.TerminologyGraphUUID]) orElse 
  lookupBundle(uuid.asInstanceOf[taggedTypes.BundleUUID])
  
  def lookupTerminologyGraph //1
  (uuid: Option[taggedTypes.TerminologyGraphUUID])
  : Option[TerminologyGraph]
  = uuid.flatMap {
    lookupTerminologyGraph
  } 
  
  def lookupTerminologyGraph //2
  (uuid: taggedTypes.TerminologyGraphUUID)
  : Option[TerminologyGraph]
  = terminologyGraphs.get(uuid)
    
  
  def lookupBundle //1
  (uuid: Option[taggedTypes.BundleUUID])
  : Option[Bundle]
  = uuid.flatMap {
    lookupBundle
  } 
  
  def lookupBundle //2
  (uuid: taggedTypes.BundleUUID)
  : Option[Bundle]
  = bundles.get(uuid)
    
  
  def lookupDescriptionBox //1
  (uuid: Option[taggedTypes.DescriptionBoxUUID])
  : Option[DescriptionBox]
  = uuid.flatMap {
    lookupDescriptionBox
  } 
  
  def lookupDescriptionBox //2
  (uuid: taggedTypes.DescriptionBoxUUID)
  : Option[DescriptionBox]
  = descriptionBoxes.get(uuid)

    def lookupAnnotations //7
  (key: Option[LogicalElement])
  : Set[AnnotationPropertyValue]
  = key
  .fold[Set[AnnotationPropertyValue]] { 
  	Set.empty[AnnotationPropertyValue] 
  }{ lookupAnnotations }
  
  def lookupAnnotations //8
  (key: LogicalElement)
  : Set[AnnotationPropertyValue]
  = annotations.getOrElse(key, Set.empty[AnnotationPropertyValue])
  
  def lookupAnnotationPropertyValue //9
  (uuid: Option[taggedTypes.AnnotationPropertyValueUUID])
  : Option[AnnotationPropertyValue]
  = uuid.flatMap {
    lookupAnnotationPropertyValue
  }
  
  def lookupAnnotationPropertyValue //10
  (uuid: taggedTypes.AnnotationPropertyValueUUID)
  : Option[AnnotationPropertyValue]
  = annotationPropertyValueByUUID.get(uuid)
    
  def lookupAnnotationProperties //7
  (key: Option[Module])
  : Set[AnnotationProperty]
  = key
  .fold[Set[AnnotationProperty]] { 
  	Set.empty[AnnotationProperty] 
  }{ lookupAnnotationProperties }
  
  def lookupAnnotationProperties //8
  (key: Module)
  : Set[AnnotationProperty]
  = annotationProperties.getOrElse(key, Set.empty[AnnotationProperty])
  
  def lookupAnnotationProperty //9
  (uuid: Option[taggedTypes.AnnotationPropertyUUID])
  : Option[AnnotationProperty]
  = uuid.flatMap {
    lookupAnnotationProperty
  }
  
  def lookupAnnotationProperty //10
  (uuid: taggedTypes.AnnotationPropertyUUID)
  : Option[AnnotationProperty]
  = annotationPropertyByUUID.get(uuid)
    
  def lookupBoxAxioms //7
  (key: Option[TerminologyBox])
  : Set[TerminologyBoxAxiom]
  = key
  .fold[Set[TerminologyBoxAxiom]] { 
  	Set.empty[TerminologyBoxAxiom] 
  }{ lookupBoxAxioms }
  
  def lookupBoxAxioms //8
  (key: TerminologyBox)
  : Set[TerminologyBoxAxiom]
  = boxAxioms.getOrElse(key, Set.empty[TerminologyBoxAxiom])
  
  def lookupTerminologyBoxAxiom //9
  (uuid: Option[taggedTypes.TerminologyBoxAxiomUUID])
  : Option[TerminologyBoxAxiom]
  = uuid.flatMap {
    lookupTerminologyBoxAxiom
  }
  
  def lookupTerminologyBoxAxiom //10
  (uuid: taggedTypes.TerminologyBoxAxiomUUID)
  : Option[TerminologyBoxAxiom]
  = terminologyBoxAxiomByUUID.get(uuid)
    
  def lookupBoxStatements //7
  (key: Option[TerminologyBox])
  : Set[TerminologyBoxStatement]
  = key
  .fold[Set[TerminologyBoxStatement]] { 
  	Set.empty[TerminologyBoxStatement] 
  }{ lookupBoxStatements }
  
  def lookupBoxStatements //8
  (key: TerminologyBox)
  : Set[TerminologyBoxStatement]
  = boxStatements.getOrElse(key, Set.empty[TerminologyBoxStatement])
  
  def lookupTerminologyBoxStatement //9
  (uuid: Option[taggedTypes.TerminologyBoxStatementUUID])
  : Option[TerminologyBoxStatement]
  = uuid.flatMap {
    lookupTerminologyBoxStatement
  }
  
  def lookupTerminologyBoxStatement //10
  (uuid: taggedTypes.TerminologyBoxStatementUUID)
  : Option[TerminologyBoxStatement]
  = terminologyBoxStatementByUUID.get(uuid)
    
  def lookupForwardProperty //3
  (key: Option[ReifiedRelationship])
  : Option[ForwardProperty]
  = key.flatMap { lookupForwardProperty }
  
  def lookupForwardProperty //4
  (key: ReifiedRelationship)
  : Option[ForwardProperty]
  = forwardProperty.get(key)
  
  //def lookupForwardProperty //5
  //(uuid: Option[taggedTypes.ForwardPropertyUUID])
  //: Option[ForwardProperty]
  //= uuid.flatMap {
  //  lookupForwardProperty
  //}
  
  def lookupForwardProperty //6
  (uuid: taggedTypes.ForwardPropertyUUID)
  : Option[ForwardProperty]
  = forwardPropertyByUUID.get(uuid)
    
  def lookupInverseProperty //3
  (key: Option[ReifiedRelationship])
  : Option[InverseProperty]
  = key.flatMap { lookupInverseProperty }
  
  def lookupInverseProperty //4
  (key: ReifiedRelationship)
  : Option[InverseProperty]
  = inverseProperty.get(key)
  
  //def lookupInverseProperty //5
  //(uuid: Option[taggedTypes.InversePropertyUUID])
  //: Option[InverseProperty]
  //= uuid.flatMap {
  //  lookupInverseProperty
  //}
  
  def lookupInverseProperty //6
  (uuid: taggedTypes.InversePropertyUUID)
  : Option[InverseProperty]
  = inversePropertyByUUID.get(uuid)
    
  def lookupFirstSegment //3
  (key: Option[ChainRule])
  : Option[RuleBodySegment]
  = key.flatMap { lookupFirstSegment }
  
  def lookupFirstSegment //4
  (key: ChainRule)
  : Option[RuleBodySegment]
  = firstSegment.get(key)
  
  //def lookupRuleBodySegment //5
  //(uuid: Option[taggedTypes.RuleBodySegmentUUID])
  //: Option[RuleBodySegment]
  //= uuid.flatMap {
  //  lookupRuleBodySegment
  //}
  
  def lookupRuleBodySegment //6
  (uuid: taggedTypes.RuleBodySegmentUUID)
  : Option[RuleBodySegment]
  = ruleBodySegmentByUUID.get(uuid)
    
  def lookupPredicate //3
  (key: Option[RuleBodySegment])
  : Option[SegmentPredicate]
  = key.flatMap { lookupPredicate }
  
  def lookupPredicate //4
  (key: RuleBodySegment)
  : Option[SegmentPredicate]
  = predicate.get(key)
  
  //def lookupSegmentPredicate //5
  //(uuid: Option[taggedTypes.SegmentPredicateUUID])
  //: Option[SegmentPredicate]
  //= uuid.flatMap {
  //  lookupSegmentPredicate
  //}
  
  def lookupSegmentPredicate //6
  (uuid: taggedTypes.SegmentPredicateUUID)
  : Option[SegmentPredicate]
  = segmentPredicateByUUID.get(uuid)
    
  def lookupNextSegment //3
  (key: Option[RuleBodySegment])
  : Option[RuleBodySegment]
  = key.flatMap { lookupNextSegment }
  
  def lookupNextSegment //4
  (key: RuleBodySegment)
  : Option[RuleBodySegment]
  = nextSegment.get(key)
    
  def lookupStructuredDataPropertyRestrictions //7
  (key: Option[RestrictionStructuredDataPropertyContext])
  : Set[RestrictionStructuredDataPropertyTuple]
  = key
  .fold[Set[RestrictionStructuredDataPropertyTuple]] { 
  	Set.empty[RestrictionStructuredDataPropertyTuple] 
  }{ lookupStructuredDataPropertyRestrictions }
  
  def lookupStructuredDataPropertyRestrictions //8
  (key: RestrictionStructuredDataPropertyContext)
  : Set[RestrictionStructuredDataPropertyTuple]
  = structuredDataPropertyRestrictions.getOrElse(key, Set.empty[RestrictionStructuredDataPropertyTuple])
  
  def lookupRestrictionStructuredDataPropertyTuple //9
  (uuid: Option[taggedTypes.RestrictionStructuredDataPropertyTupleUUID])
  : Option[RestrictionStructuredDataPropertyTuple]
  = uuid.flatMap {
    lookupRestrictionStructuredDataPropertyTuple
  }
  
  def lookupRestrictionStructuredDataPropertyTuple //10
  (uuid: taggedTypes.RestrictionStructuredDataPropertyTupleUUID)
  : Option[RestrictionStructuredDataPropertyTuple]
  = restrictionStructuredDataPropertyTupleByUUID.get(uuid)
    
  def lookupScalarDataPropertyRestrictions //7
  (key: Option[RestrictionStructuredDataPropertyContext])
  : Set[RestrictionScalarDataPropertyValue]
  = key
  .fold[Set[RestrictionScalarDataPropertyValue]] { 
  	Set.empty[RestrictionScalarDataPropertyValue] 
  }{ lookupScalarDataPropertyRestrictions }
  
  def lookupScalarDataPropertyRestrictions //8
  (key: RestrictionStructuredDataPropertyContext)
  : Set[RestrictionScalarDataPropertyValue]
  = scalarDataPropertyRestrictions.getOrElse(key, Set.empty[RestrictionScalarDataPropertyValue])
  
  def lookupRestrictionScalarDataPropertyValue //9
  (uuid: Option[taggedTypes.RestrictionScalarDataPropertyValueUUID])
  : Option[RestrictionScalarDataPropertyValue]
  = uuid.flatMap {
    lookupRestrictionScalarDataPropertyValue
  }
  
  def lookupRestrictionScalarDataPropertyValue //10
  (uuid: taggedTypes.RestrictionScalarDataPropertyValueUUID)
  : Option[RestrictionScalarDataPropertyValue]
  = restrictionScalarDataPropertyValueByUUID.get(uuid)
    
  def lookupBundleAxioms //7
  (key: Option[Bundle])
  : Set[TerminologyBundleAxiom]
  = key
  .fold[Set[TerminologyBundleAxiom]] { 
  	Set.empty[TerminologyBundleAxiom] 
  }{ lookupBundleAxioms }
  
  def lookupBundleAxioms //8
  (key: Bundle)
  : Set[TerminologyBundleAxiom]
  = bundleAxioms.getOrElse(key, Set.empty[TerminologyBundleAxiom])
  
  def lookupTerminologyBundleAxiom //9
  (uuid: Option[taggedTypes.TerminologyBundleAxiomUUID])
  : Option[TerminologyBundleAxiom]
  = uuid.flatMap {
    lookupTerminologyBundleAxiom
  }
  
  def lookupTerminologyBundleAxiom //10
  (uuid: taggedTypes.TerminologyBundleAxiomUUID)
  : Option[TerminologyBundleAxiom]
  = terminologyBundleAxiomByUUID.get(uuid)
    
  def lookupBundleStatements //7
  (key: Option[Bundle])
  : Set[TerminologyBundleStatement]
  = key
  .fold[Set[TerminologyBundleStatement]] { 
  	Set.empty[TerminologyBundleStatement] 
  }{ lookupBundleStatements }
  
  def lookupBundleStatements //8
  (key: Bundle)
  : Set[TerminologyBundleStatement]
  = bundleStatements.getOrElse(key, Set.empty[TerminologyBundleStatement])
  
  def lookupTerminologyBundleStatement //9
  (uuid: Option[taggedTypes.TerminologyBundleStatementUUID])
  : Option[TerminologyBundleStatement]
  = uuid.flatMap {
    lookupTerminologyBundleStatement
  }
  
  def lookupTerminologyBundleStatement //10
  (uuid: taggedTypes.TerminologyBundleStatementUUID)
  : Option[TerminologyBundleStatement]
  = terminologyBundleStatementByUUID.get(uuid)
    
  def lookupDisjunctions //7
  (key: Option[ConceptTreeDisjunction])
  : Set[DisjointUnionOfConceptsAxiom]
  = key
  .fold[Set[DisjointUnionOfConceptsAxiom]] { 
  	Set.empty[DisjointUnionOfConceptsAxiom] 
  }{ lookupDisjunctions }
  
  def lookupDisjunctions //8
  (key: ConceptTreeDisjunction)
  : Set[DisjointUnionOfConceptsAxiom]
  = disjunctions.getOrElse(key, Set.empty[DisjointUnionOfConceptsAxiom])
  
  def lookupDisjointUnionOfConceptsAxiom //9
  (uuid: Option[taggedTypes.DisjointUnionOfConceptsAxiomUUID])
  : Option[DisjointUnionOfConceptsAxiom]
  = uuid.flatMap {
    lookupDisjointUnionOfConceptsAxiom
  }
  
  def lookupDisjointUnionOfConceptsAxiom //10
  (uuid: taggedTypes.DisjointUnionOfConceptsAxiomUUID)
  : Option[DisjointUnionOfConceptsAxiom]
  = disjointUnionOfConceptsAxiomByUUID.get(uuid)
    
  def lookupDescriptionBoxRefinements //7
  (key: Option[DescriptionBox])
  : Set[DescriptionBoxRefinement]
  = key
  .fold[Set[DescriptionBoxRefinement]] { 
  	Set.empty[DescriptionBoxRefinement] 
  }{ lookupDescriptionBoxRefinements }
  
  def lookupDescriptionBoxRefinements //8
  (key: DescriptionBox)
  : Set[DescriptionBoxRefinement]
  = descriptionBoxRefinements.getOrElse(key, Set.empty[DescriptionBoxRefinement])
  
  def lookupDescriptionBoxRefinement //9
  (uuid: Option[taggedTypes.DescriptionBoxRefinementUUID])
  : Option[DescriptionBoxRefinement]
  = uuid.flatMap {
    lookupDescriptionBoxRefinement
  }
  
  def lookupDescriptionBoxRefinement //10
  (uuid: taggedTypes.DescriptionBoxRefinementUUID)
  : Option[DescriptionBoxRefinement]
  = descriptionBoxRefinementByUUID.get(uuid)
    
  def lookupClosedWorldDefinitions //7
  (key: Option[DescriptionBox])
  : Set[DescriptionBoxExtendsClosedWorldDefinitions]
  = key
  .fold[Set[DescriptionBoxExtendsClosedWorldDefinitions]] { 
  	Set.empty[DescriptionBoxExtendsClosedWorldDefinitions] 
  }{ lookupClosedWorldDefinitions }
  
  def lookupClosedWorldDefinitions //8
  (key: DescriptionBox)
  : Set[DescriptionBoxExtendsClosedWorldDefinitions]
  = closedWorldDefinitions.getOrElse(key, Set.empty[DescriptionBoxExtendsClosedWorldDefinitions])
  
  def lookupDescriptionBoxExtendsClosedWorldDefinitions //9
  (uuid: Option[taggedTypes.DescriptionBoxExtendsClosedWorldDefinitionsUUID])
  : Option[DescriptionBoxExtendsClosedWorldDefinitions]
  = uuid.flatMap {
    lookupDescriptionBoxExtendsClosedWorldDefinitions
  }
  
  def lookupDescriptionBoxExtendsClosedWorldDefinitions //10
  (uuid: taggedTypes.DescriptionBoxExtendsClosedWorldDefinitionsUUID)
  : Option[DescriptionBoxExtendsClosedWorldDefinitions]
  = descriptionBoxExtendsClosedWorldDefinitionsByUUID.get(uuid)
    
  def lookupConceptInstances //7
  (key: Option[DescriptionBox])
  : Set[ConceptInstance]
  = key
  .fold[Set[ConceptInstance]] { 
  	Set.empty[ConceptInstance] 
  }{ lookupConceptInstances }
  
  def lookupConceptInstances //8
  (key: DescriptionBox)
  : Set[ConceptInstance]
  = conceptInstances.getOrElse(key, Set.empty[ConceptInstance])
  
  def lookupConceptInstance //9
  (uuid: Option[taggedTypes.ConceptInstanceUUID])
  : Option[ConceptInstance]
  = uuid.flatMap {
    lookupConceptInstance
  }
  
  def lookupConceptInstance //10
  (uuid: taggedTypes.ConceptInstanceUUID)
  : Option[ConceptInstance]
  = conceptInstanceByUUID.get(uuid)
    
  def lookupReifiedRelationshipInstances //7
  (key: Option[DescriptionBox])
  : Set[ReifiedRelationshipInstance]
  = key
  .fold[Set[ReifiedRelationshipInstance]] { 
  	Set.empty[ReifiedRelationshipInstance] 
  }{ lookupReifiedRelationshipInstances }
  
  def lookupReifiedRelationshipInstances //8
  (key: DescriptionBox)
  : Set[ReifiedRelationshipInstance]
  = reifiedRelationshipInstances.getOrElse(key, Set.empty[ReifiedRelationshipInstance])
  
  def lookupReifiedRelationshipInstance //9
  (uuid: Option[taggedTypes.ReifiedRelationshipInstanceUUID])
  : Option[ReifiedRelationshipInstance]
  = uuid.flatMap {
    lookupReifiedRelationshipInstance
  }
  
  def lookupReifiedRelationshipInstance //10
  (uuid: taggedTypes.ReifiedRelationshipInstanceUUID)
  : Option[ReifiedRelationshipInstance]
  = reifiedRelationshipInstanceByUUID.get(uuid)
    
  def lookupReifiedRelationshipInstanceDomains //7
  (key: Option[DescriptionBox])
  : Set[ReifiedRelationshipInstanceDomain]
  = key
  .fold[Set[ReifiedRelationshipInstanceDomain]] { 
  	Set.empty[ReifiedRelationshipInstanceDomain] 
  }{ lookupReifiedRelationshipInstanceDomains }
  
  def lookupReifiedRelationshipInstanceDomains //8
  (key: DescriptionBox)
  : Set[ReifiedRelationshipInstanceDomain]
  = reifiedRelationshipInstanceDomains.getOrElse(key, Set.empty[ReifiedRelationshipInstanceDomain])
  
  def lookupReifiedRelationshipInstanceDomain //9
  (uuid: Option[taggedTypes.ReifiedRelationshipInstanceDomainUUID])
  : Option[ReifiedRelationshipInstanceDomain]
  = uuid.flatMap {
    lookupReifiedRelationshipInstanceDomain
  }
  
  def lookupReifiedRelationshipInstanceDomain //10
  (uuid: taggedTypes.ReifiedRelationshipInstanceDomainUUID)
  : Option[ReifiedRelationshipInstanceDomain]
  = reifiedRelationshipInstanceDomainByUUID.get(uuid)
    
  def lookupReifiedRelationshipInstanceRanges //7
  (key: Option[DescriptionBox])
  : Set[ReifiedRelationshipInstanceRange]
  = key
  .fold[Set[ReifiedRelationshipInstanceRange]] { 
  	Set.empty[ReifiedRelationshipInstanceRange] 
  }{ lookupReifiedRelationshipInstanceRanges }
  
  def lookupReifiedRelationshipInstanceRanges //8
  (key: DescriptionBox)
  : Set[ReifiedRelationshipInstanceRange]
  = reifiedRelationshipInstanceRanges.getOrElse(key, Set.empty[ReifiedRelationshipInstanceRange])
  
  def lookupReifiedRelationshipInstanceRange //9
  (uuid: Option[taggedTypes.ReifiedRelationshipInstanceRangeUUID])
  : Option[ReifiedRelationshipInstanceRange]
  = uuid.flatMap {
    lookupReifiedRelationshipInstanceRange
  }
  
  def lookupReifiedRelationshipInstanceRange //10
  (uuid: taggedTypes.ReifiedRelationshipInstanceRangeUUID)
  : Option[ReifiedRelationshipInstanceRange]
  = reifiedRelationshipInstanceRangeByUUID.get(uuid)
    
  def lookupUnreifiedRelationshipInstanceTuples //7
  (key: Option[DescriptionBox])
  : Set[UnreifiedRelationshipInstanceTuple]
  = key
  .fold[Set[UnreifiedRelationshipInstanceTuple]] { 
  	Set.empty[UnreifiedRelationshipInstanceTuple] 
  }{ lookupUnreifiedRelationshipInstanceTuples }
  
  def lookupUnreifiedRelationshipInstanceTuples //8
  (key: DescriptionBox)
  : Set[UnreifiedRelationshipInstanceTuple]
  = unreifiedRelationshipInstanceTuples.getOrElse(key, Set.empty[UnreifiedRelationshipInstanceTuple])
  
  def lookupUnreifiedRelationshipInstanceTuple //9
  (uuid: Option[taggedTypes.UnreifiedRelationshipInstanceTupleUUID])
  : Option[UnreifiedRelationshipInstanceTuple]
  = uuid.flatMap {
    lookupUnreifiedRelationshipInstanceTuple
  }
  
  def lookupUnreifiedRelationshipInstanceTuple //10
  (uuid: taggedTypes.UnreifiedRelationshipInstanceTupleUUID)
  : Option[UnreifiedRelationshipInstanceTuple]
  = unreifiedRelationshipInstanceTupleByUUID.get(uuid)
    
  def lookupSingletonScalarDataPropertyValues //7
  (key: Option[DescriptionBox])
  : Set[SingletonInstanceScalarDataPropertyValue]
  = key
  .fold[Set[SingletonInstanceScalarDataPropertyValue]] { 
  	Set.empty[SingletonInstanceScalarDataPropertyValue] 
  }{ lookupSingletonScalarDataPropertyValues }
  
  def lookupSingletonScalarDataPropertyValues //8
  (key: DescriptionBox)
  : Set[SingletonInstanceScalarDataPropertyValue]
  = singletonScalarDataPropertyValues.getOrElse(key, Set.empty[SingletonInstanceScalarDataPropertyValue])
  
  def lookupSingletonInstanceScalarDataPropertyValue //9
  (uuid: Option[taggedTypes.SingletonInstanceScalarDataPropertyValueUUID])
  : Option[SingletonInstanceScalarDataPropertyValue]
  = uuid.flatMap {
    lookupSingletonInstanceScalarDataPropertyValue
  }
  
  def lookupSingletonInstanceScalarDataPropertyValue //10
  (uuid: taggedTypes.SingletonInstanceScalarDataPropertyValueUUID)
  : Option[SingletonInstanceScalarDataPropertyValue]
  = singletonInstanceScalarDataPropertyValueByUUID.get(uuid)
    
  def lookupSingletonStructuredDataPropertyValues //7
  (key: Option[DescriptionBox])
  : Set[SingletonInstanceStructuredDataPropertyValue]
  = key
  .fold[Set[SingletonInstanceStructuredDataPropertyValue]] { 
  	Set.empty[SingletonInstanceStructuredDataPropertyValue] 
  }{ lookupSingletonStructuredDataPropertyValues }
  
  def lookupSingletonStructuredDataPropertyValues //8
  (key: DescriptionBox)
  : Set[SingletonInstanceStructuredDataPropertyValue]
  = singletonStructuredDataPropertyValues.getOrElse(key, Set.empty[SingletonInstanceStructuredDataPropertyValue])
  
  def lookupSingletonInstanceStructuredDataPropertyValue //9
  (uuid: Option[taggedTypes.SingletonInstanceStructuredDataPropertyValueUUID])
  : Option[SingletonInstanceStructuredDataPropertyValue]
  = uuid.flatMap {
    lookupSingletonInstanceStructuredDataPropertyValue
  }
  
  def lookupSingletonInstanceStructuredDataPropertyValue //10
  (uuid: taggedTypes.SingletonInstanceStructuredDataPropertyValueUUID)
  : Option[SingletonInstanceStructuredDataPropertyValue]
  = singletonInstanceStructuredDataPropertyValueByUUID.get(uuid)
    
  def lookupStructuredPropertyTuples //7
  (key: Option[SingletonInstanceStructuredDataPropertyContext])
  : Set[StructuredDataPropertyTuple]
  = key
  .fold[Set[StructuredDataPropertyTuple]] { 
  	Set.empty[StructuredDataPropertyTuple] 
  }{ lookupStructuredPropertyTuples }
  
  def lookupStructuredPropertyTuples //8
  (key: SingletonInstanceStructuredDataPropertyContext)
  : Set[StructuredDataPropertyTuple]
  = structuredPropertyTuples.getOrElse(key, Set.empty[StructuredDataPropertyTuple])
  
  def lookupStructuredDataPropertyTuple //9
  (uuid: Option[taggedTypes.StructuredDataPropertyTupleUUID])
  : Option[StructuredDataPropertyTuple]
  = uuid.flatMap {
    lookupStructuredDataPropertyTuple
  }
  
  def lookupStructuredDataPropertyTuple //10
  (uuid: taggedTypes.StructuredDataPropertyTupleUUID)
  : Option[StructuredDataPropertyTuple]
  = structuredDataPropertyTupleByUUID.get(uuid)
    
  def lookupScalarDataPropertyValues //7
  (key: Option[SingletonInstanceStructuredDataPropertyContext])
  : Set[ScalarDataPropertyValue]
  = key
  .fold[Set[ScalarDataPropertyValue]] { 
  	Set.empty[ScalarDataPropertyValue] 
  }{ lookupScalarDataPropertyValues }
  
  def lookupScalarDataPropertyValues //8
  (key: SingletonInstanceStructuredDataPropertyContext)
  : Set[ScalarDataPropertyValue]
  = scalarDataPropertyValues.getOrElse(key, Set.empty[ScalarDataPropertyValue])
  
  def lookupScalarDataPropertyValue //9
  (uuid: Option[taggedTypes.ScalarDataPropertyValueUUID])
  : Option[ScalarDataPropertyValue]
  = uuid.flatMap {
    lookupScalarDataPropertyValue
  }
  
  def lookupScalarDataPropertyValue //10
  (uuid: taggedTypes.ScalarDataPropertyValueUUID)
  : Option[ScalarDataPropertyValue]
  = scalarDataPropertyValueByUUID.get(uuid)

  
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
    lookupReifiedRelationshipInstanceDomain(uuid.asInstanceOf[taggedTypes.ReifiedRelationshipInstanceDomainUUID]) orElse
    lookupReifiedRelationshipInstanceRange(uuid.asInstanceOf[taggedTypes.ReifiedRelationshipInstanceRangeUUID]) orElse
    lookupUnreifiedRelationshipInstanceTuple(uuid.asInstanceOf[taggedTypes.UnreifiedRelationshipInstanceTupleUUID]) orElse
    lookupSingletonInstanceScalarDataPropertyValue(uuid.asInstanceOf[taggedTypes.SingletonInstanceScalarDataPropertyValueUUID]) orElse
    lookupSingletonInstanceStructuredDataPropertyValue(uuid.asInstanceOf[taggedTypes.SingletonInstanceStructuredDataPropertyValueUUID]) orElse
    lookupStructuredDataPropertyTuple(uuid.asInstanceOf[taggedTypes.StructuredDataPropertyTupleUUID]) orElse
    lookupScalarDataPropertyValue(uuid.asInstanceOf[taggedTypes.ScalarDataPropertyValueUUID])

}
