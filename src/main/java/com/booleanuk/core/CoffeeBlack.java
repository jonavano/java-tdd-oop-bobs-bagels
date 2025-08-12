package com.booleanuk.core;

public class CoffeeBlack extends StoreItem {
    public CoffeeBlack() {
        super("Coffee Black", "COFB", 0.99f);
    }

    @Override
    public boolean setFilling(String filling) {
        return false;
    }
}
