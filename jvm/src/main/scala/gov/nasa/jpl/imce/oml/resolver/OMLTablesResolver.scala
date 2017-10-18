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

import java.lang.IllegalArgumentException
import java.util.UUID

import gov.nasa.jpl.imce.oml.{resolver, _}

import scalax.collection.immutable.Graph
import scala.annotation.tailrec
import scala.collection.immutable.{Iterable,Seq,Set}
import scala.collection.parallel.immutable.ParSeq
import scala.{Option, None, Some, StringContext, Tuple2, Tuple3, Tuple4, Tuple5}
import scala.util.{Failure, Success, Try}
import scala.Predef.ArrowAssoc

case class OMLTablesResolver private[resolver]
(context: resolver.api.Extent,
 queue: tables.OMLSpecificationTables,
 factory: api.OMLResolvedFactory,
 otherContexts: Seq[resolver.api.Extent] = Seq.empty) {

  lazy val allContexts: Seq[resolver.api.Extent] = context +: otherContexts

  def lookupModule(uuid: Option[java.util.UUID])
  : Option[resolver.api.Module]
  = uuid.fold[Option[resolver.api.Module]](None) { lookupModule }

  def lookupModule(uuid: java.util.UUID)
  : Option[resolver.api.Module]
  = lookupTerminologyBox(uuid) orElse lookupDescriptionBox(uuid)

  def lookupTerminologyBox(uuid: Option[java.util.UUID])
  : Option[resolver.api.TerminologyBox]
  = uuid.fold[Option[resolver.api.TerminologyBox]](None) { lookupTerminologyBox }

  def lookupTerminologyBox(uuid: java.util.UUID)
  : Option[resolver.api.TerminologyBox]
  = lookupTerminologyGraph(uuid) orElse lookupBundle(uuid)

  def lookupAnnotationProperty(uuid: Option[java.util.UUID])
  : Option[resolver.api.AnnotationProperty]
  = uuid.fold[Option[resolver.api.AnnotationProperty]](None) { lookupAnnotationProperty }

  def lookupAnnotationProperty(uuid: java.util.UUID)
  : Option[resolver.api.AnnotationProperty]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.annotationProperties.get(uuid))

  def lookupTerminologyGraph(uuid: Option[java.util.UUID])
  : Option[resolver.api.TerminologyGraph]
  = uuid.fold[Option[resolver.api.TerminologyGraph]](None) { lookupTerminologyGraph }

  def lookupTerminologyGraph(uuid: java.util.UUID)
  : Option[resolver.api.TerminologyGraph]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.lookupTerminologyGraph(uuid))

  def lookupBundle(uuid: Option[java.util.UUID])
  : Option[resolver.api.Bundle]
  = uuid.fold[Option[resolver.api.Bundle]](None) { lookupBundle }

  def lookupBundle(uuid: java.util.UUID)
  : Option[resolver.api.Bundle]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.bundles.get(uuid))

  def lookupDescriptionBox(uuid: Option[java.util.UUID])
  : Option[resolver.api.DescriptionBox]
  = uuid.fold[Option[resolver.api.DescriptionBox]](None) { lookupDescriptionBox }

  def lookupDescriptionBox(uuid: java.util.UUID)
  : Option[resolver.api.DescriptionBox]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.descriptionBoxes.get(uuid))

  def lookupAnnotations(key: Option[resolver.api.Module])
  : Set[resolver.api.AnnotationPropertyValue]
  = key.fold[Set[resolver.api.AnnotationPropertyValue]](Set.empty[resolver.api.AnnotationPropertyValue]) { lookupAnnotations }

  def lookupAnnotations(key: resolver.api.Module)
  : Set[resolver.api.AnnotationPropertyValue]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.annotations.get(key))
    .getOrElse(Set.empty[resolver.api.AnnotationPropertyValue])

  def lookupBoxAxioms(key: Option[resolver.api.TerminologyBox])
  : Set[resolver.api.TerminologyBoxAxiom]
  = key.fold[Set[resolver.api.TerminologyBoxAxiom]](Set.empty[resolver.api.TerminologyBoxAxiom]) { lookupBoxAxioms }

  def lookupBoxAxioms(key: resolver.api.TerminologyBox)
  : Set[resolver.api.TerminologyBoxAxiom]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.boxAxioms.get(key))
    .getOrElse(Set.empty[resolver.api.TerminologyBoxAxiom])

  def lookupTerminologyBoxAxiom(uuid: Option[java.util.UUID])
  : Option[resolver.api.TerminologyBoxAxiom]
  = uuid.fold[Option[resolver.api.TerminologyBoxAxiom]](None) { lookupTerminologyBoxAxiom }

  def lookupTerminologyBoxAxiom(uuid: java.util.UUID)
  : Option[resolver.api.TerminologyBoxAxiom]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.terminologyBoxAxiomByUUID.get(uuid))

  def lookupBoxStatements(key: Option[resolver.api.TerminologyBox])
  : Set[resolver.api.TerminologyBoxStatement]
  = key.fold[Set[resolver.api.TerminologyBoxStatement]](Set.empty[resolver.api.TerminologyBoxStatement]) { lookupBoxStatements }

  def lookupBoxStatements(key: resolver.api.TerminologyBox)
  : Set[resolver.api.TerminologyBoxStatement]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.boxStatements.get(key))
    .getOrElse(Set.empty[resolver.api.TerminologyBoxStatement])

  def lookupTerminologyBoxStatement(uuid: Option[java.util.UUID])
  : Option[resolver.api.TerminologyBoxStatement]
  = uuid.fold[Option[resolver.api.TerminologyBoxStatement]](None) { lookupTerminologyBoxStatement }

  def lookupTerminologyBoxStatement(uuid: java.util.UUID)
  : Option[resolver.api.TerminologyBoxStatement]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.terminologyBoxStatementByUUID.get(uuid))

  def lookupBundleAxioms(key: Option[resolver.api.Bundle])
  : Set[resolver.api.TerminologyBundleAxiom]
  = key.fold[Set[resolver.api.TerminologyBundleAxiom]](Set.empty[resolver.api.TerminologyBundleAxiom]) { lookupBundleAxioms }

  def lookupBundleAxioms(key: resolver.api.Bundle)
  : Set[resolver.api.TerminologyBundleAxiom]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.bundleAxioms.get(key))
    .getOrElse(Set.empty[resolver.api.TerminologyBundleAxiom])

  def lookupTerminologyBundleAxiom(uuid: Option[java.util.UUID])
  : Option[resolver.api.TerminologyBundleAxiom]
  = uuid.fold[Option[resolver.api.TerminologyBundleAxiom]](None) { lookupTerminologyBundleAxiom }

  def lookupTerminologyBundleAxiom(uuid: java.util.UUID)
  : Option[resolver.api.TerminologyBundleAxiom]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.terminologyBundleAxiomByUUID.get(uuid))

  def lookupBundleStatements(key: Option[resolver.api.Bundle])
  : Set[resolver.api.TerminologyBundleStatement]
  = key.fold[Set[resolver.api.TerminologyBundleStatement]](Set.empty[resolver.api.TerminologyBundleStatement]) { lookupBundleStatements }

  def lookupBundleStatements(key: resolver.api.Bundle)
  : Set[resolver.api.TerminologyBundleStatement]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.bundleStatements.get(key))
    .getOrElse(Set.empty[resolver.api.TerminologyBundleStatement])

  def lookupTerminologyBundleStatement(uuid: Option[java.util.UUID])
  : Option[resolver.api.TerminologyBundleStatement]
  = uuid.fold[Option[resolver.api.TerminologyBundleStatement]](None) { lookupTerminologyBundleStatement }

  def lookupTerminologyBundleStatement(uuid: java.util.UUID)
  : Option[resolver.api.TerminologyBundleStatement]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.terminologyBundleStatementByUUID.get(uuid))

  def lookupDisjunctions(key: Option[resolver.api.ConceptTreeDisjunction])
  : Set[resolver.api.DisjointUnionOfConceptsAxiom]
  = key.fold[Set[resolver.api.DisjointUnionOfConceptsAxiom]](Set.empty[resolver.api.DisjointUnionOfConceptsAxiom]) { lookupDisjunctions }

  def lookupDisjunctions(key: resolver.api.ConceptTreeDisjunction)
  : Set[resolver.api.DisjointUnionOfConceptsAxiom]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.disjunctions.get(key))
    .getOrElse(Set.empty[resolver.api.DisjointUnionOfConceptsAxiom])

  def lookupDisjointUnionOfConceptsAxiom(uuid: Option[java.util.UUID])
  : Option[resolver.api.DisjointUnionOfConceptsAxiom]
  = uuid.fold[Option[resolver.api.DisjointUnionOfConceptsAxiom]](None) { lookupDisjointUnionOfConceptsAxiom }

  def lookupDisjointUnionOfConceptsAxiom(uuid: java.util.UUID)
  : Option[resolver.api.DisjointUnionOfConceptsAxiom]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.disjointUnionOfConceptsAxiomByUUID.get(uuid))

  def lookupDescriptionBoxRefinements(key: Option[resolver.api.DescriptionBox])
  : Set[resolver.api.DescriptionBoxRefinement]
  = key.fold[Set[resolver.api.DescriptionBoxRefinement]](Set.empty[resolver.api.DescriptionBoxRefinement]) { lookupDescriptionBoxRefinements }

  def lookupDescriptionBoxRefinements(key: resolver.api.DescriptionBox)
  : Set[resolver.api.DescriptionBoxRefinement]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.descriptionBoxRefinements.get(key))
    .getOrElse(Set.empty[resolver.api.DescriptionBoxRefinement])

  def lookupDescriptionBoxRefinement(uuid: Option[java.util.UUID])
  : Option[resolver.api.DescriptionBoxRefinement]
  = uuid.fold[Option[resolver.api.DescriptionBoxRefinement]](None) { lookupDescriptionBoxRefinement }

  def lookupDescriptionBoxRefinement(uuid: java.util.UUID)
  : Option[resolver.api.DescriptionBoxRefinement]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.descriptionBoxRefinementByUUID.get(uuid))

  def lookupClosedWorldDefinitions(key: Option[resolver.api.DescriptionBox])
  : Set[resolver.api.DescriptionBoxExtendsClosedWorldDefinitions]
  = key.fold[Set[resolver.api.DescriptionBoxExtendsClosedWorldDefinitions]](Set.empty[resolver.api.DescriptionBoxExtendsClosedWorldDefinitions]) { lookupClosedWorldDefinitions }

  def lookupClosedWorldDefinitions(key: resolver.api.DescriptionBox)
  : Set[resolver.api.DescriptionBoxExtendsClosedWorldDefinitions]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.closedWorldDefinitions.get(key))
    .getOrElse(Set.empty[resolver.api.DescriptionBoxExtendsClosedWorldDefinitions])

  def lookupDescriptionBoxExtendsClosedWorldDefinitions(uuid: Option[java.util.UUID])
  : Option[resolver.api.DescriptionBoxExtendsClosedWorldDefinitions]
  = uuid.fold[Option[resolver.api.DescriptionBoxExtendsClosedWorldDefinitions]](None) { lookupDescriptionBoxExtendsClosedWorldDefinitions }

  def lookupDescriptionBoxExtendsClosedWorldDefinitions(uuid: java.util.UUID)
  : Option[resolver.api.DescriptionBoxExtendsClosedWorldDefinitions]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.descriptionBoxExtendsClosedWorldDefinitionsByUUID.get(uuid))

  def lookupConceptInstances(key: Option[resolver.api.DescriptionBox])
  : Set[resolver.api.ConceptInstance]
  = key.fold[Set[resolver.api.ConceptInstance]](Set.empty[resolver.api.ConceptInstance]) { lookupConceptInstances }

  def lookupConceptInstances(key: resolver.api.DescriptionBox)
  : Set[resolver.api.ConceptInstance]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.conceptInstances.get(key))
    .getOrElse(Set.empty[resolver.api.ConceptInstance])

  def lookupConceptInstance(uuid: Option[java.util.UUID])
  : Option[resolver.api.ConceptInstance]
  = uuid.fold[Option[resolver.api.ConceptInstance]](None) { lookupConceptInstance }

  def lookupConceptInstance(uuid: java.util.UUID)
  : Option[resolver.api.ConceptInstance]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.conceptInstanceByUUID.get(uuid))

  def lookupReifiedRelationshipInstances(key: Option[resolver.api.DescriptionBox])
  : Set[resolver.api.ReifiedRelationshipInstance]
  = key.fold[Set[resolver.api.ReifiedRelationshipInstance]](Set.empty[resolver.api.ReifiedRelationshipInstance]) { lookupReifiedRelationshipInstances }

  def lookupReifiedRelationshipInstances(key: resolver.api.DescriptionBox)
  : Set[resolver.api.ReifiedRelationshipInstance]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.reifiedRelationshipInstances.get(key))
    .getOrElse(Set.empty[resolver.api.ReifiedRelationshipInstance])

  def lookupReifiedRelationshipInstance(uuid: Option[java.util.UUID])
  : Option[resolver.api.ReifiedRelationshipInstance]
  = uuid.fold[Option[resolver.api.ReifiedRelationshipInstance]](None) { lookupReifiedRelationshipInstance }

  def lookupReifiedRelationshipInstance(uuid: java.util.UUID)
  : Option[resolver.api.ReifiedRelationshipInstance]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.reifiedRelationshipInstanceByUUID.get(uuid))

  def lookupConceptualEntitySingletonInstance(uuid: java.util.UUID)
  : Option[resolver.api.ConceptualEntitySingletonInstance]
  = lookupConceptInstance(uuid) orElse lookupReifiedRelationshipInstance(uuid)
  
  def lookupReifiedRelationshipInstanceDomains(key: Option[resolver.api.DescriptionBox])
  : Set[resolver.api.ReifiedRelationshipInstanceDomain]
  = key.fold[Set[resolver.api.ReifiedRelationshipInstanceDomain]](Set.empty[resolver.api.ReifiedRelationshipInstanceDomain]) { lookupReifiedRelationshipInstanceDomains }

  def lookupReifiedRelationshipInstanceDomains(key: resolver.api.DescriptionBox)
  : Set[resolver.api.ReifiedRelationshipInstanceDomain]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.reifiedRelationshipInstanceDomains.get(key))
    .getOrElse(Set.empty[resolver.api.ReifiedRelationshipInstanceDomain])

  def lookupReifiedRelationshipInstanceDomain(uuid: Option[java.util.UUID])
  : Option[resolver.api.ReifiedRelationshipInstanceDomain]
  = uuid.fold[Option[resolver.api.ReifiedRelationshipInstanceDomain]](None) { lookupReifiedRelationshipInstanceDomain }

  def lookupReifiedRelationshipInstanceDomain(uuid: java.util.UUID)
  : Option[resolver.api.ReifiedRelationshipInstanceDomain]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.reifiedRelationshipInstanceDomainByUUID.get(uuid))

  def lookupReifiedRelationshipInstanceRanges(key: Option[resolver.api.DescriptionBox])
  : Set[resolver.api.ReifiedRelationshipInstanceRange]
  = key.fold[Set[resolver.api.ReifiedRelationshipInstanceRange]](Set.empty[resolver.api.ReifiedRelationshipInstanceRange]) { lookupReifiedRelationshipInstanceRanges }

  def lookupReifiedRelationshipInstanceRanges(key: resolver.api.DescriptionBox)
  : Set[resolver.api.ReifiedRelationshipInstanceRange]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.reifiedRelationshipInstanceRanges.get(key))
    .getOrElse(Set.empty[resolver.api.ReifiedRelationshipInstanceRange])

  def lookupReifiedRelationshipInstanceRange(uuid: Option[java.util.UUID])
  : Option[resolver.api.ReifiedRelationshipInstanceRange]
  = uuid.fold[Option[resolver.api.ReifiedRelationshipInstanceRange]](None) { lookupReifiedRelationshipInstanceRange }

  def lookupReifiedRelationshipInstanceRange(uuid: java.util.UUID)
  : Option[resolver.api.ReifiedRelationshipInstanceRange]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.reifiedRelationshipInstanceRangeByUUID.get(uuid))

  def lookupUnreifiedRelationshipInstanceTuples(key: Option[resolver.api.DescriptionBox])
  : Set[resolver.api.UnreifiedRelationshipInstanceTuple]
  = key.fold[Set[resolver.api.UnreifiedRelationshipInstanceTuple]](Set.empty[resolver.api.UnreifiedRelationshipInstanceTuple]) { lookupUnreifiedRelationshipInstanceTuples }

  def lookupUnreifiedRelationshipInstanceTuples(key: resolver.api.DescriptionBox)
  : Set[resolver.api.UnreifiedRelationshipInstanceTuple]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.unreifiedRelationshipInstanceTuples.get(key))
    .getOrElse(Set.empty[resolver.api.UnreifiedRelationshipInstanceTuple])

  def lookupUnreifiedRelationshipInstanceTuple(uuid: Option[java.util.UUID])
  : Option[resolver.api.UnreifiedRelationshipInstanceTuple]
  = uuid.fold[Option[resolver.api.UnreifiedRelationshipInstanceTuple]](None) { lookupUnreifiedRelationshipInstanceTuple }

  def lookupUnreifiedRelationshipInstanceTuple(uuid: java.util.UUID)
  : Option[resolver.api.UnreifiedRelationshipInstanceTuple]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.unreifiedRelationshipInstanceTupleByUUID.get(uuid))

  def lookupSingletonScalarDataPropertyValues(key: Option[resolver.api.DescriptionBox])
  : Set[resolver.api.SingletonInstanceScalarDataPropertyValue]
  = key.fold[Set[resolver.api.SingletonInstanceScalarDataPropertyValue]](Set.empty[resolver.api.SingletonInstanceScalarDataPropertyValue]) { lookupSingletonScalarDataPropertyValues }

  def lookupSingletonScalarDataPropertyValues(key: resolver.api.DescriptionBox)
  : Set[resolver.api.SingletonInstanceScalarDataPropertyValue]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.singletonScalarDataPropertyValues.get(key))
    .getOrElse(Set.empty[resolver.api.SingletonInstanceScalarDataPropertyValue])

  def lookupSingletonInstanceScalarDataPropertyValue(uuid: Option[java.util.UUID])
  : Option[resolver.api.SingletonInstanceScalarDataPropertyValue]
  = uuid.fold[Option[resolver.api.SingletonInstanceScalarDataPropertyValue]](None) { lookupSingletonInstanceScalarDataPropertyValue }

  def lookupSingletonInstanceScalarDataPropertyValue(uuid: java.util.UUID)
  : Option[resolver.api.SingletonInstanceScalarDataPropertyValue]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.singletonInstanceScalarDataPropertyValueByUUID.get(uuid))

  def lookupSingletonStructuredDataPropertyValues(key: Option[resolver.api.DescriptionBox])
  : Set[resolver.api.SingletonInstanceStructuredDataPropertyValue]
  = key.fold[Set[resolver.api.SingletonInstanceStructuredDataPropertyValue]](Set.empty[resolver.api.SingletonInstanceStructuredDataPropertyValue]) { lookupSingletonStructuredDataPropertyValues }

  def lookupSingletonStructuredDataPropertyValues(key: resolver.api.DescriptionBox)
  : Set[resolver.api.SingletonInstanceStructuredDataPropertyValue]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.singletonStructuredDataPropertyValues.get(key))
    .getOrElse(Set.empty[resolver.api.SingletonInstanceStructuredDataPropertyValue])

  def lookupSingletonInstanceStructuredDataPropertyValue(uuid: Option[java.util.UUID])
  : Option[resolver.api.SingletonInstanceStructuredDataPropertyValue]
  = uuid.fold[Option[resolver.api.SingletonInstanceStructuredDataPropertyValue]](None) { lookupSingletonInstanceStructuredDataPropertyValue }

  def lookupSingletonInstanceStructuredDataPropertyValue(uuid: java.util.UUID)
  : Option[resolver.api.SingletonInstanceStructuredDataPropertyValue]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.singletonInstanceStructuredDataPropertyValueByUUID.get(uuid))

  def lookupStructuredPropertyTuples(key: Option[resolver.api.SingletonInstanceStructuredDataPropertyContext])
  : Set[resolver.api.StructuredDataPropertyTuple]
  = key.fold[Set[resolver.api.StructuredDataPropertyTuple]](Set.empty[resolver.api.StructuredDataPropertyTuple]) { lookupStructuredPropertyTuples }

  def lookupStructuredPropertyTuples(key: resolver.api.SingletonInstanceStructuredDataPropertyContext)
  : Set[resolver.api.StructuredDataPropertyTuple]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.structuredPropertyTuples.get(key))
    .getOrElse(Set.empty[resolver.api.StructuredDataPropertyTuple])

  def lookupStructuredDataPropertyTuple(uuid: Option[java.util.UUID])
  : Option[resolver.api.StructuredDataPropertyTuple]
  = uuid.fold[Option[resolver.api.StructuredDataPropertyTuple]](None) { lookupStructuredDataPropertyTuple }

  def lookupStructuredDataPropertyTuple(uuid: java.util.UUID)
  : Option[resolver.api.StructuredDataPropertyTuple]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.structuredDataPropertyTupleByUUID.get(uuid))

  def lookupSingletonInstanceStructuredDataPropertyContext(uuid: java.util.UUID)
  : Option[resolver.api.SingletonInstanceStructuredDataPropertyContext]
  = lookupSingletonInstanceStructuredDataPropertyValue(uuid) orElse lookupStructuredDataPropertyTuple(uuid)

  def lookupScalarDataPropertyValues(key: Option[resolver.api.SingletonInstanceStructuredDataPropertyContext])
  : Set[resolver.api.ScalarDataPropertyValue]
  = key.fold[Set[resolver.api.ScalarDataPropertyValue]](Set.empty[resolver.api.ScalarDataPropertyValue]) { lookupScalarDataPropertyValues }

  def lookupScalarDataPropertyValues(key: resolver.api.SingletonInstanceStructuredDataPropertyContext)
  : Set[resolver.api.ScalarDataPropertyValue]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.scalarDataPropertyValues.get(key))
    .getOrElse(Set.empty[resolver.api.ScalarDataPropertyValue])

  def lookupScalarDataPropertyValue(uuid: Option[java.util.UUID])
  : Option[resolver.api.ScalarDataPropertyValue]
  = uuid.fold[Option[resolver.api.ScalarDataPropertyValue]](None) { lookupScalarDataPropertyValue }

  def lookupScalarDataPropertyValue(uuid: java.util.UUID)
  : Option[resolver.api.ScalarDataPropertyValue]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.scalarDataPropertyValueByUUID.get(uuid))

  def lookupElement(uuid: java.util.UUID)
  : Option[resolver.api.Element]
  = lookupModule(uuid) orElse
    lookupTerminologyBoxAxiom(uuid) orElse
    lookupTerminologyBoxStatement(uuid) orElse
    lookupTerminologyBundleAxiom(uuid) orElse
    lookupTerminologyBundleStatement(uuid) orElse
    lookupDisjointUnionOfConceptsAxiom(uuid) orElse
    lookupDescriptionBoxRefinement(uuid) orElse
    lookupDescriptionBoxExtendsClosedWorldDefinitions(uuid) orElse
    lookupConceptInstance(uuid) orElse
    lookupReifiedRelationshipInstance(uuid) orElse
    lookupReifiedRelationshipInstanceDomain(uuid) orElse
    lookupReifiedRelationshipInstanceRange(uuid) orElse
    lookupUnreifiedRelationshipInstanceTuple(uuid) orElse
    lookupSingletonInstanceScalarDataPropertyValue(uuid) orElse
    lookupSingletonInstanceStructuredDataPropertyValue(uuid) orElse
    lookupStructuredDataPropertyTuple(uuid) orElse
    lookupScalarDataPropertyValue(uuid)

  def lookupDataRange(uuid: java.util.UUID)
  : Option[resolver.api.DataRange]
  = lookupTerminologyBoxStatement(uuid) match {
    case Some(dr: resolver.api.DataRange) =>
      Some(dr)
    case _ =>
      None
  }
}

