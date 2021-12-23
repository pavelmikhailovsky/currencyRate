package com.exchangerates.serviceexchangerates.service;

import com.exchangerates.serviceexchangerates.services.CurrencyRateFromApiService;
import com.exchangerates.serviceexchangerates.services.ExchangeRatesService;
import com.exchangerates.serviceexchangerates.services.ExchangeRatesServiceImpl;
import com.exchangerates.serviceexchangerates.services.GifFromApiService;
import com.exchangerates.serviceexchangerates.services.feign.FeignClients;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ExchangeRatesServiceTests {

    ExchangeRatesService exchangeRatesService;

    @MockBean
    CurrencyRateFromApiService currencyRateFromApiService;

    @MockBean
    GifFromApiService gifFromApiService;

    @MockBean
    FeignClients feignClients;

    @BeforeEach
    void setUp() {
        exchangeRatesService = new ExchangeRatesServiceImpl(currencyRateFromApiService, gifFromApiService, feignClients);
        given(gifFromApiService.getGifDependingCurrencyRate(eq("rich"), any())).willReturn("rich");
        given(gifFromApiService.getGifDependingCurrencyRate(eq("broke"), any())).willReturn("broke");
    }

    @Test
    void shouldReturnRichGifIfCurrencyRateHigherBaseCurrency() {
        when(currencyRateFromApiService.getLatestCurrencyRate(anyString(), any())).thenReturn(11.11);
        when(currencyRateFromApiService.getYesterdayCurrencyRate(anyString(), any())).thenReturn(10.10);

        assertEquals(exchangeRatesService.getRandomGif("AOA"), "rich");
        assertNotEquals(exchangeRatesService.getRandomGif("AOA"), "broke");
    }

    @Test
    void shouldReturnBrokeGifIfCurrencyRateHigherBaseCurrency() {
        when(currencyRateFromApiService.getLatestCurrencyRate(anyString(), any())).thenReturn(10.10);
        when(currencyRateFromApiService.getYesterdayCurrencyRate(anyString(), any())).thenReturn(11.11);

        assertEquals(exchangeRatesService.getRandomGif("AOA"), "broke");
        assertNotEquals(exchangeRatesService.getRandomGif("AOA"), "rich");
    }

}
