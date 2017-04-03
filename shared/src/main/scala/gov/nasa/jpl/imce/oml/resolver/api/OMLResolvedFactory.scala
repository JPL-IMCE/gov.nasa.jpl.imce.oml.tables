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

trait OMLResolvedFactory {
	
  // Annotation
  
  def createAnnotation
  ( extent: Extent,
    module: Module,
    subject: Element,
    property: AnnotationProperty,
    value: scala.Predef.String )
  : (Extent, Annotation)
  
  // AnnotationEntry
  
  def createAnnotationEntry
  ( extent: Extent,
    module: Module,
    subject: Element,
    value: scala.Predef.String )
  : (Extent, AnnotationEntry)
  
  // AnnotationProperty
  
  def createAnnotationProperty
  ( extent: Extent,
    iri: gov.nasa.jpl.imce.oml.tables.IRI,
    abbrevIRI: gov.nasa.jpl.imce.oml.tables.AbbrevIRI )
  : (Extent, AnnotationProperty)
  
  // AnonymousConceptTaxonomyAxiom
  
  def createAnonymousConceptTaxonomyAxiom
  ( extent: Extent,
    bundle: Bundle,
    disjointTaxonomyParent: ConceptTreeDisjunction )
  : (Extent, AnonymousConceptTaxonomyAxiom)
  
  // Aspect
  
  def createAspect
  ( extent: Extent,
    tbox: TerminologyBox,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, Aspect)
  
  // AspectSpecializationAxiom
  
