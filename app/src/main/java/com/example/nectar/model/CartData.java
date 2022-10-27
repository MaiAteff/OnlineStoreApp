package com.example.nectar.model;

public class CartData {
    public String image;
    public String title;
    public int count;
    public float price;

    public CartData(String image, String title, int count, float price) {
        this.image = image;
        this.title = title;
        this.count = count;
        this.price = price;
    }
}
