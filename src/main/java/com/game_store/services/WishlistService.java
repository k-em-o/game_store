package com.game_store.services;

import java.util.ArrayList;
import java.util.List;

import com.game_store.models.WishlistItem;

public class WishlistService {

    private List<WishlistItem> wishlist = new ArrayList<>();

    public WishlistService() {

        wishlist.add(new WishlistItem(
                "God of War",
                49.99,
                "/com/game_store/assets/gow.jpg"
        ));

    }

    public List<WishlistItem> getWishlist() {
        return wishlist;
    }

    public void removeItem(WishlistItem item) {
        wishlist.remove(item);
    }

    public void addToCart(WishlistItem item) {
        System.out.println(item.getTitle() + " added to cart!");
    }
}
