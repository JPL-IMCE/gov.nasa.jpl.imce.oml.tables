package test.scala.jpl.omf.schema.tables

import org.scalacheck.{Prop,Properties}

import test.java.jpl.omf.schema.tables._

object NumericScalarRestrictionTests extends Properties("NumericScalarRestrictions") {

  property("empty") = Prop.apply {
    new NumericScalarRestrictionEmptyTest().creationTest()
    true
  }

  property("creation") = Prop.apply {
    new NumericScalarRestrictionTest().creationTest()
    true
  }
}