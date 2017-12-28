import org.scalajs.core.tools.linker.backend.OutputMode._

import com.typesafe.sbt.license.{LicenseInfo, DepModuleInfo}
import com.typesafe.sbt.sbtghpages.GhpagesPlugin

import de.heikoseeberger.sbtheader.HeaderPlugin
import de.heikoseeberger.sbtheader.CommentStyle

import gov.nasa.jpl.imce.sbt._

val oml_license =
  s"""|Copyright 2016 California Institute of Technology ("Caltech").
      |U.S. Government sponsorship acknowledged.
      |
      |Licensed under the Apache License, Version 2.0 (the "License");
      |you may not use this file except in compliance with the License.
      |You may obtain a copy of the License at
      |
      |    http://www.apache.org/licenses/LICENSE-2.0
      |
      |Unless required by applicable law or agreed to in writing, software
      |distributed under the License is distributed on an "AS IS" BASIS,
      |WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
      |See the License for the specific language governing permissions and
      |limitations under the License.
      |License Terms
      |""".stripMargin

scalaVersion in ThisBuild := "2.11.11"

shellPrompt in ThisBuild := { state => Project.extract(state).currentRef.project + "> " }

val tablesLicenseSettings: Seq[Setting[_]] =
  Seq(
    licenses += "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html"),

    startYear  := Some(2016),

    headerLicense := Some(HeaderLicense.ALv2("2016", oml_license)),

    headerMappings := headerMappings.value +
      (HeaderFileType.java -> CommentStyle.CStyleBlockComment) +
    (HeaderFileType("js") -> CommentStyle.CStyleBlockComment) +
    (HeaderFileType.scala -> CommentStyle.CStyleBlockComment),

    licenseReportTitle := "LicenseReportOfAggregatedSBTPluginsAndLibraries",

    licenseSelection += LicenseCategory("EPL", Seq("Eclipse Public License")),

    // Add style rules to the report.
    licenseReportStyleRules := Some("table, th, td {border: 1px solid black;}"),

    // The ivy configurations we'd like to grab licenses for.
    licenseConfigurations := Set("compile", "provided"),

    // Override the license information from ivy, if it's non-existent or wrong
    licenseOverrides := {
      case DepModuleInfo("com.jsuereth", _, _) =>
        LicenseInfo(LicenseCategory.BSD, "BSD-3-Clause", "http://opensource.org/licenses/BSD-3-Clause")
    },

    licenseReportTypes := Seq(Html)
  )

val tablesPublishSettings: Seq[Setting[_]] = Seq(
  PgpKeys.useGpg := true,

  PgpKeys.useGpgAgent := true,

  pgpSecretRing := file("local.secring.gpg"),

  pgpPublicRing := file("local.pubring.gpg"),

  git.baseVersion := Settings.version
) ++ versionWithGit

lazy val filter: ScopeFilter = ScopeFilter(inProjects(ThisProject), inConfigurations(Compile))

lazy val dependencySvgFile = settingKey[File]("Location of the dependency graph in SVG format")

