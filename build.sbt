import com.google.javascript.jscomp.CompilerOptions.LanguageMode
import org.scalajs.core.tools.linker.backend.OutputMode.ECMAScript6

name := Settings.name+"Root"

scalaVersion in ThisBuild := "2.11.8"

shellPrompt in ThisBuild := { state => Project.extract(state).currentRef.project + "> " }

val Npm = config("npm")

lazy val tablesRoot = project.in(file("."))
  .aggregate(tablesJS, tablesJVM)
  .settings(publishArtifact := false)

// a special crossProject for configuring a JS/JVM/shared structure
lazy val tables = crossProject
  .crossType(CrossType.Pure)
  .in(file("tables"))
  .settings(
    git.baseVersion := Settings.version,
    scalaVersion := Settings.versions.scala,
    scalacOptions ++= Settings.scalacOptions,
    libraryDependencies ++= Settings.sharedDependencies.value
  )
  .jvmSettings(
    libraryDependencies ++= Settings.jvmDependencies.value
  )
  // set up settings specific to the JS project
  .jsConfigure(_ enablePlugins ScalaJSPlugin)
  .jsSettings(
    inConfig(Npm)(
      PreprocessPlugin.preprocessSettings(Npm) ++
        Seq(
          preprocessVars := Map(
            "HYPHENATED_NAME" -> s"${Settings.name.replace('.', '-')}",
            "DESCRIPTION" -> description.value,
            "VERSION" -> version.value,
            "HOMEPAGE" -> (homepage in ThisBuild).value.get.toString,
            "GIT" -> (git.remoteRepo in ThisBuild).value,
            "ISSUES" -> s"${(scmInfo in ThisBuild).value.get.browseUrl.toString+"/issues"}",
            "AUTHOR" -> {
              val a = (developers in ThisBuild).value.head
              s"""{ "name": "${a.name}", "email": "${a.email}", "url": "${a.url.toString}"}"""
            },
            "CONTRIBUTORS" -> {
              val ds = (developers in ThisBuild).value.map { d =>
                s"""{ "name": "${d.name}", "email": "${d.email}", "url": "${d.url.toString}"}"""
              }
              ds.mkString("[",",", "]")
            }
          ),
          sourceDirectory := baseDirectory.value / ".." / ".." / "tables" / "src" / "npm-preprocess",
          preprocessIncludeFilter := "*.json",
          includeFilter := AllPassFilter,
          target := baseDirectory.value / ".." / ".." / "target" / "npm-dist"
        )
    ) : _*)
  .jsSettings(

    jsEnv := NodeJSEnv().value,
    scalaJSUseRhino := false,
    scalaJSStage in Global := FullOptStage,
    requiresDOM := false,

    libraryDependencies ++= Settings.scalajsDependencies.value,
    scalaJSOutputMode := ECMAScript6,
    requiresDOM := false,

    crossTarget in (Compile, fastOptJS) := baseDirectory.value / ".." / ".." / "target" / "npm-dist",
    crossTarget in (Compile, fullOptJS) := baseDirectory.value / ".." / ".." / "target" / "npm-dist",

    artifactPath in (Compile, fastOptJS) :=
      (crossTarget in (Compile, fastOptJS)).value / "jpl-omf-schema-tables.js",
    artifactPath in (Compile, fullOptJS) :=
      (crossTarget in (Compile, fullOptJS)).value / "jpl-omf-schema-tables.js",

    fastOptJS in Compile := {
      val result = (fastOptJS in Compile).value
      val p = (preprocess in config("npm")).value
      streams.value.log.info("npm/preprocess: "+p)
      IO.copyFile(baseDirectory.value / ".." / ".." / ".npmrc", baseDirectory .value / ".." / ".." / "target" / "npm-dist" / ".npmrc")
      IO.copyFile(baseDirectory.value / ".." / ".." / "README.md", baseDirectory .value / ".." / ".." / "target" / "npm-dist" / "README.md")
      val ok = Process(command=Seq[String]("npm", "pack"), cwd = baseDirectory.value / ".." / ".." / "target" / "npm-dist" ).!
      require(0 == ok, "npm pack failed: " + ok)
      result
    },

    fullOptJS in Compile := {
      val result = (fullOptJS in Compile).value
      val p = (preprocess in config("npm")).value
      streams.value.log.info("npm/preprocess: "+p)
      IO.copyFile(baseDirectory.value / ".." / ".." / ".npmrc", baseDirectory.value / ".." / ".." / "target" / "npm-dist" / ".npmrc")
      IO.copyFile(baseDirectory.value / ".." / ".." / "README.md", baseDirectory .value / ".." / ".." / "target" / "npm-dist" / "README.md")
      val ok = Process(command=Seq[String]("npm", "pack"), cwd = baseDirectory.value / ".." / ".." / "target" / "npm-dist" ).!
      require(0 == ok, "npm pack failed: " + ok)
      result
    }
  )

lazy val tablesJVM = tables.jvm.settings(name := Settings.name+"JVM")
lazy val tablesJS = tables.js.settings(name := Settings.name+"JS")
