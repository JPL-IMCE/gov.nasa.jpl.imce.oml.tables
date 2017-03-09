package test.scala.oml.tables

import org.scalacheck.{Prop, Properties}
import test.java.oml.tables.{StringScalarRestrictionEmptyTest, StringScalarRestrictionTest}

object StringScalarRestrictionTests extends Properties("StringScalarRestrictions") {

  property("empty") = Prop.apply {
    new StringScalarRestrictionEmptyTest().creationTest()
    true
  }

  property("creation") = Prop.apply {
    new StringScalarRestrictionTest().creationTest()
    true
  }
}
