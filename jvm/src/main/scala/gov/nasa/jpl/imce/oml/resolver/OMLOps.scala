package gov.nasa.jpl.imce.oml.resolver

import scala.collection.immutable.Set
import scala.{AnyVal, Boolean, None, Some}

object OMLOps {

  def uuidEquivalent
  (juuid: java.util.UUID,
   tuuid: gov.nasa.jpl.imce.oml.tables.UUID)
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

  }

  implicit def toModuleOps(m: api.Module): ModuleOps = new ModuleOps(m)

}
