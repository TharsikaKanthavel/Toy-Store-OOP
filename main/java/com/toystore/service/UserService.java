package com.toystore.service;

import com.toystore.dao.UserDao;
import com.toystore.model.User;
import java.io.IOException;

public class UserService {
    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public User registerUser(String username, String password, String email, String role) throws IOException {
        if (userDao.getUserByUsername(username) != null) {
            return null; // Username already exists
        }

        User user = new User(0, username, password, email, role);
        userDao.addUser(user);
        return user;
    }

    public User loginUser(String username, String password) throws IOException {
        User user = userDao.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User getUserById(int id) throws IOException {
        return userDao.getUserById(id);
    }

    public void updateUser(User user) throws IOException {
        userDao.updateUser(user);
    }

    public void deleteUser(int id) throws IOException {
        userDao.deleteUser(id);
    }
}