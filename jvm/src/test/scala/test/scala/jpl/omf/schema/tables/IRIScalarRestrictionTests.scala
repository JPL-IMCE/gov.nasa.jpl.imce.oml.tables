package test.scala.jpl.omf.schema.tables

import org.scalacheck.{Prop,Properties}

import test.java.jpl.omf.schema.tables._

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

