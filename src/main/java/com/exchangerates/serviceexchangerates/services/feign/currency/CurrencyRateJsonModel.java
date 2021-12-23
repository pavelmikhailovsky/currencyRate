package com.exchangerates.serviceexchangerates.services.feign.currency;

import com.google.gson.internal.LinkedTreeMap;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CurrencyRateJsonModel {

    private String disclaimer;
    private String license;
    private Integer timestamp;
    private String base;
    private LinkedTreeMap<String, Object> rates;

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public LinkedTreeMap<String, Object> getRates() {
        return rates;
    }

    public void setRates(LinkedTreeMap<String, Object> rates) {
        this.rates = rates;
    }

}
