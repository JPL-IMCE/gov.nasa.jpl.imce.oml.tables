package gov.nasa.jpl.imce.oml.resolver

import gov.nasa.jpl.imce.oml._

import scala.collection.immutable._
import scala.{None,Some}

object Extent2Tables {

  def convertAnnotationProperty
  (acc: Seq[tables.AnnotationProperty],
   x: api.AnnotationProperty)
  : Seq[tables.AnnotationProperty]
  = acc :+ tables.AnnotationProperty(x.uuid.toString, x.iri, x.abbrevIRI)

  def convertTerminologyGraph
  (acc: Seq[tables.TerminologyGraph],
   x: api.TerminologyGraph)
  : Seq[tables.TerminologyGraph]
  = acc :+ tables.TerminologyGraph(x.uuid.toString, x.kind, x.iri)

  def convertBundle
  (acc: Seq[tables.Bundle],
   x: api.Bundle)
  : Seq[tables.Bundle]
  = acc :+ tables.Bundle(x.uuid.toString, x.kind, x.iri)

  def convertDescriptionBox
  (acc: Seq[tables.DescriptionBox],
   x: api.DescriptionBox)
  : Seq[tables.DescriptionBox]
  = acc :+ tables.DescriptionBox(x.uuid.toString, x.kind, x.iri)

  def convertAspects
  (acc: Seq[tables.Aspect],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.Aspect]
  = acc ++ x._2.flatMap {
    case y: api.Aspect =>
      Some(tables.Aspect(y.uuid.toString, x._1.uuid.toString, y.name))
    case _ =>
      None
  }.to[Seq]

  def convertConcepts
  (acc: Seq[tables.Concept],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.Concept]
  = acc ++ x._2.flatMap {
    case y: api.Concept =>
      Some(tables.Concept(y.uuid.toString, x._1.uuid.toString, y.name))
    case _ =>
      None
  }.to[Seq]

  def convertReifiedRelationships
  (acc: Seq[tables.ReifiedRelationship],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.ReifiedRelationship]
  = acc ++ x._2.flatMap {
    case y: api.ReifiedRelationship =>
      Some(tables.ReifiedRelationship(
        y.uuid.toString,
        x._1.uuid.toString,
        y.source.uuid.toString,
        y.target.uuid.toString,
        y.isAsymmetric,
        y.isEssential,
        y.isFunctional,
        y.isInverseEssential,
        y.isInverseFunctional,
        y.isIrreflexive,
        y.isReflexive,
        y.isSymmetric,
        y.isTransitive,
        y.name,
        y.unreifiedPropertyName,
        y.unreifiedInversePropertyName))
    case _ =>
      None
  }.to[Seq]