  def createAspectSpecializationAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    superAspect: Aspect,
    subEntity: Entity )
  : (Extent, AspectSpecializationAxiom)
  
  // BinaryScalarRestriction
  
  def createBinaryScalarRestriction
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    length: scala.Option[scala.Int],
    minLength: scala.Option[scala.Int],
    maxLength: scala.Option[scala.Int],
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, BinaryScalarRestriction)
  
  // Bundle
  
  def createBundle
  ( extent: Extent,
    kind: gov.nasa.jpl.imce.oml.tables.TerminologyKind,
    iri: gov.nasa.jpl.imce.oml.tables.IRI )
  : (Extent, Bundle)
  
  // BundledTerminologyAxiom
  
  def createBundledTerminologyAxiom
  ( extent: Extent,
    bundledTerminology: TerminologyBox,
    bundle: Bundle )
  : (Extent, BundledTerminologyAxiom)
  
  // Concept
  
  def createConcept
  ( extent: Extent,
    tbox: TerminologyBox,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, Concept)
  
  // ConceptDesignationTerminologyAxiom
  
  def createConceptDesignationTerminologyAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    designatedConcept: Concept,
    designatedTerminology: TerminologyBox )
  : (Extent, ConceptDesignationTerminologyAxiom)
  
  // ConceptInstance
  
  def createConceptInstance
  ( extent: Extent,
    descriptionBox: DescriptionBox,
    singletonConceptClassifier: Concept,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, ConceptInstance)
  
  // ConceptSpecializationAxiom
  
  def createConceptSpecializationAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    superConcept: Concept,
    subConcept: Concept )
  : (Extent, ConceptSpecializationAxiom)
  
  // DataStructureTuple
  
  def createDataStructureTuple
  ( extent: Extent,
    dataStructureType: Structure,
    structuredDataPropertyValue: StructuredDataPropertyValue,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, DataStructureTuple)
  
  // DescriptionBox
  
  def createDescriptionBox
  ( extent: Extent,
    kind: gov.nasa.jpl.imce.oml.tables.DescriptionKind,
    iri: gov.nasa.jpl.imce.oml.tables.IRI )
  : (Extent, DescriptionBox)
  
  // DescriptionBoxExtendsClosedWorldDefinitions
  
  def createDescriptionBoxExtendsClosedWorldDefinitions
  ( extent: Extent,
    descriptionBox: DescriptionBox,
    closedWorldDefinitions: TerminologyBox )
  : (Extent, DescriptionBoxExtendsClosedWorldDefinitions)
  
  // DescriptionBoxRefinement
  
  def createDescriptionBoxRefinement
  ( extent: Extent,
    refiningDescriptionBox: DescriptionBox,
    refinedDescriptionBox: DescriptionBox )
  : (Extent, DescriptionBoxRefinement)
  
  // EntityExistentialRestrictionAxiom
  
  def createEntityExistentialRestrictionAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedRelation: EntityRelationship,
    restrictedDomain: Entity,
    restrictedRange: Entity )
  : (Extent, EntityExistentialRestrictionAxiom)
  
  // EntityScalarDataProperty
  
  def createEntityScalarDataProperty
  ( extent: Extent,
    tbox: TerminologyBox,
    domain: Entity,
    range: DataRange,
    isIdentityCriteria: scala.Boolean,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, EntityScalarDataProperty)
  
  // EntityScalarDataPropertyExistentialRestrictionAxiom
  
  def createEntityScalarDataPropertyExistentialRestrictionAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedEntity: Entity,
    scalarProperty: EntityScalarDataProperty,
    scalarRestriction: DataRange )
  : (Extent, EntityScalarDataPropertyExistentialRestrictionAxiom)
  
  // EntityScalarDataPropertyParticularRestrictionAxiom
  
  def createEntityScalarDataPropertyParticularRestrictionAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedEntity: Entity,
    scalarProperty: EntityScalarDataProperty,
    literalValue: gov.nasa.jpl.imce.oml.tables.LexicalValue )
  : (Extent, EntityScalarDataPropertyParticularRestrictionAxiom)
  
  // EntityScalarDataPropertyUniversalRestrictionAxiom
  
  def createEntityScalarDataPropertyUniversalRestrictionAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedEntity: Entity,
    scalarProperty: EntityScalarDataProperty,
    scalarRestriction: DataRange )
  : (Extent, EntityScalarDataPropertyUniversalRestrictionAxiom)
  
  // EntityStructuredDataProperty
  
  def createEntityStructuredDataProperty
  ( extent: Extent,
    tbox: TerminologyBox,
    domain: Entity,
    range: Structure,
    isIdentityCriteria: scala.Boolean,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, EntityStructuredDataProperty)
  
  // EntityUniversalRestrictionAxiom
  
  def createEntityUniversalRestrictionAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedRelation: EntityRelationship,
    restrictedDomain: Entity,
    restrictedRange: Entity )
  : (Extent, EntityUniversalRestrictionAxiom)
  
  // Extent
  
  def createExtent
  : Extent
  
  // IRIScalarRestriction
  
  def createIRIScalarRestriction
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    length: scala.Option[scala.Int],
    minLength: scala.Option[scala.Int],
    maxLength: scala.Option[scala.Int],
    name: gov.nasa.jpl.imce.oml.tables.LocalName,
    pattern: scala.Option[gov.nasa.jpl.imce.oml.tables.Pattern] )
  : (Extent, IRIScalarRestriction)
  
  // NumericScalarRestriction
  
  def createNumericScalarRestriction
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    minExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LexicalNumber],
    minInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LexicalNumber],
    maxExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LexicalNumber],
    maxInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LexicalNumber],
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, NumericScalarRestriction)
  
  // PlainLiteralScalarRestriction
  
  def createPlainLiteralScalarRestriction
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    length: scala.Option[scala.Int],
    minLength: scala.Option[scala.Int],
    maxLength: scala.Option[scala.Int],
    name: gov.nasa.jpl.imce.oml.tables.LocalName,
    langRange: scala.Option[gov.nasa.jpl.imce.oml.tables.LangRange],
    pattern: scala.Option[gov.nasa.jpl.imce.oml.tables.Pattern] )
  : (Extent, PlainLiteralScalarRestriction)
  
  // ReifiedRelationship
  
  def createReifiedRelationship
  ( extent: Extent,
    tbox: TerminologyBox,
    source: Entity,
    target: Entity,
    isAsymmetric: scala.Boolean,
    isEssential: scala.Boolean,
    isFunctional: scala.Boolean,
    isInverseEssential: scala.Boolean,
    isInverseFunctional: scala.Boolean,
    isIrreflexive: scala.Boolean,
    isReflexive: scala.Boolean,
    isSymmetric: scala.Boolean,
    isTransitive: scala.Boolean,
    name: gov.nasa.jpl.imce.oml.tables.LocalName,
    unreifiedPropertyName: gov.nasa.jpl.imce.oml.tables.LocalName,
    unreifiedInversePropertyName: scala.Option[gov.nasa.jpl.imce.oml.tables.LocalName] )
  : (Extent, ReifiedRelationship)
  
  // ReifiedRelationshipInstance
  
  def createReifiedRelationshipInstance
  ( extent: Extent,
    descriptionBox: DescriptionBox,
    singletonReifiedRelationshipClassifier: ReifiedRelationship,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, ReifiedRelationshipInstance)
  
  // ReifiedRelationshipInstanceDomain
  
  def createReifiedRelationshipInstanceDomain
  ( extent: Extent,
    descriptionBox: DescriptionBox,
    reifiedRelationshipInstance: ReifiedRelationshipInstance,
    domain: ConceptualEntitySingletonInstance,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, ReifiedRelationshipInstanceDomain)
  
  // ReifiedRelationshipInstanceRange
  
  def createReifiedRelationshipInstanceRange
  ( extent: Extent,
    descriptionBox: DescriptionBox,
    reifiedRelationshipInstance: ReifiedRelationshipInstance,
    range: ConceptualEntitySingletonInstance,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, ReifiedRelationshipInstanceRange)
  
  // ReifiedRelationshipSpecializationAxiom
  
  def createReifiedRelationshipSpecializationAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    superRelationship: ReifiedRelationship,
    subRelationship: ReifiedRelationship )
  : (Extent, ReifiedRelationshipSpecializationAxiom)
  
  // RootConceptTaxonomyAxiom
  
  def createRootConceptTaxonomyAxiom
  ( extent: Extent,
    bundle: Bundle,
    root: Concept )
  : (Extent, RootConceptTaxonomyAxiom)
  
  // Scalar
  
  def createScalar
  ( extent: Extent,
    tbox: TerminologyBox,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, Scalar)
  
  // ScalarDataProperty
  
  def createScalarDataProperty
  ( extent: Extent,
    tbox: TerminologyBox,
    domain: Structure,
    range: DataRange,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, ScalarDataProperty)
  
  // ScalarDataPropertyValue
  
  def createScalarDataPropertyValue
  ( extent: Extent,
    singletonInstance: SingletonInstance,
    scalarDataProperty: DataRelationshipToScalar,
    name: gov.nasa.jpl.imce.oml.tables.LocalName,
    scalarPropertyValue: scala.Predef.String )
  : (Extent, ScalarDataPropertyValue)
  
  // ScalarOneOfLiteralAxiom
  
  def createScalarOneOfLiteralAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    axiom: ScalarOneOfRestriction,
    value: gov.nasa.jpl.imce.oml.tables.LexicalValue )
  : (Extent, ScalarOneOfLiteralAxiom)
  
  // ScalarOneOfRestriction
  
  def createScalarOneOfRestriction
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, ScalarOneOfRestriction)
  
  // SpecificDisjointConceptAxiom
  
  def createSpecificDisjointConceptAxiom
  ( extent: Extent,
    bundle: Bundle,
    disjointTaxonomyParent: ConceptTreeDisjunction,
    disjointLeaf: Concept )
  : (Extent, SpecificDisjointConceptAxiom)
  
  // StringScalarRestriction
  
  def createStringScalarRestriction
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    length: scala.Option[scala.Int],
    minLength: scala.Option[scala.Int],
    maxLength: scala.Option[scala.Int],
    name: gov.nasa.jpl.imce.oml.tables.LocalName,
    pattern: scala.Option[gov.nasa.jpl.imce.oml.tables.Pattern] )
  : (Extent, StringScalarRestriction)
  
  // Structure
  
  def createStructure
  ( extent: Extent,
    tbox: TerminologyBox,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, Structure)
  
  // StructuredDataProperty
  
  def createStructuredDataProperty
  ( extent: Extent,
    tbox: TerminologyBox,
    domain: Structure,
    range: Structure,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, StructuredDataProperty)
  
  // StructuredDataPropertyValue
  
  def createStructuredDataPropertyValue
  ( extent: Extent,
    singletonInstance: SingletonInstance,
    structuredDataProperty: DataRelationshipToStructure,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, StructuredDataPropertyValue)
  
  // SynonymScalarRestriction
  
  def createSynonymScalarRestriction
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, SynonymScalarRestriction)
  
  // TerminologyExtensionAxiom
  
  def createTerminologyExtensionAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    extendedTerminology: TerminologyBox )
  : (Extent, TerminologyExtensionAxiom)
  
  // TerminologyGraph
  
  def createTerminologyGraph
  ( extent: Extent,
    kind: gov.nasa.jpl.imce.oml.tables.TerminologyKind,
    iri: gov.nasa.jpl.imce.oml.tables.IRI )
  : (Extent, TerminologyGraph)
  
  // TerminologyNestingAxiom
  
  def createTerminologyNestingAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    nestingTerminology: TerminologyBox,
    nestingContext: Concept )
  : (Extent, TerminologyNestingAxiom)
  
  // TimeScalarRestriction
  
  def createTimeScalarRestriction
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    minExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LexicalTime],
    minInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LexicalTime],
    maxExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LexicalTime],
    maxInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LexicalTime],
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, TimeScalarRestriction)
  
  // UnreifiedRelationship
  
  def createUnreifiedRelationship
  ( extent: Extent,
    tbox: TerminologyBox,
    source: Entity,
    target: Entity,
    isAsymmetric: scala.Boolean,
    isEssential: scala.Boolean,
    isFunctional: scala.Boolean,
    isInverseEssential: scala.Boolean,
    isInverseFunctional: scala.Boolean,
    isIrreflexive: scala.Boolean,
    isReflexive: scala.Boolean,
    isSymmetric: scala.Boolean,
    isTransitive: scala.Boolean,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, UnreifiedRelationship)
  
  // UnreifiedRelationshipInstanceTuple
  
  def createUnreifiedRelationshipInstanceTuple
  ( extent: Extent,
    descriptionBox: DescriptionBox,
    unreifiedRelationship: UnreifiedRelationship,
    domain: ConceptualEntitySingletonInstance,
    range: ConceptualEntitySingletonInstance,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, UnreifiedRelationshipInstanceTuple)
  
}
