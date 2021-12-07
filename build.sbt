name := """SOEN-6441-Gitterific-Group-KP-G08"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.6"

libraryDependencies ++= Seq(guice,javaWs,caffeine)
libraryDependencies += "org.mockito" % "mockito-core" % "2.22.0" % "test"
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.6.14" % Test
// Testing libraries for dealing with CompletionStage...
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test

javaOptions ++= Seq("-Djdk.lang.Process.allowAmbiguousCommands=true")





