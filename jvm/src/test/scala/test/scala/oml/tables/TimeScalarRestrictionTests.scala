package test.scala.oml.specification.tables

import org.scalacheck.{Prop, Properties}
import test.java.oml.specification.tables.{TimeScalarRestrictionEmptyTest, TimeScalarRestrictionTest}

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
