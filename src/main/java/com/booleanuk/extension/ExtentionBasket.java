package com.booleanuk.extension;

import com.booleanuk.core.*;

import java.util.*;

public class ExtentionBasket extends Basket {

    public ExtentionBasket() {
        super();
    }

    @Override
    public float getTotalCost() {
        var receipt = getReceipt();
        float cost = 0;
        for (var entry : receipt) {
            ReceiptItem item = entry.getKey();
            float costPerItem = item.getCost() * entry.getValue();
            cost += costPerItem;
        }

        return cost;

    }

    public float countCostBundle(StoreItem originalStoreItem, StoreItem bundleStoreItem, int count, int neededToBundleCount) {
        int bundleCount = count % neededToBundleCount;
        float bundleCost = bundleStoreItem.getCost() * bundleCount;
        float originalCost = originalStoreItem.getCost() * (count - (bundleCount * neededToBundleCount));
        return bundleCost + originalCost;



    }

    public List<Map.Entry<ReceiptItem, Integer>> getReceipt() {
        Map<String, Map.Entry<ReceiptItem, Integer>> receipt = new HashMap<>();

        for (var set: super.getOrders().entrySet()) {
            StoreItem storeItem = set.getValue().getKey();
            int amount = set.getValue().getValue();
            Filling filling = storeItem.getFilling();
            receipt.merge(storeItem.getSKU(), Map.entry(storeItem, amount),
                    (a,b) ->
                            Map.entry(storeItem,a.getValue() + b.getValue() )
                    );
            if (filling != null) {
                receipt.merge(filling.getSKU(), Map.entry(filling, amount),
                        (a,b) ->
                        Map.entry(filling, a.getValue() + b.getValue()));
            }

        }

        receipt  = bundleReceipts(receipt);

        return receipt.values().stream().sorted(
                Comparator.comparing(a -> a.getKey().getName())
        ).toList();
    }

    private Map<String, Map.Entry<ReceiptItem, Integer>> bundleReceipts(Map<String, Map.Entry<ReceiptItem, Integer>> receipt) {
        var returnValue = new HashMap<>(receipt);
        for (var set : receipt.values()) {
            ReceiptItem bagel = set.getKey();
            int count = set.getValue();
            if (bagel.getSKU().equals("BGLO") && count >= 6) {
                turnIntoBundle(returnValue, count, bagel, 6, new BundledOnionBagle());
            } else if (bagel.getSKU().equals("BGLP") && count >= 12) {
                turnIntoBundle(returnValue, count, bagel, 12, new BundledPlainBagle());
            } else if (bagel.getSKU().equals("BGLE")&& count >= 6) {
                turnIntoBundle(returnValue, count, bagel, 6, new BundleEverythingBagle());
            }
        }

        return coffeeBundleCreator(returnValue);

    }

    private static Map<String, Map.Entry<ReceiptItem, Integer>> coffeeBundleCreator(Map<String, Map.Entry<ReceiptItem, Integer>> receipt) {
        var localReceipt = new HashMap<>(receipt);
        var bagel = receipt.getOrDefault("BGLP", null);
        var coffee = receipt.getOrDefault("COFB", null);

        if (bagel != null && coffee != null) {
            int overlap = Math.min(bagel.getValue(), coffee.getValue());

            int newBagelCount = bagel.getValue() - overlap;
            int newCoffeeCount = coffee.getValue() - overlap;

            if (newBagelCount > 0) {
                localReceipt.put(bagel.getKey().getSKU(), Map.entry(bagel.getKey(), newBagelCount));
            } else {
                localReceipt.remove(bagel.getKey().getSKU());
            }

            if (newCoffeeCount > 0) {
                localReceipt.put(coffee.getKey().getSKU(), Map.entry(coffee.getKey(), newBagelCount));
            } else {
                localReceipt.remove(coffee.getKey().getSKU());
            }
            ReceiptItem bundleCoffee = new BundledCoffeeBagel();
            localReceipt.put(bundleCoffee.getSKU(), Map.entry(bundleCoffee, overlap));



        }



        return localReceipt;
    }

    private static Map<String, Map.Entry<ReceiptItem, Integer>> turnIntoBundle(Map<String, Map.Entry<ReceiptItem, Integer>> receipt, int count,
                                       ReceiptItem bagel, int needToHave, StoreItem bundle) {
        int bundleCount = count / needToHave;
        receipt.put(bundle.getSKU(), Map.entry(bundle, bundleCount));
        receipt.replace(bagel.getSKU(),
                Map.entry(bagel, (count - (bundleCount * needToHave)))
                );
        return  receipt;
    }


    public String getReceiptToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n~~~ Bob's Bagels ~~~\n\n----------------------------\n\n");
        var receipt = getReceipt();
        for (var entry : receipt) {
            var item = entry.getKey();
            sb.append(item.getName() + " \t" + entry.getValue() + " ");
            sb.append(String.format("%.2f", item.getCost() * entry.getValue()));
            sb.append("\n");
            if (entry.getKey() instanceof BundleEverythingBagle) {
                sb.append("\t\t\t\t (-$");
                float priceSavings = new EverythingBagel().getCost() * 6 - item.getCost();
                sb.append(String.format("%.2f", priceSavings * entry.getValue()));
                sb.append(")\n");
            } else if (entry.getKey() instanceof BundledPlainBagle) {
                sb.append("\t\t\t\t (-$");
                float priceSavings = new EverythingBagel().getCost() * 12 - item.getCost();
                sb.append(String.format("%.2f", priceSavings * entry.getValue()));
                sb.append(")\n");
            } else if (entry.getKey() instanceof BundledOnionBagle) {
                sb.append("\t\t\t\t (-$");
                float priceSavings = new EverythingBagel().getCost() * 6 - item.getCost();
                sb.append(String.format("%.2f", priceSavings * entry.getValue()));
                sb.append(")\n");
            } else if (entry.getKey() instanceof BundledCoffeeBagel) {
                sb.append("\t\t\t\t (-$");
                float priceSavings = new PlainBagel().getCost() + new CoffeeBlack().getCost() - item.getCost();
                sb.append(String.format("%.2f", priceSavings * entry.getValue()));
                sb.append(")\n");
            }

        }

        sb.append("\nTotal \t\t\t  " );
        sb.append(String.format("%.2f", getTotalCost()));
        sb.append("\nThank you\nfor your order!\n");
        return sb.toString();
    }
}
