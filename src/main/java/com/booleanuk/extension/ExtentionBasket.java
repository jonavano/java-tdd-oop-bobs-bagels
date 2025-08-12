package com.booleanuk.extension;

import com.booleanuk.core.Bagel;
import com.booleanuk.core.Basket;
import com.booleanuk.core.Filling;

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

    public float countCostBundle(Bagel originalBagel, Bagel bundleBagel, int count, int neededToBundleCount) {
        int bundleCount = count % neededToBundleCount;
        float bundleCost = bundleBagel.getCost() * bundleCount;
        float originalCost = originalBagel.getCost() * (count - (bundleCount * neededToBundleCount));
        return bundleCost + originalCost;



    }

    public List<Map.Entry<ReceiptItem, Integer>> getReceipt() {
        Map<String, Map.Entry<ReceiptItem, Integer>> receipt = new HashMap<>();

        for (var set: super.getOrders().entrySet()) {
            Bagel bagel = set.getValue().getKey();
            int amount = set.getValue().getValue();
            Filling filling = bagel.getFilling();
            receipt.merge(bagel.getSKU(), Map.entry(bagel, amount),
                    (a,b) ->
                            Map.entry(bagel,a.getValue() + b.getValue() )
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
        return returnValue;

    }

    private static Map<String, Map.Entry<ReceiptItem, Integer>> turnIntoBundle(Map<String, Map.Entry<ReceiptItem, Integer>> receipt, int count,
                                       ReceiptItem bagel, int needToHave, Bagel bundle) {
        int bundleCount = count / needToHave;
        receipt.put(bundle.getSKU(), Map.entry(bundle, bundleCount));
        receipt.replace(bagel.getSKU(),
                Map.entry(bagel, (count - (bundleCount * needToHave)))
                );
        return  receipt;
    }
}
