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
import scala.Unit
import scala.util.control.Exception._
import scala.util.{Failure,Success,Try}

case class OMFTables private[tables]
(
  aspects : Seq[Aspect] = Seq.empty,
  aspectSpecializationAxioms : Seq[AspectSpecializationAxiom] = Seq.empty,
  binaryScalarRestrictionAxioms : Seq[BinaryScalarRestrictionAxiom] = Seq.empty,
  bundles : Seq[Bundle] = Seq.empty,
  bundledTerminologyAxioms : Seq[BundledTerminologyAxiom] = Seq.empty,
  concepts : Seq[Concept] = Seq.empty,
  conceptDesignationTerminologyGraphAxioms : Seq[ConceptDesignationTerminologyGraphAxiom] = Seq.empty,
  conceptSpecializationAxioms : Seq[ConceptSpecializationAxiom] = Seq.empty,
  entityExistentialRestrictionAxioms : Seq[EntityExistentialRestrictionAxiom] = Seq.empty,
  entityScalarDataPropertys : Seq[EntityScalarDataProperty] = Seq.empty,
  entityScalarDataPropertyExistentialRestrictionAxioms : Seq[EntityScalarDataPropertyExistentialRestrictionAxiom] = Seq.empty,
  entityScalarDataPropertyParticularRestrictionAxioms : Seq[EntityScalarDataPropertyParticularRestrictionAxiom] = Seq.empty,
  entityScalarDataPropertyUniversalRestrictionAxioms : Seq[EntityScalarDataPropertyUniversalRestrictionAxiom] = Seq.empty,
  entityStructuredDataPropertys : Seq[EntityStructuredDataProperty] = Seq.empty,
  entityUniversalRestrictionAxioms : Seq[EntityUniversalRestrictionAxiom] = Seq.empty,
  iRIScalarRestrictionAxioms : Seq[IRIScalarRestrictionAxiom] = Seq.empty,
  numericScalarRestrictionAxioms : Seq[NumericScalarRestrictionAxiom] = Seq.empty,
  plainLiteralScalarRestrictionAxioms : Seq[PlainLiteralScalarRestrictionAxiom] = Seq.empty,
  reifiedRelationships : Seq[ReifiedRelationship] = Seq.empty,
  reifiedRelationshipSpecializationAxioms : Seq[ReifiedRelationshipSpecializationAxiom] = Seq.empty,
  scalars : Seq[Scalar] = Seq.empty,
  scalarDataPropertys : Seq[ScalarDataProperty] = Seq.empty,
  scalarOneOfLiterals : Seq[ScalarOneOfLiteral] = Seq.empty,
  scalarOneOfRestrictionAxioms : Seq[ScalarOneOfRestrictionAxiom] = Seq.empty,
  stringScalarRestrictionAxioms : Seq[StringScalarRestrictionAxiom] = Seq.empty,
  structures : Seq[Structure] = Seq.empty,
  structuredDataPropertys : Seq[StructuredDataProperty] = Seq.empty,
  terminologyExtensionAxioms : Seq[TerminologyExtensionAxiom] = Seq.empty,
  terminologyGraphs : Seq[TerminologyGraph] = Seq.empty,
  terminologyNestingAxioms : Seq[TerminologyNestingAxiom] = Seq.empty,
  timeScalarRestrictionAxioms : Seq[TimeScalarRestrictionAxiom] = Seq.empty,
  unreifiedRelationships : Seq[UnreifiedRelationship] = Seq.empty
) 
{
def readAspects(is: InputStream)
: OMFTables
= copy(aspects = readJSonTable(is, AspectHelper.fromJSON))

def readAspectSpecializationAxioms(is: InputStream)
: OMFTables
= copy(aspectSpecializationAxioms = readJSonTable(is, AspectSpecializationAxiomHelper.fromJSON))

def readBinaryScalarRestrictionAxioms(is: InputStream)
: OMFTables
= copy(binaryScalarRestrictionAxioms = readJSonTable(is, BinaryScalarRestrictionAxiomHelper.fromJSON))

def readBundles(is: InputStream)
: OMFTables
= copy(bundles = readJSonTable(is, BundleHelper.fromJSON))

def readBundledTerminologyAxioms(is: InputStream)
: OMFTables
= copy(bundledTerminologyAxioms = readJSonTable(is, BundledTerminologyAxiomHelper.fromJSON))

def readConcepts(is: InputStream)
: OMFTables
= copy(concepts = readJSonTable(is, ConceptHelper.fromJSON))

def readConceptDesignationTerminologyGraphAxioms(is: InputStream)
: OMFTables
= copy(conceptDesignationTerminologyGraphAxioms = readJSonTable(is, ConceptDesignationTerminologyGraphAxiomHelper.fromJSON))

def readConceptSpecializationAxioms(is: InputStream)
: OMFTables
= copy(conceptSpecializationAxioms = readJSonTable(is, ConceptSpecializationAxiomHelper.fromJSON))

def readEntityExistentialRestrictionAxioms(is: InputStream)
: OMFTables
= copy(entityExistentialRestrictionAxioms = readJSonTable(is, EntityExistentialRestrictionAxiomHelper.fromJSON))

def readEntityScalarDataPropertys(is: InputStream)
: OMFTables
= copy(entityScalarDataPropertys = readJSonTable(is, EntityScalarDataPropertyHelper.fromJSON))

def readEntityScalarDataPropertyExistentialRestrictionAxioms(is: InputStream)
: OMFTables
= copy(entityScalarDataPropertyExistentialRestrictionAxioms = readJSonTable(is, EntityScalarDataPropertyExistentialRestrictionAxiomHelper.fromJSON))

def readEntityScalarDataPropertyParticularRestrictionAxioms(is: InputStream)
: OMFTables
= copy(entityScalarDataPropertyParticularRestrictionAxioms = readJSonTable(is, EntityScalarDataPropertyParticularRestrictionAxiomHelper.fromJSON))

def readEntityScalarDataPropertyUniversalRestrictionAxioms(is: InputStream)
: OMFTables
= copy(entityScalarDataPropertyUniversalRestrictionAxioms = readJSonTable(is, EntityScalarDataPropertyUniversalRestrictionAxiomHelper.fromJSON))

def readEntityStructuredDataPropertys(is: InputStream)
: OMFTables
= copy(entityStructuredDataPropertys = readJSonTable(is, EntityStructuredDataPropertyHelper.fromJSON))

def readEntityUniversalRestrictionAxioms(is: InputStream)
: OMFTables
= copy(entityUniversalRestrictionAxioms = readJSonTable(is, EntityUniversalRestrictionAxiomHelper.fromJSON))

def readIRIScalarRestrictionAxioms(is: InputStream)
: OMFTables
= copy(iRIScalarRestrictionAxioms = readJSonTable(is, IRIScalarRestrictionAxiomHelper.fromJSON))

def readNumericScalarRestrictionAxioms(is: InputStream)
: OMFTables
= copy(numericScalarRestrictionAxioms = readJSonTable(is, NumericScalarRestrictionAxiomHelper.fromJSON))

def readPlainLiteralScalarRestrictionAxioms(is: InputStream)
: OMFTables
= copy(plainLiteralScalarRestrictionAxioms = readJSonTable(is, PlainLiteralScalarRestrictionAxiomHelper.fromJSON))

def readReifiedRelationships(is: InputStream)
: OMFTables
= copy(reifiedRelationships = readJSonTable(is, ReifiedRelationshipHelper.fromJSON))

def readReifiedRelationshipSpecializationAxioms(is: InputStream)
: OMFTables
= copy(reifiedRelationshipSpecializationAxioms = readJSonTable(is, ReifiedRelationshipSpecializationAxiomHelper.fromJSON))

def readScalars(is: InputStream)
: OMFTables
= copy(scalars = readJSonTable(is, ScalarHelper.fromJSON))

def readScalarDataPropertys(is: InputStream)
: OMFTables
= copy(scalarDataPropertys = readJSonTable(is, ScalarDataPropertyHelper.fromJSON))

def readScalarOneOfLiterals(is: InputStream)
: OMFTables
= copy(scalarOneOfLiterals = readJSonTable(is, ScalarOneOfLiteralHelper.fromJSON))

def readScalarOneOfRestrictionAxioms(is: InputStream)
: OMFTables
= copy(scalarOneOfRestrictionAxioms = readJSonTable(is, ScalarOneOfRestrictionAxiomHelper.fromJSON))

def readStringScalarRestrictionAxioms(is: InputStream)
: OMFTables
= copy(stringScalarRestrictionAxioms = readJSonTable(is, StringScalarRestrictionAxiomHelper.fromJSON))

def readStructures(is: InputStream)
: OMFTables
= copy(structures = readJSonTable(is, StructureHelper.fromJSON))

def readStructuredDataPropertys(is: InputStream)
: OMFTables
= copy(structuredDataPropertys = readJSonTable(is, StructuredDataPropertyHelper.fromJSON))

def readTerminologyExtensionAxioms(is: InputStream)
: OMFTables
= copy(terminologyExtensionAxioms = readJSonTable(is, TerminologyExtensionAxiomHelper.fromJSON))

def readTerminologyGraphs(is: InputStream)
: OMFTables
= copy(terminologyGraphs = readJSonTable(is, TerminologyGraphHelper.fromJSON))

def readTerminologyNestingAxioms(is: InputStream)
: OMFTables
= copy(terminologyNestingAxioms = readJSonTable(is, TerminologyNestingAxiomHelper.fromJSON))

def readTimeScalarRestrictionAxioms(is: InputStream)
: OMFTables
= copy(timeScalarRestrictionAxioms = readJSonTable(is, TimeScalarRestrictionAxiomHelper.fromJSON))

def readUnreifiedRelationships(is: InputStream)
: OMFTables
= copy(unreifiedRelationships = readJSonTable(is, UnreifiedRelationshipHelper.fromJSON))

}
object OMFTables {
	
