import sbt._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

object Settings {

  val name = "gov.nasa.jpl.imce.oml.tables"

  val dashName = name.replace('.', '-')

  val organization = "gov.nasa.jpl.imce"

  val organizationName = "JPL-IMCE"

  val version = "0.60.1"

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
    val scala = "2.11.8"
    val scalaJ8CompatVersion = "0.8.0-RC8"
    val scalaGraphLibraries = "3.4.+"
  }

  /**
    * These dependencies are shared between JS and JVM projects
    * the special %%% function selects the correct version for each project
    */
  val sharedDependencies = Def.setting(Seq(
    "org.apache.commons" % "commons-compress" % "1.4.1",
    "org.scalacheck" %%% "scalacheck" % "1.13.2" % "test",
    "com.lihaoyi" %%% "upickle" % "0.4.3"
  ))


  /** Dependencies only used by the JVM project */
  val jvmDependencies = Def.setting(Seq(
    "org.scala-lang.modules" %% "scala-java8-compat" % versions.scalaJ8CompatVersion,
    "org.scala-js" %% "scalajs-stubs" % scalaJSVersion % "provided",
    "com.novocode" % "junit-interface" % "0.11" % "test",
    "gov.nasa.jpl.imce" %% "imce.third_party.scala_graph_libraries"
      % versions.scalaGraphLibraries % "compile" artifacts
      Artifact("imce.third_party.scala_graph_libraries", "zip", "zip", "resource")
  ))

  /** Dependencies only used by the JS project (note the use of %%% instead of %%) */
  val scalajsDependencies = Def.setting(Seq(
  ))

}