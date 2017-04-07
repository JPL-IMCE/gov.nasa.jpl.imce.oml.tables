package gov.nasa.jpl.imce.oml.uuid

import java.util.UUID

import com.fasterxml.uuid.Generators
import com.fasterxml.uuid.impl.NameBasedGenerator
import scala.Predef.String

case class JVMUUIDGenerator() extends OMLUUIDGenerator {

  override def namespaceUUID
  (namespace: String, factors: (String,String)*)
  : UUID
  = {
    val name = namespace + factors.map { case (k,v) => k+":"+v }.mkString(",")
    Generators
      .nameBasedGenerator(NameBasedGenerator.NAMESPACE_URL)
      .generate(name)
  }

  override def derivedUUID
  (context: String, factors: (String,UUID)*)
  : UUID
  = {
    val name = context + factors.map { case (k,v) => k+":"+v }.mkString(",")
    Generators
      .nameBasedGenerator(NameBasedGenerator.NAMESPACE_URL)
      .generate(name)
  }

}
