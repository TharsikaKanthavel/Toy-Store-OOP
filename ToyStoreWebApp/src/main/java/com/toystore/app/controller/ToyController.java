package com.toystore.app.controller;

import com.toystore.app.model.Toy;
import com.toystore.app.model.Category;
import com.toystore.app.model.User;
import com.toystore.app.service.ToyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Arrays;

/**
 * Controller for handling toy-related web requests
 * Provides endpoints for toy management operations
 */
@Controller
public class ToyController {
    private static final Logger logger = LoggerFactory.getLogger(ToyController.class);

    @Autowired
    private ToyService toyService;

    /**
     * Display the toys page with all toys
     */
    @GetMapping("/toys")
    public String toysPage(Model model) {
        model.addAttribute("toys", toyService.getAllToys());
        model.addAttribute("categories", Category.values());
        model.addAttribute("pageTitle", "All Toys");
        return "toys";
    }

    /**
     * Display the form for adding a new toy
     */
    @GetMapping("/toys/new")
    public String newToyForm(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        // Check if user is logged in and is an admin
        User user = (User) session.getAttribute("user");
        if (user == null || !user.isAdmin()) {
            redirectAttributes.addFlashAttribute("error", "You need admin privileges to add toys");
            return "redirect:/user/login";
        }

        model.addAttribute("toy", new Toy());
        model.addAttribute("categories", Category.values());
        model.addAttribute("pageTitle", "Add New Toy");
        model.addAttribute("formAction", "add");
        return "toy-form";
    }

    /**
     * Display the form for editing an existing toy
     */
    @GetMapping("/toys/edit/{id}")
    public String editToyForm(@PathVariable String id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        // Check if user is logged in and is an admin
        User user = (User) session.getAttribute("user");
        if (user == null || !user.isAdmin()) {
            redirectAttributes.addFlashAttribute("error", "You need admin privileges to edit toys");
            return "redirect:/user/login";
        }

        Toy toy = toyService.getToyById(id);
        if (toy == null) {
            return "redirect:/toys";
        }

        model.addAttribute("toy", toy);
        model.addAttribute("categories", Category.values());
        model.addAttribute("pageTitle", "Edit Toy");
        model.addAttribute("formAction", "edit");
        return "toy-form";
    }

    /**
     * Display the toy management page (admin only)
     */
    @GetMapping("/toys/manage")
    public String manageToys(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        // Check if user is logged in and is an admin
        User user = (User) session.getAttribute("user");
        if (user == null || !user.isAdmin()) {
            redirectAttributes.addFlashAttribute("error", "You need admin privileges to manage toys");
            return "redirect:/user/login";
        }

        model.addAttribute("toys", toyService.getAllToys());
        model.addAttribute("categories", Category.values());
        model.addAttribute("pageTitle", "Manage Toys");
        return "toy-management";
    }

    /**
     * API endpoint to get all toys
     */
    @GetMapping("/api/toys")
    @ResponseBody
    public ResponseEntity<List<Toy>> getAllToys(
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) String search) {

        List<Toy> toys;

        // Apply filters based on request parameters
        if (search != null && !search.isEmpty()) {
            toys = toyService.searchToys(search);
        } else if (category != null && !category.isEmpty()) {
            try {
                Category categoryEnum = Category.valueOf(category);
                toys = toyService.getToysByCategory(categoryEnum);
            } catch (IllegalArgumentException e) {
                logger.warn("Invalid category: {}", category);
                toys = toyService.getAllToys();
            }
        } else if (minAge != null) {
            toys = toyService.getToysByAgeGroup(minAge);
        } else {
            toys = toyService.getAllToys();
        }

        // Apply sorting if requested
        if ("age".equals(sort)) {
            toys = toyService.getToysSortedByAgeGroup();
        }

        return ResponseEntity.ok(toys);
    }

    /**
     * API endpoint to get a toy by ID
     */
    @GetMapping("/api/toys/{id}")
    @ResponseBody
    public ResponseEntity<Toy> getToyById(@PathVariable String id) {
        Toy toy = toyService.getToyById(id);
        if (toy != null) {
            return ResponseEntity.ok(toy);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * API endpoint to add a new toy
     */
    @PostMapping("/api/toys")
    @ResponseBody
    public ResponseEntity<Toy> addToy(@RequestBody Toy toy, HttpSession session) {
        // Check if user is logged in and is an admin
        User user = (User) session.getAttribute("user");
        if (user == null || !user.isAdmin()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Toy addedToy = toyService.addToy(toy);
        logger.info("Admin {} added new toy: {}", user.getUsername(), toy.getName());
        return new ResponseEntity<>(addedToy, HttpStatus.CREATED);
    }

    /**
     * API endpoint to update an existing toy
     */
    @PutMapping("/api/toys/{id}")
    @ResponseBody
    public ResponseEntity<Toy> updateToy(@PathVariable String id, @RequestBody Toy toy, HttpSession session) {
        // Check if user is logged in and is an admin
        User user = (User) session.getAttribute("user");
        if (user == null || !user.isAdmin()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        toy.setId(id); // Ensure ID matches path variable
        boolean updated = toyService.updateToy(toy);
        if (updated) {
            logger.info("Admin {} updated toy: {}", user.getUsername(), toy.getName());
            return ResponseEntity.ok(toy);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * API endpoint to delete a toy
     */
    @DeleteMapping("/api/toys/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteToy(@PathVariable String id, HttpSession session) {
        // Check if user is logged in and is an admin
        User user = (User) session.getAttribute("user");
        if (user == null || !user.isAdmin()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Toy toy = toyService.getToyById(id);
        if (toy == null) {
            return ResponseEntity.notFound().build();
        }

        boolean deleted = toyService.deleteToy(id);
        if (deleted) {
            logger.info("Admin {} deleted toy: {}", user.getUsername(), toy.getName());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * API endpoint to get all categories
     */
    @GetMapping("/api/categories")
    @ResponseBody
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(Arrays.asList(Category.values()));
    }
}