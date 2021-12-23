package com.exchangerates.serviceexchangerates.services;

import com.exchangerates.serviceexchangerates.services.feign.gif.GifClient;

public interface GifFromApiService {

    String getGifDependingCurrencyRate(String searchWord, GifClient gifClient);

}
