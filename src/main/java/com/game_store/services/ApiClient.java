package com.game_store.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game_store.models.User;

public class ApiClient {
    private static final String SUPABASE_URL = "https://uxdfzosnrodoqvbwjupt.supabase.co";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InV4ZGZ6b3Nucm9kb3F2YndqdXB0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjAzNDY0NDcsImV4cCI6MjA3NTkyMjQ0N30.r6ujgy7nz8oYijnCZtoLKXslgPEd0d7c3GHzukYOR94";

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private static final ObjectMapper mapper = new ObjectMapper();

    // ==================== 📊 USERS ====================

    public static String getUsers() {
        return get("/rest/v1/users?select=*");
    }

    public static String getUserById(String userId) {
        return get("/rest/v1/users?select=*&id=eq." + userId);
    }

    public static User getUserByEmail(String email) {
        try {
            String json = get("/rest/v1/users?select=*&email=eq." + email);

            if (json == null || json.equals("[]"))
                return null;

            User[] users = mapper.readValue(json, User[].class);
            return users[0];

        } catch (Exception e) {
            System.out.println("❌ Error parsing user: " + e.getMessage());
            return null;
        }
    }

    public static String getUserByUsername(String username) {
        return get("/rest/v1/users?select=*&username=eq." + username);
    }

