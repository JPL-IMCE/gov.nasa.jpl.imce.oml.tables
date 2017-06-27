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
  : Set[resolver.api.Annotation]
  = key.fold[Set[resolver.api.Annotation]](Set.empty[resolver.api.Annotation]) { lookupAnnotations }

  def lookupAnnotations(key: resolver.api.Module)
  : Set[resolver.api.Annotation]
  = OMLTablesResolver.collectFirstOption(allContexts)(_.annotations.get(key))
    .getOrElse(Set.empty[resolver.api.Annotation])

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
    // TerminologyBundleAxiom relationships
    step3a <- mapBundledTerminologyAxioms(step2c)
    // Relational terms
    step4a <- mapRestrictedDataRanges(step3a)
    step4b <- mapReifiedRelationships(step4a)
    step4c <- mapUnreifiedRelationships(step4b)
    // DataRelationships
    step5a <- mapEntityScalarDataProperties(step4c)
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
    // -- SpecializationAxiom
    step9a <- mapAspectSpecializationAxioms(step8c)
    step9b <- mapConceptSpecializationAxioms(step9a)
    step9c <- mapReifiedRelationshipSpecializationAxioms(step9b)
    // TerminologyBundleStatements
    step10 <- mapRootConceptTaxonomyAxioms(step9c)
    // Annotations
    step11 <- mapAnnotations(step10)
  } yield
    step11

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
      rangeM = r.lookupTerminologyBoxStatement(domainUUID) match {
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
      rangeM = r.lookupTerminologyBoxStatement(domainUUID) match {
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
      rangeM = r.lookupTerminologyBoxStatement(domainUUID) match {
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
            UUID.fromString(tsool.axiomUUID) ) -> tsool
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, axiomUUID), tsool) = tuple
      tboxM = r.lookupTerminologyBox(tboxUUID)
      axiomM = r.lookupTerminologyBoxStatement(axiomUUID) match {
        case Some(e: api.ScalarOneOfRestriction) => Some(e)
        case _ => None
      }
    } yield (tboxM, axiomM, tsool)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty).map(_._3)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(axiomM), tsool) => Some(Tuple3(tboxM, axiomM, tsool))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(scalarOneOfLiteralAxioms = unresolvable)))) {
        case (Success(ri), (tboxM, axiomM, tsool)) =>
          val (ej, rsool) = ri.factory.createScalarOneOfLiteralAxiom(
            ri.context,
            tboxM,
            axiomM,
            tsool.value)

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
      rangeM = r.lookupTerminologyBoxStatement(domainUUID) match {
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
      rangeM = r.lookupTerminologyBoxStatement(domainUUID) match {
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
            UUID.fromString(tra.scalarPropertyUUID) ) -> tra
        }

    val byTBox = for {
      tuple <- byUUID
      ((tboxUUID, domainUUID, relationUUID), tra) = tuple
      tboxM = r.lookupTerminologyBox(tboxUUID)
      domainM = r.lookupTerminologyBoxStatement(domainUUID) match {
        case Some(e: api.Entity) => Some(e)
        case _ => None
      }
      relM = r.lookupTerminologyBoxStatement(relationUUID) match {
        case Some(e: api.EntityScalarDataProperty) => Some(e)
        case _ => None
      }
    } yield (tboxM, domainM, relM, tra)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty || tuple._3.isEmpty).map(_._4)
    val resolvable = byTBox.flatMap {
      case (Some(tboxM), Some(domainM), Some(relM), tra) => Some(Tuple4(tboxM, domainM, relM, tra))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(entityScalarDataPropertyParticularRestrictionAxioms = unresolvable)))) {
        case (Success(ri), (tboxM, domainM, relM, tra)) =>
          val (ej, rra) = ri.factory.createEntityScalarDataPropertyParticularRestrictionAxiom(
            ri.context,
            tboxM,
            domainM,
            relM,
            tra.literalValue)

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
    val byUUID =
      r.queue.rootConceptTaxonomyAxioms
        .map { tax =>
          ( UUID.fromString(tax.bundleUUID),
            UUID.fromString(tax.rootUUID) ) -> tax
        }

    val byTBox = for {
      tuple <- byUUID
      ((bundleUUID, rootUUID), tax) = tuple
      bundleM = r.lookupBundle(bundleUUID)
      rootM = r.lookupTerminologyBoxStatement(rootUUID) match {
        case Some(e: api.Concept) => Some(e)
        case _ => None
      }
    } yield (bundleM, rootM, tax)


    val unresolvable = byTBox.filter(tuple => tuple._1.isEmpty || tuple._2.isEmpty).map(_._3)
    val resolvable = byTBox.flatMap {
      case (Some(bundleM), Some(rootM), tax) => Some(Tuple3(bundleM, rootM, tax))
      case _ => None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]](Success(r.copy(queue = r.queue.copy(rootConceptTaxonomyAxioms = unresolvable)))) {
        case (Success(ri), (bundleM, rootM, tax)) =>
          val (ej, rax) = ri.factory.createRootConceptTaxonomyAxiom(ri.context, bundleM, rootM)

          if (!ej.lookupBundleStatements(bundleM).contains(rax))
            Failure(new IllegalArgumentException(s"RootConceptTaxonomyAxiom not in extent: $rax"))
          else if (!ej.lookupTerminologyBundleStatement(UUID.fromString(tax.uuid)).contains(rax))
            Failure(new IllegalArgumentException(s"RootConceptTaxonomyAxiom: $tax vs. $rax"))
          else
            mapDisjunctions(ri.copy(context = ej), rax, tax.uuid)
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

  def mapDisjunctions
  (r: OMLTablesResolver, conceptTreeDisjunctParent: api.ConceptTreeDisjunction, conceptTreeDisjunctUUID: tables.UUID)
  : Try[OMLTablesResolver]
  = {
    val as = r.queue.anonymousConceptUnionAxioms.partition(_.disjointTaxonomyParentUUID == conceptTreeDisjunctUUID)
    val ss = r.queue.specificDisjointConceptAxioms.partition(_.disjointTaxonomyParentUUID == conceptTreeDisjunctUUID)

    val r1 = Try(r.copy(queue = r.queue.copy(
      anonymousConceptUnionAxioms = as._2,
      specificDisjointConceptAxioms = ss._2)))

    val r2 = as._1.foldLeft[Try[OMLTablesResolver]](r1) {
        case (Success(ri), tax) =>
          val (ej, rax) = ri.factory.createAnonymousConceptUnionAxiom(ri.context, conceptTreeDisjunctParent, tax.name)

          if (!ej.conceptTreeDisjunctionOfDisjointUnionOfConceptsAxiom.get(rax).contains(conceptTreeDisjunctParent))
            Failure(new IllegalArgumentException(s"AnonymousConceptUnionAxiom not in conceptTreeDisjunctionOfDisjointUnionOfConceptsAxiom: $rax"))
          else if (!ej.lookupDisjunctions(conceptTreeDisjunctParent).contains(rax))
            Failure(new IllegalArgumentException(s"AnonymousConceptUnionAxiom: not in lookupDisjunctions: $tax vs. $rax"))
          else
            Success(ri.copy(context = ej))
        case (Failure(f), _) =>
          Failure(f)
      }

    val r3 = ss._1.foldLeft[Try[OMLTablesResolver]](r2) {
      case (Success(ri), tax) =>
        ri.lookupTerminologyBoxStatement(UUID.fromString(tax.disjointLeafUUID)) match {
          case Some(leaf: api.Concept) =>
            val (ej, rax) = ri.factory.createSpecificDisjointConceptAxiom(ri.context, conceptTreeDisjunctParent, leaf)

            if (!ej.conceptTreeDisjunctionOfDisjointUnionOfConceptsAxiom.get(rax).contains(conceptTreeDisjunctParent))
              Failure(new IllegalArgumentException(s"SpecificDisjointConceptAxiom not in conceptTreeDisjunctionOfDisjointUnionOfConceptsAxiom: $rax"))
            else if (!ej.lookupDisjunctions(conceptTreeDisjunctParent).contains(rax))
              Failure(new IllegalArgumentException(s"SpecificDisjointConceptAxiom: not in lookupDisjunctions: $tax vs. $rax"))
            else
              Success(ri.copy(context = ej))
          case _ =>
            Failure(new IllegalArgumentException(s"SpecificDisjointConceptAxiom: leaf concept not found: ${tax.disjointLeafUUID}"))
        }
      case (Failure(f), _) =>
        Failure(f)
    }

    r3
  }

  def mapAnnotations
  (r: OMLTablesResolver)
  : Try[OMLTablesResolver]
  = {

    val byUUID =
      r.queue.annotations
        .map { case (tap, taes) =>
          (r.lookupAnnotationProperty(UUID.fromString(tap.uuid)) -> tap) ->
            taes.map { tae =>
              (
                r.lookupModule(UUID.fromString(tae.moduleUUID)),
                r.lookupElement(UUID.fromString(tae.subjectUUID))
              ) -> tae
            }
        }

    val unresolvable = byUUID.flatMap {
      case ((None, tap), r2taes) =>
        Some(tap -> r2taes.map(_._2))
      case ((Some(rap), tap), r2taes) =>
        val utaes = r2taes.filter(t => t._1._1.isEmpty || t._1._2.isEmpty).map(_._2)
        if (utaes.nonEmpty)
          Some(tap -> utaes)
        else
          None
    }

    val resolvable = byUUID.flatMap {
      case ((None, tap), r2taes) =>
        None
      case ((Some(rap), tap), r2taes) =>
        val rtaes = r2taes.flatMap {
          case ((Some(m), Some(s)), tae) =>
            Some(Tuple3(m, s, tae))
          case _ =>
            None
        }
        if (rtaes.nonEmpty)
          Some(Tuple3(rap, tap, rtaes))
        else
          None
    }

    val s =
      resolvable.foldLeft[Try[OMLTablesResolver]]{
        Success(r.copy(queue = r.queue.copy(annotations = unresolvable)))
      } {
        case (Success(ri), (rap, _, rtaes)) =>
          val next
          : Try[OMLTablesResolver]
          = rtaes.foldLeft[Try[OMLTablesResolver]](Success(ri)) {
            case (Success(rj), (am, as, tae)) =>
              val (ej, ra) = rj.factory.createAnnotation(
                rj.context,
                am, as, rap, tae.value)
              if (!ej.lookupAnnotations(am).exists { a => a.property == rap && a.subject == as && a.value == tae.value })
                Failure(new IllegalArgumentException(s"Annotation not in extent: $ra"))
              else
                Success(rj.copy(context = ej))
            case (Failure(f), _) =>
              Failure(f)
          }
          next
        case (Failure(f), _) =>
          Failure(f)
      }
    s
  }

}
