package gov.nasa.jpl.imce.oml.resolver

import scala.collection.immutable.SortedSet
import scala.{AnyVal, Boolean, Option, None, Some}

object OMLOps {

  def uuidEquivalent
  (juuid: Option[java.util.UUID],
   tuuid: Option[gov.nasa.jpl.imce.oml.tables.UUID])
  : Boolean
  = (juuid, tuuid) match {
    case (None, None) => true
    case (None, Some(_)) => false
    case (Some(_), None) => false
    case (Some(j), Some(t)) => j.toString == t
  }

  class ModuleOps(val m: api.Module) extends AnyVal {

    def entities
    (implicit ex: api.Extent)
    : SortedSet[api.Entity]
    = m
      .everything()
      .flatMap {
        case e: api.Entity => Some(e)
        case _ => None
      }

    def aspects
    (implicit ex: api.Extent)
    : SortedSet[api.Aspect]
    = m
      .everything()
      .flatMap {
        case a: api.Aspect => Some(a)
        case _ => None
      }

    def concepts
    (implicit ex: api.Extent)
    : SortedSet[api.Concept]
    = m
      .everything()
      .flatMap {
        case c: api.Concept => Some(c)
        case _ => None
      }

    def dataranges
    (implicit ex: api.Extent)
    : SortedSet[api.DataRange]
    = m
      .everything()
      .flatMap {
        case dr: api.DataRange => Some(dr)
        case _ => None
      }

    def reifiedRelationships
    (implicit ex: api.Extent)
    : SortedSet[api.ReifiedRelationship]
    = m
      .everything()
      .flatMap {
        case rr: api.ReifiedRelationship => Some(rr)
        case _ => None
      }

    def unreifiedRelationships
    (implicit ex: api.Extent)
    : SortedSet[api.UnreifiedRelationship]
    = m
      .everything()
      .flatMap {
        case ur: api.UnreifiedRelationship => Some(ur)
        case _ => None
      }

    def structures
    (implicit ex: api.Extent)
    : SortedSet[api.Structure]
    = m
      .everything()
      .flatMap {
        case s: api.Structure => Some(s)
        case _ => None
      }

    def entityScalarDataProperties
    (implicit ex: api.Extent)
    : SortedSet[api.EntityScalarDataProperty]
    = m
      .everything()
      .flatMap {
        case p: api.EntityScalarDataProperty => Some(p)
        case _ => None
      }

  }

  implicit def toModuleOps(m: api.Module): ModuleOps = new ModuleOps(m)

}
