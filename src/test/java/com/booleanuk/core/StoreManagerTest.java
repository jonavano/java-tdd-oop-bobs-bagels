package com.booleanuk.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class StoreManagerTest {

    @Test
    void getBagelPrices() {
        StoreManager manager = new StoreManager();
        var prices = manager.getBagelPrices();
        Assertions.assertEquals(4, prices.size());
    }

    @Test
    void getFillingPrices() {
        StoreManager manager = new StoreManager();
        var prices = manager.getFillingPrices();
        Assertions.assertEquals(6, prices.size());
    }

    @Test
    void getFillingPrice() {
        StoreManager manager = new StoreManager();
        var filingPrice = manager.getFillingPrice("Bacon");
        Assertions.assertEquals(.12f, filingPrice);
    }

    @Test
    void getBagelPrice() {
        StoreManager manager = new StoreManager();
        var filingPrice = manager.getBagelPrice("BGLO");
        Assertions.assertEquals(.49f, filingPrice);

    }
}
