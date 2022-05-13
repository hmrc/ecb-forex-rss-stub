
# ecb-forex-rss-stub

This is a stub for the European Central Bank (ECB) foreign exchange (forex) RSS feed to be used in conjunction with [forex-rates](https://github.com/hmrc/forex-rates) when you want to use a stubbed instance rather than the actual one.

The XML returns by the endpoint will return the last 5 weekdays of random exchange rates. The randomness and the amount are configured by configuration found in application.conf

```
settings {
    length = 5
    baseRate = 0.85
    range = 0.1
}
```

Resources
----------

| Method | URL                                    | Description                                                                   | Example response |
| :----: |----------------------------------------|-------------------------------------------------------------------------------|------------------|
| GET    | /ecb-forex-rss-stub/rss/fxref-gbp.html | Returns a XML inline with ECB exchange rate feed, specifically for EUR -> GBP | As below         |

```xml
<?xml version='1.0' encoding='utf-8'?>
<rdf:RDF xsi:schemaLocation="http://www.w3.org/1999/02/22-rdf-syntax-ns# rdf.xsd" xmlns="http://purl.org/rss/1.0/" xmlns:cb="http://www.cbwiki.net/wiki/index.php/Specification_1.1" xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:dcterms="http://purl.org/dc/terms/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <channel rdf:about="http://www.ecb.europa.eu/rss/gbp.html">
        <title>ECB | Pound sterling (GBP) - Euro foreign exchange reference rates</title>
        <link>http://www.ecb.europa.eu/home/html/rss.en.html</link>
        <description>The reference rates are based on the regular daily concertation procedure between central banks within and outside the European System of Central Banks, which normally takes place at 2.15 p.m. (14:15) ECB time.</description>
        <items>
            <rdf:Seq>
                <rdf:li rdf:resource="http://www.ecb.europa.eu/stats/exchange/eurofxref/html/eurofxref-graph-gbp.en.html?date=2022-05-04&amp;rate=0.86655"/>
                <rdf:li rdf:resource="http://www.ecb.europa.eu/stats/exchange/eurofxref/html/eurofxref-graph-gbp.en.html?date=2022-05-03&amp;rate=0.82771"/>
                <rdf:li rdf:resource="http://www.ecb.europa.eu/stats/exchange/eurofxref/html/eurofxref-graph-gbp.en.html?date=2022-05-02&amp;rate=0.83087"/>
                <rdf:li rdf:resource="http://www.ecb.europa.eu/stats/exchange/eurofxref/html/eurofxref-graph-gbp.en.html?date=2022-04-29&amp;rate=0.86832"/>
                <rdf:li rdf:resource="http://www.ecb.europa.eu/stats/exchange/eurofxref/html/eurofxref-graph-gbp.en.html?date=2022-04-28&amp;rate=0.87276"/>
            </rdf:Seq>
        </items>
        <dc:publisher>European Central Bank</dc:publisher>
        <dcterms:license>http://www.ecb.europa.eu/home/html/disclaimer.en.html</dcterms:license>
    </channel>
    <item rdf:about="http://www.ecb.europa.eu/stats/exchange/eurofxref/html/eurofxref-graph-gbp.en.html?date=2022-05-04&amp;rate=0.86655">
        <title xml:lang="en">0.86655 GBP = 1 EUR 2022-05-04 ECB Reference rate</title>
        <link>http://www.ecb.europa.eu/stats/exchange/eurofxref/html/eurofxref-graph-gbp.en.html?date=2022-05-04&amp;rate=0.86655</link>
        <description xml:lang="en">1 EUR buys 0.86655 Pound sterling (GBP) - The reference exchange rates are published both by electronic market information providers and on the ECB's website shortly after the concertation procedure has been completed. Reference rates are published according to the same calendar as the TARGET system.</description>
        <dc:date>2022-05-04T14:15:00+01:00</dc:date>
        <dc:language>en</dc:language>
        <cb:statistics>
            <cb:country>U2</cb:country>
            <cb:institutionAbbrev>ECB</cb:institutionAbbrev>
            <cb:exchangeRate>
                <cb:value decimals="5" frequency="daily">0.86655</cb:value>
                <cb:baseCurrency unit_mult="0">EUR</cb:baseCurrency>
                <cb:targetCurrency>GBP</cb:targetCurrency>
                <cb:rateType>Reference rate</cb:rateType>
            </cb:exchangeRate>
        </cb:statistics>
    </item>
    <item rdf:about="http://www.ecb.europa.eu/stats/exchange/eurofxref/html/eurofxref-graph-gbp.en.html?date=2022-05-03&amp;rate=0.82771">
        <title xml:lang="en">0.82771 GBP = 1 EUR 2022-05-03 ECB Reference rate</title>
        <link>http://www.ecb.europa.eu/stats/exchange/eurofxref/html/eurofxref-graph-gbp.en.html?date=2022-05-03&amp;rate=0.82771</link>
        <description xml:lang="en">1 EUR buys 0.82771 Pound sterling (GBP) - The reference exchange rates are published both by electronic market information providers and on the ECB's website shortly after the concertation procedure has been completed. Reference rates are published according to the same calendar as the TARGET system.</description>
        <dc:date>2022-05-03T14:15:00+01:00</dc:date>
        <dc:language>en</dc:language>
        <cb:statistics>
            <cb:country>U2</cb:country>
            <cb:institutionAbbrev>ECB</cb:institutionAbbrev>
            <cb:exchangeRate>
                <cb:value decimals="5" frequency="daily">0.82771</cb:value>
                <cb:baseCurrency unit_mult="0">EUR</cb:baseCurrency>
                <cb:targetCurrency>GBP</cb:targetCurrency>
                <cb:rateType>Reference rate</cb:rateType>
            </cb:exchangeRate>
        </cb:statistics>
    </item>
    <item rdf:about="http://www.ecb.europa.eu/stats/exchange/eurofxref/html/eurofxref-graph-gbp.en.html?date=2022-05-02&amp;rate=0.83087">
        <title xml:lang="en">0.83087 GBP = 1 EUR 2022-05-02 ECB Reference rate</title>
        <link>http://www.ecb.europa.eu/stats/exchange/eurofxref/html/eurofxref-graph-gbp.en.html?date=2022-05-02&amp;rate=0.83087</link>
        <description xml:lang="en">1 EUR buys 0.83087 Pound sterling (GBP) - The reference exchange rates are published both by electronic market information providers and on the ECB's website shortly after the concertation procedure has been completed. Reference rates are published according to the same calendar as the TARGET system.</description>
        <dc:date>2022-05-02T14:15:00+01:00</dc:date>
        <dc:language>en</dc:language>
        <cb:statistics>
            <cb:country>U2</cb:country>
            <cb:institutionAbbrev>ECB</cb:institutionAbbrev>
            <cb:exchangeRate>
                <cb:value decimals="5" frequency="daily">0.83087</cb:value>
                <cb:baseCurrency unit_mult="0">EUR</cb:baseCurrency>
                <cb:targetCurrency>GBP</cb:targetCurrency>
                <cb:rateType>Reference rate</cb:rateType>
            </cb:exchangeRate>
        </cb:statistics>
    </item>
    <item rdf:about="http://www.ecb.europa.eu/stats/exchange/eurofxref/html/eurofxref-graph-gbp.en.html?date=2022-04-29&amp;rate=0.86832">
        <title xml:lang="en">0.86832 GBP = 1 EUR 2022-04-29 ECB Reference rate</title>
        <link>http://www.ecb.europa.eu/stats/exchange/eurofxref/html/eurofxref-graph-gbp.en.html?date=2022-04-29&amp;rate=0.86832</link>
        <description xml:lang="en">1 EUR buys 0.86832 Pound sterling (GBP) - The reference exchange rates are published both by electronic market information providers and on the ECB's website shortly after the concertation procedure has been completed. Reference rates are published according to the same calendar as the TARGET system.</description>
        <dc:date>2022-04-29T14:15:00+01:00</dc:date>
        <dc:language>en</dc:language>
        <cb:statistics>
            <cb:country>U2</cb:country>
            <cb:institutionAbbrev>ECB</cb:institutionAbbrev>
            <cb:exchangeRate>
                <cb:value decimals="5" frequency="daily">0.86832</cb:value>
                <cb:baseCurrency unit_mult="0">EUR</cb:baseCurrency>
                <cb:targetCurrency>GBP</cb:targetCurrency>
                <cb:rateType>Reference rate</cb:rateType>
            </cb:exchangeRate>
        </cb:statistics>
    </item>
    <item rdf:about="http://www.ecb.europa.eu/stats/exchange/eurofxref/html/eurofxref-graph-gbp.en.html?date=2022-04-28&amp;rate=0.87276">
        <title xml:lang="en">0.87276 GBP = 1 EUR 2022-04-28 ECB Reference rate</title>
        <link>http://www.ecb.europa.eu/stats/exchange/eurofxref/html/eurofxref-graph-gbp.en.html?date=2022-04-28&amp;rate=0.87276</link>
        <description xml:lang="en">1 EUR buys 0.87276 Pound sterling (GBP) - The reference exchange rates are published both by electronic market information providers and on the ECB's website shortly after the concertation procedure has been completed. Reference rates are published according to the same calendar as the TARGET system.</description>
        <dc:date>2022-04-28T14:15:00+01:00</dc:date>
        <dc:language>en</dc:language>
        <cb:statistics>
            <cb:country>U2</cb:country>
            <cb:institutionAbbrev>ECB</cb:institutionAbbrev>
            <cb:exchangeRate>
                <cb:value decimals="5" frequency="daily">0.87276</cb:value>
                <cb:baseCurrency unit_mult="0">EUR</cb:baseCurrency>
                <cb:targetCurrency>GBP</cb:targetCurrency>
                <cb:rateType>Reference rate</cb:rateType>
            </cb:exchangeRate>
        </cb:statistics>
    </item>
</rdf:RDF>
```

Requirements
------------

This service is written in [Scala](http://www.scala-lang.org/) and [Play](http://playframework.com/), so needs at least a [JRE] to run.

## Run the application

To update from Nexus and start all services from the RELEASE version instead of snapshot
```
sm --start FOREX_RATES_ALL -r
```

### To run the application locally execute the following:
```
sm --stop ECB_FOREX_RSS_STUB
```
and
```
sbt run
```

### Unit tests

```
sbt test
```

### License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").