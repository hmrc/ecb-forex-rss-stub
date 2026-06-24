import play.sbt.routes.RoutesKeys
import uk.gov.hmrc.DefaultBuildSettings
import uk.gov.hmrc.DefaultBuildSettings.integrationTestSettings

val appName = "ecb-forex-rss-stub"

lazy val microservice = Project(appName, file("."))
  .enablePlugins(play.sbt.PlayScala, SbtDistributablesPlugin)
  .settings(
    majorVersion                     := 0,
    scalaVersion                     := "3.3.6",
    libraryDependencies              ++= AppDependencies.compile ++ AppDependencies.test,
  )
  .configs(IntegrationTest)
  .settings(inConfig(IntegrationTest)(itSettings) *)
  .configs(Test)
  .settings(inConfig(Test)(testSettings) *)
  .settings(PlayKeys.playDefaultPort := 10198)
  .settings(scalacOptions ++= Seq("-Wconf:msg=Flag.*repeatedly:s", "-Wconf:src=routes/.*:s"))

lazy val itSettings = Defaults.itSettings ++ Seq(
  unmanagedSourceDirectories := Seq(
    baseDirectory.value / "it",
    baseDirectory.value / "test" / "generators"
  ),
  unmanagedResourceDirectories := Seq(
    baseDirectory.value / "it" / "resources"
  ),
  parallelExecution := false,
  fork := true
)

lazy val testSettings = Defaults.testSettings ++ Seq(
  unmanagedSourceDirectories := Seq(
    baseDirectory.value / "test"
  ),
  parallelExecution := false,
  fork := true
)
