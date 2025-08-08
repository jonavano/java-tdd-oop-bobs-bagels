package com.booleanuk.core;

public class Bagel {

    String bagelType;
    String filligType;

    StoreManager manager;

    public Bagel(String bagelType) {
        manager = new StoreManager();

        setBagelType(bagelType);
        setFilligType("none");

    }

    public boolean setBagelType(String bagelType) {
        if (manager.getBagelPrices().containsKey(bagelType)) {
            this.bagelType = bagelType;
            return true;
        }
        this.bagelType = "BGLP";
        return false;
    }

    public String getBagelType() {
        return bagelType;
    }

    public boolean setFilligType(String filligType) {
        if (manager.getFillingPrices().containsKey(filligType)) {
            this.filligType = filligType;
            return true;
        }
        this.filligType = "none";
        return false;
    }

    public String getFilligType(){
        return filligType;
    }
}
