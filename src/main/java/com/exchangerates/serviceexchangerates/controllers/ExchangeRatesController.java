package com.exchangerates.serviceexchangerates.controllers;

import com.exchangerates.serviceexchangerates.services.ExchangeRatesService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class ExchangeRatesController {

    private final ExchangeRatesService exchangeRatesService;

    public ExchangeRatesController(ExchangeRatesService exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }

    @GetMapping("/{currencyCode}")
    public RedirectView getGifAboutStateCourse(@PathVariable("currencyCode") String currencyCode) {
        return new RedirectView(exchangeRatesService.getRandomGif(currencyCode));
    }

}
