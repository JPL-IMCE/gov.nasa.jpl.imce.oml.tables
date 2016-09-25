package test.jpl.omf.schema.tables

import gov.nasa.jpl.imce.omf.schema.tables._
import org.scalacheck.Prop.forAll
import org.scalacheck.Properties

object TerminologyGraphSpecification extends Properties("TerminologyGraph") {

  property("construction") = forAll(
    SchemaGenerators.uuid,
    SchemaGenerators.name,
    SchemaGenerators.kind,
    SchemaGenerators.iri)( (uuid: java.util.UUID, name: String, kind: TerminologyGraphKind, iri: String) => {
    val w = new TerminologyGraph(uuid.toString, kind, name, iri)
    val s = TerminologyGraphHelper.toJSON(w)
    val t = s"""{"uuid":"${w.uuid}","kind":"${w.kind}","name":"${w.name}","resourceIRI":"${w.resourceIRI}"}"""
    val r = TerminologyGraphHelper.fromJSON(s)
    (s == t) && (w.uuid == r.uuid) && (w.kind == r.kind) && (w.name == r.name) && (w.resourceIRI == r.resourceIRI)
  })
}
