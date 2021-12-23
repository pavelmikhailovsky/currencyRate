package com.exchangerates.serviceexchangerates.services.feign;

import com.exchangerates.serviceexchangerates.services.feign.currency.CurrencyRateClient;
import com.exchangerates.serviceexchangerates.services.feign.gif.GifClient;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FeignClientsImpl implements FeignClients {

    @Value("${feign.client.currency-rate.url}")
    private String currencyRateUrl;

    @Value("${feign.client.gif.url}")
    private String gifUrl;

    @Override
    public CurrencyRateClient currencyRateClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .decoder(new GsonDecoder())
                .target(CurrencyRateClient.class, currencyRateUrl);
    }

    @Override
    public GifClient gifClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .decoder(new GsonDecoder())
                .target(GifClient.class, gifUrl);
    }
}
