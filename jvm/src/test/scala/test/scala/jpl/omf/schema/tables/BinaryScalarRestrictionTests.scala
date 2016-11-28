package test.scala.jpl.omf.schema.tables

import org.scalacheck.{Prop,Properties}

import test.java.jpl.omf.schema.tables._

object BinaryScalarRestrictionTests extends Properties("BinaryScalarRestrictions") {

  property("empty") = Prop.apply {
    new BinaryScalarRestrictionEmptyTest().creationTest()
    true
  }

  property("creation") = Prop.apply {
    new BinaryScalarRestrictionTest().creationTest()
    true
  }
}
