package com.booleanuk.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BasketTest {


    @Test
    void addAndGetOrders() {
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
    void setFilling() {
        Basket basket = new Basket();
        basket.addOrder("BGLP",1);
        Assertions.assertNull( basket.getOrders().get("BGLP").getKey().getFilling());
        Assertions.assertFalse(basket.setFilling("re", "Bacon"));
        Assertions.assertFalse(basket.setFilling("BGLP", "er"));
        Assertions.assertNull( basket.getOrders().get("BGLP").getKey().getFilling());
        Assertions.assertTrue(basket.setFilling("BGLP", "FILB"));
        Assertions.assertEquals("Bacon Filling", basket.getOrders().get("BGLPFILB").getKey().getFilling().getName());
        Assertions.assertTrue(basket.setFilling("BGLPFILB", "FILH"));
        Assertions.assertEquals("Ham Filling", basket.getOrders().get("BGLPFILH").getKey().getFilling().getName());


    }

    @Test
    void getTotalCost() {
        Basket basket = new Basket();
        Assertions.assertEquals(0, basket.getTotalCost());
        basket.addOrder("BGLP", 1);
        Assertions.assertEquals(0.39f, basket.getTotalCost());
        basket.setFilling("BGLP", "FILB");
        Assertions.assertEquals(0.39f, basket.getTotalCost());
        basket.addOrder("BGLO", 2);
        Assertions.assertEquals(1.37f, basket.getTotalCost());
    }

}