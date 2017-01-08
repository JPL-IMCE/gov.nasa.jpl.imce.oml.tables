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


package gov.nasa.jpl.imce.omf.schema

import java.io.InputStream

import scala.collection.immutable.Seq
import scala.io
import scala.{Int,Ordering}
import scala.Predef.String

package object tables {
	type IRI = String
	type Language = String
	type LexicalNumber = String
	type LexicalTime = String
	type LexicalValue = String
	type LocalName = String
	type Pattern = String
	type UUID = String
  	
  def readJSonTable[T](is: InputStream, fromJSon: String => T)
  : Seq[T]
  = io.Source.fromInputStream(is).getLines.map(fromJSon).to[Seq]

	implicit def annotationPropertyOrdering
  : Ordering[AnnotationProperty]
  = new Ordering[AnnotationProperty] {

    def compare(x: AnnotationProperty, y: AnnotationProperty)
    : Int
    = x.uuid.compareTo(y.uuid)

  }

  implicit def annotationOrdering
  : Ordering[Annotation]
  = new Ordering[Annotation] {

    def compare(x: Annotation, y: Annotation)
    : Int
    = x.terminologyUUID.compareTo(y.terminologyUUID) match {
      case -1 =>
        -1
      case 1 =>
        1
      case 0 =>
        x.propertyUUID.compareTo(y.propertyUUID) match {
          case -1 =>
            -1
          case 1 =>
            1
          case 0 =>
            x.subjectUUID.compareTo(y.subjectUUID) match {
              case -1 =>
                -1
              case 1 =>
                1
              case 0 =>
                x.value.compareTo(y.value)
            }
        }
    }

  }
}
