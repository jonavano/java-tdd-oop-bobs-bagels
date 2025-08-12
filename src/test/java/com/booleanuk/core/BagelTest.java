package com.booleanuk.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BagelTest {
    @Test
    void getName() {
        Bagel bagel = new PlainBagel();
        Assertions.assertEquals("Plain Bagel", bagel.getName());
        Bagel onionBagel = new OnionBagel();
        Assertions.assertEquals("Onion Bagel", onionBagel.getName());
    }

    @Test
    void getSKU() {
        Bagel bagel = new PlainBagel();
        Assertions.assertEquals("BGLP", bagel.getSKU());
        Bagel onionBagel = new OnionBagel();
        Assertions.assertEquals("BGLO", onionBagel.getSKU());
    }

    @Test
    void getCost() {
        Bagel bagel = new PlainBagel();
        Assertions.assertEquals(0.39f, bagel.getCost());
        Bagel onionBagel = new OnionBagel();
        Assertions.assertEquals(0.49f, onionBagel.getCost());
    }

    @Test
    void getFilling() {
        Assertions.assertNull(new PlainBagel().getFilling());
        Assertions.assertNull(new SesameBagel().getFilling());
    }

    @Test
    void setFilling() {
        Bagel bagel = new PlainBagel();
        Assertions.assertFalse(bagel.setFilling("not a filling"));
        Assertions.assertNull(bagel.getFilling());
        Assertions.assertTrue(bagel.setFilling("FILX"));
        Assertions.assertEquals("Cream Cheese Filling", bagel.getFilling().getName());

    }

    @Test
    void getBagelFromSKU() {
        Assertions.assertEquals("Plain Bagel", Bagel.getBagelFromSKU("BGLP").getName());
        Assertions.assertNull(Bagel.getBagelFromSKU("now a bagel"));
        Assertions.assertEquals("Sesame Bagel", Bagel.getBagelFromSKU("BGLS").getName());
    }

    @Test
    void getBagelPrices() {
        var bagels = Bagel.getBagelPrices();
        Assertions.assertEquals(4, bagels.size());
    }

}
