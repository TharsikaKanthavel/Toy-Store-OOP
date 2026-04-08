package com.toystore.service;

import com.toystore.datastructure.LinkedList;
import com.toystore.model.Toy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ToyService {

    private LinkedList<Toy> toys;

    @Autowired
    private FileService fileService;

    /**
     * Initialize toy data from file
     */
    @PostConstruct
    public void init() {
        toys = new LinkedList<>();
        List<String> toyLines = fileService.readToyFile();

        for (String line : toyLines) {
            try {
                Toy toy = Toy.fromFileString(line);
                toys.add(toy);
            } catch (Exception e) {
                System.err.println("Error parsing toy line: " + line);
                e.printStackTrace();
            }
        }

        // If no toys were loaded, add some sample toys
        if (toys.isEmpty()) {
            addSampleToys();
        }
    }

    /**
     * Returns all toys
     * @return all toys
     */
    public List<Toy> getAllToys() {
        List<Toy> toyList = new ArrayList<>();
        for (Toy toy : toys) {
            toyList.add(toy);
        }
        return toyList;
    }

    /**
     * Returns a toy by ID
     * @param id the ID to search for
     * @return the toy, or null if not found
     */
    public Toy getToyById(String id) {
        for (Toy toy : toys) {
            if (toy.getId().equals(id)) {
                return toy;
            }
        }
        return null;
    }

    /**
     * Returns featured toys
     * @return featured toys
     */
    public List<Toy> getFeaturedToys() {
        List<Toy> featuredToys = new ArrayList<>();
        for (Toy toy : toys) {
            if (toy.isFeatured()) {
                featuredToys.add(toy);
            }
        }
        return featuredToys;
    }

    /**
     * Returns toys related to the given toy (same category)
     * @param toy the reference toy
     * @return related toys (excluding the reference toy)
     */
    public List<Toy> getRelatedToys(Toy toy) {
        if (toy == null) {
            return new ArrayList<>();
        }

        List<Toy> relatedToys = new ArrayList<>();
        String category = toy.getCategory();

        for (Toy t : toys) {
            if (!t.getId().equals(toy.getId()) && t.getCategory().equals(category)) {
                relatedToys.add(t);
            }
        }

        return relatedToys;
    }

    /**
     * Searches for toys by name or description
     * @param query the search query
     * @return matching toys
     */
    public List<Toy> searchToys(String query) {
        if (query == null || query.isEmpty()) {
            return getAllToys();
        }

        String lowercaseQuery = query.toLowerCase();
        List<Toy> searchResults = new ArrayList<>();

        for (Toy toy : toys) {
            if (toy.getName().toLowerCase().contains(lowercaseQuery) ||
                    toy.getDescription().toLowerCase().contains(lowercaseQuery) ||
                    toy.getCategory().toLowerCase().contains(lowercaseQuery)) {
                searchResults.add(toy);
            }
        }

        return searchResults;
    }

    /**
     * Adds a new toy
     * @param toy the toy to add
     * @return the added toy
     */
    public Toy addToy(Toy toy) {
        if (toy.getId() == null || toy.getId().isEmpty()) {
            toy.setId(generateToyId());
        }

        toys.add(toy);
        saveToys();

        return toy;
    }

    /**
     * Updates an existing toy
     * @param toy the toy to update
     * @return the updated toy, or null if not found
     */
    public Toy updateToy(Toy toy) {
        for (int i = 0; i < toys.size(); i++) {
            Toy existingToy = toys.get(i);
            if (existingToy.getId().equals(toy.getId())) {
                toys.set(i, toy);
                saveToys();
                return toy;
            }
        }
        return null;
    }

    /**
     * Deletes a toy
     * @param id the ID of the toy to delete
     * @return true if the toy was deleted, false otherwise
     */
    public boolean deleteToy(String id) {
        Toy toyToDelete = getToyById(id);
        if (toyToDelete != null) {
            toys.remove(toyToDelete);
            saveToys();
            return true;
        }
        return false;
    }

    /**
     * Saves all toys to file
     */
    private void saveToys() {
        List<String> toyLines = new ArrayList<>();
        for (Toy toy : toys) {
            toyLines.add(toy.toFileString());
        }
        fileService.writeToyFile(toyLines);
    }

    /**
     * Generates a unique toy ID
     * @return a new toy ID
     */
    private String generateToyId() {
        return "T" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    /**
     * Adds sample toys if none exist
     */
    private void addSampleToys() {
        // Action Figures
        addToy(new Toy("T001", "Superhero Action Figure",
                "Poseable action figure with cape and accessories", 19.99,
                "Action Figures", "6-8 years", 25,
                "https://images.unsplash.com/photo-1608278047522-58806a6ac85b?w=300", true));

        addToy(new Toy("T002", "Space Explorer Figure",
                "Detailed astronaut figure with light-up helmet", 24.99,
                "Action Figures", "9-12 years", 18,
                "https://images.unsplash.com/photo-1511415582599-62585bd162c6?w=300", false));

        // Building Blocks
        addToy(new Toy("T003", "Classic Building Blocks",
                "Set of 100 colorful interlocking blocks", 29.99,
                "Building Blocks", "3-5 years", 30,
                "https://images.unsplash.com/photo-1587654780291-39c9404d746b?w=300", true));

        addToy(new Toy("T004", "Advanced Construction Set",
                "300-piece set with special connectors and gears", 49.99,
                "Building Blocks", "9-12 years", 12,
                "https://images.unsplash.com/photo-1558060370-d644479cb6f7?w=300", false));

        // Dolls
        addToy(new Toy("T005", "Fashion Doll",
                "Stylish doll with changeable outfits", 24.99,
                "Dolls", "6-8 years", 22,
                "https://images.unsplash.com/photo-1598464705532-6bc7235b079c?w=300", true));

        addToy(new Toy("T006", "Baby Doll Set",
                "Soft baby doll with accessories and carrier", 34.99,
                "Dolls", "3-5 years", 15,
                "https://images.unsplash.com/photo-1603662955674-c63cca734a0f?w=300", false));

        // Educational
        addToy(new Toy("T007", "Science Lab Kit",
                "Beginner chemistry set with 20 safe experiments", 39.99,
                "Educational", "9-12 years", 8,
                "https://images.unsplash.com/photo-1532094349884-543bc11b234d?w=300", true));

        addToy(new Toy("T008", "World Map Puzzle",
                "100-piece educational puzzle with country names", 19.99,
                "Educational", "6-8 years", 25,
                "https://images.unsplash.com/photo-1628569412108-ae8417241185?w=300", false));

        // Games
        addToy(new Toy("T009", "Strategy Board Game",
                "Family strategy game for 2-6 players", 34.99,
                "Games", "9-12 years", 20,
                "https://images.unsplash.com/photo-1611371805429-12b7e197ffbd?w=300", true));

        addToy(new Toy("T010", "Junior Card Game",
                "Easy to learn card game for young players", 12.99,
                "Games", "6-8 years", 35,
                "https://images.unsplash.com/photo-1606503153255-59d8b2e4739e?w=300", false));

        // Outdoor
        addToy(new Toy("T011", "Soccer Ball",
                "Junior size soccer ball with pump", 16.99,
                "Outdoor", "6-8 years", 40,
                "https://images.unsplash.com/photo-1575361204480-aadea25e6e68?w=300", false));

        addToy(new Toy("T012", "Kite Kit",
                "Easy to fly beginner kite with string and winder", 14.99,
                "Outdoor", "6-8 years", 18,
                "https://images.unsplash.com/photo-1475724899855-13aa0d51a5fc?w=300", true));

        // Plush Toys
        addToy(new Toy("T013", "Teddy Bear",
                "Soft classic teddy bear with ribbon", 19.99,
                "Plush Toys", "0-2 years", 30,
                "https://images.unsplash.com/photo-1588414734732-660b07304ddb?w=300", true));

        addToy(new Toy("T014", "Stuffed Dinosaur",
                "Plush green dinosaur with textured details", 22.99,
                "Plush Toys", "3-5 years", 20,
                "https://images.unsplash.com/photo-1609372332255-611485350f25?w=300", false));

        // Vehicles
        addToy(new Toy("T015", "Remote Control Car",
                "Fast RC car with rechargeable battery", 39.99,
                "Vehicles", "9-12 years", 15,
                "https://images.unsplash.com/photo-1609708536965-6e5b915b195b?w=300", true));

        addToy(new Toy("T016", "Wooden Train Set",
                "25-piece wooden train set with track", 29.99,
                "Vehicles", "3-5 years", 5,
                "https://images.unsplash.com/photo-1596461404969-9ae70f2830c1?w=300", false));

        // Save to file
        saveToys();
    }
}