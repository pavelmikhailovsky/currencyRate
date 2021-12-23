package com.exchangerates.serviceexchangerates.services.feign.gif;

import com.google.gson.internal.LinkedTreeMap;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GifJsonModel {

    private LinkedTreeMap<String, LinkedTreeMap<String, Object>> images;

    public LinkedTreeMap<String, LinkedTreeMap<String, Object>> getImages() {
        return images;
    }

    public void setImages(LinkedTreeMap<String, LinkedTreeMap<String, Object>> images) {
        this.images = images;
    }
}
