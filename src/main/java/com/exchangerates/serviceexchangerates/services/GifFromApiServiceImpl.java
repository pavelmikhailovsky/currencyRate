package com.exchangerates.serviceexchangerates.services;

import com.exchangerates.serviceexchangerates.services.feign.gif.GifClient;
import com.exchangerates.serviceexchangerates.services.feign.gif.GifJsonModel;
import com.google.gson.internal.LinkedTreeMap;
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
    public String getGifDependingCurrencyRate(String tag, GifClient gifClient) {
        GifJsonModel data = gifClient.getGifSearchResults(gifAppId, tag);
        LinkedTreeMap<String, LinkedTreeMap<String, Object>> images = (LinkedTreeMap<String, LinkedTreeMap<String, Object>>) data.getData().get("images");
        return (String) images.get(gifImagesType).get(gifImagesTypeUrl);
    }
}
