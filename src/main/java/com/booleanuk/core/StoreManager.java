package com.booleanuk.core;

import java.util.HashMap;
import java.util.Map;

public class StoreManager {
    Map<String, Float> bagelPrices;
    Map<String, Float> fillingPrices;

    public Map<String, Float> getBagelPrices() {
        return bagelPrices;
    }

    public Map<String, Float> getFillingPrices() {
        return fillingPrices;
    }

    public StoreManager(){
        bagelPrices = new HashMap<>();
        fillingPrices = new HashMap<>();
    }
}
