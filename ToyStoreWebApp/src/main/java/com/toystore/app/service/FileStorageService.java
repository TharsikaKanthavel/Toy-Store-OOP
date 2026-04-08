package com.toystore.app.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.toystore.app.model.Toy;
import com.toystore.app.model.User;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for handling file-based storage operations
 * Responsible for reading from and writing to JSON files
 */
@Service
public class FileStorageService {
    private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);
    private static final String DATA_FILE = "data/toys.json";
    private final ObjectMapper objectMapper;

    public FileStorageService() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Create data directory if it doesn't exist
        try {
            Path dataDir = Paths.get("src/main/resources/data");
            if (!Files.exists(dataDir)) {
                Files.createDirectories(dataDir);
                logger.info("Created data directory: {}", dataDir);
            }
        } catch (IOException e) {
            logger.error("Failed to create data directory", e);
        }
    }

    /**
     * Load toys from the JSON file
     * @return List of toys read from the file
     */
    public List<Toy> loadToys() {
        File file = new File(DATA_FILE);

        if (!file.exists() || file.length() == 0) {
            logger.info("Data file does not exist or is empty: {}", DATA_FILE);
            return new ArrayList<>();
        }

        try {
            List<Toy> toys = objectMapper.readValue(file, new TypeReference<List<Toy>>() {});
            logger.info("Successfully loaded {} toys from {}", toys.size(), DATA_FILE);
            return toys;
        } catch (IOException e) {
            logger.error("Failed to read toys from file: {}", DATA_FILE, e);
            return new ArrayList<>();
        }
    }

    /**
     * Save toys to the JSON file
     * @param toys The list of toys to save
     * @return true if saving was successful, false otherwise
     */
    public boolean saveToys(List<Toy> toys) {
        File file = new File(DATA_FILE);

        try {
            objectMapper.writeValue(file, toys);
            logger.info("Successfully saved {} toys to {}", toys.size(), DATA_FILE);
            return true;
        } catch (IOException e) {
            logger.error("Failed to save toys to file: {}", DATA_FILE, e);
            return false;
        }
    }

    /**
     * Load users from a JSON file
     * @param filePath Path to the users JSON file
     * @return List of users read from the file
     */
    public List<User> loadUsers(String filePath) {
        File file = new File(filePath);

        if (!file.exists() || file.length() == 0) {
            logger.info("Users file does not exist or is empty: {}", filePath);
            return new ArrayList<>();
        }

        try {
            List<User> users = objectMapper.readValue(file, new TypeReference<List<User>>() {});
            logger.info("Successfully loaded {} users from {}", users.size(), filePath);
            return users;
        } catch (IOException e) {
            logger.error("Failed to read users from file: {}", filePath, e);
            return new ArrayList<>();
        }
    }

    /**
     * Save users to a JSON file
     * @param users The list of users to save
     * @param filePath Path to the users JSON file
     * @return true if saving was successful, false otherwise
     */
    public boolean saveUsers(List<User> users, String filePath) {
        File file = new File(filePath);

        try {
            // Ensure parent directories exist
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }

            objectMapper.writeValue(file, users);
            logger.info("Successfully saved {} users to {}", users.size(), filePath);
            return true;
        } catch (IOException e) {
            logger.error("Failed to save users to file: {}", filePath, e);
            return false;
        }
    }
}