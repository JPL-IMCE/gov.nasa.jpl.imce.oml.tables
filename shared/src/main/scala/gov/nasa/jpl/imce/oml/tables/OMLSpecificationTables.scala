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


package gov.nasa.jpl.imce.oml.tables

import java.io.{File,InputStream}
import org.apache.commons.compress.archivers.zip.{ZipArchiveEntry, ZipFile}

import scala.collection.immutable.{Seq,Set}
import scala.collection.JavaConversions._
import scala.util.control.Exception._
import scala.util.{Failure,Success,Try}
import scala.{Boolean,StringContext,Unit}
import scala.Predef.String

case class OMLSpecificationTables
(
  terminologyGraphs : Seq[TerminologyGraph] = Seq.empty,
  bundles : Seq[Bundle] = Seq.empty,
  descriptionBoxes : Seq[DescriptionBox] = Seq.empty,
  annotationProperties : Seq[AnnotationProperty] = Seq.empty,
  aspects : Seq[Aspect] = Seq.empty,
  concepts : Seq[Concept] = Seq.empty,
  scalars : Seq[Scalar] = Seq.empty,
  structures : Seq[Structure] = Seq.empty,
  conceptDesignationTerminologyAxioms : Seq[ConceptDesignationTerminologyAxiom] = Seq.empty,
  terminologyExtensionAxioms : Seq[TerminologyExtensionAxiom] = Seq.empty,
  terminologyNestingAxioms : Seq[TerminologyNestingAxiom] = Seq.empty,
  bundledTerminologyAxioms : Seq[BundledTerminologyAxiom] = Seq.empty,
  descriptionBoxExtendsClosedWorldDefinitions : Seq[DescriptionBoxExtendsClosedWorldDefinitions] = Seq.empty,
  descriptionBoxRefinements : Seq[DescriptionBoxRefinement] = Seq.empty,
  binaryScalarRestrictions : Seq[BinaryScalarRestriction] = Seq.empty,
  iriScalarRestrictions : Seq[IRIScalarRestriction] = Seq.empty,
  numericScalarRestrictions : Seq[NumericScalarRestriction] = Seq.empty,
  plainLiteralScalarRestrictions : Seq[PlainLiteralScalarRestriction] = Seq.empty,
  scalarOneOfRestrictions : Seq[ScalarOneOfRestriction] = Seq.empty,
  scalarOneOfLiteralAxioms : Seq[ScalarOneOfLiteralAxiom] = Seq.empty,
  stringScalarRestrictions : Seq[StringScalarRestriction] = Seq.empty,
  synonymScalarRestrictions : Seq[SynonymScalarRestriction] = Seq.empty,
  timeScalarRestrictions : Seq[TimeScalarRestriction] = Seq.empty,
  entityScalarDataProperties : Seq[EntityScalarDataProperty] = Seq.empty,
  entityStructuredDataProperties : Seq[EntityStructuredDataProperty] = Seq.empty,
  scalarDataProperties : Seq[ScalarDataProperty] = Seq.empty,
  structuredDataProperties : Seq[StructuredDataProperty] = Seq.empty,
  reifiedRelationships : Seq[ReifiedRelationship] = Seq.empty,
  unreifiedRelationships : Seq[UnreifiedRelationship] = Seq.empty,
  chainRules : Seq[ChainRule] = Seq.empty,
  ruleBodySegments : Seq[RuleBodySegment] = Seq.empty,
  aspectPredicates : Seq[AspectPredicate] = Seq.empty,
  conceptPredicates : Seq[ConceptPredicate] = Seq.empty,
  reifiedRelationshipPredicates : Seq[ReifiedRelationshipPredicate] = Seq.empty,
  reifiedRelationshipPropertyPredicates : Seq[ReifiedRelationshipPropertyPredicate] = Seq.empty,
  reifiedRelationshipSourcePropertyPredicates : Seq[ReifiedRelationshipSourcePropertyPredicate] = Seq.empty,
  reifiedRelationshipTargetPropertyPredicates : Seq[ReifiedRelationshipTargetPropertyPredicate] = Seq.empty,
  unreifiedRelationshipPropertyPredicates : Seq[UnreifiedRelationshipPropertyPredicate] = Seq.empty,
  reifiedRelationshipInversePropertyPredicates : Seq[ReifiedRelationshipInversePropertyPredicate] = Seq.empty,
  reifiedRelationshipSourceInversePropertyPredicates : Seq[ReifiedRelationshipSourceInversePropertyPredicate] = Seq.empty,
  reifiedRelationshipTargetInversePropertyPredicates : Seq[ReifiedRelationshipTargetInversePropertyPredicate] = Seq.empty,
  unreifiedRelationshipInversePropertyPredicates : Seq[UnreifiedRelationshipInversePropertyPredicate] = Seq.empty,
  entityExistentialRestrictionAxioms : Seq[EntityExistentialRestrictionAxiom] = Seq.empty,
  entityUniversalRestrictionAxioms : Seq[EntityUniversalRestrictionAxiom] = Seq.empty,
  entityScalarDataPropertyExistentialRestrictionAxioms : Seq[EntityScalarDataPropertyExistentialRestrictionAxiom] = Seq.empty,
  entityScalarDataPropertyParticularRestrictionAxioms : Seq[EntityScalarDataPropertyParticularRestrictionAxiom] = Seq.empty,
  entityScalarDataPropertyUniversalRestrictionAxioms : Seq[EntityScalarDataPropertyUniversalRestrictionAxiom] = Seq.empty,
  entityStructuredDataPropertyParticularRestrictionAxioms : Seq[EntityStructuredDataPropertyParticularRestrictionAxiom] = Seq.empty,
  restrictionStructuredDataPropertyTuples : Seq[RestrictionStructuredDataPropertyTuple] = Seq.empty,
  restrictionScalarDataPropertyValues : Seq[RestrictionScalarDataPropertyValue] = Seq.empty,
  aspectSpecializationAxioms : Seq[AspectSpecializationAxiom] = Seq.empty,
  conceptSpecializationAxioms : Seq[ConceptSpecializationAxiom] = Seq.empty,
  reifiedRelationshipSpecializationAxioms : Seq[ReifiedRelationshipSpecializationAxiom] = Seq.empty,
  subDataPropertyOfAxioms : Seq[SubDataPropertyOfAxiom] = Seq.empty,
  subObjectPropertyOfAxioms : Seq[SubObjectPropertyOfAxiom] = Seq.empty,
  rootConceptTaxonomyAxioms : Seq[RootConceptTaxonomyAxiom] = Seq.empty,
  anonymousConceptUnionAxioms : Seq[AnonymousConceptUnionAxiom] = Seq.empty,
  specificDisjointConceptAxioms : Seq[SpecificDisjointConceptAxiom] = Seq.empty,
  conceptInstances : Seq[ConceptInstance] = Seq.empty,
  reifiedRelationshipInstances : Seq[ReifiedRelationshipInstance] = Seq.empty,
  reifiedRelationshipInstanceDomains : Seq[ReifiedRelationshipInstanceDomain] = Seq.empty,
  reifiedRelationshipInstanceRanges : Seq[ReifiedRelationshipInstanceRange] = Seq.empty,
  unreifiedRelationshipInstanceTuples : Seq[UnreifiedRelationshipInstanceTuple] = Seq.empty,
  singletonInstanceStructuredDataPropertyValues : Seq[SingletonInstanceStructuredDataPropertyValue] = Seq.empty,
  singletonInstanceScalarDataPropertyValues : Seq[SingletonInstanceScalarDataPropertyValue] = Seq.empty,
  structuredDataPropertyTuples : Seq[StructuredDataPropertyTuple] = Seq.empty,
  scalarDataPropertyValues : Seq[ScalarDataPropertyValue] = Seq.empty,
  annotationPropertyValues : Seq[AnnotationPropertyValue] = Seq.empty
)
{
  def readTerminologyGraphs(is: InputStream)
  : OMLSpecificationTables
  = copy(terminologyGraphs = 
    (terminologyGraphs.to[Set] ++ 
     readJSonTable(is, TerminologyGraphHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readBundles(is: InputStream)
  : OMLSpecificationTables
  = copy(bundles = 
    (bundles.to[Set] ++ 
     readJSonTable(is, BundleHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readDescriptionBoxes(is: InputStream)
  : OMLSpecificationTables
  = copy(descriptionBoxes = 
    (descriptionBoxes.to[Set] ++ 
     readJSonTable(is, DescriptionBoxHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readAnnotationProperties(is: InputStream)
  : OMLSpecificationTables
  = copy(annotationProperties = 
    (annotationProperties.to[Set] ++ 
     readJSonTable(is, AnnotationPropertyHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readAspects(is: InputStream)
  : OMLSpecificationTables
  = copy(aspects = 
    (aspects.to[Set] ++ 
     readJSonTable(is, AspectHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readConcepts(is: InputStream)
  : OMLSpecificationTables
  = copy(concepts = 
    (concepts.to[Set] ++ 
     readJSonTable(is, ConceptHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readScalars(is: InputStream)
  : OMLSpecificationTables
  = copy(scalars = 
    (scalars.to[Set] ++ 
     readJSonTable(is, ScalarHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readStructures(is: InputStream)
  : OMLSpecificationTables
  = copy(structures = 
    (structures.to[Set] ++ 
     readJSonTable(is, StructureHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readConceptDesignationTerminologyAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(conceptDesignationTerminologyAxioms = 
    (conceptDesignationTerminologyAxioms.to[Set] ++ 
     readJSonTable(is, ConceptDesignationTerminologyAxiomHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readTerminologyExtensionAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(terminologyExtensionAxioms = 
    (terminologyExtensionAxioms.to[Set] ++ 
     readJSonTable(is, TerminologyExtensionAxiomHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readTerminologyNestingAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(terminologyNestingAxioms = 
    (terminologyNestingAxioms.to[Set] ++ 
     readJSonTable(is, TerminologyNestingAxiomHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readBundledTerminologyAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(bundledTerminologyAxioms = 
    (bundledTerminologyAxioms.to[Set] ++ 
     readJSonTable(is, BundledTerminologyAxiomHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readDescriptionBoxExtendsClosedWorldDefinitions(is: InputStream)
  : OMLSpecificationTables
  = copy(descriptionBoxExtendsClosedWorldDefinitions = 
    (descriptionBoxExtendsClosedWorldDefinitions.to[Set] ++ 
     readJSonTable(is, DescriptionBoxExtendsClosedWorldDefinitionsHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readDescriptionBoxRefinements(is: InputStream)
  : OMLSpecificationTables
  = copy(descriptionBoxRefinements = 
    (descriptionBoxRefinements.to[Set] ++ 
     readJSonTable(is, DescriptionBoxRefinementHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readBinaryScalarRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(binaryScalarRestrictions = 
    (binaryScalarRestrictions.to[Set] ++ 
     readJSonTable(is, BinaryScalarRestrictionHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readIRIScalarRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(iriScalarRestrictions = 
    (iriScalarRestrictions.to[Set] ++ 
     readJSonTable(is, IRIScalarRestrictionHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readNumericScalarRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(numericScalarRestrictions = 
    (numericScalarRestrictions.to[Set] ++ 
     readJSonTable(is, NumericScalarRestrictionHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readPlainLiteralScalarRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(plainLiteralScalarRestrictions = 
    (plainLiteralScalarRestrictions.to[Set] ++ 
     readJSonTable(is, PlainLiteralScalarRestrictionHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readScalarOneOfRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(scalarOneOfRestrictions = 
    (scalarOneOfRestrictions.to[Set] ++ 
     readJSonTable(is, ScalarOneOfRestrictionHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readScalarOneOfLiteralAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(scalarOneOfLiteralAxioms = 
    (scalarOneOfLiteralAxioms.to[Set] ++ 
     readJSonTable(is, ScalarOneOfLiteralAxiomHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readStringScalarRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(stringScalarRestrictions = 
    (stringScalarRestrictions.to[Set] ++ 
     readJSonTable(is, StringScalarRestrictionHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readSynonymScalarRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(synonymScalarRestrictions = 
    (synonymScalarRestrictions.to[Set] ++ 
     readJSonTable(is, SynonymScalarRestrictionHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readTimeScalarRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(timeScalarRestrictions = 
    (timeScalarRestrictions.to[Set] ++ 
     readJSonTable(is, TimeScalarRestrictionHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readEntityScalarDataProperties(is: InputStream)
  : OMLSpecificationTables
  = copy(entityScalarDataProperties = 
    (entityScalarDataProperties.to[Set] ++ 
     readJSonTable(is, EntityScalarDataPropertyHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readEntityStructuredDataProperties(is: InputStream)
  : OMLSpecificationTables
  = copy(entityStructuredDataProperties = 
    (entityStructuredDataProperties.to[Set] ++ 
     readJSonTable(is, EntityStructuredDataPropertyHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readScalarDataProperties(is: InputStream)
  : OMLSpecificationTables
  = copy(scalarDataProperties = 
    (scalarDataProperties.to[Set] ++ 
     readJSonTable(is, ScalarDataPropertyHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readStructuredDataProperties(is: InputStream)
  : OMLSpecificationTables
  = copy(structuredDataProperties = 
    (structuredDataProperties.to[Set] ++ 
     readJSonTable(is, StructuredDataPropertyHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readReifiedRelationships(is: InputStream)
  : OMLSpecificationTables
  = copy(reifiedRelationships = 
    (reifiedRelationships.to[Set] ++ 
     readJSonTable(is, ReifiedRelationshipHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readUnreifiedRelationships(is: InputStream)
  : OMLSpecificationTables
  = copy(unreifiedRelationships = 
    (unreifiedRelationships.to[Set] ++ 
     readJSonTable(is, UnreifiedRelationshipHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readChainRules(is: InputStream)
  : OMLSpecificationTables
  = copy(chainRules = 
    (chainRules.to[Set] ++ 
     readJSonTable(is, ChainRuleHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readRuleBodySegments(is: InputStream)
  : OMLSpecificationTables
  = copy(ruleBodySegments = 
    (ruleBodySegments.to[Set] ++ 
     readJSonTable(is, RuleBodySegmentHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readAspectPredicates(is: InputStream)
  : OMLSpecificationTables
  = copy(aspectPredicates = 
    (aspectPredicates.to[Set] ++ 
     readJSonTable(is, AspectPredicateHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readConceptPredicates(is: InputStream)
  : OMLSpecificationTables
  = copy(conceptPredicates = 
    (conceptPredicates.to[Set] ++ 
     readJSonTable(is, ConceptPredicateHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readReifiedRelationshipPredicates(is: InputStream)
  : OMLSpecificationTables
  = copy(reifiedRelationshipPredicates = 
    (reifiedRelationshipPredicates.to[Set] ++ 
     readJSonTable(is, ReifiedRelationshipPredicateHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readReifiedRelationshipPropertyPredicates(is: InputStream)
  : OMLSpecificationTables
  = copy(reifiedRelationshipPropertyPredicates = 
    (reifiedRelationshipPropertyPredicates.to[Set] ++ 
     readJSonTable(is, ReifiedRelationshipPropertyPredicateHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readReifiedRelationshipSourcePropertyPredicates(is: InputStream)
  : OMLSpecificationTables
  = copy(reifiedRelationshipSourcePropertyPredicates = 
    (reifiedRelationshipSourcePropertyPredicates.to[Set] ++ 
     readJSonTable(is, ReifiedRelationshipSourcePropertyPredicateHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readReifiedRelationshipTargetPropertyPredicates(is: InputStream)
  : OMLSpecificationTables
  = copy(reifiedRelationshipTargetPropertyPredicates = 
    (reifiedRelationshipTargetPropertyPredicates.to[Set] ++ 
     readJSonTable(is, ReifiedRelationshipTargetPropertyPredicateHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readUnreifiedRelationshipPropertyPredicates(is: InputStream)
  : OMLSpecificationTables
  = copy(unreifiedRelationshipPropertyPredicates = 
    (unreifiedRelationshipPropertyPredicates.to[Set] ++ 
     readJSonTable(is, UnreifiedRelationshipPropertyPredicateHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readReifiedRelationshipInversePropertyPredicates(is: InputStream)
  : OMLSpecificationTables
  = copy(reifiedRelationshipInversePropertyPredicates = 
    (reifiedRelationshipInversePropertyPredicates.to[Set] ++ 
     readJSonTable(is, ReifiedRelationshipInversePropertyPredicateHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readReifiedRelationshipSourceInversePropertyPredicates(is: InputStream)
  : OMLSpecificationTables
  = copy(reifiedRelationshipSourceInversePropertyPredicates = 
    (reifiedRelationshipSourceInversePropertyPredicates.to[Set] ++ 
     readJSonTable(is, ReifiedRelationshipSourceInversePropertyPredicateHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readReifiedRelationshipTargetInversePropertyPredicates(is: InputStream)
  : OMLSpecificationTables
  = copy(reifiedRelationshipTargetInversePropertyPredicates = 
    (reifiedRelationshipTargetInversePropertyPredicates.to[Set] ++ 
     readJSonTable(is, ReifiedRelationshipTargetInversePropertyPredicateHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readUnreifiedRelationshipInversePropertyPredicates(is: InputStream)
  : OMLSpecificationTables
  = copy(unreifiedRelationshipInversePropertyPredicates = 
    (unreifiedRelationshipInversePropertyPredicates.to[Set] ++ 
     readJSonTable(is, UnreifiedRelationshipInversePropertyPredicateHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readEntityExistentialRestrictionAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(entityExistentialRestrictionAxioms = 
    (entityExistentialRestrictionAxioms.to[Set] ++ 
     readJSonTable(is, EntityExistentialRestrictionAxiomHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readEntityUniversalRestrictionAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(entityUniversalRestrictionAxioms = 
    (entityUniversalRestrictionAxioms.to[Set] ++ 
     readJSonTable(is, EntityUniversalRestrictionAxiomHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readEntityScalarDataPropertyExistentialRestrictionAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(entityScalarDataPropertyExistentialRestrictionAxioms = 
    (entityScalarDataPropertyExistentialRestrictionAxioms.to[Set] ++ 
     readJSonTable(is, EntityScalarDataPropertyExistentialRestrictionAxiomHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readEntityScalarDataPropertyParticularRestrictionAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(entityScalarDataPropertyParticularRestrictionAxioms = 
    (entityScalarDataPropertyParticularRestrictionAxioms.to[Set] ++ 
     readJSonTable(is, EntityScalarDataPropertyParticularRestrictionAxiomHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readEntityScalarDataPropertyUniversalRestrictionAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(entityScalarDataPropertyUniversalRestrictionAxioms = 
    (entityScalarDataPropertyUniversalRestrictionAxioms.to[Set] ++ 
     readJSonTable(is, EntityScalarDataPropertyUniversalRestrictionAxiomHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readEntityStructuredDataPropertyParticularRestrictionAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(entityStructuredDataPropertyParticularRestrictionAxioms = 
    (entityStructuredDataPropertyParticularRestrictionAxioms.to[Set] ++ 
     readJSonTable(is, EntityStructuredDataPropertyParticularRestrictionAxiomHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readRestrictionStructuredDataPropertyTuples(is: InputStream)
  : OMLSpecificationTables
  = copy(restrictionStructuredDataPropertyTuples = 
    (restrictionStructuredDataPropertyTuples.to[Set] ++ 
     readJSonTable(is, RestrictionStructuredDataPropertyTupleHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readRestrictionScalarDataPropertyValues(is: InputStream)
  : OMLSpecificationTables
  = copy(restrictionScalarDataPropertyValues = 
    (restrictionScalarDataPropertyValues.to[Set] ++ 
     readJSonTable(is, RestrictionScalarDataPropertyValueHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readAspectSpecializationAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(aspectSpecializationAxioms = 
    (aspectSpecializationAxioms.to[Set] ++ 
     readJSonTable(is, AspectSpecializationAxiomHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readConceptSpecializationAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(conceptSpecializationAxioms = 
    (conceptSpecializationAxioms.to[Set] ++ 
     readJSonTable(is, ConceptSpecializationAxiomHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readReifiedRelationshipSpecializationAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(reifiedRelationshipSpecializationAxioms = 
    (reifiedRelationshipSpecializationAxioms.to[Set] ++ 
     readJSonTable(is, ReifiedRelationshipSpecializationAxiomHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readSubDataPropertyOfAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(subDataPropertyOfAxioms = 
    (subDataPropertyOfAxioms.to[Set] ++ 
     readJSonTable(is, SubDataPropertyOfAxiomHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readSubObjectPropertyOfAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(subObjectPropertyOfAxioms = 
    (subObjectPropertyOfAxioms.to[Set] ++ 
     readJSonTable(is, SubObjectPropertyOfAxiomHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readRootConceptTaxonomyAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(rootConceptTaxonomyAxioms = 
    (rootConceptTaxonomyAxioms.to[Set] ++ 
     readJSonTable(is, RootConceptTaxonomyAxiomHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readAnonymousConceptUnionAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(anonymousConceptUnionAxioms = 
    (anonymousConceptUnionAxioms.to[Set] ++ 
     readJSonTable(is, AnonymousConceptUnionAxiomHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readSpecificDisjointConceptAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(specificDisjointConceptAxioms = 
    (specificDisjointConceptAxioms.to[Set] ++ 
     readJSonTable(is, SpecificDisjointConceptAxiomHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readConceptInstances(is: InputStream)
  : OMLSpecificationTables
  = copy(conceptInstances = 
    (conceptInstances.to[Set] ++ 
     readJSonTable(is, ConceptInstanceHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readReifiedRelationshipInstances(is: InputStream)
  : OMLSpecificationTables
  = copy(reifiedRelationshipInstances = 
    (reifiedRelationshipInstances.to[Set] ++ 
     readJSonTable(is, ReifiedRelationshipInstanceHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readReifiedRelationshipInstanceDomains(is: InputStream)
  : OMLSpecificationTables
  = copy(reifiedRelationshipInstanceDomains = 
    (reifiedRelationshipInstanceDomains.to[Set] ++ 
     readJSonTable(is, ReifiedRelationshipInstanceDomainHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readReifiedRelationshipInstanceRanges(is: InputStream)
  : OMLSpecificationTables
  = copy(reifiedRelationshipInstanceRanges = 
    (reifiedRelationshipInstanceRanges.to[Set] ++ 
     readJSonTable(is, ReifiedRelationshipInstanceRangeHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readUnreifiedRelationshipInstanceTuples(is: InputStream)
  : OMLSpecificationTables
  = copy(unreifiedRelationshipInstanceTuples = 
    (unreifiedRelationshipInstanceTuples.to[Set] ++ 
     readJSonTable(is, UnreifiedRelationshipInstanceTupleHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readSingletonInstanceStructuredDataPropertyValues(is: InputStream)
  : OMLSpecificationTables
  = copy(singletonInstanceStructuredDataPropertyValues = 
    (singletonInstanceStructuredDataPropertyValues.to[Set] ++ 
     readJSonTable(is, SingletonInstanceStructuredDataPropertyValueHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readSingletonInstanceScalarDataPropertyValues(is: InputStream)
  : OMLSpecificationTables
  = copy(singletonInstanceScalarDataPropertyValues = 
    (singletonInstanceScalarDataPropertyValues.to[Set] ++ 
     readJSonTable(is, SingletonInstanceScalarDataPropertyValueHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readStructuredDataPropertyTuples(is: InputStream)
  : OMLSpecificationTables
  = copy(structuredDataPropertyTuples = 
    (structuredDataPropertyTuples.to[Set] ++ 
     readJSonTable(is, StructuredDataPropertyTupleHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readScalarDataPropertyValues(is: InputStream)
  : OMLSpecificationTables
  = copy(scalarDataPropertyValues = 
    (scalarDataPropertyValues.to[Set] ++ 
     readJSonTable(is, ScalarDataPropertyValueHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  def readAnnotationPropertyValues(is: InputStream)
  : OMLSpecificationTables
  = copy(annotationPropertyValues = 
    (annotationPropertyValues.to[Set] ++ 
     readJSonTable(is, AnnotationPropertyValueHelper.fromJSON).to[Set]
    ).to[Seq].sortBy(_.uuid))
  
  def isEmpty: Boolean
  = terminologyGraphs.isEmpty &&
    bundles.isEmpty &&
    descriptionBoxes.isEmpty &&
    annotationProperties.isEmpty &&
    aspects.isEmpty &&
    concepts.isEmpty &&
    scalars.isEmpty &&
    structures.isEmpty &&
    conceptDesignationTerminologyAxioms.isEmpty &&
    terminologyExtensionAxioms.isEmpty &&
    terminologyNestingAxioms.isEmpty &&
    bundledTerminologyAxioms.isEmpty &&
    descriptionBoxExtendsClosedWorldDefinitions.isEmpty &&
    descriptionBoxRefinements.isEmpty &&
    binaryScalarRestrictions.isEmpty &&
    iriScalarRestrictions.isEmpty &&
    numericScalarRestrictions.isEmpty &&
    plainLiteralScalarRestrictions.isEmpty &&
    scalarOneOfRestrictions.isEmpty &&
    scalarOneOfLiteralAxioms.isEmpty &&
    stringScalarRestrictions.isEmpty &&
    synonymScalarRestrictions.isEmpty &&
    timeScalarRestrictions.isEmpty &&
    entityScalarDataProperties.isEmpty &&
    entityStructuredDataProperties.isEmpty &&
    scalarDataProperties.isEmpty &&
    structuredDataProperties.isEmpty &&
    reifiedRelationships.isEmpty &&
    unreifiedRelationships.isEmpty &&
    chainRules.isEmpty &&
    ruleBodySegments.isEmpty &&
    aspectPredicates.isEmpty &&
    conceptPredicates.isEmpty &&
    reifiedRelationshipPredicates.isEmpty &&
    reifiedRelationshipPropertyPredicates.isEmpty &&
    reifiedRelationshipSourcePropertyPredicates.isEmpty &&
    reifiedRelationshipTargetPropertyPredicates.isEmpty &&
    unreifiedRelationshipPropertyPredicates.isEmpty &&
    reifiedRelationshipInversePropertyPredicates.isEmpty &&
    reifiedRelationshipSourceInversePropertyPredicates.isEmpty &&
    reifiedRelationshipTargetInversePropertyPredicates.isEmpty &&
    unreifiedRelationshipInversePropertyPredicates.isEmpty &&
    entityExistentialRestrictionAxioms.isEmpty &&
    entityUniversalRestrictionAxioms.isEmpty &&
    entityScalarDataPropertyExistentialRestrictionAxioms.isEmpty &&
    entityScalarDataPropertyParticularRestrictionAxioms.isEmpty &&
    entityScalarDataPropertyUniversalRestrictionAxioms.isEmpty &&
    entityStructuredDataPropertyParticularRestrictionAxioms.isEmpty &&
    restrictionStructuredDataPropertyTuples.isEmpty &&
    restrictionScalarDataPropertyValues.isEmpty &&
    aspectSpecializationAxioms.isEmpty &&
    conceptSpecializationAxioms.isEmpty &&
    reifiedRelationshipSpecializationAxioms.isEmpty &&
    subDataPropertyOfAxioms.isEmpty &&
    subObjectPropertyOfAxioms.isEmpty &&
    rootConceptTaxonomyAxioms.isEmpty &&
    anonymousConceptUnionAxioms.isEmpty &&
    specificDisjointConceptAxioms.isEmpty &&
    conceptInstances.isEmpty &&
    reifiedRelationshipInstances.isEmpty &&
    reifiedRelationshipInstanceDomains.isEmpty &&
    reifiedRelationshipInstanceRanges.isEmpty &&
    unreifiedRelationshipInstanceTuples.isEmpty &&
    singletonInstanceStructuredDataPropertyValues.isEmpty &&
    singletonInstanceScalarDataPropertyValues.isEmpty &&
    structuredDataPropertyTuples.isEmpty &&
    scalarDataPropertyValues.isEmpty &&
    annotationPropertyValues.isEmpty
  
  def show: String = {
  
    def showSeq[T](title: String, s: Seq[T]): String = {
      if (s.isEmpty)
         "\n" + title + ": empty"
      else
         "\n" + title + s": ${s.size} entries" +
         s.map(_.toString).mkString("\n ", "\n ", "\n")
    }
  
    val buff = new scala.collection.mutable.StringBuilder()
  
    buff ++= showSeq("terminologyGraphs", terminologyGraphs)
    buff ++= showSeq("bundles", bundles)
    buff ++= showSeq("descriptionBoxes", descriptionBoxes)
    buff ++= showSeq("annotationProperties", annotationProperties)
    buff ++= showSeq("aspects", aspects)
    buff ++= showSeq("concepts", concepts)
    buff ++= showSeq("scalars", scalars)
    buff ++= showSeq("structures", structures)
    buff ++= showSeq("conceptDesignationTerminologyAxioms", conceptDesignationTerminologyAxioms)
    buff ++= showSeq("terminologyExtensionAxioms", terminologyExtensionAxioms)
    buff ++= showSeq("terminologyNestingAxioms", terminologyNestingAxioms)
    buff ++= showSeq("bundledTerminologyAxioms", bundledTerminologyAxioms)
    buff ++= showSeq("descriptionBoxExtendsClosedWorldDefinitions", descriptionBoxExtendsClosedWorldDefinitions)
    buff ++= showSeq("descriptionBoxRefinements", descriptionBoxRefinements)
    buff ++= showSeq("binaryScalarRestrictions", binaryScalarRestrictions)
    buff ++= showSeq("iriScalarRestrictions", iriScalarRestrictions)
    buff ++= showSeq("numericScalarRestrictions", numericScalarRestrictions)
    buff ++= showSeq("plainLiteralScalarRestrictions", plainLiteralScalarRestrictions)
    buff ++= showSeq("scalarOneOfRestrictions", scalarOneOfRestrictions)
    buff ++= showSeq("scalarOneOfLiteralAxioms", scalarOneOfLiteralAxioms)
    buff ++= showSeq("stringScalarRestrictions", stringScalarRestrictions)
    buff ++= showSeq("synonymScalarRestrictions", synonymScalarRestrictions)
    buff ++= showSeq("timeScalarRestrictions", timeScalarRestrictions)
    buff ++= showSeq("entityScalarDataProperties", entityScalarDataProperties)
    buff ++= showSeq("entityStructuredDataProperties", entityStructuredDataProperties)
    buff ++= showSeq("scalarDataProperties", scalarDataProperties)
    buff ++= showSeq("structuredDataProperties", structuredDataProperties)
    buff ++= showSeq("reifiedRelationships", reifiedRelationships)
    buff ++= showSeq("unreifiedRelationships", unreifiedRelationships)
    buff ++= showSeq("chainRules", chainRules)
    buff ++= showSeq("ruleBodySegments", ruleBodySegments)
    buff ++= showSeq("aspectPredicates", aspectPredicates)
    buff ++= showSeq("conceptPredicates", conceptPredicates)
    buff ++= showSeq("reifiedRelationshipPredicates", reifiedRelationshipPredicates)
    buff ++= showSeq("reifiedRelationshipPropertyPredicates", reifiedRelationshipPropertyPredicates)
    buff ++= showSeq("reifiedRelationshipSourcePropertyPredicates", reifiedRelationshipSourcePropertyPredicates)
    buff ++= showSeq("reifiedRelationshipTargetPropertyPredicates", reifiedRelationshipTargetPropertyPredicates)
    buff ++= showSeq("unreifiedRelationshipPropertyPredicates", unreifiedRelationshipPropertyPredicates)
    buff ++= showSeq("reifiedRelationshipInversePropertyPredicates", reifiedRelationshipInversePropertyPredicates)
    buff ++= showSeq("reifiedRelationshipSourceInversePropertyPredicates", reifiedRelationshipSourceInversePropertyPredicates)
    buff ++= showSeq("reifiedRelationshipTargetInversePropertyPredicates", reifiedRelationshipTargetInversePropertyPredicates)
    buff ++= showSeq("unreifiedRelationshipInversePropertyPredicates", unreifiedRelationshipInversePropertyPredicates)
    buff ++= showSeq("entityExistentialRestrictionAxioms", entityExistentialRestrictionAxioms)
    buff ++= showSeq("entityUniversalRestrictionAxioms", entityUniversalRestrictionAxioms)
    buff ++= showSeq("entityScalarDataPropertyExistentialRestrictionAxioms", entityScalarDataPropertyExistentialRestrictionAxioms)
    buff ++= showSeq("entityScalarDataPropertyParticularRestrictionAxioms", entityScalarDataPropertyParticularRestrictionAxioms)
    buff ++= showSeq("entityScalarDataPropertyUniversalRestrictionAxioms", entityScalarDataPropertyUniversalRestrictionAxioms)
    buff ++= showSeq("entityStructuredDataPropertyParticularRestrictionAxioms", entityStructuredDataPropertyParticularRestrictionAxioms)
    buff ++= showSeq("restrictionStructuredDataPropertyTuples", restrictionStructuredDataPropertyTuples)
    buff ++= showSeq("restrictionScalarDataPropertyValues", restrictionScalarDataPropertyValues)
    buff ++= showSeq("aspectSpecializationAxioms", aspectSpecializationAxioms)
    buff ++= showSeq("conceptSpecializationAxioms", conceptSpecializationAxioms)
    buff ++= showSeq("reifiedRelationshipSpecializationAxioms", reifiedRelationshipSpecializationAxioms)
    buff ++= showSeq("subDataPropertyOfAxioms", subDataPropertyOfAxioms)
    buff ++= showSeq("subObjectPropertyOfAxioms", subObjectPropertyOfAxioms)
    buff ++= showSeq("rootConceptTaxonomyAxioms", rootConceptTaxonomyAxioms)
    buff ++= showSeq("anonymousConceptUnionAxioms", anonymousConceptUnionAxioms)
    buff ++= showSeq("specificDisjointConceptAxioms", specificDisjointConceptAxioms)
    buff ++= showSeq("conceptInstances", conceptInstances)
    buff ++= showSeq("reifiedRelationshipInstances", reifiedRelationshipInstances)
    buff ++= showSeq("reifiedRelationshipInstanceDomains", reifiedRelationshipInstanceDomains)
    buff ++= showSeq("reifiedRelationshipInstanceRanges", reifiedRelationshipInstanceRanges)
    buff ++= showSeq("unreifiedRelationshipInstanceTuples", unreifiedRelationshipInstanceTuples)
    buff ++= showSeq("singletonInstanceStructuredDataPropertyValues", singletonInstanceStructuredDataPropertyValues)
    buff ++= showSeq("singletonInstanceScalarDataPropertyValues", singletonInstanceScalarDataPropertyValues)
    buff ++= showSeq("structuredDataPropertyTuples", structuredDataPropertyTuples)
    buff ++= showSeq("scalarDataPropertyValues", scalarDataPropertyValues)
    buff ++= showSeq("annotationPropertyValues", annotationPropertyValues)
  
    buff.toString
  }

}

object OMLSpecificationTables {
	
  def createEmptyOMLSpecificationTables()
  : OMLSpecificationTables
  = new OMLSpecificationTables()
  
  def loadOMLSpecificationTables(omlSchemaJsonZipFile: File)
  : Try[OMLSpecificationTables]
  = nonFatalCatch[Try[OMLSpecificationTables]]
    .withApply {
      (cause: java.lang.Throwable) =>
        cause.fillInStackTrace()
        Failure(cause)
    }
    .apply {
      val zipFile = new ZipFile(omlSchemaJsonZipFile)
      val omlTables =
        zipFile
        .getEntries
        .toIterable
        .par
         .aggregate(OMLSpecificationTables())(seqop = readZipArchive(zipFile), combop = mergeTables)
      zipFile.close()
      Success(omlTables)
    }

  def mergeTables
  (t1: OMLSpecificationTables, t2: OMLSpecificationTables)
  : OMLSpecificationTables
  = OMLSpecificationTables(
      terminologyGraphs = (
        t1.terminologyGraphs.to[Set] ++ 
        t2.terminologyGraphs.to[Set]
      ).to[Seq].sortBy(_.uuid),
      bundles = (
        t1.bundles.to[Set] ++ 
        t2.bundles.to[Set]
      ).to[Seq].sortBy(_.uuid),
      descriptionBoxes = (
        t1.descriptionBoxes.to[Set] ++ 
        t2.descriptionBoxes.to[Set]
      ).to[Seq].sortBy(_.uuid),
      annotationProperties = (
        t1.annotationProperties.to[Set] ++ 
        t2.annotationProperties.to[Set]
      ).to[Seq].sortBy(_.uuid),
      aspects = (
        t1.aspects.to[Set] ++ 
        t2.aspects.to[Set]
      ).to[Seq].sortBy(_.uuid),
      concepts = (
        t1.concepts.to[Set] ++ 
        t2.concepts.to[Set]
      ).to[Seq].sortBy(_.uuid),
      scalars = (
        t1.scalars.to[Set] ++ 
        t2.scalars.to[Set]
      ).to[Seq].sortBy(_.uuid),
      structures = (
        t1.structures.to[Set] ++ 
        t2.structures.to[Set]
      ).to[Seq].sortBy(_.uuid),
      conceptDesignationTerminologyAxioms = (
        t1.conceptDesignationTerminologyAxioms.to[Set] ++ 
        t2.conceptDesignationTerminologyAxioms.to[Set]
      ).to[Seq].sortBy(_.uuid),
      terminologyExtensionAxioms = (
        t1.terminologyExtensionAxioms.to[Set] ++ 
        t2.terminologyExtensionAxioms.to[Set]
      ).to[Seq].sortBy(_.uuid),
      terminologyNestingAxioms = (
        t1.terminologyNestingAxioms.to[Set] ++ 
        t2.terminologyNestingAxioms.to[Set]
      ).to[Seq].sortBy(_.uuid),
      bundledTerminologyAxioms = (
        t1.bundledTerminologyAxioms.to[Set] ++ 
        t2.bundledTerminologyAxioms.to[Set]
      ).to[Seq].sortBy(_.uuid),
      descriptionBoxExtendsClosedWorldDefinitions = (
        t1.descriptionBoxExtendsClosedWorldDefinitions.to[Set] ++ 
        t2.descriptionBoxExtendsClosedWorldDefinitions.to[Set]
      ).to[Seq].sortBy(_.uuid),
      descriptionBoxRefinements = (
        t1.descriptionBoxRefinements.to[Set] ++ 
        t2.descriptionBoxRefinements.to[Set]
      ).to[Seq].sortBy(_.uuid),
      binaryScalarRestrictions = (
        t1.binaryScalarRestrictions.to[Set] ++ 
        t2.binaryScalarRestrictions.to[Set]
      ).to[Seq].sortBy(_.uuid),
      iriScalarRestrictions = (
        t1.iriScalarRestrictions.to[Set] ++ 
        t2.iriScalarRestrictions.to[Set]
      ).to[Seq].sortBy(_.uuid),
      numericScalarRestrictions = (
        t1.numericScalarRestrictions.to[Set] ++ 
        t2.numericScalarRestrictions.to[Set]
      ).to[Seq].sortBy(_.uuid),
      plainLiteralScalarRestrictions = (
        t1.plainLiteralScalarRestrictions.to[Set] ++ 
        t2.plainLiteralScalarRestrictions.to[Set]
      ).to[Seq].sortBy(_.uuid),
      scalarOneOfRestrictions = (
        t1.scalarOneOfRestrictions.to[Set] ++ 
        t2.scalarOneOfRestrictions.to[Set]
      ).to[Seq].sortBy(_.uuid),
      scalarOneOfLiteralAxioms = (
        t1.scalarOneOfLiteralAxioms.to[Set] ++ 
        t2.scalarOneOfLiteralAxioms.to[Set]
      ).to[Seq].sortBy(_.uuid),
      stringScalarRestrictions = (
        t1.stringScalarRestrictions.to[Set] ++ 
        t2.stringScalarRestrictions.to[Set]
      ).to[Seq].sortBy(_.uuid),
      synonymScalarRestrictions = (
        t1.synonymScalarRestrictions.to[Set] ++ 
        t2.synonymScalarRestrictions.to[Set]
      ).to[Seq].sortBy(_.uuid),
      timeScalarRestrictions = (
        t1.timeScalarRestrictions.to[Set] ++ 
        t2.timeScalarRestrictions.to[Set]
      ).to[Seq].sortBy(_.uuid),
      entityScalarDataProperties = (
        t1.entityScalarDataProperties.to[Set] ++ 
        t2.entityScalarDataProperties.to[Set]
      ).to[Seq].sortBy(_.uuid),
      entityStructuredDataProperties = (
        t1.entityStructuredDataProperties.to[Set] ++ 
        t2.entityStructuredDataProperties.to[Set]
      ).to[Seq].sortBy(_.uuid),
      scalarDataProperties = (
        t1.scalarDataProperties.to[Set] ++ 
        t2.scalarDataProperties.to[Set]
      ).to[Seq].sortBy(_.uuid),
      structuredDataProperties = (
        t1.structuredDataProperties.to[Set] ++ 
        t2.structuredDataProperties.to[Set]
      ).to[Seq].sortBy(_.uuid),
      reifiedRelationships = (
        t1.reifiedRelationships.to[Set] ++ 
        t2.reifiedRelationships.to[Set]
      ).to[Seq].sortBy(_.uuid),
      unreifiedRelationships = (
        t1.unreifiedRelationships.to[Set] ++ 
        t2.unreifiedRelationships.to[Set]
      ).to[Seq].sortBy(_.uuid),
      chainRules = (
        t1.chainRules.to[Set] ++ 
        t2.chainRules.to[Set]
      ).to[Seq].sortBy(_.uuid),
      ruleBodySegments = (
        t1.ruleBodySegments.to[Set] ++ 
        t2.ruleBodySegments.to[Set]
      ).to[Seq].sortBy(_.uuid),
      aspectPredicates = (
        t1.aspectPredicates.to[Set] ++ 
        t2.aspectPredicates.to[Set]
      ).to[Seq].sortBy(_.uuid),
      conceptPredicates = (
        t1.conceptPredicates.to[Set] ++ 
        t2.conceptPredicates.to[Set]
      ).to[Seq].sortBy(_.uuid),
      reifiedRelationshipPredicates = (
        t1.reifiedRelationshipPredicates.to[Set] ++ 
        t2.reifiedRelationshipPredicates.to[Set]
      ).to[Seq].sortBy(_.uuid),
      reifiedRelationshipPropertyPredicates = (
        t1.reifiedRelationshipPropertyPredicates.to[Set] ++ 
        t2.reifiedRelationshipPropertyPredicates.to[Set]
      ).to[Seq].sortBy(_.uuid),
      reifiedRelationshipSourcePropertyPredicates = (
        t1.reifiedRelationshipSourcePropertyPredicates.to[Set] ++ 
        t2.reifiedRelationshipSourcePropertyPredicates.to[Set]
      ).to[Seq].sortBy(_.uuid),
      reifiedRelationshipTargetPropertyPredicates = (
        t1.reifiedRelationshipTargetPropertyPredicates.to[Set] ++ 
        t2.reifiedRelationshipTargetPropertyPredicates.to[Set]
      ).to[Seq].sortBy(_.uuid),
      unreifiedRelationshipPropertyPredicates = (
        t1.unreifiedRelationshipPropertyPredicates.to[Set] ++ 
        t2.unreifiedRelationshipPropertyPredicates.to[Set]
      ).to[Seq].sortBy(_.uuid),
      reifiedRelationshipInversePropertyPredicates = (
        t1.reifiedRelationshipInversePropertyPredicates.to[Set] ++ 
        t2.reifiedRelationshipInversePropertyPredicates.to[Set]
      ).to[Seq].sortBy(_.uuid),
      reifiedRelationshipSourceInversePropertyPredicates = (
        t1.reifiedRelationshipSourceInversePropertyPredicates.to[Set] ++ 
        t2.reifiedRelationshipSourceInversePropertyPredicates.to[Set]
      ).to[Seq].sortBy(_.uuid),
      reifiedRelationshipTargetInversePropertyPredicates = (
        t1.reifiedRelationshipTargetInversePropertyPredicates.to[Set] ++ 
        t2.reifiedRelationshipTargetInversePropertyPredicates.to[Set]
      ).to[Seq].sortBy(_.uuid),
      unreifiedRelationshipInversePropertyPredicates = (
        t1.unreifiedRelationshipInversePropertyPredicates.to[Set] ++ 
        t2.unreifiedRelationshipInversePropertyPredicates.to[Set]
      ).to[Seq].sortBy(_.uuid),
      entityExistentialRestrictionAxioms = (
        t1.entityExistentialRestrictionAxioms.to[Set] ++ 
        t2.entityExistentialRestrictionAxioms.to[Set]
      ).to[Seq].sortBy(_.uuid),
      entityUniversalRestrictionAxioms = (
        t1.entityUniversalRestrictionAxioms.to[Set] ++ 
        t2.entityUniversalRestrictionAxioms.to[Set]
      ).to[Seq].sortBy(_.uuid),
      entityScalarDataPropertyExistentialRestrictionAxioms = (
        t1.entityScalarDataPropertyExistentialRestrictionAxioms.to[Set] ++ 
        t2.entityScalarDataPropertyExistentialRestrictionAxioms.to[Set]
      ).to[Seq].sortBy(_.uuid),
      entityScalarDataPropertyParticularRestrictionAxioms = (
        t1.entityScalarDataPropertyParticularRestrictionAxioms.to[Set] ++ 
        t2.entityScalarDataPropertyParticularRestrictionAxioms.to[Set]
      ).to[Seq].sortBy(_.uuid),
      entityScalarDataPropertyUniversalRestrictionAxioms = (
        t1.entityScalarDataPropertyUniversalRestrictionAxioms.to[Set] ++ 
        t2.entityScalarDataPropertyUniversalRestrictionAxioms.to[Set]
      ).to[Seq].sortBy(_.uuid),
      entityStructuredDataPropertyParticularRestrictionAxioms = (
        t1.entityStructuredDataPropertyParticularRestrictionAxioms.to[Set] ++ 
        t2.entityStructuredDataPropertyParticularRestrictionAxioms.to[Set]
      ).to[Seq].sortBy(_.uuid),
      restrictionStructuredDataPropertyTuples = (
        t1.restrictionStructuredDataPropertyTuples.to[Set] ++ 
        t2.restrictionStructuredDataPropertyTuples.to[Set]
      ).to[Seq].sortBy(_.uuid),
      restrictionScalarDataPropertyValues = (
        t1.restrictionScalarDataPropertyValues.to[Set] ++ 
        t2.restrictionScalarDataPropertyValues.to[Set]
      ).to[Seq].sortBy(_.uuid),
      aspectSpecializationAxioms = (
        t1.aspectSpecializationAxioms.to[Set] ++ 
        t2.aspectSpecializationAxioms.to[Set]
      ).to[Seq].sortBy(_.uuid),
      conceptSpecializationAxioms = (
        t1.conceptSpecializationAxioms.to[Set] ++ 
        t2.conceptSpecializationAxioms.to[Set]
      ).to[Seq].sortBy(_.uuid),
      reifiedRelationshipSpecializationAxioms = (
        t1.reifiedRelationshipSpecializationAxioms.to[Set] ++ 
        t2.reifiedRelationshipSpecializationAxioms.to[Set]
      ).to[Seq].sortBy(_.uuid),
      subDataPropertyOfAxioms = (
        t1.subDataPropertyOfAxioms.to[Set] ++ 
        t2.subDataPropertyOfAxioms.to[Set]
      ).to[Seq].sortBy(_.uuid),
      subObjectPropertyOfAxioms = (
        t1.subObjectPropertyOfAxioms.to[Set] ++ 
        t2.subObjectPropertyOfAxioms.to[Set]
      ).to[Seq].sortBy(_.uuid),
      rootConceptTaxonomyAxioms = (
        t1.rootConceptTaxonomyAxioms.to[Set] ++ 
        t2.rootConceptTaxonomyAxioms.to[Set]
      ).to[Seq].sortBy(_.uuid),
      anonymousConceptUnionAxioms = (
        t1.anonymousConceptUnionAxioms.to[Set] ++ 
        t2.anonymousConceptUnionAxioms.to[Set]
      ).to[Seq].sortBy(_.uuid),
      specificDisjointConceptAxioms = (
        t1.specificDisjointConceptAxioms.to[Set] ++ 
        t2.specificDisjointConceptAxioms.to[Set]
      ).to[Seq].sortBy(_.uuid),
      conceptInstances = (
        t1.conceptInstances.to[Set] ++ 
        t2.conceptInstances.to[Set]
      ).to[Seq].sortBy(_.uuid),
      reifiedRelationshipInstances = (
        t1.reifiedRelationshipInstances.to[Set] ++ 
        t2.reifiedRelationshipInstances.to[Set]
      ).to[Seq].sortBy(_.uuid),
      reifiedRelationshipInstanceDomains = (
        t1.reifiedRelationshipInstanceDomains.to[Set] ++ 
        t2.reifiedRelationshipInstanceDomains.to[Set]
      ).to[Seq].sortBy(_.uuid),
      reifiedRelationshipInstanceRanges = (
        t1.reifiedRelationshipInstanceRanges.to[Set] ++ 
        t2.reifiedRelationshipInstanceRanges.to[Set]
      ).to[Seq].sortBy(_.uuid),
      unreifiedRelationshipInstanceTuples = (
        t1.unreifiedRelationshipInstanceTuples.to[Set] ++ 
        t2.unreifiedRelationshipInstanceTuples.to[Set]
      ).to[Seq].sortBy(_.uuid),
      singletonInstanceStructuredDataPropertyValues = (
        t1.singletonInstanceStructuredDataPropertyValues.to[Set] ++ 
        t2.singletonInstanceStructuredDataPropertyValues.to[Set]
      ).to[Seq].sortBy(_.uuid),
      singletonInstanceScalarDataPropertyValues = (
        t1.singletonInstanceScalarDataPropertyValues.to[Set] ++ 
        t2.singletonInstanceScalarDataPropertyValues.to[Set]
      ).to[Seq].sortBy(_.uuid),
      structuredDataPropertyTuples = (
        t1.structuredDataPropertyTuples.to[Set] ++ 
        t2.structuredDataPropertyTuples.to[Set]
      ).to[Seq].sortBy(_.uuid),
      scalarDataPropertyValues = (
        t1.scalarDataPropertyValues.to[Set] ++ 
        t2.scalarDataPropertyValues.to[Set]
      ).to[Seq].sortBy(_.uuid),
      annotationPropertyValues = (
        t1.annotationPropertyValues.to[Set] ++ 
        t2.annotationPropertyValues.to[Set]
      ).to[Seq].sortBy(_.uuid))
  
  def readZipArchive
  (zipFile: ZipFile)
  (tables: OMLSpecificationTables, ze: ZipArchiveEntry)
  : OMLSpecificationTables
  = {
  	val is = zipFile.getInputStream(ze)
  	ze.getName match {
  	  case TerminologyGraphHelper.TABLE_JSON_FILENAME =>
  	    tables.readTerminologyGraphs(is)
  	  case BundleHelper.TABLE_JSON_FILENAME =>
  	    tables.readBundles(is)
  	  case DescriptionBoxHelper.TABLE_JSON_FILENAME =>
  	    tables.readDescriptionBoxes(is)
  	  case AnnotationPropertyHelper.TABLE_JSON_FILENAME =>
  	    tables.readAnnotationProperties(is)
  	  case AspectHelper.TABLE_JSON_FILENAME =>
  	    tables.readAspects(is)
  	  case ConceptHelper.TABLE_JSON_FILENAME =>
  	    tables.readConcepts(is)
  	  case ScalarHelper.TABLE_JSON_FILENAME =>
  	    tables.readScalars(is)
  	  case StructureHelper.TABLE_JSON_FILENAME =>
  	    tables.readStructures(is)
  	  case ConceptDesignationTerminologyAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readConceptDesignationTerminologyAxioms(is)
  	  case TerminologyExtensionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readTerminologyExtensionAxioms(is)
  	  case TerminologyNestingAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readTerminologyNestingAxioms(is)
  	  case BundledTerminologyAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readBundledTerminologyAxioms(is)
  	  case DescriptionBoxExtendsClosedWorldDefinitionsHelper.TABLE_JSON_FILENAME =>
  	    tables.readDescriptionBoxExtendsClosedWorldDefinitions(is)
  	  case DescriptionBoxRefinementHelper.TABLE_JSON_FILENAME =>
  	    tables.readDescriptionBoxRefinements(is)
  	  case BinaryScalarRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readBinaryScalarRestrictions(is)
  	  case IRIScalarRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readIRIScalarRestrictions(is)
  	  case NumericScalarRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readNumericScalarRestrictions(is)
  	  case PlainLiteralScalarRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readPlainLiteralScalarRestrictions(is)
  	  case ScalarOneOfRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readScalarOneOfRestrictions(is)
  	  case ScalarOneOfLiteralAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readScalarOneOfLiteralAxioms(is)
  	  case StringScalarRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readStringScalarRestrictions(is)
  	  case SynonymScalarRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readSynonymScalarRestrictions(is)
  	  case TimeScalarRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readTimeScalarRestrictions(is)
  	  case EntityScalarDataPropertyHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityScalarDataProperties(is)
  	  case EntityStructuredDataPropertyHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityStructuredDataProperties(is)
  	  case ScalarDataPropertyHelper.TABLE_JSON_FILENAME =>
  	    tables.readScalarDataProperties(is)
  	  case StructuredDataPropertyHelper.TABLE_JSON_FILENAME =>
  	    tables.readStructuredDataProperties(is)
  	  case ReifiedRelationshipHelper.TABLE_JSON_FILENAME =>
  	    tables.readReifiedRelationships(is)
  	  case UnreifiedRelationshipHelper.TABLE_JSON_FILENAME =>
  	    tables.readUnreifiedRelationships(is)
  	  case ChainRuleHelper.TABLE_JSON_FILENAME =>
  	    tables.readChainRules(is)
  	  case RuleBodySegmentHelper.TABLE_JSON_FILENAME =>
  	    tables.readRuleBodySegments(is)
  	  case AspectPredicateHelper.TABLE_JSON_FILENAME =>
  	    tables.readAspectPredicates(is)
  	  case ConceptPredicateHelper.TABLE_JSON_FILENAME =>
  	    tables.readConceptPredicates(is)
  	  case ReifiedRelationshipPredicateHelper.TABLE_JSON_FILENAME =>
  	    tables.readReifiedRelationshipPredicates(is)
  	  case ReifiedRelationshipPropertyPredicateHelper.TABLE_JSON_FILENAME =>
  	    tables.readReifiedRelationshipPropertyPredicates(is)
  	  case ReifiedRelationshipSourcePropertyPredicateHelper.TABLE_JSON_FILENAME =>
  	    tables.readReifiedRelationshipSourcePropertyPredicates(is)
  	  case ReifiedRelationshipTargetPropertyPredicateHelper.TABLE_JSON_FILENAME =>
  	    tables.readReifiedRelationshipTargetPropertyPredicates(is)
  	  case UnreifiedRelationshipPropertyPredicateHelper.TABLE_JSON_FILENAME =>
  	    tables.readUnreifiedRelationshipPropertyPredicates(is)
  	  case ReifiedRelationshipInversePropertyPredicateHelper.TABLE_JSON_FILENAME =>
  	    tables.readReifiedRelationshipInversePropertyPredicates(is)
  	  case ReifiedRelationshipSourceInversePropertyPredicateHelper.TABLE_JSON_FILENAME =>
  	    tables.readReifiedRelationshipSourceInversePropertyPredicates(is)
  	  case ReifiedRelationshipTargetInversePropertyPredicateHelper.TABLE_JSON_FILENAME =>
  	    tables.readReifiedRelationshipTargetInversePropertyPredicates(is)
  	  case UnreifiedRelationshipInversePropertyPredicateHelper.TABLE_JSON_FILENAME =>
  	    tables.readUnreifiedRelationshipInversePropertyPredicates(is)
  	  case EntityExistentialRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityExistentialRestrictionAxioms(is)
  	  case EntityUniversalRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityUniversalRestrictionAxioms(is)
  	  case EntityScalarDataPropertyExistentialRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityScalarDataPropertyExistentialRestrictionAxioms(is)
  	  case EntityScalarDataPropertyParticularRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityScalarDataPropertyParticularRestrictionAxioms(is)
  	  case EntityScalarDataPropertyUniversalRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityScalarDataPropertyUniversalRestrictionAxioms(is)
  	  case EntityStructuredDataPropertyParticularRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityStructuredDataPropertyParticularRestrictionAxioms(is)
  	  case RestrictionStructuredDataPropertyTupleHelper.TABLE_JSON_FILENAME =>
  	    tables.readRestrictionStructuredDataPropertyTuples(is)
  	  case RestrictionScalarDataPropertyValueHelper.TABLE_JSON_FILENAME =>
  	    tables.readRestrictionScalarDataPropertyValues(is)
  	  case AspectSpecializationAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readAspectSpecializationAxioms(is)
  	  case ConceptSpecializationAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readConceptSpecializationAxioms(is)
  	  case ReifiedRelationshipSpecializationAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readReifiedRelationshipSpecializationAxioms(is)
  	  case SubDataPropertyOfAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readSubDataPropertyOfAxioms(is)
  	  case SubObjectPropertyOfAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readSubObjectPropertyOfAxioms(is)
  	  case RootConceptTaxonomyAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readRootConceptTaxonomyAxioms(is)
  	  case AnonymousConceptUnionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readAnonymousConceptUnionAxioms(is)
  	  case SpecificDisjointConceptAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readSpecificDisjointConceptAxioms(is)
  	  case ConceptInstanceHelper.TABLE_JSON_FILENAME =>
  	    tables.readConceptInstances(is)
  	  case ReifiedRelationshipInstanceHelper.TABLE_JSON_FILENAME =>
  	    tables.readReifiedRelationshipInstances(is)
  	  case ReifiedRelationshipInstanceDomainHelper.TABLE_JSON_FILENAME =>
  	    tables.readReifiedRelationshipInstanceDomains(is)
  	  case ReifiedRelationshipInstanceRangeHelper.TABLE_JSON_FILENAME =>
  	    tables.readReifiedRelationshipInstanceRanges(is)
  	  case UnreifiedRelationshipInstanceTupleHelper.TABLE_JSON_FILENAME =>
  	    tables.readUnreifiedRelationshipInstanceTuples(is)
  	  case SingletonInstanceStructuredDataPropertyValueHelper.TABLE_JSON_FILENAME =>
  	    tables.readSingletonInstanceStructuredDataPropertyValues(is)
  	  case SingletonInstanceScalarDataPropertyValueHelper.TABLE_JSON_FILENAME =>
  	    tables.readSingletonInstanceScalarDataPropertyValues(is)
  	  case StructuredDataPropertyTupleHelper.TABLE_JSON_FILENAME =>
  	    tables.readStructuredDataPropertyTuples(is)
  	  case ScalarDataPropertyValueHelper.TABLE_JSON_FILENAME =>
  	    tables.readScalarDataPropertyValues(is)
  	  case AnnotationPropertyValueHelper.TABLE_JSON_FILENAME =>
  	    tables.readAnnotationPropertyValues(is)
    }
  }
  
  def saveOMLSpecificationTables
  (tables: OMLSpecificationTables,
   omlSchemaJsonZipFile: File)
  : Try[Unit]
  = nonFatalCatch[Try[Unit]]
    .withApply {
      (cause: java.lang.Throwable) =>
        cause.fillInStackTrace()
        Failure(cause)
    }
    .apply {
  	  omlSchemaJsonZipFile.getParentFile.mkdirs()
  	  
  	  // @see http://www.oracle.com/technetwork/articles/java/compress-1565076.html
  	  val fos = new java.io.FileOutputStream(omlSchemaJsonZipFile)
  	  val bos = new java.io.BufferedOutputStream(fos, 100000)
  	  val cos = new java.util.zip.CheckedOutputStream(bos, new java.util.zip.Adler32())
  	  val zos = new java.util.zip.ZipOutputStream(new java.io.BufferedOutputStream(cos))
  
  	  zos.setMethod(java.util.zip.ZipOutputStream.DEFLATED)
  
      zos.putNextEntry(new java.util.zip.ZipEntry(TerminologyGraphHelper.TABLE_JSON_FILENAME))
      tables.terminologyGraphs.foreach { t =>
         val line = TerminologyGraphHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(BundleHelper.TABLE_JSON_FILENAME))
      tables.bundles.foreach { t =>
         val line = BundleHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(DescriptionBoxHelper.TABLE_JSON_FILENAME))
      tables.descriptionBoxes.foreach { t =>
         val line = DescriptionBoxHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(AnnotationPropertyHelper.TABLE_JSON_FILENAME))
      tables.annotationProperties.foreach { t =>
         val line = AnnotationPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(AspectHelper.TABLE_JSON_FILENAME))
      tables.aspects.foreach { t =>
         val line = AspectHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ConceptHelper.TABLE_JSON_FILENAME))
      tables.concepts.foreach { t =>
         val line = ConceptHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ScalarHelper.TABLE_JSON_FILENAME))
      tables.scalars.foreach { t =>
         val line = ScalarHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(StructureHelper.TABLE_JSON_FILENAME))
      tables.structures.foreach { t =>
         val line = StructureHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ConceptDesignationTerminologyAxiomHelper.TABLE_JSON_FILENAME))
      tables.conceptDesignationTerminologyAxioms.foreach { t =>
         val line = ConceptDesignationTerminologyAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(TerminologyExtensionAxiomHelper.TABLE_JSON_FILENAME))
      tables.terminologyExtensionAxioms.foreach { t =>
         val line = TerminologyExtensionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(TerminologyNestingAxiomHelper.TABLE_JSON_FILENAME))
      tables.terminologyNestingAxioms.foreach { t =>
         val line = TerminologyNestingAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(BundledTerminologyAxiomHelper.TABLE_JSON_FILENAME))
      tables.bundledTerminologyAxioms.foreach { t =>
         val line = BundledTerminologyAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(DescriptionBoxExtendsClosedWorldDefinitionsHelper.TABLE_JSON_FILENAME))
      tables.descriptionBoxExtendsClosedWorldDefinitions.foreach { t =>
         val line = DescriptionBoxExtendsClosedWorldDefinitionsHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(DescriptionBoxRefinementHelper.TABLE_JSON_FILENAME))
      tables.descriptionBoxRefinements.foreach { t =>
         val line = DescriptionBoxRefinementHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(BinaryScalarRestrictionHelper.TABLE_JSON_FILENAME))
      tables.binaryScalarRestrictions.foreach { t =>
         val line = BinaryScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(IRIScalarRestrictionHelper.TABLE_JSON_FILENAME))
      tables.iriScalarRestrictions.foreach { t =>
         val line = IRIScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(NumericScalarRestrictionHelper.TABLE_JSON_FILENAME))
      tables.numericScalarRestrictions.foreach { t =>
         val line = NumericScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(PlainLiteralScalarRestrictionHelper.TABLE_JSON_FILENAME))
      tables.plainLiteralScalarRestrictions.foreach { t =>
         val line = PlainLiteralScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ScalarOneOfRestrictionHelper.TABLE_JSON_FILENAME))
      tables.scalarOneOfRestrictions.foreach { t =>
         val line = ScalarOneOfRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ScalarOneOfLiteralAxiomHelper.TABLE_JSON_FILENAME))
      tables.scalarOneOfLiteralAxioms.foreach { t =>
         val line = ScalarOneOfLiteralAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(StringScalarRestrictionHelper.TABLE_JSON_FILENAME))
      tables.stringScalarRestrictions.foreach { t =>
         val line = StringScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(SynonymScalarRestrictionHelper.TABLE_JSON_FILENAME))
      tables.synonymScalarRestrictions.foreach { t =>
         val line = SynonymScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(TimeScalarRestrictionHelper.TABLE_JSON_FILENAME))
      tables.timeScalarRestrictions.foreach { t =>
         val line = TimeScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityScalarDataPropertyHelper.TABLE_JSON_FILENAME))
      tables.entityScalarDataProperties.foreach { t =>
         val line = EntityScalarDataPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityStructuredDataPropertyHelper.TABLE_JSON_FILENAME))
      tables.entityStructuredDataProperties.foreach { t =>
         val line = EntityStructuredDataPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ScalarDataPropertyHelper.TABLE_JSON_FILENAME))
      tables.scalarDataProperties.foreach { t =>
         val line = ScalarDataPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(StructuredDataPropertyHelper.TABLE_JSON_FILENAME))
      tables.structuredDataProperties.foreach { t =>
         val line = StructuredDataPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ReifiedRelationshipHelper.TABLE_JSON_FILENAME))
      tables.reifiedRelationships.foreach { t =>
         val line = ReifiedRelationshipHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(UnreifiedRelationshipHelper.TABLE_JSON_FILENAME))
      tables.unreifiedRelationships.foreach { t =>
         val line = UnreifiedRelationshipHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ChainRuleHelper.TABLE_JSON_FILENAME))
      tables.chainRules.foreach { t =>
         val line = ChainRuleHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(RuleBodySegmentHelper.TABLE_JSON_FILENAME))
      tables.ruleBodySegments.foreach { t =>
         val line = RuleBodySegmentHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(AspectPredicateHelper.TABLE_JSON_FILENAME))
      tables.aspectPredicates.foreach { t =>
         val line = AspectPredicateHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ConceptPredicateHelper.TABLE_JSON_FILENAME))
      tables.conceptPredicates.foreach { t =>
         val line = ConceptPredicateHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ReifiedRelationshipPredicateHelper.TABLE_JSON_FILENAME))
      tables.reifiedRelationshipPredicates.foreach { t =>
         val line = ReifiedRelationshipPredicateHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ReifiedRelationshipPropertyPredicateHelper.TABLE_JSON_FILENAME))
      tables.reifiedRelationshipPropertyPredicates.foreach { t =>
         val line = ReifiedRelationshipPropertyPredicateHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ReifiedRelationshipSourcePropertyPredicateHelper.TABLE_JSON_FILENAME))
      tables.reifiedRelationshipSourcePropertyPredicates.foreach { t =>
         val line = ReifiedRelationshipSourcePropertyPredicateHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ReifiedRelationshipTargetPropertyPredicateHelper.TABLE_JSON_FILENAME))
      tables.reifiedRelationshipTargetPropertyPredicates.foreach { t =>
         val line = ReifiedRelationshipTargetPropertyPredicateHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(UnreifiedRelationshipPropertyPredicateHelper.TABLE_JSON_FILENAME))
      tables.unreifiedRelationshipPropertyPredicates.foreach { t =>
         val line = UnreifiedRelationshipPropertyPredicateHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ReifiedRelationshipInversePropertyPredicateHelper.TABLE_JSON_FILENAME))
      tables.reifiedRelationshipInversePropertyPredicates.foreach { t =>
         val line = ReifiedRelationshipInversePropertyPredicateHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ReifiedRelationshipSourceInversePropertyPredicateHelper.TABLE_JSON_FILENAME))
      tables.reifiedRelationshipSourceInversePropertyPredicates.foreach { t =>
         val line = ReifiedRelationshipSourceInversePropertyPredicateHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ReifiedRelationshipTargetInversePropertyPredicateHelper.TABLE_JSON_FILENAME))
      tables.reifiedRelationshipTargetInversePropertyPredicates.foreach { t =>
         val line = ReifiedRelationshipTargetInversePropertyPredicateHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(UnreifiedRelationshipInversePropertyPredicateHelper.TABLE_JSON_FILENAME))
      tables.unreifiedRelationshipInversePropertyPredicates.foreach { t =>
         val line = UnreifiedRelationshipInversePropertyPredicateHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityExistentialRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.entityExistentialRestrictionAxioms.foreach { t =>
         val line = EntityExistentialRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityUniversalRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.entityUniversalRestrictionAxioms.foreach { t =>
         val line = EntityUniversalRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityScalarDataPropertyExistentialRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.entityScalarDataPropertyExistentialRestrictionAxioms.foreach { t =>
         val line = EntityScalarDataPropertyExistentialRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityScalarDataPropertyParticularRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.entityScalarDataPropertyParticularRestrictionAxioms.foreach { t =>
         val line = EntityScalarDataPropertyParticularRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityScalarDataPropertyUniversalRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.entityScalarDataPropertyUniversalRestrictionAxioms.foreach { t =>
         val line = EntityScalarDataPropertyUniversalRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityStructuredDataPropertyParticularRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.entityStructuredDataPropertyParticularRestrictionAxioms.foreach { t =>
         val line = EntityStructuredDataPropertyParticularRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(RestrictionStructuredDataPropertyTupleHelper.TABLE_JSON_FILENAME))
      tables.restrictionStructuredDataPropertyTuples.foreach { t =>
         val line = RestrictionStructuredDataPropertyTupleHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(RestrictionScalarDataPropertyValueHelper.TABLE_JSON_FILENAME))
      tables.restrictionScalarDataPropertyValues.foreach { t =>
         val line = RestrictionScalarDataPropertyValueHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(AspectSpecializationAxiomHelper.TABLE_JSON_FILENAME))
      tables.aspectSpecializationAxioms.foreach { t =>
         val line = AspectSpecializationAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ConceptSpecializationAxiomHelper.TABLE_JSON_FILENAME))
      tables.conceptSpecializationAxioms.foreach { t =>
         val line = ConceptSpecializationAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ReifiedRelationshipSpecializationAxiomHelper.TABLE_JSON_FILENAME))
      tables.reifiedRelationshipSpecializationAxioms.foreach { t =>
         val line = ReifiedRelationshipSpecializationAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(SubDataPropertyOfAxiomHelper.TABLE_JSON_FILENAME))
      tables.subDataPropertyOfAxioms.foreach { t =>
         val line = SubDataPropertyOfAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(SubObjectPropertyOfAxiomHelper.TABLE_JSON_FILENAME))
      tables.subObjectPropertyOfAxioms.foreach { t =>
         val line = SubObjectPropertyOfAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(RootConceptTaxonomyAxiomHelper.TABLE_JSON_FILENAME))
      tables.rootConceptTaxonomyAxioms.foreach { t =>
         val line = RootConceptTaxonomyAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(AnonymousConceptUnionAxiomHelper.TABLE_JSON_FILENAME))
      tables.anonymousConceptUnionAxioms.foreach { t =>
         val line = AnonymousConceptUnionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(SpecificDisjointConceptAxiomHelper.TABLE_JSON_FILENAME))
      tables.specificDisjointConceptAxioms.foreach { t =>
         val line = SpecificDisjointConceptAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ConceptInstanceHelper.TABLE_JSON_FILENAME))
      tables.conceptInstances.foreach { t =>
         val line = ConceptInstanceHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ReifiedRelationshipInstanceHelper.TABLE_JSON_FILENAME))
      tables.reifiedRelationshipInstances.foreach { t =>
         val line = ReifiedRelationshipInstanceHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ReifiedRelationshipInstanceDomainHelper.TABLE_JSON_FILENAME))
      tables.reifiedRelationshipInstanceDomains.foreach { t =>
         val line = ReifiedRelationshipInstanceDomainHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ReifiedRelationshipInstanceRangeHelper.TABLE_JSON_FILENAME))
      tables.reifiedRelationshipInstanceRanges.foreach { t =>
         val line = ReifiedRelationshipInstanceRangeHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(UnreifiedRelationshipInstanceTupleHelper.TABLE_JSON_FILENAME))
      tables.unreifiedRelationshipInstanceTuples.foreach { t =>
         val line = UnreifiedRelationshipInstanceTupleHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(SingletonInstanceStructuredDataPropertyValueHelper.TABLE_JSON_FILENAME))
      tables.singletonInstanceStructuredDataPropertyValues.foreach { t =>
         val line = SingletonInstanceStructuredDataPropertyValueHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(SingletonInstanceScalarDataPropertyValueHelper.TABLE_JSON_FILENAME))
      tables.singletonInstanceScalarDataPropertyValues.foreach { t =>
         val line = SingletonInstanceScalarDataPropertyValueHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(StructuredDataPropertyTupleHelper.TABLE_JSON_FILENAME))
      tables.structuredDataPropertyTuples.foreach { t =>
         val line = StructuredDataPropertyTupleHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ScalarDataPropertyValueHelper.TABLE_JSON_FILENAME))
      tables.scalarDataPropertyValues.foreach { t =>
         val line = ScalarDataPropertyValueHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(AnnotationPropertyValueHelper.TABLE_JSON_FILENAME))
      tables.annotationPropertyValues.foreach { t =>
         val line = AnnotationPropertyValueHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      
      zos.close()
  	  Success(())
  	}

}
