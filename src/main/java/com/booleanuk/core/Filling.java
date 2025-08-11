package com.booleanuk.core;

import java.util.HashMap;
import java.util.Map;

public abstract class Filling {

    private final String filling;
    private final String sku;

    public Filling(String filling, String sku) {
        this.filling = filling;
        this.sku = sku;
    }

    public String getFilling(){return filling;}

    public float getCost() {
        return 0.12f;}


    public static Filling getFillingFromSKU(String sku) {
        return switch (sku) {
            case "FILB" -> new BaconFilling();
            case "FILX" -> new CreamCheeseFilling();
            case "FILE" -> new EggFilling();
            case "FILC" -> new CheeseFilling();
            case "FILS" -> new SmokedSalmonFilling();
            case "FILH" -> new HamFilling();
            default -> null;
        };
    }

    public static Map<String, Float> getFillingPrices() {
        Filling[] fillings = {new BaconFilling(), new CreamCheeseFilling(), new EggFilling(),
                new CheeseFilling(), new SmokedSalmonFilling(), new HamFilling()};
        Map<String, Float> priceList = new HashMap<>();
        for (Filling filling: fillings) {
            priceList.put(filling.getFilling(), filling.getCost());
        }
        return priceList;
    }
}
