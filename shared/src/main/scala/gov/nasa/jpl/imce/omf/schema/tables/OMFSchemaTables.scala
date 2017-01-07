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


package gov.nasa.jpl.imce.omf.schema.tables

import java.io.{File,InputStream}
import org.apache.commons.compress.archivers.zip.{ZipArchiveEntry, ZipFile}

import scala.collection.immutable.Seq
import scala.collection.JavaConversions._
import scala.{Boolean,Unit}
import scala.util.control.Exception._
import scala.util.{Failure,Success,Try}

case class OMFSchemaTables private[tables]
(
  annotationProperties : Seq[AnnotationProperty] = Seq.empty,
  terminologyGraphs : Seq[TerminologyGraph] = Seq.empty,
  bundles : Seq[Bundle] = Seq.empty,
  conceptDesignationTerminologyAxioms : Seq[ConceptDesignationTerminologyAxiom] = Seq.empty,
  terminologyExtensionAxioms : Seq[TerminologyExtensionAxiom] = Seq.empty,
  terminologyNestingAxioms : Seq[TerminologyNestingAxiom] = Seq.empty,
  aspects : Seq[Aspect] = Seq.empty,
  concepts : Seq[Concept] = Seq.empty,
  reifiedRelationships : Seq[ReifiedRelationship] = Seq.empty,
  unreifiedRelationships : Seq[UnreifiedRelationship] = Seq.empty,
  scalars : Seq[Scalar] = Seq.empty,
  structures : Seq[Structure] = Seq.empty,
  binaryScalarRestrictions : Seq[BinaryScalarRestriction] = Seq.empty,
  iriScalarRestrictions : Seq[IRIScalarRestriction] = Seq.empty,
  numericScalarRestrictions : Seq[NumericScalarRestriction] = Seq.empty,
  plainLiteralScalarRestrictions : Seq[PlainLiteralScalarRestriction] = Seq.empty,
  scalarOneOfRestrictions : Seq[ScalarOneOfRestriction] = Seq.empty,
  stringScalarRestrictions : Seq[StringScalarRestriction] = Seq.empty,
  timeScalarRestrictions : Seq[TimeScalarRestriction] = Seq.empty,
  entityScalarDataProperties : Seq[EntityScalarDataProperty] = Seq.empty,
  entityStructuredDataProperties : Seq[EntityStructuredDataProperty] = Seq.empty,
  scalarDataProperties : Seq[ScalarDataProperty] = Seq.empty,
  structuredDataProperties : Seq[StructuredDataProperty] = Seq.empty,
  aspectSpecializationAxioms : Seq[AspectSpecializationAxiom] = Seq.empty,
  conceptSpecializationAxioms : Seq[ConceptSpecializationAxiom] = Seq.empty,
  reifiedRelationshipSpecializationAxioms : Seq[ReifiedRelationshipSpecializationAxiom] = Seq.empty,
  entityExistentialRestrictionAxioms : Seq[EntityExistentialRestrictionAxiom] = Seq.empty,
  entityUniversalRestrictionAxioms : Seq[EntityUniversalRestrictionAxiom] = Seq.empty,
  entityScalarDataPropertyExistentialRestrictionAxioms : Seq[EntityScalarDataPropertyExistentialRestrictionAxiom] = Seq.empty,
  entityScalarDataPropertyParticularRestrictionAxioms : Seq[EntityScalarDataPropertyParticularRestrictionAxiom] = Seq.empty,
  entityScalarDataPropertyUniversalRestrictionAxioms : Seq[EntityScalarDataPropertyUniversalRestrictionAxiom] = Seq.empty,
  scalarOneOfLiteralAxioms : Seq[ScalarOneOfLiteralAxiom] = Seq.empty,
  bundledTerminologyAxioms : Seq[BundledTerminologyAxiom] = Seq.empty,
  anonymousConceptTaxonomyAxioms : Seq[AnonymousConceptTaxonomyAxiom] = Seq.empty,
  rootConceptTaxonomyAxioms : Seq[RootConceptTaxonomyAxiom] = Seq.empty,
  specificDisjointConceptAxioms : Seq[SpecificDisjointConceptAxiom] = Seq.empty,
  annotationPairs : Seq[AnnotationPair] = Seq.empty
) 
{
  def readAnnotationProperties(is: InputStream)
  : OMFSchemaTables
  = copy(annotationProperties = readJSonTable(is, AnnotationPropertyHelper.fromJSON))
  def readTerminologyGraphs(is: InputStream)
  : OMFSchemaTables
  = copy(terminologyGraphs = readJSonTable(is, TerminologyGraphHelper.fromJSON))
  def readBundles(is: InputStream)
  : OMFSchemaTables
  = copy(bundles = readJSonTable(is, BundleHelper.fromJSON))
  def readConceptDesignationTerminologyAxioms(is: InputStream)
  : OMFSchemaTables
  = copy(conceptDesignationTerminologyAxioms = readJSonTable(is, ConceptDesignationTerminologyAxiomHelper.fromJSON))
  def readTerminologyExtensionAxioms(is: InputStream)
  : OMFSchemaTables
  = copy(terminologyExtensionAxioms = readJSonTable(is, TerminologyExtensionAxiomHelper.fromJSON))
  def readTerminologyNestingAxioms(is: InputStream)
  : OMFSchemaTables
  = copy(terminologyNestingAxioms = readJSonTable(is, TerminologyNestingAxiomHelper.fromJSON))
  def readAspects(is: InputStream)
  : OMFSchemaTables
  = copy(aspects = readJSonTable(is, AspectHelper.fromJSON))
  def readConcepts(is: InputStream)
  : OMFSchemaTables
  = copy(concepts = readJSonTable(is, ConceptHelper.fromJSON))
  def readReifiedRelationships(is: InputStream)
  : OMFSchemaTables
  = copy(reifiedRelationships = readJSonTable(is, ReifiedRelationshipHelper.fromJSON))
  def readUnreifiedRelationships(is: InputStream)
  : OMFSchemaTables
  = copy(unreifiedRelationships = readJSonTable(is, UnreifiedRelationshipHelper.fromJSON))
  def readScalars(is: InputStream)
  : OMFSchemaTables
  = copy(scalars = readJSonTable(is, ScalarHelper.fromJSON))
  def readStructures(is: InputStream)
  : OMFSchemaTables
  = copy(structures = readJSonTable(is, StructureHelper.fromJSON))
  def readBinaryScalarRestrictions(is: InputStream)
  : OMFSchemaTables
  = copy(binaryScalarRestrictions = readJSonTable(is, BinaryScalarRestrictionHelper.fromJSON))
  def readIRIScalarRestrictions(is: InputStream)
  : OMFSchemaTables
  = copy(iriScalarRestrictions = readJSonTable(is, IRIScalarRestrictionHelper.fromJSON))
  def readNumericScalarRestrictions(is: InputStream)
  : OMFSchemaTables
  = copy(numericScalarRestrictions = readJSonTable(is, NumericScalarRestrictionHelper.fromJSON))
  def readPlainLiteralScalarRestrictions(is: InputStream)
  : OMFSchemaTables
  = copy(plainLiteralScalarRestrictions = readJSonTable(is, PlainLiteralScalarRestrictionHelper.fromJSON))
  def readScalarOneOfRestrictions(is: InputStream)
  : OMFSchemaTables
  = copy(scalarOneOfRestrictions = readJSonTable(is, ScalarOneOfRestrictionHelper.fromJSON))
  def readStringScalarRestrictions(is: InputStream)
  : OMFSchemaTables
  = copy(stringScalarRestrictions = readJSonTable(is, StringScalarRestrictionHelper.fromJSON))
  def readTimeScalarRestrictions(is: InputStream)
  : OMFSchemaTables
  = copy(timeScalarRestrictions = readJSonTable(is, TimeScalarRestrictionHelper.fromJSON))
  def readEntityScalarDataProperties(is: InputStream)
  : OMFSchemaTables
  = copy(entityScalarDataProperties = readJSonTable(is, EntityScalarDataPropertyHelper.fromJSON))
  def readEntityStructuredDataProperties(is: InputStream)
  : OMFSchemaTables
  = copy(entityStructuredDataProperties = readJSonTable(is, EntityStructuredDataPropertyHelper.fromJSON))
  def readScalarDataProperties(is: InputStream)
  : OMFSchemaTables
  = copy(scalarDataProperties = readJSonTable(is, ScalarDataPropertyHelper.fromJSON))
  def readStructuredDataProperties(is: InputStream)
  : OMFSchemaTables
  = copy(structuredDataProperties = readJSonTable(is, StructuredDataPropertyHelper.fromJSON))
  def readAspectSpecializationAxioms(is: InputStream)
  : OMFSchemaTables
  = copy(aspectSpecializationAxioms = readJSonTable(is, AspectSpecializationAxiomHelper.fromJSON))
  def readConceptSpecializationAxioms(is: InputStream)
  : OMFSchemaTables
  = copy(conceptSpecializationAxioms = readJSonTable(is, ConceptSpecializationAxiomHelper.fromJSON))
  def readReifiedRelationshipSpecializationAxioms(is: InputStream)
  : OMFSchemaTables
  = copy(reifiedRelationshipSpecializationAxioms = readJSonTable(is, ReifiedRelationshipSpecializationAxiomHelper.fromJSON))
  def readEntityExistentialRestrictionAxioms(is: InputStream)
  : OMFSchemaTables
  = copy(entityExistentialRestrictionAxioms = readJSonTable(is, EntityExistentialRestrictionAxiomHelper.fromJSON))
  def readEntityUniversalRestrictionAxioms(is: InputStream)
  : OMFSchemaTables
  = copy(entityUniversalRestrictionAxioms = readJSonTable(is, EntityUniversalRestrictionAxiomHelper.fromJSON))
  def readEntityScalarDataPropertyExistentialRestrictionAxioms(is: InputStream)
  : OMFSchemaTables
  = copy(entityScalarDataPropertyExistentialRestrictionAxioms = readJSonTable(is, EntityScalarDataPropertyExistentialRestrictionAxiomHelper.fromJSON))
  def readEntityScalarDataPropertyParticularRestrictionAxioms(is: InputStream)
  : OMFSchemaTables
  = copy(entityScalarDataPropertyParticularRestrictionAxioms = readJSonTable(is, EntityScalarDataPropertyParticularRestrictionAxiomHelper.fromJSON))
  def readEntityScalarDataPropertyUniversalRestrictionAxioms(is: InputStream)
  : OMFSchemaTables
  = copy(entityScalarDataPropertyUniversalRestrictionAxioms = readJSonTable(is, EntityScalarDataPropertyUniversalRestrictionAxiomHelper.fromJSON))
  def readScalarOneOfLiteralAxioms(is: InputStream)
  : OMFSchemaTables
  = copy(scalarOneOfLiteralAxioms = readJSonTable(is, ScalarOneOfLiteralAxiomHelper.fromJSON))
  def readBundledTerminologyAxioms(is: InputStream)
  : OMFSchemaTables
  = copy(bundledTerminologyAxioms = readJSonTable(is, BundledTerminologyAxiomHelper.fromJSON))
  def readAnonymousConceptTaxonomyAxioms(is: InputStream)
  : OMFSchemaTables
  = copy(anonymousConceptTaxonomyAxioms = readJSonTable(is, AnonymousConceptTaxonomyAxiomHelper.fromJSON))
  def readRootConceptTaxonomyAxioms(is: InputStream)
  : OMFSchemaTables
  = copy(rootConceptTaxonomyAxioms = readJSonTable(is, RootConceptTaxonomyAxiomHelper.fromJSON))
  def readSpecificDisjointConceptAxioms(is: InputStream)
  : OMFSchemaTables
  = copy(specificDisjointConceptAxioms = readJSonTable(is, SpecificDisjointConceptAxiomHelper.fromJSON))
  def readAnnotationPairs(is: InputStream)
  : OMFSchemaTables
  = copy(annotationPairs = readJSonTable(is, AnnotationPairHelper.fromJSON))

  def isEmpty: Boolean
  = annotationProperties.isEmpty &&
    terminologyGraphs.isEmpty &&
    bundles.isEmpty &&
    conceptDesignationTerminologyAxioms.isEmpty &&
    terminologyExtensionAxioms.isEmpty &&
    terminologyNestingAxioms.isEmpty &&
    aspects.isEmpty &&
    concepts.isEmpty &&
    reifiedRelationships.isEmpty &&
    unreifiedRelationships.isEmpty &&
    scalars.isEmpty &&
    structures.isEmpty &&
    binaryScalarRestrictions.isEmpty &&
    iriScalarRestrictions.isEmpty &&
    numericScalarRestrictions.isEmpty &&
    plainLiteralScalarRestrictions.isEmpty &&
    scalarOneOfRestrictions.isEmpty &&
    stringScalarRestrictions.isEmpty &&
    timeScalarRestrictions.isEmpty &&
    entityScalarDataProperties.isEmpty &&
    entityStructuredDataProperties.isEmpty &&
    scalarDataProperties.isEmpty &&
    structuredDataProperties.isEmpty &&
    aspectSpecializationAxioms.isEmpty &&
    conceptSpecializationAxioms.isEmpty &&
    reifiedRelationshipSpecializationAxioms.isEmpty &&
    entityExistentialRestrictionAxioms.isEmpty &&
    entityUniversalRestrictionAxioms.isEmpty &&
    entityScalarDataPropertyExistentialRestrictionAxioms.isEmpty &&
    entityScalarDataPropertyParticularRestrictionAxioms.isEmpty &&
    entityScalarDataPropertyUniversalRestrictionAxioms.isEmpty &&
    scalarOneOfLiteralAxioms.isEmpty &&
    bundledTerminologyAxioms.isEmpty &&
    anonymousConceptTaxonomyAxioms.isEmpty &&
    rootConceptTaxonomyAxioms.isEmpty &&
    specificDisjointConceptAxioms.isEmpty &&
    annotationPairs.isEmpty
}

