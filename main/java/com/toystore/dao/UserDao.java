package com.toystore.dao;

import com.toystore.model.User;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class UserDao extends FileDao<User> {
    private static final String FILE_NAME = "users.txt"; // File to store user data

    public UserDao() {
        super(FILE_NAME);
    }

    @Override
    protected String convertToLine(User user) {
        if (user == null) return null;
        return String.join(",",
                String.valueOf(user.getId()),
                escapeNull(user.getUsername()),
                escapeNull(user.getEmail()),
                escapeNull(user.getPassword()),
                escapeNull(user.getRole())
        );
    }

    @Override
    protected User parseFromLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        String[] parts = line.split(",", -1); // Keep empty strings
        if (parts.length != 5) {
            return null;
        }

        try {
            int id = Integer.parseInt(parts[0]);
            String username = unescapeNull(parts[1]);
            String email = unescapeNull(parts[2]);
            String password = unescapeNull(parts[3]);
            String role = unescapeNull(parts[4]);

            // Validate required fields
            if (username == null || email == null || password == null) {
                return null;
            }

            return new User(id, username, email, password, role);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    protected int extractId(User user) {
        return user.getId();  // Implement the abstract method
    }

    public List<User> getAllUsers() throws IOException {
        return readAll().stream()
                .filter(Objects::nonNull)
                .toList();
    }

    public User getUserById(int id) throws IOException {
        return getAllUsers().stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public User getUserByUsername(String username) throws IOException {
        return getAllUsers().stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst()
                .orElse(null);
    }

    public synchronized void addUser(User user) throws IOException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        List<User> users = getAllUsers();
        // Check for duplicate username
        if (users.stream().anyMatch(u -> u.getUsername().equalsIgnoreCase(user.getUsername()))) {
            throw new IllegalArgumentException("Username already exists");
        }

        user.setId(getNextId(users));
        users.add(user);
        writeAll(users);
    }

    public void updateUser(User updatedUser) throws IOException {
        if (updatedUser == null) {
            throw new IllegalArgumentException("Updated user cannot be null");
        }

        List<User> users = getAllUsers();
        boolean found = false;

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == updatedUser.getId()) {
                users.set(i, updatedUser);
                found = true;
                break;
            }
        }

        if (!found) {
            throw new IllegalArgumentException("User with ID " + updatedUser.getId() + " not found");
        }

        writeAll(users);
    }

    public void deleteUser(int id) throws IOException {
        List<User> users = getAllUsers();
        boolean removed = users.removeIf(user -> user.getId() == id);

        if (!removed) {
            throw new IllegalArgumentException("User with ID " + id + " not found");
        }

        writeAll(users);
    }
}