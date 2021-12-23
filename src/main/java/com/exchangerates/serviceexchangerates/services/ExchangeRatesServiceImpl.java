package com.exchangerates.serviceexchangerates.services;

import com.exchangerates.serviceexchangerates.services.feign.FeignClients;
import com.exchangerates.serviceexchangerates.services.feign.currency.CurrencyRateClient;
import com.exchangerates.serviceexchangerates.services.feign.gif.GifClient;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRatesServiceImpl implements ExchangeRatesService {

    private FeignClients feignClients;
    private GifFromApiService gifFromApiService;
    private CurrencyRateFromApiService currencyRateFromApiService;

    public ExchangeRatesServiceImpl(CurrencyRateFromApiService currencyRateFromApiService,
                                    GifFromApiService gifFromApiService,
                                    FeignClients feignClients) {
        this.currencyRateFromApiService = currencyRateFromApiService;
        this.gifFromApiService = gifFromApiService;
        this.feignClients = feignClients;
    }

    @Override
    public String getRandomGif(String currencyCode) {
        String gif = "";
        GifClient gifClient = feignClients.gifClient();
        CurrencyRateClient currencyRateClient = feignClients.currencyRateClient();
        Double latestCurrencyRate = currencyRateFromApiService.getLatestCurrencyRate(currencyCode, currencyRateClient);
        Double yesterdayCurrencyRate = currencyRateFromApiService.getYesterdayCurrencyRate(currencyCode, currencyRateClient);

        if (latestCurrencyRate != null && latestCurrencyRate < yesterdayCurrencyRate) {
            gif = gifFromApiService.getGifDependingCurrencyRate("broke", gifClient);
        } else if (latestCurrencyRate != null && latestCurrencyRate > yesterdayCurrencyRate) {
            gif = gifFromApiService.getGifDependingCurrencyRate("rich", gifClient);
        }

        return gif;
    }

}
