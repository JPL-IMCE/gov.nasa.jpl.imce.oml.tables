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


package gov.nasa.jpl.imce.oml.tables

import gov.nasa.jpl.imce.oml.taggedTypes.{decodeTag,encodeTag}
import gov.nasa.jpl.imce.oml.covariantTag
import gov.nasa.jpl.imce.oml.covariantTag.@@
import io.circe.{Decoder,Encoder}
import scala.{Int,Ordering}
import scala.Predef.String

object taggedTypes {

  trait AbbrevIRITag
  type AbbrevIRI = String @@ AbbrevIRITag
  def abbrevIRI(s: String) = covariantTag[AbbrevIRITag](s)
  
  implicit val decodeAbbrevIRI: Decoder[AbbrevIRI] = decodeTag[AbbrevIRITag]
  implicit val encodeAbbrevIRI: Encoder[AbbrevIRI] = encodeTag[AbbrevIRITag]
  trait AbstractDecimalDataTypeTag
  type AbstractDecimalDataType = String @@ AbstractDecimalDataTypeTag
  def abstractDecimalDataType(s: String) = covariantTag[AbstractDecimalDataTypeTag](s)
  
  implicit val decodeAbstractDecimalDataType: Decoder[AbstractDecimalDataType] = decodeTag[AbstractDecimalDataTypeTag]
  implicit val encodeAbstractDecimalDataType: Encoder[AbstractDecimalDataType] = encodeTag[AbstractDecimalDataTypeTag]
  trait DateTimeDataTypeTag
  type DateTimeDataType = String @@ DateTimeDataTypeTag
  def dateTimeDataType(s: String) = covariantTag[DateTimeDataTypeTag](s)
  
  implicit val decodeDateTimeDataType: Decoder[DateTimeDataType] = decodeTag[DateTimeDataTypeTag]
  implicit val encodeDateTimeDataType: Encoder[DateTimeDataType] = encodeTag[DateTimeDataTypeTag]
  trait DecimalDataTypeTag
  type DecimalDataType = String @@ DecimalDataTypeTag
  def decimalDataType(s: String) = covariantTag[DecimalDataTypeTag](s)
  
  implicit val decodeDecimalDataType: Decoder[DecimalDataType] = decodeTag[DecimalDataTypeTag]
  implicit val encodeDecimalDataType: Encoder[DecimalDataType] = encodeTag[DecimalDataTypeTag]
  trait FloatDataTypeTag
  type FloatDataType = String @@ FloatDataTypeTag
  def floatDataType(s: String) = covariantTag[FloatDataTypeTag](s)
  
  implicit val decodeFloatDataType: Decoder[FloatDataType] = decodeTag[FloatDataTypeTag]
  implicit val encodeFloatDataType: Encoder[FloatDataType] = encodeTag[FloatDataTypeTag]
  trait IRITag
  type IRI = String @@ IRITag
  def iri(s: String) = covariantTag[IRITag](s)
  
  implicit val decodeIRI: Decoder[IRI] = decodeTag[IRITag]
  implicit val encodeIRI: Encoder[IRI] = encodeTag[IRITag]
  trait LanguageTagDataTypeTag
  type LanguageTagDataType = String @@ LanguageTagDataTypeTag
  def languageTagDataType(s: String) = covariantTag[LanguageTagDataTypeTag](s)
  
  implicit val decodeLanguageTagDataType: Decoder[LanguageTagDataType] = decodeTag[LanguageTagDataTypeTag]
  implicit val encodeLanguageTagDataType: Encoder[LanguageTagDataType] = encodeTag[LanguageTagDataTypeTag]
  trait LiteralPatternTag
  type LiteralPattern = String @@ LiteralPatternTag
  def literalPattern(s: String) = covariantTag[LiteralPatternTag](s)
  
  implicit val decodeLiteralPattern: Decoder[LiteralPattern] = decodeTag[LiteralPatternTag]
  implicit val encodeLiteralPattern: Encoder[LiteralPattern] = encodeTag[LiteralPatternTag]
  trait LocalNameTag
  type LocalName = String @@ LocalNameTag
  def localName(s: String) = covariantTag[LocalNameTag](s)
  
  implicit val decodeLocalName: Decoder[LocalName] = decodeTag[LocalNameTag]
  implicit val encodeLocalName: Encoder[LocalName] = encodeTag[LocalNameTag]
  trait NamespacePrefixTag
  type NamespacePrefix = String @@ NamespacePrefixTag
  def namespacePrefix(s: String) = covariantTag[NamespacePrefixTag](s)
  
  implicit val decodeNamespacePrefix: Decoder[NamespacePrefix] = decodeTag[NamespacePrefixTag]
  implicit val encodeNamespacePrefix: Encoder[NamespacePrefix] = encodeTag[NamespacePrefixTag]
  trait PositiveIntegerLiteralTag
  type PositiveIntegerLiteral = String @@ PositiveIntegerLiteralTag
  def positiveIntegerLiteral(s: String) = covariantTag[PositiveIntegerLiteralTag](s)
  
  implicit val decodePositiveIntegerLiteral: Decoder[PositiveIntegerLiteral] = decodeTag[PositiveIntegerLiteralTag]
  implicit val encodePositiveIntegerLiteral: Encoder[PositiveIntegerLiteral] = encodeTag[PositiveIntegerLiteralTag]
  trait QuotedStringDataTypeTag
  type QuotedStringDataType = String @@ QuotedStringDataTypeTag
  def quotedStringDataType(s: String) = covariantTag[QuotedStringDataTypeTag](s)
  
  implicit val decodeQuotedStringDataType: Decoder[QuotedStringDataType] = decodeTag[QuotedStringDataTypeTag]
  implicit val encodeQuotedStringDataType: Encoder[QuotedStringDataType] = encodeTag[QuotedStringDataTypeTag]
  trait RationalDataTypeTag
  type RationalDataType = String @@ RationalDataTypeTag
  def rationalDataType(s: String) = covariantTag[RationalDataTypeTag](s)
  
  implicit val decodeRationalDataType: Decoder[RationalDataType] = decodeTag[RationalDataTypeTag]
  implicit val encodeRationalDataType: Encoder[RationalDataType] = encodeTag[RationalDataTypeTag]
  trait RawStringDataTypeTag
  type RawStringDataType = String @@ RawStringDataTypeTag
  def rawStringDataType(s: String) = covariantTag[RawStringDataTypeTag](s)
  
  implicit val decodeRawStringDataType: Decoder[RawStringDataType] = decodeTag[RawStringDataTypeTag]
  implicit val encodeRawStringDataType: Encoder[RawStringDataType] = encodeTag[RawStringDataTypeTag]
  trait RealDataTypeTag
  type RealDataType = String @@ RealDataTypeTag
  def realDataType(s: String) = covariantTag[RealDataTypeTag](s)
  
  implicit val decodeRealDataType: Decoder[RealDataType] = decodeTag[RealDataTypeTag]
  implicit val encodeRealDataType: Encoder[RealDataType] = encodeTag[RealDataTypeTag]
  trait StringDataTypeTag
  type StringDataType = String @@ StringDataTypeTag
  def stringDataType(s: String) = covariantTag[StringDataTypeTag](s)
  
  implicit val decodeStringDataType: Decoder[StringDataType] = decodeTag[StringDataTypeTag]
  implicit val encodeStringDataType: Encoder[StringDataType] = encodeTag[StringDataTypeTag]
  trait URIDataTypeTag
  type URIDataType = String @@ URIDataTypeTag
  def uRIDataType(s: String) = covariantTag[URIDataTypeTag](s)
  
  implicit val decodeURIDataType: Decoder[URIDataType] = decodeTag[URIDataTypeTag]
  implicit val encodeURIDataType: Encoder[URIDataType] = encodeTag[URIDataTypeTag]
  trait UUIDDataTypeTag
  type UUIDDataType = String @@ UUIDDataTypeTag
  def uUIDDataType(s: String) = covariantTag[UUIDDataTypeTag](s)
  
  implicit val decodeUUIDDataType: Decoder[UUIDDataType] = decodeTag[UUIDDataTypeTag]
  implicit val encodeUUIDDataType: Encoder[UUIDDataType] = encodeTag[UUIDDataTypeTag]
  
  trait AnnotationPropertyTag <: IntrinsicIdentityKindTag with NonLogicalElementTag
  trait AnnotationPropertyValueTag <: NonLogicalElementTag with ValueCrossReferenceTupleTag
  trait AnonymousConceptUnionAxiomTag <: ConceptTreeDisjunctionTag with DisjointUnionOfConceptsAxiomTag
  trait AspectTag <: EntityTag with UnaryTermKindTag
  trait AspectSpecializationAxiomTag <: SpecializationAxiomTag
  trait BinaryScalarRestrictionTag <: RestrictedDataRangeTag
  trait BundleTag <: TerminologyBoxTag
  trait BundledTerminologyAxiomTag <: TerminologyBundleAxiomTag
  trait ChainRuleTag <: RuleTag
  trait CharacterizedEntityRelationshipTag <: EntityRelationshipTag
  trait ConceptTag <: ConceptualEntityTag with UnaryTermKindTag
  trait ConceptDesignationTerminologyAxiomTag <: TerminologyBoxAxiomTag
  trait ConceptInstanceTag <: ConceptualEntitySingletonInstanceTag
  trait ConceptSpecializationAxiomTag <: SpecializationAxiomTag
  trait ConceptTreeDisjunctionTag <: ElementCrossReferenceTupleTag
  trait ConceptualEntityTag <: EntityTag
  trait ConceptualEntitySingletonInstanceTag <: ResourceTag with TerminologyInstanceAssertionTag
  trait ConceptualRelationshipTag <: ConceptualEntityTag with EntityRelationshipTag
  trait CrossReferencabilityKindTag
  trait CrossReferencableKindTag <: CrossReferencabilityKindTag
  trait DataRangeTag <: DatatypeTag
  trait DataRelationshipTag <: DirectedBinaryRelationshipKindTag with TermTag
  trait DataRelationshipDomainTag <: DataRelationshipTag
  trait DataRelationshipFromEntityTag <: DataRelationshipDomainTag
  trait DataRelationshipFromStructureTag <: DataRelationshipDomainTag
  trait DataRelationshipRangeTag <: DataRelationshipTag
  trait DataRelationshipToScalarTag <: DataRelationshipRangeTag
  trait DataRelationshipToStructureTag <: DataRelationshipRangeTag
  trait DatatypeTag <: TermTag
  trait DescriptionBoxTag <: ModuleTag
  trait DescriptionBoxExtendsClosedWorldDefinitionsTag <: DescriptionBoxRelationshipTag
  trait DescriptionBoxRefinementTag <: DescriptionBoxRelationshipTag
  trait DescriptionBoxRelationshipTag <: ModuleEdgeTag
  trait DirectedBinaryRelationshipKindTag <: TermTag
  trait DisjointUnionOfConceptsAxiomTag <: ElementCrossReferenceTupleTag
  trait ElementCrossReferenceTupleTag <: CrossReferencableKindTag with ExtrinsicIdentityKindTag with LogicalElementTag
  trait EntityTag <: PredicateTag with TermTag
  trait EntityExistentialRestrictionAxiomTag <: EntityRestrictionAxiomTag
  trait EntityRelationshipTag <: DirectedBinaryRelationshipKindTag with TermTag
  trait EntityRestrictionAxiomTag <: ElementCrossReferenceTupleTag with TermAxiomTag
  trait EntityScalarDataPropertyTag <: DataRelationshipTag with DataRelationshipFromEntityTag with DataRelationshipToScalarTag
  trait EntityScalarDataPropertyExistentialRestrictionAxiomTag <: ElementCrossReferenceTupleTag with EntityScalarDataPropertyRestrictionAxiomTag
  trait EntityScalarDataPropertyParticularRestrictionAxiomTag <: EntityScalarDataPropertyRestrictionAxiomTag with ValueCrossReferenceTupleTag
  trait EntityScalarDataPropertyRestrictionAxiomTag <: ExtrinsicIdentityKindTag with TermAxiomTag
  trait EntityScalarDataPropertyUniversalRestrictionAxiomTag <: ElementCrossReferenceTupleTag with EntityScalarDataPropertyRestrictionAxiomTag
  trait EntityStructuredDataPropertyTag <: DataRelationshipTag with DataRelationshipFromEntityTag with DataRelationshipToStructureTag
  trait EntityStructuredDataPropertyParticularRestrictionAxiomTag <: EntityStructuredDataPropertyRestrictionAxiomTag with RestrictionStructuredDataPropertyContextTag
  trait EntityStructuredDataPropertyRestrictionAxiomTag <: ElementCrossReferenceTupleTag with TermAxiomTag
  trait EntityUniversalRestrictionAxiomTag <: EntityRestrictionAxiomTag
  trait ExtentTag
  trait ExtrinsicIdentityKindTag <: IdentityKindTag
  trait ForwardPropertyTag <: RestrictableRelationshipTag
  trait IRIScalarRestrictionTag <: RestrictedDataRangeTag
  trait IdentityKindTag <: CrossReferencabilityKindTag
  trait IntrinsicIdentityKindTag <: CrossReferencableKindTag with IdentityKindTag
  trait InversePropertyTag <: RestrictableRelationshipTag
  trait LiteralBooleanTag <: LiteralValueTag
  trait LiteralDateTimeTag <: LiteralValueTag
  trait LiteralDecimalTag <: LiteralNumberTag
  trait LiteralFloatTag <: LiteralNumberTag
  trait LiteralNumberTag <: LiteralValueTag
  trait LiteralQuotedStringTag <: LiteralStringTag
  trait LiteralRationalTag <: LiteralNumberTag
  trait LiteralRawStringTag <: LiteralStringTag
  trait LiteralRealTag <: LiteralNumberTag
  trait LiteralStringTag <: LiteralValueTag
  trait LiteralURITag <: LiteralValueTag
  trait LiteralUUIDTag <: LiteralValueTag
  trait LiteralValueTag
  trait LogicalElementTag <: IdentityKindTag
  trait ModuleTag <: ResourceTag
  trait ModuleEdgeTag <: ElementCrossReferenceTupleTag
  trait ModuleElementTag <: LogicalElementTag
  trait NonCrossReferencableKindTag <: CrossReferencabilityKindTag
  trait NonLogicalElementTag <: IdentityKindTag
  trait NumericScalarRestrictionTag <: RestrictedDataRangeTag
  trait PlainLiteralScalarRestrictionTag <: RestrictedDataRangeTag
  trait PredicateTag <: ResourceTag
  trait ReifiedRelationshipTag <: CharacterizedEntityRelationshipTag with ConceptualRelationshipTag
  trait ReifiedRelationshipInstanceTag <: ConceptualEntitySingletonInstanceTag
  trait ReifiedRelationshipInstanceDomainTag <: ElementCrossReferenceTupleTag with TerminologyInstanceAssertionTag
  trait ReifiedRelationshipInstanceRangeTag <: ElementCrossReferenceTupleTag with TerminologyInstanceAssertionTag
  trait ResourceTag <: IntrinsicIdentityKindTag with LogicalElementTag
  trait RestrictableRelationshipTag <: PredicateTag
  trait RestrictedDataRangeTag <: DataRangeTag
  trait RestrictionScalarDataPropertyValueTag <: LogicalElementTag with ValueCrossReferenceTupleTag
  trait RestrictionStructuredDataPropertyContextTag <: ElementCrossReferenceTupleTag with ModuleElementTag
  trait RestrictionStructuredDataPropertyTupleTag <: RestrictionStructuredDataPropertyContextTag
  trait RootConceptTaxonomyAxiomTag <: ConceptTreeDisjunctionTag with TerminologyBundleStatementTag
  trait RuleTag <: TermTag
  trait RuleBodySegmentTag <: ElementCrossReferenceTupleTag
  trait ScalarTag <: DataRangeTag with UnaryTermKindTag
  trait ScalarDataPropertyTag <: DataRelationshipTag with DataRelationshipFromStructureTag with DataRelationshipToScalarTag
  trait ScalarDataPropertyValueTag <: LogicalElementTag with ValueCrossReferenceTupleTag
  trait ScalarOneOfLiteralAxiomTag <: TermAxiomTag with ValueCrossReferenceTupleTag
  trait ScalarOneOfRestrictionTag <: RestrictedDataRangeTag
  trait SegmentPredicateTag <: ElementCrossReferenceTupleTag
  trait SingletonInstanceScalarDataPropertyValueTag <: ModuleElementTag with ValueCrossReferenceTupleTag
  trait SingletonInstanceStructuredDataPropertyContextTag <: ElementCrossReferenceTupleTag
  trait SingletonInstanceStructuredDataPropertyValueTag <: ModuleElementTag with SingletonInstanceStructuredDataPropertyContextTag
  trait SpecializationAxiomTag <: ElementCrossReferenceTupleTag with TermAxiomTag
  trait SpecializedReifiedRelationshipTag <: ConceptualRelationshipTag with SpecializationAxiomTag
  trait SpecificDisjointConceptAxiomTag <: DisjointUnionOfConceptsAxiomTag
  trait StringScalarRestrictionTag <: RestrictedDataRangeTag
  trait StructureTag <: DatatypeTag with UnaryTermKindTag
  trait StructuredDataPropertyTag <: DataRelationshipTag with DataRelationshipFromStructureTag with DataRelationshipToStructureTag
  trait StructuredDataPropertyTupleTag <: SingletonInstanceStructuredDataPropertyContextTag
  trait SubDataPropertyOfAxiomTag <: ElementCrossReferenceTupleTag with TermAxiomTag
  trait SubObjectPropertyOfAxiomTag <: ElementCrossReferenceTupleTag with TermAxiomTag
  trait SynonymScalarRestrictionTag <: RestrictedDataRangeTag
  trait TermTag <: ResourceTag with TerminologyBoxStatementTag
  trait TermAxiomTag <: TerminologyBoxStatementTag
  trait TerminologyAxiomTag <: ModuleEdgeTag
  trait TerminologyBoxTag <: ModuleTag
  trait TerminologyBoxAxiomTag <: TerminologyAxiomTag
  trait TerminologyBoxStatementTag <: ModuleElementTag
  trait TerminologyBundleAxiomTag <: TerminologyAxiomTag
  trait TerminologyBundleStatementTag <: ModuleElementTag
  trait TerminologyExtensionAxiomTag <: TerminologyBoxAxiomTag
  trait TerminologyGraphTag <: TerminologyBoxTag
  trait TerminologyInstanceAssertionTag <: ModuleElementTag
  trait TerminologyNestingAxiomTag <: TerminologyBoxAxiomTag
  trait TimeScalarRestrictionTag <: RestrictedDataRangeTag
  trait UnaryTermKindTag <: TermTag
  trait UnreifiedRelationshipTag <: CharacterizedEntityRelationshipTag with RestrictableRelationshipTag
  trait UnreifiedRelationshipInstanceTupleTag <: ElementCrossReferenceTupleTag with TerminologyInstanceAssertionTag
  trait ValueCrossReferenceTupleTag <: ExtrinsicIdentityKindTag with NonCrossReferencableKindTag
  
