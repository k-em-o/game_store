package com.game_store.models;

public class User {
    private String id;
    private String email;
    private String username;
    private String fullName;
    private String country;
    
    // Constructors
    public User() {}
    
    public User(String id, String email, String username, String fullName, String country) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.fullName = fullName;
        this.country = country;
    }
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    
    @Override
    public String toString() {
        return String.format("👤 %s (%s) - %s", fullName, username, email);
    }
}