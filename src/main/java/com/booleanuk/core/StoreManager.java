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

    public float getFillingPrice(String filling) {
        return fillingPrices.getOrDefault(filling, 0f);
    }

    public float getBagelPrice(String bagel) {
        return bagelPrices.getOrDefault(bagel, 0f);
    }

    public StoreManager(){
        bagelPrices = new HashMap<>();
        bagelPrices.put("BGLO", 0.49f);
        bagelPrices.put("BGLP", 0.39f);
        bagelPrices.put("BGLE", 0.49f);
        bagelPrices.put("BGLS", 0.49f);

        fillingPrices = new HashMap<>();
        fillingPrices.put("Bacon", 0.12f);
        fillingPrices.put("Egg", 0.12f);
        fillingPrices.put("Cheese", 0.12f);
        fillingPrices.put("Cream Cheese", 0.12f);
        fillingPrices.put("Smoked Salmon", 0.12f);
        fillingPrices.put("Ham", 0.12f);
    }
}
