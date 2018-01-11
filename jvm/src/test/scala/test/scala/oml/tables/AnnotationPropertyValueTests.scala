package test.scala.oml.tables

import java.util.UUID

import gov.nasa.jpl.imce.oml.tables.taggedTypes
import gov.nasa.jpl.imce.oml.tables.{AnnotationPropertyValue,AnnotationPropertyValueHelper}

import org.scalacheck.Properties
import org.scalacheck.Prop.BooleanOperators
import scala.Predef.augmentString

object AnnotationPropertyValueTests extends Properties("AnnotationPropertyValues") {

  property("Simple Value") = {

    val uuid = UUID.randomUUID().toString
    val subjectUUID = UUID.randomUUID().toString
    val propertyUUID = UUID.randomUUID().toString
    val value =
      """on,
        |		"on",
        |		true""".stripMargin

    val ap1 = AnnotationPropertyValue(
      taggedTypes.annotationPropertyValueUUID(uuid),
      taggedTypes.logicalElementUUID(subjectUUID),
      taggedTypes.annotationPropertyUUID(propertyUUID),
      taggedTypes.stringDataType(value)
    )

    val jap = AnnotationPropertyValueHelper.toJSON(ap1)

    val ap2 = AnnotationPropertyValueHelper.fromJSON(jap)

    java.lang.System.out.println(jap)

    (ap1 == ap2) :| "annotation property value equality after JSON conversion"
  }
}
