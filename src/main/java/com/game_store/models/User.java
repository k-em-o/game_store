package com.game_store.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String email;
    private String username;
    private String full_name;
    private String country;
    private String password_hash;

    public User() {}

    public User(String email, String username, String full_name, String country, String password_hash) {
        this.email = email;
        this.username = username;
        this.full_name = full_name;
        this.country = country;
        this.password_hash = password_hash;
    }

    // ===== Getters & Setters =====
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getFull_name() { return full_name; }
    public void setFull_name(String full_name) { this.full_name = full_name; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getPassword_hash() { return password_hash; }
    public void setPassword_hash(String password_hash) { this.password_hash = password_hash; }
}
