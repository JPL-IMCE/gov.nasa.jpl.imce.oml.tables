package test.scala.jpl.omf.schema.tables

import org.scalacheck.{Prop,Properties}

import test.java.jpl.omf.schema.tables._

object BinaryScalarRestrictionAxiomTests extends Properties("BinaryScalarRestrictionAxioms") {

  property("empty") = Prop.apply {
    new BinaryScalarRestrictionAxiomEmptyTest().creationTest()
    true
  }

  property("creation") = Prop.apply {
    new BinaryScalarRestrictionAxiomTest().creationTest()
    true
  }
}
