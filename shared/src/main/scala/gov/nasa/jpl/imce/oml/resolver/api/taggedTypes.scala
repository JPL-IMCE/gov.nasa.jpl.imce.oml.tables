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

import java.util.UUID

import gov.nasa.jpl.imce.oml.covariantTag
import gov.nasa.jpl.imce.oml.covariantTag.@@

import io.circe.{HCursor,Json}
import io.circe.{Decoder,Encoder}
import scala.{Left,Right}

object taggedTypes {

  implicit def decodeTag[Tag]: Decoder[UUID @@ Tag] = new Decoder[UUID @@ Tag] {
    final def apply(c: HCursor): Decoder.Result[UUID @@ Tag] = c.value.as[UUID] match {
      case Right(uuid) => Right(covariantTag[Tag][UUID](uuid))
      case Left(failure) => Left(failure)
    }
  }

  implicit def encodeTag[Tag]: Encoder[UUID @@ Tag] = new Encoder[UUID @@ Tag] {
    final def apply(s: UUID @@ Tag): Json = Json.fromString(s.toString)
  }
  
  def fromUUIDString[Tag](uuid: scala.Predef.String @@ Tag)
  : UUID @@ Tag 
  = covariantTag[Tag][UUID](UUID.fromString(uuid))
  
  type AnnotationPropertyUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.AnnotationPropertyTag
  
