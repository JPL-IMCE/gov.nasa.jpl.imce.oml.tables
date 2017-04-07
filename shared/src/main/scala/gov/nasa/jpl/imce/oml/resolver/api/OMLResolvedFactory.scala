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

import gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator

trait OMLResolvedFactory {
  
  val oug: OMLUUIDGenerator
  import oug._
  
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
  = {
    val uuid: java.util.UUID = namespaceUUID(iri.toString)
    createAnnotationProperty( extent, uuid,  iri,  abbrevIRI )
  }
  
  def createAnnotationProperty
  ( extent: Extent,
    uuid: java.util.UUID,
    iri: gov.nasa.jpl.imce.oml.tables.IRI,
    abbrevIRI: gov.nasa.jpl.imce.oml.tables.AbbrevIRI )
  : (Extent, AnnotationProperty)
  
  // AnonymousConceptTaxonomyAxiom
  def createAnonymousConceptTaxonomyAxiom
  ( extent: Extent,
    bundle: Bundle,
    disjointTaxonomyParent: ConceptTreeDisjunction )
  : (Extent, AnonymousConceptTaxonomyAxiom)
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = derivedUUID("AnonymousConceptTaxonomyAxiom",  "bundle" -> bundle.uuid)
    createAnonymousConceptTaxonomyAxiom( extent, uuid,  bundle,  disjointTaxonomyParent )
  }
  
  def createAnonymousConceptTaxonomyAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
    bundle: Bundle,
    disjointTaxonomyParent: ConceptTreeDisjunction )
  : (Extent, AnonymousConceptTaxonomyAxiom)
  
  // Aspect
  def createAspect
  ( extent: Extent,
    tbox: TerminologyBox,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, Aspect)
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.toString,  "name" -> name)
    createAspect( extent, uuid,  tbox,  name )
  }
  
  def createAspect
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = derivedUUID("AspectSpecializationAxiom",  "tbox" -> tbox.uuid,  "superAspect" -> superAspect.uuid,  "subEntity" -> subEntity.uuid)
    createAspectSpecializationAxiom( extent, uuid,  tbox,  superAspect,  subEntity )
  }
  
  def createAspectSpecializationAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.toString,  "name" -> name)
    createBinaryScalarRestriction( extent, uuid,  tbox,  restrictedRange,  length,  minLength,  maxLength,  name )
  }
  
  def createBinaryScalarRestriction
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    val uuid: java.util.UUID = namespaceUUID(iri.toString)
    createBundle( extent, uuid,  kind,  iri )
  }
  
  def createBundle
  ( extent: Extent,
    uuid: java.util.UUID,
    kind: gov.nasa.jpl.imce.oml.tables.TerminologyKind,
    iri: gov.nasa.jpl.imce.oml.tables.IRI )
  : (Extent, Bundle)
  
  // BundledTerminologyAxiom
  def createBundledTerminologyAxiom
  ( extent: Extent,
    bundledTerminology: TerminologyBox,
    bundle: Bundle )
  : (Extent, BundledTerminologyAxiom)
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = derivedUUID("BundledTerminologyAxiom",  "bundledTerminology" -> bundledTerminology.uuid,  "bundle" -> bundle.uuid)
    createBundledTerminologyAxiom( extent, uuid,  bundledTerminology,  bundle )
  }
  
  def createBundledTerminologyAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
    bundledTerminology: TerminologyBox,
    bundle: Bundle )
  : (Extent, BundledTerminologyAxiom)
  
  // Concept
  def createConcept
  ( extent: Extent,
    tbox: TerminologyBox,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, Concept)
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.toString,  "name" -> name)
    createConcept( extent, uuid,  tbox,  name )
  }
  
  def createConcept
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = derivedUUID("ConceptDesignationTerminologyAxiom",  "tbox" -> tbox.uuid,  "designatedConcept" -> designatedConcept.uuid,  "designatedTerminology" -> designatedTerminology.uuid)
    createConceptDesignationTerminologyAxiom( extent, uuid,  tbox,  designatedConcept,  designatedTerminology )
  }
  
  def createConceptDesignationTerminologyAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(descriptionBox.toString,  "name" -> name)
    createConceptInstance( extent, uuid,  descriptionBox,  singletonConceptClassifier,  name )
  }
  
  def createConceptInstance
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = derivedUUID("ConceptSpecializationAxiom",  "tbox" -> tbox.uuid,  "superConcept" -> superConcept.uuid,  "subConcept" -> subConcept.uuid)
    createConceptSpecializationAxiom( extent, uuid,  tbox,  superConcept,  subConcept )
  }
  
  def createConceptSpecializationAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(structuredDataPropertyValue.toString,  "name" -> name)
    createDataStructureTuple( extent, uuid,  dataStructureType,  structuredDataPropertyValue,  name )
  }
  
  def createDataStructureTuple
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    val uuid: java.util.UUID = namespaceUUID(iri.toString)
    createDescriptionBox( extent, uuid,  kind,  iri )
  }
  
  def createDescriptionBox
  ( extent: Extent,
    uuid: java.util.UUID,
    kind: gov.nasa.jpl.imce.oml.tables.DescriptionKind,
    iri: gov.nasa.jpl.imce.oml.tables.IRI )
  : (Extent, DescriptionBox)
  
  // DescriptionBoxExtendsClosedWorldDefinitions
  def createDescriptionBoxExtendsClosedWorldDefinitions
  ( extent: Extent,
    descriptionBox: DescriptionBox,
    closedWorldDefinitions: TerminologyBox )
  : (Extent, DescriptionBoxExtendsClosedWorldDefinitions)
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = derivedUUID("DescriptionBoxExtendsClosedWorldDefinitions",  "descriptionBox" -> descriptionBox.uuid,  "closedWorldDefinitions" -> closedWorldDefinitions.uuid)
    createDescriptionBoxExtendsClosedWorldDefinitions( extent, uuid,  descriptionBox,  closedWorldDefinitions )
  }
  
  def createDescriptionBoxExtendsClosedWorldDefinitions
  ( extent: Extent,
    uuid: java.util.UUID,
    descriptionBox: DescriptionBox,
    closedWorldDefinitions: TerminologyBox )
  : (Extent, DescriptionBoxExtendsClosedWorldDefinitions)
  
  // DescriptionBoxRefinement
  def createDescriptionBoxRefinement
  ( extent: Extent,
    refiningDescriptionBox: DescriptionBox,
    refinedDescriptionBox: DescriptionBox )
  : (Extent, DescriptionBoxRefinement)
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = derivedUUID("DescriptionBoxRefinement",  "refiningDescriptionBox" -> refiningDescriptionBox.uuid,  "refinedDescriptionBox" -> refinedDescriptionBox.uuid)
    createDescriptionBoxRefinement( extent, uuid,  refiningDescriptionBox,  refinedDescriptionBox )
  }
  
  def createDescriptionBoxRefinement
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = derivedUUID("EntityExistentialRestrictionAxiom",  "tbox" -> tbox.uuid,  "restrictedRelation" -> restrictedRelation.uuid,  "restrictedDomain" -> restrictedDomain.uuid,  "restrictedRange" -> restrictedRange.uuid)
    createEntityExistentialRestrictionAxiom( extent, uuid,  tbox,  restrictedRelation,  restrictedDomain,  restrictedRange )
  }
  
  def createEntityExistentialRestrictionAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.toString,  "name" -> name)
    createEntityScalarDataProperty( extent, uuid,  tbox,  domain,  range,  isIdentityCriteria,  name )
  }
  
  def createEntityScalarDataProperty
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = derivedUUID("EntityScalarDataPropertyExistentialRestrictionAxiom",  "tbox" -> tbox.uuid,  "restrictedEntity" -> restrictedEntity.uuid,  "scalarProperty" -> scalarProperty.uuid,  "scalarRestriction" -> scalarRestriction.uuid)
    createEntityScalarDataPropertyExistentialRestrictionAxiom( extent, uuid,  tbox,  restrictedEntity,  scalarProperty,  scalarRestriction )
  }
  
  def createEntityScalarDataPropertyExistentialRestrictionAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = derivedUUID("EntityScalarDataPropertyParticularRestrictionAxiom",  "tbox" -> tbox.uuid,  "restrictedEntity" -> restrictedEntity.uuid,  "scalarProperty" -> scalarProperty.uuid)
    createEntityScalarDataPropertyParticularRestrictionAxiom( extent, uuid,  tbox,  restrictedEntity,  scalarProperty,  literalValue )
  }
  
  def createEntityScalarDataPropertyParticularRestrictionAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = derivedUUID("EntityScalarDataPropertyUniversalRestrictionAxiom",  "tbox" -> tbox.uuid,  "restrictedEntity" -> restrictedEntity.uuid,  "scalarProperty" -> scalarProperty.uuid,  "scalarRestriction" -> scalarRestriction.uuid)
    createEntityScalarDataPropertyUniversalRestrictionAxiom( extent, uuid,  tbox,  restrictedEntity,  scalarProperty,  scalarRestriction )
  }
  
  def createEntityScalarDataPropertyUniversalRestrictionAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.toString,  "name" -> name)
    createEntityStructuredDataProperty( extent, uuid,  tbox,  domain,  range,  isIdentityCriteria,  name )
  }
  
  def createEntityStructuredDataProperty
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = derivedUUID("EntityUniversalRestrictionAxiom",  "tbox" -> tbox.uuid,  "restrictedRelation" -> restrictedRelation.uuid,  "restrictedDomain" -> restrictedDomain.uuid,  "restrictedRange" -> restrictedRange.uuid)
    createEntityUniversalRestrictionAxiom( extent, uuid,  tbox,  restrictedRelation,  restrictedDomain,  restrictedRange )
  }
  
  def createEntityUniversalRestrictionAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.toString,  "name" -> name)
    createIRIScalarRestriction( extent, uuid,  tbox,  restrictedRange,  length,  minLength,  maxLength,  name,  pattern )
  }
  
  def createIRIScalarRestriction
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.toString,  "name" -> name)
    createNumericScalarRestriction( extent, uuid,  tbox,  restrictedRange,  minExclusive,  minInclusive,  maxExclusive,  maxInclusive,  name )
  }
  
  def createNumericScalarRestriction
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.toString,  "name" -> name)
    createPlainLiteralScalarRestriction( extent, uuid,  tbox,  restrictedRange,  length,  minLength,  maxLength,  name,  langRange,  pattern )
  }
  
  def createPlainLiteralScalarRestriction
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.toString,  "name" -> name)
    createReifiedRelationship( extent, uuid,  tbox,  source,  target,  isAsymmetric,  isEssential,  isFunctional,  isInverseEssential,  isInverseFunctional,  isIrreflexive,  isReflexive,  isSymmetric,  isTransitive,  name,  unreifiedPropertyName,  unreifiedInversePropertyName )
  }
  
  def createReifiedRelationship
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(descriptionBox.toString,  "name" -> name)
    createReifiedRelationshipInstance( extent, uuid,  descriptionBox,  singletonReifiedRelationshipClassifier,  name )
  }
  
  def createReifiedRelationshipInstance
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(descriptionBox.toString,  "name" -> name)
    createReifiedRelationshipInstanceDomain( extent, uuid,  descriptionBox,  reifiedRelationshipInstance,  domain,  name )
  }
  
  def createReifiedRelationshipInstanceDomain
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(descriptionBox.toString,  "name" -> name)
    createReifiedRelationshipInstanceRange( extent, uuid,  descriptionBox,  reifiedRelationshipInstance,  range,  name )
  }
  
  def createReifiedRelationshipInstanceRange
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = derivedUUID("ReifiedRelationshipSpecializationAxiom",  "tbox" -> tbox.uuid,  "superRelationship" -> superRelationship.uuid,  "subRelationship" -> subRelationship.uuid)
    createReifiedRelationshipSpecializationAxiom( extent, uuid,  tbox,  superRelationship,  subRelationship )
  }
  
  def createReifiedRelationshipSpecializationAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = derivedUUID("RootConceptTaxonomyAxiom",  "bundle" -> bundle.uuid,  "root" -> root.uuid)
    createRootConceptTaxonomyAxiom( extent, uuid,  bundle,  root )
  }
  
  def createRootConceptTaxonomyAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
    bundle: Bundle,
    root: Concept )
  : (Extent, RootConceptTaxonomyAxiom)
  
  // Scalar
  def createScalar
  ( extent: Extent,
    tbox: TerminologyBox,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, Scalar)
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.toString,  "name" -> name)
    createScalar( extent, uuid,  tbox,  name )
  }
  
  def createScalar
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.toString,  "name" -> name)
    createScalarDataProperty( extent, uuid,  tbox,  domain,  range,  name )
  }
  
  def createScalarDataProperty
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(singletonInstance.toString,  "name" -> name)
    createScalarDataPropertyValue( extent, uuid,  singletonInstance,  scalarDataProperty,  name,  scalarPropertyValue )
  }
  
  def createScalarDataPropertyValue
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val implicitUUID: java.util.UUID = derivedUUID("ScalarOneOfLiteralAxiom",  "tbox" -> tbox.uuid,  "axiom" -> axiom.uuid)
    createScalarOneOfLiteralAxiom( extent, implicitUUID,  tbox,  axiom,  value )
  }
  
  def createScalarOneOfLiteralAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.toString,  "name" -> name)
    createScalarOneOfRestriction( extent, uuid,  tbox,  restrictedRange,  name )
  }
  
  def createScalarOneOfRestriction
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = derivedUUID("SpecificDisjointConceptAxiom",  "bundle" -> bundle.uuid,  "disjointLeaf" -> disjointLeaf.uuid)
    createSpecificDisjointConceptAxiom( extent, uuid,  bundle,  disjointTaxonomyParent,  disjointLeaf )
  }
  
  def createSpecificDisjointConceptAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.toString,  "name" -> name)
    createStringScalarRestriction( extent, uuid,  tbox,  restrictedRange,  length,  minLength,  maxLength,  name,  pattern )
  }
  
  def createStringScalarRestriction
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.toString,  "name" -> name)
    createStructure( extent, uuid,  tbox,  name )
  }
  
  def createStructure
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.toString,  "name" -> name)
    createStructuredDataProperty( extent, uuid,  tbox,  domain,  range,  name )
  }
  
  def createStructuredDataProperty
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(singletonInstance.toString,  "name" -> name)
    createStructuredDataPropertyValue( extent, uuid,  singletonInstance,  structuredDataProperty,  name )
  }
  
  def createStructuredDataPropertyValue
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.toString,  "name" -> name)
    createSynonymScalarRestriction( extent, uuid,  tbox,  restrictedRange,  name )
  }
  
  def createSynonymScalarRestriction
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = derivedUUID("TerminologyExtensionAxiom",  "tbox" -> tbox.uuid,  "extendedTerminology" -> extendedTerminology.uuid)
    createTerminologyExtensionAxiom( extent, uuid,  tbox,  extendedTerminology )
  }
  
  def createTerminologyExtensionAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
    tbox: TerminologyBox,
    extendedTerminology: TerminologyBox )
  : (Extent, TerminologyExtensionAxiom)
  
  // TerminologyGraph
  def createTerminologyGraph
  ( extent: Extent,
    kind: gov.nasa.jpl.imce.oml.tables.TerminologyKind,
    iri: gov.nasa.jpl.imce.oml.tables.IRI )
  : (Extent, TerminologyGraph)
  = {
    val uuid: java.util.UUID = namespaceUUID(iri.toString)
    createTerminologyGraph( extent, uuid,  kind,  iri )
  }
  
  def createTerminologyGraph
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = derivedUUID("TerminologyNestingAxiom",  "tbox" -> tbox.uuid,  "nestingTerminology" -> nestingTerminology.uuid,  "nestingContext" -> nestingContext.uuid)
    createTerminologyNestingAxiom( extent, uuid,  tbox,  nestingTerminology,  nestingContext )
  }
  
  def createTerminologyNestingAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.toString,  "name" -> name)
    createTimeScalarRestriction( extent, uuid,  tbox,  restrictedRange,  minExclusive,  minInclusive,  maxExclusive,  maxInclusive,  name )
  }
  
  def createTimeScalarRestriction
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.toString,  "name" -> name)
    createUnreifiedRelationship( extent, uuid,  tbox,  source,  target,  isAsymmetric,  isEssential,  isFunctional,  isInverseEssential,  isInverseFunctional,  isIrreflexive,  isReflexive,  isSymmetric,  isTransitive,  name )
  }
  
  def createUnreifiedRelationship
  ( extent: Extent,
    uuid: java.util.UUID,
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
  = {
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(descriptionBox.toString,  "name" -> name)
    createUnreifiedRelationshipInstanceTuple( extent, uuid,  descriptionBox,  unreifiedRelationship,  domain,  range,  name )
  }
  
  def createUnreifiedRelationshipInstanceTuple
  ( extent: Extent,
    uuid: java.util.UUID,
    descriptionBox: DescriptionBox,
    unreifiedRelationship: UnreifiedRelationship,
    domain: ConceptualEntitySingletonInstance,
    range: ConceptualEntitySingletonInstance,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, UnreifiedRelationshipInstanceTuple)
  
}
