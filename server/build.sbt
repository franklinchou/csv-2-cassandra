name := """csv-2-cassandra"""

organization := "com.fmc"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

routesGenerator := InjectedRoutesGenerator

scalaVersion := "2.12.6"

val janusVersion = "0.2.2"

libraryDependencies ++=
  Seq(
    guice,
    "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
    "org.scalatest" % "scalatest_2.12" % "3.0.3",
    "org.mockito" % "mockito-core" % "2.7.22",
    "com.chuusai" %% "shapeless" % "2.3.3",
    "org.janusgraph" % "janusgraph-core" % janusVersion,
    "org.janusgraph" % "janusgraph-cql" % janusVersion,
  )

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.fmc.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.fmc.binders._"


// Set resource directory for tests to run
// See here https://www.scala-sbt.org/1.0/docs/Howto-Customizing-Paths.html
resourceDirectory in Test := baseDirectory.value / "resources"
