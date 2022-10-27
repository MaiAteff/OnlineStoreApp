package com.example.nectar.model;

import com.example.nectar.model.Rating;

public class Product {
    public int id;
    public String title;
    public float price;
    public String description;
    public String category;
    public String image;
    public Rating rating;

    public Product(int id, String title, float price, String description, String category, String image, Rating rating) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.image = image;
        this.rating = rating;
    }
}
