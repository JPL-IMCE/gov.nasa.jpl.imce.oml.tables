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
import scala.collection.immutable.Seq
import scala.Predef.String

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
    createAnnotationProperty( extent, uuid, iri, abbrevIRI )
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
    value: gov.nasa.jpl.imce.oml.tables.StringDataType )
  : (Extent, AnnotationPropertyValue)
  = {
  	// implicitly derived uuid...
    import scala.Predef.ArrowAssoc
    val implicitUUID: java.util.UUID = namespaceUUID(
      "AnnotationPropertyValue",
      Seq.empty[(String, String)] ++
        Seq("subject" -> subject.uuid.toString) ++
        Seq("property" -> property.uuid.toString) : _*)
    createAnnotationPropertyValue( extent, implicitUUID, subject, property, value )
  }
  
  def createAnnotationPropertyValue
  ( extent: Extent,
    uuid: java.util.UUID,
    subject: Element,
    property: AnnotationProperty,
    value: gov.nasa.jpl.imce.oml.tables.StringDataType )
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
    createAnonymousConceptUnionAxiom( extent, uuid, disjointTaxonomyParent, name )
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
    createAspect( extent, uuid, tbox, name )
  }
  
  def createAspect
  ( extent: Extent,
    uuid: java.util.UUID,
    tbox: TerminologyBox,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, Aspect)
  
  // AspectPredicate
  def createAspectPredicate
  ( extent: Extent,
    aspect: Aspect,
    bodySegment: RuleBodySegment )
  : (Extent, AspectPredicate)
  = {
  	// implicitly derived uuid...
    import scala.Predef.ArrowAssoc
    val implicitUUID: java.util.UUID = namespaceUUID(
      "AspectPredicate",
      Seq.empty[(String, String)] ++
        Seq("aspect" -> aspect.uuid.toString) ++
        Seq("bodySegment" -> bodySegment.uuid.toString) : _*)
    createAspectPredicate( extent, implicitUUID, aspect, bodySegment )
  }
  
  def createAspectPredicate
  ( extent: Extent,
    uuid: java.util.UUID,
    aspect: Aspect,
    bodySegment: RuleBodySegment )
  : (Extent, AspectPredicate)
  
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
    val uuid: java.util.UUID = namespaceUUID(
      "AspectSpecializationAxiom",
      Seq.empty[(String, String)] ++
        Seq("tbox" -> tbox.uuid.toString) ++
        Seq("superAspect" -> superAspect.uuid.toString) ++
        Seq("subEntity" -> subEntity.uuid.toString) : _*)
    createAspectSpecializationAxiom( extent, uuid, tbox, superAspect, subEntity )
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
    length: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
    minLength: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
    maxLength: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, BinaryScalarRestriction)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
    createBinaryScalarRestriction( extent, uuid, tbox, restrictedRange, length, minLength, maxLength, name )
  }
  
  def createBinaryScalarRestriction
  ( extent: Extent,
    uuid: java.util.UUID,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    length: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
    minLength: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
    maxLength: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
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
    createBundle( extent, uuid, kind, iri )
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
    val uuid: java.util.UUID = namespaceUUID(
      "BundledTerminologyAxiom",
      Seq.empty[(String, String)] ++
        Seq("bundle" -> bundle.uuid.toString) ++
        Seq("bundledTerminology" -> namespaceUUID(bundledTerminology).toString) : _*)
    createBundledTerminologyAxiom( extent, uuid, bundle, bundledTerminology )
  }
  
  def createBundledTerminologyAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
    bundle: Bundle,
    bundledTerminology: gov.nasa.jpl.imce.oml.tables.IRI )
  : (Extent, BundledTerminologyAxiom)
  
  // ChainRule
  def createChainRule
  ( extent: Extent,
    tbox: TerminologyBox,
    name: gov.nasa.jpl.imce.oml.tables.LocalName,
    head: UnreifiedRelationship )
  : (Extent, ChainRule)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
    createChainRule( extent, uuid, tbox, name, head )
  }
  
  def createChainRule
  ( extent: Extent,
    uuid: java.util.UUID,
    tbox: TerminologyBox,
    name: gov.nasa.jpl.imce.oml.tables.LocalName,
    head: UnreifiedRelationship )
  : (Extent, ChainRule)
  
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
    createConcept( extent, uuid, tbox, name )
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
    val uuid: java.util.UUID = namespaceUUID(
      "ConceptDesignationTerminologyAxiom",
      Seq.empty[(String, String)] ++
        Seq("tbox" -> tbox.uuid.toString) ++
        Seq("designatedConcept" -> designatedConcept.uuid.toString) ++
        Seq("designatedTerminology" -> namespaceUUID(designatedTerminology).toString) : _*)
    createConceptDesignationTerminologyAxiom( extent, uuid, tbox, designatedConcept, designatedTerminology )
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
    createConceptInstance( extent, uuid, descriptionBox, singletonConceptClassifier, name )
  }
  
  def createConceptInstance
  ( extent: Extent,
    uuid: java.util.UUID,
    descriptionBox: DescriptionBox,
    singletonConceptClassifier: Concept,
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, ConceptInstance)
  
  // ConceptPredicate
  def createConceptPredicate
  ( extent: Extent,
    bodySegment: RuleBodySegment,
    concept: Concept )
  : (Extent, ConceptPredicate)
  = {
  	// implicitly derived uuid...
    import scala.Predef.ArrowAssoc
    val implicitUUID: java.util.UUID = namespaceUUID(
      "ConceptPredicate",
      Seq.empty[(String, String)] ++
        Seq("bodySegment" -> bodySegment.uuid.toString) ++
        Seq("concept" -> concept.uuid.toString) : _*)
    createConceptPredicate( extent, implicitUUID, bodySegment, concept )
  }
  
  def createConceptPredicate
  ( extent: Extent,
    uuid: java.util.UUID,
    bodySegment: RuleBodySegment,
    concept: Concept )
  : (Extent, ConceptPredicate)
  
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
    val uuid: java.util.UUID = namespaceUUID(
      "ConceptSpecializationAxiom",
      Seq.empty[(String, String)] ++
        Seq("tbox" -> tbox.uuid.toString) ++
        Seq("superConcept" -> superConcept.uuid.toString) ++
        Seq("subConcept" -> subConcept.uuid.toString) : _*)
    createConceptSpecializationAxiom( extent, uuid, tbox, superConcept, subConcept )
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
    createDescriptionBox( extent, uuid, kind, iri )
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
    val uuid: java.util.UUID = namespaceUUID(
      "DescriptionBoxExtendsClosedWorldDefinitions",
      Seq.empty[(String, String)] ++
        Seq("descriptionBox" -> descriptionBox.uuid.toString) ++
        Seq("closedWorldDefinitions" -> namespaceUUID(closedWorldDefinitions).toString) : _*)
    createDescriptionBoxExtendsClosedWorldDefinitions( extent, uuid, descriptionBox, closedWorldDefinitions )
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
    val uuid: java.util.UUID = namespaceUUID(
      "DescriptionBoxRefinement",
      Seq.empty[(String, String)] ++
        Seq("refiningDescriptionBox" -> refiningDescriptionBox.uuid.toString) ++
        Seq("refinedDescriptionBox" -> namespaceUUID(refinedDescriptionBox).toString) : _*)
    createDescriptionBoxRefinement( extent, uuid, refiningDescriptionBox, refinedDescriptionBox )
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
    val uuid: java.util.UUID = namespaceUUID(
      "EntityExistentialRestrictionAxiom",
      Seq.empty[(String, String)] ++
        Seq("tbox" -> tbox.uuid.toString) ++
        Seq("restrictedRelation" -> restrictedRelation.uuid.toString) ++
        Seq("restrictedDomain" -> restrictedDomain.uuid.toString) ++
        Seq("restrictedRange" -> restrictedRange.uuid.toString) : _*)
    createEntityExistentialRestrictionAxiom( extent, uuid, tbox, restrictedRelation, restrictedDomain, restrictedRange )
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
    createEntityScalarDataProperty( extent, uuid, tbox, domain, range, isIdentityCriteria, name )
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
    val uuid: java.util.UUID = namespaceUUID(
      "EntityScalarDataPropertyExistentialRestrictionAxiom",
      Seq.empty[(String, String)] ++
        Seq("tbox" -> tbox.uuid.toString) ++
        Seq("restrictedEntity" -> restrictedEntity.uuid.toString) ++
        Seq("scalarProperty" -> scalarProperty.uuid.toString) ++
        Seq("scalarRestriction" -> scalarRestriction.uuid.toString) : _*)
    createEntityScalarDataPropertyExistentialRestrictionAxiom( extent, uuid, tbox, restrictedEntity, scalarProperty, scalarRestriction )
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
    literalValue: gov.nasa.jpl.imce.oml.tables.LiteralValue,
    valueType: scala.Option[DataRange] )
  : (Extent, EntityScalarDataPropertyParticularRestrictionAxiom)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(
      "EntityScalarDataPropertyParticularRestrictionAxiom",
      Seq.empty[(String, String)] ++
        Seq("tbox" -> tbox.uuid.toString) ++
        Seq("restrictedEntity" -> restrictedEntity.uuid.toString) ++
        Seq("scalarProperty" -> scalarProperty.uuid.toString) : _*)
    createEntityScalarDataPropertyParticularRestrictionAxiom( extent, uuid, tbox, restrictedEntity, scalarProperty, literalValue, valueType )
  }
  
  def createEntityScalarDataPropertyParticularRestrictionAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
    tbox: TerminologyBox,
    restrictedEntity: Entity,
    scalarProperty: EntityScalarDataProperty,
    literalValue: gov.nasa.jpl.imce.oml.tables.LiteralValue,
    valueType: scala.Option[DataRange] )
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
    val uuid: java.util.UUID = namespaceUUID(
      "EntityScalarDataPropertyUniversalRestrictionAxiom",
      Seq.empty[(String, String)] ++
        Seq("tbox" -> tbox.uuid.toString) ++
        Seq("restrictedEntity" -> restrictedEntity.uuid.toString) ++
        Seq("scalarProperty" -> scalarProperty.uuid.toString) ++
        Seq("scalarRestriction" -> scalarRestriction.uuid.toString) : _*)
    createEntityScalarDataPropertyUniversalRestrictionAxiom( extent, uuid, tbox, restrictedEntity, scalarProperty, scalarRestriction )
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
    createEntityStructuredDataProperty( extent, uuid, tbox, domain, range, isIdentityCriteria, name )
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
    val uuid: java.util.UUID = namespaceUUID(
      "EntityStructuredDataPropertyParticularRestrictionAxiom",
      Seq.empty[(String, String)] ++
        Seq("tbox" -> tbox.uuid.toString) ++
        Seq("structuredDataProperty" -> structuredDataProperty.uuid.toString) ++
        Seq("restrictedEntity" -> restrictedEntity.uuid.toString) : _*)
    createEntityStructuredDataPropertyParticularRestrictionAxiom( extent, uuid, tbox, structuredDataProperty, restrictedEntity )
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
    val uuid: java.util.UUID = namespaceUUID(
      "EntityUniversalRestrictionAxiom",
      Seq.empty[(String, String)] ++
        Seq("tbox" -> tbox.uuid.toString) ++
        Seq("restrictedRelation" -> restrictedRelation.uuid.toString) ++
        Seq("restrictedDomain" -> restrictedDomain.uuid.toString) ++
        Seq("restrictedRange" -> restrictedRange.uuid.toString) : _*)
    createEntityUniversalRestrictionAxiom( extent, uuid, tbox, restrictedRelation, restrictedDomain, restrictedRange )
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
    length: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
    minLength: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
    maxLength: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
    name: gov.nasa.jpl.imce.oml.tables.LocalName,
    pattern: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralPattern] )
  : (Extent, IRIScalarRestriction)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
    createIRIScalarRestriction( extent, uuid, tbox, restrictedRange, length, minLength, maxLength, name, pattern )
  }
  
  def createIRIScalarRestriction
  ( extent: Extent,
    uuid: java.util.UUID,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    length: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
    minLength: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
    maxLength: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
    name: gov.nasa.jpl.imce.oml.tables.LocalName,
    pattern: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralPattern] )
  : (Extent, IRIScalarRestriction)
  
  // NumericScalarRestriction
  def createNumericScalarRestriction
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    minExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralNumber],
    minInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralNumber],
    maxExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralNumber],
    maxInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralNumber],
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, NumericScalarRestriction)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
    createNumericScalarRestriction( extent, uuid, tbox, restrictedRange, minExclusive, minInclusive, maxExclusive, maxInclusive, name )
  }
  
  def createNumericScalarRestriction
  ( extent: Extent,
    uuid: java.util.UUID,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    minExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralNumber],
    minInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralNumber],
    maxExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralNumber],
    maxInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralNumber],
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, NumericScalarRestriction)
  
  // PlainLiteralScalarRestriction
  def createPlainLiteralScalarRestriction
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    length: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
    minLength: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
    maxLength: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
    name: gov.nasa.jpl.imce.oml.tables.LocalName,
    langRange: scala.Option[gov.nasa.jpl.imce.oml.tables.LanguageTagDataType],
    pattern: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralPattern] )
  : (Extent, PlainLiteralScalarRestriction)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
    createPlainLiteralScalarRestriction( extent, uuid, tbox, restrictedRange, length, minLength, maxLength, name, langRange, pattern )
  }
  
  def createPlainLiteralScalarRestriction
  ( extent: Extent,
    uuid: java.util.UUID,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    length: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
    minLength: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
    maxLength: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
    name: gov.nasa.jpl.imce.oml.tables.LocalName,
    langRange: scala.Option[gov.nasa.jpl.imce.oml.tables.LanguageTagDataType],
    pattern: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralPattern] )
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
    createReifiedRelationship( extent, uuid, tbox, source, target, isAsymmetric, isEssential, isFunctional, isInverseEssential, isInverseFunctional, isIrreflexive, isReflexive, isSymmetric, isTransitive, name, unreifiedPropertyName, unreifiedInversePropertyName )
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
    createReifiedRelationshipInstance( extent, uuid, descriptionBox, singletonReifiedRelationshipClassifier, name )
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
    val uuid: java.util.UUID = namespaceUUID(
      "ReifiedRelationshipInstanceDomain",
      Seq.empty[(String, String)] ++
        Seq("descriptionBox" -> descriptionBox.uuid.toString) ++
        Seq("reifiedRelationshipInstance" -> reifiedRelationshipInstance.uuid.toString) ++
        Seq("domain" -> domain.uuid.toString) : _*)
    createReifiedRelationshipInstanceDomain( extent, uuid, descriptionBox, reifiedRelationshipInstance, domain )
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
    val uuid: java.util.UUID = namespaceUUID(
      "ReifiedRelationshipInstanceRange",
      Seq.empty[(String, String)] ++
        Seq("descriptionBox" -> descriptionBox.uuid.toString) ++
        Seq("reifiedRelationshipInstance" -> reifiedRelationshipInstance.uuid.toString) ++
        Seq("range" -> range.uuid.toString) : _*)
    createReifiedRelationshipInstanceRange( extent, uuid, descriptionBox, reifiedRelationshipInstance, range )
  }
  
  def createReifiedRelationshipInstanceRange
  ( extent: Extent,
    uuid: java.util.UUID,
    descriptionBox: DescriptionBox,
    reifiedRelationshipInstance: ReifiedRelationshipInstance,
    range: ConceptualEntitySingletonInstance )
  : (Extent, ReifiedRelationshipInstanceRange)
  
  // ReifiedRelationshipInversePropertyPredicate
  def createReifiedRelationshipInversePropertyPredicate
  ( extent: Extent,
    bodySegment: RuleBodySegment,
    reifiedRelationship: ReifiedRelationship )
  : (Extent, ReifiedRelationshipInversePropertyPredicate)
  = {
  	// implicitly derived uuid...
    import scala.Predef.ArrowAssoc
    val implicitUUID: java.util.UUID = namespaceUUID(
      "ReifiedRelationshipInversePropertyPredicate",
      Seq.empty[(String, String)] ++
        Seq("bodySegment" -> bodySegment.uuid.toString) ++
        Seq("reifiedRelationship" -> reifiedRelationship.uuid.toString) : _*)
    createReifiedRelationshipInversePropertyPredicate( extent, implicitUUID, bodySegment, reifiedRelationship )
  }
  
  def createReifiedRelationshipInversePropertyPredicate
  ( extent: Extent,
    uuid: java.util.UUID,
    bodySegment: RuleBodySegment,
    reifiedRelationship: ReifiedRelationship )
  : (Extent, ReifiedRelationshipInversePropertyPredicate)
  
  // ReifiedRelationshipPredicate
  def createReifiedRelationshipPredicate
  ( extent: Extent,
    bodySegment: RuleBodySegment,
    reifiedRelationship: ReifiedRelationship )
  : (Extent, ReifiedRelationshipPredicate)
  = {
  	// implicitly derived uuid...
    import scala.Predef.ArrowAssoc
    val implicitUUID: java.util.UUID = namespaceUUID(
      "ReifiedRelationshipPredicate",
      Seq.empty[(String, String)] ++
        Seq("bodySegment" -> bodySegment.uuid.toString) ++
        Seq("reifiedRelationship" -> reifiedRelationship.uuid.toString) : _*)
    createReifiedRelationshipPredicate( extent, implicitUUID, bodySegment, reifiedRelationship )
  }
  
  def createReifiedRelationshipPredicate
  ( extent: Extent,
    uuid: java.util.UUID,
    bodySegment: RuleBodySegment,
    reifiedRelationship: ReifiedRelationship )
  : (Extent, ReifiedRelationshipPredicate)
  
  // ReifiedRelationshipPropertyPredicate
  def createReifiedRelationshipPropertyPredicate
  ( extent: Extent,
    bodySegment: RuleBodySegment,
    reifiedRelationship: ReifiedRelationship )
  : (Extent, ReifiedRelationshipPropertyPredicate)
  = {
  	// implicitly derived uuid...
    import scala.Predef.ArrowAssoc
    val implicitUUID: java.util.UUID = namespaceUUID(
      "ReifiedRelationshipPropertyPredicate",
      Seq.empty[(String, String)] ++
        Seq("bodySegment" -> bodySegment.uuid.toString) ++
        Seq("reifiedRelationship" -> reifiedRelationship.uuid.toString) : _*)
    createReifiedRelationshipPropertyPredicate( extent, implicitUUID, bodySegment, reifiedRelationship )
  }
  
  def createReifiedRelationshipPropertyPredicate
  ( extent: Extent,
    uuid: java.util.UUID,
    bodySegment: RuleBodySegment,
    reifiedRelationship: ReifiedRelationship )
  : (Extent, ReifiedRelationshipPropertyPredicate)
  
  // ReifiedRelationshipSourceInversePropertyPredicate
  def createReifiedRelationshipSourceInversePropertyPredicate
  ( extent: Extent,
    bodySegment: RuleBodySegment,
    reifiedRelationship: ReifiedRelationship )
  : (Extent, ReifiedRelationshipSourceInversePropertyPredicate)
  = {
  	// implicitly derived uuid...
    import scala.Predef.ArrowAssoc
    val implicitUUID: java.util.UUID = namespaceUUID(
      "ReifiedRelationshipSourceInversePropertyPredicate",
      Seq.empty[(String, String)] ++
        Seq("bodySegment" -> bodySegment.uuid.toString) ++
        Seq("reifiedRelationship" -> reifiedRelationship.uuid.toString) : _*)
    createReifiedRelationshipSourceInversePropertyPredicate( extent, implicitUUID, bodySegment, reifiedRelationship )
  }
  
  def createReifiedRelationshipSourceInversePropertyPredicate
  ( extent: Extent,
    uuid: java.util.UUID,
    bodySegment: RuleBodySegment,
    reifiedRelationship: ReifiedRelationship )
  : (Extent, ReifiedRelationshipSourceInversePropertyPredicate)
  
  // ReifiedRelationshipSourcePropertyPredicate
  def createReifiedRelationshipSourcePropertyPredicate
  ( extent: Extent,
    bodySegment: RuleBodySegment,
    reifiedRelationship: ReifiedRelationship )
  : (Extent, ReifiedRelationshipSourcePropertyPredicate)
  = {
  	// implicitly derived uuid...
    import scala.Predef.ArrowAssoc
    val implicitUUID: java.util.UUID = namespaceUUID(
      "ReifiedRelationshipSourcePropertyPredicate",
      Seq.empty[(String, String)] ++
        Seq("bodySegment" -> bodySegment.uuid.toString) ++
        Seq("reifiedRelationship" -> reifiedRelationship.uuid.toString) : _*)
    createReifiedRelationshipSourcePropertyPredicate( extent, implicitUUID, bodySegment, reifiedRelationship )
  }
  
  def createReifiedRelationshipSourcePropertyPredicate
  ( extent: Extent,
    uuid: java.util.UUID,
    bodySegment: RuleBodySegment,
    reifiedRelationship: ReifiedRelationship )
  : (Extent, ReifiedRelationshipSourcePropertyPredicate)
  
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
    val uuid: java.util.UUID = namespaceUUID(
      "ReifiedRelationshipSpecializationAxiom",
      Seq.empty[(String, String)] ++
        Seq("tbox" -> tbox.uuid.toString) ++
        Seq("superRelationship" -> superRelationship.uuid.toString) ++
        Seq("subRelationship" -> subRelationship.uuid.toString) : _*)
    createReifiedRelationshipSpecializationAxiom( extent, uuid, tbox, superRelationship, subRelationship )
  }
  
  def createReifiedRelationshipSpecializationAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
    tbox: TerminologyBox,
    superRelationship: ReifiedRelationship,
    subRelationship: ReifiedRelationship )
  : (Extent, ReifiedRelationshipSpecializationAxiom)
  
  // ReifiedRelationshipTargetInversePropertyPredicate
  def createReifiedRelationshipTargetInversePropertyPredicate
  ( extent: Extent,
    bodySegment: RuleBodySegment,
    reifiedRelationship: ReifiedRelationship )
  : (Extent, ReifiedRelationshipTargetInversePropertyPredicate)
  = {
  	// implicitly derived uuid...
    import scala.Predef.ArrowAssoc
    val implicitUUID: java.util.UUID = namespaceUUID(
      "ReifiedRelationshipTargetInversePropertyPredicate",
      Seq.empty[(String, String)] ++
        Seq("bodySegment" -> bodySegment.uuid.toString) ++
        Seq("reifiedRelationship" -> reifiedRelationship.uuid.toString) : _*)
    createReifiedRelationshipTargetInversePropertyPredicate( extent, implicitUUID, bodySegment, reifiedRelationship )
  }
  
  def createReifiedRelationshipTargetInversePropertyPredicate
  ( extent: Extent,
    uuid: java.util.UUID,
    bodySegment: RuleBodySegment,
    reifiedRelationship: ReifiedRelationship )
  : (Extent, ReifiedRelationshipTargetInversePropertyPredicate)
  
  // ReifiedRelationshipTargetPropertyPredicate
  def createReifiedRelationshipTargetPropertyPredicate
  ( extent: Extent,
    bodySegment: RuleBodySegment,
    reifiedRelationship: ReifiedRelationship )
  : (Extent, ReifiedRelationshipTargetPropertyPredicate)
  = {
  	// implicitly derived uuid...
    import scala.Predef.ArrowAssoc
    val implicitUUID: java.util.UUID = namespaceUUID(
      "ReifiedRelationshipTargetPropertyPredicate",
      Seq.empty[(String, String)] ++
        Seq("bodySegment" -> bodySegment.uuid.toString) ++
        Seq("reifiedRelationship" -> reifiedRelationship.uuid.toString) : _*)
    createReifiedRelationshipTargetPropertyPredicate( extent, implicitUUID, bodySegment, reifiedRelationship )
  }
  
  def createReifiedRelationshipTargetPropertyPredicate
  ( extent: Extent,
    uuid: java.util.UUID,
    bodySegment: RuleBodySegment,
    reifiedRelationship: ReifiedRelationship )
  : (Extent, ReifiedRelationshipTargetPropertyPredicate)
  
  // RestrictionScalarDataPropertyValue
  def createRestrictionScalarDataPropertyValue
  ( extent: Extent,
    scalarDataProperty: DataRelationshipToScalar,
    scalarPropertyValue: gov.nasa.jpl.imce.oml.tables.LiteralValue,
    structuredDataPropertyContext: RestrictionStructuredDataPropertyContext,
    valueType: scala.Option[DataRange] )
  : (Extent, RestrictionScalarDataPropertyValue)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(
      "RestrictionScalarDataPropertyValue",
      Seq.empty[(String, String)] ++
        Seq("scalarDataProperty" -> scalarDataProperty.uuid.toString) ++
        Seq("structuredDataPropertyContext" -> structuredDataPropertyContext.uuid.toString) : _*)
    createRestrictionScalarDataPropertyValue( extent, uuid, scalarDataProperty, scalarPropertyValue, structuredDataPropertyContext, valueType )
  }
  
  def createRestrictionScalarDataPropertyValue
  ( extent: Extent,
    uuid: java.util.UUID,
    scalarDataProperty: DataRelationshipToScalar,
    scalarPropertyValue: gov.nasa.jpl.imce.oml.tables.LiteralValue,
    structuredDataPropertyContext: RestrictionStructuredDataPropertyContext,
    valueType: scala.Option[DataRange] )
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
    val uuid: java.util.UUID = namespaceUUID(
      "RestrictionStructuredDataPropertyTuple",
      Seq.empty[(String, String)] ++
        Seq("structuredDataProperty" -> structuredDataProperty.uuid.toString) ++
        Seq("structuredDataPropertyContext" -> structuredDataPropertyContext.uuid.toString) : _*)
    createRestrictionStructuredDataPropertyTuple( extent, uuid, structuredDataProperty, structuredDataPropertyContext )
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
    val uuid: java.util.UUID = namespaceUUID(
      "RootConceptTaxonomyAxiom",
      Seq.empty[(String, String)] ++
        Seq("bundle" -> bundle.uuid.toString) ++
        Seq("root" -> root.uuid.toString) : _*)
    createRootConceptTaxonomyAxiom( extent, uuid, bundle, root )
  }
  
  def createRootConceptTaxonomyAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
    bundle: Bundle,
    root: Concept )
  : (Extent, RootConceptTaxonomyAxiom)
  
  // RuleBodySegment
  def createRuleBodySegment
  ( extent: Extent,
    previousSegment: scala.Option[RuleBodySegment],
    rule: scala.Option[ChainRule] )
  : (Extent, RuleBodySegment)
  = {
    // custom uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID("RuleBodySegment",  
    		"chainRule" -> 
    			rule.fold[scala.Predef.String]{ 
    			  previousSegment.fold[scala.Predef.String]{ throw new java.lang.IllegalArgumentException("rule or previousSegment must be non-empty") }{ prev => 
    				prev.chainRule().uuid.toString
    			  } 
    			}{ r => 
    			  r.uuid.toString },
    		"position" -> 
    			previousSegment.fold[scala.Predef.String] { 
    			  "1" 
    			}{ prev => 
    			  (1 + prev.position()).toString 
    			})
    createRuleBodySegment( extent, uuid, previousSegment, rule )
  }
  
  def createRuleBodySegment
  ( extent: Extent,
    uuid: java.util.UUID,
    previousSegment: scala.Option[RuleBodySegment],
    rule: scala.Option[ChainRule] )
  : (Extent, RuleBodySegment)
  
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
    createScalar( extent, uuid, tbox, name )
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
    createScalarDataProperty( extent, uuid, tbox, domain, range, name )
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
    scalarPropertyValue: gov.nasa.jpl.imce.oml.tables.LiteralValue,
    structuredDataPropertyContext: SingletonInstanceStructuredDataPropertyContext,
    valueType: scala.Option[DataRange] )
  : (Extent, ScalarDataPropertyValue)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(
      "ScalarDataPropertyValue",
      Seq.empty[(String, String)] ++
        Seq("scalarDataProperty" -> scalarDataProperty.uuid.toString) ++
        Seq("structuredDataPropertyContext" -> structuredDataPropertyContext.uuid.toString) : _*)
    createScalarDataPropertyValue( extent, uuid, scalarDataProperty, scalarPropertyValue, structuredDataPropertyContext, valueType )
  }
  
  def createScalarDataPropertyValue
  ( extent: Extent,
    uuid: java.util.UUID,
    scalarDataProperty: DataRelationshipToScalar,
    scalarPropertyValue: gov.nasa.jpl.imce.oml.tables.LiteralValue,
    structuredDataPropertyContext: SingletonInstanceStructuredDataPropertyContext,
    valueType: scala.Option[DataRange] )
  : (Extent, ScalarDataPropertyValue)
  
  // ScalarOneOfLiteralAxiom
  def createScalarOneOfLiteralAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    axiom: ScalarOneOfRestriction,
    value: gov.nasa.jpl.imce.oml.tables.LiteralValue,
    valueType: scala.Option[DataRange] )
  : (Extent, ScalarOneOfLiteralAxiom)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(
      "ScalarOneOfLiteralAxiom",
      Seq.empty[(String, String)] ++
        Seq("tbox" -> tbox.uuid.toString) ++
        Seq("axiom" -> axiom.uuid.toString) : _*)
    createScalarOneOfLiteralAxiom( extent, uuid, tbox, axiom, value, valueType )
  }
  
  def createScalarOneOfLiteralAxiom
  ( extent: Extent,
    uuid: java.util.UUID,
    tbox: TerminologyBox,
    axiom: ScalarOneOfRestriction,
    value: gov.nasa.jpl.imce.oml.tables.LiteralValue,
    valueType: scala.Option[DataRange] )
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
    createScalarOneOfRestriction( extent, uuid, tbox, restrictedRange, name )
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
    scalarPropertyValue: gov.nasa.jpl.imce.oml.tables.LiteralValue,
    valueType: scala.Option[DataRange] )
  : (Extent, SingletonInstanceScalarDataPropertyValue)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(
      "SingletonInstanceScalarDataPropertyValue",
      Seq.empty[(String, String)] ++
        Seq("descriptionBox" -> descriptionBox.uuid.toString) ++
        Seq("singletonInstance" -> singletonInstance.uuid.toString) ++
        Seq("scalarDataProperty" -> scalarDataProperty.uuid.toString) : _*)
    createSingletonInstanceScalarDataPropertyValue( extent, uuid, descriptionBox, singletonInstance, scalarDataProperty, scalarPropertyValue, valueType )
  }
  
  def createSingletonInstanceScalarDataPropertyValue
  ( extent: Extent,
    uuid: java.util.UUID,
    descriptionBox: DescriptionBox,
    singletonInstance: ConceptualEntitySingletonInstance,
    scalarDataProperty: EntityScalarDataProperty,
    scalarPropertyValue: gov.nasa.jpl.imce.oml.tables.LiteralValue,
    valueType: scala.Option[DataRange] )
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
    val uuid: java.util.UUID = namespaceUUID(
      "SingletonInstanceStructuredDataPropertyValue",
      Seq.empty[(String, String)] ++
        Seq("descriptionBox" -> descriptionBox.uuid.toString) ++
        Seq("singletonInstance" -> singletonInstance.uuid.toString) ++
        Seq("structuredDataProperty" -> structuredDataProperty.uuid.toString) : _*)
    createSingletonInstanceStructuredDataPropertyValue( extent, uuid, descriptionBox, singletonInstance, structuredDataProperty )
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
    val uuid: java.util.UUID = namespaceUUID(
      "SpecificDisjointConceptAxiom",
      Seq.empty[(String, String)] ++
        Seq("disjointTaxonomyParent" -> disjointTaxonomyParent.uuid.toString) ++
        Seq("disjointLeaf" -> disjointLeaf.uuid.toString) : _*)
    createSpecificDisjointConceptAxiom( extent, uuid, disjointTaxonomyParent, disjointLeaf )
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
    length: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
    minLength: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
    maxLength: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
    name: gov.nasa.jpl.imce.oml.tables.LocalName,
    pattern: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralPattern] )
  : (Extent, StringScalarRestriction)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
    createStringScalarRestriction( extent, uuid, tbox, restrictedRange, length, minLength, maxLength, name, pattern )
  }
  
  def createStringScalarRestriction
  ( extent: Extent,
    uuid: java.util.UUID,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    length: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
    minLength: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
    maxLength: scala.Option[gov.nasa.jpl.imce.oml.tables.PositiveIntegerLiteral],
    name: gov.nasa.jpl.imce.oml.tables.LocalName,
    pattern: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralPattern] )
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
    createStructure( extent, uuid, tbox, name )
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
    createStructuredDataProperty( extent, uuid, tbox, domain, range, name )
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
    val uuid: java.util.UUID = namespaceUUID(
      "StructuredDataPropertyTuple",
      Seq.empty[(String, String)] ++
        Seq("structuredDataProperty" -> structuredDataProperty.uuid.toString) ++
        Seq("structuredDataPropertyContext" -> structuredDataPropertyContext.uuid.toString) : _*)
    createStructuredDataPropertyTuple( extent, uuid, structuredDataProperty, structuredDataPropertyContext )
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
    createSynonymScalarRestriction( extent, uuid, tbox, restrictedRange, name )
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
    val uuid: java.util.UUID = namespaceUUID(
      "TerminologyExtensionAxiom",
      Seq.empty[(String, String)] ++
        Seq("tbox" -> tbox.uuid.toString) ++
        Seq("extendedTerminology" -> namespaceUUID(extendedTerminology).toString) : _*)
    createTerminologyExtensionAxiom( extent, uuid, tbox, extendedTerminology )
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
    createTerminologyGraph( extent, uuid, kind, iri )
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
    val uuid: java.util.UUID = namespaceUUID(
      "TerminologyNestingAxiom",
      Seq.empty[(String, String)] ++
        Seq("tbox" -> tbox.uuid.toString) ++
        Seq("nestingContext" -> nestingContext.uuid.toString) ++
        Seq("nestingTerminology" -> namespaceUUID(nestingTerminology).toString) : _*)
    createTerminologyNestingAxiom( extent, uuid, tbox, nestingContext, nestingTerminology )
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
    minExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralDateTime],
    minInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralDateTime],
    maxExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralDateTime],
    maxInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralDateTime],
    name: gov.nasa.jpl.imce.oml.tables.LocalName )
  : (Extent, TimeScalarRestriction)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid: java.util.UUID = namespaceUUID(tbox.uuid.toString,  "name" -> name)
    createTimeScalarRestriction( extent, uuid, tbox, restrictedRange, minExclusive, minInclusive, maxExclusive, maxInclusive, name )
  }
  
  def createTimeScalarRestriction
  ( extent: Extent,
    uuid: java.util.UUID,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    minExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralDateTime],
    minInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralDateTime],
    maxExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralDateTime],
    maxInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralDateTime],
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
    createUnreifiedRelationship( extent, uuid, tbox, source, target, isAsymmetric, isEssential, isFunctional, isInverseEssential, isInverseFunctional, isIrreflexive, isReflexive, isSymmetric, isTransitive, name )
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
    val uuid: java.util.UUID = namespaceUUID(
      "UnreifiedRelationshipInstanceTuple",
      Seq.empty[(String, String)] ++
        Seq("descriptionBox" -> descriptionBox.uuid.toString) ++
        Seq("unreifiedRelationship" -> unreifiedRelationship.uuid.toString) ++
        Seq("domain" -> domain.uuid.toString) ++
        Seq("range" -> range.uuid.toString) : _*)
    createUnreifiedRelationshipInstanceTuple( extent, uuid, descriptionBox, unreifiedRelationship, domain, range )
  }
  
  def createUnreifiedRelationshipInstanceTuple
  ( extent: Extent,
    uuid: java.util.UUID,
    descriptionBox: DescriptionBox,
    unreifiedRelationship: UnreifiedRelationship,
    domain: ConceptualEntitySingletonInstance,
    range: ConceptualEntitySingletonInstance )
  : (Extent, UnreifiedRelationshipInstanceTuple)
  
  // UnreifiedRelationshipInversePropertyPredicate
  def createUnreifiedRelationshipInversePropertyPredicate
  ( extent: Extent,
    unreifiedRelationship: UnreifiedRelationship,
    bodySegment: RuleBodySegment )
  : (Extent, UnreifiedRelationshipInversePropertyPredicate)
  = {
  	// implicitly derived uuid...
    import scala.Predef.ArrowAssoc
    val implicitUUID: java.util.UUID = namespaceUUID(
      "UnreifiedRelationshipInversePropertyPredicate",
      Seq.empty[(String, String)] ++
        Seq("unreifiedRelationship" -> unreifiedRelationship.uuid.toString) ++
        Seq("bodySegment" -> bodySegment.uuid.toString) : _*)
    createUnreifiedRelationshipInversePropertyPredicate( extent, implicitUUID, unreifiedRelationship, bodySegment )
  }
  
  def createUnreifiedRelationshipInversePropertyPredicate
  ( extent: Extent,
    uuid: java.util.UUID,
    unreifiedRelationship: UnreifiedRelationship,
    bodySegment: RuleBodySegment )
  : (Extent, UnreifiedRelationshipInversePropertyPredicate)
  
  // UnreifiedRelationshipPropertyPredicate
  def createUnreifiedRelationshipPropertyPredicate
  ( extent: Extent,
    unreifiedRelationship: UnreifiedRelationship,
    bodySegment: RuleBodySegment )
  : (Extent, UnreifiedRelationshipPropertyPredicate)
  = {
  	// implicitly derived uuid...
    import scala.Predef.ArrowAssoc
    val implicitUUID: java.util.UUID = namespaceUUID(
      "UnreifiedRelationshipPropertyPredicate",
      Seq.empty[(String, String)] ++
        Seq("unreifiedRelationship" -> unreifiedRelationship.uuid.toString) ++
        Seq("bodySegment" -> bodySegment.uuid.toString) : _*)
    createUnreifiedRelationshipPropertyPredicate( extent, implicitUUID, unreifiedRelationship, bodySegment )
  }
  
  def createUnreifiedRelationshipPropertyPredicate
  ( extent: Extent,
    uuid: java.util.UUID,
    unreifiedRelationship: UnreifiedRelationship,
    bodySegment: RuleBodySegment )
  : (Extent, UnreifiedRelationshipPropertyPredicate)
  
}
