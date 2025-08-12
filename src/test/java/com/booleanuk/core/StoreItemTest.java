package com.booleanuk.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StoreItemTest {
    @Test
    void getName() {
        StoreItem storeItem = new PlainBagel();
        Assertions.assertEquals("Plain Bagel", storeItem.getName());
        StoreItem onionStoreItem = new OnionBagel();
        Assertions.assertEquals("Onion Bagel", onionStoreItem.getName());
    }

    @Test
    void getSKU() {
        StoreItem storeItem = new PlainBagel();
        Assertions.assertEquals("BGLP", storeItem.getSKU());
        StoreItem onionStoreItem = new OnionBagel();
        Assertions.assertEquals("BGLO", onionStoreItem.getSKU());
    }

    @Test
    void getCost() {
        StoreItem storeItem = new PlainBagel();
        Assertions.assertEquals(0.39f, storeItem.getCost());
        StoreItem onionStoreItem = new OnionBagel();
        Assertions.assertEquals(0.49f, onionStoreItem.getCost());
    }

    @Test
    void getFilling() {
        Assertions.assertNull(new PlainBagel().getFilling());
        Assertions.assertNull(new SesameBagel().getFilling());
    }

    @Test
    void setFilling() {
        StoreItem storeItem = new PlainBagel();
        Assertions.assertFalse(storeItem.setFilling("not a filling"));
        Assertions.assertNull(storeItem.getFilling());
        Assertions.assertTrue(storeItem.setFilling("FILX"));
        Assertions.assertEquals("Cream Cheese Filling", storeItem.getFilling().getName());

    }

    @Test
    void getBagelFromSKU() {
        Assertions.assertEquals("Plain Bagel", StoreItem.getBagelFromSKU("BGLP").getName());
        Assertions.assertNull(StoreItem.getBagelFromSKU("now a bagel"));
        Assertions.assertEquals("Sesame Bagel", StoreItem.getBagelFromSKU("BGLS").getName());
    }

    @Test
    void getStoreItemPrices() {
        var bagels = StoreItem.getStoreItemPrices();
        Assertions.assertEquals(8, bagels.size());
    }

}
