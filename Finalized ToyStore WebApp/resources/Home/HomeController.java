package com.toystore.app.controller;

import com.toystore.app.model.Toy;
import com.toystore.app.model.Category;
import com.toystore.app.service.ToyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Controller for handling home page related requests
 */
@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ToyService toyService;

    /**
     * Display the home page with featured toys and categories
     */
    @GetMapping("/")
    public String homePage(Model model) {
        logger.info("Loading home page");
        List<Toy> featuredToys = toyService.getFeaturedToys();
        model.addAttribute("featuredToys", featuredToys);
        model.addAttribute("categories", Category.values());
        model.addAttribute("pageTitle", "Home");
        logger.info("Featured toys loaded: {}", featuredToys.size());
        return "index";
    }
}