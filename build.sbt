name := "Scala Experiments"

organization := "com.rumblesan"

version := "0.1.0"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "2.1-SNAPSHOT" % "test"
)

initialCommands := "import com.rumblesan.scalaexperiments._"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-language:_")
