package com.booleanuk.core;

public class Bagel {

    String bagelType;
    String filligType;

    public Bagel(String bagelType) {
        setBagelType(bagelType);
        setBagelType("none");
    }

    public boolean setBagelType(String bagelType) {
        this.bagelType = bagelType;
        return false;
    }

    public boolean setFilligType(String filligType) {
        this.filligType = filligType;
        return false;
    }
}