object OMFSchemaTables {
	
  def createEmptyOMFSchemaTables()
  : OMFSchemaTables
  = new OMFSchemaTables()
  
  def loadOMFSchemaTables(omfSchemaJsonZipFile: File)
  : Try[OMFSchemaTables]
  = nonFatalCatch[Try[OMFSchemaTables]]
    .withApply {
      (cause: java.lang.Throwable) =>
        cause.fillInStackTrace()
        Failure(cause)
    }
    .apply {
      val zipFile = new ZipFile(omfSchemaJsonZipFile)
      val omfTables =
        zipFile
        .getEntries
        .toIterable
        .par
         .aggregate(OMFSchemaTables())(seqop = readZipArchive(zipFile), combop = mergeTables)
      zipFile.close()
      Success(omfTables)
    }

  private[tables] def mergeTables
  (t1: OMFSchemaTables, t2: OMFSchemaTables)
  : OMFSchemaTables
  = OMFSchemaTables(
      annotationProperties = t1.annotationProperties ++ t2.annotationProperties,
      terminologyGraphs = t1.terminologyGraphs ++ t2.terminologyGraphs,
      bundles = t1.bundles ++ t2.bundles,
      conceptDesignationTerminologyAxioms = t1.conceptDesignationTerminologyAxioms ++ t2.conceptDesignationTerminologyAxioms,
      terminologyExtensionAxioms = t1.terminologyExtensionAxioms ++ t2.terminologyExtensionAxioms,
      terminologyNestingAxioms = t1.terminologyNestingAxioms ++ t2.terminologyNestingAxioms,
      aspects = t1.aspects ++ t2.aspects,
      concepts = t1.concepts ++ t2.concepts,
      reifiedRelationships = t1.reifiedRelationships ++ t2.reifiedRelationships,
      unreifiedRelationships = t1.unreifiedRelationships ++ t2.unreifiedRelationships,
      scalars = t1.scalars ++ t2.scalars,
      structures = t1.structures ++ t2.structures,
      binaryScalarRestrictions = t1.binaryScalarRestrictions ++ t2.binaryScalarRestrictions,
      iriScalarRestrictions = t1.iriScalarRestrictions ++ t2.iriScalarRestrictions,
      numericScalarRestrictions = t1.numericScalarRestrictions ++ t2.numericScalarRestrictions,
      plainLiteralScalarRestrictions = t1.plainLiteralScalarRestrictions ++ t2.plainLiteralScalarRestrictions,
      scalarOneOfRestrictions = t1.scalarOneOfRestrictions ++ t2.scalarOneOfRestrictions,
      stringScalarRestrictions = t1.stringScalarRestrictions ++ t2.stringScalarRestrictions,
      timeScalarRestrictions = t1.timeScalarRestrictions ++ t2.timeScalarRestrictions,
      entityScalarDataProperties = t1.entityScalarDataProperties ++ t2.entityScalarDataProperties,
      entityStructuredDataProperties = t1.entityStructuredDataProperties ++ t2.entityStructuredDataProperties,
      scalarDataProperties = t1.scalarDataProperties ++ t2.scalarDataProperties,
      structuredDataProperties = t1.structuredDataProperties ++ t2.structuredDataProperties,
      aspectSpecializationAxioms = t1.aspectSpecializationAxioms ++ t2.aspectSpecializationAxioms,
      conceptSpecializationAxioms = t1.conceptSpecializationAxioms ++ t2.conceptSpecializationAxioms,
      reifiedRelationshipSpecializationAxioms = t1.reifiedRelationshipSpecializationAxioms ++ t2.reifiedRelationshipSpecializationAxioms,
      entityExistentialRestrictionAxioms = t1.entityExistentialRestrictionAxioms ++ t2.entityExistentialRestrictionAxioms,
      entityUniversalRestrictionAxioms = t1.entityUniversalRestrictionAxioms ++ t2.entityUniversalRestrictionAxioms,
      entityScalarDataPropertyExistentialRestrictionAxioms = t1.entityScalarDataPropertyExistentialRestrictionAxioms ++ t2.entityScalarDataPropertyExistentialRestrictionAxioms,
      entityScalarDataPropertyParticularRestrictionAxioms = t1.entityScalarDataPropertyParticularRestrictionAxioms ++ t2.entityScalarDataPropertyParticularRestrictionAxioms,
      entityScalarDataPropertyUniversalRestrictionAxioms = t1.entityScalarDataPropertyUniversalRestrictionAxioms ++ t2.entityScalarDataPropertyUniversalRestrictionAxioms,
      scalarOneOfLiteralAxioms = t1.scalarOneOfLiteralAxioms ++ t2.scalarOneOfLiteralAxioms,
      bundledTerminologyAxioms = t1.bundledTerminologyAxioms ++ t2.bundledTerminologyAxioms,
      anonymousConceptTaxonomyAxioms = t1.anonymousConceptTaxonomyAxioms ++ t2.anonymousConceptTaxonomyAxioms,
      rootConceptTaxonomyAxioms = t1.rootConceptTaxonomyAxioms ++ t2.rootConceptTaxonomyAxioms,
      specificDisjointConceptAxioms = t1.specificDisjointConceptAxioms ++ t2.specificDisjointConceptAxioms,
      annotationPairs = t1.annotationPairs ++ t2.annotationPairs) 

