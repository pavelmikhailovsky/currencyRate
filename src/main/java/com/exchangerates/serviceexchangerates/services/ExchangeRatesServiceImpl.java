package com.exchangerates.serviceexchangerates.services;

import com.exchangerates.serviceexchangerates.services.feign.currency.CurrencyRateClient;
import com.exchangerates.serviceexchangerates.services.feign.currency.CurrencyRateJsonModel;
import com.exchangerates.serviceexchangerates.services.feign.gif.GifClient;
import com.exchangerates.serviceexchangerates.services.feign.gif.GifJsonModel;
import com.exchangerates.serviceexchangerates.services.feign.gif.GifJsonModelResult;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ExchangeRatesServiceImpl implements ExchangeRatesService {

    @Value("${feign.client.currency-rate.url}")
    private String currencyRateUrl;

    @Value("${feign.client.gif.url}")
    private String gifUrl;

    @Value("${feign.client.currency-rate.app-id}")
    private String currencyRateAppId;

    @Value("${feign.client.gif.app-id}")
    private String gifAppId;

    @Value("${feign.client.currency-rate.base.currency}")
    private String baseCurrency;

    @Value("${feign.client.gif.images.type}")
    private String gifImagesType;

    @Value("${feign.client.gif.images.type.url}")
    private String gifImagesTypeUrl;

    @Override
    public String getRandomGif(String currencyCode) {
        String gif = "";
        GifClient gifClient = gifClient();
        CurrencyRateClient currencyRateClient = currencyRateClient();
        Double latestCurrencyRate = getLatestCurrencyRate(currencyCode, currencyRateClient);
        Double yesterdayCurrencyRate = getYesterdayCurrencyRate(currencyCode, currencyRateClient);

        if (latestCurrencyRate != null && latestCurrencyRate < yesterdayCurrencyRate) {
            gif = getGifDependingCurrencyRate("broke", gifClient);
        } else if (latestCurrencyRate != null && latestCurrencyRate > yesterdayCurrencyRate) {
            gif = getGifDependingCurrencyRate("rich", gifClient);
        }

        return gif;
    }

    private String getGifDependingCurrencyRate(String searchWord, GifClient gifClient) {
        GifJsonModelResult data = gifClient.getGifSearchResults(gifAppId, searchWord);
        GifJsonModel gifJsonModel = data.getData().get(0);
        return (String) gifJsonModel.getImages().get(gifImagesType).get(gifImagesTypeUrl);
    }

    private Double getLatestCurrencyRate(String currencyCode, CurrencyRateClient currencyRateClient) {
        CurrencyRateJsonModel currencyRate = currencyRateClient.getLatestCurrencyRate(currencyRateAppId,
                                                                                      baseCurrency,
                                                                                      currencyCode);
        return (Double) currencyRate.getRates().get(currencyCode);
    }

    private Double getYesterdayCurrencyRate(String currencyCode, CurrencyRateClient currencyRateClient) {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        CurrencyRateJsonModel currencyRate = currencyRateClient.historicalCurrencyRate(yesterday.toString(),
                                                                                       currencyRateAppId,
                                                                                       baseCurrency,
                                                                                       currencyCode);
        return (Double) currencyRate.getRates().get(currencyCode);
    }

    private CurrencyRateClient currencyRateClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .decoder(new GsonDecoder())
                .target(CurrencyRateClient.class, currencyRateUrl);
    }

    private GifClient gifClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .decoder(new GsonDecoder())
                .target(GifClient.class, gifUrl);
    }
}
