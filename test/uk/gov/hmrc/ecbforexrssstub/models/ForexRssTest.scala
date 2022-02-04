package uk.gov.hmrc.ecbforexrssstub.models

import org.joda.time.LocalDateTime
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import uk.gov.hmrc.ecbforexrssstub.generators.ExchangeRateGenerator

import scala.xml.NodeSeq.seqToNodeSeq
import scala.xml.XML


class ForexRssTest  extends AnyWordSpec with Matchers {

  "ForexRss" should {

    val length = 5

    val dateRegex = "([\\d]{4})-([\\d]{2})-([\\d]{2})".r

    val exchangeRates = ExchangeRateGenerator.getExchangeRates(length, 0.85, 0.1)

    val xml = XML.loadString(ForexRss.of(exchangeRates))

    "create an item in channel for each ExchangeRate" in {
      (xml \\ "channel" \\ "items" \\ "Seq" \ "li").length mustEqual length
    }

    "list items under channel in reverse chronological order" in {

      val dates = (xml \\ "channel" \\ "items" \\ "Seq" \ "li")
        .flatMap(_.attribute("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "resource"))
        .flatten.flatMap(resourceAttr => dateRegex.findFirstMatchIn(resourceAttr.toString()))
        .map(s => LocalDateTime.parse(s.toString()))

      Seq.range(1, 5).foreach { index =>
        dates(index).isBefore(dates(index - 1)) mustBe true
      }
    }

    "create an item under root node for each ExchangeRate" in {
      (xml \ "item").length mustEqual length
    }

    "list items under root in reverse chronological order" in {

      val dates = (xml \ "item")
        .map(_ \ "date")
        .flatten.flatMap(dateNode => dateRegex.findFirstMatchIn(dateNode.toString()))
        .map(s => LocalDateTime.parse(s.toString()))

      Seq.range(1, 5).foreach { index =>
        dates(index).isBefore(dates(index - 1)) mustBe true
      }
    }
  }
}