  type AnnotationPropertyUUID 
  = String @@ AnnotationPropertyTag
  
  def annotationPropertyUUID(uuid: String)
  : AnnotationPropertyUUID
  = covariantTag[AnnotationPropertyTag][String](uuid)
  
  implicit val decodeAnnotationPropertyUUID
  : Decoder[AnnotationPropertyUUID]
  = decodeTag[AnnotationPropertyTag]
  
  implicit val encodeAnnotationPropertyUUID
  : Encoder[AnnotationPropertyUUID]
  = encodeTag[AnnotationPropertyTag]
  
  implicit val orderingAnnotationPropertyUUID
  : Ordering[AnnotationPropertyUUID] 
  = new Ordering[AnnotationPropertyUUID] {
  	override def compare
  	(x: AnnotationPropertyUUID, 
  	 y: AnnotationPropertyUUID)
  	: Int = x.compareTo(y)
  }
  
  type AnnotationPropertyValueUUID 
  = String @@ AnnotationPropertyValueTag
  
  def annotationPropertyValueUUID(uuid: String)
  : AnnotationPropertyValueUUID
  = covariantTag[AnnotationPropertyValueTag][String](uuid)
  
  implicit val decodeAnnotationPropertyValueUUID
  : Decoder[AnnotationPropertyValueUUID]
  = decodeTag[AnnotationPropertyValueTag]
  
  implicit val encodeAnnotationPropertyValueUUID
  : Encoder[AnnotationPropertyValueUUID]
  = encodeTag[AnnotationPropertyValueTag]
  
  implicit val orderingAnnotationPropertyValueUUID
  : Ordering[AnnotationPropertyValueUUID] 
  = new Ordering[AnnotationPropertyValueUUID] {
  	override def compare
  	(x: AnnotationPropertyValueUUID, 
  	 y: AnnotationPropertyValueUUID)
  	: Int = x.compareTo(y)
  }
  
  type AnonymousConceptUnionAxiomUUID 
  = String @@ AnonymousConceptUnionAxiomTag
  
  def anonymousConceptUnionAxiomUUID(uuid: String)
  : AnonymousConceptUnionAxiomUUID
  = covariantTag[AnonymousConceptUnionAxiomTag][String](uuid)
  
  implicit val decodeAnonymousConceptUnionAxiomUUID
  : Decoder[AnonymousConceptUnionAxiomUUID]
  = decodeTag[AnonymousConceptUnionAxiomTag]
  
  implicit val encodeAnonymousConceptUnionAxiomUUID
  : Encoder[AnonymousConceptUnionAxiomUUID]
  = encodeTag[AnonymousConceptUnionAxiomTag]
  
