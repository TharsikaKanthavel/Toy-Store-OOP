package com.toystore.app.model;

import java.util.UUID;

public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private UserRole role;
    private String firstName;
    private String lastName;
    private String fullName;
    private boolean admin;

    public enum UserRole {
        CUSTOMER,
        ADMIN
    }

    // Default constructor
    public User() {
        this.id = UUID.randomUUID().toString();
        this.role = UserRole.CUSTOMER; // Default role is customer
    }

    // Parameterized constructor
    public User(String username, String password, String email, String firstName, String lastName) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = UserRole.CUSTOMER; // Default role is customer
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Constructor with ID (for loading from storage)
    public User(String id, String username, String password, String email, UserRole role, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public boolean isAdmin() {
        return this.role == UserRole.ADMIN;
    }
}