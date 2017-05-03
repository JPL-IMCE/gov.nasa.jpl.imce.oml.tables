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


package gov.nasa.jpl.imce.oml

import java.io.InputStream
import scala.collection.immutable.Seq
import scala.io
import scala.Predef.String

package object tables {
	type AbbrevIRI = String
	type IRI = String
	type LangRange = String
	type LexicalNumber = String
	type LexicalTime = String
	type LexicalValue = String
	type LocalName = String
	type NamespacePrefix = String
	type Pattern = String
	type UUID = String
  	
  def readJSonTable[T](is: InputStream, fromJSon: String => T)
  : Seq[T]
  = io.Source.fromInputStream(is).getLines.map(fromJSon).to[Seq]
  
  implicit def annotationEntryOrdering
  : scala.Ordering[AnnotationEntry]
  = new scala.Ordering[AnnotationEntry] {
  	def compare(x: AnnotationEntry, y: AnnotationEntry)
  	: scala.Int
  	= x.moduleUUID.compareTo(y.moduleUUID) match {
  	 	case c_moduleUUID if 0 != c_moduleUUID => c_moduleUUID
  	 	case 0 => x.subjectUUID.compareTo(y.subjectUUID) match {
  	 	case c_subjectUUID if 0 != c_subjectUUID => c_subjectUUID
  	 	case 0 => x.value.compareTo(y.value) match {
  	 	case c_value if 0 != c_value => c_value
  	 	case 0 => 0 } } }
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
  
  implicit def entityExistentialRestrictionAxiomOrdering
  : scala.Ordering[EntityExistentialRestrictionAxiom]
  = new scala.Ordering[EntityExistentialRestrictionAxiom] {
  	def compare(x: EntityExistentialRestrictionAxiom, y: EntityExistentialRestrictionAxiom)
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
  
  implicit def entityUniversalRestrictionAxiomOrdering
  : scala.Ordering[EntityUniversalRestrictionAxiom]
  = new scala.Ordering[EntityUniversalRestrictionAxiom] {
  	def compare(x: EntityUniversalRestrictionAxiom, y: EntityUniversalRestrictionAxiom)
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
  
  implicit def rootConceptTaxonomyAxiomOrdering
  : scala.Ordering[RootConceptTaxonomyAxiom]
  = new scala.Ordering[RootConceptTaxonomyAxiom] {
  	def compare(x: RootConceptTaxonomyAxiom, y: RootConceptTaxonomyAxiom)
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
  
  implicit def singletonInstanceScalarDataPropertyValueOrdering
  : scala.Ordering[SingletonInstanceScalarDataPropertyValue]
  = new scala.Ordering[SingletonInstanceScalarDataPropertyValue] {
  	def compare(x: SingletonInstanceScalarDataPropertyValue, y: SingletonInstanceScalarDataPropertyValue)
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
  
  implicit def synonymScalarRestrictionOrdering
  : scala.Ordering[SynonymScalarRestriction]
  = new scala.Ordering[SynonymScalarRestriction] {
  	def compare(x: SynonymScalarRestriction, y: SynonymScalarRestriction)
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
  
}