  def convertUnreifiedRelationships
  (acc: Seq[tables.UnreifiedRelationship],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.UnreifiedRelationship]
  = acc ++ x._2.flatMap {
    case y: api.UnreifiedRelationship =>
      Some(tables.UnreifiedRelationship(
        y.uuid.toString,
        x._1.uuid.toString,
        y.source.uuid.toString,
        y.target.uuid.toString,
        y.isAsymmetric,
        y.isEssential,
        y.isFunctional,
        y.isInverseEssential,
        y.isInverseFunctional,
        y.isIrreflexive,
        y.isReflexive,
        y.isSymmetric,
        y.isTransitive,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertScalars
  (acc: Seq[tables.Scalar],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.Scalar]
  = acc ++ x._2.flatMap {
    case y: api.Scalar =>
      Some(tables.Scalar(y.uuid.toString, x._1.uuid.toString, y.name))
    case _ =>
      None
  }.to[Seq]

  def convertStructures
  (acc: Seq[tables.Structure],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.Structure]
  = acc ++ x._2.flatMap {
    case y: api.Structure =>
      Some(tables.Structure(y.uuid.toString, x._1.uuid.toString, y.name))
    case _ =>
      None
  }.to[Seq]

  def convertBinaryScalarRestrictions
  (acc: Seq[tables.BinaryScalarRestriction],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.BinaryScalarRestriction]
  = acc ++ x._2.flatMap {
    case y: api.BinaryScalarRestriction =>
      Some(tables.BinaryScalarRestriction(
        y.uuid.toString,
        x._1.uuid.toString,
        y.restrictedRange.uuid.toString,
        y.length,
        y.minLength,
        y.maxLength,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertIRIScalarRestrictions
  (acc: Seq[tables.IRIScalarRestriction],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.IRIScalarRestriction]
  = acc ++ x._2.flatMap {
    case y: api.IRIScalarRestriction =>
      Some(tables.IRIScalarRestriction(
        y.uuid.toString,
        x._1.uuid.toString,
        y.restrictedRange.uuid.toString,
        y.length,
        y.minLength,
        y.maxLength,
        y.name,
        y.pattern))
    case _ =>
      None
  }.to[Seq]

  def convertNumericScalarRestrictions
  (acc: Seq[tables.NumericScalarRestriction],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.NumericScalarRestriction]
  = acc ++ x._2.flatMap {
    case y: api.NumericScalarRestriction =>
      Some(tables.NumericScalarRestriction(
        y.uuid.toString,
        x._1.uuid.toString,
        y.restrictedRange.uuid.toString,
        y.minExclusive,
        y.minInclusive,
        y.maxExclusive,
        y.maxInclusive,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertPlainLiteralScalarRestrictions
  (acc: Seq[tables.PlainLiteralScalarRestriction],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.PlainLiteralScalarRestriction]
  = acc ++ x._2.flatMap {
    case y: api.PlainLiteralScalarRestriction =>
      Some(tables.PlainLiteralScalarRestriction(
        y.uuid.toString,
        x._1.uuid.toString,
        y.restrictedRange.uuid.toString,
        y.length,
        y.minLength,
        y.maxLength,
        y.name,
        y.langRange,
        y.pattern))
    case _ =>
      None
  }.to[Seq]

  def convertStringScalarRestrictions
  (acc: Seq[tables.StringScalarRestriction],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.StringScalarRestriction]
  = acc ++ x._2.flatMap {
    case y: api.StringScalarRestriction =>
      Some(tables.StringScalarRestriction(
        y.uuid.toString,
        x._1.uuid.toString,
        y.restrictedRange.uuid.toString,
        y.length,
        y.minLength,
        y.maxLength,
        y.name,
        y.pattern))
    case _ =>
      None
  }.to[Seq]

  def convertSynonymScalarRestrictions
  (acc: Seq[tables.SynonymScalarRestriction],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.SynonymScalarRestriction]
  = acc ++ x._2.flatMap {
    case y: api.SynonymScalarRestriction =>
      Some(tables.SynonymScalarRestriction(
        y.uuid.toString,
        x._1.uuid.toString,
        y.restrictedRange.uuid.toString,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertTimeScalarRestrictions
  (acc: Seq[tables.TimeScalarRestriction],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.TimeScalarRestriction]
  = acc ++ x._2.flatMap {
    case y: api.TimeScalarRestriction =>
      Some(tables.TimeScalarRestriction(
        y.uuid.toString,
        x._1.uuid.toString,
        y.restrictedRange.uuid.toString,
        y.minExclusive,
        y.minInclusive,
        y.maxExclusive,
        y.maxInclusive,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertScalarOneOfRestrictions
  (acc: Seq[tables.ScalarOneOfRestriction],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.ScalarOneOfRestriction]
  = acc ++ x._2.flatMap {
    case y: api.ScalarOneOfRestriction =>
      Some(tables.ScalarOneOfRestriction(
        y.uuid.toString,
        x._1.uuid.toString,
        y.restrictedRange.uuid.toString,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertScalarOneOfLiteralAxioms
  (acc: Seq[tables.ScalarOneOfLiteralAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.ScalarOneOfLiteralAxiom]
  = acc ++ x._2.flatMap {
    case y: api.ScalarOneOfLiteralAxiom =>
      Some(tables.ScalarOneOfLiteralAxiom(
        y.uuid.toString,
        x._1.uuid.toString,
        y.axiom.uuid.toString,
        y.value))
    case _ =>
      None
  }.to[Seq]

  def convertScalarDataProperties
  (acc: Seq[tables.ScalarDataProperty],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.ScalarDataProperty]
  = acc ++ x._2.flatMap {
    case y: api.ScalarDataProperty =>
      Some(tables.ScalarDataProperty(
        y.uuid.toString,
        x._1.uuid.toString,
        y.domain.uuid.toString,
        y.range.uuid.toString,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertStructuredDataProperties
  (acc: Seq[tables.StructuredDataProperty],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.StructuredDataProperty]
  = acc ++ x._2.flatMap {
    case y: api.StructuredDataProperty =>
      Some(tables.StructuredDataProperty(
        y.uuid.toString,
        x._1.uuid.toString,
        y.domain.uuid.toString,
        y.range.uuid.toString,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertEntityScalarDataProperties
  (acc: Seq[tables.EntityScalarDataProperty],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.EntityScalarDataProperty]
  = acc ++ x._2.flatMap {
    case y: api.EntityScalarDataProperty =>
      Some(tables.EntityScalarDataProperty(
        y.uuid.toString,
        x._1.uuid.toString,
        y.domain.uuid.toString,
        y.range.uuid.toString,
        y.isIdentityCriteria,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertEntityStructuredDataProperties
  (acc: Seq[tables.EntityStructuredDataProperty],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.EntityStructuredDataProperty]
  = acc ++ x._2.flatMap {
    case y: api.EntityStructuredDataProperty =>
      Some(tables.EntityStructuredDataProperty(
        y.uuid.toString,
        x._1.uuid.toString,
        y.domain.uuid.toString,
        y.range.uuid.toString,
        y.isIdentityCriteria,
        y.name))
    case _ =>
      None
  }.to[Seq]

  def convertConceptDesignationTerminologyAxioms
  (acc: Seq[tables.ConceptDesignationTerminologyAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxAxiom]))
  : Seq[tables.ConceptDesignationTerminologyAxiom]
  = acc ++ x._2.flatMap {
    case y: api.ConceptDesignationTerminologyAxiom =>
      Some(tables.ConceptDesignationTerminologyAxiom(
        y.uuid.toString,
        x._1.uuid.toString,
        y.designatedConcept.uuid.toString,
        y.designatedTerminology.uuid.toString))
    case _ =>
      None
  }.to[Seq]

  def convertTerminologyExtensionAxioms
  (acc: Seq[tables.TerminologyExtensionAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxAxiom]))
  : Seq[tables.TerminologyExtensionAxiom]
  = acc ++ x._2.flatMap {
    case y: api.TerminologyExtensionAxiom =>
      Some(tables.TerminologyExtensionAxiom(
        y.uuid.toString,
        x._1.uuid.toString,
        y.extendedTerminology.uuid.toString))
    case _ =>
      None
  }.to[Seq]

  def convertTerminologyNestingAxioms
  (acc: Seq[tables.TerminologyNestingAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxAxiom]))
  : Seq[tables.TerminologyNestingAxiom]
  = acc ++ x._2.flatMap {
    case y: api.TerminologyNestingAxiom =>
      Some(tables.TerminologyNestingAxiom(
        y.uuid.toString,
        x._1.uuid.toString,
        y.nestingTerminology.uuid.toString,
        y.nestingContext.uuid.toString))
    case _ =>
      None
  }.to[Seq]

  def convertAspectSpecializationAxioms
  (acc: Seq[tables.AspectSpecializationAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.AspectSpecializationAxiom]
  = acc ++ x._2.flatMap {
    case y: api.AspectSpecializationAxiom =>
      Some(tables.AspectSpecializationAxiom(
        y.uuid.toString,
        x._1.uuid.toString,
        y.superAspect.uuid.toString,
        y.subEntity.uuid.toString))
    case _ =>
      None
  }.to[Seq]

  def convertConceptSpecializationAxioms
  (acc: Seq[tables.ConceptSpecializationAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.ConceptSpecializationAxiom]
  = acc ++ x._2.flatMap {
    case y: api.ConceptSpecializationAxiom =>
      Some(tables.ConceptSpecializationAxiom(
        y.uuid.toString,
        x._1.uuid.toString,
        y.superConcept.uuid.toString,
        y.subConcept.uuid.toString))
    case _ =>
      None
  }.to[Seq]

  def convertReifiedRelationshipSpecializationAxioms
  (acc: Seq[tables.ReifiedRelationshipSpecializationAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.ReifiedRelationshipSpecializationAxiom]
  = acc ++ x._2.flatMap {
    case y: api.ReifiedRelationshipSpecializationAxiom =>
      Some(tables.ReifiedRelationshipSpecializationAxiom(
        y.uuid.toString,
        x._1.uuid.toString,
        y.superRelationship.uuid.toString,
        y.subRelationship.uuid.toString))
    case _ =>
      None
  }.to[Seq]

  def convertEntityExistentialRestrictionAxioms
  (acc: Seq[tables.EntityExistentialRestrictionAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.EntityExistentialRestrictionAxiom]
  = acc ++ x._2.flatMap {
    case y: api.EntityExistentialRestrictionAxiom =>
      Some(tables.EntityExistentialRestrictionAxiom(
        y.uuid.toString,
        x._1.uuid.toString,
        y.restrictedRelation.uuid.toString,
        y.restrictedDomain.uuid.toString,
        y.restrictedRange.uuid.toString))
    case _ =>
      None
  }.to[Seq]

  def convertEntityUniversalRestrictionAxioms
  (acc: Seq[tables.EntityUniversalRestrictionAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.EntityUniversalRestrictionAxiom]
  = acc ++ x._2.flatMap {
    case y: api.EntityUniversalRestrictionAxiom =>
      Some(tables.EntityUniversalRestrictionAxiom(
        y.uuid.toString,
        x._1.uuid.toString,
        y.restrictedRelation.uuid.toString,
        y.restrictedDomain.uuid.toString,
        y.restrictedRange.uuid.toString))
    case _ =>
      None
  }.to[Seq]

  def convertEntityScalarDataPropertyExistentialRestrictionAxioms
  (acc: Seq[tables.EntityScalarDataPropertyExistentialRestrictionAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.EntityScalarDataPropertyExistentialRestrictionAxiom]
  = acc ++ x._2.flatMap {
    case y: api.EntityScalarDataPropertyExistentialRestrictionAxiom =>
      Some(tables.EntityScalarDataPropertyExistentialRestrictionAxiom(
        y.uuid.toString,
        x._1.uuid.toString,
        y.restrictedEntity.uuid.toString,
        y.scalarProperty.uuid.toString,
        y.scalarRestriction.uuid.toString))
    case _ =>
      None
  }.to[Seq]

  def convertEntityScalarDataPropertyParticularRestrictionAxioms
  (acc: Seq[tables.EntityScalarDataPropertyParticularRestrictionAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.EntityScalarDataPropertyParticularRestrictionAxiom]
  = acc ++ x._2.flatMap {
    case y: api.EntityScalarDataPropertyParticularRestrictionAxiom =>
      Some(tables.EntityScalarDataPropertyParticularRestrictionAxiom(
        y.uuid.toString,
        x._1.uuid.toString,
        y.restrictedEntity.uuid.toString,
        y.scalarProperty.uuid.toString,
        y.literalValue))
    case _ =>
      None
  }.to[Seq]

  def convertEntityScalarDataPropertyUniversalRestrictionAxioms
  (acc: Seq[tables.EntityScalarDataPropertyUniversalRestrictionAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBoxStatement]))
  : Seq[tables.EntityScalarDataPropertyUniversalRestrictionAxiom]
  = acc ++ x._2.flatMap {
    case y: api.EntityScalarDataPropertyUniversalRestrictionAxiom =>
      Some(tables.EntityScalarDataPropertyUniversalRestrictionAxiom(
        y.uuid.toString,
        x._1.uuid.toString,
        y.restrictedEntity.uuid.toString,
        y.scalarProperty.uuid.toString,
        y.scalarRestriction.uuid.toString))
    case _ =>
      None
  }.to[Seq]

  def convertBundledTerminologyAxioms
  (acc: Seq[tables.BundledTerminologyAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBundleAxiom]))
  : Seq[tables.BundledTerminologyAxiom]
  = acc ++ x._2.flatMap {
    case y: api.BundledTerminologyAxiom =>
      Some(tables.BundledTerminologyAxiom(
        y.uuid.toString,
        y.bundledTerminology.uuid.toString,
        x._1.uuid.toString))
    case _ =>
      None
  }.to[Seq]

  def convertAnonymousConceptTaxonomyAxioms
  (acc: Seq[tables.AnonymousConceptTaxonomyAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBundleStatement]))
  (implicit ext: api.Extent)
  : Seq[tables.AnonymousConceptTaxonomyAxiom]
  = acc ++ x._2.flatMap {
    case y: api.AnonymousConceptTaxonomyAxiom =>
      y.conceptTreeDisjunctionParent().map { parent =>
        tables.AnonymousConceptTaxonomyAxiom(
          y.uuid.toString,
          parent.uuid.toString,
          y.name)
      }
    case _ =>
      None
  }.to[Seq]

  def convertRootConceptTaxonomyAxioms
  (acc: Seq[tables.RootConceptTaxonomyAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBundleStatement]))
  : Seq[tables.RootConceptTaxonomyAxiom]
  = acc ++ x._2.flatMap {
    case y: api.RootConceptTaxonomyAxiom =>
      Some(tables.RootConceptTaxonomyAxiom(
        y.uuid.toString,
        x._1.uuid.toString,
        y.root.uuid.toString))
    case _ =>
      None
  }.to[Seq]

  def convertSpecificDisjointConceptAxioms
  (acc: Seq[tables.SpecificDisjointConceptAxiom],
   x: (api.TerminologyBox, Set[api.TerminologyBundleStatement]))
  (implicit ext: api.Extent)
  : Seq[tables.SpecificDisjointConceptAxiom]
  = acc ++ x._2.flatMap {
    case y: api.SpecificDisjointConceptAxiom =>
      y.conceptTreeDisjunctionParent().map { parent =>
        tables.SpecificDisjointConceptAxiom(
          y.uuid.toString,
          parent.uuid.toString,
          y.disjointLeaf.uuid.toString)
      }
    case _ =>
      None
  }.to[Seq]

  def convertConceptInstances
  (acc: Seq[tables.ConceptInstance],
   x: (api.ConceptInstance, api.DescriptionBox))
  : Seq[tables.ConceptInstance]
  = acc :+ tables.ConceptInstance(
        x._1.uuid.toString,
        x._2.uuid.toString,
        x._1.singletonConceptClassifier.uuid.toString,
        x._1.name)

  def convertDataStructureTuples
  (acc: Seq[tables.DataStructureTuple],
   x: (api.DataStructureTuple, api.StructuredDataPropertyValue))
  : Seq[tables.DataStructureTuple]
  = acc :+ tables.DataStructureTuple(
    x._1.uuid.toString,
    x._1.dataStructureType.uuid.toString,
    x._2.uuid.toString)

  def convertDescriptionBoxExtendsClosedWorldDefinitions
  (acc: Seq[tables.DescriptionBoxExtendsClosedWorldDefinitions],
   x: (api.DescriptionBoxExtendsClosedWorldDefinitions, api.DescriptionBox))
  : Seq[tables.DescriptionBoxExtendsClosedWorldDefinitions]
  = acc :+ tables.DescriptionBoxExtendsClosedWorldDefinitions(
    x._1.uuid.toString,
    x._2.uuid.toString,
    x._1.closedWorldDefinitions.uuid.toString)

  def convertDescriptionBoxRefinements
  (acc: Seq[tables.DescriptionBoxRefinement],
   x: (api.DescriptionBoxRefinement, api.DescriptionBox))
  : Seq[tables.DescriptionBoxRefinement]
  = acc :+ tables.DescriptionBoxRefinement(
    x._1.uuid.toString,
    x._2.uuid.toString,
    x._1.refinedDescriptionBox.uuid.toString)

  def convertReifiedRelationshipInstances
  (acc: Seq[tables.ReifiedRelationshipInstance],
   x: (api.ReifiedRelationshipInstance, api.DescriptionBox))
  : Seq[tables.ReifiedRelationshipInstance]
  = acc :+ tables.ReifiedRelationshipInstance(
    x._1.uuid.toString,
    x._2.uuid.toString,
    x._1.singletonReifiedRelationshipClassifier.uuid.toString,
    x._1.name)

  def convertReifiedRelationshipInstanceDomains
  (acc: Seq[tables.ReifiedRelationshipInstanceDomain],
   x: (api.ReifiedRelationshipInstanceDomain, api.DescriptionBox))
  : Seq[tables.ReifiedRelationshipInstanceDomain]
  = acc :+ tables.ReifiedRelationshipInstanceDomain(
    x._1.uuid.toString,
    x._2.uuid.toString,
    x._1.reifiedRelationshipInstance.uuid.toString,
    x._1.domain.uuid.toString)

  def convertReifiedRelationshipInstanceRanges
  (acc: Seq[tables.ReifiedRelationshipInstanceRange],
   x: (api.ReifiedRelationshipInstanceRange, api.DescriptionBox))
  : Seq[tables.ReifiedRelationshipInstanceRange]
  = acc :+ tables.ReifiedRelationshipInstanceRange(
    x._1.uuid.toString,
    x._2.uuid.toString,
    x._1.reifiedRelationshipInstance.uuid.toString,
    x._1.range.uuid.toString)

  def convertScalarDataPropertyValues
  (acc: Seq[tables.ScalarDataPropertyValue],
   x: (api.ScalarDataPropertyValue, api.SingletonInstance))
  : Seq[tables.ScalarDataPropertyValue]
  = acc :+ tables.ScalarDataPropertyValue(
    x._1.uuid.toString,
    x._2.uuid.toString,
    x._1.scalarDataProperty.uuid.toString,
    x._1.scalarPropertyValue)

  def convertStructuredDataPropertyValues
  (acc: Seq[tables.StructuredDataPropertyValue],
   x: (api.StructuredDataPropertyValue, api.SingletonInstance))
  : Seq[tables.StructuredDataPropertyValue]
  = acc :+ tables.StructuredDataPropertyValue(
    x._1.uuid.toString,
    x._2.uuid.toString,
    x._1.structuredDataProperty.uuid.toString)

  def convertUnreifiedRelationshipInstanceTuples
  (acc: Seq[tables.UnreifiedRelationshipInstanceTuple],
   x: (api.UnreifiedRelationshipInstanceTuple, api.DescriptionBox))
  : Seq[tables.UnreifiedRelationshipInstanceTuple]
  = acc :+ tables.UnreifiedRelationshipInstanceTuple(
    x._1.uuid.toString,
    x._2.uuid.toString,
    x._1.unreifiedRelationship.uuid.toString,
    x._1.domain.uuid.toString,
    x._1.range.uuid.toString)

  def convertAnnotations
  (aps: Seq[tables.AnnotationProperty])
  (acc: Map[tables.AnnotationProperty, Seq[tables.AnnotationEntry]],
   x: (api.Module, Set[api.Annotation]))
  : Map[tables.AnnotationProperty, Seq[tables.AnnotationEntry]]
  = x._2.foldLeft[Map[tables.AnnotationProperty, Seq[tables.AnnotationEntry]]](acc) { case (acc1, a) =>
    aps
      .find(_.uuid == a.property.uuid.toString)
      .fold[Map[tables.AnnotationProperty, Seq[tables.AnnotationEntry]]] {
      // This should never happen...
      acc1
    } { ap =>
      acc1.updated(
        ap,
        acc1.getOrElse(ap, Seq.empty) :+ tables.AnnotationEntry(
          x._1.uuid.toString,
          a.subject.uuid.toString,
          a.value)
      )
    }
  }

  def convert
  (e: api.Extent)
  : tables.OMLSpecificationTables
  = {
    implicit val ext: api.Extent = e
    val t =
      tables
        .OMLSpecificationTables.createEmptyOMLSpecificationTables()
        .copy(
          annotationProperties = e.annotationProperties.values
            .foldLeft[Seq[tables.AnnotationProperty]](Seq.empty)(convertAnnotationProperty)
            .sortBy(_.uuid),

          // modules

          terminologyGraphs = e.terminologyGraphs.values
            .foldLeft[Seq[tables.TerminologyGraph]](Seq.empty)(convertTerminologyGraph)
            .sortBy(_.uuid),

          bundles = e.bundles.values
            .foldLeft[Seq[tables.Bundle]](Seq.empty)(convertBundle)
            .sortBy(_.uuid),

          descriptionBoxes = e.descriptionBoxes.values
            .foldLeft[Seq[tables.DescriptionBox]](Seq.empty)(convertDescriptionBox)
            .sortBy(_.uuid),

          // terms

          aspects = e.boxStatements
            .foldLeft[Seq[tables.Aspect]](Seq.empty)(convertAspects)
            .sortBy(_.uuid),

          concepts = e.boxStatements
            .foldLeft[Seq[tables.Concept]](Seq.empty)(convertConcepts)
            .sortBy(_.uuid),

          reifiedRelationships = e.boxStatements
            .foldLeft[Seq[tables.ReifiedRelationship]](Seq.empty)(convertReifiedRelationships)
            .sortBy(_.uuid),

          unreifiedRelationships = e.boxStatements
            .foldLeft[Seq[tables.UnreifiedRelationship]](Seq.empty)(convertUnreifiedRelationships)
            .sortBy(_.uuid),

          scalars = e.boxStatements
            .foldLeft[Seq[tables.Scalar]](Seq.empty)(convertScalars)
            .sortBy(_.uuid),

          structures = e.boxStatements
            .foldLeft[Seq[tables.Structure]](Seq.empty)(convertStructures)
            .sortBy(_.uuid),

          // data ranges

          binaryScalarRestrictions = e.boxStatements
            .foldLeft[Seq[tables.BinaryScalarRestriction]](Seq.empty)(convertBinaryScalarRestrictions)
            .sortBy(_.uuid),

          iriScalarRestrictions = e.boxStatements
            .foldLeft[Seq[tables.IRIScalarRestriction]](Seq.empty)(convertIRIScalarRestrictions)
            .sortBy(_.uuid),

          numericScalarRestrictions = e.boxStatements
            .foldLeft[Seq[tables.NumericScalarRestriction]](Seq.empty)(convertNumericScalarRestrictions)
            .sortBy(_.uuid),

          plainLiteralScalarRestrictions = e.boxStatements
            .foldLeft[Seq[tables.PlainLiteralScalarRestriction]](Seq.empty)(convertPlainLiteralScalarRestrictions)
            .sortBy(_.uuid),

          stringScalarRestrictions = e.boxStatements
            .foldLeft[Seq[tables.StringScalarRestriction]](Seq.empty)(convertStringScalarRestrictions)
            .sortBy(_.uuid),

          synonymScalarRestrictions = e.boxStatements
            .foldLeft[Seq[tables.SynonymScalarRestriction]](Seq.empty)(convertSynonymScalarRestrictions)
            .sortBy(_.uuid),

          timeScalarRestrictions = e.boxStatements
            .foldLeft[Seq[tables.TimeScalarRestriction]](Seq.empty)(convertTimeScalarRestrictions)
            .sortBy(_.uuid),

          scalarOneOfRestrictions = e.boxStatements
            .foldLeft[Seq[tables.ScalarOneOfRestriction]](Seq.empty)(convertScalarOneOfRestrictions)
            .sortBy(_.uuid),

          scalarOneOfLiteralAxioms = e.boxStatements
            .foldLeft[Seq[tables.ScalarOneOfLiteralAxiom]](Seq.empty)(convertScalarOneOfLiteralAxioms)
            .sortBy(_.uuid),

          // data properties

          scalarDataProperties = e.boxStatements
            .foldLeft[Seq[tables.ScalarDataProperty]](Seq.empty)(convertScalarDataProperties)
            .sortBy(_.uuid),

          structuredDataProperties = e.boxStatements
            .foldLeft[Seq[tables.StructuredDataProperty]](Seq.empty)(convertStructuredDataProperties)
            .sortBy(_.uuid),

          entityScalarDataProperties = e.boxStatements
            .foldLeft[Seq[tables.EntityScalarDataProperty]](Seq.empty)(convertEntityScalarDataProperties)
            .sortBy(_.uuid),

          entityStructuredDataProperties = e.boxStatements
            .foldLeft[Seq[tables.EntityStructuredDataProperty]](Seq.empty)(convertEntityStructuredDataProperties)
            .sortBy(_.uuid),

          // terminology box axioms

          conceptDesignationTerminologyAxioms = e.boxAxioms
            .foldLeft[Seq[tables.ConceptDesignationTerminologyAxiom]](Seq.empty)(convertConceptDesignationTerminologyAxioms)
            .sortBy(_.uuid),

          terminologyExtensionAxioms = e.boxAxioms
            .foldLeft[Seq[tables.TerminologyExtensionAxiom]](Seq.empty)(convertTerminologyExtensionAxioms)
            .sortBy(_.uuid),

          terminologyNestingAxioms = e.boxAxioms
            .foldLeft[Seq[tables.TerminologyNestingAxiom]](Seq.empty)(convertTerminologyNestingAxioms)
            .sortBy(_.uuid),

          // terminology box statements

          aspectSpecializationAxioms = e.boxStatements
            .foldLeft[Seq[tables.AspectSpecializationAxiom]](Seq.empty)(convertAspectSpecializationAxioms)
            .sortBy(_.uuid),

          conceptSpecializationAxioms = e.boxStatements
            .foldLeft[Seq[tables.ConceptSpecializationAxiom]](Seq.empty)(convertConceptSpecializationAxioms)
            .sortBy(_.uuid),

          reifiedRelationshipSpecializationAxioms = e.boxStatements
            .foldLeft[Seq[tables.ReifiedRelationshipSpecializationAxiom]](Seq.empty)(convertReifiedRelationshipSpecializationAxioms)
            .sortBy(_.uuid),

          entityExistentialRestrictionAxioms = e.boxStatements
            .foldLeft[Seq[tables.EntityExistentialRestrictionAxiom]](Seq.empty)(convertEntityExistentialRestrictionAxioms)
            .sortBy(_.uuid),

          entityUniversalRestrictionAxioms = e.boxStatements
            .foldLeft[Seq[tables.EntityUniversalRestrictionAxiom]](Seq.empty)(convertEntityUniversalRestrictionAxioms)
            .sortBy(_.uuid),

          entityScalarDataPropertyExistentialRestrictionAxioms = e.boxStatements
            .foldLeft[Seq[tables.EntityScalarDataPropertyExistentialRestrictionAxiom]](Seq.empty)(convertEntityScalarDataPropertyExistentialRestrictionAxioms)
            .sortBy(_.uuid),

          entityScalarDataPropertyParticularRestrictionAxioms = e.boxStatements
            .foldLeft[Seq[tables.EntityScalarDataPropertyParticularRestrictionAxiom]](Seq.empty)(convertEntityScalarDataPropertyParticularRestrictionAxioms)
            .sortBy(_.uuid),

          entityScalarDataPropertyUniversalRestrictionAxioms = e.boxStatements
            .foldLeft[Seq[tables.EntityScalarDataPropertyUniversalRestrictionAxiom]](Seq.empty)(convertEntityScalarDataPropertyUniversalRestrictionAxioms)
            .sortBy(_.uuid),

          // bundle axioms

          bundledTerminologyAxioms = e.bundleAxioms
            .foldLeft[Seq[tables.BundledTerminologyAxiom]](Seq.empty)(convertBundledTerminologyAxioms)
            .sortBy(_.uuid),

          // bundle statements

          anonymousConceptTaxonomyAxioms = e.bundleStatements
            .foldLeft[Seq[tables.AnonymousConceptTaxonomyAxiom]](Seq.empty)(convertAnonymousConceptTaxonomyAxioms)
            .sortBy(_.uuid),

          rootConceptTaxonomyAxioms = e.bundleStatements
            .foldLeft[Seq[tables.RootConceptTaxonomyAxiom]](Seq.empty)(convertRootConceptTaxonomyAxioms)
            .sortBy(_.uuid),

          specificDisjointConceptAxioms = e.bundleStatements
            .foldLeft[Seq[tables.SpecificDisjointConceptAxiom]](Seq.empty)(convertSpecificDisjointConceptAxioms)
            .sortBy(_.uuid),

          // description box statements

          conceptInstances = e.descriptionBoxOfConceptInstance
            .foldLeft[Seq[tables.ConceptInstance]](Seq.empty)(convertConceptInstances)
            .sortBy(_.uuid),

          dataStructureTuples = e.structuredDataPropertyValueOfDataStructureTuple
            .foldLeft[Seq[tables.DataStructureTuple]](Seq.empty)(convertDataStructureTuples)
            .sortBy(_.uuid),

          descriptionBoxExtendsClosedWorldDefinitions = e.descriptionBoxOfDescriptionBoxExtendsClosedWorldDefinitions
            .foldLeft[Seq[tables.DescriptionBoxExtendsClosedWorldDefinitions]](Seq.empty)(convertDescriptionBoxExtendsClosedWorldDefinitions)
            .sortBy(_.uuid),

          descriptionBoxRefinements = e.descriptionBoxOfDescriptionBoxRefinement
            .foldLeft[Seq[tables.DescriptionBoxRefinement]](Seq.empty)(convertDescriptionBoxRefinements)
            .sortBy(_.uuid),

          reifiedRelationshipInstances = e.descriptionBoxOfReifiedRelationshipInstance
            .foldLeft[Seq[tables.ReifiedRelationshipInstance]](Seq.empty)(convertReifiedRelationshipInstances)
            .sortBy(_.uuid),

          reifiedRelationshipInstanceDomains = e.descriptionBoxOfReifiedRelationshipInstanceDomain
            .foldLeft[Seq[tables.ReifiedRelationshipInstanceDomain]](Seq.empty)(convertReifiedRelationshipInstanceDomains)
            .sortBy(_.uuid),

          reifiedRelationshipInstanceRanges = e.descriptionBoxOfReifiedRelationshipInstanceRange
            .foldLeft[Seq[tables.ReifiedRelationshipInstanceRange]](Seq.empty)(convertReifiedRelationshipInstanceRanges)
            .sortBy(_.uuid),

          scalarDataPropertyValues = e.singletonInstanceOfScalarDataPropertyValue
            .foldLeft[Seq[tables.ScalarDataPropertyValue]](Seq.empty)(convertScalarDataPropertyValues)
            .sortBy(_.uuid),

          structuredDataPropertyValues = e.singletonInstanceOfStructuredDataPropertyValue
            .foldLeft[Seq[tables.StructuredDataPropertyValue]](Seq.empty)(convertStructuredDataPropertyValues)
            .sortBy(_.uuid),

          unreifiedRelationshipInstanceTuples = e.descriptionBoxOfUnreifiedRelationshipInstanceTuple
            .foldLeft[Seq[tables.UnreifiedRelationshipInstanceTuple]](Seq.empty)(convertUnreifiedRelationshipInstanceTuples)
            .sortBy(_.uuid)
        )

    t.copy(
      annotations =
        e
          .annotations
          .foldLeft[Map[tables.AnnotationProperty, Seq[tables.AnnotationEntry]]]
          (Map.empty)
          (convertAnnotations(t.annotationProperties))
    )
  }

}
