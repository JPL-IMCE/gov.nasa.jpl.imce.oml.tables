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


package gov.nasa.jpl.imce.oml.resolver

package object api {
	

  implicit def UUIDOrdering
  : scala.Ordering[java.util.UUID]
  = new scala.Ordering[java.util.UUID] {
    def compare(x: java.util.UUID, y:java.util.UUID)
    : scala.Int
    = x.compareTo(y)
  }

  implicit def annotationOrdering(implicit e: Extent)
  : scala.Ordering[Annotation]
  = new scala.Ordering[Annotation] {
  	def compare(x: Annotation, y: Annotation)
  	: scala.Int
  	= elementOrdering.compare(x.subject,y.subject) match {
  	 	case c_subject if 0 != c_subject => c_subject
  	 	case 0 => annotationPropertyOrdering.compare(x.property,y.property) match {
  	 	case c_property if 0 != c_property => c_property
  	 	case 0 => x.value.compareTo(y.value) match {
  	 	case c_value if 0 != c_value => c_value
  	 	case 0 => 0 } } }
  }
  
  implicit def annotationEntryOrdering(implicit e: Extent)
  : scala.Ordering[AnnotationEntry]
  = new scala.Ordering[AnnotationEntry] {
  	def compare(x: AnnotationEntry, y: AnnotationEntry)
  	: scala.Int
  	= moduleOrdering.compare(x.module,y.module) match {
  	 	case c_module if 0 != c_module => c_module
  	 	case 0 => elementOrdering.compare(x.subject,y.subject) match {
  	 	case c_subject if 0 != c_subject => c_subject
  	 	case 0 => x.value.compareTo(y.value) match {
  	 	case c_value if 0 != c_value => c_value
  	 	case 0 => 0 } } }
  }
  
  implicit def annotationPropertyOrdering
  : scala.Ordering[AnnotationProperty]
  = new scala.Ordering[AnnotationProperty] {
  	def compare(x: AnnotationProperty, y: AnnotationProperty)
  	: scala.Int
  	= x.uuid().compareTo(y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def anonymousConceptTaxonomyAxiomOrdering(implicit e: Extent)
  : scala.Ordering[AnonymousConceptTaxonomyAxiom]
  = new scala.Ordering[AnonymousConceptTaxonomyAxiom] {
  	def compare(x: AnonymousConceptTaxonomyAxiom, y: AnonymousConceptTaxonomyAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def aspectOrdering(implicit e: Extent)
  : scala.Ordering[Aspect]
  = new scala.Ordering[Aspect] {
  	def compare(x: Aspect, y: Aspect)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def aspectSpecializationAxiomOrdering(implicit e: Extent)
  : scala.Ordering[AspectSpecializationAxiom]
  = new scala.Ordering[AspectSpecializationAxiom] {
  	def compare(x: AspectSpecializationAxiom, y: AspectSpecializationAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def binaryScalarRestrictionOrdering(implicit e: Extent)
  : scala.Ordering[BinaryScalarRestriction]
  = new scala.Ordering[BinaryScalarRestriction] {
  	def compare(x: BinaryScalarRestriction, y: BinaryScalarRestriction)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def bundleOrdering(implicit e: Extent)
  : scala.Ordering[Bundle]
  = new scala.Ordering[Bundle] {
  	def compare(x: Bundle, y: Bundle)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def bundledTerminologyAxiomOrdering(implicit e: Extent)
  : scala.Ordering[BundledTerminologyAxiom]
  = new scala.Ordering[BundledTerminologyAxiom] {
  	def compare(x: BundledTerminologyAxiom, y: BundledTerminologyAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def conceptOrdering(implicit e: Extent)
  : scala.Ordering[Concept]
  = new scala.Ordering[Concept] {
  	def compare(x: Concept, y: Concept)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def conceptDesignationTerminologyAxiomOrdering(implicit e: Extent)
  : scala.Ordering[ConceptDesignationTerminologyAxiom]
  = new scala.Ordering[ConceptDesignationTerminologyAxiom] {
  	def compare(x: ConceptDesignationTerminologyAxiom, y: ConceptDesignationTerminologyAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def conceptInstanceOrdering(implicit e: Extent)
  : scala.Ordering[ConceptInstance]
  = new scala.Ordering[ConceptInstance] {
  	def compare(x: ConceptInstance, y: ConceptInstance)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def conceptSpecializationAxiomOrdering(implicit e: Extent)
  : scala.Ordering[ConceptSpecializationAxiom]
  = new scala.Ordering[ConceptSpecializationAxiom] {
  	def compare(x: ConceptSpecializationAxiom, y: ConceptSpecializationAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def conceptualEntitySingletonInstanceOrdering(implicit e: Extent)
  : scala.Ordering[ConceptualEntitySingletonInstance]
  = new scala.Ordering[ConceptualEntitySingletonInstance] {
  	def compare(x: ConceptualEntitySingletonInstance, y: ConceptualEntitySingletonInstance)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def dataRangeOrdering(implicit e: Extent)
  : scala.Ordering[DataRange]
  = new scala.Ordering[DataRange] {
  	def compare(x: DataRange, y: DataRange)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def dataRelationshipOrdering(implicit e: Extent)
  : scala.Ordering[DataRelationship]
  = new scala.Ordering[DataRelationship] {
  	def compare(x: DataRelationship, y: DataRelationship)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def dataStructureTupleOrdering(implicit e: Extent)
  : scala.Ordering[DataStructureTuple]
  = new scala.Ordering[DataStructureTuple] {
  	def compare(x: DataStructureTuple, y: DataStructureTuple)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def datatypeOrdering(implicit e: Extent)
  : scala.Ordering[Datatype]
  = new scala.Ordering[Datatype] {
  	def compare(x: Datatype, y: Datatype)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def descriptionBoxOrdering(implicit e: Extent)
  : scala.Ordering[DescriptionBox]
  = new scala.Ordering[DescriptionBox] {
  	def compare(x: DescriptionBox, y: DescriptionBox)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def descriptionBoxExtendsClosedWorldDefinitionsOrdering(implicit e: Extent)
  : scala.Ordering[DescriptionBoxExtendsClosedWorldDefinitions]
  = new scala.Ordering[DescriptionBoxExtendsClosedWorldDefinitions] {
  	def compare(x: DescriptionBoxExtendsClosedWorldDefinitions, y: DescriptionBoxExtendsClosedWorldDefinitions)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def descriptionBoxRefinementOrdering(implicit e: Extent)
  : scala.Ordering[DescriptionBoxRefinement]
  = new scala.Ordering[DescriptionBoxRefinement] {
  	def compare(x: DescriptionBoxRefinement, y: DescriptionBoxRefinement)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def descriptionBoxRelationshipOrdering(implicit e: Extent)
  : scala.Ordering[DescriptionBoxRelationship]
  = new scala.Ordering[DescriptionBoxRelationship] {
  	def compare(x: DescriptionBoxRelationship, y: DescriptionBoxRelationship)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def disjointUnionOfConceptsAxiomOrdering(implicit e: Extent)
  : scala.Ordering[DisjointUnionOfConceptsAxiom]
  = new scala.Ordering[DisjointUnionOfConceptsAxiom] {
  	def compare(x: DisjointUnionOfConceptsAxiom, y: DisjointUnionOfConceptsAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def elementOrdering(implicit e: Extent)
  : scala.Ordering[Element]
  = new scala.Ordering[Element] {
  	def compare(x: Element, y: Element)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityOrdering(implicit e: Extent)
  : scala.Ordering[Entity]
  = new scala.Ordering[Entity] {
  	def compare(x: Entity, y: Entity)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityExistentialRestrictionAxiomOrdering(implicit e: Extent)
  : scala.Ordering[EntityExistentialRestrictionAxiom]
  = new scala.Ordering[EntityExistentialRestrictionAxiom] {
  	def compare(x: EntityExistentialRestrictionAxiom, y: EntityExistentialRestrictionAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityRelationshipOrdering(implicit e: Extent)
  : scala.Ordering[EntityRelationship]
  = new scala.Ordering[EntityRelationship] {
  	def compare(x: EntityRelationship, y: EntityRelationship)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityRestrictionAxiomOrdering(implicit e: Extent)
  : scala.Ordering[EntityRestrictionAxiom]
  = new scala.Ordering[EntityRestrictionAxiom] {
  	def compare(x: EntityRestrictionAxiom, y: EntityRestrictionAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityScalarDataPropertyOrdering(implicit e: Extent)
  : scala.Ordering[EntityScalarDataProperty]
  = new scala.Ordering[EntityScalarDataProperty] {
  	def compare(x: EntityScalarDataProperty, y: EntityScalarDataProperty)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityScalarDataPropertyExistentialRestrictionAxiomOrdering(implicit e: Extent)
  : scala.Ordering[EntityScalarDataPropertyExistentialRestrictionAxiom]
  = new scala.Ordering[EntityScalarDataPropertyExistentialRestrictionAxiom] {
  	def compare(x: EntityScalarDataPropertyExistentialRestrictionAxiom, y: EntityScalarDataPropertyExistentialRestrictionAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityScalarDataPropertyParticularRestrictionAxiomOrdering(implicit e: Extent)
  : scala.Ordering[EntityScalarDataPropertyParticularRestrictionAxiom]
  = new scala.Ordering[EntityScalarDataPropertyParticularRestrictionAxiom] {
  	def compare(x: EntityScalarDataPropertyParticularRestrictionAxiom, y: EntityScalarDataPropertyParticularRestrictionAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityScalarDataPropertyRestrictionAxiomOrdering(implicit e: Extent)
  : scala.Ordering[EntityScalarDataPropertyRestrictionAxiom]
  = new scala.Ordering[EntityScalarDataPropertyRestrictionAxiom] {
  	def compare(x: EntityScalarDataPropertyRestrictionAxiom, y: EntityScalarDataPropertyRestrictionAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityScalarDataPropertyUniversalRestrictionAxiomOrdering(implicit e: Extent)
  : scala.Ordering[EntityScalarDataPropertyUniversalRestrictionAxiom]
  = new scala.Ordering[EntityScalarDataPropertyUniversalRestrictionAxiom] {
  	def compare(x: EntityScalarDataPropertyUniversalRestrictionAxiom, y: EntityScalarDataPropertyUniversalRestrictionAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityStructuredDataPropertyOrdering(implicit e: Extent)
  : scala.Ordering[EntityStructuredDataProperty]
  = new scala.Ordering[EntityStructuredDataProperty] {
  	def compare(x: EntityStructuredDataProperty, y: EntityStructuredDataProperty)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityUniversalRestrictionAxiomOrdering(implicit e: Extent)
  : scala.Ordering[EntityUniversalRestrictionAxiom]
  = new scala.Ordering[EntityUniversalRestrictionAxiom] {
  	def compare(x: EntityUniversalRestrictionAxiom, y: EntityUniversalRestrictionAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def iRIScalarRestrictionOrdering(implicit e: Extent)
  : scala.Ordering[IRIScalarRestriction]
  = new scala.Ordering[IRIScalarRestriction] {
  	def compare(x: IRIScalarRestriction, y: IRIScalarRestriction)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def moduleOrdering(implicit e: Extent)
  : scala.Ordering[Module]
  = new scala.Ordering[Module] {
  	def compare(x: Module, y: Module)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def moduleEdgeOrdering(implicit e: Extent)
  : scala.Ordering[ModuleEdge]
  = new scala.Ordering[ModuleEdge] {
  	def compare(x: ModuleEdge, y: ModuleEdge)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def moduleElementOrdering(implicit e: Extent)
  : scala.Ordering[ModuleElement]
  = new scala.Ordering[ModuleElement] {
  	def compare(x: ModuleElement, y: ModuleElement)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def numericScalarRestrictionOrdering(implicit e: Extent)
  : scala.Ordering[NumericScalarRestriction]
  = new scala.Ordering[NumericScalarRestriction] {
  	def compare(x: NumericScalarRestriction, y: NumericScalarRestriction)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def plainLiteralScalarRestrictionOrdering(implicit e: Extent)
  : scala.Ordering[PlainLiteralScalarRestriction]
  = new scala.Ordering[PlainLiteralScalarRestriction] {
  	def compare(x: PlainLiteralScalarRestriction, y: PlainLiteralScalarRestriction)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def reifiedRelationshipOrdering(implicit e: Extent)
  : scala.Ordering[ReifiedRelationship]
  = new scala.Ordering[ReifiedRelationship] {
  	def compare(x: ReifiedRelationship, y: ReifiedRelationship)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def reifiedRelationshipInstanceOrdering(implicit e: Extent)
  : scala.Ordering[ReifiedRelationshipInstance]
  = new scala.Ordering[ReifiedRelationshipInstance] {
  	def compare(x: ReifiedRelationshipInstance, y: ReifiedRelationshipInstance)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def reifiedRelationshipInstanceDomainOrdering(implicit e: Extent)
  : scala.Ordering[ReifiedRelationshipInstanceDomain]
  = new scala.Ordering[ReifiedRelationshipInstanceDomain] {
  	def compare(x: ReifiedRelationshipInstanceDomain, y: ReifiedRelationshipInstanceDomain)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def reifiedRelationshipInstanceRangeOrdering(implicit e: Extent)
  : scala.Ordering[ReifiedRelationshipInstanceRange]
  = new scala.Ordering[ReifiedRelationshipInstanceRange] {
  	def compare(x: ReifiedRelationshipInstanceRange, y: ReifiedRelationshipInstanceRange)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def reifiedRelationshipSpecializationAxiomOrdering(implicit e: Extent)
  : scala.Ordering[ReifiedRelationshipSpecializationAxiom]
  = new scala.Ordering[ReifiedRelationshipSpecializationAxiom] {
  	def compare(x: ReifiedRelationshipSpecializationAxiom, y: ReifiedRelationshipSpecializationAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def restrictedDataRangeOrdering(implicit e: Extent)
  : scala.Ordering[RestrictedDataRange]
  = new scala.Ordering[RestrictedDataRange] {
  	def compare(x: RestrictedDataRange, y: RestrictedDataRange)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def rootConceptTaxonomyAxiomOrdering(implicit e: Extent)
  : scala.Ordering[RootConceptTaxonomyAxiom]
  = new scala.Ordering[RootConceptTaxonomyAxiom] {
  	def compare(x: RootConceptTaxonomyAxiom, y: RootConceptTaxonomyAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def scalarOrdering(implicit e: Extent)
  : scala.Ordering[Scalar]
  = new scala.Ordering[Scalar] {
  	def compare(x: Scalar, y: Scalar)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def scalarDataPropertyOrdering(implicit e: Extent)
  : scala.Ordering[ScalarDataProperty]
  = new scala.Ordering[ScalarDataProperty] {
  	def compare(x: ScalarDataProperty, y: ScalarDataProperty)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def scalarDataPropertyValueOrdering(implicit e: Extent)
  : scala.Ordering[ScalarDataPropertyValue]
  = new scala.Ordering[ScalarDataPropertyValue] {
  	def compare(x: ScalarDataPropertyValue, y: ScalarDataPropertyValue)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def scalarOneOfLiteralAxiomOrdering(implicit e: Extent)
  : scala.Ordering[ScalarOneOfLiteralAxiom]
  = new scala.Ordering[ScalarOneOfLiteralAxiom] {
  	def compare(x: ScalarOneOfLiteralAxiom, y: ScalarOneOfLiteralAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def scalarOneOfRestrictionOrdering(implicit e: Extent)
  : scala.Ordering[ScalarOneOfRestriction]
  = new scala.Ordering[ScalarOneOfRestriction] {
  	def compare(x: ScalarOneOfRestriction, y: ScalarOneOfRestriction)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def singletonInstanceOrdering(implicit e: Extent)
  : scala.Ordering[SingletonInstance]
  = new scala.Ordering[SingletonInstance] {
  	def compare(x: SingletonInstance, y: SingletonInstance)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def specializationAxiomOrdering(implicit e: Extent)
  : scala.Ordering[SpecializationAxiom]
  = new scala.Ordering[SpecializationAxiom] {
  	def compare(x: SpecializationAxiom, y: SpecializationAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def specificDisjointConceptAxiomOrdering(implicit e: Extent)
  : scala.Ordering[SpecificDisjointConceptAxiom]
  = new scala.Ordering[SpecificDisjointConceptAxiom] {
  	def compare(x: SpecificDisjointConceptAxiom, y: SpecificDisjointConceptAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def stringScalarRestrictionOrdering(implicit e: Extent)
  : scala.Ordering[StringScalarRestriction]
  = new scala.Ordering[StringScalarRestriction] {
  	def compare(x: StringScalarRestriction, y: StringScalarRestriction)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def structureOrdering(implicit e: Extent)
  : scala.Ordering[Structure]
  = new scala.Ordering[Structure] {
  	def compare(x: Structure, y: Structure)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def structuredDataPropertyOrdering(implicit e: Extent)
  : scala.Ordering[StructuredDataProperty]
  = new scala.Ordering[StructuredDataProperty] {
  	def compare(x: StructuredDataProperty, y: StructuredDataProperty)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def structuredDataPropertyValueOrdering(implicit e: Extent)
  : scala.Ordering[StructuredDataPropertyValue]
  = new scala.Ordering[StructuredDataPropertyValue] {
  	def compare(x: StructuredDataPropertyValue, y: StructuredDataPropertyValue)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def synonymScalarRestrictionOrdering(implicit e: Extent)
  : scala.Ordering[SynonymScalarRestriction]
  = new scala.Ordering[SynonymScalarRestriction] {
  	def compare(x: SynonymScalarRestriction, y: SynonymScalarRestriction)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def termOrdering(implicit e: Extent)
  : scala.Ordering[Term]
  = new scala.Ordering[Term] {
  	def compare(x: Term, y: Term)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def termAxiomOrdering(implicit e: Extent)
  : scala.Ordering[TermAxiom]
  = new scala.Ordering[TermAxiom] {
  	def compare(x: TermAxiom, y: TermAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def terminologyAxiomOrdering(implicit e: Extent)
  : scala.Ordering[TerminologyAxiom]
  = new scala.Ordering[TerminologyAxiom] {
  	def compare(x: TerminologyAxiom, y: TerminologyAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def terminologyBoxOrdering(implicit e: Extent)
  : scala.Ordering[TerminologyBox]
  = new scala.Ordering[TerminologyBox] {
  	def compare(x: TerminologyBox, y: TerminologyBox)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def terminologyBoxAxiomOrdering(implicit e: Extent)
  : scala.Ordering[TerminologyBoxAxiom]
  = new scala.Ordering[TerminologyBoxAxiom] {
  	def compare(x: TerminologyBoxAxiom, y: TerminologyBoxAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def terminologyBoxStatementOrdering(implicit e: Extent)
  : scala.Ordering[TerminologyBoxStatement]
  = new scala.Ordering[TerminologyBoxStatement] {
  	def compare(x: TerminologyBoxStatement, y: TerminologyBoxStatement)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def terminologyBundleAxiomOrdering(implicit e: Extent)
  : scala.Ordering[TerminologyBundleAxiom]
  = new scala.Ordering[TerminologyBundleAxiom] {
  	def compare(x: TerminologyBundleAxiom, y: TerminologyBundleAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def terminologyBundleStatementOrdering(implicit e: Extent)
  : scala.Ordering[TerminologyBundleStatement]
  = new scala.Ordering[TerminologyBundleStatement] {
  	def compare(x: TerminologyBundleStatement, y: TerminologyBundleStatement)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def terminologyExtensionAxiomOrdering(implicit e: Extent)
  : scala.Ordering[TerminologyExtensionAxiom]
  = new scala.Ordering[TerminologyExtensionAxiom] {
  	def compare(x: TerminologyExtensionAxiom, y: TerminologyExtensionAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def terminologyGraphOrdering(implicit e: Extent)
  : scala.Ordering[TerminologyGraph]
  = new scala.Ordering[TerminologyGraph] {
  	def compare(x: TerminologyGraph, y: TerminologyGraph)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def terminologyInstanceAssertionOrdering(implicit e: Extent)
  : scala.Ordering[TerminologyInstanceAssertion]
  = new scala.Ordering[TerminologyInstanceAssertion] {
  	def compare(x: TerminologyInstanceAssertion, y: TerminologyInstanceAssertion)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def terminologyNestingAxiomOrdering(implicit e: Extent)
  : scala.Ordering[TerminologyNestingAxiom]
  = new scala.Ordering[TerminologyNestingAxiom] {
  	def compare(x: TerminologyNestingAxiom, y: TerminologyNestingAxiom)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def timeScalarRestrictionOrdering(implicit e: Extent)
  : scala.Ordering[TimeScalarRestriction]
  = new scala.Ordering[TimeScalarRestriction] {
  	def compare(x: TimeScalarRestriction, y: TimeScalarRestriction)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def unreifiedRelationshipOrdering(implicit e: Extent)
  : scala.Ordering[UnreifiedRelationship]
  = new scala.Ordering[UnreifiedRelationship] {
  	def compare(x: UnreifiedRelationship, y: UnreifiedRelationship)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def unreifiedRelationshipInstanceTupleOrdering(implicit e: Extent)
  : scala.Ordering[UnreifiedRelationshipInstanceTuple]
  = new scala.Ordering[UnreifiedRelationshipInstanceTuple] {
  	def compare(x: UnreifiedRelationshipInstanceTuple, y: UnreifiedRelationshipInstanceTuple)
  	: scala.Int
  	= scala.Ordering.Option[java.util.UUID](UUIDOrdering).compare(x.uuid(),y.uuid()) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
}
