package com.booleanuk.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoreManagerTest {

    @Test
    void getBasketSize() {
        Assertions.assertEquals(25, StoreManager.getBasketSize());;
    }

    @Test
    void setBasketSize() {
        Assertions.assertTrue(StoreManager.setBasketSize(2, true));
        Assertions.assertEquals(2, StoreManager.getBasketSize());


    }
}