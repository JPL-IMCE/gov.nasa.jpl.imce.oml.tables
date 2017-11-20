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

import gov.nasa.jpl.imce.oml.covariantTag.@@

import scala.collection.immutable.Set
import scala.{AnyVal, Boolean, None, Some}
import scala.Predef.String

object OMLOps {

  def uuidEquivalent[T]
  (juuid: java.util.UUID,
   tuuid: String @@ T)
  : Boolean
  = juuid.toString == tuuid

  class ModuleOps(val m: api.Module) extends AnyVal {

    def entities
    (implicit ex: api.Extent)
    : Set[api.Entity]
    = m match {
      case g: api.TerminologyBox =>
        Set.empty[api.Entity] ++ ex.lookupBoxStatements(g).flatMap {
          case e: api.Entity => Some(e)
          case _ => None
        }
      case _: api.DescriptionBox =>
        Set.empty[api.Entity]
    }

    def aspects
    (implicit ex: api.Extent)
    : Set[api.Aspect]
    = m match {
      case g: api.TerminologyBox =>
        Set.empty[api.Aspect] ++ ex.lookupBoxStatements(g).flatMap {
          case a: api.Aspect => Some(a)
          case _ => None
        }
      case d: api.DescriptionBox =>
        Set.empty[api.Aspect]
    }

    def concepts
    (implicit ex: api.Extent)
    : Set[api.Concept]
    = m match {
      case g: api.TerminologyBox =>
        Set.empty[api.Concept] ++ ex.lookupBoxStatements(g).flatMap {
          case c: api.Concept => Some(c)
          case _ => None
        }
      case d: api.DescriptionBox =>
        Set.empty[api.Concept]
    }

    def dataranges
    (implicit ex: api.Extent)
    : Set[api.DataRange]
    = m match {
      case g: api.TerminologyBox =>
        Set.empty[api.DataRange] ++ ex.lookupBoxStatements(g).flatMap {
          case dr: api.DataRange => Some(dr)
          case _ => None
        }
      case d: api.DescriptionBox =>
        Set.empty[api.DataRange]
    }

    def reifiedRelationships
    (implicit ex: api.Extent)
    : Set[api.ReifiedRelationship]
    = m match {
      case g: api.TerminologyBox =>
        Set.empty[api.ReifiedRelationship] ++ ex.lookupBoxStatements(g).flatMap {
          case rr: api.ReifiedRelationship => Some(rr)
          case _ => None
        }
      case _: api.DescriptionBox =>
        Set.empty[api.ReifiedRelationship]
    }

    def unreifiedRelationships
    (implicit ex: api.Extent)
    : Set[api.UnreifiedRelationship]
    = m match {
      case g: api.TerminologyBox =>
        Set.empty[api.UnreifiedRelationship] ++ ex.lookupBoxStatements(g).flatMap {
          case ur: api.UnreifiedRelationship => Some(ur)
          case _ => None
        }
      case _: api.DescriptionBox =>
        Set.empty[api.UnreifiedRelationship]
    }

    def structures
    (implicit ex: api.Extent)
    : Set[api.Structure]
    = m match {
      case g: api.TerminologyBox =>
        Set.empty[api.Structure] ++ ex.lookupBoxStatements(g).flatMap {
          case s: api.Structure => Some(s)
          case _ => None
        }
      case _: api.DescriptionBox =>
        Set.empty[api.Structure]
    }

    def entityScalarDataProperties
    (implicit ex: api.Extent)
    : Set[api.EntityScalarDataProperty]
    = m match {
      case g: api.TerminologyBox =>
        Set.empty[api.EntityScalarDataProperty] ++ ex.lookupBoxStatements(g).flatMap {
          case sdp: api.EntityScalarDataProperty => Some(sdp)
          case _ => None
        }
      case _: api.DescriptionBox =>
        Set.empty[api.EntityScalarDataProperty]
    }

    def entityStructuredDataProperties
    (implicit ex: api.Extent)
    : Set[api.EntityStructuredDataProperty]
    = m match {
      case g: api.TerminologyBox =>
        Set.empty[api.EntityStructuredDataProperty] ++ ex.lookupBoxStatements(g).flatMap {
          case sdp: api.EntityStructuredDataProperty => Some(sdp)
          case _ => None
        }
      case _: api.DescriptionBox =>
        Set.empty[api.EntityStructuredDataProperty]
    }

    def scalarDataProperties
    (implicit ex: api.Extent)
    : Set[api.ScalarDataProperty]
    = m match {
      case g: api.TerminologyBox =>
        Set.empty[api.ScalarDataProperty] ++ ex.lookupBoxStatements(g).flatMap {
          case sdp: api.ScalarDataProperty => Some(sdp)
          case _ => None
        }
      case _: api.DescriptionBox =>
        Set.empty[api.ScalarDataProperty]
    }

    def structuredDataProperties
    (implicit ex: api.Extent)
    : Set[api.StructuredDataProperty]
    = m match {
      case g: api.TerminologyBox =>
        Set.empty[api.StructuredDataProperty] ++ ex.lookupBoxStatements(g).flatMap {
          case sdp: api.StructuredDataProperty => Some(sdp)
          case _ => None
        }
      case _: api.DescriptionBox =>
        Set.empty[api.StructuredDataProperty]
    }

    def boxStatements
    (implicit ex: api.Extent)
    : Set[api.TerminologyBoxStatement]
    = m match {
      case g: api.TerminologyBox =>
        ex.lookupBoxStatements(g)
      case _ =>
        Set.empty[api.TerminologyBoxStatement]
    }

  }

  implicit def toModuleOps(m: api.Module): ModuleOps = new ModuleOps(m)

}
