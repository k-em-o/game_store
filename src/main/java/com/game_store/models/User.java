// User.java
package com.game_store.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String id; // جديد: معرف المستخدم
    private String email;
    private String username;
    private String full_name;
    private String country;
    private String password_hash;
    private String user_role;
    private boolean is_developer;




    public User() {}

    public User(String id, String email, String username, String full_name, String country, String password_hash, String user_role) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.full_name = full_name;
        this.country = country;
        this.password_hash = password_hash;
        this.user_role = user_role;
    }

    // ===== Getters & Setters =====
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

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

    public String getUser_role() { return user_role; }
    public void setUser_role(String user_role) { this.user_role = user_role; }

    public boolean isIs_developer() { return is_developer; }
    public void setIs_developer(boolean is_developer) { this.is_developer = is_developer; }
}
