
sbtPlugin := false

description := "JPL's Ontological Modeling Framework (OMF) Schema Tables for Integrated Model-Centric Engineering (IMCE)."

moduleName := Settings.name

organization in ThisBuild := Settings.organization

homepage in ThisBuild := Some(url(s"https://jpl-imce.github.io/${moduleName.value}"))

organizationName := Settings.organizationName

organizationHomepage := Some(url(s"https://github.com/${organizationName.value}"))

git.remoteRepo in ThisBuild := s"git@github.com:${organizationName.value}/${moduleName.value}"

startYear := Some(2016)

scmInfo in ThisBuild := Some(ScmInfo(
  browseUrl = url(s"https://github.com/${organizationName.value}/${moduleName.value}"),
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

