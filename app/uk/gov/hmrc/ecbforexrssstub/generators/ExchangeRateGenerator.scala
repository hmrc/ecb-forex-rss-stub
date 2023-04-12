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

package uk.gov.hmrc.ecbforexrssstub.generators

import uk.gov.hmrc.ecbforexrssstub.models.ExchangeRate

import java.time.format.DateTimeFormatter
import java.time.{DayOfWeek, LocalDateTime}
import scala.annotation.tailrec
import scala.util.Random

object ExchangeRateGenerator {

  private final val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

  def getExchangeRates(length: Int, baseRate: Double, range: Double): Seq[ExchangeRate] =
    getExchangeRates(length, baseRate, range, LocalDateTime.now())

  def getExchangeRates(length: Int, baseRate: Double, range: Double, endDate: LocalDateTime): Seq[ExchangeRate] = {

    val random = new Random(42)

    def randomRate = baseRate + ((random.nextDouble() - 0.5) * range)

    val dates = getWeekdayDates(length, endDate, Seq.empty)
      .map(day => formatter.format(day))
      .reverse

    val rates = Seq.fill(length)(randomRate)
      .map(rate => BigDecimal(rate).setScale(5, BigDecimal.RoundingMode.HALF_UP).toString)

    dates.zip(rates).map(dateAndRate => ExchangeRate(date = dateAndRate._1, rate = dateAndRate._2))

  }

  private[generators] def isWeekday(localDateTime: LocalDateTime): Boolean = {
    val weekdays = Seq(
      DayOfWeek.MONDAY,
      DayOfWeek.TUESDAY,
      DayOfWeek.WEDNESDAY,
      DayOfWeek.THURSDAY,
      DayOfWeek.FRIDAY
    )
    weekdays.contains(localDateTime.getDayOfWeek)
  }

  @tailrec
  private def getWeekdayDates(length: Int, currentDate: LocalDateTime, dates: Seq[LocalDateTime]): Seq[LocalDateTime] = {
    if(dates.length == length)
      dates
    else if(isWeekday(currentDate))
      getWeekdayDates(length, currentDate.minusDays(1), dates :+ currentDate)
    else
      getWeekdayDates(length, currentDate.minusDays(1), dates)
  }
}
