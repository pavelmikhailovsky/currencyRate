package com.exchangerates.serviceexchangerates.services.feign.gif;

import feign.Param;
import feign.RequestLine;

public interface GifClient {

    @RequestLine("GET ?api_key={appId}&tag={tag}")
    GifJsonModel getGifSearchResults(@Param("appId") String appId,
                                     @Param("tag") String tag);

}
