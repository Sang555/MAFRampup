name := "TypeClasssesEx"

version := "0.1"

scalaVersion := "2.13.2"


libraryDependencies += "org.typelevel" %% "cats-effect" % "2.1.1" withSources() withJavadoc()
scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-language:postfixOps",
  "-language:higherKinds"
)