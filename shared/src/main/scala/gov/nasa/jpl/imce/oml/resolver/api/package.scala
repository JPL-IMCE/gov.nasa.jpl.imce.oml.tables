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

  implicit def annotationPropertyOrdering
  : scala.Ordering[AnnotationProperty]
  = new scala.Ordering[AnnotationProperty] {
  	def compare(x: AnnotationProperty, y: AnnotationProperty)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def annotationPropertyValueOrdering
  : scala.Ordering[AnnotationPropertyValue]
  = new scala.Ordering[AnnotationPropertyValue] {
  	def compare(x: AnnotationPropertyValue, y: AnnotationPropertyValue)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def anonymousConceptUnionAxiomOrdering
  : scala.Ordering[AnonymousConceptUnionAxiom]
  = new scala.Ordering[AnonymousConceptUnionAxiom] {
  	def compare(x: AnonymousConceptUnionAxiom, y: AnonymousConceptUnionAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def aspectOrdering
  : scala.Ordering[Aspect]
  = new scala.Ordering[Aspect] {
  	def compare(x: Aspect, y: Aspect)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def aspectSpecializationAxiomOrdering
  : scala.Ordering[AspectSpecializationAxiom]
  = new scala.Ordering[AspectSpecializationAxiom] {
  	def compare(x: AspectSpecializationAxiom, y: AspectSpecializationAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def binaryScalarRestrictionOrdering
  : scala.Ordering[BinaryScalarRestriction]
  = new scala.Ordering[BinaryScalarRestriction] {
  	def compare(x: BinaryScalarRestriction, y: BinaryScalarRestriction)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def bundleOrdering
  : scala.Ordering[Bundle]
  = new scala.Ordering[Bundle] {
  	def compare(x: Bundle, y: Bundle)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def bundledTerminologyAxiomOrdering
  : scala.Ordering[BundledTerminologyAxiom]
  = new scala.Ordering[BundledTerminologyAxiom] {
  	def compare(x: BundledTerminologyAxiom, y: BundledTerminologyAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def chainRuleOrdering
  : scala.Ordering[ChainRule]
  = new scala.Ordering[ChainRule] {
  	def compare(x: ChainRule, y: ChainRule)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def conceptOrdering
  : scala.Ordering[Concept]
  = new scala.Ordering[Concept] {
  	def compare(x: Concept, y: Concept)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def conceptDesignationTerminologyAxiomOrdering
  : scala.Ordering[ConceptDesignationTerminologyAxiom]
  = new scala.Ordering[ConceptDesignationTerminologyAxiom] {
  	def compare(x: ConceptDesignationTerminologyAxiom, y: ConceptDesignationTerminologyAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def conceptInstanceOrdering
  : scala.Ordering[ConceptInstance]
  = new scala.Ordering[ConceptInstance] {
  	def compare(x: ConceptInstance, y: ConceptInstance)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def conceptSpecializationAxiomOrdering
  : scala.Ordering[ConceptSpecializationAxiom]
  = new scala.Ordering[ConceptSpecializationAxiom] {
  	def compare(x: ConceptSpecializationAxiom, y: ConceptSpecializationAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def conceptTreeDisjunctionOrdering
  : scala.Ordering[ConceptTreeDisjunction]
  = new scala.Ordering[ConceptTreeDisjunction] {
  	def compare(x: ConceptTreeDisjunction, y: ConceptTreeDisjunction)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def conceptualEntityOrdering
  : scala.Ordering[ConceptualEntity]
  = new scala.Ordering[ConceptualEntity] {
  	def compare(x: ConceptualEntity, y: ConceptualEntity)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def conceptualEntitySingletonInstanceOrdering
  : scala.Ordering[ConceptualEntitySingletonInstance]
  = new scala.Ordering[ConceptualEntitySingletonInstance] {
  	def compare(x: ConceptualEntitySingletonInstance, y: ConceptualEntitySingletonInstance)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def crossReferencabilityKindOrdering
  : scala.Ordering[CrossReferencabilityKind]
  = new scala.Ordering[CrossReferencabilityKind] {
  	def compare(x: CrossReferencabilityKind, y: CrossReferencabilityKind)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def crossReferencableKindOrdering
  : scala.Ordering[CrossReferencableKind]
  = new scala.Ordering[CrossReferencableKind] {
  	def compare(x: CrossReferencableKind, y: CrossReferencableKind)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def dataRangeOrdering
  : scala.Ordering[DataRange]
  = new scala.Ordering[DataRange] {
  	def compare(x: DataRange, y: DataRange)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def dataRelationshipOrdering
  : scala.Ordering[DataRelationship]
  = new scala.Ordering[DataRelationship] {
  	def compare(x: DataRelationship, y: DataRelationship)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def dataRelationshipDomainOrdering
  : scala.Ordering[DataRelationshipDomain]
  = new scala.Ordering[DataRelationshipDomain] {
  	def compare(x: DataRelationshipDomain, y: DataRelationshipDomain)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def dataRelationshipFromEntityOrdering
  : scala.Ordering[DataRelationshipFromEntity]
  = new scala.Ordering[DataRelationshipFromEntity] {
  	def compare(x: DataRelationshipFromEntity, y: DataRelationshipFromEntity)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def dataRelationshipFromStructureOrdering
  : scala.Ordering[DataRelationshipFromStructure]
  = new scala.Ordering[DataRelationshipFromStructure] {
  	def compare(x: DataRelationshipFromStructure, y: DataRelationshipFromStructure)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def dataRelationshipRangeOrdering
  : scala.Ordering[DataRelationshipRange]
  = new scala.Ordering[DataRelationshipRange] {
  	def compare(x: DataRelationshipRange, y: DataRelationshipRange)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def dataRelationshipToScalarOrdering
  : scala.Ordering[DataRelationshipToScalar]
  = new scala.Ordering[DataRelationshipToScalar] {
  	def compare(x: DataRelationshipToScalar, y: DataRelationshipToScalar)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def dataRelationshipToStructureOrdering
  : scala.Ordering[DataRelationshipToStructure]
  = new scala.Ordering[DataRelationshipToStructure] {
  	def compare(x: DataRelationshipToStructure, y: DataRelationshipToStructure)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def datatypeOrdering
  : scala.Ordering[Datatype]
  = new scala.Ordering[Datatype] {
  	def compare(x: Datatype, y: Datatype)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def descriptionBoxOrdering
  : scala.Ordering[DescriptionBox]
  = new scala.Ordering[DescriptionBox] {
  	def compare(x: DescriptionBox, y: DescriptionBox)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def descriptionBoxExtendsClosedWorldDefinitionsOrdering
  : scala.Ordering[DescriptionBoxExtendsClosedWorldDefinitions]
  = new scala.Ordering[DescriptionBoxExtendsClosedWorldDefinitions] {
  	def compare(x: DescriptionBoxExtendsClosedWorldDefinitions, y: DescriptionBoxExtendsClosedWorldDefinitions)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def descriptionBoxRefinementOrdering
  : scala.Ordering[DescriptionBoxRefinement]
  = new scala.Ordering[DescriptionBoxRefinement] {
  	def compare(x: DescriptionBoxRefinement, y: DescriptionBoxRefinement)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def descriptionBoxRelationshipOrdering
  : scala.Ordering[DescriptionBoxRelationship]
  = new scala.Ordering[DescriptionBoxRelationship] {
  	def compare(x: DescriptionBoxRelationship, y: DescriptionBoxRelationship)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def directedBinaryRelationshipKindOrdering
  : scala.Ordering[DirectedBinaryRelationshipKind]
  = new scala.Ordering[DirectedBinaryRelationshipKind] {
  	def compare(x: DirectedBinaryRelationshipKind, y: DirectedBinaryRelationshipKind)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def disjointUnionOfConceptsAxiomOrdering
  : scala.Ordering[DisjointUnionOfConceptsAxiom]
  = new scala.Ordering[DisjointUnionOfConceptsAxiom] {
  	def compare(x: DisjointUnionOfConceptsAxiom, y: DisjointUnionOfConceptsAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def elementCrossReferenceTupleOrdering
  : scala.Ordering[ElementCrossReferenceTuple]
  = new scala.Ordering[ElementCrossReferenceTuple] {
  	def compare(x: ElementCrossReferenceTuple, y: ElementCrossReferenceTuple)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityOrdering
  : scala.Ordering[Entity]
  = new scala.Ordering[Entity] {
  	def compare(x: Entity, y: Entity)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityExistentialRestrictionAxiomOrdering
  : scala.Ordering[EntityExistentialRestrictionAxiom]
  = new scala.Ordering[EntityExistentialRestrictionAxiom] {
  	def compare(x: EntityExistentialRestrictionAxiom, y: EntityExistentialRestrictionAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityRelationshipOrdering
  : scala.Ordering[EntityRelationship]
  = new scala.Ordering[EntityRelationship] {
  	def compare(x: EntityRelationship, y: EntityRelationship)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityRestrictionAxiomOrdering
  : scala.Ordering[EntityRestrictionAxiom]
  = new scala.Ordering[EntityRestrictionAxiom] {
  	def compare(x: EntityRestrictionAxiom, y: EntityRestrictionAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityScalarDataPropertyOrdering
  : scala.Ordering[EntityScalarDataProperty]
  = new scala.Ordering[EntityScalarDataProperty] {
  	def compare(x: EntityScalarDataProperty, y: EntityScalarDataProperty)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityScalarDataPropertyExistentialRestrictionAxiomOrdering
  : scala.Ordering[EntityScalarDataPropertyExistentialRestrictionAxiom]
  = new scala.Ordering[EntityScalarDataPropertyExistentialRestrictionAxiom] {
  	def compare(x: EntityScalarDataPropertyExistentialRestrictionAxiom, y: EntityScalarDataPropertyExistentialRestrictionAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityScalarDataPropertyParticularRestrictionAxiomOrdering
  : scala.Ordering[EntityScalarDataPropertyParticularRestrictionAxiom]
  = new scala.Ordering[EntityScalarDataPropertyParticularRestrictionAxiom] {
  	def compare(x: EntityScalarDataPropertyParticularRestrictionAxiom, y: EntityScalarDataPropertyParticularRestrictionAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityScalarDataPropertyRestrictionAxiomOrdering
  : scala.Ordering[EntityScalarDataPropertyRestrictionAxiom]
  = new scala.Ordering[EntityScalarDataPropertyRestrictionAxiom] {
  	def compare(x: EntityScalarDataPropertyRestrictionAxiom, y: EntityScalarDataPropertyRestrictionAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityScalarDataPropertyUniversalRestrictionAxiomOrdering
  : scala.Ordering[EntityScalarDataPropertyUniversalRestrictionAxiom]
  = new scala.Ordering[EntityScalarDataPropertyUniversalRestrictionAxiom] {
  	def compare(x: EntityScalarDataPropertyUniversalRestrictionAxiom, y: EntityScalarDataPropertyUniversalRestrictionAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityStructuredDataPropertyOrdering
  : scala.Ordering[EntityStructuredDataProperty]
  = new scala.Ordering[EntityStructuredDataProperty] {
  	def compare(x: EntityStructuredDataProperty, y: EntityStructuredDataProperty)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityStructuredDataPropertyParticularRestrictionAxiomOrdering
  : scala.Ordering[EntityStructuredDataPropertyParticularRestrictionAxiom]
  = new scala.Ordering[EntityStructuredDataPropertyParticularRestrictionAxiom] {
  	def compare(x: EntityStructuredDataPropertyParticularRestrictionAxiom, y: EntityStructuredDataPropertyParticularRestrictionAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityStructuredDataPropertyRestrictionAxiomOrdering
  : scala.Ordering[EntityStructuredDataPropertyRestrictionAxiom]
  = new scala.Ordering[EntityStructuredDataPropertyRestrictionAxiom] {
  	def compare(x: EntityStructuredDataPropertyRestrictionAxiom, y: EntityStructuredDataPropertyRestrictionAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def entityUniversalRestrictionAxiomOrdering
  : scala.Ordering[EntityUniversalRestrictionAxiom]
  = new scala.Ordering[EntityUniversalRestrictionAxiom] {
  	def compare(x: EntityUniversalRestrictionAxiom, y: EntityUniversalRestrictionAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def extrinsicIdentityKindOrdering
  : scala.Ordering[ExtrinsicIdentityKind]
  = new scala.Ordering[ExtrinsicIdentityKind] {
  	def compare(x: ExtrinsicIdentityKind, y: ExtrinsicIdentityKind)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def forwardPropertyOrdering
  : scala.Ordering[ForwardProperty]
  = new scala.Ordering[ForwardProperty] {
  	def compare(x: ForwardProperty, y: ForwardProperty)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def iRIScalarRestrictionOrdering
  : scala.Ordering[IRIScalarRestriction]
  = new scala.Ordering[IRIScalarRestriction] {
  	def compare(x: IRIScalarRestriction, y: IRIScalarRestriction)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def identityKindOrdering
  : scala.Ordering[IdentityKind]
  = new scala.Ordering[IdentityKind] {
  	def compare(x: IdentityKind, y: IdentityKind)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def intrinsicIdentityKindOrdering
  : scala.Ordering[IntrinsicIdentityKind]
  = new scala.Ordering[IntrinsicIdentityKind] {
  	def compare(x: IntrinsicIdentityKind, y: IntrinsicIdentityKind)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def inversePropertyOrdering
  : scala.Ordering[InverseProperty]
  = new scala.Ordering[InverseProperty] {
  	def compare(x: InverseProperty, y: InverseProperty)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def logicalElementOrdering
  : scala.Ordering[LogicalElement]
  = new scala.Ordering[LogicalElement] {
  	def compare(x: LogicalElement, y: LogicalElement)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def moduleOrdering
  : scala.Ordering[Module]
  = new scala.Ordering[Module] {
  	def compare(x: Module, y: Module)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def moduleEdgeOrdering
  : scala.Ordering[ModuleEdge]
  = new scala.Ordering[ModuleEdge] {
  	def compare(x: ModuleEdge, y: ModuleEdge)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def moduleElementOrdering
  : scala.Ordering[ModuleElement]
  = new scala.Ordering[ModuleElement] {
  	def compare(x: ModuleElement, y: ModuleElement)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def nonCrossReferencableKindOrdering
  : scala.Ordering[NonCrossReferencableKind]
  = new scala.Ordering[NonCrossReferencableKind] {
  	def compare(x: NonCrossReferencableKind, y: NonCrossReferencableKind)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def nonLogicalElementOrdering
  : scala.Ordering[NonLogicalElement]
  = new scala.Ordering[NonLogicalElement] {
  	def compare(x: NonLogicalElement, y: NonLogicalElement)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def numericScalarRestrictionOrdering
  : scala.Ordering[NumericScalarRestriction]
  = new scala.Ordering[NumericScalarRestriction] {
  	def compare(x: NumericScalarRestriction, y: NumericScalarRestriction)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def plainLiteralScalarRestrictionOrdering
  : scala.Ordering[PlainLiteralScalarRestriction]
  = new scala.Ordering[PlainLiteralScalarRestriction] {
  	def compare(x: PlainLiteralScalarRestriction, y: PlainLiteralScalarRestriction)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def predicateOrdering
  : scala.Ordering[Predicate]
  = new scala.Ordering[Predicate] {
  	def compare(x: Predicate, y: Predicate)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def reifiedRelationshipOrdering
  : scala.Ordering[ReifiedRelationship]
  = new scala.Ordering[ReifiedRelationship] {
  	def compare(x: ReifiedRelationship, y: ReifiedRelationship)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def reifiedRelationshipInstanceOrdering
  : scala.Ordering[ReifiedRelationshipInstance]
  = new scala.Ordering[ReifiedRelationshipInstance] {
  	def compare(x: ReifiedRelationshipInstance, y: ReifiedRelationshipInstance)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def reifiedRelationshipInstanceDomainOrdering
  : scala.Ordering[ReifiedRelationshipInstanceDomain]
  = new scala.Ordering[ReifiedRelationshipInstanceDomain] {
  	def compare(x: ReifiedRelationshipInstanceDomain, y: ReifiedRelationshipInstanceDomain)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def reifiedRelationshipInstanceRangeOrdering
  : scala.Ordering[ReifiedRelationshipInstanceRange]
  = new scala.Ordering[ReifiedRelationshipInstanceRange] {
  	def compare(x: ReifiedRelationshipInstanceRange, y: ReifiedRelationshipInstanceRange)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def reifiedRelationshipSpecializationAxiomOrdering
  : scala.Ordering[ReifiedRelationshipSpecializationAxiom]
  = new scala.Ordering[ReifiedRelationshipSpecializationAxiom] {
  	def compare(x: ReifiedRelationshipSpecializationAxiom, y: ReifiedRelationshipSpecializationAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def resourceOrdering
  : scala.Ordering[Resource]
  = new scala.Ordering[Resource] {
  	def compare(x: Resource, y: Resource)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def restrictableRelationshipOrdering
  : scala.Ordering[RestrictableRelationship]
  = new scala.Ordering[RestrictableRelationship] {
  	def compare(x: RestrictableRelationship, y: RestrictableRelationship)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def restrictedDataRangeOrdering
  : scala.Ordering[RestrictedDataRange]
  = new scala.Ordering[RestrictedDataRange] {
  	def compare(x: RestrictedDataRange, y: RestrictedDataRange)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def restrictionScalarDataPropertyValueOrdering
  : scala.Ordering[RestrictionScalarDataPropertyValue]
  = new scala.Ordering[RestrictionScalarDataPropertyValue] {
  	def compare(x: RestrictionScalarDataPropertyValue, y: RestrictionScalarDataPropertyValue)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def restrictionStructuredDataPropertyContextOrdering
  : scala.Ordering[RestrictionStructuredDataPropertyContext]
  = new scala.Ordering[RestrictionStructuredDataPropertyContext] {
  	def compare(x: RestrictionStructuredDataPropertyContext, y: RestrictionStructuredDataPropertyContext)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def restrictionStructuredDataPropertyTupleOrdering
  : scala.Ordering[RestrictionStructuredDataPropertyTuple]
  = new scala.Ordering[RestrictionStructuredDataPropertyTuple] {
  	def compare(x: RestrictionStructuredDataPropertyTuple, y: RestrictionStructuredDataPropertyTuple)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def rootConceptTaxonomyAxiomOrdering
  : scala.Ordering[RootConceptTaxonomyAxiom]
  = new scala.Ordering[RootConceptTaxonomyAxiom] {
  	def compare(x: RootConceptTaxonomyAxiom, y: RootConceptTaxonomyAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def ruleOrdering
  : scala.Ordering[Rule]
  = new scala.Ordering[Rule] {
  	def compare(x: Rule, y: Rule)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def ruleBodySegmentOrdering
  : scala.Ordering[RuleBodySegment]
  = new scala.Ordering[RuleBodySegment] {
  	def compare(x: RuleBodySegment, y: RuleBodySegment)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def scalarOrdering
  : scala.Ordering[Scalar]
  = new scala.Ordering[Scalar] {
  	def compare(x: Scalar, y: Scalar)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def scalarDataPropertyOrdering
  : scala.Ordering[ScalarDataProperty]
  = new scala.Ordering[ScalarDataProperty] {
  	def compare(x: ScalarDataProperty, y: ScalarDataProperty)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def scalarDataPropertyValueOrdering
  : scala.Ordering[ScalarDataPropertyValue]
  = new scala.Ordering[ScalarDataPropertyValue] {
  	def compare(x: ScalarDataPropertyValue, y: ScalarDataPropertyValue)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def scalarOneOfLiteralAxiomOrdering
  : scala.Ordering[ScalarOneOfLiteralAxiom]
  = new scala.Ordering[ScalarOneOfLiteralAxiom] {
  	def compare(x: ScalarOneOfLiteralAxiom, y: ScalarOneOfLiteralAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def scalarOneOfRestrictionOrdering
  : scala.Ordering[ScalarOneOfRestriction]
  = new scala.Ordering[ScalarOneOfRestriction] {
  	def compare(x: ScalarOneOfRestriction, y: ScalarOneOfRestriction)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def segmentPredicateOrdering
  : scala.Ordering[SegmentPredicate]
  = new scala.Ordering[SegmentPredicate] {
  	def compare(x: SegmentPredicate, y: SegmentPredicate)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def singletonInstanceScalarDataPropertyValueOrdering
  : scala.Ordering[SingletonInstanceScalarDataPropertyValue]
  = new scala.Ordering[SingletonInstanceScalarDataPropertyValue] {
  	def compare(x: SingletonInstanceScalarDataPropertyValue, y: SingletonInstanceScalarDataPropertyValue)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def singletonInstanceStructuredDataPropertyContextOrdering
  : scala.Ordering[SingletonInstanceStructuredDataPropertyContext]
  = new scala.Ordering[SingletonInstanceStructuredDataPropertyContext] {
  	def compare(x: SingletonInstanceStructuredDataPropertyContext, y: SingletonInstanceStructuredDataPropertyContext)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def singletonInstanceStructuredDataPropertyValueOrdering
  : scala.Ordering[SingletonInstanceStructuredDataPropertyValue]
  = new scala.Ordering[SingletonInstanceStructuredDataPropertyValue] {
  	def compare(x: SingletonInstanceStructuredDataPropertyValue, y: SingletonInstanceStructuredDataPropertyValue)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def specializationAxiomOrdering
  : scala.Ordering[SpecializationAxiom]
  = new scala.Ordering[SpecializationAxiom] {
  	def compare(x: SpecializationAxiom, y: SpecializationAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def specificDisjointConceptAxiomOrdering
  : scala.Ordering[SpecificDisjointConceptAxiom]
  = new scala.Ordering[SpecificDisjointConceptAxiom] {
  	def compare(x: SpecificDisjointConceptAxiom, y: SpecificDisjointConceptAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def stringScalarRestrictionOrdering
  : scala.Ordering[StringScalarRestriction]
  = new scala.Ordering[StringScalarRestriction] {
  	def compare(x: StringScalarRestriction, y: StringScalarRestriction)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def structureOrdering
  : scala.Ordering[Structure]
  = new scala.Ordering[Structure] {
  	def compare(x: Structure, y: Structure)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def structuredDataPropertyOrdering
  : scala.Ordering[StructuredDataProperty]
  = new scala.Ordering[StructuredDataProperty] {
  	def compare(x: StructuredDataProperty, y: StructuredDataProperty)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def structuredDataPropertyTupleOrdering
  : scala.Ordering[StructuredDataPropertyTuple]
  = new scala.Ordering[StructuredDataPropertyTuple] {
  	def compare(x: StructuredDataPropertyTuple, y: StructuredDataPropertyTuple)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def subDataPropertyOfAxiomOrdering
  : scala.Ordering[SubDataPropertyOfAxiom]
  = new scala.Ordering[SubDataPropertyOfAxiom] {
  	def compare(x: SubDataPropertyOfAxiom, y: SubDataPropertyOfAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def subObjectPropertyOfAxiomOrdering
  : scala.Ordering[SubObjectPropertyOfAxiom]
  = new scala.Ordering[SubObjectPropertyOfAxiom] {
  	def compare(x: SubObjectPropertyOfAxiom, y: SubObjectPropertyOfAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def synonymScalarRestrictionOrdering
  : scala.Ordering[SynonymScalarRestriction]
  = new scala.Ordering[SynonymScalarRestriction] {
  	def compare(x: SynonymScalarRestriction, y: SynonymScalarRestriction)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def termOrdering
  : scala.Ordering[Term]
  = new scala.Ordering[Term] {
  	def compare(x: Term, y: Term)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def termAxiomOrdering
  : scala.Ordering[TermAxiom]
  = new scala.Ordering[TermAxiom] {
  	def compare(x: TermAxiom, y: TermAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def terminologyAxiomOrdering
  : scala.Ordering[TerminologyAxiom]
  = new scala.Ordering[TerminologyAxiom] {
  	def compare(x: TerminologyAxiom, y: TerminologyAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def terminologyBoxOrdering
  : scala.Ordering[TerminologyBox]
  = new scala.Ordering[TerminologyBox] {
  	def compare(x: TerminologyBox, y: TerminologyBox)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def terminologyBoxAxiomOrdering
  : scala.Ordering[TerminologyBoxAxiom]
  = new scala.Ordering[TerminologyBoxAxiom] {
  	def compare(x: TerminologyBoxAxiom, y: TerminologyBoxAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def terminologyBoxStatementOrdering
  : scala.Ordering[TerminologyBoxStatement]
  = new scala.Ordering[TerminologyBoxStatement] {
  	def compare(x: TerminologyBoxStatement, y: TerminologyBoxStatement)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def terminologyBundleAxiomOrdering
  : scala.Ordering[TerminologyBundleAxiom]
  = new scala.Ordering[TerminologyBundleAxiom] {
  	def compare(x: TerminologyBundleAxiom, y: TerminologyBundleAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def terminologyBundleStatementOrdering
  : scala.Ordering[TerminologyBundleStatement]
  = new scala.Ordering[TerminologyBundleStatement] {
  	def compare(x: TerminologyBundleStatement, y: TerminologyBundleStatement)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def terminologyExtensionAxiomOrdering
  : scala.Ordering[TerminologyExtensionAxiom]
  = new scala.Ordering[TerminologyExtensionAxiom] {
  	def compare(x: TerminologyExtensionAxiom, y: TerminologyExtensionAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def terminologyGraphOrdering
  : scala.Ordering[TerminologyGraph]
  = new scala.Ordering[TerminologyGraph] {
  	def compare(x: TerminologyGraph, y: TerminologyGraph)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def terminologyInstanceAssertionOrdering
  : scala.Ordering[TerminologyInstanceAssertion]
  = new scala.Ordering[TerminologyInstanceAssertion] {
  	def compare(x: TerminologyInstanceAssertion, y: TerminologyInstanceAssertion)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def terminologyNestingAxiomOrdering
  : scala.Ordering[TerminologyNestingAxiom]
  = new scala.Ordering[TerminologyNestingAxiom] {
  	def compare(x: TerminologyNestingAxiom, y: TerminologyNestingAxiom)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def timeScalarRestrictionOrdering
  : scala.Ordering[TimeScalarRestriction]
  = new scala.Ordering[TimeScalarRestriction] {
  	def compare(x: TimeScalarRestriction, y: TimeScalarRestriction)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def unaryTermKindOrdering
  : scala.Ordering[UnaryTermKind]
  = new scala.Ordering[UnaryTermKind] {
  	def compare(x: UnaryTermKind, y: UnaryTermKind)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def unreifiedRelationshipOrdering
  : scala.Ordering[UnreifiedRelationship]
  = new scala.Ordering[UnreifiedRelationship] {
  	def compare(x: UnreifiedRelationship, y: UnreifiedRelationship)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def unreifiedRelationshipInstanceTupleOrdering
  : scala.Ordering[UnreifiedRelationshipInstanceTuple]
  = new scala.Ordering[UnreifiedRelationshipInstanceTuple] {
  	def compare(x: UnreifiedRelationshipInstanceTuple, y: UnreifiedRelationshipInstanceTuple)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
  implicit def valueCrossReferenceTupleOrdering
  : scala.Ordering[ValueCrossReferenceTuple]
  = new scala.Ordering[ValueCrossReferenceTuple] {
  	def compare(x: ValueCrossReferenceTuple, y: ValueCrossReferenceTuple)
  	: scala.Int
  	= x.uuid.compareTo(y.uuid) match {
  	 	case c_uuid if 0 != c_uuid => c_uuid
  	 	case 0 => 0 }
  }
  
}
