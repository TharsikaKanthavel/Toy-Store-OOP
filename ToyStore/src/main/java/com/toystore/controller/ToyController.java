package com.toystore.controller;

import com.toystore.model.Toy;
import com.toystore.service.ToyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ToyController {

    @Autowired
    private ToyService toyService;

    /**
     * Home page
     */
    @GetMapping("/")
    public String home(Model model) {
        // Get featured toys
        List<Toy> featuredToys = toyService.getFeaturedToys();
        model.addAttribute("featuredToys", featuredToys);

        // Get all available categories
        Set<String> categories = toyService.getAllToys().stream()
                .map(Toy::getCategory)
                .collect(Collectors.toSet());
        model.addAttribute("categories", categories);

        return "index";
    }

    /**
     * View all toys
     */
    @GetMapping("/toys")
    public String viewAllToys(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String ageGroup,
            @RequestParam(required = false) String sort,
            Model model) {

        List<Toy> toys = toyService.getAllToys();

        // Filter by category if provided
        if (category != null && !category.isEmpty()) {
            toys = toys.stream()
                    .filter(toy -> toy.getCategory().equals(category))
                    .collect(Collectors.toList());
        }

        // Filter by age group if provided
        if (ageGroup != null && !ageGroup.isEmpty()) {
            toys = toys.stream()
                    .filter(toy -> toy.getAgeGroup().equals(ageGroup))
                    .collect(Collectors.toList());
        }

        // Sort toys if sort parameter is provided
        if (sort != null) {
            switch (sort) {
                case "price_asc":
                    toys.sort((t1, t2) -> Double.compare(t1.getPrice(), t2.getPrice()));
                    break;
                case "price_desc":
                    toys.sort((t1, t2) -> Double.compare(t2.getPrice(), t1.getPrice()));
                    break;
                case "name_asc":
                    toys.sort((t1, t2) -> t1.getName().compareTo(t2.getName()));
                    break;
                case "name_desc":
                    toys.sort((t1, t2) -> t2.getName().compareTo(t1.getName()));
                    break;
                default:
                    // No sorting
                    break;
            }
        }

        model.addAttribute("toys", toys);

        // Get all available categories and age groups for filters
        Set<String> categories = new HashSet<>();
        Set<String> ageGroups = new HashSet<>();

        for (Toy toy : toyService.getAllToys()) {
            categories.add(toy.getCategory());
            ageGroups.add(toy.getAgeGroup());
        }

        model.addAttribute("categories", categories);
        model.addAttribute("ageGroups", ageGroups);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("selectedAgeGroup", ageGroup);
        model.addAttribute("selectedSort", sort);

        return "toys";
    }

    /**
     * View toy details
     */
    @GetMapping("/toys/{id}")
    public String viewToyDetails(@PathVariable("id") String id, Model model) {
        Toy toy = toyService.getToyById(id);

        if (toy == null) {
            return "redirect:/toys";
        }

        model.addAttribute("toy", toy);

        // Get related toys (same category)
        List<Toy> relatedToys = toyService.getRelatedToys(toy);
        model.addAttribute("relatedToys", relatedToys);

        return "toy-detail";
    }

    /**
     * Search for toys
     */
    @GetMapping("/search")
    public String searchToys(@RequestParam("query") String query, Model model) {
        List<Toy> searchResults = toyService.searchToys(query);
        model.addAttribute("toys", searchResults);
        model.addAttribute("searchQuery", query);

        // Get all available categories and age groups for filters
        Set<String> categories = new HashSet<>();
        Set<String> ageGroups = new HashSet<>();

        for (Toy toy : toyService.getAllToys()) {
            categories.add(toy.getCategory());
            ageGroups.add(toy.getAgeGroup());
        }

        model.addAttribute("categories", categories);
        model.addAttribute("ageGroups", ageGroups);

        return "toys";
    }
}