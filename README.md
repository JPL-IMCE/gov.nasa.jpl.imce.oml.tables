# JPL's Ontological Modeling Framework (OMF) Schema Tables for Integrated Model-Centric Engineering (IMCE).

[![Build Status](https://travis-ci.org/JPL-IMCE/jpl.omf.schema.tables.svg?branch=master)](https://travis-ci.org/JPL-IMCE/jpl.omf.schema.tables)
 [ ![Download](https://api.bintray.com/packages/jpl-imce/gov.nasa.jpl.imce.npm/jpl-omf-schema-tables/images/download.svg) ](https://bintray.com/jpl-imce/gov.nasa.jpl.imce.npm/jpl-omf-schema-tables/_latestVersion)
 [ ![Download](https://api.bintray.com/packages/jpl-imce/gov.nasa.jpl.imce/jpl.omf.schema.tables/images/download.svg) ](https://bintray.com/jpl-imce/gov.nasa.jpl.imce/jpl.omf.schema.tables/_latestVersion)
 
This project specifies a set of normalized schema tables for JPL's Ontological Modeling Framework.
By normalize schema tables, we mean precisely a [4th Normal Form database schema](http://www.bkent.net/Doc/simple5.htm#label4).

This schema is intended to be a single source of truth for technology-neutral data interchange of OMF models.
By technology-neutral data interchange, we mean the separation between:
   - the specification of the data to be exchanged among tools,
   - the representation of this data in a particular technology stack.
   
Normalized schema tables specify the shape of the data to be exchanged in terms of tables with single-valued columns.
This means that for each table:
 - each column specifies a simple attribute typed by a scalar datatype (e.g. string, integer, boolean, ..)
 - there are no "multiple values" in a given table row; instead, multiple values are represented as multiple rows.

The representation of these normalized schema tables is deliberately left open 
to leverage various technologies and serializations.
In particular, representation technologies include but are not limited to:
- JavaScript
- Java
- Scala

In particular, serializations include but are not limited to:
- JSon
- RDF (RDF/XML, RDF/Json, RDF/NTriples, ...)
- OWL (OWL/XML, RDF/XML, Manchester, ...)
- XML
- SQL

The reference serialization for OMF normalized schema tables data is Json in the following format:
- Each row of a table is a single line Json tuple of name/value pairs for each table column.

This format is deliberately chosen to facilitate processing OMF data according 
to the [Reactive Manifesto](http://www.reactivemanifesto.org); 
for example, using [Apache Spark](http://spark.apache.org).

## Scala as a single-source of truth

The OMF normalized schema tables are specified in the Scala programming language:
- each table is a Scala case class
- each table column is an immutable field of a Scala case class

Via cross-compilation using [scala.js](http://scala-js.org), 
tables and column fields are annotated 
[@JSExport](https://www.scala-js.org/doc/interoperability/export-to-javascript.html)
to make them accessible by their name in JavaScript.

Cross-compiling this project results in three distinct libraries:

1. A JVM library for writing pure Java applications; mixed Java/Scala applications using Scala or pure Scala applications.
   
2. A ScalaJS library for writing mixed Java/Scala/JavaScript applications using Scala.JS.
   
3. An NPM module for developing pure JavaScript applications using conventional JavaScript practices.

All 3 libraries are built on [Travis CI](https://travis-ci.org/JPL-IMCE/omf.schema.tables) and published 
on [Bintray NPM](https://bintray.com/jpl-imce/gov.nasa.jpl.imce.npm/jpl-omf-schema-tables)
and [Bintray Maven](https://bintray.com/jpl-imce/gov.nasa.jpl.imce/omf.schema.tables).

## Publishing to & resolving from bintray.com as a scoped NPM package.

Publishing a scoped NPM package is important for using a combination of multiple NPM repositories
for resolving NPM packages:
- Unscoped packages are resolved against the default NPM repository.
- Scoped packages are resolved against a scoped entry in the project or user's `.npmrc`.

For publishing, `.npmrc` needs:

```
@imce:registry=https://api.bintray.com/npm/jpl-imce/gov.nasa.jpl.imce.npm/
//api.bintray.com/npm/jpl-imce/gov.nasa.jpl.imce.npm/:username=nrouquette
//api.bintray.com/npm/jpl-imce/gov.nasa.jpl.imce.npm/:_authToken=<base64 API key>
//api.bintray.com/npm/jpl-imce/gov.nasa.jpl.imce.npm/:email=nicolas.f.rouquette@jpl.nasa.gov
//api.bintray.com/npm/jpl-imce/gov.nasa.jpl.imce.npm/:always-auth=true
```

For resolving, `.npmrc` needs:

```
@imce:registry=https://api.bintray.com/npm/jpl-imce/gov.nasa.jpl.imce.npm/
//api.bintray.com/npm/jpl-imce/gov.nasa.jpl.imce.npm/:always-auth=false
```

## Testing JS library

```
sbt fullOptJS
cd target/npm-dist
node ../../tables/test/js/index
```
