package com.toystore.app.model;

import java.util.UUID;

public class Toy {
    private String id;
    private String name;
    private String description;
    private double price;
    private int ageGroup; // Age group in years (e.g., 3-5 would be stored as 3)
    private int quantity;
    private Category category;
    private String imageUrl;

    // Default constructor
    public Toy() {
        this.id = UUID.randomUUID().toString();
    }

    // Parameterized constructor
    public Toy(String name, String description, double price, int ageGroup, int quantity, Category category, String imageUrl) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
        this.ageGroup = ageGroup;
        this.quantity = quantity;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    // Constructor with ID (for loading from storage)
    public Toy(String id, String name, String description, double price, int ageGroup, int quantity, Category category, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.ageGroup = ageGroup;
        this.quantity = quantity;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(int ageGroup) {
        this.ageGroup = ageGroup;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Toy{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", ageGroup=" + ageGroup +
                ", quantity=" + quantity +
                ", category=" + category +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}