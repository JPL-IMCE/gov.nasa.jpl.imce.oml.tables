package test.scala.oml.tables

import org.scalacheck.{Prop, Properties}
import test.java.oml.tables.{PlainLiteralScalarRestrictionEmptyTest, PlainLiteralScalarRestrictionTest}

object PlainLiteralScalarRestrictionTests extends Properties("PlainLiteralScalarRestrictions") {

  property("empty") = Prop.apply {
    new PlainLiteralScalarRestrictionEmptyTest().creationTest()
    true
  }

  property("creation") = Prop.apply {
    new PlainLiteralScalarRestrictionTest().creationTest()
    true
  }
}
