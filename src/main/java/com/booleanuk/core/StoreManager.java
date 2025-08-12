package com.booleanuk.core;

public abstract class StoreManager {

    static private int basketSize = 25;

    public static int getBasketSize() {
        return basketSize;
    }

    public static boolean setBasketSize(int newBasketSize, boolean isManager) {
        if (!isManager)
            return false;
        basketSize = newBasketSize;
        return true;
    }


}
