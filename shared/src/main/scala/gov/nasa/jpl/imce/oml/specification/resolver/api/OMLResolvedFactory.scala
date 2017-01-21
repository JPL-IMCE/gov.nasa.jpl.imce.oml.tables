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

package gov.nasa.jpl.imce.oml.specification.resolver.api

trait OMLResolvedFactory {
	
  // Annotation
  
  def createAnnotation
  ( terminology: TerminologyBox,
    subject: TerminologyThing,
    property: AnnotationProperty,
    value: scala.Predef.String )
  : Annotation
  
  // AnnotationEntry
  
  def createAnnotationEntry
  ( terminology: TerminologyBox,
    subject: TerminologyThing,
    value: scala.Predef.String )
  : AnnotationEntry
  
  // AnnotationProperty
  
  def createAnnotationProperty
  ( uuid: java.util.UUID,
    iri: gov.nasa.jpl.imce.oml.specification.tables.IRI,
    abbrevIRI: gov.nasa.jpl.imce.oml.specification.tables.AbbrevIRI )
  : AnnotationProperty
  
  // AnnotationPropertyTable
  
  def createAnnotationPropertyTable
  ( key: AnnotationProperty,
    value: scala.collection.immutable.SortedSet[AnnotationEntry] )
  : AnnotationPropertyTable
  
  // AnonymousConceptTaxonomyAxiom
  
  def createAnonymousConceptTaxonomyAxiom
  ( uuid: java.util.UUID,
    bundle: Bundle,
    disjointTaxonomyParent: ConceptTreeDisjunction )
  : AnonymousConceptTaxonomyAxiom
  
  // Aspect
  
