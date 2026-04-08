package com.toystore.dao;

import com.toystore.model.Toy;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ToyDao extends FileDao<Toy> {
    private static final String FILE_NAME = "toys.txt"; // Relative to application root

    public ToyDao() {
        super(FILE_NAME);
    }

    @Override
    protected String convertToLine(Toy toy) {
        if (toy == null) return null;
        return String.join(",",
                String.valueOf(toy.getId()),
                escapeNull(toy.getName()),
                escapeNull(toy.getDescription()),
                String.valueOf(toy.getPrice()),
                String.valueOf(toy.getQuantity()),
                escapeNull(toy.getCategory())
        );
    }

    @Override
    protected Toy parseFromLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        String[] parts = line.split(",", -1); // Keep empty strings
        if (parts.length != 6) {
            return null;
        }

        try {
            int id = Integer.parseInt(parts[0]);
            String name = unescapeNull(parts[1]);
            String description = unescapeNull(parts[2]);
            double price = Double.parseDouble(parts[3]);
            int quantity = Integer.parseInt(parts[4]);
            String category = unescapeNull(parts[5]);

            // Validate required fields
            if (name == null || category == null) {
                return null;
            }

            return new Toy(id, name, description, price, quantity, category);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    protected int extractId(Toy toy) {
        return toy.getId();
    }

    public List<Toy> getAllToys() throws IOException {
        return readAll().stream()
                .filter(Objects::nonNull)
                .toList();
    }

    public Toy getToyById(int id) throws IOException {
        return getAllToys().stream()
                .filter(toy -> toy.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public synchronized void addToy(Toy toy) throws IOException {
        if (toy == null) {
            throw new IllegalArgumentException("Toy cannot be null");
        }

        List<Toy> toys = getAllToys();
        // Check for duplicate name
        if (toys.stream().anyMatch(t -> t.getName().equalsIgnoreCase(toy.getName()))) {
            throw new IllegalArgumentException("Toy with this name already exists");
        }

        toy.setId(getNextId(toys));
        toys.add(toy);
        writeAll(toys);
    }

    public void updateToy(Toy updatedToy) throws IOException {
        if (updatedToy == null) {
            throw new IllegalArgumentException("Updated toy cannot be null");
        }

        List<Toy> toys = getAllToys();
        boolean found = false;

        for (int i = 0; i < toys.size(); i++) {
            if (toys.get(i).getId() == updatedToy.getId()) {
                toys.set(i, updatedToy);
                found = true;
                break;
            }
        }

        if (!found) {
            throw new IllegalArgumentException("Toy with ID " + updatedToy.getId() + " not found");
        }

        writeAll(toys);
    }

    public void deleteToy(int id) throws IOException {
        List<Toy> toys = getAllToys();
        boolean removed = toys.removeIf(toy -> toy.getId() == id);

        if (!removed) {
            throw new IllegalArgumentException("Toy with ID " + id + " not found");
        }

        writeAll(toys);
    }

    public List<Toy> searchToysByName(String name) throws IOException {
        return getAllToys().stream()
                .filter(toy -> toy.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    public List<Toy> getToysByCategory(String category) throws IOException {
        return getAllToys().stream()
                .filter(toy -> toy.getCategory().equalsIgnoreCase(category))
                .toList();
    }
}