package com.toystore.service;

import com.toystore.model.Toy;
import com.toystore.model.inventory.AgeGroupStats;
import com.toystore.model.inventory.CategoryStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private ToyService toyService;

    /**
     * Returns total stock across all toys
     * @return total stock
     */
    public int getTotalStock() {
        return toyService.getAllToys().stream()
                .mapToInt(Toy::getStockQuantity)
                .sum();
    }

    /**
     * Returns toys with low stock (5 or fewer)
     * @return low stock toys
     */
    public List<Toy> getLowStockItems() {
        return toyService.getAllToys().stream()
                .filter(toy -> toy.getStockQuantity() <= 5 && toy.getStockQuantity() > 0)
                .collect(Collectors.toList());
    }

    /**
     * Returns toys that are out of stock
     * @return out of stock toys
     */
    public List<Toy> getOutOfStockItems() {
        return toyService.getAllToys().stream()
                .filter(toy -> toy.getStockQuantity() == 0)
                .collect(Collectors.toList());
    }

    /**
     * Returns statistics grouped by category
     * @return category statistics
     */
    public List<CategoryStats> getCategoryStatistics() {
        Map<String, CategoryStats> statsMap = new HashMap<>();

        for (Toy toy : toyService.getAllToys()) {
            String category = toy.getCategory();

            if (!statsMap.containsKey(category)) {
                statsMap.put(category, new CategoryStats(category, 0, 0));
            }

            CategoryStats stats = statsMap.get(category);
            stats.setProductCount(stats.getProductCount() + 1);
            stats.setStockCount(stats.getStockCount() + toy.getStockQuantity());
        }

        return new ArrayList<>(statsMap.values());
    }

    /**
     * Returns statistics grouped by age group
     * @return age group statistics
     */
    public List<AgeGroupStats> getAgeGroupStatistics() {
        Map<String, AgeGroupStats> statsMap = new HashMap<>();

        for (Toy toy : toyService.getAllToys()) {
            String ageGroup = toy.getAgeGroup();

            if (!statsMap.containsKey(ageGroup)) {
                statsMap.put(ageGroup, new AgeGroupStats(ageGroup, 0, 0));
            }

            AgeGroupStats stats = statsMap.get(ageGroup);
            stats.setProductCount(stats.getProductCount() + 1);
            stats.setStockCount(stats.getStockCount() + toy.getStockQuantity());
        }

        return new ArrayList<>(statsMap.values());
    }

    /**
     * Restocks a specific toy
     * @param toyId the ID of the toy to restock
     * @param quantity the quantity to add
     * @return true if the toy was restocked, false otherwise
     */
    public boolean restockToy(String toyId, int quantity) {
        if (quantity <= 0) {
            return false;
        }

        Toy toy = toyService.getToyById(toyId);
        if (toy != null) {
            toy.setStockQuantity(toy.getStockQuantity() + quantity);
            toyService.updateToy(toy);
            return true;
        }

        return false;
    }

    /**
     * Restocks all low stock items (adds 10 to each)
     * @return the number of restocked items
     */
    public int restockLowStockItems() {
        List<Toy> lowStockItems = getLowStockItems();

        for (Toy toy : lowStockItems) {
            toy.setStockQuantity(toy.getStockQuantity() + 10);
            toyService.updateToy(toy);
        }

        return lowStockItems.size();
    }
}