val tablesGhPagesSettings: Seq[Setting[_]] =
  Seq[Setting[_]](
    preprocessVars in Preprocess := Map(
      "CI" -> s"https://travis-ci.org/${Settings.organizationName.toLowerCase}/${Settings.name}",
      "GIT" -> "github.com",
      "REPO" -> Settings.name,
      "VER" -> version.value,
      "ORG" -> Settings.organizationName.toLowerCase,
      "SUBJECT" -> Settings.organizationName,
      "ORG_NAME" -> organizationName.value,
      "DESC" -> description.value,
      "PKG" -> moduleName.value,
      "CONTRIBUTORS" -> {
        val commit = Process("git rev-parse HEAD").lines.head
        val p1 = Process(s"git shortlog -sne --no-merges $commit")
        val p2 = Process(
          Seq("sed",
            "-e",
            """s|^\s*\([0-9][0-9]*\)\s*\(\w.*\w\)\s*<\([a-zA-Z].*\)>.*$|<tr><td align='right'>\1</td><td>\2</td><td>\3</td></tr>|"""))
        val whoswho = p1 #| p2
        whoswho.lines.mkString("\n")
      },
      "VERSION" -> {
        git.gitCurrentTags.value match {
          case Seq(tag) =>
            s"""<a href="https://@GIT@/@ORG@/${moduleName.value}/tree/$tag">$tag</a>"""
          case _ =>
            val v = version.value
            git.gitHeadCommit.value.fold[String](v) { sha =>
              if (git.gitUncommittedChanges.value)
                v
              else
                s"""<a href="https://@GIT@/@ORG@/${moduleName.value}/tree/$sha">$v</a>"""
            }
        }
      }
    )
  ) ++ Seq[Setting[_]](
    dependencyDotFile := baseDirectory.value / "target" / "dependencies.dot",

    dependencySvgFile := baseDirectory.value / "target" / "dependencies.svg",

    dumpLicenseReport := {
      val dotFile = dependencyDot.all(filter).value
      val ok = Process(command = "dot", arguments = Seq[String]("-Tsvg", dotFile.head.getAbsolutePath, "-o" + dependencySvgFile.value.getAbsolutePath)).!
      require(0 == ok, "dot2svg failed: $ok")
      dumpLicenseReport.value
    },

    makeSite := {
      val _ = dumpLicenseReport.value
      makeSite.value
    },

      siteMappings := {
      val _ = dumpLicenseReport.value
      siteMappings.value
    },

    siteMappings += (licenseReportDir.value / "LicenseReportOfAggregatedSBTPluginsAndLibraries.html") -> "LicenseReportOfAggregatedSBTPluginsAndLibraries.html",
    siteMappings += dependencySvgFile.value -> "dependencies.svg",

    previewFixedPort := Some(4004),

    previewLaunchBrowser := false
  )

val Npm = config("npm")

pomIncludeRepository in Global := { _ => false }

publishTo in Global := Some(
  "JPL-IMCE" at
    s"https://api.bintray.com/content/jpl-imce/gov.nasa.jpl.imce/${Settings.dashName}/${version.value}")

resolvers in Global += Resolver.bintrayRepo(Settings.organizationName.toLowerCase, Settings.organization)

resolvers in Global += "Artima Maven Repository" at "http://repo.artima.com/releases"

lazy val tablesRoot = project.in(file("."))
  .aggregate(tablesJS, tablesJVM)
  .settings(publishArtifact := false)

