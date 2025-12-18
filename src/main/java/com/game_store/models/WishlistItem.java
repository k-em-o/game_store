package com.game_store.models;

public class WishlistItem {

    private String title;
    private double price;
    private String imagePath;

    public WishlistItem(String title, double price, String imagePath) {
        this.title = title;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }
}