  def annotationPropertyUUID(uuid: UUID): AnnotationPropertyUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AnnotationPropertyTag][UUID](uuid)
  
  implicit val decodeAnnotationPropertyUUID: Decoder[AnnotationPropertyUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AnnotationPropertyTag]
  
  implicit val encodeAnnotationPropertyUUID: Encoder[AnnotationPropertyUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AnnotationPropertyTag]
  
  type AnnotationPropertyValueUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.AnnotationPropertyValueTag
  
  def annotationPropertyValueUUID(uuid: UUID): AnnotationPropertyValueUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AnnotationPropertyValueTag][UUID](uuid)
  
  implicit val decodeAnnotationPropertyValueUUID: Decoder[AnnotationPropertyValueUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AnnotationPropertyValueTag]
  
  implicit val encodeAnnotationPropertyValueUUID: Encoder[AnnotationPropertyValueUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AnnotationPropertyValueTag]
  
  type AnonymousConceptUnionAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.AnonymousConceptUnionAxiomTag
  
  def anonymousConceptUnionAxiomUUID(uuid: UUID): AnonymousConceptUnionAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AnonymousConceptUnionAxiomTag][UUID](uuid)
  
  implicit val decodeAnonymousConceptUnionAxiomUUID: Decoder[AnonymousConceptUnionAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AnonymousConceptUnionAxiomTag]
  
  implicit val encodeAnonymousConceptUnionAxiomUUID: Encoder[AnonymousConceptUnionAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AnonymousConceptUnionAxiomTag]
  
  type AspectUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectTag
  
  def aspectUUID(uuid: UUID): AspectUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectTag][UUID](uuid)
  
  implicit val decodeAspectUUID: Decoder[AspectUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectTag]
  
  implicit val encodeAspectUUID: Encoder[AspectUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectTag]
  
  type AspectPredicateUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectPredicateTag
  
  def aspectPredicateUUID(uuid: UUID): AspectPredicateUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectPredicateTag][UUID](uuid)
  
  implicit val decodeAspectPredicateUUID: Decoder[AspectPredicateUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectPredicateTag]
  
  implicit val encodeAspectPredicateUUID: Encoder[AspectPredicateUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectPredicateTag]
  
  type AspectSpecializationAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectSpecializationAxiomTag
  
  def aspectSpecializationAxiomUUID(uuid: UUID): AspectSpecializationAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectSpecializationAxiomTag][UUID](uuid)
  
  implicit val decodeAspectSpecializationAxiomUUID: Decoder[AspectSpecializationAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectSpecializationAxiomTag]
  
  implicit val encodeAspectSpecializationAxiomUUID: Encoder[AspectSpecializationAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectSpecializationAxiomTag]
  
  type BinaryScalarRestrictionUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.BinaryScalarRestrictionTag
  
  def binaryScalarRestrictionUUID(uuid: UUID): BinaryScalarRestrictionUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BinaryScalarRestrictionTag][UUID](uuid)
  
  implicit val decodeBinaryScalarRestrictionUUID: Decoder[BinaryScalarRestrictionUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BinaryScalarRestrictionTag]
  
  implicit val encodeBinaryScalarRestrictionUUID: Encoder[BinaryScalarRestrictionUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BinaryScalarRestrictionTag]
  
  type BinarySegmentForwardPropertyPredicateUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.BinarySegmentForwardPropertyPredicateTag
  
  def binarySegmentForwardPropertyPredicateUUID(uuid: UUID): BinarySegmentForwardPropertyPredicateUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BinarySegmentForwardPropertyPredicateTag][UUID](uuid)
  
  implicit val decodeBinarySegmentForwardPropertyPredicateUUID: Decoder[BinarySegmentForwardPropertyPredicateUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BinarySegmentForwardPropertyPredicateTag]
  
  implicit val encodeBinarySegmentForwardPropertyPredicateUUID: Encoder[BinarySegmentForwardPropertyPredicateUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BinarySegmentForwardPropertyPredicateTag]
  
  type BinarySegmentPropertyPredicateUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.BinarySegmentPropertyPredicateTag
  
  def binarySegmentPropertyPredicateUUID(uuid: UUID): BinarySegmentPropertyPredicateUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BinarySegmentPropertyPredicateTag][UUID](uuid)
  
  implicit val decodeBinarySegmentPropertyPredicateUUID: Decoder[BinarySegmentPropertyPredicateUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BinarySegmentPropertyPredicateTag]
  
  implicit val encodeBinarySegmentPropertyPredicateUUID: Encoder[BinarySegmentPropertyPredicateUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BinarySegmentPropertyPredicateTag]
  
  type BinarySegmentReversePropertyPredicateUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.BinarySegmentReversePropertyPredicateTag
  
  def binarySegmentReversePropertyPredicateUUID(uuid: UUID): BinarySegmentReversePropertyPredicateUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BinarySegmentReversePropertyPredicateTag][UUID](uuid)
  
  implicit val decodeBinarySegmentReversePropertyPredicateUUID: Decoder[BinarySegmentReversePropertyPredicateUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BinarySegmentReversePropertyPredicateTag]
  
  implicit val encodeBinarySegmentReversePropertyPredicateUUID: Encoder[BinarySegmentReversePropertyPredicateUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BinarySegmentReversePropertyPredicateTag]
  
  type BundleUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.BundleTag
  
  def bundleUUID(uuid: UUID): BundleUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BundleTag][UUID](uuid)
  
  implicit val decodeBundleUUID: Decoder[BundleUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BundleTag]
  
  implicit val encodeBundleUUID: Encoder[BundleUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BundleTag]
  
  type BundledTerminologyAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.BundledTerminologyAxiomTag
  
  def bundledTerminologyAxiomUUID(uuid: UUID): BundledTerminologyAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BundledTerminologyAxiomTag][UUID](uuid)
  
  implicit val decodeBundledTerminologyAxiomUUID: Decoder[BundledTerminologyAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BundledTerminologyAxiomTag]
  
  implicit val encodeBundledTerminologyAxiomUUID: Encoder[BundledTerminologyAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BundledTerminologyAxiomTag]
  
  type ChainRuleUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ChainRuleTag
  
  def chainRuleUUID(uuid: UUID): ChainRuleUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ChainRuleTag][UUID](uuid)
  
  implicit val decodeChainRuleUUID: Decoder[ChainRuleUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ChainRuleTag]
  
  implicit val encodeChainRuleUUID: Encoder[ChainRuleUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ChainRuleTag]
  
  type ConceptUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptTag
  
  def conceptUUID(uuid: UUID): ConceptUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptTag][UUID](uuid)
  
  implicit val decodeConceptUUID: Decoder[ConceptUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptTag]
  
  implicit val encodeConceptUUID: Encoder[ConceptUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptTag]
  
  type ConceptDesignationTerminologyAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptDesignationTerminologyAxiomTag
  
  def conceptDesignationTerminologyAxiomUUID(uuid: UUID): ConceptDesignationTerminologyAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptDesignationTerminologyAxiomTag][UUID](uuid)
  
  implicit val decodeConceptDesignationTerminologyAxiomUUID: Decoder[ConceptDesignationTerminologyAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptDesignationTerminologyAxiomTag]
  
  implicit val encodeConceptDesignationTerminologyAxiomUUID: Encoder[ConceptDesignationTerminologyAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptDesignationTerminologyAxiomTag]
  
  type ConceptInstanceUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptInstanceTag
  
  def conceptInstanceUUID(uuid: UUID): ConceptInstanceUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptInstanceTag][UUID](uuid)
  
  implicit val decodeConceptInstanceUUID: Decoder[ConceptInstanceUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptInstanceTag]
  
  implicit val encodeConceptInstanceUUID: Encoder[ConceptInstanceUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptInstanceTag]
  
  type ConceptPredicateUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptPredicateTag
  
  def conceptPredicateUUID(uuid: UUID): ConceptPredicateUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptPredicateTag][UUID](uuid)
  
  implicit val decodeConceptPredicateUUID: Decoder[ConceptPredicateUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptPredicateTag]
  
  implicit val encodeConceptPredicateUUID: Encoder[ConceptPredicateUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptPredicateTag]
  
  type ConceptSpecializationAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptSpecializationAxiomTag
  
  def conceptSpecializationAxiomUUID(uuid: UUID): ConceptSpecializationAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptSpecializationAxiomTag][UUID](uuid)
  
  implicit val decodeConceptSpecializationAxiomUUID: Decoder[ConceptSpecializationAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptSpecializationAxiomTag]
  
  implicit val encodeConceptSpecializationAxiomUUID: Encoder[ConceptSpecializationAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptSpecializationAxiomTag]
  
  type ConceptTreeDisjunctionUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptTreeDisjunctionTag
  
  def conceptTreeDisjunctionUUID(uuid: UUID): ConceptTreeDisjunctionUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptTreeDisjunctionTag][UUID](uuid)
  
  implicit val decodeConceptTreeDisjunctionUUID: Decoder[ConceptTreeDisjunctionUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptTreeDisjunctionTag]
  
  implicit val encodeConceptTreeDisjunctionUUID: Encoder[ConceptTreeDisjunctionUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptTreeDisjunctionTag]
  
  type ConceptualEntityUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptualEntityTag
  
  def conceptualEntityUUID(uuid: UUID): ConceptualEntityUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptualEntityTag][UUID](uuid)
  
  implicit val decodeConceptualEntityUUID: Decoder[ConceptualEntityUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptualEntityTag]
  
  implicit val encodeConceptualEntityUUID: Encoder[ConceptualEntityUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptualEntityTag]
  
  type ConceptualEntitySingletonInstanceUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptualEntitySingletonInstanceTag
  
  def conceptualEntitySingletonInstanceUUID(uuid: UUID): ConceptualEntitySingletonInstanceUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptualEntitySingletonInstanceTag][UUID](uuid)
  
  implicit val decodeConceptualEntitySingletonInstanceUUID: Decoder[ConceptualEntitySingletonInstanceUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptualEntitySingletonInstanceTag]
  
  implicit val encodeConceptualEntitySingletonInstanceUUID: Encoder[ConceptualEntitySingletonInstanceUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptualEntitySingletonInstanceTag]
  
  type DataRangeUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRangeTag
  
  def dataRangeUUID(uuid: UUID): DataRangeUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRangeTag][UUID](uuid)
  
  implicit val decodeDataRangeUUID: Decoder[DataRangeUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRangeTag]
  
  implicit val encodeDataRangeUUID: Encoder[DataRangeUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRangeTag]
  
  type DataRelationshipUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipTag
  
  def dataRelationshipUUID(uuid: UUID): DataRelationshipUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipTag][UUID](uuid)
  
  implicit val decodeDataRelationshipUUID: Decoder[DataRelationshipUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipTag]
  
  implicit val encodeDataRelationshipUUID: Encoder[DataRelationshipUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipTag]
  
  type DataRelationshipDomainUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipDomainTag
  
  def dataRelationshipDomainUUID(uuid: UUID): DataRelationshipDomainUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipDomainTag][UUID](uuid)
  
  implicit val decodeDataRelationshipDomainUUID: Decoder[DataRelationshipDomainUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipDomainTag]
  
  implicit val encodeDataRelationshipDomainUUID: Encoder[DataRelationshipDomainUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipDomainTag]
  
  type DataRelationshipFromEntityUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipFromEntityTag
  
  def dataRelationshipFromEntityUUID(uuid: UUID): DataRelationshipFromEntityUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipFromEntityTag][UUID](uuid)
  
  implicit val decodeDataRelationshipFromEntityUUID: Decoder[DataRelationshipFromEntityUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipFromEntityTag]
  
  implicit val encodeDataRelationshipFromEntityUUID: Encoder[DataRelationshipFromEntityUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipFromEntityTag]
  
  type DataRelationshipFromStructureUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipFromStructureTag
  
  def dataRelationshipFromStructureUUID(uuid: UUID): DataRelationshipFromStructureUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipFromStructureTag][UUID](uuid)
  
  implicit val decodeDataRelationshipFromStructureUUID: Decoder[DataRelationshipFromStructureUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipFromStructureTag]
  
  implicit val encodeDataRelationshipFromStructureUUID: Encoder[DataRelationshipFromStructureUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipFromStructureTag]
  
  type DataRelationshipRangeUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipRangeTag
  
  def dataRelationshipRangeUUID(uuid: UUID): DataRelationshipRangeUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipRangeTag][UUID](uuid)
  
  implicit val decodeDataRelationshipRangeUUID: Decoder[DataRelationshipRangeUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipRangeTag]
  
  implicit val encodeDataRelationshipRangeUUID: Encoder[DataRelationshipRangeUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipRangeTag]
  
  type DataRelationshipToScalarUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipToScalarTag
  
  def dataRelationshipToScalarUUID(uuid: UUID): DataRelationshipToScalarUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipToScalarTag][UUID](uuid)
  
  implicit val decodeDataRelationshipToScalarUUID: Decoder[DataRelationshipToScalarUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipToScalarTag]
  
  implicit val encodeDataRelationshipToScalarUUID: Encoder[DataRelationshipToScalarUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipToScalarTag]
  
  type DataRelationshipToStructureUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipToStructureTag
  
  def dataRelationshipToStructureUUID(uuid: UUID): DataRelationshipToStructureUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipToStructureTag][UUID](uuid)
  
  implicit val decodeDataRelationshipToStructureUUID: Decoder[DataRelationshipToStructureUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipToStructureTag]
  
  implicit val encodeDataRelationshipToStructureUUID: Encoder[DataRelationshipToStructureUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipToStructureTag]
  
  type DatatypeUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DatatypeTag
  
  def datatypeUUID(uuid: UUID): DatatypeUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DatatypeTag][UUID](uuid)
  
  implicit val decodeDatatypeUUID: Decoder[DatatypeUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DatatypeTag]
  
  implicit val encodeDatatypeUUID: Encoder[DatatypeUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DatatypeTag]
  
  type DescriptionBoxUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxTag
  
  def descriptionBoxUUID(uuid: UUID): DescriptionBoxUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxTag][UUID](uuid)
  
  implicit val decodeDescriptionBoxUUID: Decoder[DescriptionBoxUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxTag]
  
  implicit val encodeDescriptionBoxUUID: Encoder[DescriptionBoxUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxTag]
  
  type DescriptionBoxExtendsClosedWorldDefinitionsUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxExtendsClosedWorldDefinitionsTag
  
  def descriptionBoxExtendsClosedWorldDefinitionsUUID(uuid: UUID): DescriptionBoxExtendsClosedWorldDefinitionsUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxExtendsClosedWorldDefinitionsTag][UUID](uuid)
  
  implicit val decodeDescriptionBoxExtendsClosedWorldDefinitionsUUID: Decoder[DescriptionBoxExtendsClosedWorldDefinitionsUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxExtendsClosedWorldDefinitionsTag]
  
  implicit val encodeDescriptionBoxExtendsClosedWorldDefinitionsUUID: Encoder[DescriptionBoxExtendsClosedWorldDefinitionsUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxExtendsClosedWorldDefinitionsTag]
  
  type DescriptionBoxRefinementUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxRefinementTag
  
  def descriptionBoxRefinementUUID(uuid: UUID): DescriptionBoxRefinementUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxRefinementTag][UUID](uuid)
  
  implicit val decodeDescriptionBoxRefinementUUID: Decoder[DescriptionBoxRefinementUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxRefinementTag]
  
  implicit val encodeDescriptionBoxRefinementUUID: Encoder[DescriptionBoxRefinementUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxRefinementTag]
  
  type DescriptionBoxRelationshipUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxRelationshipTag
  
  def descriptionBoxRelationshipUUID(uuid: UUID): DescriptionBoxRelationshipUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxRelationshipTag][UUID](uuid)
  
  implicit val decodeDescriptionBoxRelationshipUUID: Decoder[DescriptionBoxRelationshipUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxRelationshipTag]
  
  implicit val encodeDescriptionBoxRelationshipUUID: Encoder[DescriptionBoxRelationshipUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxRelationshipTag]
  
  type DirectedBinaryRelationshipKindUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DirectedBinaryRelationshipKindTag
  
  def directedBinaryRelationshipKindUUID(uuid: UUID): DirectedBinaryRelationshipKindUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DirectedBinaryRelationshipKindTag][UUID](uuid)
  
  implicit val decodeDirectedBinaryRelationshipKindUUID: Decoder[DirectedBinaryRelationshipKindUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DirectedBinaryRelationshipKindTag]
  
  implicit val encodeDirectedBinaryRelationshipKindUUID: Encoder[DirectedBinaryRelationshipKindUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DirectedBinaryRelationshipKindTag]
  
  type DisjointUnionOfConceptsAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DisjointUnionOfConceptsAxiomTag
  
  def disjointUnionOfConceptsAxiomUUID(uuid: UUID): DisjointUnionOfConceptsAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DisjointUnionOfConceptsAxiomTag][UUID](uuid)
  
  implicit val decodeDisjointUnionOfConceptsAxiomUUID: Decoder[DisjointUnionOfConceptsAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DisjointUnionOfConceptsAxiomTag]
  
  implicit val encodeDisjointUnionOfConceptsAxiomUUID: Encoder[DisjointUnionOfConceptsAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DisjointUnionOfConceptsAxiomTag]
  
  type ElementUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ElementTag
  
  def elementUUID(uuid: UUID): ElementUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ElementTag][UUID](uuid)
  
  implicit val decodeElementUUID: Decoder[ElementUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ElementTag]
  
  implicit val encodeElementUUID: Encoder[ElementUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ElementTag]
  
  type EntityUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityTag
  
  def entityUUID(uuid: UUID): EntityUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityTag][UUID](uuid)
  
  implicit val decodeEntityUUID: Decoder[EntityUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityTag]
  
  implicit val encodeEntityUUID: Encoder[EntityUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityTag]
  
  type EntityExistentialRestrictionAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityExistentialRestrictionAxiomTag
  
  def entityExistentialRestrictionAxiomUUID(uuid: UUID): EntityExistentialRestrictionAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityExistentialRestrictionAxiomTag][UUID](uuid)
  
  implicit val decodeEntityExistentialRestrictionAxiomUUID: Decoder[EntityExistentialRestrictionAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityExistentialRestrictionAxiomTag]
  
  implicit val encodeEntityExistentialRestrictionAxiomUUID: Encoder[EntityExistentialRestrictionAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityExistentialRestrictionAxiomTag]
  
  type EntityRelationshipUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityRelationshipTag
  
  def entityRelationshipUUID(uuid: UUID): EntityRelationshipUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityRelationshipTag][UUID](uuid)
  
  implicit val decodeEntityRelationshipUUID: Decoder[EntityRelationshipUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityRelationshipTag]
  
  implicit val encodeEntityRelationshipUUID: Encoder[EntityRelationshipUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityRelationshipTag]
  
  type EntityRestrictionAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityRestrictionAxiomTag
  
  def entityRestrictionAxiomUUID(uuid: UUID): EntityRestrictionAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityRestrictionAxiomTag][UUID](uuid)
  
  implicit val decodeEntityRestrictionAxiomUUID: Decoder[EntityRestrictionAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityRestrictionAxiomTag]
  
  implicit val encodeEntityRestrictionAxiomUUID: Encoder[EntityRestrictionAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityRestrictionAxiomTag]
  
  type EntityScalarDataPropertyUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyTag
  
  def entityScalarDataPropertyUUID(uuid: UUID): EntityScalarDataPropertyUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyTag][UUID](uuid)
  
  implicit val decodeEntityScalarDataPropertyUUID: Decoder[EntityScalarDataPropertyUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyTag]
  
  implicit val encodeEntityScalarDataPropertyUUID: Encoder[EntityScalarDataPropertyUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyTag]
  
  type EntityScalarDataPropertyExistentialRestrictionAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyExistentialRestrictionAxiomTag
  
  def entityScalarDataPropertyExistentialRestrictionAxiomUUID(uuid: UUID): EntityScalarDataPropertyExistentialRestrictionAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyExistentialRestrictionAxiomTag][UUID](uuid)
  
  implicit val decodeEntityScalarDataPropertyExistentialRestrictionAxiomUUID: Decoder[EntityScalarDataPropertyExistentialRestrictionAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyExistentialRestrictionAxiomTag]
  
  implicit val encodeEntityScalarDataPropertyExistentialRestrictionAxiomUUID: Encoder[EntityScalarDataPropertyExistentialRestrictionAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyExistentialRestrictionAxiomTag]
  
  type EntityScalarDataPropertyParticularRestrictionAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyParticularRestrictionAxiomTag
  
  def entityScalarDataPropertyParticularRestrictionAxiomUUID(uuid: UUID): EntityScalarDataPropertyParticularRestrictionAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyParticularRestrictionAxiomTag][UUID](uuid)
  
  implicit val decodeEntityScalarDataPropertyParticularRestrictionAxiomUUID: Decoder[EntityScalarDataPropertyParticularRestrictionAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyParticularRestrictionAxiomTag]
  
  implicit val encodeEntityScalarDataPropertyParticularRestrictionAxiomUUID: Encoder[EntityScalarDataPropertyParticularRestrictionAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyParticularRestrictionAxiomTag]
  
  type EntityScalarDataPropertyRestrictionAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyRestrictionAxiomTag
  
  def entityScalarDataPropertyRestrictionAxiomUUID(uuid: UUID): EntityScalarDataPropertyRestrictionAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyRestrictionAxiomTag][UUID](uuid)
  
  implicit val decodeEntityScalarDataPropertyRestrictionAxiomUUID: Decoder[EntityScalarDataPropertyRestrictionAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyRestrictionAxiomTag]
  
  implicit val encodeEntityScalarDataPropertyRestrictionAxiomUUID: Encoder[EntityScalarDataPropertyRestrictionAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyRestrictionAxiomTag]
  
  type EntityScalarDataPropertyUniversalRestrictionAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyUniversalRestrictionAxiomTag
  
  def entityScalarDataPropertyUniversalRestrictionAxiomUUID(uuid: UUID): EntityScalarDataPropertyUniversalRestrictionAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyUniversalRestrictionAxiomTag][UUID](uuid)
  
  implicit val decodeEntityScalarDataPropertyUniversalRestrictionAxiomUUID: Decoder[EntityScalarDataPropertyUniversalRestrictionAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyUniversalRestrictionAxiomTag]
  
  implicit val encodeEntityScalarDataPropertyUniversalRestrictionAxiomUUID: Encoder[EntityScalarDataPropertyUniversalRestrictionAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyUniversalRestrictionAxiomTag]
  
  type EntityStructuredDataPropertyUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyTag
  
  def entityStructuredDataPropertyUUID(uuid: UUID): EntityStructuredDataPropertyUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyTag][UUID](uuid)
  
  implicit val decodeEntityStructuredDataPropertyUUID: Decoder[EntityStructuredDataPropertyUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyTag]
  
  implicit val encodeEntityStructuredDataPropertyUUID: Encoder[EntityStructuredDataPropertyUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyTag]
  
  type EntityStructuredDataPropertyParticularRestrictionAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyParticularRestrictionAxiomTag
  
  def entityStructuredDataPropertyParticularRestrictionAxiomUUID(uuid: UUID): EntityStructuredDataPropertyParticularRestrictionAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyParticularRestrictionAxiomTag][UUID](uuid)
  
  implicit val decodeEntityStructuredDataPropertyParticularRestrictionAxiomUUID: Decoder[EntityStructuredDataPropertyParticularRestrictionAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyParticularRestrictionAxiomTag]
  
  implicit val encodeEntityStructuredDataPropertyParticularRestrictionAxiomUUID: Encoder[EntityStructuredDataPropertyParticularRestrictionAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyParticularRestrictionAxiomTag]
  
  type EntityStructuredDataPropertyRestrictionAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyRestrictionAxiomTag
  
  def entityStructuredDataPropertyRestrictionAxiomUUID(uuid: UUID): EntityStructuredDataPropertyRestrictionAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyRestrictionAxiomTag][UUID](uuid)
  
  implicit val decodeEntityStructuredDataPropertyRestrictionAxiomUUID: Decoder[EntityStructuredDataPropertyRestrictionAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyRestrictionAxiomTag]
  
  implicit val encodeEntityStructuredDataPropertyRestrictionAxiomUUID: Encoder[EntityStructuredDataPropertyRestrictionAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyRestrictionAxiomTag]
  
  type EntityUniversalRestrictionAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityUniversalRestrictionAxiomTag
  
  def entityUniversalRestrictionAxiomUUID(uuid: UUID): EntityUniversalRestrictionAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityUniversalRestrictionAxiomTag][UUID](uuid)
  
  implicit val decodeEntityUniversalRestrictionAxiomUUID: Decoder[EntityUniversalRestrictionAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityUniversalRestrictionAxiomTag]
  
  implicit val encodeEntityUniversalRestrictionAxiomUUID: Encoder[EntityUniversalRestrictionAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityUniversalRestrictionAxiomTag]
  
  type ExtentUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ExtentTag
  
  def extentUUID(uuid: UUID): ExtentUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ExtentTag][UUID](uuid)
  
  implicit val decodeExtentUUID: Decoder[ExtentUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ExtentTag]
  
  implicit val encodeExtentUUID: Encoder[ExtentUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ExtentTag]
  
  type IRIScalarRestrictionUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.IRIScalarRestrictionTag
  
  def iriScalarRestrictionUUID(uuid: UUID): IRIScalarRestrictionUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.IRIScalarRestrictionTag][UUID](uuid)
  
  implicit val decodeIRIScalarRestrictionUUID: Decoder[IRIScalarRestrictionUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.IRIScalarRestrictionTag]
  
  implicit val encodeIRIScalarRestrictionUUID: Encoder[IRIScalarRestrictionUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.IRIScalarRestrictionTag]
  
  type LiteralBooleanUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralBooleanTag
  
  def literalBooleanUUID(uuid: UUID): LiteralBooleanUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralBooleanTag][UUID](uuid)
  
  implicit val decodeLiteralBooleanUUID: Decoder[LiteralBooleanUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralBooleanTag]
  
  implicit val encodeLiteralBooleanUUID: Encoder[LiteralBooleanUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralBooleanTag]
  
  type LiteralDateTimeUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralDateTimeTag
  
  def literalDateTimeUUID(uuid: UUID): LiteralDateTimeUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralDateTimeTag][UUID](uuid)
  
  implicit val decodeLiteralDateTimeUUID: Decoder[LiteralDateTimeUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralDateTimeTag]
  
  implicit val encodeLiteralDateTimeUUID: Encoder[LiteralDateTimeUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralDateTimeTag]
  
  type LiteralDecimalUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralDecimalTag
  
  def literalDecimalUUID(uuid: UUID): LiteralDecimalUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralDecimalTag][UUID](uuid)
  
  implicit val decodeLiteralDecimalUUID: Decoder[LiteralDecimalUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralDecimalTag]
  
  implicit val encodeLiteralDecimalUUID: Encoder[LiteralDecimalUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralDecimalTag]
  
  type LiteralFloatUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralFloatTag
  
  def literalFloatUUID(uuid: UUID): LiteralFloatUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralFloatTag][UUID](uuid)
  
  implicit val decodeLiteralFloatUUID: Decoder[LiteralFloatUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralFloatTag]
  
  implicit val encodeLiteralFloatUUID: Encoder[LiteralFloatUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralFloatTag]
  
  type LiteralNumberUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralNumberTag
  
  def literalNumberUUID(uuid: UUID): LiteralNumberUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralNumberTag][UUID](uuid)
  
  implicit val decodeLiteralNumberUUID: Decoder[LiteralNumberUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralNumberTag]
  
  implicit val encodeLiteralNumberUUID: Encoder[LiteralNumberUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralNumberTag]
  
  type LiteralQuotedStringUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralQuotedStringTag
  
  def literalQuotedStringUUID(uuid: UUID): LiteralQuotedStringUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralQuotedStringTag][UUID](uuid)
  
  implicit val decodeLiteralQuotedStringUUID: Decoder[LiteralQuotedStringUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralQuotedStringTag]
  
  implicit val encodeLiteralQuotedStringUUID: Encoder[LiteralQuotedStringUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralQuotedStringTag]
  
  type LiteralRationalUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralRationalTag
  
  def literalRationalUUID(uuid: UUID): LiteralRationalUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralRationalTag][UUID](uuid)
  
  implicit val decodeLiteralRationalUUID: Decoder[LiteralRationalUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralRationalTag]
  
  implicit val encodeLiteralRationalUUID: Encoder[LiteralRationalUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralRationalTag]
  
  type LiteralRawStringUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralRawStringTag
  
  def literalRawStringUUID(uuid: UUID): LiteralRawStringUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralRawStringTag][UUID](uuid)
  
  implicit val decodeLiteralRawStringUUID: Decoder[LiteralRawStringUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralRawStringTag]
  
  implicit val encodeLiteralRawStringUUID: Encoder[LiteralRawStringUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralRawStringTag]
  
  type LiteralRealUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralRealTag
  
  def literalRealUUID(uuid: UUID): LiteralRealUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralRealTag][UUID](uuid)
  
  implicit val decodeLiteralRealUUID: Decoder[LiteralRealUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralRealTag]
  
  implicit val encodeLiteralRealUUID: Encoder[LiteralRealUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralRealTag]
  
  type LiteralStringUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralStringTag
  
  def literalStringUUID(uuid: UUID): LiteralStringUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralStringTag][UUID](uuid)
  
  implicit val decodeLiteralStringUUID: Decoder[LiteralStringUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralStringTag]
  
  implicit val encodeLiteralStringUUID: Encoder[LiteralStringUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralStringTag]
  
  type LiteralURIUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralURITag
  
  def literalURIUUID(uuid: UUID): LiteralURIUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralURITag][UUID](uuid)
  
  implicit val decodeLiteralURIUUID: Decoder[LiteralURIUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralURITag]
  
  implicit val encodeLiteralURIUUID: Encoder[LiteralURIUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralURITag]
  
  type LiteralUUIDUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralUUIDTag
  
  def literalUUIDUUID(uuid: UUID): LiteralUUIDUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralUUIDTag][UUID](uuid)
  
  implicit val decodeLiteralUUIDUUID: Decoder[LiteralUUIDUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralUUIDTag]
  
  implicit val encodeLiteralUUIDUUID: Encoder[LiteralUUIDUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralUUIDTag]
  
  type LiteralValueUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralValueTag
  
  def literalValueUUID(uuid: UUID): LiteralValueUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralValueTag][UUID](uuid)
  
  implicit val decodeLiteralValueUUID: Decoder[LiteralValueUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralValueTag]
  
  implicit val encodeLiteralValueUUID: Encoder[LiteralValueUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LiteralValueTag]
  
  type ModuleUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleTag
  
  def moduleUUID(uuid: UUID): ModuleUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleTag][UUID](uuid)
  
  implicit val decodeModuleUUID: Decoder[ModuleUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleTag]
  
  implicit val encodeModuleUUID: Encoder[ModuleUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleTag]
  
  type ModuleEdgeUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleEdgeTag
  
  def moduleEdgeUUID(uuid: UUID): ModuleEdgeUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleEdgeTag][UUID](uuid)
  
  implicit val decodeModuleEdgeUUID: Decoder[ModuleEdgeUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleEdgeTag]
  
  implicit val encodeModuleEdgeUUID: Encoder[ModuleEdgeUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleEdgeTag]
  
  type ModuleElementUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleElementTag
  
  def moduleElementUUID(uuid: UUID): ModuleElementUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleElementTag][UUID](uuid)
  
  implicit val decodeModuleElementUUID: Decoder[ModuleElementUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleElementTag]
  
  implicit val encodeModuleElementUUID: Encoder[ModuleElementUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleElementTag]
  
  type NumericScalarRestrictionUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.NumericScalarRestrictionTag
  
  def numericScalarRestrictionUUID(uuid: UUID): NumericScalarRestrictionUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.NumericScalarRestrictionTag][UUID](uuid)
  
  implicit val decodeNumericScalarRestrictionUUID: Decoder[NumericScalarRestrictionUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.NumericScalarRestrictionTag]
  
  implicit val encodeNumericScalarRestrictionUUID: Encoder[NumericScalarRestrictionUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.NumericScalarRestrictionTag]
  
  type PlainLiteralScalarRestrictionUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.PlainLiteralScalarRestrictionTag
  
  def plainLiteralScalarRestrictionUUID(uuid: UUID): PlainLiteralScalarRestrictionUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.PlainLiteralScalarRestrictionTag][UUID](uuid)
  
  implicit val decodePlainLiteralScalarRestrictionUUID: Decoder[PlainLiteralScalarRestrictionUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.PlainLiteralScalarRestrictionTag]
  
  implicit val encodePlainLiteralScalarRestrictionUUID: Encoder[PlainLiteralScalarRestrictionUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.PlainLiteralScalarRestrictionTag]
  
  type ReifiedRelationshipUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipTag
  
  def reifiedRelationshipUUID(uuid: UUID): ReifiedRelationshipUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipTag][UUID](uuid)
  
  implicit val decodeReifiedRelationshipUUID: Decoder[ReifiedRelationshipUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipTag]
  
  implicit val encodeReifiedRelationshipUUID: Encoder[ReifiedRelationshipUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipTag]
  
  type ReifiedRelationshipInstanceUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceTag
  
  def reifiedRelationshipInstanceUUID(uuid: UUID): ReifiedRelationshipInstanceUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceTag][UUID](uuid)
  
  implicit val decodeReifiedRelationshipInstanceUUID: Decoder[ReifiedRelationshipInstanceUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceTag]
  
  implicit val encodeReifiedRelationshipInstanceUUID: Encoder[ReifiedRelationshipInstanceUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceTag]
  
  type ReifiedRelationshipInstanceDomainUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceDomainTag
  
  def reifiedRelationshipInstanceDomainUUID(uuid: UUID): ReifiedRelationshipInstanceDomainUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceDomainTag][UUID](uuid)
  
  implicit val decodeReifiedRelationshipInstanceDomainUUID: Decoder[ReifiedRelationshipInstanceDomainUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceDomainTag]
  
  implicit val encodeReifiedRelationshipInstanceDomainUUID: Encoder[ReifiedRelationshipInstanceDomainUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceDomainTag]
  
  type ReifiedRelationshipInstanceRangeUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceRangeTag
  
  def reifiedRelationshipInstanceRangeUUID(uuid: UUID): ReifiedRelationshipInstanceRangeUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceRangeTag][UUID](uuid)
  
  implicit val decodeReifiedRelationshipInstanceRangeUUID: Decoder[ReifiedRelationshipInstanceRangeUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceRangeTag]
  
  implicit val encodeReifiedRelationshipInstanceRangeUUID: Encoder[ReifiedRelationshipInstanceRangeUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceRangeTag]
  
  type ReifiedRelationshipInversePropertyPredicateUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInversePropertyPredicateTag
  
  def reifiedRelationshipInversePropertyPredicateUUID(uuid: UUID): ReifiedRelationshipInversePropertyPredicateUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInversePropertyPredicateTag][UUID](uuid)
  
  implicit val decodeReifiedRelationshipInversePropertyPredicateUUID: Decoder[ReifiedRelationshipInversePropertyPredicateUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInversePropertyPredicateTag]
  
  implicit val encodeReifiedRelationshipInversePropertyPredicateUUID: Encoder[ReifiedRelationshipInversePropertyPredicateUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInversePropertyPredicateTag]
  
  type ReifiedRelationshipPredicateUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipPredicateTag
  
  def reifiedRelationshipPredicateUUID(uuid: UUID): ReifiedRelationshipPredicateUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipPredicateTag][UUID](uuid)
  
  implicit val decodeReifiedRelationshipPredicateUUID: Decoder[ReifiedRelationshipPredicateUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipPredicateTag]
  
  implicit val encodeReifiedRelationshipPredicateUUID: Encoder[ReifiedRelationshipPredicateUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipPredicateTag]
  
  type ReifiedRelationshipPropertyPredicateUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipPropertyPredicateTag
  
  def reifiedRelationshipPropertyPredicateUUID(uuid: UUID): ReifiedRelationshipPropertyPredicateUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipPropertyPredicateTag][UUID](uuid)
  
  implicit val decodeReifiedRelationshipPropertyPredicateUUID: Decoder[ReifiedRelationshipPropertyPredicateUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipPropertyPredicateTag]
  
  implicit val encodeReifiedRelationshipPropertyPredicateUUID: Encoder[ReifiedRelationshipPropertyPredicateUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipPropertyPredicateTag]
  
  type ReifiedRelationshipSourceInversePropertyPredicateUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipSourceInversePropertyPredicateTag
  
  def reifiedRelationshipSourceInversePropertyPredicateUUID(uuid: UUID): ReifiedRelationshipSourceInversePropertyPredicateUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipSourceInversePropertyPredicateTag][UUID](uuid)
  
  implicit val decodeReifiedRelationshipSourceInversePropertyPredicateUUID: Decoder[ReifiedRelationshipSourceInversePropertyPredicateUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipSourceInversePropertyPredicateTag]
  
  implicit val encodeReifiedRelationshipSourceInversePropertyPredicateUUID: Encoder[ReifiedRelationshipSourceInversePropertyPredicateUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipSourceInversePropertyPredicateTag]
  
  type ReifiedRelationshipSourcePropertyPredicateUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipSourcePropertyPredicateTag
  
  def reifiedRelationshipSourcePropertyPredicateUUID(uuid: UUID): ReifiedRelationshipSourcePropertyPredicateUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipSourcePropertyPredicateTag][UUID](uuid)
  
  implicit val decodeReifiedRelationshipSourcePropertyPredicateUUID: Decoder[ReifiedRelationshipSourcePropertyPredicateUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipSourcePropertyPredicateTag]
  
  implicit val encodeReifiedRelationshipSourcePropertyPredicateUUID: Encoder[ReifiedRelationshipSourcePropertyPredicateUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipSourcePropertyPredicateTag]
  
  type ReifiedRelationshipSpecializationAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipSpecializationAxiomTag
  
  def reifiedRelationshipSpecializationAxiomUUID(uuid: UUID): ReifiedRelationshipSpecializationAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipSpecializationAxiomTag][UUID](uuid)
  
  implicit val decodeReifiedRelationshipSpecializationAxiomUUID: Decoder[ReifiedRelationshipSpecializationAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipSpecializationAxiomTag]
  
  implicit val encodeReifiedRelationshipSpecializationAxiomUUID: Encoder[ReifiedRelationshipSpecializationAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipSpecializationAxiomTag]
  
  type ReifiedRelationshipTargetInversePropertyPredicateUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipTargetInversePropertyPredicateTag
  
  def reifiedRelationshipTargetInversePropertyPredicateUUID(uuid: UUID): ReifiedRelationshipTargetInversePropertyPredicateUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipTargetInversePropertyPredicateTag][UUID](uuid)
  
  implicit val decodeReifiedRelationshipTargetInversePropertyPredicateUUID: Decoder[ReifiedRelationshipTargetInversePropertyPredicateUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipTargetInversePropertyPredicateTag]
  
  implicit val encodeReifiedRelationshipTargetInversePropertyPredicateUUID: Encoder[ReifiedRelationshipTargetInversePropertyPredicateUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipTargetInversePropertyPredicateTag]
  
  type ReifiedRelationshipTargetPropertyPredicateUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipTargetPropertyPredicateTag
  
  def reifiedRelationshipTargetPropertyPredicateUUID(uuid: UUID): ReifiedRelationshipTargetPropertyPredicateUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipTargetPropertyPredicateTag][UUID](uuid)
  
  implicit val decodeReifiedRelationshipTargetPropertyPredicateUUID: Decoder[ReifiedRelationshipTargetPropertyPredicateUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipTargetPropertyPredicateTag]
  
  implicit val encodeReifiedRelationshipTargetPropertyPredicateUUID: Encoder[ReifiedRelationshipTargetPropertyPredicateUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipTargetPropertyPredicateTag]
  
  type ResourceUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ResourceTag
  
  def resourceUUID(uuid: UUID): ResourceUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ResourceTag][UUID](uuid)
  
  implicit val decodeResourceUUID: Decoder[ResourceUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ResourceTag]
  
  implicit val encodeResourceUUID: Encoder[ResourceUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ResourceTag]
  
  type RestrictedDataRangeUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictedDataRangeTag
  
  def restrictedDataRangeUUID(uuid: UUID): RestrictedDataRangeUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictedDataRangeTag][UUID](uuid)
  
  implicit val decodeRestrictedDataRangeUUID: Decoder[RestrictedDataRangeUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictedDataRangeTag]
  
  implicit val encodeRestrictedDataRangeUUID: Encoder[RestrictedDataRangeUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictedDataRangeTag]
  
  type RestrictionScalarDataPropertyValueUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionScalarDataPropertyValueTag
  
  def restrictionScalarDataPropertyValueUUID(uuid: UUID): RestrictionScalarDataPropertyValueUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionScalarDataPropertyValueTag][UUID](uuid)
  
  implicit val decodeRestrictionScalarDataPropertyValueUUID: Decoder[RestrictionScalarDataPropertyValueUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionScalarDataPropertyValueTag]
  
  implicit val encodeRestrictionScalarDataPropertyValueUUID: Encoder[RestrictionScalarDataPropertyValueUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionScalarDataPropertyValueTag]
  
  type RestrictionStructuredDataPropertyContextUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionStructuredDataPropertyContextTag
  
  def restrictionStructuredDataPropertyContextUUID(uuid: UUID): RestrictionStructuredDataPropertyContextUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionStructuredDataPropertyContextTag][UUID](uuid)
  
  implicit val decodeRestrictionStructuredDataPropertyContextUUID: Decoder[RestrictionStructuredDataPropertyContextUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionStructuredDataPropertyContextTag]
  
  implicit val encodeRestrictionStructuredDataPropertyContextUUID: Encoder[RestrictionStructuredDataPropertyContextUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionStructuredDataPropertyContextTag]
  
  type RestrictionStructuredDataPropertyTupleUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionStructuredDataPropertyTupleTag
  
  def restrictionStructuredDataPropertyTupleUUID(uuid: UUID): RestrictionStructuredDataPropertyTupleUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionStructuredDataPropertyTupleTag][UUID](uuid)
  
  implicit val decodeRestrictionStructuredDataPropertyTupleUUID: Decoder[RestrictionStructuredDataPropertyTupleUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionStructuredDataPropertyTupleTag]
  
  implicit val encodeRestrictionStructuredDataPropertyTupleUUID: Encoder[RestrictionStructuredDataPropertyTupleUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionStructuredDataPropertyTupleTag]
  
  type RootConceptTaxonomyAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.RootConceptTaxonomyAxiomTag
  
  def rootConceptTaxonomyAxiomUUID(uuid: UUID): RootConceptTaxonomyAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RootConceptTaxonomyAxiomTag][UUID](uuid)
  
  implicit val decodeRootConceptTaxonomyAxiomUUID: Decoder[RootConceptTaxonomyAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RootConceptTaxonomyAxiomTag]
  
  implicit val encodeRootConceptTaxonomyAxiomUUID: Encoder[RootConceptTaxonomyAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RootConceptTaxonomyAxiomTag]
  
  type RuleUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.RuleTag
  
  def ruleUUID(uuid: UUID): RuleUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RuleTag][UUID](uuid)
  
  implicit val decodeRuleUUID: Decoder[RuleUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RuleTag]
  
  implicit val encodeRuleUUID: Encoder[RuleUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RuleTag]
  
  type RuleBodySegmentUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.RuleBodySegmentTag
  
  def ruleBodySegmentUUID(uuid: UUID): RuleBodySegmentUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RuleBodySegmentTag][UUID](uuid)
  
  implicit val decodeRuleBodySegmentUUID: Decoder[RuleBodySegmentUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RuleBodySegmentTag]
  
  implicit val encodeRuleBodySegmentUUID: Encoder[RuleBodySegmentUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RuleBodySegmentTag]
  
  type ScalarUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarTag
  
  def scalarUUID(uuid: UUID): ScalarUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarTag][UUID](uuid)
  
  implicit val decodeScalarUUID: Decoder[ScalarUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarTag]
  
  implicit val encodeScalarUUID: Encoder[ScalarUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarTag]
  
  type ScalarDataPropertyUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarDataPropertyTag
  
  def scalarDataPropertyUUID(uuid: UUID): ScalarDataPropertyUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarDataPropertyTag][UUID](uuid)
  
  implicit val decodeScalarDataPropertyUUID: Decoder[ScalarDataPropertyUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarDataPropertyTag]
  
  implicit val encodeScalarDataPropertyUUID: Encoder[ScalarDataPropertyUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarDataPropertyTag]
  
  type ScalarDataPropertyValueUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarDataPropertyValueTag
  
  def scalarDataPropertyValueUUID(uuid: UUID): ScalarDataPropertyValueUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarDataPropertyValueTag][UUID](uuid)
  
  implicit val decodeScalarDataPropertyValueUUID: Decoder[ScalarDataPropertyValueUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarDataPropertyValueTag]
  
  implicit val encodeScalarDataPropertyValueUUID: Encoder[ScalarDataPropertyValueUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarDataPropertyValueTag]
  
  type ScalarOneOfLiteralAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarOneOfLiteralAxiomTag
  
  def scalarOneOfLiteralAxiomUUID(uuid: UUID): ScalarOneOfLiteralAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarOneOfLiteralAxiomTag][UUID](uuid)
  
  implicit val decodeScalarOneOfLiteralAxiomUUID: Decoder[ScalarOneOfLiteralAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarOneOfLiteralAxiomTag]
  
  implicit val encodeScalarOneOfLiteralAxiomUUID: Encoder[ScalarOneOfLiteralAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarOneOfLiteralAxiomTag]
  
  type ScalarOneOfRestrictionUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarOneOfRestrictionTag
  
  def scalarOneOfRestrictionUUID(uuid: UUID): ScalarOneOfRestrictionUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarOneOfRestrictionTag][UUID](uuid)
  
  implicit val decodeScalarOneOfRestrictionUUID: Decoder[ScalarOneOfRestrictionUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarOneOfRestrictionTag]
  
  implicit val encodeScalarOneOfRestrictionUUID: Encoder[ScalarOneOfRestrictionUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarOneOfRestrictionTag]
  
  type SegmentPredicateUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.SegmentPredicateTag
  
  def segmentPredicateUUID(uuid: UUID): SegmentPredicateUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SegmentPredicateTag][UUID](uuid)
  
  implicit val decodeSegmentPredicateUUID: Decoder[SegmentPredicateUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SegmentPredicateTag]
  
  implicit val encodeSegmentPredicateUUID: Encoder[SegmentPredicateUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SegmentPredicateTag]
  
  type SingletonInstanceScalarDataPropertyValueUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceScalarDataPropertyValueTag
  
  def singletonInstanceScalarDataPropertyValueUUID(uuid: UUID): SingletonInstanceScalarDataPropertyValueUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceScalarDataPropertyValueTag][UUID](uuid)
  
  implicit val decodeSingletonInstanceScalarDataPropertyValueUUID: Decoder[SingletonInstanceScalarDataPropertyValueUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceScalarDataPropertyValueTag]
  
  implicit val encodeSingletonInstanceScalarDataPropertyValueUUID: Encoder[SingletonInstanceScalarDataPropertyValueUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceScalarDataPropertyValueTag]
  
  type SingletonInstanceStructuredDataPropertyContextUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceStructuredDataPropertyContextTag
  
  def singletonInstanceStructuredDataPropertyContextUUID(uuid: UUID): SingletonInstanceStructuredDataPropertyContextUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceStructuredDataPropertyContextTag][UUID](uuid)
  
  implicit val decodeSingletonInstanceStructuredDataPropertyContextUUID: Decoder[SingletonInstanceStructuredDataPropertyContextUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceStructuredDataPropertyContextTag]
  
  implicit val encodeSingletonInstanceStructuredDataPropertyContextUUID: Encoder[SingletonInstanceStructuredDataPropertyContextUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceStructuredDataPropertyContextTag]
  
  type SingletonInstanceStructuredDataPropertyValueUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceStructuredDataPropertyValueTag
  
  def singletonInstanceStructuredDataPropertyValueUUID(uuid: UUID): SingletonInstanceStructuredDataPropertyValueUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceStructuredDataPropertyValueTag][UUID](uuid)
  
  implicit val decodeSingletonInstanceStructuredDataPropertyValueUUID: Decoder[SingletonInstanceStructuredDataPropertyValueUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceStructuredDataPropertyValueTag]
  
  implicit val encodeSingletonInstanceStructuredDataPropertyValueUUID: Encoder[SingletonInstanceStructuredDataPropertyValueUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceStructuredDataPropertyValueTag]
  
  type SpecializationAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.SpecializationAxiomTag
  
  def specializationAxiomUUID(uuid: UUID): SpecializationAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SpecializationAxiomTag][UUID](uuid)
  
  implicit val decodeSpecializationAxiomUUID: Decoder[SpecializationAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SpecializationAxiomTag]
  
  implicit val encodeSpecializationAxiomUUID: Encoder[SpecializationAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SpecializationAxiomTag]
  
  type SpecificDisjointConceptAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.SpecificDisjointConceptAxiomTag
  
  def specificDisjointConceptAxiomUUID(uuid: UUID): SpecificDisjointConceptAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SpecificDisjointConceptAxiomTag][UUID](uuid)
  
  implicit val decodeSpecificDisjointConceptAxiomUUID: Decoder[SpecificDisjointConceptAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SpecificDisjointConceptAxiomTag]
  
  implicit val encodeSpecificDisjointConceptAxiomUUID: Encoder[SpecificDisjointConceptAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SpecificDisjointConceptAxiomTag]
  
  type StringScalarRestrictionUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.StringScalarRestrictionTag
  
  def stringScalarRestrictionUUID(uuid: UUID): StringScalarRestrictionUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StringScalarRestrictionTag][UUID](uuid)
  
  implicit val decodeStringScalarRestrictionUUID: Decoder[StringScalarRestrictionUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StringScalarRestrictionTag]
  
  implicit val encodeStringScalarRestrictionUUID: Encoder[StringScalarRestrictionUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StringScalarRestrictionTag]
  
  type StructureUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.StructureTag
  
  def structureUUID(uuid: UUID): StructureUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StructureTag][UUID](uuid)
  
  implicit val decodeStructureUUID: Decoder[StructureUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StructureTag]
  
  implicit val encodeStructureUUID: Encoder[StructureUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StructureTag]
  
  type StructuredDataPropertyUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.StructuredDataPropertyTag
  
  def structuredDataPropertyUUID(uuid: UUID): StructuredDataPropertyUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StructuredDataPropertyTag][UUID](uuid)
  
  implicit val decodeStructuredDataPropertyUUID: Decoder[StructuredDataPropertyUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StructuredDataPropertyTag]
  
  implicit val encodeStructuredDataPropertyUUID: Encoder[StructuredDataPropertyUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StructuredDataPropertyTag]
  
  type StructuredDataPropertyTupleUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.StructuredDataPropertyTupleTag
  
  def structuredDataPropertyTupleUUID(uuid: UUID): StructuredDataPropertyTupleUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StructuredDataPropertyTupleTag][UUID](uuid)
  
  implicit val decodeStructuredDataPropertyTupleUUID: Decoder[StructuredDataPropertyTupleUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StructuredDataPropertyTupleTag]
  
  implicit val encodeStructuredDataPropertyTupleUUID: Encoder[StructuredDataPropertyTupleUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StructuredDataPropertyTupleTag]
  
  type SynonymScalarRestrictionUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.SynonymScalarRestrictionTag
  
  def synonymScalarRestrictionUUID(uuid: UUID): SynonymScalarRestrictionUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SynonymScalarRestrictionTag][UUID](uuid)
  
  implicit val decodeSynonymScalarRestrictionUUID: Decoder[SynonymScalarRestrictionUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SynonymScalarRestrictionTag]
  
  implicit val encodeSynonymScalarRestrictionUUID: Encoder[SynonymScalarRestrictionUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SynonymScalarRestrictionTag]
  
  type TermUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TermTag
  
  def termUUID(uuid: UUID): TermUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TermTag][UUID](uuid)
  
  implicit val decodeTermUUID: Decoder[TermUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TermTag]
  
  implicit val encodeTermUUID: Encoder[TermUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TermTag]
  
  type TermAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TermAxiomTag
  
  def termAxiomUUID(uuid: UUID): TermAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TermAxiomTag][UUID](uuid)
  
  implicit val decodeTermAxiomUUID: Decoder[TermAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TermAxiomTag]
  
  implicit val encodeTermAxiomUUID: Encoder[TermAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TermAxiomTag]
  
  type TerminologyAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyAxiomTag
  
  def terminologyAxiomUUID(uuid: UUID): TerminologyAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyAxiomTag][UUID](uuid)
  
  implicit val decodeTerminologyAxiomUUID: Decoder[TerminologyAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyAxiomTag]
  
  implicit val encodeTerminologyAxiomUUID: Encoder[TerminologyAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyAxiomTag]
  
  type TerminologyBoxUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxTag
  
  def terminologyBoxUUID(uuid: UUID): TerminologyBoxUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxTag][UUID](uuid)
  
  implicit val decodeTerminologyBoxUUID: Decoder[TerminologyBoxUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxTag]
  
  implicit val encodeTerminologyBoxUUID: Encoder[TerminologyBoxUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxTag]
  
  type TerminologyBoxAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxAxiomTag
  
  def terminologyBoxAxiomUUID(uuid: UUID): TerminologyBoxAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxAxiomTag][UUID](uuid)
  
  implicit val decodeTerminologyBoxAxiomUUID: Decoder[TerminologyBoxAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxAxiomTag]
  
  implicit val encodeTerminologyBoxAxiomUUID: Encoder[TerminologyBoxAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxAxiomTag]
  
  type TerminologyBoxStatementUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxStatementTag
  
  def terminologyBoxStatementUUID(uuid: UUID): TerminologyBoxStatementUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxStatementTag][UUID](uuid)
  
  implicit val decodeTerminologyBoxStatementUUID: Decoder[TerminologyBoxStatementUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxStatementTag]
  
  implicit val encodeTerminologyBoxStatementUUID: Encoder[TerminologyBoxStatementUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxStatementTag]
  
  type TerminologyBundleAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBundleAxiomTag
  
  def terminologyBundleAxiomUUID(uuid: UUID): TerminologyBundleAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBundleAxiomTag][UUID](uuid)
  
  implicit val decodeTerminologyBundleAxiomUUID: Decoder[TerminologyBundleAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBundleAxiomTag]
  
  implicit val encodeTerminologyBundleAxiomUUID: Encoder[TerminologyBundleAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBundleAxiomTag]
  
  type TerminologyBundleStatementUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBundleStatementTag
  
  def terminologyBundleStatementUUID(uuid: UUID): TerminologyBundleStatementUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBundleStatementTag][UUID](uuid)
  
  implicit val decodeTerminologyBundleStatementUUID: Decoder[TerminologyBundleStatementUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBundleStatementTag]
  
  implicit val encodeTerminologyBundleStatementUUID: Encoder[TerminologyBundleStatementUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBundleStatementTag]
  
  type TerminologyExtensionAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyExtensionAxiomTag
  
  def terminologyExtensionAxiomUUID(uuid: UUID): TerminologyExtensionAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyExtensionAxiomTag][UUID](uuid)
  
  implicit val decodeTerminologyExtensionAxiomUUID: Decoder[TerminologyExtensionAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyExtensionAxiomTag]
  
  implicit val encodeTerminologyExtensionAxiomUUID: Encoder[TerminologyExtensionAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyExtensionAxiomTag]
  
  type TerminologyGraphUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyGraphTag
  
  def terminologyGraphUUID(uuid: UUID): TerminologyGraphUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyGraphTag][UUID](uuid)
  
  implicit val decodeTerminologyGraphUUID: Decoder[TerminologyGraphUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyGraphTag]
  
  implicit val encodeTerminologyGraphUUID: Encoder[TerminologyGraphUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyGraphTag]
  
  type TerminologyInstanceAssertionUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyInstanceAssertionTag
  
  def terminologyInstanceAssertionUUID(uuid: UUID): TerminologyInstanceAssertionUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyInstanceAssertionTag][UUID](uuid)
  
  implicit val decodeTerminologyInstanceAssertionUUID: Decoder[TerminologyInstanceAssertionUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyInstanceAssertionTag]
  
  implicit val encodeTerminologyInstanceAssertionUUID: Encoder[TerminologyInstanceAssertionUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyInstanceAssertionTag]
  
  type TerminologyNestingAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyNestingAxiomTag
  
  def terminologyNestingAxiomUUID(uuid: UUID): TerminologyNestingAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyNestingAxiomTag][UUID](uuid)
  
  implicit val decodeTerminologyNestingAxiomUUID: Decoder[TerminologyNestingAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyNestingAxiomTag]
  
  implicit val encodeTerminologyNestingAxiomUUID: Encoder[TerminologyNestingAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyNestingAxiomTag]
  
  type TimeScalarRestrictionUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TimeScalarRestrictionTag
  
  def timeScalarRestrictionUUID(uuid: UUID): TimeScalarRestrictionUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TimeScalarRestrictionTag][UUID](uuid)
  
  implicit val decodeTimeScalarRestrictionUUID: Decoder[TimeScalarRestrictionUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TimeScalarRestrictionTag]
  
  implicit val encodeTimeScalarRestrictionUUID: Encoder[TimeScalarRestrictionUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TimeScalarRestrictionTag]
  
  type UnarySegmentPredicateUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.UnarySegmentPredicateTag
  
  def unarySegmentPredicateUUID(uuid: UUID): UnarySegmentPredicateUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnarySegmentPredicateTag][UUID](uuid)
  
  implicit val decodeUnarySegmentPredicateUUID: Decoder[UnarySegmentPredicateUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnarySegmentPredicateTag]
  
  implicit val encodeUnarySegmentPredicateUUID: Encoder[UnarySegmentPredicateUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnarySegmentPredicateTag]
  
  type UnaryTermKindUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.UnaryTermKindTag
  
  def unaryTermKindUUID(uuid: UUID): UnaryTermKindUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnaryTermKindTag][UUID](uuid)
  
  implicit val decodeUnaryTermKindUUID: Decoder[UnaryTermKindUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnaryTermKindTag]
  
  implicit val encodeUnaryTermKindUUID: Encoder[UnaryTermKindUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnaryTermKindTag]
  
  type UnreifiedRelationshipUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipTag
  
  def unreifiedRelationshipUUID(uuid: UUID): UnreifiedRelationshipUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipTag][UUID](uuid)
  
  implicit val decodeUnreifiedRelationshipUUID: Decoder[UnreifiedRelationshipUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipTag]
  
  implicit val encodeUnreifiedRelationshipUUID: Encoder[UnreifiedRelationshipUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipTag]
  
  type UnreifiedRelationshipInstanceTupleUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipInstanceTupleTag
  
  def unreifiedRelationshipInstanceTupleUUID(uuid: UUID): UnreifiedRelationshipInstanceTupleUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipInstanceTupleTag][UUID](uuid)
  
  implicit val decodeUnreifiedRelationshipInstanceTupleUUID: Decoder[UnreifiedRelationshipInstanceTupleUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipInstanceTupleTag]
  
  implicit val encodeUnreifiedRelationshipInstanceTupleUUID: Encoder[UnreifiedRelationshipInstanceTupleUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipInstanceTupleTag]
  
  type UnreifiedRelationshipInversePropertyPredicateUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipInversePropertyPredicateTag
  
  def unreifiedRelationshipInversePropertyPredicateUUID(uuid: UUID): UnreifiedRelationshipInversePropertyPredicateUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipInversePropertyPredicateTag][UUID](uuid)
  
  implicit val decodeUnreifiedRelationshipInversePropertyPredicateUUID: Decoder[UnreifiedRelationshipInversePropertyPredicateUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipInversePropertyPredicateTag]
  
  implicit val encodeUnreifiedRelationshipInversePropertyPredicateUUID: Encoder[UnreifiedRelationshipInversePropertyPredicateUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipInversePropertyPredicateTag]
  
  type UnreifiedRelationshipPropertyPredicateUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipPropertyPredicateTag
  
  def unreifiedRelationshipPropertyPredicateUUID(uuid: UUID): UnreifiedRelationshipPropertyPredicateUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipPropertyPredicateTag][UUID](uuid)
  
  implicit val decodeUnreifiedRelationshipPropertyPredicateUUID: Decoder[UnreifiedRelationshipPropertyPredicateUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipPropertyPredicateTag]
  
  implicit val encodeUnreifiedRelationshipPropertyPredicateUUID: Encoder[UnreifiedRelationshipPropertyPredicateUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipPropertyPredicateTag]
  
}
