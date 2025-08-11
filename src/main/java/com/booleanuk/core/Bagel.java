package com.booleanuk.core;

import java.util.HashMap;
import java.util.Map;

public abstract class Bagel {

    private final String bagelName;
    private final String sku;
    private final float cost;

    private Filling filling;

    Bagel(String bagelName, String sku, float cost) {
        this.sku = sku;
        this.bagelName = bagelName;
        this.cost = cost;
    }

    public String getBagel() {
        return bagelName;
    }

    public String getSku() {
        return sku;
    }

    public float getCost() {
        float fulCost = cost;
        if (filling != null) {
            fulCost += filling.getCost();
        }
        return fulCost;
    }

    public Filling getFilling() {
        return this.filling;
    }


    public boolean setFilling(String fillingSKU) {
        Filling filling = Filling.getFillingFromSKU(fillingSKU);
        if (filling != null) {
            this.filling = filling;
            return true;
        }
        return false;
    }

    public static Bagel getBagelFromSKU(String sku) {
        return switch (sku) {
            case "BGLP" -> new PlainBagel();
            case "BGLO" -> new OnionBagel();
            case "BGLE" -> new EverythingBagel();
            case "BGLS" -> new SesameBagel();
            default -> null;
        };
    }

    public static Map<String, Float> getBagelPrices() {
        Bagel[] bagels = {new PlainBagel(), new OnionBagel(), new SesameBagel(), new EverythingBagel()};
        Map<String, Float> priceList = new HashMap<>();
        for (Bagel bagel: bagels) {
            priceList.put(bagel.getBagel(), bagel.getCost());
        }
        return priceList;
    }


}
