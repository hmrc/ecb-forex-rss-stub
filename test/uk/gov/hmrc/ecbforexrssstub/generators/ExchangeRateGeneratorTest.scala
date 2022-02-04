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

package uk.gov.hmrc.ecbforexrssstub.generators

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ExchangeRateGeneratorTest extends AnyWordSpec with Matchers {

  private final val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

  "ExchangeRateGenerator" should {

    val initialRate = 0.85
    val range = 0.1

    "generate correct number of exchange rates" in {
      val exchangeRates = ExchangeRateGenerator.getExchangeRates(5, initialRate, range)
      exchangeRates.length mustEqual 5
    }

    "return empty list for length 0" in {
      val exchangeRates = ExchangeRateGenerator.getExchangeRates(0, initialRate, range)
      exchangeRates.isEmpty mustEqual true
    }

    "generate exchange rates within specified range" in {

      val exchangeRates = ExchangeRateGenerator.getExchangeRates(100, initialRate, range)

      exchangeRates.foreach(rate => {
        val r = BigDecimal(rate.rate)
        val upperLimit = BigDecimal(initialRate + (range / 2))
        val lowerLimit = BigDecimal(initialRate - (range / 2))
        r <= upperLimit mustEqual true
        r >= lowerLimit mustEqual true
      })
    }

    "return identical values when range is 0" in {

      val exchangeRates = ExchangeRateGenerator.getExchangeRates(5, initialRate, 0)

      exchangeRates.foreach(rate => {
        rate.rate.toDouble mustEqual initialRate
      })
    }

    "contain incrementing dates ending with the end date" in {

      val exchangeRates = ExchangeRateGenerator.getExchangeRates(5, initialRate, range, LocalDateTime.now())

      val dates = exchangeRates.map(exchangeRate => exchangeRate.date + "T00:00:00").map(LocalDateTime.parse)

      Seq.range(0, 4).foreach { index =>
        dates(index).isBefore(dates(index + 1)) mustBe true
      }

      if(ExchangeRateGenerator.isWeekday(LocalDateTime.now())) {
        exchangeRates.last.date mustEqual formatter.format(LocalDateTime.now())
      }
    }

    "calculate weekdays correctly in " in {
      ExchangeRateGenerator.isWeekday(LocalDateTime.of(2022, 2, 4, 0, 0)) mustBe true
      ExchangeRateGenerator.isWeekday(LocalDateTime.of(2022, 2, 5, 0, 0)) mustBe false
      ExchangeRateGenerator.isWeekday(LocalDateTime.of(2022, 2, 6, 0, 0)) mustBe false
      ExchangeRateGenerator.isWeekday(LocalDateTime.of(2022, 2, 7, 0, 0)) mustBe true
    }

    "not include weekends in exchange rates" in {

      val exchangeRates = ExchangeRateGenerator.getExchangeRates(100, initialRate, range, LocalDateTime.now())

      val dates = exchangeRates.map(exchangeRate => exchangeRate.date + "T00:00:00").map(LocalDateTime.parse)

      dates.forall(date => ExchangeRateGenerator.isWeekday(date)) mustBe true
    }
  }
}
