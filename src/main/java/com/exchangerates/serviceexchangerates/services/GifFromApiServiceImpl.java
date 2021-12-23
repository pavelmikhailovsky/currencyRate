package com.exchangerates.serviceexchangerates.services;

import com.exchangerates.serviceexchangerates.services.feign.gif.GifClient;
import com.exchangerates.serviceexchangerates.services.feign.gif.GifJsonModel;
import com.exchangerates.serviceexchangerates.services.feign.gif.GifJsonModelResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GifFromApiServiceImpl implements GifFromApiService {

    @Value("${feign.client.gif.app-id}")
    private String gifAppId;

    @Value("${feign.client.gif.images.type}")
    private String gifImagesType;

    @Value("${feign.client.gif.images.type.url}")
    private String gifImagesTypeUrl;

    @Override
    public String getGifDependingCurrencyRate(String searchWord, GifClient gifClient) {
        GifJsonModelResult data = gifClient.getGifSearchResults(gifAppId, searchWord);
        GifJsonModel gifJsonModel = data.getData().get(0);
        return (String) gifJsonModel.getImages().get(gifImagesType).get(gifImagesTypeUrl);
    }
}
