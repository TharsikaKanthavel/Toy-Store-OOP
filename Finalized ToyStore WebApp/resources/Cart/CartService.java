package com.toystore.app.service;

import com.toystore.app.model.CartItem;
import com.toystore.app.model.Toy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class CartService {
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private ToyService toyService;

    private Map<String, Map<String, CartItem>> userCarts = new HashMap<>();




/* Loads all user carts using fileStorageService.

Replaces the current userCarts map with the loaded data.

Logs the event. */
    @PostConstruct
    public void init() {
        loadCarts();
    }

    private void loadCarts() {
        userCarts = fileStorageService.loadCart();
        logger.info("Loaded carts from storage");
    }




    /*   Persists the current state of all carts to a storage file (possibly JSON or database).

Logs the save operation.  */

    private void saveCarts() {
        fileStorageService.saveCart(userCarts);
        logger.info("Saved carts to storage");
    }




    public void addItemToCart(String userId, String toyId, int quantity, double price) {
        Map<String, CartItem> cart = userCarts.computeIfAbsent(userId, k -> new HashMap<>());
/*  If this user already has a cart, it fetches it.

If not, it creates a new empty cart (new HashMap<>) and adds it to userCart  */



        CartItem cartItem = cart.getOrDefault(toyId, new CartItem(toyId, 0, 0.0));
        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartItem.setPrice(price * cartItem.getQuantity()); // Update price based on quantity
        cart.put(toyId, cartItem);
        saveCarts();
        logger.info("Added to cart: userId={}, toyId={}, quantity={}", userId, toyId, quantity);
    }

    public Map<String, CartItem> getCart(String userId) {
        return new HashMap<>(userCarts.getOrDefault(userId, new HashMap<>()));
    }/*Returns a copy of the user's cart.

If the user has no cart, returns an empty one.

Prevents direct modification of the internal userCarts map.*/



    public void clearCart(String userId) {
        userCarts.remove(userId);
        saveCarts();
        logger.info("Cleared cart for userId={}", userId);
    }/*Completely removes the cart for the specified user.

Saves the updated data to storage.

Logs the cart clearance.*/



    public boolean removeItemFromCart(String userId, String toyId) {
        Map<String, CartItem> cart = userCarts.get(userId);//get the users cart

        if (cart != null && cart.remove(toyId) != null) {
            saveCarts();
            logger.info("Removed item from cart: userId={}, toyId={}", userId, toyId);
            return true;












        }//If the cart exists and the item is successfully removed, save and log it, and return true.



        logger.warn("Item not found in cart: userId={}, toyId={}", userId, toyId);
        return false;//If the item wasn't found, log a warning and return false.


    }
}