    public static boolean createUser(String jsonBody) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SUPABASE_URL + "/rest/v1/users"))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .header("Prefer", "return=representation")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("CREATE USER STATUS: " + response.statusCode());
            System.out.println("RESPONSE: " + response.body());

            return response.statusCode() == 201;

        } catch (Exception e) {
            System.out.println("❌ POST Failed: " + e.getMessage());
            return false;
        }
    }

    public static boolean updateUser(String userId, String jsonBody) {
        return patch("/rest/v1/users?id=eq." + userId, jsonBody);
    }

    public static boolean deleteUser(String userId) {
        return delete("/rest/v1/users?id=eq." + userId);
    }

    public static String login(String email, String password) {
        String endpoint = "/rest/v1/users?select=*&email=eq." + email + "&password=eq." + password;
        return get(endpoint);
    }

    // ==================== 🎮 GAMES ====================

    public static String getGames() {
        return get("/rest/v1/games?select=*");
    }

    public static String getPublishedGames() {
        return get("/rest/v1/games?select=*&is_published=eq.true");
    }

    public static String getGameById(String gameId) {
        return get("/rest/v1/games?select=*&id=eq." + gameId);
    }

    public static String getGamesByCategory(String categoryId) {
        return get("/rest/v1/games?select=*&category_id=eq." + categoryId);
    }

    public static String getDiscountedGames() {
        return get("/rest/v1/games?select=*&discount_percentage=gt.0");
    }

    public static String getPopularGames() {
        return get("/rest/v1/games?select=*&order=downloads_count.desc");
    }

    public static String searchGames(String query) {
        return get("/rest/v1/games?select=*&title=ilike.%25" + query + "%25");
    }

    public static boolean createGame(String jsonBody) {
        return post("/rest/v1/games", jsonBody);
    }

    public static boolean updateGame(String gameId, String jsonBody) {
        return patch("/rest/v1/games?id=eq." + gameId, jsonBody);
    }

    public static boolean deleteGame(String gameId) {
        return delete("/rest/v1/games?id=eq." + gameId);
    }

    // ==================== 🏷️ CATEGORIES ====================

    public static String getCategories() {
        return get("/rest/v1/categories?select=*");
    }

    public static String getCategoryById(String categoryId) {
        return get("/rest/v1/categories?select=*&id=eq." + categoryId);
    }

    public static boolean createCategory(String jsonBody) {
        return post("/rest/v1/categories", jsonBody);
    }

    public static boolean updateCategory(String categoryId, String jsonBody) {
        return patch("/rest/v1/categories?id=eq." + categoryId, jsonBody);
    }

    public static boolean deleteCategory(String categoryId) {
        return delete("/rest/v1/categories?id=eq." + categoryId);
    }

    // ==================== ⭐ REVIEWS ====================

    public static String getReviews() {
        return get("/rest/v1/reviews?select=*");
    }

    public static String getReviewsByGame(String gameId) {
        return get("/rest/v1/reviews?select=*&game_id=eq." + gameId);
    }

    public static String getReviewsByUser(String userId) {
        return get("/rest/v1/reviews?select=*&user_id=eq." + userId);
    }

    public static boolean createReview(String jsonBody) {
        return post("/rest/v1/reviews", jsonBody);
    }

    public static boolean updateReview(String reviewId, String jsonBody) {
        return patch("/rest/v1/reviews?id=eq." + reviewId, jsonBody);
    }

    public static boolean deleteReview(String reviewId) {
        return delete("/rest/v1/reviews?id=eq." + reviewId);
    }

    // ==================== ❤️ WISHLIST ====================

    public static String getWishlistByUser(String userId) {
        return get("/rest/v1/wishlist?select=*&user_id=eq." + userId);
    }

    public static String getWishlistWithGames(String userId) {
        return get("/rest/v1/wishlist?select=*,games(*)&user_id=eq." + userId);
    }

    public static boolean addToWishlist(String jsonBody) {
        return post("/rest/v1/wishlist", jsonBody);
    }

    public static boolean removeFromWishlist(String userId, String gameId) {
        return delete("/rest/v1/wishlist?user_id=eq." + userId + "&game_id=eq." + gameId);
    }

    // ==================== 🛒 ORDERS ====================

    public static String getOrders() {
        return get("/rest/v1/orders?select=*");
    }

    public static String getOrdersByUser(String userId) {
        return get("/rest/v1/orders?select=*&user_id=eq." + userId);
    }

    public static String getOrdersByStatus(String status) {
        return get("/rest/v1/orders?select=*&status=eq." + status);
    }

    public static boolean createOrder(String jsonBody) {
        return post("/rest/v1/orders", jsonBody);
    }

    public static boolean updateOrder(String orderId, String jsonBody) {
        return patch("/rest/v1/orders?id=eq." + orderId, jsonBody);
    }

    public static boolean deleteOrder(String orderId) {
        return delete("/rest/v1/orders?id=eq." + orderId);
    }

    // ==================== 📦 ORDER ITEMS ====================

    public static String getOrderItems(String orderId) {
        return get("/rest/v1/order_items?select=*&order_id=eq." + orderId);
    }

    public static String getOrderItemsWithGames(String orderId) {
        return get("/rest/v1/order_items?select=*,games(*)&order_id=eq." + orderId);
    }

    public static boolean createOrderItem(String jsonBody) {
        return post("/rest/v1/order_items", jsonBody);
    }

    public static boolean deleteOrderItem(String itemId) {
        return delete("/rest/v1/order_items?id=eq." + itemId);
    }

    // ==================== 💰 TRANSACTIONS ====================

    public static String getTransactions() {
        return get("/rest/v1/transactions?select=*");
    }

    public static String getTransactionsByUser(String userId) {
        return get("/rest/v1/transactions?select=*&user_id=eq." + userId);
    }

    public static boolean createTransaction(String jsonBody) {
        return post("/rest/v1/transactions", jsonBody);
    }

    public static boolean updateTransaction(String transactionId, String jsonBody) {
        return patch("/rest/v1/transactions?id=eq." + transactionId, jsonBody);
    }

    // ==================== 📸 GAME SCREENSHOTS ====================

    public static String getGameScreenshots(String gameId) {
        return get("/rest/v1/game_screenshots?select=*&game_id=eq." + gameId);
    }

    public static boolean createGameScreenshot(String jsonBody) {
        return post("/rest/v1/game_screenshots", jsonBody);
    }

    public static boolean updateGameScreenshot(String screenshotId, String jsonBody) {
        return patch("/rest/v1/game_screenshots?id=eq." + screenshotId, jsonBody);
    }

    public static boolean deleteGameScreenshot(String screenshotId) {
        return delete("/rest/v1/game_screenshots?id=eq." + screenshotId);
    }

    // ==================== 🔥 FEATURED GAMES ====================

    public static String getFeaturedGames() {
        return get("/rest/v1/featured_games?select=*");
    }

    public static String getFeaturedGamesWithDetails() {
        return get("/rest/v1/featured_games?select=*,games(*)&order=priority.desc");
    }

    public static boolean createFeaturedGame(String jsonBody) {
        return post("/rest/v1/featured_games", jsonBody);
    }

    public static boolean updateFeaturedGame(String featuredId, String jsonBody) {
        return patch("/rest/v1/featured_games?id=eq." + featuredId, jsonBody);
    }

    public static boolean deleteFeaturedGame(String featuredId) {
        return delete("/rest/v1/featured_games?id=eq." + featuredId);
    }

    // ==================== 🏷️ GAME CATEGORIES ====================

    public static String getGameCategories(String gameId) {
        return get("/rest/v1/game_categories?select=*&game_id=eq." + gameId);
    }

    public static String getCategoryGames(String categoryId) {
        return get("/rest/v1/game_categories?select=*&category_id=eq." + categoryId);
    }

    public static boolean createGameCategory(String jsonBody) {
        return post("/rest/v1/game_categories", jsonBody);
    }

    public static boolean deleteGameCategory(String gameId, String categoryId) {
        return delete("/rest/v1/game_categories?game_id=eq." + gameId + "&category_id=eq." + categoryId);
    }

    // ==================== 💬 COMMUNITY POSTS ====================

    public static String getCommunityPosts() {
        return get("/rest/v1/community_posts?select=*");
    }

    public static String getCommunityPostsByUser(String userId) {
        return get("/rest/v1/community_posts?select=*&user_id=eq." + userId);
    }

    public static String getPopularPosts() {
        return get("/rest/v1/community_posts?select=*&order=likes_count.desc");
    }

    public static boolean createCommunityPost(String jsonBody) {
        return post("/rest/v1/community_posts", jsonBody);
    }

    public static boolean updateCommunityPost(String postId, String jsonBody) {
        return patch("/rest/v1/community_posts?id=eq." + postId, jsonBody);
    }

    public static boolean deleteCommunityPost(String postId) {
        return delete("/rest/v1/community_posts?id=eq." + postId);
    }

    // ==================== 💬 COMMUNITY COMMENTS ====================

    public static String getPostComments(String postId) {
        return get("/rest/v1/community_comments?select=*&post_id=eq." + postId);
    }

    public static String getUserComments(String userId) {
        return get("/rest/v1/community_comments?select=*&user_id=eq." + userId);
    }

    public static boolean createCommunityComment(String jsonBody) {
        return post("/rest/v1/community_comments", jsonBody);
    }

    public static boolean updateCommunityComment(String commentId, String jsonBody) {
        return patch("/rest/v1/community_comments?id=eq." + commentId, jsonBody);
    }

    public static boolean deleteCommunityComment(String commentId) {
        return delete("/rest/v1/community_comments?id=eq." + commentId);
    }

    // ==================== 🏷️ GAME DISCOUNTS ====================

    public static String getActiveDiscounts() {
        return get("/rest/v1/game_discounts?select=*&start_date=lte.now()&end_date=gte.now()");
    }

    public static String getGameDiscounts(String gameId) {
        return get("/rest/v1/game_discounts?select=*&game_id=eq." + gameId);
    }

    public static boolean createGameDiscount(String jsonBody) {
        return post("/rest/v1/game_discounts", jsonBody);
    }

    public static boolean updateGameDiscount(String discountId, String jsonBody) {
        return patch("/rest/v1/game_discounts?id=eq." + discountId, jsonBody);
    }

    public static boolean deleteGameDiscount(String discountId) {
        return delete("/rest/v1/game_discounts?id=eq." + discountId);
    }

    // ==================== 🔧 CORE HTTP METHODS ====================

    private static String get(String endpoint) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SUPABASE_URL + endpoint))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .GET().build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 200 ? response.body() : null;

        } catch (Exception e) {
            System.out.println("❌ GET Failed: " + e.getMessage());
            return null;
        }
    }

    private static boolean post(String endpoint, String jsonBody) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SUPABASE_URL + endpoint))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .header("Prefer", "return=representation")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 201;

        } catch (Exception e) {
            System.out.println("❌ POST Failed: " + e.getMessage());
            return false;
        }
    }

    private static boolean patch(String endpoint, String jsonBody) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SUPABASE_URL + endpoint))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .header("Prefer", "return=representation")
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 200;

        } catch (Exception e) {
            System.out.println("❌ PATCH Failed: " + e.getMessage());
            return false;
        }
    }

    private static boolean delete(String endpoint) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SUPABASE_URL + endpoint))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .DELETE()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 200 || response.statusCode() == 204;

        } catch (Exception e) {
            System.out.println("❌ DELETE Failed: " + e.getMessage());
            return false;
        }
    }
}