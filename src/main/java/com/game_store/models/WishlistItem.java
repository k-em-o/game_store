  
package com.game_store.models;

public class WishlistItem {

    private String id;
    private String user_id;
    private String game_id;
    private String created_at;

    // Default constructor لازم
    public WishlistItem() {}

    // Constructor كامل اختياري
    public WishlistItem(String id, String user_id, String game_id, String created_at) {
        this.id = id;
        this.user_id = user_id;
        this.game_id = game_id;
        this.created_at = created_at;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }

    public String getGame_id() { return game_id; }
    public void setGame_id(String game_id) { this.game_id = game_id; }

    public String getCreated_at() { return created_at; }
    public void setCreated_at(String created_at) { this.created_at = created_at; }
}
