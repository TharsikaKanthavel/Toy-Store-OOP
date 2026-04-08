package com.toystore.service;

import com.toystore.dao.ToyDao;
import com.toystore.model.Toy;
import java.io.IOException;
import java.util.List;

public class ToyService {
    private final ToyDao toyDao;

    public ToyService() {
        this.toyDao = new ToyDao();
    }

    public List<Toy> getAllToys() throws IOException {
        return toyDao.getAllToys();
    }

    public Toy getToyById(int id) throws IOException {
        return toyDao.getToyById(id);
    }

    public void addToy(Toy toy) throws IOException {
        toyDao.addToy(toy);
    }

    public void updateToy(Toy toy) throws IOException {
        toyDao.updateToy(toy);
    }

    public void deleteToy(int id) throws IOException {
        toyDao.deleteToy(id);
    }

    public List<Toy> searchToysByName(String name) throws IOException {
        return toyDao.searchToysByName(name);
    }

    public List<Toy> getToysByCategory(String category) throws IOException {
        return toyDao.getToysByCategory(category);
    }

    public List<String> getAllCategories() throws IOException {
        return toyDao.getAllToys().stream()
                .map(Toy::getCategory)
                .distinct()
                .toList();
    }
}