package com.game_store.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Game {

    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("developer_id")
    private String developerId;

    @JsonProperty("price")
    private String price;

    @JsonProperty("discount_percentage")
    private int discountPercentage;

    @JsonProperty("rating")
    private String rating;

    @JsonProperty("cover_image_url")
    private String coverImageUrl;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("category_id")
    private String categoryId;

    @JsonProperty("is_published")
    private boolean isPublished;

    @JsonProperty("downloads_count")
    private int downloadsCount;

    @JsonProperty("platform")
    private String platform;

    @JsonProperty("tags")
    private String[] tags;

    @JsonProperty("store_url")
    private String storeUrl;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    // ================= Getters =================
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getDeveloperId() { return developerId; }
    public double getPrice() { return Double.parseDouble(price); }
    public int getDiscount() { return discountPercentage; }
    public double getRating() { return Double.parseDouble(rating); }
    public String getCoverImage() { return coverImageUrl; }
    public String getReleaseDate() { return releaseDate; }
    public String getCategoryId() { return categoryId; }
    public boolean isPublished() { return isPublished; }
    public int getDownloadsCount() { return downloadsCount; }
    public String getPlatform() { return platform; }
    public String[] getTags() { return tags; }
    public String getStoreUrl() { return storeUrl; }
    public String getCreatedAt() { return createdAt; }
    public String getUpdatedAt() { return updatedAt; }

    // ================= Setters =================
    public void setId(String id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setDeveloperId(String developerId) { this.developerId = developerId; }
    public void setPrice(String price) { this.price = price; }
    public void setDiscount(int discountPercentage) { this.discountPercentage = discountPercentage; }
    public void setRating(String rating) { this.rating = rating; }
    public void setCoverImage(String coverImageUrl) { this.coverImageUrl = coverImageUrl; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }
    public void setPublished(boolean published) { isPublished = published; }
    public void setDownloadsCount(int downloadsCount) { this.downloadsCount = downloadsCount; }
    public void setPlatform(String platform) { this.platform = platform; }
    public void setTags(String[] tags) { this.tags = tags; }
    public void setStoreUrl(String storeUrl) { this.storeUrl = storeUrl; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}
