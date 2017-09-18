#!/usr/bin/env node
'use strict';

const OMF = require('../../../gov-nasa-jpl-imce-oml-tables').__ScalaJSExportsNamespace;

console.log("OMF Schema Tables...");

const w1 = new OMF.gov.nasa.jpl.imce.omf.schema.tables.Concept(
    "BCDEF-12345-6789A-012345",
    "12345-6789A-BCDEF-012345",
    false,
    "ElectricCar",
    "https://imce.jpl.nasa.gov/project/VehicleExample#ElectricCar");

const s1 = OMF.gov.nasa.jpl.imce.omf.schema.tables.ConceptHelper().toJSON(w1);
const r1 = OMF.gov.nasa.jpl.imce.omf.schema.tables.ConceptHelper().fromJSON(s1);

console.log("W1: ", w1);
console.log("S1: ", s1);

console.log("Checking name...", w1.name === 'ElectricCar');
console.log("Checking graphUUID...", w1.graphUUID === r1.graphUUID);
console.log("Checking uuid...", w1.uuid === r1.uuid);
console.log("Checking isAbstract...", w1.isAbstract === r1.isAbstract);
console.log("Checking name...", w1.name === r1.name);
console.log("Checking resourceIRI...", w1.resourceIRI === r1.resourceIRI);
