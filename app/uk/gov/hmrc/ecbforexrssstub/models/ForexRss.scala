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

package uk.gov.hmrc.ecbforexrssstub.models

import java.io.StringWriter
import scala.xml.{Elem, XML}

object ForexRss {

  /**
   * Returns XML in the format of https://www.ecb.europa.eu/rss/fxref-gbp.html with the provided ExchangeRate data
   * @param datesAndRates ExchangeRate data, each containing a date and the associated exchange rate
   * @return
   */
  def of(datesAndRates: Seq[ExchangeRate]): String = {

    // construct root RDF node

    val reversedDatesAndRates = datesAndRates.reverse

    val rdfNode = <rdf:RDF xmlns = "http://purl.org/rss/1.0/" xmlns:cb = "http://www.cbwiki.net/wiki/index.php/Specification_1.1" xmlns:dc = "http://purl.org/dc/elements/1.1/" xmlns:dcterms = "http://purl.org/dc/terms/" xmlns:rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation = "http://www.w3.org/1999/02/22-rdf-syntax-ns# rdf.xsd" >
      {toChannel(reversedDatesAndRates)}
      {reversedDatesAndRates.map(exchangeRate => toItem(exchangeRate.rate, exchangeRate.date))}
    </rdf:RDF>

    // format spacing correctly

    val formatted =  new scala.xml.PrettyPrinter(width = 1000, step = 2, minimizeEmpty = false).format(rdfNode)

    // add XML declaration

    val writer : StringWriter = new StringWriter()
    XML.write(writer, XML.loadString(formatted), "utf-8", true, null)
    writer.flush()
    writer.toString

  }

  private def toChannel(datesAndRates: Seq[ExchangeRate]): Elem = {

    val items = datesAndRates
      .map(exchangeRate => <rdf:li rdf:resource={ s"http://www.ecb.europa.eu/stats/exchange/eurofxref/html/eurofxref-graph-gbp.en.html?date=${exchangeRate.date}&rate=${exchangeRate.rate}" }/>)

    <channel  rdf:about = "http://www.ecb.europa.eu/rss/gbp.html">
      <title>ECB | Pound sterling (GBP) - Euro foreign exchange reference rates</title>
      <link>http://www.ecb.europa.eu/home/html/rss.en.html</link>
      <description>The reference rates are based on the regular daily concertation procedure between central banks within and outside the European System of Central Banks, which normally takes place at 2.15 p.m. (14:15) ECB time.</description>
      <items>
        <rdf:Seq>
          {items}
        </rdf:Seq>
      </items>
      <dc:publisher>European Central Bank</dc:publisher>
      <dcterms:license>http://www.ecb.europa.eu/home/html/disclaimer.en.html</dcterms:license>
    </channel>
  }

  private def toItem(gbp: String, date: String): Elem = {

    val about = s"http://www.ecb.europa.eu/stats/exchange/eurofxref/html/eurofxref-graph-gbp.en.html?date=${date}&rate=${gbp}"

    <item rdf:about={about}>
      <title xml:lang="en">{gbp} GBP = 1 EUR {date} ECB Reference rate</title>
      <link>http://www.ecb.europa.eu/stats/exchange/eurofxref/html/eurofxref-graph-gbp.en.html?date={date}&amp;rate={gbp}</link>
      <description xml:lang="en">1 EUR buys {gbp} Pound sterling (GBP) - The reference exchange rates are published both by electronic market information providers and on the ECB's website shortly after the concertation procedure has been completed. Reference rates are published according to the same  calendar as the TARGET system.</description>
      <dc:date>{date}T14:15:00+01:00</dc:date>
      <dc:language>en</dc:language>
      <cb:statistics>
        <cb:country>U2</cb:country>
        <cb:institutionAbbrev>ECB</cb:institutionAbbrev>
        <cb:exchangeRate>
          <cb:value frequency="daily" decimals="5">{gbp}</cb:value>
          <cb:baseCurrency unit_mult="0">EUR</cb:baseCurrency>
          <cb:targetCurrency>GBP</cb:targetCurrency>
          <cb:rateType>Reference rate</cb:rateType>
        </cb:exchangeRate>
      </cb:statistics>
    </item>
  }
}