object OMLTablesResolver {

  def collectFirstOption[E, R]
  (es: Iterable[E])(f: E => Option[R])
  : Option[R]
  = {

    @scala.annotation.tailrec
    def collectFirstOptionInternal
    (queue: Iterable[E])
    : Option[R]
    = if (queue.isEmpty)
      None
    else {
      val (h, t) = (queue.head, queue.tail)
      f(h) match {
        case Some(r) =>
          Some(r)
        case None =>
          collectFirstOptionInternal(t)
      }
    }

    collectFirstOptionInternal(es)
  }

  def mapAnnotationProperties
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = r.queue.annotationProperties.foldLeft[Try[OMLTablesResolver]]{
    Success(r.copy(queue = r.queue.copy(annotationProperties = Seq.empty)))
  } {
    case (Success(ri), tap) =>
      val (ej, rap) = ri.factory.createAnnotationProperty(ri.context, UUID.fromString(tap.uuid), tap.iri, tap.abbrevIRI)
      if (!ej.lookupAnnotationProperty(rap.uuid).contains(rap))
        Failure(new IllegalArgumentException(s"AnnotationProperty not in extent: $rap"))
      else if (rap.uuid.toString != tap.uuid)
        Failure(new IllegalArgumentException(s"AnnotationProperty: $tap vs. $rap"))
      else
        Success(ri.copy(context = ej))
    case (Failure(f), _) =>
      Failure(f)
  }

  def mapTerminologyGraphs
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = r.queue.terminologyGraphs.foldLeft[Try[OMLTablesResolver]] {
    Success(r.copy(queue = r.queue.copy(terminologyGraphs = Seq.empty)))
  } {
    case (Success(ri), tg) =>
      val (ej, rg) = ri.factory.createTerminologyGraph(ri.context, tg.kind, tg.iri)
      if (!ej.lookupTerminologyGraph(rg.uuid).contains(rg))
        Failure(new IllegalArgumentException(s"TerminologyGraph not in extent: $rg"))
      else if (!OMLOps.uuidEquivalent(rg.uuid, tg.uuid))
        Failure(new IllegalArgumentException(s"TerminologyGraph: $tg vs. $rg"))
      else
        Success(ri.copy(context = ej))
    case (Failure(f), _) =>
      Failure(f)
  }

  def mapBundles
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = r.queue.bundles.foldLeft[Try[OMLTablesResolver]]{
    Success(r.copy(queue = r.queue.copy(bundles = Seq.empty)))
  } {
    case (Success(ri), tb) =>
      val (ej, rb) = ri.factory.createBundle(ri.context, tb.kind, tb.iri)
      if (!ej.lookupBundle(rb.uuid).contains(rb))
        Failure(new IllegalArgumentException(s"Bundle not in extent: $rb"))
      else if (!OMLOps.uuidEquivalent(rb.uuid, tb.uuid))
        Failure(new IllegalArgumentException(s"Bundle: $tb vs. $rb"))
      else
        Success(ri.copy(context = ej))
    case (Failure(f), _) =>
      Failure(f)
  }

  def mapDescriptionBoxes
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = r.queue.descriptionBoxes.foldLeft[Try[OMLTablesResolver]]{
    Success(r.copy(queue = r.queue.copy(descriptionBoxes = Seq.empty)))
  } {
    case (Success(ri), tdb) =>
      val (ej, rdb) = ri.factory.createDescriptionBox(ri.context, tdb.kind, tdb.iri)
      if (!ej.lookupDescriptionBox(rdb.uuid).contains(rdb))
        Failure(new IllegalArgumentException(s"DescriptionBox not in extent: $rdb"))
      else if (!OMLOps.uuidEquivalent(rdb.uuid, tdb.uuid))
        Failure(new IllegalArgumentException(s"DescriptionBox: $tdb vs. $rdb"))
      else
        Success(ri.copy(context = ej))
    case (Failure(f), _) =>
      Failure(f)
  }

  def mapAspects
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.aspects
        .groupBy(_.tboxUUID)
        .map { case (tboxUUID, aspects) => UUID.fromString(tboxUUID) -> aspects }

    val byTBox = for {
      pair <- byUUID
      (tboxUUID, aspects) = pair
      tboxM = r.lookupTerminologyBox(tboxUUID)
    } yield (tboxM, aspects)

