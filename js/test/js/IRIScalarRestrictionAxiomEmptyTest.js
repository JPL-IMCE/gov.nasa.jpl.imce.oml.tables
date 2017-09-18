#!/usr/bin/env node
'use strict';

const OMF = require('../../../gov-nasa-jpl-imce-oml-tables').__ScalaJSExportsNamespace;

console.log("OMF Schema Tables...");

const w1 = IRIScalarRestrictionAxiomJS.jsIRIScalarRestrictionAxiom(
    "BCDEF-12345-6789A-012345",
    "12345-6789A-BCDEF-012345",
    undefined,
    undefined,
    undefined,
    undefined,
    "4567-2345-ABCD-1245",
    "1245-ABCD-2345-4567");

const s1 = OMF.gov.nasa.jpl.imce.omf.schema.tables.IRIScalarRestrictionAxiomHelper().toJSON(w1);
const r1 = OMF.gov.nasa.jpl.imce.omf.schema.tables.IRIScalarRestrictionAxiomHelper().fromJSON(s1);

console.log("W1: ", w1);
console.log("S1: ", s1);

console.log("Checking graphUUID...", w1.graphUUID === r1.graphUUID);
console.log("Checking uuid...", w1.uuid === r1.uuid);
console.log("Checking length...", w1.length === r1.length);
console.log("Checking maxLength...", w1.maxLength === r1.maxLength);
console.log("Checking minLength...", w1.minLength === r1.minLength);
console.log("Checking pattern...", w1.pattern === r1.pattern);
console.log("Checking restrictedScalarUUID...", w1.restrictedScalarUUID === r1.restrictedScalarUUID);
console.log("Checking scalarUUID...", w1.scalarUUID === r1.scalarUUID);
