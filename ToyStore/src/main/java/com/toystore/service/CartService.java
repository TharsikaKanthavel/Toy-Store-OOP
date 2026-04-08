package com.toystore.service;

import com.toystore.model.Cart;
import com.toystore.model.Toy;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    /**
     * Adds a toy to the cart
     * @param cart the cart
     * @param toy the toy to add
     * @param quantity the quantity to add
     */
    public void addItem(Cart cart, Toy toy, int quantity) {
        if (cart == null || toy == null || quantity <= 0) {
            return;
        }

        // Check if there's enough stock
        if (toy.getStockQuantity() < quantity) {
            quantity = toy.getStockQuantity(); // Limit to available stock
        }

        if (quantity > 0) {
            cart.addItem(toy, quantity);
        }
    }

    /**
     * Updates the quantity of an item in the cart
     * @param cart the cart
     * @param toyId the ID of the toy to update
     * @param quantity the new quantity
     * @return true if the item was updated, false otherwise
     */
    public boolean updateItemQuantity(Cart cart, String toyId, int quantity) {
        if (cart == null || toyId == null || quantity < 0) {
            return false;
        }

        return cart.updateItemQuantity(toyId, quantity);
    }

    /**
     * Removes an item from the cart
     * @param cart the cart
     * @param toyId the ID of the toy to remove
     * @return true if the item was removed, false otherwise
     */
    public boolean removeItem(Cart cart, String toyId) {
        if (cart == null || toyId == null) {
            return false;
        }

        return cart.removeItem(toyId);
    }

    /**
     * Clears all items from the cart
     * @param cart the cart to clear
     */
    public void clearCart(Cart cart) {
        if (cart != null) {
            cart.clear();
        }
    }
}