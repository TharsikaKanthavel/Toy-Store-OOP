package com.toystore.app.controller;

import com.toystore.app.model.CartItem;
import com.toystore.app.model.Order;
import com.toystore.app.model.Toy;
import com.toystore.app.service.CartService;
import com.toystore.app.service.OrderService;
import com.toystore.app.service.ToyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cart")
public class CartController {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService cartService;
    @Autowired
    private ToyService toyService;
    @Autowired
    private OrderService orderService; // Autowire OrderService

    @GetMapping
    public String viewCart(Model model, HttpSession session) {
        String userId = getUserIdFromSession(session);
        if (userId == null) {
            return "redirect:/user/login";
        }
        Map<String, CartItem> cart = cartService.getCart(userId);
        model.addAttribute("cart", cart);
        model.addAttribute("pageTitle", "Your Cart");
        if (cart != null && !cart.isEmpty()) {
            Map<String, String> toyNames = cart.entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> toyService.getToyById(entry.getValue().getToyId()) != null
                                    ? toyService.getToyById(entry.getValue().getToyId()).getName()
                                    : "Unknown Toy"
                    ));
            double totalPrice = cart.values().stream().mapToDouble(CartItem::getPrice).sum();
            model.addAttribute("toyNames", toyNames);
            model.addAttribute("totalPrice", totalPrice);
        } else {
            model.addAttribute("totalPrice", 0.0);
        }
        return "cart";
    }

    @PostMapping("/add")
    @ResponseBody
    public String addToCart(@RequestParam String toyId, @RequestParam int quantity, HttpSession session) {
        String userId = getUserIdFromSession(session);
        if (userId == null) {
            return "Please log in to add items to cart";
        }
        try {
            Toy toy = toyService.getToyById(toyId);
            if (toy == null || toy.getQuantity() < quantity) {
                return "Invalid toy or insufficient quantity";
            }
            cartService.addItemToCart(userId, toyId, quantity, toy.getPrice());
            return "Item added to cart";
        } catch (Exception e) {
            return "Error adding to cart: " + e.getMessage();
        }
    }

    @PostMapping("/remove")
    @ResponseBody
    public String removeFromCart(@RequestParam String toyId, HttpSession session) {
        String userId = getUserIdFromSession(session);
        if (userId == null) {
            return "Please log in to remove items from cart";
        }
        boolean removed = cartService.removeItemFromCart(userId, toyId);
        if (removed) {
            return "Item removed from cart";
        }
        return "Item not found in cart";
    }

    @GetMapping("/api/cart")
    @ResponseBody
    public Map<String, CartItem> getCart(HttpSession session) {
        String userId = getUserIdFromSession(session);
        if (userId == null) {
            return Map.of();
        }
        return cartService.getCart(userId);
    }

    @PostMapping("/placeOrder")
    public String placeOrder(HttpSession session, RedirectAttributes redirectAttributes) {
        String userId = getUserIdFromSession(session);
        if (userId == null) {
            return "redirect:/user/login";
        }

        try {
            logger.info("Attempting to place order for userId: {}", userId);
            Order order = orderService.placeOrder(userId);
            if (order == null) {
                logger.warn("Order placement failed: Cart is empty or processing failed for userId: {}", userId);
                redirectAttributes.addFlashAttribute("error", "Cart is empty or order placement failed");
                return "redirect:/cart";
            }
            logger.info("Order placed successfully for userId: {} with orderId: {}", userId, order.getOrderId());
            redirectAttributes.addFlashAttribute("success", "Order placed successfully with orderId: " + order.getOrderId());
            return "redirect:/orders";
        } catch (Exception e) {
            logger.error("Error placing order for userId: {} - {}", userId, e.getMessage(), e);
            redirectAttributes.addFlashAttribute("error", "Error placing order: " + e.getMessage());
            return "redirect:/cart";
        }
    }

    private String getUserIdFromSession(HttpSession session) {
        Object user = session.getAttribute("user");
        if (user == null) {
            return null;
        }
        if (user instanceof com.toystore.app.model.User) {
            com.toystore.app.model.User userObj = (com.toystore.app.model.User) user;
            return userObj.getId();
        }
        return user.toString(); // Fallback for String username (e.g., "admin")
    }
}