package com.toystore.app.service;

import com.toystore.app.model.User;
import com.toystore.app.model.User.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private FileStorageService fileStorageService;

    private List<User> users = new ArrayList<>();
    private static final String USERS_FILE = "src/main/resources/data/users.json";

    @PostConstruct
    public void init() {
        loadUsers();

        // Create a default admin user if no users exist
        if (users.isEmpty()) {
            User adminUser = new User("admin", "admin123", "admin@toystore.com", "Admin", "User");
            adminUser.setRole(UserRole.ADMIN);
            users.add(adminUser);
            saveUsers();
            logger.info("Created default admin user: admin/admin123");
        }
    }

    /**
     * Load users from storage
     */
    private void loadUsers() {
        logger.info("Loading users from storage");
        List<User> loadedUsers = fileStorageService.loadUsers(USERS_FILE);
        if (loadedUsers != null) {
            this.users = loadedUsers;
            logger.info("Loaded {} users from storage", users.size());
        } else {
            this.users = new ArrayList<>();
            logger.info("No users found in storage, starting with empty list");
        }
    }

    /**
     * Save users to storage
     */
    private void saveUsers() {
        fileStorageService.saveUsers(users, USERS_FILE);
        logger.info("Saved {} users to storage", users.size());
    }

    /**
     * Register a new user
     * @param user The user to register
     * @return true if registration successful, false if username already exists
     */
    public boolean registerUser(User user) {
        // Check if username already exists
        if (users.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()))) {
            logger.warn("Username already exists: {}", user.getUsername());
            return false;
        }

        // Check if email already exists
        if (users.stream().anyMatch(u -> u.getEmail().equals(user.getEmail()))) {
            logger.warn("Email already exists: {}", user.getEmail());
            return false;
        }

        users.add(user);
        saveUsers();
        logger.info("User registered: {}", user.getUsername());
        return true;
    }

    /**
     * Authenticate a user
     * @param username The username
     * @param password The password
     * @return The authenticated user or null if authentication failed
     */
    public User authenticateUser(String username, String password) {
        Optional<User> userOpt = users.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst();

        if (userOpt.isPresent()) {
            logger.info("User authenticated: {}", username);
            return userOpt.get();
        } else {
            logger.warn("Authentication failed for user: {}", username);
            return null;
        }
    }

    /**
     * Get a user by ID
     * @param id The user ID
     * @return The user with the specified ID or null if not found
     */
    public User getUserById(String id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Get a user by username
     * @param username The username
     * @return The user with the specified username or null if not found
     */
    public User getUserByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    /**
     * Get all users
     * @return List of all users
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    /**
     * Get all users by role
     * @param role The role to filter by
     * @return List of users with the specified role
     */
    public List<User> getUsersByRole(UserRole role) {
        return users.stream()
                .filter(u -> u.getRole() == role)
                .collect(Collectors.toList());
    }

    /**
     * Update a user
     * @param user The user to update
     * @return true if update successful, false if user not found
     */
    public boolean updateUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())) {
                users.set(i, user);
                saveUsers();
                logger.info("User updated: {}", user.getUsername());
                return true;
            }
        }
        logger.warn("User not found for update: {}", user.getId());
        return false;
    }

    /**
     * Delete a user
     * @param id The ID of the user to delete
     * @return true if deletion successful, false if user not found
     */
    public boolean deleteUser(String id) {
        boolean removed = users.removeIf(u -> u.getId().equals(id));
        if (removed) {
            saveUsers();
            logger.info("User deleted: {}", id);
        } else {
            logger.warn("User not found for deletion: {}", id);
        }
        return removed;
    }
}