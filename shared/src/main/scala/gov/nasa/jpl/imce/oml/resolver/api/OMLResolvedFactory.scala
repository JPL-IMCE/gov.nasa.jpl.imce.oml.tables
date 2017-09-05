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
  
  // AnnotationPropertyValue
  def createAnnotationPropertyValue
  ( extent: Extent,
    subject: Element,
    property: AnnotationProperty,
    value: scala.Predef.String )
  : (Extent, AnnotationPropertyValue)
  = {
  	// implicitly derived uuid...
    import scala.Predef.ArrowAssoc
    val implicitUUID: java.util.UUID = namespaceUUID("AnnotationPropertyValue",  "subject" -> subject.uuid.toString,  "property" -> property.uuid.toString)
    createAnnotationPropertyValue( extent, implicitUUID,  subject,  property,  value )
  }
  
  def createAnnotationPropertyValue
  ( extent: Extent,
    uuid: java.util.UUID,
    subject: Element,
    property: AnnotationProperty,
    value: scala.Predef.String )
  : (Extent, AnnotationPropertyValue)
  
  // AnonymousConceptUnionAxiom
  def createAnonymousConceptUnionAxiom
  ( extent: Extent,
    disjointTaxonomyParent: ConceptTreeDisjunction,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, AnonymousConceptUnionAxiom)
  = {
  	// namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(disjointTaxonomyParent.uuid.toString,  "name" -> name)
    createAnonymousConceptUnionAxiom( extent, uuid,  disjointTaxonomyParent,  name )
  }
  
  def createAnonymousConceptUnionAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
    disjointTaxonomyParent: ConceptTreeDisjunction,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, AnonymousConceptUnionAxiom)
  
  // Aspect
  def createAspect
  ( extent: Extent,
    tbox: TerminologyBox,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, Aspect)
  = {
  	// namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
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
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("AspectSpecializationAxiom",  "tbox" -> tbox.uuid.toString,  "superAspect" -> superAspect.uuid.toString,  "subEntity" -> subEntity.uuid.toString)
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
  	// namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
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
    bundle: Bundle,
    bundledTerminology: gov.nasa.jpl.imce.oml.tables.IRI )
  : (Extent, BundledTerminologyAxiom)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("BundledTerminologyAxiom",  "bundle" -> bundle.uuid.toString,  "bundledTerminology" -> namespaceUUID(bundledTerminology).toString)
    createBundledTerminologyAxiom( extent, uuid,  bundle,  bundledTerminology )
  }
  
  def createBundledTerminologyAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
    bundle: Bundle,
    bundledTerminology: gov.nasa.jpl.imce.oml.tables.IRI )
  : (Extent, BundledTerminologyAxiom)
  
  // Concept
  def createConcept
  ( extent: Extent,
    tbox: TerminologyBox,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, Concept)
  = {
  	// namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
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
    designatedTerminology: gov.nasa.jpl.imce.oml.tables.IRI )
  : (Extent, ConceptDesignationTerminologyAxiom)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("ConceptDesignationTerminologyAxiom",  "tbox" -> tbox.uuid.toString,  "designatedConcept" -> designatedConcept.uuid.toString,  "designatedTerminology" -> namespaceUUID(designatedTerminology).toString)
    createConceptDesignationTerminologyAxiom( extent, uuid,  tbox,  designatedConcept,  designatedTerminology )
  }
  
  def createConceptDesignationTerminologyAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
    tbox: TerminologyBox,
    designatedConcept: Concept,
    designatedTerminology: gov.nasa.jpl.imce.oml.tables.IRI )
  : (Extent, ConceptDesignationTerminologyAxiom)
  
  // ConceptInstance
  def createConceptInstance
  ( extent: Extent,
    descriptionBox: DescriptionBox,
    singletonConceptClassifier: Concept,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, ConceptInstance)
  = {
  	// namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(descriptionBox.uuid.toString,  "name" -> name)
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
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("ConceptSpecializationAxiom",  "tbox" -> tbox.uuid.toString,  "superConcept" -> superConcept.uuid.toString,  "subConcept" -> subConcept.uuid.toString)
    createConceptSpecializationAxiom( extent, uuid,  tbox,  superConcept,  subConcept )
  }
  
  def createConceptSpecializationAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
    tbox: TerminologyBox,
    superConcept: Concept,
    subConcept: Concept )
  : (Extent, ConceptSpecializationAxiom)
  
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
    closedWorldDefinitions: gov.nasa.jpl.imce.oml.tables.IRI )
  : (Extent, DescriptionBoxExtendsClosedWorldDefinitions)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("DescriptionBoxExtendsClosedWorldDefinitions",  "descriptionBox" -> descriptionBox.uuid.toString,  "closedWorldDefinitions" -> namespaceUUID(closedWorldDefinitions).toString)
    createDescriptionBoxExtendsClosedWorldDefinitions( extent, uuid,  descriptionBox,  closedWorldDefinitions )
  }
  
  def createDescriptionBoxExtendsClosedWorldDefinitions
  ( extent: Extent,
    uuid: java.util.UUID,
    descriptionBox: DescriptionBox,
    closedWorldDefinitions: gov.nasa.jpl.imce.oml.tables.IRI )
  : (Extent, DescriptionBoxExtendsClosedWorldDefinitions)
  
  // DescriptionBoxRefinement
  def createDescriptionBoxRefinement
  ( extent: Extent,
    refiningDescriptionBox: DescriptionBox,
    refinedDescriptionBox: gov.nasa.jpl.imce.oml.tables.IRI )
  : (Extent, DescriptionBoxRefinement)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("DescriptionBoxRefinement",  "refiningDescriptionBox" -> refiningDescriptionBox.uuid.toString,  "refinedDescriptionBox" -> namespaceUUID(refinedDescriptionBox).toString)
    createDescriptionBoxRefinement( extent, uuid,  refiningDescriptionBox,  refinedDescriptionBox )
  }
  
  def createDescriptionBoxRefinement
  ( extent: Extent,
    uuid: java.util.UUID,
    refiningDescriptionBox: DescriptionBox,
    refinedDescriptionBox: gov.nasa.jpl.imce.oml.tables.IRI )
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
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("EntityExistentialRestrictionAxiom",  "tbox" -> tbox.uuid.toString,  "restrictedRelation" -> restrictedRelation.uuid.toString,  "restrictedDomain" -> restrictedDomain.uuid.toString,  "restrictedRange" -> restrictedRange.uuid.toString)
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
  	// namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
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
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("EntityScalarDataPropertyExistentialRestrictionAxiom",  "tbox" -> tbox.uuid.toString,  "restrictedEntity" -> restrictedEntity.uuid.toString,  "scalarProperty" -> scalarProperty.uuid.toString,  "scalarRestriction" -> scalarRestriction.uuid.toString)
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
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("EntityScalarDataPropertyParticularRestrictionAxiom",  "tbox" -> tbox.uuid.toString,  "restrictedEntity" -> restrictedEntity.uuid.toString,  "scalarProperty" -> scalarProperty.uuid.toString)
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
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("EntityScalarDataPropertyUniversalRestrictionAxiom",  "tbox" -> tbox.uuid.toString,  "restrictedEntity" -> restrictedEntity.uuid.toString,  "scalarProperty" -> scalarProperty.uuid.toString,  "scalarRestriction" -> scalarRestriction.uuid.toString)
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
  	// namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
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
  
  // EntityStructuredDataPropertyParticularRestrictionAxiom
  def createEntityStructuredDataPropertyParticularRestrictionAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    structuredDataProperty: DataRelationshipToStructure,
    restrictedEntity: Entity )
  : (Extent, EntityStructuredDataPropertyParticularRestrictionAxiom)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("EntityStructuredDataPropertyParticularRestrictionAxiom",  "tbox" -> tbox.uuid.toString,  "structuredDataProperty" -> structuredDataProperty.uuid.toString,  "restrictedEntity" -> restrictedEntity.uuid.toString)
    createEntityStructuredDataPropertyParticularRestrictionAxiom( extent, uuid,  tbox,  structuredDataProperty,  restrictedEntity )
  }
  
  def createEntityStructuredDataPropertyParticularRestrictionAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
    tbox: TerminologyBox,
    structuredDataProperty: DataRelationshipToStructure,
    restrictedEntity: Entity )
  : (Extent, EntityStructuredDataPropertyParticularRestrictionAxiom)
  
  // EntityUniversalRestrictionAxiom
  def createEntityUniversalRestrictionAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedRelation: EntityRelationship,
    restrictedDomain: Entity,
    restrictedRange: Entity )
  : (Extent, EntityUniversalRestrictionAxiom)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("EntityUniversalRestrictionAxiom",  "tbox" -> tbox.uuid.toString,  "restrictedRelation" -> restrictedRelation.uuid.toString,  "restrictedDomain" -> restrictedDomain.uuid.toString,  "restrictedRange" -> restrictedRange.uuid.toString)
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
  	// namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
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
  	// namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
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
  	// namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
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
  	// namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
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
  	// namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(descriptionBox.uuid.toString,  "name" -> name)
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
    domain: ConceptualEntitySingletonInstance )
  : (Extent, ReifiedRelationshipInstanceDomain)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("ReifiedRelationshipInstanceDomain",  "descriptionBox" -> descriptionBox.uuid.toString,  "reifiedRelationshipInstance" -> reifiedRelationshipInstance.uuid.toString,  "domain" -> domain.uuid.toString)
    createReifiedRelationshipInstanceDomain( extent, uuid,  descriptionBox,  reifiedRelationshipInstance,  domain )
  }
  
  def createReifiedRelationshipInstanceDomain
  ( extent: Extent,
    uuid: java.util.UUID,
    descriptionBox: DescriptionBox,
    reifiedRelationshipInstance: ReifiedRelationshipInstance,
    domain: ConceptualEntitySingletonInstance )
  : (Extent, ReifiedRelationshipInstanceDomain)
  
  // ReifiedRelationshipInstanceRange
  def createReifiedRelationshipInstanceRange
  ( extent: Extent,
    descriptionBox: DescriptionBox,
    reifiedRelationshipInstance: ReifiedRelationshipInstance,
    range: ConceptualEntitySingletonInstance )
  : (Extent, ReifiedRelationshipInstanceRange)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("ReifiedRelationshipInstanceRange",  "descriptionBox" -> descriptionBox.uuid.toString,  "reifiedRelationshipInstance" -> reifiedRelationshipInstance.uuid.toString,  "range" -> range.uuid.toString)
    createReifiedRelationshipInstanceRange( extent, uuid,  descriptionBox,  reifiedRelationshipInstance,  range )
  }
  
  def createReifiedRelationshipInstanceRange
  ( extent: Extent,
    uuid: java.util.UUID,
    descriptionBox: DescriptionBox,
    reifiedRelationshipInstance: ReifiedRelationshipInstance,
    range: ConceptualEntitySingletonInstance )
  : (Extent, ReifiedRelationshipInstanceRange)
  
  // ReifiedRelationshipSpecializationAxiom
  def createReifiedRelationshipSpecializationAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    superRelationship: ReifiedRelationship,
    subRelationship: ReifiedRelationship )
  : (Extent, ReifiedRelationshipSpecializationAxiom)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("ReifiedRelationshipSpecializationAxiom",  "tbox" -> tbox.uuid.toString,  "superRelationship" -> superRelationship.uuid.toString,  "subRelationship" -> subRelationship.uuid.toString)
    createReifiedRelationshipSpecializationAxiom( extent, uuid,  tbox,  superRelationship,  subRelationship )
  }
  
  def createReifiedRelationshipSpecializationAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
    tbox: TerminologyBox,
    superRelationship: ReifiedRelationship,
    subRelationship: ReifiedRelationship )
  : (Extent, ReifiedRelationshipSpecializationAxiom)
  
  // RestrictionScalarDataPropertyValue
  def createRestrictionScalarDataPropertyValue
  ( extent: Extent,
    scalarDataProperty: DataRelationshipToScalar,
    scalarPropertyValue: gov.nasa.jpl.imce.oml.tables.LexicalValue,
    structuredDataPropertyContext: RestrictionStructuredDataPropertyContext )
  : (Extent, RestrictionScalarDataPropertyValue)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("RestrictionScalarDataPropertyValue",  "scalarDataProperty" -> scalarDataProperty.uuid.toString,  "structuredDataPropertyContext" -> structuredDataPropertyContext.uuid.toString)
    createRestrictionScalarDataPropertyValue( extent, uuid,  scalarDataProperty,  scalarPropertyValue,  structuredDataPropertyContext )
  }
  
  def createRestrictionScalarDataPropertyValue
  ( extent: Extent,
    uuid: java.util.UUID,
    scalarDataProperty: DataRelationshipToScalar,
    scalarPropertyValue: gov.nasa.jpl.imce.oml.tables.LexicalValue,
    structuredDataPropertyContext: RestrictionStructuredDataPropertyContext )
  : (Extent, RestrictionScalarDataPropertyValue)
  
  // RestrictionStructuredDataPropertyTuple
  def createRestrictionStructuredDataPropertyTuple
  ( extent: Extent,
    structuredDataProperty: DataRelationshipToStructure,
    structuredDataPropertyContext: RestrictionStructuredDataPropertyContext )
  : (Extent, RestrictionStructuredDataPropertyTuple)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("RestrictionStructuredDataPropertyTuple",  "structuredDataProperty" -> structuredDataProperty.uuid.toString,  "structuredDataPropertyContext" -> structuredDataPropertyContext.uuid.toString)
    createRestrictionStructuredDataPropertyTuple( extent, uuid,  structuredDataProperty,  structuredDataPropertyContext )
  }
  
  def createRestrictionStructuredDataPropertyTuple
  ( extent: Extent,
    uuid: java.util.UUID,
    structuredDataProperty: DataRelationshipToStructure,
    structuredDataPropertyContext: RestrictionStructuredDataPropertyContext )
  : (Extent, RestrictionStructuredDataPropertyTuple)
  
  // RootConceptTaxonomyAxiom
  def createRootConceptTaxonomyAxiom
  ( extent: Extent,
    bundle: Bundle,
    root: Concept )
  : (Extent, RootConceptTaxonomyAxiom)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("RootConceptTaxonomyAxiom",  "bundle" -> bundle.uuid.toString,  "root" -> root.uuid.toString)
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
  	// namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
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
  	// namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
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
    scalarDataProperty: DataRelationshipToScalar,
    scalarPropertyValue: gov.nasa.jpl.imce.oml.tables.LexicalValue,
    structuredDataPropertyContext: SingletonInstanceStructuredDataPropertyContext )
  : (Extent, ScalarDataPropertyValue)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("ScalarDataPropertyValue",  "scalarDataProperty" -> scalarDataProperty.uuid.toString,  "structuredDataPropertyContext" -> structuredDataPropertyContext.uuid.toString)
    createScalarDataPropertyValue( extent, uuid,  scalarDataProperty,  scalarPropertyValue,  structuredDataPropertyContext )
  }
  
  def createScalarDataPropertyValue
  ( extent: Extent,
    uuid: java.util.UUID,
    scalarDataProperty: DataRelationshipToScalar,
    scalarPropertyValue: gov.nasa.jpl.imce.oml.tables.LexicalValue,
    structuredDataPropertyContext: SingletonInstanceStructuredDataPropertyContext )
  : (Extent, ScalarDataPropertyValue)
  
  // ScalarOneOfLiteralAxiom
  def createScalarOneOfLiteralAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    axiom: ScalarOneOfRestriction,
    value: gov.nasa.jpl.imce.oml.tables.LexicalValue )
  : (Extent, ScalarOneOfLiteralAxiom)
  = {
  	// implicitly derived uuid...
    import scala.Predef.ArrowAssoc
    val implicitUUID: java.util.UUID = namespaceUUID("ScalarOneOfLiteralAxiom",  "tbox" -> tbox.uuid.toString,  "axiom" -> axiom.uuid.toString)
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
  	// namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
    createScalarOneOfRestriction( extent, uuid,  tbox,  restrictedRange,  name )
  }
  
  def createScalarOneOfRestriction
  ( extent: Extent,
    uuid: java.util.UUID,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, ScalarOneOfRestriction)
  
  // SingletonInstanceScalarDataPropertyValue
  def createSingletonInstanceScalarDataPropertyValue
  ( extent: Extent,
    descriptionBox: DescriptionBox,
    singletonInstance: ConceptualEntitySingletonInstance,
    scalarDataProperty: EntityScalarDataProperty,
    scalarPropertyValue: scala.Predef.String )
  : (Extent, SingletonInstanceScalarDataPropertyValue)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("SingletonInstanceScalarDataPropertyValue",  "descriptionBox" -> descriptionBox.uuid.toString,  "singletonInstance" -> singletonInstance.uuid.toString,  "scalarDataProperty" -> scalarDataProperty.uuid.toString)
    createSingletonInstanceScalarDataPropertyValue( extent, uuid,  descriptionBox,  singletonInstance,  scalarDataProperty,  scalarPropertyValue )
  }
  
  def createSingletonInstanceScalarDataPropertyValue
  ( extent: Extent,
    uuid: java.util.UUID,
    descriptionBox: DescriptionBox,
    singletonInstance: ConceptualEntitySingletonInstance,
    scalarDataProperty: EntityScalarDataProperty,
    scalarPropertyValue: scala.Predef.String )
  : (Extent, SingletonInstanceScalarDataPropertyValue)
  
  // SingletonInstanceStructuredDataPropertyValue
  def createSingletonInstanceStructuredDataPropertyValue
  ( extent: Extent,
    descriptionBox: DescriptionBox,
    singletonInstance: ConceptualEntitySingletonInstance,
    structuredDataProperty: DataRelationshipToStructure )
  : (Extent, SingletonInstanceStructuredDataPropertyValue)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("SingletonInstanceStructuredDataPropertyValue",  "descriptionBox" -> descriptionBox.uuid.toString,  "singletonInstance" -> singletonInstance.uuid.toString,  "structuredDataProperty" -> structuredDataProperty.uuid.toString)
    createSingletonInstanceStructuredDataPropertyValue( extent, uuid,  descriptionBox,  singletonInstance,  structuredDataProperty )
  }
  
  def createSingletonInstanceStructuredDataPropertyValue
  ( extent: Extent,
    uuid: java.util.UUID,
    descriptionBox: DescriptionBox,
    singletonInstance: ConceptualEntitySingletonInstance,
    structuredDataProperty: DataRelationshipToStructure )
  : (Extent, SingletonInstanceStructuredDataPropertyValue)
  
  // SpecificDisjointConceptAxiom
  def createSpecificDisjointConceptAxiom
  ( extent: Extent,
    disjointTaxonomyParent: ConceptTreeDisjunction,
    disjointLeaf: Concept )
  : (Extent, SpecificDisjointConceptAxiom)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("SpecificDisjointConceptAxiom",  "disjointTaxonomyParent" -> disjointTaxonomyParent.uuid.toString,  "disjointLeaf" -> disjointLeaf.uuid.toString)
    createSpecificDisjointConceptAxiom( extent, uuid,  disjointTaxonomyParent,  disjointLeaf )
  }
  
  def createSpecificDisjointConceptAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
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
  	// namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
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
  	// namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
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
  	// namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
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
  
  // StructuredDataPropertyTuple
  def createStructuredDataPropertyTuple
  ( extent: Extent,
    structuredDataProperty: DataRelationshipToStructure,
    structuredDataPropertyContext: SingletonInstanceStructuredDataPropertyContext )
  : (Extent, StructuredDataPropertyTuple)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("StructuredDataPropertyTuple",  "structuredDataProperty" -> structuredDataProperty.uuid.toString,  "structuredDataPropertyContext" -> structuredDataPropertyContext.uuid.toString)
    createStructuredDataPropertyTuple( extent, uuid,  structuredDataProperty,  structuredDataPropertyContext )
  }
  
  def createStructuredDataPropertyTuple
  ( extent: Extent,
    uuid: java.util.UUID,
    structuredDataProperty: DataRelationshipToStructure,
    structuredDataPropertyContext: SingletonInstanceStructuredDataPropertyContext )
  : (Extent, StructuredDataPropertyTuple)
  
  // SynonymScalarRestriction
  def createSynonymScalarRestriction
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, SynonymScalarRestriction)
  = {
  	// namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
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
    extendedTerminology: gov.nasa.jpl.imce.oml.tables.IRI )
  : (Extent, TerminologyExtensionAxiom)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("TerminologyExtensionAxiom",  "tbox" -> tbox.uuid.toString,  "extendedTerminology" -> namespaceUUID(extendedTerminology).toString)
    createTerminologyExtensionAxiom( extent, uuid,  tbox,  extendedTerminology )
  }
  
  def createTerminologyExtensionAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
    tbox: TerminologyBox,
    extendedTerminology: gov.nasa.jpl.imce.oml.tables.IRI )
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
    nestingContext: Concept,
    nestingTerminology: gov.nasa.jpl.imce.oml.tables.IRI )
  : (Extent, TerminologyNestingAxiom)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("TerminologyNestingAxiom",  "tbox" -> tbox.uuid.toString,  "nestingContext" -> nestingContext.uuid.toString,  "nestingTerminology" -> namespaceUUID(nestingTerminology).toString)
    createTerminologyNestingAxiom( extent, uuid,  tbox,  nestingContext,  nestingTerminology )
  }
  
  def createTerminologyNestingAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
    tbox: TerminologyBox,
    nestingContext: Concept,
    nestingTerminology: gov.nasa.jpl.imce.oml.tables.IRI )
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
  	// namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
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
  	// namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
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
    range: ConceptualEntitySingletonInstance )
  : (Extent, UnreifiedRelationshipInstanceTuple)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("UnreifiedRelationshipInstanceTuple",  "descriptionBox" -> descriptionBox.uuid.toString,  "unreifiedRelationship" -> unreifiedRelationship.uuid.toString,  "domain" -> domain.uuid.toString,  "range" -> range.uuid.toString)
    createUnreifiedRelationshipInstanceTuple( extent, uuid,  descriptionBox,  unreifiedRelationship,  domain,  range )
  }
  
  def createUnreifiedRelationshipInstanceTuple
  ( extent: Extent,
    uuid: java.util.UUID,
    descriptionBox: DescriptionBox,
    unreifiedRelationship: UnreifiedRelationship,
    domain: ConceptualEntitySingletonInstance,
    range: ConceptualEntitySingletonInstance )
  : (Extent, UnreifiedRelationshipInstanceTuple)
  
}
