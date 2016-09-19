
sbtPlugin := false

description := "JPL's Ontological Modeling Framework (OMF) Schema Tables for Integrated Model-Centric Engineering (IMCE)."

moduleName := Settings.name

organization in ThisBuild := Settings.organization

homepage in ThisBuild := Some(url(s"https://jpl-imce.github.io/${moduleName.value}"))

organizationName := "JPL-IMCE"

organizationHomepage := Some(url(s"https://github.com/JPL-IMCE"))

git.remoteRepo in ThisBuild := s"git@github.com:JPL-IMCE/${moduleName.value}"

startYear := Some(2016)

scmInfo in ThisBuild := Some(ScmInfo(
  browseUrl = url(s"https://github.com/JPL-IMCE/${moduleName.value}"),
  connection = "scm:"+git.remoteRepo.value))

developers in ThisBuild := List(
  Developer(
    id="NicolasRouquette",
    name="Nicolas F. Rouquette",
    email="nicolas.f.rouquette@jpl.nasa.gov",
    url=url("https://github.com/NicolasRouquette")),
  Developer(
    id="TylerRyan",
    name="Tyler J. Ryan",
    email="tyler.j.ryan@jpl.nasa.gov",
    url=url("https://github.com/TylerJRyan"))
  )

