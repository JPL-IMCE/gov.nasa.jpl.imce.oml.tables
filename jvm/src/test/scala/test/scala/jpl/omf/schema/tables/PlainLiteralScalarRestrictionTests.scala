package test.scala.jpl.omf.schema.tables

import org.scalacheck.{Prop,Properties}

import test.java.jpl.omf.schema.tables._

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
