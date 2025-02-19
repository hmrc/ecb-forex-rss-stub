import play.core.PlayVersion
import play.sbt.PlayImport._
import sbt.Keys.libraryDependencies
import sbt.*

object AppDependencies {

  val compile = Seq(
    "uk.gov.hmrc"             %% "bootstrap-backend-play-30"  % "9.9.0"
  )

  val test = Seq(
    "uk.gov.hmrc"             %% "bootstrap-test-play-30"     % "9.9.0",
    "com.vladsch.flexmark"    %  "flexmark-all"               % "0.64.8",
    "org.scalatest"           %% "scalatest"                  % "3.2.19"
  ).map(_ % "test, it")
}
