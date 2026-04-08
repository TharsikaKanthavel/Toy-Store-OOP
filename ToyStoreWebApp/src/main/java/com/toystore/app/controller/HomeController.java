package com.toystore.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller for handling home page related requests
 */
@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * Display the home page
     */
    @GetMapping("/")
    public String homePage() {
        logger.info("Loading home page");
        return "index";
    }
}