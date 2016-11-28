package test.scala.jpl.omf.schema.tables

import org.scalacheck.{Prop,Properties}

import test.java.jpl.omf.schema.tables._

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
