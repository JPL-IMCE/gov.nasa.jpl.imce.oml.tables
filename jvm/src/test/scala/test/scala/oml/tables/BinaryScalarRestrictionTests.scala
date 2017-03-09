package test.scala.oml.specification.tables

import org.scalacheck.{Prop, Properties}
import test.java.oml.specification.tables.{BinaryScalarRestrictionEmptyTest, BinaryScalarRestrictionTest}

object BinaryScalarRestrictionTests extends Properties("BinaryScalarRestrictions") {

  property("empty") = Prop.apply {
    new BinaryScalarRestrictionEmptyTest().creationTest()
    true
  }

  property("creation") = Prop.apply {
    new BinaryScalarRestrictionTest().creationTest()
    true
  }
}