  def createAspect
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    name: gov.nasa.jpl.imce.oml.specification.tables.LocalName )
  : Aspect
  
  // AspectSpecializationAxiom
  
  def createAspectSpecializationAxiom
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    subEntity: Entity,
    superAspect: Aspect )
  : AspectSpecializationAxiom
  
  // BinaryScalarRestriction
  
  def createBinaryScalarRestriction
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    name: gov.nasa.jpl.imce.oml.specification.tables.LocalName,
    length: scala.Option[scala.Int],
    maxLength: scala.Option[scala.Int],
    minLength: scala.Option[scala.Int],
    restrictedRange: DataRange )
  : BinaryScalarRestriction
  
  // Bundle
  
  def createBundle
  ( uuid: java.util.UUID,
    kind: gov.nasa.jpl.imce.oml.specification.tables.TerminologyGraphKind,
    name: gov.nasa.jpl.imce.oml.specification.tables.LocalName,
    iri: gov.nasa.jpl.imce.oml.specification.tables.IRI,
    nsPrefix: gov.nasa.jpl.imce.oml.specification.tables.NamespacePrefix,
    annotations: scala.collection.immutable.SortedSet[Annotation],
    boxStatements: scala.collection.immutable.SortedSet[TerminologyBoxStatement],
    bundleStatements: scala.collection.immutable.SortedSet[TerminologyBundleStatement],
    terminologyBoxAxioms: scala.collection.immutable.SortedSet[TerminologyBoxAxiom],
    terminologyBundleAxioms: scala.collection.immutable.SortedSet[TerminologyBundleAxiom] )
  : Bundle
  
  // BundledTerminologyAxiom
  
  def createBundledTerminologyAxiom
  ( uuid: java.util.UUID,
    bundledTerminology: TerminologyBox,
    terminologyBundle: Bundle )
  : BundledTerminologyAxiom
  
  def copyBundledTerminologyAxiom_bundledTerminology
  ( that: BundledTerminologyAxiom,
    bundledTerminology: TerminologyBox )
  : BundledTerminologyAxiom
  
  def copyBundledTerminologyAxiom_terminologyBundle
  ( that: BundledTerminologyAxiom,
    terminologyBundle: Bundle )
  : BundledTerminologyAxiom
  
  // Concept
  
  def createConcept
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    isAbstract: scala.Boolean,
    name: gov.nasa.jpl.imce.oml.specification.tables.LocalName )
  : Concept
  
  // ConceptDesignationTerminologyAxiom
  
  def createConceptDesignationTerminologyAxiom
  ( uuid: java.util.UUID,
    terminology: TerminologyBox,
    designatedConcept: Concept,
    designatedTerminology: TerminologyBox )
  : ConceptDesignationTerminologyAxiom
  
  def copyConceptDesignationTerminologyAxiom_terminology
  ( that: ConceptDesignationTerminologyAxiom,
    terminology: TerminologyBox )
  : ConceptDesignationTerminologyAxiom
  
  def copyConceptDesignationTerminologyAxiom_designatedTerminology
  ( that: ConceptDesignationTerminologyAxiom,
    designatedTerminology: TerminologyBox )
  : ConceptDesignationTerminologyAxiom
  
  // ConceptSpecializationAxiom
  
  def createConceptSpecializationAxiom
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    subConcept: Concept,
    superConcept: Concept )
  : ConceptSpecializationAxiom
  
  // EntityExistentialRestrictionAxiom
  
  def createEntityExistentialRestrictionAxiom
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    restrictedDomain: Entity,
    restrictedRange: Entity,
    restrictedRelation: ReifiedRelationship )
  : EntityExistentialRestrictionAxiom
  
  // EntityScalarDataProperty
  
  def createEntityScalarDataProperty
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    name: gov.nasa.jpl.imce.oml.specification.tables.LocalName,
    domain: Entity,
    range: DataRange )
  : EntityScalarDataProperty
  
  // EntityScalarDataPropertyExistentialRestrictionAxiom
  
  def createEntityScalarDataPropertyExistentialRestrictionAxiom
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    restrictedEntity: Entity,
    scalarProperty: EntityScalarDataProperty,
    scalarRestriction: DataRange )
  : EntityScalarDataPropertyExistentialRestrictionAxiom
  
  // EntityScalarDataPropertyParticularRestrictionAxiom
  
  def createEntityScalarDataPropertyParticularRestrictionAxiom
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    literalValue: gov.nasa.jpl.imce.oml.specification.tables.LexicalValue,
    restrictedEntity: Entity,
    scalarProperty: EntityScalarDataProperty )
  : EntityScalarDataPropertyParticularRestrictionAxiom
  
  // EntityScalarDataPropertyUniversalRestrictionAxiom
  
  def createEntityScalarDataPropertyUniversalRestrictionAxiom
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    restrictedEntity: Entity,
    scalarProperty: EntityScalarDataProperty,
    scalarRestriction: DataRange )
  : EntityScalarDataPropertyUniversalRestrictionAxiom
  
  // EntityStructuredDataProperty
  
  def createEntityStructuredDataProperty
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    name: gov.nasa.jpl.imce.oml.specification.tables.LocalName,
    domain: Entity,
    range: Structure )
  : EntityStructuredDataProperty
  
  // EntityUniversalRestrictionAxiom
  
  def createEntityUniversalRestrictionAxiom
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    restrictedDomain: Entity,
    restrictedRange: Entity,
    restrictedRelation: ReifiedRelationship )
  : EntityUniversalRestrictionAxiom
  
  // IRIScalarRestriction
  
  def createIRIScalarRestriction
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    name: gov.nasa.jpl.imce.oml.specification.tables.LocalName,
    length: scala.Option[scala.Int],
    maxLength: scala.Option[scala.Int],
    minLength: scala.Option[scala.Int],
    pattern: scala.Option[gov.nasa.jpl.imce.oml.specification.tables.Pattern],
    restrictedRange: DataRange )
  : IRIScalarRestriction
  
  // NumericScalarRestriction
  
  def createNumericScalarRestriction
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    name: gov.nasa.jpl.imce.oml.specification.tables.LocalName,
    maxExclusive: scala.Option[gov.nasa.jpl.imce.oml.specification.tables.LexicalNumber],
    maxInclusive: scala.Option[gov.nasa.jpl.imce.oml.specification.tables.LexicalNumber],
    minExclusive: scala.Option[gov.nasa.jpl.imce.oml.specification.tables.LexicalNumber],
    minInclusive: scala.Option[gov.nasa.jpl.imce.oml.specification.tables.LexicalNumber],
    restrictedRange: DataRange )
  : NumericScalarRestriction
  
  // PlainLiteralScalarRestriction
  
  def createPlainLiteralScalarRestriction
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    name: gov.nasa.jpl.imce.oml.specification.tables.LocalName,
    language: scala.Option[gov.nasa.jpl.imce.oml.specification.tables.Language],
    length: scala.Option[scala.Int],
    maxLength: scala.Option[scala.Int],
    minLength: scala.Option[scala.Int],
    pattern: scala.Option[gov.nasa.jpl.imce.oml.specification.tables.Pattern],
    restrictedRange: DataRange )
  : PlainLiteralScalarRestriction
  
  // ReifiedRelationship
  
  def createReifiedRelationship
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    isAbstract: scala.Boolean,
    name: gov.nasa.jpl.imce.oml.specification.tables.LocalName,
    unreifiedPropertyName: gov.nasa.jpl.imce.oml.specification.tables.LocalName,
    unreifiedInversePropertyName: scala.Option[gov.nasa.jpl.imce.oml.specification.tables.LocalName],
    isAsymmetric: scala.Boolean,
    isEssential: scala.Boolean,
    isFunctional: scala.Boolean,
    isInverseEssential: scala.Boolean,
    isInverseFunctional: scala.Boolean,
    isIrreflexive: scala.Boolean,
    isReflexive: scala.Boolean,
    isSymmetric: scala.Boolean,
    isTransitive: scala.Boolean,
    source: Entity,
    target: Entity )
  : ReifiedRelationship
  
  // ReifiedRelationshipSpecializationAxiom
  
  def createReifiedRelationshipSpecializationAxiom
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    subRelationship: ReifiedRelationship,
    superRelationship: ReifiedRelationship )
  : ReifiedRelationshipSpecializationAxiom
  
  // RootConceptTaxonomyAxiom
  
  def createRootConceptTaxonomyAxiom
  ( uuid: java.util.UUID,
    bundle: Bundle,
    root: Concept )
  : RootConceptTaxonomyAxiom
  
  // Scalar
  
  def createScalar
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    name: gov.nasa.jpl.imce.oml.specification.tables.LocalName )
  : Scalar
  
  // ScalarDataProperty
  
  def createScalarDataProperty
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    name: gov.nasa.jpl.imce.oml.specification.tables.LocalName,
    domain: Structure,
    range: DataRange )
  : ScalarDataProperty
  
  // ScalarOneOfLiteralAxiom
  
  def createScalarOneOfLiteralAxiom
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    axiom: ScalarOneOfRestriction,
    value: gov.nasa.jpl.imce.oml.specification.tables.LexicalValue )
  : ScalarOneOfLiteralAxiom
  
  // ScalarOneOfRestriction
  
  def createScalarOneOfRestriction
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    name: gov.nasa.jpl.imce.oml.specification.tables.LocalName,
    restrictedRange: DataRange )
  : ScalarOneOfRestriction
  
  // SpecificDisjointConceptAxiom
  
  def createSpecificDisjointConceptAxiom
  ( uuid: java.util.UUID,
    bundle: Bundle,
    disjointLeaf: Concept,
    disjointTaxonomyParent: ConceptTreeDisjunction )
  : SpecificDisjointConceptAxiom
  
  // StringScalarRestriction
  
  def createStringScalarRestriction
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    name: gov.nasa.jpl.imce.oml.specification.tables.LocalName,
    length: scala.Option[scala.Int],
    maxLength: scala.Option[scala.Int],
    minLength: scala.Option[scala.Int],
    pattern: scala.Option[gov.nasa.jpl.imce.oml.specification.tables.Pattern],
    restrictedRange: DataRange )
  : StringScalarRestriction
  
  // Structure
  
  def createStructure
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    name: gov.nasa.jpl.imce.oml.specification.tables.LocalName )
  : Structure
  
  // StructuredDataProperty
  
  def createStructuredDataProperty
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    name: gov.nasa.jpl.imce.oml.specification.tables.LocalName,
    domain: Structure,
    range: Structure )
  : StructuredDataProperty
  
  // SynonymScalarRestriction
  
  def createSynonymScalarRestriction
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    name: gov.nasa.jpl.imce.oml.specification.tables.LocalName,
    restrictedRange: DataRange )
  : SynonymScalarRestriction
  
  // TerminologyExtensionAxiom
  
  def createTerminologyExtensionAxiom
  ( uuid: java.util.UUID,
    terminology: TerminologyBox,
    extendedTerminology: TerminologyBox )
  : TerminologyExtensionAxiom
  
  def copyTerminologyExtensionAxiom_terminology
  ( that: TerminologyExtensionAxiom,
    terminology: TerminologyBox )
  : TerminologyExtensionAxiom
  
  def copyTerminologyExtensionAxiom_extendedTerminology
  ( that: TerminologyExtensionAxiom,
    extendedTerminology: TerminologyBox )
  : TerminologyExtensionAxiom
  
  // TerminologyExtent
  
  def createTerminologyExtent
  ( annotationProperties: scala.collection.immutable.SortedSet[AnnotationProperty],
    bundles: scala.collection.immutable.SortedSet[Bundle],
    terminologyGraphs: scala.collection.immutable.SortedSet[TerminologyGraph] )
  : TerminologyExtent
  
  def copyTerminologyExtent_annotationProperties
  ( that: TerminologyExtent,
    annotationProperties: scala.collection.immutable.SortedSet[AnnotationProperty] )
  : TerminologyExtent
  
  def copyTerminologyExtent_bundles
  ( that: TerminologyExtent,
    bundles: scala.collection.immutable.SortedSet[Bundle] )
  : TerminologyExtent
  
  def copyTerminologyExtent_terminologyGraphs
  ( that: TerminologyExtent,
    terminologyGraphs: scala.collection.immutable.SortedSet[TerminologyGraph] )
  : TerminologyExtent
  
  // TerminologyGraph
  
  def createTerminologyGraph
  ( uuid: java.util.UUID,
    kind: gov.nasa.jpl.imce.oml.specification.tables.TerminologyGraphKind,
    name: gov.nasa.jpl.imce.oml.specification.tables.LocalName,
    iri: gov.nasa.jpl.imce.oml.specification.tables.IRI,
    nsPrefix: gov.nasa.jpl.imce.oml.specification.tables.NamespacePrefix,
    annotations: scala.collection.immutable.SortedSet[Annotation],
    boxStatements: scala.collection.immutable.SortedSet[TerminologyBoxStatement],
    terminologyBoxAxioms: scala.collection.immutable.SortedSet[TerminologyBoxAxiom] )
  : TerminologyGraph
  
  // TerminologyNestingAxiom
  
  def createTerminologyNestingAxiom
  ( uuid: java.util.UUID,
    terminology: TerminologyBox,
    nestingContext: Concept,
    nestingTerminology: TerminologyBox )
  : TerminologyNestingAxiom
  
  def copyTerminologyNestingAxiom_terminology
  ( that: TerminologyNestingAxiom,
    terminology: TerminologyBox )
  : TerminologyNestingAxiom
  
  def copyTerminologyNestingAxiom_nestingTerminology
  ( that: TerminologyNestingAxiom,
    nestingTerminology: TerminologyBox )
  : TerminologyNestingAxiom
  
  // TimeScalarRestriction
  
  def createTimeScalarRestriction
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    name: gov.nasa.jpl.imce.oml.specification.tables.LocalName,
    maxExclusive: scala.Option[gov.nasa.jpl.imce.oml.specification.tables.LexicalTime],
    maxInclusive: scala.Option[gov.nasa.jpl.imce.oml.specification.tables.LexicalTime],
    minExclusive: scala.Option[gov.nasa.jpl.imce.oml.specification.tables.LexicalTime],
    minInclusive: scala.Option[gov.nasa.jpl.imce.oml.specification.tables.LexicalTime],
    restrictedRange: DataRange )
  : TimeScalarRestriction
  
  // UnreifiedRelationship
  
  def createUnreifiedRelationship
  ( graph: TerminologyBox,
    uuid: java.util.UUID,
    name: gov.nasa.jpl.imce.oml.specification.tables.LocalName,
    isAsymmetric: scala.Boolean,
    isEssential: scala.Boolean,
    isFunctional: scala.Boolean,
    isInverseEssential: scala.Boolean,
    isInverseFunctional: scala.Boolean,
    isIrreflexive: scala.Boolean,
    isReflexive: scala.Boolean,
    isSymmetric: scala.Boolean,
    isTransitive: scala.Boolean,
    source: Entity,
    target: Entity )
  : UnreifiedRelationship
  
}
