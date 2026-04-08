package com.toystore.app.controller;

import com.toystore.app.model.User;
import com.toystore.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * Display login form
     */
    @GetMapping("/login")
    public String showLoginForm(Model model, HttpSession session) {
        // If user is already logged in, redirect to home
        if (session.getAttribute("user") != null) {
            return "redirect:/";
        }

        model.addAttribute("loginRequest", new User());
        return "login";
    }

    /**
     * Process login form submission
     */
    @PostMapping("/login")
    public String processLogin(@ModelAttribute("loginRequest") User loginRequest,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        User user = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());

        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/user/login";
        }

        // Store user in session
        session.setAttribute("user", user);
        session.setAttribute("isAdmin", user.isAdmin());
        logger.info("User logged in: {}", user.getUsername());

        // Redirect to home page
        return "redirect:/";
    }

    /**
     * Logout user
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            logger.info("User logged out: {}", user.getUsername());
        }

        // Invalidate session
        session.invalidate();

        // Redirect to home page
        return "redirect:/";
    }

    /**
     * Display registration form
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model, HttpSession session) {
        // If user is already logged in, redirect to home
        if (session.getAttribute("user") != null) {
            return "redirect:/";
        }

        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * Process registration form submission
     */
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("user") User user,
                                      RedirectAttributes redirectAttributes) {
        boolean success = userService.registerUser(user);

        if (!success) {
            redirectAttributes.addFlashAttribute("error", "Username or email already exists");
            return "redirect:/user/register";
        }

        redirectAttributes.addFlashAttribute("success", "Registration successful! You can now log in.");
        logger.info("New user registered: {}", user.getUsername());

        // Redirect to login page
        return "redirect:/user/login";
    }

    /**
     * Display admin dashboard
     */
    @GetMapping("/admin")
    public String showAdminDashboard(Model model, HttpSession session) {
        // Check if user is logged in and is an admin
        User user = (User) session.getAttribute("user");
        if (user == null || !user.isAdmin()) {
            return "redirect:/user/login";
        }

        model.addAttribute("users", userService.getAllUsers());
        return "admin-dashboard";
    }

    /**
     * Process user deletion (admin only)
     */
    @PostMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") String id,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        // Check if user is logged in and is an admin
        User user = (User) session.getAttribute("user");
        if (user == null || !user.isAdmin()) {
            return "redirect:/user/login";
        }

        // Prevent deleting own account
        if (id.equals(user.getId())) {
            redirectAttributes.addFlashAttribute("error", "You cannot delete your own admin account");
            return "redirect:/user/admin";
        }

        boolean success = userService.deleteUser(id);

        if (success) {
            redirectAttributes.addFlashAttribute("success", "User deleted successfully");
            logger.info("Admin {} deleted user with ID: {}", user.getUsername(), id);
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to delete user");
        }

        return "redirect:/user/admin";
    }

    /**
     * Promote user to admin (admin only)
     */
    @PostMapping("/admin/promote/{id}")
    public String promoteUser(@PathVariable("id") String id,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        // Check if user is logged in and is an admin
        User user = (User) session.getAttribute("user");
        if (user == null || !user.isAdmin()) {
            return "redirect:/user/login";
        }

        User targetUser = userService.getUserById(id);

        if (targetUser == null) {
            redirectAttributes.addFlashAttribute("error", "User not found");
            return "redirect:/user/admin";
        }

        targetUser.setRole(User.UserRole.ADMIN);
        boolean success = userService.updateUser(targetUser);

        if (success) {
            redirectAttributes.addFlashAttribute("success",
                    "User " + targetUser.getUsername() + " promoted to admin");
            logger.info("Admin {} promoted user {} to admin", user.getUsername(), targetUser.getUsername());
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to promote user");
        }

        return "redirect:/user/admin";
    }

    /**
     * Display user profile
     */
    @GetMapping("/profile")
    public String showUserProfile(Model model, HttpSession session) {
        // Check if user is logged in
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }

        model.addAttribute("user", user);
        return "profile";
    }

    /**
     * Process profile update
     */
    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute("user") User updatedUser,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        // Check if user is logged in
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }

        // Update only allowed fields
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());

        // Only update password if a new one is provided
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            user.setPassword(updatedUser.getPassword());
        }

        boolean success = userService.updateUser(user);

        if (success) {
            // Update session with updated user
            session.setAttribute("user", user);
            redirectAttributes.addFlashAttribute("success", "Profile updated successfully");
            logger.info("User {} updated their profile", user.getUsername());
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to update profile");
        }

        return "redirect:/user/profile";
    }
}