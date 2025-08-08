package com.booleanuk.core;

import java.util.HashMap;
import java.util.Map;

public class Basket {

    int orderLimit;
    Map<String, Map.Entry<Bagel, Integer>> orders;

    StoreManager manager;

    public Basket() {
        this. manager = new StoreManager();
        changeBasketSizeLimit(5);
        orders = new HashMap<>();
    }

    public Map<String, Map.Entry<Bagel, Integer>> getOrders() {
        return orders;
    }

    public boolean addOrder(String bagelCode, int amount) {
        if (manager.getBagelPrices().containsKey(bagelCode) && amount > 0 && getOrderSize() + amount <= orderLimit) {
            orders.put(bagelCode, Map.entry(new Bagel(bagelCode), amount));
            return true;
        }
        return false;
    }

    public boolean changeOrder(String bagelToChange, String newBagel, int amount) {
        int prevAmount = 0;
        if (orders.containsKey(bagelToChange) && manager.getBagelPrices().containsKey(newBagel) && amount > 0) {
            if (bagelToChange.equals(newBagel)) {
                prevAmount = orders.get(bagelToChange).getValue();
            } else {
                orders.remove(bagelToChange);
            }
            return addOrder(newBagel, amount + prevAmount);
        }
        return false;
    }

    public boolean removeOrder(String bagel) {
        if (orders.containsKey(bagel)) {
            orders.remove(bagel);
            return true;
        }
        return false;
    }

    public boolean changeBasketSizeLimit(int newSizeLimit) {
        if (newSizeLimit > 0) {
            this.orderLimit = newSizeLimit;
            return true;
        }
        return false;
    }

    private int getOrderSize() {
        int count =0;
        for (var order : orders.entrySet()) {
            count+= order.getValue().getValue();

        }
        return count;
    }

    public boolean setFilling(String bagel, String filling) {
        if (orders.containsKey(bagel)) {
            var orderSet = orders.get(bagel);
            if (orderSet.getKey().setFilligType(filling)) {
                return true;
            }

        }
        return false;
    }

    public float getTotalCost() {
        float cost =0;
        for (var order : orders.entrySet()) {
            var singleOrder = order.getValue();
            Bagel bagel = singleOrder.getKey();
            int amount = singleOrder.getValue();

            cost += (manager.getBagelPrice(bagel.getBagelType())) * amount;
            cost += manager.getFillingPrice(bagel.getFilligType()) * amount;


        }
        return cost;
    }

}
