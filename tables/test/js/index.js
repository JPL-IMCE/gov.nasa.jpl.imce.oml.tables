#!/usr/bin/env node
'use strict';

const omf = require('../../../target/npm-dist/jpl-omf-schema-tables');

console.log("OMF Schema Tables...");

const c1 = new gov.nasa.jpl.imce.omf.schema.tables.Concept(
    "https://imce.jpl.nasa.gov/project/VehicleExample#ElectricCar",
    "12345-6789A-BCDEF-012345",
    false,
    "ElectricCar");

const c2 = new gov.nasa.jpl.imce.omf.schema.tables.Concept(
    "https://imce.jpl.nasa.gov/project/VehicleExample#ElectricBicycle",
    "12345-6789A-BCDEF-012345",
    false,
    "ElectricBicycle");

console.log("C1: ", c1);
console.log("Checking name...", c1.name === 'ElectricCar');

console.log("C2: ", c2);
console.log("Checking name...", c2.name === 'ElectricBicycle');

