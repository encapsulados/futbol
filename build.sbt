import sbtassembly.AssemblyPlugin.defaultUniversalScript

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.4"

ThisBuild / assemblyPrependShellScript := Some(defaultUniversalScript(shebang = false))

lazy val root = (project in file("."))
  .settings(
    name := "futbol",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.18" % Test
    ),
  )
