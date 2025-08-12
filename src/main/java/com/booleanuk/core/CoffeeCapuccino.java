package com.booleanuk.core;

public class CoffeeCapuccino extends StoreItem{
    public CoffeeCapuccino() {
        super("Coffee Cappuccino", "COFC", 1.29f);
    }

    @Override
    public boolean setFilling(String filling) {
        return false;
    }
}
