package com.bahadir.cagatay.bahadircagatayproject;

public class CoffeeBrand {
    private String coffeeBrand;
    private int imgId;

    public CoffeeBrand(String coffeeBrand, int imgId) {
        this.coffeeBrand = coffeeBrand;
        this.imgId = imgId;
    }
    public CoffeeBrand(String coffeeBrand) {
        this.coffeeBrand = coffeeBrand;

    }

    public String getCoffeeBrand() {
        return coffeeBrand;
    }

    public void setCoffeeBrand(String coffeeBrand) {
        this.coffeeBrand = coffeeBrand;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
