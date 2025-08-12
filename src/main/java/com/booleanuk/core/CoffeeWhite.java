package com.booleanuk.core;

public class CoffeeWhite extends StoreItem{
    public CoffeeWhite() {
        super("Coffee White", "COFW", 1.19f);
    }

    @Override
    public boolean setFilling(String filling) {
        return false;
    }
}
