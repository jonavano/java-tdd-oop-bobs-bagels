package com.booleanuk.extension;

import com.booleanuk.core.Basket;
import com.booleanuk.core.PlainBagel;
import com.booleanuk.core.StoreManeger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExtentionBasketTest {

    @Test
    void getTotalCost() {
        ExtentionBasket basket = new ExtentionBasket();
        basket.addOrder("BGLO", 3);
        basket.setFilling("BGLO", "FILH");
        Assertions.assertEquals(1.83f, basket.getTotalCost());
        basket.addOrder("BGLP", 3);
        basket.setFilling("BGLP", "FILH");
        Assertions.assertEquals(3.36f, basket.getTotalCost(), 0.0001);
        basket.addOrder("BGLP", 11);
        Assertions.assertEquals(6.96f, basket.getTotalCost(), 0.0001);
    }

    @Test
    void countCostBundle() {
        ExtentionBasket basket = new ExtentionBasket();
        StoreManeger.setBasketSize(99, true);
        basket.addOrder("BGLP", 13);
        Assertions.assertEquals(4.38, basket.countCostBundle(new PlainBagel(), new BundledPlainBagle(), 13, 12), 0.001);
    }

    @Test
    void getReceipt() {
        ExtentionBasket basket = new ExtentionBasket();
        basket.addOrder("BGLO", 3);
        basket.setFilling("BGLO", "FILH");
        Assertions.assertEquals(2, basket.getReceipt().size());
        basket.addOrder("BGLP", 3);
        basket.setFilling("BGLP", "FILH");
        basket.addOrder("BGLP", 11);
        Assertions.assertEquals(4, basket.getReceipt().size());

    }

}