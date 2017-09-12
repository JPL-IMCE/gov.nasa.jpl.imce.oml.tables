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


package gov.nasa.jpl.imce.oml.provenance.oti

import java.io.{File,InputStream}
import org.apache.commons.compress.archivers.zip.{ZipArchiveEntry, ZipFile}

import scala.collection.immutable.Seq
import scala.collection.JavaConversions._
import scala.util.control.Exception._
import scala.util.{Failure,Success,Try}
import scala.{Boolean,Unit}

case class OML2OTIProvenanceTables
(
  oml2OTIProvenances : Seq[OML2OTIProvenance] = Seq.empty
)
{
  def readOML2OTIProvenances(is: InputStream)
  : OML2OTIProvenanceTables
  = copy(oml2OTIProvenances = readJSonTable(is, OML2OTIProvenanceHelper.fromJSON))
  
  def isEmpty: Boolean
  = oml2OTIProvenances.isEmpty
  

}

object OML2OTIProvenanceTables {
	
  def createEmptyOML2OTIProvenanceTables()
  : OML2OTIProvenanceTables
  = new OML2OTIProvenanceTables()
  
  def loadOML2OTIProvenanceTables(omlSchemaJsonZipFile: File)
  : Try[OML2OTIProvenanceTables]
  = nonFatalCatch[Try[OML2OTIProvenanceTables]]
    .withApply {
      (cause: java.lang.Throwable) =>
        cause.fillInStackTrace()
        Failure(cause)
    }
    .apply {
      val zipFile = new ZipFile(omlSchemaJsonZipFile)
      val omlTables =
        zipFile
        .getEntries
        .toIterable
        .par
         .aggregate(OML2OTIProvenanceTables())(seqop = readZipArchive(zipFile), combop = mergeTables)
      zipFile.close()
      Success(omlTables)
    }

  def mergeTables
  (t1: OML2OTIProvenanceTables, t2: OML2OTIProvenanceTables)
  : OML2OTIProvenanceTables
  = OML2OTIProvenanceTables(
      oml2OTIProvenances = t1.oml2OTIProvenances ++ t2.oml2OTIProvenances)
  
  def readZipArchive
  (zipFile: ZipFile)
  (tables: OML2OTIProvenanceTables, ze: ZipArchiveEntry)
  : OML2OTIProvenanceTables
  = {
  	val is = zipFile.getInputStream(ze)
  	ze.getName match {
  	  case OML2OTIProvenanceHelper.TABLE_JSON_FILENAME =>
  	    tables.readOML2OTIProvenances(is)
    }
  }
  
  def saveOML2OTIProvenanceTables
  (tables: OML2OTIProvenanceTables,
   omlSchemaJsonZipFile: File)
  : Try[Unit]
  = nonFatalCatch[Try[Unit]]
    .withApply {
      (cause: java.lang.Throwable) =>
        cause.fillInStackTrace()
        Failure(cause)
    }
    .apply {
  	  omlSchemaJsonZipFile.getParentFile.mkdirs()
  	  
  	  // @see http://www.oracle.com/technetwork/articles/java/compress-1565076.html
  	  val fos = new java.io.FileOutputStream(omlSchemaJsonZipFile)
  	  val bos = new java.io.BufferedOutputStream(fos, 100000)
  	  val cos = new java.util.zip.CheckedOutputStream(bos, new java.util.zip.Adler32())
  	  val zos = new java.util.zip.ZipOutputStream(new java.io.BufferedOutputStream(cos))
  
  	  zos.setMethod(java.util.zip.ZipOutputStream.DEFLATED)
  
      zos.putNextEntry(new java.util.zip.ZipEntry(OML2OTIProvenanceHelper.TABLE_JSON_FILENAME))
      tables.oml2OTIProvenances.foreach { t =>
         val line = OML2OTIProvenanceHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
      
      zos.close()
  	  Success(())
  	}

}
