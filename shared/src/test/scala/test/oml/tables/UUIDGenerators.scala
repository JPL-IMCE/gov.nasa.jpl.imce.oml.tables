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


package test.oml.tables

import gov.nasa.jpl.imce.oml.tables.taggedTypes
import org.scalacheck.Gen

object UUIDGenerators {

  val uuid = Gen.uuid

  val annotationPropertyUUID = uuid.map(id => taggedTypes.annotationPropertyUUID(id.toString))
  val annotationPropertyValueUUID = uuid.map(id => taggedTypes.annotationPropertyValueUUID(id.toString))
  val anonymousConceptUnionAxiomUUID = uuid.map(id => taggedTypes.anonymousConceptUnionAxiomUUID(id.toString))
  val aspectUUID = uuid.map(id => taggedTypes.aspectUUID(id.toString))
  val aspectPredicateUUID = uuid.map(id => taggedTypes.aspectPredicateUUID(id.toString))
  val aspectSpecializationAxiomUUID = uuid.map(id => taggedTypes.aspectSpecializationAxiomUUID(id.toString))
  val binaryScalarRestrictionUUID = uuid.map(id => taggedTypes.binaryScalarRestrictionUUID(id.toString))
  val binarySegmentForwardPropertyPredicateUUID = uuid.map(id => taggedTypes.binarySegmentForwardPropertyPredicateUUID(id.toString))
  val binarySegmentPropertyPredicateUUID = uuid.map(id => taggedTypes.binarySegmentPropertyPredicateUUID(id.toString))
  val binarySegmentReversePropertyPredicateUUID = uuid.map(id => taggedTypes.binarySegmentReversePropertyPredicateUUID(id.toString))
  val bundleUUID = uuid.map(id => taggedTypes.bundleUUID(id.toString))
  val bundledTerminologyAxiomUUID = uuid.map(id => taggedTypes.bundledTerminologyAxiomUUID(id.toString))
  val chainRuleUUID = uuid.map(id => taggedTypes.chainRuleUUID(id.toString))
  val conceptUUID = uuid.map(id => taggedTypes.conceptUUID(id.toString))
  val conceptDesignationTerminologyAxiomUUID = uuid.map(id => taggedTypes.conceptDesignationTerminologyAxiomUUID(id.toString))
  val conceptInstanceUUID = uuid.map(id => taggedTypes.conceptInstanceUUID(id.toString))
  val conceptPredicateUUID = uuid.map(id => taggedTypes.conceptPredicateUUID(id.toString))
  val conceptSpecializationAxiomUUID = uuid.map(id => taggedTypes.conceptSpecializationAxiomUUID(id.toString))
  val conceptTreeDisjunctionUUID = uuid.map(id => taggedTypes.conceptTreeDisjunctionUUID(id.toString))
  val conceptualEntityUUID = uuid.map(id => taggedTypes.conceptualEntityUUID(id.toString))
  val conceptualEntitySingletonInstanceUUID = uuid.map(id => taggedTypes.conceptualEntitySingletonInstanceUUID(id.toString))
  val crossReferencabilityKindUUID = uuid.map(id => taggedTypes.crossReferencabilityKindUUID(id.toString))
  val crossReferencableKindUUID = uuid.map(id => taggedTypes.crossReferencableKindUUID(id.toString))
  val dataRangeUUID = uuid.map(id => taggedTypes.dataRangeUUID(id.toString))
  val dataRelationshipUUID = uuid.map(id => taggedTypes.dataRelationshipUUID(id.toString))
  val dataRelationshipDomainUUID = uuid.map(id => taggedTypes.dataRelationshipDomainUUID(id.toString))
  val dataRelationshipFromEntityUUID = uuid.map(id => taggedTypes.dataRelationshipFromEntityUUID(id.toString))
  val dataRelationshipFromStructureUUID = uuid.map(id => taggedTypes.dataRelationshipFromStructureUUID(id.toString))
  val dataRelationshipRangeUUID = uuid.map(id => taggedTypes.dataRelationshipRangeUUID(id.toString))
  val dataRelationshipToScalarUUID = uuid.map(id => taggedTypes.dataRelationshipToScalarUUID(id.toString))
  val dataRelationshipToStructureUUID = uuid.map(id => taggedTypes.dataRelationshipToStructureUUID(id.toString))
  val datatypeUUID = uuid.map(id => taggedTypes.datatypeUUID(id.toString))
  val descriptionBoxUUID = uuid.map(id => taggedTypes.descriptionBoxUUID(id.toString))
  val descriptionBoxExtendsClosedWorldDefinitionsUUID = uuid.map(id => taggedTypes.descriptionBoxExtendsClosedWorldDefinitionsUUID(id.toString))
  val descriptionBoxRefinementUUID = uuid.map(id => taggedTypes.descriptionBoxRefinementUUID(id.toString))
  val descriptionBoxRelationshipUUID = uuid.map(id => taggedTypes.descriptionBoxRelationshipUUID(id.toString))
  val directedBinaryRelationshipKindUUID = uuid.map(id => taggedTypes.directedBinaryRelationshipKindUUID(id.toString))
  val disjointUnionOfConceptsAxiomUUID = uuid.map(id => taggedTypes.disjointUnionOfConceptsAxiomUUID(id.toString))
  val elementCrossReferenceTupleUUID = uuid.map(id => taggedTypes.elementCrossReferenceTupleUUID(id.toString))
  val entityUUID = uuid.map(id => taggedTypes.entityUUID(id.toString))
  val entityExistentialRestrictionAxiomUUID = uuid.map(id => taggedTypes.entityExistentialRestrictionAxiomUUID(id.toString))
  val entityRelationshipUUID = uuid.map(id => taggedTypes.entityRelationshipUUID(id.toString))
  val entityRestrictionAxiomUUID = uuid.map(id => taggedTypes.entityRestrictionAxiomUUID(id.toString))
  val entityScalarDataPropertyUUID = uuid.map(id => taggedTypes.entityScalarDataPropertyUUID(id.toString))
  val entityScalarDataPropertyExistentialRestrictionAxiomUUID = uuid.map(id => taggedTypes.entityScalarDataPropertyExistentialRestrictionAxiomUUID(id.toString))
  val entityScalarDataPropertyParticularRestrictionAxiomUUID = uuid.map(id => taggedTypes.entityScalarDataPropertyParticularRestrictionAxiomUUID(id.toString))
  val entityScalarDataPropertyRestrictionAxiomUUID = uuid.map(id => taggedTypes.entityScalarDataPropertyRestrictionAxiomUUID(id.toString))
  val entityScalarDataPropertyUniversalRestrictionAxiomUUID = uuid.map(id => taggedTypes.entityScalarDataPropertyUniversalRestrictionAxiomUUID(id.toString))
  val entityStructuredDataPropertyUUID = uuid.map(id => taggedTypes.entityStructuredDataPropertyUUID(id.toString))
  val entityStructuredDataPropertyParticularRestrictionAxiomUUID = uuid.map(id => taggedTypes.entityStructuredDataPropertyParticularRestrictionAxiomUUID(id.toString))
  val entityStructuredDataPropertyRestrictionAxiomUUID = uuid.map(id => taggedTypes.entityStructuredDataPropertyRestrictionAxiomUUID(id.toString))
  val entityUniversalRestrictionAxiomUUID = uuid.map(id => taggedTypes.entityUniversalRestrictionAxiomUUID(id.toString))
  val extrinsicIdentityKindUUID = uuid.map(id => taggedTypes.extrinsicIdentityKindUUID(id.toString))
  val iriScalarRestrictionUUID = uuid.map(id => taggedTypes.iriScalarRestrictionUUID(id.toString))
  val identityKindUUID = uuid.map(id => taggedTypes.identityKindUUID(id.toString))
  val intrinsicIdentityKindUUID = uuid.map(id => taggedTypes.intrinsicIdentityKindUUID(id.toString))
  val logicalElementUUID = uuid.map(id => taggedTypes.logicalElementUUID(id.toString))
  val moduleUUID = uuid.map(id => taggedTypes.moduleUUID(id.toString))
  val moduleEdgeUUID = uuid.map(id => taggedTypes.moduleEdgeUUID(id.toString))
  val moduleElementUUID = uuid.map(id => taggedTypes.moduleElementUUID(id.toString))
  val nonCrossReferencableKindUUID = uuid.map(id => taggedTypes.nonCrossReferencableKindUUID(id.toString))
  val nonLogicalElementUUID = uuid.map(id => taggedTypes.nonLogicalElementUUID(id.toString))
  val numericScalarRestrictionUUID = uuid.map(id => taggedTypes.numericScalarRestrictionUUID(id.toString))
  val plainLiteralScalarRestrictionUUID = uuid.map(id => taggedTypes.plainLiteralScalarRestrictionUUID(id.toString))
  val reifiedRelationshipUUID = uuid.map(id => taggedTypes.reifiedRelationshipUUID(id.toString))
  val reifiedRelationshipInstanceUUID = uuid.map(id => taggedTypes.reifiedRelationshipInstanceUUID(id.toString))
  val reifiedRelationshipInstanceDomainUUID = uuid.map(id => taggedTypes.reifiedRelationshipInstanceDomainUUID(id.toString))
  val reifiedRelationshipInstanceRangeUUID = uuid.map(id => taggedTypes.reifiedRelationshipInstanceRangeUUID(id.toString))
  val reifiedRelationshipInversePropertyPredicateUUID = uuid.map(id => taggedTypes.reifiedRelationshipInversePropertyPredicateUUID(id.toString))
  val reifiedRelationshipPredicateUUID = uuid.map(id => taggedTypes.reifiedRelationshipPredicateUUID(id.toString))
  val reifiedRelationshipPropertyPredicateUUID = uuid.map(id => taggedTypes.reifiedRelationshipPropertyPredicateUUID(id.toString))
  val reifiedRelationshipSourceInversePropertyPredicateUUID = uuid.map(id => taggedTypes.reifiedRelationshipSourceInversePropertyPredicateUUID(id.toString))
  val reifiedRelationshipSourcePropertyPredicateUUID = uuid.map(id => taggedTypes.reifiedRelationshipSourcePropertyPredicateUUID(id.toString))
  val reifiedRelationshipSpecializationAxiomUUID = uuid.map(id => taggedTypes.reifiedRelationshipSpecializationAxiomUUID(id.toString))
  val reifiedRelationshipTargetInversePropertyPredicateUUID = uuid.map(id => taggedTypes.reifiedRelationshipTargetInversePropertyPredicateUUID(id.toString))
  val reifiedRelationshipTargetPropertyPredicateUUID = uuid.map(id => taggedTypes.reifiedRelationshipTargetPropertyPredicateUUID(id.toString))
  val resourceUUID = uuid.map(id => taggedTypes.resourceUUID(id.toString))
  val restrictedDataRangeUUID = uuid.map(id => taggedTypes.restrictedDataRangeUUID(id.toString))
  val restrictionScalarDataPropertyValueUUID = uuid.map(id => taggedTypes.restrictionScalarDataPropertyValueUUID(id.toString))
  val restrictionStructuredDataPropertyContextUUID = uuid.map(id => taggedTypes.restrictionStructuredDataPropertyContextUUID(id.toString))
  val restrictionStructuredDataPropertyTupleUUID = uuid.map(id => taggedTypes.restrictionStructuredDataPropertyTupleUUID(id.toString))
  val rootConceptTaxonomyAxiomUUID = uuid.map(id => taggedTypes.rootConceptTaxonomyAxiomUUID(id.toString))
  val ruleUUID = uuid.map(id => taggedTypes.ruleUUID(id.toString))
  val ruleBodySegmentUUID = uuid.map(id => taggedTypes.ruleBodySegmentUUID(id.toString))
  val scalarUUID = uuid.map(id => taggedTypes.scalarUUID(id.toString))
  val scalarDataPropertyUUID = uuid.map(id => taggedTypes.scalarDataPropertyUUID(id.toString))
  val scalarDataPropertyValueUUID = uuid.map(id => taggedTypes.scalarDataPropertyValueUUID(id.toString))
  val scalarOneOfLiteralAxiomUUID = uuid.map(id => taggedTypes.scalarOneOfLiteralAxiomUUID(id.toString))
  val scalarOneOfRestrictionUUID = uuid.map(id => taggedTypes.scalarOneOfRestrictionUUID(id.toString))
  val segmentPredicateUUID = uuid.map(id => taggedTypes.segmentPredicateUUID(id.toString))
  val singletonInstanceScalarDataPropertyValueUUID = uuid.map(id => taggedTypes.singletonInstanceScalarDataPropertyValueUUID(id.toString))
  val singletonInstanceStructuredDataPropertyContextUUID = uuid.map(id => taggedTypes.singletonInstanceStructuredDataPropertyContextUUID(id.toString))
  val singletonInstanceStructuredDataPropertyValueUUID = uuid.map(id => taggedTypes.singletonInstanceStructuredDataPropertyValueUUID(id.toString))
  val specializationAxiomUUID = uuid.map(id => taggedTypes.specializationAxiomUUID(id.toString))
  val specificDisjointConceptAxiomUUID = uuid.map(id => taggedTypes.specificDisjointConceptAxiomUUID(id.toString))
  val stringScalarRestrictionUUID = uuid.map(id => taggedTypes.stringScalarRestrictionUUID(id.toString))
  val structureUUID = uuid.map(id => taggedTypes.structureUUID(id.toString))
  val structuredDataPropertyUUID = uuid.map(id => taggedTypes.structuredDataPropertyUUID(id.toString))
  val structuredDataPropertyTupleUUID = uuid.map(id => taggedTypes.structuredDataPropertyTupleUUID(id.toString))
  val subDataPropertyOfAxiomUUID = uuid.map(id => taggedTypes.subDataPropertyOfAxiomUUID(id.toString))
  val subObjectPropertyOfAxiomUUID = uuid.map(id => taggedTypes.subObjectPropertyOfAxiomUUID(id.toString))
  val synonymScalarRestrictionUUID = uuid.map(id => taggedTypes.synonymScalarRestrictionUUID(id.toString))
  val termUUID = uuid.map(id => taggedTypes.termUUID(id.toString))
  val termAxiomUUID = uuid.map(id => taggedTypes.termAxiomUUID(id.toString))
  val terminologyAxiomUUID = uuid.map(id => taggedTypes.terminologyAxiomUUID(id.toString))
  val terminologyBoxUUID = uuid.map(id => taggedTypes.terminologyBoxUUID(id.toString))
  val terminologyBoxAxiomUUID = uuid.map(id => taggedTypes.terminologyBoxAxiomUUID(id.toString))
  val terminologyBoxStatementUUID = uuid.map(id => taggedTypes.terminologyBoxStatementUUID(id.toString))
  val terminologyBundleAxiomUUID = uuid.map(id => taggedTypes.terminologyBundleAxiomUUID(id.toString))
  val terminologyBundleStatementUUID = uuid.map(id => taggedTypes.terminologyBundleStatementUUID(id.toString))
  val terminologyExtensionAxiomUUID = uuid.map(id => taggedTypes.terminologyExtensionAxiomUUID(id.toString))
  val terminologyGraphUUID = uuid.map(id => taggedTypes.terminologyGraphUUID(id.toString))
  val terminologyInstanceAssertionUUID = uuid.map(id => taggedTypes.terminologyInstanceAssertionUUID(id.toString))
  val terminologyNestingAxiomUUID = uuid.map(id => taggedTypes.terminologyNestingAxiomUUID(id.toString))
  val timeScalarRestrictionUUID = uuid.map(id => taggedTypes.timeScalarRestrictionUUID(id.toString))
  val unarySegmentPredicateUUID = uuid.map(id => taggedTypes.unarySegmentPredicateUUID(id.toString))
  val unaryTermKindUUID = uuid.map(id => taggedTypes.unaryTermKindUUID(id.toString))
  val unreifiedRelationshipUUID = uuid.map(id => taggedTypes.unreifiedRelationshipUUID(id.toString))
  val unreifiedRelationshipInstanceTupleUUID = uuid.map(id => taggedTypes.unreifiedRelationshipInstanceTupleUUID(id.toString))
  val unreifiedRelationshipInversePropertyPredicateUUID = uuid.map(id => taggedTypes.unreifiedRelationshipInversePropertyPredicateUUID(id.toString))
  val unreifiedRelationshipPropertyPredicateUUID = uuid.map(id => taggedTypes.unreifiedRelationshipPropertyPredicateUUID(id.toString))
  val valueCrossReferenceTupleUUID = uuid.map(id => taggedTypes.valueCrossReferenceTupleUUID(id.toString))

}
