package com.booleanuk.core;

public class CoffeeLatte extends StoreItem{
    public CoffeeLatte() {
        super("Coffee Latte", "COFL", 1.29f);
    }

    @Override
    public boolean setFilling(String filling) {
        return false;
    }
}
