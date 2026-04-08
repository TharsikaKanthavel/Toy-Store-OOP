package com.toystore.app.service;

import com.toystore.app.datastructure.LinkedList;
import com.toystore.app.model.Category;
import com.toystore.app.model.Toy;
import com.toystore.app.util.SortingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for managing toy-related operations
 * Uses the custom LinkedList implementation for toy storage
 */
@Service
public class ToyService {
    private static final Logger logger = LoggerFactory.getLogger(ToyService.class);

    @Autowired
    private FileStorageService fileStorageService;

    private LinkedList toyList;

    /**
     * Initialize the toy service
     * This method is called after dependency injection is complete
     */
    @PostConstruct
    public void init() {
        // Initialize the linked list
        toyList = new LinkedList();

        // Load toys from storage
        loadToysFromStorage();
    }

    /**
     * Load toys from storage into the linked list
     */
    private void loadToysFromStorage() {
        logger.info("Loading toys from storage");
        List<Toy> toys = fileStorageService.loadToys();

        // Clear existing toys
        toyList.clear();

        // Add toys to the linked list
        for (Toy toy : toys) {
            toyList.add(toy);
        }

        logger.info("Loaded {} toys from storage", toyList.size());
    }

    /**
     * Save toys from the linked list to storage
     */
    private void saveToysToStorage() {
        List<Toy> toys = toyList.toList();
        fileStorageService.saveToys(toys);
    }

    /**
     * Get all toys
     * @return List of all toys
     */
    public List<Toy> getAllToys() {
        return toyList.toList();
    }

    /**
     * Get a toy by its ID
     * @param id The ID of the toy to retrieve
     * @return The toy with the specified ID, or null if not found
     */
    public Toy getToyById(String id) {
        return toyList.getById(id);
    }

    /**
     * Add a new toy
     * @param toy The toy to add
     * @return The added toy
     */
    public Toy addToy(Toy toy) {
        toyList.add(toy);
        saveToysToStorage();
        return toy;
    }

    /**
     * Update an existing toy
     * @param toy The toy with updated values
     * @return true if the toy was updated, false if not found
     */
    public boolean updateToy(Toy toy) {
        boolean updated = toyList.update(toy);
        if (updated) {
            saveToysToStorage();
        }
        return updated;
    }

    /**
     * Delete a toy by its ID
     * @param id The ID of the toy to delete
     * @return true if the toy was deleted, false if not found
     */
    public boolean deleteToy(String id) {
        boolean deleted = toyList.remove(id);
        if (deleted) {
            saveToysToStorage();
        }
        return deleted;
    }

    /**
     * Get toys by category
     * @param category The category to filter by
     * @return List of toys in the specified category
     */
    public List<Toy> getToysByCategory(Category category) {
        return toyList.findAll(toy -> toy.getCategory() == category);
    }

    /**
     * Get toys by age group
     * @param minAge The minimum age to filter by
     * @return List of toys suitable for the specified minimum age
     */
    public List<Toy> getToysByAgeGroup(int minAge) {
        return toyList.findAll(toy -> toy.getAgeGroup() >= minAge);
    }

    /**
     * Search for toys by name or description
     * @param query The search query
     * @return List of toys matching the search query
     */
    public List<Toy> searchToys(String query) {
        String lowerQuery = query.toLowerCase();
        return toyList.findAll(toy ->
                toy.getName().toLowerCase().contains(lowerQuery) ||
                        toy.getDescription().toLowerCase().contains(lowerQuery)
        );
    }

    /**
     * Get toys sorted by age group using the Selection Sort algorithm
     * @return List of toys sorted by age group
     */
    public List<Toy> getToysSortedByAgeGroup() {
        Toy[] toys = toyList.toArray();

        // Use Selection Sort to sort toys by age group
        SortingUtil.selectionSortByAgeGroup(toys);

        // Convert array back to list
        List<Toy> sortedToys = new ArrayList<>(toys.length);
        for (Toy toy : toys) {
            sortedToys.add(toy);
        }

        return sortedToys;
    }

    /**
     * Get a list of featured toys (first 3 toys as a simple implementation)
     * @return List of up to 3 featured toys
     */
    public List<Toy> getFeaturedToys() {
        List<Toy> allToys = toyList.toList();
        logger.info("Fetching featured toys, total available: {}, returning up to 3", allToys.size());
        return allToys.stream()
                .limit(3)
                .collect(Collectors.toList());
    }
}