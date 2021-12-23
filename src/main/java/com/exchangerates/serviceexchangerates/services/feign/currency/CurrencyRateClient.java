package com.exchangerates.serviceexchangerates.services.feign.currency;

import feign.Param;
import feign.RequestLine;

public interface CurrencyRateClient {

    @RequestLine("GET /latest.json?app_id={appId}&base={baseCurrency}&symbols={symbols}")
    CurrencyRateJsonModel getLatestCurrencyRate(@Param("appId") String appId,
                                                @Param("baseCurrency") String baseCurrency,
                                                @Param("symbols") String symbols);

    @RequestLine("GET /historical/{date}.json?app_id={appId}&base={baseCurrency}&symbols={symbols}")
    CurrencyRateJsonModel historicalCurrencyRate(@Param("date") String date,
                                                 @Param("appId") String appId,
                                                 @Param("baseCurrency") String baseCurrency,
                                                 @Param("symbols") String symbols);

}
