package com.exchangerates.serviceexchangerates.services;

import com.exchangerates.serviceexchangerates.services.feign.currency.CurrencyRateClient;

public interface CurrencyRateFromApiService {

    Double getLatestCurrencyRate(String currencyCode, CurrencyRateClient currencyRateClient);

    Double getYesterdayCurrencyRate(String currencyCode, CurrencyRateClient currencyRateClient);

}
