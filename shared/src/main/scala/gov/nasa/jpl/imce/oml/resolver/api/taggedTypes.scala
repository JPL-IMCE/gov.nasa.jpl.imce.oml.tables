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
import scala.{Int,Left,Ordering,Right}

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

  implicit val orderingAnnotationPropertyUUID
  : Ordering[AnnotationPropertyUUID]
  = new Ordering[AnnotationPropertyUUID] {
  	override def compare
  	(x: AnnotationPropertyUUID, 
  	 y: AnnotationPropertyUUID)
  	: Int = x.compareTo(y)
  }
  
  type AnnotationPropertyValueUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.AnnotationPropertyValueTag
  
  def annotationPropertyValueUUID(uuid: UUID): AnnotationPropertyValueUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AnnotationPropertyValueTag][UUID](uuid)
  
  implicit val decodeAnnotationPropertyValueUUID: Decoder[AnnotationPropertyValueUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AnnotationPropertyValueTag]
  
  implicit val encodeAnnotationPropertyValueUUID: Encoder[AnnotationPropertyValueUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AnnotationPropertyValueTag]

  implicit val orderingAnnotationPropertyValueUUID
  : Ordering[AnnotationPropertyValueUUID]
  = new Ordering[AnnotationPropertyValueUUID] {
  	override def compare
  	(x: AnnotationPropertyValueUUID, 
  	 y: AnnotationPropertyValueUUID)
  	: Int = x.compareTo(y)
  }
  
  type AnonymousConceptUnionAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.AnonymousConceptUnionAxiomTag
  
  def anonymousConceptUnionAxiomUUID(uuid: UUID): AnonymousConceptUnionAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AnonymousConceptUnionAxiomTag][UUID](uuid)
  
  implicit val decodeAnonymousConceptUnionAxiomUUID: Decoder[AnonymousConceptUnionAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AnonymousConceptUnionAxiomTag]
  
  implicit val encodeAnonymousConceptUnionAxiomUUID: Encoder[AnonymousConceptUnionAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AnonymousConceptUnionAxiomTag]

  implicit val orderingAnonymousConceptUnionAxiomUUID
  : Ordering[AnonymousConceptUnionAxiomUUID]
  = new Ordering[AnonymousConceptUnionAxiomUUID] {
  	override def compare
  	(x: AnonymousConceptUnionAxiomUUID, 
  	 y: AnonymousConceptUnionAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type AspectUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectTag
  
  def aspectUUID(uuid: UUID): AspectUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectTag][UUID](uuid)
  
  implicit val decodeAspectUUID: Decoder[AspectUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectTag]
  
  implicit val encodeAspectUUID: Encoder[AspectUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectTag]

  implicit val orderingAspectUUID
  : Ordering[AspectUUID]
  = new Ordering[AspectUUID] {
  	override def compare
  	(x: AspectUUID, 
  	 y: AspectUUID)
  	: Int = x.compareTo(y)
  }
  
  type AspectKindUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectKindTag
  
  def aspectKindUUID(uuid: UUID): AspectKindUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectKindTag][UUID](uuid)
  
  implicit val decodeAspectKindUUID: Decoder[AspectKindUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectKindTag]
  
  implicit val encodeAspectKindUUID: Encoder[AspectKindUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectKindTag]

  implicit val orderingAspectKindUUID
  : Ordering[AspectKindUUID]
  = new Ordering[AspectKindUUID] {
  	override def compare
  	(x: AspectKindUUID, 
  	 y: AspectKindUUID)
  	: Int = x.compareTo(y)
  }
  
  type AspectSpecializationAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectSpecializationAxiomTag
  
  def aspectSpecializationAxiomUUID(uuid: UUID): AspectSpecializationAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectSpecializationAxiomTag][UUID](uuid)
  
  implicit val decodeAspectSpecializationAxiomUUID: Decoder[AspectSpecializationAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectSpecializationAxiomTag]
  
  implicit val encodeAspectSpecializationAxiomUUID: Encoder[AspectSpecializationAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.AspectSpecializationAxiomTag]

  implicit val orderingAspectSpecializationAxiomUUID
  : Ordering[AspectSpecializationAxiomUUID]
  = new Ordering[AspectSpecializationAxiomUUID] {
  	override def compare
  	(x: AspectSpecializationAxiomUUID, 
  	 y: AspectSpecializationAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type BinaryScalarRestrictionUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.BinaryScalarRestrictionTag
  
  def binaryScalarRestrictionUUID(uuid: UUID): BinaryScalarRestrictionUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BinaryScalarRestrictionTag][UUID](uuid)
  
  implicit val decodeBinaryScalarRestrictionUUID: Decoder[BinaryScalarRestrictionUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BinaryScalarRestrictionTag]
  
  implicit val encodeBinaryScalarRestrictionUUID: Encoder[BinaryScalarRestrictionUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BinaryScalarRestrictionTag]

  implicit val orderingBinaryScalarRestrictionUUID
  : Ordering[BinaryScalarRestrictionUUID]
  = new Ordering[BinaryScalarRestrictionUUID] {
  	override def compare
  	(x: BinaryScalarRestrictionUUID, 
  	 y: BinaryScalarRestrictionUUID)
  	: Int = x.compareTo(y)
  }
  
  type BundleUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.BundleTag
  
  def bundleUUID(uuid: UUID): BundleUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BundleTag][UUID](uuid)
  
  implicit val decodeBundleUUID: Decoder[BundleUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BundleTag]
  
  implicit val encodeBundleUUID: Encoder[BundleUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BundleTag]

  implicit val orderingBundleUUID
  : Ordering[BundleUUID]
  = new Ordering[BundleUUID] {
  	override def compare
  	(x: BundleUUID, 
  	 y: BundleUUID)
  	: Int = x.compareTo(y)
  }
  
  type BundledTerminologyAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.BundledTerminologyAxiomTag
  
  def bundledTerminologyAxiomUUID(uuid: UUID): BundledTerminologyAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BundledTerminologyAxiomTag][UUID](uuid)
  
  implicit val decodeBundledTerminologyAxiomUUID: Decoder[BundledTerminologyAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BundledTerminologyAxiomTag]
  
  implicit val encodeBundledTerminologyAxiomUUID: Encoder[BundledTerminologyAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.BundledTerminologyAxiomTag]

  implicit val orderingBundledTerminologyAxiomUUID
  : Ordering[BundledTerminologyAxiomUUID]
  = new Ordering[BundledTerminologyAxiomUUID] {
  	override def compare
  	(x: BundledTerminologyAxiomUUID, 
  	 y: BundledTerminologyAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type CardinalityRestrictedAspectUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.CardinalityRestrictedAspectTag
  
  def cardinalityRestrictedAspectUUID(uuid: UUID): CardinalityRestrictedAspectUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.CardinalityRestrictedAspectTag][UUID](uuid)
  
  implicit val decodeCardinalityRestrictedAspectUUID: Decoder[CardinalityRestrictedAspectUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.CardinalityRestrictedAspectTag]
  
  implicit val encodeCardinalityRestrictedAspectUUID: Encoder[CardinalityRestrictedAspectUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.CardinalityRestrictedAspectTag]

  implicit val orderingCardinalityRestrictedAspectUUID
  : Ordering[CardinalityRestrictedAspectUUID]
  = new Ordering[CardinalityRestrictedAspectUUID] {
  	override def compare
  	(x: CardinalityRestrictedAspectUUID, 
  	 y: CardinalityRestrictedAspectUUID)
  	: Int = x.compareTo(y)
  }
  
  type CardinalityRestrictedConceptUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.CardinalityRestrictedConceptTag
  
  def cardinalityRestrictedConceptUUID(uuid: UUID): CardinalityRestrictedConceptUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.CardinalityRestrictedConceptTag][UUID](uuid)
  
  implicit val decodeCardinalityRestrictedConceptUUID: Decoder[CardinalityRestrictedConceptUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.CardinalityRestrictedConceptTag]
  
  implicit val encodeCardinalityRestrictedConceptUUID: Encoder[CardinalityRestrictedConceptUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.CardinalityRestrictedConceptTag]

  implicit val orderingCardinalityRestrictedConceptUUID
  : Ordering[CardinalityRestrictedConceptUUID]
  = new Ordering[CardinalityRestrictedConceptUUID] {
  	override def compare
  	(x: CardinalityRestrictedConceptUUID, 
  	 y: CardinalityRestrictedConceptUUID)
  	: Int = x.compareTo(y)
  }
  
  type CardinalityRestrictedReifiedRelationshipUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.CardinalityRestrictedReifiedRelationshipTag
  
  def cardinalityRestrictedReifiedRelationshipUUID(uuid: UUID): CardinalityRestrictedReifiedRelationshipUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.CardinalityRestrictedReifiedRelationshipTag][UUID](uuid)
  
  implicit val decodeCardinalityRestrictedReifiedRelationshipUUID: Decoder[CardinalityRestrictedReifiedRelationshipUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.CardinalityRestrictedReifiedRelationshipTag]
  
  implicit val encodeCardinalityRestrictedReifiedRelationshipUUID: Encoder[CardinalityRestrictedReifiedRelationshipUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.CardinalityRestrictedReifiedRelationshipTag]

  implicit val orderingCardinalityRestrictedReifiedRelationshipUUID
  : Ordering[CardinalityRestrictedReifiedRelationshipUUID]
  = new Ordering[CardinalityRestrictedReifiedRelationshipUUID] {
  	override def compare
  	(x: CardinalityRestrictedReifiedRelationshipUUID, 
  	 y: CardinalityRestrictedReifiedRelationshipUUID)
  	: Int = x.compareTo(y)
  }
  
  type ChainRuleUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ChainRuleTag
  
  def chainRuleUUID(uuid: UUID): ChainRuleUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ChainRuleTag][UUID](uuid)
  
  implicit val decodeChainRuleUUID: Decoder[ChainRuleUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ChainRuleTag]
  
  implicit val encodeChainRuleUUID: Encoder[ChainRuleUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ChainRuleTag]

  implicit val orderingChainRuleUUID
  : Ordering[ChainRuleUUID]
  = new Ordering[ChainRuleUUID] {
  	override def compare
  	(x: ChainRuleUUID, 
  	 y: ChainRuleUUID)
  	: Int = x.compareTo(y)
  }
  
  type CharacterizedEntityRelationshipUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.CharacterizedEntityRelationshipTag
  
  def characterizedEntityRelationshipUUID(uuid: UUID): CharacterizedEntityRelationshipUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.CharacterizedEntityRelationshipTag][UUID](uuid)
  
  implicit val decodeCharacterizedEntityRelationshipUUID: Decoder[CharacterizedEntityRelationshipUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.CharacterizedEntityRelationshipTag]
  
  implicit val encodeCharacterizedEntityRelationshipUUID: Encoder[CharacterizedEntityRelationshipUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.CharacterizedEntityRelationshipTag]

  implicit val orderingCharacterizedEntityRelationshipUUID
  : Ordering[CharacterizedEntityRelationshipUUID]
  = new Ordering[CharacterizedEntityRelationshipUUID] {
  	override def compare
  	(x: CharacterizedEntityRelationshipUUID, 
  	 y: CharacterizedEntityRelationshipUUID)
  	: Int = x.compareTo(y)
  }
  
  type ConceptUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptTag
  
  def conceptUUID(uuid: UUID): ConceptUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptTag][UUID](uuid)
  
  implicit val decodeConceptUUID: Decoder[ConceptUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptTag]
  
  implicit val encodeConceptUUID: Encoder[ConceptUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptTag]

  implicit val orderingConceptUUID
  : Ordering[ConceptUUID]
  = new Ordering[ConceptUUID] {
  	override def compare
  	(x: ConceptUUID, 
  	 y: ConceptUUID)
  	: Int = x.compareTo(y)
  }
  
  type ConceptDesignationTerminologyAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptDesignationTerminologyAxiomTag
  
  def conceptDesignationTerminologyAxiomUUID(uuid: UUID): ConceptDesignationTerminologyAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptDesignationTerminologyAxiomTag][UUID](uuid)
  
  implicit val decodeConceptDesignationTerminologyAxiomUUID: Decoder[ConceptDesignationTerminologyAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptDesignationTerminologyAxiomTag]
  
  implicit val encodeConceptDesignationTerminologyAxiomUUID: Encoder[ConceptDesignationTerminologyAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptDesignationTerminologyAxiomTag]

  implicit val orderingConceptDesignationTerminologyAxiomUUID
  : Ordering[ConceptDesignationTerminologyAxiomUUID]
  = new Ordering[ConceptDesignationTerminologyAxiomUUID] {
  	override def compare
  	(x: ConceptDesignationTerminologyAxiomUUID, 
  	 y: ConceptDesignationTerminologyAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type ConceptInstanceUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptInstanceTag
  
  def conceptInstanceUUID(uuid: UUID): ConceptInstanceUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptInstanceTag][UUID](uuid)
  
  implicit val decodeConceptInstanceUUID: Decoder[ConceptInstanceUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptInstanceTag]
  
  implicit val encodeConceptInstanceUUID: Encoder[ConceptInstanceUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptInstanceTag]

  implicit val orderingConceptInstanceUUID
  : Ordering[ConceptInstanceUUID]
  = new Ordering[ConceptInstanceUUID] {
  	override def compare
  	(x: ConceptInstanceUUID, 
  	 y: ConceptInstanceUUID)
  	: Int = x.compareTo(y)
  }
  
  type ConceptKindUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptKindTag
  
  def conceptKindUUID(uuid: UUID): ConceptKindUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptKindTag][UUID](uuid)
  
  implicit val decodeConceptKindUUID: Decoder[ConceptKindUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptKindTag]
  
  implicit val encodeConceptKindUUID: Encoder[ConceptKindUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptKindTag]

  implicit val orderingConceptKindUUID
  : Ordering[ConceptKindUUID]
  = new Ordering[ConceptKindUUID] {
  	override def compare
  	(x: ConceptKindUUID, 
  	 y: ConceptKindUUID)
  	: Int = x.compareTo(y)
  }
  
  type ConceptSpecializationAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptSpecializationAxiomTag
  
  def conceptSpecializationAxiomUUID(uuid: UUID): ConceptSpecializationAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptSpecializationAxiomTag][UUID](uuid)
  
  implicit val decodeConceptSpecializationAxiomUUID: Decoder[ConceptSpecializationAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptSpecializationAxiomTag]
  
  implicit val encodeConceptSpecializationAxiomUUID: Encoder[ConceptSpecializationAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptSpecializationAxiomTag]

  implicit val orderingConceptSpecializationAxiomUUID
  : Ordering[ConceptSpecializationAxiomUUID]
  = new Ordering[ConceptSpecializationAxiomUUID] {
  	override def compare
  	(x: ConceptSpecializationAxiomUUID, 
  	 y: ConceptSpecializationAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type ConceptTreeDisjunctionUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptTreeDisjunctionTag
  
  def conceptTreeDisjunctionUUID(uuid: UUID): ConceptTreeDisjunctionUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptTreeDisjunctionTag][UUID](uuid)
  
  implicit val decodeConceptTreeDisjunctionUUID: Decoder[ConceptTreeDisjunctionUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptTreeDisjunctionTag]
  
  implicit val encodeConceptTreeDisjunctionUUID: Encoder[ConceptTreeDisjunctionUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptTreeDisjunctionTag]

  implicit val orderingConceptTreeDisjunctionUUID
  : Ordering[ConceptTreeDisjunctionUUID]
  = new Ordering[ConceptTreeDisjunctionUUID] {
  	override def compare
  	(x: ConceptTreeDisjunctionUUID, 
  	 y: ConceptTreeDisjunctionUUID)
  	: Int = x.compareTo(y)
  }
  
  type ConceptualEntityUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptualEntityTag
  
  def conceptualEntityUUID(uuid: UUID): ConceptualEntityUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptualEntityTag][UUID](uuid)
  
  implicit val decodeConceptualEntityUUID: Decoder[ConceptualEntityUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptualEntityTag]
  
  implicit val encodeConceptualEntityUUID: Encoder[ConceptualEntityUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptualEntityTag]

  implicit val orderingConceptualEntityUUID
  : Ordering[ConceptualEntityUUID]
  = new Ordering[ConceptualEntityUUID] {
  	override def compare
  	(x: ConceptualEntityUUID, 
  	 y: ConceptualEntityUUID)
  	: Int = x.compareTo(y)
  }
  
  type ConceptualEntitySingletonInstanceUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptualEntitySingletonInstanceTag
  
  def conceptualEntitySingletonInstanceUUID(uuid: UUID): ConceptualEntitySingletonInstanceUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptualEntitySingletonInstanceTag][UUID](uuid)
  
  implicit val decodeConceptualEntitySingletonInstanceUUID: Decoder[ConceptualEntitySingletonInstanceUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptualEntitySingletonInstanceTag]
  
  implicit val encodeConceptualEntitySingletonInstanceUUID: Encoder[ConceptualEntitySingletonInstanceUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptualEntitySingletonInstanceTag]

  implicit val orderingConceptualEntitySingletonInstanceUUID
  : Ordering[ConceptualEntitySingletonInstanceUUID]
  = new Ordering[ConceptualEntitySingletonInstanceUUID] {
  	override def compare
  	(x: ConceptualEntitySingletonInstanceUUID, 
  	 y: ConceptualEntitySingletonInstanceUUID)
  	: Int = x.compareTo(y)
  }
  
  type ConceptualRelationshipUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptualRelationshipTag
  
  def conceptualRelationshipUUID(uuid: UUID): ConceptualRelationshipUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptualRelationshipTag][UUID](uuid)
  
  implicit val decodeConceptualRelationshipUUID: Decoder[ConceptualRelationshipUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptualRelationshipTag]
  
  implicit val encodeConceptualRelationshipUUID: Encoder[ConceptualRelationshipUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ConceptualRelationshipTag]

  implicit val orderingConceptualRelationshipUUID
  : Ordering[ConceptualRelationshipUUID]
  = new Ordering[ConceptualRelationshipUUID] {
  	override def compare
  	(x: ConceptualRelationshipUUID, 
  	 y: ConceptualRelationshipUUID)
  	: Int = x.compareTo(y)
  }
  
  type CrossReferencabilityKindUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.CrossReferencabilityKindTag
  
  def crossReferencabilityKindUUID(uuid: UUID): CrossReferencabilityKindUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.CrossReferencabilityKindTag][UUID](uuid)
  
  implicit val decodeCrossReferencabilityKindUUID: Decoder[CrossReferencabilityKindUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.CrossReferencabilityKindTag]
  
  implicit val encodeCrossReferencabilityKindUUID: Encoder[CrossReferencabilityKindUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.CrossReferencabilityKindTag]

  implicit val orderingCrossReferencabilityKindUUID
  : Ordering[CrossReferencabilityKindUUID]
  = new Ordering[CrossReferencabilityKindUUID] {
  	override def compare
  	(x: CrossReferencabilityKindUUID, 
  	 y: CrossReferencabilityKindUUID)
  	: Int = x.compareTo(y)
  }
  
  type CrossReferencableKindUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.CrossReferencableKindTag
  
  def crossReferencableKindUUID(uuid: UUID): CrossReferencableKindUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.CrossReferencableKindTag][UUID](uuid)
  
  implicit val decodeCrossReferencableKindUUID: Decoder[CrossReferencableKindUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.CrossReferencableKindTag]
  
  implicit val encodeCrossReferencableKindUUID: Encoder[CrossReferencableKindUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.CrossReferencableKindTag]

  implicit val orderingCrossReferencableKindUUID
  : Ordering[CrossReferencableKindUUID]
  = new Ordering[CrossReferencableKindUUID] {
  	override def compare
  	(x: CrossReferencableKindUUID, 
  	 y: CrossReferencableKindUUID)
  	: Int = x.compareTo(y)
  }
  
  type DataRangeUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRangeTag
  
  def dataRangeUUID(uuid: UUID): DataRangeUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRangeTag][UUID](uuid)
  
  implicit val decodeDataRangeUUID: Decoder[DataRangeUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRangeTag]
  
  implicit val encodeDataRangeUUID: Encoder[DataRangeUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRangeTag]

  implicit val orderingDataRangeUUID
  : Ordering[DataRangeUUID]
  = new Ordering[DataRangeUUID] {
  	override def compare
  	(x: DataRangeUUID, 
  	 y: DataRangeUUID)
  	: Int = x.compareTo(y)
  }
  
  type DataRelationshipUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipTag
  
  def dataRelationshipUUID(uuid: UUID): DataRelationshipUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipTag][UUID](uuid)
  
  implicit val decodeDataRelationshipUUID: Decoder[DataRelationshipUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipTag]
  
  implicit val encodeDataRelationshipUUID: Encoder[DataRelationshipUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipTag]

  implicit val orderingDataRelationshipUUID
  : Ordering[DataRelationshipUUID]
  = new Ordering[DataRelationshipUUID] {
  	override def compare
  	(x: DataRelationshipUUID, 
  	 y: DataRelationshipUUID)
  	: Int = x.compareTo(y)
  }
  
  type DataRelationshipDomainUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipDomainTag
  
  def dataRelationshipDomainUUID(uuid: UUID): DataRelationshipDomainUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipDomainTag][UUID](uuid)
  
  implicit val decodeDataRelationshipDomainUUID: Decoder[DataRelationshipDomainUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipDomainTag]
  
  implicit val encodeDataRelationshipDomainUUID: Encoder[DataRelationshipDomainUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipDomainTag]

  implicit val orderingDataRelationshipDomainUUID
  : Ordering[DataRelationshipDomainUUID]
  = new Ordering[DataRelationshipDomainUUID] {
  	override def compare
  	(x: DataRelationshipDomainUUID, 
  	 y: DataRelationshipDomainUUID)
  	: Int = x.compareTo(y)
  }
  
  type DataRelationshipFromEntityUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipFromEntityTag
  
  def dataRelationshipFromEntityUUID(uuid: UUID): DataRelationshipFromEntityUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipFromEntityTag][UUID](uuid)
  
  implicit val decodeDataRelationshipFromEntityUUID: Decoder[DataRelationshipFromEntityUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipFromEntityTag]
  
  implicit val encodeDataRelationshipFromEntityUUID: Encoder[DataRelationshipFromEntityUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipFromEntityTag]

  implicit val orderingDataRelationshipFromEntityUUID
  : Ordering[DataRelationshipFromEntityUUID]
  = new Ordering[DataRelationshipFromEntityUUID] {
  	override def compare
  	(x: DataRelationshipFromEntityUUID, 
  	 y: DataRelationshipFromEntityUUID)
  	: Int = x.compareTo(y)
  }
  
  type DataRelationshipFromStructureUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipFromStructureTag
  
  def dataRelationshipFromStructureUUID(uuid: UUID): DataRelationshipFromStructureUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipFromStructureTag][UUID](uuid)
  
  implicit val decodeDataRelationshipFromStructureUUID: Decoder[DataRelationshipFromStructureUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipFromStructureTag]
  
  implicit val encodeDataRelationshipFromStructureUUID: Encoder[DataRelationshipFromStructureUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipFromStructureTag]

  implicit val orderingDataRelationshipFromStructureUUID
  : Ordering[DataRelationshipFromStructureUUID]
  = new Ordering[DataRelationshipFromStructureUUID] {
  	override def compare
  	(x: DataRelationshipFromStructureUUID, 
  	 y: DataRelationshipFromStructureUUID)
  	: Int = x.compareTo(y)
  }
  
  type DataRelationshipRangeUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipRangeTag
  
  def dataRelationshipRangeUUID(uuid: UUID): DataRelationshipRangeUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipRangeTag][UUID](uuid)
  
  implicit val decodeDataRelationshipRangeUUID: Decoder[DataRelationshipRangeUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipRangeTag]
  
  implicit val encodeDataRelationshipRangeUUID: Encoder[DataRelationshipRangeUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipRangeTag]

  implicit val orderingDataRelationshipRangeUUID
  : Ordering[DataRelationshipRangeUUID]
  = new Ordering[DataRelationshipRangeUUID] {
  	override def compare
  	(x: DataRelationshipRangeUUID, 
  	 y: DataRelationshipRangeUUID)
  	: Int = x.compareTo(y)
  }
  
  type DataRelationshipToScalarUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipToScalarTag
  
  def dataRelationshipToScalarUUID(uuid: UUID): DataRelationshipToScalarUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipToScalarTag][UUID](uuid)
  
  implicit val decodeDataRelationshipToScalarUUID: Decoder[DataRelationshipToScalarUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipToScalarTag]
  
  implicit val encodeDataRelationshipToScalarUUID: Encoder[DataRelationshipToScalarUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipToScalarTag]

  implicit val orderingDataRelationshipToScalarUUID
  : Ordering[DataRelationshipToScalarUUID]
  = new Ordering[DataRelationshipToScalarUUID] {
  	override def compare
  	(x: DataRelationshipToScalarUUID, 
  	 y: DataRelationshipToScalarUUID)
  	: Int = x.compareTo(y)
  }
  
  type DataRelationshipToStructureUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipToStructureTag
  
  def dataRelationshipToStructureUUID(uuid: UUID): DataRelationshipToStructureUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipToStructureTag][UUID](uuid)
  
  implicit val decodeDataRelationshipToStructureUUID: Decoder[DataRelationshipToStructureUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipToStructureTag]
  
  implicit val encodeDataRelationshipToStructureUUID: Encoder[DataRelationshipToStructureUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DataRelationshipToStructureTag]

  implicit val orderingDataRelationshipToStructureUUID
  : Ordering[DataRelationshipToStructureUUID]
  = new Ordering[DataRelationshipToStructureUUID] {
  	override def compare
  	(x: DataRelationshipToStructureUUID, 
  	 y: DataRelationshipToStructureUUID)
  	: Int = x.compareTo(y)
  }
  
  type DatatypeUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DatatypeTag
  
  def datatypeUUID(uuid: UUID): DatatypeUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DatatypeTag][UUID](uuid)
  
  implicit val decodeDatatypeUUID: Decoder[DatatypeUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DatatypeTag]
  
  implicit val encodeDatatypeUUID: Encoder[DatatypeUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DatatypeTag]

  implicit val orderingDatatypeUUID
  : Ordering[DatatypeUUID]
  = new Ordering[DatatypeUUID] {
  	override def compare
  	(x: DatatypeUUID, 
  	 y: DatatypeUUID)
  	: Int = x.compareTo(y)
  }
  
  type DescriptionBoxUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxTag
  
  def descriptionBoxUUID(uuid: UUID): DescriptionBoxUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxTag][UUID](uuid)
  
  implicit val decodeDescriptionBoxUUID: Decoder[DescriptionBoxUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxTag]
  
  implicit val encodeDescriptionBoxUUID: Encoder[DescriptionBoxUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxTag]

  implicit val orderingDescriptionBoxUUID
  : Ordering[DescriptionBoxUUID]
  = new Ordering[DescriptionBoxUUID] {
  	override def compare
  	(x: DescriptionBoxUUID, 
  	 y: DescriptionBoxUUID)
  	: Int = x.compareTo(y)
  }
  
  type DescriptionBoxExtendsClosedWorldDefinitionsUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxExtendsClosedWorldDefinitionsTag
  
  def descriptionBoxExtendsClosedWorldDefinitionsUUID(uuid: UUID): DescriptionBoxExtendsClosedWorldDefinitionsUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxExtendsClosedWorldDefinitionsTag][UUID](uuid)
  
  implicit val decodeDescriptionBoxExtendsClosedWorldDefinitionsUUID: Decoder[DescriptionBoxExtendsClosedWorldDefinitionsUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxExtendsClosedWorldDefinitionsTag]
  
  implicit val encodeDescriptionBoxExtendsClosedWorldDefinitionsUUID: Encoder[DescriptionBoxExtendsClosedWorldDefinitionsUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxExtendsClosedWorldDefinitionsTag]

  implicit val orderingDescriptionBoxExtendsClosedWorldDefinitionsUUID
  : Ordering[DescriptionBoxExtendsClosedWorldDefinitionsUUID]
  = new Ordering[DescriptionBoxExtendsClosedWorldDefinitionsUUID] {
  	override def compare
  	(x: DescriptionBoxExtendsClosedWorldDefinitionsUUID, 
  	 y: DescriptionBoxExtendsClosedWorldDefinitionsUUID)
  	: Int = x.compareTo(y)
  }
  
  type DescriptionBoxRefinementUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxRefinementTag
  
  def descriptionBoxRefinementUUID(uuid: UUID): DescriptionBoxRefinementUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxRefinementTag][UUID](uuid)
  
  implicit val decodeDescriptionBoxRefinementUUID: Decoder[DescriptionBoxRefinementUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxRefinementTag]
  
  implicit val encodeDescriptionBoxRefinementUUID: Encoder[DescriptionBoxRefinementUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxRefinementTag]

  implicit val orderingDescriptionBoxRefinementUUID
  : Ordering[DescriptionBoxRefinementUUID]
  = new Ordering[DescriptionBoxRefinementUUID] {
  	override def compare
  	(x: DescriptionBoxRefinementUUID, 
  	 y: DescriptionBoxRefinementUUID)
  	: Int = x.compareTo(y)
  }
  
  type DescriptionBoxRelationshipUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxRelationshipTag
  
  def descriptionBoxRelationshipUUID(uuid: UUID): DescriptionBoxRelationshipUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxRelationshipTag][UUID](uuid)
  
  implicit val decodeDescriptionBoxRelationshipUUID: Decoder[DescriptionBoxRelationshipUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxRelationshipTag]
  
  implicit val encodeDescriptionBoxRelationshipUUID: Encoder[DescriptionBoxRelationshipUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DescriptionBoxRelationshipTag]

  implicit val orderingDescriptionBoxRelationshipUUID
  : Ordering[DescriptionBoxRelationshipUUID]
  = new Ordering[DescriptionBoxRelationshipUUID] {
  	override def compare
  	(x: DescriptionBoxRelationshipUUID, 
  	 y: DescriptionBoxRelationshipUUID)
  	: Int = x.compareTo(y)
  }
  
  type DirectedBinaryRelationshipKindUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DirectedBinaryRelationshipKindTag
  
  def directedBinaryRelationshipKindUUID(uuid: UUID): DirectedBinaryRelationshipKindUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DirectedBinaryRelationshipKindTag][UUID](uuid)
  
  implicit val decodeDirectedBinaryRelationshipKindUUID: Decoder[DirectedBinaryRelationshipKindUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DirectedBinaryRelationshipKindTag]
  
  implicit val encodeDirectedBinaryRelationshipKindUUID: Encoder[DirectedBinaryRelationshipKindUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DirectedBinaryRelationshipKindTag]

  implicit val orderingDirectedBinaryRelationshipKindUUID
  : Ordering[DirectedBinaryRelationshipKindUUID]
  = new Ordering[DirectedBinaryRelationshipKindUUID] {
  	override def compare
  	(x: DirectedBinaryRelationshipKindUUID, 
  	 y: DirectedBinaryRelationshipKindUUID)
  	: Int = x.compareTo(y)
  }
  
  type DisjointUnionOfConceptsAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.DisjointUnionOfConceptsAxiomTag
  
  def disjointUnionOfConceptsAxiomUUID(uuid: UUID): DisjointUnionOfConceptsAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DisjointUnionOfConceptsAxiomTag][UUID](uuid)
  
  implicit val decodeDisjointUnionOfConceptsAxiomUUID: Decoder[DisjointUnionOfConceptsAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DisjointUnionOfConceptsAxiomTag]
  
  implicit val encodeDisjointUnionOfConceptsAxiomUUID: Encoder[DisjointUnionOfConceptsAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.DisjointUnionOfConceptsAxiomTag]

  implicit val orderingDisjointUnionOfConceptsAxiomUUID
  : Ordering[DisjointUnionOfConceptsAxiomUUID]
  = new Ordering[DisjointUnionOfConceptsAxiomUUID] {
  	override def compare
  	(x: DisjointUnionOfConceptsAxiomUUID, 
  	 y: DisjointUnionOfConceptsAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type ElementCrossReferenceTupleUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ElementCrossReferenceTupleTag
  
  def elementCrossReferenceTupleUUID(uuid: UUID): ElementCrossReferenceTupleUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ElementCrossReferenceTupleTag][UUID](uuid)
  
  implicit val decodeElementCrossReferenceTupleUUID: Decoder[ElementCrossReferenceTupleUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ElementCrossReferenceTupleTag]
  
  implicit val encodeElementCrossReferenceTupleUUID: Encoder[ElementCrossReferenceTupleUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ElementCrossReferenceTupleTag]

  implicit val orderingElementCrossReferenceTupleUUID
  : Ordering[ElementCrossReferenceTupleUUID]
  = new Ordering[ElementCrossReferenceTupleUUID] {
  	override def compare
  	(x: ElementCrossReferenceTupleUUID, 
  	 y: ElementCrossReferenceTupleUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityTag
  
  def entityUUID(uuid: UUID): EntityUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityTag][UUID](uuid)
  
  implicit val decodeEntityUUID: Decoder[EntityUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityTag]
  
  implicit val encodeEntityUUID: Encoder[EntityUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityTag]

  implicit val orderingEntityUUID
  : Ordering[EntityUUID]
  = new Ordering[EntityUUID] {
  	override def compare
  	(x: EntityUUID, 
  	 y: EntityUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityExistentialRestrictionAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityExistentialRestrictionAxiomTag
  
  def entityExistentialRestrictionAxiomUUID(uuid: UUID): EntityExistentialRestrictionAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityExistentialRestrictionAxiomTag][UUID](uuid)
  
  implicit val decodeEntityExistentialRestrictionAxiomUUID: Decoder[EntityExistentialRestrictionAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityExistentialRestrictionAxiomTag]
  
  implicit val encodeEntityExistentialRestrictionAxiomUUID: Encoder[EntityExistentialRestrictionAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityExistentialRestrictionAxiomTag]

  implicit val orderingEntityExistentialRestrictionAxiomUUID
  : Ordering[EntityExistentialRestrictionAxiomUUID]
  = new Ordering[EntityExistentialRestrictionAxiomUUID] {
  	override def compare
  	(x: EntityExistentialRestrictionAxiomUUID, 
  	 y: EntityExistentialRestrictionAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityRelationshipUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityRelationshipTag
  
  def entityRelationshipUUID(uuid: UUID): EntityRelationshipUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityRelationshipTag][UUID](uuid)
  
  implicit val decodeEntityRelationshipUUID: Decoder[EntityRelationshipUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityRelationshipTag]
  
  implicit val encodeEntityRelationshipUUID: Encoder[EntityRelationshipUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityRelationshipTag]

  implicit val orderingEntityRelationshipUUID
  : Ordering[EntityRelationshipUUID]
  = new Ordering[EntityRelationshipUUID] {
  	override def compare
  	(x: EntityRelationshipUUID, 
  	 y: EntityRelationshipUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityRestrictionAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityRestrictionAxiomTag
  
  def entityRestrictionAxiomUUID(uuid: UUID): EntityRestrictionAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityRestrictionAxiomTag][UUID](uuid)
  
  implicit val decodeEntityRestrictionAxiomUUID: Decoder[EntityRestrictionAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityRestrictionAxiomTag]
  
  implicit val encodeEntityRestrictionAxiomUUID: Encoder[EntityRestrictionAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityRestrictionAxiomTag]

  implicit val orderingEntityRestrictionAxiomUUID
  : Ordering[EntityRestrictionAxiomUUID]
  = new Ordering[EntityRestrictionAxiomUUID] {
  	override def compare
  	(x: EntityRestrictionAxiomUUID, 
  	 y: EntityRestrictionAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityScalarDataPropertyUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyTag
  
  def entityScalarDataPropertyUUID(uuid: UUID): EntityScalarDataPropertyUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyTag][UUID](uuid)
  
  implicit val decodeEntityScalarDataPropertyUUID: Decoder[EntityScalarDataPropertyUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyTag]
  
  implicit val encodeEntityScalarDataPropertyUUID: Encoder[EntityScalarDataPropertyUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyTag]

  implicit val orderingEntityScalarDataPropertyUUID
  : Ordering[EntityScalarDataPropertyUUID]
  = new Ordering[EntityScalarDataPropertyUUID] {
  	override def compare
  	(x: EntityScalarDataPropertyUUID, 
  	 y: EntityScalarDataPropertyUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityScalarDataPropertyExistentialRestrictionAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyExistentialRestrictionAxiomTag
  
  def entityScalarDataPropertyExistentialRestrictionAxiomUUID(uuid: UUID): EntityScalarDataPropertyExistentialRestrictionAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyExistentialRestrictionAxiomTag][UUID](uuid)
  
  implicit val decodeEntityScalarDataPropertyExistentialRestrictionAxiomUUID: Decoder[EntityScalarDataPropertyExistentialRestrictionAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyExistentialRestrictionAxiomTag]
  
  implicit val encodeEntityScalarDataPropertyExistentialRestrictionAxiomUUID: Encoder[EntityScalarDataPropertyExistentialRestrictionAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyExistentialRestrictionAxiomTag]

  implicit val orderingEntityScalarDataPropertyExistentialRestrictionAxiomUUID
  : Ordering[EntityScalarDataPropertyExistentialRestrictionAxiomUUID]
  = new Ordering[EntityScalarDataPropertyExistentialRestrictionAxiomUUID] {
  	override def compare
  	(x: EntityScalarDataPropertyExistentialRestrictionAxiomUUID, 
  	 y: EntityScalarDataPropertyExistentialRestrictionAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityScalarDataPropertyParticularRestrictionAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyParticularRestrictionAxiomTag
  
  def entityScalarDataPropertyParticularRestrictionAxiomUUID(uuid: UUID): EntityScalarDataPropertyParticularRestrictionAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyParticularRestrictionAxiomTag][UUID](uuid)
  
  implicit val decodeEntityScalarDataPropertyParticularRestrictionAxiomUUID: Decoder[EntityScalarDataPropertyParticularRestrictionAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyParticularRestrictionAxiomTag]
  
  implicit val encodeEntityScalarDataPropertyParticularRestrictionAxiomUUID: Encoder[EntityScalarDataPropertyParticularRestrictionAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyParticularRestrictionAxiomTag]

  implicit val orderingEntityScalarDataPropertyParticularRestrictionAxiomUUID
  : Ordering[EntityScalarDataPropertyParticularRestrictionAxiomUUID]
  = new Ordering[EntityScalarDataPropertyParticularRestrictionAxiomUUID] {
  	override def compare
  	(x: EntityScalarDataPropertyParticularRestrictionAxiomUUID, 
  	 y: EntityScalarDataPropertyParticularRestrictionAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityScalarDataPropertyRestrictionAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyRestrictionAxiomTag
  
  def entityScalarDataPropertyRestrictionAxiomUUID(uuid: UUID): EntityScalarDataPropertyRestrictionAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyRestrictionAxiomTag][UUID](uuid)
  
  implicit val decodeEntityScalarDataPropertyRestrictionAxiomUUID: Decoder[EntityScalarDataPropertyRestrictionAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyRestrictionAxiomTag]
  
  implicit val encodeEntityScalarDataPropertyRestrictionAxiomUUID: Encoder[EntityScalarDataPropertyRestrictionAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyRestrictionAxiomTag]

  implicit val orderingEntityScalarDataPropertyRestrictionAxiomUUID
  : Ordering[EntityScalarDataPropertyRestrictionAxiomUUID]
  = new Ordering[EntityScalarDataPropertyRestrictionAxiomUUID] {
  	override def compare
  	(x: EntityScalarDataPropertyRestrictionAxiomUUID, 
  	 y: EntityScalarDataPropertyRestrictionAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityScalarDataPropertyUniversalRestrictionAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyUniversalRestrictionAxiomTag
  
  def entityScalarDataPropertyUniversalRestrictionAxiomUUID(uuid: UUID): EntityScalarDataPropertyUniversalRestrictionAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyUniversalRestrictionAxiomTag][UUID](uuid)
  
  implicit val decodeEntityScalarDataPropertyUniversalRestrictionAxiomUUID: Decoder[EntityScalarDataPropertyUniversalRestrictionAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyUniversalRestrictionAxiomTag]
  
  implicit val encodeEntityScalarDataPropertyUniversalRestrictionAxiomUUID: Encoder[EntityScalarDataPropertyUniversalRestrictionAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityScalarDataPropertyUniversalRestrictionAxiomTag]

  implicit val orderingEntityScalarDataPropertyUniversalRestrictionAxiomUUID
  : Ordering[EntityScalarDataPropertyUniversalRestrictionAxiomUUID]
  = new Ordering[EntityScalarDataPropertyUniversalRestrictionAxiomUUID] {
  	override def compare
  	(x: EntityScalarDataPropertyUniversalRestrictionAxiomUUID, 
  	 y: EntityScalarDataPropertyUniversalRestrictionAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityStructuredDataPropertyUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyTag
  
  def entityStructuredDataPropertyUUID(uuid: UUID): EntityStructuredDataPropertyUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyTag][UUID](uuid)
  
  implicit val decodeEntityStructuredDataPropertyUUID: Decoder[EntityStructuredDataPropertyUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyTag]
  
  implicit val encodeEntityStructuredDataPropertyUUID: Encoder[EntityStructuredDataPropertyUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyTag]

  implicit val orderingEntityStructuredDataPropertyUUID
  : Ordering[EntityStructuredDataPropertyUUID]
  = new Ordering[EntityStructuredDataPropertyUUID] {
  	override def compare
  	(x: EntityStructuredDataPropertyUUID, 
  	 y: EntityStructuredDataPropertyUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityStructuredDataPropertyParticularRestrictionAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyParticularRestrictionAxiomTag
  
  def entityStructuredDataPropertyParticularRestrictionAxiomUUID(uuid: UUID): EntityStructuredDataPropertyParticularRestrictionAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyParticularRestrictionAxiomTag][UUID](uuid)
  
  implicit val decodeEntityStructuredDataPropertyParticularRestrictionAxiomUUID: Decoder[EntityStructuredDataPropertyParticularRestrictionAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyParticularRestrictionAxiomTag]
  
  implicit val encodeEntityStructuredDataPropertyParticularRestrictionAxiomUUID: Encoder[EntityStructuredDataPropertyParticularRestrictionAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyParticularRestrictionAxiomTag]

  implicit val orderingEntityStructuredDataPropertyParticularRestrictionAxiomUUID
  : Ordering[EntityStructuredDataPropertyParticularRestrictionAxiomUUID]
  = new Ordering[EntityStructuredDataPropertyParticularRestrictionAxiomUUID] {
  	override def compare
  	(x: EntityStructuredDataPropertyParticularRestrictionAxiomUUID, 
  	 y: EntityStructuredDataPropertyParticularRestrictionAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityStructuredDataPropertyRestrictionAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyRestrictionAxiomTag
  
  def entityStructuredDataPropertyRestrictionAxiomUUID(uuid: UUID): EntityStructuredDataPropertyRestrictionAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyRestrictionAxiomTag][UUID](uuid)
  
  implicit val decodeEntityStructuredDataPropertyRestrictionAxiomUUID: Decoder[EntityStructuredDataPropertyRestrictionAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyRestrictionAxiomTag]
  
  implicit val encodeEntityStructuredDataPropertyRestrictionAxiomUUID: Encoder[EntityStructuredDataPropertyRestrictionAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityStructuredDataPropertyRestrictionAxiomTag]

  implicit val orderingEntityStructuredDataPropertyRestrictionAxiomUUID
  : Ordering[EntityStructuredDataPropertyRestrictionAxiomUUID]
  = new Ordering[EntityStructuredDataPropertyRestrictionAxiomUUID] {
  	override def compare
  	(x: EntityStructuredDataPropertyRestrictionAxiomUUID, 
  	 y: EntityStructuredDataPropertyRestrictionAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type EntityUniversalRestrictionAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityUniversalRestrictionAxiomTag
  
  def entityUniversalRestrictionAxiomUUID(uuid: UUID): EntityUniversalRestrictionAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityUniversalRestrictionAxiomTag][UUID](uuid)
  
  implicit val decodeEntityUniversalRestrictionAxiomUUID: Decoder[EntityUniversalRestrictionAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityUniversalRestrictionAxiomTag]
  
  implicit val encodeEntityUniversalRestrictionAxiomUUID: Encoder[EntityUniversalRestrictionAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.EntityUniversalRestrictionAxiomTag]

  implicit val orderingEntityUniversalRestrictionAxiomUUID
  : Ordering[EntityUniversalRestrictionAxiomUUID]
  = new Ordering[EntityUniversalRestrictionAxiomUUID] {
  	override def compare
  	(x: EntityUniversalRestrictionAxiomUUID, 
  	 y: EntityUniversalRestrictionAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type ExtrinsicIdentityKindUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ExtrinsicIdentityKindTag
  
  def extrinsicIdentityKindUUID(uuid: UUID): ExtrinsicIdentityKindUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ExtrinsicIdentityKindTag][UUID](uuid)
  
  implicit val decodeExtrinsicIdentityKindUUID: Decoder[ExtrinsicIdentityKindUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ExtrinsicIdentityKindTag]
  
  implicit val encodeExtrinsicIdentityKindUUID: Encoder[ExtrinsicIdentityKindUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ExtrinsicIdentityKindTag]

  implicit val orderingExtrinsicIdentityKindUUID
  : Ordering[ExtrinsicIdentityKindUUID]
  = new Ordering[ExtrinsicIdentityKindUUID] {
  	override def compare
  	(x: ExtrinsicIdentityKindUUID, 
  	 y: ExtrinsicIdentityKindUUID)
  	: Int = x.compareTo(y)
  }
  
  type ForwardPropertyUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ForwardPropertyTag
  
  def forwardPropertyUUID(uuid: UUID): ForwardPropertyUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ForwardPropertyTag][UUID](uuid)
  
  implicit val decodeForwardPropertyUUID: Decoder[ForwardPropertyUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ForwardPropertyTag]
  
  implicit val encodeForwardPropertyUUID: Encoder[ForwardPropertyUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ForwardPropertyTag]

  implicit val orderingForwardPropertyUUID
  : Ordering[ForwardPropertyUUID]
  = new Ordering[ForwardPropertyUUID] {
  	override def compare
  	(x: ForwardPropertyUUID, 
  	 y: ForwardPropertyUUID)
  	: Int = x.compareTo(y)
  }
  
  type IRIScalarRestrictionUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.IRIScalarRestrictionTag
  
  def iriScalarRestrictionUUID(uuid: UUID): IRIScalarRestrictionUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.IRIScalarRestrictionTag][UUID](uuid)
  
  implicit val decodeIRIScalarRestrictionUUID: Decoder[IRIScalarRestrictionUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.IRIScalarRestrictionTag]
  
  implicit val encodeIRIScalarRestrictionUUID: Encoder[IRIScalarRestrictionUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.IRIScalarRestrictionTag]

  implicit val orderingIRIScalarRestrictionUUID
  : Ordering[IRIScalarRestrictionUUID]
  = new Ordering[IRIScalarRestrictionUUID] {
  	override def compare
  	(x: IRIScalarRestrictionUUID, 
  	 y: IRIScalarRestrictionUUID)
  	: Int = x.compareTo(y)
  }
  
  type IdentityKindUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.IdentityKindTag
  
  def identityKindUUID(uuid: UUID): IdentityKindUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.IdentityKindTag][UUID](uuid)
  
  implicit val decodeIdentityKindUUID: Decoder[IdentityKindUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.IdentityKindTag]
  
  implicit val encodeIdentityKindUUID: Encoder[IdentityKindUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.IdentityKindTag]

  implicit val orderingIdentityKindUUID
  : Ordering[IdentityKindUUID]
  = new Ordering[IdentityKindUUID] {
  	override def compare
  	(x: IdentityKindUUID, 
  	 y: IdentityKindUUID)
  	: Int = x.compareTo(y)
  }
  
  type IntrinsicIdentityKindUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.IntrinsicIdentityKindTag
  
  def intrinsicIdentityKindUUID(uuid: UUID): IntrinsicIdentityKindUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.IntrinsicIdentityKindTag][UUID](uuid)
  
  implicit val decodeIntrinsicIdentityKindUUID: Decoder[IntrinsicIdentityKindUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.IntrinsicIdentityKindTag]
  
  implicit val encodeIntrinsicIdentityKindUUID: Encoder[IntrinsicIdentityKindUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.IntrinsicIdentityKindTag]

  implicit val orderingIntrinsicIdentityKindUUID
  : Ordering[IntrinsicIdentityKindUUID]
  = new Ordering[IntrinsicIdentityKindUUID] {
  	override def compare
  	(x: IntrinsicIdentityKindUUID, 
  	 y: IntrinsicIdentityKindUUID)
  	: Int = x.compareTo(y)
  }
  
  type InversePropertyUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.InversePropertyTag
  
  def inversePropertyUUID(uuid: UUID): InversePropertyUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.InversePropertyTag][UUID](uuid)
  
  implicit val decodeInversePropertyUUID: Decoder[InversePropertyUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.InversePropertyTag]
  
  implicit val encodeInversePropertyUUID: Encoder[InversePropertyUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.InversePropertyTag]

  implicit val orderingInversePropertyUUID
  : Ordering[InversePropertyUUID]
  = new Ordering[InversePropertyUUID] {
  	override def compare
  	(x: InversePropertyUUID, 
  	 y: InversePropertyUUID)
  	: Int = x.compareTo(y)
  }
  
  type LogicalElementUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.LogicalElementTag
  
  def logicalElementUUID(uuid: UUID): LogicalElementUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LogicalElementTag][UUID](uuid)
  
  implicit val decodeLogicalElementUUID: Decoder[LogicalElementUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LogicalElementTag]
  
  implicit val encodeLogicalElementUUID: Encoder[LogicalElementUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.LogicalElementTag]

  implicit val orderingLogicalElementUUID
  : Ordering[LogicalElementUUID]
  = new Ordering[LogicalElementUUID] {
  	override def compare
  	(x: LogicalElementUUID, 
  	 y: LogicalElementUUID)
  	: Int = x.compareTo(y)
  }
  
  type ModuleUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleTag
  
  def moduleUUID(uuid: UUID): ModuleUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleTag][UUID](uuid)
  
  implicit val decodeModuleUUID: Decoder[ModuleUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleTag]
  
  implicit val encodeModuleUUID: Encoder[ModuleUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleTag]

  implicit val orderingModuleUUID
  : Ordering[ModuleUUID]
  = new Ordering[ModuleUUID] {
  	override def compare
  	(x: ModuleUUID, 
  	 y: ModuleUUID)
  	: Int = x.compareTo(y)
  }
  
  type ModuleEdgeUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleEdgeTag
  
  def moduleEdgeUUID(uuid: UUID): ModuleEdgeUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleEdgeTag][UUID](uuid)
  
  implicit val decodeModuleEdgeUUID: Decoder[ModuleEdgeUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleEdgeTag]
  
  implicit val encodeModuleEdgeUUID: Encoder[ModuleEdgeUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleEdgeTag]

  implicit val orderingModuleEdgeUUID
  : Ordering[ModuleEdgeUUID]
  = new Ordering[ModuleEdgeUUID] {
  	override def compare
  	(x: ModuleEdgeUUID, 
  	 y: ModuleEdgeUUID)
  	: Int = x.compareTo(y)
  }
  
  type ModuleElementUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleElementTag
  
  def moduleElementUUID(uuid: UUID): ModuleElementUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleElementTag][UUID](uuid)
  
  implicit val decodeModuleElementUUID: Decoder[ModuleElementUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleElementTag]
  
  implicit val encodeModuleElementUUID: Encoder[ModuleElementUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ModuleElementTag]

  implicit val orderingModuleElementUUID
  : Ordering[ModuleElementUUID]
  = new Ordering[ModuleElementUUID] {
  	override def compare
  	(x: ModuleElementUUID, 
  	 y: ModuleElementUUID)
  	: Int = x.compareTo(y)
  }
  
  type NonCrossReferencableKindUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.NonCrossReferencableKindTag
  
  def nonCrossReferencableKindUUID(uuid: UUID): NonCrossReferencableKindUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.NonCrossReferencableKindTag][UUID](uuid)
  
  implicit val decodeNonCrossReferencableKindUUID: Decoder[NonCrossReferencableKindUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.NonCrossReferencableKindTag]
  
  implicit val encodeNonCrossReferencableKindUUID: Encoder[NonCrossReferencableKindUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.NonCrossReferencableKindTag]

  implicit val orderingNonCrossReferencableKindUUID
  : Ordering[NonCrossReferencableKindUUID]
  = new Ordering[NonCrossReferencableKindUUID] {
  	override def compare
  	(x: NonCrossReferencableKindUUID, 
  	 y: NonCrossReferencableKindUUID)
  	: Int = x.compareTo(y)
  }
  
  type NonLogicalElementUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.NonLogicalElementTag
  
  def nonLogicalElementUUID(uuid: UUID): NonLogicalElementUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.NonLogicalElementTag][UUID](uuid)
  
  implicit val decodeNonLogicalElementUUID: Decoder[NonLogicalElementUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.NonLogicalElementTag]
  
  implicit val encodeNonLogicalElementUUID: Encoder[NonLogicalElementUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.NonLogicalElementTag]

  implicit val orderingNonLogicalElementUUID
  : Ordering[NonLogicalElementUUID]
  = new Ordering[NonLogicalElementUUID] {
  	override def compare
  	(x: NonLogicalElementUUID, 
  	 y: NonLogicalElementUUID)
  	: Int = x.compareTo(y)
  }
  
  type NumericScalarRestrictionUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.NumericScalarRestrictionTag
  
  def numericScalarRestrictionUUID(uuid: UUID): NumericScalarRestrictionUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.NumericScalarRestrictionTag][UUID](uuid)
  
  implicit val decodeNumericScalarRestrictionUUID: Decoder[NumericScalarRestrictionUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.NumericScalarRestrictionTag]
  
  implicit val encodeNumericScalarRestrictionUUID: Encoder[NumericScalarRestrictionUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.NumericScalarRestrictionTag]

  implicit val orderingNumericScalarRestrictionUUID
  : Ordering[NumericScalarRestrictionUUID]
  = new Ordering[NumericScalarRestrictionUUID] {
  	override def compare
  	(x: NumericScalarRestrictionUUID, 
  	 y: NumericScalarRestrictionUUID)
  	: Int = x.compareTo(y)
  }
  
  type PlainLiteralScalarRestrictionUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.PlainLiteralScalarRestrictionTag
  
  def plainLiteralScalarRestrictionUUID(uuid: UUID): PlainLiteralScalarRestrictionUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.PlainLiteralScalarRestrictionTag][UUID](uuid)
  
  implicit val decodePlainLiteralScalarRestrictionUUID: Decoder[PlainLiteralScalarRestrictionUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.PlainLiteralScalarRestrictionTag]
  
  implicit val encodePlainLiteralScalarRestrictionUUID: Encoder[PlainLiteralScalarRestrictionUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.PlainLiteralScalarRestrictionTag]

  implicit val orderingPlainLiteralScalarRestrictionUUID
  : Ordering[PlainLiteralScalarRestrictionUUID]
  = new Ordering[PlainLiteralScalarRestrictionUUID] {
  	override def compare
  	(x: PlainLiteralScalarRestrictionUUID, 
  	 y: PlainLiteralScalarRestrictionUUID)
  	: Int = x.compareTo(y)
  }
  
  type PredicateUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.PredicateTag
  
  def predicateUUID(uuid: UUID): PredicateUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.PredicateTag][UUID](uuid)
  
  implicit val decodePredicateUUID: Decoder[PredicateUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.PredicateTag]
  
  implicit val encodePredicateUUID: Encoder[PredicateUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.PredicateTag]

  implicit val orderingPredicateUUID
  : Ordering[PredicateUUID]
  = new Ordering[PredicateUUID] {
  	override def compare
  	(x: PredicateUUID, 
  	 y: PredicateUUID)
  	: Int = x.compareTo(y)
  }
  
  type ReifiedRelationshipUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipTag
  
  def reifiedRelationshipUUID(uuid: UUID): ReifiedRelationshipUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipTag][UUID](uuid)
  
  implicit val decodeReifiedRelationshipUUID: Decoder[ReifiedRelationshipUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipTag]
  
  implicit val encodeReifiedRelationshipUUID: Encoder[ReifiedRelationshipUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipTag]

  implicit val orderingReifiedRelationshipUUID
  : Ordering[ReifiedRelationshipUUID]
  = new Ordering[ReifiedRelationshipUUID] {
  	override def compare
  	(x: ReifiedRelationshipUUID, 
  	 y: ReifiedRelationshipUUID)
  	: Int = x.compareTo(y)
  }
  
  type ReifiedRelationshipInstanceUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceTag
  
  def reifiedRelationshipInstanceUUID(uuid: UUID): ReifiedRelationshipInstanceUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceTag][UUID](uuid)
  
  implicit val decodeReifiedRelationshipInstanceUUID: Decoder[ReifiedRelationshipInstanceUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceTag]
  
  implicit val encodeReifiedRelationshipInstanceUUID: Encoder[ReifiedRelationshipInstanceUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceTag]

  implicit val orderingReifiedRelationshipInstanceUUID
  : Ordering[ReifiedRelationshipInstanceUUID]
  = new Ordering[ReifiedRelationshipInstanceUUID] {
  	override def compare
  	(x: ReifiedRelationshipInstanceUUID, 
  	 y: ReifiedRelationshipInstanceUUID)
  	: Int = x.compareTo(y)
  }
  
  type ReifiedRelationshipInstanceDomainUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceDomainTag
  
  def reifiedRelationshipInstanceDomainUUID(uuid: UUID): ReifiedRelationshipInstanceDomainUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceDomainTag][UUID](uuid)
  
  implicit val decodeReifiedRelationshipInstanceDomainUUID: Decoder[ReifiedRelationshipInstanceDomainUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceDomainTag]
  
  implicit val encodeReifiedRelationshipInstanceDomainUUID: Encoder[ReifiedRelationshipInstanceDomainUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceDomainTag]

  implicit val orderingReifiedRelationshipInstanceDomainUUID
  : Ordering[ReifiedRelationshipInstanceDomainUUID]
  = new Ordering[ReifiedRelationshipInstanceDomainUUID] {
  	override def compare
  	(x: ReifiedRelationshipInstanceDomainUUID, 
  	 y: ReifiedRelationshipInstanceDomainUUID)
  	: Int = x.compareTo(y)
  }
  
  type ReifiedRelationshipInstanceRangeUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceRangeTag
  
  def reifiedRelationshipInstanceRangeUUID(uuid: UUID): ReifiedRelationshipInstanceRangeUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceRangeTag][UUID](uuid)
  
  implicit val decodeReifiedRelationshipInstanceRangeUUID: Decoder[ReifiedRelationshipInstanceRangeUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceRangeTag]
  
  implicit val encodeReifiedRelationshipInstanceRangeUUID: Encoder[ReifiedRelationshipInstanceRangeUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipInstanceRangeTag]

  implicit val orderingReifiedRelationshipInstanceRangeUUID
  : Ordering[ReifiedRelationshipInstanceRangeUUID]
  = new Ordering[ReifiedRelationshipInstanceRangeUUID] {
  	override def compare
  	(x: ReifiedRelationshipInstanceRangeUUID, 
  	 y: ReifiedRelationshipInstanceRangeUUID)
  	: Int = x.compareTo(y)
  }
  
  type ReifiedRelationshipRestrictionUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipRestrictionTag
  
  def reifiedRelationshipRestrictionUUID(uuid: UUID): ReifiedRelationshipRestrictionUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipRestrictionTag][UUID](uuid)
  
  implicit val decodeReifiedRelationshipRestrictionUUID: Decoder[ReifiedRelationshipRestrictionUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipRestrictionTag]
  
  implicit val encodeReifiedRelationshipRestrictionUUID: Encoder[ReifiedRelationshipRestrictionUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipRestrictionTag]

  implicit val orderingReifiedRelationshipRestrictionUUID
  : Ordering[ReifiedRelationshipRestrictionUUID]
  = new Ordering[ReifiedRelationshipRestrictionUUID] {
  	override def compare
  	(x: ReifiedRelationshipRestrictionUUID, 
  	 y: ReifiedRelationshipRestrictionUUID)
  	: Int = x.compareTo(y)
  }
  
  type ReifiedRelationshipSpecializationAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipSpecializationAxiomTag
  
  def reifiedRelationshipSpecializationAxiomUUID(uuid: UUID): ReifiedRelationshipSpecializationAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipSpecializationAxiomTag][UUID](uuid)
  
  implicit val decodeReifiedRelationshipSpecializationAxiomUUID: Decoder[ReifiedRelationshipSpecializationAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipSpecializationAxiomTag]
  
  implicit val encodeReifiedRelationshipSpecializationAxiomUUID: Encoder[ReifiedRelationshipSpecializationAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ReifiedRelationshipSpecializationAxiomTag]

  implicit val orderingReifiedRelationshipSpecializationAxiomUUID
  : Ordering[ReifiedRelationshipSpecializationAxiomUUID]
  = new Ordering[ReifiedRelationshipSpecializationAxiomUUID] {
  	override def compare
  	(x: ReifiedRelationshipSpecializationAxiomUUID, 
  	 y: ReifiedRelationshipSpecializationAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type ResourceUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ResourceTag
  
  def resourceUUID(uuid: UUID): ResourceUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ResourceTag][UUID](uuid)
  
  implicit val decodeResourceUUID: Decoder[ResourceUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ResourceTag]
  
  implicit val encodeResourceUUID: Encoder[ResourceUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ResourceTag]

  implicit val orderingResourceUUID
  : Ordering[ResourceUUID]
  = new Ordering[ResourceUUID] {
  	override def compare
  	(x: ResourceUUID, 
  	 y: ResourceUUID)
  	: Int = x.compareTo(y)
  }
  
  type RestrictableRelationshipUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictableRelationshipTag
  
  def restrictableRelationshipUUID(uuid: UUID): RestrictableRelationshipUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictableRelationshipTag][UUID](uuid)
  
  implicit val decodeRestrictableRelationshipUUID: Decoder[RestrictableRelationshipUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictableRelationshipTag]
  
  implicit val encodeRestrictableRelationshipUUID: Encoder[RestrictableRelationshipUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictableRelationshipTag]

  implicit val orderingRestrictableRelationshipUUID
  : Ordering[RestrictableRelationshipUUID]
  = new Ordering[RestrictableRelationshipUUID] {
  	override def compare
  	(x: RestrictableRelationshipUUID, 
  	 y: RestrictableRelationshipUUID)
  	: Int = x.compareTo(y)
  }
  
  type RestrictedDataRangeUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictedDataRangeTag
  
  def restrictedDataRangeUUID(uuid: UUID): RestrictedDataRangeUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictedDataRangeTag][UUID](uuid)
  
  implicit val decodeRestrictedDataRangeUUID: Decoder[RestrictedDataRangeUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictedDataRangeTag]
  
  implicit val encodeRestrictedDataRangeUUID: Encoder[RestrictedDataRangeUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictedDataRangeTag]

  implicit val orderingRestrictedDataRangeUUID
  : Ordering[RestrictedDataRangeUUID]
  = new Ordering[RestrictedDataRangeUUID] {
  	override def compare
  	(x: RestrictedDataRangeUUID, 
  	 y: RestrictedDataRangeUUID)
  	: Int = x.compareTo(y)
  }
  
  type RestrictionScalarDataPropertyValueUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionScalarDataPropertyValueTag
  
  def restrictionScalarDataPropertyValueUUID(uuid: UUID): RestrictionScalarDataPropertyValueUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionScalarDataPropertyValueTag][UUID](uuid)
  
  implicit val decodeRestrictionScalarDataPropertyValueUUID: Decoder[RestrictionScalarDataPropertyValueUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionScalarDataPropertyValueTag]
  
  implicit val encodeRestrictionScalarDataPropertyValueUUID: Encoder[RestrictionScalarDataPropertyValueUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionScalarDataPropertyValueTag]

  implicit val orderingRestrictionScalarDataPropertyValueUUID
  : Ordering[RestrictionScalarDataPropertyValueUUID]
  = new Ordering[RestrictionScalarDataPropertyValueUUID] {
  	override def compare
  	(x: RestrictionScalarDataPropertyValueUUID, 
  	 y: RestrictionScalarDataPropertyValueUUID)
  	: Int = x.compareTo(y)
  }
  
  type RestrictionStructuredDataPropertyContextUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionStructuredDataPropertyContextTag
  
  def restrictionStructuredDataPropertyContextUUID(uuid: UUID): RestrictionStructuredDataPropertyContextUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionStructuredDataPropertyContextTag][UUID](uuid)
  
  implicit val decodeRestrictionStructuredDataPropertyContextUUID: Decoder[RestrictionStructuredDataPropertyContextUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionStructuredDataPropertyContextTag]
  
  implicit val encodeRestrictionStructuredDataPropertyContextUUID: Encoder[RestrictionStructuredDataPropertyContextUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionStructuredDataPropertyContextTag]

  implicit val orderingRestrictionStructuredDataPropertyContextUUID
  : Ordering[RestrictionStructuredDataPropertyContextUUID]
  = new Ordering[RestrictionStructuredDataPropertyContextUUID] {
  	override def compare
  	(x: RestrictionStructuredDataPropertyContextUUID, 
  	 y: RestrictionStructuredDataPropertyContextUUID)
  	: Int = x.compareTo(y)
  }
  
  type RestrictionStructuredDataPropertyTupleUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionStructuredDataPropertyTupleTag
  
  def restrictionStructuredDataPropertyTupleUUID(uuid: UUID): RestrictionStructuredDataPropertyTupleUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionStructuredDataPropertyTupleTag][UUID](uuid)
  
  implicit val decodeRestrictionStructuredDataPropertyTupleUUID: Decoder[RestrictionStructuredDataPropertyTupleUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionStructuredDataPropertyTupleTag]
  
  implicit val encodeRestrictionStructuredDataPropertyTupleUUID: Encoder[RestrictionStructuredDataPropertyTupleUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RestrictionStructuredDataPropertyTupleTag]

  implicit val orderingRestrictionStructuredDataPropertyTupleUUID
  : Ordering[RestrictionStructuredDataPropertyTupleUUID]
  = new Ordering[RestrictionStructuredDataPropertyTupleUUID] {
  	override def compare
  	(x: RestrictionStructuredDataPropertyTupleUUID, 
  	 y: RestrictionStructuredDataPropertyTupleUUID)
  	: Int = x.compareTo(y)
  }
  
  type RootConceptTaxonomyAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.RootConceptTaxonomyAxiomTag
  
  def rootConceptTaxonomyAxiomUUID(uuid: UUID): RootConceptTaxonomyAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RootConceptTaxonomyAxiomTag][UUID](uuid)
  
  implicit val decodeRootConceptTaxonomyAxiomUUID: Decoder[RootConceptTaxonomyAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RootConceptTaxonomyAxiomTag]
  
  implicit val encodeRootConceptTaxonomyAxiomUUID: Encoder[RootConceptTaxonomyAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RootConceptTaxonomyAxiomTag]

  implicit val orderingRootConceptTaxonomyAxiomUUID
  : Ordering[RootConceptTaxonomyAxiomUUID]
  = new Ordering[RootConceptTaxonomyAxiomUUID] {
  	override def compare
  	(x: RootConceptTaxonomyAxiomUUID, 
  	 y: RootConceptTaxonomyAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type RuleUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.RuleTag
  
  def ruleUUID(uuid: UUID): RuleUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RuleTag][UUID](uuid)
  
  implicit val decodeRuleUUID: Decoder[RuleUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RuleTag]
  
  implicit val encodeRuleUUID: Encoder[RuleUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RuleTag]

  implicit val orderingRuleUUID
  : Ordering[RuleUUID]
  = new Ordering[RuleUUID] {
  	override def compare
  	(x: RuleUUID, 
  	 y: RuleUUID)
  	: Int = x.compareTo(y)
  }
  
  type RuleBodySegmentUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.RuleBodySegmentTag
  
  def ruleBodySegmentUUID(uuid: UUID): RuleBodySegmentUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RuleBodySegmentTag][UUID](uuid)
  
  implicit val decodeRuleBodySegmentUUID: Decoder[RuleBodySegmentUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RuleBodySegmentTag]
  
  implicit val encodeRuleBodySegmentUUID: Encoder[RuleBodySegmentUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.RuleBodySegmentTag]

  implicit val orderingRuleBodySegmentUUID
  : Ordering[RuleBodySegmentUUID]
  = new Ordering[RuleBodySegmentUUID] {
  	override def compare
  	(x: RuleBodySegmentUUID, 
  	 y: RuleBodySegmentUUID)
  	: Int = x.compareTo(y)
  }
  
  type ScalarUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarTag
  
  def scalarUUID(uuid: UUID): ScalarUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarTag][UUID](uuid)
  
  implicit val decodeScalarUUID: Decoder[ScalarUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarTag]
  
  implicit val encodeScalarUUID: Encoder[ScalarUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarTag]

  implicit val orderingScalarUUID
  : Ordering[ScalarUUID]
  = new Ordering[ScalarUUID] {
  	override def compare
  	(x: ScalarUUID, 
  	 y: ScalarUUID)
  	: Int = x.compareTo(y)
  }
  
  type ScalarDataPropertyUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarDataPropertyTag
  
  def scalarDataPropertyUUID(uuid: UUID): ScalarDataPropertyUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarDataPropertyTag][UUID](uuid)
  
  implicit val decodeScalarDataPropertyUUID: Decoder[ScalarDataPropertyUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarDataPropertyTag]
  
  implicit val encodeScalarDataPropertyUUID: Encoder[ScalarDataPropertyUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarDataPropertyTag]

  implicit val orderingScalarDataPropertyUUID
  : Ordering[ScalarDataPropertyUUID]
  = new Ordering[ScalarDataPropertyUUID] {
  	override def compare
  	(x: ScalarDataPropertyUUID, 
  	 y: ScalarDataPropertyUUID)
  	: Int = x.compareTo(y)
  }
  
  type ScalarDataPropertyValueUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarDataPropertyValueTag
  
  def scalarDataPropertyValueUUID(uuid: UUID): ScalarDataPropertyValueUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarDataPropertyValueTag][UUID](uuid)
  
  implicit val decodeScalarDataPropertyValueUUID: Decoder[ScalarDataPropertyValueUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarDataPropertyValueTag]
  
  implicit val encodeScalarDataPropertyValueUUID: Encoder[ScalarDataPropertyValueUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarDataPropertyValueTag]

  implicit val orderingScalarDataPropertyValueUUID
  : Ordering[ScalarDataPropertyValueUUID]
  = new Ordering[ScalarDataPropertyValueUUID] {
  	override def compare
  	(x: ScalarDataPropertyValueUUID, 
  	 y: ScalarDataPropertyValueUUID)
  	: Int = x.compareTo(y)
  }
  
  type ScalarOneOfLiteralAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarOneOfLiteralAxiomTag
  
  def scalarOneOfLiteralAxiomUUID(uuid: UUID): ScalarOneOfLiteralAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarOneOfLiteralAxiomTag][UUID](uuid)
  
  implicit val decodeScalarOneOfLiteralAxiomUUID: Decoder[ScalarOneOfLiteralAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarOneOfLiteralAxiomTag]
  
  implicit val encodeScalarOneOfLiteralAxiomUUID: Encoder[ScalarOneOfLiteralAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarOneOfLiteralAxiomTag]

  implicit val orderingScalarOneOfLiteralAxiomUUID
  : Ordering[ScalarOneOfLiteralAxiomUUID]
  = new Ordering[ScalarOneOfLiteralAxiomUUID] {
  	override def compare
  	(x: ScalarOneOfLiteralAxiomUUID, 
  	 y: ScalarOneOfLiteralAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type ScalarOneOfRestrictionUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarOneOfRestrictionTag
  
  def scalarOneOfRestrictionUUID(uuid: UUID): ScalarOneOfRestrictionUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarOneOfRestrictionTag][UUID](uuid)
  
  implicit val decodeScalarOneOfRestrictionUUID: Decoder[ScalarOneOfRestrictionUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarOneOfRestrictionTag]
  
  implicit val encodeScalarOneOfRestrictionUUID: Encoder[ScalarOneOfRestrictionUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ScalarOneOfRestrictionTag]

  implicit val orderingScalarOneOfRestrictionUUID
  : Ordering[ScalarOneOfRestrictionUUID]
  = new Ordering[ScalarOneOfRestrictionUUID] {
  	override def compare
  	(x: ScalarOneOfRestrictionUUID, 
  	 y: ScalarOneOfRestrictionUUID)
  	: Int = x.compareTo(y)
  }
  
  type SegmentPredicateUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.SegmentPredicateTag
  
  def segmentPredicateUUID(uuid: UUID): SegmentPredicateUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SegmentPredicateTag][UUID](uuid)
  
  implicit val decodeSegmentPredicateUUID: Decoder[SegmentPredicateUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SegmentPredicateTag]
  
  implicit val encodeSegmentPredicateUUID: Encoder[SegmentPredicateUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SegmentPredicateTag]

  implicit val orderingSegmentPredicateUUID
  : Ordering[SegmentPredicateUUID]
  = new Ordering[SegmentPredicateUUID] {
  	override def compare
  	(x: SegmentPredicateUUID, 
  	 y: SegmentPredicateUUID)
  	: Int = x.compareTo(y)
  }
  
  type SingletonInstanceScalarDataPropertyValueUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceScalarDataPropertyValueTag
  
  def singletonInstanceScalarDataPropertyValueUUID(uuid: UUID): SingletonInstanceScalarDataPropertyValueUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceScalarDataPropertyValueTag][UUID](uuid)
  
  implicit val decodeSingletonInstanceScalarDataPropertyValueUUID: Decoder[SingletonInstanceScalarDataPropertyValueUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceScalarDataPropertyValueTag]
  
  implicit val encodeSingletonInstanceScalarDataPropertyValueUUID: Encoder[SingletonInstanceScalarDataPropertyValueUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceScalarDataPropertyValueTag]

  implicit val orderingSingletonInstanceScalarDataPropertyValueUUID
  : Ordering[SingletonInstanceScalarDataPropertyValueUUID]
  = new Ordering[SingletonInstanceScalarDataPropertyValueUUID] {
  	override def compare
  	(x: SingletonInstanceScalarDataPropertyValueUUID, 
  	 y: SingletonInstanceScalarDataPropertyValueUUID)
  	: Int = x.compareTo(y)
  }
  
  type SingletonInstanceStructuredDataPropertyContextUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceStructuredDataPropertyContextTag
  
  def singletonInstanceStructuredDataPropertyContextUUID(uuid: UUID): SingletonInstanceStructuredDataPropertyContextUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceStructuredDataPropertyContextTag][UUID](uuid)
  
  implicit val decodeSingletonInstanceStructuredDataPropertyContextUUID: Decoder[SingletonInstanceStructuredDataPropertyContextUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceStructuredDataPropertyContextTag]
  
  implicit val encodeSingletonInstanceStructuredDataPropertyContextUUID: Encoder[SingletonInstanceStructuredDataPropertyContextUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceStructuredDataPropertyContextTag]

  implicit val orderingSingletonInstanceStructuredDataPropertyContextUUID
  : Ordering[SingletonInstanceStructuredDataPropertyContextUUID]
  = new Ordering[SingletonInstanceStructuredDataPropertyContextUUID] {
  	override def compare
  	(x: SingletonInstanceStructuredDataPropertyContextUUID, 
  	 y: SingletonInstanceStructuredDataPropertyContextUUID)
  	: Int = x.compareTo(y)
  }
  
  type SingletonInstanceStructuredDataPropertyValueUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceStructuredDataPropertyValueTag
  
  def singletonInstanceStructuredDataPropertyValueUUID(uuid: UUID): SingletonInstanceStructuredDataPropertyValueUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceStructuredDataPropertyValueTag][UUID](uuid)
  
  implicit val decodeSingletonInstanceStructuredDataPropertyValueUUID: Decoder[SingletonInstanceStructuredDataPropertyValueUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceStructuredDataPropertyValueTag]
  
  implicit val encodeSingletonInstanceStructuredDataPropertyValueUUID: Encoder[SingletonInstanceStructuredDataPropertyValueUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SingletonInstanceStructuredDataPropertyValueTag]

  implicit val orderingSingletonInstanceStructuredDataPropertyValueUUID
  : Ordering[SingletonInstanceStructuredDataPropertyValueUUID]
  = new Ordering[SingletonInstanceStructuredDataPropertyValueUUID] {
  	override def compare
  	(x: SingletonInstanceStructuredDataPropertyValueUUID, 
  	 y: SingletonInstanceStructuredDataPropertyValueUUID)
  	: Int = x.compareTo(y)
  }
  
  type SpecializationAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.SpecializationAxiomTag
  
  def specializationAxiomUUID(uuid: UUID): SpecializationAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SpecializationAxiomTag][UUID](uuid)
  
  implicit val decodeSpecializationAxiomUUID: Decoder[SpecializationAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SpecializationAxiomTag]
  
  implicit val encodeSpecializationAxiomUUID: Encoder[SpecializationAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SpecializationAxiomTag]

  implicit val orderingSpecializationAxiomUUID
  : Ordering[SpecializationAxiomUUID]
  = new Ordering[SpecializationAxiomUUID] {
  	override def compare
  	(x: SpecializationAxiomUUID, 
  	 y: SpecializationAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type SpecificDisjointConceptAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.SpecificDisjointConceptAxiomTag
  
  def specificDisjointConceptAxiomUUID(uuid: UUID): SpecificDisjointConceptAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SpecificDisjointConceptAxiomTag][UUID](uuid)
  
  implicit val decodeSpecificDisjointConceptAxiomUUID: Decoder[SpecificDisjointConceptAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SpecificDisjointConceptAxiomTag]
  
  implicit val encodeSpecificDisjointConceptAxiomUUID: Encoder[SpecificDisjointConceptAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SpecificDisjointConceptAxiomTag]

  implicit val orderingSpecificDisjointConceptAxiomUUID
  : Ordering[SpecificDisjointConceptAxiomUUID]
  = new Ordering[SpecificDisjointConceptAxiomUUID] {
  	override def compare
  	(x: SpecificDisjointConceptAxiomUUID, 
  	 y: SpecificDisjointConceptAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type StringScalarRestrictionUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.StringScalarRestrictionTag
  
  def stringScalarRestrictionUUID(uuid: UUID): StringScalarRestrictionUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StringScalarRestrictionTag][UUID](uuid)
  
  implicit val decodeStringScalarRestrictionUUID: Decoder[StringScalarRestrictionUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StringScalarRestrictionTag]
  
  implicit val encodeStringScalarRestrictionUUID: Encoder[StringScalarRestrictionUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StringScalarRestrictionTag]

  implicit val orderingStringScalarRestrictionUUID
  : Ordering[StringScalarRestrictionUUID]
  = new Ordering[StringScalarRestrictionUUID] {
  	override def compare
  	(x: StringScalarRestrictionUUID, 
  	 y: StringScalarRestrictionUUID)
  	: Int = x.compareTo(y)
  }
  
  type StructureUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.StructureTag
  
  def structureUUID(uuid: UUID): StructureUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StructureTag][UUID](uuid)
  
  implicit val decodeStructureUUID: Decoder[StructureUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StructureTag]
  
  implicit val encodeStructureUUID: Encoder[StructureUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StructureTag]

  implicit val orderingStructureUUID
  : Ordering[StructureUUID]
  = new Ordering[StructureUUID] {
  	override def compare
  	(x: StructureUUID, 
  	 y: StructureUUID)
  	: Int = x.compareTo(y)
  }
  
  type StructuredDataPropertyUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.StructuredDataPropertyTag
  
  def structuredDataPropertyUUID(uuid: UUID): StructuredDataPropertyUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StructuredDataPropertyTag][UUID](uuid)
  
  implicit val decodeStructuredDataPropertyUUID: Decoder[StructuredDataPropertyUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StructuredDataPropertyTag]
  
  implicit val encodeStructuredDataPropertyUUID: Encoder[StructuredDataPropertyUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StructuredDataPropertyTag]

  implicit val orderingStructuredDataPropertyUUID
  : Ordering[StructuredDataPropertyUUID]
  = new Ordering[StructuredDataPropertyUUID] {
  	override def compare
  	(x: StructuredDataPropertyUUID, 
  	 y: StructuredDataPropertyUUID)
  	: Int = x.compareTo(y)
  }
  
  type StructuredDataPropertyTupleUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.StructuredDataPropertyTupleTag
  
  def structuredDataPropertyTupleUUID(uuid: UUID): StructuredDataPropertyTupleUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StructuredDataPropertyTupleTag][UUID](uuid)
  
  implicit val decodeStructuredDataPropertyTupleUUID: Decoder[StructuredDataPropertyTupleUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StructuredDataPropertyTupleTag]
  
  implicit val encodeStructuredDataPropertyTupleUUID: Encoder[StructuredDataPropertyTupleUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.StructuredDataPropertyTupleTag]

  implicit val orderingStructuredDataPropertyTupleUUID
  : Ordering[StructuredDataPropertyTupleUUID]
  = new Ordering[StructuredDataPropertyTupleUUID] {
  	override def compare
  	(x: StructuredDataPropertyTupleUUID, 
  	 y: StructuredDataPropertyTupleUUID)
  	: Int = x.compareTo(y)
  }
  
  type SubDataPropertyOfAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.SubDataPropertyOfAxiomTag
  
  def subDataPropertyOfAxiomUUID(uuid: UUID): SubDataPropertyOfAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SubDataPropertyOfAxiomTag][UUID](uuid)
  
  implicit val decodeSubDataPropertyOfAxiomUUID: Decoder[SubDataPropertyOfAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SubDataPropertyOfAxiomTag]
  
  implicit val encodeSubDataPropertyOfAxiomUUID: Encoder[SubDataPropertyOfAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SubDataPropertyOfAxiomTag]

  implicit val orderingSubDataPropertyOfAxiomUUID
  : Ordering[SubDataPropertyOfAxiomUUID]
  = new Ordering[SubDataPropertyOfAxiomUUID] {
  	override def compare
  	(x: SubDataPropertyOfAxiomUUID, 
  	 y: SubDataPropertyOfAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type SubObjectPropertyOfAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.SubObjectPropertyOfAxiomTag
  
  def subObjectPropertyOfAxiomUUID(uuid: UUID): SubObjectPropertyOfAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SubObjectPropertyOfAxiomTag][UUID](uuid)
  
  implicit val decodeSubObjectPropertyOfAxiomUUID: Decoder[SubObjectPropertyOfAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SubObjectPropertyOfAxiomTag]
  
  implicit val encodeSubObjectPropertyOfAxiomUUID: Encoder[SubObjectPropertyOfAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SubObjectPropertyOfAxiomTag]

  implicit val orderingSubObjectPropertyOfAxiomUUID
  : Ordering[SubObjectPropertyOfAxiomUUID]
  = new Ordering[SubObjectPropertyOfAxiomUUID] {
  	override def compare
  	(x: SubObjectPropertyOfAxiomUUID, 
  	 y: SubObjectPropertyOfAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type SynonymScalarRestrictionUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.SynonymScalarRestrictionTag
  
  def synonymScalarRestrictionUUID(uuid: UUID): SynonymScalarRestrictionUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SynonymScalarRestrictionTag][UUID](uuid)
  
  implicit val decodeSynonymScalarRestrictionUUID: Decoder[SynonymScalarRestrictionUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SynonymScalarRestrictionTag]
  
  implicit val encodeSynonymScalarRestrictionUUID: Encoder[SynonymScalarRestrictionUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.SynonymScalarRestrictionTag]

  implicit val orderingSynonymScalarRestrictionUUID
  : Ordering[SynonymScalarRestrictionUUID]
  = new Ordering[SynonymScalarRestrictionUUID] {
  	override def compare
  	(x: SynonymScalarRestrictionUUID, 
  	 y: SynonymScalarRestrictionUUID)
  	: Int = x.compareTo(y)
  }
  
  type TermUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TermTag
  
  def termUUID(uuid: UUID): TermUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TermTag][UUID](uuid)
  
  implicit val decodeTermUUID: Decoder[TermUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TermTag]
  
  implicit val encodeTermUUID: Encoder[TermUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TermTag]

  implicit val orderingTermUUID
  : Ordering[TermUUID]
  = new Ordering[TermUUID] {
  	override def compare
  	(x: TermUUID, 
  	 y: TermUUID)
  	: Int = x.compareTo(y)
  }
  
  type TermAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TermAxiomTag
  
  def termAxiomUUID(uuid: UUID): TermAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TermAxiomTag][UUID](uuid)
  
  implicit val decodeTermAxiomUUID: Decoder[TermAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TermAxiomTag]
  
  implicit val encodeTermAxiomUUID: Encoder[TermAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TermAxiomTag]

  implicit val orderingTermAxiomUUID
  : Ordering[TermAxiomUUID]
  = new Ordering[TermAxiomUUID] {
  	override def compare
  	(x: TermAxiomUUID, 
  	 y: TermAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type TerminologyAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyAxiomTag
  
  def terminologyAxiomUUID(uuid: UUID): TerminologyAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyAxiomTag][UUID](uuid)
  
  implicit val decodeTerminologyAxiomUUID: Decoder[TerminologyAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyAxiomTag]
  
  implicit val encodeTerminologyAxiomUUID: Encoder[TerminologyAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyAxiomTag]

  implicit val orderingTerminologyAxiomUUID
  : Ordering[TerminologyAxiomUUID]
  = new Ordering[TerminologyAxiomUUID] {
  	override def compare
  	(x: TerminologyAxiomUUID, 
  	 y: TerminologyAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type TerminologyBoxUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxTag
  
  def terminologyBoxUUID(uuid: UUID): TerminologyBoxUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxTag][UUID](uuid)
  
  implicit val decodeTerminologyBoxUUID: Decoder[TerminologyBoxUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxTag]
  
  implicit val encodeTerminologyBoxUUID: Encoder[TerminologyBoxUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxTag]

  implicit val orderingTerminologyBoxUUID
  : Ordering[TerminologyBoxUUID]
  = new Ordering[TerminologyBoxUUID] {
  	override def compare
  	(x: TerminologyBoxUUID, 
  	 y: TerminologyBoxUUID)
  	: Int = x.compareTo(y)
  }
  
  type TerminologyBoxAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxAxiomTag
  
  def terminologyBoxAxiomUUID(uuid: UUID): TerminologyBoxAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxAxiomTag][UUID](uuid)
  
  implicit val decodeTerminologyBoxAxiomUUID: Decoder[TerminologyBoxAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxAxiomTag]
  
  implicit val encodeTerminologyBoxAxiomUUID: Encoder[TerminologyBoxAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxAxiomTag]

  implicit val orderingTerminologyBoxAxiomUUID
  : Ordering[TerminologyBoxAxiomUUID]
  = new Ordering[TerminologyBoxAxiomUUID] {
  	override def compare
  	(x: TerminologyBoxAxiomUUID, 
  	 y: TerminologyBoxAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type TerminologyBoxStatementUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxStatementTag
  
  def terminologyBoxStatementUUID(uuid: UUID): TerminologyBoxStatementUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxStatementTag][UUID](uuid)
  
  implicit val decodeTerminologyBoxStatementUUID: Decoder[TerminologyBoxStatementUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxStatementTag]
  
  implicit val encodeTerminologyBoxStatementUUID: Encoder[TerminologyBoxStatementUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBoxStatementTag]

  implicit val orderingTerminologyBoxStatementUUID
  : Ordering[TerminologyBoxStatementUUID]
  = new Ordering[TerminologyBoxStatementUUID] {
  	override def compare
  	(x: TerminologyBoxStatementUUID, 
  	 y: TerminologyBoxStatementUUID)
  	: Int = x.compareTo(y)
  }
  
  type TerminologyBundleAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBundleAxiomTag
  
  def terminologyBundleAxiomUUID(uuid: UUID): TerminologyBundleAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBundleAxiomTag][UUID](uuid)
  
  implicit val decodeTerminologyBundleAxiomUUID: Decoder[TerminologyBundleAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBundleAxiomTag]
  
  implicit val encodeTerminologyBundleAxiomUUID: Encoder[TerminologyBundleAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBundleAxiomTag]

  implicit val orderingTerminologyBundleAxiomUUID
  : Ordering[TerminologyBundleAxiomUUID]
  = new Ordering[TerminologyBundleAxiomUUID] {
  	override def compare
  	(x: TerminologyBundleAxiomUUID, 
  	 y: TerminologyBundleAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type TerminologyBundleStatementUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBundleStatementTag
  
  def terminologyBundleStatementUUID(uuid: UUID): TerminologyBundleStatementUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBundleStatementTag][UUID](uuid)
  
  implicit val decodeTerminologyBundleStatementUUID: Decoder[TerminologyBundleStatementUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBundleStatementTag]
  
  implicit val encodeTerminologyBundleStatementUUID: Encoder[TerminologyBundleStatementUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyBundleStatementTag]

  implicit val orderingTerminologyBundleStatementUUID
  : Ordering[TerminologyBundleStatementUUID]
  = new Ordering[TerminologyBundleStatementUUID] {
  	override def compare
  	(x: TerminologyBundleStatementUUID, 
  	 y: TerminologyBundleStatementUUID)
  	: Int = x.compareTo(y)
  }
  
  type TerminologyExtensionAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyExtensionAxiomTag
  
  def terminologyExtensionAxiomUUID(uuid: UUID): TerminologyExtensionAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyExtensionAxiomTag][UUID](uuid)
  
  implicit val decodeTerminologyExtensionAxiomUUID: Decoder[TerminologyExtensionAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyExtensionAxiomTag]
  
  implicit val encodeTerminologyExtensionAxiomUUID: Encoder[TerminologyExtensionAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyExtensionAxiomTag]

  implicit val orderingTerminologyExtensionAxiomUUID
  : Ordering[TerminologyExtensionAxiomUUID]
  = new Ordering[TerminologyExtensionAxiomUUID] {
  	override def compare
  	(x: TerminologyExtensionAxiomUUID, 
  	 y: TerminologyExtensionAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type TerminologyGraphUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyGraphTag
  
  def terminologyGraphUUID(uuid: UUID): TerminologyGraphUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyGraphTag][UUID](uuid)
  
  implicit val decodeTerminologyGraphUUID: Decoder[TerminologyGraphUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyGraphTag]
  
  implicit val encodeTerminologyGraphUUID: Encoder[TerminologyGraphUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyGraphTag]

  implicit val orderingTerminologyGraphUUID
  : Ordering[TerminologyGraphUUID]
  = new Ordering[TerminologyGraphUUID] {
  	override def compare
  	(x: TerminologyGraphUUID, 
  	 y: TerminologyGraphUUID)
  	: Int = x.compareTo(y)
  }
  
  type TerminologyInstanceAssertionUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyInstanceAssertionTag
  
  def terminologyInstanceAssertionUUID(uuid: UUID): TerminologyInstanceAssertionUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyInstanceAssertionTag][UUID](uuid)
  
  implicit val decodeTerminologyInstanceAssertionUUID: Decoder[TerminologyInstanceAssertionUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyInstanceAssertionTag]
  
  implicit val encodeTerminologyInstanceAssertionUUID: Encoder[TerminologyInstanceAssertionUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyInstanceAssertionTag]

  implicit val orderingTerminologyInstanceAssertionUUID
  : Ordering[TerminologyInstanceAssertionUUID]
  = new Ordering[TerminologyInstanceAssertionUUID] {
  	override def compare
  	(x: TerminologyInstanceAssertionUUID, 
  	 y: TerminologyInstanceAssertionUUID)
  	: Int = x.compareTo(y)
  }
  
  type TerminologyNestingAxiomUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyNestingAxiomTag
  
  def terminologyNestingAxiomUUID(uuid: UUID): TerminologyNestingAxiomUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyNestingAxiomTag][UUID](uuid)
  
  implicit val decodeTerminologyNestingAxiomUUID: Decoder[TerminologyNestingAxiomUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyNestingAxiomTag]
  
  implicit val encodeTerminologyNestingAxiomUUID: Encoder[TerminologyNestingAxiomUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TerminologyNestingAxiomTag]

  implicit val orderingTerminologyNestingAxiomUUID
  : Ordering[TerminologyNestingAxiomUUID]
  = new Ordering[TerminologyNestingAxiomUUID] {
  	override def compare
  	(x: TerminologyNestingAxiomUUID, 
  	 y: TerminologyNestingAxiomUUID)
  	: Int = x.compareTo(y)
  }
  
  type TimeScalarRestrictionUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.TimeScalarRestrictionTag
  
  def timeScalarRestrictionUUID(uuid: UUID): TimeScalarRestrictionUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TimeScalarRestrictionTag][UUID](uuid)
  
  implicit val decodeTimeScalarRestrictionUUID: Decoder[TimeScalarRestrictionUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TimeScalarRestrictionTag]
  
  implicit val encodeTimeScalarRestrictionUUID: Encoder[TimeScalarRestrictionUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.TimeScalarRestrictionTag]

  implicit val orderingTimeScalarRestrictionUUID
  : Ordering[TimeScalarRestrictionUUID]
  = new Ordering[TimeScalarRestrictionUUID] {
  	override def compare
  	(x: TimeScalarRestrictionUUID, 
  	 y: TimeScalarRestrictionUUID)
  	: Int = x.compareTo(y)
  }
  
  type UnaryTermKindUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.UnaryTermKindTag
  
  def unaryTermKindUUID(uuid: UUID): UnaryTermKindUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnaryTermKindTag][UUID](uuid)
  
  implicit val decodeUnaryTermKindUUID: Decoder[UnaryTermKindUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnaryTermKindTag]
  
  implicit val encodeUnaryTermKindUUID: Encoder[UnaryTermKindUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnaryTermKindTag]

  implicit val orderingUnaryTermKindUUID
  : Ordering[UnaryTermKindUUID]
  = new Ordering[UnaryTermKindUUID] {
  	override def compare
  	(x: UnaryTermKindUUID, 
  	 y: UnaryTermKindUUID)
  	: Int = x.compareTo(y)
  }
  
  type UnreifiedRelationshipUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipTag
  
  def unreifiedRelationshipUUID(uuid: UUID): UnreifiedRelationshipUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipTag][UUID](uuid)
  
  implicit val decodeUnreifiedRelationshipUUID: Decoder[UnreifiedRelationshipUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipTag]
  
  implicit val encodeUnreifiedRelationshipUUID: Encoder[UnreifiedRelationshipUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipTag]

  implicit val orderingUnreifiedRelationshipUUID
  : Ordering[UnreifiedRelationshipUUID]
  = new Ordering[UnreifiedRelationshipUUID] {
  	override def compare
  	(x: UnreifiedRelationshipUUID, 
  	 y: UnreifiedRelationshipUUID)
  	: Int = x.compareTo(y)
  }
  
  type UnreifiedRelationshipInstanceTupleUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipInstanceTupleTag
  
  def unreifiedRelationshipInstanceTupleUUID(uuid: UUID): UnreifiedRelationshipInstanceTupleUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipInstanceTupleTag][UUID](uuid)
  
  implicit val decodeUnreifiedRelationshipInstanceTupleUUID: Decoder[UnreifiedRelationshipInstanceTupleUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipInstanceTupleTag]
  
  implicit val encodeUnreifiedRelationshipInstanceTupleUUID: Encoder[UnreifiedRelationshipInstanceTupleUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.UnreifiedRelationshipInstanceTupleTag]

  implicit val orderingUnreifiedRelationshipInstanceTupleUUID
  : Ordering[UnreifiedRelationshipInstanceTupleUUID]
  = new Ordering[UnreifiedRelationshipInstanceTupleUUID] {
  	override def compare
  	(x: UnreifiedRelationshipInstanceTupleUUID, 
  	 y: UnreifiedRelationshipInstanceTupleUUID)
  	: Int = x.compareTo(y)
  }
  
  type ValueCrossReferenceTupleUUID
  = UUID @@ gov.nasa.jpl.imce.oml.tables.taggedTypes.ValueCrossReferenceTupleTag
  
  def valueCrossReferenceTupleUUID(uuid: UUID): ValueCrossReferenceTupleUUID
  = covariantTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ValueCrossReferenceTupleTag][UUID](uuid)
  
  implicit val decodeValueCrossReferenceTupleUUID: Decoder[ValueCrossReferenceTupleUUID]
  = decodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ValueCrossReferenceTupleTag]
  
  implicit val encodeValueCrossReferenceTupleUUID: Encoder[ValueCrossReferenceTupleUUID]
  = encodeTag[gov.nasa.jpl.imce.oml.tables.taggedTypes.ValueCrossReferenceTupleTag]

  implicit val orderingValueCrossReferenceTupleUUID
  : Ordering[ValueCrossReferenceTupleUUID]
  = new Ordering[ValueCrossReferenceTupleUUID] {
  	override def compare
  	(x: ValueCrossReferenceTupleUUID, 
  	 y: ValueCrossReferenceTupleUUID)
  	: Int = x.compareTo(y)
  }
  
}
