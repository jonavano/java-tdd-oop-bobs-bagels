package com.booleanuk.core;

import com.booleanuk.extension.ReceiptItem;

import java.util.HashMap;
import java.util.Map;

public abstract class StoreItem implements ReceiptItem {

    private final String bagelName;
    private final String sku;
    private final float cost;

    private Filling filling;

    protected StoreItem(String bagelName, String sku, float cost) {
        this.sku = sku;
        this.bagelName = bagelName;
        this.cost = cost;
    }

    public String getName() {
        return bagelName;
    }

    public String getSKU() {
        return sku;
    }

    public float getCost() {
        return this.cost;
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

    public void removeFilling() {
        this.filling = null;
    }

    public static StoreItem getBagelFromSKU(String sku) {
        return switch (sku) {
            case "BGLP" -> new PlainBagel();
            case "BGLO" -> new OnionBagel();
            case "BGLE" -> new EverythingBagel();
            case "BGLS" -> new SesameBagel();
            case "COFB" -> new CoffeeBlack();
            case "COFW" -> new CoffeeWhite();
            case "COFC" -> new CoffeeCapuccino();
            case "COFL" -> new CoffeeLatte();
            default -> null;
        };
    }

    public static Map<String, Float> getStoreItemPrices() {
        StoreItem[] storeItems = {new PlainBagel(), new OnionBagel(), new SesameBagel(), new EverythingBagel(),
        new CoffeeBlack(), new CoffeeWhite(), new CoffeeCapuccino(), new CoffeeLatte()};
        Map<String, Float> priceList = new HashMap<>();
        for (StoreItem storeItem : storeItems) {
            priceList.put(storeItem.getName(), storeItem.getCost());
        }
        return priceList;
    }


}
