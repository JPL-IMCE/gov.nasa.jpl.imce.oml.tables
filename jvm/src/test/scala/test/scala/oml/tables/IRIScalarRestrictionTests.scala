package test.scala.oml.tables

import org.scalacheck.{Prop, Properties}
import test.java.oml.tables.{IRIScalarRestrictionEmptyTest, IRIScalarRestrictionTest}

object IRIScalarRestrictionTests extends Properties("IRIScalarRestrictions") {

  property("empty") = Prop.apply {
    new IRIScalarRestrictionEmptyTest().creationTest()
    true
  }

  property("creation") = Prop.apply {
    new IRIScalarRestrictionTest().creationTest()
    true
  }
}

