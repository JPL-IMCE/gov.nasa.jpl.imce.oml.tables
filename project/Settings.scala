import sbt._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

object Settings {

  val name = "gov.nasa.jpl.imce.oml.tables"

  val dashName = name.replace('.', '-')

  val organization = "gov.nasa.jpl.imce"

  val organizationName = "JPL-IMCE"

  val version = "0.94"

  val scalacOptions = Seq(
    "-deprecation",
    "-encoding", "UTF-8",     // yes, this is 2 args
    "-feature",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-unchecked",
    "-Xfatal-warnings",
    "-Xlint",
    "-Yno-adapted-args",
    "-Ywarn-dead-code",       // N.B. doesn't work well with the ??? hole
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    "-Xfuture",
    "-Ywarn-unused-import",   // 2.11 only
    "-Yno-imports"            // no automatic imports at all; all symbols must be imported explicitly
  )

  object versions {
    val scala = "2.11.11"
    val scalaJ8CompatVersion = "0.8.0"
    val scalaGraphLibraries = "3.6.0"

    val circeVersion = "0.9.0-M2"
    val catsCoreVersion = "1.0.0-RC1"

    // spark requirements
    val spark_jackson="2.8.10"
  }

  /**
    * These dependencies are shared between JS and JVM projects
    * the special %%% function selects the correct version for each project
    */
  val sharedDependencies = Def.setting(Seq(
    "org.apache.commons" % "commons-compress" % "1.4.1",
    "org.scalacheck" %%% "scalacheck" % "1.13.5" % "test",
    "org.typelevel" %%% "cats-core" % versions.catsCoreVersion,
    "io.circe" %%% "circe-generic" % versions.circeVersion,
    "io.circe" %%% "circe-literal" % versions.circeVersion,
    "io.circe" %%% "circe-optics" % versions.circeVersion,
    "io.circe" %%% "circe-parser" % versions.circeVersion
  ))


  /** Dependencies only used by the JVM project */
  val jvmDependencies = Def.setting(Seq(
    "org.scala-lang.modules" %% "scala-java8-compat" % versions.scalaJ8CompatVersion,
    "org.scala-js" %% "scalajs-stubs" % scalaJSVersion % "provided",
    "com.novocode" % "junit-interface" % "0.11" % "test",

    // https://github.com/ben-manes/caffeine
    "com.github.ben-manes.caffeine" % "caffeine" % "2.6.1",
    "com.github.ben-manes.caffeine" % "guava" % "2.6.1",
    "com.github.ben-manes.caffeine" % "jcache" % "2.6.1",

    "gov.nasa.jpl.imce" %% "imce.third_party.scala_graph_libraries"
      % versions.scalaGraphLibraries % "compile" artifacts
      Artifact("imce.third_party.scala_graph_libraries", "zip", "zip", "resource")
  ))

  /** Dependencies only used by the JS project (note the use of %%% instead of %%) */
  val scalajsDependencies = Def.setting(Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.3"
  ))

}