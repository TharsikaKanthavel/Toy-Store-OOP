package com.toystore.app.service;

import com.toystore.app.model.CartItem;
import com.toystore.app.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private CartService cartService;

    private Map<String, List<Order>> ordersByUser = new HashMap<>();

    @PostConstruct
    public void init() {
        loadOrders();
    }

    private void loadOrders() {
        logger.info("Loading orders from storage");
        ordersByUser = fileStorageService.loadOrders();
        logger.info("Loaded orders for {} users from storage", ordersByUser.size());
    }

    private void saveOrders() {
        logger.info("Saving orders for {} users", ordersByUser.size());
        fileStorageService.saveOrders(ordersByUser);
    }

    public Order placeOrder(String userId) {
        Map<String, CartItem> cart = cartService.getCart(userId);
        if (cart == null || cart.isEmpty()) {
            logger.warn("No items in cart for userId={}", userId);
            return null;
        }
        double totalPrice = cart.values().stream().mapToDouble(CartItem::getPrice).sum();
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setUserId(userId);
        order.setCartItems(new ArrayList<>(cart.values()));
        order.setTotalPrice(totalPrice);
        order.setOrderDate(LocalDateTime.now());

        ordersByUser.computeIfAbsent(userId, k -> new ArrayList<>()).add(order);
        saveOrders();
        cartService.clearCart(userId);
        logger.info("Order placed for userId={} with orderId={}", userId, order.getOrderId());
        return order;
    }

    public List<Order> getOrdersByUserId(String userId) {
        return ordersByUser.getOrDefault(userId, new ArrayList<>());
    }
}