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

 
package gov.nasa.jpl.imce.oml.tables

import scala.annotation.meta.field
import scala.scalajs.js.annotation.{JSExport,JSExportTopLevel}
import scala._
import scala.Predef._

/**
  * @param uuid[1,1]
  * @param dataStructureTypeUUID[1,1]
  * @param structuredDataPropertyValueUUID[1,1]
  * @param name[1,1]
  */
@JSExportTopLevel("DataStructureTuple")
case class DataStructureTuple
(
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) dataStructureTypeUUID: UUID,
  @(JSExport @field) structuredDataPropertyValueUUID: UUID,
  @(JSExport @field) name: LocalName
) {
  // Ctor(uuidWithGenerator)   
  def this(
    oug: gov.nasa.jpl.imce.oml.uuid.OMLUUIDGenerator,
    dataStructureTypeUUID: UUID,
    structuredDataPropertyValueUUID: UUID,
    name: LocalName)
  = this(
      oug.namespaceUUID(structuredDataPropertyValueUUID, "name" -> name).toString,
      dataStructureTypeUUID,
      structuredDataPropertyValueUUID,
      name)

  override val hashCode
  : scala.Int 
  = (uuid, dataStructureTypeUUID, structuredDataPropertyValueUUID, name).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: DataStructureTuple =>
  	  (this.uuid == that.uuid) &&
  	  (this.dataStructureTypeUUID == that.dataStructureTypeUUID) &&
  	  (this.structuredDataPropertyValueUUID == that.structuredDataPropertyValueUUID) &&
  	  (this.name == that.name)
    case _ =>
      false
  }
  
}

@JSExportTopLevel("DataStructureTupleHelper")
object DataStructureTupleHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "DataStructureTuples.json"
  
  implicit val w
  : upickle.default.Writer[DataStructureTuple]
  = upickle.default.macroW[DataStructureTuple]

  @JSExport
  def toJSON(c: DataStructureTuple)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[DataStructureTuple]
  = upickle.default.macroR[DataStructureTuple]

  @JSExport
  def fromJSON(c: String)
  : DataStructureTuple
  = upickle.default.read[DataStructureTuple](c)

}	
