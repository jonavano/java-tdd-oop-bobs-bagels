package com.booleanuk.core;

public abstract class StoreManeger {

    static private int basketSize = 15;

    public static int getBasketSize() {
        return basketSize;
    }

    public static boolean setBasketSize(int newBasketSize, boolean isManeger) {
        if (!isManeger)
            return false;
        basketSize = newBasketSize;
        return true;
    }


}
