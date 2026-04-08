package com.toystore.app.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private String orderId;
    private String userId;
    private List<CartItem> cartItems;
    private double totalPrice;
    private LocalDateTime orderDate;

    // No-args constructor for Jackson deserialization
    public Order() {
    }

    // Parameterized constructor for manual creation
    public Order(String userId, List<CartItem> cartItems, double totalPrice) {
        this.orderId = java.util.UUID.randomUUID().toString();
        this.userId = userId;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
        this.orderDate = LocalDateTime.now();
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", cartItems=" + cartItems +
                ", totalPrice=" + totalPrice +
                ", orderDate=" + orderDate +
                '}';
    }
}