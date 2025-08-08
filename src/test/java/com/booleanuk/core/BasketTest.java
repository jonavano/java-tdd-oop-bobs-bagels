package com.booleanuk.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {

    @Test
    void addOrder() {
        Basket basket = new Basket();
        Assertions.assertEquals(0, basket.getOrders().size());
        Assertions.assertTrue(basket.addOrder("BGLP",1));
        Assertions.assertEquals(1, basket.getOrders().size());
        Assertions.assertTrue(basket.addOrder("BGLP",1));
        Assertions.assertEquals(1, basket.getOrders().size());
        Assertions.assertFalse(basket.addOrder("BGLO",0));
        Assertions.assertEquals(1, basket.getOrders().size());
        Assertions.assertTrue(basket.addOrder("BGLO", 2));
        Assertions.assertEquals(2, basket.getOrders().size());

    }

    @Test
    void changeOrder() {
        Basket basket = new Basket();
        basket.addOrder("BGLP",1);
        Assertions.assertTrue(basket.changeOrder("BGLP", "BGLO",1));
        Assertions.assertEquals(1, basket.getOrders().size());
        Assertions.assertFalse(basket.changeOrder("BGLP", "BGLP",1));
        Assertions.assertFalse(basket.changeOrder("krekrje", "BGLP",1));
        Assertions.assertFalse(basket.changeOrder("BGLO", "BGLO",-8));
        Assertions.assertTrue(basket.changeOrder("BGLO", "BGLO",2));
        Assertions.assertEquals(3, basket.getOrders().get("BGLO").getValue());
    }

    @Test
    void removeOrder() {
        Basket basket = new Basket();
        Assertions.assertEquals(0, basket.getOrders().size());
        basket.addOrder("BGLP",1);
        Assertions.assertEquals(1, basket.getOrders().size());
        Assertions.assertFalse(basket.removeOrder("BGLO"));
        Assertions.assertEquals(1, basket.getOrders().size());
        Assertions.assertTrue(basket.removeOrder("BGLP"));
        Assertions.assertEquals(0, basket.getOrders().size());
    }

    @Test
    void changeBasketSizeLimit() {
        Basket basket = new Basket();
        Assertions.assertEquals(0, basket.getOrders().size());
        Assertions.assertFalse(basket.addOrder("BGLP",6));
        Assertions.assertEquals(0, basket.getOrders().size());
        Assertions.assertFalse(basket.changeBasketSizeLimit(-1));
        Assertions.assertFalse(basket.addOrder("BGLP",6));
        Assertions.assertEquals(0, basket.getOrders().size());
        Assertions.assertTrue(basket.changeBasketSizeLimit(10));
        Assertions.assertTrue(basket.addOrder("BGLP",6));
        Assertions.assertEquals(1, basket.getOrders().size());
    }

    @Test
    void setFillingTest() {
        Basket basket = new Basket();
        basket.addOrder("BGLP",1);
        Assertions.assertEquals("none", basket.getOrders().get("BGLP").getKey().getFilligType());
        Assertions.assertFalse(basket.setFilling("re", "Bacon"));
        Assertions.assertFalse(basket.setFilling("BGLP", "er"));
        Assertions.assertEquals("none", basket.getOrders().get("BGLP").getKey().getFilligType());
        Assertions.assertTrue(basket.setFilling("BGLP", "Bacon"));
        Assertions.assertEquals("Bacon", basket.getOrders().get("BGLP").getKey().getFilligType());
    }

    @Test
    void getTotalCost() {
        Basket basket = new Basket();
        Assertions.assertEquals(0, basket.getTotalCost());
        basket.addOrder("BGLP", 1);
        Assertions.assertEquals(0.39f, basket.getTotalCost());
        basket.setFilling("BGLP", "Bacon");
        Assertions.assertEquals(0.51f, basket.getTotalCost());
        basket.addOrder("BGLO", 2);
        Assertions.assertEquals(1.49f, basket.getTotalCost());

    }
}