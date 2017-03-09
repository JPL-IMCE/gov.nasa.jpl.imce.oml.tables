package test.scala.oml.tables

import org.scalacheck.{Prop, Properties}
import test.java.oml.tables.{TimeScalarRestrictionEmptyTest, TimeScalarRestrictionTest}

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
