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
import scala.{Option,None}
 
// Container types:
// - bundles.Bundle
// - bundles.ConceptTreeDisjunction
// - descriptions.DescriptionBox
// - common.Module
// - descriptions.SingletonInstance
// - descriptions.StructuredDataPropertyValue
// - terminologies.TerminologyBox
// Contained types:
// - common.Annotation
// - descriptions.ConceptInstance
// - descriptions.DataStructureTuple
// - descriptions.DescriptionBoxExtendsClosedWorldDefinitions
// - descriptions.DescriptionBoxRefinement
// - bundles.DisjointUnionOfConceptsAxiom
// - descriptions.ReifiedRelationshipInstance
// - descriptions.ReifiedRelationshipInstanceDomain
// - descriptions.ReifiedRelationshipInstanceRange
// - descriptions.ScalarDataPropertyValue
// - descriptions.StructuredDataPropertyValue
// - terminologies.TerminologyBoxAxiom
// - terminologies.TerminologyBoxStatement
// - bundles.TerminologyBundleAxiom
// - bundles.TerminologyBundleStatement
// - descriptions.UnreifiedRelationshipInstanceTuple
/*
 * An OML Extent is an in-memory store of all OML Element(s)
 * loaded from external OML documents.
 */
case class Extent
( annotationProperties: Map[java.util.UUID, AnnotationProperty] = HashMap.empty[java.util.UUID, AnnotationProperty],
  terminologyGraphs: Map[java.util.UUID, TerminologyGraph] = HashMap.empty[java.util.UUID, TerminologyGraph],
  bundles: Map[java.util.UUID, Bundle] = HashMap.empty[java.util.UUID, Bundle],
  descriptionBoxes: Map[java.util.UUID, DescriptionBox] = HashMap.empty[java.util.UUID, DescriptionBox],

  annotations: Map[Module, Set[Annotation]] = HashMap.empty[Module, Set[Annotation]],
  boxAxioms: Map[TerminologyBox, Set[TerminologyBoxAxiom]] = HashMap.empty[TerminologyBox, Set[TerminologyBoxAxiom]],
  boxStatements: Map[TerminologyBox, Set[TerminologyBoxStatement]] = HashMap.empty[TerminologyBox, Set[TerminologyBoxStatement]],
  bundleStatements: Map[Bundle, Set[TerminologyBundleStatement]] = HashMap.empty[Bundle, Set[TerminologyBundleStatement]],
  bundleAxioms: Map[Bundle, Set[TerminologyBundleAxiom]] = HashMap.empty[Bundle, Set[TerminologyBundleAxiom]],
  disjunctions: Map[ConceptTreeDisjunction, Set[DisjointUnionOfConceptsAxiom]] = HashMap.empty[ConceptTreeDisjunction, Set[DisjointUnionOfConceptsAxiom]],
  closedWorldDefinitions: Map[DescriptionBox, Set[DescriptionBoxExtendsClosedWorldDefinitions]] = HashMap.empty[DescriptionBox, Set[DescriptionBoxExtendsClosedWorldDefinitions]],
  descriptionBoxRefinements: Map[DescriptionBox, Set[DescriptionBoxRefinement]] = HashMap.empty[DescriptionBox, Set[DescriptionBoxRefinement]],
  conceptInstances: Map[DescriptionBox, Set[ConceptInstance]] = HashMap.empty[DescriptionBox, Set[ConceptInstance]],
  reifiedRelationshipInstances: Map[DescriptionBox, Set[ReifiedRelationshipInstance]] = HashMap.empty[DescriptionBox, Set[ReifiedRelationshipInstance]],
  reifiedRelationshipInstanceDomains: Map[DescriptionBox, Set[ReifiedRelationshipInstanceDomain]] = HashMap.empty[DescriptionBox, Set[ReifiedRelationshipInstanceDomain]],
  reifiedRelationshipInstanceRanges: Map[DescriptionBox, Set[ReifiedRelationshipInstanceRange]] = HashMap.empty[DescriptionBox, Set[ReifiedRelationshipInstanceRange]],
  unreifiedRelationshipInstanceTuples: Map[DescriptionBox, Set[UnreifiedRelationshipInstanceTuple]] = HashMap.empty[DescriptionBox, Set[UnreifiedRelationshipInstanceTuple]],
  scalarDataPropertyValues: Map[SingletonInstance, Set[ScalarDataPropertyValue]] = HashMap.empty[SingletonInstance, Set[ScalarDataPropertyValue]],
  structuredDataPropertyValues: Map[SingletonInstance, Set[StructuredDataPropertyValue]] = HashMap.empty[SingletonInstance, Set[StructuredDataPropertyValue]],
  structuredPropertyTuple: Map[StructuredDataPropertyValue, Set[DataStructureTuple]] = HashMap.empty[StructuredDataPropertyValue, Set[DataStructureTuple]],

  moduleOfAnnotation: Map[Annotation, Module] = HashMap.empty[Annotation, Module],
  terminologyBoxOfTerminologyBoxAxiom: Map[TerminologyBoxAxiom, TerminologyBox] = HashMap.empty[TerminologyBoxAxiom, TerminologyBox],
  terminologyBoxOfTerminologyBoxStatement: Map[TerminologyBoxStatement, TerminologyBox] = HashMap.empty[TerminologyBoxStatement, TerminologyBox],
  bundleOfTerminologyBundleStatement: Map[TerminologyBundleStatement, Bundle] = HashMap.empty[TerminologyBundleStatement, Bundle],
  bundleOfTerminologyBundleAxiom: Map[TerminologyBundleAxiom, Bundle] = HashMap.empty[TerminologyBundleAxiom, Bundle],
  conceptTreeDisjunctionOfDisjointUnionOfConceptsAxiom: Map[DisjointUnionOfConceptsAxiom, ConceptTreeDisjunction] = HashMap.empty[DisjointUnionOfConceptsAxiom, ConceptTreeDisjunction],
  descriptionBoxOfDescriptionBoxExtendsClosedWorldDefinitions: Map[DescriptionBoxExtendsClosedWorldDefinitions, DescriptionBox] = HashMap.empty[DescriptionBoxExtendsClosedWorldDefinitions, DescriptionBox],
  descriptionBoxOfDescriptionBoxRefinement: Map[DescriptionBoxRefinement, DescriptionBox] = HashMap.empty[DescriptionBoxRefinement, DescriptionBox],
  descriptionBoxOfConceptInstance: Map[ConceptInstance, DescriptionBox] = HashMap.empty[ConceptInstance, DescriptionBox],
  descriptionBoxOfReifiedRelationshipInstance: Map[ReifiedRelationshipInstance, DescriptionBox] = HashMap.empty[ReifiedRelationshipInstance, DescriptionBox],
  descriptionBoxOfReifiedRelationshipInstanceDomain: Map[ReifiedRelationshipInstanceDomain, DescriptionBox] = HashMap.empty[ReifiedRelationshipInstanceDomain, DescriptionBox],
  descriptionBoxOfReifiedRelationshipInstanceRange: Map[ReifiedRelationshipInstanceRange, DescriptionBox] = HashMap.empty[ReifiedRelationshipInstanceRange, DescriptionBox],
  descriptionBoxOfUnreifiedRelationshipInstanceTuple: Map[UnreifiedRelationshipInstanceTuple, DescriptionBox] = HashMap.empty[UnreifiedRelationshipInstanceTuple, DescriptionBox],
  singletonInstanceOfScalarDataPropertyValue: Map[ScalarDataPropertyValue, SingletonInstance] = HashMap.empty[ScalarDataPropertyValue, SingletonInstance],
  singletonInstanceOfStructuredDataPropertyValue: Map[StructuredDataPropertyValue, SingletonInstance] = HashMap.empty[StructuredDataPropertyValue, SingletonInstance],
  structuredDataPropertyValueOfDataStructureTuple: Map[DataStructureTuple, StructuredDataPropertyValue] = HashMap.empty[DataStructureTuple, StructuredDataPropertyValue],

  terminologyBoxAxiomByUUID: Map[java.util.UUID, TerminologyBoxAxiom] = HashMap.empty[java.util.UUID, TerminologyBoxAxiom],
  terminologyBoxStatementByUUID: Map[java.util.UUID, TerminologyBoxStatement] = HashMap.empty[java.util.UUID, TerminologyBoxStatement],
  terminologyBundleStatementByUUID: Map[java.util.UUID, TerminologyBundleStatement] = HashMap.empty[java.util.UUID, TerminologyBundleStatement],
  terminologyBundleAxiomByUUID: Map[java.util.UUID, TerminologyBundleAxiom] = HashMap.empty[java.util.UUID, TerminologyBundleAxiom],
  disjointUnionOfConceptsAxiomByUUID: Map[java.util.UUID, DisjointUnionOfConceptsAxiom] = HashMap.empty[java.util.UUID, DisjointUnionOfConceptsAxiom],
  descriptionBoxExtendsClosedWorldDefinitionsByUUID: Map[java.util.UUID, DescriptionBoxExtendsClosedWorldDefinitions] = HashMap.empty[java.util.UUID, DescriptionBoxExtendsClosedWorldDefinitions],
  descriptionBoxRefinementByUUID: Map[java.util.UUID, DescriptionBoxRefinement] = HashMap.empty[java.util.UUID, DescriptionBoxRefinement],
  conceptInstanceByUUID: Map[java.util.UUID, ConceptInstance] = HashMap.empty[java.util.UUID, ConceptInstance],
  reifiedRelationshipInstanceByUUID: Map[java.util.UUID, ReifiedRelationshipInstance] = HashMap.empty[java.util.UUID, ReifiedRelationshipInstance],
  reifiedRelationshipInstanceDomainByUUID: Map[java.util.UUID, ReifiedRelationshipInstanceDomain] = HashMap.empty[java.util.UUID, ReifiedRelationshipInstanceDomain],
  reifiedRelationshipInstanceRangeByUUID: Map[java.util.UUID, ReifiedRelationshipInstanceRange] = HashMap.empty[java.util.UUID, ReifiedRelationshipInstanceRange],
  unreifiedRelationshipInstanceTupleByUUID: Map[java.util.UUID, UnreifiedRelationshipInstanceTuple] = HashMap.empty[java.util.UUID, UnreifiedRelationshipInstanceTuple],
  scalarDataPropertyValueByUUID: Map[java.util.UUID, ScalarDataPropertyValue] = HashMap.empty[java.util.UUID, ScalarDataPropertyValue],
  structuredDataPropertyValueByUUID: Map[java.util.UUID, StructuredDataPropertyValue] = HashMap.empty[java.util.UUID, StructuredDataPropertyValue],
  dataStructureTupleByUUID: Map[java.util.UUID, DataStructureTuple] = HashMap.empty[java.util.UUID, DataStructureTuple]
) {
  def withAnnotation(module: Module, annotation: Annotation)
  : Map[Module, Set[Annotation]] 
  = annotations.updated(module, annotations.getOrElse(module, Set.empty[Annotation]) + annotation)
  
  def withTerminologyBoxAxiom(terminologyBox: TerminologyBox, terminologyBoxAxiom: TerminologyBoxAxiom)
  : Map[TerminologyBox, Set[TerminologyBoxAxiom]] 
  = boxAxioms.updated(terminologyBox, boxAxioms.getOrElse(terminologyBox, Set.empty[TerminologyBoxAxiom]) + terminologyBoxAxiom)
  
  def withTerminologyBoxStatement(terminologyBox: TerminologyBox, terminologyBoxStatement: TerminologyBoxStatement)
  : Map[TerminologyBox, Set[TerminologyBoxStatement]] 
  = boxStatements.updated(terminologyBox, boxStatements.getOrElse(terminologyBox, Set.empty[TerminologyBoxStatement]) + terminologyBoxStatement)
  
  def withTerminologyBundleStatement(bundle: Bundle, terminologyBundleStatement: TerminologyBundleStatement)
  : Map[Bundle, Set[TerminologyBundleStatement]] 
  = bundleStatements.updated(bundle, bundleStatements.getOrElse(bundle, Set.empty[TerminologyBundleStatement]) + terminologyBundleStatement)
  
  def withTerminologyBundleAxiom(bundle: Bundle, terminologyBundleAxiom: TerminologyBundleAxiom)
  : Map[Bundle, Set[TerminologyBundleAxiom]] 
  = bundleAxioms.updated(bundle, bundleAxioms.getOrElse(bundle, Set.empty[TerminologyBundleAxiom]) + terminologyBundleAxiom)
  
  def withDisjointUnionOfConceptsAxiom(conceptTreeDisjunction: ConceptTreeDisjunction, disjointUnionOfConceptsAxiom: DisjointUnionOfConceptsAxiom)
  : Map[ConceptTreeDisjunction, Set[DisjointUnionOfConceptsAxiom]] 
  = disjunctions.updated(conceptTreeDisjunction, disjunctions.getOrElse(conceptTreeDisjunction, Set.empty[DisjointUnionOfConceptsAxiom]) + disjointUnionOfConceptsAxiom)
  
  def withDescriptionBoxExtendsClosedWorldDefinitions(descriptionBox: DescriptionBox, descriptionBoxExtendsClosedWorldDefinitions: DescriptionBoxExtendsClosedWorldDefinitions)
  : Map[DescriptionBox, Set[DescriptionBoxExtendsClosedWorldDefinitions]] 
  = closedWorldDefinitions.updated(descriptionBox, closedWorldDefinitions.getOrElse(descriptionBox, Set.empty[DescriptionBoxExtendsClosedWorldDefinitions]) + descriptionBoxExtendsClosedWorldDefinitions)
  
  def withDescriptionBoxRefinement(descriptionBox: DescriptionBox, descriptionBoxRefinement: DescriptionBoxRefinement)
  : Map[DescriptionBox, Set[DescriptionBoxRefinement]] 
  = descriptionBoxRefinements.updated(descriptionBox, descriptionBoxRefinements.getOrElse(descriptionBox, Set.empty[DescriptionBoxRefinement]) + descriptionBoxRefinement)
  
  def withConceptInstance(descriptionBox: DescriptionBox, conceptInstance: ConceptInstance)
  : Map[DescriptionBox, Set[ConceptInstance]] 
  = conceptInstances.updated(descriptionBox, conceptInstances.getOrElse(descriptionBox, Set.empty[ConceptInstance]) + conceptInstance)
  
  def withReifiedRelationshipInstance(descriptionBox: DescriptionBox, reifiedRelationshipInstance: ReifiedRelationshipInstance)
  : Map[DescriptionBox, Set[ReifiedRelationshipInstance]] 
  = reifiedRelationshipInstances.updated(descriptionBox, reifiedRelationshipInstances.getOrElse(descriptionBox, Set.empty[ReifiedRelationshipInstance]) + reifiedRelationshipInstance)
  
  def withReifiedRelationshipInstanceDomain(descriptionBox: DescriptionBox, reifiedRelationshipInstanceDomain: ReifiedRelationshipInstanceDomain)
  : Map[DescriptionBox, Set[ReifiedRelationshipInstanceDomain]] 
  = reifiedRelationshipInstanceDomains.updated(descriptionBox, reifiedRelationshipInstanceDomains.getOrElse(descriptionBox, Set.empty[ReifiedRelationshipInstanceDomain]) + reifiedRelationshipInstanceDomain)
  
  def withReifiedRelationshipInstanceRange(descriptionBox: DescriptionBox, reifiedRelationshipInstanceRange: ReifiedRelationshipInstanceRange)
  : Map[DescriptionBox, Set[ReifiedRelationshipInstanceRange]] 
  = reifiedRelationshipInstanceRanges.updated(descriptionBox, reifiedRelationshipInstanceRanges.getOrElse(descriptionBox, Set.empty[ReifiedRelationshipInstanceRange]) + reifiedRelationshipInstanceRange)
  
  def withUnreifiedRelationshipInstanceTuple(descriptionBox: DescriptionBox, unreifiedRelationshipInstanceTuple: UnreifiedRelationshipInstanceTuple)
  : Map[DescriptionBox, Set[UnreifiedRelationshipInstanceTuple]] 
  = unreifiedRelationshipInstanceTuples.updated(descriptionBox, unreifiedRelationshipInstanceTuples.getOrElse(descriptionBox, Set.empty[UnreifiedRelationshipInstanceTuple]) + unreifiedRelationshipInstanceTuple)
  
  def withScalarDataPropertyValue(singletonInstance: SingletonInstance, scalarDataPropertyValue: ScalarDataPropertyValue)
  : Map[SingletonInstance, Set[ScalarDataPropertyValue]] 
  = scalarDataPropertyValues.updated(singletonInstance, scalarDataPropertyValues.getOrElse(singletonInstance, Set.empty[ScalarDataPropertyValue]) + scalarDataPropertyValue)
  
  def withStructuredDataPropertyValue(singletonInstance: SingletonInstance, structuredDataPropertyValue: StructuredDataPropertyValue)
  : Map[SingletonInstance, Set[StructuredDataPropertyValue]] 
  = structuredDataPropertyValues.updated(singletonInstance, structuredDataPropertyValues.getOrElse(singletonInstance, Set.empty[StructuredDataPropertyValue]) + structuredDataPropertyValue)
  
  def withDataStructureTuple(structuredDataPropertyValue: StructuredDataPropertyValue, dataStructureTuple: DataStructureTuple)
  : Map[StructuredDataPropertyValue, Set[DataStructureTuple]] 
  = structuredPropertyTuple.updated(structuredDataPropertyValue, structuredPropertyTuple.getOrElse(structuredDataPropertyValue, Set.empty[DataStructureTuple]) + dataStructureTuple)
  
		
  def lookupModule(uuid: Option[java.util.UUID])
  : Option[Module]
  = uuid.fold[Option[Module]](None) { lookupModule }
  
  def lookupModule(uuid: java.util.UUID)
  : Option[Module]
  = lookupTerminologyBox(uuid) orElse lookupDescriptionBox(uuid)
  
  def lookupTerminologyBox(uuid: Option[java.util.UUID])
  : Option[TerminologyBox]
  = uuid.fold[Option[TerminologyBox]](None) { lookupTerminologyBox }
  
  def lookupTerminologyBox(uuid: java.util.UUID)
  : Option[TerminologyBox]
  = lookupTerminologyGraph(uuid) orElse lookupBundle(uuid)

  def lookupAnnotationProperty(uuid: Option[java.util.UUID])
  : Option[AnnotationProperty]
  = uuid.fold[Option[AnnotationProperty]](None) { lookupAnnotationProperty } 
  
  def lookupAnnotationProperty(uuid: java.util.UUID)
  : Option[AnnotationProperty]
  = annotationProperties.get(uuid)
    
  def lookupTerminologyGraph(uuid: Option[java.util.UUID])
  : Option[TerminologyGraph]
  = uuid.fold[Option[TerminologyGraph]](None) { lookupTerminologyGraph } 
  
  def lookupTerminologyGraph(uuid: java.util.UUID)
  : Option[TerminologyGraph]
  = terminologyGraphs.get(uuid)
    
  def lookupBundle(uuid: Option[java.util.UUID])
  : Option[Bundle]
  = uuid.fold[Option[Bundle]](None) { lookupBundle } 
  
  def lookupBundle(uuid: java.util.UUID)
  : Option[Bundle]
  = bundles.get(uuid)
    
  def lookupDescriptionBox(uuid: Option[java.util.UUID])
  : Option[DescriptionBox]
  = uuid.fold[Option[DescriptionBox]](None) { lookupDescriptionBox } 
  
  def lookupDescriptionBox(uuid: java.util.UUID)
  : Option[DescriptionBox]
  = descriptionBoxes.get(uuid)

  
  def lookupAnnotations(key: Option[Module])
  : Set[Annotation]
  = key.fold[Set[Annotation]](Set.empty[Annotation]) { lookupAnnotations }
  
  def lookupAnnotations(key: Module)
  : Set[Annotation]
  = annotations.getOrElse(key, Set.empty[Annotation])
    
  def lookupBoxAxioms(key: Option[TerminologyBox])
  : Set[TerminologyBoxAxiom]
  = key.fold[Set[TerminologyBoxAxiom]](Set.empty[TerminologyBoxAxiom]) { lookupBoxAxioms }
  
  def lookupBoxAxioms(key: TerminologyBox)
  : Set[TerminologyBoxAxiom]
  = boxAxioms.getOrElse(key, Set.empty[TerminologyBoxAxiom])
  
  def lookupTerminologyBoxAxiom(uuid: Option[java.util.UUID])
  : Option[TerminologyBoxAxiom]
  = uuid.fold[Option[TerminologyBoxAxiom]](None) { lookupTerminologyBoxAxiom } 
  
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
  = uuid.fold[Option[TerminologyBoxStatement]](None) { lookupTerminologyBoxStatement } 
  
  def lookupTerminologyBoxStatement(uuid: java.util.UUID)
  : Option[TerminologyBoxStatement]
  = terminologyBoxStatementByUUID.get(uuid)
    
  def lookupBundleStatements(key: Option[Bundle])
  : Set[TerminologyBundleStatement]
  = key.fold[Set[TerminologyBundleStatement]](Set.empty[TerminologyBundleStatement]) { lookupBundleStatements }
  
  def lookupBundleStatements(key: Bundle)
  : Set[TerminologyBundleStatement]
  = bundleStatements.getOrElse(key, Set.empty[TerminologyBundleStatement])
  
  def lookupTerminologyBundleStatement(uuid: Option[java.util.UUID])
  : Option[TerminologyBundleStatement]
  = uuid.fold[Option[TerminologyBundleStatement]](None) { lookupTerminologyBundleStatement } 
  
  def lookupTerminologyBundleStatement(uuid: java.util.UUID)
  : Option[TerminologyBundleStatement]
  = terminologyBundleStatementByUUID.get(uuid)
    
  def lookupBundleAxioms(key: Option[Bundle])
  : Set[TerminologyBundleAxiom]
  = key.fold[Set[TerminologyBundleAxiom]](Set.empty[TerminologyBundleAxiom]) { lookupBundleAxioms }
  
  def lookupBundleAxioms(key: Bundle)
  : Set[TerminologyBundleAxiom]
  = bundleAxioms.getOrElse(key, Set.empty[TerminologyBundleAxiom])
  
  def lookupTerminologyBundleAxiom(uuid: Option[java.util.UUID])
  : Option[TerminologyBundleAxiom]
  = uuid.fold[Option[TerminologyBundleAxiom]](None) { lookupTerminologyBundleAxiom } 
  
  def lookupTerminologyBundleAxiom(uuid: java.util.UUID)
  : Option[TerminologyBundleAxiom]
  = terminologyBundleAxiomByUUID.get(uuid)
    
  def lookupDisjunctions(key: Option[ConceptTreeDisjunction])
  : Set[DisjointUnionOfConceptsAxiom]
  = key.fold[Set[DisjointUnionOfConceptsAxiom]](Set.empty[DisjointUnionOfConceptsAxiom]) { lookupDisjunctions }
  
  def lookupDisjunctions(key: ConceptTreeDisjunction)
  : Set[DisjointUnionOfConceptsAxiom]
  = disjunctions.getOrElse(key, Set.empty[DisjointUnionOfConceptsAxiom])
  
  def lookupDisjointUnionOfConceptsAxiom(uuid: Option[java.util.UUID])
  : Option[DisjointUnionOfConceptsAxiom]
  = uuid.fold[Option[DisjointUnionOfConceptsAxiom]](None) { lookupDisjointUnionOfConceptsAxiom } 
  
  def lookupDisjointUnionOfConceptsAxiom(uuid: java.util.UUID)
  : Option[DisjointUnionOfConceptsAxiom]
  = disjointUnionOfConceptsAxiomByUUID.get(uuid)
    
  def lookupClosedWorldDefinitions(key: Option[DescriptionBox])
  : Set[DescriptionBoxExtendsClosedWorldDefinitions]
  = key.fold[Set[DescriptionBoxExtendsClosedWorldDefinitions]](Set.empty[DescriptionBoxExtendsClosedWorldDefinitions]) { lookupClosedWorldDefinitions }
  
  def lookupClosedWorldDefinitions(key: DescriptionBox)
  : Set[DescriptionBoxExtendsClosedWorldDefinitions]
  = closedWorldDefinitions.getOrElse(key, Set.empty[DescriptionBoxExtendsClosedWorldDefinitions])
  
  def lookupDescriptionBoxExtendsClosedWorldDefinitions(uuid: Option[java.util.UUID])
  : Option[DescriptionBoxExtendsClosedWorldDefinitions]
  = uuid.fold[Option[DescriptionBoxExtendsClosedWorldDefinitions]](None) { lookupDescriptionBoxExtendsClosedWorldDefinitions } 
  
  def lookupDescriptionBoxExtendsClosedWorldDefinitions(uuid: java.util.UUID)
  : Option[DescriptionBoxExtendsClosedWorldDefinitions]
  = descriptionBoxExtendsClosedWorldDefinitionsByUUID.get(uuid)
    
  def lookupDescriptionBoxRefinements(key: Option[DescriptionBox])
  : Set[DescriptionBoxRefinement]
  = key.fold[Set[DescriptionBoxRefinement]](Set.empty[DescriptionBoxRefinement]) { lookupDescriptionBoxRefinements }
  
  def lookupDescriptionBoxRefinements(key: DescriptionBox)
  : Set[DescriptionBoxRefinement]
  = descriptionBoxRefinements.getOrElse(key, Set.empty[DescriptionBoxRefinement])
  
  def lookupDescriptionBoxRefinement(uuid: Option[java.util.UUID])
  : Option[DescriptionBoxRefinement]
  = uuid.fold[Option[DescriptionBoxRefinement]](None) { lookupDescriptionBoxRefinement } 
  
  def lookupDescriptionBoxRefinement(uuid: java.util.UUID)
  : Option[DescriptionBoxRefinement]
  = descriptionBoxRefinementByUUID.get(uuid)
    
  def lookupConceptInstances(key: Option[DescriptionBox])
  : Set[ConceptInstance]
  = key.fold[Set[ConceptInstance]](Set.empty[ConceptInstance]) { lookupConceptInstances }
  
  def lookupConceptInstances(key: DescriptionBox)
  : Set[ConceptInstance]
  = conceptInstances.getOrElse(key, Set.empty[ConceptInstance])
  
  def lookupConceptInstance(uuid: Option[java.util.UUID])
  : Option[ConceptInstance]
  = uuid.fold[Option[ConceptInstance]](None) { lookupConceptInstance } 
  
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
  = uuid.fold[Option[ReifiedRelationshipInstance]](None) { lookupReifiedRelationshipInstance } 
  
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
  = uuid.fold[Option[ReifiedRelationshipInstanceDomain]](None) { lookupReifiedRelationshipInstanceDomain } 
  
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
  = uuid.fold[Option[ReifiedRelationshipInstanceRange]](None) { lookupReifiedRelationshipInstanceRange } 
  
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
  = uuid.fold[Option[UnreifiedRelationshipInstanceTuple]](None) { lookupUnreifiedRelationshipInstanceTuple } 
  
  def lookupUnreifiedRelationshipInstanceTuple(uuid: java.util.UUID)
  : Option[UnreifiedRelationshipInstanceTuple]
  = unreifiedRelationshipInstanceTupleByUUID.get(uuid)
    
  def lookupScalarDataPropertyValues(key: Option[SingletonInstance])
  : Set[ScalarDataPropertyValue]
  = key.fold[Set[ScalarDataPropertyValue]](Set.empty[ScalarDataPropertyValue]) { lookupScalarDataPropertyValues }
  
  def lookupScalarDataPropertyValues(key: SingletonInstance)
  : Set[ScalarDataPropertyValue]
  = scalarDataPropertyValues.getOrElse(key, Set.empty[ScalarDataPropertyValue])
  
  def lookupScalarDataPropertyValue(uuid: Option[java.util.UUID])
  : Option[ScalarDataPropertyValue]
  = uuid.fold[Option[ScalarDataPropertyValue]](None) { lookupScalarDataPropertyValue } 
  
  def lookupScalarDataPropertyValue(uuid: java.util.UUID)
  : Option[ScalarDataPropertyValue]
  = scalarDataPropertyValueByUUID.get(uuid)
    
  def lookupStructuredDataPropertyValues(key: Option[SingletonInstance])
  : Set[StructuredDataPropertyValue]
  = key.fold[Set[StructuredDataPropertyValue]](Set.empty[StructuredDataPropertyValue]) { lookupStructuredDataPropertyValues }
  
  def lookupStructuredDataPropertyValues(key: SingletonInstance)
  : Set[StructuredDataPropertyValue]
  = structuredDataPropertyValues.getOrElse(key, Set.empty[StructuredDataPropertyValue])
  
  def lookupStructuredDataPropertyValue(uuid: Option[java.util.UUID])
  : Option[StructuredDataPropertyValue]
  = uuid.fold[Option[StructuredDataPropertyValue]](None) { lookupStructuredDataPropertyValue } 
  
  def lookupStructuredDataPropertyValue(uuid: java.util.UUID)
  : Option[StructuredDataPropertyValue]
  = structuredDataPropertyValueByUUID.get(uuid)
    
  def lookupStructuredPropertyTuple(key: Option[StructuredDataPropertyValue])
  : Set[DataStructureTuple]
  = key.fold[Set[DataStructureTuple]](Set.empty[DataStructureTuple]) { lookupStructuredPropertyTuple }
  
  def lookupStructuredPropertyTuple(key: StructuredDataPropertyValue)
  : Set[DataStructureTuple]
  = structuredPropertyTuple.getOrElse(key, Set.empty[DataStructureTuple])
  
  def lookupDataStructureTuple(uuid: Option[java.util.UUID])
  : Option[DataStructureTuple]
  = uuid.fold[Option[DataStructureTuple]](None) { lookupDataStructureTuple } 
  
  def lookupDataStructureTuple(uuid: java.util.UUID)
  : Option[DataStructureTuple]
  = dataStructureTupleByUUID.get(uuid)

  
  def lookupElement(uuid: java.util.UUID)
  : Option[Element]
  = lookupModule(uuid) orElse
    lookupTerminologyBoxAxiom(uuid) orElse
    lookupTerminologyBoxStatement(uuid) orElse
    lookupTerminologyBundleStatement(uuid) orElse
    lookupTerminologyBundleAxiom(uuid) orElse
    lookupDisjointUnionOfConceptsAxiom(uuid) orElse
    lookupDescriptionBoxExtendsClosedWorldDefinitions(uuid) orElse
    lookupDescriptionBoxRefinement(uuid) orElse
    lookupConceptInstance(uuid) orElse
    lookupReifiedRelationshipInstance(uuid) orElse
    lookupReifiedRelationshipInstanceDomain(uuid) orElse
    lookupReifiedRelationshipInstanceRange(uuid) orElse
    lookupUnreifiedRelationshipInstanceTuple(uuid) orElse
    lookupScalarDataPropertyValue(uuid) orElse
    lookupStructuredDataPropertyValue(uuid) orElse
    lookupDataStructureTuple(uuid)

}
