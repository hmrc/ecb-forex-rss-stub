/*
 * Copyright 2023 HM Revenue & Customs
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

import play.api.mvc.{Action, AnyContent, ControllerComponents}
import uk.gov.hmrc.ecbforexrssstub.config.AppConfig
import uk.gov.hmrc.ecbforexrssstub.generators.ExchangeRateGenerator
import uk.gov.hmrc.ecbforexrssstub.models.ForexRss
import uk.gov.hmrc.play.bootstrap.backend.controller.BackendController

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future

@Singleton()
class RssFeedController @Inject()(cc: ControllerComponents, appConfig: AppConfig)
    extends BackendController(cc) {

  def get(): Action[AnyContent] = Action.async {
    val rates = ExchangeRateGenerator.getExchangeRates(appConfig.length, appConfig.baseRate, appConfig.range)

    Future.successful(Ok(ForexRss.of(rates)).as("application/xml"))
  }
}
