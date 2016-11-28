package test.scala.jpl.omf.schema.tables

import org.scalacheck.{Prop,Properties}

import test.java.jpl.omf.schema.tables._

object TimeScalarRestrictionTests extends Properties("TimeScalarRestrictions") {

  property("empty") = Prop.apply {
    new TimeScalarRestrictionEmptyTest().creationTest()
    true
  }

  property("creation") = Prop.apply {
    new TimeScalarRestrictionTest().creationTest()
    true
  }
}
