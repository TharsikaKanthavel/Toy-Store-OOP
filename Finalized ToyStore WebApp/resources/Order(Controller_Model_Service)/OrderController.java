package com.toystore.app.controller;

import com.toystore.app.model.CartItem;
import com.toystore.app.model.Order;
import com.toystore.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public String viewOrders(Model model, HttpSession session) {
        String userId = getUserIdFromSession(session);
        if (userId == null) {
            return "redirect:/user/login";
        }
        List<Order> userOrders = orderService.getOrdersByUserId(userId);
        model.addAttribute("orders", userOrders);
        model.addAttribute("pageTitle", "Your Orders");
        return "orders";
    }

    @PostMapping("/place")
    @ResponseBody
    public String placeOrder(HttpSession session) {
        String userId = getUserIdFromSession(session);
        if (userId == null) {
            return "Please log in to place an order";
        }
        try {
            Order order = orderService.placeOrder(userId);
            if (order == null) {
                return "Cart is empty";
            }
            return "Order placed successfully";
        } catch (Exception e) {
            return "Error placing order: " + e.getMessage();
        }
    }

    @GetMapping("/api/orders")
    @ResponseBody
    public List<Order> getOrders(HttpSession session) {
        String userId = getUserIdFromSession(session);
        if (userId == null) {
            return new ArrayList<>();
        }
        return orderService.getOrdersByUserId(userId);
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
        return user.toString();
    }
}