  private[tables] def readZipArchive
  (zipFile: ZipFile)
  (tables: OMFSchemaTables, ze: ZipArchiveEntry)
  : OMFSchemaTables
  = {
  	val is = zipFile.getInputStream(ze)
  	ze.getName match {
  	  case AnnotationPropertyHelper.TABLE_JSON_FILENAME =>
  	    tables.readAnnotationProperties(is)
  	  case TerminologyGraphHelper.TABLE_JSON_FILENAME =>
  	    tables.readTerminologyGraphs(is)
  	  case BundleHelper.TABLE_JSON_FILENAME =>
  	    tables.readBundles(is)
  	  case ConceptDesignationTerminologyAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readConceptDesignationTerminologyAxioms(is)
  	  case TerminologyExtensionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readTerminologyExtensionAxioms(is)
  	  case TerminologyNestingAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readTerminologyNestingAxioms(is)
  	  case AspectHelper.TABLE_JSON_FILENAME =>
  	    tables.readAspects(is)
  	  case ConceptHelper.TABLE_JSON_FILENAME =>
  	    tables.readConcepts(is)
  	  case ReifiedRelationshipHelper.TABLE_JSON_FILENAME =>
  	    tables.readReifiedRelationships(is)
  	  case UnreifiedRelationshipHelper.TABLE_JSON_FILENAME =>
  	    tables.readUnreifiedRelationships(is)
  	  case ScalarHelper.TABLE_JSON_FILENAME =>
  	    tables.readScalars(is)
  	  case StructureHelper.TABLE_JSON_FILENAME =>
  	    tables.readStructures(is)
  	  case BinaryScalarRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readBinaryScalarRestrictions(is)
  	  case IRIScalarRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readIRIScalarRestrictions(is)
  	  case NumericScalarRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readNumericScalarRestrictions(is)
  	  case PlainLiteralScalarRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readPlainLiteralScalarRestrictions(is)
  	  case ScalarOneOfRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readScalarOneOfRestrictions(is)
  	  case StringScalarRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readStringScalarRestrictions(is)
  	  case TimeScalarRestrictionHelper.TABLE_JSON_FILENAME =>
  	    tables.readTimeScalarRestrictions(is)
  	  case EntityScalarDataPropertyHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityScalarDataProperties(is)
  	  case EntityStructuredDataPropertyHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityStructuredDataProperties(is)
  	  case ScalarDataPropertyHelper.TABLE_JSON_FILENAME =>
  	    tables.readScalarDataProperties(is)
  	  case StructuredDataPropertyHelper.TABLE_JSON_FILENAME =>
  	    tables.readStructuredDataProperties(is)
  	  case AspectSpecializationAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readAspectSpecializationAxioms(is)
  	  case ConceptSpecializationAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readConceptSpecializationAxioms(is)
  	  case ReifiedRelationshipSpecializationAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readReifiedRelationshipSpecializationAxioms(is)
  	  case EntityExistentialRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityExistentialRestrictionAxioms(is)
  	  case EntityUniversalRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityUniversalRestrictionAxioms(is)
  	  case EntityScalarDataPropertyExistentialRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityScalarDataPropertyExistentialRestrictionAxioms(is)
  	  case EntityScalarDataPropertyParticularRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityScalarDataPropertyParticularRestrictionAxioms(is)
  	  case EntityScalarDataPropertyUniversalRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityScalarDataPropertyUniversalRestrictionAxioms(is)
  	  case ScalarOneOfLiteralAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readScalarOneOfLiteralAxioms(is)
  	  case BundledTerminologyAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readBundledTerminologyAxioms(is)
  	  case AnonymousConceptTaxonomyAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readAnonymousConceptTaxonomyAxioms(is)
  	  case RootConceptTaxonomyAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readRootConceptTaxonomyAxioms(is)
  	  case SpecificDisjointConceptAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readSpecificDisjointConceptAxioms(is)
  	  case AnnotationPairHelper.TABLE_JSON_FILENAME =>
  	    tables.readAnnotationPairs(is)
    }
  }
  
