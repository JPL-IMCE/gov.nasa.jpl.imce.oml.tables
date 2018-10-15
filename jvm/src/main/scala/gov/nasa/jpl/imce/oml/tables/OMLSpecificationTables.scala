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
import gov.nasa.jpl.imce.oml.parallelSort

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
  reifiedRelationshipRestrictions : Seq[ReifiedRelationshipRestriction] = Seq.empty,
  forwardProperties : Seq[ForwardProperty] = Seq.empty,
  inverseProperties : Seq[InverseProperty] = Seq.empty,
  unreifiedRelationships : Seq[UnreifiedRelationship] = Seq.empty,
  chainRules : Seq[ChainRule] = Seq.empty,
  ruleBodySegments : Seq[RuleBodySegment] = Seq.empty,
  segmentPredicates : Seq[SegmentPredicate] = Seq.empty,
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
  annotationPropertyValues : Seq[AnnotationPropertyValue] = Seq.empty,
  cardinalityRestrictedAspects : Seq[CardinalityRestrictedAspect] = Seq.empty,
  cardinalityRestrictedConcepts : Seq[CardinalityRestrictedConcept] = Seq.empty,
  cardinalityRestrictedReifiedRelationships : Seq[CardinalityRestrictedReifiedRelationship] = Seq.empty,
  instanceRelationshipEnumerationRestrictions : Seq[InstanceRelationshipEnumerationRestriction] = Seq.empty,
  instanceRelationshipExistentialRangeRestrictions : Seq[InstanceRelationshipExistentialRangeRestriction] = Seq.empty,
  instanceRelationshipOneOfRestrictions : Seq[InstanceRelationshipOneOfRestriction] = Seq.empty,
  instanceRelationshipUniversalRangeRestrictions : Seq[InstanceRelationshipUniversalRangeRestriction] = Seq.empty,
  instanceRelationshipValueRestrictions : Seq[InstanceRelationshipValueRestriction] = Seq.empty
)
{
  def readTerminologyGraphs(is: InputStream)
  : OMLSpecificationTables
  = copy(terminologyGraphs = 
    parallelSort.parSortBy((terminologyGraphs.to[Set] ++ 
     readJSonTable(is, TerminologyGraphHelper.fromJSON).to[Set]
    ).to[Seq], (a: TerminologyGraph) => a.uuid))
  def readBundles(is: InputStream)
  : OMLSpecificationTables
  = copy(bundles = 
    parallelSort.parSortBy((bundles.to[Set] ++ 
     readJSonTable(is, BundleHelper.fromJSON).to[Set]
    ).to[Seq], (a: Bundle) => a.uuid))
  def readDescriptionBoxes(is: InputStream)
  : OMLSpecificationTables
  = copy(descriptionBoxes = 
    parallelSort.parSortBy((descriptionBoxes.to[Set] ++ 
     readJSonTable(is, DescriptionBoxHelper.fromJSON).to[Set]
    ).to[Seq], (a: DescriptionBox) => a.uuid))
  def readAnnotationProperties(is: InputStream)
  : OMLSpecificationTables
  = copy(annotationProperties = 
    parallelSort.parSortBy((annotationProperties.to[Set] ++ 
     readJSonTable(is, AnnotationPropertyHelper.fromJSON).to[Set]
    ).to[Seq], (a: AnnotationProperty) => a.uuid))
  def readAspects(is: InputStream)
  : OMLSpecificationTables
  = copy(aspects = 
    parallelSort.parSortBy((aspects.to[Set] ++ 
     readJSonTable(is, AspectHelper.fromJSON).to[Set]
    ).to[Seq], (a: Aspect) => a.uuid))
  def readConcepts(is: InputStream)
  : OMLSpecificationTables
  = copy(concepts = 
    parallelSort.parSortBy((concepts.to[Set] ++ 
     readJSonTable(is, ConceptHelper.fromJSON).to[Set]
    ).to[Seq], (a: Concept) => a.uuid))
  def readScalars(is: InputStream)
  : OMLSpecificationTables
  = copy(scalars = 
    parallelSort.parSortBy((scalars.to[Set] ++ 
     readJSonTable(is, ScalarHelper.fromJSON).to[Set]
    ).to[Seq], (a: Scalar) => a.uuid))
  def readStructures(is: InputStream)
  : OMLSpecificationTables
  = copy(structures = 
    parallelSort.parSortBy((structures.to[Set] ++ 
     readJSonTable(is, StructureHelper.fromJSON).to[Set]
    ).to[Seq], (a: Structure) => a.uuid))
  def readConceptDesignationTerminologyAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(conceptDesignationTerminologyAxioms = 
    parallelSort.parSortBy((conceptDesignationTerminologyAxioms.to[Set] ++ 
     readJSonTable(is, ConceptDesignationTerminologyAxiomHelper.fromJSON).to[Set]
    ).to[Seq], (a: ConceptDesignationTerminologyAxiom) => a.uuid))
  def readTerminologyExtensionAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(terminologyExtensionAxioms = 
    parallelSort.parSortBy((terminologyExtensionAxioms.to[Set] ++ 
     readJSonTable(is, TerminologyExtensionAxiomHelper.fromJSON).to[Set]
    ).to[Seq], (a: TerminologyExtensionAxiom) => a.uuid))
  def readTerminologyNestingAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(terminologyNestingAxioms = 
    parallelSort.parSortBy((terminologyNestingAxioms.to[Set] ++ 
     readJSonTable(is, TerminologyNestingAxiomHelper.fromJSON).to[Set]
    ).to[Seq], (a: TerminologyNestingAxiom) => a.uuid))
  def readBundledTerminologyAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(bundledTerminologyAxioms = 
    parallelSort.parSortBy((bundledTerminologyAxioms.to[Set] ++ 
     readJSonTable(is, BundledTerminologyAxiomHelper.fromJSON).to[Set]
    ).to[Seq], (a: BundledTerminologyAxiom) => a.uuid))
  def readDescriptionBoxExtendsClosedWorldDefinitions(is: InputStream)
  : OMLSpecificationTables
  = copy(descriptionBoxExtendsClosedWorldDefinitions = 
    parallelSort.parSortBy((descriptionBoxExtendsClosedWorldDefinitions.to[Set] ++ 
     readJSonTable(is, DescriptionBoxExtendsClosedWorldDefinitionsHelper.fromJSON).to[Set]
    ).to[Seq], (a: DescriptionBoxExtendsClosedWorldDefinitions) => a.uuid))
  def readDescriptionBoxRefinements(is: InputStream)
  : OMLSpecificationTables
  = copy(descriptionBoxRefinements = 
    parallelSort.parSortBy((descriptionBoxRefinements.to[Set] ++ 
     readJSonTable(is, DescriptionBoxRefinementHelper.fromJSON).to[Set]
    ).to[Seq], (a: DescriptionBoxRefinement) => a.uuid))
  def readBinaryScalarRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(binaryScalarRestrictions = 
    parallelSort.parSortBy((binaryScalarRestrictions.to[Set] ++ 
     readJSonTable(is, BinaryScalarRestrictionHelper.fromJSON).to[Set]
    ).to[Seq], (a: BinaryScalarRestriction) => a.uuid))
  def readIRIScalarRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(iriScalarRestrictions = 
    parallelSort.parSortBy((iriScalarRestrictions.to[Set] ++ 
     readJSonTable(is, IRIScalarRestrictionHelper.fromJSON).to[Set]
    ).to[Seq], (a: IRIScalarRestriction) => a.uuid))
  def readNumericScalarRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(numericScalarRestrictions = 
    parallelSort.parSortBy((numericScalarRestrictions.to[Set] ++ 
     readJSonTable(is, NumericScalarRestrictionHelper.fromJSON).to[Set]
    ).to[Seq], (a: NumericScalarRestriction) => a.uuid))
  def readPlainLiteralScalarRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(plainLiteralScalarRestrictions = 
    parallelSort.parSortBy((plainLiteralScalarRestrictions.to[Set] ++ 
     readJSonTable(is, PlainLiteralScalarRestrictionHelper.fromJSON).to[Set]
    ).to[Seq], (a: PlainLiteralScalarRestriction) => a.uuid))
  def readScalarOneOfRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(scalarOneOfRestrictions = 
    parallelSort.parSortBy((scalarOneOfRestrictions.to[Set] ++ 
     readJSonTable(is, ScalarOneOfRestrictionHelper.fromJSON).to[Set]
    ).to[Seq], (a: ScalarOneOfRestriction) => a.uuid))
  def readScalarOneOfLiteralAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(scalarOneOfLiteralAxioms = 
    parallelSort.parSortBy((scalarOneOfLiteralAxioms.to[Set] ++ 
     readJSonTable(is, ScalarOneOfLiteralAxiomHelper.fromJSON).to[Set]
    ).to[Seq], (a: ScalarOneOfLiteralAxiom) => a.uuid))
  def readStringScalarRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(stringScalarRestrictions = 
    parallelSort.parSortBy((stringScalarRestrictions.to[Set] ++ 
     readJSonTable(is, StringScalarRestrictionHelper.fromJSON).to[Set]
    ).to[Seq], (a: StringScalarRestriction) => a.uuid))
  def readSynonymScalarRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(synonymScalarRestrictions = 
    parallelSort.parSortBy((synonymScalarRestrictions.to[Set] ++ 
     readJSonTable(is, SynonymScalarRestrictionHelper.fromJSON).to[Set]
    ).to[Seq], (a: SynonymScalarRestriction) => a.uuid))
  def readTimeScalarRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(timeScalarRestrictions = 
    parallelSort.parSortBy((timeScalarRestrictions.to[Set] ++ 
     readJSonTable(is, TimeScalarRestrictionHelper.fromJSON).to[Set]
    ).to[Seq], (a: TimeScalarRestriction) => a.uuid))
  def readEntityScalarDataProperties(is: InputStream)
  : OMLSpecificationTables
  = copy(entityScalarDataProperties = 
    parallelSort.parSortBy((entityScalarDataProperties.to[Set] ++ 
     readJSonTable(is, EntityScalarDataPropertyHelper.fromJSON).to[Set]
    ).to[Seq], (a: EntityScalarDataProperty) => a.uuid))
  def readEntityStructuredDataProperties(is: InputStream)
  : OMLSpecificationTables
  = copy(entityStructuredDataProperties = 
    parallelSort.parSortBy((entityStructuredDataProperties.to[Set] ++ 
     readJSonTable(is, EntityStructuredDataPropertyHelper.fromJSON).to[Set]
    ).to[Seq], (a: EntityStructuredDataProperty) => a.uuid))
  def readScalarDataProperties(is: InputStream)
  : OMLSpecificationTables
  = copy(scalarDataProperties = 
    parallelSort.parSortBy((scalarDataProperties.to[Set] ++ 
     readJSonTable(is, ScalarDataPropertyHelper.fromJSON).to[Set]
    ).to[Seq], (a: ScalarDataProperty) => a.uuid))
  def readStructuredDataProperties(is: InputStream)
  : OMLSpecificationTables
  = copy(structuredDataProperties = 
    parallelSort.parSortBy((structuredDataProperties.to[Set] ++ 
     readJSonTable(is, StructuredDataPropertyHelper.fromJSON).to[Set]
    ).to[Seq], (a: StructuredDataProperty) => a.uuid))
  def readReifiedRelationships(is: InputStream)
  : OMLSpecificationTables
  = copy(reifiedRelationships = 
    parallelSort.parSortBy((reifiedRelationships.to[Set] ++ 
     readJSonTable(is, ReifiedRelationshipHelper.fromJSON).to[Set]
    ).to[Seq], (a: ReifiedRelationship) => a.uuid))
  def readReifiedRelationshipRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(reifiedRelationshipRestrictions = 
    parallelSort.parSortBy((reifiedRelationshipRestrictions.to[Set] ++ 
     readJSonTable(is, ReifiedRelationshipRestrictionHelper.fromJSON).to[Set]
    ).to[Seq], (a: ReifiedRelationshipRestriction) => a.uuid))
  def readForwardProperties(is: InputStream)
  : OMLSpecificationTables
  = copy(forwardProperties = 
    parallelSort.parSortBy((forwardProperties.to[Set] ++ 
     readJSonTable(is, ForwardPropertyHelper.fromJSON).to[Set]
    ).to[Seq], (a: ForwardProperty) => a.uuid))
  def readInverseProperties(is: InputStream)
  : OMLSpecificationTables
  = copy(inverseProperties = 
    parallelSort.parSortBy((inverseProperties.to[Set] ++ 
     readJSonTable(is, InversePropertyHelper.fromJSON).to[Set]
    ).to[Seq], (a: InverseProperty) => a.uuid))
  def readUnreifiedRelationships(is: InputStream)
  : OMLSpecificationTables
  = copy(unreifiedRelationships = 
    parallelSort.parSortBy((unreifiedRelationships.to[Set] ++ 
     readJSonTable(is, UnreifiedRelationshipHelper.fromJSON).to[Set]
    ).to[Seq], (a: UnreifiedRelationship) => a.uuid))
  def readChainRules(is: InputStream)
  : OMLSpecificationTables
  = copy(chainRules = 
    parallelSort.parSortBy((chainRules.to[Set] ++ 
     readJSonTable(is, ChainRuleHelper.fromJSON).to[Set]
    ).to[Seq], (a: ChainRule) => a.uuid))
  def readRuleBodySegments(is: InputStream)
  : OMLSpecificationTables
  = copy(ruleBodySegments = 
    parallelSort.parSortBy((ruleBodySegments.to[Set] ++ 
     readJSonTable(is, RuleBodySegmentHelper.fromJSON).to[Set]
    ).to[Seq], (a: RuleBodySegment) => a.uuid))
  def readSegmentPredicates(is: InputStream)
  : OMLSpecificationTables
  = copy(segmentPredicates = 
    parallelSort.parSortBy((segmentPredicates.to[Set] ++ 
     readJSonTable(is, SegmentPredicateHelper.fromJSON).to[Set]
    ).to[Seq], (a: SegmentPredicate) => a.uuid))
  def readEntityExistentialRestrictionAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(entityExistentialRestrictionAxioms = 
    parallelSort.parSortBy((entityExistentialRestrictionAxioms.to[Set] ++ 
     readJSonTable(is, EntityExistentialRestrictionAxiomHelper.fromJSON).to[Set]
    ).to[Seq], (a: EntityExistentialRestrictionAxiom) => a.uuid))
  def readEntityUniversalRestrictionAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(entityUniversalRestrictionAxioms = 
    parallelSort.parSortBy((entityUniversalRestrictionAxioms.to[Set] ++ 
     readJSonTable(is, EntityUniversalRestrictionAxiomHelper.fromJSON).to[Set]
    ).to[Seq], (a: EntityUniversalRestrictionAxiom) => a.uuid))
  def readEntityScalarDataPropertyExistentialRestrictionAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(entityScalarDataPropertyExistentialRestrictionAxioms = 
    parallelSort.parSortBy((entityScalarDataPropertyExistentialRestrictionAxioms.to[Set] ++ 
     readJSonTable(is, EntityScalarDataPropertyExistentialRestrictionAxiomHelper.fromJSON).to[Set]
    ).to[Seq], (a: EntityScalarDataPropertyExistentialRestrictionAxiom) => a.uuid))
  def readEntityScalarDataPropertyParticularRestrictionAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(entityScalarDataPropertyParticularRestrictionAxioms = 
    parallelSort.parSortBy((entityScalarDataPropertyParticularRestrictionAxioms.to[Set] ++ 
     readJSonTable(is, EntityScalarDataPropertyParticularRestrictionAxiomHelper.fromJSON).to[Set]
    ).to[Seq], (a: EntityScalarDataPropertyParticularRestrictionAxiom) => a.uuid))
  def readEntityScalarDataPropertyUniversalRestrictionAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(entityScalarDataPropertyUniversalRestrictionAxioms = 
    parallelSort.parSortBy((entityScalarDataPropertyUniversalRestrictionAxioms.to[Set] ++ 
     readJSonTable(is, EntityScalarDataPropertyUniversalRestrictionAxiomHelper.fromJSON).to[Set]
    ).to[Seq], (a: EntityScalarDataPropertyUniversalRestrictionAxiom) => a.uuid))
  def readEntityStructuredDataPropertyParticularRestrictionAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(entityStructuredDataPropertyParticularRestrictionAxioms = 
    parallelSort.parSortBy((entityStructuredDataPropertyParticularRestrictionAxioms.to[Set] ++ 
     readJSonTable(is, EntityStructuredDataPropertyParticularRestrictionAxiomHelper.fromJSON).to[Set]
    ).to[Seq], (a: EntityStructuredDataPropertyParticularRestrictionAxiom) => a.uuid))
  def readRestrictionStructuredDataPropertyTuples(is: InputStream)
  : OMLSpecificationTables
  = copy(restrictionStructuredDataPropertyTuples = 
    parallelSort.parSortBy((restrictionStructuredDataPropertyTuples.to[Set] ++ 
     readJSonTable(is, RestrictionStructuredDataPropertyTupleHelper.fromJSON).to[Set]
    ).to[Seq], (a: RestrictionStructuredDataPropertyTuple) => a.uuid))
  def readRestrictionScalarDataPropertyValues(is: InputStream)
  : OMLSpecificationTables
  = copy(restrictionScalarDataPropertyValues = 
    parallelSort.parSortBy((restrictionScalarDataPropertyValues.to[Set] ++ 
     readJSonTable(is, RestrictionScalarDataPropertyValueHelper.fromJSON).to[Set]
    ).to[Seq], (a: RestrictionScalarDataPropertyValue) => a.uuid))
  def readAspectSpecializationAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(aspectSpecializationAxioms = 
    parallelSort.parSortBy((aspectSpecializationAxioms.to[Set] ++ 
     readJSonTable(is, AspectSpecializationAxiomHelper.fromJSON).to[Set]
    ).to[Seq], (a: AspectSpecializationAxiom) => a.uuid))
  def readConceptSpecializationAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(conceptSpecializationAxioms = 
    parallelSort.parSortBy((conceptSpecializationAxioms.to[Set] ++ 
     readJSonTable(is, ConceptSpecializationAxiomHelper.fromJSON).to[Set]
    ).to[Seq], (a: ConceptSpecializationAxiom) => a.uuid))
  def readReifiedRelationshipSpecializationAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(reifiedRelationshipSpecializationAxioms = 
    parallelSort.parSortBy((reifiedRelationshipSpecializationAxioms.to[Set] ++ 
     readJSonTable(is, ReifiedRelationshipSpecializationAxiomHelper.fromJSON).to[Set]
    ).to[Seq], (a: ReifiedRelationshipSpecializationAxiom) => a.uuid))
  def readSubDataPropertyOfAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(subDataPropertyOfAxioms = 
    parallelSort.parSortBy((subDataPropertyOfAxioms.to[Set] ++ 
     readJSonTable(is, SubDataPropertyOfAxiomHelper.fromJSON).to[Set]
    ).to[Seq], (a: SubDataPropertyOfAxiom) => a.uuid))
  def readSubObjectPropertyOfAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(subObjectPropertyOfAxioms = 
    parallelSort.parSortBy((subObjectPropertyOfAxioms.to[Set] ++ 
     readJSonTable(is, SubObjectPropertyOfAxiomHelper.fromJSON).to[Set]
    ).to[Seq], (a: SubObjectPropertyOfAxiom) => a.uuid))
  def readRootConceptTaxonomyAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(rootConceptTaxonomyAxioms = 
    parallelSort.parSortBy((rootConceptTaxonomyAxioms.to[Set] ++ 
     readJSonTable(is, RootConceptTaxonomyAxiomHelper.fromJSON).to[Set]
    ).to[Seq], (a: RootConceptTaxonomyAxiom) => a.uuid))
  def readAnonymousConceptUnionAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(anonymousConceptUnionAxioms = 
    parallelSort.parSortBy((anonymousConceptUnionAxioms.to[Set] ++ 
     readJSonTable(is, AnonymousConceptUnionAxiomHelper.fromJSON).to[Set]
    ).to[Seq], (a: AnonymousConceptUnionAxiom) => a.uuid))
  def readSpecificDisjointConceptAxioms(is: InputStream)
  : OMLSpecificationTables
  = copy(specificDisjointConceptAxioms = 
    parallelSort.parSortBy((specificDisjointConceptAxioms.to[Set] ++ 
     readJSonTable(is, SpecificDisjointConceptAxiomHelper.fromJSON).to[Set]
    ).to[Seq], (a: SpecificDisjointConceptAxiom) => a.uuid))
  def readConceptInstances(is: InputStream)
  : OMLSpecificationTables
  = copy(conceptInstances = 
    parallelSort.parSortBy((conceptInstances.to[Set] ++ 
     readJSonTable(is, ConceptInstanceHelper.fromJSON).to[Set]
    ).to[Seq], (a: ConceptInstance) => a.uuid))
  def readReifiedRelationshipInstances(is: InputStream)
  : OMLSpecificationTables
  = copy(reifiedRelationshipInstances = 
    parallelSort.parSortBy((reifiedRelationshipInstances.to[Set] ++ 
     readJSonTable(is, ReifiedRelationshipInstanceHelper.fromJSON).to[Set]
    ).to[Seq], (a: ReifiedRelationshipInstance) => a.uuid))
  def readReifiedRelationshipInstanceDomains(is: InputStream)
  : OMLSpecificationTables
  = copy(reifiedRelationshipInstanceDomains = 
    parallelSort.parSortBy((reifiedRelationshipInstanceDomains.to[Set] ++ 
     readJSonTable(is, ReifiedRelationshipInstanceDomainHelper.fromJSON).to[Set]
    ).to[Seq], (a: ReifiedRelationshipInstanceDomain) => a.uuid))
  def readReifiedRelationshipInstanceRanges(is: InputStream)
  : OMLSpecificationTables
  = copy(reifiedRelationshipInstanceRanges = 
    parallelSort.parSortBy((reifiedRelationshipInstanceRanges.to[Set] ++ 
     readJSonTable(is, ReifiedRelationshipInstanceRangeHelper.fromJSON).to[Set]
    ).to[Seq], (a: ReifiedRelationshipInstanceRange) => a.uuid))
  def readUnreifiedRelationshipInstanceTuples(is: InputStream)
  : OMLSpecificationTables
  = copy(unreifiedRelationshipInstanceTuples = 
    parallelSort.parSortBy((unreifiedRelationshipInstanceTuples.to[Set] ++ 
     readJSonTable(is, UnreifiedRelationshipInstanceTupleHelper.fromJSON).to[Set]
    ).to[Seq], (a: UnreifiedRelationshipInstanceTuple) => a.uuid))
  def readSingletonInstanceStructuredDataPropertyValues(is: InputStream)
  : OMLSpecificationTables
  = copy(singletonInstanceStructuredDataPropertyValues = 
    parallelSort.parSortBy((singletonInstanceStructuredDataPropertyValues.to[Set] ++ 
     readJSonTable(is, SingletonInstanceStructuredDataPropertyValueHelper.fromJSON).to[Set]
    ).to[Seq], (a: SingletonInstanceStructuredDataPropertyValue) => a.uuid))
  def readSingletonInstanceScalarDataPropertyValues(is: InputStream)
  : OMLSpecificationTables
  = copy(singletonInstanceScalarDataPropertyValues = 
    parallelSort.parSortBy((singletonInstanceScalarDataPropertyValues.to[Set] ++ 
     readJSonTable(is, SingletonInstanceScalarDataPropertyValueHelper.fromJSON).to[Set]
    ).to[Seq], (a: SingletonInstanceScalarDataPropertyValue) => a.uuid))
  def readStructuredDataPropertyTuples(is: InputStream)
  : OMLSpecificationTables
  = copy(structuredDataPropertyTuples = 
    parallelSort.parSortBy((structuredDataPropertyTuples.to[Set] ++ 
     readJSonTable(is, StructuredDataPropertyTupleHelper.fromJSON).to[Set]
    ).to[Seq], (a: StructuredDataPropertyTuple) => a.uuid))
  def readScalarDataPropertyValues(is: InputStream)
  : OMLSpecificationTables
  = copy(scalarDataPropertyValues = 
    parallelSort.parSortBy((scalarDataPropertyValues.to[Set] ++ 
     readJSonTable(is, ScalarDataPropertyValueHelper.fromJSON).to[Set]
    ).to[Seq], (a: ScalarDataPropertyValue) => a.uuid))
  def readAnnotationPropertyValues(is: InputStream)
  : OMLSpecificationTables
  = copy(annotationPropertyValues = 
    parallelSort.parSortBy((annotationPropertyValues.to[Set] ++ 
     readJSonTable(is, AnnotationPropertyValueHelper.fromJSON).to[Set]
    ).to[Seq], (a: AnnotationPropertyValue) => a.uuid))
  def readCardinalityRestrictedAspects(is: InputStream)
  : OMLSpecificationTables
  = copy(cardinalityRestrictedAspects = 
    parallelSort.parSortBy((cardinalityRestrictedAspects.to[Set] ++ 
     readJSonTable(is, CardinalityRestrictedAspectHelper.fromJSON).to[Set]
    ).to[Seq], (a: CardinalityRestrictedAspect) => a.uuid))
  def readCardinalityRestrictedConcepts(is: InputStream)
  : OMLSpecificationTables
  = copy(cardinalityRestrictedConcepts = 
    parallelSort.parSortBy((cardinalityRestrictedConcepts.to[Set] ++ 
     readJSonTable(is, CardinalityRestrictedConceptHelper.fromJSON).to[Set]
    ).to[Seq], (a: CardinalityRestrictedConcept) => a.uuid))
  def readCardinalityRestrictedReifiedRelationships(is: InputStream)
  : OMLSpecificationTables
  = copy(cardinalityRestrictedReifiedRelationships = 
    parallelSort.parSortBy((cardinalityRestrictedReifiedRelationships.to[Set] ++ 
     readJSonTable(is, CardinalityRestrictedReifiedRelationshipHelper.fromJSON).to[Set]
    ).to[Seq], (a: CardinalityRestrictedReifiedRelationship) => a.uuid))
  def readInstanceRelationshipEnumerationRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(instanceRelationshipEnumerationRestrictions = 
    parallelSort.parSortBy((instanceRelationshipEnumerationRestrictions.to[Set] ++ 
     readJSonTable(is, InstanceRelationshipEnumerationRestrictionHelper.fromJSON).to[Set]
    ).to[Seq], (a: InstanceRelationshipEnumerationRestriction) => a.uuid))
  def readInstanceRelationshipExistentialRangeRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(instanceRelationshipExistentialRangeRestrictions = 
    parallelSort.parSortBy((instanceRelationshipExistentialRangeRestrictions.to[Set] ++ 
     readJSonTable(is, InstanceRelationshipExistentialRangeRestrictionHelper.fromJSON).to[Set]
    ).to[Seq], (a: InstanceRelationshipExistentialRangeRestriction) => a.uuid))
  def readInstanceRelationshipOneOfRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(instanceRelationshipOneOfRestrictions = 
    parallelSort.parSortBy((instanceRelationshipOneOfRestrictions.to[Set] ++ 
     readJSonTable(is, InstanceRelationshipOneOfRestrictionHelper.fromJSON).to[Set]
    ).to[Seq], (a: InstanceRelationshipOneOfRestriction) => a.uuid))
  def readInstanceRelationshipUniversalRangeRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(instanceRelationshipUniversalRangeRestrictions = 
    parallelSort.parSortBy((instanceRelationshipUniversalRangeRestrictions.to[Set] ++ 
     readJSonTable(is, InstanceRelationshipUniversalRangeRestrictionHelper.fromJSON).to[Set]
    ).to[Seq], (a: InstanceRelationshipUniversalRangeRestriction) => a.uuid))
  def readInstanceRelationshipValueRestrictions(is: InputStream)
  : OMLSpecificationTables
  = copy(instanceRelationshipValueRestrictions = 
    parallelSort.parSortBy((instanceRelationshipValueRestrictions.to[Set] ++ 
     readJSonTable(is, InstanceRelationshipValueRestrictionHelper.fromJSON).to[Set]
    ).to[Seq], (a: InstanceRelationshipValueRestriction) => a.uuid))
  
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
    reifiedRelationshipRestrictions.isEmpty &&
    forwardProperties.isEmpty &&
    inverseProperties.isEmpty &&
    unreifiedRelationships.isEmpty &&
    chainRules.isEmpty &&
    ruleBodySegments.isEmpty &&
    segmentPredicates.isEmpty &&
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
    annotationPropertyValues.isEmpty &&
    cardinalityRestrictedAspects.isEmpty &&
    cardinalityRestrictedConcepts.isEmpty &&
    cardinalityRestrictedReifiedRelationships.isEmpty &&
    instanceRelationshipEnumerationRestrictions.isEmpty &&
    instanceRelationshipExistentialRangeRestrictions.isEmpty &&
    instanceRelationshipOneOfRestrictions.isEmpty &&
    instanceRelationshipUniversalRangeRestrictions.isEmpty &&
    instanceRelationshipValueRestrictions.isEmpty
  
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
    buff ++= showSeq("reifiedRelationshipRestrictions", reifiedRelationshipRestrictions)
    buff ++= showSeq("forwardProperties", forwardProperties)
    buff ++= showSeq("inverseProperties", inverseProperties)
    buff ++= showSeq("unreifiedRelationships", unreifiedRelationships)
    buff ++= showSeq("chainRules", chainRules)
    buff ++= showSeq("ruleBodySegments", ruleBodySegments)
    buff ++= showSeq("segmentPredicates", segmentPredicates)
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
    buff ++= showSeq("cardinalityRestrictedAspects", cardinalityRestrictedAspects)
    buff ++= showSeq("cardinalityRestrictedConcepts", cardinalityRestrictedConcepts)
    buff ++= showSeq("cardinalityRestrictedReifiedRelationships", cardinalityRestrictedReifiedRelationships)
    buff ++= showSeq("instanceRelationshipEnumerationRestrictions", instanceRelationshipEnumerationRestrictions)
    buff ++= showSeq("instanceRelationshipExistentialRangeRestrictions", instanceRelationshipExistentialRangeRestrictions)
    buff ++= showSeq("instanceRelationshipOneOfRestrictions", instanceRelationshipOneOfRestrictions)
    buff ++= showSeq("instanceRelationshipUniversalRangeRestrictions", instanceRelationshipUniversalRangeRestrictions)
    buff ++= showSeq("instanceRelationshipValueRestrictions", instanceRelationshipValueRestrictions)
  
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
      terminologyGraphs = parallelSort.parSortBy((
        t1.terminologyGraphs.to[Set] ++ 
        t2.terminologyGraphs.to[Set]
      ).to[Seq], (a: TerminologyGraph) => a.uuid),
      bundles = parallelSort.parSortBy((
        t1.bundles.to[Set] ++ 
        t2.bundles.to[Set]
      ).to[Seq], (a: Bundle) => a.uuid),
      descriptionBoxes = parallelSort.parSortBy((
        t1.descriptionBoxes.to[Set] ++ 
        t2.descriptionBoxes.to[Set]
      ).to[Seq], (a: DescriptionBox) => a.uuid),
      annotationProperties = parallelSort.parSortBy((
        t1.annotationProperties.to[Set] ++ 
        t2.annotationProperties.to[Set]
      ).to[Seq], (a: AnnotationProperty) => a.uuid),
      aspects = parallelSort.parSortBy((
        t1.aspects.to[Set] ++ 
        t2.aspects.to[Set]
      ).to[Seq], (a: Aspect) => a.uuid),
      concepts = parallelSort.parSortBy((
        t1.concepts.to[Set] ++ 
        t2.concepts.to[Set]
      ).to[Seq], (a: Concept) => a.uuid),
      scalars = parallelSort.parSortBy((
        t1.scalars.to[Set] ++ 
        t2.scalars.to[Set]
      ).to[Seq], (a: Scalar) => a.uuid),
      structures = parallelSort.parSortBy((
        t1.structures.to[Set] ++ 
        t2.structures.to[Set]
      ).to[Seq], (a: Structure) => a.uuid),
      conceptDesignationTerminologyAxioms = parallelSort.parSortBy((
        t1.conceptDesignationTerminologyAxioms.to[Set] ++ 
        t2.conceptDesignationTerminologyAxioms.to[Set]
      ).to[Seq], (a: ConceptDesignationTerminologyAxiom) => a.uuid),
      terminologyExtensionAxioms = parallelSort.parSortBy((
        t1.terminologyExtensionAxioms.to[Set] ++ 
        t2.terminologyExtensionAxioms.to[Set]
      ).to[Seq], (a: TerminologyExtensionAxiom) => a.uuid),
      terminologyNestingAxioms = parallelSort.parSortBy((
        t1.terminologyNestingAxioms.to[Set] ++ 
        t2.terminologyNestingAxioms.to[Set]
      ).to[Seq], (a: TerminologyNestingAxiom) => a.uuid),
      bundledTerminologyAxioms = parallelSort.parSortBy((
        t1.bundledTerminologyAxioms.to[Set] ++ 
        t2.bundledTerminologyAxioms.to[Set]
      ).to[Seq], (a: BundledTerminologyAxiom) => a.uuid),
      descriptionBoxExtendsClosedWorldDefinitions = parallelSort.parSortBy((
        t1.descriptionBoxExtendsClosedWorldDefinitions.to[Set] ++ 
        t2.descriptionBoxExtendsClosedWorldDefinitions.to[Set]
      ).to[Seq], (a: DescriptionBoxExtendsClosedWorldDefinitions) => a.uuid),
      descriptionBoxRefinements = parallelSort.parSortBy((
        t1.descriptionBoxRefinements.to[Set] ++ 
        t2.descriptionBoxRefinements.to[Set]
      ).to[Seq], (a: DescriptionBoxRefinement) => a.uuid),
      binaryScalarRestrictions = parallelSort.parSortBy((
        t1.binaryScalarRestrictions.to[Set] ++ 
        t2.binaryScalarRestrictions.to[Set]
      ).to[Seq], (a: BinaryScalarRestriction) => a.uuid),
      iriScalarRestrictions = parallelSort.parSortBy((
        t1.iriScalarRestrictions.to[Set] ++ 
        t2.iriScalarRestrictions.to[Set]
      ).to[Seq], (a: IRIScalarRestriction) => a.uuid),
      numericScalarRestrictions = parallelSort.parSortBy((
        t1.numericScalarRestrictions.to[Set] ++ 
        t2.numericScalarRestrictions.to[Set]
      ).to[Seq], (a: NumericScalarRestriction) => a.uuid),
      plainLiteralScalarRestrictions = parallelSort.parSortBy((
        t1.plainLiteralScalarRestrictions.to[Set] ++ 
        t2.plainLiteralScalarRestrictions.to[Set]
      ).to[Seq], (a: PlainLiteralScalarRestriction) => a.uuid),
      scalarOneOfRestrictions = parallelSort.parSortBy((
        t1.scalarOneOfRestrictions.to[Set] ++ 
        t2.scalarOneOfRestrictions.to[Set]
      ).to[Seq], (a: ScalarOneOfRestriction) => a.uuid),
      scalarOneOfLiteralAxioms = parallelSort.parSortBy((
        t1.scalarOneOfLiteralAxioms.to[Set] ++ 
        t2.scalarOneOfLiteralAxioms.to[Set]
      ).to[Seq], (a: ScalarOneOfLiteralAxiom) => a.uuid),
      stringScalarRestrictions = parallelSort.parSortBy((
        t1.stringScalarRestrictions.to[Set] ++ 
        t2.stringScalarRestrictions.to[Set]
      ).to[Seq], (a: StringScalarRestriction) => a.uuid),
      synonymScalarRestrictions = parallelSort.parSortBy((
        t1.synonymScalarRestrictions.to[Set] ++ 
        t2.synonymScalarRestrictions.to[Set]
      ).to[Seq], (a: SynonymScalarRestriction) => a.uuid),
      timeScalarRestrictions = parallelSort.parSortBy((
        t1.timeScalarRestrictions.to[Set] ++ 
        t2.timeScalarRestrictions.to[Set]
      ).to[Seq], (a: TimeScalarRestriction) => a.uuid),
      entityScalarDataProperties = parallelSort.parSortBy((
        t1.entityScalarDataProperties.to[Set] ++ 
        t2.entityScalarDataProperties.to[Set]
      ).to[Seq], (a: EntityScalarDataProperty) => a.uuid),
      entityStructuredDataProperties = parallelSort.parSortBy((
        t1.entityStructuredDataProperties.to[Set] ++ 
        t2.entityStructuredDataProperties.to[Set]
      ).to[Seq], (a: EntityStructuredDataProperty) => a.uuid),
      scalarDataProperties = parallelSort.parSortBy((
        t1.scalarDataProperties.to[Set] ++ 
        t2.scalarDataProperties.to[Set]
      ).to[Seq], (a: ScalarDataProperty) => a.uuid),
      structuredDataProperties = parallelSort.parSortBy((
        t1.structuredDataProperties.to[Set] ++ 
        t2.structuredDataProperties.to[Set]
      ).to[Seq], (a: StructuredDataProperty) => a.uuid),
      reifiedRelationships = parallelSort.parSortBy((
        t1.reifiedRelationships.to[Set] ++ 
        t2.reifiedRelationships.to[Set]
      ).to[Seq], (a: ReifiedRelationship) => a.uuid),
      reifiedRelationshipRestrictions = parallelSort.parSortBy((
        t1.reifiedRelationshipRestrictions.to[Set] ++ 
        t2.reifiedRelationshipRestrictions.to[Set]
      ).to[Seq], (a: ReifiedRelationshipRestriction) => a.uuid),
      forwardProperties = parallelSort.parSortBy((
        t1.forwardProperties.to[Set] ++ 
        t2.forwardProperties.to[Set]
      ).to[Seq], (a: ForwardProperty) => a.uuid),
      inverseProperties = parallelSort.parSortBy((
        t1.inverseProperties.to[Set] ++ 
        t2.inverseProperties.to[Set]
      ).to[Seq], (a: InverseProperty) => a.uuid),
      unreifiedRelationships = parallelSort.parSortBy((
        t1.unreifiedRelationships.to[Set] ++ 
        t2.unreifiedRelationships.to[Set]
      ).to[Seq], (a: UnreifiedRelationship) => a.uuid),
      chainRules = parallelSort.parSortBy((
        t1.chainRules.to[Set] ++ 
        t2.chainRules.to[Set]
      ).to[Seq], (a: ChainRule) => a.uuid),
      ruleBodySegments = parallelSort.parSortBy((
        t1.ruleBodySegments.to[Set] ++ 
        t2.ruleBodySegments.to[Set]
      ).to[Seq], (a: RuleBodySegment) => a.uuid),
      segmentPredicates = parallelSort.parSortBy((
        t1.segmentPredicates.to[Set] ++ 
        t2.segmentPredicates.to[Set]
      ).to[Seq], (a: SegmentPredicate) => a.uuid),
      entityExistentialRestrictionAxioms = parallelSort.parSortBy((
        t1.entityExistentialRestrictionAxioms.to[Set] ++ 
        t2.entityExistentialRestrictionAxioms.to[Set]
      ).to[Seq], (a: EntityExistentialRestrictionAxiom) => a.uuid),
      entityUniversalRestrictionAxioms = parallelSort.parSortBy((
        t1.entityUniversalRestrictionAxioms.to[Set] ++ 
        t2.entityUniversalRestrictionAxioms.to[Set]
      ).to[Seq], (a: EntityUniversalRestrictionAxiom) => a.uuid),
      entityScalarDataPropertyExistentialRestrictionAxioms = parallelSort.parSortBy((
        t1.entityScalarDataPropertyExistentialRestrictionAxioms.to[Set] ++ 
        t2.entityScalarDataPropertyExistentialRestrictionAxioms.to[Set]
      ).to[Seq], (a: EntityScalarDataPropertyExistentialRestrictionAxiom) => a.uuid),
      entityScalarDataPropertyParticularRestrictionAxioms = parallelSort.parSortBy((
        t1.entityScalarDataPropertyParticularRestrictionAxioms.to[Set] ++ 
        t2.entityScalarDataPropertyParticularRestrictionAxioms.to[Set]
      ).to[Seq], (a: EntityScalarDataPropertyParticularRestrictionAxiom) => a.uuid),
      entityScalarDataPropertyUniversalRestrictionAxioms = parallelSort.parSortBy((
        t1.entityScalarDataPropertyUniversalRestrictionAxioms.to[Set] ++ 
        t2.entityScalarDataPropertyUniversalRestrictionAxioms.to[Set]
      ).to[Seq], (a: EntityScalarDataPropertyUniversalRestrictionAxiom) => a.uuid),
      entityStructuredDataPropertyParticularRestrictionAxioms = parallelSort.parSortBy((
        t1.entityStructuredDataPropertyParticularRestrictionAxioms.to[Set] ++ 
        t2.entityStructuredDataPropertyParticularRestrictionAxioms.to[Set]
      ).to[Seq], (a: EntityStructuredDataPropertyParticularRestrictionAxiom) => a.uuid),
      restrictionStructuredDataPropertyTuples = parallelSort.parSortBy((
        t1.restrictionStructuredDataPropertyTuples.to[Set] ++ 
        t2.restrictionStructuredDataPropertyTuples.to[Set]
      ).to[Seq], (a: RestrictionStructuredDataPropertyTuple) => a.uuid),
      restrictionScalarDataPropertyValues = parallelSort.parSortBy((
        t1.restrictionScalarDataPropertyValues.to[Set] ++ 
        t2.restrictionScalarDataPropertyValues.to[Set]
      ).to[Seq], (a: RestrictionScalarDataPropertyValue) => a.uuid),
      aspectSpecializationAxioms = parallelSort.parSortBy((
        t1.aspectSpecializationAxioms.to[Set] ++ 
        t2.aspectSpecializationAxioms.to[Set]
      ).to[Seq], (a: AspectSpecializationAxiom) => a.uuid),
      conceptSpecializationAxioms = parallelSort.parSortBy((
        t1.conceptSpecializationAxioms.to[Set] ++ 
        t2.conceptSpecializationAxioms.to[Set]
      ).to[Seq], (a: ConceptSpecializationAxiom) => a.uuid),
      reifiedRelationshipSpecializationAxioms = parallelSort.parSortBy((
        t1.reifiedRelationshipSpecializationAxioms.to[Set] ++ 
        t2.reifiedRelationshipSpecializationAxioms.to[Set]
      ).to[Seq], (a: ReifiedRelationshipSpecializationAxiom) => a.uuid),
      subDataPropertyOfAxioms = parallelSort.parSortBy((
        t1.subDataPropertyOfAxioms.to[Set] ++ 
        t2.subDataPropertyOfAxioms.to[Set]
      ).to[Seq], (a: SubDataPropertyOfAxiom) => a.uuid),
      subObjectPropertyOfAxioms = parallelSort.parSortBy((
        t1.subObjectPropertyOfAxioms.to[Set] ++ 
        t2.subObjectPropertyOfAxioms.to[Set]
      ).to[Seq], (a: SubObjectPropertyOfAxiom) => a.uuid),
      rootConceptTaxonomyAxioms = parallelSort.parSortBy((
        t1.rootConceptTaxonomyAxioms.to[Set] ++ 
        t2.rootConceptTaxonomyAxioms.to[Set]
      ).to[Seq], (a: RootConceptTaxonomyAxiom) => a.uuid),
      anonymousConceptUnionAxioms = parallelSort.parSortBy((
        t1.anonymousConceptUnionAxioms.to[Set] ++ 
        t2.anonymousConceptUnionAxioms.to[Set]
      ).to[Seq], (a: AnonymousConceptUnionAxiom) => a.uuid),
      specificDisjointConceptAxioms = parallelSort.parSortBy((
        t1.specificDisjointConceptAxioms.to[Set] ++ 
        t2.specificDisjointConceptAxioms.to[Set]
      ).to[Seq], (a: SpecificDisjointConceptAxiom) => a.uuid),
      conceptInstances = parallelSort.parSortBy((
        t1.conceptInstances.to[Set] ++ 
        t2.conceptInstances.to[Set]
      ).to[Seq], (a: ConceptInstance) => a.uuid),
      reifiedRelationshipInstances = parallelSort.parSortBy((
        t1.reifiedRelationshipInstances.to[Set] ++ 
        t2.reifiedRelationshipInstances.to[Set]
      ).to[Seq], (a: ReifiedRelationshipInstance) => a.uuid),
      reifiedRelationshipInstanceDomains = parallelSort.parSortBy((
        t1.reifiedRelationshipInstanceDomains.to[Set] ++ 
        t2.reifiedRelationshipInstanceDomains.to[Set]
      ).to[Seq], (a: ReifiedRelationshipInstanceDomain) => a.uuid),
      reifiedRelationshipInstanceRanges = parallelSort.parSortBy((
        t1.reifiedRelationshipInstanceRanges.to[Set] ++ 
        t2.reifiedRelationshipInstanceRanges.to[Set]
      ).to[Seq], (a: ReifiedRelationshipInstanceRange) => a.uuid),
      unreifiedRelationshipInstanceTuples = parallelSort.parSortBy((
        t1.unreifiedRelationshipInstanceTuples.to[Set] ++ 
        t2.unreifiedRelationshipInstanceTuples.to[Set]
      ).to[Seq], (a: UnreifiedRelationshipInstanceTuple) => a.uuid),
      singletonInstanceStructuredDataPropertyValues = parallelSort.parSortBy((
        t1.singletonInstanceStructuredDataPropertyValues.to[Set] ++ 
        t2.singletonInstanceStructuredDataPropertyValues.to[Set]
      ).to[Seq], (a: SingletonInstanceStructuredDataPropertyValue) => a.uuid),
      singletonInstanceScalarDataPropertyValues = parallelSort.parSortBy((
        t1.singletonInstanceScalarDataPropertyValues.to[Set] ++ 
        t2.singletonInstanceScalarDataPropertyValues.to[Set]
      ).to[Seq], (a: SingletonInstanceScalarDataPropertyValue) => a.uuid),
      structuredDataPropertyTuples = parallelSort.parSortBy((
        t1.structuredDataPropertyTuples.to[Set] ++ 
        t2.structuredDataPropertyTuples.to[Set]
      ).to[Seq], (a: StructuredDataPropertyTuple) => a.uuid),
      scalarDataPropertyValues = parallelSort.parSortBy((
        t1.scalarDataPropertyValues.to[Set] ++ 
        t2.scalarDataPropertyValues.to[Set]
      ).to[Seq], (a: ScalarDataPropertyValue) => a.uuid),
      annotationPropertyValues = parallelSort.parSortBy((
        t1.annotationPropertyValues.to[Set] ++ 
        t2.annotationPropertyValues.to[Set]
      ).to[Seq], (a: AnnotationPropertyValue) => a.uuid),
      cardinalityRestrictedAspects = parallelSort.parSortBy((
        t1.cardinalityRestrictedAspects.to[Set] ++ 
        t2.cardinalityRestrictedAspects.to[Set]
      ).to[Seq], (a: CardinalityRestrictedAspect) => a.uuid),
      cardinalityRestrictedConcepts = parallelSort.parSortBy((
        t1.cardinalityRestrictedConcepts.to[Set] ++ 
        t2.cardinalityRestrictedConcepts.to[Set]
      ).to[Seq], (a: CardinalityRestrictedConcept) => a.uuid),
      cardinalityRestrictedReifiedRelationships = parallelSort.parSortBy((
        t1.cardinalityRestrictedReifiedRelationships.to[Set] ++ 
        t2.cardinalityRestrictedReifiedRelationships.to[Set]
      ).to[Seq], (a: CardinalityRestrictedReifiedRelationship) => a.uuid),
      instanceRelationshipEnumerationRestrictions = parallelSort.parSortBy((
        t1.instanceRelationshipEnumerationRestrictions.to[Set] ++ 
        t2.instanceRelationshipEnumerationRestrictions.to[Set]
      ).to[Seq], (a: InstanceRelationshipEnumerationRestriction) => a.uuid),
      instanceRelationshipExistentialRangeRestrictions = parallelSort.parSortBy((
        t1.instanceRelationshipExistentialRangeRestrictions.to[Set] ++ 
        t2.instanceRelationshipExistentialRangeRestrictions.to[Set]
      ).to[Seq], (a: InstanceRelationshipExistentialRangeRestriction) => a.uuid),
      instanceRelationshipOneOfRestrictions = parallelSort.parSortBy((
        t1.instanceRelationshipOneOfRestrictions.to[Set] ++ 
        t2.instanceRelationshipOneOfRestrictions.to[Set]
      ).to[Seq], (a: InstanceRelationshipOneOfRestriction) => a.uuid),
      instanceRelationshipUniversalRangeRestrictions = parallelSort.parSortBy((
        t1.instanceRelationshipUniversalRangeRestrictions.to[Set] ++ 
        t2.instanceRelationshipUniversalRangeRestrictions.to[Set]
      ).to[Seq], (a: InstanceRelationshipUniversalRangeRestriction) => a.uuid),
      instanceRelationshipValueRestrictions = parallelSort.parSortBy((
        t1.instanceRelationshipValueRestrictions.to[Set] ++ 
        t2.instanceRelationshipValueRestrictions.to[Set]
      ).to[Seq], (a: InstanceRelationshipValueRestriction) => a.uuid))
  
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
  	  case ReifiedRelationshipRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readReifiedRelationshipRestrictions(is)
  	  case ForwardPropertyHelper.TABLE_JSON_FILENAME =>
  	    tables.readForwardProperties(is)
  	  case InversePropertyHelper.TABLE_JSON_FILENAME =>
  	    tables.readInverseProperties(is)
  	  case UnreifiedRelationshipHelper.TABLE_JSON_FILENAME =>
  	    tables.readUnreifiedRelationships(is)
  	  case ChainRuleHelper.TABLE_JSON_FILENAME =>
  	    tables.readChainRules(is)
  	  case RuleBodySegmentHelper.TABLE_JSON_FILENAME =>
  	    tables.readRuleBodySegments(is)
  	  case SegmentPredicateHelper.TABLE_JSON_FILENAME =>
  	    tables.readSegmentPredicates(is)
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
  	  case CardinalityRestrictedAspectHelper.TABLE_JSON_FILENAME =>
  	    tables.readCardinalityRestrictedAspects(is)
  	  case CardinalityRestrictedConceptHelper.TABLE_JSON_FILENAME =>
  	    tables.readCardinalityRestrictedConcepts(is)
  	  case CardinalityRestrictedReifiedRelationshipHelper.TABLE_JSON_FILENAME =>
  	    tables.readCardinalityRestrictedReifiedRelationships(is)
  	  case InstanceRelationshipEnumerationRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readInstanceRelationshipEnumerationRestrictions(is)
  	  case InstanceRelationshipExistentialRangeRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readInstanceRelationshipExistentialRangeRestrictions(is)
  	  case InstanceRelationshipOneOfRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readInstanceRelationshipOneOfRestrictions(is)
  	  case InstanceRelationshipUniversalRangeRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readInstanceRelationshipUniversalRangeRestrictions(is)
  	  case InstanceRelationshipValueRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readInstanceRelationshipValueRestrictions(is)
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
  
      {
      	val e = new java.util.zip.ZipEntry(TerminologyGraphHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.terminologyGraphs.foreach { t =>
         val line = TerminologyGraphHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(BundleHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.bundles.foreach { t =>
         val line = BundleHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(DescriptionBoxHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.descriptionBoxes.foreach { t =>
         val line = DescriptionBoxHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(AnnotationPropertyHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.annotationProperties.foreach { t =>
         val line = AnnotationPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(AspectHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.aspects.foreach { t =>
         val line = AspectHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(ConceptHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.concepts.foreach { t =>
         val line = ConceptHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(ScalarHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.scalars.foreach { t =>
         val line = ScalarHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(StructureHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.structures.foreach { t =>
         val line = StructureHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(ConceptDesignationTerminologyAxiomHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.conceptDesignationTerminologyAxioms.foreach { t =>
         val line = ConceptDesignationTerminologyAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(TerminologyExtensionAxiomHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.terminologyExtensionAxioms.foreach { t =>
         val line = TerminologyExtensionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(TerminologyNestingAxiomHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.terminologyNestingAxioms.foreach { t =>
         val line = TerminologyNestingAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(BundledTerminologyAxiomHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.bundledTerminologyAxioms.foreach { t =>
         val line = BundledTerminologyAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(DescriptionBoxExtendsClosedWorldDefinitionsHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.descriptionBoxExtendsClosedWorldDefinitions.foreach { t =>
         val line = DescriptionBoxExtendsClosedWorldDefinitionsHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(DescriptionBoxRefinementHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.descriptionBoxRefinements.foreach { t =>
         val line = DescriptionBoxRefinementHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(BinaryScalarRestrictionHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.binaryScalarRestrictions.foreach { t =>
         val line = BinaryScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(IRIScalarRestrictionHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.iriScalarRestrictions.foreach { t =>
         val line = IRIScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(NumericScalarRestrictionHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.numericScalarRestrictions.foreach { t =>
         val line = NumericScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(PlainLiteralScalarRestrictionHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.plainLiteralScalarRestrictions.foreach { t =>
         val line = PlainLiteralScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(ScalarOneOfRestrictionHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.scalarOneOfRestrictions.foreach { t =>
         val line = ScalarOneOfRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(ScalarOneOfLiteralAxiomHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.scalarOneOfLiteralAxioms.foreach { t =>
         val line = ScalarOneOfLiteralAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(StringScalarRestrictionHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.stringScalarRestrictions.foreach { t =>
         val line = StringScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(SynonymScalarRestrictionHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.synonymScalarRestrictions.foreach { t =>
         val line = SynonymScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(TimeScalarRestrictionHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.timeScalarRestrictions.foreach { t =>
         val line = TimeScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(EntityScalarDataPropertyHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.entityScalarDataProperties.foreach { t =>
         val line = EntityScalarDataPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(EntityStructuredDataPropertyHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.entityStructuredDataProperties.foreach { t =>
         val line = EntityStructuredDataPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(ScalarDataPropertyHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.scalarDataProperties.foreach { t =>
         val line = ScalarDataPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(StructuredDataPropertyHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.structuredDataProperties.foreach { t =>
         val line = StructuredDataPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(ReifiedRelationshipHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.reifiedRelationships.foreach { t =>
         val line = ReifiedRelationshipHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(ReifiedRelationshipRestrictionHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.reifiedRelationshipRestrictions.foreach { t =>
         val line = ReifiedRelationshipRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(ForwardPropertyHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.forwardProperties.foreach { t =>
         val line = ForwardPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(InversePropertyHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.inverseProperties.foreach { t =>
         val line = InversePropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(UnreifiedRelationshipHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.unreifiedRelationships.foreach { t =>
         val line = UnreifiedRelationshipHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(ChainRuleHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.chainRules.foreach { t =>
         val line = ChainRuleHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(RuleBodySegmentHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.ruleBodySegments.foreach { t =>
         val line = RuleBodySegmentHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(SegmentPredicateHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.segmentPredicates.foreach { t =>
         val line = SegmentPredicateHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(EntityExistentialRestrictionAxiomHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.entityExistentialRestrictionAxioms.foreach { t =>
         val line = EntityExistentialRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(EntityUniversalRestrictionAxiomHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.entityUniversalRestrictionAxioms.foreach { t =>
         val line = EntityUniversalRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(EntityScalarDataPropertyExistentialRestrictionAxiomHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.entityScalarDataPropertyExistentialRestrictionAxioms.foreach { t =>
         val line = EntityScalarDataPropertyExistentialRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(EntityScalarDataPropertyParticularRestrictionAxiomHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.entityScalarDataPropertyParticularRestrictionAxioms.foreach { t =>
         val line = EntityScalarDataPropertyParticularRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(EntityScalarDataPropertyUniversalRestrictionAxiomHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.entityScalarDataPropertyUniversalRestrictionAxioms.foreach { t =>
         val line = EntityScalarDataPropertyUniversalRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(EntityStructuredDataPropertyParticularRestrictionAxiomHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.entityStructuredDataPropertyParticularRestrictionAxioms.foreach { t =>
         val line = EntityStructuredDataPropertyParticularRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(RestrictionStructuredDataPropertyTupleHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.restrictionStructuredDataPropertyTuples.foreach { t =>
         val line = RestrictionStructuredDataPropertyTupleHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(RestrictionScalarDataPropertyValueHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.restrictionScalarDataPropertyValues.foreach { t =>
         val line = RestrictionScalarDataPropertyValueHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(AspectSpecializationAxiomHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.aspectSpecializationAxioms.foreach { t =>
         val line = AspectSpecializationAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(ConceptSpecializationAxiomHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.conceptSpecializationAxioms.foreach { t =>
         val line = ConceptSpecializationAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(ReifiedRelationshipSpecializationAxiomHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.reifiedRelationshipSpecializationAxioms.foreach { t =>
         val line = ReifiedRelationshipSpecializationAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(SubDataPropertyOfAxiomHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.subDataPropertyOfAxioms.foreach { t =>
         val line = SubDataPropertyOfAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(SubObjectPropertyOfAxiomHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.subObjectPropertyOfAxioms.foreach { t =>
         val line = SubObjectPropertyOfAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(RootConceptTaxonomyAxiomHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.rootConceptTaxonomyAxioms.foreach { t =>
         val line = RootConceptTaxonomyAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(AnonymousConceptUnionAxiomHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.anonymousConceptUnionAxioms.foreach { t =>
         val line = AnonymousConceptUnionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(SpecificDisjointConceptAxiomHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.specificDisjointConceptAxioms.foreach { t =>
         val line = SpecificDisjointConceptAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(ConceptInstanceHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.conceptInstances.foreach { t =>
         val line = ConceptInstanceHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(ReifiedRelationshipInstanceHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.reifiedRelationshipInstances.foreach { t =>
         val line = ReifiedRelationshipInstanceHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(ReifiedRelationshipInstanceDomainHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.reifiedRelationshipInstanceDomains.foreach { t =>
         val line = ReifiedRelationshipInstanceDomainHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(ReifiedRelationshipInstanceRangeHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.reifiedRelationshipInstanceRanges.foreach { t =>
         val line = ReifiedRelationshipInstanceRangeHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(UnreifiedRelationshipInstanceTupleHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.unreifiedRelationshipInstanceTuples.foreach { t =>
         val line = UnreifiedRelationshipInstanceTupleHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(SingletonInstanceStructuredDataPropertyValueHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.singletonInstanceStructuredDataPropertyValues.foreach { t =>
         val line = SingletonInstanceStructuredDataPropertyValueHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(SingletonInstanceScalarDataPropertyValueHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.singletonInstanceScalarDataPropertyValues.foreach { t =>
         val line = SingletonInstanceScalarDataPropertyValueHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(StructuredDataPropertyTupleHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.structuredDataPropertyTuples.foreach { t =>
         val line = StructuredDataPropertyTupleHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(ScalarDataPropertyValueHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.scalarDataPropertyValues.foreach { t =>
         val line = ScalarDataPropertyValueHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(AnnotationPropertyValueHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.annotationPropertyValues.foreach { t =>
         val line = AnnotationPropertyValueHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(CardinalityRestrictedAspectHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.cardinalityRestrictedAspects.foreach { t =>
         val line = CardinalityRestrictedAspectHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(CardinalityRestrictedConceptHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.cardinalityRestrictedConcepts.foreach { t =>
         val line = CardinalityRestrictedConceptHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(CardinalityRestrictedReifiedRelationshipHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.cardinalityRestrictedReifiedRelationships.foreach { t =>
         val line = CardinalityRestrictedReifiedRelationshipHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(InstanceRelationshipEnumerationRestrictionHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.instanceRelationshipEnumerationRestrictions.foreach { t =>
         val line = InstanceRelationshipEnumerationRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(InstanceRelationshipExistentialRangeRestrictionHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.instanceRelationshipExistentialRangeRestrictions.foreach { t =>
         val line = InstanceRelationshipExistentialRangeRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(InstanceRelationshipOneOfRestrictionHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.instanceRelationshipOneOfRestrictions.foreach { t =>
         val line = InstanceRelationshipOneOfRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(InstanceRelationshipUniversalRangeRestrictionHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.instanceRelationshipUniversalRangeRestrictions.foreach { t =>
         val line = InstanceRelationshipUniversalRangeRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      {
      	val e = new java.util.zip.ZipEntry(InstanceRelationshipValueRestrictionHelper.TABLE_JSON_FILENAME)
      	e.setTime(0L)
      	zos.putNextEntry(e)
      }
      tables.instanceRelationshipValueRestrictions.foreach { t =>
         val line = InstanceRelationshipValueRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry();
      
      zos.close()
  	  Success(())
  	}

}
