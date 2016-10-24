#!/usr/bin/env node
'use strict';

const OMF = require('../../../jpl-omf-schema-tables').__ScalaJSExportsNamespace;

console.log("OMF Schema Tables...");

const w1 = NumericScalarRestrictionAxiomJS.jsNumericScalarRestrictionAxiom(
    "BCDEF-12345-6789A-012345",
    "12345-6789A-BCDEF-012345",
    undefined,
    undefined,
    undefined,
    undefined,
    "4567-2345-ABCD-1245",
    "1245-ABCD-2345-4567");

const s1 = OMF.gov.nasa.jpl.imce.omf.schema.tables.NumericScalarRestrictionAxiomHelper().toJSON(w1);
const r1 = OMF.gov.nasa.jpl.imce.omf.schema.tables.NumericScalarRestrictionAxiomHelper().fromJSON(s1);

console.log("W1: ", w1);
console.log("S1: ", s1);

console.log("Checking graphUUID...", w1.graphUUID === r1.graphUUID);
console.log("Checking uuid...", w1.uuid === r1.uuid);
console.log("Checking maxExclusive...", w1.maxExclusive === r1.maxExclusive);
console.log("Checking maxInclusive...", w1.maxInclusive === r1.maxInclusive);
console.log("Checking minExclusive...", w1.minExclusive === r1.minExclusive);
console.log("Checking minExclusive...", w1.minExclusive === r1.minExclusive);
console.log("Checking restrictedScalarUUID...", w1.restrictedScalarUUID === r1.restrictedScalarUUID);
console.log("Checking scalarUUID...", w1.scalarUUID === r1.scalarUUID);
