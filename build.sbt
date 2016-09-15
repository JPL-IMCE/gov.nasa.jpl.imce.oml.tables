import com.google.javascript.jscomp.CompilerOptions.LanguageMode
import org.scalajs.core.tools.linker.backend.OutputMode.ECMAScript6

name := "omf.schema.table root project"

scalaVersion in ThisBuild := "2.11.8"

shellPrompt in ThisBuild := { state => Project.extract(state).currentRef.project + "> " }

lazy val root = project.in(file("."))
  .aggregate(tablesJS, tablesJVM)
  .settings(publishArtifact := false)

// a special crossProject for configuring a JS/JVM/shared structure
lazy val tables = crossProject
  .crossType(CrossType.Pure)
  .in(file("tables"))
  .settings(
    version := Settings.version,
    scalaVersion := Settings.versions.scala,
    scalacOptions ++= Settings.scalacOptions,
    libraryDependencies ++= Settings.sharedDependencies.value
  )
  // set up settings specific to the JS project
  .jsConfigure(_ enablePlugins ScalaJSPlugin)
  .jsSettings(
    libraryDependencies ++= Settings.scalajsDependencies.value,
    scalaJSOutputMode := ECMAScript6,
    requiresDOM := false,

    crossTarget in (Compile, fastOptJS) := baseDirectory.value / ".." / ".." / "js",
    crossTarget in (Compile, fullOptJS) := baseDirectory.value / ".." / ".." / "js",

    artifactPath in (Compile, fastOptJS) :=
      (crossTarget in (Compile, fastOptJS)).value / "omf-schema-tables-fast.js",
    artifactPath in (Compile, fullOptJS) :=
      (crossTarget in (Compile, fullOptJS)).value / "omf-schema-tables.js"
  )
  .jvmSettings(
    libraryDependencies ++= Settings.jvmDependencies.value
  )

lazy val tablesJVM = tables.jvm.settings(name := Settings.name+"JVM")
lazy val tablesJS = tables.js.settings(name := Settings.name+"JS")
