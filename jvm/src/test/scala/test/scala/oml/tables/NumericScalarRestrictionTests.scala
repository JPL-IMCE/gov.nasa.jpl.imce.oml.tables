package test.scala.oml.tables

import org.scalacheck.{Prop, Properties}
import test.java.oml.tables.{NumericScalarRestrictionEmptyTest, NumericScalarRestrictionTest}

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