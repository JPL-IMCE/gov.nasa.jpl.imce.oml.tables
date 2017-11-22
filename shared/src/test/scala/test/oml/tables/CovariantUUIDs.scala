package test.oml.tables

import gov.nasa.jpl.imce.oml.tables.taggedTypes
import org.scalacheck.Prop.forAll
import org.scalacheck.Properties

import scala.collection.immutable.Seq
import scala.Option

object CovariantUUIDs extends Properties("CovariantUUIDs") {

  property("entities") = forAll(
    UUIDGenerators.aspectUUID,
    UUIDGenerators.conceptUUID,
    UUIDGenerators.reifiedRelationshipUUID,
    UUIDGenerators.unreifiedRelationshipUUID
  )((a: taggedTypes.AspectUUID,
     c: taggedTypes.ConceptUUID,
     rr: taggedTypes.ReifiedRelationshipUUID,
     ur: taggedTypes.UnreifiedRelationshipUUID) => {

    val e1 = Seq(a, c, rr)
    val e2 = e1 :+ ur
    val es: Seq[taggedTypes.EntityUUID] = e1
    val p1 = es.size == 3

    val e_c: Option[taggedTypes.EntityUUID] = es.find(_ == c)
    val p2 = e_c.contains(c)

    p1 && p2
  })

}
