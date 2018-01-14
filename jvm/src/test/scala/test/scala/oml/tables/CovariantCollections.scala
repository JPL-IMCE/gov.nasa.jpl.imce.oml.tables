package test.scala.oml.tables

import gov.nasa.jpl.imce.oml.tables.{taggedTypes,Aspect,Concept,ReifiedRelationship,OpenWorldDefinitions,TerminologyGraph}
import gov.nasa.jpl.imce.oml.uuid.JVMUUIDGenerator
import org.scalacheck.Prop.forAll
import org.scalacheck.Properties
import test.oml.tables.{SchemaGenerators, UUIDGenerators}

import scala.collection.immutable.Seq
import scala.Predef.String

object CovariantCollections extends Properties("CovariantCollections") {

  val oug = JVMUUIDGenerator()

  property("entity collections") =
    forAll(UUIDGenerators.terminologyGraphUUID)((g: taggedTypes.TerminologyGraphUUID) => {

      forAll(
        SchemaGenerators.iri,
        SchemaGenerators.name,
        SchemaGenerators.name,
        SchemaGenerators.name,
        SchemaGenerators.name,
        SchemaGenerators.name
      )((gIRI: String,
         a1Name: String,
         a2Name: String,
         c1Name: String,
         c2Name: String,
         rrName: String
        ) => {

        val g = new TerminologyGraph(oug, OpenWorldDefinitions, taggedTypes.iri(gIRI))
        val a1 = new Aspect(oug, g.uuid, taggedTypes.localName(a1Name))
        val a2 = new Aspect(oug, g.uuid, taggedTypes.localName(a2Name))
        val c1 = new Concept(oug, g.uuid, taggedTypes.localName(c1Name))
        val c2 = new Concept(oug, g.uuid, taggedTypes.localName(c2Name))
        val rr = new ReifiedRelationship(oug, g.uuid, a1.uuid, c1.uuid,
          isAsymmetric = true,
          isEssential = false,
          isInverseEssential = false,
          isFunctional = true,
          isInverseFunctional = false,
          isIrreflexive = true,
          isReflexive = false,
          isSymmetric = false,
          isTransitive = false,
          name = taggedTypes.localName(rrName))

        val as = Seq(a1, a2)
        val as1 = as.find(_.uuid == a1.uuid)
        as1.contains(a1)

      }).check

      true
    })
}