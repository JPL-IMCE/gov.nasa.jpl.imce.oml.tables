package gov.nasa.jpl.imce.oml.uuid

import java.util.UUID
import scala.Predef.String

trait OMLUUIDGenerator {

  def namespaceUUID
  (namespace: String, factors: (String,String)*)
  : UUID

  def derivedUUID
  (context: String, factors: (String,UUID)*)
  : UUID

}