  implicit val orderingAnonymousConceptUnionAxiomUUID
  : Ordering[AnonymousConceptUnionAxiomUUID] 
  = new Ordering[AnonymousConceptUnionAxiomUUID] {
  	override def compare
  	(x: AnonymousConceptUnionAxiomUUID, 
  	 y: AnonymousConceptUnionAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type AspectUUID 
  = String @@ AspectTag
  
  def aspectUUID(uuid: String)
  : AspectUUID
  = covariantTag[AspectTag][String](uuid)
  
  implicit val decodeAspectUUID
  : Decoder[AspectUUID]
  = decodeTag[AspectTag]
  
  implicit val encodeAspectUUID
  : Encoder[AspectUUID]
  = encodeTag[AspectTag]
  
  implicit val orderingAspectUUID
  : Ordering[AspectUUID] 
  = new Ordering[AspectUUID] {
  	override def compare
  	(x: AspectUUID, 
  	 y: AspectUUID)
  	: Int = x.compareTo(y)
  }
  
  type AspectSpecializationAxiomUUID 
  = String @@ AspectSpecializationAxiomTag
  
  def aspectSpecializationAxiomUUID(uuid: String)
  : AspectSpecializationAxiomUUID
  = covariantTag[AspectSpecializationAxiomTag][String](uuid)
  
  implicit val decodeAspectSpecializationAxiomUUID
  : Decoder[AspectSpecializationAxiomUUID]
  = decodeTag[AspectSpecializationAxiomTag]
  
  implicit val encodeAspectSpecializationAxiomUUID
  : Encoder[AspectSpecializationAxiomUUID]
  = encodeTag[AspectSpecializationAxiomTag]
  
  implicit val orderingAspectSpecializationAxiomUUID
  : Ordering[AspectSpecializationAxiomUUID] 
  = new Ordering[AspectSpecializationAxiomUUID] {
  	override def compare
  	(x: AspectSpecializationAxiomUUID, 
  	 y: AspectSpecializationAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type BinaryScalarRestrictionUUID 
  = String @@ BinaryScalarRestrictionTag
  
  def binaryScalarRestrictionUUID(uuid: String)
  : BinaryScalarRestrictionUUID
  = covariantTag[BinaryScalarRestrictionTag][String](uuid)
  
  implicit val decodeBinaryScalarRestrictionUUID
  : Decoder[BinaryScalarRestrictionUUID]
  = decodeTag[BinaryScalarRestrictionTag]
  
  implicit val encodeBinaryScalarRestrictionUUID
  : Encoder[BinaryScalarRestrictionUUID]
  = encodeTag[BinaryScalarRestrictionTag]
  
  implicit val orderingBinaryScalarRestrictionUUID
  : Ordering[BinaryScalarRestrictionUUID] 
  = new Ordering[BinaryScalarRestrictionUUID] {
  	override def compare
  	(x: BinaryScalarRestrictionUUID, 
  	 y: BinaryScalarRestrictionUUID)
  	: Int = x.compareTo(y)
  }
  
  type BundleUUID 
  = String @@ BundleTag
  
  def bundleUUID(uuid: String)
  : BundleUUID
  = covariantTag[BundleTag][String](uuid)
  
  implicit val decodeBundleUUID
  : Decoder[BundleUUID]
  = decodeTag[BundleTag]
  
  implicit val encodeBundleUUID
  : Encoder[BundleUUID]
  = encodeTag[BundleTag]
  
  implicit val orderingBundleUUID
  : Ordering[BundleUUID] 
  = new Ordering[BundleUUID] {
  	override def compare
  	(x: BundleUUID, 
  	 y: BundleUUID)
  	: Int = x.compareTo(y)
  }
  
  type BundledTerminologyAxiomUUID 
  = String @@ BundledTerminologyAxiomTag
  
  def bundledTerminologyAxiomUUID(uuid: String)
  : BundledTerminologyAxiomUUID
  = covariantTag[BundledTerminologyAxiomTag][String](uuid)
  
  implicit val decodeBundledTerminologyAxiomUUID
  : Decoder[BundledTerminologyAxiomUUID]
  = decodeTag[BundledTerminologyAxiomTag]
  
  implicit val encodeBundledTerminologyAxiomUUID
  : Encoder[BundledTerminologyAxiomUUID]
  = encodeTag[BundledTerminologyAxiomTag]
  
  implicit val orderingBundledTerminologyAxiomUUID
  : Ordering[BundledTerminologyAxiomUUID] 
  = new Ordering[BundledTerminologyAxiomUUID] {
  	override def compare
  	(x: BundledTerminologyAxiomUUID, 
  	 y: BundledTerminologyAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type ChainRuleUUID 
  = String @@ ChainRuleTag
  
  def chainRuleUUID(uuid: String)
  : ChainRuleUUID
  = covariantTag[ChainRuleTag][String](uuid)
  
  implicit val decodeChainRuleUUID
  : Decoder[ChainRuleUUID]
  = decodeTag[ChainRuleTag]
  
  implicit val encodeChainRuleUUID
  : Encoder[ChainRuleUUID]
  = encodeTag[ChainRuleTag]
  
  implicit val orderingChainRuleUUID
  : Ordering[ChainRuleUUID] 
  = new Ordering[ChainRuleUUID] {
  	override def compare
  	(x: ChainRuleUUID, 
  	 y: ChainRuleUUID)
  	: Int = x.compareTo(y)
  }
  
  type CharacterizedEntityRelationshipUUID 
  = String @@ CharacterizedEntityRelationshipTag
  
  def characterizedEntityRelationshipUUID(uuid: String)
  : CharacterizedEntityRelationshipUUID
  = covariantTag[CharacterizedEntityRelationshipTag][String](uuid)
  
  implicit val decodeCharacterizedEntityRelationshipUUID
  : Decoder[CharacterizedEntityRelationshipUUID]
  = decodeTag[CharacterizedEntityRelationshipTag]
  
  implicit val encodeCharacterizedEntityRelationshipUUID
  : Encoder[CharacterizedEntityRelationshipUUID]
  = encodeTag[CharacterizedEntityRelationshipTag]
  
  implicit val orderingCharacterizedEntityRelationshipUUID
  : Ordering[CharacterizedEntityRelationshipUUID] 
  = new Ordering[CharacterizedEntityRelationshipUUID] {
  	override def compare
  	(x: CharacterizedEntityRelationshipUUID, 
  	 y: CharacterizedEntityRelationshipUUID)
  	: Int = x.compareTo(y)
  }
  
  type ConceptUUID 
  = String @@ ConceptTag
  
  def conceptUUID(uuid: String)
  : ConceptUUID
  = covariantTag[ConceptTag][String](uuid)
  
  implicit val decodeConceptUUID
  : Decoder[ConceptUUID]
  = decodeTag[ConceptTag]
  
  implicit val encodeConceptUUID
  : Encoder[ConceptUUID]
  = encodeTag[ConceptTag]
  
  implicit val orderingConceptUUID
  : Ordering[ConceptUUID] 
  = new Ordering[ConceptUUID] {
  	override def compare
  	(x: ConceptUUID, 
  	 y: ConceptUUID)
  	: Int = x.compareTo(y)
  }
  
  type ConceptDesignationTerminologyAxiomUUID 
  = String @@ ConceptDesignationTerminologyAxiomTag
  
  def conceptDesignationTerminologyAxiomUUID(uuid: String)
  : ConceptDesignationTerminologyAxiomUUID
  = covariantTag[ConceptDesignationTerminologyAxiomTag][String](uuid)
  
  implicit val decodeConceptDesignationTerminologyAxiomUUID
  : Decoder[ConceptDesignationTerminologyAxiomUUID]
  = decodeTag[ConceptDesignationTerminologyAxiomTag]
  
  implicit val encodeConceptDesignationTerminologyAxiomUUID
  : Encoder[ConceptDesignationTerminologyAxiomUUID]
  = encodeTag[ConceptDesignationTerminologyAxiomTag]
  
  implicit val orderingConceptDesignationTerminologyAxiomUUID
  : Ordering[ConceptDesignationTerminologyAxiomUUID] 
  = new Ordering[ConceptDesignationTerminologyAxiomUUID] {
  	override def compare
  	(x: ConceptDesignationTerminologyAxiomUUID, 
  	 y: ConceptDesignationTerminologyAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type ConceptInstanceUUID 
  = String @@ ConceptInstanceTag
  
  def conceptInstanceUUID(uuid: String)
  : ConceptInstanceUUID
  = covariantTag[ConceptInstanceTag][String](uuid)
  
  implicit val decodeConceptInstanceUUID
  : Decoder[ConceptInstanceUUID]
  = decodeTag[ConceptInstanceTag]
  
  implicit val encodeConceptInstanceUUID
  : Encoder[ConceptInstanceUUID]
  = encodeTag[ConceptInstanceTag]
  
  implicit val orderingConceptInstanceUUID
  : Ordering[ConceptInstanceUUID] 
  = new Ordering[ConceptInstanceUUID] {
  	override def compare
  	(x: ConceptInstanceUUID, 
  	 y: ConceptInstanceUUID)
  	: Int = x.compareTo(y)
  }
  
  type ConceptSpecializationAxiomUUID 
  = String @@ ConceptSpecializationAxiomTag
  
  def conceptSpecializationAxiomUUID(uuid: String)
  : ConceptSpecializationAxiomUUID
  = covariantTag[ConceptSpecializationAxiomTag][String](uuid)
  
  implicit val decodeConceptSpecializationAxiomUUID
  : Decoder[ConceptSpecializationAxiomUUID]
  = decodeTag[ConceptSpecializationAxiomTag]
  
  implicit val encodeConceptSpecializationAxiomUUID
  : Encoder[ConceptSpecializationAxiomUUID]
  = encodeTag[ConceptSpecializationAxiomTag]
  
  implicit val orderingConceptSpecializationAxiomUUID
  : Ordering[ConceptSpecializationAxiomUUID] 
  = new Ordering[ConceptSpecializationAxiomUUID] {
  	override def compare
  	(x: ConceptSpecializationAxiomUUID, 
  	 y: ConceptSpecializationAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type ConceptTreeDisjunctionUUID 
  = String @@ ConceptTreeDisjunctionTag
  
  def conceptTreeDisjunctionUUID(uuid: String)
  : ConceptTreeDisjunctionUUID
  = covariantTag[ConceptTreeDisjunctionTag][String](uuid)
  
  implicit val decodeConceptTreeDisjunctionUUID
  : Decoder[ConceptTreeDisjunctionUUID]
  = decodeTag[ConceptTreeDisjunctionTag]
  
  implicit val encodeConceptTreeDisjunctionUUID
  : Encoder[ConceptTreeDisjunctionUUID]
  = encodeTag[ConceptTreeDisjunctionTag]
  
  implicit val orderingConceptTreeDisjunctionUUID
  : Ordering[ConceptTreeDisjunctionUUID] 
  = new Ordering[ConceptTreeDisjunctionUUID] {
  	override def compare
  	(x: ConceptTreeDisjunctionUUID, 
  	 y: ConceptTreeDisjunctionUUID)
  	: Int = x.compareTo(y)
  }
  
  type ConceptualEntityUUID 
  = String @@ ConceptualEntityTag
  
  def conceptualEntityUUID(uuid: String)
  : ConceptualEntityUUID
  = covariantTag[ConceptualEntityTag][String](uuid)
  
  implicit val decodeConceptualEntityUUID
  : Decoder[ConceptualEntityUUID]
  = decodeTag[ConceptualEntityTag]
  
  implicit val encodeConceptualEntityUUID
  : Encoder[ConceptualEntityUUID]
  = encodeTag[ConceptualEntityTag]
  
  implicit val orderingConceptualEntityUUID
  : Ordering[ConceptualEntityUUID] 
  = new Ordering[ConceptualEntityUUID] {
  	override def compare
  	(x: ConceptualEntityUUID, 
  	 y: ConceptualEntityUUID)
  	: Int = x.compareTo(y)
  }
  
  type ConceptualEntitySingletonInstanceUUID 
  = String @@ ConceptualEntitySingletonInstanceTag
  
  def conceptualEntitySingletonInstanceUUID(uuid: String)
  : ConceptualEntitySingletonInstanceUUID
  = covariantTag[ConceptualEntitySingletonInstanceTag][String](uuid)
  
  implicit val decodeConceptualEntitySingletonInstanceUUID
  : Decoder[ConceptualEntitySingletonInstanceUUID]
  = decodeTag[ConceptualEntitySingletonInstanceTag]
  
  implicit val encodeConceptualEntitySingletonInstanceUUID
  : Encoder[ConceptualEntitySingletonInstanceUUID]
  = encodeTag[ConceptualEntitySingletonInstanceTag]
  
  implicit val orderingConceptualEntitySingletonInstanceUUID
  : Ordering[ConceptualEntitySingletonInstanceUUID] 
  = new Ordering[ConceptualEntitySingletonInstanceUUID] {
  	override def compare
  	(x: ConceptualEntitySingletonInstanceUUID, 
  	 y: ConceptualEntitySingletonInstanceUUID)
  	: Int = x.compareTo(y)
  }
  
  type ConceptualRelationshipUUID 
  = String @@ ConceptualRelationshipTag
  
  def conceptualRelationshipUUID(uuid: String)
  : ConceptualRelationshipUUID
  = covariantTag[ConceptualRelationshipTag][String](uuid)
  
  implicit val decodeConceptualRelationshipUUID
  : Decoder[ConceptualRelationshipUUID]
  = decodeTag[ConceptualRelationshipTag]
  
  implicit val encodeConceptualRelationshipUUID
  : Encoder[ConceptualRelationshipUUID]
  = encodeTag[ConceptualRelationshipTag]
  
  implicit val orderingConceptualRelationshipUUID
  : Ordering[ConceptualRelationshipUUID] 
  = new Ordering[ConceptualRelationshipUUID] {
  	override def compare
  	(x: ConceptualRelationshipUUID, 
  	 y: ConceptualRelationshipUUID)
  	: Int = x.compareTo(y)
  }
  
  type CrossReferencabilityKindUUID 
  = String @@ CrossReferencabilityKindTag
  
  def crossReferencabilityKindUUID(uuid: String)
  : CrossReferencabilityKindUUID
  = covariantTag[CrossReferencabilityKindTag][String](uuid)
  
  implicit val decodeCrossReferencabilityKindUUID
  : Decoder[CrossReferencabilityKindUUID]
  = decodeTag[CrossReferencabilityKindTag]
  
  implicit val encodeCrossReferencabilityKindUUID
  : Encoder[CrossReferencabilityKindUUID]
  = encodeTag[CrossReferencabilityKindTag]
  
  implicit val orderingCrossReferencabilityKindUUID
  : Ordering[CrossReferencabilityKindUUID] 
  = new Ordering[CrossReferencabilityKindUUID] {
  	override def compare
  	(x: CrossReferencabilityKindUUID, 
  	 y: CrossReferencabilityKindUUID)
  	: Int = x.compareTo(y)
  }
  
  type CrossReferencableKindUUID 
  = String @@ CrossReferencableKindTag
  
  def crossReferencableKindUUID(uuid: String)
  : CrossReferencableKindUUID
  = covariantTag[CrossReferencableKindTag][String](uuid)
  
  implicit val decodeCrossReferencableKindUUID
  : Decoder[CrossReferencableKindUUID]
  = decodeTag[CrossReferencableKindTag]
  
  implicit val encodeCrossReferencableKindUUID
  : Encoder[CrossReferencableKindUUID]
  = encodeTag[CrossReferencableKindTag]
  
  implicit val orderingCrossReferencableKindUUID
  : Ordering[CrossReferencableKindUUID] 
  = new Ordering[CrossReferencableKindUUID] {
  	override def compare
  	(x: CrossReferencableKindUUID, 
  	 y: CrossReferencableKindUUID)
  	: Int = x.compareTo(y)
  }
  
  type DataRangeUUID 
  = String @@ DataRangeTag
  
  def dataRangeUUID(uuid: String)
  : DataRangeUUID
  = covariantTag[DataRangeTag][String](uuid)
  
  implicit val decodeDataRangeUUID
  : Decoder[DataRangeUUID]
  = decodeTag[DataRangeTag]
  
  implicit val encodeDataRangeUUID
  : Encoder[DataRangeUUID]
  = encodeTag[DataRangeTag]
  
  implicit val orderingDataRangeUUID
  : Ordering[DataRangeUUID] 
  = new Ordering[DataRangeUUID] {
  	override def compare
  	(x: DataRangeUUID, 
  	 y: DataRangeUUID)
  	: Int = x.compareTo(y)
  }
  
  type DataRelationshipUUID 
  = String @@ DataRelationshipTag
  
  def dataRelationshipUUID(uuid: String)
  : DataRelationshipUUID
  = covariantTag[DataRelationshipTag][String](uuid)
  
  implicit val decodeDataRelationshipUUID
  : Decoder[DataRelationshipUUID]
  = decodeTag[DataRelationshipTag]
  
  implicit val encodeDataRelationshipUUID
  : Encoder[DataRelationshipUUID]
  = encodeTag[DataRelationshipTag]
  
  implicit val orderingDataRelationshipUUID
  : Ordering[DataRelationshipUUID] 
  = new Ordering[DataRelationshipUUID] {
  	override def compare
  	(x: DataRelationshipUUID, 
  	 y: DataRelationshipUUID)
  	: Int = x.compareTo(y)
  }
  
  type DataRelationshipDomainUUID 
  = String @@ DataRelationshipDomainTag
  
  def dataRelationshipDomainUUID(uuid: String)
  : DataRelationshipDomainUUID
  = covariantTag[DataRelationshipDomainTag][String](uuid)
  
  implicit val decodeDataRelationshipDomainUUID
  : Decoder[DataRelationshipDomainUUID]
  = decodeTag[DataRelationshipDomainTag]
  
  implicit val encodeDataRelationshipDomainUUID
  : Encoder[DataRelationshipDomainUUID]
  = encodeTag[DataRelationshipDomainTag]
  
  implicit val orderingDataRelationshipDomainUUID
  : Ordering[DataRelationshipDomainUUID] 
  = new Ordering[DataRelationshipDomainUUID] {
  	override def compare
  	(x: DataRelationshipDomainUUID, 
  	 y: DataRelationshipDomainUUID)
  	: Int = x.compareTo(y)
  }
  
  type DataRelationshipFromEntityUUID 
  = String @@ DataRelationshipFromEntityTag
  
  def dataRelationshipFromEntityUUID(uuid: String)
  : DataRelationshipFromEntityUUID
  = covariantTag[DataRelationshipFromEntityTag][String](uuid)
  
  implicit val decodeDataRelationshipFromEntityUUID
  : Decoder[DataRelationshipFromEntityUUID]
  = decodeTag[DataRelationshipFromEntityTag]
  
  implicit val encodeDataRelationshipFromEntityUUID
  : Encoder[DataRelationshipFromEntityUUID]
  = encodeTag[DataRelationshipFromEntityTag]
  
  implicit val orderingDataRelationshipFromEntityUUID
  : Ordering[DataRelationshipFromEntityUUID] 
  = new Ordering[DataRelationshipFromEntityUUID] {
  	override def compare
  	(x: DataRelationshipFromEntityUUID, 
  	 y: DataRelationshipFromEntityUUID)
  	: Int = x.compareTo(y)
  }
  
  type DataRelationshipFromStructureUUID 
  = String @@ DataRelationshipFromStructureTag
  
  def dataRelationshipFromStructureUUID(uuid: String)
  : DataRelationshipFromStructureUUID
  = covariantTag[DataRelationshipFromStructureTag][String](uuid)
  
  implicit val decodeDataRelationshipFromStructureUUID
  : Decoder[DataRelationshipFromStructureUUID]
  = decodeTag[DataRelationshipFromStructureTag]
  
  implicit val encodeDataRelationshipFromStructureUUID
  : Encoder[DataRelationshipFromStructureUUID]
  = encodeTag[DataRelationshipFromStructureTag]
  
  implicit val orderingDataRelationshipFromStructureUUID
  : Ordering[DataRelationshipFromStructureUUID] 
  = new Ordering[DataRelationshipFromStructureUUID] {
  	override def compare
  	(x: DataRelationshipFromStructureUUID, 
  	 y: DataRelationshipFromStructureUUID)
  	: Int = x.compareTo(y)
  }
  
  type DataRelationshipRangeUUID 
  = String @@ DataRelationshipRangeTag
  
  def dataRelationshipRangeUUID(uuid: String)
  : DataRelationshipRangeUUID
  = covariantTag[DataRelationshipRangeTag][String](uuid)
  
  implicit val decodeDataRelationshipRangeUUID
  : Decoder[DataRelationshipRangeUUID]
  = decodeTag[DataRelationshipRangeTag]
  
  implicit val encodeDataRelationshipRangeUUID
  : Encoder[DataRelationshipRangeUUID]
  = encodeTag[DataRelationshipRangeTag]
  
  implicit val orderingDataRelationshipRangeUUID
  : Ordering[DataRelationshipRangeUUID] 
  = new Ordering[DataRelationshipRangeUUID] {
  	override def compare
  	(x: DataRelationshipRangeUUID, 
  	 y: DataRelationshipRangeUUID)
  	: Int = x.compareTo(y)
  }
  
  type DataRelationshipToScalarUUID 
  = String @@ DataRelationshipToScalarTag
  
  def dataRelationshipToScalarUUID(uuid: String)
  : DataRelationshipToScalarUUID
  = covariantTag[DataRelationshipToScalarTag][String](uuid)
  
  implicit val decodeDataRelationshipToScalarUUID
  : Decoder[DataRelationshipToScalarUUID]
  = decodeTag[DataRelationshipToScalarTag]
  
  implicit val encodeDataRelationshipToScalarUUID
  : Encoder[DataRelationshipToScalarUUID]
  = encodeTag[DataRelationshipToScalarTag]
  
  implicit val orderingDataRelationshipToScalarUUID
  : Ordering[DataRelationshipToScalarUUID] 
  = new Ordering[DataRelationshipToScalarUUID] {
  	override def compare
  	(x: DataRelationshipToScalarUUID, 
  	 y: DataRelationshipToScalarUUID)
  	: Int = x.compareTo(y)
  }
  
  type DataRelationshipToStructureUUID 
  = String @@ DataRelationshipToStructureTag
  
  def dataRelationshipToStructureUUID(uuid: String)
  : DataRelationshipToStructureUUID
  = covariantTag[DataRelationshipToStructureTag][String](uuid)
  
  implicit val decodeDataRelationshipToStructureUUID
  : Decoder[DataRelationshipToStructureUUID]
  = decodeTag[DataRelationshipToStructureTag]
  
  implicit val encodeDataRelationshipToStructureUUID
  : Encoder[DataRelationshipToStructureUUID]
  = encodeTag[DataRelationshipToStructureTag]
  
  implicit val orderingDataRelationshipToStructureUUID
  : Ordering[DataRelationshipToStructureUUID] 
  = new Ordering[DataRelationshipToStructureUUID] {
  	override def compare
  	(x: DataRelationshipToStructureUUID, 
  	 y: DataRelationshipToStructureUUID)
  	: Int = x.compareTo(y)
  }
  
  type DatatypeUUID 
  = String @@ DatatypeTag
  
  def datatypeUUID(uuid: String)
  : DatatypeUUID
  = covariantTag[DatatypeTag][String](uuid)
  
  implicit val decodeDatatypeUUID
  : Decoder[DatatypeUUID]
  = decodeTag[DatatypeTag]
  
  implicit val encodeDatatypeUUID
  : Encoder[DatatypeUUID]
  = encodeTag[DatatypeTag]
  
  implicit val orderingDatatypeUUID
  : Ordering[DatatypeUUID] 
  = new Ordering[DatatypeUUID] {
  	override def compare
  	(x: DatatypeUUID, 
  	 y: DatatypeUUID)
  	: Int = x.compareTo(y)
  }
  
  type DescriptionBoxUUID 
  = String @@ DescriptionBoxTag
  
  def descriptionBoxUUID(uuid: String)
  : DescriptionBoxUUID
  = covariantTag[DescriptionBoxTag][String](uuid)
  
  implicit val decodeDescriptionBoxUUID
  : Decoder[DescriptionBoxUUID]
  = decodeTag[DescriptionBoxTag]
  
  implicit val encodeDescriptionBoxUUID
  : Encoder[DescriptionBoxUUID]
  = encodeTag[DescriptionBoxTag]
  
  implicit val orderingDescriptionBoxUUID
  : Ordering[DescriptionBoxUUID] 
  = new Ordering[DescriptionBoxUUID] {
  	override def compare
  	(x: DescriptionBoxUUID, 
  	 y: DescriptionBoxUUID)
  	: Int = x.compareTo(y)
  }
  
  type DescriptionBoxExtendsClosedWorldDefinitionsUUID 
  = String @@ DescriptionBoxExtendsClosedWorldDefinitionsTag
  
  def descriptionBoxExtendsClosedWorldDefinitionsUUID(uuid: String)
  : DescriptionBoxExtendsClosedWorldDefinitionsUUID
  = covariantTag[DescriptionBoxExtendsClosedWorldDefinitionsTag][String](uuid)
  
  implicit val decodeDescriptionBoxExtendsClosedWorldDefinitionsUUID
  : Decoder[DescriptionBoxExtendsClosedWorldDefinitionsUUID]
  = decodeTag[DescriptionBoxExtendsClosedWorldDefinitionsTag]
  
  implicit val encodeDescriptionBoxExtendsClosedWorldDefinitionsUUID
  : Encoder[DescriptionBoxExtendsClosedWorldDefinitionsUUID]
  = encodeTag[DescriptionBoxExtendsClosedWorldDefinitionsTag]
  
  implicit val orderingDescriptionBoxExtendsClosedWorldDefinitionsUUID
  : Ordering[DescriptionBoxExtendsClosedWorldDefinitionsUUID] 
  = new Ordering[DescriptionBoxExtendsClosedWorldDefinitionsUUID] {
  	override def compare
  	(x: DescriptionBoxExtendsClosedWorldDefinitionsUUID, 
  	 y: DescriptionBoxExtendsClosedWorldDefinitionsUUID)
  	: Int = x.compareTo(y)
  }
  
  type DescriptionBoxRefinementUUID 
  = String @@ DescriptionBoxRefinementTag
  
  def descriptionBoxRefinementUUID(uuid: String)
  : DescriptionBoxRefinementUUID
  = covariantTag[DescriptionBoxRefinementTag][String](uuid)
  
  implicit val decodeDescriptionBoxRefinementUUID
  : Decoder[DescriptionBoxRefinementUUID]
  = decodeTag[DescriptionBoxRefinementTag]
  
  implicit val encodeDescriptionBoxRefinementUUID
  : Encoder[DescriptionBoxRefinementUUID]
  = encodeTag[DescriptionBoxRefinementTag]
  
  implicit val orderingDescriptionBoxRefinementUUID
  : Ordering[DescriptionBoxRefinementUUID] 
  = new Ordering[DescriptionBoxRefinementUUID] {
  	override def compare
  	(x: DescriptionBoxRefinementUUID, 
  	 y: DescriptionBoxRefinementUUID)
  	: Int = x.compareTo(y)
  }
  
  type DescriptionBoxRelationshipUUID 
  = String @@ DescriptionBoxRelationshipTag
  
  def descriptionBoxRelationshipUUID(uuid: String)
  : DescriptionBoxRelationshipUUID
  = covariantTag[DescriptionBoxRelationshipTag][String](uuid)
  
  implicit val decodeDescriptionBoxRelationshipUUID
  : Decoder[DescriptionBoxRelationshipUUID]
  = decodeTag[DescriptionBoxRelationshipTag]
  
  implicit val encodeDescriptionBoxRelationshipUUID
  : Encoder[DescriptionBoxRelationshipUUID]
  = encodeTag[DescriptionBoxRelationshipTag]
  
  implicit val orderingDescriptionBoxRelationshipUUID
  : Ordering[DescriptionBoxRelationshipUUID] 
  = new Ordering[DescriptionBoxRelationshipUUID] {
  	override def compare
  	(x: DescriptionBoxRelationshipUUID, 
  	 y: DescriptionBoxRelationshipUUID)
  	: Int = x.compareTo(y)
  }
  
  type DirectedBinaryRelationshipKindUUID 
  = String @@ DirectedBinaryRelationshipKindTag
  
  def directedBinaryRelationshipKindUUID(uuid: String)
  : DirectedBinaryRelationshipKindUUID
  = covariantTag[DirectedBinaryRelationshipKindTag][String](uuid)
  
  implicit val decodeDirectedBinaryRelationshipKindUUID
  : Decoder[DirectedBinaryRelationshipKindUUID]
  = decodeTag[DirectedBinaryRelationshipKindTag]
  
  implicit val encodeDirectedBinaryRelationshipKindUUID
  : Encoder[DirectedBinaryRelationshipKindUUID]
  = encodeTag[DirectedBinaryRelationshipKindTag]
  
  implicit val orderingDirectedBinaryRelationshipKindUUID
  : Ordering[DirectedBinaryRelationshipKindUUID] 
  = new Ordering[DirectedBinaryRelationshipKindUUID] {
  	override def compare
  	(x: DirectedBinaryRelationshipKindUUID, 
  	 y: DirectedBinaryRelationshipKindUUID)
  	: Int = x.compareTo(y)
  }
  
  type DisjointUnionOfConceptsAxiomUUID 
  = String @@ DisjointUnionOfConceptsAxiomTag
  
  def disjointUnionOfConceptsAxiomUUID(uuid: String)
  : DisjointUnionOfConceptsAxiomUUID
  = covariantTag[DisjointUnionOfConceptsAxiomTag][String](uuid)
  
  implicit val decodeDisjointUnionOfConceptsAxiomUUID
  : Decoder[DisjointUnionOfConceptsAxiomUUID]
  = decodeTag[DisjointUnionOfConceptsAxiomTag]
  
  implicit val encodeDisjointUnionOfConceptsAxiomUUID
  : Encoder[DisjointUnionOfConceptsAxiomUUID]
  = encodeTag[DisjointUnionOfConceptsAxiomTag]
  
  implicit val orderingDisjointUnionOfConceptsAxiomUUID
  : Ordering[DisjointUnionOfConceptsAxiomUUID] 
  = new Ordering[DisjointUnionOfConceptsAxiomUUID] {
  	override def compare
  	(x: DisjointUnionOfConceptsAxiomUUID, 
  	 y: DisjointUnionOfConceptsAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type ElementCrossReferenceTupleUUID 
  = String @@ ElementCrossReferenceTupleTag
  
  def elementCrossReferenceTupleUUID(uuid: String)
  : ElementCrossReferenceTupleUUID
  = covariantTag[ElementCrossReferenceTupleTag][String](uuid)
  
  implicit val decodeElementCrossReferenceTupleUUID
  : Decoder[ElementCrossReferenceTupleUUID]
  = decodeTag[ElementCrossReferenceTupleTag]
  
  implicit val encodeElementCrossReferenceTupleUUID
  : Encoder[ElementCrossReferenceTupleUUID]
  = encodeTag[ElementCrossReferenceTupleTag]
  
  implicit val orderingElementCrossReferenceTupleUUID
  : Ordering[ElementCrossReferenceTupleUUID] 
  = new Ordering[ElementCrossReferenceTupleUUID] {
  	override def compare
  	(x: ElementCrossReferenceTupleUUID, 
  	 y: ElementCrossReferenceTupleUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityUUID 
  = String @@ EntityTag
  
  def entityUUID(uuid: String)
  : EntityUUID
  = covariantTag[EntityTag][String](uuid)
  
  implicit val decodeEntityUUID
  : Decoder[EntityUUID]
  = decodeTag[EntityTag]
  
  implicit val encodeEntityUUID
  : Encoder[EntityUUID]
  = encodeTag[EntityTag]
  
  implicit val orderingEntityUUID
  : Ordering[EntityUUID] 
  = new Ordering[EntityUUID] {
  	override def compare
  	(x: EntityUUID, 
  	 y: EntityUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityExistentialRestrictionAxiomUUID 
  = String @@ EntityExistentialRestrictionAxiomTag
  
  def entityExistentialRestrictionAxiomUUID(uuid: String)
  : EntityExistentialRestrictionAxiomUUID
  = covariantTag[EntityExistentialRestrictionAxiomTag][String](uuid)
  
  implicit val decodeEntityExistentialRestrictionAxiomUUID
  : Decoder[EntityExistentialRestrictionAxiomUUID]
  = decodeTag[EntityExistentialRestrictionAxiomTag]
  
  implicit val encodeEntityExistentialRestrictionAxiomUUID
  : Encoder[EntityExistentialRestrictionAxiomUUID]
  = encodeTag[EntityExistentialRestrictionAxiomTag]
  
  implicit val orderingEntityExistentialRestrictionAxiomUUID
  : Ordering[EntityExistentialRestrictionAxiomUUID] 
  = new Ordering[EntityExistentialRestrictionAxiomUUID] {
  	override def compare
  	(x: EntityExistentialRestrictionAxiomUUID, 
  	 y: EntityExistentialRestrictionAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityRelationshipUUID 
  = String @@ EntityRelationshipTag
  
  def entityRelationshipUUID(uuid: String)
  : EntityRelationshipUUID
  = covariantTag[EntityRelationshipTag][String](uuid)
  
  implicit val decodeEntityRelationshipUUID
  : Decoder[EntityRelationshipUUID]
  = decodeTag[EntityRelationshipTag]
  
  implicit val encodeEntityRelationshipUUID
  : Encoder[EntityRelationshipUUID]
  = encodeTag[EntityRelationshipTag]
  
  implicit val orderingEntityRelationshipUUID
  : Ordering[EntityRelationshipUUID] 
  = new Ordering[EntityRelationshipUUID] {
  	override def compare
  	(x: EntityRelationshipUUID, 
  	 y: EntityRelationshipUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityRestrictionAxiomUUID 
  = String @@ EntityRestrictionAxiomTag
  
  def entityRestrictionAxiomUUID(uuid: String)
  : EntityRestrictionAxiomUUID
  = covariantTag[EntityRestrictionAxiomTag][String](uuid)
  
  implicit val decodeEntityRestrictionAxiomUUID
  : Decoder[EntityRestrictionAxiomUUID]
  = decodeTag[EntityRestrictionAxiomTag]
  
  implicit val encodeEntityRestrictionAxiomUUID
  : Encoder[EntityRestrictionAxiomUUID]
  = encodeTag[EntityRestrictionAxiomTag]
  
  implicit val orderingEntityRestrictionAxiomUUID
  : Ordering[EntityRestrictionAxiomUUID] 
  = new Ordering[EntityRestrictionAxiomUUID] {
  	override def compare
  	(x: EntityRestrictionAxiomUUID, 
  	 y: EntityRestrictionAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityScalarDataPropertyUUID 
  = String @@ EntityScalarDataPropertyTag
  
  def entityScalarDataPropertyUUID(uuid: String)
  : EntityScalarDataPropertyUUID
  = covariantTag[EntityScalarDataPropertyTag][String](uuid)
  
  implicit val decodeEntityScalarDataPropertyUUID
  : Decoder[EntityScalarDataPropertyUUID]
  = decodeTag[EntityScalarDataPropertyTag]
  
  implicit val encodeEntityScalarDataPropertyUUID
  : Encoder[EntityScalarDataPropertyUUID]
  = encodeTag[EntityScalarDataPropertyTag]
  
  implicit val orderingEntityScalarDataPropertyUUID
  : Ordering[EntityScalarDataPropertyUUID] 
  = new Ordering[EntityScalarDataPropertyUUID] {
  	override def compare
  	(x: EntityScalarDataPropertyUUID, 
  	 y: EntityScalarDataPropertyUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityScalarDataPropertyExistentialRestrictionAxiomUUID 
  = String @@ EntityScalarDataPropertyExistentialRestrictionAxiomTag
  
  def entityScalarDataPropertyExistentialRestrictionAxiomUUID(uuid: String)
  : EntityScalarDataPropertyExistentialRestrictionAxiomUUID
  = covariantTag[EntityScalarDataPropertyExistentialRestrictionAxiomTag][String](uuid)
  
  implicit val decodeEntityScalarDataPropertyExistentialRestrictionAxiomUUID
  : Decoder[EntityScalarDataPropertyExistentialRestrictionAxiomUUID]
  = decodeTag[EntityScalarDataPropertyExistentialRestrictionAxiomTag]
  
  implicit val encodeEntityScalarDataPropertyExistentialRestrictionAxiomUUID
  : Encoder[EntityScalarDataPropertyExistentialRestrictionAxiomUUID]
  = encodeTag[EntityScalarDataPropertyExistentialRestrictionAxiomTag]
  
  implicit val orderingEntityScalarDataPropertyExistentialRestrictionAxiomUUID
  : Ordering[EntityScalarDataPropertyExistentialRestrictionAxiomUUID] 
  = new Ordering[EntityScalarDataPropertyExistentialRestrictionAxiomUUID] {
  	override def compare
  	(x: EntityScalarDataPropertyExistentialRestrictionAxiomUUID, 
  	 y: EntityScalarDataPropertyExistentialRestrictionAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityScalarDataPropertyParticularRestrictionAxiomUUID 
  = String @@ EntityScalarDataPropertyParticularRestrictionAxiomTag
  
  def entityScalarDataPropertyParticularRestrictionAxiomUUID(uuid: String)
  : EntityScalarDataPropertyParticularRestrictionAxiomUUID
  = covariantTag[EntityScalarDataPropertyParticularRestrictionAxiomTag][String](uuid)
  
  implicit val decodeEntityScalarDataPropertyParticularRestrictionAxiomUUID
  : Decoder[EntityScalarDataPropertyParticularRestrictionAxiomUUID]
  = decodeTag[EntityScalarDataPropertyParticularRestrictionAxiomTag]
  
  implicit val encodeEntityScalarDataPropertyParticularRestrictionAxiomUUID
  : Encoder[EntityScalarDataPropertyParticularRestrictionAxiomUUID]
  = encodeTag[EntityScalarDataPropertyParticularRestrictionAxiomTag]
  
  implicit val orderingEntityScalarDataPropertyParticularRestrictionAxiomUUID
  : Ordering[EntityScalarDataPropertyParticularRestrictionAxiomUUID] 
  = new Ordering[EntityScalarDataPropertyParticularRestrictionAxiomUUID] {
  	override def compare
  	(x: EntityScalarDataPropertyParticularRestrictionAxiomUUID, 
  	 y: EntityScalarDataPropertyParticularRestrictionAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityScalarDataPropertyRestrictionAxiomUUID 
  = String @@ EntityScalarDataPropertyRestrictionAxiomTag
  
  def entityScalarDataPropertyRestrictionAxiomUUID(uuid: String)
  : EntityScalarDataPropertyRestrictionAxiomUUID
  = covariantTag[EntityScalarDataPropertyRestrictionAxiomTag][String](uuid)
  
  implicit val decodeEntityScalarDataPropertyRestrictionAxiomUUID
  : Decoder[EntityScalarDataPropertyRestrictionAxiomUUID]
  = decodeTag[EntityScalarDataPropertyRestrictionAxiomTag]
  
  implicit val encodeEntityScalarDataPropertyRestrictionAxiomUUID
  : Encoder[EntityScalarDataPropertyRestrictionAxiomUUID]
  = encodeTag[EntityScalarDataPropertyRestrictionAxiomTag]
  
  implicit val orderingEntityScalarDataPropertyRestrictionAxiomUUID
  : Ordering[EntityScalarDataPropertyRestrictionAxiomUUID] 
  = new Ordering[EntityScalarDataPropertyRestrictionAxiomUUID] {
  	override def compare
  	(x: EntityScalarDataPropertyRestrictionAxiomUUID, 
  	 y: EntityScalarDataPropertyRestrictionAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityScalarDataPropertyUniversalRestrictionAxiomUUID 
  = String @@ EntityScalarDataPropertyUniversalRestrictionAxiomTag
  
  def entityScalarDataPropertyUniversalRestrictionAxiomUUID(uuid: String)
  : EntityScalarDataPropertyUniversalRestrictionAxiomUUID
  = covariantTag[EntityScalarDataPropertyUniversalRestrictionAxiomTag][String](uuid)
  
  implicit val decodeEntityScalarDataPropertyUniversalRestrictionAxiomUUID
  : Decoder[EntityScalarDataPropertyUniversalRestrictionAxiomUUID]
  = decodeTag[EntityScalarDataPropertyUniversalRestrictionAxiomTag]
  
  implicit val encodeEntityScalarDataPropertyUniversalRestrictionAxiomUUID
  : Encoder[EntityScalarDataPropertyUniversalRestrictionAxiomUUID]
  = encodeTag[EntityScalarDataPropertyUniversalRestrictionAxiomTag]
  
  implicit val orderingEntityScalarDataPropertyUniversalRestrictionAxiomUUID
  : Ordering[EntityScalarDataPropertyUniversalRestrictionAxiomUUID] 
  = new Ordering[EntityScalarDataPropertyUniversalRestrictionAxiomUUID] {
  	override def compare
  	(x: EntityScalarDataPropertyUniversalRestrictionAxiomUUID, 
  	 y: EntityScalarDataPropertyUniversalRestrictionAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityStructuredDataPropertyUUID 
  = String @@ EntityStructuredDataPropertyTag
  
  def entityStructuredDataPropertyUUID(uuid: String)
  : EntityStructuredDataPropertyUUID
  = covariantTag[EntityStructuredDataPropertyTag][String](uuid)
  
  implicit val decodeEntityStructuredDataPropertyUUID
  : Decoder[EntityStructuredDataPropertyUUID]
  = decodeTag[EntityStructuredDataPropertyTag]
  
  implicit val encodeEntityStructuredDataPropertyUUID
  : Encoder[EntityStructuredDataPropertyUUID]
  = encodeTag[EntityStructuredDataPropertyTag]
  
  implicit val orderingEntityStructuredDataPropertyUUID
  : Ordering[EntityStructuredDataPropertyUUID] 
  = new Ordering[EntityStructuredDataPropertyUUID] {
  	override def compare
  	(x: EntityStructuredDataPropertyUUID, 
  	 y: EntityStructuredDataPropertyUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityStructuredDataPropertyParticularRestrictionAxiomUUID 
  = String @@ EntityStructuredDataPropertyParticularRestrictionAxiomTag
  
  def entityStructuredDataPropertyParticularRestrictionAxiomUUID(uuid: String)
  : EntityStructuredDataPropertyParticularRestrictionAxiomUUID
  = covariantTag[EntityStructuredDataPropertyParticularRestrictionAxiomTag][String](uuid)
  
  implicit val decodeEntityStructuredDataPropertyParticularRestrictionAxiomUUID
  : Decoder[EntityStructuredDataPropertyParticularRestrictionAxiomUUID]
  = decodeTag[EntityStructuredDataPropertyParticularRestrictionAxiomTag]
  
  implicit val encodeEntityStructuredDataPropertyParticularRestrictionAxiomUUID
  : Encoder[EntityStructuredDataPropertyParticularRestrictionAxiomUUID]
  = encodeTag[EntityStructuredDataPropertyParticularRestrictionAxiomTag]
  
  implicit val orderingEntityStructuredDataPropertyParticularRestrictionAxiomUUID
  : Ordering[EntityStructuredDataPropertyParticularRestrictionAxiomUUID] 
  = new Ordering[EntityStructuredDataPropertyParticularRestrictionAxiomUUID] {
  	override def compare
  	(x: EntityStructuredDataPropertyParticularRestrictionAxiomUUID, 
  	 y: EntityStructuredDataPropertyParticularRestrictionAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityStructuredDataPropertyRestrictionAxiomUUID 
  = String @@ EntityStructuredDataPropertyRestrictionAxiomTag
  
  def entityStructuredDataPropertyRestrictionAxiomUUID(uuid: String)
  : EntityStructuredDataPropertyRestrictionAxiomUUID
  = covariantTag[EntityStructuredDataPropertyRestrictionAxiomTag][String](uuid)
  
  implicit val decodeEntityStructuredDataPropertyRestrictionAxiomUUID
  : Decoder[EntityStructuredDataPropertyRestrictionAxiomUUID]
  = decodeTag[EntityStructuredDataPropertyRestrictionAxiomTag]
  
  implicit val encodeEntityStructuredDataPropertyRestrictionAxiomUUID
  : Encoder[EntityStructuredDataPropertyRestrictionAxiomUUID]
  = encodeTag[EntityStructuredDataPropertyRestrictionAxiomTag]
  
  implicit val orderingEntityStructuredDataPropertyRestrictionAxiomUUID
  : Ordering[EntityStructuredDataPropertyRestrictionAxiomUUID] 
  = new Ordering[EntityStructuredDataPropertyRestrictionAxiomUUID] {
  	override def compare
  	(x: EntityStructuredDataPropertyRestrictionAxiomUUID, 
  	 y: EntityStructuredDataPropertyRestrictionAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityUniversalRestrictionAxiomUUID 
  = String @@ EntityUniversalRestrictionAxiomTag
  
  def entityUniversalRestrictionAxiomUUID(uuid: String)
  : EntityUniversalRestrictionAxiomUUID
  = covariantTag[EntityUniversalRestrictionAxiomTag][String](uuid)
  
  implicit val decodeEntityUniversalRestrictionAxiomUUID
  : Decoder[EntityUniversalRestrictionAxiomUUID]
  = decodeTag[EntityUniversalRestrictionAxiomTag]
  
  implicit val encodeEntityUniversalRestrictionAxiomUUID
  : Encoder[EntityUniversalRestrictionAxiomUUID]
  = encodeTag[EntityUniversalRestrictionAxiomTag]
  
  implicit val orderingEntityUniversalRestrictionAxiomUUID
  : Ordering[EntityUniversalRestrictionAxiomUUID] 
  = new Ordering[EntityUniversalRestrictionAxiomUUID] {
  	override def compare
  	(x: EntityUniversalRestrictionAxiomUUID, 
  	 y: EntityUniversalRestrictionAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type ExtrinsicIdentityKindUUID 
  = String @@ ExtrinsicIdentityKindTag
  
  def extrinsicIdentityKindUUID(uuid: String)
  : ExtrinsicIdentityKindUUID
  = covariantTag[ExtrinsicIdentityKindTag][String](uuid)
  
  implicit val decodeExtrinsicIdentityKindUUID
  : Decoder[ExtrinsicIdentityKindUUID]
  = decodeTag[ExtrinsicIdentityKindTag]
  
  implicit val encodeExtrinsicIdentityKindUUID
  : Encoder[ExtrinsicIdentityKindUUID]
  = encodeTag[ExtrinsicIdentityKindTag]
  
  implicit val orderingExtrinsicIdentityKindUUID
  : Ordering[ExtrinsicIdentityKindUUID] 
  = new Ordering[ExtrinsicIdentityKindUUID] {
  	override def compare
  	(x: ExtrinsicIdentityKindUUID, 
  	 y: ExtrinsicIdentityKindUUID)
  	: Int = x.compareTo(y)
  }
  
  type ForwardPropertyUUID 
  = String @@ ForwardPropertyTag
  
  def forwardPropertyUUID(uuid: String)
  : ForwardPropertyUUID
  = covariantTag[ForwardPropertyTag][String](uuid)
  
  implicit val decodeForwardPropertyUUID
  : Decoder[ForwardPropertyUUID]
  = decodeTag[ForwardPropertyTag]
  
  implicit val encodeForwardPropertyUUID
  : Encoder[ForwardPropertyUUID]
  = encodeTag[ForwardPropertyTag]
  
  implicit val orderingForwardPropertyUUID
  : Ordering[ForwardPropertyUUID] 
  = new Ordering[ForwardPropertyUUID] {
  	override def compare
  	(x: ForwardPropertyUUID, 
  	 y: ForwardPropertyUUID)
  	: Int = x.compareTo(y)
  }
  
  type IRIScalarRestrictionUUID 
  = String @@ IRIScalarRestrictionTag
  
  def iriScalarRestrictionUUID(uuid: String)
  : IRIScalarRestrictionUUID
  = covariantTag[IRIScalarRestrictionTag][String](uuid)
  
  implicit val decodeIRIScalarRestrictionUUID
  : Decoder[IRIScalarRestrictionUUID]
  = decodeTag[IRIScalarRestrictionTag]
  
  implicit val encodeIRIScalarRestrictionUUID
  : Encoder[IRIScalarRestrictionUUID]
  = encodeTag[IRIScalarRestrictionTag]
  
  implicit val orderingIRIScalarRestrictionUUID
  : Ordering[IRIScalarRestrictionUUID] 
  = new Ordering[IRIScalarRestrictionUUID] {
  	override def compare
  	(x: IRIScalarRestrictionUUID, 
  	 y: IRIScalarRestrictionUUID)
  	: Int = x.compareTo(y)
  }
  
  type IdentityKindUUID 
  = String @@ IdentityKindTag
  
  def identityKindUUID(uuid: String)
  : IdentityKindUUID
  = covariantTag[IdentityKindTag][String](uuid)
  
  implicit val decodeIdentityKindUUID
  : Decoder[IdentityKindUUID]
  = decodeTag[IdentityKindTag]
  
  implicit val encodeIdentityKindUUID
  : Encoder[IdentityKindUUID]
  = encodeTag[IdentityKindTag]
  
  implicit val orderingIdentityKindUUID
  : Ordering[IdentityKindUUID] 
  = new Ordering[IdentityKindUUID] {
  	override def compare
  	(x: IdentityKindUUID, 
  	 y: IdentityKindUUID)
  	: Int = x.compareTo(y)
  }
  
  type IntrinsicIdentityKindUUID 
  = String @@ IntrinsicIdentityKindTag
  
  def intrinsicIdentityKindUUID(uuid: String)
  : IntrinsicIdentityKindUUID
  = covariantTag[IntrinsicIdentityKindTag][String](uuid)
  
  implicit val decodeIntrinsicIdentityKindUUID
  : Decoder[IntrinsicIdentityKindUUID]
  = decodeTag[IntrinsicIdentityKindTag]
  
  implicit val encodeIntrinsicIdentityKindUUID
  : Encoder[IntrinsicIdentityKindUUID]
  = encodeTag[IntrinsicIdentityKindTag]
  
  implicit val orderingIntrinsicIdentityKindUUID
  : Ordering[IntrinsicIdentityKindUUID] 
  = new Ordering[IntrinsicIdentityKindUUID] {
  	override def compare
  	(x: IntrinsicIdentityKindUUID, 
  	 y: IntrinsicIdentityKindUUID)
  	: Int = x.compareTo(y)
  }
  
  type InversePropertyUUID 
  = String @@ InversePropertyTag
  
  def inversePropertyUUID(uuid: String)
  : InversePropertyUUID
  = covariantTag[InversePropertyTag][String](uuid)
  
  implicit val decodeInversePropertyUUID
  : Decoder[InversePropertyUUID]
  = decodeTag[InversePropertyTag]
  
  implicit val encodeInversePropertyUUID
  : Encoder[InversePropertyUUID]
  = encodeTag[InversePropertyTag]
  
  implicit val orderingInversePropertyUUID
  : Ordering[InversePropertyUUID] 
  = new Ordering[InversePropertyUUID] {
  	override def compare
  	(x: InversePropertyUUID, 
  	 y: InversePropertyUUID)
  	: Int = x.compareTo(y)
  }
  
  type LogicalElementUUID 
  = String @@ LogicalElementTag
  
  def logicalElementUUID(uuid: String)
  : LogicalElementUUID
  = covariantTag[LogicalElementTag][String](uuid)
  
  implicit val decodeLogicalElementUUID
  : Decoder[LogicalElementUUID]
  = decodeTag[LogicalElementTag]
  
  implicit val encodeLogicalElementUUID
  : Encoder[LogicalElementUUID]
  = encodeTag[LogicalElementTag]
  
  implicit val orderingLogicalElementUUID
  : Ordering[LogicalElementUUID] 
  = new Ordering[LogicalElementUUID] {
  	override def compare
  	(x: LogicalElementUUID, 
  	 y: LogicalElementUUID)
  	: Int = x.compareTo(y)
  }
  
  type ModuleUUID 
  = String @@ ModuleTag
  
  def moduleUUID(uuid: String)
  : ModuleUUID
  = covariantTag[ModuleTag][String](uuid)
  
  implicit val decodeModuleUUID
  : Decoder[ModuleUUID]
  = decodeTag[ModuleTag]
  
  implicit val encodeModuleUUID
  : Encoder[ModuleUUID]
  = encodeTag[ModuleTag]
  
  implicit val orderingModuleUUID
  : Ordering[ModuleUUID] 
  = new Ordering[ModuleUUID] {
  	override def compare
  	(x: ModuleUUID, 
  	 y: ModuleUUID)
  	: Int = x.compareTo(y)
  }
  
  type ModuleEdgeUUID 
  = String @@ ModuleEdgeTag
  
  def moduleEdgeUUID(uuid: String)
  : ModuleEdgeUUID
  = covariantTag[ModuleEdgeTag][String](uuid)
  
  implicit val decodeModuleEdgeUUID
  : Decoder[ModuleEdgeUUID]
  = decodeTag[ModuleEdgeTag]
  
  implicit val encodeModuleEdgeUUID
  : Encoder[ModuleEdgeUUID]
  = encodeTag[ModuleEdgeTag]
  
  implicit val orderingModuleEdgeUUID
  : Ordering[ModuleEdgeUUID] 
  = new Ordering[ModuleEdgeUUID] {
  	override def compare
  	(x: ModuleEdgeUUID, 
  	 y: ModuleEdgeUUID)
  	: Int = x.compareTo(y)
  }
  
  type ModuleElementUUID 
  = String @@ ModuleElementTag
  
  def moduleElementUUID(uuid: String)
  : ModuleElementUUID
  = covariantTag[ModuleElementTag][String](uuid)
  
  implicit val decodeModuleElementUUID
  : Decoder[ModuleElementUUID]
  = decodeTag[ModuleElementTag]
  
  implicit val encodeModuleElementUUID
  : Encoder[ModuleElementUUID]
  = encodeTag[ModuleElementTag]
  
  implicit val orderingModuleElementUUID
  : Ordering[ModuleElementUUID] 
  = new Ordering[ModuleElementUUID] {
  	override def compare
  	(x: ModuleElementUUID, 
  	 y: ModuleElementUUID)
  	: Int = x.compareTo(y)
  }
  
  type NonCrossReferencableKindUUID 
  = String @@ NonCrossReferencableKindTag
  
  def nonCrossReferencableKindUUID(uuid: String)
  : NonCrossReferencableKindUUID
  = covariantTag[NonCrossReferencableKindTag][String](uuid)
  
  implicit val decodeNonCrossReferencableKindUUID
  : Decoder[NonCrossReferencableKindUUID]
  = decodeTag[NonCrossReferencableKindTag]
  
  implicit val encodeNonCrossReferencableKindUUID
  : Encoder[NonCrossReferencableKindUUID]
  = encodeTag[NonCrossReferencableKindTag]
  
  implicit val orderingNonCrossReferencableKindUUID
  : Ordering[NonCrossReferencableKindUUID] 
  = new Ordering[NonCrossReferencableKindUUID] {
  	override def compare
  	(x: NonCrossReferencableKindUUID, 
  	 y: NonCrossReferencableKindUUID)
  	: Int = x.compareTo(y)
  }
  
  type NonLogicalElementUUID 
  = String @@ NonLogicalElementTag
  
  def nonLogicalElementUUID(uuid: String)
  : NonLogicalElementUUID
  = covariantTag[NonLogicalElementTag][String](uuid)
  
  implicit val decodeNonLogicalElementUUID
  : Decoder[NonLogicalElementUUID]
  = decodeTag[NonLogicalElementTag]
  
  implicit val encodeNonLogicalElementUUID
  : Encoder[NonLogicalElementUUID]
  = encodeTag[NonLogicalElementTag]
  
  implicit val orderingNonLogicalElementUUID
  : Ordering[NonLogicalElementUUID] 
  = new Ordering[NonLogicalElementUUID] {
  	override def compare
  	(x: NonLogicalElementUUID, 
  	 y: NonLogicalElementUUID)
  	: Int = x.compareTo(y)
  }
  
  type NumericScalarRestrictionUUID 
  = String @@ NumericScalarRestrictionTag
  
  def numericScalarRestrictionUUID(uuid: String)
  : NumericScalarRestrictionUUID
  = covariantTag[NumericScalarRestrictionTag][String](uuid)
  
  implicit val decodeNumericScalarRestrictionUUID
  : Decoder[NumericScalarRestrictionUUID]
  = decodeTag[NumericScalarRestrictionTag]
  
  implicit val encodeNumericScalarRestrictionUUID
  : Encoder[NumericScalarRestrictionUUID]
  = encodeTag[NumericScalarRestrictionTag]
  
  implicit val orderingNumericScalarRestrictionUUID
  : Ordering[NumericScalarRestrictionUUID] 
  = new Ordering[NumericScalarRestrictionUUID] {
  	override def compare
  	(x: NumericScalarRestrictionUUID, 
  	 y: NumericScalarRestrictionUUID)
  	: Int = x.compareTo(y)
  }
  
  type PlainLiteralScalarRestrictionUUID 
  = String @@ PlainLiteralScalarRestrictionTag
  
  def plainLiteralScalarRestrictionUUID(uuid: String)
  : PlainLiteralScalarRestrictionUUID
  = covariantTag[PlainLiteralScalarRestrictionTag][String](uuid)
  
  implicit val decodePlainLiteralScalarRestrictionUUID
  : Decoder[PlainLiteralScalarRestrictionUUID]
  = decodeTag[PlainLiteralScalarRestrictionTag]
  
  implicit val encodePlainLiteralScalarRestrictionUUID
  : Encoder[PlainLiteralScalarRestrictionUUID]
  = encodeTag[PlainLiteralScalarRestrictionTag]
  
  implicit val orderingPlainLiteralScalarRestrictionUUID
  : Ordering[PlainLiteralScalarRestrictionUUID] 
  = new Ordering[PlainLiteralScalarRestrictionUUID] {
  	override def compare
  	(x: PlainLiteralScalarRestrictionUUID, 
  	 y: PlainLiteralScalarRestrictionUUID)
  	: Int = x.compareTo(y)
  }
  
  type PredicateUUID 
  = String @@ PredicateTag
  
  def predicateUUID(uuid: String)
  : PredicateUUID
  = covariantTag[PredicateTag][String](uuid)
  
  implicit val decodePredicateUUID
  : Decoder[PredicateUUID]
  = decodeTag[PredicateTag]
  
  implicit val encodePredicateUUID
  : Encoder[PredicateUUID]
  = encodeTag[PredicateTag]
  
  implicit val orderingPredicateUUID
  : Ordering[PredicateUUID] 
  = new Ordering[PredicateUUID] {
  	override def compare
  	(x: PredicateUUID, 
  	 y: PredicateUUID)
  	: Int = x.compareTo(y)
  }
  
  type ReifiedRelationshipUUID 
  = String @@ ReifiedRelationshipTag
  
  def reifiedRelationshipUUID(uuid: String)
  : ReifiedRelationshipUUID
  = covariantTag[ReifiedRelationshipTag][String](uuid)
  
  implicit val decodeReifiedRelationshipUUID
  : Decoder[ReifiedRelationshipUUID]
  = decodeTag[ReifiedRelationshipTag]
  
  implicit val encodeReifiedRelationshipUUID
  : Encoder[ReifiedRelationshipUUID]
  = encodeTag[ReifiedRelationshipTag]
  
  implicit val orderingReifiedRelationshipUUID
  : Ordering[ReifiedRelationshipUUID] 
  = new Ordering[ReifiedRelationshipUUID] {
  	override def compare
  	(x: ReifiedRelationshipUUID, 
  	 y: ReifiedRelationshipUUID)
  	: Int = x.compareTo(y)
  }
  
  type ReifiedRelationshipInstanceUUID 
  = String @@ ReifiedRelationshipInstanceTag
  
  def reifiedRelationshipInstanceUUID(uuid: String)
  : ReifiedRelationshipInstanceUUID
  = covariantTag[ReifiedRelationshipInstanceTag][String](uuid)
  
  implicit val decodeReifiedRelationshipInstanceUUID
  : Decoder[ReifiedRelationshipInstanceUUID]
  = decodeTag[ReifiedRelationshipInstanceTag]
  
  implicit val encodeReifiedRelationshipInstanceUUID
  : Encoder[ReifiedRelationshipInstanceUUID]
  = encodeTag[ReifiedRelationshipInstanceTag]
  
  implicit val orderingReifiedRelationshipInstanceUUID
  : Ordering[ReifiedRelationshipInstanceUUID] 
  = new Ordering[ReifiedRelationshipInstanceUUID] {
  	override def compare
  	(x: ReifiedRelationshipInstanceUUID, 
  	 y: ReifiedRelationshipInstanceUUID)
  	: Int = x.compareTo(y)
  }
  
  type ReifiedRelationshipInstanceDomainUUID 
  = String @@ ReifiedRelationshipInstanceDomainTag
  
  def reifiedRelationshipInstanceDomainUUID(uuid: String)
  : ReifiedRelationshipInstanceDomainUUID
  = covariantTag[ReifiedRelationshipInstanceDomainTag][String](uuid)
  
  implicit val decodeReifiedRelationshipInstanceDomainUUID
  : Decoder[ReifiedRelationshipInstanceDomainUUID]
  = decodeTag[ReifiedRelationshipInstanceDomainTag]
  
  implicit val encodeReifiedRelationshipInstanceDomainUUID
  : Encoder[ReifiedRelationshipInstanceDomainUUID]
  = encodeTag[ReifiedRelationshipInstanceDomainTag]
  
  implicit val orderingReifiedRelationshipInstanceDomainUUID
  : Ordering[ReifiedRelationshipInstanceDomainUUID] 
  = new Ordering[ReifiedRelationshipInstanceDomainUUID] {
  	override def compare
  	(x: ReifiedRelationshipInstanceDomainUUID, 
  	 y: ReifiedRelationshipInstanceDomainUUID)
  	: Int = x.compareTo(y)
  }
  
  type ReifiedRelationshipInstanceRangeUUID 
  = String @@ ReifiedRelationshipInstanceRangeTag
  
  def reifiedRelationshipInstanceRangeUUID(uuid: String)
  : ReifiedRelationshipInstanceRangeUUID
  = covariantTag[ReifiedRelationshipInstanceRangeTag][String](uuid)
  
  implicit val decodeReifiedRelationshipInstanceRangeUUID
  : Decoder[ReifiedRelationshipInstanceRangeUUID]
  = decodeTag[ReifiedRelationshipInstanceRangeTag]
  
  implicit val encodeReifiedRelationshipInstanceRangeUUID
  : Encoder[ReifiedRelationshipInstanceRangeUUID]
  = encodeTag[ReifiedRelationshipInstanceRangeTag]
  
  implicit val orderingReifiedRelationshipInstanceRangeUUID
  : Ordering[ReifiedRelationshipInstanceRangeUUID] 
  = new Ordering[ReifiedRelationshipInstanceRangeUUID] {
  	override def compare
  	(x: ReifiedRelationshipInstanceRangeUUID, 
  	 y: ReifiedRelationshipInstanceRangeUUID)
  	: Int = x.compareTo(y)
  }
  
  type ResourceUUID 
  = String @@ ResourceTag
  
  def resourceUUID(uuid: String)
  : ResourceUUID
  = covariantTag[ResourceTag][String](uuid)
  
  implicit val decodeResourceUUID
  : Decoder[ResourceUUID]
  = decodeTag[ResourceTag]
  
  implicit val encodeResourceUUID
  : Encoder[ResourceUUID]
  = encodeTag[ResourceTag]
  
  implicit val orderingResourceUUID
  : Ordering[ResourceUUID] 
  = new Ordering[ResourceUUID] {
  	override def compare
  	(x: ResourceUUID, 
  	 y: ResourceUUID)
  	: Int = x.compareTo(y)
  }
  
  type RestrictableRelationshipUUID 
  = String @@ RestrictableRelationshipTag
  
  def restrictableRelationshipUUID(uuid: String)
  : RestrictableRelationshipUUID
  = covariantTag[RestrictableRelationshipTag][String](uuid)
  
  implicit val decodeRestrictableRelationshipUUID
  : Decoder[RestrictableRelationshipUUID]
  = decodeTag[RestrictableRelationshipTag]
  
  implicit val encodeRestrictableRelationshipUUID
  : Encoder[RestrictableRelationshipUUID]
  = encodeTag[RestrictableRelationshipTag]
  
  implicit val orderingRestrictableRelationshipUUID
  : Ordering[RestrictableRelationshipUUID] 
  = new Ordering[RestrictableRelationshipUUID] {
  	override def compare
  	(x: RestrictableRelationshipUUID, 
  	 y: RestrictableRelationshipUUID)
  	: Int = x.compareTo(y)
  }
  
  type RestrictedDataRangeUUID 
  = String @@ RestrictedDataRangeTag
  
  def restrictedDataRangeUUID(uuid: String)
  : RestrictedDataRangeUUID
  = covariantTag[RestrictedDataRangeTag][String](uuid)
  
  implicit val decodeRestrictedDataRangeUUID
  : Decoder[RestrictedDataRangeUUID]
  = decodeTag[RestrictedDataRangeTag]
  
  implicit val encodeRestrictedDataRangeUUID
  : Encoder[RestrictedDataRangeUUID]
  = encodeTag[RestrictedDataRangeTag]
  
  implicit val orderingRestrictedDataRangeUUID
  : Ordering[RestrictedDataRangeUUID] 
  = new Ordering[RestrictedDataRangeUUID] {
  	override def compare
  	(x: RestrictedDataRangeUUID, 
  	 y: RestrictedDataRangeUUID)
  	: Int = x.compareTo(y)
  }
  
  type RestrictionScalarDataPropertyValueUUID 
  = String @@ RestrictionScalarDataPropertyValueTag
  
  def restrictionScalarDataPropertyValueUUID(uuid: String)
  : RestrictionScalarDataPropertyValueUUID
  = covariantTag[RestrictionScalarDataPropertyValueTag][String](uuid)
  
  implicit val decodeRestrictionScalarDataPropertyValueUUID
  : Decoder[RestrictionScalarDataPropertyValueUUID]
  = decodeTag[RestrictionScalarDataPropertyValueTag]
  
  implicit val encodeRestrictionScalarDataPropertyValueUUID
  : Encoder[RestrictionScalarDataPropertyValueUUID]
  = encodeTag[RestrictionScalarDataPropertyValueTag]
  
  implicit val orderingRestrictionScalarDataPropertyValueUUID
  : Ordering[RestrictionScalarDataPropertyValueUUID] 
  = new Ordering[RestrictionScalarDataPropertyValueUUID] {
  	override def compare
  	(x: RestrictionScalarDataPropertyValueUUID, 
  	 y: RestrictionScalarDataPropertyValueUUID)
  	: Int = x.compareTo(y)
  }
  
  type RestrictionStructuredDataPropertyContextUUID 
  = String @@ RestrictionStructuredDataPropertyContextTag
  
  def restrictionStructuredDataPropertyContextUUID(uuid: String)
  : RestrictionStructuredDataPropertyContextUUID
  = covariantTag[RestrictionStructuredDataPropertyContextTag][String](uuid)
  
  implicit val decodeRestrictionStructuredDataPropertyContextUUID
  : Decoder[RestrictionStructuredDataPropertyContextUUID]
  = decodeTag[RestrictionStructuredDataPropertyContextTag]
  
  implicit val encodeRestrictionStructuredDataPropertyContextUUID
  : Encoder[RestrictionStructuredDataPropertyContextUUID]
  = encodeTag[RestrictionStructuredDataPropertyContextTag]
  
  implicit val orderingRestrictionStructuredDataPropertyContextUUID
  : Ordering[RestrictionStructuredDataPropertyContextUUID] 
  = new Ordering[RestrictionStructuredDataPropertyContextUUID] {
  	override def compare
  	(x: RestrictionStructuredDataPropertyContextUUID, 
  	 y: RestrictionStructuredDataPropertyContextUUID)
  	: Int = x.compareTo(y)
  }
  
  type RestrictionStructuredDataPropertyTupleUUID 
  = String @@ RestrictionStructuredDataPropertyTupleTag
  
  def restrictionStructuredDataPropertyTupleUUID(uuid: String)
  : RestrictionStructuredDataPropertyTupleUUID
  = covariantTag[RestrictionStructuredDataPropertyTupleTag][String](uuid)
  
  implicit val decodeRestrictionStructuredDataPropertyTupleUUID
  : Decoder[RestrictionStructuredDataPropertyTupleUUID]
  = decodeTag[RestrictionStructuredDataPropertyTupleTag]
  
  implicit val encodeRestrictionStructuredDataPropertyTupleUUID
  : Encoder[RestrictionStructuredDataPropertyTupleUUID]
  = encodeTag[RestrictionStructuredDataPropertyTupleTag]
  
  implicit val orderingRestrictionStructuredDataPropertyTupleUUID
  : Ordering[RestrictionStructuredDataPropertyTupleUUID] 
  = new Ordering[RestrictionStructuredDataPropertyTupleUUID] {
  	override def compare
  	(x: RestrictionStructuredDataPropertyTupleUUID, 
  	 y: RestrictionStructuredDataPropertyTupleUUID)
  	: Int = x.compareTo(y)
  }
  
  type RootConceptTaxonomyAxiomUUID 
  = String @@ RootConceptTaxonomyAxiomTag
  
  def rootConceptTaxonomyAxiomUUID(uuid: String)
  : RootConceptTaxonomyAxiomUUID
  = covariantTag[RootConceptTaxonomyAxiomTag][String](uuid)
  
  implicit val decodeRootConceptTaxonomyAxiomUUID
  : Decoder[RootConceptTaxonomyAxiomUUID]
  = decodeTag[RootConceptTaxonomyAxiomTag]
  
  implicit val encodeRootConceptTaxonomyAxiomUUID
  : Encoder[RootConceptTaxonomyAxiomUUID]
  = encodeTag[RootConceptTaxonomyAxiomTag]
  
  implicit val orderingRootConceptTaxonomyAxiomUUID
  : Ordering[RootConceptTaxonomyAxiomUUID] 
  = new Ordering[RootConceptTaxonomyAxiomUUID] {
  	override def compare
  	(x: RootConceptTaxonomyAxiomUUID, 
  	 y: RootConceptTaxonomyAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type RuleUUID 
  = String @@ RuleTag
  
  def ruleUUID(uuid: String)
  : RuleUUID
  = covariantTag[RuleTag][String](uuid)
  
  implicit val decodeRuleUUID
  : Decoder[RuleUUID]
  = decodeTag[RuleTag]
  
  implicit val encodeRuleUUID
  : Encoder[RuleUUID]
  = encodeTag[RuleTag]
  
  implicit val orderingRuleUUID
  : Ordering[RuleUUID] 
  = new Ordering[RuleUUID] {
  	override def compare
  	(x: RuleUUID, 
  	 y: RuleUUID)
  	: Int = x.compareTo(y)
  }
  
  type RuleBodySegmentUUID 
  = String @@ RuleBodySegmentTag
  
  def ruleBodySegmentUUID(uuid: String)
  : RuleBodySegmentUUID
  = covariantTag[RuleBodySegmentTag][String](uuid)
  
  implicit val decodeRuleBodySegmentUUID
  : Decoder[RuleBodySegmentUUID]
  = decodeTag[RuleBodySegmentTag]
  
  implicit val encodeRuleBodySegmentUUID
  : Encoder[RuleBodySegmentUUID]
  = encodeTag[RuleBodySegmentTag]
  
  implicit val orderingRuleBodySegmentUUID
  : Ordering[RuleBodySegmentUUID] 
  = new Ordering[RuleBodySegmentUUID] {
  	override def compare
  	(x: RuleBodySegmentUUID, 
  	 y: RuleBodySegmentUUID)
  	: Int = x.compareTo(y)
  }
  
  type ScalarUUID 
  = String @@ ScalarTag
  
  def scalarUUID(uuid: String)
  : ScalarUUID
  = covariantTag[ScalarTag][String](uuid)
  
  implicit val decodeScalarUUID
  : Decoder[ScalarUUID]
  = decodeTag[ScalarTag]
  
  implicit val encodeScalarUUID
  : Encoder[ScalarUUID]
  = encodeTag[ScalarTag]
  
  implicit val orderingScalarUUID
  : Ordering[ScalarUUID] 
  = new Ordering[ScalarUUID] {
  	override def compare
  	(x: ScalarUUID, 
  	 y: ScalarUUID)
  	: Int = x.compareTo(y)
  }
  
  type ScalarDataPropertyUUID 
  = String @@ ScalarDataPropertyTag
  
  def scalarDataPropertyUUID(uuid: String)
  : ScalarDataPropertyUUID
  = covariantTag[ScalarDataPropertyTag][String](uuid)
  
  implicit val decodeScalarDataPropertyUUID
  : Decoder[ScalarDataPropertyUUID]
  = decodeTag[ScalarDataPropertyTag]
  
  implicit val encodeScalarDataPropertyUUID
  : Encoder[ScalarDataPropertyUUID]
  = encodeTag[ScalarDataPropertyTag]
  
  implicit val orderingScalarDataPropertyUUID
  : Ordering[ScalarDataPropertyUUID] 
  = new Ordering[ScalarDataPropertyUUID] {
  	override def compare
  	(x: ScalarDataPropertyUUID, 
  	 y: ScalarDataPropertyUUID)
  	: Int = x.compareTo(y)
  }
  
  type ScalarDataPropertyValueUUID 
  = String @@ ScalarDataPropertyValueTag
  
  def scalarDataPropertyValueUUID(uuid: String)
  : ScalarDataPropertyValueUUID
  = covariantTag[ScalarDataPropertyValueTag][String](uuid)
  
  implicit val decodeScalarDataPropertyValueUUID
  : Decoder[ScalarDataPropertyValueUUID]
  = decodeTag[ScalarDataPropertyValueTag]
  
  implicit val encodeScalarDataPropertyValueUUID
  : Encoder[ScalarDataPropertyValueUUID]
  = encodeTag[ScalarDataPropertyValueTag]
  
  implicit val orderingScalarDataPropertyValueUUID
  : Ordering[ScalarDataPropertyValueUUID] 
  = new Ordering[ScalarDataPropertyValueUUID] {
  	override def compare
  	(x: ScalarDataPropertyValueUUID, 
  	 y: ScalarDataPropertyValueUUID)
  	: Int = x.compareTo(y)
  }
  
  type ScalarOneOfLiteralAxiomUUID 
  = String @@ ScalarOneOfLiteralAxiomTag
  
  def scalarOneOfLiteralAxiomUUID(uuid: String)
  : ScalarOneOfLiteralAxiomUUID
  = covariantTag[ScalarOneOfLiteralAxiomTag][String](uuid)
  
  implicit val decodeScalarOneOfLiteralAxiomUUID
  : Decoder[ScalarOneOfLiteralAxiomUUID]
  = decodeTag[ScalarOneOfLiteralAxiomTag]
  
  implicit val encodeScalarOneOfLiteralAxiomUUID
  : Encoder[ScalarOneOfLiteralAxiomUUID]
  = encodeTag[ScalarOneOfLiteralAxiomTag]
  
  implicit val orderingScalarOneOfLiteralAxiomUUID
  : Ordering[ScalarOneOfLiteralAxiomUUID] 
  = new Ordering[ScalarOneOfLiteralAxiomUUID] {
  	override def compare
  	(x: ScalarOneOfLiteralAxiomUUID, 
  	 y: ScalarOneOfLiteralAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type ScalarOneOfRestrictionUUID 
  = String @@ ScalarOneOfRestrictionTag
  
  def scalarOneOfRestrictionUUID(uuid: String)
  : ScalarOneOfRestrictionUUID
  = covariantTag[ScalarOneOfRestrictionTag][String](uuid)
  
  implicit val decodeScalarOneOfRestrictionUUID
  : Decoder[ScalarOneOfRestrictionUUID]
  = decodeTag[ScalarOneOfRestrictionTag]
  
  implicit val encodeScalarOneOfRestrictionUUID
  : Encoder[ScalarOneOfRestrictionUUID]
  = encodeTag[ScalarOneOfRestrictionTag]
  
  implicit val orderingScalarOneOfRestrictionUUID
  : Ordering[ScalarOneOfRestrictionUUID] 
  = new Ordering[ScalarOneOfRestrictionUUID] {
  	override def compare
  	(x: ScalarOneOfRestrictionUUID, 
  	 y: ScalarOneOfRestrictionUUID)
  	: Int = x.compareTo(y)
  }
  
  type SegmentPredicateUUID 
  = String @@ SegmentPredicateTag
  
  def segmentPredicateUUID(uuid: String)
  : SegmentPredicateUUID
  = covariantTag[SegmentPredicateTag][String](uuid)
  
  implicit val decodeSegmentPredicateUUID
  : Decoder[SegmentPredicateUUID]
  = decodeTag[SegmentPredicateTag]
  
  implicit val encodeSegmentPredicateUUID
  : Encoder[SegmentPredicateUUID]
  = encodeTag[SegmentPredicateTag]
  
  implicit val orderingSegmentPredicateUUID
  : Ordering[SegmentPredicateUUID] 
  = new Ordering[SegmentPredicateUUID] {
  	override def compare
  	(x: SegmentPredicateUUID, 
  	 y: SegmentPredicateUUID)
  	: Int = x.compareTo(y)
  }
  
  type SingletonInstanceScalarDataPropertyValueUUID 
  = String @@ SingletonInstanceScalarDataPropertyValueTag
  
  def singletonInstanceScalarDataPropertyValueUUID(uuid: String)
  : SingletonInstanceScalarDataPropertyValueUUID
  = covariantTag[SingletonInstanceScalarDataPropertyValueTag][String](uuid)
  
  implicit val decodeSingletonInstanceScalarDataPropertyValueUUID
  : Decoder[SingletonInstanceScalarDataPropertyValueUUID]
  = decodeTag[SingletonInstanceScalarDataPropertyValueTag]
  
  implicit val encodeSingletonInstanceScalarDataPropertyValueUUID
  : Encoder[SingletonInstanceScalarDataPropertyValueUUID]
  = encodeTag[SingletonInstanceScalarDataPropertyValueTag]
  
  implicit val orderingSingletonInstanceScalarDataPropertyValueUUID
  : Ordering[SingletonInstanceScalarDataPropertyValueUUID] 
  = new Ordering[SingletonInstanceScalarDataPropertyValueUUID] {
  	override def compare
  	(x: SingletonInstanceScalarDataPropertyValueUUID, 
  	 y: SingletonInstanceScalarDataPropertyValueUUID)
  	: Int = x.compareTo(y)
  }
  
  type SingletonInstanceStructuredDataPropertyContextUUID 
  = String @@ SingletonInstanceStructuredDataPropertyContextTag
  
  def singletonInstanceStructuredDataPropertyContextUUID(uuid: String)
  : SingletonInstanceStructuredDataPropertyContextUUID
  = covariantTag[SingletonInstanceStructuredDataPropertyContextTag][String](uuid)
  
  implicit val decodeSingletonInstanceStructuredDataPropertyContextUUID
  : Decoder[SingletonInstanceStructuredDataPropertyContextUUID]
  = decodeTag[SingletonInstanceStructuredDataPropertyContextTag]
  
  implicit val encodeSingletonInstanceStructuredDataPropertyContextUUID
  : Encoder[SingletonInstanceStructuredDataPropertyContextUUID]
  = encodeTag[SingletonInstanceStructuredDataPropertyContextTag]
  
  implicit val orderingSingletonInstanceStructuredDataPropertyContextUUID
  : Ordering[SingletonInstanceStructuredDataPropertyContextUUID] 
  = new Ordering[SingletonInstanceStructuredDataPropertyContextUUID] {
  	override def compare
  	(x: SingletonInstanceStructuredDataPropertyContextUUID, 
  	 y: SingletonInstanceStructuredDataPropertyContextUUID)
  	: Int = x.compareTo(y)
  }
  
  type SingletonInstanceStructuredDataPropertyValueUUID 
  = String @@ SingletonInstanceStructuredDataPropertyValueTag
  
  def singletonInstanceStructuredDataPropertyValueUUID(uuid: String)
  : SingletonInstanceStructuredDataPropertyValueUUID
  = covariantTag[SingletonInstanceStructuredDataPropertyValueTag][String](uuid)
  
  implicit val decodeSingletonInstanceStructuredDataPropertyValueUUID
  : Decoder[SingletonInstanceStructuredDataPropertyValueUUID]
  = decodeTag[SingletonInstanceStructuredDataPropertyValueTag]
  
  implicit val encodeSingletonInstanceStructuredDataPropertyValueUUID
  : Encoder[SingletonInstanceStructuredDataPropertyValueUUID]
  = encodeTag[SingletonInstanceStructuredDataPropertyValueTag]
  
  implicit val orderingSingletonInstanceStructuredDataPropertyValueUUID
  : Ordering[SingletonInstanceStructuredDataPropertyValueUUID] 
  = new Ordering[SingletonInstanceStructuredDataPropertyValueUUID] {
  	override def compare
  	(x: SingletonInstanceStructuredDataPropertyValueUUID, 
  	 y: SingletonInstanceStructuredDataPropertyValueUUID)
  	: Int = x.compareTo(y)
  }
  
  type SpecializationAxiomUUID 
  = String @@ SpecializationAxiomTag
  
  def specializationAxiomUUID(uuid: String)
  : SpecializationAxiomUUID
  = covariantTag[SpecializationAxiomTag][String](uuid)
  
  implicit val decodeSpecializationAxiomUUID
  : Decoder[SpecializationAxiomUUID]
  = decodeTag[SpecializationAxiomTag]
  
  implicit val encodeSpecializationAxiomUUID
  : Encoder[SpecializationAxiomUUID]
  = encodeTag[SpecializationAxiomTag]
  
  implicit val orderingSpecializationAxiomUUID
  : Ordering[SpecializationAxiomUUID] 
  = new Ordering[SpecializationAxiomUUID] {
  	override def compare
  	(x: SpecializationAxiomUUID, 
  	 y: SpecializationAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type SpecializedReifiedRelationshipUUID 
  = String @@ SpecializedReifiedRelationshipTag
  
  def specializedReifiedRelationshipUUID(uuid: String)
  : SpecializedReifiedRelationshipUUID
  = covariantTag[SpecializedReifiedRelationshipTag][String](uuid)
  
  implicit val decodeSpecializedReifiedRelationshipUUID
  : Decoder[SpecializedReifiedRelationshipUUID]
  = decodeTag[SpecializedReifiedRelationshipTag]
  
  implicit val encodeSpecializedReifiedRelationshipUUID
  : Encoder[SpecializedReifiedRelationshipUUID]
  = encodeTag[SpecializedReifiedRelationshipTag]
  
  implicit val orderingSpecializedReifiedRelationshipUUID
  : Ordering[SpecializedReifiedRelationshipUUID] 
  = new Ordering[SpecializedReifiedRelationshipUUID] {
  	override def compare
  	(x: SpecializedReifiedRelationshipUUID, 
  	 y: SpecializedReifiedRelationshipUUID)
  	: Int = x.compareTo(y)
  }
  
  type SpecificDisjointConceptAxiomUUID 
  = String @@ SpecificDisjointConceptAxiomTag
  
  def specificDisjointConceptAxiomUUID(uuid: String)
  : SpecificDisjointConceptAxiomUUID
  = covariantTag[SpecificDisjointConceptAxiomTag][String](uuid)
  
  implicit val decodeSpecificDisjointConceptAxiomUUID
  : Decoder[SpecificDisjointConceptAxiomUUID]
  = decodeTag[SpecificDisjointConceptAxiomTag]
  
  implicit val encodeSpecificDisjointConceptAxiomUUID
  : Encoder[SpecificDisjointConceptAxiomUUID]
  = encodeTag[SpecificDisjointConceptAxiomTag]
  
  implicit val orderingSpecificDisjointConceptAxiomUUID
  : Ordering[SpecificDisjointConceptAxiomUUID] 
  = new Ordering[SpecificDisjointConceptAxiomUUID] {
  	override def compare
  	(x: SpecificDisjointConceptAxiomUUID, 
  	 y: SpecificDisjointConceptAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type StringScalarRestrictionUUID 
  = String @@ StringScalarRestrictionTag
  
  def stringScalarRestrictionUUID(uuid: String)
  : StringScalarRestrictionUUID
  = covariantTag[StringScalarRestrictionTag][String](uuid)
  
  implicit val decodeStringScalarRestrictionUUID
  : Decoder[StringScalarRestrictionUUID]
  = decodeTag[StringScalarRestrictionTag]
  
  implicit val encodeStringScalarRestrictionUUID
  : Encoder[StringScalarRestrictionUUID]
  = encodeTag[StringScalarRestrictionTag]
  
  implicit val orderingStringScalarRestrictionUUID
  : Ordering[StringScalarRestrictionUUID] 
  = new Ordering[StringScalarRestrictionUUID] {
  	override def compare
  	(x: StringScalarRestrictionUUID, 
  	 y: StringScalarRestrictionUUID)
  	: Int = x.compareTo(y)
  }
  
  type StructureUUID 
  = String @@ StructureTag
  
  def structureUUID(uuid: String)
  : StructureUUID
  = covariantTag[StructureTag][String](uuid)
  
  implicit val decodeStructureUUID
  : Decoder[StructureUUID]
  = decodeTag[StructureTag]
  
  implicit val encodeStructureUUID
  : Encoder[StructureUUID]
  = encodeTag[StructureTag]
  
  implicit val orderingStructureUUID
  : Ordering[StructureUUID] 
  = new Ordering[StructureUUID] {
  	override def compare
  	(x: StructureUUID, 
  	 y: StructureUUID)
  	: Int = x.compareTo(y)
  }
  
  type StructuredDataPropertyUUID 
  = String @@ StructuredDataPropertyTag
  
  def structuredDataPropertyUUID(uuid: String)
  : StructuredDataPropertyUUID
  = covariantTag[StructuredDataPropertyTag][String](uuid)
  
  implicit val decodeStructuredDataPropertyUUID
  : Decoder[StructuredDataPropertyUUID]
  = decodeTag[StructuredDataPropertyTag]
  
  implicit val encodeStructuredDataPropertyUUID
  : Encoder[StructuredDataPropertyUUID]
  = encodeTag[StructuredDataPropertyTag]
  
  implicit val orderingStructuredDataPropertyUUID
  : Ordering[StructuredDataPropertyUUID] 
  = new Ordering[StructuredDataPropertyUUID] {
  	override def compare
  	(x: StructuredDataPropertyUUID, 
  	 y: StructuredDataPropertyUUID)
  	: Int = x.compareTo(y)
  }
  
  type StructuredDataPropertyTupleUUID 
  = String @@ StructuredDataPropertyTupleTag
  
  def structuredDataPropertyTupleUUID(uuid: String)
  : StructuredDataPropertyTupleUUID
  = covariantTag[StructuredDataPropertyTupleTag][String](uuid)
  
  implicit val decodeStructuredDataPropertyTupleUUID
  : Decoder[StructuredDataPropertyTupleUUID]
  = decodeTag[StructuredDataPropertyTupleTag]
  
  implicit val encodeStructuredDataPropertyTupleUUID
  : Encoder[StructuredDataPropertyTupleUUID]
  = encodeTag[StructuredDataPropertyTupleTag]
  
  implicit val orderingStructuredDataPropertyTupleUUID
  : Ordering[StructuredDataPropertyTupleUUID] 
  = new Ordering[StructuredDataPropertyTupleUUID] {
  	override def compare
  	(x: StructuredDataPropertyTupleUUID, 
  	 y: StructuredDataPropertyTupleUUID)
  	: Int = x.compareTo(y)
  }
  
  type SubDataPropertyOfAxiomUUID 
  = String @@ SubDataPropertyOfAxiomTag
  
  def subDataPropertyOfAxiomUUID(uuid: String)
  : SubDataPropertyOfAxiomUUID
  = covariantTag[SubDataPropertyOfAxiomTag][String](uuid)
  
  implicit val decodeSubDataPropertyOfAxiomUUID
  : Decoder[SubDataPropertyOfAxiomUUID]
  = decodeTag[SubDataPropertyOfAxiomTag]
  
  implicit val encodeSubDataPropertyOfAxiomUUID
  : Encoder[SubDataPropertyOfAxiomUUID]
  = encodeTag[SubDataPropertyOfAxiomTag]
  
  implicit val orderingSubDataPropertyOfAxiomUUID
  : Ordering[SubDataPropertyOfAxiomUUID] 
  = new Ordering[SubDataPropertyOfAxiomUUID] {
  	override def compare
  	(x: SubDataPropertyOfAxiomUUID, 
  	 y: SubDataPropertyOfAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type SubObjectPropertyOfAxiomUUID 
  = String @@ SubObjectPropertyOfAxiomTag
  
  def subObjectPropertyOfAxiomUUID(uuid: String)
  : SubObjectPropertyOfAxiomUUID
  = covariantTag[SubObjectPropertyOfAxiomTag][String](uuid)
  
  implicit val decodeSubObjectPropertyOfAxiomUUID
  : Decoder[SubObjectPropertyOfAxiomUUID]
  = decodeTag[SubObjectPropertyOfAxiomTag]
  
  implicit val encodeSubObjectPropertyOfAxiomUUID
  : Encoder[SubObjectPropertyOfAxiomUUID]
  = encodeTag[SubObjectPropertyOfAxiomTag]
  
  implicit val orderingSubObjectPropertyOfAxiomUUID
  : Ordering[SubObjectPropertyOfAxiomUUID] 
  = new Ordering[SubObjectPropertyOfAxiomUUID] {
  	override def compare
  	(x: SubObjectPropertyOfAxiomUUID, 
  	 y: SubObjectPropertyOfAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type SynonymScalarRestrictionUUID 
  = String @@ SynonymScalarRestrictionTag
  
  def synonymScalarRestrictionUUID(uuid: String)
  : SynonymScalarRestrictionUUID
  = covariantTag[SynonymScalarRestrictionTag][String](uuid)
  
  implicit val decodeSynonymScalarRestrictionUUID
  : Decoder[SynonymScalarRestrictionUUID]
  = decodeTag[SynonymScalarRestrictionTag]
  
  implicit val encodeSynonymScalarRestrictionUUID
  : Encoder[SynonymScalarRestrictionUUID]
  = encodeTag[SynonymScalarRestrictionTag]
  
  implicit val orderingSynonymScalarRestrictionUUID
  : Ordering[SynonymScalarRestrictionUUID] 
  = new Ordering[SynonymScalarRestrictionUUID] {
  	override def compare
  	(x: SynonymScalarRestrictionUUID, 
  	 y: SynonymScalarRestrictionUUID)
  	: Int = x.compareTo(y)
  }
  
  type TermUUID 
  = String @@ TermTag
  
  def termUUID(uuid: String)
  : TermUUID
  = covariantTag[TermTag][String](uuid)
  
  implicit val decodeTermUUID
  : Decoder[TermUUID]
  = decodeTag[TermTag]
  
  implicit val encodeTermUUID
  : Encoder[TermUUID]
  = encodeTag[TermTag]
  
  implicit val orderingTermUUID
  : Ordering[TermUUID] 
  = new Ordering[TermUUID] {
  	override def compare
  	(x: TermUUID, 
  	 y: TermUUID)
  	: Int = x.compareTo(y)
  }
  
  type TermAxiomUUID 
  = String @@ TermAxiomTag
  
  def termAxiomUUID(uuid: String)
  : TermAxiomUUID
  = covariantTag[TermAxiomTag][String](uuid)
  
  implicit val decodeTermAxiomUUID
  : Decoder[TermAxiomUUID]
  = decodeTag[TermAxiomTag]
  
  implicit val encodeTermAxiomUUID
  : Encoder[TermAxiomUUID]
  = encodeTag[TermAxiomTag]
  
  implicit val orderingTermAxiomUUID
  : Ordering[TermAxiomUUID] 
  = new Ordering[TermAxiomUUID] {
  	override def compare
  	(x: TermAxiomUUID, 
  	 y: TermAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type TerminologyAxiomUUID 
  = String @@ TerminologyAxiomTag
  
  def terminologyAxiomUUID(uuid: String)
  : TerminologyAxiomUUID
  = covariantTag[TerminologyAxiomTag][String](uuid)
  
  implicit val decodeTerminologyAxiomUUID
  : Decoder[TerminologyAxiomUUID]
  = decodeTag[TerminologyAxiomTag]
  
  implicit val encodeTerminologyAxiomUUID
  : Encoder[TerminologyAxiomUUID]
  = encodeTag[TerminologyAxiomTag]
  
  implicit val orderingTerminologyAxiomUUID
  : Ordering[TerminologyAxiomUUID] 
  = new Ordering[TerminologyAxiomUUID] {
  	override def compare
  	(x: TerminologyAxiomUUID, 
  	 y: TerminologyAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type TerminologyBoxUUID 
  = String @@ TerminologyBoxTag
  
  def terminologyBoxUUID(uuid: String)
  : TerminologyBoxUUID
  = covariantTag[TerminologyBoxTag][String](uuid)
  
  implicit val decodeTerminologyBoxUUID
  : Decoder[TerminologyBoxUUID]
  = decodeTag[TerminologyBoxTag]
  
  implicit val encodeTerminologyBoxUUID
  : Encoder[TerminologyBoxUUID]
  = encodeTag[TerminologyBoxTag]
  
  implicit val orderingTerminologyBoxUUID
  : Ordering[TerminologyBoxUUID] 
  = new Ordering[TerminologyBoxUUID] {
  	override def compare
  	(x: TerminologyBoxUUID, 
  	 y: TerminologyBoxUUID)
  	: Int = x.compareTo(y)
  }
  
  type TerminologyBoxAxiomUUID 
  = String @@ TerminologyBoxAxiomTag
  
  def terminologyBoxAxiomUUID(uuid: String)
  : TerminologyBoxAxiomUUID
  = covariantTag[TerminologyBoxAxiomTag][String](uuid)
  
  implicit val decodeTerminologyBoxAxiomUUID
  : Decoder[TerminologyBoxAxiomUUID]
  = decodeTag[TerminologyBoxAxiomTag]
  
  implicit val encodeTerminologyBoxAxiomUUID
  : Encoder[TerminologyBoxAxiomUUID]
  = encodeTag[TerminologyBoxAxiomTag]
  
  implicit val orderingTerminologyBoxAxiomUUID
  : Ordering[TerminologyBoxAxiomUUID] 
  = new Ordering[TerminologyBoxAxiomUUID] {
  	override def compare
  	(x: TerminologyBoxAxiomUUID, 
  	 y: TerminologyBoxAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type TerminologyBoxStatementUUID 
  = String @@ TerminologyBoxStatementTag
  
  def terminologyBoxStatementUUID(uuid: String)
  : TerminologyBoxStatementUUID
  = covariantTag[TerminologyBoxStatementTag][String](uuid)
  
  implicit val decodeTerminologyBoxStatementUUID
  : Decoder[TerminologyBoxStatementUUID]
  = decodeTag[TerminologyBoxStatementTag]
  
  implicit val encodeTerminologyBoxStatementUUID
  : Encoder[TerminologyBoxStatementUUID]
  = encodeTag[TerminologyBoxStatementTag]
  
  implicit val orderingTerminologyBoxStatementUUID
  : Ordering[TerminologyBoxStatementUUID] 
  = new Ordering[TerminologyBoxStatementUUID] {
  	override def compare
  	(x: TerminologyBoxStatementUUID, 
  	 y: TerminologyBoxStatementUUID)
  	: Int = x.compareTo(y)
  }
  
  type TerminologyBundleAxiomUUID 
  = String @@ TerminologyBundleAxiomTag
  
  def terminologyBundleAxiomUUID(uuid: String)
  : TerminologyBundleAxiomUUID
  = covariantTag[TerminologyBundleAxiomTag][String](uuid)
  
  implicit val decodeTerminologyBundleAxiomUUID
  : Decoder[TerminologyBundleAxiomUUID]
  = decodeTag[TerminologyBundleAxiomTag]
  
  implicit val encodeTerminologyBundleAxiomUUID
  : Encoder[TerminologyBundleAxiomUUID]
  = encodeTag[TerminologyBundleAxiomTag]
  
  implicit val orderingTerminologyBundleAxiomUUID
  : Ordering[TerminologyBundleAxiomUUID] 
  = new Ordering[TerminologyBundleAxiomUUID] {
  	override def compare
  	(x: TerminologyBundleAxiomUUID, 
  	 y: TerminologyBundleAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type TerminologyBundleStatementUUID 
  = String @@ TerminologyBundleStatementTag
  
  def terminologyBundleStatementUUID(uuid: String)
  : TerminologyBundleStatementUUID
  = covariantTag[TerminologyBundleStatementTag][String](uuid)
  
  implicit val decodeTerminologyBundleStatementUUID
  : Decoder[TerminologyBundleStatementUUID]
  = decodeTag[TerminologyBundleStatementTag]
  
  implicit val encodeTerminologyBundleStatementUUID
  : Encoder[TerminologyBundleStatementUUID]
  = encodeTag[TerminologyBundleStatementTag]
  
  implicit val orderingTerminologyBundleStatementUUID
  : Ordering[TerminologyBundleStatementUUID] 
  = new Ordering[TerminologyBundleStatementUUID] {
  	override def compare
  	(x: TerminologyBundleStatementUUID, 
  	 y: TerminologyBundleStatementUUID)
  	: Int = x.compareTo(y)
  }
  
  type TerminologyExtensionAxiomUUID 
  = String @@ TerminologyExtensionAxiomTag
  
  def terminologyExtensionAxiomUUID(uuid: String)
  : TerminologyExtensionAxiomUUID
  = covariantTag[TerminologyExtensionAxiomTag][String](uuid)
  
  implicit val decodeTerminologyExtensionAxiomUUID
  : Decoder[TerminologyExtensionAxiomUUID]
  = decodeTag[TerminologyExtensionAxiomTag]
  
  implicit val encodeTerminologyExtensionAxiomUUID
  : Encoder[TerminologyExtensionAxiomUUID]
  = encodeTag[TerminologyExtensionAxiomTag]
  
  implicit val orderingTerminologyExtensionAxiomUUID
  : Ordering[TerminologyExtensionAxiomUUID] 
  = new Ordering[TerminologyExtensionAxiomUUID] {
  	override def compare
  	(x: TerminologyExtensionAxiomUUID, 
  	 y: TerminologyExtensionAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type TerminologyGraphUUID 
  = String @@ TerminologyGraphTag
  
  def terminologyGraphUUID(uuid: String)
  : TerminologyGraphUUID
  = covariantTag[TerminologyGraphTag][String](uuid)
  
  implicit val decodeTerminologyGraphUUID
  : Decoder[TerminologyGraphUUID]
  = decodeTag[TerminologyGraphTag]
  
  implicit val encodeTerminologyGraphUUID
  : Encoder[TerminologyGraphUUID]
  = encodeTag[TerminologyGraphTag]
  
  implicit val orderingTerminologyGraphUUID
  : Ordering[TerminologyGraphUUID] 
  = new Ordering[TerminologyGraphUUID] {
  	override def compare
  	(x: TerminologyGraphUUID, 
  	 y: TerminologyGraphUUID)
  	: Int = x.compareTo(y)
  }
  
  type TerminologyInstanceAssertionUUID 
  = String @@ TerminologyInstanceAssertionTag
  
  def terminologyInstanceAssertionUUID(uuid: String)
  : TerminologyInstanceAssertionUUID
  = covariantTag[TerminologyInstanceAssertionTag][String](uuid)
  
  implicit val decodeTerminologyInstanceAssertionUUID
  : Decoder[TerminologyInstanceAssertionUUID]
  = decodeTag[TerminologyInstanceAssertionTag]
  
  implicit val encodeTerminologyInstanceAssertionUUID
  : Encoder[TerminologyInstanceAssertionUUID]
  = encodeTag[TerminologyInstanceAssertionTag]
  
  implicit val orderingTerminologyInstanceAssertionUUID
  : Ordering[TerminologyInstanceAssertionUUID] 
  = new Ordering[TerminologyInstanceAssertionUUID] {
  	override def compare
  	(x: TerminologyInstanceAssertionUUID, 
  	 y: TerminologyInstanceAssertionUUID)
  	: Int = x.compareTo(y)
  }
  
  type TerminologyNestingAxiomUUID 
  = String @@ TerminologyNestingAxiomTag
  
  def terminologyNestingAxiomUUID(uuid: String)
  : TerminologyNestingAxiomUUID
  = covariantTag[TerminologyNestingAxiomTag][String](uuid)
  
  implicit val decodeTerminologyNestingAxiomUUID
  : Decoder[TerminologyNestingAxiomUUID]
  = decodeTag[TerminologyNestingAxiomTag]
  
  implicit val encodeTerminologyNestingAxiomUUID
  : Encoder[TerminologyNestingAxiomUUID]
  = encodeTag[TerminologyNestingAxiomTag]
  
  implicit val orderingTerminologyNestingAxiomUUID
  : Ordering[TerminologyNestingAxiomUUID] 
  = new Ordering[TerminologyNestingAxiomUUID] {
  	override def compare
  	(x: TerminologyNestingAxiomUUID, 
  	 y: TerminologyNestingAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type TimeScalarRestrictionUUID 
  = String @@ TimeScalarRestrictionTag
  
  def timeScalarRestrictionUUID(uuid: String)
  : TimeScalarRestrictionUUID
  = covariantTag[TimeScalarRestrictionTag][String](uuid)
  
  implicit val decodeTimeScalarRestrictionUUID
  : Decoder[TimeScalarRestrictionUUID]
  = decodeTag[TimeScalarRestrictionTag]
  
  implicit val encodeTimeScalarRestrictionUUID
  : Encoder[TimeScalarRestrictionUUID]
  = encodeTag[TimeScalarRestrictionTag]
  
  implicit val orderingTimeScalarRestrictionUUID
  : Ordering[TimeScalarRestrictionUUID] 
  = new Ordering[TimeScalarRestrictionUUID] {
  	override def compare
  	(x: TimeScalarRestrictionUUID, 
  	 y: TimeScalarRestrictionUUID)
  	: Int = x.compareTo(y)
  }
  
  type UnaryTermKindUUID 
  = String @@ UnaryTermKindTag
  
  def unaryTermKindUUID(uuid: String)
  : UnaryTermKindUUID
  = covariantTag[UnaryTermKindTag][String](uuid)
  
  implicit val decodeUnaryTermKindUUID
  : Decoder[UnaryTermKindUUID]
  = decodeTag[UnaryTermKindTag]
  
  implicit val encodeUnaryTermKindUUID
  : Encoder[UnaryTermKindUUID]
  = encodeTag[UnaryTermKindTag]
  
  implicit val orderingUnaryTermKindUUID
  : Ordering[UnaryTermKindUUID] 
  = new Ordering[UnaryTermKindUUID] {
  	override def compare
  	(x: UnaryTermKindUUID, 
  	 y: UnaryTermKindUUID)
  	: Int = x.compareTo(y)
  }
  
  type UnreifiedRelationshipUUID 
  = String @@ UnreifiedRelationshipTag
  
  def unreifiedRelationshipUUID(uuid: String)
  : UnreifiedRelationshipUUID
  = covariantTag[UnreifiedRelationshipTag][String](uuid)
  
  implicit val decodeUnreifiedRelationshipUUID
  : Decoder[UnreifiedRelationshipUUID]
  = decodeTag[UnreifiedRelationshipTag]
  
  implicit val encodeUnreifiedRelationshipUUID
  : Encoder[UnreifiedRelationshipUUID]
  = encodeTag[UnreifiedRelationshipTag]
  
  implicit val orderingUnreifiedRelationshipUUID
  : Ordering[UnreifiedRelationshipUUID] 
  = new Ordering[UnreifiedRelationshipUUID] {
  	override def compare
  	(x: UnreifiedRelationshipUUID, 
  	 y: UnreifiedRelationshipUUID)
  	: Int = x.compareTo(y)
  }
  
  type UnreifiedRelationshipInstanceTupleUUID 
  = String @@ UnreifiedRelationshipInstanceTupleTag
  
  def unreifiedRelationshipInstanceTupleUUID(uuid: String)
  : UnreifiedRelationshipInstanceTupleUUID
  = covariantTag[UnreifiedRelationshipInstanceTupleTag][String](uuid)
  
  implicit val decodeUnreifiedRelationshipInstanceTupleUUID
  : Decoder[UnreifiedRelationshipInstanceTupleUUID]
  = decodeTag[UnreifiedRelationshipInstanceTupleTag]
  
  implicit val encodeUnreifiedRelationshipInstanceTupleUUID
  : Encoder[UnreifiedRelationshipInstanceTupleUUID]
  = encodeTag[UnreifiedRelationshipInstanceTupleTag]
  
  implicit val orderingUnreifiedRelationshipInstanceTupleUUID
  : Ordering[UnreifiedRelationshipInstanceTupleUUID] 
  = new Ordering[UnreifiedRelationshipInstanceTupleUUID] {
  	override def compare
  	(x: UnreifiedRelationshipInstanceTupleUUID, 
  	 y: UnreifiedRelationshipInstanceTupleUUID)
  	: Int = x.compareTo(y)
  }
  
  type ValueCrossReferenceTupleUUID 
  = String @@ ValueCrossReferenceTupleTag
  
  def valueCrossReferenceTupleUUID(uuid: String)
  : ValueCrossReferenceTupleUUID
  = covariantTag[ValueCrossReferenceTupleTag][String](uuid)
  
  implicit val decodeValueCrossReferenceTupleUUID
  : Decoder[ValueCrossReferenceTupleUUID]
  = decodeTag[ValueCrossReferenceTupleTag]
  
  implicit val encodeValueCrossReferenceTupleUUID
  : Encoder[ValueCrossReferenceTupleUUID]
  = encodeTag[ValueCrossReferenceTupleTag]
  
  implicit val orderingValueCrossReferenceTupleUUID
  : Ordering[ValueCrossReferenceTupleUUID] 
  = new Ordering[ValueCrossReferenceTupleUUID] {
  	override def compare
  	(x: ValueCrossReferenceTupleUUID, 
  	 y: ValueCrossReferenceTupleUUID)
  	: Int = x.compareTo(y)
  }
  
}
