package com.exchangerates.serviceexchangerates.services;

import com.exchangerates.serviceexchangerates.services.feign.currency.CurrencyRateClient;
import com.exchangerates.serviceexchangerates.services.feign.currency.CurrencyRateJsonModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CurrencyRateFromApiServiceImpl implements CurrencyRateFromApiService {

    @Value("${feign.client.currency-rate.app-id}")
    private String currencyRateAppId;

    @Value("${feign.client.currency-rate.base.currency}")
    private String baseCurrency;

    @Override
    public Double getLatestCurrencyRate(String currencyCode, CurrencyRateClient currencyRateClient) {
        CurrencyRateJsonModel currencyRate = currencyRateClient.getLatestCurrencyRate(currencyRateAppId,
                baseCurrency,
                currencyCode);
        return (Double) currencyRate.getRates().get(currencyCode);
    }

    @Override
    public Double getYesterdayCurrencyRate(String currencyCode, CurrencyRateClient currencyRateClient) {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        CurrencyRateJsonModel currencyRate = currencyRateClient.historicalCurrencyRate(yesterday.toString(),
                currencyRateAppId,
                baseCurrency,
                currencyCode);
        return (Double) currencyRate.getRates().get(currencyCode);
    }
}