// a special crossProject for configuring a JS/JVM/shared structure
lazy val tables = crossProject
  .in(file("."))
  .enablePlugins(IMCEGitPlugin)
  .enablePlugins(GhpagesPlugin)
  .settings(
    IMCEKeys.licenseYearOrRange := "2016",
    IMCEKeys.organizationInfo :=
      OrganizationInfo(
        Settings.organization,
        Settings.organizationName,
        Some(url(s"https://github.com/${Settings.organizationName}"))),
    IMCEKeys.targetJDK := IMCEKeys.jdk18.value
  )
  .settings(tablesLicenseSettings : _*)
  .settings(tablesPublishSettings : _*)
  .settings(tablesGhPagesSettings : _*)
  .settings(
    name := Settings.name,

    // https://github.com/typesafehub/sbteclipse/wiki/Using-sbteclipse#useprojectid
    EclipseKeys.useProjectId := true,

    // https://github.com/typesafehub/sbteclipse/wiki/Using-sbteclipse#skipparents
    EclipseKeys.skipParents in ThisBuild := false,

    // https://github.com/typesafehub/sbteclipse/wiki/Using-sbteclipse#withsource
    EclipseKeys.withSource := true,

    // https://github.com/typesafehub/sbteclipse/wiki/Using-sbteclipse#relativizelibs
    EclipseKeys.relativizeLibs := true,

    // https://github.com/typesafehub/sbteclipse/wiki/Using-sbteclipse#executionenvironment
    EclipseKeys.executionEnvironment := Some(EclipseExecutionEnvironment.JavaSE18),

    // https://github.com/typesafehub/sbteclipse/wiki/Using-sbteclipse#withbundledscalacontainers
    EclipseKeys.withBundledScalaContainers := true,

    git.baseVersion := Settings.version,
    scalaVersion := Settings.versions.scala,
    scalacOptions ++= Settings.scalacOptions,

    // do not include all repositories in the POM
    // (this is important for staging since artifacts published to a staging repository
    //  can be promoted (i.e. published) to another repository)
    pomAllRepositories := false,

    // make sure no repositories show up in the POM file
    pomIncludeRepository := { _ => false },

    // include *.zip artifacts in the POM dependency section
    makePomConfiguration :=
      makePomConfiguration.value.copy(includeTypes = Set(Artifact.DefaultType, Artifact.PomType, "zip")),

    publishTo := Some(
      "JPL-IMCE" at
        s"https://api.bintray.com/content/jpl-imce/gov.nasa.jpl.imce/${Settings.dashName}/${version.value}"),


    scalacOptions in (Compile, compile) += s"-P:artima-supersafe:config-file:${baseDirectory.value}/project/supersafe.cfg",
    scalacOptions in (Test, compile) += s"-P:artima-supersafe:config-file:${baseDirectory.value}/project/supersafe.cfg",
    scalacOptions in (Compile, doc) += "-Xplugin-disable:artima-supersafe",
    scalacOptions in (Test, doc) += "-Xplugin-disable:artima-supersafe",
    libraryDependencies ++= Settings.sharedDependencies.value,

    // Avoid unresolvable dependencies from old versions of log4j
    libraryDependencies ~= {
      _ map {
        case m if m.organization == "log4j" =>
          m
            .exclude("javax.jms", "jms")
            .exclude("com.sun.jmx", "jmxri")
            .exclude("com.sun.jdmk", "jmxtools")
        case m => m
      }
    }
  )
  .jvmConfigure(_ enablePlugins HeaderPlugin)
  .jvmConfigure(_ enablePlugins PreprocessPlugin)
  .jvmConfigure(_ enablePlugins SiteScaladocPlugin)
  .jvmSettings(
    inConfig(Preprocess)(
      sourceDirectory := baseDirectory.value / ".." / "shared" / "src" / "site-preprocess"
    ) : _*
  )
  .jvmSettings(
    resolvers += Resolver.bintrayRepo("jpl-imce", "gov.nasa.jpl.imce"),
    libraryDependencies ++= Settings.jvmDependencies.value,
    dynamicScriptsResourceSettings(Settings.dashName),

    scalacOptions in (Compile,doc) ++= Seq(
      "-diagrams",
      "-doc-title", name.value,
      "-doc-root-content", baseDirectory.value + "/rootdoc.txt"),

    apiURL := Some(url("https://jpl-imce.github.io/gov.nasa.jpl.imce.oml.tables/latest/api/index.html")),
    dependencyOverrides += "com.fasterxml.jackson.module" % "jackson-module-paranamer" % Settings.versions.spark_jackson % "compile",
    dependencyOverrides += "com.fasterxml.jackson.module" %% "jackson-module-scala" % Settings.versions.spark_jackson % "compile"

  )
  // set up settings specific to the JS project
  .jsConfigure(_ enablePlugins HeaderPlugin)
  .jsConfigure(_ enablePlugins PreprocessPlugin)
  .jsConfigure(_ enablePlugins SiteScaladocPlugin)
  .jsConfigure(_ enablePlugins ScalaJSPlugin)
  .jsSettings(
    inConfig(Npm)(
      PreprocessPlugin.preprocessSettings(Npm) ++
        Seq(
          preprocessVars := Map(
            "HYPHENATED_NAME" -> Settings.dashName,
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
          sourceDirectory := baseDirectory.value / ".." / "shared" / "src" / "npm-preprocess",
          preprocessIncludeFilter := "*.json",
          includeFilter := AllPassFilter,
          target := baseDirectory.value / ".."
        )
    ) : _*)
  .jsSettings(
    inConfig(Preprocess)(
      sourceDirectory := baseDirectory.value / ".." / "shared" / "src" / "site-preprocess"
    ) : _*
  )
  .jsSettings(

    // For easier debugging, disable JavaScript optimizations
    // scalaJSLinkerConfig ~= { _.withOptimizer(false) },

    // Disable if debugging.
    scalaJSStage in Global := FullOptStage,

    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv,

    libraryDependencies ++= Settings.scalajsDependencies.value,
    //scalaJSOutputMode := ECMAScript51Global,
    //scalaJSOutputMode := ECMAScript51Isolated,
    scalaJSOutputMode := ECMAScript2015,

    crossTarget in (Compile, fastOptJS) := baseDirectory.value / "..",
    crossTarget in (Compile, fullOptJS) := baseDirectory.value / "..",

    artifactPath in (Compile, fastOptJS) :=
      (crossTarget in (Compile, fastOptJS)).value / s"${Settings.dashName}.js",
    artifactPath in (Compile, fullOptJS) :=
      (crossTarget in (Compile, fullOptJS)).value / s"${Settings.dashName}.js",

    fastOptJS in Compile := {
      val result = (fastOptJS in Compile).value
      val p = (preprocess in config("npm")).value
      streams.value.log.info("npm/preprocess: "+p)
      val ok = Process(command=Seq[String]("npm", "pack"), cwd = baseDirectory.value / ".." ).!
      require(0 == ok, "npm pack failed: " + ok)
      result
    },

    fullOptJS in Compile := {
      val result = (fullOptJS in Compile).value
      val p = (preprocess in config("npm")).value
      streams.value.log.info("npm/preprocess: "+p)
      val ok = Process(command=Seq[String]("npm", "pack"), cwd = baseDirectory.value / ".." ).!
      require(0 == ok, "npm pack failed: " + ok)
      result
    }
  )

lazy val tablesJVM = tables.jvm
lazy val tablesJS = tables.js

def dynamicScriptsResourceSettings(projectName: String): Seq[Setting[_]] = {

  import com.typesafe.sbt.packager.universal.UniversalPlugin.autoImport._

  def addIfExists(f: File, name: String): Seq[(File, String)] =
    if (!f.exists) Seq()
    else Seq((f, name))

  val QUALIFIED_NAME = "^[a-zA-Z][\\w_]*(\\.[a-zA-Z][\\w_]*)*$".r

  Seq(
    // the '*-resource.zip' archive will start from: 'dynamicScripts/<dynamicScriptsProjectName>'
    com.typesafe.sbt.packager.Keys.topLevelDirectory in Universal := None,

    // name the '*-resource.zip' in the same way as other artifacts
    com.typesafe.sbt.packager.Keys.packageName in Universal :=
      // normalizedName.value + "_" + scalaBinaryVersion.value + "-" + version.value + "-resource",
      projectName + "_" + scalaBinaryVersion.value + "-" + version.value + "-resource",

    // contents of the '*-resource.zip' to be produced by 'universal:packageBin'
    mappings in Universal in packageBin ++= {
      val dir = baseDirectory.value
      val bin = (packageBin in Compile).value
      val src = (packageSrc in Compile).value
      val doc = (packageDoc in Compile).value
      val binT = (packageBin in Test).value
      val srcT = (packageSrc in Test).value
      val docT = (packageDoc in Test).value

      (dir * ".classpath").pair(rebase(dir, projectName)) ++
        (dir * "*.md").pair(rebase(dir, projectName)) ++
        (dir / "resources" ***).pair(rebase(dir, projectName)) ++
        addIfExists(bin, projectName + "/lib/" + bin.name) ++
        addIfExists(binT, projectName + "/lib/" + binT.name) ++
        addIfExists(src, projectName + "/lib.sources/" + src.name) ++
        addIfExists(srcT, projectName + "/lib.sources/" + srcT.name) ++
        addIfExists(doc, projectName + "/lib.javadoc/" + doc.name) ++
        addIfExists(docT, projectName + "/lib.javadoc/" + docT.name)
    },

    artifacts += {
      val n = projectName // (name in Universal).value
      Artifact(n, "zip", "zip", Some("resource"), Seq(), None, Map())
    },
    packagedArtifacts += {
      val p = (packageBin in Universal).value
      val n = projectName // (name in Universal).value
      Artifact(n, "zip", "zip", Some("resource"), Seq(), None, Map()) -> p
    }
  )
}

