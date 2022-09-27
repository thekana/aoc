ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name                 := "scala",
    idePackagePrefix     := Some("com.github.thekana.aoc"),
    assembly / mainClass := Some("com.github.thekana.aoc.Couchbase"),
    assemblyShadeRules in assembly := Seq(
      ShadeRule
        .rename("com.couchbase.client.**" -> "shaded.com.couchbase.client.@1")
        .inLibrary("com.couchbase.client" % "scala-client" % "1.3.4")
        .inAll
    )
  )

enablePlugins(ShadingPlugin)

libraryDependencies += "org.scalatest"        %% "scalatest"    % "3.2.12" % "test"
libraryDependencies += "com.couchbase.client" %% "scala-client" % "1.3.4"

shadedModules += "com.couchbase.client" %% "core"
shadingRules += ShadingRule.moveUnder("core-io", "test.shaded")
//validNamespaces += "test"

//assemblyShadeRules in assembly ++= Seq(
//  ShadeRule
//    .rename("com.couchbase.client.**" -> "shaded.com.couchbase.client.@0")
//    .inLibrary("com.couchbase.client" % "scala-client" % "1.3.4")
//    .inProject
//)

crossTarget := baseDirectory.value / "myDirectory"
//publishTo   := Some(MavenCache("local-maven", file("path/to/maven-repo/releases")))
