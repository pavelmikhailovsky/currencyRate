package com.exchangerates.serviceexchangerates.services.feign.gif;

import feign.Param;
import feign.RequestLine;

public interface GifClient {

    @RequestLine("GET ?api_key={appId}&q={search}&limit=1")
    GifJsonModelResult getGifSearchResults(@Param("appId") String appId,
                                           @Param("search") String searchWord);

}
