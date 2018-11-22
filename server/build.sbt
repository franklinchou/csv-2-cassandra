name := """csv-2-cassandra"""

organization := "com.fmc"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

libraryDependencies ++=
  Seq(
    guice,
    "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
    "org.scalatest" % "scalatest_2.12" % "3.0.3",
    "org.mockito" % "mockito-core" % "2.7.22"
  )

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.fmc.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.fmc.binders._"
