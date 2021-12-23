package com.exchangerates.serviceexchangerates.services.feign;

import com.exchangerates.serviceexchangerates.services.feign.currency.CurrencyRateClient;
import com.exchangerates.serviceexchangerates.services.feign.gif.GifClient;

public interface FeignClients {

    CurrencyRateClient currencyRateClient();

    GifClient gifClient();

}
