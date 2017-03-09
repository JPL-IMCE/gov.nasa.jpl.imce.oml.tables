package gov.nasa.jpl.imce.oml.resolver

import scala.collection.immutable.SortedSet
import scala.{AnyVal, None, Some}

object OMLOps {

  class ModuleOps(val m: api.Module) extends AnyVal {

    def entities(): SortedSet[api.Entity] =
      m.everything()
        .flatMap {
          case e: api.Entity => Some(e)
          case _ => None
        }

    def aspects(): SortedSet[api.Aspect] =
      m.everything()
        .flatMap {
          case a: api.Aspect => Some(a)
          case _ => None
        }

    def concepts(): SortedSet[api.Concept] =
      m.everything()
        .flatMap {
          case c: api.Concept => Some(c)
          case _ => None
        }

    def dataranges(): SortedSet[api.DataRange] =
      m.everything()
        .flatMap {
          case dr: api.DataRange => Some(dr)
          case _ => None
        }

    def reifiedRelationships(): SortedSet[api.ReifiedRelationship] =
      m.everything()
        .flatMap {
          case rr: api.ReifiedRelationship => Some(rr)
          case _ => None
        }

    def unreifiedRelationships(): SortedSet[api.UnreifiedRelationship] =
      m.everything()
        .flatMap {
          case ur: api.UnreifiedRelationship => Some(ur)
          case _ => None
        }

    def structures(): SortedSet[api.Structure] =
      m.everything()
        .flatMap {
          case s: api.Structure => Some(s)
          case _ => None
        }

    def entityScalarDataProperties(): SortedSet[api.EntityScalarDataProperty] =
      m.everything()
        .flatMap {
          case p: api.EntityScalarDataProperty => Some(p)
          case _ => None
        }

  }

  implicit def toModuleOps(m: api.Module): ModuleOps = new ModuleOps(m)

}
