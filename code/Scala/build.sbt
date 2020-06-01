import Dependencies._

ThisBuild / scalaVersion     := "2.13.2"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "net.sigusr"
ThisBuild / organizationName := "Sigusr"

lazy val root = (project in file("."))
  .settings(
    name := "Tagless Final",
    libraryDependencies += specs2 % Test,
    scalacOptions ++= Seq(
      "-Xfatal-warnings",
      "-Yrangepos",
      "-deprecation",
      "-feature",
      "-unchecked",
      "-language:implicitConversions",
      "-language:higherKinds",
      "-language:existentials",
      "-language:postfixOps"
    )
  )

libraryDependencies += {
  val version = scalaBinaryVersion.value match {
    case "2.10" => "1.0.3"
    case _ ⇒ "2.1.4"
  }
  "com.lihaoyi" % "ammonite" % version % "test" cross CrossVersion.full
}

sourceGenerators in Test += Def.task {
  val file = (sourceManaged in Test).value / "amm.scala"
  IO.write(file, """object amm extends App { ammonite.Main.main(args) }""")
  Seq(file)
}.taskValue