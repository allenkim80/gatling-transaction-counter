import io.gatling.sbt.GatlingPlugin

val scala_version   = "2.11.8"
val gatlingVersion  = "2.2.5"

lazy val root = (project in file("."))
  .enablePlugins(GatlingPlugin)
  .settings(
    name := "gatling-transaction-counter",
    version := (version in ThisBuild).value,
    organization := "com.github.allenkim",
    scalaVersion := scala_version,
    libraryDependencies ++= {
      Seq(
        "org.scala-lang"      % "scala-library"              % scala_version,
        "org.scalatest"       %% "scalatest"                  % "3.0.5"         % "test",

        "io.gatling"          % "gatling-core"                % gatlingVersion,
        "io.gatling"          % "gatling-test-framework"      % gatlingVersion  % "test"
      )
    }
  )

publishArtifact in Test := false

publishArtifact in packageBin := true

publishMavenStyle := true

//publishTo := {
//  val nexus = "http://apseo-nexus"
//  if (isSnapshot.value)
//    Some("snapshots" at nexus + "/repository/maven-snapshots")
//  else
//    Some("releases"  at nexus + "/repository/maven-releases")
//}
//
//credentials += Credentials( "Sonatype Nexus Repository Manager", "apseo-nexus", "", "")

pomIncludeRepository := { _ => false }

pomExtra := <url>http://github.com/allenkim80/gatling-transaction-counter</url>
  <licenses>
    <license>
      <name>The MIT License (MIT)</name>
      <url>https://opensource.org/licenses/MIT</url>
    </license>
  </licenses>
  <scm>
    <url>http://github.com/allenkim80/gatling-transaction-counter.git</url>
    <connection>scm:git:http://github.com/allenkim80/gatling-transaction-counter.git</connection>
  </scm>
  <developers>
    <developer>
      <id>allenkim80</id>
      <name>Allen Kim</name>
    </developer>
  </developers>