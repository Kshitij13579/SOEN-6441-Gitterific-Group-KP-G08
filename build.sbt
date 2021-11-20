name := """SOEN-6441-Gitterific-Group-KP-G08"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.6"

libraryDependencies ++= Seq(guice,javaWs,caffeine)
libraryDependencies += "org.mockito" % "mockito-core" % "2.10.0" % "test"


