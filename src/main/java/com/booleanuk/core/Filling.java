package com.booleanuk.core;

import com.booleanuk.extension.ReceiptItem;

import java.util.HashMap;
import java.util.Map;

public abstract class Filling implements ReceiptItem {

    private final String filling;
    private final String sku;

    public Filling(String filling, String sku) {
        this.filling = filling;
        this.sku = sku;
    }

    public String getName(){return filling;}

    public float getCost() {
        return 0.12f;}

    public String getSKU() {
        return sku;
    }


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
            priceList.put(filling.getName(), filling.getCost());
        }
        return priceList;
    }
}
