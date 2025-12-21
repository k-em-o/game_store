// WishlistService.java
package com.game_store.services;

import java.util.ArrayList;
import java.util.List;

import com.game_store.models.Game;
import com.game_store.models.WishlistItem;

public class WishlistService {

    // جلب جميع الألعاب في الويشليست
    public static List<Game> getWishlistGames(String userId) {
        List<WishlistItem> wishlist = ApiClient.getWishlist(userId);
        List<Game> games = new ArrayList<>();

        for (WishlistItem item : wishlist) {
            Game game = ApiClient.getGameById(item.getGame_id()); // جلب بيانات اللعبة من جدول Games
            if (game != null) {
                games.add(game);
            }
        }

        return games;
    }

    // ===== ADD ITEM =====
    public static boolean addItem(String userId, String gameId) {
        boolean success = ApiClient.addToWishlist(userId, gameId);
        return success;
    }

    // ===== REMOVE ITEM =====
    public static boolean removeItem(String userId, String wishlistId) {
        boolean success = ApiClient.removeFromWishlist(wishlistId);
        return success;
    }

    // ===== ADD TO CART =====
    public static void addToCart(Game game) {
        System.out.println("Added to cart: " + game.getTitle());
    }
}
