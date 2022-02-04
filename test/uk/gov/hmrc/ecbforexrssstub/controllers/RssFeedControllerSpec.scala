/*
 * Copyright 2022 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ecbforexrssstub.controllers

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import play.api.Application
import play.api.http.Status
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.Helpers._
import play.api.test.{FakeRequest, Helpers}
import uk.gov.hmrc.ecbforexrssstub.config.AppConfig

class RssFeedControllerSpec extends AnyWordSpec with Matchers {

  private val fakeRequest = FakeRequest("GET", "/")

  private val application: Application = new GuiceApplicationBuilder()
    .configure(("settings.length", 5),
              ("settings.baseRate", 0.9),
              ("settings.range", 0.1)).build()

  private val appConfig: AppConfig = application.injector.instanceOf[AppConfig]

  private val controller = new RssFeedController(Helpers.stubControllerComponents(), appConfig)

  val xmlDeclaration = "<?xml version='1.0' encoding='utf-8'?>"

  "GET /rss" should {

    val result = controller.get()(fakeRequest)

    "return 200" in {
      status(result) shouldBe Status.OK
    }

    "have XML content type" in {
      contentType(result) shouldBe Some("application/xml")
    }

    "contain XML content" in {
      contentAsString(result).startsWith(xmlDeclaration) shouldBe true
    }
  }
}
