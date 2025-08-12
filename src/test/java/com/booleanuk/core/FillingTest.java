package com.booleanuk.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FillingTest {

    @Test
    void getName() {
        Filling fillingHam = new HamFilling();
        Assertions.assertEquals("Ham Filling", fillingHam.getName());
        Filling fillingCheese = new CheeseFilling();
        Assertions.assertEquals("Cheese Filling", fillingCheese.getName());
    }

    @Test
    void getCost() {
        Filling fillingHam = new HamFilling();
        Assertions.assertEquals(0.12f, fillingHam.getCost());
        Filling fillingCheese = new CheeseFilling();
        Assertions.assertEquals(0.12f, fillingCheese.getCost());


    }

    @Test
    void getFillingFromSKU() {
        Assertions.assertEquals("Cheese Filling", Filling.getFillingFromSKU("FILC").getName());
        Assertions.assertEquals("Cream Cheese Filling", Filling.getFillingFromSKU("FILX").getName());
        Assertions.assertNull(Filling.getFillingFromSKU("not a filling"));


    }

    @Test
    void getFillingPrices() {
        var fillings = Filling.getFillingPrices();
        Assertions.assertEquals(6, fillings.size());
    }
}