    val unresolvable = byTBox.filter(_._1.isEmpty).flatMap(_._2).to[Seq]
    val resolvable = byTBox.flatMap {
      case ((Some(tboxM), aspects)) => Some(Tuple2(tboxM, aspects))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(aspects = unresolvable)))) {
        case (Success(ri), (tboxM, aspects)) =>
          val rj
          : Try[OMLTablesResolver]
          = aspects.foldLeft[Try[OMLTablesResolver]](Success(ri)) {
            case (Success(rk), taspect) =>
              val (ek, raspect) = rk.factory.createAspect(rk.context, tboxM, taspect.name)
              if (!ek.lookupBoxStatements(tboxM).contains(raspect))
                Failure(new IllegalArgumentException(s"Aspect not in extent: $raspect"))
              else if (!OMLOps.uuidEquivalent(raspect.uuid, taspect.uuid))
                Failure(new IllegalArgumentException(s"Aspect: $taspect vs. $raspect"))
              else
                Success(rk.copy(context = ek))
            case (Failure(f), _) =>
              Failure(f)
          }
          rj
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapConcepts
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.concepts
        .groupBy(_.tboxUUID)
        .map { case (tboxUUID, concepts) => UUID.fromString(tboxUUID) -> concepts }

    val byTBox = for {
      pair <- byUUID
      (tboxUUID, concepts) = pair
      tboxM = r.lookupTerminologyBox(tboxUUID)
    } yield (tboxM, concepts)

    val unresolvable = byTBox.filter(_._1.isEmpty).flatMap(_._2).to[Seq]
    val resolvable = byTBox.flatMap {
      case ((Some(tboxM), concepts)) => Some(Tuple2(tboxM, concepts))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r)) {
        case (Success(ri), (tboxM, concepts)) =>
          val rj
          : Try[OMLTablesResolver]
          = concepts.foldLeft[Try[OMLTablesResolver]](Success(ri.copy(queue = ri.queue.copy(concepts = unresolvable)))) {
            case (Success(rk), tconcept) =>
              val (ek, rconcept) = rk.factory.createConcept(rk.context, tboxM, tconcept.name)
              if (!ek.lookupBoxStatements(tboxM).contains(rconcept))
                Failure(new IllegalArgumentException(s"Concept not in extent: $rconcept"))
              else if (!OMLOps.uuidEquivalent(rconcept.uuid, tconcept.uuid))
                Failure(new IllegalArgumentException(s"Concept: $tconcept vs. $rconcept"))
              else
                Success(rk.copy(context = ek))
            case (Failure(f), _) =>
              Failure(f)
          }
          rj
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapScalars
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.scalars
        .groupBy(_.tboxUUID)
        .map { case (tboxUUID, scalars) => UUID.fromString(tboxUUID) -> scalars }

    val byTBox = for {
      pair <- byUUID
      (tboxUUID, scalars) = pair
      tboxM = r.lookupTerminologyBox(tboxUUID)
    } yield (tboxM, scalars)

    val unresolvable = byTBox.filter(_._1.isEmpty).flatMap(_._2).to[Seq]
    val resolvable = byTBox.flatMap {
      case ((Some(tboxM), scalars)) => Some(Tuple2(tboxM, scalars))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(scalars = unresolvable)))) {
        case (Success(ri), (tboxM, scalars)) =>
          val rj
          : Try[OMLTablesResolver]
          = scalars.foldLeft[Try[OMLTablesResolver]](Success(ri)) {
            case (Success(rk), tscalar) =>
              val (ek, rscalar) = rk.factory.createScalar(rk.context, tboxM, tscalar.name)
              if (!ek.lookupBoxStatements(tboxM).contains(rscalar))
                Failure(new IllegalArgumentException(s"Scalar not in extent: $rscalar"))
              else if (!OMLOps.uuidEquivalent(rscalar.uuid, tscalar.uuid))
                Failure(new IllegalArgumentException(s"Scalar: $tscalar vs. $rscalar"))
              else
                Success(rk.copy(context = ek))
            case (Failure(f), _) =>
              Failure(f)
          }
          rj
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapStructures
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.structures
        .groupBy(_.tboxUUID)
        .map { case (tboxUUID, structures) => UUID.fromString(tboxUUID) -> structures }

    val byTBox = for {
      pair <- byUUID
      (tboxUUID, structures) = pair
      tboxM = r.lookupTerminologyBox(tboxUUID)
    } yield (tboxM, structures)

    val unresolvable = byTBox.filter(_._1.isEmpty).flatMap(_._2).to[Seq]
    val resolvable = byTBox.flatMap {
      case ((Some(tboxM), structures)) => Some(Tuple2(tboxM, structures))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(structures = unresolvable)))) {
        case (Success(ri), (tboxM, structures)) =>
          val rj
          : Try[OMLTablesResolver]
          = structures.foldLeft[Try[OMLTablesResolver]](Success(ri)) {
            case (Success(rk), tconcept) =>
              val (ek, rconcept) = rk.factory.createStructure(rk.context, tboxM, tconcept.name)
              if (!ek.lookupBoxStatements(tboxM).contains(rconcept))
                Failure(new IllegalArgumentException(s"Structure not in extent: $rconcept"))
              else if (!OMLOps.uuidEquivalent(rconcept.uuid, tconcept.uuid))
                Failure(new IllegalArgumentException(s"Structure: $tconcept vs. $rconcept"))
              else
                Success(rk.copy(context = ek))
            case (Failure(f), _) =>
              Failure(f)
          }
          rj
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapTerminologyExtends
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.terminologyExtensionAxioms
        .map { tAxiom => UUID.fromString(tAxiom.tboxUUID) -> tAxiom }

    val byTBox = for {
      pair <- byUUID
      (tboxUUIDSource, extension) = pair
      tboxMSource = r.lookupTerminologyBox(tboxUUIDSource)
    } yield (tboxMSource, extension)

    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty).map(_._2)
    val resolvable = byTBox.flatMap {
      case (Some(tboxMSource), extension) => Some(Tuple2(tboxMSource, extension))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(terminologyExtensionAxioms = unresolvable)))) {
        case (Success(ri), (tboxMSource, textension)) =>
          val (ej, rextension) = ri.factory.createTerminologyExtensionAxiom(ri.context, tboxMSource, textension.extendedTerminologyIRI)
          if (!ej.lookupBoxAxioms(tboxMSource).contains(rextension))
            Failure(new IllegalArgumentException(s"TerminologyExtensionAxiom not in extent: $rextension"))
          else if (!OMLOps.uuidEquivalent(rextension.uuid, textension.uuid))
            Failure(new IllegalArgumentException(s"TerminologyExtensionAxiom: $textension vs. $rextension"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapTerminologyNestings
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.terminologyNestingAxioms
        .map { tAxiom =>
          ( UUID.fromString(tAxiom.tboxUUID),
            UUID.fromString(tAxiom.nestingContextUUID) ) -> tAxiom
        }

    val byTBox = for {
      pair <- byUUID
      ((tboxUUIDSource, tboxUUIDConcept), nesting) = pair
      tboxMSource = r.lookupTerminologyGraph(tboxUUIDSource)
      tboxMConcept = r.lookupTerminologyBoxStatement(tboxUUIDConcept) match {
        case c: api.Concept => Some(c)
        case _ => None
      }
    } yield (tboxMSource, tboxMConcept, nesting)

    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty).map(_._3)
    val resolvable = byTBox.flatMap {
      case (Some(tboxMSource), Some(tboxMConcept), nesting) => Some(Tuple3(tboxMSource, tboxMConcept, nesting))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(terminologyNestingAxioms = unresolvable)))) {
        case (Success(ri), (tboxMSource, tboxMConcept, tnesting)) =>
          val (ej, rnesting) = ri.factory.createTerminologyNestingAxiom(ri.context, tboxMSource, tboxMConcept, tnesting.nestingTerminologyIRI)
          if (!ej.lookupBoxAxioms(tboxMSource).contains(rnesting))
            Failure(new IllegalArgumentException(s"TerminologyNestingAxiom not in extent: $rnesting"))
          else if (!OMLOps.uuidEquivalent(rnesting.uuid, tnesting.uuid))
            Failure(new IllegalArgumentException(s"TerminologyNestingAxiom: $tnesting vs. $rnesting"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapConceptDesignationTerminologyAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.conceptDesignationTerminologyAxioms
        .map { tAxiom =>
          ( UUID.fromString(tAxiom.tboxUUID),
            UUID.fromString(tAxiom.designatedConceptUUID) ) -> tAxiom
        }

    val byTBox = for {
      pair <- byUUID
      ((tboxUUIDSource, tboxUUIDConcept), conceptDesignation) = pair
      tboxMSource = r.lookupTerminologyGraph(tboxUUIDSource)
      tboxMConcept = r.lookupTerminologyBoxStatement(tboxUUIDConcept) match {
        case c: api.Concept => Some(c)
        case _ => None
      }
    } yield (tboxMSource, tboxMConcept, conceptDesignation)

    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty).map(_._3)
    val resolvable = byTBox.flatMap {
      case (Some(tboxMSource), Some(tboxMConcept), conceptDesignation) => Some(Tuple3(tboxMSource, tboxMConcept, conceptDesignation))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(conceptDesignationTerminologyAxioms = unresolvable)))) {
        case (Success(ri), (tboxMSource, tboxMConcept, tConceptDesignation)) =>
          val (ej, rConceptDesignation) = ri.factory.createConceptDesignationTerminologyAxiom(ri.context, tboxMSource, tboxMConcept, tConceptDesignation.designatedTerminologyIRI)
          if (!ej.lookupBoxAxioms(tboxMSource).contains(rConceptDesignation))
            Failure(new IllegalArgumentException(s"ConceptDesignationTerminologyAxiom: axiom not in extent: $rConceptDesignation"))
          else if (!OMLOps.uuidEquivalent(rConceptDesignation.uuid, tConceptDesignation.uuid))
            Failure(new IllegalArgumentException(s"ConceptDesignationTerminologyAxiom: $tConceptDesignation vs. $rConceptDesignation"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapBundledTerminologyAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.bundledTerminologyAxioms
        .map { tAxiom => UUID.fromString(tAxiom.bundleUUID) -> tAxiom }

    val byTBox = for {
      pair <- byUUID
      (bundleUUIDSource, bundleAxiom) = pair
      bundleMSource = r.lookupBundle(bundleUUIDSource)
    } yield (bundleMSource, bundleAxiom)

    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty).map(_._2)
    val resolvable = byTBox.flatMap {
      case (Some(bundleMSource), bundleAxiom) => Some(Tuple2(bundleMSource, bundleAxiom))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(bundledTerminologyAxioms = unresolvable)))) {
        case (Success(ri), (bundleMSource, tBundleAxiom)) =>
          val (ej, rBundleAxiom) = ri.factory.createBundledTerminologyAxiom(ri.context, bundleMSource, tBundleAxiom.bundledTerminologyIRI)
          if (!ej.lookupBundleAxioms(bundleMSource).contains(rBundleAxiom))
            Failure(new IllegalArgumentException(s"BundledTerminologyAxiom not in extent: $rBundleAxiom"))
          else if (!OMLOps.uuidEquivalent(rBundleAxiom.uuid, tBundleAxiom.uuid))
            Failure(new IllegalArgumentException(s"BundledTerminologyAxiom: $tBundleAxiom vs. $rBundleAxiom"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapDescriptionBoxExtendsClosedWorldDefinitions
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val info =
      r.queue.descriptionBoxExtendsClosedWorldDefinitions.map { tAxiom =>
        val dboxUUID = UUID.fromString(tAxiom.descriptionBoxUUID)
        val dboxM = r.lookupDescriptionBox(dboxUUID)
        dboxM -> tAxiom
      }

    val unresolvable = info.filter(tuple => tuple._1.isEmpty).map(_._2)
    val resolvable = info.flatMap {
      case (Some(dboxM), tAxiom) => Some((dboxM, tAxiom))
      case _ => None
    }

    val r1 = r.copy(queue = r.queue.copy(descriptionBoxExtendsClosedWorldDefinitions = unresolvable))

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r1)) {
        case (Success(ri), (dboxM, tAxiom)) =>
          val (ej, rAxiom) = ri.factory.createDescriptionBoxExtendsClosedWorldDefinitions(ri.context, dboxM, tAxiom.closedWorldDefinitionsIRI)
          if (!ej.lookupClosedWorldDefinitions(dboxM).contains(rAxiom))
            Failure(new IllegalArgumentException(s"DescriptionBoxExtendsClosedWorldDefinition not in extent: $rAxiom"))
          else if (!OMLOps.uuidEquivalent(rAxiom.uuid, tAxiom.uuid))
            Failure(new IllegalArgumentException(s"DescriptionBoxExtendsClosedWorldDefinition: $tAxiom vs. $rAxiom"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapDescriptionBoxRefinements
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val info =
      r.queue.descriptionBoxRefinements.map { tAxiom =>
        val dboxUUID = UUID.fromString(tAxiom.refiningDescriptionBoxUUID)
        val dboxM = r.lookupDescriptionBox(dboxUUID)
        dboxM -> tAxiom
      }

    val unresolvable = info.filter(tuple => tuple._1.isEmpty).map(_._2)
    val resolvable = info.flatMap {
      case (Some(dboxM), tAxiom) => Some((dboxM, tAxiom))
      case _ => None
    }

    val r1 = r.copy(queue = r.queue.copy(descriptionBoxRefinements = unresolvable))

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r1)) {
        case (Success(ri), (dboxM, tAxiom)) =>
          val (ej, rAxiom) = ri.factory.createDescriptionBoxRefinement(ri.context, dboxM, tAxiom.refinedDescriptionBoxIRI)
          if (!ej.lookupDescriptionBoxRefinements(dboxM).contains(rAxiom))
            Failure(new IllegalArgumentException(s"DescriptionBoxRefinement not in extent: $rAxiom"))
          else if (!OMLOps.uuidEquivalent(rAxiom.uuid, tAxiom.uuid))
            Failure(new IllegalArgumentException(s"DescriptionBoxRefinement: $tAxiom vs. $rAxiom"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapRestrictedDataRanges
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val ns = r.context.terminologyGraphs
    val (r1, u1) =
      r.queue.binaryScalarRestrictions
        .groupBy(_.tboxUUID)
        .map { case (uuid, ranges) => UUID.fromString(uuid) -> ranges }
        .partition { case (tboxUUID, _) => ns.contains(tboxUUID) }
    val (r2, u2) =
      r.queue.iriScalarRestrictions
        .groupBy(_.tboxUUID)
        .map { case (uuid, ranges) => UUID.fromString(uuid) -> ranges }
        .partition { case (tboxUUID, _) => ns.contains(tboxUUID) }
    val (r3, u3) =
      r.queue.numericScalarRestrictions
        .groupBy(_.tboxUUID)
        .map { case (uuid, ranges) => UUID.fromString(uuid) -> ranges }
        .partition { case (tboxUUID, _) => ns.contains(tboxUUID) }
    val (r4, u4) =
      r.queue.plainLiteralScalarRestrictions
        .groupBy(_.tboxUUID)
        .map { case (uuid, ranges) => UUID.fromString(uuid) -> ranges }
        .partition { case (tboxUUID, _) => ns.contains(tboxUUID) }
    val (r5, u5) =
      r.queue.scalarOneOfRestrictions
        .groupBy(_.tboxUUID)
        .map { case (uuid, ranges) => UUID.fromString(uuid) -> ranges }
        .partition { case (tboxUUID, _) => ns.contains(tboxUUID) }
    val (r6, u6) =
      r.queue.stringScalarRestrictions
        .groupBy(_.tboxUUID)
        .map { case (uuid, ranges) => UUID.fromString(uuid) -> ranges }
        .partition { case (tboxUUID, _) => ns.contains(tboxUUID) }
    val (r7, u7) =
      r.queue.synonymScalarRestrictions
        .groupBy(_.tboxUUID)
        .map { case (uuid, ranges) => UUID.fromString(uuid) -> ranges }
        .partition { case (tboxUUID, _) => ns.contains(tboxUUID) }
    val (r8, u8) =
      r.queue.timeScalarRestrictions
        .groupBy(_.tboxUUID)
        .map { case (uuid, ranges) => UUID.fromString(uuid) -> ranges }
        .partition { case (tboxUUID, _) => ns.contains(tboxUUID) }

    val worklist = DataRangesToResolve(
      binaryScalarRestrictions =
        r1.map { case (guuid, drs) => guuid -> drs.map(dr => UUID.fromString(dr.restrictedRangeUUID) -> dr) },
      iriScalarRestrictions =
        r2.map { case (guuid, drs) => guuid -> drs.map(dr => UUID.fromString(dr.restrictedRangeUUID) -> dr) },
      numericScalarRestrictions =
        r3.map { case (guuid, drs) => guuid -> drs.map(dr => UUID.fromString(dr.restrictedRangeUUID) -> dr) },
      plainLiteralScalarRestrictions =
        r4.map { case (guuid, drs) => guuid -> drs.map(dr => UUID.fromString(dr.restrictedRangeUUID) -> dr) },
      scalarOneOfRestrictions =
        r5.map { case (guuid, drs) => guuid -> drs.map(dr => UUID.fromString(dr.restrictedRangeUUID) -> dr) },
      stringScalarRestrictions =
        r6.map { case (guuid, drs) => guuid -> drs.map(dr => UUID.fromString(dr.restrictedRangeUUID) -> dr) },
      synonymScalarRestrictions =
        r7.map { case (guuid, drs) => guuid -> drs.map(dr => UUID.fromString(dr.restrictedRangeUUID) -> dr) },
      timeScalarRestrictions =
        r8.map { case (guuid, drs) => guuid -> drs.map(dr => UUID.fromString(dr.restrictedRangeUUID) -> dr) }
    )

    DataRangesToResolve
      .resolve(r, worklist)
      .map { case (resolved, remaining) =>

        val updated = resolved.queue.copy(
          binaryScalarRestrictions =
            u1.flatMap(_._2).to[Seq] ++ remaining.binaryScalarRestrictions.flatMap(_._2.map(_._2)).to[Seq],
          iriScalarRestrictions =
            u2.flatMap(_._2).to[Seq] ++ remaining.iriScalarRestrictions.flatMap(_._2.map(_._2)).to[Seq],
          numericScalarRestrictions =
            u3.flatMap(_._2).to[Seq] ++ remaining.numericScalarRestrictions.flatMap(_._2.map(_._2)).to[Seq],
          plainLiteralScalarRestrictions =
            u4.flatMap(_._2).to[Seq] ++ remaining.plainLiteralScalarRestrictions.flatMap(_._2.map(_._2)).to[Seq],
          scalarOneOfRestrictions =
            u5.flatMap(_._2).to[Seq] ++ remaining.scalarOneOfRestrictions.flatMap(_._2.map(_._2)).to[Seq],
          stringScalarRestrictions =
            u6.flatMap(_._2).to[Seq] ++ remaining.stringScalarRestrictions.flatMap(_._2.map(_._2)).to[Seq],
          synonymScalarRestrictions =
            u7.flatMap(_._2).to[Seq] ++ remaining.synonymScalarRestrictions.flatMap(_._2.map(_._2)).to[Seq],
          timeScalarRestrictions =
            u8.flatMap(_._2).to[Seq] ++ remaining.timeScalarRestrictions.flatMap(_._2.map(_._2)).to[Seq]
        )

        resolved.copy(queue = updated)
      }
  }

  def initializeTablesResolver
  (factory: api.OMLResolvedFactory)
  : OMLTablesResolver
  = OMLTablesResolver(
    factory.createExtent,
    tables.OMLSpecificationTables.createEmptyOMLSpecificationTables(),
    factory)

  def accumulateResultContext
  (otr: OMLTablesResolver)
  : OMLTablesResolver
  = otr
    .copy(otherContexts = otr.otherContexts :+ otr.context)
    .copy(context = otr.factory.createExtent)

  def resolve
  (otr: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = for {
    init <- Try.apply(otr)

    // AnnotationProperties
    step00 <- mapAnnotationProperties(init)
    // Terminologies
    step0a <- mapTerminologyGraphs(step00)
    step0b <- mapBundles(step0a)
    step0c <- mapDescriptionBoxes(step0b)
    // Atomic terms
    step1a <- mapAspects(step0c)
    step1b <- mapConcepts(step1a)
    step1c <- mapScalars(step1b)
    step1d <- mapStructures(step1c)
    // TerminologyBoxAxiom relationships
    step2a <- mapTerminologyExtends(step1d)
    step2b <- mapTerminologyNestings(step2a)
    step2c <- mapConceptDesignationTerminologyAxioms(step2b)
    // TerminologyBundleAxiom && DescriptionBox relationships
    step3a <- mapBundledTerminologyAxioms(step2c)
    step3b <- mapDescriptionBoxExtendsClosedWorldDefinitions(step3a)
    step3c <- mapDescriptionBoxRefinements(step3b)
    // Relational terms
    step4a <- mapRestrictedDataRanges(step3c)
    step4b <- mapReifiedRelationships(step4a)
    step4c <- mapUnreifiedRelationships(step4b)
    step4d <- mapChainRules(step4c)

    // DataRelationships
    step5a <- mapEntityScalarDataProperties(step4d)
    step5b <- mapEntityStructuredDataProperties(step5a)
    step5c <- mapScalarDataProperties(step5b)
    step5d <- mapStructuredDataProperties(step5c)
    // Axioms
    step6a <- mapScalarOneOfLiteralAxioms(step5d)
    // - TermAxioms
    // -- EntityRestrictionAxioms
    step7a <- mapEntityExistentialRestrictionAxioms(step6a)
    step7b <- mapEntityUniversalRestrictionAxioms(step7a)
    // -- EntityScalarDataPropertyRestrictionAxioms
    step8a <- mapEntityScalarDataPropertyExistentialRestrictionAxioms(step7b)
    step8b <- mapEntityScalarDataPropertyParticularRestrictionAxioms(step8a)
    step8c <- mapEntityScalarDataPropertyUniversalRestrictionAxioms(step8b)
    step8d <- mapEntityStructuredDataPropertyParticularRestrictionAxioms(step8c)
    // -- SpecializationAxiom
    step9a <- mapAspectSpecializationAxioms(step8d)
    step9b <- mapConceptSpecializationAxioms(step9a)
    step9c <- mapReifiedRelationshipSpecializationAxioms(step9b)
    // TerminologyBundleStatements
    step10 <- mapRootConceptTaxonomyAxioms(step9c)
    // DescriptionBoxStatements
    step11a <- mapConceptInstances(step10)
    step11b <- mapReifiedRelationshipInstances(step11a)
    step11c <- mapReifiedRelationshipInstanceDomains(step11b)
    step11d <- mapReifiedRelationshipInstanceRanges(step11c)
    step11e <- mapUnreifiedRelationshipInstanceTuples(step11d)
    step11f <- mapSingletonInstanceScalarDataPropertyValues(step11e)
    step11g <- mapSingletonInstanceStructuredDataPropertyValues(step11f)
    // Annotations
    step12 <- mapAnnotations(step11g)
  } yield
    step12

  def seqopAppend[T]
  (s: Seq[T], entry: (UUID, ParSeq[T]))
  : Seq[T]
  = s ++ entry._2

  def seqopAppend1[T]
  (s: Seq[T], entry: ((UUID, UUID), T))
  : Seq[T]
  = s :+ entry._2

  def combopGraphs
  (g1: Graph[api.Module, ModuleGraphEdge],
   g2: Graph[api.Module, ModuleGraphEdge])
  : Graph[api.Module, ModuleGraphEdge]
  = g1.union(g2)

  type HyperGraphV = Try[Graph[api.Module, ModuleGraphEdge]]

  def mapReifiedRelationships
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.reifiedRelationships
        .map { trr =>
          ( UUID.fromString(trr.tboxUUID),
            UUID.fromString(trr.sourceUUID),
            UUID.fromString(trr.targetUUID) ) -> trr
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, sourceUUID, targetUUID), trr) = tuple
      tboxM = r.lookupTerminologyBox(tboxUUID)
      sourceM = r.lookupTerminologyBoxStatement(sourceUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      targetM = r.lookupTerminologyBoxStatement(targetUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
    } yield (tboxM, sourceM, targetM, trr)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(sourceM), Some(targetM), trr) => Some(Tuple4(tboxM, sourceM, targetM, trr))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(reifiedRelationships = unresolvable)))) {
        case (Success(ri), (tboxM, sourceM, targetM, trr)) =>
          val (ej, rrr) = ri.factory.createReifiedRelationship(
            ri.context,
            tboxM,
            sourceM,
            targetM,
            trr.isAsymmetric,
            trr.isEssential,
            trr.isFunctional,
            trr.isInverseEssential,
            trr.isInverseFunctional,
            trr.isIrreflexive,
            trr.isReflexive,
            trr.isSymmetric,
            trr.isTransitive,
            trr.name,
            trr.unreifiedPropertyName,
            trr.unreifiedInversePropertyName)

          if (!ej.lookupBoxStatements(tboxM).contains(rrr))
            Failure(new IllegalArgumentException(s"ReifiedRelationship not in extent: $rrr"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(trr.uuid)).contains(rrr))
            Failure(new IllegalArgumentException(s"ReifiedRelationship: $trr vs. $rrr"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapUnreifiedRelationships
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.unreifiedRelationships
        .map { tur =>
          ( UUID.fromString(tur.tboxUUID),
            UUID.fromString(tur.sourceUUID),
            UUID.fromString(tur.targetUUID) ) -> tur
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, sourceUUID, targetUUID), tur) = tuple
      tboxM = r.lookupTerminologyBox(tboxUUID)
      sourceM = r.lookupTerminologyBoxStatement(sourceUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      targetM = r.lookupTerminologyBoxStatement(targetUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
    } yield (tboxM, sourceM, targetM, tur)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    if (unresolvable.nonEmpty) {
      java.lang.System.out.println(s"${unresolvable.size} unreified relationships")
    }
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(sourceM), Some(targetM), tur) => Some(Tuple4(tboxM, sourceM, targetM, tur))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(unreifiedRelationships = unresolvable)))) {
        case (Success(ri), (tboxM, sourceM, targetM, tur)) =>
          val (ej, rur) = ri.factory.createUnreifiedRelationship(
            ri.context,
            tboxM,
            sourceM,
            targetM,
            tur.isAsymmetric,
            tur.isEssential,
            tur.isFunctional,
            tur.isInverseEssential,
            tur.isInverseFunctional,
            tur.isIrreflexive,
            tur.isReflexive,
            tur.isSymmetric,
            tur.isTransitive,
            tur.name)

          if (!ej.lookupBoxStatements(tboxM).contains(rur))
            Failure(new IllegalArgumentException(s"UnreifiedRelationship not in extent: $rur"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tur.uuid)).contains(rur))
            Failure(new IllegalArgumentException(s"UnreifiedRelationship: $tur vs. $rur"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapChainRules
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.chainRules.map { rule =>
        ( UUID.fromString(rule.tboxUUID),
          UUID.fromString(rule.headUUID) ) -> rule
      }

    val info = for {
      tuple <- byUUID
      ((tboxUUID, headUUID), rule) = tuple
      tboxM = r.lookupTerminologyBox(tboxUUID)
      headM = r.lookupTerminologyBoxStatement(headUUID) match {
        case Some(ur: api.UnreifiedRelationship) => Some(ur)
        case _ => None
      }
    } yield (tboxM, headM, rule)

    val unresolvable = info.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty).map(_._3)
    if (unresolvable.nonEmpty) {
      java.lang.System.out.println(s"${unresolvable.size} chain rules")
    }

    val resolvable = info.flatMap {
      case (Some(tboxM), Some(headM), rule) => Some(Tuple3(tboxM, headM, rule))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(chainRules = unresolvable)))) {
        case (Success(ri), (tboxM, headM, trule)) =>
          val (ej, rrule) = ri.factory.createChainRule(
            ri.context,
            tboxM,
            trule.name,
            headM)

          if (!ej.lookupBoxStatements(tboxM).contains(rrule))
            Failure(new IllegalArgumentException(s"ChainRule not in extent: $rrule"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(trule.uuid)).contains(rrule))
            Failure(new IllegalArgumentException(s"ChainRule: $trule vs. $rrule"))
          else
            ri.queue.ruleBodySegments
              .find { seg =>
                seg.ruleUUID.contains(trule.uuid)
              } match {
              case Some(tseg) =>
                mapRuleSegments(ri.copy(context = ej), trule, tseg, None, rrule)
              case None =>
                Failure(new IllegalArgumentException(s"ChainRule: $trule lacks a first segment!"))
            }

        case (Failure(t), _) =>
          Failure(t)
      }
    s
  }

  @tailrec
  def mapRuleSegments
  (ri: OMLTablesResolver,
   trule: tables.ChainRule,
   tseg: tables.RuleBodySegment,
   prev: Option[api.RuleBodySegment],
   rrule: api.ChainRule)
  : Try[OMLTablesResolver]
  = {
    val puuid = tseg.uuid
    val tnext = ri.queue.ruleBodySegments.find(_.previousSegmentUUID.contains(puuid))
    val (eij, rseg) = ri.factory.createRuleBodySegment(ri.context, prev, if (prev.isEmpty) Some(rrule) else None)
    val ej = prev.fold {
      eij.copy(firstSegment = eij.withRuleBodySegment(rrule, rseg))
    } { prevSegment =>
      eij.copy(nextSegment = eij.withRuleBodySegment(prevSegment, rseg))
    }
    val rj = ri.copy(
      context = ej,
      queue = ri.queue.copy(ruleBodySegments =
        ri.queue.ruleBodySegments.filter(_ != tseg)))
    ri.queue.aspectPredicates.find(_.bodySegmentUUID == puuid) match {
      case Some(tap: tables.AspectPredicate) =>
        ej.lookupTerminologyBoxStatement(UUID.fromString(tap.aspectUUID)) match {
          case Some(ra: api.Aspect) =>
            val (ek, _) = rj.factory.createAspectPredicate(rj.context, ra, rseg)
            val rk = rj.copy(
              context = ek,
              queue = rj.queue.copy(aspectPredicates =
                rj.queue.aspectPredicates.filter(_ != tap)))
            tnext match {
              case Some(tn) =>
                mapRuleSegments(rk, trule, tn, Some(rseg), rrule)
              case None =>
                Success(rk)
            }
          case _ =>
            Failure(new IllegalArgumentException(s"AspectPredicate: $tap failed to resolve aspect: ${tap.aspectUUID}"))
        }
      case None =>
        ri.queue.conceptPredicates.find(_.bodySegmentUUID == puuid) match {
          case Some(tcp: tables.ConceptPredicate) =>
            ej.lookupTerminologyBoxStatement(UUID.fromString(tcp.conceptUUID)) match {
              case Some(rc: api.Concept) =>
                val (ek, _) = rj.factory.createConceptPredicate(rj.context, rseg, rc)
                val rk = rj.copy(
                  context = ek,
                  queue = rj.queue.copy(conceptPredicates =
                    rj.queue.conceptPredicates.filter(_ != tcp)))
                tnext match {
                  case Some(tn) =>
                    mapRuleSegments(rk, trule, tn, Some(rseg), rrule)
                  case None =>
                    Success(rk)
                }
              case _ =>
                Failure(new IllegalArgumentException(s"ConceptPredicate: $tcp failed to resolve concept: ${tcp.conceptUUID}"))
            }
          case None =>
            ri.queue.reifiedRelationshipPredicates.find(_.bodySegmentUUID == puuid) match {
              case Some(trrp: tables.ReifiedRelationshipPredicate) =>
                ej.lookupTerminologyBoxStatement(UUID.fromString(trrp.reifiedRelationshipUUID)) match {
                  case Some(rrr: api.ReifiedRelationship) =>
                    val (ek, _) = rj.factory.createReifiedRelationshipPredicate(rj.context, rseg, rrr)
                    val rk = rj.copy(
                      context = ek,
                      queue = rj.queue.copy(reifiedRelationshipPredicates =
                        rj.queue.reifiedRelationshipPredicates.filter(_ != trrp)))
                    tnext match {
                      case Some(tn) =>
                        mapRuleSegments(rk, trule, tn, Some(rseg), rrule)
                      case None =>
                        Success(rk)
                    }
                  case _ =>
                    Failure(new IllegalArgumentException(s"ReifiedRelationshipPredicate: $trrp failed to resolve reified relationship: ${trrp.reifiedRelationshipUUID}"))
                }
              case None =>
                ri.queue.reifiedRelationshipPropertyPredicates.find(_.bodySegmentUUID == puuid) match {
                  case Some(trrp: tables.ReifiedRelationshipPropertyPredicate) =>
                    ej.lookupTerminologyBoxStatement(UUID.fromString(trrp.reifiedRelationshipUUID)) match {
                      case Some(rrr: api.ReifiedRelationship) =>
                        val (ek, _) = rj.factory.createReifiedRelationshipPropertyPredicate(rj.context, rseg, rrr)
                        val rk = rj.copy(
                          context = ek,
                          queue = rj.queue.copy(reifiedRelationshipPropertyPredicates =
                            rj.queue.reifiedRelationshipPropertyPredicates.filter(_ != trrp)))
                        tnext match {
                          case Some(tn) =>
                            mapRuleSegments(rk, trule, tn, Some(rseg), rrule)
                          case None =>
                            Success(rk)
                        }
                      case _ =>
                        Failure(new IllegalArgumentException(s"ReifiedRelationshipPropertyPredicate: $trrp failed to resolve reified relationship: ${trrp.reifiedRelationshipUUID}"))
                    }
                  case None =>
                    ri.queue.reifiedRelationshipSourcePropertyPredicates.find(_.bodySegmentUUID == puuid) match {
                      case Some(trrp: tables.ReifiedRelationshipSourcePropertyPredicate) =>
                        ej.lookupTerminologyBoxStatement(UUID.fromString(trrp.reifiedRelationshipUUID)) match {
                          case Some(rrr: api.ReifiedRelationship) =>
                            val (ek, _) = rj.factory.createReifiedRelationshipSourcePropertyPredicate(rj.context, rseg, rrr)
                            val rk = rj.copy(
                              context = ek,
                              queue = rj.queue.copy(reifiedRelationshipSourcePropertyPredicates =
                                rj.queue.reifiedRelationshipSourcePropertyPredicates.filter(_ != trrp)))
                            tnext match {
                              case Some(tn) =>
                                mapRuleSegments(rk, trule, tn, Some(rseg), rrule)
                              case None =>
                                Success(rk)
                            }
                          case _ =>
                            Failure(new IllegalArgumentException(s"ReifiedRelationshipSourcePropertyPredicate: $trrp failed to resolve reified relationship: ${trrp.reifiedRelationshipUUID}"))
                        }
                      case None =>
                        ri.queue.reifiedRelationshipTargetPropertyPredicates.find(_.bodySegmentUUID == puuid) match {
                          case Some(trrp: tables.ReifiedRelationshipTargetPropertyPredicate) =>
                            ej.lookupTerminologyBoxStatement(UUID.fromString(trrp.reifiedRelationshipUUID)) match {
                              case Some(rrr: api.ReifiedRelationship) =>
                                val (ek, _) = rj.factory.createReifiedRelationshipTargetPropertyPredicate(rj.context, rseg, rrr)
                                val rk = rj.copy(
                                  context = ek,
                                  queue = rj.queue.copy(reifiedRelationshipTargetPropertyPredicates =
                                    rj.queue.reifiedRelationshipTargetPropertyPredicates.filter(_ != trrp)))
                                tnext match {
                                  case Some(tn) =>
                                    mapRuleSegments(rk, trule, tn, Some(rseg), rrule)
                                  case None =>
                                    Success(rk)
                                }
                              case _ =>
                                Failure(new IllegalArgumentException(s"ReifiedRelationshipTargetPropertyPredicate: $trrp failed to resolve reified relationship: ${trrp.reifiedRelationshipUUID}"))
                            }
                          case None =>
                            ri.queue.unreifiedRelationshipPropertyPredicates.find(_.bodySegmentUUID == puuid) match {
                              case Some(turp: tables.UnreifiedRelationshipPropertyPredicate) =>
                                ej.lookupTerminologyBoxStatement(UUID.fromString(turp.unreifiedRelationshipUUID)) match {
                                  case Some(rur: api.UnreifiedRelationship) =>
                                    val (ek, _) = rj.factory.createUnreifiedRelationshipPropertyPredicate(rj.context, rur, rseg)
                                    val rk = rj.copy(
                                      context = ek,
                                      queue = rj.queue.copy(unreifiedRelationshipPropertyPredicates =
                                        rj.queue.unreifiedRelationshipPropertyPredicates.filter(_ != turp)))
                                    tnext match {
                                      case Some(tn) =>
                                        mapRuleSegments(rk, trule, tn, Some(rseg), rrule)
                                      case None =>
                                        Success(rk)
                                    }
                                  case _ =>
                                    Failure(new IllegalArgumentException(s"UnreifiedRelationshipPropertyPredicate: $turp failed to resolve unreified relationship: ${turp.unreifiedRelationshipUUID}"))
                                }
                              case None =>
                                ri.queue.reifiedRelationshipInversePropertyPredicates.find(_.bodySegmentUUID == puuid) match {
                                  case Some(trrp: tables.ReifiedRelationshipInversePropertyPredicate) =>
                                    ej.lookupTerminologyBoxStatement(UUID.fromString(trrp.reifiedRelationshipUUID)) match {
                                      case Some(rrr: api.ReifiedRelationship) =>
                                        val (ek, _) = rj.factory.createReifiedRelationshipInversePropertyPredicate(rj.context, rseg, rrr)
                                        val rk = rj.copy(
                                          context = ek,
                                          queue = rj.queue.copy(reifiedRelationshipInversePropertyPredicates =
                                            rj.queue.reifiedRelationshipInversePropertyPredicates.filter(_ != trrp)))
                                        tnext match {
                                          case Some(tn) =>
                                            mapRuleSegments(rk, trule, tn, Some(rseg), rrule)
                                          case None =>
                                            Success(rk)
                                        }
                                      case _ =>
                                        Failure(new IllegalArgumentException(s"ReifiedRelationshipInversePropertyPredicate: $trrp failed to resolve reified relationship: ${trrp.reifiedRelationshipUUID}"))
                                    }
                                  case None =>
                                    ri.queue.reifiedRelationshipSourceInversePropertyPredicates.find(_.bodySegmentUUID == puuid) match {
                                      case Some(trrp: tables.ReifiedRelationshipSourceInversePropertyPredicate) =>
                                        ej.lookupTerminologyBoxStatement(UUID.fromString(trrp.reifiedRelationshipUUID)) match {
                                          case Some(rrr: api.ReifiedRelationship) =>
                                            val (ek, _) = rj.factory.createReifiedRelationshipSourceInversePropertyPredicate(rj.context, rseg, rrr)
                                            val rk = rj.copy(
                                              context = ek,
                                              queue = rj.queue.copy(reifiedRelationshipSourceInversePropertyPredicates =
                                                rj.queue.reifiedRelationshipSourceInversePropertyPredicates.filter(_ != trrp)))
                                            tnext match {
                                              case Some(tn) =>
                                                mapRuleSegments(rk, trule, tn, Some(rseg), rrule)
                                              case None =>
                                                Success(rk)
                                            }
                                          case _ =>
                                            Failure(new IllegalArgumentException(s"ReifiedRelationshipSourceInversePropertyPredicate: $trrp failed to resolve reified relationship: ${trrp.reifiedRelationshipUUID}"))
                                        }
                                      case None =>
                                        ri.queue.reifiedRelationshipTargetInversePropertyPredicates.find(_.bodySegmentUUID == puuid) match {
                                          case Some(trrp: tables.ReifiedRelationshipTargetInversePropertyPredicate) =>
                                            ej.lookupTerminologyBoxStatement(UUID.fromString(trrp.reifiedRelationshipUUID)) match {
                                              case Some(rrr: api.ReifiedRelationship) =>
                                                val (ek, _) = rj.factory.createReifiedRelationshipTargetInversePropertyPredicate(rj.context, rseg, rrr)
                                                val rk = rj.copy(
                                                  context = ek,
                                                  queue = rj.queue.copy(reifiedRelationshipTargetInversePropertyPredicates =
                                                    rj.queue.reifiedRelationshipTargetInversePropertyPredicates.filter(_ != trrp)))
                                                tnext match {
                                                  case Some(tn) =>
                                                    mapRuleSegments(rk, trule, tn, Some(rseg), rrule)
                                                  case None =>
                                                    Success(rk)
                                                }
                                              case _ =>
                                                Failure(new IllegalArgumentException(s"ReifiedRelationshipTargetInversePropertyPredicate: $trrp failed to resolve reified relationship: ${trrp.reifiedRelationshipUUID}"))
                                            }
                                          case None =>
                                            ri.queue.unreifiedRelationshipInversePropertyPredicates.find(_.bodySegmentUUID == puuid) match {
                                              case Some(turp: tables.UnreifiedRelationshipInversePropertyPredicate) =>
                                                ej.lookupTerminologyBoxStatement(UUID.fromString(turp.unreifiedRelationshipUUID)) match {
                                                  case Some(rur: api.UnreifiedRelationship) =>
                                                    val (ek, _) = rj.factory.createUnreifiedRelationshipInversePropertyPredicate(rj.context, rur, rseg)
                                                    val rk = rj.copy(
                                                      context = ek,
                                                      queue = rj.queue.copy(unreifiedRelationshipInversePropertyPredicates =
                                                        rj.queue.unreifiedRelationshipInversePropertyPredicates.filter(_ != turp)))
                                                    tnext match {
                                                      case Some(tn) =>
                                                        mapRuleSegments(rk, trule, tn, Some(rseg), rrule)
                                                      case None =>
                                                        Success(rk)
                                                    }
                                                  case _ =>
                                                    Failure(new IllegalArgumentException(s"UnreifiedRelationshipInversePropertyPredicate: $turp failed to resolve unreified relationship: ${turp.unreifiedRelationshipUUID}"))
                                                }
                                              case None =>
                                                Success(rj)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
  }

  def mapEntityScalarDataProperties
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.entityScalarDataProperties
        .map { tsdp =>
          ( UUID.fromString(tsdp.tboxUUID),
            UUID.fromString(tsdp.domainUUID),
            UUID.fromString(tsdp.rangeUUID) ) -> tsdp
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, domainUUID, rangeUUID), tsdp) = tuple
      tboxM = r.lookupTerminologyBox(tboxUUID)
      domainM = r.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      rangeM = r.lookupTerminologyBoxStatement(rangeUUID) match {
        case Some(dr: api.DataRange) => Some(dr)
        case _ => None
      }
    } yield (tboxM, domainM, rangeM, tsdp)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(domainM), Some(rangeM), tur) => Some(Tuple4(tboxM, domainM, rangeM, tur))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(entityScalarDataProperties = unresolvable)))) {
        case (Success(ri), (tboxM, domainM, rangeM, tsdp)) =>
          val (ej, rsdp) = ri.factory.createEntityScalarDataProperty(
            ri.context,
            tboxM,
            domainM,
            rangeM,
            tsdp.isIdentityCriteria,
            tsdp.name)

          if (!ej.lookupBoxStatements(tboxM).contains(rsdp))
            Failure(new IllegalArgumentException(s"EntityScalarDataProperty not in extent: $rsdp"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tsdp.uuid)).contains(rsdp))
            Failure(new IllegalArgumentException(s"EntityScalarDataProperty: $tsdp vs. $rsdp"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapEntityStructuredDataProperties
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.entityStructuredDataProperties
        .map { tsdp =>
          ( UUID.fromString(tsdp.tboxUUID),
            UUID.fromString(tsdp.domainUUID),
            UUID.fromString(tsdp.rangeUUID) ) -> tsdp
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, domainUUID, rangeUUID), tsdp) = tuple
      tboxM = r.lookupTerminologyBox(tboxUUID)
      domainM = r.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      rangeM = r.lookupTerminologyBoxStatement(rangeUUID) match {
        case Some(dr: api.Structure) => Some(dr)
        case _ => None
      }
    } yield (tboxM, domainM, rangeM, tsdp)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(domainM), Some(rangeM), tur) => Some(Tuple4(tboxM, domainM, rangeM, tur))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(entityStructuredDataProperties = unresolvable)))) {
        case (Success(ri), (tboxM, domainM, rangeM, tsdp)) =>
          val (ej, rsdp) = ri.factory.createEntityStructuredDataProperty(
            ri.context,
            tboxM,
            domainM,
            rangeM,
            tsdp.isIdentityCriteria,
            tsdp.name)

          if (!ej.lookupBoxStatements(tboxM).contains(rsdp))
            Failure(new IllegalArgumentException(s"EntityStructuredDataProperty not in extent: $rsdp"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tsdp.uuid)).contains(rsdp))
            Failure(new IllegalArgumentException(s"EntityStructuredDataProperty: $tsdp vs. $rsdp"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapScalarDataProperties
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.scalarDataProperties
        .map { tsdp =>
          ( UUID.fromString(tsdp.tboxUUID),
            UUID.fromString(tsdp.domainUUID),
            UUID.fromString(tsdp.rangeUUID) ) -> tsdp
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, domainUUID, rangeUUID), tsdp) = tuple
      tboxM = r.lookupTerminologyBox(tboxUUID)
      domainM = r.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(e: api.Structure) => Some(e)
        case _ => None
      }
      rangeM = r.lookupTerminologyBoxStatement(rangeUUID) match {
        case Some(dr: api.DataRange) => Some(dr)
        case _ => None
      }
    } yield (tboxM, domainM, rangeM, tsdp)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(domainM), Some(rangeM), tur) => Some(Tuple4(tboxM, domainM, rangeM, tur))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(scalarDataProperties = unresolvable)))) {
        case (Success(ri), (tboxM, domainM, rangeM, tsdp)) =>
          val (ej, rsdp) = ri.factory.createScalarDataProperty(
            ri.context,
            tboxM,
            domainM,
            rangeM,
            tsdp.name)

          if (!ej.lookupBoxStatements(tboxM).contains(rsdp))
            Failure(new IllegalArgumentException(s"ScalarDataProperty not in extent: $rsdp"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tsdp.uuid)).contains(rsdp))
            Failure(new IllegalArgumentException(s"ScalarDataProperty: $tsdp vs. $rsdp"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapStructuredDataProperties
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.structuredDataProperties
        .map { tsdp =>
          ( UUID.fromString(tsdp.tboxUUID),
            UUID.fromString(tsdp.domainUUID),
            UUID.fromString(tsdp.rangeUUID) ) -> tsdp
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, domainUUID, rangeUUID), tsdp) = tuple
      tboxM = r.lookupTerminologyBox(tboxUUID)
      domainM = r.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(e: api.Structure) => Some(e)
        case _ => None
      }
      rangeM = r.lookupTerminologyBoxStatement(rangeUUID) match {
        case Some(dr: api.Structure) => Some(dr)
        case _ => None
      }
    } yield (tboxM, domainM, rangeM, tsdp)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(domainM), Some(rangeM), tur) => Some(Tuple4(tboxM, domainM, rangeM, tur))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(structuredDataProperties = unresolvable)))) {
        case (Success(ri), (tboxM, domainM, rangeM, tsdp)) =>
          val (ej, rsdp) = ri.factory.createStructuredDataProperty(
            ri.context,
            tboxM,
            domainM,
            rangeM,
            tsdp.name)

          if (!ej.lookupBoxStatements(tboxM).contains(rsdp))
            Failure(new IllegalArgumentException(s"StructuredDataProperty not in extent: $rsdp"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tsdp.uuid)).contains(rsdp))
            Failure(new IllegalArgumentException(s"StructuredDataProperty: $tsdp vs. $rsdp"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapScalarOneOfLiteralAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.scalarOneOfLiteralAxioms
        .map { tsool =>
          ( UUID.fromString(tsool.tboxUUID),
            UUID.fromString(tsool.axiomUUID),
            tsool.valueTypeUUID.map(UUID.fromString) ) -> tsool
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, axiomUUID, valueTypeOUUID), tsool) = tuple
      tboxM = r.lookupTerminologyBox(tboxUUID)
      axiomM = r.lookupTerminologyBoxStatement(axiomUUID) match {
        case Some(e: api.ScalarOneOfRestriction) => Some(e)
        case _ => None
      }
      valueTypeM = valueTypeOUUID.flatMap(r.lookupDataRange)
    } yield (tboxM, axiomM, valueTypeOUUID, valueTypeM, tsool)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || (tuple._3.nonEmpty && tuple._4.isEmpty)).map(_._5)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(axiomM), Some(_), Some(valueTypeM), tsool) => Some(Tuple4(tboxM, axiomM, Some(valueTypeM), tsool))
      case (Some(tboxM), Some(axiomM), None, None, tsool) => Some(Tuple4(tboxM, axiomM, None, tsool))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(scalarOneOfLiteralAxioms = unresolvable)))) {
        case (Success(ri), (tboxM, axiomM, valueTypeM, tsool)) =>
          val (ej, rsool) = ri.factory.createScalarOneOfLiteralAxiom(
            ri.context,
            tboxM,
            axiomM,
            tsool.value,
            valueTypeM)

          if (!ej.lookupBoxStatements(tboxM).contains(rsool))
            Failure(new IllegalArgumentException(s"ScalarOneOfLiteralAxiom not in extent: $rsool"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tsool.uuid)).contains(rsool))
            Failure(new IllegalArgumentException(s"ScalarOneOfLiteralAxiom: $tsool vs. $rsool"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapEntityExistentialRestrictionAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.entityExistentialRestrictionAxioms
        .map { tra =>
          ( UUID.fromString(tra.tboxUUID),
            UUID.fromString(tra.restrictedDomainUUID),
            UUID.fromString(tra.restrictedRangeUUID),
            UUID.fromString(tra.restrictedRelationUUID) ) -> tra
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, domainUUID, rangeUUID, relationUUID), tra) = tuple
      tboxM = r.lookupTerminologyBox(tboxUUID)
      domainM = r.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      rangeM = r.lookupTerminologyBoxStatement(rangeUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      relM = r.lookupTerminologyBoxStatement(relationUUID) match {
        case Some(e: api.EntityRelationship) => Some(e)
        case _ => None
      }
    } yield (tboxM, domainM, rangeM, relM, tra)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty || tuple._4.isEmpty).map(_._5)
    if (unresolvable.nonEmpty) {
      java.lang.System.out.println(s"${unresolvable.size} entityExistentialRestrictionAxioms")
    }
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(domainM), Some(rangeM), Some(relM), tra) => Some(Tuple5(tboxM, domainM, rangeM, relM, tra))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(entityExistentialRestrictionAxioms = unresolvable)))) {
        case (Success(ri), (tboxM, domainM, rangeM, relM, tra)) =>
          val (ej, rra) = ri.factory.createEntityExistentialRestrictionAxiom(
            ri.context,
            tboxM,
            relM,
            domainM,
            rangeM)

          if (!ej.lookupBoxStatements(tboxM).contains(rra))
            Failure(new IllegalArgumentException(s"EntityExistentialRestrictionAxiom not in extent: $rra"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tra.uuid)).contains(rra))
            Failure(new IllegalArgumentException(s"EntityExistentialRestrictionAxiom: $tra vs. $rra"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapEntityUniversalRestrictionAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.entityUniversalRestrictionAxioms
        .map { tra =>
          ( UUID.fromString(tra.tboxUUID),
            UUID.fromString(tra.restrictedDomainUUID),
            UUID.fromString(tra.restrictedRangeUUID),
            UUID.fromString(tra.restrictedRelationUUID) ) -> tra
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, domainUUID, rangeUUID, relationUUID), tra) = tuple
      tboxM = r.lookupTerminologyBox(tboxUUID)
      domainM = r.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      rangeM = r.lookupTerminologyBoxStatement(rangeUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      relM = r.lookupTerminologyBoxStatement(relationUUID) match {
        case Some(e: api.EntityRelationship) => Some(e)
        case _ => None
      }
    } yield (tboxM, domainM, rangeM, relM, tra)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty || tuple._4.isEmpty).map(_._5)
    if (unresolvable.nonEmpty) {
      java.lang.System.out.println(s"${unresolvable.size} entityUniversalRestrictionAxioms")
    }
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(domainM), Some(rangeM), Some(relM), tra) => Some(Tuple5(tboxM, domainM, rangeM, relM, tra))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(entityUniversalRestrictionAxioms = unresolvable)))) {
        case (Success(ri), (tboxM, domainM, rangeM, relM, tra)) =>
          val (ej, rra) = ri.factory.createEntityUniversalRestrictionAxiom(
            ri.context,
            tboxM,
            relM,
            domainM,
            rangeM)

          if (!ej.lookupBoxStatements(tboxM).contains(rra))
            Failure(new IllegalArgumentException(s"EntityUniversalRestrictionAxiom not in extent: $rra"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tra.uuid)).contains(rra))
            Failure(new IllegalArgumentException(s"EntityUniversalRestrictionAxiom: $tra vs. $rra"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapEntityScalarDataPropertyExistentialRestrictionAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.entityScalarDataPropertyExistentialRestrictionAxioms
        .map { tra =>
          ( UUID.fromString(tra.tboxUUID),
            UUID.fromString(tra.restrictedEntityUUID),
            UUID.fromString(tra.scalarPropertyUUID),
            UUID.fromString(tra.scalarRestrictionUUID) ) -> tra
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, domainUUID, relationUUID, rangeUUID), tra) = tuple
      tboxM = r.lookupTerminologyBox(tboxUUID)
      domainM = r.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      rangeM = r.lookupTerminologyBoxStatement(rangeUUID) match {
        case Some(e: api.DataRange) => Some(e)
        case _ => None
      }
      relM = r.lookupTerminologyBoxStatement(relationUUID) match {
        case Some(e: api.EntityScalarDataProperty) => Some(e)
        case _ => None
      }
    } yield (tboxM, domainM, rangeM, relM, tra)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty || tuple._4.isEmpty).map(_._5)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(domainM), Some(rangeM), Some(relM), tra) => Some(Tuple5(tboxM, domainM, rangeM, relM, tra))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(entityScalarDataPropertyExistentialRestrictionAxioms = unresolvable)))) {
        case (Success(ri), (tboxM, domainM, rangeM, relM, tra)) =>
          val (ej, rra) = ri.factory.createEntityScalarDataPropertyExistentialRestrictionAxiom(
            ri.context,
            tboxM,
            domainM,
            relM,
            rangeM)

          if (!ej.lookupBoxStatements(tboxM).contains(rra))
            Failure(new IllegalArgumentException(s"EntityScalarDataPropertyExistentialRestrictionAxiom not in extent: $rra"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tra.uuid)).contains(rra))
            Failure(new IllegalArgumentException(s"EntityScalarDataPropertyExistentialRestrictionAxiom: $tra vs. $rra"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapEntityScalarDataPropertyUniversalRestrictionAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.entityScalarDataPropertyUniversalRestrictionAxioms
        .map { tra =>
          ( UUID.fromString(tra.tboxUUID),
            UUID.fromString(tra.restrictedEntityUUID),
            UUID.fromString(tra.scalarPropertyUUID),
            UUID.fromString(tra.scalarRestrictionUUID) ) -> tra
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, domainUUID, relationUUID, rangeUUID), tra) = tuple
      tboxM = r.lookupTerminologyBox(tboxUUID)
      domainM = r.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      rangeM = r.lookupTerminologyBoxStatement(rangeUUID) match {
        case Some(e: api.DataRange) => Some(e)
        case _ => None
      }
      relM = r.lookupTerminologyBoxStatement(relationUUID) match {
        case Some(e: api.EntityScalarDataProperty) => Some(e)
        case _ => None
      }
    } yield (tboxM, domainM, rangeM, relM, tra)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty || tuple._4.isEmpty).map(_._5)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(domainM), Some(rangeM), Some(relM), tra) => Some(Tuple5(tboxM, domainM, rangeM, relM, tra))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(entityScalarDataPropertyUniversalRestrictionAxioms = unresolvable)))) {
        case (Success(ri), (tboxM, domainM, rangeM, relM, tra)) =>
          val (ej, rra) = ri.factory.createEntityScalarDataPropertyUniversalRestrictionAxiom(
            ri.context,
            tboxM,
            domainM,
            relM,
            rangeM)

          if (!ej.lookupBoxStatements(tboxM).contains(rra))
            Failure(new IllegalArgumentException(s"EntityScalarDataPropertyUniversalRestrictionAxiom not in extent: $rra"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tra.uuid)).contains(rra))
            Failure(new IllegalArgumentException(s"EntityScalarDataPropertyUniversalRestrictionAxiom: $tra vs. $rra"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapEntityScalarDataPropertyParticularRestrictionAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.entityScalarDataPropertyParticularRestrictionAxioms
        .map { tra =>
          ( UUID.fromString(tra.tboxUUID),
            UUID.fromString(tra.restrictedEntityUUID),
            UUID.fromString(tra.scalarPropertyUUID),
            tra.valueTypeUUID.map(UUID.fromString)) -> tra
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, domainUUID, relationUUID, valueTypeOUUID), tra) = tuple
      tboxM = r.lookupTerminologyBox(tboxUUID)
      domainM = r.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      relM = r.lookupTerminologyBoxStatement(relationUUID) match {
        case Some(e: api.EntityScalarDataProperty) => Some(e)
        case _ => None
      }
      valueTypeM = valueTypeOUUID.flatMap(r.lookupDataRange)
    } yield (tboxM, domainM, relM, valueTypeOUUID, valueTypeM, tra)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty || (tuple._4.nonEmpty && tuple._5.isEmpty)).map(_._6)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(domainM), Some(relM), Some(_), Some(valueTypeM), tra) => Some(Tuple5(tboxM, domainM, relM, Some(valueTypeM), tra))
      case (Some(tboxM), Some(domainM), Some(relM), None, None, tra) => Some(Tuple5(tboxM, domainM, relM, None, tra))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(entityScalarDataPropertyParticularRestrictionAxioms = unresolvable)))) {
        case (Success(ri), (tboxM, domainM, relM, valueTypeM, tra)) =>
          val (ej, rra) = ri.factory.createEntityScalarDataPropertyParticularRestrictionAxiom(
            ri.context,
            tboxM,
            domainM,
            relM,
            tra.literalValue,
            valueTypeM)

          if (!ej.lookupBoxStatements(tboxM).contains(rra))
            Failure(new IllegalArgumentException(s"EntityScalarDataPropertyParticularRestrictionAxiom not in extent: $rra"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tra.uuid)).contains(rra))
            Failure(new IllegalArgumentException(s"EntityScalarDataPropertyParticularRestrictionAxiom: $tra vs. $rra"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapEntityStructuredDataPropertyParticularRestrictionAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.entityStructuredDataPropertyParticularRestrictionAxioms
      .map { tra =>
        ( UUID.fromString(tra.tboxUUID),
          UUID.fromString(tra.restrictedEntityUUID),
          UUID.fromString(tra.structuredDataPropertyUUID)) -> tra
      }

    val info = for {
      tuple <- byUUID
      ((tboxUUID, restrictedEntityUUID, structuredDataPropertyUUID), tra) = tuple
      tboxM = r.lookupTerminologyBox(tboxUUID)
      entityM = r.lookupTerminologyBoxStatement(restrictedEntityUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      sdpM = r.lookupTerminologyBoxStatement(structuredDataPropertyUUID) match {
        case Some(sdp: api.StructuredDataProperty) => Some(sdp)
        case _ => None
      }
    } yield (tboxM, entityM, sdpM, tra)

    val unresolvable = info.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = info.flatMap {
      case (Some(tboxM), Some(entityM), Some(sdpM), tra) => Some(Tuple4(tboxM, entityM, sdpM, tra))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(entityStructuredDataPropertyParticularRestrictionAxioms = unresolvable)))) {
        case (Success(ri), (tboxM, entityM, sdpM, tra)) =>
          val (ej, rra) = ri.factory.createEntityStructuredDataPropertyParticularRestrictionAxiom(
            ri.context,
            tboxM,
            sdpM,
            entityM)
          if (!ej.lookupBoxStatements(tboxM).contains(rra))
            Failure(new IllegalArgumentException(s"EntityStructuredDataPropertyParticularRestrictionAxiom not in extent: $rra"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tra.uuid)).contains(rra))
            Failure(new IllegalArgumentException(s"EntityStructuredDataPropertyParticularRestrictionAxiom: $tra vs. $rra"))
          else
            mapRestrictionStructuredDataPropertyContext(ri.copy(context = ej), Seq(tra.uuid -> rra))
        case (Failure(t), _) =>
          Failure(t)
      }
    s
  }

  @tailrec
  def mapRestrictionStructuredDataPropertyContext
  (r: OMLTablesResolver, queue: Seq[(tables.UUID, api.RestrictionStructuredDataPropertyContext)])
  : Try[OMLTablesResolver]
  = if (queue.isEmpty)
    Success(r)
  else {
    val (contextUUID, rcontext) = queue.head

    val scr = r
      .queue
      .restrictionScalarDataPropertyValues
      .partition(_.structuredDataPropertyContextUUID == contextUUID)

    val ssr = r
      .queue
      .restrictionStructuredDataPropertyTuples
      .partition(_.structuredDataPropertyContextUUID == contextUUID)

    val r0 = r.copy(queue = r.queue.copy(
      restrictionScalarDataPropertyValues = scr._2,
      restrictionStructuredDataPropertyTuples = ssr._2))

    val values =
      scr._1.map { v =>
        val sdpUUID = UUID.fromString(v.scalarDataPropertyUUID)
        val rsdp = r0.lookupTerminologyBoxStatement(sdpUUID) match {
          case Some(sdp: api.ScalarDataProperty) => Some(sdp)
          case _ => None
        }
        val vtUUID = v.valueTypeUUID.map(UUID.fromString)
        val rvt = vtUUID.flatMap { id =>
          r0.lookupTerminologyBoxStatement(id) match {
            case Some(vt: api.DataRange) => Some(vt)
            case _ => None
          }
        }
        (rsdp, vtUUID, rvt) -> v
      }

    val vUnresolvable = values.flatMap {
      case ((None, _, _), v) => Some(v)
      case ((_, Some(_), None), v) => Some(v)
      case _ => None
    }

    val vResolvable = values.flatMap {
      case ((Some(rsdp), _, rvt), v) => Some((rsdp, rvt, v))
      case _ => None
    }

    val r1 =
      r0.copy(queue = r0.queue.copy(restrictionScalarDataPropertyValues =
        r0.queue.restrictionScalarDataPropertyValues ++ vUnresolvable))

    val r2 =
      vResolvable.foldLeft[OMLTablesResolver](r1) {
        case (ri, (rsdp, rvt, tv)) =>
          val (ej, _) = ri.factory.createRestrictionScalarDataPropertyValue(
            ri.context,
            rsdp,
            tv.scalarPropertyValue,
            rcontext,
            rvt)
          ri.copy(context = ej)
      }

    val tuples =
      ssr._1.map { t =>
        val sdpUUID = UUID.fromString(t.structuredDataPropertyUUID)
        val rsdp = r2.lookupTerminologyBoxStatement(sdpUUID) match {
          case Some(sdp: api.StructuredDataProperty) => Some(sdp)
          case _ => None
        }
        rsdp -> t
      }

    val tUnresolvable = tuples.flatMap {
      case (None, t) => Some(t)
      case _ => None
    }

    val tResolvable = tuples.flatMap {
      case (Some(rsdp), t) => Some((rsdp, t))
      case _ => None
    }

    val r3 =
      r2.copy(queue =
        r2.queue.copy(restrictionStructuredDataPropertyTuples =
          r2.queue.restrictionStructuredDataPropertyTuples ++ tUnresolvable))

    val (r4, more) =
      tResolvable
        .foldLeft[(OMLTablesResolver, Seq[(tables.UUID, api.RestrictionStructuredDataPropertyContext)])]{
        (r3, Seq.empty[(tables.UUID, api.RestrictionStructuredDataPropertyContext)])
      } {
        case ((ri, acc), (rsdp, tv)) =>
          val (ej, rv) = ri.factory.createRestrictionStructuredDataPropertyTuple(
            ri.context,
            rsdp,
            rcontext)
          (ri.copy(context = ej), acc :+ (tv.uuid -> rv))
      }

    mapRestrictionStructuredDataPropertyContext(r4, more ++ queue.tail)
  }

  def mapAspectSpecializationAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.aspectSpecializationAxioms
        .map { tax =>
          ( UUID.fromString(tax.tboxUUID),
            UUID.fromString(tax.superAspectUUID),
            UUID.fromString(tax.subEntityUUID) ) -> tax
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, superUUID, subUUID), tax) = tuple
      tboxM = r.lookupTerminologyBox(tboxUUID)
      superM = r.lookupTerminologyBoxStatement(superUUID) match {
        case Some(e: api.Aspect) => Some(e)
        case _ => None
      }
      subM = r.lookupTerminologyBoxStatement(subUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
    } yield (tboxM, superM, subM, tax)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(superM), Some(subM), tax) => Some(Tuple4(tboxM, superM, subM, tax))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(aspectSpecializationAxioms = unresolvable)))) {
        case (Success(ri), (tboxM, superM, subM, tax)) =>
          val (ej, rax) = ri.factory.createAspectSpecializationAxiom(
            ri.context,
            tboxM,
            superM,
            subM)

          if (!ej.lookupBoxStatements(tboxM).contains(rax))
            Failure(new IllegalArgumentException(s"AspectSpecializationAxiom not in extent: $rax"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tax.uuid)).contains(rax))
            Failure(new IllegalArgumentException(s"AspectSpecializationAxiom: $tax vs. $rax"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapConceptSpecializationAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.conceptSpecializationAxioms
        .map { tax =>
          ( UUID.fromString(tax.tboxUUID),
            UUID.fromString(tax.superConceptUUID),
            UUID.fromString(tax.subConceptUUID) ) -> tax
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, superUUID, subUUID), tax) = tuple
      tboxM = r.lookupTerminologyBox(tboxUUID)
      superM = r.lookupTerminologyBoxStatement(superUUID) match {
        case Some(e: api.Concept) => Some(e)
        case _ => None
      }
      subM = r.lookupTerminologyBoxStatement(subUUID) match {
        case Some(e: api.Concept) => Some(e)
        case _ => None
      }
    } yield (tboxM, superM, subM, tax)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    if (unresolvable.nonEmpty) {
      java.lang.System.out.println(s"${unresolvable.size} concept specializations")
    }
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(superM), Some(subM), tax) => Some(Tuple4(tboxM, superM, subM, tax))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(conceptSpecializationAxioms = unresolvable)))) {
        case (Success(ri), (tboxM, superM, subM, tax)) =>
          val (ej, rax) = ri.factory.createConceptSpecializationAxiom(
            ri.context,
            tboxM,
            superM,
            subM)

          if (!ej.lookupBoxStatements(tboxM).contains(rax))
            Failure(new IllegalArgumentException(s"ConceptSpecializationAxiom not in extent: $rax"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tax.uuid)).contains(rax))
            Failure(new IllegalArgumentException(s"ConceptSpecializationAxiom: $tax vs. $rax"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapReifiedRelationshipSpecializationAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val byUUID =
      r.queue.reifiedRelationshipSpecializationAxioms
        .map { tax =>
          ( UUID.fromString(tax.tboxUUID),
            UUID.fromString(tax.superRelationshipUUID),
            UUID.fromString(tax.subRelationshipUUID) ) -> tax
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, superUUID, subUUID), tax) = tuple
      tboxM = r.lookupTerminologyBox(tboxUUID)
      superM = r.lookupTerminologyBoxStatement(superUUID) match {
        case Some(e: api.ReifiedRelationship) => Some(e)
        case _ => None
      }
      subM = r.lookupTerminologyBoxStatement(subUUID) match {
        case Some(e: api.ReifiedRelationship) => Some(e)
        case _ => None
      }
    } yield (tboxM, superM, subM, tax)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(superM), Some(subM), tax) => Some(Tuple4(tboxM, superM, subM, tax))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(reifiedRelationshipSpecializationAxioms = unresolvable)))) {
        case (Success(ri), (tboxM, superM, subM, tax)) =>
          val (ej, rax) = ri.factory.createReifiedRelationshipSpecializationAxiom(
            ri.context,
            tboxM,
            superM,
            subM)

          if (!ej.lookupBoxStatements(tboxM).contains(rax))
            Failure(new IllegalArgumentException(s"ReifiedRelationshipSpecializationAxiom not in extent: $rax"))
          else if (!ej.lookupTerminologyBoxStatement(UUID.fromString(tax.uuid)).contains(rax))
            Failure(new IllegalArgumentException(s"ReifiedRelationshipSpecializationAxiom: $tax vs. $rax"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapRootConceptTaxonomyAxioms
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val info =
      r.queue.rootConceptTaxonomyAxioms
        .map { tax =>
          val bundleUUID = UUID.fromString(tax.bundleUUID)
          val rootUUID = UUID.fromString(tax.rootUUID)

          val bundleM = r.lookupBundle(bundleUUID)
          val rootM = r.lookupTerminologyBoxStatement(rootUUID) match {
            case Some(e: api.Concept) => Some(e)
            case _ => None
          }
          (bundleM, rootM, tax)
        }

    val unresolvable = info.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty).map(_._3)
    val resolvable = info.flatMap {
      case (Some(bundleM), Some(rootM), tax) => Some(Tuple3(bundleM, rootM, tax))
      case _ => None
    }

    val r0 = r.copy(queue = r.queue.copy(rootConceptTaxonomyAxioms = unresolvable))
    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r0)) {
        case (Success(ri), (bundleM, rootM, tax)) =>
          val (ej, rax) = ri.factory.createRootConceptTaxonomyAxiom(ri.context, bundleM, rootM)

          if (!ej.lookupBundleStatements(bundleM).contains(rax))
            Failure(new IllegalArgumentException(s"RootConceptTaxonomyAxiom not in extent: $rax"))
          else if (!ej.lookupTerminologyBundleStatement(UUID.fromString(tax.uuid)).contains(rax))
            Failure(new IllegalArgumentException(s"RootConceptTaxonomyAxiom: $tax vs. $rax"))
          else
            mapDisjunctions(ri.copy(context = ej), Seq(rax -> tax.uuid))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  @tailrec
  def mapDisjunctions
  (r: OMLTablesResolver, queue: Seq[(api.ConceptTreeDisjunction, tables.UUID)])
  : Try[OMLTablesResolver]
  = if (queue.isEmpty)
    Success(r)
  else {
    val (conceptTreeDisjunctParent, conceptTreeDisjunctUUID) = queue.head
    val as = r.queue.anonymousConceptUnionAxioms.partition(_.disjointTaxonomyParentUUID == conceptTreeDisjunctUUID)
    val ss = r.queue.specificDisjointConceptAxioms.partition(_.disjointTaxonomyParentUUID == conceptTreeDisjunctUUID)

    val r1 = r.copy(queue = r.queue.copy(
      anonymousConceptUnionAxioms = as._2,
      specificDisjointConceptAxioms = ss._2))

    val r2 = as._1.foldLeft[Try[(OMLTablesResolver, Seq[(api.ConceptTreeDisjunction, tables.UUID)])]]{
      Success(r1 -> Seq.empty[(api.ConceptTreeDisjunction, tables.UUID)])
    } {
        case (Success((ri, acc)), tax) =>
          val (ej, rax) = ri.factory.createAnonymousConceptUnionAxiom(ri.context, conceptTreeDisjunctParent, tax.name)

          if (!ej.conceptTreeDisjunctionOfDisjointUnionOfConceptsAxiom.get(rax).contains(conceptTreeDisjunctParent))
            Failure(new IllegalArgumentException(s"AnonymousConceptUnionAxiom not in conceptTreeDisjunctionOfDisjointUnionOfConceptsAxiom: $rax"))
          else if (!ej.lookupDisjunctions(conceptTreeDisjunctParent).contains(rax))
            Failure(new IllegalArgumentException(s"AnonymousConceptUnionAxiom: not in lookupDisjunctions: $tax vs. $rax"))
          else
            Success(ri.copy(context = ej) -> (acc :+ (rax -> tax.uuid)))
        case (Failure(f), _) =>
          Failure(f)
      }

    val r3 = ss._1.foldLeft[Try[(OMLTablesResolver, Seq[(api.ConceptTreeDisjunction, tables.UUID)])]](r2) {
      case (Success((ri, acc)), tax) =>
        ri.lookupTerminologyBoxStatement(UUID.fromString(tax.disjointLeafUUID)) match {
          case Some(leaf: api.Concept) =>
            val (ej, rax) = ri.factory.createSpecificDisjointConceptAxiom(ri.context, conceptTreeDisjunctParent, leaf)

            if (!ej.conceptTreeDisjunctionOfDisjointUnionOfConceptsAxiom.get(rax).contains(conceptTreeDisjunctParent))
              Failure(new IllegalArgumentException(s"SpecificDisjointConceptAxiom not in conceptTreeDisjunctionOfDisjointUnionOfConceptsAxiom: $rax"))
            else if (!ej.lookupDisjunctions(conceptTreeDisjunctParent).contains(rax))
              Failure(new IllegalArgumentException(s"SpecificDisjointConceptAxiom: not in lookupDisjunctions: $tax vs. $rax"))
            else
              Success(ri.copy(context = ej) -> acc)
          case _ =>
            Failure(new IllegalArgumentException(s"SpecificDisjointConceptAxiom: leaf concept not found: ${tax.disjointLeafUUID}"))
        }
      case (Failure(f), _) =>
        Failure(f)
    }

    r3 match {
      case Success((r4, acc)) =>
        mapDisjunctions(r4, acc ++ queue.tail)
      case Failure(f) =>
        Failure(f)
    }
  }

  def mapConceptInstances
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val info = r.queue.conceptInstances.map { tci =>
      val dboxUUID = UUID.fromString(tci.descriptionBoxUUID)
      val cUUID = UUID.fromString(tci.singletonConceptClassifierUUID)
      val dboxM = r.lookupDescriptionBox(dboxUUID)
      val cM = r.lookupTerminologyBoxStatement(cUUID) match {
        case Some(c: api.Concept) => Some(c)
        case _ => None
      }
      (dboxM, cM, tci)
    }

    val unresolvable = info.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty).map(_._3)
    val resolvable = info.flatMap {
      case (Some(dboxM), Some(cM), tci) => Some((dboxM, cM, tci))
      case _ => None
    }

    val r1 = r.copy(queue = r.queue.copy(conceptInstances = unresolvable))

    val s = resolvable.foldLeft[Try[OMLTablesResolver]](Success(r1)) {
      case (Success(ri), (dboxM, cM, tci)) =>
        val (ej, rci) = ri.factory.createConceptInstance(
          ri.context,
          dboxM,
          cM,
          tci.name)

        if (!ej.lookupConceptInstances(dboxM).contains(rci))
          Failure(new IllegalArgumentException(s"ConceptInstance not in extent: $rci"))
        else if (!ej.lookupConceptInstance(UUID.fromString(tci.uuid)).contains(rci))
          Failure(new IllegalArgumentException(s"ConceptInstance: $rci vs. $tci"))
        else
          Success(ri.copy(context = ej))
      case (Failure(t), _) =>
        Failure(t)
    }

    s
  }

  def mapReifiedRelationshipInstances
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val info = r.queue.reifiedRelationshipInstances.map { trri =>
      val dboxUUID = UUID.fromString(trri.descriptionBoxUUID)
      val rrUUID = UUID.fromString(trri.singletonReifiedRelationshipClassifierUUID)
      val dboxM = r.lookupDescriptionBox(dboxUUID)
      val rrM = r.lookupTerminologyBoxStatement(rrUUID) match {
        case Some(rr: api.ReifiedRelationship) => Some(rr)
        case _ => None
      }
      (dboxM, rrM, trri)
    }

    val unresolvable = info.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty).map(_._3)
    val resolvable = info.flatMap {
      case (Some(dboxM), Some(rrM), trri) => Some((dboxM, rrM, trri))
      case _ => None
    }

    val r1 = r.copy(queue = r.queue.copy(reifiedRelationshipInstances = unresolvable))

    val s = resolvable.foldLeft[Try[OMLTablesResolver]](Success(r1)) {
      case (Success(ri), (dboxM, rrM, trri)) =>
        val (ej, rrri) = ri.factory.createReifiedRelationshipInstance(
          ri.context,
          dboxM,
          rrM,
          trri.name)

        if (!ej.lookupReifiedRelationshipInstances(dboxM).contains(rrri))
          Failure(new IllegalArgumentException(s"ReifiedRelationshipInstance not in extent: $rrri"))
        else if (!ej.lookupReifiedRelationshipInstance(UUID.fromString(trri.uuid)).contains(rrri))
          Failure(new IllegalArgumentException(s"ReifiedRelationshipInstance: $rrri vs. $trri"))
        else
          Success(ri.copy(context = ej))
      case (Failure(t), _) =>
        Failure(t)
    }

    s
  }

  def mapReifiedRelationshipInstanceDomains
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val info = r.queue.reifiedRelationshipInstanceDomains.map { trrid =>
      val dboxUUID = UUID.fromString(trrid.descriptionBoxUUID)
      val rriUUID = UUID.fromString(trrid.reifiedRelationshipInstanceUUID)
      val dUUID = UUID.fromString(trrid.domainUUID)
      val dboxM = r.lookupDescriptionBox(dboxUUID)
      val rriM = r.lookupReifiedRelationshipInstance(rriUUID)
      val dM = r.lookupConceptualEntitySingletonInstance(dUUID)
      (dboxM, rriM, dM, trrid)
    }

    val unresolvable = info.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = info.flatMap {
      case (Some(dboxM), Some(rriM), Some(dM), trrid) => Some((dboxM, rriM, dM, trrid))
      case _ => None
    }

    val r1 = r.copy(queue = r.queue.copy(reifiedRelationshipInstanceDomains = unresolvable))

    val s = resolvable.foldLeft[Try[OMLTablesResolver]](Success(r1)) {
      case (Success(ri), (dboxM, rriM, dM, trrid)) =>
        val (ej, rrrid) = ri.factory.createReifiedRelationshipInstanceDomain(
          ri.context,
          dboxM,
          rriM,
          dM)

        if (!ej.lookupReifiedRelationshipInstanceDomains(dboxM).contains(rrrid))
          Failure(new IllegalArgumentException(s"ReifiedRelationshipInstanceDomain not in extent: $rrrid"))
        else if (!ej.lookupReifiedRelationshipInstanceDomain(UUID.fromString(trrid.uuid)).contains(rrrid))
          Failure(new IllegalArgumentException(s"ReifiedRelationshipInstanceDomain: $rrrid vs. $trrid"))
        else
          Success(ri.copy(context = ej))
      case (Failure(t), _) =>
        Failure(t)
    }

    s
  }

  def mapReifiedRelationshipInstanceRanges
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val info = r.queue.reifiedRelationshipInstanceRanges.map { trrir =>
      val dboxUUID = UUID.fromString(trrir.descriptionBoxUUID)
      val rriUUID = UUID.fromString(trrir.reifiedRelationshipInstanceUUID)
      val rUUID = UUID.fromString(trrir.rangeUUID)
      val dboxM = r.lookupDescriptionBox(dboxUUID)
      val rriM = r.lookupReifiedRelationshipInstance(rriUUID)
      val rM = r.lookupConceptualEntitySingletonInstance(rUUID)
      (dboxM, rriM, rM, trrir)
    }

    val unresolvable = info.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = info.flatMap {
      case (Some(dboxM), Some(rriM), Some(rM), trrir) => Some((dboxM, rriM, rM, trrir))
      case _ => None
    }

    val r1 = r.copy(queue = r.queue.copy(reifiedRelationshipInstanceRanges = unresolvable))

    val s = resolvable.foldLeft[Try[OMLTablesResolver]](Success(r1)) {
      case (Success(ri), (dboxM, rriM, rM, trrir)) =>
        val (ej, rrrir) = ri.factory.createReifiedRelationshipInstanceRange(
          ri.context,
          dboxM,
          rriM,
          rM)

        if (!ej.lookupReifiedRelationshipInstanceRanges(dboxM).contains(rrrir))
          Failure(new IllegalArgumentException(s"ReifiedRelationshipInstanceRange not in extent: $rrrir"))
        else if (!ej.lookupReifiedRelationshipInstanceRange(UUID.fromString(trrir.uuid)).contains(rrrir))
          Failure(new IllegalArgumentException(s"ReifiedRelationshipInstanceRange: $rrrir vs. $trrir"))
        else
          Success(ri.copy(context = ej))
      case (Failure(t), _) =>
        Failure(t)
    }

    s
  }

  def mapUnreifiedRelationshipInstanceTuples
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val info = r.queue.unreifiedRelationshipInstanceTuples.map { turi =>
      val dboxUUID = UUID.fromString(turi.descriptionBoxUUID)
      val urUUID = UUID.fromString(turi.unreifiedRelationshipUUID)
      val dUUID = UUID.fromString(turi.domainUUID)
      val rUUID = UUID.fromString(turi.rangeUUID)
      val dboxM = r.lookupDescriptionBox(dboxUUID)
      val urM = r.lookupTerminologyBoxStatement(urUUID) match {
        case Some(ur: api.UnreifiedRelationship) => Some(ur)
        case _ => None
      }
      val dM = r.lookupConceptualEntitySingletonInstance(dUUID)
      val rM = r.lookupConceptualEntitySingletonInstance(rUUID)
      (dboxM, urM, dM, rM, turi)
    }

    val unresolvable = info.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty || tuple._4.isEmpty).map(_._5)
    val resolvable = info.flatMap {
      case (Some(dboxM), Some(urM), Some(dM), Some(rM), turi) => Some((dboxM, urM, dM, rM, turi))
      case _ => None
    }

    val r1 = r.copy(queue = r.queue.copy(unreifiedRelationshipInstanceTuples = unresolvable))

    val s = resolvable.foldLeft[Try[OMLTablesResolver]](Success(r1)) {
      case (Success(ri), (dboxM, urM, dM, rM, turi)) =>
        val (ej, ruri) = ri.factory.createUnreifiedRelationshipInstanceTuple(
          ri.context,
          dboxM,
          urM,
          dM,
          rM)

        if (!ej.lookupUnreifiedRelationshipInstanceTuples(dboxM).contains(ruri))
          Failure(new IllegalArgumentException(s"UnreifiedRelationshipInstanceTuple not in extent: $ruri"))
        else if (!ej.lookupUnreifiedRelationshipInstanceTuple(UUID.fromString(turi.uuid)).contains(ruri))
          Failure(new IllegalArgumentException(s"UnreifiedRelationshipInstanceTuple: $ruri vs. $turi"))
        else
          Success(ri.copy(context = ej))
      case (Failure(t), _) =>
        Failure(t)
    }

    s
  }

  def mapSingletonInstanceScalarDataPropertyValues
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val info = r.queue.singletonInstanceScalarDataPropertyValues.map { tvi =>
      val dboxUUID = UUID.fromString(tvi.descriptionBoxUUID)
      val ciUUID = UUID.fromString(tvi.singletonInstanceUUID)
      val dpUUID = UUID.fromString(tvi.scalarDataPropertyUUID)
      val vtUUID = tvi.valueTypeUUID.map(UUID.fromString)
      val dboxM = r.lookupDescriptionBox(dboxUUID)
      val ciM = r.lookupConceptualEntitySingletonInstance(ciUUID)
      val dpM = r.lookupTerminologyBoxStatement(dpUUID) match {
        case Some(dp: api.EntityScalarDataProperty) => Some(dp)
        case _ => None
      }
      val vtM = vtUUID.flatMap { id => r.lookupTerminologyBoxStatement(id) match {
        case Some(vt: api.DataRange) => Some(vt)
        case _ => None
      }}
      (dboxM, ciM, dpM, vtUUID, vtM, tvi)
    }

    val unresolvable = info.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty || (tuple._4.nonEmpty && tuple._5.isEmpty)).map(_._6)
    val resolvable = info.flatMap {
      case (Some(dboxM), Some(ciM), Some(dpM), _, vtM, tvi) => Some((dboxM, ciM, dpM, vtM, tvi))
      case _ => None
    }

    val r1 = r.copy(queue = r.queue.copy(singletonInstanceScalarDataPropertyValues = unresolvable))

    val s = resolvable.foldLeft[Try[OMLTablesResolver]](Success(r1)) {
      case (Success(ri), (dboxM, ciM, dpM, vtM, tvi)) =>
        val (ej, rvi) = ri.factory.createSingletonInstanceScalarDataPropertyValue(
          ri.context,
          dboxM,
          ciM,
          dpM,
          tvi.scalarPropertyValue,
          vtM)

        if (!ej.lookupSingletonScalarDataPropertyValues(dboxM).contains(rvi))
          Failure(new IllegalArgumentException(s"SingletonInstanceScalarDataPropertyValue not in extent: $rvi"))
        else if (!ej.lookupSingletonInstanceScalarDataPropertyValue(UUID.fromString(tvi.uuid)).contains(rvi))
          Failure(new IllegalArgumentException(s"SingletonInstanceScalarDataPropertyValue: $rvi vs. $tvi"))
        else
          Success(ri.copy(context = ej))
      case (Failure(t), _) =>
        Failure(t)
    }

    s
  }

  def mapSingletonInstanceStructuredDataPropertyValues
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {
    val info = r.queue.singletonInstanceStructuredDataPropertyValues.map { tvi =>
      val dboxUUID = UUID.fromString(tvi.descriptionBoxUUID)
      val ciUUID = UUID.fromString(tvi.singletonInstanceUUID)
      val dpUUID = UUID.fromString(tvi.structuredDataPropertyUUID)
      val dboxM = r.lookupDescriptionBox(dboxUUID)
      val ciM = r.lookupConceptualEntitySingletonInstance(ciUUID)
      val dpM = r.lookupTerminologyBoxStatement(dpUUID) match {
        case Some(dp: api.EntityStructuredDataProperty) => Some(dp)
        case _ => None
      }
      (dboxM, ciM, dpM, tvi)
    }

    val unresolvable = info.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = info.flatMap {
      case (Some(dboxM), Some(ciM), Some(dpM), tvi) => Some((dboxM, ciM, dpM, tvi))
      case _ => None
    }

    val r1 = r.copy(queue = r.queue.copy(singletonInstanceStructuredDataPropertyValues = unresolvable))

    val s = resolvable.foldLeft[Try[OMLTablesResolver]](Success(r1)) {
      case (Success(ri), (dboxM, ciM, dpM, tvi)) =>
        val (ej, rvi) = ri.factory.createSingletonInstanceStructuredDataPropertyValue(
          ri.context,
          dboxM,
          ciM,
          dpM)

        if (!ej.lookupSingletonStructuredDataPropertyValues(dboxM).contains(rvi))
          Failure(new IllegalArgumentException(s"SingletonInstanceStructuredDataPropertyValue not in extent: $rvi"))
        else if (!ej.lookupSingletonInstanceStructuredDataPropertyValue(UUID.fromString(tvi.uuid)).contains(rvi))
          Failure(new IllegalArgumentException(s"SingletonInstanceStructuredDataPropertyValue: $rvi vs. $tvi"))
        else
          mapSingletonInstanceStructuredDataPropertyContext(ri.copy(context = ej), Seq(tvi.uuid -> rvi))
      case (Failure(t), _) =>
        Failure(t)
    }

    s
  }

  @tailrec
  def mapSingletonInstanceStructuredDataPropertyContext
  (r: OMLTablesResolver, queue: Seq[(tables.UUID, api.SingletonInstanceStructuredDataPropertyContext)])
  : Try[OMLTablesResolver]
  = if (queue.isEmpty)
    Success(r)
  else {
    val (contextUUID, rcontext) = queue.head

    val scr = r
      .queue
      .scalarDataPropertyValues
      .partition(_.structuredDataPropertyContextUUID == contextUUID)

    val str = r
      .queue
      .structuredDataPropertyTuples
      .partition(_.structuredDataPropertyContextUUID == contextUUID)

    val r0 = r.copy(queue = r.queue.copy(
      scalarDataPropertyValues = scr._2,
      structuredDataPropertyTuples = str._2))

    val values =
      scr._1.map { tvi =>
      val dpUUID = UUID.fromString(tvi.scalarDataPropertyUUID)
      val vtUUID = tvi.valueTypeUUID.map(UUID.fromString)
      val dpM = r.lookupTerminologyBoxStatement(dpUUID) match {
        case Some(dp: api.DataRelationshipToScalar) => Some(dp)
        case _ => None
      }
      val vtM = vtUUID.flatMap { id => r.lookupTerminologyBoxStatement(id) match {
        case Some(vt: api.DataRange) => Some(vt)
        case _ => None
      }}
      (dpM, vtUUID, vtM, tvi)
    }

    val vUnresolvable = values.filter(tuple => tuple._1.isEmpty || (tuple._2.nonEmpty && tuple._3.isEmpty)).map(_._4)
    val vResolvable = values.flatMap {
      case (Some(dpM), _, vtM, tvi) => Some((dpM, vtM, tvi))
      case _ => None
    }

    val r1 = r0.copy(queue = r0.queue.copy(scalarDataPropertyValues =
      r0.queue.scalarDataPropertyValues ++ vUnresolvable))

    val tr2 = vResolvable.foldLeft[Try[OMLTablesResolver]](Success(r1)) {
      case (Success(ri), (dpM, vtM, tvi)) =>
        val (ej, rvi) = ri.factory.createScalarDataPropertyValue(
          ri.context,
          dpM,
          tvi.scalarPropertyValue,
          rcontext,
          vtM)

        if (!ej.lookupScalarDataPropertyValues(rcontext).contains(rvi))
          Failure(new IllegalArgumentException(s"ScalarDataPropertyValue not in extent: $rvi"))
        else if (!ej.lookupScalarDataPropertyValue(UUID.fromString(tvi.uuid)).contains(rvi))
          Failure(new IllegalArgumentException(s"ScalarDataPropertyValue: $rvi vs. $tvi"))
        else
          Success(ri.copy(context = ej))
      case (Failure(t), _) =>
        Failure(t)
    }

    tr2 match {
      case Success(r2) =>

        val tuples =
          str._1.map { tvi =>
            val dpUUID = UUID.fromString(tvi.structuredDataPropertyUUID)
            val dpM = r.lookupTerminologyBoxStatement(dpUUID) match {
              case Some(dp: api.DataRelationshipToStructure) => Some(dp)
              case _ => None
            }
            (dpM, tvi)
          }

        val tUnresolvable = tuples.filter(tuple => tuple._1.isEmpty).map(_._2)
        val tResolvable = tuples.flatMap {
          case (Some(dpM), tvi) => Some((dpM, tvi))
          case _ => None
        }

        val r3 = r2.copy(queue = r2.queue.copy(structuredDataPropertyTuples =
          r2.queue.structuredDataPropertyTuples ++ tUnresolvable))

        val tr4 = tResolvable
          .foldLeft[Try[(OMLTablesResolver, Seq[(tables.UUID, api.SingletonInstanceStructuredDataPropertyContext)])]] {
          Success(r3 -> Seq.empty[(tables.UUID, api.SingletonInstanceStructuredDataPropertyContext)])
        } {
          case (Success((ri, acc)), (dpM, tvi)) =>
            val (ej, rvi) = ri.factory.createStructuredDataPropertyTuple(
              ri.context,
              dpM,
              rcontext)

            if (!ej.lookupStructuredPropertyTuples(rcontext).contains(rvi))
              Failure(new IllegalArgumentException(s"StructuredDataPropertyTuple not in extent: $rvi"))
            else if (!ej.lookupStructuredDataPropertyTuple(UUID.fromString(tvi.uuid)).contains(rvi))
              Failure(new IllegalArgumentException(s"StructuredDataPropertyTuple: $rvi vs. $tvi"))
            else
              Success(ri.copy(context = ej) -> (acc :+ (tvi.uuid -> rvi)))
          case (Failure(t), _) =>
            Failure(t)
        }

        tr4 match {
          case Success((r4, more)) =>
            mapSingletonInstanceStructuredDataPropertyContext(r4, more ++ queue.tail)
          case Failure(t) =>
            Failure(t)
        }

      case Failure(t) =>
        Failure(t)
    }
  }

  def mapAnnotations
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {

    val byUUID
    : Seq[((Option[api.AnnotationProperty], Option[api.Element]), tables.AnnotationPropertyValue)]
    = r.queue.annotationPropertyValues
        .map { apv =>
          ( r.lookupAnnotationProperty(UUID.fromString(apv.propertyUUID)),
            r.lookupElement(UUID.fromString(apv.subjectUUID))
          ) -> apv
        }

    val unresolvable
    : Seq[tables.AnnotationPropertyValue]
    = byUUID.flatMap {
      case ((None, _), apv) =>
        Some(apv)
      case ((_, None), apv) =>
        Some(apv)
      case (_, _) =>
        None
    }

    val resolvable
    : Seq[(api.AnnotationProperty, api.Element, tables.AnnotationPropertyValue)]
    = byUUID.flatMap {
      case ((Some(rap), Some(re)), apv) =>
        Some(Tuple3(rap, re, apv))
      case _ =>
        None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]]{
        Success(r.copy(queue = r.queue.copy(annotationPropertyValues = unresolvable)))
      } {
        case (Success(ri), (rap, re, apv)) =>
          val (ej, ra) = ri.factory.createAnnotationPropertyValue(ri.context, re, rap, apv.value)
          if (!ej.lookupAnnotationPropertyValue(ra.uuid).exists { a => a.property == rap && a.value == apv.value })
            Failure(new IllegalArgumentException(s"Annotation not in extent: $ra"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

}
