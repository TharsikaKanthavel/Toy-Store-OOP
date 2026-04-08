package com.toystore.app.model;

public class CartItem {
    private String toyId;
    private int quantity;
    private double price;

    // Default constructor
    public CartItem() {}

    // Parameterized constructor
    public CartItem(String toyId, int quantity, double price) {
        this.toyId = toyId;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public String getToyId() {
        return toyId;
    }

    public void setToyId(String toyId) {
        this.toyId = toyId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "toyId='" + toyId + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}