package com.exchangerates.serviceexchangerates.services.feign.gif;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class GifJsonModelResult {

    private List<GifJsonModel> data;

    public List<GifJsonModel> getData() {
        return data;
    }

    public void setData(List<GifJsonModel> data) {
        this.data = data;
    }
}
