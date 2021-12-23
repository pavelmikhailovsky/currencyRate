package com.exchangerates.serviceexchangerates.services.feign.gif;

import com.google.gson.internal.LinkedTreeMap;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GifJsonModel {

    private LinkedTreeMap<String, Object> data;

    public LinkedTreeMap<String, Object> getData() {
        return data;
    }

    public void setData(LinkedTreeMap<String, Object> data) {
        this.data = data;
    }
}
