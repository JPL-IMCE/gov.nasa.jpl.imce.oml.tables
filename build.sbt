import org.scalajs.core.tools.linker.backend.OutputMode.ECMAScript6

import com.typesafe.sbt.license.{LicenseInfo, DepModuleInfo}

import de.heikoseeberger.sbtheader.HeaderPlugin
import de.heikoseeberger.sbtheader.HeaderPattern
import de.heikoseeberger.sbtheader.license.CommentBlock

import com.typesafe.sbt.SbtGhPages._

val oti_mof_schema_license =
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

scalaVersion in ThisBuild := "2.11.8"

shellPrompt in ThisBuild := { state => Project.extract(state).currentRef.project + "> " }

val tablesLicenseSettings: Seq[Setting[_]] =
  Seq(
    licenses += "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html"),

    startYear  := Some(2016),

    headers := Map(
      "java" -> (HeaderPattern.cStyleBlockComment, CommentBlock.cStyle(oti_mof_schema_license)),
      "js" -> (HeaderPattern.cStyleBlockComment, CommentBlock.cStyle(oti_mof_schema_license)),
      "scala" -> (HeaderPattern.cStyleBlockComment, CommentBlock.cStyle(oti_mof_schema_license))

    ),

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

  // include *.pom as an artifact
  publishMavenStyle := true,

  // do not include all repositories in the POM
  pomAllRepositories := false,

  // make sure no repositories show up in the POM file
  pomIncludeRepository := { _ => false },

  additionalProperties := {
    <git.branch>
      {git.gitCurrentBranch.value}
    </git.branch>
      <git.commit>
        {git.gitHeadCommit.value.getOrElse("N/A") + (if (git.gitUncommittedChanges.value) "-SNAPSHOT" else "")}
      </git.commit>
      <git.tags>
        {git.gitCurrentTags.value.map(tag => <git.tag>$tag</git.tag> )}
      </git.tags>
  },

  pomPostProcess <<= additionalProperties { (additions) =>
    new xml.transform.RuleTransformer(new xml.transform.RewriteRule {
      override def transform(n: xml.Node): Seq[xml.Node] =
        n match {
          case <properties>{props @ _*}</properties> =>
            <properties>{props}{additions}</properties>
          case _ =>
            n
        }
    })
  },

  git.baseVersion := Settings.version,

  git.useGitDescribe := true
) ++ versionWithGit

lazy val filter: ScopeFilter = ScopeFilter(inProjects(ThisProject), inConfigurations(Compile))

lazy val dependencySvgFile = settingKey[File]("Location of the dependency graph in SVG format")

val tablesGhPagesSettings: Seq[Setting[_]] =
  Seq(
    preprocessVars in Preprocess := Map(
      "CI" -> "https://travis-ci.org/JPL-IMCE/jpl.omf.schema.tables",
      "GIT" -> "github.com",
      "REPO" -> "jpl.omf.schema.tables",
      "VER" -> version.value,
      "ORG" -> "JPL-IMCE",
      "SUBJECT" -> "JPL-IMCE",
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
    ),


    sourceDirectory in preprocess := baseDirectory.value / ".." / "tables" / "src" / "site-preprocess",

    target in preprocess := (target in makeSite).value
  ) ++ ghpages.settings ++ Seq(

    dependencyDotFile := baseDirectory.value / "target" / "dependencies.dot",

    dependencySvgFile := baseDirectory.value / "target" / "dependencies.svg",

    dumpLicenseReport := {
      val dotFile = dependencyDot.all(filter).value
      val ok = Process(command = "dot", arguments = Seq[String]("-Tsvg", dotFile.head.getAbsolutePath, "-o" + dependencySvgFile.value.getAbsolutePath)).!
      require(0 == ok, "dot2svg failed: $ok")
      dumpLicenseReport.value
    },

    makeSite <<= makeSite.dependsOn(dumpLicenseReport),

    siteMappings <<= siteMappings.dependsOn(dumpLicenseReport),

    siteMappings += (licenseReportDir.value / "LicenseReportOfAggregatedSBTPluginsAndLibraries.html") -> "LicenseReportOfAggregatedSBTPluginsAndLibraries.html",
    siteMappings += dependencySvgFile.value -> "dependencies.svg",

    previewFixedPort := Some(4004),

    previewLaunchBrowser := false,

    releasePublishArtifactsAction := {
      val _ = GhPagesKeys.pushSite.value
      releasePublishArtifactsAction.value
    }
  )

val Npm = config("npm")

lazy val tablesRoot = project.in(file("."))
  .aggregate(tablesJS, tablesJVM)
  .settings(publishArtifact := false)

// a special crossProject for configuring a JS/JVM/shared structure
lazy val tables = crossProject
  .in(file("."))
  .settings(tablesLicenseSettings : _*)
  .settings(tablesPublishSettings : _*)
  .settings(tablesGhPagesSettings : _*)
  .settings(
    name := Settings.name,
    git.baseVersion := Settings.version,
    scalaVersion := Settings.versions.scala,
    scalacOptions ++= Settings.scalacOptions,
    libraryDependencies ++= Settings.sharedDependencies.value,
    publishTo := Some(
      "JPL-IMCE" at
        s"https://api.bintray.com/content/jpl-imce/gov.nasa.jpl.imce/jpl.omf.schema.tables/${version.value}")
  )
  .jvmConfigure(_ enablePlugins HeaderPlugin)
  .jvmConfigure(_ enablePlugins PreprocessPlugin)
  .jvmConfigure(_ enablePlugins SiteScaladocPlugin)
  .jvmSettings(
    libraryDependencies ++= Settings.jvmDependencies.value
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
          sourceDirectory := baseDirectory.value / ".." / "shared" / "src" / "npm-preprocess",
          preprocessIncludeFilter := "*.json",
          includeFilter := AllPassFilter,
          target := baseDirectory.value / ".."
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

    crossTarget in (Compile, fastOptJS) := baseDirectory.value / "..",
    crossTarget in (Compile, fullOptJS) := baseDirectory.value / "..",

    artifactPath in (Compile, fastOptJS) :=
      (crossTarget in (Compile, fastOptJS)).value / "jpl-omf-schema-tables.js",
    artifactPath in (Compile, fullOptJS) :=
      (crossTarget in (Compile, fullOptJS)).value / "jpl-omf-schema-tables.js",

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
