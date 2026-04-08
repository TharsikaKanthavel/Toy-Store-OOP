package com.toystore.model;

import java.io.Serializable;

public class Toy implements Serializable {
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String category;

    // Constructors
    public Toy() {}

    public Toy(int id, String name, String description, double price, int quantity, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    @Override
    public String toString() {
        return id + "," + name + "," + description + "," + price + "," + quantity + "," + category;
    }
}