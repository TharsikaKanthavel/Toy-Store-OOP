package com.toystore.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents a shopping cart
 */
public class Cart {

    private List<CartItem> items;

    /**
     * Default constructor
     */
    public Cart() {
        this.items = new ArrayList<>();
    }

    /**
     * Returns the items in this cart
     * @return the items
     */
    public List<CartItem> getItems() {
        return items;
    }

    /**
     * Sets the items in this cart
     * @param items the items to set
     */
    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    /**
     * Adds a toy to the cart
     * @param toy the toy to add
     * @param quantity the quantity to add
     */
    public void addItem(Toy toy, int quantity) {
        if (toy == null || quantity <= 0) {
            return;
        }

        // Check if the toy is already in the cart
        Optional<CartItem> existingItem = items.stream()
                .filter(item -> item.getToy().getId().equals(toy.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            // Update quantity of existing item
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            // Add new item
            CartItem newItem = new CartItem(toy, quantity);
            items.add(newItem);
        }
    }

    /**
     * Updates the quantity of an item in the cart
     * @param toyId the ID of the toy to update
     * @param quantity the new quantity
     * @return true if the item was updated, false otherwise
     */
    public boolean updateItemQuantity(String toyId, int quantity) {
        if (toyId == null || quantity < 0) {
            return false;
        }

        // If quantity is 0, remove the item
        if (quantity == 0) {
            return removeItem(toyId);
        }

        // Find and update the item
        Optional<CartItem> itemToUpdate = items.stream()
                .filter(item -> item.getToy().getId().equals(toyId))
                .findFirst();

        if (itemToUpdate.isPresent()) {
            itemToUpdate.get().setQuantity(quantity);
            return true;
        }

        return false;
    }

    /**
     * Removes an item from the cart
     * @param toyId the ID of the toy to remove
     * @return true if the item was removed, false otherwise
     */
    public boolean removeItem(String toyId) {
        if (toyId == null) {
            return false;
        }

        int initialSize = items.size();
        items.removeIf(item -> item.getToy().getId().equals(toyId));

        return items.size() < initialSize;
    }

    /**
     * Clears all items from the cart
     */
    public void clear() {
        items.clear();
    }

    /**
     * Returns the number of items in the cart (sum of quantities)
     * @return the number of items
     */
    public int getItemCount() {
        return items.stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

    /**
     * Returns the total price of all items in the cart
     * @return the total price
     */
    public double getTotalPrice() {
        return items.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    /**
     * Returns a formatted total price string
     * @return formatted total price string
     */
    public String getFormattedTotalPrice() {
        return String.format("$%.2f", getTotalPrice());
    }

    /**
     * Returns a cart item by toy ID
     * @param toyId the toy ID to search for
     * @return the cart item, or null if not found
     */
    public CartItem getItemById(String toyId) {
        if (toyId == null) {
            return null;
        }

        return items.stream()
                .filter(item -> item.getToy().getId().equals(toyId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                ", itemCount=" + getItemCount() +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }
}