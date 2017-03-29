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
  ( module: scala.Option[java.util.UUID] /* reference to a Module */,
    subject: Element,
    property: AnnotationProperty,
    value: scala.Predef.String )
  : Annotation
  
  // AnnotationEntry
  
  def createAnnotationEntry
  ( module: Module,
    subject: Element,
    value: scala.Predef.String )
  : AnnotationEntry
  
  // AnnotationProperty
  
  def createAnnotationProperty
  ( iri: gov.nasa.jpl.imce.oml.tables.IRI,
    abbrevIRI: gov.nasa.jpl.imce.oml.tables.AbbrevIRI )
  : AnnotationProperty
  
  // AnnotationPropertyTable
  
  def createAnnotationPropertyTable
  ( key: AnnotationProperty,
    value: scala.collection.immutable.SortedSet[AnnotationEntry] )
  : AnnotationPropertyTable
  
  // AnonymousConceptTaxonomyAxiom
  
  def createAnonymousConceptTaxonomyAxiom
  ( bundle: scala.Option[java.util.UUID] /* reference to a Bundle */,
    disjointTaxonomyParent: ConceptTreeDisjunction )
  : AnonymousConceptTaxonomyAxiom
  
  // Aspect
  
  def createAspect
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : Aspect
  
  // AspectSpecializationAxiom
  
  def createAspectSpecializationAxiom
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    superAspect: Aspect,
    subEntity: Entity )
  : AspectSpecializationAxiom
  
  // BinaryScalarRestriction
  
  def createBinaryScalarRestriction
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    restrictedRange: DataRange,
    length: scala.Option[scala.Int],
    minLength: scala.Option[scala.Int],
    maxLength: scala.Option[scala.Int],
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : BinaryScalarRestriction
  
  // Bundle
  
  def createBundle
  ( extent: scala.Option[java.util.UUID] /* reference to a Extent */,
    kind: gov.nasa.jpl.imce.oml.tables.TerminologyKind,
    iri: gov.nasa.jpl.imce.oml.tables.IRI,
    annotations: scala.collection.immutable.SortedSet[Annotation],
    boxStatements: scala.collection.immutable.SortedSet[TerminologyBoxStatement],
    boxAxioms: scala.collection.immutable.SortedSet[TerminologyBoxAxiom],
    bundleStatements: scala.collection.immutable.SortedSet[TerminologyBundleStatement],
    bundleAxioms: scala.collection.immutable.SortedSet[TerminologyBundleAxiom] )
  : Bundle
  
  // BundledTerminologyAxiom
  
  def createBundledTerminologyAxiom
  ( bundledTerminology: TerminologyBox,
    bundle: scala.Option[java.util.UUID] /* reference to a Bundle */ )
  : BundledTerminologyAxiom
  
  def copyBundledTerminologyAxiom_bundledTerminology
  ( that: BundledTerminologyAxiom,
    bundledTerminology: TerminologyBox )
  : BundledTerminologyAxiom
  
  def copyBundledTerminologyAxiom_bundle
  ( that: BundledTerminologyAxiom,
    bundle: scala.Option[java.util.UUID] /* reference to a Bundle */ )
  : BundledTerminologyAxiom
  
  // Concept
  
  def createConcept
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : Concept
  
  // ConceptDesignationTerminologyAxiom
  
  def createConceptDesignationTerminologyAxiom
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    designatedConcept: Concept,
    designatedTerminology: TerminologyBox )
  : ConceptDesignationTerminologyAxiom
  
  def copyConceptDesignationTerminologyAxiom_tbox
  ( that: ConceptDesignationTerminologyAxiom,
    tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */ )
  : ConceptDesignationTerminologyAxiom
  
  def copyConceptDesignationTerminologyAxiom_designatedTerminology
  ( that: ConceptDesignationTerminologyAxiom,
    designatedTerminology: TerminologyBox )
  : ConceptDesignationTerminologyAxiom
  
  // ConceptInstance
  
  def createConceptInstance
  ( descriptionBox: scala.Option[java.util.UUID] /* reference to a DescriptionBox */,
    singletonConceptClassifier: Concept,
    name: gov.nasa.jpl.imce.oml.tables.LocalName,
    scalarDataPropertyValues: scala.collection.immutable.SortedSet[ScalarDataPropertyValue],
    structuredDataPropertyValues: scala.collection.immutable.SortedSet[StructuredDataPropertyValue] )
  : ConceptInstance
  
  def copyConceptInstance_scalarDataPropertyValues
  ( that: ConceptInstance,
    scalarDataPropertyValues: scala.collection.immutable.SortedSet[ScalarDataPropertyValue] )
  : ConceptInstance
  
  def copyConceptInstance_structuredDataPropertyValues
  ( that: ConceptInstance,
    structuredDataPropertyValues: scala.collection.immutable.SortedSet[StructuredDataPropertyValue] )
  : ConceptInstance
  
  // ConceptSpecializationAxiom
  
  def createConceptSpecializationAxiom
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    superConcept: Concept,
    subConcept: Concept )
  : ConceptSpecializationAxiom
  
  // DataStructureTuple
  
  def createDataStructureTuple
  ( dataStructureType: Structure,
    structuredDataPropertyValue: scala.Option[java.util.UUID] /* reference to a StructuredDataPropertyValue */,
    name: gov.nasa.jpl.imce.oml.tables.LocalName,
    scalarDataPropertyValues: scala.collection.immutable.SortedSet[ScalarDataPropertyValue],
    structuredDataPropertyValues: scala.collection.immutable.SortedSet[StructuredDataPropertyValue] )
  : DataStructureTuple
  
  def copyDataStructureTuple_scalarDataPropertyValues
  ( that: DataStructureTuple,
    scalarDataPropertyValues: scala.collection.immutable.SortedSet[ScalarDataPropertyValue] )
  : DataStructureTuple
  
  def copyDataStructureTuple_structuredDataPropertyValues
  ( that: DataStructureTuple,
    structuredDataPropertyValues: scala.collection.immutable.SortedSet[StructuredDataPropertyValue] )
  : DataStructureTuple
  
  // DescriptionBox
  
  def createDescriptionBox
  ( extent: scala.Option[java.util.UUID] /* reference to a Extent */,
    closedWorldDefinitions: scala.collection.immutable.SortedSet[DescriptionBoxExtendsClosedWorldDefinitions],
    kind: gov.nasa.jpl.imce.oml.tables.DescriptionKind,
    iri: gov.nasa.jpl.imce.oml.tables.IRI,
    annotations: scala.collection.immutable.SortedSet[Annotation],
    conceptInstances: scala.collection.immutable.SortedSet[ConceptInstance],
    descriptionBoxRefinements: scala.collection.immutable.SortedSet[DescriptionBoxRefinement],
    reifiedRelationshipInstanceDomains: scala.collection.immutable.SortedSet[ReifiedRelationshipInstanceDomain],
    reifiedRelationshipInstanceRanges: scala.collection.immutable.SortedSet[ReifiedRelationshipInstanceRange],
    reifiedRelationshipInstances: scala.collection.immutable.SortedSet[ReifiedRelationshipInstance],
    unreifiedRelationshipInstanceTuples: scala.collection.immutable.SortedSet[UnreifiedRelationshipInstanceTuple] )
  : DescriptionBox
  
  def copyDescriptionBox_closedWorldDefinitions
  ( that: DescriptionBox,
    closedWorldDefinitions: scala.collection.immutable.SortedSet[DescriptionBoxExtendsClosedWorldDefinitions] )
  : DescriptionBox
  
  def copyDescriptionBox_conceptInstances
  ( that: DescriptionBox,
    conceptInstances: scala.collection.immutable.SortedSet[ConceptInstance] )
  : DescriptionBox
  
  def copyDescriptionBox_descriptionBoxRefinements
  ( that: DescriptionBox,
    descriptionBoxRefinements: scala.collection.immutable.SortedSet[DescriptionBoxRefinement] )
  : DescriptionBox
  
  def copyDescriptionBox_reifiedRelationshipInstanceDomains
  ( that: DescriptionBox,
    reifiedRelationshipInstanceDomains: scala.collection.immutable.SortedSet[ReifiedRelationshipInstanceDomain] )
  : DescriptionBox
  
  def copyDescriptionBox_reifiedRelationshipInstanceRanges
  ( that: DescriptionBox,
    reifiedRelationshipInstanceRanges: scala.collection.immutable.SortedSet[ReifiedRelationshipInstanceRange] )
  : DescriptionBox
  
  def copyDescriptionBox_reifiedRelationshipInstances
  ( that: DescriptionBox,
    reifiedRelationshipInstances: scala.collection.immutable.SortedSet[ReifiedRelationshipInstance] )
  : DescriptionBox
  
  def copyDescriptionBox_unreifiedRelationshipInstanceTuples
  ( that: DescriptionBox,
    unreifiedRelationshipInstanceTuples: scala.collection.immutable.SortedSet[UnreifiedRelationshipInstanceTuple] )
  : DescriptionBox
  
  // DescriptionBoxExtendsClosedWorldDefinitions
  
  def createDescriptionBoxExtendsClosedWorldDefinitions
  ( descriptionBox: scala.Option[java.util.UUID] /* reference to a DescriptionBox */,
    closedWorldDefinitions: TerminologyBox )
  : DescriptionBoxExtendsClosedWorldDefinitions
  
  // DescriptionBoxRefinement
  
  def createDescriptionBoxRefinement
  ( refiningDescriptionBox: scala.Option[java.util.UUID] /* reference to a DescriptionBox */,
    refinedDescriptionBox: DescriptionBox )
  : DescriptionBoxRefinement
  
  // EntityExistentialRestrictionAxiom
  
  def createEntityExistentialRestrictionAxiom
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    restrictedRelation: EntityRelationship,
    restrictedDomain: Entity,
    restrictedRange: Entity )
  : EntityExistentialRestrictionAxiom
  
  // EntityScalarDataProperty
  
  def createEntityScalarDataProperty
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    domain: Entity,
    range: DataRange,
    isIdentityCriteria: scala.Boolean,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : EntityScalarDataProperty
  
  // EntityScalarDataPropertyExistentialRestrictionAxiom
  
  def createEntityScalarDataPropertyExistentialRestrictionAxiom
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    restrictedEntity: Entity,
    scalarProperty: EntityScalarDataProperty,
    scalarRestriction: DataRange )
  : EntityScalarDataPropertyExistentialRestrictionAxiom
  
  // EntityScalarDataPropertyParticularRestrictionAxiom
  
  def createEntityScalarDataPropertyParticularRestrictionAxiom
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    restrictedEntity: Entity,
    scalarProperty: EntityScalarDataProperty,
    literalValue: gov.nasa.jpl.imce.oml.tables.LexicalValue )
  : EntityScalarDataPropertyParticularRestrictionAxiom
  
  // EntityScalarDataPropertyUniversalRestrictionAxiom
  
  def createEntityScalarDataPropertyUniversalRestrictionAxiom
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    restrictedEntity: Entity,
    scalarProperty: EntityScalarDataProperty,
    scalarRestriction: DataRange )
  : EntityScalarDataPropertyUniversalRestrictionAxiom
  
  // EntityStructuredDataProperty
  
  def createEntityStructuredDataProperty
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    domain: Entity,
    range: Structure,
    isIdentityCriteria: scala.Boolean,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : EntityStructuredDataProperty
  
  // EntityUniversalRestrictionAxiom
  
  def createEntityUniversalRestrictionAxiom
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    restrictedRelation: EntityRelationship,
    restrictedDomain: Entity,
    restrictedRange: Entity )
  : EntityUniversalRestrictionAxiom
  
  // Extent
  
  def createExtent
  ( uuid: java.util.UUID,
    annotationProperties: scala.collection.immutable.SortedSet[AnnotationProperty],
    modules: scala.collection.immutable.Map[java.util.UUID, Module] )
  : Extent
  
  def copyExtent_annotationProperties
  ( that: Extent,
    annotationProperties: scala.collection.immutable.SortedSet[AnnotationProperty] )
  : Extent
  
  def copyExtent_modules
  ( that: Extent,
    modules: scala.collection.immutable.Map[java.util.UUID, Module] )
  : Extent
  
  // IRIScalarRestriction
  
  def createIRIScalarRestriction
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    restrictedRange: DataRange,
    length: scala.Option[scala.Int],
    minLength: scala.Option[scala.Int],
    maxLength: scala.Option[scala.Int],
    name: gov.nasa.jpl.imce.oml.tables.LocalName,
    pattern: scala.Option[gov.nasa.jpl.imce.oml.tables.Pattern] )
  : IRIScalarRestriction
  
  // NumericScalarRestriction
  
  def createNumericScalarRestriction
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    restrictedRange: DataRange,
    minExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LexicalNumber],
    minInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LexicalNumber],
    maxExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LexicalNumber],
    maxInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LexicalNumber],
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : NumericScalarRestriction
  
  // PlainLiteralScalarRestriction
  
  def createPlainLiteralScalarRestriction
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    restrictedRange: DataRange,
    length: scala.Option[scala.Int],
    minLength: scala.Option[scala.Int],
    maxLength: scala.Option[scala.Int],
    name: gov.nasa.jpl.imce.oml.tables.LocalName,
    langRange: scala.Option[gov.nasa.jpl.imce.oml.tables.LangRange],
    pattern: scala.Option[gov.nasa.jpl.imce.oml.tables.Pattern] )
  : PlainLiteralScalarRestriction
  
  // ReifiedRelationship
  
  def createReifiedRelationship
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
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
  : ReifiedRelationship
  
  // ReifiedRelationshipInstance
  
  def createReifiedRelationshipInstance
  ( descriptionBox: scala.Option[java.util.UUID] /* reference to a DescriptionBox */,
    singletonReifiedRelationshipClassifier: ReifiedRelationship,
    name: gov.nasa.jpl.imce.oml.tables.LocalName,
    scalarDataPropertyValues: scala.collection.immutable.SortedSet[ScalarDataPropertyValue],
    structuredDataPropertyValues: scala.collection.immutable.SortedSet[StructuredDataPropertyValue] )
  : ReifiedRelationshipInstance
  
  def copyReifiedRelationshipInstance_scalarDataPropertyValues
  ( that: ReifiedRelationshipInstance,
    scalarDataPropertyValues: scala.collection.immutable.SortedSet[ScalarDataPropertyValue] )
  : ReifiedRelationshipInstance
  
  def copyReifiedRelationshipInstance_structuredDataPropertyValues
  ( that: ReifiedRelationshipInstance,
    structuredDataPropertyValues: scala.collection.immutable.SortedSet[StructuredDataPropertyValue] )
  : ReifiedRelationshipInstance
  
  // ReifiedRelationshipInstanceDomain
  
  def createReifiedRelationshipInstanceDomain
  ( descriptionBox: scala.Option[java.util.UUID] /* reference to a DescriptionBox */,
    reifiedRelationshipInstance: ReifiedRelationshipInstance,
    domain: ConceptualEntitySingletonInstance,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : ReifiedRelationshipInstanceDomain
  
  // ReifiedRelationshipInstanceRange
  
  def createReifiedRelationshipInstanceRange
  ( descriptionBox: scala.Option[java.util.UUID] /* reference to a DescriptionBox */,
    reifiedRelationshipInstance: ReifiedRelationshipInstance,
    range: ConceptualEntitySingletonInstance,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : ReifiedRelationshipInstanceRange
  
  // ReifiedRelationshipSpecializationAxiom
  
  def createReifiedRelationshipSpecializationAxiom
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    superRelationship: ReifiedRelationship,
    subRelationship: ReifiedRelationship )
  : ReifiedRelationshipSpecializationAxiom
  
  // RootConceptTaxonomyAxiom
  
  def createRootConceptTaxonomyAxiom
  ( bundle: scala.Option[java.util.UUID] /* reference to a Bundle */,
    root: Concept )
  : RootConceptTaxonomyAxiom
  
  // Scalar
  
  def createScalar
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : Scalar
  
  // ScalarDataProperty
  
  def createScalarDataProperty
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    domain: Structure,
    range: DataRange,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : ScalarDataProperty
  
  // ScalarDataPropertyValue
  
  def createScalarDataPropertyValue
  ( singletonInstance: scala.Option[java.util.UUID] /* reference to a SingletonInstance */,
    scalarDataProperty: DataRelationshipToScalar,
    name: gov.nasa.jpl.imce.oml.tables.LocalName,
    scalarPropertyValue: scala.Predef.String )
  : ScalarDataPropertyValue
  
  // ScalarOneOfLiteralAxiom
  
  def createScalarOneOfLiteralAxiom
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    axiom: ScalarOneOfRestriction,
    value: gov.nasa.jpl.imce.oml.tables.LexicalValue )
  : ScalarOneOfLiteralAxiom
  
  // ScalarOneOfRestriction
  
  def createScalarOneOfRestriction
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    restrictedRange: DataRange,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : ScalarOneOfRestriction
  
  // SpecificDisjointConceptAxiom
  
  def createSpecificDisjointConceptAxiom
  ( bundle: scala.Option[java.util.UUID] /* reference to a Bundle */,
    disjointTaxonomyParent: ConceptTreeDisjunction,
    disjointLeaf: Concept )
  : SpecificDisjointConceptAxiom
  
  // StringScalarRestriction
  
  def createStringScalarRestriction
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    restrictedRange: DataRange,
    length: scala.Option[scala.Int],
    minLength: scala.Option[scala.Int],
    maxLength: scala.Option[scala.Int],
    name: gov.nasa.jpl.imce.oml.tables.LocalName,
    pattern: scala.Option[gov.nasa.jpl.imce.oml.tables.Pattern] )
  : StringScalarRestriction
  
  // Structure
  
  def createStructure
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : Structure
  
  // StructuredDataProperty
  
  def createStructuredDataProperty
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    domain: Structure,
    range: Structure,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : StructuredDataProperty
  
  // StructuredDataPropertyValue
  
  def createStructuredDataPropertyValue
  ( singletonInstance: scala.Option[java.util.UUID] /* reference to a SingletonInstance */,
    structuredDataProperty: DataRelationshipToStructure,
    structuredPropertyTuple: DataStructureTuple,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : StructuredDataPropertyValue
  
  // SynonymScalarRestriction
  
  def createSynonymScalarRestriction
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    restrictedRange: DataRange,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : SynonymScalarRestriction
  
  // TerminologyExtensionAxiom
  
  def createTerminologyExtensionAxiom
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    extendedTerminology: TerminologyBox )
  : TerminologyExtensionAxiom
  
  def copyTerminologyExtensionAxiom_tbox
  ( that: TerminologyExtensionAxiom,
    tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */ )
  : TerminologyExtensionAxiom
  
  def copyTerminologyExtensionAxiom_extendedTerminology
  ( that: TerminologyExtensionAxiom,
    extendedTerminology: TerminologyBox )
  : TerminologyExtensionAxiom
  
  // TerminologyGraph
  
  def createTerminologyGraph
  ( extent: scala.Option[java.util.UUID] /* reference to a Extent */,
    kind: gov.nasa.jpl.imce.oml.tables.TerminologyKind,
    iri: gov.nasa.jpl.imce.oml.tables.IRI,
    annotations: scala.collection.immutable.SortedSet[Annotation],
    boxStatements: scala.collection.immutable.SortedSet[TerminologyBoxStatement],
    boxAxioms: scala.collection.immutable.SortedSet[TerminologyBoxAxiom] )
  : TerminologyGraph
  
  // TerminologyNestingAxiom
  
  def createTerminologyNestingAxiom
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    nestingTerminology: TerminologyBox,
    nestingContext: Concept )
  : TerminologyNestingAxiom
  
  def copyTerminologyNestingAxiom_tbox
  ( that: TerminologyNestingAxiom,
    tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */ )
  : TerminologyNestingAxiom
  
  def copyTerminologyNestingAxiom_nestingTerminology
  ( that: TerminologyNestingAxiom,
    nestingTerminology: TerminologyBox )
  : TerminologyNestingAxiom
  
  // TimeScalarRestriction
  
  def createTimeScalarRestriction
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
    restrictedRange: DataRange,
    minExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LexicalTime],
    minInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LexicalTime],
    maxExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LexicalTime],
    maxInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LexicalTime],
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : TimeScalarRestriction
  
  // UnreifiedRelationship
  
  def createUnreifiedRelationship
  ( tbox: scala.Option[java.util.UUID] /* reference to a TerminologyBox */,
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
  : UnreifiedRelationship
  
  // UnreifiedRelationshipInstanceTuple
  
  def createUnreifiedRelationshipInstanceTuple
  ( descriptionBox: scala.Option[java.util.UUID] /* reference to a DescriptionBox */,
    unreifiedRelationship: UnreifiedRelationship,
    domain: ConceptualEntitySingletonInstance,
    range: ConceptualEntitySingletonInstance,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : UnreifiedRelationshipInstanceTuple
  
}
