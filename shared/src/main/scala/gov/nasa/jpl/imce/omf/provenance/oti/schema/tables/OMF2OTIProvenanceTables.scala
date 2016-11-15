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

package gov.nasa.jpl.imce.omf.provenance.oti.schema.tables

import java.io.{File,InputStream}
import org.apache.commons.compress.archivers.zip.{ZipArchiveEntry, ZipFile}

import scala.collection.immutable.Seq
import scala.collection.JavaConversions._
import scala.{Boolean,Unit}
import scala.util.control.Exception._
import scala.util.{Failure,Success,Try}

case class OMF2OTIProvenanceTables private[tables]
(
  omf2OTIProvenances : Seq[OMF2OTIProvenance] = Seq.empty
) 
{
  def readOMF2OTIProvenances(is: InputStream)
  : OMF2OTIProvenanceTables
  = copy(omf2OTIProvenances = readJSonTable(is, OMF2OTIProvenanceHelper.fromJSON))

  def isEmpty: Boolean
  = omf2OTIProvenances.isEmpty
}

object OMF2OTIProvenanceTables {
	
  def createEmptyOMF2OTIProvenanceTables()
  : OMF2OTIProvenanceTables
  = new OMF2OTIProvenanceTables()
  
  def loadOMF2OTIProvenanceTables(omfSchemaJsonZipFile: File)
  : Try[OMF2OTIProvenanceTables]
  = nonFatalCatch[Try[OMF2OTIProvenanceTables]]
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
         .aggregate(OMF2OTIProvenanceTables())(seqop = readZipArchive(zipFile), combop = mergeTables)
      zipFile.close()
      Success(omfTables)
    }

  private[tables] def mergeTables
  (t1: OMF2OTIProvenanceTables, t2: OMF2OTIProvenanceTables)
  : OMF2OTIProvenanceTables
  = OMF2OTIProvenanceTables(
      omf2OTIProvenances = t1.omf2OTIProvenances ++ t2.omf2OTIProvenances) 

  private[tables] def readZipArchive
  (zipFile: ZipFile)
  (tables: OMF2OTIProvenanceTables, ze: ZipArchiveEntry)
  : OMF2OTIProvenanceTables
  = {
  	val is = zipFile.getInputStream(ze)
  	ze.getName match {
  	  case OMF2OTIProvenanceHelper.TABLE_JSON_FILENAME =>
  	    tables.readOMF2OTIProvenances(is)
    }
  }
  
  def saveOMF2OTIProvenanceTables
  (tables: OMF2OTIProvenanceTables,
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
  
      zos.putNextEntry(new java.util.zip.ZipEntry(OMF2OTIProvenanceHelper.TABLE_JSON_FILENAME))
      tables.omf2OTIProvenances.foreach { t =>
         val line = OMF2OTIProvenanceHelper.toJSON(t)+"\n"
         zos.write(line.getBytes(java.nio.charset.Charset.forName("UTF-8")))
      }
      zos.closeEntry()
  
      zos.close()
  	  Success(())
  	}

}