  def saveOMFSchemaTables
  (tables: OMFSchemaTables,
   omfSchemaJsonZipFile: File)
  : Try[Unit]
  = nonFatalCatch[Try[Unit]]
    .withApply {
      (cause: java.lang.Throwable) =>
        cause.fillInStackTrace()
        Failure(cause)
    }
    .apply {
  	  // @see http://www.oracle.com/technetwork/articles/java/compress-1565076.html
  	  val fos = new java.io.FileOutputStream(omfSchemaJsonZipFile)
  	  val bos = new java.io.BufferedOutputStream(fos, 100000)
  	  val cos = new java.util.zip.CheckedOutputStream(bos, new java.util.zip.Adler32())
  	  val zos = new java.util.zip.ZipOutputStream(new java.io.BufferedOutputStream(cos))
  
  	  zos.setMethod(java.util.zip.ZipOutputStream.DEFLATED)
  
      zos.putNextEntry(new java.util.zip.ZipEntry(AnnotationPropertyHelper.TABLE_JSON_FILENAME))
      tables.annotationProperties.foreach { t =>
         val line = AnnotationPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(TerminologyGraphHelper.TABLE_JSON_FILENAME))
      tables.terminologyGraphs.foreach { t =>
         val line = TerminologyGraphHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(BundleHelper.TABLE_JSON_FILENAME))
      tables.bundles.foreach { t =>
         val line = BundleHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ConceptDesignationTerminologyAxiomHelper.TABLE_JSON_FILENAME))
      tables.conceptDesignationTerminologyAxioms.foreach { t =>
         val line = ConceptDesignationTerminologyAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(TerminologyExtensionAxiomHelper.TABLE_JSON_FILENAME))
      tables.terminologyExtensionAxioms.foreach { t =>
         val line = TerminologyExtensionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(TerminologyNestingAxiomHelper.TABLE_JSON_FILENAME))
      tables.terminologyNestingAxioms.foreach { t =>
         val line = TerminologyNestingAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(AspectHelper.TABLE_JSON_FILENAME))
      tables.aspects.foreach { t =>
         val line = AspectHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ConceptHelper.TABLE_JSON_FILENAME))
      tables.concepts.foreach { t =>
         val line = ConceptHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ReifiedRelationshipHelper.TABLE_JSON_FILENAME))
      tables.reifiedRelationships.foreach { t =>
         val line = ReifiedRelationshipHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(UnreifiedRelationshipHelper.TABLE_JSON_FILENAME))
      tables.unreifiedRelationships.foreach { t =>
         val line = UnreifiedRelationshipHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ScalarHelper.TABLE_JSON_FILENAME))
      tables.scalars.foreach { t =>
         val line = ScalarHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(StructureHelper.TABLE_JSON_FILENAME))
      tables.structures.foreach { t =>
         val line = StructureHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(BinaryScalarRestrictionHelper.TABLE_JSON_FILENAME))
      tables.binaryScalarRestrictions.foreach { t =>
         val line = BinaryScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(IRIScalarRestrictionHelper.TABLE_JSON_FILENAME))
      tables.iriScalarRestrictions.foreach { t =>
         val line = IRIScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(NumericScalarRestrictionHelper.TABLE_JSON_FILENAME))
      tables.numericScalarRestrictions.foreach { t =>
         val line = NumericScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(PlainLiteralScalarRestrictionHelper.TABLE_JSON_FILENAME))
      tables.plainLiteralScalarRestrictions.foreach { t =>
         val line = PlainLiteralScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ScalarOneOfRestrictionHelper.TABLE_JSON_FILENAME))
      tables.scalarOneOfRestrictions.foreach { t =>
         val line = ScalarOneOfRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(StringScalarRestrictionHelper.TABLE_JSON_FILENAME))
      tables.stringScalarRestrictions.foreach { t =>
         val line = StringScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(TimeScalarRestrictionHelper.TABLE_JSON_FILENAME))
      tables.timeScalarRestrictions.foreach { t =>
         val line = TimeScalarRestrictionHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityScalarDataPropertyHelper.TABLE_JSON_FILENAME))
      tables.entityScalarDataProperties.foreach { t =>
         val line = EntityScalarDataPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityStructuredDataPropertyHelper.TABLE_JSON_FILENAME))
      tables.entityStructuredDataProperties.foreach { t =>
         val line = EntityStructuredDataPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ScalarDataPropertyHelper.TABLE_JSON_FILENAME))
      tables.scalarDataProperties.foreach { t =>
         val line = ScalarDataPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(StructuredDataPropertyHelper.TABLE_JSON_FILENAME))
      tables.structuredDataProperties.foreach { t =>
         val line = StructuredDataPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(AspectSpecializationAxiomHelper.TABLE_JSON_FILENAME))
      tables.aspectSpecializationAxioms.foreach { t =>
         val line = AspectSpecializationAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ConceptSpecializationAxiomHelper.TABLE_JSON_FILENAME))
      tables.conceptSpecializationAxioms.foreach { t =>
         val line = ConceptSpecializationAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ReifiedRelationshipSpecializationAxiomHelper.TABLE_JSON_FILENAME))
      tables.reifiedRelationshipSpecializationAxioms.foreach { t =>
         val line = ReifiedRelationshipSpecializationAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityExistentialRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.entityExistentialRestrictionAxioms.foreach { t =>
         val line = EntityExistentialRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityUniversalRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.entityUniversalRestrictionAxioms.foreach { t =>
         val line = EntityUniversalRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityScalarDataPropertyExistentialRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.entityScalarDataPropertyExistentialRestrictionAxioms.foreach { t =>
         val line = EntityScalarDataPropertyExistentialRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityScalarDataPropertyParticularRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.entityScalarDataPropertyParticularRestrictionAxioms.foreach { t =>
         val line = EntityScalarDataPropertyParticularRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityScalarDataPropertyUniversalRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.entityScalarDataPropertyUniversalRestrictionAxioms.foreach { t =>
         val line = EntityScalarDataPropertyUniversalRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ScalarOneOfLiteralAxiomHelper.TABLE_JSON_FILENAME))
      tables.scalarOneOfLiteralAxioms.foreach { t =>
         val line = ScalarOneOfLiteralAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(BundledTerminologyAxiomHelper.TABLE_JSON_FILENAME))
      tables.bundledTerminologyAxioms.foreach { t =>
         val line = BundledTerminologyAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(AnonymousConceptTaxonomyAxiomHelper.TABLE_JSON_FILENAME))
      tables.anonymousConceptTaxonomyAxioms.foreach { t =>
         val line = AnonymousConceptTaxonomyAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(RootConceptTaxonomyAxiomHelper.TABLE_JSON_FILENAME))
      tables.rootConceptTaxonomyAxioms.foreach { t =>
         val line = RootConceptTaxonomyAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(SpecificDisjointConceptAxiomHelper.TABLE_JSON_FILENAME))
      tables.specificDisjointConceptAxioms.foreach { t =>
         val line = SpecificDisjointConceptAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(AnnotationPairHelper.TABLE_JSON_FILENAME))
      tables.annotationPairs.foreach { t =>
         val line = AnnotationPairHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
  
      zos.close()
  	  Success(())
  	}

}
