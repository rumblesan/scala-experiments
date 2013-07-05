name := "Scala Experiments"

organization := "com.rumblesan"

version := "0.1.0"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "2.0" % "test"
)

initialCommands := "import com.rumblesan.scalaexperiments._"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-language:_")

resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                  "releases"  at "http://oss.sonatype.org/content/repositories/releases")
