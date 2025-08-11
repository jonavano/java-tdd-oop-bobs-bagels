package com.booleanuk.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FillingTest {

    @Test
    void getFilling() {
        Filling fillingHam = new HamFilling();
        Assertions.assertEquals("Ham Filling", fillingHam.getFilling());
        Filling fillingCheese = new CheeseFilling();
        Assertions.assertEquals("Cheese Filling", fillingCheese.getFilling());
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
        Assertions.assertEquals("Cheese Filling", Filling.getFillingFromSKU("FILC").getFilling());
        Assertions.assertEquals("Cream Cheese Filling", Filling.getFillingFromSKU("FILX").getFilling());
        Assertions.assertNull(Filling.getFillingFromSKU("not a filling"));


    }

    @Test
    void getFillingPrices() {
        var fillings = Filling.getFillingPrices();
        Assertions.assertEquals(6, fillings.size());
    }
}