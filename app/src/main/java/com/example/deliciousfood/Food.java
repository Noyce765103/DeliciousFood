package com.example.deliciousfood;

public class Food {

    public static final int CHINESE_FOOD = 1;

    public static final int FAST_FOOD = 2;

    public static final int DESSERT_FOOD = 3;

    private String name;

    private int imgResID;

    private int price;

    private int type;

    private boolean isSpicy;

    private float rating;

    public Food(String name, int imgResID, int price, int type, boolean isSpicy, float rating) {
        this.name = name;
        this.imgResID = imgResID;
        this.price = price;
        this.type = type;
        this.isSpicy = isSpicy;
        this.rating = rating;
    }

    public Food() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgResID() {
        return imgResID;
    }

    public void setImgResID(int imgResID) {
        this.imgResID = imgResID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isSpicy() {
        return isSpicy;
    }

    public void setSpicy(boolean spicy) {
        isSpicy = spicy;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
