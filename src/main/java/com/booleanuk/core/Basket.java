package com.booleanuk.core;

import java.util.HashMap;
import java.util.Map;

public class Basket {

    int orderLimit = 3;
    Map<String, Map.Entry<Bagel, Integer>> orders;

    public Basket() {
        orders = new HashMap<>();
    }

    public boolean addOrder(String bagelCode, int amount) {
        return false;
    }

    public boolean changeOrder(String bagelToChange, String newBagel, int amount) {
        return false;
    }

    public boolean removeOrder(String bagel) {
        return false;
    }

    public boolean changeBasketSizeLimit(int newSizeLimit) {
        return false;
    }

    public int getTotalCost() {
        return -1;
    }

}
