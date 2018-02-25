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
    module: Module,
    iri: gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI,
    abbrevIRI: gov.nasa.jpl.imce.oml.tables.taggedTypes.AbbrevIRI )
  : (Extent, AnnotationProperty)
  = {
    val uuid = taggedTypes.annotationPropertyUUID(namespaceUUID(iri.toString))
    createAnnotationProperty( extent, uuid, module, iri, abbrevIRI )
  }
  
  def createAnnotationProperty
  ( extent: Extent,
    uuid: taggedTypes.AnnotationPropertyUUID,
    module: Module,
    iri: gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI,
    abbrevIRI: gov.nasa.jpl.imce.oml.tables.taggedTypes.AbbrevIRI )
  : (Extent, AnnotationProperty)
  
  // AnnotationPropertyValue
  def createAnnotationPropertyValue
  ( extent: Extent,
    subject: LogicalElement,
    property: AnnotationProperty,
    value: gov.nasa.jpl.imce.oml.tables.taggedTypes.StringDataType )
  : (Extent, AnnotationPropertyValue)
  = {
  	// implicitly derived uuid...
    import scala.Predef.ArrowAssoc
    val implicitUUID = taggedTypes.annotationPropertyValueUUID(namespaceUUID(
      "AnnotationPropertyValue",
      Seq.empty[(String, String)] ++
          Seq("subject" -> subject.uuid.toString) ++
          Seq("property" -> property.uuid.toString) ++
          Seq("value" -> value.toString) : _*))
    createAnnotationPropertyValue( extent, implicitUUID, subject, property, value )
  }
  
  def createAnnotationPropertyValue
  ( extent: Extent,
    uuid: taggedTypes.AnnotationPropertyValueUUID,
    subject: LogicalElement,
    property: AnnotationProperty,
    value: gov.nasa.jpl.imce.oml.tables.taggedTypes.StringDataType )
  : (Extent, AnnotationPropertyValue)
  
  // AnonymousConceptUnionAxiom
  def createAnonymousConceptUnionAxiom
  ( extent: Extent,
    disjointTaxonomyParent: ConceptTreeDisjunction,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, AnonymousConceptUnionAxiom)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.anonymousConceptUnionAxiomUUID(namespaceUUID(disjointTaxonomyParent.uuid.toString,  "name" -> name))
    createAnonymousConceptUnionAxiom( extent, uuid, disjointTaxonomyParent, name )
  }
  
  def createAnonymousConceptUnionAxiom
  ( extent: Extent,
    uuid: taggedTypes.AnonymousConceptUnionAxiomUUID,
    disjointTaxonomyParent: ConceptTreeDisjunction,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, AnonymousConceptUnionAxiom)
  
  // Aspect
  def createAspect
  ( extent: Extent,
    tbox: TerminologyBox,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, Aspect)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.aspectUUID(namespaceUUID(tbox.uuid.toString,  "name" -> name))
    createAspect( extent, uuid, tbox, name )
  }
  
  def createAspect
  ( extent: Extent,
    uuid: taggedTypes.AspectUUID,
    tbox: TerminologyBox,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
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
    val uuid = taggedTypes.aspectSpecializationAxiomUUID(namespaceUUID(
      "AspectSpecializationAxiom",
      Seq.empty[(String, String)] ++
          Seq("tbox" -> tbox.uuid.toString) ++
          Seq("superAspect" -> superAspect.uuid.toString) ++
          Seq("subEntity" -> subEntity.uuid.toString) : _*))
    createAspectSpecializationAxiom( extent, uuid, tbox, superAspect, subEntity )
  }
  
  def createAspectSpecializationAxiom
  ( extent: Extent,
    uuid: taggedTypes.AspectSpecializationAxiomUUID,
    tbox: TerminologyBox,
    superAspect: Aspect,
    subEntity: Entity )
  : (Extent, AspectSpecializationAxiom)
  
  // BinaryScalarRestriction
  def createBinaryScalarRestriction
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    length: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    minLength: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    maxLength: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, BinaryScalarRestriction)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.binaryScalarRestrictionUUID(namespaceUUID(tbox.uuid.toString,  "name" -> name))
    createBinaryScalarRestriction( extent, uuid, tbox, restrictedRange, length, minLength, maxLength, name )
  }
  
  def createBinaryScalarRestriction
  ( extent: Extent,
    uuid: taggedTypes.BinaryScalarRestrictionUUID,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    length: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    minLength: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    maxLength: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, BinaryScalarRestriction)
  
  // Bundle
  def createBundle
  ( extent: Extent,
    kind: gov.nasa.jpl.imce.oml.tables.TerminologyKind,
    iri: gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI )
  : (Extent, Bundle)
  = {
    val uuid = taggedTypes.bundleUUID(namespaceUUID(iri.toString))
    createBundle( extent, uuid, kind, iri )
  }
  
  def createBundle
  ( extent: Extent,
    uuid: taggedTypes.BundleUUID,
    kind: gov.nasa.jpl.imce.oml.tables.TerminologyKind,
    iri: gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI )
  : (Extent, Bundle)
  
  // BundledTerminologyAxiom
  def createBundledTerminologyAxiom
  ( extent: Extent,
    bundle: Bundle,
    bundledTerminology: gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI )
  : (Extent, BundledTerminologyAxiom)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.bundledTerminologyAxiomUUID(namespaceUUID(
      "BundledTerminologyAxiom",
      Seq.empty[(String, String)] ++
          Seq("bundle" -> bundle.uuid.toString) ++
        Seq("bundledTerminology" -> namespaceUUID(bundledTerminology).toString) : _*))
    createBundledTerminologyAxiom( extent, uuid, bundle, bundledTerminology )
  }
  
  def createBundledTerminologyAxiom
  ( extent: Extent,
    uuid: taggedTypes.BundledTerminologyAxiomUUID,
    bundle: Bundle,
    bundledTerminology: gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI )
  : (Extent, BundledTerminologyAxiom)
  
  // ChainRule
  def createChainRule
  ( extent: Extent,
    tbox: TerminologyBox,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName,
    head: UnreifiedRelationship )
  : (Extent, ChainRule)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.chainRuleUUID(namespaceUUID(tbox.uuid.toString,  "name" -> name))
    createChainRule( extent, uuid, tbox, name, head )
  }
  
  def createChainRule
  ( extent: Extent,
    uuid: taggedTypes.ChainRuleUUID,
    tbox: TerminologyBox,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName,
    head: UnreifiedRelationship )
  : (Extent, ChainRule)
  
  // Concept
  def createConcept
  ( extent: Extent,
    tbox: TerminologyBox,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, Concept)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.conceptUUID(namespaceUUID(tbox.uuid.toString,  "name" -> name))
    createConcept( extent, uuid, tbox, name )
  }
  
  def createConcept
  ( extent: Extent,
    uuid: taggedTypes.ConceptUUID,
    tbox: TerminologyBox,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, Concept)
  
  // ConceptDesignationTerminologyAxiom
  def createConceptDesignationTerminologyAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    designatedConcept: Concept,
    designatedTerminology: gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI )
  : (Extent, ConceptDesignationTerminologyAxiom)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.conceptDesignationTerminologyAxiomUUID(namespaceUUID(
      "ConceptDesignationTerminologyAxiom",
      Seq.empty[(String, String)] ++
          Seq("tbox" -> tbox.uuid.toString) ++
          Seq("designatedConcept" -> designatedConcept.uuid.toString) ++
        Seq("designatedTerminology" -> namespaceUUID(designatedTerminology).toString) : _*))
    createConceptDesignationTerminologyAxiom( extent, uuid, tbox, designatedConcept, designatedTerminology )
  }
  
  def createConceptDesignationTerminologyAxiom
  ( extent: Extent,
    uuid: taggedTypes.ConceptDesignationTerminologyAxiomUUID,
    tbox: TerminologyBox,
    designatedConcept: Concept,
    designatedTerminology: gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI )
  : (Extent, ConceptDesignationTerminologyAxiom)
  
  // ConceptInstance
  def createConceptInstance
  ( extent: Extent,
    descriptionBox: DescriptionBox,
    singletonConceptClassifier: Concept,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, ConceptInstance)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.conceptInstanceUUID(namespaceUUID(descriptionBox.uuid.toString,  "name" -> name))
    createConceptInstance( extent, uuid, descriptionBox, singletonConceptClassifier, name )
  }
  
  def createConceptInstance
  ( extent: Extent,
    uuid: taggedTypes.ConceptInstanceUUID,
    descriptionBox: DescriptionBox,
    singletonConceptClassifier: Concept,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
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
    val uuid = taggedTypes.conceptSpecializationAxiomUUID(namespaceUUID(
      "ConceptSpecializationAxiom",
      Seq.empty[(String, String)] ++
          Seq("tbox" -> tbox.uuid.toString) ++
          Seq("superConcept" -> superConcept.uuid.toString) ++
          Seq("subConcept" -> subConcept.uuid.toString) : _*))
    createConceptSpecializationAxiom( extent, uuid, tbox, superConcept, subConcept )
  }
  
  def createConceptSpecializationAxiom
  ( extent: Extent,
    uuid: taggedTypes.ConceptSpecializationAxiomUUID,
    tbox: TerminologyBox,
    superConcept: Concept,
    subConcept: Concept )
  : (Extent, ConceptSpecializationAxiom)
  
  // DescriptionBox
  def createDescriptionBox
  ( extent: Extent,
    kind: gov.nasa.jpl.imce.oml.tables.DescriptionKind,
    iri: gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI )
  : (Extent, DescriptionBox)
  = {
    val uuid = taggedTypes.descriptionBoxUUID(namespaceUUID(iri.toString))
    createDescriptionBox( extent, uuid, kind, iri )
  }
  
  def createDescriptionBox
  ( extent: Extent,
    uuid: taggedTypes.DescriptionBoxUUID,
    kind: gov.nasa.jpl.imce.oml.tables.DescriptionKind,
    iri: gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI )
  : (Extent, DescriptionBox)
  
  // DescriptionBoxExtendsClosedWorldDefinitions
  def createDescriptionBoxExtendsClosedWorldDefinitions
  ( extent: Extent,
    descriptionBox: DescriptionBox,
    closedWorldDefinitions: gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI )
  : (Extent, DescriptionBoxExtendsClosedWorldDefinitions)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.descriptionBoxExtendsClosedWorldDefinitionsUUID(namespaceUUID(
      "DescriptionBoxExtendsClosedWorldDefinitions",
      Seq.empty[(String, String)] ++
          Seq("descriptionBox" -> descriptionBox.uuid.toString) ++
        Seq("closedWorldDefinitions" -> namespaceUUID(closedWorldDefinitions).toString) : _*))
    createDescriptionBoxExtendsClosedWorldDefinitions( extent, uuid, descriptionBox, closedWorldDefinitions )
  }
  
  def createDescriptionBoxExtendsClosedWorldDefinitions
  ( extent: Extent,
    uuid: taggedTypes.DescriptionBoxExtendsClosedWorldDefinitionsUUID,
    descriptionBox: DescriptionBox,
    closedWorldDefinitions: gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI )
  : (Extent, DescriptionBoxExtendsClosedWorldDefinitions)
  
  // DescriptionBoxRefinement
  def createDescriptionBoxRefinement
  ( extent: Extent,
    refiningDescriptionBox: DescriptionBox,
    refinedDescriptionBox: gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI )
  : (Extent, DescriptionBoxRefinement)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.descriptionBoxRefinementUUID(namespaceUUID(
      "DescriptionBoxRefinement",
      Seq.empty[(String, String)] ++
          Seq("refiningDescriptionBox" -> refiningDescriptionBox.uuid.toString) ++
        Seq("refinedDescriptionBox" -> namespaceUUID(refinedDescriptionBox).toString) : _*))
    createDescriptionBoxRefinement( extent, uuid, refiningDescriptionBox, refinedDescriptionBox )
  }
  
  def createDescriptionBoxRefinement
  ( extent: Extent,
    uuid: taggedTypes.DescriptionBoxRefinementUUID,
    refiningDescriptionBox: DescriptionBox,
    refinedDescriptionBox: gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI )
  : (Extent, DescriptionBoxRefinement)
  
  // EntityExistentialRestrictionAxiom
  def createEntityExistentialRestrictionAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedDomain: Entity,
    restrictedRange: Entity,
    restrictedRelationship: RestrictableRelationship )
  : (Extent, EntityExistentialRestrictionAxiom)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.entityExistentialRestrictionAxiomUUID(namespaceUUID(
      "EntityExistentialRestrictionAxiom",
      Seq.empty[(String, String)] ++
          Seq("tbox" -> tbox.uuid.toString) ++
          Seq("restrictedDomain" -> restrictedDomain.uuid.toString) ++
          Seq("restrictedRange" -> restrictedRange.uuid.toString) ++
          Seq("restrictedRelationship" -> restrictedRelationship.uuid.toString) : _*))
    createEntityExistentialRestrictionAxiom( extent, uuid, tbox, restrictedDomain, restrictedRange, restrictedRelationship )
  }
  
  def createEntityExistentialRestrictionAxiom
  ( extent: Extent,
    uuid: taggedTypes.EntityExistentialRestrictionAxiomUUID,
    tbox: TerminologyBox,
    restrictedDomain: Entity,
    restrictedRange: Entity,
    restrictedRelationship: RestrictableRelationship )
  : (Extent, EntityExistentialRestrictionAxiom)
  
  // EntityScalarDataProperty
  def createEntityScalarDataProperty
  ( extent: Extent,
    tbox: TerminologyBox,
    domain: Entity,
    range: DataRange,
    isIdentityCriteria: scala.Boolean,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, EntityScalarDataProperty)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.entityScalarDataPropertyUUID(namespaceUUID(tbox.uuid.toString,  "name" -> name))
    createEntityScalarDataProperty( extent, uuid, tbox, domain, range, isIdentityCriteria, name )
  }
  
  def createEntityScalarDataProperty
  ( extent: Extent,
    uuid: taggedTypes.EntityScalarDataPropertyUUID,
    tbox: TerminologyBox,
    domain: Entity,
    range: DataRange,
    isIdentityCriteria: scala.Boolean,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
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
    val uuid = taggedTypes.entityScalarDataPropertyExistentialRestrictionAxiomUUID(namespaceUUID(
      "EntityScalarDataPropertyExistentialRestrictionAxiom",
      Seq.empty[(String, String)] ++
          Seq("tbox" -> tbox.uuid.toString) ++
          Seq("restrictedEntity" -> restrictedEntity.uuid.toString) ++
          Seq("scalarProperty" -> scalarProperty.uuid.toString) ++
          Seq("scalarRestriction" -> scalarRestriction.uuid.toString) : _*))
    createEntityScalarDataPropertyExistentialRestrictionAxiom( extent, uuid, tbox, restrictedEntity, scalarProperty, scalarRestriction )
  }
  
  def createEntityScalarDataPropertyExistentialRestrictionAxiom
  ( extent: Extent,
    uuid: taggedTypes.EntityScalarDataPropertyExistentialRestrictionAxiomUUID,
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
    val uuid = taggedTypes.entityScalarDataPropertyParticularRestrictionAxiomUUID(namespaceUUID(
      "EntityScalarDataPropertyParticularRestrictionAxiom",
      Seq.empty[(String, String)] ++
          Seq("tbox" -> tbox.uuid.toString) ++
          Seq("restrictedEntity" -> restrictedEntity.uuid.toString) ++
          Seq("scalarProperty" -> scalarProperty.uuid.toString) ++
          Seq("literalValue" -> literalValue.value) : _*))
    createEntityScalarDataPropertyParticularRestrictionAxiom( extent, uuid, tbox, restrictedEntity, scalarProperty, literalValue, valueType )
  }
  
  def createEntityScalarDataPropertyParticularRestrictionAxiom
  ( extent: Extent,
    uuid: taggedTypes.EntityScalarDataPropertyParticularRestrictionAxiomUUID,
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
    val uuid = taggedTypes.entityScalarDataPropertyUniversalRestrictionAxiomUUID(namespaceUUID(
      "EntityScalarDataPropertyUniversalRestrictionAxiom",
      Seq.empty[(String, String)] ++
          Seq("tbox" -> tbox.uuid.toString) ++
          Seq("restrictedEntity" -> restrictedEntity.uuid.toString) ++
          Seq("scalarProperty" -> scalarProperty.uuid.toString) ++
          Seq("scalarRestriction" -> scalarRestriction.uuid.toString) : _*))
    createEntityScalarDataPropertyUniversalRestrictionAxiom( extent, uuid, tbox, restrictedEntity, scalarProperty, scalarRestriction )
  }
  
  def createEntityScalarDataPropertyUniversalRestrictionAxiom
  ( extent: Extent,
    uuid: taggedTypes.EntityScalarDataPropertyUniversalRestrictionAxiomUUID,
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
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, EntityStructuredDataProperty)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.entityStructuredDataPropertyUUID(namespaceUUID(tbox.uuid.toString,  "name" -> name))
    createEntityStructuredDataProperty( extent, uuid, tbox, domain, range, isIdentityCriteria, name )
  }
  
  def createEntityStructuredDataProperty
  ( extent: Extent,
    uuid: taggedTypes.EntityStructuredDataPropertyUUID,
    tbox: TerminologyBox,
    domain: Entity,
    range: Structure,
    isIdentityCriteria: scala.Boolean,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
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
    val uuid = taggedTypes.entityStructuredDataPropertyParticularRestrictionAxiomUUID(namespaceUUID(
      "EntityStructuredDataPropertyParticularRestrictionAxiom",
      Seq.empty[(String, String)] ++
          Seq("tbox" -> tbox.uuid.toString) ++
          Seq("structuredDataProperty" -> structuredDataProperty.uuid.toString) ++
          Seq("restrictedEntity" -> restrictedEntity.uuid.toString) : _*))
    createEntityStructuredDataPropertyParticularRestrictionAxiom( extent, uuid, tbox, structuredDataProperty, restrictedEntity )
  }
  
  def createEntityStructuredDataPropertyParticularRestrictionAxiom
  ( extent: Extent,
    uuid: taggedTypes.EntityStructuredDataPropertyParticularRestrictionAxiomUUID,
    tbox: TerminologyBox,
    structuredDataProperty: DataRelationshipToStructure,
    restrictedEntity: Entity )
  : (Extent, EntityStructuredDataPropertyParticularRestrictionAxiom)
  
  // EntityUniversalRestrictionAxiom
  def createEntityUniversalRestrictionAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedDomain: Entity,
    restrictedRange: Entity,
    restrictedRelationship: RestrictableRelationship )
  : (Extent, EntityUniversalRestrictionAxiom)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.entityUniversalRestrictionAxiomUUID(namespaceUUID(
      "EntityUniversalRestrictionAxiom",
      Seq.empty[(String, String)] ++
          Seq("tbox" -> tbox.uuid.toString) ++
          Seq("restrictedDomain" -> restrictedDomain.uuid.toString) ++
          Seq("restrictedRange" -> restrictedRange.uuid.toString) ++
          Seq("restrictedRelationship" -> restrictedRelationship.uuid.toString) : _*))
    createEntityUniversalRestrictionAxiom( extent, uuid, tbox, restrictedDomain, restrictedRange, restrictedRelationship )
  }
  
  def createEntityUniversalRestrictionAxiom
  ( extent: Extent,
    uuid: taggedTypes.EntityUniversalRestrictionAxiomUUID,
    tbox: TerminologyBox,
    restrictedDomain: Entity,
    restrictedRange: Entity,
    restrictedRelationship: RestrictableRelationship )
  : (Extent, EntityUniversalRestrictionAxiom)
  
  // Extent
  def createExtent
  : Extent
  
  // ForwardProperty
  def createForwardProperty
  ( extent: Extent,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName,
    reifiedRelationship: ReifiedRelationship )
  : (Extent, ForwardProperty)
  = {
  	// implicitly derived uuid...
    import scala.Predef.ArrowAssoc
    val implicitUUID = taggedTypes.forwardPropertyUUID(namespaceUUID(
      "ForwardProperty",
      Seq.empty[(String, String)] ++
          Seq("name" -> name.toString) ++
          Seq("reifiedRelationship" -> reifiedRelationship.uuid.toString) : _*))
    createForwardProperty( extent, implicitUUID, name, reifiedRelationship )
  }
  
  def createForwardProperty
  ( extent: Extent,
    uuid: taggedTypes.ForwardPropertyUUID,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName,
    reifiedRelationship: ReifiedRelationship )
  : (Extent, ForwardProperty)
  
  // IRIScalarRestriction
  def createIRIScalarRestriction
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    length: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    minLength: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    maxLength: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName,
    pattern: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralPattern] )
  : (Extent, IRIScalarRestriction)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.iriScalarRestrictionUUID(namespaceUUID(tbox.uuid.toString,  "name" -> name))
    createIRIScalarRestriction( extent, uuid, tbox, restrictedRange, length, minLength, maxLength, name, pattern )
  }
  
  def createIRIScalarRestriction
  ( extent: Extent,
    uuid: taggedTypes.IRIScalarRestrictionUUID,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    length: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    minLength: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    maxLength: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName,
    pattern: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralPattern] )
  : (Extent, IRIScalarRestriction)
  
  // InverseProperty
  def createInverseProperty
  ( extent: Extent,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName,
    reifiedRelationship: ReifiedRelationship )
  : (Extent, InverseProperty)
  = {
  	// implicitly derived uuid...
    import scala.Predef.ArrowAssoc
    val implicitUUID = taggedTypes.inversePropertyUUID(namespaceUUID(
      "InverseProperty",
      Seq.empty[(String, String)] ++
          Seq("name" -> name.toString) ++
          Seq("reifiedRelationship" -> reifiedRelationship.uuid.toString) : _*))
    createInverseProperty( extent, implicitUUID, name, reifiedRelationship )
  }
  
  def createInverseProperty
  ( extent: Extent,
    uuid: taggedTypes.InversePropertyUUID,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName,
    reifiedRelationship: ReifiedRelationship )
  : (Extent, InverseProperty)
  
  // NumericScalarRestriction
  def createNumericScalarRestriction
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    minExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralNumber],
    minInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralNumber],
    maxExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralNumber],
    maxInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralNumber],
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, NumericScalarRestriction)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.numericScalarRestrictionUUID(namespaceUUID(tbox.uuid.toString,  "name" -> name))
    createNumericScalarRestriction( extent, uuid, tbox, restrictedRange, minExclusive, minInclusive, maxExclusive, maxInclusive, name )
  }
  
  def createNumericScalarRestriction
  ( extent: Extent,
    uuid: taggedTypes.NumericScalarRestrictionUUID,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    minExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralNumber],
    minInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralNumber],
    maxExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralNumber],
    maxInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralNumber],
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, NumericScalarRestriction)
  
  // PlainLiteralScalarRestriction
  def createPlainLiteralScalarRestriction
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    length: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    minLength: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    maxLength: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName,
    langRange: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.LanguageTagDataType],
    pattern: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralPattern] )
  : (Extent, PlainLiteralScalarRestriction)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.plainLiteralScalarRestrictionUUID(namespaceUUID(tbox.uuid.toString,  "name" -> name))
    createPlainLiteralScalarRestriction( extent, uuid, tbox, restrictedRange, length, minLength, maxLength, name, langRange, pattern )
  }
  
  def createPlainLiteralScalarRestriction
  ( extent: Extent,
    uuid: taggedTypes.PlainLiteralScalarRestrictionUUID,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    length: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    minLength: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    maxLength: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName,
    langRange: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.LanguageTagDataType],
    pattern: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralPattern] )
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
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, ReifiedRelationship)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.reifiedRelationshipUUID(namespaceUUID(tbox.uuid.toString,  "name" -> name))
    createReifiedRelationship( extent, uuid, tbox, source, target, isAsymmetric, isEssential, isFunctional, isInverseEssential, isInverseFunctional, isIrreflexive, isReflexive, isSymmetric, isTransitive, name )
  }
  
  def createReifiedRelationship
  ( extent: Extent,
    uuid: taggedTypes.ReifiedRelationshipUUID,
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
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, ReifiedRelationship)
  
  // ReifiedRelationshipInstance
  def createReifiedRelationshipInstance
  ( extent: Extent,
    descriptionBox: DescriptionBox,
    singletonConceptualRelationshipClassifier: ConceptualRelationship,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, ReifiedRelationshipInstance)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.reifiedRelationshipInstanceUUID(namespaceUUID(descriptionBox.uuid.toString,  "name" -> name))
    createReifiedRelationshipInstance( extent, uuid, descriptionBox, singletonConceptualRelationshipClassifier, name )
  }
  
  def createReifiedRelationshipInstance
  ( extent: Extent,
    uuid: taggedTypes.ReifiedRelationshipInstanceUUID,
    descriptionBox: DescriptionBox,
    singletonConceptualRelationshipClassifier: ConceptualRelationship,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
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
    val uuid = taggedTypes.reifiedRelationshipInstanceDomainUUID(namespaceUUID(
      "ReifiedRelationshipInstanceDomain",
      Seq.empty[(String, String)] ++
          Seq("descriptionBox" -> descriptionBox.uuid.toString) ++
          Seq("reifiedRelationshipInstance" -> reifiedRelationshipInstance.uuid.toString) ++
          Seq("domain" -> domain.uuid.toString) : _*))
    createReifiedRelationshipInstanceDomain( extent, uuid, descriptionBox, reifiedRelationshipInstance, domain )
  }
  
  def createReifiedRelationshipInstanceDomain
  ( extent: Extent,
    uuid: taggedTypes.ReifiedRelationshipInstanceDomainUUID,
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
    val uuid = taggedTypes.reifiedRelationshipInstanceRangeUUID(namespaceUUID(
      "ReifiedRelationshipInstanceRange",
      Seq.empty[(String, String)] ++
          Seq("descriptionBox" -> descriptionBox.uuid.toString) ++
          Seq("reifiedRelationshipInstance" -> reifiedRelationshipInstance.uuid.toString) ++
          Seq("range" -> range.uuid.toString) : _*))
    createReifiedRelationshipInstanceRange( extent, uuid, descriptionBox, reifiedRelationshipInstance, range )
  }
  
  def createReifiedRelationshipInstanceRange
  ( extent: Extent,
    uuid: taggedTypes.ReifiedRelationshipInstanceRangeUUID,
    descriptionBox: DescriptionBox,
    reifiedRelationshipInstance: ReifiedRelationshipInstance,
    range: ConceptualEntitySingletonInstance )
  : (Extent, ReifiedRelationshipInstanceRange)
  
  // RestrictionScalarDataPropertyValue
  def createRestrictionScalarDataPropertyValue
  ( extent: Extent,
    structuredDataPropertyContext: RestrictionStructuredDataPropertyContext,
    scalarDataProperty: DataRelationshipToScalar,
    scalarPropertyValue: gov.nasa.jpl.imce.oml.tables.LiteralValue,
    valueType: scala.Option[DataRange] )
  : (Extent, RestrictionScalarDataPropertyValue)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.restrictionScalarDataPropertyValueUUID(namespaceUUID(
      "RestrictionScalarDataPropertyValue",
      Seq.empty[(String, String)] ++
          Seq("structuredDataPropertyContext" -> structuredDataPropertyContext.uuid.toString) ++
          Seq("scalarDataProperty" -> scalarDataProperty.uuid.toString) ++
          Seq("scalarPropertyValue" -> scalarPropertyValue.value) : _*))
    createRestrictionScalarDataPropertyValue( extent, uuid, structuredDataPropertyContext, scalarDataProperty, scalarPropertyValue, valueType )
  }
  
  def createRestrictionScalarDataPropertyValue
  ( extent: Extent,
    uuid: taggedTypes.RestrictionScalarDataPropertyValueUUID,
    structuredDataPropertyContext: RestrictionStructuredDataPropertyContext,
    scalarDataProperty: DataRelationshipToScalar,
    scalarPropertyValue: gov.nasa.jpl.imce.oml.tables.LiteralValue,
    valueType: scala.Option[DataRange] )
  : (Extent, RestrictionScalarDataPropertyValue)
  
  // RestrictionStructuredDataPropertyTuple
  def createRestrictionStructuredDataPropertyTuple
  ( extent: Extent,
    structuredDataPropertyContext: RestrictionStructuredDataPropertyContext,
    structuredDataProperty: DataRelationshipToStructure )
  : (Extent, RestrictionStructuredDataPropertyTuple)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.restrictionStructuredDataPropertyTupleUUID(namespaceUUID(
      "RestrictionStructuredDataPropertyTuple",
      Seq.empty[(String, String)] ++
          Seq("structuredDataPropertyContext" -> structuredDataPropertyContext.uuid.toString) ++
          Seq("structuredDataProperty" -> structuredDataProperty.uuid.toString) : _*))
    createRestrictionStructuredDataPropertyTuple( extent, uuid, structuredDataPropertyContext, structuredDataProperty )
  }
  
  def createRestrictionStructuredDataPropertyTuple
  ( extent: Extent,
    uuid: taggedTypes.RestrictionStructuredDataPropertyTupleUUID,
    structuredDataPropertyContext: RestrictionStructuredDataPropertyContext,
    structuredDataProperty: DataRelationshipToStructure )
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
    val uuid = taggedTypes.rootConceptTaxonomyAxiomUUID(namespaceUUID(
      "RootConceptTaxonomyAxiom",
      Seq.empty[(String, String)] ++
          Seq("bundle" -> bundle.uuid.toString) ++
          Seq("root" -> root.uuid.toString) : _*))
    createRootConceptTaxonomyAxiom( extent, uuid, bundle, root )
  }
  
  def createRootConceptTaxonomyAxiom
  ( extent: Extent,
    uuid: taggedTypes.RootConceptTaxonomyAxiomUUID,
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
    val id = namespaceUUID("RuleBodySegment",  
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
    val uuid = taggedTypes.ruleBodySegmentUUID(id)
    createRuleBodySegment( extent, uuid, previousSegment, rule )
  }
  
  def createRuleBodySegment
  ( extent: Extent,
    uuid: taggedTypes.RuleBodySegmentUUID,
    previousSegment: scala.Option[RuleBodySegment],
    rule: scala.Option[ChainRule] )
  : (Extent, RuleBodySegment)
  
  // Scalar
  def createScalar
  ( extent: Extent,
    tbox: TerminologyBox,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, Scalar)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.scalarUUID(namespaceUUID(tbox.uuid.toString,  "name" -> name))
    createScalar( extent, uuid, tbox, name )
  }
  
  def createScalar
  ( extent: Extent,
    uuid: taggedTypes.ScalarUUID,
    tbox: TerminologyBox,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, Scalar)
  
  // ScalarDataProperty
  def createScalarDataProperty
  ( extent: Extent,
    tbox: TerminologyBox,
    domain: Structure,
    range: DataRange,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, ScalarDataProperty)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.scalarDataPropertyUUID(namespaceUUID(tbox.uuid.toString,  "name" -> name))
    createScalarDataProperty( extent, uuid, tbox, domain, range, name )
  }
  
  def createScalarDataProperty
  ( extent: Extent,
    uuid: taggedTypes.ScalarDataPropertyUUID,
    tbox: TerminologyBox,
    domain: Structure,
    range: DataRange,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, ScalarDataProperty)
  
  // ScalarDataPropertyValue
  def createScalarDataPropertyValue
  ( extent: Extent,
    structuredDataPropertyContext: SingletonInstanceStructuredDataPropertyContext,
    scalarDataProperty: DataRelationshipToScalar,
    scalarPropertyValue: gov.nasa.jpl.imce.oml.tables.LiteralValue,
    valueType: scala.Option[DataRange] )
  : (Extent, ScalarDataPropertyValue)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.scalarDataPropertyValueUUID(namespaceUUID(
      "ScalarDataPropertyValue",
      Seq.empty[(String, String)] ++
          Seq("structuredDataPropertyContext" -> structuredDataPropertyContext.uuid.toString) ++
          Seq("scalarDataProperty" -> scalarDataProperty.uuid.toString) ++
          Seq("scalarPropertyValue" -> scalarPropertyValue.value) : _*))
    createScalarDataPropertyValue( extent, uuid, structuredDataPropertyContext, scalarDataProperty, scalarPropertyValue, valueType )
  }
  
  def createScalarDataPropertyValue
  ( extent: Extent,
    uuid: taggedTypes.ScalarDataPropertyValueUUID,
    structuredDataPropertyContext: SingletonInstanceStructuredDataPropertyContext,
    scalarDataProperty: DataRelationshipToScalar,
    scalarPropertyValue: gov.nasa.jpl.imce.oml.tables.LiteralValue,
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
    val uuid = taggedTypes.scalarOneOfLiteralAxiomUUID(namespaceUUID(
      "ScalarOneOfLiteralAxiom",
      Seq.empty[(String, String)] ++
          Seq("tbox" -> tbox.uuid.toString) ++
          Seq("axiom" -> axiom.uuid.toString) ++
          Seq("value" -> value.value) : _*))
    createScalarOneOfLiteralAxiom( extent, uuid, tbox, axiom, value, valueType )
  }
  
  def createScalarOneOfLiteralAxiom
  ( extent: Extent,
    uuid: taggedTypes.ScalarOneOfLiteralAxiomUUID,
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
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, ScalarOneOfRestriction)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.scalarOneOfRestrictionUUID(namespaceUUID(tbox.uuid.toString,  "name" -> name))
    createScalarOneOfRestriction( extent, uuid, tbox, restrictedRange, name )
  }
  
  def createScalarOneOfRestriction
  ( extent: Extent,
    uuid: taggedTypes.ScalarOneOfRestrictionUUID,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, ScalarOneOfRestriction)
  
  // SegmentPredicate
  def createSegmentPredicate
  ( extent: Extent,
    bodySegment: RuleBodySegment,
    predicate: scala.Option[Predicate],
    reifiedRelationshipSource: scala.Option[ReifiedRelationship],
    reifiedRelationshipInverseSource: scala.Option[ReifiedRelationship],
    reifiedRelationshipTarget: scala.Option[ReifiedRelationship],
    reifiedRelationshipInverseTarget: scala.Option[ReifiedRelationship],
    unreifiedRelationshipInverse: scala.Option[UnreifiedRelationship] )
  : (Extent, SegmentPredicate)
  = {
  	// implicitly derived uuid...
    import scala.Predef.ArrowAssoc
    val implicitUUID = taggedTypes.segmentPredicateUUID(namespaceUUID(
      "SegmentPredicate",
      Seq.empty[(String, String)] ++
          Seq("bodySegment" -> bodySegment.uuid.toString) ++
        predicate.map { vt => "predicate" -> vt.uuid.toString } ++
        reifiedRelationshipSource.map { vt => "reifiedRelationshipSource" -> vt.uuid.toString } ++
        reifiedRelationshipInverseSource.map { vt => "reifiedRelationshipInverseSource" -> vt.uuid.toString } ++
        reifiedRelationshipTarget.map { vt => "reifiedRelationshipTarget" -> vt.uuid.toString } ++
        reifiedRelationshipInverseTarget.map { vt => "reifiedRelationshipInverseTarget" -> vt.uuid.toString } ++
        unreifiedRelationshipInverse.map { vt => "unreifiedRelationshipInverse" -> vt.uuid.toString } : _*))
    createSegmentPredicate( extent, implicitUUID, bodySegment, predicate, reifiedRelationshipSource, reifiedRelationshipInverseSource, reifiedRelationshipTarget, reifiedRelationshipInverseTarget, unreifiedRelationshipInverse )
  }
  
  def createSegmentPredicate
  ( extent: Extent,
    uuid: taggedTypes.SegmentPredicateUUID,
    bodySegment: RuleBodySegment,
    predicate: scala.Option[Predicate],
    reifiedRelationshipSource: scala.Option[ReifiedRelationship],
    reifiedRelationshipInverseSource: scala.Option[ReifiedRelationship],
    reifiedRelationshipTarget: scala.Option[ReifiedRelationship],
    reifiedRelationshipInverseTarget: scala.Option[ReifiedRelationship],
    unreifiedRelationshipInverse: scala.Option[UnreifiedRelationship] )
  : (Extent, SegmentPredicate)
  
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
    val uuid = taggedTypes.singletonInstanceScalarDataPropertyValueUUID(namespaceUUID(
      "SingletonInstanceScalarDataPropertyValue",
      Seq.empty[(String, String)] ++
          Seq("descriptionBox" -> descriptionBox.uuid.toString) ++
          Seq("singletonInstance" -> singletonInstance.uuid.toString) ++
          Seq("scalarDataProperty" -> scalarDataProperty.uuid.toString) ++
          Seq("scalarPropertyValue" -> scalarPropertyValue.value) : _*))
    createSingletonInstanceScalarDataPropertyValue( extent, uuid, descriptionBox, singletonInstance, scalarDataProperty, scalarPropertyValue, valueType )
  }
  
  def createSingletonInstanceScalarDataPropertyValue
  ( extent: Extent,
    uuid: taggedTypes.SingletonInstanceScalarDataPropertyValueUUID,
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
    val uuid = taggedTypes.singletonInstanceStructuredDataPropertyValueUUID(namespaceUUID(
      "SingletonInstanceStructuredDataPropertyValue",
      Seq.empty[(String, String)] ++
          Seq("descriptionBox" -> descriptionBox.uuid.toString) ++
          Seq("singletonInstance" -> singletonInstance.uuid.toString) ++
          Seq("structuredDataProperty" -> structuredDataProperty.uuid.toString) : _*))
    createSingletonInstanceStructuredDataPropertyValue( extent, uuid, descriptionBox, singletonInstance, structuredDataProperty )
  }
  
  def createSingletonInstanceStructuredDataPropertyValue
  ( extent: Extent,
    uuid: taggedTypes.SingletonInstanceStructuredDataPropertyValueUUID,
    descriptionBox: DescriptionBox,
    singletonInstance: ConceptualEntitySingletonInstance,
    structuredDataProperty: DataRelationshipToStructure )
  : (Extent, SingletonInstanceStructuredDataPropertyValue)
  
  // SpecializedReifiedRelationship
  def createSpecializedReifiedRelationship
  ( extent: Extent,
    tbox: TerminologyBox,
    source: Entity,
    target: Entity,
    general: ConceptualRelationship,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, SpecializedReifiedRelationship)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.specializedReifiedRelationshipUUID(namespaceUUID(tbox.uuid.toString,  "name" -> name))
    createSpecializedReifiedRelationship( extent, uuid, tbox, source, target, general, name )
  }
  
  def createSpecializedReifiedRelationship
  ( extent: Extent,
    uuid: taggedTypes.SpecializedReifiedRelationshipUUID,
    tbox: TerminologyBox,
    source: Entity,
    target: Entity,
    general: ConceptualRelationship,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, SpecializedReifiedRelationship)
  
  // SpecificDisjointConceptAxiom
  def createSpecificDisjointConceptAxiom
  ( extent: Extent,
    disjointTaxonomyParent: ConceptTreeDisjunction,
    disjointLeaf: Concept )
  : (Extent, SpecificDisjointConceptAxiom)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.specificDisjointConceptAxiomUUID(namespaceUUID(
      "SpecificDisjointConceptAxiom",
      Seq.empty[(String, String)] ++
          Seq("disjointTaxonomyParent" -> disjointTaxonomyParent.uuid.toString) ++
          Seq("disjointLeaf" -> disjointLeaf.uuid.toString) : _*))
    createSpecificDisjointConceptAxiom( extent, uuid, disjointTaxonomyParent, disjointLeaf )
  }
  
  def createSpecificDisjointConceptAxiom
  ( extent: Extent,
    uuid: taggedTypes.SpecificDisjointConceptAxiomUUID,
    disjointTaxonomyParent: ConceptTreeDisjunction,
    disjointLeaf: Concept )
  : (Extent, SpecificDisjointConceptAxiom)
  
  // StringScalarRestriction
  def createStringScalarRestriction
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    length: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    minLength: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    maxLength: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName,
    pattern: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralPattern] )
  : (Extent, StringScalarRestriction)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.stringScalarRestrictionUUID(namespaceUUID(tbox.uuid.toString,  "name" -> name))
    createStringScalarRestriction( extent, uuid, tbox, restrictedRange, length, minLength, maxLength, name, pattern )
  }
  
  def createStringScalarRestriction
  ( extent: Extent,
    uuid: taggedTypes.StringScalarRestrictionUUID,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    length: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    minLength: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    maxLength: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.PositiveIntegerLiteral],
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName,
    pattern: scala.Option[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralPattern] )
  : (Extent, StringScalarRestriction)
  
  // Structure
  def createStructure
  ( extent: Extent,
    tbox: TerminologyBox,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, Structure)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.structureUUID(namespaceUUID(tbox.uuid.toString,  "name" -> name))
    createStructure( extent, uuid, tbox, name )
  }
  
  def createStructure
  ( extent: Extent,
    uuid: taggedTypes.StructureUUID,
    tbox: TerminologyBox,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, Structure)
  
  // StructuredDataProperty
  def createStructuredDataProperty
  ( extent: Extent,
    tbox: TerminologyBox,
    domain: Structure,
    range: Structure,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, StructuredDataProperty)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.structuredDataPropertyUUID(namespaceUUID(tbox.uuid.toString,  "name" -> name))
    createStructuredDataProperty( extent, uuid, tbox, domain, range, name )
  }
  
  def createStructuredDataProperty
  ( extent: Extent,
    uuid: taggedTypes.StructuredDataPropertyUUID,
    tbox: TerminologyBox,
    domain: Structure,
    range: Structure,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, StructuredDataProperty)
  
  // StructuredDataPropertyTuple
  def createStructuredDataPropertyTuple
  ( extent: Extent,
    structuredDataPropertyContext: SingletonInstanceStructuredDataPropertyContext,
    structuredDataProperty: DataRelationshipToStructure )
  : (Extent, StructuredDataPropertyTuple)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.structuredDataPropertyTupleUUID(namespaceUUID(
      "StructuredDataPropertyTuple",
      Seq.empty[(String, String)] ++
          Seq("structuredDataPropertyContext" -> structuredDataPropertyContext.uuid.toString) ++
          Seq("structuredDataProperty" -> structuredDataProperty.uuid.toString) : _*))
    createStructuredDataPropertyTuple( extent, uuid, structuredDataPropertyContext, structuredDataProperty )
  }
  
  def createStructuredDataPropertyTuple
  ( extent: Extent,
    uuid: taggedTypes.StructuredDataPropertyTupleUUID,
    structuredDataPropertyContext: SingletonInstanceStructuredDataPropertyContext,
    structuredDataProperty: DataRelationshipToStructure )
  : (Extent, StructuredDataPropertyTuple)
  
  // SubDataPropertyOfAxiom
  def createSubDataPropertyOfAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    subProperty: EntityScalarDataProperty,
    superProperty: EntityScalarDataProperty )
  : (Extent, SubDataPropertyOfAxiom)
  = {
  	// implicitly derived uuid...
    import scala.Predef.ArrowAssoc
    val implicitUUID = taggedTypes.subDataPropertyOfAxiomUUID(namespaceUUID(
      "SubDataPropertyOfAxiom",
      Seq.empty[(String, String)] ++
          Seq("tbox" -> tbox.uuid.toString) ++
          Seq("subProperty" -> subProperty.uuid.toString) ++
          Seq("superProperty" -> superProperty.uuid.toString) : _*))
    createSubDataPropertyOfAxiom( extent, implicitUUID, tbox, subProperty, superProperty )
  }
  
  def createSubDataPropertyOfAxiom
  ( extent: Extent,
    uuid: taggedTypes.SubDataPropertyOfAxiomUUID,
    tbox: TerminologyBox,
    subProperty: EntityScalarDataProperty,
    superProperty: EntityScalarDataProperty )
  : (Extent, SubDataPropertyOfAxiom)
  
  // SubObjectPropertyOfAxiom
  def createSubObjectPropertyOfAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    subProperty: UnreifiedRelationship,
    superProperty: UnreifiedRelationship )
  : (Extent, SubObjectPropertyOfAxiom)
  = {
  	// implicitly derived uuid...
    import scala.Predef.ArrowAssoc
    val implicitUUID = taggedTypes.subObjectPropertyOfAxiomUUID(namespaceUUID(
      "SubObjectPropertyOfAxiom",
      Seq.empty[(String, String)] ++
          Seq("tbox" -> tbox.uuid.toString) ++
          Seq("subProperty" -> subProperty.uuid.toString) ++
          Seq("superProperty" -> superProperty.uuid.toString) : _*))
    createSubObjectPropertyOfAxiom( extent, implicitUUID, tbox, subProperty, superProperty )
  }
  
  def createSubObjectPropertyOfAxiom
  ( extent: Extent,
    uuid: taggedTypes.SubObjectPropertyOfAxiomUUID,
    tbox: TerminologyBox,
    subProperty: UnreifiedRelationship,
    superProperty: UnreifiedRelationship )
  : (Extent, SubObjectPropertyOfAxiom)
  
  // SynonymScalarRestriction
  def createSynonymScalarRestriction
  ( extent: Extent,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, SynonymScalarRestriction)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.synonymScalarRestrictionUUID(namespaceUUID(tbox.uuid.toString,  "name" -> name))
    createSynonymScalarRestriction( extent, uuid, tbox, restrictedRange, name )
  }
  
  def createSynonymScalarRestriction
  ( extent: Extent,
    uuid: taggedTypes.SynonymScalarRestrictionUUID,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, SynonymScalarRestriction)
  
  // TerminologyExtensionAxiom
  def createTerminologyExtensionAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    extendedTerminology: gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI )
  : (Extent, TerminologyExtensionAxiom)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.terminologyExtensionAxiomUUID(namespaceUUID(
      "TerminologyExtensionAxiom",
      Seq.empty[(String, String)] ++
          Seq("tbox" -> tbox.uuid.toString) ++
        Seq("extendedTerminology" -> namespaceUUID(extendedTerminology).toString) : _*))
    createTerminologyExtensionAxiom( extent, uuid, tbox, extendedTerminology )
  }
  
  def createTerminologyExtensionAxiom
  ( extent: Extent,
    uuid: taggedTypes.TerminologyExtensionAxiomUUID,
    tbox: TerminologyBox,
    extendedTerminology: gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI )
  : (Extent, TerminologyExtensionAxiom)
  
  // TerminologyGraph
  def createTerminologyGraph
  ( extent: Extent,
    kind: gov.nasa.jpl.imce.oml.tables.TerminologyKind,
    iri: gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI )
  : (Extent, TerminologyGraph)
  = {
    val uuid = taggedTypes.terminologyGraphUUID(namespaceUUID(iri.toString))
    createTerminologyGraph( extent, uuid, kind, iri )
  }
  
  def createTerminologyGraph
  ( extent: Extent,
    uuid: taggedTypes.TerminologyGraphUUID,
    kind: gov.nasa.jpl.imce.oml.tables.TerminologyKind,
    iri: gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI )
  : (Extent, TerminologyGraph)
  
  // TerminologyNestingAxiom
  def createTerminologyNestingAxiom
  ( extent: Extent,
    tbox: TerminologyBox,
    nestingContext: Concept,
    nestingTerminology: gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI )
  : (Extent, TerminologyNestingAxiom)
  = {
  	// derived uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.terminologyNestingAxiomUUID(namespaceUUID(
      "TerminologyNestingAxiom",
      Seq.empty[(String, String)] ++
          Seq("tbox" -> tbox.uuid.toString) ++
          Seq("nestingContext" -> nestingContext.uuid.toString) ++
        Seq("nestingTerminology" -> namespaceUUID(nestingTerminology).toString) : _*))
    createTerminologyNestingAxiom( extent, uuid, tbox, nestingContext, nestingTerminology )
  }
  
  def createTerminologyNestingAxiom
  ( extent: Extent,
    uuid: taggedTypes.TerminologyNestingAxiomUUID,
    tbox: TerminologyBox,
    nestingContext: Concept,
    nestingTerminology: gov.nasa.jpl.imce.oml.tables.taggedTypes.IRI )
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
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, TimeScalarRestriction)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.timeScalarRestrictionUUID(namespaceUUID(tbox.uuid.toString,  "name" -> name))
    createTimeScalarRestriction( extent, uuid, tbox, restrictedRange, minExclusive, minInclusive, maxExclusive, maxInclusive, name )
  }
  
  def createTimeScalarRestriction
  ( extent: Extent,
    uuid: taggedTypes.TimeScalarRestrictionUUID,
    tbox: TerminologyBox,
    restrictedRange: DataRange,
    minExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralDateTime],
    minInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralDateTime],
    maxExclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralDateTime],
    maxInclusive: scala.Option[gov.nasa.jpl.imce.oml.tables.LiteralDateTime],
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
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
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
  : (Extent, UnreifiedRelationship)
  = {
    // namespace uuid...
    import scala.Predef.ArrowAssoc
    val uuid = taggedTypes.unreifiedRelationshipUUID(namespaceUUID(tbox.uuid.toString,  "name" -> name))
    createUnreifiedRelationship( extent, uuid, tbox, source, target, isAsymmetric, isEssential, isFunctional, isInverseEssential, isInverseFunctional, isIrreflexive, isReflexive, isSymmetric, isTransitive, name )
  }
  
  def createUnreifiedRelationship
  ( extent: Extent,
    uuid: taggedTypes.UnreifiedRelationshipUUID,
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
    name: gov.nasa.jpl.imce.oml.tables.taggedTypes.LocalName )
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
    val uuid = taggedTypes.unreifiedRelationshipInstanceTupleUUID(namespaceUUID(
      "UnreifiedRelationshipInstanceTuple",
      Seq.empty[(String, String)] ++
          Seq("descriptionBox" -> descriptionBox.uuid.toString) ++
          Seq("unreifiedRelationship" -> unreifiedRelationship.uuid.toString) ++
          Seq("domain" -> domain.uuid.toString) ++
          Seq("range" -> range.uuid.toString) : _*))
    createUnreifiedRelationshipInstanceTuple( extent, uuid, descriptionBox, unreifiedRelationship, domain, range )
  }
  
  def createUnreifiedRelationshipInstanceTuple
  ( extent: Extent,
    uuid: taggedTypes.UnreifiedRelationshipInstanceTupleUUID,
    descriptionBox: DescriptionBox,
    unreifiedRelationship: UnreifiedRelationship,
    domain: ConceptualEntitySingletonInstance,
    range: ConceptualEntitySingletonInstance )
  : (Extent, UnreifiedRelationshipInstanceTuple)
  
}
