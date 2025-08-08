package com.booleanuk.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BagelTest {
    @Test
    void setBagelType() {
        Bagel bagel = new Bagel("BGLP");
        Bagel bagel1 = new Bagel("BGLO");
        Assertions.assertNotEquals(bagel.getBagelType(), bagel1.getBagelType());
        bagel1.setBagelType("BGLP");
        Assertions.assertEquals(bagel.getBagelType(), bagel1.getBagelType());
        bagel.setBagelType("wow");
        Assertions.assertEquals(bagel.getBagelType(), bagel1.getBagelType());

    }

    @Test
    void setFilligType() {
        Bagel bagel = new Bagel("BGLP");
        Bagel bagel1 = new Bagel("BGLP");
        Assertions.assertEquals(bagel.getFilligType(), bagel1.getFilligType());
        bagel.setFilligType("Bacon");
        Assertions.assertNotEquals(bagel.getFilligType(), bagel1.getFilligType());
        bagel.setFilligType("wow");
        Assertions.assertEquals(bagel.getFilligType(), bagel1.getFilligType());

    }
}
