package com.game_store.models;


import java.math.BigDecimal;

public class Game {
    private String id;
    private String title;
    private String description;
    private BigDecimal price;
    private BigDecimal rating;
    private String platform;
    
    // Constructors, Getters, Setters
    public Game() {}
    
    public Game(String id, String title, String description, BigDecimal price, BigDecimal rating, String platform) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.platform = platform;
    }
    
    // Getters and Setters هنا
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    
    public BigDecimal getRating() { return rating; }
    public void setRating(BigDecimal rating) { this.rating = rating; }
    
    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }
    
    @Override
    public String toString() {
        return String.format("🎮 %s - $%.2f - ⭐%.1f - %s", title, price, rating, platform);
    }
}