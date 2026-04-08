package com.toystore.controller;

import com.toystore.model.Cart;
import com.toystore.model.CartItem;
import com.toystore.model.Toy;
import com.toystore.service.CartService;
import com.toystore.service.ToyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ToyService toyService;

    /**
     * Get the cart from session or create a new one
     */
    private Cart getOrCreateCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    /**
     * View cart page
     */
    @GetMapping
    public String viewCart(Model model, HttpSession session) {
        Cart cart = getOrCreateCart(session);
        model.addAttribute("cart", cart);
        model.addAttribute("cartItems", cart.getItems());
        model.addAttribute("totalPrice", cart.getTotalPrice());
        return "cart";
    }

    /**
     * Add an item to the cart (REST API)
     */
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addItemToCart(
            @RequestParam("toyId") String toyId,
            @RequestParam(value = "quantity", defaultValue = "1") int quantity,
            HttpSession session) {

        Cart cart = getOrCreateCart(session);
        Toy toy = toyService.getToyById(toyId);

        Map<String, Object> response = new HashMap<>();

        if (toy == null) {
            response.put("success", false);
            response.put("message", "Toy not found");
            return ResponseEntity.badRequest().body(response);
        }

        cartService.addItem(cart, toy, quantity);
        session.setAttribute("cart", cart); // Update session

        response.put("success", true);
        response.put("message", "Item added to cart");
        response.put("cartSize", cart.getItemCount());
        response.put("cartTotal", cart.getTotalPrice());

        return ResponseEntity.ok(response);
    }

    /**
     * Update cart item quantity (REST API)
     */
    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateCartItem(
            @RequestParam("toyId") String toyId,
            @RequestParam("quantity") int quantity,
            HttpSession session) {

        Cart cart = getOrCreateCart(session);
        cartService.updateItemQuantity(cart, toyId, quantity);
        session.setAttribute("cart", cart); // Update session

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("cartSize", cart.getItemCount());
        response.put("cartTotal", cart.getTotalPrice());

        // Get updated item info
        CartItem item = cart.getItemById(toyId);
        if (item != null) {
            response.put("itemTotal", item.getTotalPrice());
        }

        return ResponseEntity.ok(response);
    }

    /**
     * Remove an item from the cart (REST API)
     */
    @PostMapping("/remove")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> removeCartItem(
            @RequestParam("toyId") String toyId,
            HttpSession session) {

        Cart cart = getOrCreateCart(session);
        cartService.removeItem(cart, toyId);
        session.setAttribute("cart", cart); // Update session

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("cartSize", cart.getItemCount());
        response.put("cartTotal", cart.getTotalPrice());

        return ResponseEntity.ok(response);
    }

    /**
     * Clear the cart (REST API)
     */
    @PostMapping("/clear")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> clearCart(HttpSession session) {
        Cart cart = getOrCreateCart(session);
        cartService.clearCart(cart);
        session.setAttribute("cart", cart); // Update session

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);

        return ResponseEntity.ok(response);
    }
}