  def loadOMFTables(omfSchemaJsonZipFile: File)
  : Try[OMFTables]
  = nonFatalCatch[Try[OMFTables]]
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
         .aggregate(OMFTables())(seqop = readZipArchive(zipFile), combop = mergeTables)
      zipFile.close()
      Success(omfTables)
    }

  private[tables] def mergeTables
  (t1: OMFTables, t2: OMFTables)
  : OMFTables
  = OMFTables(
      aspects = t1.aspects ++ t2.aspects,
      aspectSpecializationAxioms = t1.aspectSpecializationAxioms ++ t2.aspectSpecializationAxioms,
      binaryScalarRestrictionAxioms = t1.binaryScalarRestrictionAxioms ++ t2.binaryScalarRestrictionAxioms,
      bundles = t1.bundles ++ t2.bundles,
      bundledTerminologyAxioms = t1.bundledTerminologyAxioms ++ t2.bundledTerminologyAxioms,
      concepts = t1.concepts ++ t2.concepts,
      conceptDesignationTerminologyGraphAxioms = t1.conceptDesignationTerminologyGraphAxioms ++ t2.conceptDesignationTerminologyGraphAxioms,
      conceptSpecializationAxioms = t1.conceptSpecializationAxioms ++ t2.conceptSpecializationAxioms,
      entityExistentialRestrictionAxioms = t1.entityExistentialRestrictionAxioms ++ t2.entityExistentialRestrictionAxioms,
      entityScalarDataPropertys = t1.entityScalarDataPropertys ++ t2.entityScalarDataPropertys,
      entityScalarDataPropertyExistentialRestrictionAxioms = t1.entityScalarDataPropertyExistentialRestrictionAxioms ++ t2.entityScalarDataPropertyExistentialRestrictionAxioms,
      entityScalarDataPropertyParticularRestrictionAxioms = t1.entityScalarDataPropertyParticularRestrictionAxioms ++ t2.entityScalarDataPropertyParticularRestrictionAxioms,
      entityScalarDataPropertyUniversalRestrictionAxioms = t1.entityScalarDataPropertyUniversalRestrictionAxioms ++ t2.entityScalarDataPropertyUniversalRestrictionAxioms,
      entityStructuredDataPropertys = t1.entityStructuredDataPropertys ++ t2.entityStructuredDataPropertys,
      entityUniversalRestrictionAxioms = t1.entityUniversalRestrictionAxioms ++ t2.entityUniversalRestrictionAxioms,
      iRIScalarRestrictionAxioms = t1.iRIScalarRestrictionAxioms ++ t2.iRIScalarRestrictionAxioms,
      numericScalarRestrictionAxioms = t1.numericScalarRestrictionAxioms ++ t2.numericScalarRestrictionAxioms,
      plainLiteralScalarRestrictionAxioms = t1.plainLiteralScalarRestrictionAxioms ++ t2.plainLiteralScalarRestrictionAxioms,
      reifiedRelationships = t1.reifiedRelationships ++ t2.reifiedRelationships,
      reifiedRelationshipSpecializationAxioms = t1.reifiedRelationshipSpecializationAxioms ++ t2.reifiedRelationshipSpecializationAxioms,
      scalars = t1.scalars ++ t2.scalars,
      scalarDataPropertys = t1.scalarDataPropertys ++ t2.scalarDataPropertys,
      scalarOneOfLiterals = t1.scalarOneOfLiterals ++ t2.scalarOneOfLiterals,
      scalarOneOfRestrictionAxioms = t1.scalarOneOfRestrictionAxioms ++ t2.scalarOneOfRestrictionAxioms,
      stringScalarRestrictionAxioms = t1.stringScalarRestrictionAxioms ++ t2.stringScalarRestrictionAxioms,
      structures = t1.structures ++ t2.structures,
      structuredDataPropertys = t1.structuredDataPropertys ++ t2.structuredDataPropertys,
      terminologyExtensionAxioms = t1.terminologyExtensionAxioms ++ t2.terminologyExtensionAxioms,
      terminologyGraphs = t1.terminologyGraphs ++ t2.terminologyGraphs,
      terminologyNestingAxioms = t1.terminologyNestingAxioms ++ t2.terminologyNestingAxioms,
      timeScalarRestrictionAxioms = t1.timeScalarRestrictionAxioms ++ t2.timeScalarRestrictionAxioms,
      unreifiedRelationships = t1.unreifiedRelationships ++ t2.unreifiedRelationships) 

  private[tables] def readZipArchive
  (zipFile: ZipFile)
  (tables: OMFTables, ze: ZipArchiveEntry)
  : OMFTables
  = {
  	val is = zipFile.getInputStream(ze)
  	ze.getName match {
  	  case AspectHelper.TABLE_JSON_FILENAME =>
  	    tables.readAspects(is)
  	  case AspectSpecializationAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readAspectSpecializationAxioms(is)
  	  case BinaryScalarRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readBinaryScalarRestrictionAxioms(is)
  	  case BundleHelper.TABLE_JSON_FILENAME =>
  	    tables.readBundles(is)
  	  case BundledTerminologyAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readBundledTerminologyAxioms(is)
  	  case ConceptHelper.TABLE_JSON_FILENAME =>
  	    tables.readConcepts(is)
  	  case ConceptDesignationTerminologyGraphAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readConceptDesignationTerminologyGraphAxioms(is)
  	  case ConceptSpecializationAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readConceptSpecializationAxioms(is)
  	  case EntityExistentialRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityExistentialRestrictionAxioms(is)
  	  case EntityScalarDataPropertyHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityScalarDataPropertys(is)
  	  case EntityScalarDataPropertyExistentialRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityScalarDataPropertyExistentialRestrictionAxioms(is)
  	  case EntityScalarDataPropertyParticularRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityScalarDataPropertyParticularRestrictionAxioms(is)
  	  case EntityScalarDataPropertyUniversalRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityScalarDataPropertyUniversalRestrictionAxioms(is)
  	  case EntityStructuredDataPropertyHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityStructuredDataPropertys(is)
  	  case EntityUniversalRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readEntityUniversalRestrictionAxioms(is)
  	  case IRIScalarRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readIRIScalarRestrictionAxioms(is)
  	  case NumericScalarRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readNumericScalarRestrictionAxioms(is)
  	  case PlainLiteralScalarRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readPlainLiteralScalarRestrictionAxioms(is)
  	  case ReifiedRelationshipHelper.TABLE_JSON_FILENAME =>
  	    tables.readReifiedRelationships(is)
  	  case ReifiedRelationshipSpecializationAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readReifiedRelationshipSpecializationAxioms(is)
  	  case ScalarHelper.TABLE_JSON_FILENAME =>
  	    tables.readScalars(is)
  	  case ScalarDataPropertyHelper.TABLE_JSON_FILENAME =>
  	    tables.readScalarDataPropertys(is)
  	  case ScalarOneOfLiteralHelper.TABLE_JSON_FILENAME =>
  	    tables.readScalarOneOfLiterals(is)
  	  case ScalarOneOfRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readScalarOneOfRestrictionAxioms(is)
  	  case StringScalarRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readStringScalarRestrictionAxioms(is)
  	  case StructureHelper.TABLE_JSON_FILENAME =>
  	    tables.readStructures(is)
  	  case StructuredDataPropertyHelper.TABLE_JSON_FILENAME =>
  	    tables.readStructuredDataPropertys(is)
  	  case TerminologyExtensionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readTerminologyExtensionAxioms(is)
  	  case TerminologyGraphHelper.TABLE_JSON_FILENAME =>
  	    tables.readTerminologyGraphs(is)
  	  case TerminologyNestingAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readTerminologyNestingAxioms(is)
  	  case TimeScalarRestrictionAxiomHelper.TABLE_JSON_FILENAME =>
  	    tables.readTimeScalarRestrictionAxioms(is)
  	  case UnreifiedRelationshipHelper.TABLE_JSON_FILENAME =>
  	    tables.readUnreifiedRelationships(is)
    }
  }
  
  def saveOMFTables
  (tables: OMFTables,
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
  
      zos.putNextEntry(new java.util.zip.ZipEntry(AspectHelper.TABLE_JSON_FILENAME))
      tables.aspects.foreach { t =>
         val line = AspectHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(AspectSpecializationAxiomHelper.TABLE_JSON_FILENAME))
      tables.aspectSpecializationAxioms.foreach { t =>
         val line = AspectSpecializationAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(BinaryScalarRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.binaryScalarRestrictionAxioms.foreach { t =>
         val line = BinaryScalarRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(BundleHelper.TABLE_JSON_FILENAME))
      tables.bundles.foreach { t =>
         val line = BundleHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(BundledTerminologyAxiomHelper.TABLE_JSON_FILENAME))
      tables.bundledTerminologyAxioms.foreach { t =>
         val line = BundledTerminologyAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ConceptHelper.TABLE_JSON_FILENAME))
      tables.concepts.foreach { t =>
         val line = ConceptHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ConceptDesignationTerminologyGraphAxiomHelper.TABLE_JSON_FILENAME))
      tables.conceptDesignationTerminologyGraphAxioms.foreach { t =>
         val line = ConceptDesignationTerminologyGraphAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ConceptSpecializationAxiomHelper.TABLE_JSON_FILENAME))
      tables.conceptSpecializationAxioms.foreach { t =>
         val line = ConceptSpecializationAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityExistentialRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.entityExistentialRestrictionAxioms.foreach { t =>
         val line = EntityExistentialRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityScalarDataPropertyHelper.TABLE_JSON_FILENAME))
      tables.entityScalarDataPropertys.foreach { t =>
         val line = EntityScalarDataPropertyHelper.toJSON(t)+"\n"
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
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityStructuredDataPropertyHelper.TABLE_JSON_FILENAME))
      tables.entityStructuredDataPropertys.foreach { t =>
         val line = EntityStructuredDataPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(EntityUniversalRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.entityUniversalRestrictionAxioms.foreach { t =>
         val line = EntityUniversalRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(IRIScalarRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.iRIScalarRestrictionAxioms.foreach { t =>
         val line = IRIScalarRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(NumericScalarRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.numericScalarRestrictionAxioms.foreach { t =>
         val line = NumericScalarRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(PlainLiteralScalarRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.plainLiteralScalarRestrictionAxioms.foreach { t =>
         val line = PlainLiteralScalarRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ReifiedRelationshipHelper.TABLE_JSON_FILENAME))
      tables.reifiedRelationships.foreach { t =>
         val line = ReifiedRelationshipHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ReifiedRelationshipSpecializationAxiomHelper.TABLE_JSON_FILENAME))
      tables.reifiedRelationshipSpecializationAxioms.foreach { t =>
         val line = ReifiedRelationshipSpecializationAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ScalarHelper.TABLE_JSON_FILENAME))
      tables.scalars.foreach { t =>
         val line = ScalarHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ScalarDataPropertyHelper.TABLE_JSON_FILENAME))
      tables.scalarDataPropertys.foreach { t =>
         val line = ScalarDataPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ScalarOneOfLiteralHelper.TABLE_JSON_FILENAME))
      tables.scalarOneOfLiterals.foreach { t =>
         val line = ScalarOneOfLiteralHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(ScalarOneOfRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.scalarOneOfRestrictionAxioms.foreach { t =>
         val line = ScalarOneOfRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(StringScalarRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.stringScalarRestrictionAxioms.foreach { t =>
         val line = StringScalarRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(StructureHelper.TABLE_JSON_FILENAME))
      tables.structures.foreach { t =>
         val line = StructureHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(StructuredDataPropertyHelper.TABLE_JSON_FILENAME))
      tables.structuredDataPropertys.foreach { t =>
         val line = StructuredDataPropertyHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(TerminologyExtensionAxiomHelper.TABLE_JSON_FILENAME))
      tables.terminologyExtensionAxioms.foreach { t =>
         val line = TerminologyExtensionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(TerminologyGraphHelper.TABLE_JSON_FILENAME))
      tables.terminologyGraphs.foreach { t =>
         val line = TerminologyGraphHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(TerminologyNestingAxiomHelper.TABLE_JSON_FILENAME))
      tables.terminologyNestingAxioms.foreach { t =>
         val line = TerminologyNestingAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(TimeScalarRestrictionAxiomHelper.TABLE_JSON_FILENAME))
      tables.timeScalarRestrictionAxioms.foreach { t =>
         val line = TimeScalarRestrictionAxiomHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      zos.putNextEntry(new java.util.zip.ZipEntry(UnreifiedRelationshipHelper.TABLE_JSON_FILENAME))
      tables.unreifiedRelationships.foreach { t =>
         val line = UnreifiedRelationshipHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
  
      zos.close()
  	  Success(())
  	}

}
