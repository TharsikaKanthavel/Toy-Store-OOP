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
import java.util.stream.Collectors;

@Controller
public class ToyController {
    private static final Logger logger = LoggerFactory.getLogger(ToyController.class);

    @Autowired
    private ToyService toyService;

    @GetMapping("/toys")
    public String toysPage(Model model, HttpSession session,
                           @RequestParam(required = false) String category,
                           @RequestParam(required = false) String minAge,
                           @RequestParam(defaultValue = "default") String sort,
                           @RequestParam(required = false) String search) {
        List<Toy> toys = toyService.getAllToys();
        logger.info("Initial toy count: {}", toys.size());

        // Apply search filter first
        if (search != null && !search.trim().isEmpty()) {
            toys = toyService.searchToys(search.trim());
            logger.info("Toys after search '{}': {}", search, toys.size());
        }

        // Apply category filter
        if (category != null && !category.isEmpty()) {
            List<Category> categories = Arrays.stream(category.split(","))
                    .map(cat -> {
                        try {
                            return Category.valueOf(cat.toUpperCase());
                        } catch (IllegalArgumentException e) {
                            logger.warn("Invalid category: {}, skipping", cat);
                            return null;
                        }
                    })
                    .filter(cat -> cat != null)
                    .collect(Collectors.toList());

            if (!categories.isEmpty()) {
                toys = toys.stream()
                        .filter(t -> categories.contains(t.getCategory()))
                        .collect(Collectors.toList());
                logger.info("Toys after category filter: {}", toys.size());
            }
        }

        // Apply age group filter
        if (minAge != null && !minAge.isEmpty()) {
            List<Integer> minAges = Arrays.stream(minAge.split(","))
                    .map(age -> {
                        try {
                            return Integer.parseInt(age);
                        } catch (NumberFormatException e) {
                            logger.warn("Invalid minAge: {}, skipping", age);
                            return null;
                        }
                    })
                    .filter(age -> age != null)
                    .collect(Collectors.toList());

            if (!minAges.isEmpty()) {
                toys = toys.stream()
                        .filter(t -> minAges.stream().anyMatch(age -> t.getAgeGroup() >= age))
                        .collect(Collectors.toList());
                logger.info("Toys after age filter: {}", toys.size());
            }
        }

        // Apply sorting
        if ("age".equals(sort)) {
            toys = toyService.getToysSortedByAgeGroup();
            logger.info("Applied sorting by age");
        }

        model.addAttribute("toys", toys);
        model.addAttribute("categories", Category.values());
        model.addAttribute("pageTitle", "All Toys");
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        logger.info("Loading toys page, user: {}", user != null ? user.getUsername() : "Not logged in");
        return "toys";
    }

    // Rest of the controller remains unchanged
    @GetMapping("/toys/new")
    public String newToyForm(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
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

    @GetMapping("/toys/edit/{id}")
    public String editToyForm(@PathVariable String id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
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

    @GetMapping("/toys/manage")
    public String manageToys(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
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

    @GetMapping("/api/toys")
    @ResponseBody
    public ResponseEntity<List<Toy>> getAllToys(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String minAge,
            @RequestParam(defaultValue = "default") String sort) {
        List<Toy> toys = toyService.getAllToys();
        logger.info("Fetching toys with filters - category: {}, minAge: {}, sort: {}", category, minAge, sort);

        if (category != null && !category.isEmpty()) {
            List<Category> categories = Arrays.stream(category.split(","))
                    .map(cat -> {
                        try {
                            return Category.valueOf(cat.toUpperCase());
                        } catch (IllegalArgumentException e) {
                            logger.warn("Invalid category: {}, skipping", cat);
                            return null;
                        }
                    })
                    .filter(cat -> cat != null)
                    .collect(Collectors.toList());

            if (!categories.isEmpty()) {
                toys = toys.stream()
                        .filter(t -> categories.contains(t.getCategory()))
                        .collect(Collectors.toList());
            }
        }

        if (minAge != null && !minAge.isEmpty()) {
            List<Integer> minAges = Arrays.stream(minAge.split(","))
                    .map(age -> {
                        try {
                            return Integer.parseInt(age);
                        } catch (NumberFormatException e) {
                            logger.warn("Invalid minAge: {}, skipping", age);
                            return null;
                        }
                    })
                    .filter(age -> age != null)
                    .collect(Collectors.toList());

            if (!minAges.isEmpty()) {
                toys = toys.stream()
                        .filter(t -> minAges.stream().anyMatch(age -> t.getAgeGroup() >= age))
                        .collect(Collectors.toList());
            }
        }

        if ("age".equals(sort)) {
            toys = toyService.getToysSortedByAgeGroup();
        }

        logger.info("Returning {} toys after filtering", toys.size());
        return ResponseEntity.ok(toys);
    }

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

    @PostMapping("/api/toys")
    @ResponseBody
    public ResponseEntity<Toy> addToy(@RequestBody Toy toy, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || !user.isAdmin()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Toy addedToy = toyService.addToy(toy);
        logger.info("Admin {} added new toy: {}", user.getUsername(), toy.getName());
        return new ResponseEntity<>(addedToy, HttpStatus.CREATED);
    }

    @PutMapping("/api/toys/{id}")
    @ResponseBody
    public ResponseEntity<Toy> updateToy(@PathVariable String id, @RequestBody Toy toy, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || !user.isAdmin()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        toy.setId(id);
        boolean updated = toyService.updateToy(toy);
        if (updated) {
            logger.info("Admin {} updated toy: {}", user.getUsername(), toy.getName());
            return ResponseEntity.ok(toy);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/toys/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteToy(@PathVariable String id, HttpSession session) {
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

    @GetMapping("/api/categories")
    @ResponseBody
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(Arrays.asList(Category.values()));
    }
}