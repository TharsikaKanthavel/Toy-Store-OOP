package com.toystore.controller;

import com.toystore.model.Toy;
import com.toystore.model.inventory.AgeGroupStats;
import com.toystore.model.inventory.CategoryStats;
import com.toystore.service.InventoryService;
import com.toystore.service.ToyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    @Autowired
    private ToyService toyService;

    @Autowired
    private InventoryService inventoryService;

    /**
     * Admin dashboard page
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Get inventory statistics
        int totalProducts = toyService.getAllToys().size();
        int totalStock = inventoryService.getTotalStock();
        int lowStockCount = inventoryService.getLowStockItems().size();

        // Add statistics to model
        model.addAttribute("totalProducts", totalProducts);
        model.addAttribute("totalStock", totalStock);
        model.addAttribute("lowStockCount", lowStockCount);

        // Get category and age group statistics
        List<CategoryStats> categoryStats = inventoryService.getCategoryStatistics();
        List<AgeGroupStats> ageGroupStats = inventoryService.getAgeGroupStatistics();

        model.addAttribute("categoryStats", categoryStats);
        model.addAttribute("ageGroupStats", ageGroupStats);

        // Get low stock items
        List<Toy> lowStockItems = inventoryService.getLowStockItems();
        model.addAttribute("lowStockItems", lowStockItems);

        return "admin/dashboard";
    }

    /**
     * Restock a specific toy
     */
    @PostMapping("/inventory/restock-item")
    public String restockToy(@RequestParam("toyId") String toyId,
                             @RequestParam(value = "quantity", defaultValue = "10") int quantity,
                             RedirectAttributes redirectAttributes) {
        // Validate quantity (minimum 1, maximum 100)
        int restockQuantity = Math.min(Math.max(quantity, 1), 100);

        Toy toy = toyService.getToyById(toyId);
        inventoryService.restockToy(toyId, restockQuantity);

        redirectAttributes.addFlashAttribute("successMessage",
                "Added " + restockQuantity + " units to inventory for \"" +
                        (toy != null ? toy.getName() : "Unknown Toy") + "\".");

        return "redirect:/admin/dashboard";
    }

    /**
     * Restock all low stock items
     */
    @GetMapping("/inventory/restock")
    public String restockAllLowStockItems(RedirectAttributes redirectAttributes) {
        inventoryService.restockLowStockItems();

        redirectAttributes.addFlashAttribute("successMessage",
                "All low stock items have been restocked successfully.");

        return "redirect:/admin/dashboard";
    }

    /**
     * Manage toys page
     */
    @GetMapping("/toys")
    public String manageToys(Model model) {
        List<Toy> toys = toyService.getAllToys();
        model.addAttribute("toys", toys);
        return "admin/manage-toys";
    }

    /**
     * Add toy form
     */
    @GetMapping("/toys/add")
    public String addToyForm(Model model) {
        model.addAttribute("toy", new Toy());
        return "admin/add-toy";
    }

    /**
     * Save a new toy
     */
    @PostMapping("/toys/add")
    public String saveToy(@ModelAttribute Toy toy, RedirectAttributes redirectAttributes) {
        toyService.addToy(toy);

        redirectAttributes.addFlashAttribute("successMessage",
                "Toy has been added successfully.");

        return "redirect:/admin/toys";
    }

    /**
     * Edit toy form
     */
    @GetMapping("/toys/edit/{id}")
    public String editToyForm(@PathVariable("id") String id, Model model) {
        Toy toy = toyService.getToyById(id);
        model.addAttribute("toy", toy);
        return "admin/edit-toy";
    }

    /**
     * Update an existing toy
     */
    @PostMapping("/toys/edit/{id}")
    public String updateToy(@PathVariable("id") String id,
                            @ModelAttribute Toy toy,
                            RedirectAttributes redirectAttributes) {
        toy.setId(id); // Ensure ID is set correctly
        toyService.updateToy(toy);

        redirectAttributes.addFlashAttribute("successMessage",
                "Toy has been updated successfully.");

        return "redirect:/admin/toys";
    }

    /**
     * Delete a toy
     */
    @GetMapping("/toys/delete/{id}")
    public String deleteToy(@PathVariable("id") String id,
                            RedirectAttributes redirectAttributes) {
        toyService.deleteToy(id);

        redirectAttributes.addFlashAttribute("successMessage",
                "Toy has been deleted successfully.");

        return "redirect:/admin/toys";
    }
}