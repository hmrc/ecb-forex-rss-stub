import play.core.PlayVersion
import play.sbt.PlayImport._
import sbt.Keys.libraryDependencies
import sbt._

object AppDependencies {

  val compile = Seq(
    "uk.gov.hmrc"             %% "bootstrap-backend-play-28"  % "5.20.0"
  )

  val test = Seq(
    "uk.gov.hmrc"             %% "bootstrap-test-play-28"     % "5.20.0",
    "com.vladsch.flexmark"    %  "flexmark-all"               % "0.62.2",
    "org.scalatest"           %% "scalatest"               % "3.2.10"
  ).map(_ % "test, it")
}
