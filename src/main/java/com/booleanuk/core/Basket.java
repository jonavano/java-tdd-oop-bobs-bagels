package com.booleanuk.core;

import java.util.HashMap;
import java.util.Map;

public class Basket {

    Map<String, Map.Entry<Bagel, Integer>> orders;

    public Basket() {
        orders = new HashMap<>();
    }

    public Map<String, Map.Entry<Bagel, Integer>> getOrders() {
        return orders;
    }

    public boolean addOrder(String sku, int amount) {
        int orderLimit = StoreManeger.getBasketSize();

        Bagel bagel = Bagel.getBagelFromSKU(sku);
        if (bagel != null && amount > 0 && getOrderSize() + amount <= orderLimit) {
            orders.merge(sku, Map.entry(bagel, amount),
                    (a, b) -> Map.entry(bagel, a.getValue()+b.getValue()));
            return true;
        }
        return false;
    }

    public boolean changeOrder(String prevSKU, String newBagelSKU, int amount) {
        int prevAmount = 0;
        Bagel newBagel = Bagel.getBagelFromSKU(newBagelSKU);
        if (orders.containsKey(prevSKU) && newBagel != null && amount > 0) {

            if (prevSKU.equals(newBagelSKU)) {
                return addOrder(newBagelSKU, amount);
            } else {
                if (addOrder(newBagelSKU, amount)){
                    orders.remove(prevSKU);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeOrder(String bagelSKU) {
        if (orders.containsKey(bagelSKU)) {
            orders.remove(bagelSKU);
            return true;
        }
        return false;
    }

//    public boolean setBasketSizeLimit(int newSizeLimit) {
//        if (newSizeLimit > 0) {
//            this.orderLimit = newSizeLimit;
//            return true;
//        }
//        return false;
//    }

    private int getOrderSize() {
        int count =0;
        for (var order : orders.entrySet()) {
            count+= order.getValue().getValue();
        }
        return count;
    }

    public boolean setFilling(String bagelSKU, String fillingSKU) {
        if (orders.containsKey(bagelSKU)) {
            var set = orders.get(bagelSKU);
            Bagel bagel = set.getKey();
            if (bagel.setFilling(fillingSKU)) {
                orders.remove(bagelSKU);
                orders.put(bagel.getSKU() + fillingSKU, set);
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

            cost += bagel.getCost() * amount;
        }
        return cost;
    }

}
